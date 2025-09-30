package iuh.fit.se.finalprojectwww.service.impl;

import iuh.fit.se.finalprojectwww.model.User;
import iuh.fit.se.finalprojectwww.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;
    
    @Override
    public void sendRegistrationConfirmation(User user) {
        try {
            Context context = new Context();
            context.setVariable("user", user);
            context.setVariable("baseUrl", baseUrl);
            
            String htmlContent = templateEngine.process("email/registration-confirmation", context);
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Chào mừng bạn đến với Website Bán Thiệp Trực Tuyến!");
            helper.setText(htmlContent, true);
            
            mailSender.send(message);
            log.info("Registration confirmation email sent to: {}", user.getEmail());
            
        } catch (MessagingException e) {
            log.error("Failed to send registration confirmation email to: {}", user.getEmail(), e);
        }
    }
    
    @Override
    public void sendOrderConfirmation(String userEmail, String orderDetails) {
        try {
            Context context = new Context();
            context.setVariable("userEmail", userEmail);
            context.setVariable("orderDetails", orderDetails);
            context.setVariable("baseUrl", baseUrl);
            
            String htmlContent = templateEngine.process("email/order-confirmation", context);
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(userEmail);
            helper.setSubject("Xác nhận đơn hàng - Website Bán Thiệp Trực Tuyến");
            helper.setText(htmlContent, true);
            
            mailSender.send(message);
            log.info("Order confirmation email sent to: {}", userEmail);
            
        } catch (MessagingException e) {
            log.error("Failed to send order confirmation email to: {}", userEmail, e);
        }
    }
    
    @Override
    public void sendPasswordResetEmail(String userEmail, String resetToken) {
        try {
            Context context = new Context();
            context.setVariable("userEmail", userEmail);
            context.setVariable("resetToken", resetToken);
            context.setVariable("baseUrl", baseUrl);
            
            String htmlContent = templateEngine.process("email/password-reset", context);
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(userEmail);
            helper.setSubject("Đặt lại mật khẩu - Website Bán Thiệp Trực Tuyến");
            helper.setText(htmlContent, true);
            
            mailSender.send(message);
            log.info("Password reset email sent to: {}", userEmail);
            
        } catch (MessagingException e) {
            log.error("Failed to send password reset email to: {}", userEmail, e);
        }
    }
    
    @Override
    public void sendNotificationEmail(String userEmail, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(userEmail);
            message.setSubject(subject);
            message.setText(content);
            
            mailSender.send(message);
            log.info("Notification email sent to: {}", userEmail);
            
        } catch (Exception e) {
            log.error("Failed to send notification email to: {}", userEmail, e);
        }
    }
    
    @Override
    public void sendTestEmail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            
            mailSender.send(message);
            log.info("Test email sent to: {}", to);
            
        } catch (Exception e) {
            log.error("Failed to send test email to: {}", to, e);
        }
    }
}