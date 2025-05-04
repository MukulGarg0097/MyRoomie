package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomVariantPojo extends BaseCreatedPojo{

	private String roomVariantType;
	private Double amount;
	private Boolean availabilityStatus;
	private Integer roomId;
}
