package com.myRoomie.response.dto;

import java.util.Set;

import com.myRoomie.Pojos.BaseCreatedPojo;
import com.myRoomie.Pojos.PropertyScheduledVisitsPojo;
import com.myRoomie.Pojos.RoomScheduledVisitsPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse extends BaseCreatedPojo{

	private Integer roomId;
	private Integer roomVariantId;
	private Integer propertyId;
	private Double roomAmount;
	private Integer quantity;
	private Double subTotal;
	private Double totalDiscountAmount;
	private Double subTotalAfterDiscount;
	private Double totalTaxAmount;
	private Double netTotalAmount;
	
	private Set<DiscountResponse> discounts;
	
	private Set<TaxesResponse> taxes;
	
	private PropertyScheduledVisitsPojo property;
	
	private RoomScheduledVisitsPojo room;
	
	private RoomVariantResponse roomVariant;
}
