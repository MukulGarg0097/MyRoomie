package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.ChargesEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.ChargesRequest;
import com.myRoomie.response.ChargesResponse;

@Service
public class ChargesMapper {
	
	public ChargesMapper() {
	}
	
	public ChargesEntity mapChargesRequestToEntity(ChargesRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		ChargesEntity response=new ChargesEntity();
		response.setChargeAmount(request.getChargeAmount());
		response.setChargeName(request.getChargeName());
		response.setDescription(request.getDescription());
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setRoomId(request.getRoomId());
		return response;
	}
	
	public ChargesResponse mapChargesEntityToResponse(ChargesEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		ChargesResponse response=new ChargesResponse();
		response.setChargeAmount(entity.getChargeAmount());
		response.setChargeName(entity.getChargeName());
		response.setDescription(entity.getDescription());
		response.setId(entity.getId());
		response.setRoomId(entity.getRoomId());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
			return response;
	}
	
	public List<ChargesEntity> mapChargesRequestToEntity(List<ChargesRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<ChargesEntity> entityList=new ArrayList<>();
		for(ChargesRequest request : requestList)
		{
			ChargesEntity response=new ChargesEntity();
			response = mapChargesRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<ChargesResponse> mapChargesEntityToResponse(List<ChargesEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<ChargesResponse> responseList=new ArrayList<>();
		for(ChargesEntity entity : entityList)
		{
			ChargesResponse response=new ChargesResponse();
			response = mapChargesEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public Set<ChargesEntity> mapChargesRequestToEntity(Set<ChargesRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<ChargesEntity> entitySet=new HashSet<>();
		for(ChargesRequest request : requestSet)
		{
			ChargesEntity response=new ChargesEntity();
			response = mapChargesRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<ChargesResponse> mapChargesEntityToResponse(Set<ChargesEntity> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<ChargesResponse> responseSet=new HashSet<>();
		for(ChargesEntity entity : entitySet)
		{
			ChargesResponse response=new ChargesResponse();
			response = mapChargesEntityToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
}