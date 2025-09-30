# Hướng Dẫn Sử Dụng - Website Bán Thiệp Trực Tuyến

## 📖 Tổng Quan

Website bán thiệp trực tuyến cho phép người dùng duyệt, mua và quản lý thiệp chúc mừng trực tuyến. Hướng dẫn này sẽ giúp bạn sử dụng hiệu quả tất cả các chức năng của website.

## 🌐 Truy Cập Website

### Địa Chỉ Website
- **Development**: http://localhost:8080
- **Production**: https://yourdomain.com

### Trình Duyệt Hỗ Trợ
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

## 👤 Đăng Ký và Đăng Nhập

### Đăng Ký Tài Khoản Mới

1. **Truy cập trang đăng ký**
   - Click vào nút "Đăng Ký" ở góc phải trên
   - Hoặc truy cập trực tiếp: `/register`

2. **Điền thông tin đăng ký**
   ```
   Email: your-email@example.com
   Mật khẩu: password123
   Xác nhận mật khẩu: password123
   Họ tên: Nguyễn Văn A
   Số điện thoại: 0987654321
   Địa chỉ: 123 Main Street, District 1, HCMC
   ```

3. **Xác thực đăng ký**
   - Click "Đăng Ký"
   - Kiểm tra email để xác nhận tài khoản
   - Đăng nhập sau khi xác nhận thành công

### Đăng Nhập

1. **Truy cập trang đăng nhập**
   - Click vào nút "Đăng Nhập" ở góc phải trên
   - Hoặc truy cập trực tiếp: `/login`

2. **Nhập thông tin đăng nhập**
   ```
   Email: your-email@example.com
   Mật khẩu: password123
   ```

3. **Đăng nhập**
   - Click "Đăng Nhập"
   - Hệ thống sẽ chuyển hướng đến trang chủ

### Đăng Xuất
- Click vào tên người dùng ở góc phải trên
- Chọn "Đăng Xuất" từ menu dropdown

## 🛍️ Duyệt và Tìm Kiếm Sản Phẩm

### Trang Chủ
- **Banner quảng cáo**: Hiển thị các thiệp nổi bật
- **Danh mục thiệp**: Click để xem thiệp theo danh mục
- **Thiệp mới nhất**: Danh sách thiệp vừa được thêm
- **Thiệp bán chạy**: Thiệp được mua nhiều nhất

### Duyệt Theo Danh Mục

1. **Chọn danh mục**
   - Click vào danh mục từ menu chính
   - Hoặc từ trang chủ

2. **Danh mục có sẵn**
   - Thiệp Sinh Nhật
   - Thiệp Giáng Sinh
   - Thiệp Valentine
   - Thiệp Cưới
   - Thiệp Tốt Nghiệp

3. **Lọc và sắp xếp**
   ```
   Sắp xếp theo:
   - Tên A-Z
   - Tên Z-A
   - Giá thấp đến cao
   - Giá cao đến thấp
   - Mới nhất
   
   Lọc theo giá:
   - Dưới 20,000 VNĐ
   - 20,000 - 50,000 VNĐ
   - Trên 50,000 VNĐ
   ```

### Tìm Kiếm Thiệp

1. **Sử dụng thanh tìm kiếm**
   - Nhập từ khóa vào ô tìm kiếm
   - Ví dụ: "sinh nhật", "hoa hồng", "valentine"

2. **Tìm kiếm nâng cao**
   - Click "Tìm kiếm nâng cao"
   - Chọn danh mục
   - Đặt khoảng giá
   - Chọn từ khóa

3. **Kết quả tìm kiếm**
   - Hiển thị số lượng kết quả
   - Có thể sắp xếp lại kết quả
   - Phân trang cho kết quả nhiều

## 📄 Xem Chi Tiết Thiệp

### Thông Tin Thiệp
- **Tên thiệp**: Tên đầy đủ của thiệp
- **Mô tả**: Chi tiết về thiệp
- **Giá**: Giá bán hiện tại
- **Tồn kho**: Số lượng còn lại
- **Hình ảnh**: Hình ảnh chất lượng cao
- **Danh mục**: Thuộc danh mục nào

### Thêm Vào Giỏ Hàng
1. **Chọn số lượng**
   - Sử dụng nút +/- hoặc nhập trực tiếp
   - Kiểm tra số lượng tồn kho

2. **Thêm vào giỏ hàng**
   - Click "Thêm vào giỏ hàng"
   - Thông báo thành công sẽ hiển thị
   - Có thể tiếp tục mua sắm hoặc xem giỏ hàng

## 🛒 Quản Lý Giỏ Hàng

### Xem Giỏ Hàng
1. **Truy cập giỏ hàng**
   - Click icon giỏ hàng ở góc phải trên
   - Hoặc click "Xem giỏ hàng" sau khi thêm sản phẩm

2. **Thông tin giỏ hàng**
   ```
   Danh sách sản phẩm:
   - Tên thiệp
   - Giá
   - Số lượng
   - Thành tiền
   - Hình ảnh thu nhỏ
   
   Tổng cộng:
   - Tổng số sản phẩm
   - Tổng tiền
   ```

### Chỉnh Sửa Giỏ Hàng

1. **Thay đổi số lượng**
   - Click nút +/- để tăng/giảm số lượng
   - Nhập số lượng mới và nhấn Enter
   - Số lượng = 0 sẽ xóa sản phẩm khỏi giỏ hàng

2. **Xóa sản phẩm**
   - Click nút "Xóa" bên cạnh sản phẩm
   - Xác nhận xóa trong popup

3. **Xóa toàn bộ giỏ hàng**
   - Click "Xóa tất cả"
   - Xác nhận trong popup

### Cập Nhật Giỏ Hàng
- Giỏ hàng tự động cập nhật khi thay đổi
- Tổng tiền được tính lại ngay lập tức
- Thông báo khi có thay đổi

## 💳 Thanh Toán

### Điều Kiện Thanh Toán
- Phải đăng nhập vào hệ thống
- Giỏ hàng phải có ít nhất 1 sản phẩm
- Địa chỉ giao hàng phải hợp lệ

### Quy Trình Thanh Toán

1. **Kiểm tra giỏ hàng**
   - Xem lại sản phẩm đã chọn
   - Kiểm tra số lượng và giá
   - Click "Thanh toán"

2. **Nhập thông tin giao hàng**
   ```
   Địa chỉ giao hàng:
   - Số nhà, tên đường
   - Phường/Xã, Quận/Huyện
   - Tỉnh/Thành phố
   
   Ghi chú:
   - Thời gian giao hàng mong muốn
   - Hướng dẫn đặc biệt
   ```

3. **Xác nhận đơn hàng**
   - Kiểm tra lại thông tin
   - Xác nhận tổng tiền
   - Click "Xác nhận đặt hàng"

4. **Hoàn tất đơn hàng**
   - Thông báo đặt hàng thành công
   - Mã đơn hàng được hiển thị
   - Email xác nhận được gửi
   - Giỏ hàng được xóa

## 📦 Quản Lý Đơn Hàng

### Xem Lịch Sử Đơn Hàng

1. **Truy cập trang đơn hàng**
   - Click vào tên người dùng
   - Chọn "Đơn hàng của tôi"

2. **Danh sách đơn hàng**
   ```
   Thông tin hiển thị:
   - Mã đơn hàng
   - Ngày đặt hàng
   - Tổng tiền
   - Trạng thái
   - Số lượng sản phẩm
   ```

3. **Lọc đơn hàng**
   - Theo trạng thái: Tất cả, Chờ xác nhận, Đã xác nhận, Đã giao
   - Theo thời gian: 7 ngày, 30 ngày, 3 tháng, 1 năm

### Xem Chi Tiết Đơn Hàng

1. **Click vào mã đơn hàng**
   - Hiển thị trang chi tiết đơn hàng

2. **Thông tin chi tiết**
   ```
   Thông tin đơn hàng:
   - Mã đơn hàng
   - Ngày đặt hàng
   - Trạng thái hiện tại
   - Tổng tiền
   
   Thông tin giao hàng:
   - Địa chỉ giao hàng
   - Ghi chú
   
   Danh sách sản phẩm:
   - Tên thiệp
   - Số lượng
   - Giá
   - Thành tiền
   ```

### Trạng Thái Đơn Hàng
- **Chờ xác nhận**: Đơn hàng mới, chờ admin xác nhận
- **Đã xác nhận**: Admin đã xác nhận đơn hàng
- **Đang giao**: Đơn hàng đang được giao
- **Đã giao**: Đơn hàng đã giao thành công
- **Đã hủy**: Đơn hàng bị hủy

## 👤 Quản Lý Tài Khoản

### Xem Thông Tin Cá Nhân

1. **Truy cập trang cá nhân**
   - Click vào tên người dùng
   - Chọn "Thông tin cá nhân"

2. **Thông tin hiển thị**
   ```
   Thông tin cơ bản:
   - Email (không thể thay đổi)
   - Họ tên
   - Số điện thoại
   - Địa chỉ
   - Ngày đăng ký
   ```

### Cập Nhật Thông Tin

1. **Chỉnh sửa thông tin**
   - Click "Chỉnh sửa"
   - Thay đổi thông tin cần thiết
   - Click "Lưu thay đổi"

2. **Thay đổi mật khẩu**
   - Click "Đổi mật khẩu"
   - Nhập mật khẩu cũ
   - Nhập mật khẩu mới
   - Xác nhận mật khẩu mới
   - Click "Cập nhật mật khẩu"

## 🔧 Xử Lý Sự Cố

### Lỗi Thường Gặp

#### 1. Không thể đăng nhập
**Nguyên nhân:**
- Sai email hoặc mật khẩu
- Tài khoản chưa được kích hoạt
- Tài khoản bị khóa

**Giải pháp:**
- Kiểm tra lại thông tin đăng nhập
- Kiểm tra email để kích hoạt tài khoản
- Liên hệ admin để mở khóa tài khoản

#### 2. Không thể thêm vào giỏ hàng
**Nguyên nhân:**
- Sản phẩm hết hàng
- Số lượng vượt quá tồn kho
- Lỗi kết nối

**Giải pháp:**
- Kiểm tra số lượng tồn kho
- Giảm số lượng đặt mua
- Refresh trang và thử lại

#### 3. Không thể thanh toán
**Nguyên nhân:**
- Chưa đăng nhập
- Giỏ hàng trống
- Thông tin giao hàng không hợp lệ

**Giải pháp:**
- Đăng nhập vào hệ thống
- Thêm sản phẩm vào giỏ hàng
- Kiểm tra và điền đầy đủ thông tin giao hàng

#### 4. Không nhận được email xác nhận
**Nguyên nhân:**
- Email bị chặn bởi spam filter
- Sai địa chỉ email
- Lỗi hệ thống email

**Giải pháp:**
- Kiểm tra thư mục spam/junk
- Kiểm tra lại địa chỉ email
- Liên hệ admin để gửi lại email

### Liên Hệ Hỗ Trợ

#### Thông Tin Liên Hệ
- **Email hỗ trợ**: support@greetingcards.com
- **Hotline**: 1900-xxxx
- **Thời gian hỗ trợ**: 8:00 - 17:00 (Thứ 2 - Thứ 6)

#### Cách Liên Hệ
1. **Email hỗ trợ**
   - Gửi email với tiêu đề rõ ràng
   - Mô tả chi tiết vấn đề gặp phải
   - Đính kèm screenshot nếu cần

2. **Gọi hotline**
   - Chuẩn bị mã đơn hàng (nếu có)
   - Mô tả vấn đề một cách rõ ràng
   - Ghi chú lại mã ticket hỗ trợ

## 💡 Mẹo Sử Dụng Hiệu Quả

### Tìm Kiếm Thiệp
- Sử dụng từ khóa cụ thể: "sinh nhật", "valentine"
- Kết hợp nhiều từ khóa: "thiệp sinh nhật hoa hồng"
- Sử dụng bộ lọc để thu hẹp kết quả

### Quản Lý Giỏ Hàng
- Thường xuyên kiểm tra giỏ hàng
- Xóa các sản phẩm không cần thiết
- Lưu ý thời gian hết hạn session

### Đặt Hàng
- Điền đầy đủ thông tin giao hàng
- Thêm ghi chú đặc biệt nếu cần
- Kiểm tra lại đơn hàng trước khi xác nhận

### Bảo Mật Tài Khoản
- Sử dụng mật khẩu mạnh
- Không chia sẻ thông tin đăng nhập
- Đăng xuất sau khi sử dụng xong
- Thay đổi mật khẩu định kỳ

## 📱 Sử Dụng Trên Mobile

### Responsive Design
- Website tự động điều chỉnh theo kích thước màn hình
- Giao diện thân thiện với touch
- Tối ưu cho các thiết bị di động

### Tính Năng Mobile
- Swipe để xem hình ảnh thiệp
- Touch để thêm vào giỏ hàng
- Responsive menu và navigation
- Tối ưu tốc độ tải trang

### Khuyến Nghị
- Sử dụng WiFi để tải hình ảnh nhanh hơn
- Đảm bảo kết nối internet ổn định
- Cập nhật trình duyệt lên phiên bản mới nhất