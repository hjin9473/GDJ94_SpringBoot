package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.winter.app.board.qna.QnaDTO;


@Controller
@RequestMapping("/users/*")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping
	public String register() throws Exception{
		return "users/register";
	}
	
    
    @PostMapping("/users/register")
    public String register(UsersDTO usersDTO, RedirectAttributes redirectAttributes) throws Exception {
        
        int result = usersService.register(usersDTO); 
        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("result", "회원가입 성공"); 
        } else {
            redirectAttributes.addFlashAttribute("result", "회원가입 실패");
        }
        
        return "redirect:./list"; 
    }
} 
	


