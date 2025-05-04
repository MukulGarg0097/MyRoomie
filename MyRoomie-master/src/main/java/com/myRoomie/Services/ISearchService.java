package com.myRoomie.Services;

import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.SearchRequest;
import com.myRoomie.response.SearchResponse;
import com.myRoomie.response.dto.PropertyResponse;

public interface ISearchService{
	
	public List<SearchResponse> Search(String search, Double latitude, Double longitude) throws BaseException;

	public List<SearchResponse> PropertySearch(String search) throws BaseException;

	public List<SearchResponse> GoogleLocalitySearch(String search, Double latitude, Double longitude)
			throws BaseException, ApiException, InterruptedException, IOException;

	public List<PropertyResponse> getPropertiesNearBy(SearchRequest request) throws BaseException;

	public List<SearchResponse> NearByPlacesSearch(String search) throws BaseException;

	public List<SearchResponse> googleSearch(String search, Double lat, Double lng) throws BaseException, IOException;
	
}
