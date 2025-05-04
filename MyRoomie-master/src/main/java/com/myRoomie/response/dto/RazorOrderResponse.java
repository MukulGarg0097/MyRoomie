package com.myRoomie.response.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RazorOrderResponse  extends BaseCreatedPojo {

	private TransactionResponse transaction;
	
	private Integer amount; 
	private Integer amountPaid;
	private String notes;
	private String razorOrderCreatedAt;
	private Integer amountDue; 
	private String currency;
	private Integer transactionId;
	private String razorOrderId;
	private String entity;
	private String razorOfferId;
	private String status;
	private Integer attempts;
	private String razorpayPaymentId;
	private String razorpaySignature;
}