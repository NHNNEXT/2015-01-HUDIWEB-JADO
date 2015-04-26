package core.filter;

import jado.controller.Result;
import jado.controller.ResultValue;

import java.io.IOException;
import java.security.PrivateKey;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.fabric.Response;

import core.util.DecryptRSA;

/*
 *  @WebFilter(urlPatterns="/login")
 */
public class RSADecryptFilter implements Filter {
	
	private FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String method = request.getMethod();
		
		Result decryptedUserId = null;
		Result decryptedUserPassword = null;
		PrivateKey privateKey = null;
		String userId = null;
		String password = null;
		
		if (method != null && !"".equals(method)) {
			HttpSession session = request.getSession();
		
			// decrypt
			if ("POST".equals(method.toUpperCase())) {
				userId = (String) req.getAttribute("idEncryption");
				password = (String) req.getAttribute("pwEncryption");
			
				privateKey = getPrivateKeyAndDestroyKey(session);
				decryptedUserId = decryptString(privateKey, userId);
				decryptedUserPassword = decryptString(privateKey, password);
				
				if(!(decryptedUserId.isSuccess() && decryptedUserPassword.isSuccess())){
					// 이 부분은 조금 더 찾아보세요
					response.sendError(0, "error");
				}
				
				request.setAttribute("userId", decryptedUserId.getValue("decryptedString"));
				request.setAttribute("password", decryptedUserId.getValue("decryptedString"));
			}
			// encrypt
			else if ("GET".equals(method.toUpperCase())) {
				// TODO encrypting logic
			}
		}
	}
	
	@Override
	public void destroy() {
		// do-nothing
	}
	
	private Result decryptString(PrivateKey privateKey, String decryptTargetString) {
		String decryptedString = null;
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			decryptedString = rsa.decryptRsa(decryptTargetString);
		} catch (Exception e) {
			return new Result(false, e.getMessage());
		}
		return new Result(true, new ResultValue("decryptedString", decryptedString));
	}

	private PrivateKey getPrivateKeyAndDestroyKey(HttpSession session) {
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		return privateKey;
	}
	

}
