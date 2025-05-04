package com.myRoomie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.PropertyEntity;

@Repository
public interface IPropertyRepository extends JpaRepository<PropertyEntity, Integer>{

	List<PropertyEntity> findByCity(String city);
	List<PropertyEntity> findByPropertyGenderType(String propertyGenderType);
	List<PropertyEntity> findByPropertyType(String propertyType);
	List<PropertyEntity> findByPropertyTypeAndCity(String propertyType, String city);
	List<PropertyEntity> findByPropertyGenderTypeAndCity(String propertyGenderType, String city);
	List<PropertyEntity> findByAddressLatitudeAndAddressLongitude(Double latitude, Double longitude);
	List<PropertyEntity> findByIsActive(Boolean isActive);
	List<PropertyEntity> findAllByIsFeatureFlag(Boolean isFeatureFlag);
	
	List<PropertyEntity> findByCityOrPropertyGenderTypeOrPropertyTypeOrPropertyNameOrAboutPropertyOrAddressLocalityOrAddressLandmarkOrAddressCountryOrAddressState
	(String search,String search1,String search2,String search3,String search4,String search5,String search6,String search7,String search8);

}
