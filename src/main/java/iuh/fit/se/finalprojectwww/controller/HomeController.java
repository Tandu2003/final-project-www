package iuh.fit.se.finalprojectwww.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("userEmail", authentication.getName());
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        
        return "home/index";
    }
    
    @GetMapping("/home")
    public String homeRedirect() {
        return "redirect:/";
    }
}