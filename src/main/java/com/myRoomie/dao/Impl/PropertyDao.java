package com.myRoomie.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.constants.EntityDetails;
import com.myRoomie.dao.IPropertyDao;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.PropertyParamRequest;


@Repository
public class PropertyDao implements IPropertyDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<PropertyEntity> findByParam(PropertyParamRequest request, String[] cols){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<PropertyEntity> criteria = builder.createQuery(PropertyEntity.class);
		Root<PropertyEntity> root = criteria.from(PropertyEntity.class);
		List<Predicate> predicate = new ArrayList<>();
		if (!StringUtils.isEmpty(request.getCity())) {
			predicate.add(
					builder.like(root.get(cols[0]), "%"+ request.getCity() + "%"));
		}
		if(!StringUtils.isEmpty(request.getIsActive())) {
			predicate.add(
					builder.equal(root.get(cols[1]), request.getIsActive()));
		}
		if(!StringUtils.isEmpty(request.getIsFeatureFlag())) {
			predicate.add(
					builder.equal(root.get(cols[2]), request.getIsFeatureFlag()));
		}
		if(!StringUtils.isEmpty(request.getPropertyGenderType())) {
			predicate.add(
					builder.equal(root.get(cols[3]), request.getPropertyGenderType()));
		}
		if(!StringUtils.isEmpty(request.getPropertyName())) {
			predicate.add(
					builder.like(root.get(cols[4]),"%"+  request.getPropertyName() + "%"));
		}
		if(!StringUtils.isEmpty(request.getPropertyType())) {
			predicate.add(
					builder.equal(root.get(cols[5]), request.getPropertyType()));
		}
		criteria.where(predicate.toArray(new Predicate[predicate.size()]));
		TypedQuery<PropertyEntity> entityList = entityManager.createQuery(criteria);
		if(ObjectUtils.isEmpty(entityList)) {
			return null;
		}
		return entityList.getResultList();
	}

	@Override
	public List<PropertyEntity> search(String search) throws BaseException{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PropertyEntity> criteria = builder.createQuery(PropertyEntity.class);
		Root<PropertyEntity> root = criteria.from(PropertyEntity.class);
		List<Predicate> predicate = new ArrayList<>();
		String[] andCols= EntityDetails.PropertyEntity.SEARCH_AND_COLS;
		String[] likeCols= EntityDetails.PropertyEntity.SEARCH_LIKE_COLS;

		if(!StringUtils.isEmpty(search)) {
			for(String s:andCols) {
				predicate.add(
					builder.like(root.get(s), "%" + search+ "%"));
			}
			predicate.add(
				builder.like(root.get(likeCols[0]), "%" + search+ "%"));
			predicate.add(
				builder.like(root.get(likeCols[1]), "%" + search+ "%"));
			predicate.add(
				builder.like(root.get(likeCols[2]), "%" + search+ "%"));
			predicate.add(
				builder.like(root.get(EntityDetails.PropertyEntity.ADDRESS).get(likeCols[3]), "%" + search+ "%"));
			predicate.add(
				builder.like(root.get(EntityDetails.PropertyEntity.ADDRESS).get(likeCols[4]), "%" + search+ "%"));
			predicate.add(
				builder.like(root.get(EntityDetails.PropertyEntity.ADDRESS).get(likeCols[5]), "%" + search+ "%"));
			predicate.add(
				builder.like(root.get(EntityDetails.PropertyEntity.ADDRESS).get(likeCols[6]), "%" + search+ "%"));
		}
		Predicate s1=builder.equal(root.get(EntityDetails.PropertyEntity.IS_ACTIVE), true);
		Predicate s2 =builder.or(predicate.toArray(new Predicate[predicate.size()]));
		criteria.where(builder.and(s1, s2));
		TypedQuery<PropertyEntity> entityList = entityManager.createQuery(criteria);
		List<PropertyEntity> response= entityList.getResultList();
		if(CollectionUtils.isEmpty(response)) {
			return null;
		}
		return response;
	}
}