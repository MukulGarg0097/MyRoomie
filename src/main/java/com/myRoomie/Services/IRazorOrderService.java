package com.myRoomie.Services;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.response.dto.RazorOrderResponse;
import com.myRoomie.response.dto.TransactionResponse;

public interface IRazorOrderService {

	public RazorOrderResponse createOrder(TransactionResponse transaction) throws BaseException;

}
