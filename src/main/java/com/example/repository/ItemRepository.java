package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

	List<Item> findByNameLike(String keyword);

	List<Item> findByNameAndPrice(String name, int price);

	// TODO Camelケースで書きましょう
	List<Item> findBynameNotLike(String name);

	List<Item> findByPriceLessThan(int price);
}