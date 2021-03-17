package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	List<Item> findAll();
}