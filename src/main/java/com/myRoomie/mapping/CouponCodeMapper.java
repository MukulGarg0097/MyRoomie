package com.myRoomie.mapping;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.myRoomie.Entities.CouponCodeEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.CouponCodeRequest;
import com.myRoomie.response.dto.CouponCodeResponse;

@Service
public class CouponCodeMapper {
	
	public CouponCodeMapper() {
	}
	
	public CouponCodeEntity mapCouponCodeRequestToEntity(CouponCodeRequest request) throws BaseException
	{
		CouponCodeEntity response=new CouponCodeEntity();
		response.setIsExpired(true);
		response.setIsActive(false);
		Date expiry = DateUtil.parseDate(request.getExpiryDate() , DateUtil.DB_TIMESTAMP_PATTERN);
		if(expiry!=null) {
			if(expiry.before(Date.from(Instant.now())))
				response.setIsExpired(true);
			else
				response.setIsExpired(false);
		}
		else {
			throw new BaseException(ResponseCode.INVALID_DATE);
		}
		Date start = DateUtil.parseDate(request.getStartDate() , DateUtil.DB_TIMESTAMP_PATTERN);
		if(start!=null) {
			if(start.after(Date.from(Instant.now())))
				response.setIsActive(false);
			else
				response.setIsActive(request.getIsActive());
		}
		else {
			throw new BaseException(ResponseCode.INVALID_DATE);
		}
		
		if(response.getIsExpired())
				response.setIsActive(false);
		else
			response.setIsActive(request.getIsActive());
		
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setCouponCodeName(request.getCouponCodeName());
		response.setDiscountPercentage(request.getDiscountPercentage());
		response.setExpiryDate(expiry);
		response.setStartDate(start);
		return response;
	}
	
	public CouponCodeResponse mapCouponCodeEntityToResponse(CouponCodeEntity entity)
	{
		CouponCodeResponse response=new CouponCodeResponse();
		if(entity.getExpiryDate()!=null)
		{
			if(entity.getExpiryDate().before(Date.from(Instant.now())))
				response.setIsExpired(true);
			else
				response.setIsExpired(entity.getIsExpired());
		}
		if(entity.getStartDate()!=null)
		{
			if(entity.getStartDate().after(Date.from(Instant.now())))
				response.setIsActive(false);
			else
				response.setIsActive(entity.getIsActive());
		}
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setCouponCodeName(entity.getCouponCodeName());
		response.setDiscountPercentage(entity.getDiscountPercentage());
		response.setExpiryDate(DateUtil.formateDate(entity.getExpiryDate() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setStartDate(DateUtil.formateDate(entity.getStartDate() , DateUtil.DB_TIMESTAMP_PATTERN));
		return response;
	}
	
	public List<CouponCodeEntity> mapCouponCodeRequestToEntity(List<CouponCodeRequest> requestList) throws BaseException
	{
		List<CouponCodeEntity> entityList=new ArrayList<>();
		for(CouponCodeRequest request : requestList)
		{
			CouponCodeEntity response=new CouponCodeEntity();
			response = mapCouponCodeRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<CouponCodeResponse> mapCouponCodeEntityToResponse(List<CouponCodeEntity> entityList) {
		List<CouponCodeResponse> responseList=new ArrayList<>();
		for(CouponCodeEntity entity : entityList)
		{
			CouponCodeResponse response=new CouponCodeResponse();
			response = mapCouponCodeEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public Set<CouponCodeEntity> mapCouponCodeRequestToEntity(Set<CouponCodeRequest> requestSet) throws BaseException
	{
		Set<CouponCodeEntity> entitySet=new HashSet<>();
		for(CouponCodeRequest request : requestSet)
		{
			CouponCodeEntity response=new CouponCodeEntity();
			response = mapCouponCodeRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<CouponCodeResponse> mapCouponCodeEntityToResponse(Set<CouponCodeEntity> entitySet) {
		Set<CouponCodeResponse> responseSet=new HashSet<>();
		for(CouponCodeEntity entity : entitySet)
		{
			CouponCodeResponse response=new CouponCodeResponse();
			response = mapCouponCodeEntityToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
}