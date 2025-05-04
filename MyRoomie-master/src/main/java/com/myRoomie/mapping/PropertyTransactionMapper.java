package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.PropertyTransactionEntity;
import com.myRoomie.Services.IPropertyService;
import com.myRoomie.Services.IRoomService;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.PropertyTransactionRequest;
import com.myRoomie.response.dto.PropertyTransactionResponse;

@Service
public class PropertyTransactionMapper {
	
	public PropertyTransactionMapper() {
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

	public PropertyTransactionEntity mapTransactionRequestToEntity(PropertyTransactionRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		PropertyTransactionEntity response=new PropertyTransactionEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setAmount(request.getAmount());
		response.setPropertyId(request.getPropertyId());
		response.setQuantity(request.getQuantity());
		response.setDiscounts(DiscountMapper.mapDiscountRequestToEntity(request.getDiscounts()));
		response.setNetTotalAmount(request.getNetTotalAmount());
		response.setSubTotal(request.getSubTotal());
		response.setSubTotalAfterDiscount(request.getSubTotalAfterDiscount());
		response.setTaxes(TaxesMapper.mapTaxesRequestToEntity(request.getTaxes()));
		response.setTotalDiscountAmount(request.getTotalDiscountAmount());
		response.setTotalTaxAmount(request.getTotalTaxAmount());
		response.setCreatedByName(request.getCreatedByName());
		response.setCreatedByEmail(request.getCreatedByEmail());
		response.setContactNo(request.getContactNo());
		response.setDate(DateUtil.parseDate(request.getDate(), DateUtil.DB_DATE_PATTERN));
		return response;
	}
	
	public PropertyTransactionResponse mapTransactionEntityToResponse(PropertyTransactionEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		PropertyTransactionResponse response=new PropertyTransactionResponse();
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setAmount(entity.getAmount());
		response.setPropertyId(entity.getPropertyId());
		response.setQuantity(entity.getQuantity());
		response.setDiscounts(DiscountMapper.mapDiscountEntityToResponse(entity.getDiscounts()));
		response.setNetTotalAmount(entity.getNetTotalAmount());
		response.setSubTotal(entity.getSubTotal());
		response.setSubTotalAfterDiscount(entity.getSubTotalAfterDiscount());
		response.setTaxes(TaxesMapper.mapTaxesEntityToResponse(entity.getTaxes()));
		response.setTotalDiscountAmount(entity.getTotalDiscountAmount());
		response.setTotalTaxAmount(entity.getTotalTaxAmount());
		response.setPropertyId(response.getPropertyId());
		response.setProperty(propertyMapper.mapPropertyEntityToScheduledVisitsPojo(response.getPropertyId()));
		response.setCreatedByName(entity.getCreatedByName());
		response.setCreatedByEmail(entity.getCreatedByEmail());
		response.setContactNo(entity.getContactNo());
		response.setDate(DateUtil.formateDate(entity.getDate(), DateUtil.DB_DATE_PATTERN));
	return response;
	}
	
	public List<PropertyTransactionEntity> mapTransactionRequestToEntity(List<PropertyTransactionRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<PropertyTransactionEntity> entityList=new ArrayList<>();
		for(PropertyTransactionRequest request : requestList)
		{
			PropertyTransactionEntity response=new PropertyTransactionEntity();
			response = mapTransactionRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<PropertyTransactionResponse> mapTransactionEntityToResponse(List<PropertyTransactionEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<PropertyTransactionResponse> responseList=new ArrayList<>();
		for(PropertyTransactionEntity entity : entityList)
		{
			PropertyTransactionResponse response=new PropertyTransactionResponse();
			response = mapTransactionEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public Set<PropertyTransactionEntity> mapTransactionRequestToEntity(Set<PropertyTransactionRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<PropertyTransactionEntity> entitySet=new HashSet<>();
		for(PropertyTransactionRequest request : requestSet)
		{
			PropertyTransactionEntity response=new PropertyTransactionEntity();
			response = mapTransactionRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<PropertyTransactionResponse> mapTransactionEntityToResponse(Set<PropertyTransactionEntity> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<PropertyTransactionResponse> responseSet=new HashSet<>();
		for(PropertyTransactionEntity entity : entitySet)
		{
			PropertyTransactionResponse response=new PropertyTransactionResponse();
			response = mapTransactionEntityToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
}