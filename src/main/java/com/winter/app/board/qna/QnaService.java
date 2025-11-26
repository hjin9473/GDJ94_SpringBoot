package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.util.Pager;

@Service
public class QnaService {

	@Autowired
	private QnaDAO noticeDAO;
	
	public List<QnaDTO> list (Pager pager)throws Exception{
		Long totalCount= noticeDAO.count();
		
		pager.pageing(totalCount);

		
		return noticeDAO.list(pager);
	}
	
	public QnaDTO detail(QnaDTO noticeDTO)throws Exception{
		return noticeDAO.detail(noticeDTO);
	}
	
	public int add(QnaDTO noticeDTO)throws Exception{
		return noticeDAO.add(noticeDTO);
	}
	
	
}