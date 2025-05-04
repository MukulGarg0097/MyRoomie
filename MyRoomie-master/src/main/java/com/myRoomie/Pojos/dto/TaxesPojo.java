package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxesPojo extends BaseCreatedPojo{

	private String taxName;
	private Double taxAmount;
	private Double taxPercent;
	private Double taxUpon;
	
}