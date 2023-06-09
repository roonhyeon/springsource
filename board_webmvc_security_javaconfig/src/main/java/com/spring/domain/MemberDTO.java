package com.spring.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class MemberDTO {
	private String userid;
	private String userpw;
	private String username;
	private Date regdate;
	private Date updatedate;
	private boolean enabled;
	
	private List<MemberAuthDTO> authorities;

}
