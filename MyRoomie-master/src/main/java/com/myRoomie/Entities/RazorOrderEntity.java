package com.myRoomie.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myRoomie.constants.EntityDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name=EntityDetails.RazorOrderEntity.TABLE_NAME)
public class RazorOrderEntity extends BaseCreatedEntity {

	@Column
	private Integer amount; 
	@Column
	private Integer amountPaid;
	@Column
	private String notes;
	@Column
	private String razorOrderCreatedAt;
	@Column
	private Integer amountDue; 
	@Column
	private String currency;
	@Column
	private Integer transactionId;
	@Column
	private String razorOrderId;
	@Column
	private String entity;
	@Column
	private String razorOfferId;
	@Column
	private String status;
	@Column
	private Integer attempts;
	@Column
	private String razorpayPaymentId;
	@Column
	private String razorpaySignature;
	@Column
	private String invoiceId;
	@Column(columnDefinition="boolean default 0")
	private Boolean international=false;
	@Column
	private String paymentMethod;
	@Column
	private Integer amountRefunded;
	@Column
	private String refundStatus;
	@Column(columnDefinition="boolean default 0")
	private Boolean captured=false;
	@Column
	private String paymentDescription;
	@Column
	private String cardId;
	@Column
	private String bank;
	@Column
	private String wallet;
	@Column
	private String email;
	@Column
	private String vpa;
	@Column
	private String contact;
	@Column
	private String fee;
	@Column
	private String tax;
	@Column
	private String errorCode;
	@Column
	private String errorDescription;
	@Column
	private Integer paymentCreatedAt;
	
}
