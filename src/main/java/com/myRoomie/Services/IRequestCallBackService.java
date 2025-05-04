package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.RequestCallBackRequest;
import com.myRoomie.response.dto.RequestCallBackResponse;

public interface IRequestCallBackService{

	List<RequestCallBackResponse> findAll() throws BaseException;

	RequestCallBackResponse findById(Integer id) throws BaseException;

	RequestCallBackResponse save(RequestCallBackRequest request) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;

}
