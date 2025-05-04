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

import com.myRoomie.Services.IRoomService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.RoomMapper;
import com.myRoomie.request.RoomRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.RoomResponse;

@RestController
@RequestMapping(path="/room")
public class RoomController {

	@Autowired
	IRoomService roomService;
	
	@Autowired
	RoomMapper roomMapper;
	
	@RequestMapping(path=PathMappings.Room.BASE_MAPPING_ALL, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<RoomResponse>> findAll()
			throws BaseException{
		return new BaseResponse<List<RoomResponse>>(false, roomService.findAll(), ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Room.BASE_MAPPING_ALL_ACTIVE, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<RoomResponse>> findByIsActive()
			throws BaseException{
		return new BaseResponse<List<RoomResponse>>(false, roomService.findByIsActive(), ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.ID_PARAM, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<RoomResponse> findById(
			@PathVariable(value = "id") Integer id)
			throws BaseException{
		if(id==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		RoomResponse response = roomService.findById(id);
		if(response.getId() == null) {
			return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}
		return new BaseResponse<RoomResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Room.AVAILABILITY_STATUS, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<RoomResponse>> findByAvailabilityStatus(
			@RequestParam(required=false, value = "availabilityStatus") Boolean availabilityStatus)
			throws BaseException{
		if(availabilityStatus==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		List<RoomResponse> response = roomService.findByAvailabilityStatus(availabilityStatus);
		if(response == null) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<RoomResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Room.MAX_NO_OF_GUESTS, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<RoomResponse>> findByMaxNoOfGuests(
			@RequestParam(required=false, value = "maxNoOfGuests") Integer maxNoOfGuests)
			throws BaseException{
		if(maxNoOfGuests==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		List<RoomResponse> response = roomService.findByMaxNoOfGuests(maxNoOfGuests);
		if(response == null) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<RoomResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Room.NO_OF_BEDS, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<RoomResponse>> findByNoOfBeds(
			@RequestParam(required=false, value = "noOfBeds") Integer noOfBeds)
			throws BaseException{
		if(noOfBeds==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		List<RoomResponse> response = roomService.findByNoOfBeds(noOfBeds);
		if(response == null) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<RoomResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Room.START_AMOUNT, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<RoomResponse>> findByAmount(
			@RequestParam(required=false, value = "startAmount") Double startAmount)
			throws BaseException{
		if(startAmount==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.AMOUNT_NOT_PRESENT);
		}
		List<RoomResponse> response = roomService.findByStartAmount(startAmount);
		if(response == null) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<RoomResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.SAVE, method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<RoomResponse> save(
			@RequestBody(required=true) RoomRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		RoomResponse response = roomService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_SAVING);
		}
		return new BaseResponse<RoomResponse>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.UPDATE, method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse<RoomResponse> update(
			@RequestBody(required=true) RoomRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		RoomResponse response = roomService.save(request);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_UPDATING);
		}
		return new BaseResponse<RoomResponse>(false, response, ResponseCode.OK);
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
		if(roomService.deleteById(id))
		{
			return new BaseResponse<>(false, null, ResponseCode.OK);
		}
		return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_DELETING);
	}
}
