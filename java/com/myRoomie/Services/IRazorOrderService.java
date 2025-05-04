package com.myRoomie.Services;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.response.RazorOrderResponse;
import com.myRoomie.response.TransactionResponse;

public interface IRazorOrderService {

	public RazorOrderResponse createOrder(TransactionResponse transaction) throws BaseException;

}
