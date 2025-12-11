package com.winter.app.ajax;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.nimbusds.jose.shaded.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AjaxTest1 {
	
	public void t3() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Content-type", "application/jason; charset=UTF-8");
		
		
//		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//		params.add("title", "title");
//		params.add("body", "body");
//		params.add("userId", 5);
		
		PostDTO postDTO = new PostDTO();
		postDTO.setTitle("title");
		postDTO.setBody("body");
		postDTO.setUserId(5);
		
		
//		HttpEntity<MultiValueMap<String, Object>> req = new HttpEntity<MultiValueMap<String,Object>>(params, headers);
		
	    
//		log.info("{}", res.get(5));
	}

	public void t2() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		List<PostDTO> res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class);
		
		log.info("{}", res.get(5));
		
	}
	
	
	
	
	public void t1() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PostDTO> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", PostDTO.class, (Object) null);
		PostDTO postDTO = res.getBody();
		
//		PostDTO postDTO = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/2", PostDTO.class, (Object) null);
		
		
		log.info("{}", postDTO);
				
	}
}
