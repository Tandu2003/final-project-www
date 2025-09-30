package iuh.fit.se.finalprojectwww.service.impl;

import iuh.fit.se.finalprojectwww.dto.RegisterDTO;
import iuh.fit.se.finalprojectwww.dto.UserDTO;
import iuh.fit.se.finalprojectwww.model.User;
import iuh.fit.se.finalprojectwww.repository.UserRepository;
import iuh.fit.se.finalprojectwww.service.EmailService;
import iuh.fit.se.finalprojectwww.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    
    @Override
    public User createUser(RegisterDTO registerDTO) {
        // Kiểm tra email đã tồn tại
        if (existsByEmail(registerDTO.getEmail())) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }
        
        // Kiểm tra mật khẩu xác nhận
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Mật khẩu xác nhận không khớp");
        }
        
        // Tạo user mới
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encodePassword(registerDTO.getPassword()));
        user.setFullName(registerDTO.getFullName());
        user.setPhone(registerDTO.getPhone());
        user.setAddress(registerDTO.getAddress());
        user.setRole(User.Role.USER);
        user.setIsActive(true);
        
        User savedUser = userRepository.save(user);
        
        // Gửi email xác nhận
        try {
            sendRegistrationEmail(savedUser);
        } catch (Exception e) {
            log.error("Failed to send registration email to: {}", savedUser.getEmail(), e);
        }
        
        log.info("User created successfully: {}", savedUser.getEmail());
        return savedUser;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        // Cập nhật thông tin
        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        
        // Admin có thể thay đổi role và trạng thái
        if (userDTO.getRole() != null) {
            user.setRole(userDTO.getRole());
        }
        if (userDTO.getIsActive() != null) {
            user.setIsActive(userDTO.getIsActive());
        }
        
        User updatedUser = userRepository.save(user);
        log.info("User updated successfully: {}", updatedUser.getEmail());
        return updatedUser;
    }
    
    @Override
    public User updateProfile(String email, UserDTO userDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        
        User updatedUser = userRepository.save(user);
        log.info("User profile updated successfully: {}", updatedUser.getEmail());
        return updatedUser;
    }
    
    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        // Xác thực mật khẩu cũ
        if (!verifyPassword(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu cũ không đúng");
        }
        
        // Cập nhật mật khẩu mới
        user.setPassword(encodePassword(newPassword));
        userRepository.save(user);
        
        log.info("Password changed successfully for user: {}", email);
        return true;
    }
    
    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        // Kiểm tra user có đơn hàng không
        // TODO: Implement check for orders
        
        // Soft delete - chỉ vô hiệu hóa user
        user.setIsActive(false);
        userRepository.save(user);
        
        log.info("User deactivated successfully: {}", user.getEmail());
        return true;
    }
    
    @Override
    public User toggleUserStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        
        user.setIsActive(!user.getIsActive());
        User updatedUser = userRepository.save(user);
        
        log.info("User status toggled successfully: {} - Active: {}", 
                updatedUser.getEmail(), updatedUser.getIsActive());
        return updatedUser;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserDTO::new);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> searchUsers(String search, Pageable pageable) {
        return userRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, pageable)
                .map(UserDTO::new);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getUsersByRole(User.Role role, Pageable pageable) {
        return userRepository.findByRole(role, pageable)
                .map(UserDTO::new);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getUsersWithOrders(Pageable pageable) {
        return userRepository.findUsersWithOrders(pageable)
                .map(UserDTO::new);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getUsersWithoutOrders(Pageable pageable) {
        return userRepository.findUsersWithoutOrders(pageable)
                .map(UserDTO::new);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long countUsersByRole(User.Role role) {
        return userRepository.countByRole(role);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long countActiveUsers() {
        return userRepository.countByIsActiveTrue();
    }
    
    @Override
    public void sendRegistrationEmail(User user) {
        emailService.sendRegistrationConfirmation(user);
    }
    
    @Override
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}