package edu.poly.sprg.manager.service;

import javax.mail.MessagingException;

import edu.poly.sprg.manager.entity.Staffs;

public interface MailService {
	void sendEmail(boolean check, Staffs staff, String reason) throws MessagingException;
}
