package com.example.demo.domain.model;

public record Champions(
		long id,
		String name,
		String role,
		String lore,
		String imagUrl
		) {
	public String generateContextByQuestion(String question) {
		return """
				Pergunta: %s
				Nome do Campeão: %s
				Função: %s
				Lore (História): %s
				""".formatted(question, this.name, this.role, this.lore);
	}
}