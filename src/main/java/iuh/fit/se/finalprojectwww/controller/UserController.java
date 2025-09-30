package iuh.fit.se.finalprojectwww.controller;

import iuh.fit.se.finalprojectwww.dto.UserDTO;
import iuh.fit.se.finalprojectwww.model.User;
import iuh.fit.se.finalprojectwww.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    
    private final UserService userService;
    
    /**
     * Hiển thị trang profile của user
     */
    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        model.addAttribute("user", new UserDTO(user));
        return "user/profile";
    }
    
    /**
     * Hiển thị form chỉnh sửa profile
     */
    @GetMapping("/profile/edit")
    public String showEditProfile(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        model.addAttribute("userDTO", new UserDTO(user));
        return "user/edit-profile";
    }
    
    /**
     * Cập nhật profile
     */
    @PostMapping("/profile/edit")
    public String updateProfile(@Valid @ModelAttribute UserDTO userDTO,
                               BindingResult bindingResult,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            return "user/edit-profile";
        }
        
        try {
            String email = authentication.getName();
            userService.updateProfile(email, userDTO);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thông tin thành công!");
            return "redirect:/user/profile";
            
        } catch (Exception e) {
            log.error("Error updating profile for user: {}", authentication.getName(), e);
            redirectAttributes.addFlashAttribute("error", "Cập nhật thông tin thất bại!");
            return "redirect:/user/profile/edit";
        }
    }
    
    /**
     * Hiển thị form đổi mật khẩu
     */
    @GetMapping("/change-password")
    public String showChangePassword(Model model) {
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "user/change-password";
    }
    
    /**
     * Xử lý đổi mật khẩu
     */
    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute ChangePasswordForm changePasswordForm,
                                BindingResult bindingResult,
                                Authentication authentication,
                                RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            return "user/change-password";
        }
        
        // Kiểm tra mật khẩu mới và xác nhận
        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Mật khẩu xác nhận không khớp");
            return "user/change-password";
        }
        
        try {
            String email = authentication.getName();
            userService.changePassword(email, changePasswordForm.getOldPassword(), changePasswordForm.getNewPassword());
            redirectAttributes.addFlashAttribute("success", "Đổi mật khẩu thành công!");
            return "redirect:/user/profile";
            
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/user/change-password";
        } catch (Exception e) {
            log.error("Error changing password for user: {}", authentication.getName(), e);
            redirectAttributes.addFlashAttribute("error", "Đổi mật khẩu thất bại!");
            return "redirect:/user/change-password";
        }
    }
    
    /**
     * Inner class cho form đổi mật khẩu
     */
    public static class ChangePasswordForm {
        private String oldPassword;
        private String newPassword;
        private String confirmPassword;
        
        // Getters and setters
        public String getOldPassword() { return oldPassword; }
        public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }
        
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
        
        public String getConfirmPassword() { return confirmPassword; }
        public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    }
}