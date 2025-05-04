package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.ScheduledVisitsEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.ScheduledVisitsRequest;
import com.myRoomie.response.dto.ScheduledVisitsResponse;

@Service
public class ScheduledVisitsMapper {
	
	public ScheduledVisitsMapper() {
	}
	
	@Autowired
	PropertyMapper propertyMapper;
	
	@Autowired
	RoomMapper roomMapper;
	
	@Autowired
	RoomVariantMapper roomVariantMapper;
	
	public ScheduledVisitsEntity mapScheduledVisitsRequestToEntity(ScheduledVisitsRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		ScheduledVisitsEntity response=new ScheduledVisitsEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setName(request.getName());
		response.setEmail(request.getEmail());
		response.setContactNo(request.getContactNo());
		response.setRoomId(request.getRoomId());
		response.setPropertyId(request.getPropertyId());
		response.setRoomVariantId(request.getRoomVariantId());
		response.setVisitingDate(DateUtil.parseDate(request.getVisitingDate(), DateUtil.DB_TIMESTAMP_PATTERN));
		return response;
	}
	
	public ScheduledVisitsResponse mapScheduledVisitsEntityToResponse(ScheduledVisitsEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		ScheduledVisitsResponse response=new ScheduledVisitsResponse();
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
		response.setContactNo(entity.getContactNo());
		response.setRoomId(entity.getRoomId());
		response.setPropertyId(entity.getPropertyId());
		response.setRoomVariantId(entity.getRoomVariantId());
		response.setVisitingDate(DateUtil.formateDate(entity.getVisitingDate(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setProperty(propertyMapper.mapPropertyEntityToScheduledVisitsPojo(entity.getPropertyId()));
		response.setRoom(roomMapper.mapRoomEntityToScheduledVisitsPojo(entity.getRoomId()));
		response.setRoomVariant(roomVariantMapper.mapRoomVariantEntityToResponse(entity.getRoomVariantId()));
//		if(!ObjectUtils.isEmpty(response.getProperty()))
//			response.setNearByPlaces(response.getProperty().getNearByPlaces());
			return response;
	}
	
	public List<ScheduledVisitsEntity> mapScheduledVisitsRequestToEntity(List<ScheduledVisitsRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<ScheduledVisitsEntity> entityList=new ArrayList<>();
		for(ScheduledVisitsRequest request : requestList)
		{
			ScheduledVisitsEntity response=new ScheduledVisitsEntity();
			response = mapScheduledVisitsRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<ScheduledVisitsResponse> mapScheduledVisitsEntityToResponse(List<ScheduledVisitsEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<ScheduledVisitsResponse> responseList=new ArrayList<>();
		for(ScheduledVisitsEntity entity : entityList)
		{
			ScheduledVisitsResponse response=new ScheduledVisitsResponse();
			response = mapScheduledVisitsEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public Set<ScheduledVisitsEntity> mapScheduledVisitsRequestToEntity(Set<ScheduledVisitsRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<ScheduledVisitsEntity> entitySet=new HashSet<>();
		for(ScheduledVisitsRequest request : requestSet)
		{
			ScheduledVisitsEntity response=new ScheduledVisitsEntity();
			response = mapScheduledVisitsRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<ScheduledVisitsResponse> mapScheduledVisitsEntityToResponse(Set<ScheduledVisitsEntity> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<ScheduledVisitsResponse> responseSet=new HashSet<>();
		for(ScheduledVisitsEntity entity : entitySet)
		{
			ScheduledVisitsResponse response=new ScheduledVisitsResponse();
			response = mapScheduledVisitsEntityToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
}