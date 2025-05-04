package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountPojo extends BaseCreatedPojo{

	private Double discountAmount;
	private Double discountPercent;
	private Double discountUpon;
	private String discountType;
	private Integer couponCodeId;
	
	
	
}