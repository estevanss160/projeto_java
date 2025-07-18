package com.example.demo.aplication;

import java.util.List;

import com.example.demo.domain.model.Champions;
import com.example.demo.domain.ports.ChampionsRepository;

public record ListChampionsUseCase(ChampionsRepository repository) {
	
	public List<Champions> findAll() {
		return repository.findAll();
	}
}