package com.myRoomie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.NearByPlacesEntity;

@Repository
public interface INearByPlacesRepository extends JpaRepository<NearByPlacesEntity,Integer> {
	
	List<NearByPlacesEntity> findByPlaceName(String name);
	
	@Query(nativeQuery=true,value="Select distinct(place_name) from near_by_places where place_name like :name")
	List<String> findDistinctByPlaceNameLike(String name);

}
