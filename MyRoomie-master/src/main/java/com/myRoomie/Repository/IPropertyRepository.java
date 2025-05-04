package com.myRoomie.Repository;

import com.myRoomie.Entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPropertyRepository extends JpaRepository<PropertyEntity,Integer>{

	List<PropertyEntity> findByCity(String city);

    PropertyEntity findByIdOrIdProductLocation(Integer id, String idProductLocation);
	List<PropertyEntity> findByPropertyGenderType(String propertyGenderType);
	List<PropertyEntity> findByPropertyType(String propertyType);
	List<PropertyEntity> findByPropertyTypeAndCity(String propertyType, String city);
	List<PropertyEntity> findByPropertyGenderTypeAndCity(String propertyGenderType, String city);
	List<PropertyEntity> findByAddressLatitudeAndAddressLongitude(Double latitude, Double longitude);

    List<PropertyEntity> findAllByIsActive(Boolean isActive);
	List<PropertyEntity> findAllByIsFeatureFlag(Boolean isFeatureFlag);
	
	@Query(value="Select * from property_entity p JOIN address_entity a ON " + 
			"a.id=p.address_id where p.city LIKE %:search% OR p.property_type " + 
			"LIKE %:search% OR p.property_name LIKE %:search% OR p.about_property LIKE " + 
			"%:search% OR a.locality LIKE %:search% OR a.landmark LIKE %:search% "
			+ "OR a.state LIKE '%:search%' OR a.country LIKE '%:search%'",nativeQuery=true)
	List<PropertyEntity> search(@Param("search") String search);
	
}
