package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
	
	@Autowired
	private UsersDAO usersDAO;
	
	public int register(UsersDTO usersDTO)throws Exception{
		return usersDAO.register(usersDTO);
	}

}
