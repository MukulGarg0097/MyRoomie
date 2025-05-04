package com.myRoomie.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponse{

	public Integer id;
	public Double latitude;
	public Double longitude;
	public String name;
	public String displayName;
	public String type;
	public String address;
}
