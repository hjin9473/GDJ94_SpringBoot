package com.winter.app.ajax;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AjaxTest2 {
	
	private WebClient webClient;
	
	public AjaxTest2() {
		webClient = WebClient
				.builder()
				//공통 요소 설정
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build()
				;
	}
	
	
	public void t3() throws Exception {
		Flux<PostDTO> res = webClient
			.get()
			.uri("posts")
			.retrieve()
			.bodyToFlux(PostDTO.class)
			;
		
	    
//		Mono<List<PostDTO>> list = res.collectList();
//		List<PostDTO> result = list.block();
//	    log.info("{}", result.get(15));
		res.subscribe(dto ->{
			PostDTO postDTO = (PostDTO)dto;
			log.info("{}", postDTO.getId());
		});
	}
	
	
	public void t2() throws Exception {
		Mono<PostDTO> res = webClient
			.get()
			.uri("posts/2")
			.retrieve()
			.bodyToMono(PostDTO.class)
			;
		
		PostDTO postDTO = res.block();
	    
	    log.info("{}", postDTO);
		
	}
	
	
	public void t1() throws Exception {
		WebClient webClient = WebClient
				.builder()
				//공통 요소 설정
				.baseUrl("https://jsonplaceholder.typicode.com/")
				.build()
				;
	    Mono<ResponseEntity<PostDTO>> res = webClient
			.get()
			.uri("posts/1")
			.retrieve()
			.toEntity(PostDTO.class);
	    
	    PostDTO postDTO = res.block().getBody();
	    
	    log.info("{}", postDTO);
		
	}

}
