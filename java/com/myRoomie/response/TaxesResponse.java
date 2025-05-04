package com.myRoomie.response;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxesResponse extends BaseCreatedPojo{

	private String taxName;
	private Double taxAmount;
	private Double taxPercent;
	private Double taxUpon;
	
}
