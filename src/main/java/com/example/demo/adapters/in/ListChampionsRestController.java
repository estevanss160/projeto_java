package com.example.demo.adapters.in;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aplication.ListChampionsUseCase;
import com.example.demo.domain.model.Champions;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Campeões", description = "Endpoints do domínio de Campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {
		
	@GetMapping
	public List<Champions> findAllChampions() { return useCase.findAll(); }
}