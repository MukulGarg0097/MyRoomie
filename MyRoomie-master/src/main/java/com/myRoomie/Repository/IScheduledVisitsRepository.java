package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.ScheduledVisitsEntity;

@Repository
public interface IScheduledVisitsRepository extends JpaRepository<ScheduledVisitsEntity, Integer> {


}
