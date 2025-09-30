package iuh.fit.se.finalprojectwww.repository;

import iuh.fit.se.finalprojectwww.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Tìm user theo email
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Kiểm tra email đã tồn tại chưa
     */
    boolean existsByEmail(String email);
    
    /**
     * Tìm user theo email và trạng thái active
     */
    Optional<User> findByEmailAndIsActiveTrue(String email);
    
    /**
     * Tìm kiếm user theo tên hoặc email
     */
    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.fullName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<User> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            @Param("search") String search, Pageable pageable);
    
    /**
     * Tìm user theo role
     */
    Page<User> findByRole(User.Role role, Pageable pageable);
    
    /**
     * Tìm user theo trạng thái active
     */
    Page<User> findByIsActive(Boolean isActive, Pageable pageable);
    
    /**
     * Đếm số user theo role
     */
    long countByRole(User.Role role);
    
    /**
     * Đếm số user active
     */
    long countByIsActiveTrue();
    
    /**
     * Tìm user đã có đơn hàng
     */
    @Query("SELECT DISTINCT u FROM User u JOIN u.orders o")
    Page<User> findUsersWithOrders(Pageable pageable);
    
    /**
     * Tìm user chưa có đơn hàng
     */
    @Query("SELECT u FROM User u WHERE u.id NOT IN " +
           "(SELECT DISTINCT o.user.id FROM Order o WHERE o.user.id IS NOT NULL)")
    Page<User> findUsersWithoutOrders(Pageable pageable);
}