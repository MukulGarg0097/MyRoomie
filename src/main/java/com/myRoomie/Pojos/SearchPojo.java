package com.myRoomie.Pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPojo{
    //id or idProductLocation
	public Integer id;
    public String idProductLocation;
	public Double latitude;
	public Double longitude;
	public String name;
	public String displayName;
	public String type;
	public String address;
}
