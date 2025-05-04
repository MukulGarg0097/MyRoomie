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
@Table(name=EntityDetails.RoomAmenitiesMapper.TABLE_NAME)
public class RoomAmenitiesMapper extends BaseCreatedEntity{

	@Column
	private String amenityDescription;
	
	@Column(columnDefinition = "bit(1) DEFAULT false")
	private Boolean	amenityMappingStatus=false;
	
	@Column(columnDefinition = "INT(11) DEFAULT NULL")
	private Integer masterRoomAmenityId;
	@Column
	private Integer roomId;
}