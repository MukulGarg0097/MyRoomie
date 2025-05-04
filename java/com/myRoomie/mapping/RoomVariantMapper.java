package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RoomVariantEntity;
import com.myRoomie.Repository.IRoomVariantRepository;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.RoomVariantRequest;
import com.myRoomie.response.RoomVariantResponse;

@Service
public class RoomVariantMapper {
	
	public RoomVariantMapper() {
	}
	
	@Autowired
	IRoomVariantRepository roomVariantRepo;
	
	public RoomVariantEntity mapRoomVariantRequestToEntity(RoomVariantRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		RoomVariantEntity response=new RoomVariantEntity();
		response.setAmount(request.getAmount());
		response.setAvailabilityStatus(request.getAvailabilityStatus());
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setRoomId(request.getRoomId());
		response.setRoomVariantType(request.getRoomVariantType());
		return response;
	}
	
	public RoomVariantResponse mapRoomVariantEntityToResponse(RoomVariantEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		RoomVariantResponse response=new RoomVariantResponse();
		response.setAmount(entity.getAmount());
		response.setAvailabilityStatus(entity.getAvailabilityStatus());
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setRoomVariantType(entity.getRoomVariantType());
		response.setRoomId(entity.getRoomId());
		return response;
	}
	
	public List<RoomVariantEntity> mapRoomVariantRequestToEntity(List<RoomVariantRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<RoomVariantEntity> entityList=new ArrayList<>();
		for(RoomVariantRequest request : requestList)
		{
			RoomVariantEntity response=new RoomVariantEntity();
			response = mapRoomVariantRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<RoomVariantResponse> mapRoomVariantEntityToResponse(List<RoomVariantEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<RoomVariantResponse> responseList=new ArrayList<>();
		for(RoomVariantEntity entity : entityList)
		{
			RoomVariantResponse response=new RoomVariantResponse();
			response = mapRoomVariantEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public RoomVariantResponse mapRoomVariantEntityToResponse(Integer roomVariantId)
	{
		if(roomVariantId == null)
		{
			return null;
		}
		RoomVariantEntity entity = new RoomVariantEntity();
		if(roomVariantRepo.existsById(roomVariantId))
			entity = roomVariantRepo.findById(roomVariantId).get();
		else
			return null;
		RoomVariantResponse response=new RoomVariantResponse();
		response.setAmount(entity.getAmount());
		response.setAvailabilityStatus(entity.getAvailabilityStatus());
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setRoomVariantType(entity.getRoomVariantType());
		response.setRoomId(entity.getRoomId());
		return response;
	}
	
	public Set<RoomVariantEntity> mapRoomVariantRequestToEntity(Set<RoomVariantRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<RoomVariantEntity> entitySet=new HashSet<>();
		for(RoomVariantRequest request : requestSet)
		{
			RoomVariantEntity response=new RoomVariantEntity();
			response = mapRoomVariantRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<RoomVariantResponse> mapRoomVariantEntityToResponse(Set<RoomVariantEntity> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<RoomVariantResponse> responseSet=new HashSet<>();
		for(RoomVariantEntity entity : entitySet)
		{
			RoomVariantResponse response=new RoomVariantResponse();
			response = mapRoomVariantEntityToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
}