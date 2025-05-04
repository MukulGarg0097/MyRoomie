package com.myRoomie.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RazorOrderEntity;
import com.myRoomie.Entities.TransactionEntity;
import com.myRoomie.Repository.IRazorOrderRepository;
import com.myRoomie.Repository.ITransactionRepository;
import com.myRoomie.Services.ITransactionService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.RazorOrderMapper;
import com.myRoomie.mapping.TransactionMapper;
import com.myRoomie.request.dto.TransactionRequest;
import com.myRoomie.response.dto.RazorOrderResponse;
import com.myRoomie.response.dto.TransactionResponse;

@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	ITransactionRepository transactionRepo;
	
	@Autowired
	TransactionMapper transactionMapper;
	
	@Autowired
	RazorOrderServiceImpl razorService;

	@Autowired
	IRazorOrderRepository razorOrderRepo;
	
	@Autowired
	RazorOrderMapper razorMap;
	
	@Override
	public RazorOrderResponse save(TransactionRequest request)  throws BaseException
	{
		TransactionEntity entity=transactionMapper.mapTransactionRequestToEntity(request);
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		TransactionResponse transaction= transactionMapper.mapTransactionEntityToResponse(transactionRepo.save(entity));
		RazorOrderResponse order=razorService.createOrder(transaction);
		if(ObjectUtils.isEmpty(order)) 
			return null;
		return order;
	}

	@Override
	public Page<RazorOrderResponse> findAll(Pageable pageable)  throws BaseException{
		Page<RazorOrderEntity> paging= razorOrderRepo.findAll(pageable);
		if(CollectionUtils.isEmpty(paging.getContent())) {
			return null;
		}
		Page<RazorOrderResponse> pagedResponse=new PageImpl<>(razorMap.mapRazorOrderEntityToResponse(paging.getContent()),pageable,paging.getTotalElements());
		return pagedResponse;
	}

	@Override
	public RazorOrderResponse findById(Integer id)  throws BaseException{
		RazorOrderEntity entity = new RazorOrderEntity();
		if(transactionRepo.existsById(id) && !CollectionUtils.isEmpty(razorOrderRepo.findByTransactionId(id)))
			entity = razorOrderRepo.findByTransactionId(id).get(0);
		else
			return null;
		return razorMap.mapRazorOrderEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		transactionRepo.deleteById(id);
		return true;
	}
	
	
}
