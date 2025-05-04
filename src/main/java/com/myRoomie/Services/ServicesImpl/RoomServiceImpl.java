package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RoomEntity;
import com.myRoomie.Repository.IRoomRepository;
import com.myRoomie.Services.IRoomService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.RoomMapper;
import com.myRoomie.request.dto.RoomRequest;
import com.myRoomie.response.dto.RoomResponse;

@Service
public class RoomServiceImpl implements IRoomService{

	@Autowired
	IRoomRepository roomRepo;
	
	@Autowired
	RoomMapper roomMapper;

	@Override
	public List<RoomResponse> findByStartAmount(Double startAmount)  throws BaseException{
		List<RoomEntity> entity = roomRepo.findByStartAmount(startAmount);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return roomMapper.mapRoomEntityToResponse(entity);
	}

	@Override
	public List<RoomResponse> findByAvailabilityStatus(Boolean availabilityStatus)  throws BaseException{
		List<RoomEntity> entity = roomRepo.findByAvailabilityStatus(availabilityStatus);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return roomMapper.mapRoomEntityToResponse(entity);
	}

	@Override
	public List<RoomResponse> findByMaxNoOfGuests(Integer maxNoOfGuests)  throws BaseException{
		List<RoomEntity> entity = roomRepo.findByMaxNoOfGuests(maxNoOfGuests);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return roomMapper.mapRoomEntityToResponse(entity);
	}

	@Override
	public List<RoomResponse> findByNoOfBeds(Integer noOfBeds) throws BaseException {
		List<RoomEntity> entity = roomRepo.findByNoOfBeds(noOfBeds);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return roomMapper.mapRoomEntityToResponse(entity);
	}

	@Override
	public List<RoomResponse> findAll() throws BaseException {
		List<RoomEntity> entity = roomRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return roomMapper.mapRoomEntityToResponse(entity);
	}
	
	@Override
	public List<RoomResponse> findByIsActive() throws BaseException {
		List<RoomEntity> entity = roomRepo.findByIsActive(true);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return roomMapper.mapRoomEntityToResponse(entity);
	}

	@Override
	public RoomResponse findById(Integer id)  throws BaseException{
		RoomEntity entity = new RoomEntity();
		if(roomRepo.existsById(id))
			entity = roomRepo.findById(id).get();
		else
			return null;
		return roomMapper.mapRoomEntityToResponse(entity);
	}

	@Override
	public RoomResponse save(RoomRequest request)  throws BaseException{
		RoomEntity entity = roomRepo.save(roomMapper.mapRoomRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return roomMapper.mapRoomEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
		RoomEntity entity = new RoomEntity();
		if(roomRepo.existsById(id))
		{	entity = roomRepo.findById(id).get();
			entity.setIsActive(!entity.getIsActive());
			if(!ObjectUtils.isEmpty(roomRepo.save(entity)))
			{
				return true;
			}
		}
		return false;
	}
}
