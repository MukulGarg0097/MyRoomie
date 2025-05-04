package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RoomAmenitiesMapper;
import com.myRoomie.Repository.IMasterRoomAmenitiesRepository;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.RoomAmenitiesMapperRequest;
import com.myRoomie.response.dto.RoomAmenitiesMapperResponse;

@Service
public class RoomAmenitiesMappersMapper {
	
	public RoomAmenitiesMappersMapper() {
	}
	
	@Autowired
	IMasterRoomAmenitiesRepository masterAmenityrepo;
	
	@Autowired
	MasterRoomAmenitiesMapper amenityMapper;
	
	public RoomAmenitiesMapper mapRoomAmenityRequestToEntity(RoomAmenitiesMapperRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		RoomAmenitiesMapper response=new RoomAmenitiesMapper();
		response.setAmenityDescription(request.getAmenityDescription());
		response.setCategory(request.getCategory());
		response.setSubCategory(request.getSubCategory());
		response.setAmenityMappingStatus(request.getAmenityMappingStatus());
		response.setMasterRoomAmenityId(request.getMasterRoomAmenityId());
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setRoomId(request.getRoomId());
		return response;
	}
	
	public RoomAmenitiesMapperResponse mapRoomAmenitiesToResponse(RoomAmenitiesMapper entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		RoomAmenitiesMapperResponse response=new RoomAmenitiesMapperResponse();
		response.setAmenityDescription(entity.getAmenityDescription());
		response.setCategory(entity.getCategory());
		response.setSubCategory(entity.getSubCategory());
		response.setAmenityMappingStatus(entity.getAmenityMappingStatus());
		response.setMasterRoomAmenityId(entity.getMasterRoomAmenityId());
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setRoomId(entity.getRoomId());
		if(masterAmenityrepo.existsById(entity.getMasterRoomAmenityId()))
			response.setMasterRoomAmenity(amenityMapper.mapMasterRoomAmenitiesToResponse(masterAmenityrepo.findById(entity.getMasterRoomAmenityId()).get()));
		return response;
	}
	
	public List<RoomAmenitiesMapper> mapRoomAmenityRequestToEntity(List<RoomAmenitiesMapperRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<RoomAmenitiesMapper> entityList=new ArrayList<>();
		for(RoomAmenitiesMapperRequest request : requestList)
		{
			RoomAmenitiesMapper response=new RoomAmenitiesMapper();
			response = mapRoomAmenityRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<RoomAmenitiesMapperResponse> mapRoomAmenitiesToResponse(List<RoomAmenitiesMapper> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<RoomAmenitiesMapperResponse> responseList=new ArrayList<>();
		for(RoomAmenitiesMapper entity : entityList)
		{
			RoomAmenitiesMapperResponse response=new RoomAmenitiesMapperResponse();
			response = mapRoomAmenitiesToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public Set<RoomAmenitiesMapper> mapRoomAmenityRequestToEntity(Set<RoomAmenitiesMapperRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<RoomAmenitiesMapper> entityList=new HashSet<>();
		for(RoomAmenitiesMapperRequest request : requestSet)
		{
			RoomAmenitiesMapper response=new RoomAmenitiesMapper();
			response = mapRoomAmenityRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}
	
	public Set<RoomAmenitiesMapperResponse> mapRoomAmenitiesToResponse(Set<RoomAmenitiesMapper> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<RoomAmenitiesMapperResponse> responseList=new HashSet<>();
		for(RoomAmenitiesMapper entity : entitySet)
		{
			RoomAmenitiesMapperResponse response=new RoomAmenitiesMapperResponse();
			response = mapRoomAmenitiesToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
}