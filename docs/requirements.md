# Yêu Cầu Dự Án - Website Bán Thiệp Trực Tuyến

## 📋 Tổng Quan Yêu Cầu

Dự án Website bán thiệp trực tuyến được phát triển để đáp ứng các tiêu chí đánh giá của môn Lập trình WWW/Lập trình Web Nâng cao (Java). Dự án tập trung vào việc xây dựng một hệ thống thương mại điện tử hoàn chỉnh với các chức năng quản lý người dùng, sản phẩm và đơn hàng.

## 🎯 Tiêu Chí Đánh Giá (CLO)

### CLO 1: Làm Việc Nhóm
- Tham gia tích cực các hoạt động nhóm
- Hoàn thành công việc được phân công
- Hợp tác hiệu quả với các thành viên khác

### CLO 2: Viết Tài Liệu Báo Cáo
- Tài liệu rõ ràng, đúng cấu trúc
- Đầy đủ nội dung và trích dẫn
- Tài liệu tham khảo theo yêu cầu

### CLO 3: Xác Định Mục Tiêu và Ràng Buộc
- Xác định mục tiêu công việc của ứng dụng Web
- Phân tích các ràng buộc đi kèm
- Thiết kế giải pháp phù hợp

### CLO 4: Kết Nối Cơ Sở Dữ Liệu
- Trình bày cách kết nối đến data sources
- Sử dụng JSPs/Servlets với database
- Xử lý dữ liệu hiệu quả

### CLO 5: Công Nghệ Web và Spring Ecosystem
- Trình bày các công nghệ phát triển Web
- Hiểu kiến trúc và cấu hình Spring ecosystem
- Triển khai môi trường thực thi

### CLO 6: Spring Ecosystem và Web Services
- Chọn lựa công nghệ Spring phù hợp
- Cài đặt ứng dụng sử dụng Spring ecosystem
- Triển khai Web services

## 🏪 Chức Năng Tối Thiểu

### 1. Phân Quyền Người Dùng

#### 1.1 Guest (Người dùng không có tài khoản)
**Chức năng xem sản phẩm:**
- Xem danh sách thiệp từ cơ sở dữ liệu
- Xem chi tiết từng thiệp
- Tìm kiếm và lọc thiệp theo danh mục

**Chức năng mua hàng:**
- Chọn mua thiệp từ danh sách hoặc trang chi tiết
- Thêm thiệp vào giỏ hàng
- Xem giỏ hàng (lưu trong Session)
- Chỉnh sửa số lượng thiệp trong giỏ hàng
- Xóa thiệp khỏi giỏ hàng (số lượng = 0)

**Chức năng đăng ký:**
- Đăng ký tài khoản với thông tin hợp lệ
- Email không trùng lặp
- Lưu trữ vào cơ sở dữ liệu
- Gửi email xác nhận
- Thông báo đăng ký thành công

#### 1.2 Customer (Người dùng có tài khoản)
**Kế thừa từ Guest:**
- Tất cả chức năng của Guest

**Chức năng bổ sung:**
- Đăng nhập/đăng xuất hệ thống
- Thanh toán đơn hàng (khi đã đăng nhập và có sản phẩm trong giỏ)
- Cập nhật thông tin vào cơ sở dữ liệu
- Gửi email xác nhận đặt hàng
- Thông báo đặt hàng thành công
- Xóa Session sau khi thanh toán thành công

#### 1.3 Admin (Quản trị hệ thống)
**Kế thừa từ Customer:**
- Tất cả chức năng của Customer

**Chức năng Backend:**

**Tìm kiếm thông tin:**
- Tìm kiếm sản phẩm/loại sản phẩm
- Tìm kiếm tài khoản người dùng
- Tìm kiếm đơn đặt hàng

**Quản lý sản phẩm/loại sản phẩm:**
- Xem danh sách sản phẩm/loại sản phẩm
- Xem chi tiết từng sản phẩm/loại sản phẩm
- Thêm mới sản phẩm/loại sản phẩm
- Cập nhật thông tin sản phẩm/loại sản phẩm
- Xóa sản phẩm (nếu chưa có trong đơn hàng nào)
- Xóa loại sản phẩm (nếu chưa có sản phẩm nào)

**Quản lý tài khoản người dùng:**
- Xem danh sách tài khoản đã đăng ký
- Xem chi tiết tài khoản (không hiển thị password)
- Cập nhật thông tin tài khoản
- Xóa tài khoản (nếu chưa có đơn hàng)

**Quản lý đơn hàng:**
- Xem danh sách đơn hàng (sắp xếp theo ngày mua)
- Xem chi tiết đơn hàng
- Cập nhật số lượng mặt hàng trong đơn hàng

## 🔧 Ràng Buộc Kỹ Thuật

### Ràng Buộc Xóa Dữ Liệu
- Không thể xóa sản phẩm đã có trong đơn hàng
- Không thể xóa loại sản phẩm đã có sản phẩm
- Không thể xóa tài khoản đã có đơn hàng

### Validation
- Kiểm tra dữ liệu phía Client (JavaScript/jQuery)
- Kiểm tra dữ liệu phía Server (Model validation)
- Không sử dụng Functions/Check constraints/Stored Procedures trong database

### Bảo Mật
- Mã hóa password
- Session management
- CSRF protection
- Input validation và sanitization

## 📊 Yêu Cầu Hiệu Suất

- Thời gian phản hồi < 2 giây
- Hỗ trợ đồng thời 100+ người dùng
- Tối ưu hóa truy vấn database
- Caching cho dữ liệu tĩnh

## 🌐 Yêu Cầu Giao Diện

- Responsive design (mobile-friendly)
- Giao diện thân thiện, dễ sử dụng
- Tương thích với các trình duyệt phổ biến
- Accessibility compliance

## 📧 Yêu Cầu Email

- Gửi email xác nhận đăng ký
- Gửi email xác nhận đặt hàng
- Template email chuyên nghiệp
- Hỗ trợ HTML email

## 🗄️ Yêu Cầu Database

- Thiết kế database chuẩn hóa
- Index cho các truy vấn thường xuyên
- Backup và recovery
- Data integrity constraints

## 🧪 Yêu Cầu Testing

- Unit testing cho business logic
- Integration testing cho API
- UI testing cho giao diện
- Performance testing

## 📱 Yêu Cầu Responsive

- Tương thích mobile (320px+)
- Tablet optimization (768px+)
- Desktop optimization (1024px+)
- Touch-friendly interface