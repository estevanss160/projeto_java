package com.example.demo.domain.exception;

public class ChampionNotFoundException extends RuntimeException{
	public ChampionNotFoundException(Long championId) {
		super("Champion %d not found. ".formatted(championId));
	}
}