package com.example.demo.aplication;

import com.example.demo.domain.exception.ChampionNotFoundException;
import com.example.demo.domain.model.Champions;
import com.example.demo.domain.ports.ChampionsRepository;
import com.example.demo.domain.ports.GenerativeAiService;

public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiService genAiService) {
	
	public String askChampion(Long championId, String question) {
		Champions champions  = repository.findById(championId).orElseThrow(() -> new ChampionNotFoundException(championId));
		
		String context = champions.generateContextByQuestion(question);
		String objective = """
				Atue como uma assistente com a habilidade de se comportar como os Campeões do League of Legends (LOL).
				Responda perguntas incorporando a personalidades e estilo de um determinado Campeão.
				Segue a pergunta, o nome do Campeão e sua Respectiva lore (história)
				""";
		
		
		

		return genAiService.generateContent(objective, context);
	}
}