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
@Table(name=EntityDetails.TaxesEntity.TABLE_NAME)
public class TaxesEntity extends BaseCreatedEntity{

	@Column
	private String taxName;
	@Column
	private Double taxAmount;
	@Column
	private Double taxPercent;
	@Column
	private Double taxUpon;
	@Column
	private Integer propertyTransactionId;
	
}