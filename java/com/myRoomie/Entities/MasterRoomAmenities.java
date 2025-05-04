package com.myRoomie.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myRoomie.constants.EntityDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name=EntityDetails.RoomAmenity.TABLE_NAME)
public class MasterRoomAmenities extends BaseCreatedEntity{

	@Column
	private String masterAmenityName;
	@Column
	private String masterAmenityDescription;
	@Column
	private String masterAmenityIconUrl;
}
