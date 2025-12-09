package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users/*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Value("${category.user}")
	private String category;

	@ModelAttribute("category")
	private String getCategory() {
		return this.category;
	}
	
	@GetMapping("register")
	public void register(UserDTO userDTO)throws Exception{}	
	
	
	@PostMapping("register")
	public String register(@Validated(RegisterGroup.class) UserDTO userDTO,BindingResult bindingResult ,MultipartFile attach)throws Exception{
		if(userService.getError(userDTO, bindingResult)) {
			return "users/register";
		}
		
		//int result = userService.register(userDTO, attach);
		
		return "redirect:/";
	}
	@GetMapping("mypage")
	public void detail(@AuthenticationPrincipal UserDTO userDTO, Model model)throws Exception{
		userDTO = userService.detail(userDTO);
		model.addAttribute("user", userDTO);
	}
	
	
	@GetMapping("login")
	public void login()throws Exception{}	
	

	
	@GetMapping("update")
	public void update(@AuthenticationPrincipal UserDTO userDTO, Model model)throws Exception{
		
		model.addAttribute("userDTO", userDTO);
	}
	
	   @PostMapping("update")
	   public String update(@Validated(RegisterGroup.class) UserDTO usersDTO, BindingResult bindingResult, Authentication authentication) throws Exception{
	      if (bindingResult.hasErrors()) {
	         return "users/update";
	      }
	      
	      usersDTO.setUsername(authentication.getName());
	      
	      int result = userService.update(usersDTO);
	      
	      if (result > 0) {
	         UsernamePasswordAuthenticationToken to = new UsernamePasswordAuthenticationToken(bindingResult, authentication.getCredentials(), authentication.getAuthorities());
	         SecurityContextHolder.getContext().setAuthentication(to);
	      }
	      
	      return "redirect:/";
	   }
	
	@GetMapping("change")
	public void change(UserDTO userDTO)throws Exception{
		
	}
	
	@PostMapping("change")
	public String change(@Validated(PasswordGroup.class) UserDTO userDTO,BindingResult bindingResult  ,  String exist)throws Exception{
		
		if(userService.getError(userDTO, bindingResult)) {
			return "users/change";
		}
		
		return "redirect:mypage";
	}

	
}
