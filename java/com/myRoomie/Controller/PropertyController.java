package com.myRoomie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myRoomie.Services.IPropertyService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.PropertyMapper;
import com.myRoomie.request.PropertyRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.PropertyResponse;

@RestController
@RequestMapping(path="/property")
public class PropertyController {

	
	@Autowired
	IPropertyService propertyService;
	
	@Autowired
	PropertyMapper propMapper;
	
	@RequestMapping(path=PathMappings.Property.BASE_MAPPING_ALL, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findAll()
			throws BaseException{
		 List<PropertyResponse> responseList= propertyService.findAll();
		return new BaseResponse<List<PropertyResponse>>(false, responseList, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Property.BASE_MAPPING_ALL_ACTIVE, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findByIsActive()
			throws BaseException{
		 List<PropertyResponse> responseList= propertyService.findByIsActive();
		return new BaseResponse<List<PropertyResponse>>(false, responseList, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.ID_PARAM, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<PropertyResponse> findById(
			@PathVariable(value = "id") Integer id)
			throws BaseException{
		if(id==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		PropertyResponse response = propertyService.findById(id);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		return new BaseResponse<PropertyResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Property.CITY, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findByCity(
			@RequestParam(required=true , value = "city") String city)
			throws BaseException{
		if(city==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.CITY_NOT_PRESENT);
		}
		List<PropertyResponse> response = propertyService.findByCity(city);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.NO_CITY_FOUND);
		}
		return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Property.FEATURED, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findAllByFeatured(
			@RequestParam(required=true , value = "isFeatureFlag") Boolean isFeatureFlag)
			throws BaseException{
		if(isFeatureFlag==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		List<PropertyResponse> response = propertyService.findAllByFeatured(isFeatureFlag);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Property.PROPERTY_GENDER_TYPE, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findByGenderType(
			@RequestParam(required=true , value = "propertyGenderType") String propertyGenderType)
			throws BaseException{
		if(propertyGenderType==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.PROPERTY_GENDER_TYPE);
		}
		List<PropertyResponse> response = propertyService.findByGenderType(propertyGenderType);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.PROPERTY_GENDER_TYPE);
		}
		return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Property.PROPERTY_TYPE, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findByPropertyType(
			@RequestParam(required=true , value = "propertyType") String propertyType)
			throws BaseException{
		if(propertyType==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.PROPERTY_TYPE);
		}
		List<PropertyResponse> response = propertyService.findByPropertyType(propertyType);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.PROPERTY_TYPE);
		}
		return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Property.PROPERTY_TYPE_AND_CITY, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findByPropertyTypeAndCity(
			@RequestParam(required=true , value = "propertyType") String propertyType,
			@RequestParam(required=true , value = "city") String city)
			throws BaseException{
		if(propertyType==null || city==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INCORRECT_JSON_DATA);
		}
		List<PropertyResponse> response = propertyService.findByPropertyTypeAndCity(propertyType,city);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Property.PROPERTY_GENDER_TYPE_AND_CITY, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findByPropertyGenderTypeAndCity(
			@RequestParam(required=true , value = "propertyGenderType") String propertyGenderType,
			@RequestParam(required=true , value = "city") String city)
			throws BaseException{
		if(propertyGenderType==null || city==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INCORRECT_JSON_DATA);
		}
		List<PropertyResponse> response = propertyService.findByPropertyGenderTypeAndCity(propertyGenderType,city);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Property.LAT_LONG, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findByLattitudeAndLongitude(
			@RequestParam(required=true , value = "lattitude") Double lattitude,
			@RequestParam(required=true , value = "longitude") Double longitude)
			throws BaseException{
		if(lattitude==null || longitude==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.BAD_REQUEST);
		}
		List<PropertyResponse> response = propertyService.findByLattitudeAndLongitude(lattitude,longitude);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.SAVE, method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<PropertyResponse> save(
			@RequestBody(required=false) PropertyRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		PropertyResponse response = propertyService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_SAVING);
		}
		return new BaseResponse<PropertyResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.UPDATE, method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<PropertyResponse> update(
			@RequestBody(required=false) PropertyRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		PropertyResponse response = propertyService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_UPDATING);
		}
		return new BaseResponse<PropertyResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.ID_PARAM, method = RequestMethod.DELETE)
	@ResponseBody
	public BaseResponse<?> deleteById(
			@PathVariable(required=true , value = "id") Integer id)
			throws BaseException{
		if(id==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		if(propertyService.deleteById(id))
		{
			return new BaseResponse<>(false, null, ResponseCode.OK);
		}
		return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_DELETING);
	}

}



//Pagination


//@RequestMapping(path=PathMappings.Property.BASE_MAPPING_ALL, method = RequestMethod.GET)
//@ResponseBody
//public BaseResponse<Page<PropertyResponse>> findAll(Pageable pageable)
//		throws BaseException{
//	Page<PropertyEntity> paging=propertyRepository.findAll(pageable);
//	 Page<PropertyResponse> pagedResponse=new PageImpl<>(propMapper.mapPropertyEntityToResponse(paging.getContent()),pageable,paging.getTotalElements());
//	return new BaseResponse<Page<PropertyResponse>>(false, pagedResponse, ResponseCode.OK);
//}