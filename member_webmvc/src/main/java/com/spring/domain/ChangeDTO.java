package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor
public class ChangeDTO {
	private String userid;
	private String currentPwd;
	private String newPwd;
	private String confirmPwd;
	
	public boolean passwordEquals() {
		return newPwd.equals(confirmPwd);
	}

}
