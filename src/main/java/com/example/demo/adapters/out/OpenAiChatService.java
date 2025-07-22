package com.example.demo.adapters.out;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.ports.GenerativeAiService;

import feign.RequestInterceptor;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "OPENAI", matchIfMissing = true)
@FeignClient(name = "openAiApi", url = "${openai.base-url}", configuration = OpenAiChatService.Config.class)
public interface OpenAiChatService extends GenerativeAiService {
	
	@PostMapping("/v1/chat/completions")
	OpenAiChatCompletionResp chatCompletion(OpenAiChatCompletionReq req);
		
	@Override
	default String generateContent(String objective, String context) {
		String model = "gpt-4.1";
		List<Message> messages = List.of(
				new Message("system", objective),
				new Message("user", context)
				);
		OpenAiChatCompletionReq req = new OpenAiChatCompletionReq(model, messages);
		
		OpenAiChatCompletionResp resp = chatCompletion(req);
		return resp.choices().getFirst().message().content();
	}
	record OpenAiChatCompletionReq(String model, List<Message> messagens) {}
	record Message(String role, String content) {}

	record OpenAiChatCompletionResp(List<Choice> choices) {}
	record Choice(Message message) {}
	
	class Config {
		public RequestInterceptor apiKeyRequstInterceptor(@Value("${open.api-key") String apikey) {
			return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apikey));
		}
	}
}