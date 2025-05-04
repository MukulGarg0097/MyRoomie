package com.myRoomie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.AddressEntity;

@Repository
public interface IAddressRepository extends JpaRepository<AddressEntity,Integer> {

	List<AddressEntity> findByCity(String city);
	
	List<AddressEntity> findByState(String state);

	List<AddressEntity> findByPincode(String pincode);
	
	List<AddressEntity> findByLatitudeAndLongitude(Double latitude,Double longitude );

	List<AddressEntity> findByCountry(String country);	
}
