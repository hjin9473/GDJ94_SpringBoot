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
	    
		if (usersDTO.getPassword() != null && usersDTO.getPassword().isEmpty()) {
	        usersDTO.setPassword(null);
	    }
	    int result = usersDAO.update(usersDTO);
	    
	    if (attach != null && !attach.isEmpty() && result > 0) {
	        UsersDTO oldUser = usersDAO.detail(usersDTO);     
	        
	        if (oldUser != null && oldUser.getProfileDTOs() != null) {
	            for (UsersFileDTO fileDTO : oldUser.getProfileDTOs()) {
	                fileManager.fileDelete(new File(uploadPath), fileDTO.getFileName()); 
	            }
	        }
	        
	        usersDAO.profileDelete(usersDTO.getUsername());

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