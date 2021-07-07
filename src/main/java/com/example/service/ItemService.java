package com.example.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	// 依存性の注入（dependency injection:DI）
	// これ以降ItemRepositoryのものはnewする必要がなくなる

	public Item create(Item item) {
		return itemRepository.save(item);
	}

	public void delete(Integer deleteid) {
		itemRepository.deleteById(deleteid);
	}

	public Item update(Item item) {
		return itemRepository.save(item);
	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public Item findById(Integer id) {
		return itemRepository.findById(id).get();
//		getとは????
	}

	public List<Item> findByNameLike(String keyword) {
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

//		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",
//				match -> match.ignoreCase().startsWith());

		Example<Item> example = Example.of(item);
//		Example<Item> example = Example.of(item, matcher);

		return itemRepository.findAll(example);
	}

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
//				return cb.like(root.get("memo"), "%" + keyword + "%");
				return root.get("memo").in(keyword);
			}

		};
	}

	public List<Item> findAllByName(String keyword) {

		String[] words = keyword.split(" ");

//		List<String> l1 = new ArrayList<>();
//
//		for (String word : words)
//			l1.add(word);

		List<String> l2 = Arrays.asList(words);
//		List<String> l3 = new ArrayList<>(Arrays.asList(words));

//		Specification<Item> spec = Specification.where((Specification<Item>) null);
////			spec = Specification.where(keywordContains(word)).or(spec);
//		spec = nameInclude(l2);
		return itemRepository.findAll(nameInclude(l2));

	}

	private Specification<Item> nameInclude(List<String> nameList) {
		return nameList.size() == 0 ? null : new Specification<Item>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				return root.get("name").in(nameList);
			}
		};
	}

}
