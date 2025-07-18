package com.example.demo.adapters.in;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aplication.AskChampionUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Campeões", description = "Endpoints do domínio de Campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record AskChampionRestController(AskChampionUseCase useCase) {
		
	@PostMapping("/{id}/ask") 
	public AskChampionResponse AskChampions(
			@PathVariable("id") Long championId, 
			@RequestBody AskChampionRequest request) { 
		
		String answer = useCase.askChampion(championId, request.question());
		
		return new AskChampionResponse(answer); 
		}
	public record AskChampionRequest(String question) {}
	public record AskChampionResponse(String answer) {}
}