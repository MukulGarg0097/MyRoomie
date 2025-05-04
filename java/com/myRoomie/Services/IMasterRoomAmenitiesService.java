package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.MasterRoomAmenitiesRequest;
import com.myRoomie.response.MasterRoomAmenitiesResponse;

public interface IMasterRoomAmenitiesService{

	MasterRoomAmenitiesResponse save(MasterRoomAmenitiesRequest request) throws BaseException;

	List<MasterRoomAmenitiesResponse> findAll() throws BaseException;

	MasterRoomAmenitiesResponse findById(Integer id) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;


}
