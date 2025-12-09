package com.winter.app.home;


import java.security.Principal;
import java.util.Enumeration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.users.UserDTO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	
	public String index5(@AuthenticationPrincipal UserDTO userDTO)throws Exception{
		//Spring boot 3.2 이상 부터 가능
		
		
		System.out.println(userDTO.getUsername());
		
		return "index";
	}
	
	public String index4(Principal principal)throws Exception{
		//보류 error 발생
		UserDetails userDTO = (UserDetails)principal;
		System.out.println(userDTO.getUsername());
		
		return "index";
	}
	
	
	public String index3(Authentication authentication)throws Exception{
		UserDTO userDTO = (UserDTO)authentication.getPrincipal();
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		return "index";
	}
	
	
	public String index2()throws Exception{
		Object obj = SecurityContextHolder.getContext().getAuthentication();
		
		Authentication authentication = (Authentication)obj;
		
		UserDTO userDTO = (UserDTO)authentication.getPrincipal();
		
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		
		return "index";
	}
	
	
	
	public String index(HttpSession session)throws Exception{
		SecurityContextImpl obj=(SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication authentication = obj.getAuthentication();
		log.info("{}", authentication);
		UserDTO userDTO=(UserDTO) authentication.getPrincipal();
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		return "index";
	}
}





