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
@Table(name=EntityDetails.NearByPlaces.TABLE_NAME)
public class NearByPlacesEntity  extends BaseCreatedEntity{

	@Column
	private String placeName;
	@Column
	private String displayTime;
	@Column
	private String type;
	@Column
	private Integer propertyId;
	@Column(columnDefinition = "boolean default true", nullable = false)
	private Boolean showOnWebsite = true;
}
