package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.AddressEntity;
import com.myRoomie.Repository.IAddressRepository;
import com.myRoomie.Services.IAddressService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.AddressMapper;
import com.myRoomie.request.dto.AddressRequest;
import com.myRoomie.response.dto.AddressResponse;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	IAddressRepository addressRepo;

	@Autowired
	AddressMapper addressMapper;
	
	@Override
	public List<AddressResponse> findAll()  throws BaseException{
		List<AddressEntity> entity = addressRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return addressMapper.mapAddressEntityToResponse(entity);
	}

	@Override
	public AddressResponse findById(Integer id)  throws BaseException{
		AddressEntity entity =new AddressEntity();
		if(addressRepo.existsById(id)) 
			entity = addressRepo.getOne(id);
		else
			return null;
		return addressMapper.mapAddressEntityToResponse(entity);
	}

	@Override
	public List<AddressResponse> findByCity(String city)  throws BaseException{
		List<AddressEntity> entity = addressRepo.findByCity(city);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return addressMapper.mapAddressEntityToResponse(entity);
	}

	@Override
	public List<AddressResponse> findByState(String state)  throws BaseException{
		List<AddressEntity> entity = addressRepo.findByState(state);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return addressMapper.mapAddressEntityToResponse(entity);
	}

	@Override
	public List<AddressResponse> findByCountry(String country)  throws BaseException{
		List<AddressEntity> entity = addressRepo.findByCountry(country);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return addressMapper.mapAddressEntityToResponse(entity);
	}

	@Override
	public List<AddressResponse> findByPincode(String pincode)  throws BaseException{
		List<AddressEntity> entity = addressRepo.findByPincode(pincode);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return addressMapper.mapAddressEntityToResponse(entity);
	}

	@Override
	public List<AddressResponse> findByLattitudeAndLongitude(Double latitude, Double longitude)  throws BaseException{
		List<AddressEntity> entity = addressRepo.findByLatitudeAndLongitude(latitude, longitude);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return addressMapper.mapAddressEntityToResponse(entity);
	}

	@Override
	public AddressResponse save(AddressRequest request)  throws BaseException{
		AddressEntity entity = addressRepo.save(addressMapper.mapAddressRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return addressMapper.mapAddressEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		addressRepo.deleteById(id);
			return true;
	}


	
}
