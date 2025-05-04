package com.myRoomie.mapping;

import com.google.maps.model.PlacesSearchResult;
import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.response.SearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchMapper {
	
	public SearchMapper() {
	}
		
	public SearchResponse mapPropertyToSearchResponse(PropertyEntity request, String type)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		SearchResponse response=new SearchResponse();
		response.setId(request.getId());
		response.setIdProductLocation(request.getIdProductLocation());
		response.setLatitude(request.getAddress().getLatitude());
		response.setLongitude(request.getAddress().getLongitude());
		response.setName(request.getPropertyName());
		response.setType(type);
		String address=request.getAddress().getFlatNo()+" "+
					   request.getAddress().getHouseNo()+" "+
					   request.getAddress().getLocality()+" "+
					   request.getAddress().getLandmark()+" "+
					   request.getAddress().getCity()+" "+
					   request.getAddress().getState()+" "+
					   request.getAddress().getCountry()+" "+
					   request.getAddress().getPincode();
//		address.replaceAll("null ", "");
		response.setDisplayName(request.getPropertyName()+" \n "+address.replaceAll("null ", ""));
		response.setAddress(address.replaceAll("null ", ""));
		
		return response;
	}
	
	public List<SearchResponse> mapPropertyToSearchResponse(List<PropertyEntity> request, String string)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		List<SearchResponse> response=new ArrayList<>();
		for(PropertyEntity entity:request)
		{
			response.add(mapPropertyToSearchResponse(entity, string));
		}
		return response;
	}

	public SearchResponse mapGoogleSearchToSearchResponse(PlacesSearchResult request, String type) {
		
		
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		SearchResponse response=new SearchResponse();
		if(request.formattedAddress!=null)	
			response.setAddress(request.formattedAddress);
		if(request.name!=null && (type.equalsIgnoreCase("school") || type.equalsIgnoreCase("university")))
			response.setDisplayName("Properties nearby "+request.name);
		else
			response.setDisplayName("Properties in "+request.name);
		response.setLatitude(request.geometry.location.lat);
		response.setLongitude(request.geometry.location.lng);
		if(request.name!=null)
			response.setName(request.name);
		if(type!=null)
			response.setType(type);
		return response;
	}
	
	public List<SearchResponse> mapGoogleSearchToSearchResponse(PlacesSearchResult[] request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		List<SearchResponse> response=new ArrayList<>();
		for(int i=0;i<request.length;i++)
		{
			String[] searchTypes= request[i].types;
			for(String type:searchTypes)
			{
				if(type.equalsIgnoreCase("school") || type.equalsIgnoreCase("university"))
				{
					response.add(mapGoogleSearchToSearchResponse(request[i],"university"));
					break;
				}
				else if(type.equalsIgnoreCase("locality") || type.equalsIgnoreCase("city")|| type.equalsIgnoreCase("state")
						|| type.equalsIgnoreCase("country")|| type.equalsIgnoreCase("geocode")|| type.equalsIgnoreCase("street_address")
						|| type.equalsIgnoreCase("street_number")|| type.equalsIgnoreCase("sublocality")
						|| type.equalsIgnoreCase("sublocality_level_4")|| type.equalsIgnoreCase("sublocality_level_5")
						|| type.equalsIgnoreCase("sublocality_level_3")|| type.equalsIgnoreCase("sublocality_level_2")
						|| type.equalsIgnoreCase("sublocality_level_1"))
				{
					response.add(mapGoogleSearchToSearchResponse(request[i],"locality"));
					break;
				}
			}
		}
		return response;
	}

	public SearchResponse mapNearByPlaceToSearchResponse(String request, String type)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		SearchResponse response=new SearchResponse();
		response.setName(request);
		response.setType(type);
		response.setDisplayName("Properties near by "+ request);
		
		return response;
	}
	
	public List<SearchResponse> mapNearByPlaceToSearchResponse(List<String> request, String string)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		List<SearchResponse> response=new ArrayList<>();
		for(String entity:request)
		{
			response.add(mapNearByPlaceToSearchResponse(entity, string));
		}
		return response;
	}
}