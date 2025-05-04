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
@Table(name = EntityDetails.ScheduledVisitsEntity.TABLE_NAME)
public class ScheduledVisitsEntity extends BaseCreatedEntity {
    
	
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private Integer contactNo;
	@Column(columnDefinition="timestamp DEFAULT CURRENT_TIMESTAMP")
	private Date visitingDate;
	@Column
	private Integer propertyId;
	@Column
	private Integer roomId;
	@Column
	private Integer roomVariantId;
	
}
