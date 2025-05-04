package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.AddressEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.dto.AddressRequest;
import com.myRoomie.response.dto.AddressResponse;

@Service
public class AddressMapper {
	
	public AddressMapper() {
	}
		
	public AddressEntity mapAddressRequestToEntity(AddressRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		AddressEntity response=new AddressEntity();
		response.setCity(request.getCity());
		response.setCountry(request.getCountry());
		response.setFlatNo(request.getFlatNo());
		response.setHouseNo(request.getHouseNo());
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setLandmark(request.getLandmark());
		response.setLatitude(request.getLatitude());
		response.setLocality(request.getLocality());
		response.setLongitude(request.getLongitude());
		response.setPincode(request.getPincode());
		response.setState(request.getState());
		return response;
	}
	
	public AddressResponse mapAddressEntityToResponse(AddressEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		AddressResponse response=new AddressResponse();
		response.setCity(entity.getCity());
		response.setCountry(entity.getCountry());
		response.setFlatNo(entity.getFlatNo());
		response.setHouseNo(entity.getHouseNo());
		response.setId(entity.getId());
		response.setLandmark(entity.getLandmark());
		response.setLatitude(entity.getLatitude());
		response.setLocality(entity.getLocality());
		response.setLongitude(entity.getLongitude());
		response.setPincode(entity.getPincode());
		response.setState(entity.getState());
		response.setCreated(DateUtil.formateDate(entity.getCreated() , DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated() , DateUtil.DB_TIMESTAMP_PATTERN));
		return response;
	}
	
	public List<AddressEntity> mapAddressRequestToEntity(List<AddressRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<AddressEntity> entityList=new ArrayList<AddressEntity>();
		for(AddressRequest request: requestList)
		{
			AddressEntity entity = mapAddressRequestToEntity(request);
			entityList.add(entity);
		}
		return entityList;
	}

	public List<AddressResponse> mapAddressEntityToResponse(List<AddressEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<AddressResponse> responseList=new ArrayList<AddressResponse>();
		for(AddressEntity entity: entityList)
		{
			AddressResponse response = new AddressResponse();
			response = mapAddressEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
	
	public Set<AddressEntity> mapAddressRequestToEntity(Set<AddressRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		Set<AddressEntity> entityList=new HashSet<AddressEntity>();
		for(AddressRequest request: requestList)
		{
			AddressEntity entity = mapAddressRequestToEntity(request);
			entityList.add(entity);
		}
		return entityList;
	}

	public Set<AddressResponse> mapAddressEntityToResponse(Set<AddressEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		Set<AddressResponse> responseList=new HashSet<AddressResponse>();
		for(AddressEntity entity: entityList)
		{
			AddressResponse response = new AddressResponse();
			response = mapAddressEntityToResponse(entity);
			responseList.add(response);
		}
		return responseList;
	}
}