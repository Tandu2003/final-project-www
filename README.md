# Website Bán Thiệp Trực Tuyến (Online Greeting Card Store)

## 📋 Tổng Quan Dự Án

Website bán thiệp trực tuyến là một ứng dụng web được phát triển bằng Spring Boot, cho phép người dùng duyệt, mua và quản lý thiệp chúc mừng trực tuyến. Dự án này đáp ứng các yêu cầu của môn Lập trình WWW/Lập trình Web Nâng cao (Java).

## 🎯 Mục Tiêu Dự Án

- Xây dựng một hệ thống thương mại điện tử hoàn chỉnh cho việc bán thiệp chúc mừng
- Triển khai các chức năng quản lý người dùng, sản phẩm và đơn hàng
- Áp dụng các công nghệ Spring ecosystem hiện đại
- Đảm bảo tính bảo mật và hiệu suất cao

## 🏗️ Kiến Trúc Hệ Thống

### Công Nghệ Sử Dụng
- **Backend**: Spring Boot 3.5.6
- **Database**: PostgreSQL
- **Frontend**: Thymeleaf Templates
- **Security**: Spring Security
- **Email**: Spring Mail
- **Build Tool**: Maven
- **Java Version**: 17

### Cấu Trúc Dự Án
```
final-project-www/
├── src/main/java/iuh/fit/se/finalprojectwww/
│   ├── controller/          # Controllers xử lý HTTP requests
│   ├── service/            # Business logic layer
│   ├── repository/         # Data access layer
│   ├── model/              # Entity models
│   ├── dto/                # Data Transfer Objects
│   ├── config/             # Configuration classes
│   └── util/               # Utility classes
├── src/main/resources/
│   ├── templates/          # Thymeleaf templates
│   ├── static/             # Static resources (CSS, JS, images)
│   └── application.properties
└── docs/                   # Documentation files
```

## 👥 Phân Quyền Người Dùng

### 1. Guest (Khách hàng không đăng ký)
- Xem danh sách thiệp
- Xem chi tiết thiệp
- Thêm thiệp vào giỏ hàng
- Xem và chỉnh sửa giỏ hàng
- Đăng ký tài khoản

### 2. Customer (Khách hàng đã đăng ký)
- Tất cả chức năng của Guest
- Đăng nhập/đăng xuất
- Thanh toán đơn hàng
- Xem lịch sử đơn hàng

### 3. Admin (Quản trị viên)
- Tất cả chức năng của Customer
- Quản lý thiệp và danh mục thiệp
- Quản lý tài khoản người dùng
- Quản lý đơn hàng
- Thống kê và báo cáo

## 🛒 Chức Năng Chính

### Quản Lý Thiệp
- Hiển thị danh sách thiệp theo danh mục
- Tìm kiếm và lọc thiệp
- Chi tiết thiệp với hình ảnh và mô tả
- Quản lý kho hàng

### Giỏ Hàng
- Thêm/xóa thiệp khỏi giỏ hàng
- Cập nhật số lượng
- Tính toán tổng tiền
- Lưu trữ trong Session

### Thanh Toán
- Xử lý đơn hàng
- Gửi email xác nhận
- Cập nhật cơ sở dữ liệu
- Quản lý trạng thái đơn hàng

## 🚀 Cài Đặt và Chạy Dự Án

### Yêu Cầu Hệ Thống
- Java 17+
- Maven 3.6+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Cài Đặt
1. Clone repository
2. Cấu hình database trong `application.properties`
3. Chạy lệnh: `mvn spring-boot:run`
4. Truy cập: `http://localhost:8080`

## 📚 Tài Liệu Chi Tiết

- [Yêu Cầu Dự Án](./docs/requirements.md)
- [Thiết Kế Hệ Thống](./docs/system-design.md)
- [Thiết Kế Database](./docs/database-design.md)
- [API Documentation](./docs/api-documentation.md)
- [Hướng Dẫn Sử Dụng](./docs/user-manual.md)
- [Hướng Dẫn Phát Triển](./docs/development-guide.md)
- [Hướng Dẫn Triển Khai](./docs/deployment-guide.md)

## 📋 Todo List

Xem chi tiết các công việc cần thực hiện trong file [TODO.md](./TODO.md)

## 👨‍💻 Nhóm Phát Triển

- **Môn học**: Lập trình WWW/Lập trình Web Nâng cao (Java)
- **Trường**: Đại học Công nghệ Thông tin - Đại học Quốc gia TP.HCM
- **Năm học**: 2024-2025

## 📄 License

Dự án này được phát triển cho mục đích học tập và nghiên cứu.

---

**Lưu ý**: Đây là dự án học tập, không sử dụng cho mục đích thương mại.