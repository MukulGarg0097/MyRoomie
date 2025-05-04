package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.response.PropertyRazorOrderResponse;
import com.myRoomie.response.dto.PropertyTransactionResponse;
import com.razorpay.Payment;

public interface IPropertyRazorOrderService {

	public PropertyRazorOrderResponse createOrder(PropertyTransactionResponse transaction) throws BaseException;

	public List<Payment> getOrderById(String orderId) throws BaseException;

}
