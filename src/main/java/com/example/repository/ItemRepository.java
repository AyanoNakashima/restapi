package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findAll();

	List<Item> findByNameLike(String keyword);

	List<Item> findByNameAndPrice(String name, int price);

	List<Item> findByNameOrPrice(String name, int price);

	List<Item> findBynameNotLike(String name);

	List<Item> findByPriceLessThan(int price);

}