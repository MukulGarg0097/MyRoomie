package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.NearByPlacesEntity;
import com.myRoomie.Repository.INearByPlacesRepository;
import com.myRoomie.Services.INearByPlacesService;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.NearByPlacesMapper;
import com.myRoomie.request.dto.NearByPlacesRequest;
import com.myRoomie.response.dto.NearByPlacesResponse;

@Service
public class NearByPlacesServiceImpl implements INearByPlacesService{

	@Autowired
	INearByPlacesRepository nearByPlacesRepo;

	@Override
	public List<NearByPlacesResponse> findAll()  throws BaseException{
		List<NearByPlacesEntity> entity = nearByPlacesRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return NearByPlacesMapper.mapNearByPlacesEntityToResponse(entity);
	}

	@Override
	public NearByPlacesResponse findById(Integer id)  throws BaseException{
		NearByPlacesEntity entity =new NearByPlacesEntity();
		if(nearByPlacesRepo.existsById(id)) 
			entity = nearByPlacesRepo.getOne(id);
		else
			return null;
		return NearByPlacesMapper.mapNearByPlacesEntityToResponse(entity);
	}

	@Override
	public NearByPlacesResponse save(NearByPlacesRequest request)  throws BaseException{
		NearByPlacesEntity entity = nearByPlacesRepo.save(NearByPlacesMapper.mapNearByPlacesRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return NearByPlacesMapper.mapNearByPlacesEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
		if(nearByPlacesRepo.existsById(id)) 
			nearByPlacesRepo.deleteById(id);
		else
			throw new BaseException(ResponseCode.NEAR_BY_PLACES_NOT_PRESENT);
		return true;
	}
	
}
