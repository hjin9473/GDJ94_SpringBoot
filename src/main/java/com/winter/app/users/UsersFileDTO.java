package com.winter.app.users;

import com.winter.app.files.BoardFileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersFileDTO extends BoardFileDTO {
	
	private String username;

}
