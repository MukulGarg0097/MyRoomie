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
@Table(name=EntityDetails.DiscountEntity.TABLE_NAME)
public class DiscountEntity extends BaseCreatedEntity{

	@Column
	private Double discountAmount;
	@Column
	private Double discountPercent;
	@Column
	private Double discountUpon;
	@Column
	private String discountType;
	@Column
	private Integer couponCodeId;
	
	
	
}