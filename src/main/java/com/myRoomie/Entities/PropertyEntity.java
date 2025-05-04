package com.myRoomie.Entities;

import com.myRoomie.constants.EntityDetails;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name=EntityDetails.PropertyEntity.TABLE_NAME)
public class PropertyEntity extends BaseCreatedEntity {

    @Column(unique = true)
    private String idProductLocation;

	@Column
    @NotNull(message = "Property Name may not be null")
	private String propertyName;
	@Column
	private String aboutProperty;
	@Column
	private String sharingType;
	@Column
	private String roomType;
	@Column
	private String propertyGenderType;
	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean isFeatureFlag=false;
	@Column(columnDefinition = "boolean default true", nullable = false)
	private Boolean isActive=true;
	@Column
	private String propertyType;
	@Column
    @NotNull(message = "City may not be null")
	private String city;
	@Column
	private Double startAmount;
	@Column
	private Double discPercentage;
	@Column
	private String amenityIds;
	@Column
	private String view360;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "propertyId")
	private Set<FileEntity> propertyImagesUrl;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(updatable=true, name = "propertyId")
	private Set<RoomEntity> rooms;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(updatable=true, name = "propertyId")
	private Set<NearByPlacesEntity> nearByPlaces;
	
	@OneToOne(fetch=FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private AddressEntity address;
	
}
