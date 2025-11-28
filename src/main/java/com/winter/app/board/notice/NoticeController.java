package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public void list(Pager pager, Model model)throws Exception{
		

		List<NoticeDTO> list= noticeService.list(pager);
	
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
	}
	
	@GetMapping("add")
	public void add() throws Exception {}
	
	@PostMapping("add")
	public ModelAndView add(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    int result = noticeService.add(noticeDTO); // NoticeDAO의 add 메서드 호출
	    
	    if(result > 0) {
	        redirectAttributes.addFlashAttribute("result", "공지사항 등록 성공"); 
	    } else {
	        redirectAttributes.addFlashAttribute("result", "공지사항 등록 실패");
	    }
	    mv.setViewName("redirect:./list"); 
	    
	    return mv;
	}
	@GetMapping("detail")
		public ModelAndView detail(NoticeDTO noticeDTO) throws Exception {
			ModelAndView mv = new ModelAndView();
			
			noticeDTO = noticeService.detail(noticeDTO);
			
			mv.addObject("dto", noticeDTO);
			mv.setViewName("notice/detail");
			
			return mv;	
	}
	
	@GetMapping("update") 
    public ModelAndView update(NoticeDTO noticeDTO) throws Exception {
        ModelAndView mv = new ModelAndView();
        
        noticeDTO = noticeService.detail(noticeDTO); 
        
        mv.addObject("dto", noticeDTO);
        mv.setViewName("notice/update"); 
        
        return mv;
    }
	@PostMapping("update")
    public ModelAndView update(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView();
        
        int result = noticeService.update(noticeDTO); 
        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("result", "게시물이 성공적으로 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("result", "게시물 수정에 실패했습니다.");
        }
        
        mv.setViewName("redirect:./list");
        
        return mv;
    }
	
	@PostMapping("delete")
    public ModelAndView delete(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView();
        
        int result = noticeService.delete(noticeDTO); 
        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("result", "게시물이 성공적으로 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("result", "게시물 삭제에 실패했습니다.");
        }
        
        mv.setViewName("redirect:./list");
        
        return mv;
    }
	
	

}