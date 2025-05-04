package com.myRoomie.response;

import com.myRoomie.Pojos.BaseCreatedPojo;
import com.myRoomie.response.dto.PropertyTransactionResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyRazorOrderResponse  extends BaseCreatedPojo {

	private PropertyTransactionResponse transaction;
	
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