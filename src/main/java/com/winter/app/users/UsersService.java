package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
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
	
	// UsersService.java (삭제할 메서드)
	public Boolean getError(UsersDTO usersDTO, BindingResult bindingResult) throws Exception{
	    //check : true -> 검증 실패, error 존재
	    //check : flase -> 검증 성공, error 존재 X
	    //1. annotation 검증 결과
	    boolean check = bindingResult.hasErrors();
	    
	    //2. password 일치 하는지 검증
	    if (!usersDTO.getPassword().equals(usersDTO.getPasswordCheck())) {
	        check=true;
	        //bindingResult.reject("멤버변수명", "properties의 키값");
	        bindingResult.rejectValue("passwordCheck", "user.password.equal");
	    }
	    return check;
	}
	
	
	
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
	
	// com.winter.app.users.UsersService.java (update 메서드 수정)

	public int update(UsersDTO usersDTO, MultipartFile attach) throws Exception {
	    
	    // 1. 비밀번호 처리 (기존 유지)
	    if (usersDTO.getPassword() == null || usersDTO.getPassword().isEmpty()) {
	        usersDTO.setPassword(null);
	    }
	    
	    // 2. 회원 정보(users 테이블) 업데이트
	    // 'result'는 users 테이블의 수정 여부만 담고 있음 (0 또는 1)
	    int result = usersDAO.update(usersDTO); 
	    
	    // 3. 프로필 파일 처리 (result에 관계없이 파일이 첨부되었으면 시도)
	    if (attach != null && !attach.isEmpty()) { // <-- 이 조건만 사용해야 합니다!
	        
	        // ... (기존 파일 삭제 및 DB 삭제 로직 - oldUser를 이용) ...
	        UsersDTO oldUser = usersDAO.detail(usersDTO);
	        if (oldUser != null && oldUser.getProfileDTOs() != null && !oldUser.getProfileDTOs().isEmpty()) {
	            UsersFileDTO oldFileDTO = oldUser.getProfileDTOs().get(0);
	            fileManager.fileDelete(new File(uploadPath), oldFileDTO.getFileName());
	            usersDAO.profileDelete(usersDTO.getUsername());
	        }

	        // 새 파일 저장 및 DB 추가
	        File file = new File(uploadPath);
	        String fileName = fileManager.fileSave(file, attach); 
	        
	        UsersFileDTO usersFileDTO = new UsersFileDTO();
	        usersFileDTO.setFileName(fileName);
	        usersFileDTO.setFileOrigin(attach.getOriginalFilename());
	        usersFileDTO.setUsername(usersDTO.getUsername());
	        
	        int fileResult = usersDAO.profileAdd(usersFileDTO);
	        
	        // 🚨 최종 결과 반영: users 테이블이 수정되었거나 (result=1) 파일이 수정되었으면 (fileResult=1) 성공으로 간주
	        if (fileResult > 0) {
	            result = 1; 
	        }
	    }
	    
	    // users 테이블 또는 profile 테이블 중 하나라도 수정되었으면 1을 반환
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