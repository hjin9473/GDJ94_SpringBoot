package com.winter.app.users;

import java.sql.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersDTO implements RegisterGroup,UpdateGroup{
	@NotBlank(groups = {RegisterGroup.class})
	private String username;
	
	@NotBlank(groups = {RegisterGroup.class})
    private String password;
		
    private String passwordCheck;
    
    @NotBlank(groups = {RegisterGroup.class, UpdateGroup.class})
    private String name;
    
    @Email(groups = {RegisterGroup.class, UpdateGroup.class})
    @NotBlank(groups = {RegisterGroup.class})
    private String email;
    
    private String phone;
    
    private Date birth;
    
    private List<UsersFileDTO> profileDTOs;

}
