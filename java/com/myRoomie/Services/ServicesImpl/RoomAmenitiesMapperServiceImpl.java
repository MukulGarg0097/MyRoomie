package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RoomAmenitiesMapper;
import com.myRoomie.Repository.IRoomAmenitiesMapperRepository;
import com.myRoomie.Services.IRoomAmenitiesMapperService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.RoomAmenitiesMappersMapper;
import com.myRoomie.request.RoomAmenitiesMapperRequest;
import com.myRoomie.response.RoomAmenitiesMapperResponse;

@Service
public class RoomAmenitiesMapperServiceImpl implements IRoomAmenitiesMapperService{

	@Autowired
	IRoomAmenitiesMapperRepository roomAmenityRepo;
	
	@Autowired
	RoomAmenitiesMappersMapper roomAmenityMapper;

	@Override
	public RoomAmenitiesMapperResponse save(RoomAmenitiesMapperRequest request) throws BaseException
	{
		RoomAmenitiesMapper entity=roomAmenityRepo.save(roomAmenityMapper.mapRoomAmenityRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return roomAmenityMapper.mapRoomAmenitiesToResponse(entity);
	}

	@Override
	public List<RoomAmenitiesMapperResponse> findAll()  throws BaseException{
		List<RoomAmenitiesMapper> entity = roomAmenityRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return roomAmenityMapper.mapRoomAmenitiesToResponse(entity);
	}

	@Override
	public RoomAmenitiesMapperResponse findById(Integer id)  throws BaseException{
		RoomAmenitiesMapper entity = new RoomAmenitiesMapper();
		if(roomAmenityRepo.existsById(id))
			entity = roomAmenityRepo.findById(id).get();
		else
			return null;
		return roomAmenityMapper.mapRoomAmenitiesToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		roomAmenityRepo.deleteById(id);
		return true;
					
	}	
	
}
