package com.myRoomie.Pojos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GooglePlaceResponsePojo {
	List<result> results;
	String status;
	
	@Getter
	@Setter
	public class result{
		String formatted_address;
		geo geometry;
		String name;
		String[] types;
		
		@Getter
		@Setter
		public class geo{
			loc location;
			@Getter
			@Setter
			public class loc{
				Double lat;
				Double lng;
			}
		
		}
	}
}
