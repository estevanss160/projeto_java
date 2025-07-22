package com.example.demo.adapters.out;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.ports.GenerativeAiService;

import feign.FeignException;
import feign.RequestInterceptor;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "gemini", matchIfMissing = true)
@FeignClient(name = "geminiApi", url = "${gemini.base-url}", configuration = GoogleGeminiService.Config.class)
public interface GoogleGeminiService extends GenerativeAiService {
	
	@PostMapping("/v1beta/models/gemini-2.0-flash:generateContent")
	GoogleGeminiResp textOnlyInput(GoogleGeminiReq req);
		
	@Override
	default String generateContent(String objective, String context) {
		String prompt = """
				%s
				%s
				""".formatted(objective, context);
		
		GoogleGeminiReq req = new GoogleGeminiReq(
				List.of(new Content(List.of(new Part(prompt))))
				);
		try {
		GoogleGeminiResp resp = textOnlyInput(req);
		return resp.candidates().getFirst().content().parts().getFirst().text();
		} catch (FeignException httpErrors) {
			System.out.println(httpErrors.getMessage());
			return "Foi mal! Error de comunicação com a API do Google Gemini.";
		} catch (Exception unexceptedError) {
			return "Deu mais ruim ainda! O retorno da API do Google Gemini não contem os dados esperados.";
		}
		
	}
	record GoogleGeminiReq(List<Content> contents) {}
	record Content(List<Part> parts) {}
	record Part(String text) {}
	record GoogleGeminiResp(List<Candidate> candidates) {}
	record Candidate(Content content) {}
	
	class Config {
		@Bean
		public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apikey) {
			return requestTemplate -> requestTemplate.query("key", apikey);
		}
	}
}