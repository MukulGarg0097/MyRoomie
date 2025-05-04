package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.MasterRoomAmenities;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.MasterRoomAmenitiesRequest;
import com.myRoomie.response.MasterRoomAmenitiesResponse;

@Service
public class MasterRoomAmenitiesMapper {
	
	public MasterRoomAmenitiesMapper() {
	}
	
	public MasterRoomAmenities mapRoomAmenityRequestToEntity(MasterRoomAmenitiesRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		MasterRoomAmenities response=new MasterRoomAmenities();
		response.setMasterAmenityDescription(request.getMasterAmenityDescription());
		response.setMasterAmenityIconUrl(request.getMasterAmenityIconUrl());
		response.setMasterAmenityName(request.getMasterAmenityName());
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		return response;
	}
	
	public MasterRoomAmenitiesResponse mapMasterRoomAmenitiesToResponse(MasterRoomAmenities entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		MasterRoomAmenitiesResponse response=new MasterRoomAmenitiesResponse();
		response.setMasterAmenityDescription(entity.getMasterAmenityDescription());
		response.setMasterAmenityIconUrl(entity.getMasterAmenityIconUrl());
		response.setMasterAmenityName(entity.getMasterAmenityName());
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		return response;
	}
	
	public List<MasterRoomAmenities> mapRoomAmenityRequestToEntity(List<MasterRoomAmenitiesRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<MasterRoomAmenities> entityList=new ArrayList<>();
		for(MasterRoomAmenitiesRequest request : requestList)
		{
			MasterRoomAmenities response=new MasterRoomAmenities();
			response = mapRoomAmenityRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<MasterRoomAmenitiesResponse> mapMasterRoomAmenitiesToResponse(List<MasterRoomAmenities> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<MasterRoomAmenitiesResponse> responseList=new ArrayList<>();
		for(MasterRoomAmenities entity : entityList)
		{
			MasterRoomAmenitiesResponse response=new MasterRoomAmenitiesResponse();
			response = mapMasterRoomAmenitiesToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public Set<MasterRoomAmenities> mapRoomAmenityRequestToEntity(Set<MasterRoomAmenitiesRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<MasterRoomAmenities> entitySet=new HashSet<>();
		for(MasterRoomAmenitiesRequest request : requestSet)
		{
			MasterRoomAmenities response=new MasterRoomAmenities();
			response = mapRoomAmenityRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<MasterRoomAmenitiesResponse> mapMasterRoomAmenitiesToResponse(Set<MasterRoomAmenities> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<MasterRoomAmenitiesResponse> responseSet=new HashSet<>();
		for(MasterRoomAmenities entity : entitySet)
		{
			MasterRoomAmenitiesResponse response=new MasterRoomAmenitiesResponse();
			response = mapMasterRoomAmenitiesToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
}