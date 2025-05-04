package com.myRoomie.Services.ServicesImpl;

import com.google.gson.Gson;
import com.myRoomie.Entities.PropertyTransactionEntity;
import com.myRoomie.Entities.RazorOrderEntity;
import com.myRoomie.Pojos.RazorPaymentResponsePojo;
import com.myRoomie.Repository.IPropertyTransactionRepository;
import com.myRoomie.Repository.IRazorOrderRepository;
import com.myRoomie.Services.IPropertyTransactionService;
import com.myRoomie.Services.eventListner.EventPojo;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.PropertyRazorOrderMapper;
import com.myRoomie.mapping.PropertyTransactionMapper;
import com.myRoomie.request.PropertyTransactionSuccessRequest;
import com.myRoomie.request.dto.PropertyTransactionRequest;
import com.myRoomie.response.PropertyRazorOrderResponse;
import com.myRoomie.response.dto.PropertyTransactionResponse;
import com.razorpay.Payment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class PropertyTransactionServiceImpl implements IPropertyTransactionService {

    @Value("${com.razorpaysecretKey}")
    String razorSecret;

    @Autowired
    IPropertyTransactionRepository transactionRepo;

    @Autowired
    PropertyTransactionMapper transactionMapper;

    @Autowired
    PropertyRazorOrderServiceImpl razorService;

    @Autowired
    IRazorOrderRepository razorOrderRepo;

    @Autowired
    PropertyRazorOrderMapper razorMap;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public PropertyRazorOrderResponse save(PropertyTransactionRequest request) throws BaseException {
        PropertyTransactionEntity entity = transactionMapper.mapTransactionRequestToEntity(request);
        if (ObjectUtils.isEmpty(entity))
            return null;
        PropertyTransactionResponse transaction = transactionMapper.mapTransactionEntityToResponse(transactionRepo.save(entity));
        if (!ObjectUtils.isEmpty(transaction)) {
            PropertyRazorOrderResponse order = razorService.createOrder(transaction);
            if (ObjectUtils.isEmpty(order))
                return null;
            return order;
        }
        return null;
    }

    @Override
    public List<PropertyRazorOrderResponse> findAll(Pageable pageable) throws BaseException {
        Pageable pageabled = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("created").descending());
        Page<RazorOrderEntity> paging = razorOrderRepo.findAll(pageabled);
        if (CollectionUtils.isEmpty(paging.getContent())) {
            return null;
        }
        Page<PropertyRazorOrderResponse> pagedResponse = new PageImpl<>(razorMap.mapRazorOrderEntityToResponse(paging.getContent()), pageable, paging.getTotalElements());
        return pagedResponse.getContent();
    }

    @Override
    public PropertyRazorOrderResponse findById(Integer id) throws BaseException {
        RazorOrderEntity entity = new RazorOrderEntity();
        if (transactionRepo.existsById(id) && !CollectionUtils.isEmpty(razorOrderRepo.findByTransactionId(id)))
            entity = razorOrderRepo.findByTransactionId(id).get(0);
        else
            return null;
        return razorMap.mapRazorOrderEntityToResponse(entity);
    }

    @Override
    public PropertyRazorOrderResponse transactionSuccess(PropertyTransactionSuccessRequest request) throws BaseException {
        String razorOrderId = request.getRazorpayOrderId();
        RazorOrderEntity entity = razorOrderRepo.findByRazorOrderId(razorOrderId);
        List<Payment> orderPayment = razorService.getOrderById(razorOrderId);
        RazorPaymentResponsePojo paymentResponse = null;
        if (CollectionUtils.isEmpty(orderPayment)) {
            entity.setStatus("failed");
            entity.setRazorpayPaymentId(request.getRazorpayPaymentId());
            if (StringUtils.isEmpty(request.getRazorpaySignature()))
                entity.setRazorpaySignature(request.getRazorpaySignature());
        } else {
            Gson gson = new Gson();
            paymentResponse = gson.fromJson(orderPayment.get(orderPayment.size() - 1).toString(), RazorPaymentResponsePojo.class);
            if (!ObjectUtils.isEmpty(entity) && !ObjectUtils.isEmpty(paymentResponse)) {
                entity = razorMap.mapRazorPaymentResponseToRazorOrderEntity(entity, paymentResponse);
                if (!StringUtils.isEmpty(request.getRazorpaySignature()))
                    entity.setRazorpaySignature(request.getRazorpaySignature());
            }
        }
        razorOrderRepo.save(entity);
        // email send
        PropertyRazorOrderResponse orderResponse = razorMap.mapRazorOrderEntityToResponse(entity);
        sendEmail(orderResponse);
        return orderResponse;
    }

    @Override
    public Boolean deleteById(Integer id) throws BaseException {
//		transactionRepo.deleteById(id);
        return false;
    }

    private void sendEmail(PropertyRazorOrderResponse entity) {
        if (entity != null) {
            EventPojo emailObject = new EventPojo(this);
            emailObject.setRazorOrderResponse(entity);
            emailObject.setType(EventPojo.EventType.RAZOR_ORDER_TRANSACTION);
            eventPublisher.publishEvent(emailObject);
        } else
            System.out.println("Razor order response null");
    }

}
