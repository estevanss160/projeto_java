package com.example.demo.aplication;

import com.example.demo.domain.exception.ChampionNotFoundException;
import com.example.demo.domain.model.Champions;
import com.example.demo.domain.ports.ChampionsRepository;

public record AskChampionUseCase(ChampionsRepository repository) {
	
	public String askChampion(Long championId, String question) {
		Champions champions  = repository.findById(championId).orElseThrow(() -> new ChampionNotFoundException(championId));
		
		String championContext = champions.generateContextByQuestion(question);
		
		// TODO: Evoluir a lógica de negócio para considerar a integreação com IAs Generativas.	
		return championContext;
	}
}