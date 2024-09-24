package helper;
import java.io.UnsupportedEncodingException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailSMTP {
    public static String getOTP() {
        int min = 100000;
        int max = 999999;
        return Integer.toString((int) ((Math.random() * (max - min)) + min));
    }
    
    public static void sendOTP(String ten,String emailTo, String otp) throws UnsupportedEncodingException {
        StringBuilder sbb = new StringBuilder();
        sbb.append("<section style=\"display: flex; justify-content: center; align-items: center;\">")
           .append("<div style=\"margin: 0; padding: 0; border: 2px dashed #0984e3; border-radius: 15px; margin-left: auto; margin-right: auto;\">")
           .append("<div style=\"position: relative; align-items: center; justify-content: center; padding: 20px;\">")
           .append("<img src=\"https://bit.ly/logoImg\" width=\"250\" style=\"display: block; margin-left: auto; margin-right: auto;\" alt=\"\">")
           .append("<div style=\"font-size: 45px; font-weight: 650; margin-top: 20px; margin-bottom: 20px; color:#2d3436; font-family:'Roboto', sans-serif; text-align: center;\">Your verify code is</div>")
           .append("<div style=\"font-size: 60px; color: #0984e3; font-weight: 800; font-family:'Roboto', sans-serif; margin-bottom: 20px; text-align: center;\">")
           .append(otp).append("</div>")
           .append("<p style=\"font-size: 20px; font-weight: 600; color:#2d3436; font-family:'Roboto', sans-serif; padding: 5px; text-align: center;\">Hello, ").append(ten).append("</p>")
           .append("<p style=\"font-size: 20px; font-weight: 600; color:#2d3436; font-family:'Roboto', sans-serif; padding: 5px; text-align: center;\">Please, return to the forgot page and insert the code above to verify your identity.</p>")
           .append("<p style=\"font-size: 20px; font-weight: 600; color:#2d3436; font-family:'Roboto', sans-serif; padding: 5px; text-align: center;\">FROM</p>")
           .append("<p style=\"font-size: 25px; font-weight: 700; color:#2d3436; font-family:'Roboto', sans-serif; text-align: center;\">TOANDICODEDAO</p>")
           .append("<img src=\"https://bit.ly/imgBtom\" width=\"750\" style=\"display: block; margin-left: auto; margin-right: auto;\" alt=\"\">")
           .append("</div></div></section>");
            String username = "pvt8626@gmail.com";
            String password = "gvqeaoegprsxxnkv";
            String subject = "Bạn đang gửi yêu cầu đặt lại mật khẩu";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        String message = sbb.toString();
       try {
        Session mailSession = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(username, "ToanPhan"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
        msg.setSubject(subject);
        msg.setContent(message, "text/html; charset=utf-8");
        Transport.send(msg);
        
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
