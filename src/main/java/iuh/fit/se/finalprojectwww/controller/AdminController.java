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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    
    private final UserService userService;
    
    /**
     * Dashboard admin
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Thống kê cơ bản
        long totalUsers = userService.countActiveUsers();
        long adminUsers = userService.countUsersByRole(User.Role.ADMIN);
        long regularUsers = userService.countUsersByRole(User.Role.USER);
        
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("adminUsers", adminUsers);
        model.addAttribute("regularUsers", regularUsers);
        
        return "admin/dashboard";
    }
    
    /**
     * Danh sách tất cả user
     */
    @GetMapping("/users")
    public String listUsers(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "createdAt") String sortBy,
                           @RequestParam(defaultValue = "desc") String sortDir,
                           @RequestParam(required = false) String search,
                           @RequestParam(required = false) String role,
                           Model model) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<UserDTO> users;
        
        if (search != null && !search.trim().isEmpty()) {
            users = userService.searchUsers(search, pageable);
        } else if (role != null && !role.trim().isEmpty()) {
            User.Role userRole = User.Role.valueOf(role.toUpperCase());
            users = userService.getUsersByRole(userRole, pageable);
        } else {
            users = userService.getAllUsers(pageable);
        }
        
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("totalElements", users.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("search", search);
        model.addAttribute("role", role);
        
        return "admin/users";
    }
    
    /**
     * Chi tiết user
     */
    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        model.addAttribute("user", new UserDTO(user));
        return "admin/user-detail";
    }
    
    /**
     * Form chỉnh sửa user
     */
    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        model.addAttribute("userDTO", new UserDTO(user));
        return "admin/edit-user";
    }
    
    /**
     * Cập nhật user
     */
    @PostMapping("/users/{id}/edit")
    public String updateUser(@PathVariable Long id,
                            @Valid @ModelAttribute UserDTO userDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            return "admin/edit-user";
        }
        
        try {
            userService.updateUser(id, userDTO);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thông tin user thành công!");
            return "redirect:/admin/users/" + id;
            
        } catch (Exception e) {
            log.error("Error updating user: {}", id, e);
            redirectAttributes.addFlashAttribute("error", "Cập nhật thông tin user thất bại!");
            return "redirect:/admin/users/" + id + "/edit";
        }
    }
    
    /**
     * Toggle trạng thái user (active/inactive)
     */
    @PostMapping("/users/{id}/toggle-status")
    public String toggleUserStatus(@PathVariable Long id,
                                  RedirectAttributes redirectAttributes) {
        try {
            User user = userService.toggleUserStatus(id);
            String message = user.getIsActive() ? "Kích hoạt user thành công!" : "Vô hiệu hóa user thành công!";
            redirectAttributes.addFlashAttribute("success", message);
            
        } catch (Exception e) {
            log.error("Error toggling user status: {}", id, e);
            redirectAttributes.addFlashAttribute("error", "Thay đổi trạng thái user thất bại!");
        }
        
        return "redirect:/admin/users/" + id;
    }
    
    /**
     * Xóa user (soft delete)
     */
    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id,
                            RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("success", "Xóa user thành công!");
            
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            log.error("Error deleting user: {}", id, e);
            redirectAttributes.addFlashAttribute("error", "Xóa user thất bại!");
        }
        
        return "redirect:/admin/users";
    }
    
    /**
     * Danh sách user có đơn hàng
     */
    @GetMapping("/users/with-orders")
    public String usersWithOrders(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<UserDTO> users = userService.getUsersWithOrders(pageable);
        
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("totalElements", users.getTotalElements());
        model.addAttribute("title", "Users with Orders");
        
        return "admin/users";
    }
    
    /**
     * Danh sách user chưa có đơn hàng
     */
    @GetMapping("/users/without-orders")
    public String usersWithoutOrders(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<UserDTO> users = userService.getUsersWithoutOrders(pageable);
        
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("totalElements", users.getTotalElements());
        model.addAttribute("title", "Users without Orders");
        
        return "admin/users";
    }
}