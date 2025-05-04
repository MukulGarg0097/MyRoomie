package com.myRoomie.Services.ServicesImpl;

import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.Repository.IPropertyRepository;
import com.myRoomie.Services.IPropertyService;
import com.myRoomie.Services.eventListner.EventPojo;
import com.myRoomie.constants.EntityDetails;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.dao.IPropertyDao;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.PropertyMapper;
import com.myRoomie.request.PropertyParamRequest;
import com.myRoomie.request.dto.PropertyRequest;
import com.myRoomie.response.dto.PropertyResponse;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class PropertyServiceImpl implements IPropertyService {

    @Autowired
    IPropertyRepository propertyRepo;

    @Autowired
    IPropertyDao propertyDao;

    @Autowired
    PropertyMapper propMapper;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public PropertyResponse save(PropertyRequest request) throws BaseException {
        String uniqueId = getUniqueId(request.getPropertyName(), request.getCity());
        PropertyEntity pEntity = propertyRepo.findByIdOrIdProductLocation(null, uniqueId);
        if (pEntity != null)
            throw new BaseException(ResponseCode.PROPERTY_PRESENT_NAME_LOCATION);
        PropertyEntity entity = propMapper.mapPropertyRequestToEntity(request);
        entity.setIdProductLocation(uniqueId);
        PropertyEntity response = propertyRepo.save(entity);
        if (ObjectUtils.isEmpty(response))
            return null;
        createSiteMap();
        return propMapper.mapPropertyEntityToResponse(response);
    }

    @Override
    public PropertyResponse update(PropertyRequest request) throws BaseException {
        PropertyEntity pEntity = propertyRepo.findByIdOrIdProductLocation(request.getId(), request.getIdProductLocation());
        if (pEntity == null)
            throw new BaseException(ResponseCode.INVALID_ID);
        PropertyEntity entity = propMapper.mapPropertyUpdate(pEntity, request);
        if (!(TextUtils.isEmpty(request.getPropertyName()) && TextUtils.isEmpty(request.getPropertyName()))) {
            String uniqueId = getUniqueId(request.getPropertyName(), request.getCity());
            if (!entity.getIdProductLocation().equals(uniqueId)) {
                PropertyEntity propertyEntity = propertyRepo.findByIdOrIdProductLocation(null, uniqueId);
                if (propertyEntity != null)
                    throw new BaseException(ResponseCode.PROPERTY_PRESENT_NAME_LOCATION);
                entity.setIdProductLocation(uniqueId);
            }
        }
        PropertyEntity response = propertyRepo.save(entity);
        if (ObjectUtils.isEmpty(response))
            return null;
        createSiteMap();
        return propMapper.mapPropertyEntityToResponse(response);
    }

    @Override
    public List<PropertyResponse> findAll() throws BaseException {
        List<PropertyEntity> entity = propertyRepo.findAll();
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public List<PropertyResponse> findByIsActive() throws BaseException {
        List<PropertyEntity> entity = propertyRepo.findAllByIsActive(true);
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public PropertyResponse findById(Integer id, String idProductLocation) throws BaseException {
        PropertyEntity pEntity = propertyRepo.findByIdOrIdProductLocation(id, idProductLocation);
        if (pEntity == null)
            return null;
        return propMapper.mapPropertyEntityToResponse(pEntity);
    }

    @Override
    public List<PropertyResponse> findByCity(String city) throws BaseException {
        List<PropertyEntity> entity = propertyRepo.findByCity(city);
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public List<PropertyResponse> findAllByFeatured(Boolean isFeatureFlag) throws BaseException {
        List<PropertyEntity> entity = propertyRepo.findAllByIsFeatureFlag(isFeatureFlag);
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public List<PropertyResponse> findByGenderType(String propertyGenderType) throws BaseException {
        List<PropertyEntity> entity = propertyRepo.findByPropertyGenderType(propertyGenderType);
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public List<PropertyResponse> findByPropertyType(String propertyType) throws BaseException {
        List<PropertyEntity> entity = propertyRepo.findByPropertyType(propertyType);
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public List<PropertyResponse> findByPropertyTypeAndCity(String propertyType, String city) throws BaseException {
        List<PropertyEntity> entity = propertyRepo.findByPropertyTypeAndCity(propertyType, city);
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public List<PropertyResponse> findByPropertyGenderTypeAndCity(String propertyGenderType, String city) throws
            BaseException {
        List<PropertyEntity> entity = propertyRepo.findByPropertyGenderTypeAndCity(propertyGenderType, city);
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public List<PropertyResponse> findByLattitudeAndLongitude(Double latitude, Double longitude) throws
            BaseException {
        List<PropertyEntity> entity = propertyRepo.findByAddressLatitudeAndAddressLongitude(latitude, longitude);
        if (CollectionUtils.isEmpty(entity))
            return null;
        return propMapper.mapPropertyEntityToResponse(entity);
    }

    @Override
    public List<PropertyResponse> find(PropertyParamRequest request) throws BaseException {
        String[] cols = EntityDetails.PropertyEntity.FIND_BY_PARAM_COLS;
//		List<PropertyEntity> entity = propertyRepo.find(request.getCity(),request.getGenderType(),request.getPropertyType()
//				,request.getIsActive(),request.getIsFeatured(),request.getPropertyName(),request.getLatitude(),request.getLongitude());
        List<PropertyEntity> ids = propertyDao.findByParam(request, cols);
        if (ObjectUtils.isEmpty(ids))
            return null;
        return propMapper.mapPropertyEntityToResponse(ids);
    }

    @Override
    public Boolean deleteById(Integer id, String idProductLocation, Boolean delete) throws BaseException {
        PropertyEntity entity = propertyRepo.findByIdOrIdProductLocation(id, idProductLocation);
        if (entity != null) {
            entity.setIsActive(!entity.getIsActive());
            if (delete) {
                propertyRepo.deleteById(id);
                return true;
            } else return !ObjectUtils.isEmpty(propertyRepo.save(entity));
        }
        return false;
    }

    private void createSiteMap() {
        EventPojo object = new EventPojo(this);
        object.setType(EventPojo.EventType.SITE_MAP_FILE_GENERATE);
        eventPublisher.publishEvent(object);
    }

    @Override
    public void updateId() {
        List<PropertyEntity> propertyRepoAll = propertyRepo.findAll();
        for (PropertyEntity entity : propertyRepoAll) {
            if (TextUtils.isEmpty(entity.getIdProductLocation())) {
                if (entity.getPropertyName() != null && entity.getCity() != null) {
                    String uniqueId = getUniqueId(entity.getPropertyName(), entity.getCity());
                    PropertyEntity pEntity = propertyRepo.findByIdOrIdProductLocation(null, uniqueId);
                    if (pEntity == null) {
                        entity.setIdProductLocation(uniqueId);
                        propertyRepo.save(entity);
                    }
                }
            }
        }
    }

    private String getUniqueId(String propertyName, String city) {
        return propertyName.toLowerCase().trim().replaceAll("\\s+", "-") + "-" + city.toLowerCase().trim().replaceAll("\\s+", "-");
    }
}
