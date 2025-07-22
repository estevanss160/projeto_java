package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.example.demo.aplication.AskChampionUseCase;
import com.example.demo.aplication.ListChampionsUseCase;
import com.example.demo.domain.ports.ChampionsRepository;
import com.example.demo.domain.ports.GenerativeAiService;

@EnableFeignClients
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository) {
		return new ListChampionsUseCase(repository);
	}
	@Bean
	public AskChampionUseCase provideAskChampionsUseCase(ChampionsRepository repository, GenerativeAiService genAiService) {
		return new AskChampionUseCase(repository, genAiService);
	}
}