package com.example.api;

import java.util.List;

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

import com.example.domain.Item;
import com.example.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemRestController {

	@Autowired
	ItemService itemService;

	@GetMapping
	public List<Item> getItems() {

		// TODO 1行で書きましょう
		List<Item> customers = itemService.findAll();
		return customers;
	}

	@PostMapping
	// TODO @ResponseStatusの補足
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

	// TODO @GetMapping(path = "{id}")で統一しましょう
	@GetMapping("/{id}")
	public Item findById(@PathVariable Integer id) {

		return itemService.findById(id);
	}

	@GetMapping("/findByNameLike")
	// TODO @PathVariableの補足
	public List<Item> findByNameLike(@RequestParam(name = "keyword") String name) {

		return itemService.findByNameLike(name);
	}

	@GetMapping("/findByNameAndPrice")
	public List<Item> findByNameAndPrice(@RequestParam String name, int price) {

		return itemService.findByNameAndPrice(name, price);
	}

	@GetMapping("/findByNameNotLike")
	public List<Item> findByNameOrPrice(@RequestParam String name) {

		return itemService.findBynameNotLike(name);
	}

	@GetMapping("/findByPriceLessThan")
	public List<Item> findByPriceLessThan(@RequestParam int price) {

		return itemService.findByPriceLessThan(price);
	}

	// TODO findAllByItemとか
	@PostMapping("/find")
	public List<Item> find(@RequestBody Item item) {

		return itemService.find(item);
	}

	// TODO findAllByMemoとか
	@GetMapping("/findAllMemo")
	public List<Item> findAllMemo(@RequestParam String keyword) {

		return itemService.findAllMemo(keyword);
	}

	@GetMapping("/findAllByName")
	public List<Item> findAllByName(@RequestParam String keyword) {

		return itemService.findAllByName(keyword);
	}
}
