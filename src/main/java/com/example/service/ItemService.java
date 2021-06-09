package com.example.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
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

	// TODO
	public List<Item> findAllMemo(String keyword) {

		String[] words = keyword.split(" ");
		Specification<Item> spec = Specification.where((Specification<Item>) null);

		for (String word : words) {

//			spec = Specification.where(keywordContains(word)).or(spec);
			spec = spec.or(keywordContains(word));

		}

		return itemRepository.findAll(spec);
	}

//	public List<Item> findAllMemo(String keyword) {
//		
//		Specification<Item> spec = keywordContains(keyword);
//
//		return itemRepository.findAll(spec);
//	}

	private Specification<Item> keywordContains(String keyword) {
		return new Specification<Item>() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("memo"), "%" + keyword + "%");
			}

		};
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

	public List<Item> find(Item item) {
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",
				match -> match.ignoreCase().startsWith());

		Example<Item> example = Example.of(item, matcher);

		return itemRepository.findAll(example);
	}

}
