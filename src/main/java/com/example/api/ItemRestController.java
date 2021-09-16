package com.example.api;

import java.util.List;

import com.example.domain.Item;
import com.example.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemRestController {

	@Autowired
	ItemService itemService;

	@GetMapping
	public List<Item> getItems() {

		return itemService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Item postItem(@RequestBody Item item) {

		return itemService.create(item);
	}

	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteItem(@PathVariable Integer id) {

		itemService.delete(id);
	}

	@PutMapping(path = "{id}")
	public Item putItem(@PathVariable Integer id, @RequestBody Item item) {

		item.setId(id);

		return itemService.update(item);
	}

	@GetMapping(path = "{id}")
	public Item findById(@PathVariable Integer id) {

		return itemService.findById(id);
	}

	@GetMapping("/findByNameLike")
	public List<Item> findByNameLike(@RequestParam(name = "keyword") String name) {

		return itemService.findByNameLike(name);
	}

	@GetMapping("/findByNameAndPrice")
	public List<Item> findByNameAndPrice(@RequestParam String name, int price) {

		return itemService.findByNameAndPrice(name, price);
	}

	@GetMapping("/findByNameNotLike")
	public List<Item> findByNameOrPrice(@RequestParam String name) {

		return itemService.findByNameNotLike(name);
	}

	@GetMapping("/findByPriceLessThan")
	public List<Item> findByPriceLessThan(@RequestParam int price) {

		return itemService.findByPriceLessThan(price);
	}

	@PostMapping("/findAllByItem")
	public List<Item> findAllByItem(@RequestBody Item item) {

		return itemService.findAllByItem(item);
	}

	@GetMapping("/findAllByColumnLike")
	public List<Item> findAllByColumnLike(@RequestParam String column, String keyword) {

		return itemService.findAllByColumnLike(column, keyword);
	}

	@GetMapping("/findAllByColumn")
	public List<Item> findAllByColum(@RequestParam String column, String keyword) {

		return itemService.findAllByColumn(column, keyword);
	}

	@PutMapping("/addstar/{id}")
	public Item addStar(@PathVariable Integer id, @RequestParam Integer star) {

		Item item = itemService.findById(id);

		item.setStar_count(item.getStar_count() + 1);

		item.setStar((item.getStarAmount() + star) / item.getStar_count() + 1);

		item.setStarAmount(item.getStarAmount() + star);

		return itemService.update(item);
	}

}
