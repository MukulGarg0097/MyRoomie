package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RequestCallBackEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.RequestCallBackRequest;
import com.myRoomie.response.dto.RequestCallBackResponse;

@Service
public class RequestCallBackMapper {
	
	public RequestCallBackMapper() {
	}
		
	public static RequestCallBackEntity mapCallBackRequestToEntity(RequestCallBackRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		RequestCallBackEntity response=new RequestCallBackEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setName(request.getName());
		response.setMobileNo(request.getMobileNo());
		return response;
	}
	
	public static RequestCallBackResponse mapCallBackEntityToResponse(RequestCallBackEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		RequestCallBackResponse response=new RequestCallBackResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setMobileNo(entity.getMobileNo());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		return response;
	}
	
	public static List<RequestCallBackEntity> mapCallBackRequestToEntity(List<RequestCallBackRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<RequestCallBackEntity> entityList=new ArrayList<>();
		for(RequestCallBackRequest request: requestList)
		{
			RequestCallBackEntity entity = mapCallBackRequestToEntity(request);
			entityList.add(entity);
		}
		return entityList;
	}

	public static List<RequestCallBackResponse> mapCallBackEntityToResponse(List<RequestCallBackEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<RequestCallBackResponse> responseList=new ArrayList<>();
		for(RequestCallBackEntity entity: entityList)
		{
			RequestCallBackResponse response = new RequestCallBackResponse();
			response = mapCallBackEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
	
	public static Set<RequestCallBackEntity> mapCallBackRequestToEntity(Set<RequestCallBackRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		Set<RequestCallBackEntity> entityList=new HashSet<>();
		for(RequestCallBackRequest request: requestList)
		{
			RequestCallBackEntity entity = mapCallBackRequestToEntity(request);
			entityList.add(entity);
		}
		return entityList;
	}

	public static Set<RequestCallBackResponse> mapCallBackEntityToResponse(Set<RequestCallBackEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		Set<RequestCallBackResponse> responseList=new HashSet<>();
		for(RequestCallBackEntity entity: entityList)
		{
			RequestCallBackResponse response = new RequestCallBackResponse();
			response = mapCallBackEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
}