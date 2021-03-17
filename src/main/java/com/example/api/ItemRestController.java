package com.example.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Item;
import com.example.service.ItemService;

@RestController
@RequestMapping("api/items")
public class ItemRestController {
	@Autowired
	ItemService itemService;

	@GetMapping
	List<Item> getItems() {
//        List<Item> customers = itemService.findAll();
//        return customers;

		return Arrays.asList(new Item());
	}

}