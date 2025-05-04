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
	@Column(columnDefinition = "bit(1) DEFAULT 0")
	private Boolean availabilityStatus;
	@Column
	private Integer roomId;
}
