package com.winter.app.board.qna;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardService; // 인터페이스 임포트
import com.winter.app.files.BoardFileDTO;
import com.winter.app.files.FileManager;
import com.winter.app.util.Pager;

@Service
public class QnaService implements BoardService { // BoardService 구현
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")
	private String uploadPath;
	
	@Override
	public List<BoardDTO> list (Pager pager)throws Exception{
		Long totalCount= qnaDAO.count(pager);
		pager.pageing(totalCount);
		return qnaDAO.list(pager);
	}
	
	@Override
	public BoardDTO detail(BoardDTO boardDTO)throws Exception{
		return qnaDAO.detail(boardDTO);
	}
	
	@Override
	public int add(BoardDTO boardDTO, MultipartFile [] attach)throws Exception{
	    
	    QnaDTO qnaDTO = (QnaDTO)boardDTO; // BoardDTO를 QnaDTO로 캐스팅
	    
	    int result = qnaDAO.add(qnaDTO);
	    
	    qnaDAO.refUpdate(qnaDTO); 
	    
	    if (attach != null) { 
	        File file = new File(uploadPath);
	        
	        for(MultipartFile f: attach) {
	            if (f== null || f.isEmpty()) {
	                continue;
	            }
	            
	            String fileName = fileManager.fileSave(file, f);
	        
	            BoardFileDTO boardFileDTO = new QnaFileDTO(); // QnaFileDTO 사용
	            boardFileDTO.setFileName(fileName);
	            boardFileDTO.setFileOrigin(f.getOriginalFilename());
	            boardFileDTO.setBoardNum(qnaDTO.getBoardNum()); // 캐스팅된 qnaDTO에서 boardNum 사용
	            qnaDAO.fileAdd(boardFileDTO);
	        }
	    }
	    
	    return result; 
	}
    
	@Override
	public int update(BoardDTO boardDTO)throws Exception{
		QnaDTO qnaDTO = (QnaDTO)boardDTO;
		return qnaDAO.update(qnaDTO);
	}
	@Override
	public int delete(BoardDTO boardDTO)throws Exception{
		QnaDTO qnaDTO = (QnaDTO)boardDTO;
		return qnaDAO.delete(qnaDTO);
	}
	public int reply(QnaDTO qnaDTO) throws Exception{
		//1. 부모의 정보를 조회
		QnaDTO parent = (QnaDTO)qnaDAO.detail(qnaDTO);
		//2. 부모의 정보를 이용해서 step을 업데이트
		int result = qnaDAO.stepUpdate(parent);
		//3. 부모의 정보를 이용해서 ref, step, depth를 세팅
		qnaDTO.setBoardRef(parent.getBoardRef());
	    qnaDTO.setBoardStep(parent.getBoardStep() + 1);
	    qnaDTO.setBoardDepth(parent.getBoardDepth() + 1);
		//4. insert
	    result = qnaDAO.reply(qnaDTO);
	    
	    return result;
	}
	@Override
	public BoardFileDTO fileDetail(BoardFileDTO boardFileDTO) throws Exception {
		return qnaDAO.fileDetail(boardFileDTO);
	}

}