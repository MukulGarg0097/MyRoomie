package com.myRoomie.Pojos.dto;

import java.util.Date;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponCodePojo extends BaseCreatedPojo {

	private String couponCodeName;
	private Boolean isActive=false;
	private Boolean isExpired=true;
	private Date expiryDate;
	private Date startDate;
	private Integer discountPercentage;
	
}
