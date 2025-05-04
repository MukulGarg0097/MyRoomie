package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RoomVariantEntity;
import com.myRoomie.Repository.IRoomVariantRepository;
import com.myRoomie.Services.IRoomVariantService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.RoomVariantMapper;
import com.myRoomie.request.RoomVariantRequest;
import com.myRoomie.response.RoomVariantResponse;

@Service
public class RoomVariantServiceImpl implements IRoomVariantService{

	@Autowired
	IRoomVariantRepository roomVariantRepo;
	
	@Autowired
	RoomVariantMapper roomVariantMapper;
	
	@Override
	public List<RoomVariantResponse> findAll() throws BaseException {
		List<RoomVariantEntity> entity = roomVariantRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return roomVariantMapper.mapRoomVariantEntityToResponse(entity);
	}

	@Override
	public RoomVariantResponse findById(Integer id)  throws BaseException{
		RoomVariantEntity entity = new RoomVariantEntity();
		if(roomVariantRepo.existsById(id))
			entity = roomVariantRepo.findById(id).get();
		else
			return null;
		return roomVariantMapper.mapRoomVariantEntityToResponse(entity);
	}

	@Override
	public RoomVariantResponse save(RoomVariantRequest request)  throws BaseException{
		RoomVariantEntity entity = roomVariantRepo.save(roomVariantMapper.mapRoomVariantRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return roomVariantMapper.mapRoomVariantEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		roomVariantRepo.deleteById(id);
		return true;
	}

}
