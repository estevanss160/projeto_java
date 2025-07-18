package com.example.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.aplication.ListChampionsUseCase;
import com.example.demo.domain.model.Champions;

@SpringBootTest
public class ListChampionsUseCaseIntegrationTest {
	
	@Autowired
	private ListChampionsUseCase listChampionsUseCase;
	
	public void testListChampions() {
		List<Champions> champions = listChampionsUseCase.findAll();
		
		assertEquals(13, champions.size());
	}
}
