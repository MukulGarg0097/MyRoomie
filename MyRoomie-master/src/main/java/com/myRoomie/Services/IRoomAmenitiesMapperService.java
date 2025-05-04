package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.RoomAmenitiesMapperRequest;
import com.myRoomie.response.dto.RoomAmenitiesMapperResponse;

public interface IRoomAmenitiesMapperService{

	RoomAmenitiesMapperResponse save(RoomAmenitiesMapperRequest request) throws BaseException;

	List<RoomAmenitiesMapperResponse> findAll() throws BaseException;

	RoomAmenitiesMapperResponse findById(Integer id) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;


}
