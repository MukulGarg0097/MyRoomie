package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.myRoomie.Entities.RazorOrderEntity;
import com.myRoomie.Repository.ITransactionRepository;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.response.RazorOrderResponse;
import com.razorpay.Order;

@Service
public class RazorOrderMapper {
	
	@Autowired
	ITransactionRepository transRepo;
	
	@Autowired
	TransactionMapper transMap;
	
	public RazorOrderMapper() {
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
	
	public RazorOrderResponse mapRazorOrderEntityToResponse(RazorOrderEntity entity)
	{
		RazorOrderResponse response=new RazorOrderResponse();
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
			response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
			response.setUpdated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
			if(!StringUtils.isEmpty(entity.getTransactionId()))
			{
				if(transRepo.existsById(entity.getTransactionId()))
					response.setTransaction(transMap.mapTransactionEntityToResponse(transRepo.getOne(entity.getTransactionId())));
			}
		return response;	
	}


	public List<RazorOrderResponse> mapRazorOrderEntityToResponse(List<RazorOrderEntity> entityList)
	{
		if(CollectionUtils.isEmpty(entityList)){
			return null;
		}
		List<RazorOrderResponse> response=new ArrayList<>();
		for(RazorOrderEntity entity:entityList)
		{
			response.add(mapRazorOrderEntityToResponse(entity));
		}
		return response;	
	}

	
}