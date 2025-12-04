package com.winter.app.board.notice;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardService; // 인터페이스 임포트
import com.winter.app.files.BoardFileDTO;
import com.winter.app.files.FileManager;
import com.winter.app.util.Pager;

@Service
public class NoticeService implements BoardService {
    
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.notice}")
	private String uploadPath;
	
    // 1. LIST
	@Override
	public List<BoardDTO> list (Pager pager)throws Exception{
		// 1. totalCount 구하기
		Long totalCount= noticeDAO.count(pager);
		pager.pageing(totalCount);
		return noticeDAO.list(pager);
	}
	
	@Override
	public BoardDTO detail(BoardDTO boardDTO)throws Exception{
		return noticeDAO.detail(boardDTO);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int add(BoardDTO boardDTO, MultipartFile [] attach)throws Exception{
		
		int result = noticeDAO.add(boardDTO);
		
		
		//1. 파일을 HDD에 저장
		//	1) 어디에 저장?
		//	2) 어떤 이름으로 저장?
		if (attach != null) { 
			File file = new File(uploadPath);
		for(MultipartFile f: attach) {
			
			if (f== null || f.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(file, f);
		
			//4. 정보를 DB에 저장
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setFileOrigin(f.getOriginalFilename());
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			noticeDAO.fileAdd(boardFileDTO);
		}
		}
		
		return result;//noticeDAO.add(boardDTO);
	}
	
	@Override
	public int update(BoardDTO boardDTO)throws Exception{
		NoticeDTO noticeDTO = (NoticeDTO)boardDTO; 
		return noticeDAO.update(noticeDTO);
	}
	
	@Override
	public int delete(BoardDTO boardDTO)throws Exception{
		NoticeDTO noticeDTO = (NoticeDTO)boardDTO;
        
        BoardFileDTO boardFileDTO = new BoardFileDTO();
        boardFileDTO.setBoardNum(noticeDTO.getBoardNum());
        
        List<BoardFileDTO> fileList = noticeDAO.getFileList(boardFileDTO);
        
        if (fileList != null) {
            File file = new File(uploadPath); // 기본 저장 경로 (예: D:/GDJ94/upload/notice/)
            for(BoardFileDTO fileDTO : fileList) {
                fileManager.fileDelete(file, fileDTO.getFileName()); 
            }
        }
        
        // 3. DB 게시글 삭제 (ON DELETE CASCADE로 파일 테이블 레코드도 자동 삭제)
		return noticeDAO.delete(noticeDTO);
	}
	@Override
	public BoardFileDTO fileDetail(BoardFileDTO boardFileDTO) throws Exception {
		return noticeDAO.fileDetail(boardFileDTO);
	}
	
}