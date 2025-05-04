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
@Table(name=EntityDetails.RequestCallBack.TABLE_NAME)
public class RequestCallBackEntity  extends BaseCreatedEntity{

	@Column
	private String name;
	@Column
	private String mobileNo;
}
