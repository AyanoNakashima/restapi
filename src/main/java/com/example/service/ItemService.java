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

		// TODO Optionalの説明
		return itemRepository.findById(id).get();
	}

	public List<Item> findByNameLike(String keyword) {

		return itemRepository.findByNameLike("%" + keyword + "%");
	}

	public List<Item> findByNameAndPrice(String name, int price) {

		return itemRepository.findByNameAndPrice(name, price);
	}

	// TODO Camelケースで書きましょう
	public List<Item> findBynameNotLike(String name) {

		return itemRepository.findBynameNotLike("%" + name + "%");
	}

	public List<Item> findByPriceLessThan(int price) {

		return itemRepository.findByPriceLessThan(price);
	}

	public List<Item> find(Item item) {

		// TODO 1行で書きましょう
		Example<Item> example = Example.of(item);
		return itemRepository.findAll(example);
	}

	public List<Item> findAllMemo(String keyword) {

		String[] words = keyword.split(" ");

		Specification<Item> spec = Specification.where((Specification<Item>) null);

		for (String word : words) {

			spec = spec.or(keywordContains(word));
		}

		return itemRepository.findAll(spec);
	}


	// TODO 項目名を引数で受け取り、memo/Like句検索 ⇒ Like句検索用の Specificationに変更
	private Specification<Item> keywordContains(String keyword) {

		// TODO ラムダ式を使いましょう
		// TODO nullチェックしましょう
		return new Specification<Item>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				// TODO ？？
//				return cb.like(root.get("memo"), "%" + keyword + "%");
				return root.get("memo").in(keyword);
			}
		};
	}

	public List<Item> findAllByName(String keyword) {

		// TODO 1行で書きましょう
		String[] words = keyword.split(" ");
		List<String> l2 = Arrays.asList(words);
		return itemRepository.findAll(nameInclude(l2));
	}

	// TODO 項目名を引数で受け取り、name/IN句検索 ⇒ IN句検索用の Specificationに変更
	private Specification<Item> nameInclude(List<String> nameList) {

		// TODO ラムダ式を使いましょう
		// TODO CollectionUtils#isEmpty()を使いましょう
		return nameList.size() == 0 ? null : new Specification<Item>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {

				return root.get("name").in(nameList);
			}
		};
	}
}
