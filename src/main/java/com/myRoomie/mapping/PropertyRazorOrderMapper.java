package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.myRoomie.Entities.RazorOrderEntity;
import com.myRoomie.Pojos.RazorPaymentResponsePojo;
import com.myRoomie.Repository.IPropertyTransactionRepository;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.response.PropertyRazorOrderResponse;
import com.razorpay.Order;

@Service
public class PropertyRazorOrderMapper {
	
	@Autowired
	IPropertyTransactionRepository transRepo;
	
	@Autowired
	PropertyTransactionMapper transMap;
	
	public PropertyRazorOrderMapper() {
	}
	
	public RazorOrderEntity mapOrderToRazorOrderEntity(Order order)
	{
		RazorOrderEntity entity=new RazorOrderEntity();
			if(ObjectUtils.isEmpty(order))
			{
				return null;
			}
			if(order.get("amount")!=null)
				entity.setAmount(order.get("amount"));
			if(order.get("amount_paid")!=null)
				entity.setAmountPaid(order.get("amount_paid"));
			if(order.get("notes").toString()!=null)
				entity.setNotes(order.get("notes").toString());
			entity.setRazorOrderCreatedAt(DateUtil.formateDate(order.get("created_at"), DateUtil.DB_TIMESTAMP_PATTERN));
			if(order.get("amount_due")!=null)
				entity.setAmountDue(order.get("amount_due"));
			if(!StringUtils.isEmpty(order.get("currency")))
				entity.setCurrency(order.get("currency"));
			if(!StringUtils.isEmpty(order.get("receipt")))
				entity.setTransactionId(Integer.valueOf(order.get("receipt")));
			if(!StringUtils.isEmpty(order.get("id")))
				entity.setRazorOrderId(order.get("id"));
			if(!StringUtils.isEmpty(order.get("entity")))
				entity.setEntity(order.get("entity"));
			if(order.get("offer_id")!=null)
				entity.setRazorOfferId(order.get("offer_id").toString());
			if(!StringUtils.isEmpty(order.get("status")))
				entity.setStatus(order.get("status"));
			if(!StringUtils.isEmpty(order.get("attempts")))
				entity.setAttempts(order.get("attempts"));
		return entity;	
	}
	
	public PropertyRazorOrderResponse mapRazorOrderEntityToResponse(RazorOrderEntity entity)
	{
		PropertyRazorOrderResponse response=new PropertyRazorOrderResponse();
			if(ObjectUtils.isEmpty(entity))
			{
				return null;
			}
			if(entity.getAmount()!=null || entity.getAmount()!=0)
				response.setAmount((entity.getAmount())/100);
			if(entity.getAmountPaid()!=null || entity.getAmountPaid()!= 0 )
				response.setAmountPaid((entity.getAmountPaid())/100);
			if(entity.getNotes()!=null)
				response.setNotes(entity.getNotes() );
			response.setRazorOrderCreatedAt(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
			if(entity.getAmountDue()!=null || entity.getAmountDue()!=0)
				response.setAmountDue((entity.getAmountDue())/100);
			if(!StringUtils.isEmpty(entity.getCurrency()))
				response.setCurrency(entity.getCurrency());
			if(!StringUtils.isEmpty(entity.getTransactionId()))
				response.setTransactionId(entity.getTransactionId());
			if(!StringUtils.isEmpty(entity.getRazorOrderId()))
				response.setRazorOrderId(entity.getRazorOrderId());
			if(!StringUtils.isEmpty(entity.getEntity()))
				response.setEntity(entity.getEntity());
			if(entity.getRazorOfferId()!=null)
				response.setRazorOfferId(entity.getRazorOfferId());
			if(!StringUtils.isEmpty(entity.getStatus()))
				response.setStatus(entity.getStatus());
			if(!StringUtils.isEmpty(entity.getAttempts()))
				response.setAttempts(entity.getAttempts());
			if(!StringUtils.isEmpty(entity.getId()))
				response.setId(entity.getId());
			if(!StringUtils.isEmpty(entity.getRazorpayPaymentId()))
				response.setRazorpayPaymentId(entity.getRazorpayPaymentId());
			if(!StringUtils.isEmpty(entity.getRazorpaySignature()))
				response.setRazorpaySignature(entity.getRazorpaySignature());
			response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
			response.setUpdated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
//			if(!StringUtils.isEmpty(entity.getTransactionId()))
//			{
//				if(transRepo.existsById(entity.getTransactionId()))
					response.setTransaction(transMap.mapTransactionEntityToResponse(transRepo.findById(entity.getTransactionId()).get()));
//			}
		return response;	
	}

	public RazorOrderEntity mapRazorPaymentResponseToRazorOrderEntity(RazorOrderEntity orderEntity
			, RazorPaymentResponsePojo paymentResponse) {
		
		orderEntity.setRazorpayPaymentId(paymentResponse.getId());
		orderEntity.setAmountRefunded(paymentResponse.getAmount_refunded());
		orderEntity.setBank(paymentResponse.getBank());
		orderEntity.setCardId(paymentResponse.getCard_id());
		orderEntity.setContact(paymentResponse.getContact());
		orderEntity.setCurrency(paymentResponse.getCurrency());
		orderEntity.setEmail(paymentResponse.getEmail());
		orderEntity.setEntity(paymentResponse.getEntity());
		orderEntity.setErrorCode(paymentResponse.getError_code());
		orderEntity.setErrorDescription(paymentResponse.getError_description());
		orderEntity.setFee(paymentResponse.getFee());
		orderEntity.setInvoiceId(paymentResponse.getInvoice_id());
		orderEntity.setPaymentDescription(paymentResponse.getDescription());
		orderEntity.setPaymentMethod(paymentResponse.getMethod());
		orderEntity.setRefundStatus(paymentResponse.getRefund_status());
		orderEntity.setStatus(paymentResponse.getStatus());
		orderEntity.setTax(paymentResponse.getTax());
		orderEntity.setVpa(paymentResponse.getVpa());
		orderEntity.setWallet(paymentResponse.getWallet());
		orderEntity.setCaptured(paymentResponse.getCaptured());
		orderEntity.setPaymentCreatedAt(paymentResponse.getCreated_at());
		orderEntity.setInternational(paymentResponse.getInternational());
		
		if(!ObjectUtils.isEmpty(orderEntity.getNotes()))
			orderEntity.setNotes(String.join(",", orderEntity.getNotes()));
		
		if(paymentResponse.getStatus().equalsIgnoreCase("captured")) {
			orderEntity.setAmountPaid(paymentResponse.getAmount());
			orderEntity.setAmountDue(orderEntity.getAmountDue()-paymentResponse.getAmount());
		}
		orderEntity.setStatus(paymentResponse.getStatus());
		
		return orderEntity;	
	}
	

	public List<PropertyRazorOrderResponse> mapRazorOrderEntityToResponse(List<RazorOrderEntity> entityList)
	{
		if(CollectionUtils.isEmpty(entityList)){
			return null;
		}
		List<PropertyRazorOrderResponse> response=new ArrayList<>();
		for(RazorOrderEntity entity:entityList)
		{
			response.add(mapRazorOrderEntityToResponse(entity));
		}
		return response;	
	}

	
}