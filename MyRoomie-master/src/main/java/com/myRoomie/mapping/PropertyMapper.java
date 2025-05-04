package com.myRoomie.mapping;

import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.Entities.RoomEntity;
import com.myRoomie.Pojos.PropertyScheduledVisitsPojo;
import com.myRoomie.Repository.IMasterRoomAmenitiesRepository;
import com.myRoomie.Repository.IPropertyRepository;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.Utilities.JsonUtils;
import com.myRoomie.Utilities.SetAndListUtil;
import com.myRoomie.request.dto.PropertyRequest;
import com.myRoomie.response.PropertyFileResponse;
import com.myRoomie.response.dto.MasterRoomAmenitiesResponse;
import com.myRoomie.response.dto.PropertyResponse;
import com.myRoomie.response.dto.RoomResponse;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PropertyMapper {

    @Autowired
    IPropertyRepository propRepo;
    @Autowired
    PropertyFileMapper propertyFileMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    MasterRoomAmenitiesMapper masterAmentiy;
    @Autowired
    IMasterRoomAmenitiesRepository masterAmentiyRepo;

    public PropertyMapper() {
    }

    public PropertyEntity mapPropertyRequestToEntity(PropertyRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return null;
        }
        PropertyEntity response = new PropertyEntity();
        Set<RoomEntity> roomSet = SetAndListUtil.convertListToSet(roomMapper.
                mapRoomRequestToEntity(SetAndListUtil.convertSetToList(request.getRooms())));
        response.setRooms(roomSet);
        response.setAboutProperty(request.getAboutProperty());
        response.setAddress(addressMapper.mapAddressRequestToEntity(request.getAddress()));
        response.setRoomType(request.getRoomType());
        response.setSharingType(request.getSharingType());
        response.setPropertyName(request.getPropertyName());
        response.setCity(request.getCity());
        response.setIsFeatureFlag(request.getIsFeatureFlag());
        response.setPropertyGenderType(request.getPropertyGenderType());
        response.setPropertyImagesUrl(propertyFileMapper.mapFileRequestToEntity(request.getPropertyImagesUrl()));
        response.setPropertyType(request.getPropertyType());
        response.setNearByPlaces(NearByPlacesMapper.mapNearByPlacesRequestToEntity(request.getNearByPlaces()));
        response.setStartAmount(request.getStartAmount());
        response.setDiscPercentage(request.getDiscPercentage());
        response.setAmenityIds(JsonUtils.convertToJSON(request.getAmenityIds()));
        response.setView360(request.getView360());
        response.setId(request.getId());
        response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
        response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
        if (request.getIsActive() == null)
            response.setIsActive(true);
        else
            response.setIsActive(request.getIsActive());
        return response;
    }

    public PropertyEntity mapPropertyUpdate(PropertyEntity response, PropertyRequest request) {
        if (!ObjectUtils.isEmpty(request)) {
            if (!ObjectUtils.isEmpty(request.getRooms())) {
                Set<RoomEntity> roomSet = SetAndListUtil.convertListToSet(roomMapper.
                        mapRoomRequestToEntity(SetAndListUtil.convertSetToList(request.getRooms())));
                response.setRooms(roomSet);
            }
            if (!TextUtils.isEmpty(request.getAboutProperty())) {
                response.setAboutProperty(request.getAboutProperty());
            }
            if (request.getAddress() != null) {
                response.setAddress(addressMapper.mapAddressRequestToEntity(request.getAddress()));
            }
            if (!TextUtils.isEmpty(request.getRoomType())) {
                response.setRoomType(request.getRoomType());
            }
            if (!TextUtils.isEmpty(request.getSharingType())) {
                response.setSharingType(request.getSharingType());
            }
            if (!TextUtils.isEmpty(request.getPropertyName())) {
                response.setPropertyName(request.getPropertyName());
            }
            if (!TextUtils.isEmpty(request.getCity())) {
                response.setCity(request.getCity());
            }
            if (request.getIsFeatureFlag() != null) {
                response.setIsFeatureFlag(request.getIsFeatureFlag());
            }
            if (!TextUtils.isEmpty(request.getPropertyGenderType())) {
                response.setPropertyGenderType(request.getPropertyGenderType());
            }
            if (!CollectionUtils.isEmpty(request.getPropertyImagesUrl())) {
                response.setPropertyImagesUrl(propertyFileMapper.mapFileRequestToEntity(request.getPropertyImagesUrl()));
            }
            if (!TextUtils.isEmpty(request.getPropertyType())) {
                response.setPropertyType(request.getPropertyType());
            }
            if (!CollectionUtils.isEmpty(request.getNearByPlaces())) {
                response.setNearByPlaces(NearByPlacesMapper.mapNearByPlacesRequestToEntity(request.getNearByPlaces()));
            }
            if (request.getStartAmount() != null) {
                response.setStartAmount(request.getStartAmount());
            }
            if (request.getDiscPercentage() != null) {
                response.setDiscPercentage(request.getDiscPercentage());
            }
            if (request.getAmenityIds() != null && request.getAmenityIds().length > 0) {
                response.setAmenityIds(JsonUtils.convertToJSON(request.getAmenityIds()));
            }
            if (!TextUtils.isEmpty(request.getView360())) {
                response.setView360(request.getView360());
            }
            response.setId(request.getId());
            response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
            if (request.getIsActive() == null)
                response.setIsActive(true);
            else
                response.setIsActive(request.getIsActive());
        }
        return response;
    }

    public PropertyResponse mapPropertyEntityToResponse(PropertyEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }
        PropertyResponse response = new PropertyResponse();
        response.setIdProductLocation(entity.getIdProductLocation());
        Set<RoomResponse> roomSet = SetAndListUtil.convertListToSet(roomMapper.
                mapRoomEntityToResponse(SetAndListUtil.convertSetToList(entity.getRooms())));
        response.setRooms(roomSet);

        Set<PropertyFileResponse> propertyImages = propertyFileMapper.mapFileEntityToResponse(entity.getPropertyImagesUrl());
        response.setPropertyImagesUrl(propertyImages);

        response.setId(entity.getId());
        response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
        response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
        response.setAboutProperty(entity.getAboutProperty());
        response.setAddress(addressMapper.mapAddressEntityToResponse(entity.getAddress()));
        response.setRoomType(entity.getRoomType());
        response.setSharingType(entity.getSharingType());
        response.setPropertyName(entity.getPropertyName());
        response.setCity(entity.getCity());
        response.setIsFeatureFlag(entity.getIsFeatureFlag());
        response.setPropertyGenderType(entity.getPropertyGenderType());
        response.setPropertyType(entity.getPropertyType());
        response.setIsActive(entity.getIsActive());
        response.setNearByPlaces(NearByPlacesMapper.mapNearByPlacesEntityToResponse(entity.getNearByPlaces()));

        response.setStartAmount(entity.getStartAmount());
        response.setDiscPercentage(entity.getDiscPercentage());
        List<Integer> array = new ArrayList<>();
        List<Integer> masterIds = new ArrayList<Integer>();
        JsonUtils.fromJson(entity.getAmenityIds(), array);

        if (!CollectionUtils.isEmpty(array)) {
            Set<MasterRoomAmenitiesResponse> resp = new HashSet<>();
            for (Integer s : array) {
                Integer id = (s);
                if (masterAmentiyRepo.existsById(id)) {
                    resp.add((masterAmentiy.mapMasterRoomAmenitiesToResponse(masterAmentiyRepo.findById(id).get())));
                    masterIds.add(id);
                }
            }
            response.setAmenityIds(new HashSet<Integer>(masterIds));
            response.setMasterAmenities(resp);
        }

        response.setView360(entity.getView360());
        return response;
    }

    public List<PropertyEntity> mapPropertyRequestToEntity(List<PropertyRequest> requestList) {
        if (CollectionUtils.isEmpty(requestList)) {
            return null;
        }
        List<PropertyEntity> entityList = new ArrayList<>();
        for (PropertyRequest request : requestList) {
            PropertyEntity response = new PropertyEntity();
            response = mapPropertyRequestToEntity(request);
            entityList.add(response);
        }
        return entityList;
    }

    public List<PropertyResponse> mapPropertyEntityToResponse(List<PropertyEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<PropertyResponse> responseList = new ArrayList<>();
        for (PropertyEntity entity : entityList) {
            PropertyResponse response = new PropertyResponse();
            response = mapPropertyEntityToResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    public PropertyScheduledVisitsPojo mapPropertyEntityToScheduledVisitsPojo(Integer propertyId) {
        if (propertyId == null) {
            return null;
        }
        PropertyEntity entity = new PropertyEntity();
        if (propRepo.existsById(propertyId))
            entity = propRepo.findById(propertyId).get();
        else
            return null;
        PropertyScheduledVisitsPojo response = new PropertyScheduledVisitsPojo();
        response.setId(entity.getId());
        response.setIdProductLocation(entity.getIdProductLocation());
        response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
        response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
        response.setAboutProperty(entity.getAboutProperty());
//		response.setAddress(entity.getAddress());
        response.setRoomType(entity.getRoomType());
        response.setSharingType(entity.getSharingType());
        response.setPropertyName(entity.getPropertyName());
        response.setCity(entity.getCity());
        response.setIsFeatureFlag(entity.getIsFeatureFlag());
        response.setPropertyGenderType(entity.getPropertyGenderType());
        response.setPropertyType(entity.getPropertyType());
        response.setIsActive(entity.getIsActive());
        response.setView360(entity.getView360());
//		response.setNearByPlaces(NearByPlacesMapper.mapNearByPlacesEntityToResponse(entity.getNearByPlaces()));

//		List<Integer> array =new ArrayList<>();
//		List<Integer> masterIds= new ArrayList<>();
//		JsonUtils.fromJson(entity.getAmenityIds(), array);
//		
//		if(!CollectionUtils.isEmpty(array))
//		{	
//			Set<MasterRoomAmenitiesResponse> resp=new HashSet<>();
//			for(Integer s:array) {
//				Integer id=(s);
//				if(masterAmentiyRepo.existsById(id)) {
//					resp.add((masterAmentiy.mapMasterRoomAmenitiesToResponse(masterAmentiyRepo.findById(id).get())));
//					masterIds.add(id);
//				}
//			}
//			response.setAmenityIds(new HashSet<Integer>(masterIds));
//			response.setMasterAmenities(resp);
//		}

        return response;
    }

    public Set<PropertyEntity> mapPropertyRequestToEntity(Set<PropertyRequest> requestSet) {
        if (CollectionUtils.isEmpty(requestSet)) {
            return null;
        }
        Set<PropertyEntity> entitySet = new HashSet<>();
        for (PropertyRequest request : requestSet) {
            PropertyEntity response = new PropertyEntity();
            response = mapPropertyRequestToEntity(request);
            entitySet.add(response);
        }
        return entitySet;
    }

    public Set<PropertyResponse> mapPropertyEntityToResponse(Set<PropertyEntity> entitySet) {
        if (CollectionUtils.isEmpty(entitySet)) {
            return null;
        }
        Set<PropertyResponse> responseSet = new HashSet<>();
        for (PropertyEntity entity : entitySet) {
            PropertyResponse response = new PropertyResponse();
            response = mapPropertyEntityToResponse(entity);
            responseSet.add(response);
        }
        return responseSet;
    }

}