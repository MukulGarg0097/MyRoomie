package com.myRoomie.mapping;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.DiscountEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.DiscountRequest;
import com.myRoomie.response.dto.DiscountResponse;

@Service
public class DiscountMapper {
	
	public DiscountMapper() {
	}
	
	public static DiscountEntity mapDiscountRequestToEntity(DiscountRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		DiscountEntity response=new DiscountEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setDiscountAmount(request.getDiscountAmount());
		response.setDiscountPercent(request.getDiscountPercent());
		response.setDiscountUpon(request.getDiscountUpon());
		response.setDiscountType(request.getDiscountType());
		response.setCouponCodeId(request.getCouponCodeId());
		return response;
	}
	
	public static DiscountResponse mapDiscountEntityToResponse(DiscountEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		DiscountResponse response=new DiscountResponse();
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setDiscountAmount(entity.getDiscountAmount());
		response.setDiscountPercent(entity.getDiscountPercent());
		response.setDiscountUpon(entity.getDiscountUpon());
		response.setDiscountType(entity.getDiscountType());
		response.setCouponCodeId(entity.getCouponCodeId());
			return response;
	}
	
	public static Set<DiscountEntity> mapDiscountRequestToEntity(Set<DiscountRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		Set<DiscountEntity> entityList=new HashSet<>();
		for(DiscountRequest request : requestList)
		{
			DiscountEntity response=new DiscountEntity();
			response = mapDiscountRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public static Set<DiscountResponse> mapDiscountEntityToResponse(Set<DiscountEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		Set<DiscountResponse> responseList=new HashSet<>();
		for(DiscountEntity entity : entityList)
		{
			DiscountResponse response=new DiscountResponse();
			response = mapDiscountEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
}