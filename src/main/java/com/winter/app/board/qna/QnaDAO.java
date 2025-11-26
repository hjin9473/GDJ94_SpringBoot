package com.winter.app.board.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager;

@Mapper
public interface QnaDAO {
	
	public QnaDTO detail(QnaDTO noticeDTO) throws Exception;
	
	public List<QnaDTO> list(Pager pager) throws Exception;
	
	public Long getTotalCount(Pager pager) throws Exception;
	
	public Long count() throws Exception;
	
	public int add(QnaDTO noticeDTO) throws Exception;

	
	
	
}
