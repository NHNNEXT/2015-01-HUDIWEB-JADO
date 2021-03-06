package core.mail.template;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailTemplateStorage {
	
	@Autowired
	private JoinVerifyMail joinVerifyMail;
	@Autowired
	private JoinVerifyResendMail joinVerfJoinVerifyResendMail;
	@Autowired
	private JoinWelcomeMail joinWelcomeMail;
	@Autowired
	private PayInfoMail payInfoMail;
	@Autowired
	private DeleteUserMail deleteUserMail;
	
	private  Map<Type, AbstractMailTemplate> templates = new HashMap<>();
	
	public static enum Type {
		// Join Type
		JOIN_VERIFY(1), JOIN_WELCOME(2), JOIN_VERIFY_RESEND(5),

		// Pay Info
		PAY_INFO(3), 
		
		// 회원 탈퇴시
		DELETE_USER(4);
		
		// 이곳에 새로운 타입의 메일들의 상수를 추가하면 됨
		// Ex) BUY_VERIFY(3), BUY_RECEIPT(4)
		
		private int value;

		private Type(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	@PostConstruct
	public void initialize() {
		templates.put(Type.JOIN_VERIFY, joinVerifyMail);
		templates.put(Type.JOIN_WELCOME, joinWelcomeMail);
		templates.put(Type.PAY_INFO, payInfoMail);
		templates.put(Type.DELETE_USER, deleteUserMail);
		templates.put(Type.JOIN_VERIFY_RESEND, joinVerfJoinVerifyResendMail);
	}

	public AbstractMailTemplate getTemplate(Type joinVerify) {
		return templates.get(joinVerify);
	}
}
