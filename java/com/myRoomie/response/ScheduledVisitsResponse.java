package com.myRoomie.response;

import com.myRoomie.Pojos.BaseCreatedPojo;
import com.myRoomie.Pojos.PropertyScheduledVisitsPojo;
import com.myRoomie.Pojos.RoomScheduledVisitsPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduledVisitsResponse extends BaseCreatedPojo{


	private String name;
	private String email;
	private Integer contactNo;
	private String visitingDate;
	private Integer propertyId;
	private Integer roomId;
	private Integer roomVariantId;
	private PropertyScheduledVisitsPojo property;
	private RoomScheduledVisitsPojo room;
	private RoomVariantResponse roomVariant;
	
}
