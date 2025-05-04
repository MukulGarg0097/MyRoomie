package com.myRoomie.Entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myRoomie.constants.EntityDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name=EntityDetails.PropertyEntity.TABLE_NAME)
public class PropertyEntity extends BaseCreatedEntity {

	@Column
	private String propertyName;
	@Column
	private String aboutProperty;
	@Column
	private String propertyGenderType;
	@Column(columnDefinition = "bit(1) DEFAULT 0")
	private Boolean isFeatureFlag=false;
	@Column(columnDefinition = "bit(1) DEFAULT 1")
	private Boolean isActive=true;
	@Column
	private String propertyType;
	@Column
	private String city;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "propertyId")
	private Set<FileEntity> propertyImagesUrl;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(updatable=true, name = "propertyId")
	private Set<RoomEntity> rooms;
	
	@OneToOne(fetch=FetchType.EAGER ,cascade = CascadeType.ALL )
    @JoinColumn(name = "addressId")
    private AddressEntity address;
}
