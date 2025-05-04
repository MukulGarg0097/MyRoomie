package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.TransactionEntity;
import com.myRoomie.Services.IPropertyService;
import com.myRoomie.Services.IRoomService;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.TransactionRequest;
import com.myRoomie.response.dto.TransactionResponse;

@Service
public class TransactionMapper {
	
	public TransactionMapper() {
	}
	
	@Autowired
	IPropertyService propertyService;
	
	@Autowired
	IRoomService roomService;
	
	@Autowired
	PropertyMapper propertyMapper;
	
	@Autowired
	RoomMapper roomMapper;
	
	@Autowired
	RoomVariantMapper roomVariantMapper;

	public TransactionEntity mapTransactionRequestToEntity(TransactionRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		TransactionEntity response=new TransactionEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setRoomAmount(request.getRoomAmount());
		response.setRoomId(request.getRoomId());
		response.setRoomVariantId(request.getRoomVariantId());
		response.setQuantity(request.getQuantity());
		response.setDiscounts(DiscountMapper.mapDiscountRequestToEntity(request.getDiscounts()));
		response.setNetTotalAmount(request.getNetTotalAmount());
		response.setSubTotal(request.getSubTotal());
		response.setSubTotalAfterDiscount(request.getSubTotalAfterDiscount());
		response.setTaxes(TaxesMapper.mapTaxesRequestToEntity(request.getTaxes()));
		response.setTotalDiscountAmount(request.getTotalDiscountAmount());
		response.setTotalTaxAmount(request.getTotalTaxAmount());
		return response;
	}
	
	public TransactionResponse mapTransactionEntityToResponse(TransactionEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		TransactionResponse response=new TransactionResponse();
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setRoomAmount(entity.getRoomAmount());
		response.setRoomId(entity.getRoomId());
		response.setRoomVariantId(entity.getRoomVariantId());
		response.setQuantity(entity.getQuantity());
		response.setDiscounts(DiscountMapper.mapDiscountEntityToResponse(entity.getDiscounts()));
		response.setNetTotalAmount(entity.getNetTotalAmount());
		response.setSubTotal(entity.getSubTotal());
		response.setSubTotalAfterDiscount(entity.getSubTotalAfterDiscount());
		response.setTaxes(TaxesMapper.mapTaxesEntityToResponse(entity.getTaxes()));
		response.setTotalDiscountAmount(entity.getTotalDiscountAmount());
		response.setTotalTaxAmount(entity.getTotalTaxAmount());
		response.setRoom(roomMapper.mapRoomEntityToScheduledVisitsPojo(entity.getRoomId()));
		response.setPropertyId(response.getRoom().getPropertyId());
		response.setProperty(propertyMapper.mapPropertyEntityToScheduledVisitsPojo(response.getPropertyId()));
		response.setRoomVariant(roomVariantMapper.mapRoomVariantEntityToResponse(entity.getRoomVariantId()));
		return response;
	}
	
	public List<TransactionEntity> mapTransactionRequestToEntity(List<TransactionRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<TransactionEntity> entityList=new ArrayList<>();
		for(TransactionRequest request : requestList)
		{
			TransactionEntity response=new TransactionEntity();
			response = mapTransactionRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<TransactionResponse> mapTransactionEntityToResponse(List<TransactionEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<TransactionResponse> responseList=new ArrayList<>();
		for(TransactionEntity entity : entityList)
		{
			TransactionResponse response=new TransactionResponse();
			response = mapTransactionEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public Set<TransactionEntity> mapTransactionRequestToEntity(Set<TransactionRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<TransactionEntity> entitySet=new HashSet<>();
		for(TransactionRequest request : requestSet)
		{
			TransactionEntity response=new TransactionEntity();
			response = mapTransactionRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<TransactionResponse> mapTransactionEntityToResponse(Set<TransactionEntity> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<TransactionResponse> responseSet=new HashSet<>();
		for(TransactionEntity entity : entitySet)
		{
			TransactionResponse response=new TransactionResponse();
			response = mapTransactionEntityToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
}