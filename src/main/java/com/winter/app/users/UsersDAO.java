package com.winter.app.users;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersDAO {
	
	public int register(UsersDTO usersDTO) throws Exception;
	
	public UsersDTO detail(UsersDTO usersDTO) throws Exception;
	
	public int profileAdd(UsersFileDTO usersFileDTO) throws Exception;
	
	public int update(UsersDTO usersDTO) throws Exception;
	    
    public int delete(UsersDTO usersDTO) throws Exception;
    
    public int profileDelete(String username) throws Exception;

}
