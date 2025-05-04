package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.ChargesRequest;
import com.myRoomie.response.dto.ChargesResponse;

public interface IChargesService{

	ChargesResponse save(ChargesRequest request) throws BaseException;

	List<ChargesResponse> findAll() throws BaseException;

	ChargesResponse findById(Integer id) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;
}
