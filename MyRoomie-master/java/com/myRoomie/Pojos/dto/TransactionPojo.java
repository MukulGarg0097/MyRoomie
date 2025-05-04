package com.myRoomie.Pojos.dto;

import java.util.Set;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionPojo extends BaseCreatedPojo {

	private Integer roomId;
	private Integer roomVariantId;
	private Double roomAmount;
	private Integer quantity;
	private Double subTotal;
	private Double totalDiscountAmount;
	private Double subTotalAfterDiscount;
	private Double totalTaxAmount;
	private Double netTotalAmount;

	private Set<DiscountPojo> discounts;
	
	private Set<TaxesPojo> taxes;
}
