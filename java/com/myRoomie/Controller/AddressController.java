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

import com.myRoomie.Services.IAddressService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.AddressRequest;
import com.myRoomie.response.AddressResponse;
import com.myRoomie.response.BaseResponse;

@RestController
@RequestMapping(path="/address")
public class AddressController {

	@Autowired
	IAddressService addressService;
//	, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
	
	@RequestMapping(path=PathMappings.Address.BASE_MAPPING_ALL, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<AddressResponse>> findAll()
			throws BaseException{
		return new BaseResponse<List<AddressResponse>>(false, addressService.findAll(), ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.ID_PARAM, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<AddressResponse> findById(
			@PathVariable(value = "id") Integer id)
			throws BaseException{
		if(id==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		AddressResponse response = addressService.findById(id);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		return new BaseResponse<AddressResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Address.CITY, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<AddressResponse>> findByCity(
			@RequestParam(required=true , value = "city") String city)
			throws BaseException{
		if(city==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.CITY_NOT_PRESENT);
		}
		List<AddressResponse> response = addressService.findByCity(city);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.NO_CITY_FOUND);
		}
		return new BaseResponse<List<AddressResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Address.STATE, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<AddressResponse>> findByState(
			@RequestParam(required=true , value = "state") String state)
			throws BaseException{
		if(state==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.STATE_NOT_PRESENT);
		}
		List<AddressResponse> response = addressService.findByState(state);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.STATE_NOT_PRESENT);
		}
		return new BaseResponse<List<AddressResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Address.COUNTRY, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<AddressResponse>> findByCountry(
			@RequestParam(required=true , value = "country") String country)
			throws BaseException{
		if(country==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.COUNTRY_NOT_PRESENT);
		}
		List<AddressResponse> response = addressService.findByCountry(country);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.COUNTRY_NOT_PRESENT);
		}
		return new BaseResponse<List<AddressResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Address.PINCODE, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<AddressResponse>> findByPinicode(
			@RequestParam(required=true , value = "pincode") String pincode)
			throws BaseException{
		if(pincode==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.PINCODE_NOT_PRESENT);
		}
		List<AddressResponse> response = addressService.findByPincode(pincode);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.PINCODE_NOT_PRESENT);
		}
		return new BaseResponse<List<AddressResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Address.LAT_LONG, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<AddressResponse>> findByLatAndLong(
			@RequestParam(required=true , value = "lattitude") Double lattitude,
			@RequestParam(required=true , value = "longitude") Double longitude)
			throws BaseException{
		if(lattitude==null || longitude==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.BAD_REQUEST);
		}
		List<AddressResponse> response = addressService.findByLattitudeAndLongitude(lattitude, longitude);
		if(CollectionUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ADDRESS_NOT_PRESENT);
		}
		return new BaseResponse<List<AddressResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.SAVE, method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<AddressResponse> save(
			@RequestBody(required=true) AddressRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		AddressResponse response = addressService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_SAVING);
		}
		return new BaseResponse<AddressResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.UPDATE, method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<AddressResponse> update(
			@RequestBody(required=true) AddressRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		AddressResponse response = addressService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_UPDATING);
		}
		return new BaseResponse<AddressResponse>(false, response, ResponseCode.OK);
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
		if(addressService.deleteById(id))
			return new BaseResponse<>(false, null, ResponseCode.OK);
		return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_DELETING);
	}
}
