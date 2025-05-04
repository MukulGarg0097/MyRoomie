package com.myRoomie.request.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduledVisitsRequest extends BaseCreatedPojo{
	
	private String name;
	private String email;
	private String contactNo;
	private String visitingDate;
	private Integer propertyId;
	private Integer roomId;
	private Integer roomVariantId;
	
}
