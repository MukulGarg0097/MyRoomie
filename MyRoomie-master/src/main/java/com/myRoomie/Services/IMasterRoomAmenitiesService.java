package com.myRoomie.Services;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.MasterRoomAmenitiesRequest;
import com.myRoomie.response.dto.MasterRoomAmenitiesResponse;

import java.util.List;

public interface IMasterRoomAmenitiesService{

	MasterRoomAmenitiesResponse save(MasterRoomAmenitiesRequest request) throws BaseException;

	List<MasterRoomAmenitiesResponse> findAll() throws BaseException;

	MasterRoomAmenitiesResponse findById(Integer id) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;


    Boolean fileSecure();

}
