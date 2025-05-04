package com.myRoomie.constants;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Predicates {
	
	private List<Predicate> andPredicates;
	private List<Predicate> orPredicates;
	
	public void addToAndPredicates(Predicate predicate) {
		andPredicates.add(predicate);
	}
	public void addToOrPredicates(Predicate predicate) {
		orPredicates.add(predicate);
	}
	
	public Predicate[] getAndPredicatesAsArray() {
		return andPredicates.toArray(new Predicate[andPredicates.size()]);
	}
	
	public Predicate[] getOrPredicatesAsArray() {
		return orPredicates.toArray(new Predicate[orPredicates.size()]);
	}
	public Predicates() {
		this.andPredicates = new ArrayList<>();
		this.orPredicates = new ArrayList<>();
	}
	
	public Predicate getAllPredicatesCombined(CriteriaBuilder builder) {
		Predicate andPredicate = builder.and(getAndPredicatesAsArray());
		Predicate orPredicate = builder.or(getOrPredicatesAsArray());
		return builder.or(andPredicate, orPredicate);
	}

}
