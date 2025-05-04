package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargesPojo extends BaseCreatedPojo{

	private String chargeName;
	private String description;
	private Double chargeAmount;
	private Integer roomId;
	
}
