package com.myRoomie.request.dto;

import java.util.Set;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyTransactionRequest extends BaseCreatedPojo{

	private String createdByName;
	private String createdByEmail;
	private String contactNo;
	private String date ;
	private Integer propertyId;
	private Double amount;
	private Integer quantity;
	private Double subTotal;
	private Double totalDiscountAmount;
	private Double subTotalAfterDiscount;
	private Double totalTaxAmount;
	private Double netTotalAmount;
	
	private Set<DiscountRequest> discounts;
	
	private Set<TaxesRequest> taxes;
	
}
