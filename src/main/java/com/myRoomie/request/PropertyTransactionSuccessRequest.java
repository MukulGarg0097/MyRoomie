package com.myRoomie.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyTransactionSuccessRequest{

	@JsonProperty("razorpay_order_id")
	String razorpayOrderId;
	@JsonProperty("razorpay_payment_id")
	String razorpayPaymentId;
	@JsonProperty("razorpay_signature")
	String razorpaySignature;
	String status;
}
