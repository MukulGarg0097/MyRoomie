package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduledVisitsPojo extends BaseCreatedPojo {
    
	
	private String name;
	private String email;
	private Integer contactNo;
	private String visitingDate;
	private Integer propertyId;
	private Integer roomId;
	private Integer roomVariantId;
	
}
