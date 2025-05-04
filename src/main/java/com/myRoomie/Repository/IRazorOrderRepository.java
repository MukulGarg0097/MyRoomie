package com.myRoomie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.RazorOrderEntity;


@Repository
public interface IRazorOrderRepository extends JpaRepository<RazorOrderEntity, Integer>{

	List<RazorOrderEntity> findByTransactionId(Integer transactionId);
	RazorOrderEntity findByRazorOrderId(String razorOrderId);
	
}
