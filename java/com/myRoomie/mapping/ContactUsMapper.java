package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.ContactUsEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.ContactUsRequest;
import com.myRoomie.response.ContactUsResponse;

public class ContactUsMapper {
	
	public ContactUsMapper() {
	}
		
	public static ContactUsEntity mapContactUsRequestToEntity(ContactUsRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		ContactUsEntity response=new ContactUsEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setEmail(request.getEmail());
		response.setName(request.getName());
		response.setMessage(request.getMessage());
		return response;
	}
	
	public static ContactUsResponse mapContactUsEntityToResponse(ContactUsEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		ContactUsResponse response=new ContactUsResponse();
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setEmail(entity.getEmail());
		response.setName(entity.getName());
		response.setMessage(entity.getMessage());
		return response;
	}
	
	public static List<ContactUsEntity> mapContactUsRequestToEntity(List<ContactUsRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<ContactUsEntity> entityList=new ArrayList<ContactUsEntity>();
		for(ContactUsRequest request: requestList)
		{
			ContactUsEntity entity = mapContactUsRequestToEntity(request);
			entityList.add(entity);
		}
		return entityList;
	}

	public static List<ContactUsResponse> mapContactUsEntityToResponse(List<ContactUsEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<ContactUsResponse> responseList=new ArrayList<ContactUsResponse>();
		for(ContactUsEntity entity: entityList)
		{
			ContactUsResponse response = new ContactUsResponse();
			response = mapContactUsEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
	
	public static Set<ContactUsEntity> mapContactUsRequestToEntity(Set<ContactUsRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		Set<ContactUsEntity> entityList=new HashSet<ContactUsEntity>();
		for(ContactUsRequest request: requestList)
		{
			ContactUsEntity entity = mapContactUsRequestToEntity(request);
			entityList.add(entity);
		}
		return entityList;
	}

	public static Set<ContactUsResponse> mapContactUsEntityToResponse(Set<ContactUsEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		Set<ContactUsResponse> responseList=new HashSet<ContactUsResponse>();
		for(ContactUsEntity entity: entityList)
		{
			ContactUsResponse response = new ContactUsResponse();
			response = mapContactUsEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
}