package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Review;
import com.example.service.ReviewService;

@RestController
@RequestMapping("api/reviews")
public class ReviewRestController {
	@Autowired
	ReviewService reviewservice;

	@GetMapping
	public List<Review> getreviews() {
		List<Review> reviews = reviewservice.findAll();
		return reviews;
	}



}
