package iuh.fit.se.finalprojectwww.service;

import iuh.fit.se.finalprojectwww.dto.RegisterDTO;
import iuh.fit.se.finalprojectwww.dto.UserDTO;
import iuh.fit.se.finalprojectwww.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    
    /**
     * Tạo user mới từ RegisterDTO
     */
    User createUser(RegisterDTO registerDTO);
    
    /**
     * Tìm user theo ID
     */
    Optional<User> findById(Long id);
    
    /**
     * Tìm user theo email
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Kiểm tra email đã tồn tại chưa
     */
    boolean existsByEmail(String email);
    
    /**
     * Cập nhật thông tin user
     */
    User updateUser(Long id, UserDTO userDTO);
    
    /**
     * Cập nhật profile của user hiện tại
     */
    User updateProfile(String email, UserDTO userDTO);
    
    /**
     * Thay đổi mật khẩu
     */
    boolean changePassword(String email, String oldPassword, String newPassword);
    
    /**
     * Xóa user (soft delete)
     */
    boolean deleteUser(Long id);
    
    /**
     * Kích hoạt/vô hiệu hóa user
     */
    User toggleUserStatus(Long id);
    
    /**
     * Lấy danh sách tất cả user với phân trang
     */
    Page<UserDTO> getAllUsers(Pageable pageable);
    
    /**
     * Tìm kiếm user theo từ khóa
     */
    Page<UserDTO> searchUsers(String search, Pageable pageable);
    
    /**
     * Lấy danh sách user theo role
     */
    Page<UserDTO> getUsersByRole(User.Role role, Pageable pageable);
    
    /**
     * Lấy danh sách user đã có đơn hàng
     */
    Page<UserDTO> getUsersWithOrders(Pageable pageable);
    
    /**
     * Lấy danh sách user chưa có đơn hàng
     */
    Page<UserDTO> getUsersWithoutOrders(Pageable pageable);
    
    /**
     * Đếm số user theo role
     */
    long countUsersByRole(User.Role role);
    
    /**
     * Đếm tổng số user active
     */
    long countActiveUsers();
    
    /**
     * Gửi email xác nhận đăng ký
     */
    void sendRegistrationEmail(User user);
    
    /**
     * Xác thực mật khẩu
     */
    boolean verifyPassword(String rawPassword, String encodedPassword);
    
    /**
     * Mã hóa mật khẩu
     */
    String encodePassword(String rawPassword);
}