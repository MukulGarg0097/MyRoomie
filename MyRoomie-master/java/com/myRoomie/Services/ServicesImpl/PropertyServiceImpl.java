package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.Repository.IPropertyRepository;
import com.myRoomie.Services.IPropertyService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.PropertyMapper;
import com.myRoomie.request.PropertyRequest;
import com.myRoomie.response.PropertyResponse;

@Service
public class PropertyServiceImpl implements IPropertyService{

	@Autowired
	IPropertyRepository propertyRepo;
	
	@Autowired
	PropertyMapper propMapper;

	@Override
	public PropertyResponse save(PropertyRequest request) throws BaseException
	{
		PropertyEntity entity=propertyRepo.save(propMapper.mapPropertyRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public List<PropertyResponse> findAll()  throws BaseException{
		List<PropertyEntity> entity = propertyRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}
	
	@Override
	public List<PropertyResponse> findByIsActive()  throws BaseException{
		List<PropertyEntity> entity = propertyRepo.findByIsActive(true);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public PropertyResponse findById(Integer id)  throws BaseException{
		PropertyEntity entity = new PropertyEntity();
		if(propertyRepo.existsById(id))
			entity = propertyRepo.findById(id).get();
		else
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public List<PropertyResponse> findByCity(String city) throws BaseException {
		List<PropertyEntity> entity = propertyRepo.findByCity(city);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}
	
	@Override
	public List<PropertyResponse> findAllByFeatured(Boolean isFeatureFlag) throws BaseException {
		List<PropertyEntity> entity = propertyRepo.findAllByIsFeatureFlag(isFeatureFlag);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public List<PropertyResponse> findByGenderType(String propertyGenderType) throws BaseException {
		List<PropertyEntity> entity = propertyRepo.findByPropertyGenderType(propertyGenderType);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public List<PropertyResponse> findByPropertyType(String propertyType)  throws BaseException{
		List<PropertyEntity> entity = propertyRepo.findByPropertyType(propertyType);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public List<PropertyResponse> findByPropertyTypeAndCity(String propertyType, String city)  throws BaseException{
		List<PropertyEntity> entity = propertyRepo.findByPropertyTypeAndCity(propertyType,city);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public List<PropertyResponse> findByPropertyGenderTypeAndCity(String propertyGenderType, String city)  throws BaseException{
		List<PropertyEntity> entity = propertyRepo.findByPropertyGenderTypeAndCity(propertyGenderType,city);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public List<PropertyResponse> findByLattitudeAndLongitude(Double latitude, Double longitude)  throws BaseException{
		List<PropertyEntity> entity = propertyRepo.findByAddressLatitudeAndAddressLongitude(latitude,longitude);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return propMapper.mapPropertyEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
		PropertyEntity entity = new PropertyEntity();
		if(propertyRepo.existsById(id))
		{	entity = propertyRepo.findById(id).get();
			entity.setIsActive(!entity.getIsActive());
			if(!ObjectUtils.isEmpty(propertyRepo.save(entity)))
			{
				return true;
			}
		}
		return false;
	}
	
	
}
