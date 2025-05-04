package com.myRoomie.request.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class PropertyRequest extends BaseCreatedRequest{

    private String idProductLocation;
    @NotNull(message = "Property Name may not be null")
    private String propertyName;
	private String aboutProperty;
	private String sharingType;
	private String roomType;
	private String propertyGenderType;
	private Boolean isFeatureFlag;
	private String propertyType;
    @NotNull(message = "City may not be null")
    private String city;
	private Boolean isActive;
	private Double startAmount;
	private Double discPercentage;
	
	private Integer[] amenityIds;
	
	private Set<FileRequest> propertyImagesUrl;
	
	private Set<RoomRequest> rooms;
	
	private Set<NearByPlacesRequest> nearByPlaces;
	
    private AddressRequest address;
    
    private String view360;
	
}
