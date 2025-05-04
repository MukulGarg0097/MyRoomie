package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.Entities.RoomEntity;
import com.myRoomie.Pojos.PropertyScheduledVisitsPojo;
import com.myRoomie.Repository.IPropertyRepository;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.Utilities.SetAndListUtil;
import com.myRoomie.request.PropertyRequest;
import com.myRoomie.response.PropertyFileResponse;
import com.myRoomie.response.PropertyResponse;
import com.myRoomie.response.RoomResponse;

@Service
public class PropertyMapper {
	
	public PropertyMapper() {
	}
	
	@Autowired
	IPropertyRepository propRepo;
	
	@Autowired
	PropertyFileMapper propertyFileMapper;
	
	@Autowired
	RoomMapper roomMapper;
	
	@Autowired
	AddressMapper addressMapper;
	
	public PropertyEntity mapPropertyRequestToEntity(PropertyRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		PropertyEntity response=new PropertyEntity();
		Set<RoomEntity> roomSet=SetAndListUtil.convertListToSet(roomMapper.
				mapRoomRequestToEntity(SetAndListUtil.convertSetToList(request.getRooms())));
		response.setRooms(roomSet);
//		if(!CollectionUtils.isEmpty(request.getRooms()))
//		{
//			Set<RoomEntity> roomSet=new HashSet<>(roomMapper.mapRoomRequestToEntity(new ArrayList<>(request.getRooms())));
//			response.setRooms(roomSet);
//		}
		response.setAboutProperty(request.getAboutProperty());
		response.setAddress(request.getAddress());
		response.setPropertyName(request.getPropertyName());
		response.setCity(request.getCity());
		response.setIsFeatureFlag(request.getIsFeatureFlag());
		response.setPropertyGenderType(request.getPropertyGenderType());
		response.setPropertyImagesUrl(propertyFileMapper.mapFileRequestToEntity(request.getPropertyImagesUrl()));
		response.setPropertyType(request.getPropertyType());
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setIsActive(request.getIsActive());
		return response;
	}
	
	public PropertyResponse mapPropertyEntityToResponse(PropertyEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		PropertyResponse response=new PropertyResponse();
		Set<RoomResponse> roomSet=SetAndListUtil.convertListToSet(roomMapper.
				mapRoomEntityToResponse(SetAndListUtil.convertSetToList(entity.getRooms())));
		response.setRooms(roomSet);
//		if(!CollectionUtils.isEmpty(entity.getRooms()))
//		{
//			Set<RoomResponse> roomSet=new HashSet<>(roomMapper.mapRoomEntityToResponse(new ArrayList<>(entity.getRooms())));
//			response.setRooms(roomSet);
//		}
		Set<PropertyFileResponse> propertyImages=propertyFileMapper.mapFileEntityToResponse(entity.getPropertyImagesUrl());
		response.setPropertyImagesUrl(propertyImages);
		
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setAboutProperty(entity.getAboutProperty());
		response.setAddress(addressMapper.mapAddressEntityToResponse(entity.getAddress()));
		response.setPropertyName(entity.getPropertyName());
		response.setCity(entity.getCity());
		response.setIsFeatureFlag(entity.getIsFeatureFlag());
		response.setPropertyGenderType(entity.getPropertyGenderType());
		response.setPropertyType(entity.getPropertyType());
		response.setIsActive(entity.getIsActive());
		return response;
	}
	
	public List<PropertyEntity> mapPropertyRequestToEntity(List<PropertyRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<PropertyEntity> entityList=new ArrayList<>();
		for(PropertyRequest request : requestList)
		{
			PropertyEntity response=new PropertyEntity();
			response = mapPropertyRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<PropertyResponse> mapPropertyEntityToResponse(List<PropertyEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<PropertyResponse> responseList=new ArrayList<>();
		for(PropertyEntity entity : entityList)
		{
			PropertyResponse response=new PropertyResponse();
			response = mapPropertyEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public PropertyScheduledVisitsPojo mapPropertyEntityToScheduledVisitsPojo(Integer propertyId)
	{
		if(propertyId==null)
		{
			return null;
		}
		PropertyEntity entity = new PropertyEntity();
		if(propRepo.existsById(propertyId))
			entity = propRepo.findById(propertyId).get();
		else
			return null;
		PropertyScheduledVisitsPojo response=new PropertyScheduledVisitsPojo();
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setAboutProperty(entity.getAboutProperty());
		response.setAddress(addressMapper.mapAddressEntityToResponse(entity.getAddress()));
		response.setPropertyName(entity.getPropertyName());
		response.setCity(entity.getCity());
		response.setIsFeatureFlag(entity.getIsFeatureFlag());
		response.setPropertyGenderType(entity.getPropertyGenderType());
		response.setPropertyType(entity.getPropertyType());
		response.setIsActive(entity.getIsActive());
		return response;
	}
	
	public Set<PropertyEntity> mapPropertyRequestToEntity(Set<PropertyRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<PropertyEntity> entitySet=new HashSet<>();
		for(PropertyRequest request : requestSet)
		{
			PropertyEntity response=new PropertyEntity();
			response = mapPropertyRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<PropertyResponse> mapPropertyEntityToResponse(Set<PropertyEntity> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<PropertyResponse> responseSet=new HashSet<>();
		for(PropertyEntity entity : entitySet)
		{
			PropertyResponse response=new PropertyResponse();
			response = mapPropertyEntityToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
	
}