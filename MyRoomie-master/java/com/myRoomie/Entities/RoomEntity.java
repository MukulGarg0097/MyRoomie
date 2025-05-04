package com.myRoomie.Entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.myRoomie.constants.EntityDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name=EntityDetails.RoomEntity.TABLE_NAME)
public class RoomEntity extends BaseCreatedEntity {

	@Column
	private String roomTypeName;
	@Column
	private Double startAmount;
	@Column(columnDefinition = "bit(1) DEFAULT 1")
	private Boolean availabilityStatus=true;
	@Column
	private Integer noOfBeds;
	@Column
	private Integer maxNoOfGuests;
	@Column
	private String sharingStatus;
	@Column
	private String services;
	@Column
	private String equipments;
	@Column
	private Integer propertyId;
	@Column(columnDefinition = "bit(1) DEFAULT 1")
	private Boolean isActive=true;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "roomId")
	private Set<RoomVariantEntity> roomVariants;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "roomId")
	private Set<FileEntity> roomImagesUrl;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "roomId")
	private Set<RoomAmenitiesMapper> roomAmenitiesMapper;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "roomId")
	private Set<ChargesEntity> charges;
	
}
