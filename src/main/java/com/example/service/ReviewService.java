package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Review;
import com.example.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;

	public List<Review> findAll() {
		return reviewRepository.findAll();
	}

}
