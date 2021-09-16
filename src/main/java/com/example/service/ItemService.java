package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

	public void delete(Integer deleteid) {

		itemRepository.deleteById(deleteid);
	}

	public Item update(Item item) {

		return itemRepository.save(item);
	}

	public Item findById(Integer id) {

		return itemRepository.findById(id).orElse(null);
	}

	public List<Item> findByNameLike(String keyword) {

		return itemRepository.findByNameLike("%" + keyword + "%");
	}

	public List<Item> findByNameAndPrice(String name, int price) {

		return itemRepository.findByNameAndPrice(name, price);
	}

	public List<Item> findByNameNotLike(String name) {

		return itemRepository.findByNameNotLike("%" + name + "%");
	}

	public List<Item> findByPriceLessThan(int price) {

		return itemRepository.findByPriceLessThan(price);
	}

	public List<Item> findAllByItem(Item item) {

		return itemRepository.findAll(Example.of(item));
	}

	public List<Item> findAllByColumnLike(String column, String keyword) {

		String[] words = keyword.split(" ");

		Specification<Item> spec = Specification.where((Specification<Item>) null);

		for (String word : words) {

			spec = spec.or(keywordContains(column, word));
		}

		return itemRepository.findAll(spec);
	}

	private Specification<Item> keywordContains(String column, String keyword) {

		return keyword.equals("") ? null
				: (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(column), "%" + keyword + "%");

	}

	public List<Item> findAllByColumn(String column, String keyword) {

		return itemRepository.findAll(nameInclude(column, new ArrayList<>(Arrays.asList(keyword.split(" ")))));
	}

	private Specification<Item> nameInclude(String column, List<String> nameList) {

		for (int i = nameList.size() - 1; i >= 0; i--) {
			nameList.remove("");

		}

		return CollectionUtils.isEmpty(nameList) ? null
				: (root, criteriaQuery, criteriaBuilder) -> root.get(column).in(nameList);
	}





}
