package com.myRoomie.Pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RazorPaymentResponsePojo {
	
	private String id;
	private String entity;
	private Integer amount;
	private String currency;
	private String status;
	private Integer amount_refunded;
	private String refund_status;
	private String email;
	private String contact;
	private String error_code;
	private String error_description;
	private Integer created_at;
	private String[] notes;
	private String invoice_id;
	private Boolean international;
	private String method;
	private Boolean captured;
	private String description;
	private String card_id;
	private String bank;
	private String wallet;
	private String vpa;
	private String fee;
	private String tax;
	
}