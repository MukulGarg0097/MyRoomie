package com.myRoomie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.CouponCodeEntity;
import com.myRoomie.exceptions.BaseException;

@Repository
public interface ICouponCodeRepository extends JpaRepository<CouponCodeEntity,Integer> {

	List<CouponCodeEntity> findByIsActive(boolean isActive);
	CouponCodeEntity findByCouponCodeName(String couponCodeName) throws BaseException;

}
