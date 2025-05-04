package com.myRoomie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myRoomie.Services.ICouponCodeService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.CouponCodeRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.CouponCodeResponse;

@RestController
@RequestMapping(path="/couponCode")
public class CouponCodeController {

	@Autowired
	ICouponCodeService couponCodeService;
	
	@RequestMapping(path=PathMappings.CouponCode.BASE_MAPPING_ALL, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<CouponCodeResponse>> findAll()
			throws BaseException{
		
		return new BaseResponse<List<CouponCodeResponse>>(false, couponCodeService.findAll(), ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.CouponCode.COUPON_CODE_NAME, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<CouponCodeResponse> findByCouponCodeName(
			@RequestParam(required=true , value = "couponCodeName") String couponCodeName)
			throws BaseException{
		if(couponCodeName==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.COUPON_CODE_NAME_NOT_PRESENT);
		}
		CouponCodeResponse response = couponCodeService.findByCouponCodeName(couponCodeName);
		
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.NO_COUPON_CODE_FOUND);
		}
		if(!response.getIsActive()) {
			return new BaseResponse<>(true, null, ResponseCode.COUPON_CODE_NOT_ACTIVE);
		}
		if(response.getIsExpired()) {
			return new BaseResponse<>(true, null, ResponseCode.COUPON_CODE_EXPIRED);
		}
		return new BaseResponse<CouponCodeResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.ID_PARAM, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<CouponCodeResponse> findById(
			@PathVariable(value = "id") Integer id)
			throws BaseException{
		if(id==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		CouponCodeResponse response = couponCodeService.findById(id);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		return new BaseResponse<CouponCodeResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.CouponCode.BASE_MAPPING_ALL_ACTIVE, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<CouponCodeResponse>> findByIsActive()
			throws BaseException{
		return new BaseResponse<List<CouponCodeResponse>>(false, couponCodeService.findByIsActive(), ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.SAVE, method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<CouponCodeResponse> save(
			@RequestBody(required=true) CouponCodeRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		CouponCodeResponse response = couponCodeService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.COUPON_CODE_ALREADY_PRESENT);
		}
		return new BaseResponse<CouponCodeResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.UPDATE, method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<CouponCodeResponse> update(
			@RequestBody(required=true) CouponCodeRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		CouponCodeResponse response = couponCodeService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_UPDATING);
		}
		return new BaseResponse<CouponCodeResponse>(false, response, ResponseCode.OK);
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
		if(couponCodeService.deleteById(id))
		{
			return new BaseResponse<>(false, null, ResponseCode.OK);
		}
		return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_DELETING);
	}
}
