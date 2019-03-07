package edu.poly.sprg.manager.service.impl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import edu.poly.sprg.manager.entity.Staffs;
import edu.poly.sprg.manager.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(boolean check, Staffs staff, String reason) throws MessagingException {
		Locale locale = LocaleContextHolder.getLocale();

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

		String message = "";
		if (locale == new Locale("vi", "VN")) {
			message = "Xin chào " + staff.getName() + " !";
			message += "\n Đây là email thông báo về việc ghi nhận thành tích và kỷ luật của bạn trong đợt này";
			if (check == true) { // thành tích = true = 1
				message += "\n Bạn vừa nhận được một thành tích tốt trong đợt đánh giá này!";
			} else {
				message += "\n Bạn vừa nhận được một điểm kỷ luật, lý do : " + reason;
			}
			message += "\n Chúng tôi hy vọng bạn sẽ cố gắng hơn nữa để phát huy điểm mạnh và khắc phục điểm yếu nữa nhé!";
			message += "\n Cảm ơn !";
		}
		if (locale == null || locale == Locale.ENGLISH) {
			message = "Hi " + staff.getName() + " !";
			message += "\n This is a notification email about your record of achievement and discipline in this round.";
			if (check == true) { // thành tích = true = 1
				message += "\n You just received a good record in this review!";
			} else {
				message += "\n You just received a discipline point, the reason : " + reason;
			}
			message += "\n We hope you will try harder to promote your strengths and overcome weaknesses!";
			message += "\n Thanks!";
		}

		mimeMessageHelper.setSubject("not reply");
		mimeMessageHelper.setTo(staff.getEmail());
		mimeMessageHelper.setText(message);
		// Send email
		mailSender.send(mimeMessage);
	}
}
