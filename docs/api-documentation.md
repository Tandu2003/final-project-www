# API Documentation - Website Bán Thiệp Trực Tuyến

## 📋 Tổng Quan API

Website bán thiệp trực tuyến cung cấp RESTful API để hỗ trợ các chức năng frontend và tích hợp với các hệ thống khác. API được thiết kế theo chuẩn REST với JSON format.

## 🔗 Base URL
```
Development: http://localhost:8080/api
Production: https://yourdomain.com/api
```

## 🔐 Authentication

### Session-based Authentication
API sử dụng session-based authentication với Spring Security.

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "user": {
    "id": 1,
    "email": "user@example.com",
    "fullName": "Nguyễn Văn A",
    "role": "USER"
  }
}
```

### Logout
```http
POST /api/auth/logout
```

## 👤 User Management APIs

### 1. User Registration
```http
POST /api/users/register
Content-Type: application/json

{
  "email": "newuser@example.com",
  "password": "password123",
  "fullName": "Nguyễn Văn B",
  "phone": "0987654321",
  "address": "123 Main Street"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Registration successful",
  "user": {
    "id": 2,
    "email": "newuser@example.com",
    "fullName": "Nguyễn Văn B",
    "role": "USER"
  }
}
```

### 2. Get User Profile
```http
GET /api/users/profile
```

**Response:**
```json
{
  "id": 1,
  "email": "user@example.com",
  "fullName": "Nguyễn Văn A",
  "phone": "0987654321",
  "address": "123 Main Street",
  "role": "USER",
  "createdAt": "2024-01-01T10:00:00Z"
}
```

### 3. Update User Profile
```http
PUT /api/users/profile
Content-Type: application/json

{
  "fullName": "Nguyễn Văn A Updated",
  "phone": "0987654321",
  "address": "456 Updated Street"
}
```

## 🛍️ Product APIs

### 1. Get All Products
```http
GET /api/products?page=0&size=10&categoryId=1&sort=name,asc
```

**Query Parameters:**
- `page`: Page number (default: 0)
- `size`: Page size (default: 10)
- `categoryId`: Filter by category ID
- `sort`: Sort criteria (name,asc or price,desc)
- `search`: Search term

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Thiệp Sinh Nhật Hoa Hồng",
      "description": "Thiệp sinh nhật với thiết kế hoa hồng đẹp mắt",
      "price": 25000,
      "stock": 100,
      "imageUrl": "/images/products/birthday-rose.jpg",
      "category": {
        "id": 1,
        "name": "Thiệp Sinh Nhật"
      }
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "sorted": true,
      "unsorted": false
    }
  },
  "totalElements": 25,
  "totalPages": 3,
  "first": true,
  "last": false
}
```

### 2. Get Product by ID
```http
GET /api/products/{id}
```

**Response:**
```json
{
  "id": 1,
  "name": "Thiệp Sinh Nhật Hoa Hồng",
  "description": "Thiệp sinh nhật với thiết kế hoa hồng đẹp mắt, phù hợp cho mọi lứa tuổi",
  "price": 25000,
  "stock": 100,
  "imageUrl": "/images/products/birthday-rose.jpg",
  "category": {
    "id": 1,
    "name": "Thiệp Sinh Nhật",
    "description": "Thiệp chúc mừng sinh nhật"
  },
  "createdAt": "2024-01-01T10:00:00Z",
  "updatedAt": "2024-01-01T10:00:00Z"
}
```

### 3. Search Products
```http
GET /api/products/search?q=sinh nhật&categoryId=1&minPrice=20000&maxPrice=50000
```

**Query Parameters:**
- `q`: Search query
- `categoryId`: Filter by category
- `minPrice`: Minimum price
- `maxPrice`: Maximum price

## 🏷️ Category APIs

### 1. Get All Categories
```http
GET /api/categories
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Thiệp Sinh Nhật",
    "description": "Thiệp chúc mừng sinh nhật với nhiều thiết kế đẹp",
    "imageUrl": "/images/categories/birthday.jpg",
    "productCount": 15
  },
  {
    "id": 2,
    "name": "Thiệp Giáng Sinh",
    "description": "Thiệp chúc mừng Giáng Sinh và Năm Mới",
    "imageUrl": "/images/categories/christmas.jpg",
    "productCount": 8
  }
]
```

### 2. Get Category by ID
```http
GET /api/categories/{id}
```

## 🛒 Shopping Cart APIs

### 1. Get Cart Items
```http
GET /api/cart
```

**Response:**
```json
{
  "items": [
    {
      "productId": 1,
      "productName": "Thiệp Sinh Nhật Hoa Hồng",
      "price": 25000,
      "quantity": 2,
      "subtotal": 50000,
      "imageUrl": "/images/products/birthday-rose.jpg"
    }
  ],
  "totalItems": 2,
  "totalAmount": 50000
}
```

### 2. Add Item to Cart
```http
POST /api/cart/add
Content-Type: application/json

{
  "productId": 1,
  "quantity": 2
}
```

**Response:**
```json
{
  "success": true,
  "message": "Item added to cart successfully",
  "cartItem": {
    "productId": 1,
    "productName": "Thiệp Sinh Nhật Hoa Hồng",
    "price": 25000,
    "quantity": 2,
    "subtotal": 50000
  }
}
```

### 3. Update Cart Item Quantity
```http
PUT /api/cart/update
Content-Type: application/json

{
  "productId": 1,
  "quantity": 3
}
```

### 4. Remove Item from Cart
```http
DELETE /api/cart/remove/{productId}
```

### 5. Clear Cart
```http
DELETE /api/cart/clear
```

## 📦 Order APIs

### 1. Create Order
```http
POST /api/orders
Content-Type: application/json

{
  "shippingAddress": "123 Main Street, District 1, Ho Chi Minh City",
  "notes": "Please deliver in the morning"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Order created successfully",
  "order": {
    "id": 1,
    "orderDate": "2024-01-01T10:00:00Z",
    "totalAmount": 50000,
    "status": "PENDING",
    "shippingAddress": "123 Main Street, District 1, Ho Chi Minh City",
    "items": [
      {
        "productId": 1,
        "productName": "Thiệp Sinh Nhật Hoa Hồng",
        "quantity": 2,
        "price": 25000
      }
    ]
  }
}
```

### 2. Get User Orders
```http
GET /api/orders?page=0&size=10&status=PENDING
```

**Query Parameters:**
- `page`: Page number
- `size`: Page size
- `status`: Filter by status

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "orderDate": "2024-01-01T10:00:00Z",
      "totalAmount": 50000,
      "status": "PENDING",
      "shippingAddress": "123 Main Street",
      "itemCount": 2
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 5,
  "totalPages": 1
}
```

### 3. Get Order Details
```http
GET /api/orders/{id}
```

**Response:**
```json
{
  "id": 1,
  "orderDate": "2024-01-01T10:00:00Z",
  "totalAmount": 50000,
  "status": "PENDING",
  "shippingAddress": "123 Main Street, District 1, Ho Chi Minh City",
  "notes": "Please deliver in the morning",
  "customer": {
    "id": 1,
    "fullName": "Nguyễn Văn A",
    "email": "user@example.com",
    "phone": "0987654321"
  },
  "items": [
    {
      "id": 1,
      "product": {
        "id": 1,
        "name": "Thiệp Sinh Nhật Hoa Hồng",
        "imageUrl": "/images/products/birthday-rose.jpg"
      },
      "quantity": 2,
      "price": 25000
    }
  ]
}
```

## 👨‍💼 Admin APIs

### 1. Product Management

#### Create Product
```http
POST /api/admin/products
Content-Type: application/json

{
  "name": "Thiệp Mới",
  "description": "Mô tả thiệp mới",
  "price": 30000,
  "stock": 50,
  "categoryId": 1,
  "imageUrl": "/images/products/new-card.jpg"
}
```

#### Update Product
```http
PUT /api/admin/products/{id}
Content-Type: application/json

{
  "name": "Thiệp Đã Cập Nhật",
  "description": "Mô tả đã cập nhật",
  "price": 35000,
  "stock": 75,
  "categoryId": 1
}
```

#### Delete Product
```http
DELETE /api/admin/products/{id}
```

#### Get All Products (Admin)
```http
GET /api/admin/products?page=0&size=20&search=&categoryId=&sort=createdAt,desc
```

### 2. Category Management

#### Create Category
```http
POST /api/admin/categories
Content-Type: application/json

{
  "name": "Thiệp Mới",
  "description": "Mô tả danh mục mới",
  "imageUrl": "/images/categories/new-category.jpg"
}
```

#### Update Category
```http
PUT /api/admin/categories/{id}
Content-Type: application/json

{
  "name": "Thiệp Đã Cập Nhật",
  "description": "Mô tả đã cập nhật",
  "imageUrl": "/images/categories/updated-category.jpg"
}
```

#### Delete Category
```http
DELETE /api/admin/categories/{id}
```

### 3. User Management

#### Get All Users
```http
GET /api/admin/users?page=0&size=20&search=&role=&sort=createdAt,desc
```

#### Get User Details
```http
GET /api/admin/users/{id}
```

#### Update User
```http
PUT /api/admin/users/{id}
Content-Type: application/json

{
  "fullName": "Tên Đã Cập Nhật",
  "phone": "0987654321",
  "address": "Địa chỉ mới",
  "role": "USER",
  "isActive": true
}
```

#### Delete User
```http
DELETE /api/admin/users/{id}
```

### 4. Order Management

#### Get All Orders
```http
GET /api/admin/orders?page=0&size=20&status=&userId=&sort=orderDate,desc
```

#### Update Order Status
```http
PUT /api/admin/orders/{id}/status
Content-Type: application/json

{
  "status": "CONFIRMED"
}
```

#### Update Order Item Quantity
```http
PUT /api/admin/orders/{orderId}/items/{itemId}
Content-Type: application/json

{
  "quantity": 3
}
```

## 📊 Statistics APIs

### 1. Dashboard Statistics
```http
GET /api/admin/statistics/dashboard
```

**Response:**
```json
{
  "totalUsers": 150,
  "totalProducts": 45,
  "totalOrders": 320,
  "totalRevenue": 15000000,
  "recentOrders": [
    {
      "id": 1,
      "customerName": "Nguyễn Văn A",
      "totalAmount": 50000,
      "orderDate": "2024-01-01T10:00:00Z"
    }
  ],
  "topProducts": [
    {
      "id": 1,
      "name": "Thiệp Sinh Nhật Hoa Hồng",
      "totalSold": 25,
      "revenue": 625000
    }
  ]
}
```

### 2. Sales Report
```http
GET /api/admin/statistics/sales?from=2024-01-01&to=2024-01-31&groupBy=day
```

**Query Parameters:**
- `from`: Start date (YYYY-MM-DD)
- `to`: End date (YYYY-MM-DD)
- `groupBy`: day, week, month

## 📧 Email APIs

### 1. Send Test Email
```http
POST /api/admin/email/test
Content-Type: application/json

{
  "to": "test@example.com",
  "subject": "Test Email",
  "body": "This is a test email"
}
```

## 🔍 Search APIs

### 1. Global Search
```http
GET /api/search?q=sinh nhật&type=all&page=0&size=10
```

**Query Parameters:**
- `q`: Search query
- `type`: all, products, categories, users
- `page`: Page number
- `size`: Page size

**Response:**
```json
{
  "products": [
    {
      "id": 1,
      "name": "Thiệp Sinh Nhật Hoa Hồng",
      "price": 25000,
      "imageUrl": "/images/products/birthday-rose.jpg"
    }
  ],
  "categories": [
    {
      "id": 1,
      "name": "Thiệp Sinh Nhật"
    }
  ],
  "totalResults": 15
}
```

## ❌ Error Responses

### Standard Error Format
```json
{
  "timestamp": "2024-01-01T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/products",
  "details": [
    {
      "field": "name",
      "message": "Product name is required"
    }
  ]
}
```

### Common HTTP Status Codes
- `200 OK`: Request successful
- `201 Created`: Resource created successfully
- `400 Bad Request`: Invalid request data
- `401 Unauthorized`: Authentication required
- `403 Forbidden`: Access denied
- `404 Not Found`: Resource not found
- `409 Conflict`: Resource conflict (e.g., duplicate email)
- `500 Internal Server Error`: Server error

## 🔒 Security Considerations

### Rate Limiting
- API calls limited to 100 requests per minute per IP
- Admin endpoints limited to 50 requests per minute per user

### Input Validation
- All input data validated on server side
- SQL injection prevention through parameterized queries
- XSS prevention through output encoding

### CORS Configuration
```java
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
```

## 📝 API Testing

### Postman Collection
Import the provided Postman collection for API testing:
- Environment variables for different environments
- Pre-request scripts for authentication
- Test scripts for response validation

### cURL Examples
```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password123"}'

# Get products
curl -X GET "http://localhost:8080/api/products?page=0&size=10" \
  -H "Cookie: JSESSIONID=your-session-id"

# Add to cart
curl -X POST http://localhost:8080/api/cart/add \
  -H "Content-Type: application/json" \
  -H "Cookie: JSESSIONID=your-session-id" \
  -d '{"productId":1,"quantity":2}'
```

## 📚 API Versioning

### Current Version: v1
All API endpoints are prefixed with `/api/` (no version in URL for now).

### Future Versions
- v2: Planned for advanced features
- Backward compatibility maintained for at least 2 versions