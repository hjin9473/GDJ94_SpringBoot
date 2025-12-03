package com.winter.app.users;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersDTO {
	private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Date birth;
    
    private List<UsersFileDTO> profileDTOs;

}
