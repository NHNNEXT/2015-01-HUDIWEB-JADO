package core.mail;


import java.util.Map;

import core.mail.template.MailTemplate;


public class Mail {
	
	String mailRecipient;
	String mailSubject;
	String mailBody;
	
	public Mail(Map<String, Object> mailParameterMap, MailTemplate template) {
		this.mailRecipient = (String)mailParameterMap.get("mailRecipient");
		this.mailSubject = template.getSubject();
		this.mailBody = template.getBody(mailParameterMap);
	}

	public String getMailRecipient() {
		return mailRecipient;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailRecipient(String mailRecipient) {
		this.mailRecipient = mailRecipient;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	
}
