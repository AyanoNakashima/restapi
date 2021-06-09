package com.example.api;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.domain.Item;

public class Aaa implements Specification<Item> {

	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		return criteriaBuilder.like(root.get("memo"), "%aaaaa%");
	}

//	@Override
//	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//		// TODO Auto-generated method stub
//
////		Specification<Item> wordContains(String word){
//		    return (item, cq, cb) -> cb.like(item.get("word"), "%" + word + "%");
//		}
//	}
}
