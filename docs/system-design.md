# Thiết Kế Hệ Thống - Website Bán Thiệp Trực Tuyến

## 🏗️ Kiến Trúc Tổng Thể

### Mô Hình Kiến Trúc
Website bán thiệp trực tuyến được thiết kế theo mô hình **MVC (Model-View-Controller)** với Spring Boot framework, đảm bảo tính modular, scalable và maintainable.

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Presentation  │    │   Business      │    │   Data Access   │
│     Layer       │    │     Layer       │    │     Layer       │
│                 │    │                 │    │                 │
│  Controllers    │◄──►│    Services     │◄──►│  Repositories   │
│  Thymeleaf      │    │   Validators    │    │   Entities      │
│  Static Files   │    │   DTOs          │    │   Database      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🛠️ Công Nghệ Stack

### Backend Technologies
- **Spring Boot 3.5.6**: Main framework
- **Spring MVC**: Web layer
- **Spring Data JPA**: Data access layer
- **Spring Security**: Authentication & authorization
- **Spring Mail**: Email services
- **Spring Validation**: Input validation
- **Thymeleaf**: Template engine

### Database
- **PostgreSQL**: Primary database
- **JPA/Hibernate**: ORM framework
- **Flyway**: Database migration

### Frontend Technologies
- **Thymeleaf**: Server-side templating
- **Bootstrap 5**: CSS framework
- **jQuery**: JavaScript library
- **Font Awesome**: Icons

### Development Tools
- **Maven**: Build tool
- **Lombok**: Code generation
- **Spotless**: Code formatting
- **Spring Boot DevTools**: Development utilities

## 📁 Cấu Trúc Package

```
iuh.fit.se.finalprojectwww/
├── config/                 # Configuration classes
│   ├── SecurityConfig.java
│   ├── WebConfig.java
│   └── DatabaseConfig.java
├── controller/             # Web controllers
│   ├── HomeController.java
│   ├── ProductController.java
│   ├── CartController.java
│   ├── OrderController.java
│   ├── UserController.java
│   └── AdminController.java
├── service/                # Business logic
│   ├── ProductService.java
│   ├── CartService.java
│   ├── OrderService.java
│   ├── UserService.java
│   ├── EmailService.java
│   └── impl/               # Service implementations
├── repository/             # Data access
│   ├── ProductRepository.java
│   ├── CategoryRepository.java
│   ├── UserRepository.java
│   ├── OrderRepository.java
│   └── OrderItemRepository.java
├── model/                  # Entity models
│   ├── Product.java
│   ├── Category.java
│   ├── User.java
│   ├── Order.java
│   └── OrderItem.java
├── dto/                    # Data Transfer Objects
│   ├── ProductDTO.java
│   ├── UserDTO.java
│   ├── OrderDTO.java
│   └── CartItemDTO.java
├── util/                   # Utility classes
│   ├── EmailUtil.java
│   ├── FileUtil.java
│   └── ValidationUtil.java
└── exception/              # Exception handling
    ├── GlobalExceptionHandler.java
    ├── ProductNotFoundException.java
    └── UserNotFoundException.java
```

## 🔄 Luồng Xử Lý Chính

### 1. Luồng Đăng Ký Người Dùng
```
User Registration Flow:
1. Guest → Register Form
2. Submit Registration Data
3. Validation (Client + Server)
4. Check Email Uniqueness
5. Save to Database
6. Send Confirmation Email
7. Redirect to Login Page
```

### 2. Luồng Mua Hàng
```
Shopping Flow:
1. Guest/Customer → Browse Products
2. Add Product to Cart (Session)
3. View Cart → Modify Quantities
4. Checkout (Login Required)
5. Process Payment
6. Create Order in Database
7. Send Confirmation Email
8. Clear Cart Session
9. Redirect to Order Success
```

### 3. Luồng Quản Lý Admin
```
Admin Management Flow:
1. Admin Login
2. Access Admin Dashboard
3. Manage Products/Categories
4. Manage Users
5. Manage Orders
6. View Reports/Statistics
```

## 🔐 Bảo Mật Hệ Thống

### Authentication & Authorization
- **Spring Security**: Framework chính
- **Session-based Authentication**: Quản lý phiên đăng nhập
- **Role-based Access Control**: Phân quyền theo vai trò
- **Password Encryption**: BCrypt hashing

### Security Layers
```
┌─────────────────────────────────────┐
│           Security Layer            │
├─────────────────────────────────────┤
│ 1. CSRF Protection                  │
│ 2. XSS Prevention                   │
│ 3. SQL Injection Prevention         │
│ 4. Input Validation                 │
│ 5. Session Management               │
│ 6. Password Encryption              │
└─────────────────────────────────────┘
```

### URL Security Mapping
- `/admin/**` → ROLE_ADMIN only
- `/user/**` → ROLE_USER, ROLE_ADMIN
- `/public/**` → All users
- `/api/**` → REST API endpoints

## 📊 Data Flow Architecture

### Request Processing Flow
```
HTTP Request → DispatcherServlet → Controller → Service → Repository → Database
                     ↓
HTTP Response ← ViewResolver ← Thymeleaf ← Service ← Controller ← Model
```

### Session Management
- **Cart Data**: Stored in HTTP Session
- **User Authentication**: Spring Security Session
- **Shopping Cart**: Session-scoped beans
- **Session Timeout**: Configurable (30 minutes default)

## 🗄️ Database Design Pattern

### Repository Pattern
- **JPA Repository**: Spring Data JPA interfaces
- **Custom Queries**: @Query annotations
- **Pagination**: Pageable support
- **Sorting**: Sort parameter support

### Entity Relationships
```
User (1) ←→ (N) Order (1) ←→ (N) OrderItem (N) ←→ (1) Product (N) ←→ (1) Category
```

## 🎨 Frontend Architecture

### Template Structure
```
templates/
├── layout/
│   ├── base.html          # Base template
│   ├── header.html        # Header component
│   └── footer.html        # Footer component
├── home/
│   └── index.html         # Home page
├── product/
│   ├── list.html          # Product listing
│   └── detail.html        # Product detail
├── cart/
│   └── view.html          # Shopping cart
├── order/
│   ├── checkout.html      # Checkout page
│   └── success.html       # Order success
├── user/
│   ├── login.html         # Login page
│   ├── register.html      # Registration page
│   └── profile.html       # User profile
└── admin/
    ├── dashboard.html     # Admin dashboard
    ├── products.html      # Product management
    ├── users.html         # User management
    └── orders.html        # Order management
```

### Static Resources Organization
```
static/
├── css/
│   ├── bootstrap.min.css
│   ├── custom.css
│   └── admin.css
├── js/
│   ├── jquery.min.js
│   ├── bootstrap.min.js
│   ├── custom.js
│   └── cart.js
├── images/
│   ├── products/
│   ├── icons/
│   └── logos/
└── fonts/
    └── fontawesome/
```

## 📧 Email Service Architecture

### Email Templates
- **Registration Confirmation**: Welcome email
- **Order Confirmation**: Order details
- **Password Reset**: Security email
- **Newsletter**: Marketing email

### Email Configuration
```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${EMAIL_USERNAME}
    password: ${EMAIL_PASSWORD}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

## 🔧 Configuration Management

### Application Properties
```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/greeting_cards_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Thymeleaf Configuration
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

## 🚀 Performance Optimization

### Caching Strategy
- **Product List**: Redis cache (5 minutes)
- **Category List**: Application cache
- **Static Resources**: Browser cache
- **Database Queries**: Query optimization

### Database Optimization
- **Indexes**: On frequently queried columns
- **Connection Pooling**: HikariCP
- **Query Optimization**: N+1 problem prevention
- **Pagination**: For large datasets

## 📱 Responsive Design Strategy

### Breakpoints
- **Mobile**: 320px - 767px
- **Tablet**: 768px - 1023px
- **Desktop**: 1024px+

### Progressive Enhancement
- **Base HTML**: Semantic markup
- **CSS**: Responsive styles
- **JavaScript**: Enhanced functionality
- **Accessibility**: WCAG 2.1 compliance

## 🔄 Error Handling Strategy

### Exception Hierarchy
```
RuntimeException
├── BusinessException
│   ├── ProductNotFoundException
│   ├── UserNotFoundException
│   └── OrderNotFoundException
├── ValidationException
└── SystemException
```

### Error Response Format
```json
{
  "timestamp": "2024-01-01T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Product not found",
  "path": "/api/products/999"
}
```

## 📈 Monitoring & Logging

### Logging Strategy
- **Application Logs**: Logback configuration
- **Access Logs**: Request/response logging
- **Error Logs**: Exception tracking
- **Performance Logs**: Response time monitoring

### Health Checks
- **Database Connection**: Health endpoint
- **Email Service**: SMTP connectivity
- **File System**: Upload directory access
- **External Services**: API dependencies