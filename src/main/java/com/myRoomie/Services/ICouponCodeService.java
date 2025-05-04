package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.CouponCodeRequest;
import com.myRoomie.response.dto.CouponCodeResponse;

public interface ICouponCodeService {

	List<CouponCodeResponse> findAll() throws BaseException;
	CouponCodeResponse findById(Integer id) throws BaseException;
	CouponCodeResponse save(CouponCodeRequest request) throws BaseException;
	Boolean deleteById(Integer id) throws BaseException;
	List<CouponCodeResponse> findByIsActive() throws BaseException;
	CouponCodeResponse findByCouponCodeName(String couponCodeName) throws BaseException;
	
}
