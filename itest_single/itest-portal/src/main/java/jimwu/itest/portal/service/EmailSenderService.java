package jimwu.itest.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,String resetLink)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("jimwu4web@gmail.com","GameWeb");
        helper.setTo(toEmail);

        String content = "<p>hello</p>" +
                "<p>You have requested to reset your password</p>" +
                "<p>click the link below to change your password</p>" +
                "<p><b><a href=\"" + resetLink + "\">Change my password</a></b></p>" +
                "<p>igonre this email if you do remember your password , or you have not made the request</p>";

        helper.setSubject("Here's link to reset your password");
        helper.setText(content,true);


        mailSender.send(message);

        System.out.println("mail send successfully");
    }
}
