package iuh.fit.se.finalprojectwww.service;

import iuh.fit.se.finalprojectwww.model.User;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    
    /**
     * Gửi email xác nhận đăng ký
     */
    void sendRegistrationConfirmation(User user);
    
    /**
     * Gửi email xác nhận đơn hàng
     */
    void sendOrderConfirmation(String userEmail, String orderDetails);
    
    /**
     * Gửi email reset mật khẩu
     */
    void sendPasswordResetEmail(String userEmail, String resetToken);
    
    /**
     * Gửi email thông báo chung
     */
    void sendNotificationEmail(String userEmail, String subject, String content);
    
    /**
     * Gửi email test
     */
    void sendTestEmail(String to, String subject, String content);
}