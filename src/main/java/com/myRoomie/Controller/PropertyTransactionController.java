package com.myRoomie.Controller;

import com.myRoomie.Services.IPropertyTransactionService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.PropertyTransactionSuccessRequest;
import com.myRoomie.request.dto.PropertyTransactionRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.PropertyRazorOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path=PathMappings.PropertyTransaction.BASE_MAPPING)
public class PropertyTransactionController {

	@Autowired
	IPropertyTransactionService transactionService;
	
	@RequestMapping(path=PathMappings.PropertyTransaction.BASE_MAPPING_ALL, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyRazorOrderResponse>> findAll(Pageable pageable)
			throws BaseException{
		List<PropertyRazorOrderResponse> response=transactionService.findAll(pageable);
		 return new BaseResponse<List<PropertyRazorOrderResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.ID_PARAM, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<PropertyRazorOrderResponse> findById(
			@PathVariable(value = "id") Integer id)
			throws BaseException{
		if(id==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		PropertyRazorOrderResponse response = transactionService.findById(id);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		return new BaseResponse<PropertyRazorOrderResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.SAVE, method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<PropertyRazorOrderResponse> save(
			@RequestBody(required=true) PropertyTransactionRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		PropertyRazorOrderResponse response = transactionService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_SAVING);
		}

        return new BaseResponse<PropertyRazorOrderResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.UPDATE, method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<PropertyRazorOrderResponse> update(
			@RequestBody(required=true) PropertyTransactionRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		PropertyRazorOrderResponse response = transactionService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_UPDATING);
		}
		return new BaseResponse<PropertyRazorOrderResponse>(false, response, ResponseCode.OK);
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
		if(transactionService.deleteById(id))
			return new BaseResponse<>(false, null, ResponseCode.OK);
		return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_DELETING);
	}
	
	@RequestMapping(path=PathMappings.PropertyTransaction.TRANSACTION_SUCCESS, method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<?> paymentTransactionSuccess(@RequestBody(required = true) PropertyTransactionSuccessRequest request)
			throws BaseException{
		if (ObjectUtils.isEmpty(request)) {
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		PropertyRazorOrderResponse response = transactionService.transactionSuccess(request);
		if(!ObjectUtils.isEmpty(response))
			return new BaseResponse<>(false, response.getStatus(), ResponseCode.OK);
		return new BaseResponse<>(true, "order is not present", ResponseCode.ERROR);
	}
}
