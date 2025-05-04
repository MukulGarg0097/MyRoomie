package com.myRoomie.Services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.PropertyTransactionSuccessRequest;
import com.myRoomie.request.dto.PropertyTransactionRequest;
import com.myRoomie.response.PropertyRazorOrderResponse;

public interface IPropertyTransactionService{

	public PropertyRazorOrderResponse save(PropertyTransactionRequest request) throws BaseException;

	public 	List<PropertyRazorOrderResponse> findAll(Pageable pageable) throws BaseException;

	public 	PropertyRazorOrderResponse findById(Integer id) throws BaseException;

	public 	Boolean deleteById(Integer id) throws BaseException;

	public PropertyRazorOrderResponse transactionSuccess(PropertyTransactionSuccessRequest request) throws BaseException;
}
