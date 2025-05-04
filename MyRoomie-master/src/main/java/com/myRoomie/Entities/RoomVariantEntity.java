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
@Table(name=EntityDetails.RoomVariant.TABLE_NAME)
public class RoomVariantEntity extends BaseCreatedEntity{

	@Column
	private String roomVariantType;
	@Column
	private Double amount;
	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean availabilityStatus=false;
	@Column
	private Integer roomId;
}
