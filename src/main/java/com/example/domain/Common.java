package com.example.domain;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data

public abstract class Common {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Integer id;

}
