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
@Table(name=EntityDetails.AddressEntity.TABLE_NAME)
public class AddressEntity  extends BaseCreatedEntity{

	@Column(name="flat_no")
	private String flatNo;
	@Column(name="house_no")
	private String houseNo;
	@Column(name="locality")
	private String locality;
	@Column(name="landmark")
	private	String landmark;
	@Column(name="latitude")
	private Double latitude;
	@Column(name="longitude")
	private Double longitude;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="country")
	private String country;
	@Column(name="pincode")
	private String pincode;
}
