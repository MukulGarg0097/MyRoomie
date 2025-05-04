package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.MasterRoomAmenities;
import com.myRoomie.Repository.IMasterRoomAmenitiesRepository;
import com.myRoomie.Services.IMasterRoomAmenitiesService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.MasterRoomAmenitiesMapper;
import com.myRoomie.request.MasterRoomAmenitiesRequest;
import com.myRoomie.response.MasterRoomAmenitiesResponse;

@Service
public class MasterRoomAmenitiesServiceImpl implements IMasterRoomAmenitiesService{

	@Autowired
	IMasterRoomAmenitiesRepository roomAmenityRepo;

	@Autowired
	MasterRoomAmenitiesMapper masterRoomAmenitiesMapper;
	
	@Override
	public MasterRoomAmenitiesResponse save(MasterRoomAmenitiesRequest request) throws BaseException
	{
		MasterRoomAmenities entity=roomAmenityRepo.save(masterRoomAmenitiesMapper.mapRoomAmenityRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return masterRoomAmenitiesMapper.mapMasterRoomAmenitiesToResponse(entity);
	}

	@Override
	public List<MasterRoomAmenitiesResponse> findAll()  throws BaseException{
		List<MasterRoomAmenities> entity = roomAmenityRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return masterRoomAmenitiesMapper.mapMasterRoomAmenitiesToResponse(entity);
	}

	@Override
	public MasterRoomAmenitiesResponse findById(Integer id)  throws BaseException{
		MasterRoomAmenities entity = new MasterRoomAmenities();
		if(roomAmenityRepo.existsById(id))
			entity = roomAmenityRepo.findById(id).get();
		else
			return null;
		return masterRoomAmenitiesMapper.mapMasterRoomAmenitiesToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		roomAmenityRepo.deleteById(id);
		return true;
	}
	
	
}
