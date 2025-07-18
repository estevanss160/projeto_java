package com.example.demo.domain.ports;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Champions;

public interface ChampionsRepository {
	
	List<Champions> findAll();
	
	Optional<Champions> findById(Long id);
}