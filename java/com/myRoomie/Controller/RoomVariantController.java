package com.myRoomie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myRoomie.Services.IRoomVariantService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.RoomVariantRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.RoomVariantResponse;

@RestController
@RequestMapping(path="/roomVariant")
public class RoomVariantController {

	@Autowired
	IRoomVariantService roomVariantService;
//	, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
	
	@RequestMapping(path=PathMappings.RoomVariant.BASE_MAPPING_ALL, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<RoomVariantResponse>> findAll()
			throws BaseException{
		
		return new BaseResponse<List<RoomVariantResponse>>(false, roomVariantService.findAll(), ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.ID_PARAM, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<RoomVariantResponse> findById(
			@PathVariable(value = "id") Integer id)
			throws BaseException{
		if(id==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		RoomVariantResponse response = roomVariantService.findById(id);
		if(response.getId() == null) {
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		return new BaseResponse<RoomVariantResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.SAVE, method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<RoomVariantResponse> save(
			@RequestBody(required=true) RoomVariantRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		RoomVariantResponse response = roomVariantService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_SAVING);
		}
		return new BaseResponse<RoomVariantResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.UPDATE, method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<RoomVariantResponse> update(
			@RequestBody(required=true) RoomVariantRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		RoomVariantResponse response = roomVariantService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_UPDATING);
		}
		return new BaseResponse<RoomVariantResponse>(false, response, ResponseCode.OK);
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
		if(roomVariantService.deleteById(id))
			return new BaseResponse<>(false, null, ResponseCode.OK);
		return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_DELETING);
	}
}
