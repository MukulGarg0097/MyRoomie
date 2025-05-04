package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.RoomVariantRequest;
import com.myRoomie.response.RoomVariantResponse;

public interface IRoomVariantService {

	List<RoomVariantResponse> findAll() throws BaseException;

	RoomVariantResponse findById(Integer id) throws BaseException;

	RoomVariantResponse save(RoomVariantRequest request) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;

}
