package com.myRoomie.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myRoomie.constants.EntityDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name=EntityDetails.CouponCodeEntity.TABLE_NAME)
public class CouponCodeEntity extends BaseCreatedEntity {

	@Column(unique=true)
	private String couponCodeName;
	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean isActive=false;
	@Column(columnDefinition = "boolean default true", nullable = false)
	private Boolean isExpired=true;
	@Column
	private Date expiryDate;
	@Column
	private Date startDate;
	@Column
	private Integer discountPercentage;
	
}
