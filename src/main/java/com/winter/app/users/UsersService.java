package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManager;

@Service
public class UsersService {
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
    private FileManager fileManager;
	
	@Value("${app.upload.profile}") 
    private String uploadPath;
	
	public int register(UsersDTO usersDTO, MultipartFile attach) throws Exception{

        int result = usersDAO.register(usersDTO); 

        if (attach != null && !attach.isEmpty() && result > 0) {

            File file = new File(uploadPath);

            String fileName = fileManager.fileSave(file, attach); 

            UsersFileDTO usersFileDTO = new UsersFileDTO();
            usersFileDTO.setFileName(fileName);
            usersFileDTO.setFileOrigin(attach.getOriginalFilename());
            usersFileDTO.setUsername(usersDTO.getUsername());
            usersDAO.profileAdd(usersFileDTO);
        }

        return result;
    }
	
	public UsersDTO login(UsersDTO usersDTO) throws Exception {
	        
	        UsersDTO dbUsersDTO = usersDAO.detail(usersDTO);
	        
	        if (dbUsersDTO == null) {
	            return null;
	        }
	        if (dbUsersDTO.getPassword().equals(usersDTO.getPassword())) {
	            dbUsersDTO.setPassword(null); 
	            return dbUsersDTO;
	        }
	        return null;
    }
	
public int update(UsersDTO usersDTO, MultipartFile attach) throws Exception {
	    
	    // 1. 비밀번호가 비어있으면 기존 비밀번호를 유지하기 위해 null로 설정 (UsersDAO.xml의 <if> 조건 처리)
	    if (usersDTO.getPassword() == null || usersDTO.getPassword().isEmpty()) {
	        usersDTO.setPassword(null);
	    }
	    
	    // 2. 회원 정보(이름, 이메일, 전화번호, 생일, 비밀번호) 업데이트
	    int result = usersDAO.update(usersDTO);
	    
	    // 3. 프로필 파일 처리
	    if (attach != null && !attach.isEmpty() && result > 0) {
	        
	        // 기존 프로필 정보 조회
	        UsersDTO oldUser = usersDAO.detail(usersDTO);
	        
	        // 기존 파일이 있으면 서버에서 삭제하고 DB에서도 삭제
	        if (oldUser != null && oldUser.getProfileDTOs() != null && !oldUser.getProfileDTOs().isEmpty()) {
	            UsersFileDTO oldFileDTO = oldUser.getProfileDTOs().get(0); // 프로필은 1개라고 가정
	            
	            // 서버 파일 시스템에서 삭제
	            fileManager.fileDelete(new File(uploadPath), oldFileDTO.getFileName());
	            
	            // DB에서 삭제
	            usersDAO.profileDelete(usersDTO.getUsername());
	        }
	        
	        // 새 파일 저장
	        File file = new File(uploadPath);
	        String fileName = fileManager.fileSave(file, attach); 
	        
	        UsersFileDTO usersFileDTO = new UsersFileDTO();
	        usersFileDTO.setFileName(fileName);
	        usersFileDTO.setFileOrigin(attach.getOriginalFilename());
	        usersFileDTO.setUsername(usersDTO.getUsername());
	        
	        // DB에 새 프로필 정보 추가
	        usersDAO.profileAdd(usersFileDTO);
	    }
	    
	    return result;
	}
	        

    public int delete(UsersDTO usersDTO) throws Exception {
        
        UsersDTO oldUser = usersDAO.detail(usersDTO);
        
        if (oldUser != null && oldUser.getProfileDTOs() != null) {
            for (UsersFileDTO fileDTO : oldUser.getProfileDTOs()) {
                 fileManager.fileDelete(new File(uploadPath), fileDTO.getFileName());
            }
        }
        
        usersDAO.profileDelete(usersDTO.getUsername());
        
        return usersDAO.delete(usersDTO);
    }
	
    public UsersDTO detail(UsersDTO usersDTO) throws Exception {
        return usersDAO.detail(usersDTO); 
    }

}