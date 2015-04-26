package jado.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	
	/*
	 * user도메인으로 묶는 것도 좋을 것 같아요.
	 * API와 controller가 분리시 관리가 어려울것 같아요 
	 */
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		session.invalidate();
		return "main";
	}
}
