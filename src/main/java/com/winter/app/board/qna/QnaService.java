package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.winter.app.board.notice.NoticeDTO;
import com.winter.app.util.Pager;

@Service
public class QnaService {

	@Autowired
	private QnaDAO qnaDAO;
	
	public List<QnaDTO> list (Pager pager)throws Exception{
		Long totalCount= qnaDAO.count(pager);
		
		pager.pageing(totalCount);

		
		return qnaDAO.list(pager);
	}
	
	public QnaDTO detail(QnaDTO qnaDTO)throws Exception{
		return qnaDAO.detail(qnaDTO);
	}
	
	public int add(QnaDTO qnaDTO)throws Exception{
		qnaDAO.add(qnaDTO);
		return qnaDAO.refUpdate(qnaDTO);
	}
	public int update(QnaDTO qnaDTO)throws Exception{
		return qnaDAO.update(qnaDTO);
	}
	
	public int delete(QnaDTO qnaDTO)throws Exception{
		return qnaDAO.delete(qnaDTO);
	}
	
}