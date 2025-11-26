package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService noticeService;
	
	@GetMapping("list")
	public void list(Pager pager, Model model)throws Exception{
		

		List<QnaDTO> list= noticeService.list(pager);
	
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
	}

}