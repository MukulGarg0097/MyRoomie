package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RoomEntity;
import com.myRoomie.Pojos.RoomScheduledVisitsPojo;
import com.myRoomie.Repository.IRoomRepository;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.RoomRequest;
import com.myRoomie.response.RoomFileResponse;
import com.myRoomie.response.dto.RoomAmenitiesMapperResponse;
import com.myRoomie.response.dto.RoomResponse;

@Service
public class RoomMapper {
	
	@Autowired
	IRoomRepository roomRepo;
	
	@Autowired
	RoomAmenitiesMappersMapper roomAmenitiesMappersMapper;
	
	@Autowired
	RoomFileMapper roomFileMapper;
	
	@Autowired
	ChargesMapper chargesMapper;
	
	@Autowired
	RoomVariantMapper variantMapper;
	
	public RoomMapper() {
	}
	
	public RoomEntity mapRoomRequestToEntity(RoomRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		String servicesArrayToString="";
		String equipmentsArrayToString="";
		RoomEntity response=new RoomEntity();
		if(request.getServices() != null)
			servicesArrayToString = String.join(",", request.getServices());
		if(request.getEquipments() != null)
			equipmentsArrayToString = String.join(",", request.getEquipments());
		if(servicesArrayToString != null)
			response.setServices(servicesArrayToString);
		if(equipmentsArrayToString != null)
			response.setEquipments(equipmentsArrayToString);
		response.setAvailabilityStatus(request.getAvailabilityStatus());
		response.setCharges(chargesMapper.mapChargesRequestToEntity(request.getCharges()));
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setId(request.getId());
		response.setMaxNoOfGuests(request.getMaxNoOfGuests());
		response.setNoOfBeds(request.getNoOfBeds());
		response.setRoomAmenitiesMapper(roomAmenitiesMappersMapper.mapRoomAmenityRequestToEntity(request.getRoomAmenities()));
		response.setRoomImagesUrl(roomFileMapper.mapFileRequestToEntity(request.getRoomImagesUrl()));
		response.setRoomTypeName(request.getRoomTypeName());
		response.setSharingStatus(request.getSharingStatus());
		response.setStartAmount(request.getStartAmount());
		response.setRoomVariants(variantMapper.mapRoomVariantRequestToEntity(request.getRoomVariants()));
		response.setPropertyId(request.getPropertyId());
		response.setIsActive(request.getIsActive());
		return response;
	}
	
	public RoomResponse mapRoomEntityToResponse(RoomEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		String[] servicesArray=null;
		String[] equipmentsArray=null;
		RoomResponse response=new RoomResponse();
		if(entity.getServices() != null )//&& entity.getServices().contains(","))
			servicesArray = entity.getServices().split(",");
		if(entity.getEquipments() != null)// && entity.getEquipments().contains(","))	
			equipmentsArray = entity.getEquipments().split(",");
		if(servicesArray != null)
			response.setServices(servicesArray);
		if(equipmentsArray != null)
			response.setEquipments(equipmentsArray);

//		Set<RoomAmenitiesMapperResponse> amenityResponseSet=new HashSet<RoomAmenitiesMapperResponse>();
//		if(!CollectionUtils.isEmpty(entity.getRoomAmenitiesMapper())) {
//			for(RoomAmenitiesMapper amenityMapper: entity.getRoomAmenitiesMapper())
//			{
//				amenityResponseSet.add(roomAmenityMappersMapper.mapRoomAmenitiesToResponse(amenityMapper));
//			}
//			response.setRoomAmenities(amenityResponseSet);
//		}
//		Set<RoomFileResponse> roomImages=new HashSet<RoomFileResponse>();
//		if(!CollectionUtils.isEmpty(entity.getRoomAmenitiesMapper())) {
//			for(FileEntity fileEntity: entity.getRoomImagesUrl())
//			{
//				roomImages.add(roomFileMapper.mapFileEntityToResponse(fileEntity));
//			}
//		}

		Set<RoomAmenitiesMapperResponse> amenityResponseSet=roomAmenitiesMappersMapper.
				mapRoomAmenitiesToResponse(entity.getRoomAmenitiesMapper());
		response.setRoomAmenities(amenityResponseSet);
		Set<RoomFileResponse> roomImages=roomFileMapper.mapFileEntityToResponse(entity.getRoomImagesUrl());
		response.setRoomImagesUrl(roomImages);
	
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setAvailabilityStatus(entity.getAvailabilityStatus());
		response.setCharges(chargesMapper.mapChargesEntityToResponse(entity.getCharges()));
		response.setMaxNoOfGuests(entity.getMaxNoOfGuests());
		response.setNoOfBeds(entity.getNoOfBeds());
		response.setRoomTypeName(entity.getRoomTypeName());
		response.setSharingStatus(entity.getSharingStatus());
		response.setStartAmount(entity.getStartAmount());
		response.setRoomVariants(variantMapper.mapRoomVariantEntityToResponse(entity.getRoomVariants()));
		response.setPropertyId(entity.getPropertyId());
		response.setIsActive(entity.getIsActive());
		return response;
	}
	
	public List<RoomEntity> mapRoomRequestToEntity(List<RoomRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<RoomEntity> responseList=new ArrayList<RoomEntity>();
		for(RoomRequest request:requestList)
		{
			RoomEntity response=new RoomEntity();
			response = mapRoomRequestToEntity(request);
			responseList.add(response);
		}
		return responseList;
	}
	
	public List<RoomResponse> mapRoomEntityToResponse(List<RoomEntity> entityList)
	{
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<RoomResponse> responseList=new ArrayList<RoomResponse>();
		for(RoomEntity entity:entityList)
		{
			RoomResponse response=new RoomResponse();
			response = mapRoomEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
	
	public RoomScheduledVisitsPojo mapRoomEntityToScheduledVisitsPojo(Integer roomId)
	{
		if(roomId==null)
			return null;
		RoomEntity entity = new RoomEntity();
		if(roomRepo.existsById(roomId))
			entity = roomRepo.findById(roomId).get();
		else
			return null;
		RoomScheduledVisitsPojo response=new RoomScheduledVisitsPojo();
		String[] servicesArray=null;
		String[] equipmentsArray=null;
		if(entity.getServices() != null )
			servicesArray = entity.getServices().split(",");
		if(entity.getEquipments() != null)
			equipmentsArray = entity.getEquipments().split(",");
		if(servicesArray != null)
			response.setServices(servicesArray);
		if(equipmentsArray != null)
			response.setEquipments(equipmentsArray);
//		Set<RoomAmenitiesMapperResponse> amenityResponseSet=new HashSet<RoomAmenitiesMapperResponse>();
//		for(RoomAmenitiesMapper amenityMapper: entity.getRoomAmenitiesMapper())
//		{
//			RoomAmenitiesMapperResponse amenityResponse=new RoomAmenitiesMapperResponse();
//			amenityResponse =roomAmenityMappersMapper.mapRoomAmenitiesToResponse(amenityMapper);
//			amenityResponseSet.add(amenityResponse);
//		}
//		response.setRoomAmenitiesMapper(amenityResponseSet);
//		Set<RoomAmenitiesMapperResponse> amenityResponseSet=roomAmenitiesMappersMapper.
//				mapRoomAmenitiesToResponse(entity.getRoomAmenitiesMapper());
//		response.setRoomAmenitiesMapper(amenityResponseSet);
		
		response.setId(entity.getId());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setAvailabilityStatus(entity.getAvailabilityStatus());
//		response.setCharges(entity.getCharges());
		response.setMaxNoOfGuests(entity.getMaxNoOfGuests());
		response.setNoOfBeds(entity.getNoOfBeds());
		response.setRoomTypeName(entity.getRoomTypeName());
		response.setSharingStatus(entity.getSharingStatus());
		response.setStartAmount(entity.getStartAmount());
		response.setPropertyId(entity.getPropertyId());
		response.setIsActive(entity.getIsActive());
		
		return response;
	}
	
	public Set<RoomEntity> mapRoomRequestToEntity(Set<RoomRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<RoomEntity> responseSet=new HashSet<RoomEntity>();
		for(RoomRequest request:requestSet)
		{
			RoomEntity response=new RoomEntity();
			response = mapRoomRequestToEntity(request);
			responseSet.add(response);
		}
		return responseSet;
	}
	
	public Set<RoomResponse> mapRoomEntityToResponse(Set<RoomEntity> entitySet)
	{
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<RoomResponse> responseSet=new HashSet<RoomResponse>();
		for(RoomEntity entity:entitySet)
		{
			RoomResponse response=new RoomResponse();
			response = mapRoomEntityToResponse(entity);
			responseSet.add(response);
		}
		return responseSet;
	}
}
