package com.example.api;

import java.util.List;

import com.example.domain.Review;
import com.example.service.ItemService;
import com.example.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewRestController {

	@Autowired
	ReviewService reviewService;

	@Autowired
	ItemService itemService;

	@GetMapping
	public List<Review> getreviews() {

		return reviewService.findAll();
	}

	@PutMapping(path = "{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Review putreview(@PathVariable Integer id,@RequestBody Review review) {
		
		review.setItem(itemService.findById(id));
		
		return review.getItem() == null ? null : reviewService.create(review);
	}

	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletereview(@PathVariable Integer id) {

		reviewService.delete(id);
	}

	// @PutMapping(path = "{id}")
	// public Review putreview(@PathVariable Integer id, @RequestBody Review review) {

	// 	review.setId(id);

	// 	return reviewService.update(review);
	// }

	@GetMapping(path = "{id}")
	public Review findById(@PathVariable Integer id) {

		return reviewService.findById(id);
	
	}

	@PutMapping("/addstar/{id}")
	public Review addStar(@PathVariable Integer id, @RequestParam Double star) {

		Review review = reviewService.findById(id);

		if (review != null) {

			review.setStar((review.getStar() * review.getStarCount() + star) / (review.getStarCount() + 1));
			review.setStarCount(review.getStarCount() + 1);

			return reviewService.update(review);

		} else {

			return null;

		}

	}

}
