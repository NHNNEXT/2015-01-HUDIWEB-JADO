package jado.controller;

import jado.service.LoginService;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.util.DecryptRSA;
import core.util.EncryptRSA;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String viewLoginPage(String returnUrl, Model model, HttpSession session) {

		// TODO 로그인한 사용자가 로그인 페이지에 접속하기 이전 주소로 돌려주기 위한 기능에 오류가 존재함 - 수정 필요
		logger.debug("접속한 회원의 이전 주소 {}", returnUrl);
		model.addAttribute("url", returnUrl);

		if(!encryptPrepareProcess(session, model).isSuccess()) {
			return "loginFailure";
		}
		return "login";
	}

	/*
	 * 제 생각엔
	 * rsa decrypt 로직을 filter에 넣어도 될 것 같아요. 
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String processLogin(@RequestParam("idEncryption") String userId, @RequestParam("pwEncryption") String password, HttpSession session, Model model) {
		Result decryptedUserId; 
		Result decryptedUserPassword;
		
		PrivateKey privateKey = getPrivateKeyAndDestroyKey(session);
		decryptedUserId = decryptString(privateKey, model,  userId);
		decryptedUserPassword = decryptString(privateKey, model, password);

		if(!(decryptedUserId.isSuccess() && decryptedUserPassword.isSuccess())){
			model.addAttribute("errorMessage", "암호화 전송된 정보를 해독하는 과정에서 오류 발생, 페이지 새로고침후 재시도 바람");
			return "loginFailure";
		}
		
		userId = (String)decryptedUserId.getValue("decryptedString");
		password = (String)decryptedUserPassword.getValue("decryptedString");

		Result loginResult = loginService.logIn(userId, password, session); 
		if(!loginResult.isSuccess()) {
			model.addAttribute("errorMessage", loginResult.getValue("errorMessage"));
			return "loginFailure";
		}
		
		return "main";
	}

	private Result decryptString(PrivateKey privateKey, Model model, String decryptTargetString) {
		String decryptedString = null;
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			decryptedString = rsa.decryptRsa(decryptTargetString);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new Result(false, e.getMessage());
		}
		return new Result(true, new ResultValue("decryptedString", decryptedString));
	}

	private PrivateKey getPrivateKeyAndDestroyKey(HttpSession session) {
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		return privateKey;
	}

	private Result encryptPrepareProcess(HttpSession session, Model model) {
		try {
			EncryptRSA rsa = new EncryptRSA();
			session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
			model.addAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
			model.addAttribute("publicKeyExponent", rsa.getPublicKeyExponent());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			session.removeAttribute("__rsaPrivateKey__");
			model.addAttribute("errorMessage", e.getMessage());
			return new Result(false, e.getMessage());
		}
		return new Result(true);
	}
}