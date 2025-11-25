package com.winter.app.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class BoardDAOTest {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Test
	void testList()throws Exception{
		List<BoardDTO> ar = boardDAO.list();
		assertNotEquals(0, ar.size());
		System.out.println(ar);
		
	}
	@Test
	void testDelete() throws Exception {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(104L);
		int result = boardDAO.delete(boardDTO);
	    assertEquals(1, result);
	}
	
	@Test
	void testUpdate() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("t", "update T");
		map.put("c", "update C");
		map.put("n", 108L);
		
	    int result = boardDAO.update(map);
	    assertEquals(1, result);
	}
	
	@Test
	void testAdd() throws Exception {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setWriter("테스트");
		boardDTO.setTitle("생성");
	    boardDTO.setContents("완료");
	    int result = boardDAO.add(boardDTO);
	    assertEquals(1, result);
	}

	@Test
	void testDetail() throws Exception {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(11111111L);
		
		boardDTO=boardDAO.detail(boardDTO);
//		log.info(boardDTO.toString());
		
		assertNotNull(boardDTO);
	}

}
