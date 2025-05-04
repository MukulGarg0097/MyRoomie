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
@Table(name=EntityDetails.ChargesEntity.TABLE_NAME)
public class ChargesEntity extends BaseCreatedEntity{

	@Column
	private String chargeName;
	@Column
	private String description;
	@Column
	private Double chargeAmount;
	@Column
	private Integer roomId;
	
}
