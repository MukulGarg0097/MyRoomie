package com.myRoomie.request.dto;

import java.util.Set;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest extends BaseCreatedPojo{

	private Integer roomId;
	private Integer roomVariantId;
	private Double roomAmount;
	private Integer quantity;
	private Double subTotal;
	private Double totalDiscountAmount;
	private Double subTotalAfterDiscount;
	private Double totalTaxAmount;
	private Double netTotalAmount;
	
	private Set<DiscountRequest> discounts;
	
	private Set<TaxesRequest> taxes;
	
}
