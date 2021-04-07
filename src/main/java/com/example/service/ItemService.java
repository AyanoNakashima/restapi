package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemRepository;

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public Item create(Item item) {
		return itemRepository.save(item);
	}

	public void delete(Integer absd) {
		itemRepository.deleteById(absd);

//	//	Item a = new Item();
//		a.setId(idyyy);
//		itemRepository.delete(a);
	}

	public Item update(Item item) {
		return itemRepository.save(item);
	}

	public Item findById(Integer aaa) {
//		Optional<Item> ss =itemRepository.findById(aaa);
//		Item fff = ss.get();
//		return fff;

		return itemRepository.findById(aaa).get();
	}

	public List<Item> findByNameLike(String keyword) {
//		List<Item> aaa = itemRepository.findByNameLike("%"+keyword+"%");
		return itemRepository.findByNameLike("%" + keyword + "%");
	}

	public List<Item> findByNameAndPrice(String name, int price) {
		return itemRepository.findByNameAndPrice(name, price);
	}

	public List<Item> findBynameNotLike(String name) {
		return itemRepository.findBynameNotLike("%" + name + "%");
	}

	public List<Item> findByPriceLessThan(int price) {
		return itemRepository.findByPriceLessThan(price);
	}

}