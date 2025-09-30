# Hướng Dẫn Phát Triển - Website Bán Thiệp Trực Tuyến

## 🚀 Tổng Quan Phát Triển

Hướng dẫn này cung cấp thông tin chi tiết về cách thiết lập môi trường phát triển, cấu trúc dự án, và các quy trình phát triển cho website bán thiệp trực tuyến.

## 📋 Yêu Cầu Hệ Thống

### Phần Mềm Cần Thiết
- **Java**: JDK 17 hoặc cao hơn
- **Maven**: 3.6+ (để build và quản lý dependencies)
- **PostgreSQL**: 12+ (database)
- **Git**: 2.30+ (version control)
- **IDE**: IntelliJ IDEA, Eclipse, hoặc VS Code

### Công Cụ Khuyến Nghị
- **Postman**: Test API
- **pgAdmin**: Quản lý PostgreSQL
- **Docker**: Containerization (optional)
- **Node.js**: Frontend build tools (optional)

## 🛠️ Thiết Lập Môi Trường Phát Triển

### 1. Clone Repository
```bash
git clone https://github.com/your-username/final-project-www.git
cd final-project-www
```

### 2. Cấu Hình Database

#### Tạo Database PostgreSQL
```sql
-- Kết nối PostgreSQL với user postgres
CREATE DATABASE greeting_cards_db;
CREATE USER greeting_cards_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE greeting_cards_db TO greeting_cards_user;
```

#### Cấu Hình application.properties
```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/greeting_cards_db
spring.datasource.username=greeting_cards_user
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server Configuration
server.port=8080
server.servlet.context-path=/

# Thymeleaf Configuration
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Email Configuration (Development)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${EMAIL_USERNAME:your-email@gmail.com}
spring.mail.password=${EMAIL_PASSWORD:your-app-password}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Logging Configuration
logging.level.iuh.fit.se.finalprojectwww=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

### 3. Cài Đặt Dependencies
```bash
# Maven sẽ tự động download dependencies khi build
mvn clean install
```

### 4. Chạy Ứng Dụng
```bash
# Cách 1: Sử dụng Maven
mvn spring-boot:run

# Cách 2: Sử dụng JAR file
mvn clean package
java -jar target/final-project-www-0.0.1-SNAPSHOT.jar

# Cách 3: Chạy từ IDE
# Run FinalProjectWwwApplication.java
```

### 5. Kiểm Tra Ứng Dụng
- Truy cập: http://localhost:8080
- Kiểm tra database connection
- Test các endpoint cơ bản

## 📁 Cấu Trúc Dự Án Chi Tiết

### Package Structure
```
src/main/java/iuh/fit/se/finalprojectwww/
├── config/                     # Configuration classes
│   ├── SecurityConfig.java     # Spring Security configuration
│   ├── WebConfig.java          # Web MVC configuration
│   ├── DatabaseConfig.java     # Database configuration
│   └── EmailConfig.java        # Email configuration
├── controller/                 # Web controllers
│   ├── HomeController.java     # Home page controller
│   ├── ProductController.java  # Product management
│   ├── CartController.java     # Shopping cart
│   ├── OrderController.java    # Order processing
│   ├── UserController.java     # User management
│   ├── AuthController.java     # Authentication
│   └── AdminController.java    # Admin functions
├── service/                    # Business logic layer
│   ├── ProductService.java     # Product business logic
│   ├── CartService.java        # Cart management
│   ├── OrderService.java       # Order processing
│   ├── UserService.java        # User management
│   ├── EmailService.java       # Email functionality
│   ├── AuthService.java        # Authentication logic
│   └── impl/                   # Service implementations
│       ├── ProductServiceImpl.java
│       ├── CartServiceImpl.java
│       ├── OrderServiceImpl.java
│       ├── UserServiceImpl.java
│       ├── EmailServiceImpl.java
│       └── AuthServiceImpl.java
├── repository/                 # Data access layer
│   ├── ProductRepository.java  # Product data access
│   ├── CategoryRepository.java # Category data access
│   ├── UserRepository.java     # User data access
│   ├── OrderRepository.java    # Order data access
│   └── OrderItemRepository.java # Order item data access
├── model/                      # Entity models
│   ├── Product.java            # Product entity
│   ├── Category.java           # Category entity
│   ├── User.java               # User entity
│   ├── Order.java              # Order entity
│   └── OrderItem.java          # Order item entity
├── dto/                        # Data Transfer Objects
│   ├── ProductDTO.java         # Product data transfer
│   ├── UserDTO.java            # User data transfer
│   ├── OrderDTO.java           # Order data transfer
│   ├── CartItemDTO.java        # Cart item data transfer
│   └── LoginDTO.java           # Login data transfer
├── util/                       # Utility classes
│   ├── EmailUtil.java          # Email utilities
│   ├── FileUtil.java           # File handling utilities
│   ├── ValidationUtil.java     # Validation utilities
│   └── SecurityUtil.java       # Security utilities
├── exception/                  # Exception handling
│   ├── GlobalExceptionHandler.java # Global exception handler
│   ├── ProductNotFoundException.java # Product not found
│   ├── UserNotFoundException.java   # User not found
│   └── OrderNotFoundException.java  # Order not found
└── FinalProjectWwwApplication.java # Main application class
```

### Resource Structure
```
src/main/resources/
├── application.properties       # Application configuration
├── application-dev.properties # Development configuration
├── application-prod.properties # Production configuration
├── static/                     # Static resources
│   ├── css/                    # CSS files
│   │   ├── bootstrap.min.css
│   │   ├── custom.css
│   │   └── admin.css
│   ├── js/                     # JavaScript files
│   │   ├── jquery.min.js
│   │   ├── bootstrap.min.js
│   │   ├── custom.js
│   │   └── cart.js
│   ├── images/                 # Image files
│   │   ├── products/           # Product images
│   │   ├── categories/         # Category images
│   │   ├── icons/              # Icon images
│   │   └── logos/              # Logo images
│   └── fonts/                  # Font files
│       └── fontawesome/        # FontAwesome fonts
└── templates/                  # Thymeleaf templates
    ├── layout/                 # Layout templates
    │   ├── base.html           # Base template
    │   ├── header.html         # Header component
    │   └── footer.html         # Footer component
    ├── home/                   # Home page templates
    │   └── index.html          # Home page
    ├── product/                # Product templates
    │   ├── list.html           # Product listing
    │   └── detail.html         # Product detail
    ├── cart/                   # Cart templates
    │   └── view.html           # Shopping cart
    ├── order/                  # Order templates
    │   ├── checkout.html       # Checkout page
    │   └── success.html        # Order success
    ├── user/                   # User templates
    │   ├── login.html          # Login page
    │   ├── register.html       # Registration page
    │   └── profile.html        # User profile
    └── admin/                  # Admin templates
        ├── dashboard.html      # Admin dashboard
        ├── products.html       # Product management
        ├── users.html          # User management
        └── orders.html         # Order management
```

## 🔧 Cấu Hình Development

### IDE Configuration

#### IntelliJ IDEA
1. **Import Project**
   - File → Open → Chọn thư mục dự án
   - Chọn "Import project from external model" → Maven
   - Đợi Maven import dependencies

2. **Configure Database**
   - View → Tool Windows → Database
   - Add Data Source → PostgreSQL
   - Cấu hình connection details

3. **Run Configuration**
   - Run → Edit Configurations
   - Add New → Application
   - Main class: `iuh.fit.se.finalprojectwww.FinalProjectWwwApplication`
   - VM options: `-Dspring.profiles.active=dev`

#### Eclipse
1. **Import Project**
   - File → Import → Existing Maven Projects
   - Browse đến thư mục dự án
   - Finish

2. **Configure Database**
   - Window → Show View → Other → Data Source Explorer
   - Add connection profile

#### VS Code
1. **Install Extensions**
   - Extension Pack for Java
   - Spring Boot Extension Pack
   - PostgreSQL Extension

2. **Configure Settings**
   ```json
   {
     "java.configuration.updateBuildConfiguration": "automatic",
     "spring-boot.ls.java.home": "/path/to/java17"
   }
   ```

### Database Setup Scripts

#### Initial Data Script
```sql
-- File: src/main/resources/data.sql
-- Insert sample data for development

-- Categories
INSERT INTO categories (name, description, image_url) VALUES
('Thiệp Sinh Nhật', 'Thiệp chúc mừng sinh nhật với nhiều thiết kế đẹp', '/images/categories/birthday.jpg'),
('Thiệp Giáng Sinh', 'Thiệp chúc mừng Giáng Sinh và Năm Mới', '/images/categories/christmas.jpg'),
('Thiệp Valentine', 'Thiệp tình yêu cho ngày Valentine', '/images/categories/valentine.jpg'),
('Thiệp Cưới', 'Thiệp mời cưới và chúc mừng', '/images/categories/wedding.jpg'),
('Thiệp Tốt Nghiệp', 'Thiệp chúc mừng tốt nghiệp', '/images/categories/graduation.jpg');

-- Products
INSERT INTO products (name, description, price, stock, image_url, category_id) VALUES
('Thiệp Sinh Nhật Hoa Hồng', 'Thiệp sinh nhật với thiết kế hoa hồng đẹp mắt', 25000, 100, '/images/products/birthday-rose.jpg', 1),
('Thiệp Giáng Sinh Cây Thông', 'Thiệp Giáng Sinh với hình cây thông Noel', 30000, 80, '/images/products/christmas-tree.jpg', 2),
('Thiệp Valentine Trái Tim', 'Thiệp Valentine với thiết kế trái tim lãng mạn', 28000, 120, '/images/products/valentine-heart.jpg', 3);

-- Admin User
INSERT INTO users (email, password, full_name, phone, address, role) VALUES
('admin@greetingcards.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Admin System', '0123456789', '123 Admin Street', 'ADMIN');
```

## 🧪 Testing Strategy

### Unit Testing
```java
// Example: ProductServiceTest.java
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    
    @Mock
    private ProductRepository productRepository;
    
    @InjectMocks
    private ProductServiceImpl productService;
    
    @Test
    void testFindById_WhenProductExists_ShouldReturnProduct() {
        // Given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        
        // When
        Product result = productService.findById(productId);
        
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(productId);
        assertThat(result.getName()).isEqualTo("Test Product");
    }
}
```

### Integration Testing
```java
// Example: ProductControllerIntegrationTest.java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testGetProducts_ShouldReturnProductList() {
        // When
        ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(
            "/api/products", ProductDTO[].class);
        
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}
```

### Test Configuration
```properties
# src/test/resources/application-test.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

## 🔄 Development Workflow

### Git Workflow
```bash
# 1. Create feature branch
git checkout -b feature/product-management

# 2. Make changes and commit
git add .
git commit -m "feat: add product management functionality"

# 3. Push to remote
git push origin feature/product-management

# 4. Create Pull Request
# 5. After review, merge to main
git checkout main
git pull origin main
git branch -d feature/product-management
```

### Code Style Guidelines
- **Naming Convention**: camelCase for variables, PascalCase for classes
- **Package Structure**: Follow Spring Boot conventions
- **Documentation**: JavaDoc for public methods
- **Error Handling**: Use custom exceptions with meaningful messages
- **Logging**: Use SLF4J with appropriate log levels

### Code Review Checklist
- [ ] Code follows naming conventions
- [ ] Proper error handling implemented
- [ ] Unit tests written and passing
- [ ] No hardcoded values (use properties)
- [ ] Security considerations addressed
- [ ] Performance optimizations applied
- [ ] Documentation updated

## 🐛 Debugging Tips

### Common Issues

#### 1. Database Connection Issues
```bash
# Check PostgreSQL service
sudo systemctl status postgresql

# Check connection
psql -h localhost -U greeting_cards_user -d greeting_cards_db
```

#### 2. Port Already in Use
```bash
# Find process using port 8080
lsof -i :8080

# Kill process
kill -9 <PID>
```

#### 3. Maven Build Issues
```bash
# Clean and rebuild
mvn clean install -U

# Skip tests if needed
mvn clean install -DskipTests
```

### Debug Configuration
```properties
# Enable debug logging
logging.level.iuh.fit.se.finalprojectwww=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## 📦 Build và Packaging

### Development Build
```bash
# Clean build
mvn clean compile

# Run tests
mvn test

# Package application
mvn package

# Run application
mvn spring-boot:run
```

### Production Build
```bash
# Build with production profile
mvn clean package -Pprod

# Create Docker image (if using Docker)
docker build -t greeting-cards-app .

# Run with Docker
docker run -p 8080:8080 greeting-cards-app
```

## 🔧 Development Tools

### Useful Maven Commands
```bash
# Check dependencies
mvn dependency:tree

# Update dependencies
mvn versions:display-dependency-updates

# Format code (Spotless)
mvn spotless:apply

# Generate site documentation
mvn site
```

### Database Tools
```bash
# Backup database
pg_dump -h localhost -U greeting_cards_user greeting_cards_db > backup.sql

# Restore database
psql -h localhost -U greeting_cards_user greeting_cards_db < backup.sql

# Reset database
dropdb greeting_cards_db && createdb greeting_cards_db
```

## 📚 Learning Resources

### Spring Boot Documentation
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Security](https://docs.spring.io/spring-security/reference/)

### Best Practices
- [Spring Boot Best Practices](https://spring.io/guides/gs/spring-boot/)
- [REST API Design](https://restfulapi.net/)
- [Database Design](https://www.postgresql.org/docs/current/ddl.html)

### Community Resources
- [Spring Boot GitHub](https://github.com/spring-projects/spring-boot)
- [Stack Overflow](https://stackoverflow.com/questions/tagged/spring-boot)
- [Spring Boot Examples](https://github.com/spring-projects/spring-boot/tree/main/spring-boot-samples)