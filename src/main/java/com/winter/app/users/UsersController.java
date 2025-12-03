package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users") 
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/register") 
	public String register() throws Exception{
		return "users/register";
	}
	
	@PostMapping("/register")
    public String register(UsersDTO usersDTO, MultipartFile attach, RedirectAttributes redirectAttributes) throws Exception { 

        int result = usersService.register(usersDTO, attach); 

        if (result > 0) {
            redirectAttributes.addFlashAttribute("result", "회원가입 성공"); 
        } else {
            redirectAttributes.addFlashAttribute("result", "회원가입 실패");
        }

        return "redirect:/"; 
    }
    
    @GetMapping("/login")
    public String loginForm() throws Exception {
        return "users/login";
    }
    @PostMapping("/login")
    public String login(UsersDTO usersDTO, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        
        UsersDTO resultDTO = usersService.login(usersDTO);
        
        if (resultDTO != null) {
            session.setAttribute("usersDTO", resultDTO); 
            redirectAttributes.addFlashAttribute("result", resultDTO.getName() + "님 환영합니다!");
            return "redirect:/"; 
        } else {
            redirectAttributes.addFlashAttribute("result", "로그인 실패: ID 또는 비밀번호를 확인해주세요.");
            return "redirect:./login"; 
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate(); 
        return "redirect:/"; 
    }
    
    @GetMapping("/mypage")
    public String mypage() throws Exception {
        return "users/mypage"; 
    }
    
    @GetMapping("/update")
    public ModelAndView updateForm(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        
        UsersDTO loginUser = (UsersDTO)session.getAttribute("usersDTO");
        
        if (loginUser == null) {
            mv.setViewName("redirect:/users/login");
            return mv;
        }
        
        UsersDTO paramDTO = new UsersDTO();
        paramDTO.setUsername(loginUser.getUsername());
        
        UsersDTO retrievedDTO = usersService.detail(paramDTO);
        
        mv.addObject("dto", retrievedDTO);
        mv.setViewName("users/update"); // users/update.jsp 호출
        return mv;
    }
     
 // UsersController.java 파일 수정
    @PostMapping("/update")
    public String update(UsersDTO usersDTO, MultipartFile attach, 
                         RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
        
        int result = usersService.update(usersDTO, attach);
        
        if (result > 0) {
            
            UsersDTO paramDTO = new UsersDTO(); 
            paramDTO.setUsername(usersDTO.getUsername());
            UsersDTO updatedDTO = usersService.detail(paramDTO);
            
            session.setAttribute("usersDTO", updatedDTO); 
            
            redirectAttributes.addFlashAttribute("result", "정보가 성공적으로 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("result", "정보 수정에 실패했습니다.");
        }
        
        return "redirect:./mypage"; 
    }
    
    
    
    @PostMapping("/delete") 
    public String delete(UsersDTO usersDTO, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        
        int result = usersService.delete(usersDTO);
        
        if (result > 0) {
            session.invalidate(); // 회원 탈퇴 성공 시 세션 무효화 (로그아웃)
            redirectAttributes.addFlashAttribute("result", "회원 탈퇴가 완료되었습니다.");
            return "redirect:/"; // 메인 페이지로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("result", "회원 탈퇴에 실패했습니다.");
            return "redirect:./mypage";
        }
    }
}