package com.myRoomie.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.TransactionRequest;
import com.myRoomie.response.dto.RazorOrderResponse;

public interface ITransactionService{

	public RazorOrderResponse save(TransactionRequest request) throws BaseException;

	public 	Page<RazorOrderResponse> findAll(Pageable pageable) throws BaseException;

	public 	RazorOrderResponse findById(Integer id) throws BaseException;

	public 	Boolean deleteById(Integer id) throws BaseException;
}
