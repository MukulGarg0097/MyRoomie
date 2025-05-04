package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.NearByPlacesRequest;
import com.myRoomie.response.dto.NearByPlacesResponse;

public interface INearByPlacesService{

	List<NearByPlacesResponse> findAll() throws BaseException;

	NearByPlacesResponse findById(Integer id) throws BaseException;

	NearByPlacesResponse save(NearByPlacesRequest request) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;

}
