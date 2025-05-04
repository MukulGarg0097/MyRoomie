package com.myRoomie.Services.ServicesImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.Repository.IPropertyRepository;
import com.myRoomie.Services.ISearchService;
import com.myRoomie.Utilities.CoordinatesDistanceUtil;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.PropertyMapper;
import com.myRoomie.mapping.SearchMapper;
import com.myRoomie.request.SearchRequest;
import com.myRoomie.response.PropertyResponse;
import com.myRoomie.response.SearchResponse;

@Service
public class SearchServiceImpl implements ISearchService{

	@Value("${com.googlemaps.ApiKey}")
	String googleApikey;
	
	@Autowired
	IPropertyRepository propRepo;
	
	@Autowired
	PropertyMapper propMap;
	
	@Autowired
	SearchMapper map;
	
	@Override
	public List<SearchResponse> Search(String search, Double latitude, Double longitude)  throws BaseException{
		
		List<SearchResponse> response=new ArrayList<>();
		try {
			response.addAll(GoogleLocalitySearch(search, latitude, longitude));
			response.addAll(PropertySearch(search));
		} catch (Exception e) {
		}
		return response;
	}
	
	@Override
	public List<SearchResponse> PropertySearch(String search)  throws BaseException{
		
		List<PropertyEntity> prop=new ArrayList<PropertyEntity>();
		try {
			prop = propRepo.findByCityOrPropertyGenderTypeOrPropertyTypeOrPropertyNameOrAboutPropertyOrAddressLocalityOrAddressLandmarkOrAddressCountryOrAddressState
				(search, search, search, search, search, search, search, search, search);
		}catch(Exception e) {
		}
		if(CollectionUtils.isEmpty(prop)) 
			return null;
		List<SearchResponse> response=new ArrayList<>();
		response = map.mapPropertyToSearchResponse(prop,"property");
		return response;
	}
	
	@Override
	public List<SearchResponse> GoogleLocalitySearch(String search,Double latitude,Double longitude) 
					throws BaseException, ApiException, InterruptedException, IOException{
		//Google Api key request builder
		GeoApiContext context = new GeoApiContext.Builder().apiKey(googleApikey).maxRetries(6).build();
		PlacesSearchResult[] results= {};
		try {
			if(latitude != null && longitude!=null){
				results= PlacesApi.textSearchQuery(context, search)
						.region("IN").location(new LatLng(latitude,longitude)).radius(100000).awaitIgnoreError().results;	
			}
			else{
				results= PlacesApi.textSearchQuery(context, search).region("IN").awaitIgnoreError().results;
			}
		
		} catch (Exception  e) {
		}
		if(results.length==0)
			return null;
		List<SearchResponse> response = map.mapGoogleSearchToSearchResponse(results);
		return response;
	}

	@Override
	public List<PropertyResponse> getPropertiesNearBy(SearchRequest request) {
		List<PropertyEntity> properties= propRepo.findAll();
		final Double serchLatitude = request.getLatitude();
		final Double searchLongitude = request.getLongitude();
		List<PropertyResponse> response = new ArrayList<>();
		if(CollectionUtils.isEmpty(properties)) {
			return null;
		}
		for(PropertyEntity property:properties) {
			Double distance=CoordinatesDistanceUtil.distance( serchLatitude
					, searchLongitude, property.getAddress().getLatitude() 
					, property.getAddress().getLongitude());
			if(distance<=100){
				response.add(propMap.mapPropertyEntityToResponse(property));
			}
		}
		return response;
	}
}
