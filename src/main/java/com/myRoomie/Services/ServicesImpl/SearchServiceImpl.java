package com.myRoomie.Services.ServicesImpl;

import com.google.gson.Gson;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import com.myRoomie.Entities.NearByPlacesEntity;
import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.Pojos.GooglePlaceResponsePojo;
import com.myRoomie.Pojos.GooglePlaceResponsePojo.result;
import com.myRoomie.Repository.INearByPlacesRepository;
import com.myRoomie.Repository.IPropertyRepository;
import com.myRoomie.Services.IPropertyService;
import com.myRoomie.Services.ISearchService;
import com.myRoomie.Utilities.CoordinatesDistanceUtil;
import com.myRoomie.Utilities.HttpRequest;
import com.myRoomie.dao.IPropertyDao;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.PropertyMapper;
import com.myRoomie.mapping.SearchMapper;
import com.myRoomie.request.dto.SearchRequest;
import com.myRoomie.response.SearchResponse;
import com.myRoomie.response.dto.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements ISearchService {

    @Value("${com.googlemaps.ApiKey}")
    String googleApikey;

    @Autowired
    IPropertyRepository propRepo;
    @Autowired
    IPropertyService propertyService;

    @Autowired
    INearByPlacesRepository nearRepo;

    @Autowired
    IPropertyDao propDao;

    @Autowired
    PropertyMapper propMap;

    @Autowired
    SearchMapper map;

    @Override
    public List<SearchResponse> Search(String search, Double latitude, Double longitude) throws BaseException {
        if (search.length() <= 1) {
            return null;
        }
        List<SearchResponse> response = new ArrayList<>();
        try {
            List<SearchResponse> propResponse = PropertySearch(search);
            List<SearchResponse> nearByResponse = NearByPlacesSearch(search);
            if (!CollectionUtils.isEmpty(propResponse))
                response.addAll(propResponse);
            if (!CollectionUtils.isEmpty(nearByResponse))
                response.addAll(nearByResponse);
            if (CollectionUtils.isEmpty(propResponse) && CollectionUtils.isEmpty(nearByResponse)) {
//				List<SearchResponse> googleSearch=GoogleLocalitySearch(search, latitude, longitude);
                List<SearchResponse> googleSearch = googleSearch(search, latitude, longitude);
                if (!CollectionUtils.isEmpty(googleSearch))
                    response.addAll(googleSearch);
            }
        } catch (Exception e) {
        }
        return response;
    }

    @Override
    public List<SearchResponse> PropertySearch(String search) throws BaseException {
        List<PropertyEntity> prop = new ArrayList<>();
        try {
            List<PropertyEntity> properties = propDao.search(search);
            if (!CollectionUtils.isEmpty(properties))
                prop.addAll(properties);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        if (CollectionUtils.isEmpty(prop))
            return null;
        List<SearchResponse> response = new ArrayList<>();
        response = map.mapPropertyToSearchResponse(prop, "property");
        return response;
    }

    @Override
    public List<SearchResponse> NearByPlacesSearch(String search) throws BaseException {
        List<String> prop = new ArrayList<>();
        try {
            List<String> propertiesNearBy = nearRepo.findDistinctByPlaceNameLike("%" + search + "%");
            if (!CollectionUtils.isEmpty(propertiesNearBy))
                prop.addAll(propertiesNearBy);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        if (CollectionUtils.isEmpty(prop))
            return null;
        List<SearchResponse> response = new ArrayList<>();
        response = map.mapNearByPlaceToSearchResponse(prop, "nearByPlace");
        return response;
    }

    @Override
    public List<SearchResponse> GoogleLocalitySearch(String search, Double latitude, Double longitude)
            throws BaseException, ApiException, InterruptedException, IOException {
        // Google Api key request builder
        GeoApiContext context = new GeoApiContext.Builder().apiKey(googleApikey).maxRetries(6).build();
        PlacesSearchResult[] results = {};
        try {
            if (latitude != null && longitude != null) {
                results = PlacesApi.textSearchQuery(context, search).region("in")
                        .location(new LatLng(latitude, longitude)).radius(50000).awaitIgnoreError().results;
            } else {
                results = PlacesApi.textSearchQuery(context, search).region("in").awaitIgnoreError().results;
            }

        } catch (Exception e) {
        }
        if (results.length == 0)
            return null;
        List<SearchResponse> response = map.mapGoogleSearchToSearchResponse(results);
        return response;
    }

    @Override
    public List<PropertyResponse> getPropertiesNearBy(SearchRequest request) throws BaseException {
        if (StringUtils.isEmpty(request.getType())) {
            List<PropertyEntity> properties = propRepo.findAll();
            final Double searchLatitude = request.getLatitude();
            final Double searchLongitude = request.getLongitude();
            List<PropertyResponse> response = new ArrayList<>();
            if (CollectionUtils.isEmpty(properties) || (searchLongitude == null && searchLatitude == null)) {
                return null;
            }
            for (PropertyEntity property : properties) {
                Double distance = CoordinatesDistanceUtil.distance(searchLatitude, searchLongitude,
                        property.getAddress().getLatitude(), property.getAddress().getLongitude());
                if (distance <= 100) {
                    response.add(propMap.mapPropertyEntityToResponse(property));
                }
                System.out.println(distance);
            }
            return response;
        }
        if (request.getType().equalsIgnoreCase("nearByPlace")) {
            List<NearByPlacesEntity> nearByPlaces = nearRepo.findByPlaceName(request.getName());
            List<Integer> propertyIds = nearByPlaces.stream().map(r -> r.getPropertyId()).collect(Collectors.toList());
            List<PropertyResponse> response = propMap.mapPropertyEntityToResponse(propRepo.findAllById(propertyIds));
            if (CollectionUtils.isEmpty(response)) {
                return null;
            }
            return response;
        } else if (request.getType().equalsIgnoreCase("property")) {
            List<PropertyResponse> responses = new ArrayList<PropertyResponse>();
            responses.add(propertyService.findById(request.id, request.idProductLocation));
            return responses;
        }
        return null;

    }

    @Override
    public List<SearchResponse> googleSearch(String search, Double lat, Double lng) throws BaseException, IOException {

        String api_key = googleApikey;
        String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?region=in&";

        if (lat != null && lng != null) {
            url += "location=" + lat + "," + lng + "&radius=50000&";
        }

        String searchTerm = URLEncoder.encode(search, "UTF-8");

        String responsed = HttpRequest.sendGET(url + "query=" + searchTerm + "&key=" + api_key);
        if (StringUtils.isEmpty(responsed)) {
            return null;
        }
        Gson gson = new Gson();
        GooglePlaceResponsePojo request = gson.fromJson(responsed, GooglePlaceResponsePojo.class);

        if (CollectionUtils.isEmpty(request.getResults())) {
            return null;
        }
        List<SearchResponse> response = new ArrayList<>();
        for (int i = 0; i < request.getResults().size(); i++) {
            String[] searchTypes = request.getResults().get(i).getTypes();
            for (String type : searchTypes) {
                if (type.equalsIgnoreCase("school") || type.equalsIgnoreCase("university")) {
                    response.add(mapGoogleResponseToSearchResponse(request.getResults().get(i), "university"));
                    break;
                } else if (type.equalsIgnoreCase("locality") || type.equalsIgnoreCase("city") || type.equalsIgnoreCase("state")
                        || type.equalsIgnoreCase("country") || type.equalsIgnoreCase("geocode") || type.equalsIgnoreCase("street_address")
                        || type.equalsIgnoreCase("street_number") || type.equalsIgnoreCase("sublocality")
                        || type.equalsIgnoreCase("sublocality_level_4") || type.equalsIgnoreCase("sublocality_level_5")
                        || type.equalsIgnoreCase("sublocality_level_3") || type.equalsIgnoreCase("sublocality_level_2")
                        || type.equalsIgnoreCase("sublocality_level_1") || type.equalsIgnoreCase("administrative_area_level_1")
                        || type.equalsIgnoreCase("administrative_area_level_2") || type.equalsIgnoreCase("administrative_area_level_3")
                        || type.equalsIgnoreCase("administrative_area_level_4") || type.equalsIgnoreCase("administrative_area_level_5")) {
                    response.add(mapGoogleResponseToSearchResponse(request.getResults().get(i), "locality"));
                    break;
                }
            }
        }

        return response;
    }

    private SearchResponse mapGoogleResponseToSearchResponse(result result, String type) {
        result obj = result;

        if (ObjectUtils.isEmpty(result)) {
            return null;
        }
        SearchResponse res = new SearchResponse();
        if (obj.getFormatted_address() != null)
            res.setAddress(obj.getFormatted_address());
        if (obj.getName() != null && (type.equalsIgnoreCase("school") || type.equalsIgnoreCase("university")))
            res.setDisplayName("Properties nearby " + obj.getName());
        else
            res.setDisplayName("Properties in " + obj.getName());
        res.setLatitude(obj.getGeometry().getLocation().getLat());
        res.setLongitude(obj.getGeometry().getLocation().getLng());
        if (obj.getName() != null)
            res.setName(obj.getName());
        if (type != null)
            res.setType(type);

        return res;
    }

    private String getSearchTerm(String search) {
        String searchTerm = search.replaceAll("\\S", "+");
        return searchTerm;
    }

}