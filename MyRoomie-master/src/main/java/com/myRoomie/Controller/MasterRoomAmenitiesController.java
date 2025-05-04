package com.myRoomie.Controller;

import com.myRoomie.Services.IMasterRoomAmenitiesService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.MasterRoomAmenitiesRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.dto.MasterRoomAmenitiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path=PathMappings.RoomAmenity.BASE_MAPPING)
public class MasterRoomAmenitiesController {

	@Autowired
	IMasterRoomAmenitiesService roomAmenityService;

	@RequestMapping(path=PathMappings.RoomAmenity.BASE_MAPPING_ALL, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<MasterRoomAmenitiesResponse>> findAll()
			throws BaseException{
		
		return new BaseResponse<List<MasterRoomAmenitiesResponse>>(false, roomAmenityService.findAll(), ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.ID_PARAM, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<MasterRoomAmenitiesResponse> findById(
			@PathVariable(value = "id") Integer id)
			throws BaseException{
		if(id==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		MasterRoomAmenitiesResponse response = roomAmenityService.findById(id);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		return new BaseResponse<MasterRoomAmenitiesResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.SAVE, method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<MasterRoomAmenitiesResponse> save(
			@RequestBody(required=true) MasterRoomAmenitiesRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		MasterRoomAmenitiesResponse response = roomAmenityService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_SAVING);
		}
		return new BaseResponse<MasterRoomAmenitiesResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.UPDATE, method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<MasterRoomAmenitiesResponse> update(
			@RequestBody(required=true) MasterRoomAmenitiesRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		MasterRoomAmenitiesResponse response = roomAmenityService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_UPDATING);
		}
		return new BaseResponse<MasterRoomAmenitiesResponse>(false, response, ResponseCode.OK);
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
		if(roomAmenityService.deleteById(id))
			return new BaseResponse<>(false, null, ResponseCode.OK);
		return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_DELETING);
	}

    @RequestMapping(path = PathMappings.File.FILE_SECURE, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<Boolean> fileSecure() throws BaseException {

        return new BaseResponse<Boolean>(false, roomAmenityService.fileSecure(), ResponseCode.OK);
    }
}
