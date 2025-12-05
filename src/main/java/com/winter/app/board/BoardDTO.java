package com.winter.app.board;

import java.time.LocalDate;
import java.util.List;

import com.winter.app.files.BoardFileDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//게시판관련 DTO의 부모로 사용
@Getter
@Setter
@ToString
public class BoardDTO extends CommentDTO {
	
	
	@NotBlank(message="dd")
	private String boardTitle;   
	@NotBlank(message="작성자는 필수입니다")
	private String boardWriter;   
	
	private Long boardHit;    
	
	private List<BoardFileDTO> fileDTOs;
}
