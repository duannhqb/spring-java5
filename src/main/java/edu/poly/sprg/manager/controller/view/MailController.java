package edu.poly.sprg.manager.controller.view;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

import edu.poly.sprg.manager.entity.Staffs;

@Controller
public class MailController {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(boolean check, Staffs staff) throws MessagingException {
	    Locale locale = LocaleContextHolder.getLocale();

	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
	    
	    String message = "";
	    if(locale== new Locale("vi", "VN")) {
	    	message = "Xin chào " + staff.getName() + " !";
	    	message += "Đây là email thông báo về việc ghi nhận thành tích và kỷ luật của bạn trong đợt này";
	    	message += "Tổng thành tích : " + "";
	    	message += "Tổng kỷ luật : " + "";
	    	message += "Điểm thưởng của bạn là : ";
	    	message += "Chúng tôi hi vọng bạn sẽ cố gắng hơn nữa để phát huy điểm mạnh và khắc phục điểm yếu nữa nhé!";
	    	message += "Cảm ơn !";
	    }
	    if (locale == null) {
            locale = Locale.ENGLISH;
        }
	    
	    mimeMessageHelper.setSubject("not reply");
	    mimeMessageHelper.setTo(staff.getEmail());
	    mimeMessageHelper.setText(message);
	    // Send email
	    mailSender.send(mimeMessage);
	  }

	
}
