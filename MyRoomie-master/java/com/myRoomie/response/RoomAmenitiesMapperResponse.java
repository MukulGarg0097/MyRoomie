package com.myRoomie.response;

import com.myRoomie.Pojos.dto.RoomAmenitiesMapperPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomAmenitiesMapperResponse extends RoomAmenitiesMapperPojo{

	private MasterRoomAmenitiesResponse masterRoomAmenity;
}
