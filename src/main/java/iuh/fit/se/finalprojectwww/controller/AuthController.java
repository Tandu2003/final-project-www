package iuh.fit.se.finalprojectwww.controller;

import iuh.fit.se.finalprojectwww.dto.LoginDTO;
import iuh.fit.se.finalprojectwww.dto.RegisterDTO;
import iuh.fit.se.finalprojectwww.model.User;
import iuh.fit.se.finalprojectwww.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    
    private final UserService userService;
    
    /**
     * Hiển thị trang đăng nhập
     */
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // Nếu user đã đăng nhập, redirect về trang chủ
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            return "redirect:/";
        }
        
        model.addAttribute("loginDTO", new LoginDTO());
        return "auth/login";
    }
    
    /**
     * Xử lý đăng nhập
     */
    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute LoginDTO loginDTO,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginDTO", loginDTO);
            return "auth/login";
        }
        
        try {
            // Kiểm tra user tồn tại
            User user = userService.findByEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Email hoặc mật khẩu không đúng"));
            
            // Kiểm tra user active
            if (!user.getIsActive()) {
                model.addAttribute("error", "Tài khoản đã bị vô hiệu hóa");
                model.addAttribute("loginDTO", loginDTO);
                return "auth/login";
            }
            
            // Kiểm tra mật khẩu
            if (!userService.verifyPassword(loginDTO.getPassword(), user.getPassword())) {
                model.addAttribute("error", "Email hoặc mật khẩu không đúng");
                model.addAttribute("loginDTO", loginDTO);
                return "auth/login";
            }
            
            // Đăng nhập thành công - Spring Security sẽ xử lý
            redirectAttributes.addFlashAttribute("success", "Đăng nhập thành công!");
            return "redirect:/";
            
        } catch (Exception e) {
            log.error("Login error for email: {}", loginDTO.getEmail(), e);
            model.addAttribute("error", "Đăng nhập thất bại. Vui lòng thử lại.");
            model.addAttribute("loginDTO", loginDTO);
            return "auth/login";
        }
    }
    
    /**
     * Hiển thị trang đăng ký
     */
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        // Nếu user đã đăng nhập, redirect về trang chủ
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            return "redirect:/";
        }
        
        model.addAttribute("registerDTO", new RegisterDTO());
        return "auth/register";
    }
    
    /**
     * Xử lý đăng ký
     */
    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute RegisterDTO registerDTO,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerDTO", registerDTO);
            return "auth/register";
        }
        
        // Kiểm tra điều khoản
        if (!registerDTO.isAgreeToTerms()) {
            model.addAttribute("error", "Vui lòng đồng ý với điều khoản sử dụng");
            model.addAttribute("registerDTO", registerDTO);
            return "auth/register";
        }
        
        try {
            User user = userService.createUser(registerDTO);
            redirectAttributes.addFlashAttribute("success", 
                    "Đăng ký thành công! Vui lòng kiểm tra email để xác nhận tài khoản.");
            return "redirect:/auth/login";
            
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("registerDTO", registerDTO);
            return "auth/register";
        } catch (Exception e) {
            log.error("Registration error for email: {}", registerDTO.getEmail(), e);
            model.addAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
            model.addAttribute("registerDTO", registerDTO);
            return "auth/register";
        }
    }
    
    /**
     * Đăng xuất
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login?logout=true";
    }
    
    /**
     * Xử lý đăng xuất GET request
     */
    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest request, HttpServletResponse response) {
        return logout(request, response);
    }
    
    /**
     * Trang thông báo đăng xuất
     */
    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất thành công!");
        return "auth/logout-success";
    }
    
    /**
     * Trang lỗi đăng nhập
     */
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.");
        model.addAttribute("loginDTO", new LoginDTO());
        return "auth/login";
    }
    
    /**
     * Trang lỗi truy cập
     */
    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("error", "Bạn không có quyền truy cập trang này.");
        return "auth/access-denied";
    }
}