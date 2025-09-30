# Thiết Kế Cơ Sở Dữ Liệu - Website Bán Thiệp Trực Tuyến

## 🗄️ Tổng Quan Database

Website bán thiệp trực tuyến sử dụng PostgreSQL làm hệ quản trị cơ sở dữ liệu chính, được thiết kế theo chuẩn hóa 3NF để đảm bảo tính nhất quán và hiệu suất cao.

## 📊 Sơ Đồ ERD (Entity Relationship Diagram)

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│   Category  │     │   Product   │     │ OrderItem   │
│             │     │             │     │             │
│ id (PK)     │◄────┤ category_id │     │ id (PK)     │
│ name        │     │ id (PK)     │◄────┤ product_id  │
│ description │     │ name        │     │ order_id    │
│ image_url   │     │ description │     │ quantity    │
│ created_at  │     │ price       │     │ price       │
│ updated_at  │     │ stock       │     │             │
│             │     │ image_url   │     │             │
│             │     │ created_at  │     │             │
│             │     │ updated_at  │     │             │
└─────────────┘     └─────────────┘     └─────────────┘
                                              │
                                              │
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│    User     │     │    Order    │     │             │
│             │     │             │     │             │
│ id (PK)     │◄────┤ user_id     │     │             │
│ email       │     │ id (PK)     │     │             │
│ password    │     │ order_date  │     │             │
│ full_name   │     │ total_amount│     │             │
│ phone       │     │ status      │     │             │
│ address     │     │ shipping_   │     │             │
│ role        │     │   address   │     │             │
│ created_at  │     │ created_at  │     │             │
│ updated_at  │     │ updated_at  │     │             │
└─────────────┘     └─────────────┘     └─────────────┘
```

## 📋 Chi Tiết Các Bảng

### 1. Bảng `categories` - Danh Mục Thiệp

```sql
CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_categories_name ON categories(name);
CREATE INDEX idx_categories_created_at ON categories(created_at);
```

**Mô tả:**
- `id`: Khóa chính tự tăng
- `name`: Tên danh mục (duy nhất)
- `description`: Mô tả danh mục
- `image_url`: URL hình ảnh đại diện
- `created_at`, `updated_at`: Timestamp tự động

**Dữ liệu mẫu:**
```sql
INSERT INTO categories (name, description, image_url) VALUES
('Thiệp Sinh Nhật', 'Thiệp chúc mừng sinh nhật với nhiều thiết kế đẹp', '/images/categories/birthday.jpg'),
('Thiệp Giáng Sinh', 'Thiệp chúc mừng Giáng Sinh và Năm Mới', '/images/categories/christmas.jpg'),
('Thiệp Valentine', 'Thiệp tình yêu cho ngày Valentine', '/images/categories/valentine.jpg'),
('Thiệp Cưới', 'Thiệp mời cưới và chúc mừng', '/images/categories/wedding.jpg'),
('Thiệp Tốt Nghiệp', 'Thiệp chúc mừng tốt nghiệp', '/images/categories/graduation.jpg');
```

### 2. Bảng `products` - Sản Phẩm Thiệp

```sql
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    stock INTEGER NOT NULL DEFAULT 0 CHECK (stock >= 0),
    image_url VARCHAR(255),
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_products_category 
        FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT
);

-- Indexes
CREATE INDEX idx_products_category_id ON products(category_id);
CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_products_price ON products(price);
CREATE INDEX idx_products_stock ON products(stock);
CREATE INDEX idx_products_created_at ON products(created_at);
```

**Mô tả:**
- `id`: Khóa chính tự tăng
- `name`: Tên sản phẩm
- `description`: Mô tả chi tiết
- `price`: Giá bán (phải > 0)
- `stock`: Số lượng tồn kho
- `image_url`: URL hình ảnh sản phẩm
- `category_id`: Khóa ngoại đến bảng categories

**Dữ liệu mẫu:**
```sql
INSERT INTO products (name, description, price, stock, image_url, category_id) VALUES
('Thiệp Sinh Nhật Hoa Hồng', 'Thiệp sinh nhật với thiết kế hoa hồng đẹp mắt', 25000, 100, '/images/products/birthday-rose.jpg', 1),
('Thiệp Giáng Sinh Cây Thông', 'Thiệp Giáng Sinh với hình cây thông Noel', 30000, 80, '/images/products/christmas-tree.jpg', 2),
('Thiệp Valentine Trái Tim', 'Thiệp Valentine với thiết kế trái tim lãng mạn', 28000, 120, '/images/products/valentine-heart.jpg', 3);
```

### 3. Bảng `users` - Người Dùng

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    role VARCHAR(20) NOT NULL DEFAULT 'USER' CHECK (role IN ('USER', 'ADMIN')),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_created_at ON users(created_at);
```

**Mô tả:**
- `id`: Khóa chính tự tăng
- `email`: Email đăng nhập (duy nhất)
- `password`: Mật khẩu đã mã hóa
- `full_name`: Họ tên đầy đủ
- `phone`: Số điện thoại
- `address`: Địa chỉ
- `role`: Vai trò (USER/ADMIN)
- `is_active`: Trạng thái hoạt động

**Dữ liệu mẫu:**
```sql
INSERT INTO users (email, password, full_name, phone, address, role) VALUES
('admin@greetingcards.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Admin System', '0123456789', '123 Admin Street', 'ADMIN'),
('user1@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Nguyễn Văn A', '0987654321', '456 User Street', 'USER');
```

### 4. Bảng `orders` - Đơn Hàng

```sql
CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(12,2) NOT NULL CHECK (total_amount > 0),
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED')),
    shipping_address TEXT NOT NULL,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_orders_user 
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

-- Indexes
CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_order_date ON orders(order_date);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_created_at ON orders(created_at);
```

**Mô tả:**
- `id`: Khóa chính tự tăng
- `user_id`: Khóa ngoại đến bảng users
- `order_date`: Ngày đặt hàng
- `total_amount`: Tổng tiền đơn hàng
- `status`: Trạng thái đơn hàng
- `shipping_address`: Địa chỉ giao hàng
- `notes`: Ghi chú

### 5. Bảng `order_items` - Chi Tiết Đơn Hàng

```sql
CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_order_items_order 
        FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_items_product 
        FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
);

-- Indexes
CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_order_items_product_id ON order_items(product_id);
```

**Mô tả:**
- `id`: Khóa chính tự tăng
- `order_id`: Khóa ngoại đến bảng orders
- `product_id`: Khóa ngoại đến bảng products
- `quantity`: Số lượng sản phẩm
- `price`: Giá tại thời điểm mua

## 🔗 Quan Hệ Giữa Các Bảng

### 1. Quan Hệ One-to-Many
- **Category → Products**: Một danh mục có nhiều sản phẩm
- **User → Orders**: Một người dùng có nhiều đơn hàng
- **Order → OrderItems**: Một đơn hàng có nhiều chi tiết

### 2. Quan Hệ Many-to-Many (Thông qua OrderItems)
- **Products ↔ Orders**: Nhiều sản phẩm có thể có trong nhiều đơn hàng

## 📊 Views và Stored Procedures

### 1. View: Product Summary
```sql
CREATE VIEW product_summary AS
SELECT 
    p.id,
    p.name,
    p.price,
    p.stock,
    c.name as category_name,
    COUNT(oi.id) as total_orders,
    COALESCE(SUM(oi.quantity), 0) as total_sold
FROM products p
LEFT JOIN categories c ON p.category_id = c.id
LEFT JOIN order_items oi ON p.id = oi.product_id
GROUP BY p.id, p.name, p.price, p.stock, c.name;
```

### 2. View: Order Summary
```sql
CREATE VIEW order_summary AS
SELECT 
    o.id,
    o.order_date,
    o.total_amount,
    o.status,
    u.full_name as customer_name,
    u.email as customer_email,
    COUNT(oi.id) as item_count
FROM orders o
JOIN users u ON o.user_id = u.id
LEFT JOIN order_items oi ON o.id = oi.order_id
GROUP BY o.id, o.order_date, o.total_amount, o.status, u.full_name, u.email;
```

## 🔍 Indexes và Performance

### Primary Indexes
- Tất cả khóa chính tự động có index
- Khóa ngoại có index để tối ưu JOIN

### Composite Indexes
```sql
-- Index cho tìm kiếm sản phẩm theo danh mục và giá
CREATE INDEX idx_products_category_price ON products(category_id, price);

-- Index cho tìm kiếm đơn hàng theo người dùng và ngày
CREATE INDEX idx_orders_user_date ON orders(user_id, order_date);

-- Index cho tìm kiếm theo trạng thái và ngày
CREATE INDEX idx_orders_status_date ON orders(status, order_date);
```

### Query Optimization
```sql
-- Tìm kiếm sản phẩm hiệu quả
SELECT p.*, c.name as category_name 
FROM products p 
JOIN categories c ON p.category_id = c.id 
WHERE c.name = 'Thiệp Sinh Nhật' 
AND p.price BETWEEN 20000 AND 50000 
AND p.stock > 0;

-- Thống kê doanh thu theo tháng
SELECT 
    DATE_TRUNC('month', order_date) as month,
    COUNT(*) as order_count,
    SUM(total_amount) as total_revenue
FROM orders 
WHERE status = 'DELIVERED'
GROUP BY DATE_TRUNC('month', order_date)
ORDER BY month;
```

## 🔒 Constraints và Validation

### Check Constraints
```sql
-- Giá sản phẩm phải > 0
ALTER TABLE products ADD CONSTRAINT chk_price_positive CHECK (price > 0);

-- Số lượng tồn kho >= 0
ALTER TABLE products ADD CONSTRAINT chk_stock_non_negative CHECK (stock >= 0);

-- Số lượng đặt hàng > 0
ALTER TABLE order_items ADD CONSTRAINT chk_quantity_positive CHECK (quantity > 0);

-- Tổng tiền đơn hàng > 0
ALTER TABLE orders ADD CONSTRAINT chk_total_amount_positive CHECK (total_amount > 0);

-- Role chỉ được USER hoặc ADMIN
ALTER TABLE users ADD CONSTRAINT chk_role_valid CHECK (role IN ('USER', 'ADMIN'));

-- Status đơn hàng hợp lệ
ALTER TABLE orders ADD CONSTRAINT chk_status_valid CHECK (status IN ('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED'));
```

### Unique Constraints
```sql
-- Email duy nhất
ALTER TABLE users ADD CONSTRAINT uk_users_email UNIQUE (email);

-- Tên danh mục duy nhất
ALTER TABLE categories ADD CONSTRAINT uk_categories_name UNIQUE (name);
```

## 🗃️ Database Migration Scripts

### 1. Initial Schema (V1.0)
```sql
-- File: V1.0__Initial_schema.sql
-- Tạo tất cả bảng và constraints cơ bản
```

### 2. Add Indexes (V1.1)
```sql
-- File: V1.1__Add_indexes.sql
-- Thêm các index để tối ưu performance
```

### 3. Add Views (V1.2)
```sql
-- File: V1.2__Add_views.sql
-- Tạo các view để báo cáo
```

## 📈 Data Seeding

### Sample Data Script
```sql
-- File: sample_data.sql
-- Chèn dữ liệu mẫu cho development và testing
```

## 🔄 Backup và Recovery

### Backup Strategy
```bash
# Full backup
pg_dump -h localhost -U username -d greeting_cards_db > backup_full.sql

# Schema only
pg_dump -h localhost -U username -d greeting_cards_db --schema-only > backup_schema.sql

# Data only
pg_dump -h localhost -U username -d greeting_cards_db --data-only > backup_data.sql
```

### Recovery Strategy
```bash
# Restore from backup
psql -h localhost -U username -d greeting_cards_db < backup_full.sql
```

## 📊 Monitoring và Maintenance

### Performance Monitoring
```sql
-- Query để kiểm tra performance
SELECT 
    schemaname,
    tablename,
    attname,
    n_distinct,
    correlation
FROM pg_stats 
WHERE schemaname = 'public'
ORDER BY tablename, attname;
```

### Maintenance Tasks
- **VACUUM**: Dọn dẹp database định kỳ
- **ANALYZE**: Cập nhật thống kê cho query optimizer
- **REINDEX**: Tái tạo index khi cần thiết