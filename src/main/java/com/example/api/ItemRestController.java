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
@RequestMapping("api/items")
public class ItemRestController {
	@Autowired
	ItemService itemService;

	@GetMapping
	public List<Item> getItems() {
		List<Item> customers = itemService.findAll();
		return customers;
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

	@GetMapping("/{id}")
	public Item findById(@PathVariable Integer id) {
		return itemService.findById(id);
	}

	@GetMapping("/findByNameLike")
	public List<Item> findByNameLike(@RequestParam(name = "keyword") String name) {
//	public List<Item> findByNameLike(@RequestParam String keyword,Model model) {
//		List<Item> aaa = itemService.findByNameLike(name);
//		model.addAttribute("aaa",aaa);
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

	@PostMapping("/find")
	public List<Item> find(@RequestBody Item item) {
		return itemService.find(item);
	}

	@GetMapping("/findAllMemo")
	public List<Item> findAllMemo(@RequestParam String keyword) {
		return itemService.findAllMemo(keyword);
	}
//	public List<Item> findByMemoLike(@RequestParam String word1) {
//	
//		String[] words = word1.split(" ");
//		String str = "";
//	
//		Specification<Item> zero = Specification.where((Specification<Item>)null);
//
//		for (String word : words) {
//			
//			str = str + word;
//			System.out.println("%" + str + "%");
//			
//			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//				Specification<Item> wordContains(String word){
//				    return (item, cq, cb) -> cb.like(item.get("word"), "%" + word + "%");
//				}
//			
//			zero += Specification.or(Specification<Item>.wordContains());
//}}

//		return StringUtils.isEmpty(word) ? null : new Specification<Item>() {
//			/**
//			 * 
//			 */ïï
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				return cb.like(root.get("word1"), "%" + word1 + "%");
//			}
//		};

//		n週目まで１個づつ取り出したwordをSQLにしてぶっ飛ばす
//		つまりここでSQLを作る
//		ということは？？どうすればいいの？？？？？？？

}
