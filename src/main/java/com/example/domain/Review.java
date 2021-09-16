package com.example.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "reviews")
@Data
public class Review implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7316415712051167210L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer item_id;
	
	@Column(nullable = false)
	private String reviewer_name;

	@Column(nullable = false)
	private String comment;
	
	@Column(nullable = false)
	private LocalDateTime created_at;
	
//	private String imgPath;
}