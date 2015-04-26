package jado.controller;

import jado.service.MailAuthService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.mail.template.MailTemplateStorage.Type;
import core.util.ServletRequestUtils;

@Controller
public class MailAuthController {
	private static final Logger logger = LoggerFactory.getLogger(MailAuthController.class);
	
	@Autowired private MailAuthService mailAuthService;
	
	/*
	 * 도대체 user가... 어디까지 있는거죠? ㅋㅋㅋ 
	 */
	@RequestMapping(value = "/user/mailAuth", method = RequestMethod.GET)
	public void InvalidAccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO 공통 에러 페이지가 만들어 지면 리다이렉트 시키기
		resp.getWriter().print("<h1>Invalid  access</h1>");
	}
	
	@RequestMapping(value = "/user/mailAuth", method = RequestMethod.POST)
	public void processMailAuth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userEmail = ServletRequestUtils.getRequiredStringParameter(request, "requestemail");
		String uuid = ServletRequestUtils.getRequiredStringParameter(request, "requestuuid");
		
		if(IsDuplicatedVerifyRequest(response, userEmail)) return;
		
		if(!mailAuthService.verify(userEmail, uuid)) {
			// TODO 인증 실패 페이지로 이동
			response.getWriter().print("<h1>Your e-mail authentication failed</h1>");
			logger.debug("메일 인증 실패");
			return;
		}
		
		mailAuthService.updateMailAuthStatus();
		mailAuthService.send(userEmail, Type.JOIN_WELCOME);

		// TODO 인증 성공 페이지로 이동
		response.getWriter().print("<h1>Your email is successfully authenticated</h1>");
		logger.debug("메일 인증 성공");
	}

	private boolean IsDuplicatedVerifyRequest(HttpServletResponse response, String userEmail) throws IOException {
		if(mailAuthService.isAlreadyVerified(userEmail)) {
			response.getWriter().print("<h1>Your email is Already authenticated</h1>");
			logger.debug("메일 중복 인증 시도가 발생함");
			return true;
		}
		return false;
	}
}
