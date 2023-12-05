package SMTP_Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    private String userName ;
    private String password ;
    public SendMail(String userName, String password){
        this.userName=userName;
        this.password=password;
    }
    public void SendVerificationMail(String toEmail,String verificationCode) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.debug", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Email Verification");
            message.setText("verify code<h1>\nYour verification code is: " + verificationCode);
            Transport.send(message);
        } catch (AuthenticationFailedException e) {
            // Handle authentication failure
            e.printStackTrace();
        } catch (MessagingException e) {
            // Handle other messaging exceptions
            e.printStackTrace();
        }

    }
}
