package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.NearByPlacesEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.NearByPlacesRequest;
import com.myRoomie.response.dto.NearByPlacesResponse;

@Service
public class NearByPlacesMapper {
	
	public NearByPlacesMapper() {
	}
		
	public static NearByPlacesEntity mapNearByPlacesRequestToEntity(NearByPlacesRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		NearByPlacesEntity response=new NearByPlacesEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		
		response.setDisplayTime(request.getDisplayTime());
		if(request.getShowOnWebsite()==null)
			response.setShowOnWebsite(true);
		else
			response.setShowOnWebsite(request.getShowOnWebsite());
		response.setPlaceName(request.getPlaceName());
		response.setType(request.getType());
		response.setPropertyId(request.getPropertyId());
		return response;
	}
	
	public static NearByPlacesResponse mapNearByPlacesEntityToResponse(NearByPlacesEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		NearByPlacesResponse response=new NearByPlacesResponse();
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		
		response.setDisplayTime(entity.getDisplayTime());
		response.setShowOnWebsite(entity.getShowOnWebsite());
		response.setPlaceName(entity.getPlaceName());
		response.setType(entity.getType());
		response.setPropertyId(entity.getPropertyId());
		return response;
	}
	
	public static List<NearByPlacesEntity> mapNearByPlacesRequestToEntity(List<NearByPlacesRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<NearByPlacesEntity> entityList=new ArrayList<>();
		for(NearByPlacesRequest request: requestList)
		{
			NearByPlacesEntity entity = mapNearByPlacesRequestToEntity(request);
			entityList.add(entity);
		}
		return entityList;
	}

	public static List<NearByPlacesResponse> mapNearByPlacesEntityToResponse(List<NearByPlacesEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<NearByPlacesResponse> responseList=new ArrayList<>();
		for(NearByPlacesEntity entity: entityList)
		{
			NearByPlacesResponse response = new NearByPlacesResponse();
			response = mapNearByPlacesEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
	
	public static Set<NearByPlacesEntity> mapNearByPlacesRequestToEntity(Set<NearByPlacesRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		Set<NearByPlacesEntity> entityList=new HashSet<>();
		for(NearByPlacesRequest request: requestList)
		{
			NearByPlacesEntity entity = mapNearByPlacesRequestToEntity(request);
			entityList.add(entity);
		}
		return entityList;
	}

	public static Set<NearByPlacesResponse> mapNearByPlacesEntityToResponse(Set<NearByPlacesEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		Set<NearByPlacesResponse> responseList=new HashSet<>();
		for(NearByPlacesEntity entity: entityList)
		{
			NearByPlacesResponse response = new NearByPlacesResponse();
			response = mapNearByPlacesEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
}