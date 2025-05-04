package com.myRoomie.mapping;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.TaxesEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.TaxesRequest;
import com.myRoomie.response.TaxesResponse;

@Service
public class TaxesMapper {
	
	public TaxesMapper() {
	}
	
	public static TaxesEntity mapTaxesRequestToEntity(TaxesRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		TaxesEntity response=new TaxesEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setTaxAmount(request.getTaxAmount());
		response.setTaxPercent(request.getTaxPercent());
		response.setTaxUpon(request.getTaxUpon());
		response.setTaxName(request.getTaxName());
		return response;
	}
	
	public static TaxesResponse mapTaxesEntityToResponse(TaxesEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		TaxesResponse response=new TaxesResponse();
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setTaxAmount(entity.getTaxAmount());
		response.setTaxPercent(entity.getTaxPercent());
		response.setTaxUpon(entity.getTaxUpon());
		response.setTaxName(entity.getTaxName());
			return response;
	}
	
	public static Set<TaxesEntity> mapTaxesRequestToEntity(Set<TaxesRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		Set<TaxesEntity> entityList=new HashSet<>();
		for(TaxesRequest request : requestList)
		{
			TaxesEntity response=new TaxesEntity();
			response = mapTaxesRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public static Set<TaxesResponse> mapTaxesEntityToResponse(Set<TaxesEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		Set<TaxesResponse> responseList=new HashSet<>();
		for(TaxesEntity entity : entityList)
		{
			TaxesResponse response=new TaxesResponse();
			response = mapTaxesEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
}