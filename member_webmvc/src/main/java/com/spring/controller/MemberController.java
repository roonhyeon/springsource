package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// login.jsp를 보여주는 컨트롤러 작성
	@GetMapping("/login")
	public void loginForm() {
		log.info("login form request");
	}

	@PostMapping("/login")
	public String loginPost(LoginDTO loginDTO, HttpSession session) {
		log.info("login request "+loginDTO); // 중간 중간 확인용
		
		AuthDTO authDTO = service.login(loginDTO);
		
		if(authDTO!=null) {
			// session 로그인 정보 담기
			session.setAttribute("authDTO", authDTO);
			
			// index로 이동
			return "redirect:/";
		}else {
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("/logout")
	public String logoutGet(HttpSession session) {
		log.info("로그아웃 요청");
		
		session.removeAttribute("authDTO");
		
		// session 해제 index 이동
		return "redirect:/";
	}
	
	@GetMapping("/step1")
	public void stepGet() {
		log.info("약관 페이지 보여주기");
		// member/step1.jsp 보여주기
	}
	
	@PostMapping("/step1")
    public String stepPost(boolean agree, RedirectAttributes rttr) {
		log.info("약관동의 "+agree);
    	// 약관동의 여부
    	
    	// true인 경우 register.jsp 보여주기
    	// false인 경우 step1.jsp 보여주기
        if(agree) return "/member/register";
        else {
        	rttr.addFlashAttribute("check", false);
        	return "redirect:/member/step1";
//        	return "/member/step1";
        }
    	
    }
	
	@PostMapping("/register")
	public String registerPost(MemberDTO memberDTO) {
		log.info("register request "+memberDTO);
		service.insert(memberDTO);
		
		// 회원가입 성공시 로그인 페이지로 이동
		return "/member/login";
	}
	
	// 중복 아이디 점검
	@PostMapping("/dupId")
	@ResponseBody // 컨트롤러 작업이 완료될 때 결과값으로 리턴(View Resolver를 동작시키지 않는다.)
	public String dupIdCheck(String userid) {
		log.info("duplicate request "+userid);
		
		boolean idCheck=service.dupId(userid);
		
		if(idCheck) {
			return "true"; // /WEB-INF/views/test.jsp
		}else {
			return "false"; // /WEB-INF/views/false.jsp
		}
	}
	
	// 회원탈퇴: leave.jsp 띄어주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("leave form request");
	}
	
	// 회원탈퇴 성공 시 index로 이동
	@PostMapping("/leave")
	public String leave(LoginDTO loginDTO, HttpSession session) {
		log.info("leave request ");
		
		boolean pwdCheck=service.remove(loginDTO);
		
		if(pwdCheck) {
			session.invalidate(); // session 해제
			return "redirect:/";
		}
		return "redirect:/member/leave";
	}
	
	// changePwd.jsp 보여주기
	@GetMapping("/changePwd")
	public void changeForm() {
		log.info("change form request");
	}
	
	@PostMapping("/changePwd")
	public String change(ChangeDTO changeDTO, HttpSession session) {
		log.info("change request "+changeDTO);
		
		if(changeDTO.passwordEquals()) {
			// 현재 비밀번호 일치 확인
			// 일치 시(true) 비밀번호 변경 & session 제거 & 로그인 페이지 보여주기
			// false: 비밀번호 변경 페이지 보여주기
			boolean pwd=service.update(changeDTO);
			
			if(pwd) {
				session.invalidate();
				return "redirect:/member/login";
			}
		}
		return "redirect:/member/changePwd";
	}

}
