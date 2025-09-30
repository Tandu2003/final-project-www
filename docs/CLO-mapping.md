# CLO Mapping - Website Bán Thiệp Trực Tuyến

## 📋 Tổng Quan CLO Mapping

Tài liệu này ánh xạ các tiêu chí đánh giá (CLO) của môn Lập trình WWW/Lập trình Web Nâng cao (Java) với các chức năng và công nghệ được triển khai trong dự án Website bán thiệp trực tuyến.

## 🎯 CLO 1: Làm Việc Nhóm

### Yêu Cầu
- Tham gia các hoạt động của nhóm trong quá trình hoàn thành công việc
- Hoàn thành công việc của mình trong nhóm khi được phân công

### Triển Khai Trong Dự Án
- **Git Workflow**: Sử dụng Git để quản lý phiên bản và hợp tác
- **Branch Strategy**: Feature branches cho từng chức năng
- **Code Review**: Review code trước khi merge
- **Task Distribution**: Phân chia công việc theo modules

### Deliverables
- [ ] Git repository với commit history rõ ràng
- [ ] Pull requests và code reviews
- [ ] Task assignment và progress tracking
- [ ] Team collaboration documentation

### Evidence
- Git commit messages với format chuẩn
- Pull request reviews và comments
- Task completion reports
- Team meeting minutes

---

## 📝 CLO 2: Viết Tài Liệu Báo Cáo

### Yêu Cầu
- Viết tài liệu báo cáo rõ ràng theo quy định
- Đúng cấu trúc, đầy đủ nội dung
- Trích dẫn, tài liệu tham khảo theo yêu cầu

### Triển Khai Trong Dự Án
- **README.md**: Tổng quan dự án và hướng dẫn
- **Requirements Documentation**: Yêu cầu chi tiết
- **System Design**: Thiết kế hệ thống
- **API Documentation**: Tài liệu API đầy đủ
- **User Manual**: Hướng dẫn sử dụng
- **Deployment Guide**: Hướng dẫn triển khai

### Deliverables
- [x] README.md - Tổng quan dự án
- [x] docs/requirements.md - Yêu cầu chi tiết
- [x] docs/system-design.md - Thiết kế hệ thống
- [x] docs/database-design.md - Thiết kế database
- [x] docs/api-documentation.md - Tài liệu API
- [x] docs/user-manual.md - Hướng dẫn sử dụng
- [x] docs/development-guide.md - Hướng dẫn phát triển
- [x] docs/deployment-guide.md - Hướng dẫn triển khai
- [x] TODO.md - Danh sách công việc

### Evidence
- Tài liệu có cấu trúc rõ ràng với mục lục
- Nội dung đầy đủ và chi tiết
- Trích dẫn các nguồn tham khảo
- Format chuẩn markdown

---

## 🎯 CLO 3: Xác Định Mục Tiêu và Ràng Buộc

### Yêu Cầu
- Xác định được các mục tiêu công việc của một ứng dụng Web
- Xác định các ràng buộc đi kèm

### Triển Khai Trong Dự Án
- **Business Goals**: Mục tiêu kinh doanh của website
- **Technical Goals**: Mục tiêu kỹ thuật
- **User Requirements**: Yêu cầu người dùng
- **Constraints**: Ràng buộc kỹ thuật và kinh doanh

### Deliverables
- [ ] Business requirements analysis
- [ ] Technical requirements specification
- [ ] User stories và use cases
- [ ] Constraint analysis document

### Evidence
- Requirements document với phân tích chi tiết
- User stories với acceptance criteria
- Constraint matrix
- Risk assessment

---

## 🗄️ CLO 4: Kết Nối Cơ Sở Dữ Liệu

### Yêu Cầu
- Trình bày và cài đặt được cách kết nối đến các nguồn dữ liệu (data sources)
- Xây dựng ứng dụng web sử dụng JSPs/Servlets có kết nối đến cơ sở dữ liệu

### Triển Khai Trong Dự Án
- **Database Connection**: PostgreSQL connection
- **JPA/Hibernate**: ORM framework
- **Repository Pattern**: Data access layer
- **Connection Pooling**: HikariCP
- **Database Migration**: Flyway

### Deliverables
- [ ] Database schema design
- [ ] Entity models với JPA annotations
- [ ] Repository interfaces
- [ ] Database connection configuration
- [ ] Migration scripts

### Evidence
- Entity classes với JPA annotations
- Repository interfaces với custom queries
- Database connection configuration
- Migration scripts và sample data

---

## 🏗️ CLO 5: Công Nghệ Web và Spring Ecosystem

### Yêu Cầu
- Trình bày được các công nghệ trong phát triển ứng dụng Web
- Trình bày được kiến trúc và cấu hình được môi trường thực thi của ứng dụng theo công nghệ Spring ecosystem

### Triển Khai Trong Dự Án
- **Spring Boot**: Main framework
- **Spring MVC**: Web layer
- **Spring Data JPA**: Data access
- **Spring Security**: Security
- **Spring Mail**: Email services
- **Thymeleaf**: Template engine

### Deliverables
- [ ] Spring Boot application structure
- [ ] MVC architecture implementation
- [ ] Security configuration
- [ ] Email service integration
- [ ] Template engine setup

### Evidence
- Spring Boot configuration files
- MVC controllers và services
- Security configuration
- Email service implementation
- Thymeleaf templates

---

## 🌐 CLO 6: Spring Ecosystem và Web Services

### Yêu Cầu
- Chọn lựa và cài đặt được ứng dụng sử dụng công nghệ Spring ecosystem
- Sử dụng Web services

### Triển Khai Trong Dự Án
- **RESTful APIs**: REST web services
- **Spring Boot**: Application framework
- **Maven**: Build tool
- **Docker**: Containerization (optional)
- **Microservices**: Modular architecture

### Deliverables
- [ ] REST API endpoints
- [ ] Spring Boot application
- [ ] Maven build configuration
- [ ] API documentation
- [ ] Web service integration

### Evidence
- REST controller implementations
- API documentation với examples
- Maven pom.xml configuration
- Postman collection
- Web service tests

---

## 📊 CLO Assessment Matrix

| CLO | Weight | Implementation | Evidence | Status |
|-----|--------|----------------|----------|--------|
| CLO 1 | 15% | Git workflow, team collaboration | Commit history, PR reviews | 🔄 In Progress |
| CLO 2 | 20% | Comprehensive documentation | Documentation files | ✅ Completed |
| CLO 3 | 10% | Requirements analysis | Requirements document | 🔄 In Progress |
| CLO 4 | 20% | Database integration | Entity models, repositories | 🔄 In Progress |
| CLO 5 | 20% | Spring ecosystem | Spring Boot application | 🔄 In Progress |
| CLO 6 | 15% | Web services | REST APIs | 🔄 In Progress |

---

## 🎯 CLO-Specific Deliverables

### CLO 1: Teamwork Evidence
- [ ] Git repository với commit history
- [ ] Branch strategy documentation
- [ ] Code review process
- [ ] Task assignment records
- [ ] Team collaboration tools usage

### CLO 2: Documentation Evidence
- [x] README.md với project overview
- [x] Requirements specification
- [x] System architecture document
- [x] Database design document
- [x] API documentation
- [x] User manual
- [x] Development guide
- [x] Deployment guide

### CLO 3: Requirements Evidence
- [ ] Business requirements analysis
- [ ] Technical requirements
- [ ] User stories
- [ ] Use case diagrams
- [ ] Constraint analysis
- [ ] Risk assessment

### CLO 4: Database Evidence
- [ ] Database schema
- [ ] Entity models
- [ ] Repository interfaces
- [ ] Database configuration
- [ ] Migration scripts
- [ ] Sample data

### CLO 5: Spring Ecosystem Evidence
- [ ] Spring Boot application
- [ ] MVC architecture
- [ ] Security configuration
- [ ] Email service
- [ ] Template engine
- [ ] Configuration files

### CLO 6: Web Services Evidence
- [ ] REST API endpoints
- [ ] API documentation
- [ ] Web service tests
- [ ] Integration examples
- [ ] Postman collection

---

## 📈 Progress Tracking

### Phase 1: Documentation (Completed ✅)
- [x] All documentation files created
- [x] CLO 2 requirements met
- [x] Project structure documented

### Phase 2: Backend Development (In Progress 🔄)
- [ ] CLO 4: Database integration
- [ ] CLO 5: Spring ecosystem
- [ ] CLO 6: Web services

### Phase 3: Frontend Development (Pending ⏳)
- [ ] CLO 5: Template engine
- [ ] CLO 6: Web services integration

### Phase 4: Testing & Deployment (Pending ⏳)
- [ ] CLO 1: Team collaboration
- [ ] CLO 2: Final documentation
- [ ] CLO 3: Requirements validation

---

## 🎓 Learning Outcomes

### Technical Skills
- Spring Boot application development
- Database design and integration
- RESTful web services
- Security implementation
- Email service integration
- Template engine usage

### Soft Skills
- Team collaboration
- Documentation writing
- Requirements analysis
- Project management
- Problem solving
- Communication

### Tools & Technologies
- Java 17
- Spring Boot 3.5.6
- PostgreSQL
- Maven
- Git
- Thymeleaf
- Bootstrap
- Postman

---

## 📋 Assessment Criteria

### Excellent (90-100%)
- All CLOs fully implemented
- High-quality documentation
- Excellent code quality
- Comprehensive testing
- Professional presentation

### Good (80-89%)
- Most CLOs implemented
- Good documentation
- Good code quality
- Adequate testing
- Good presentation

### Satisfactory (70-79%)
- Basic CLOs implemented
- Basic documentation
- Acceptable code quality
- Basic testing
- Acceptable presentation

### Needs Improvement (<70%)
- Limited CLO implementation
- Poor documentation
- Poor code quality
- Insufficient testing
- Poor presentation

---

## 🎯 Success Metrics

### CLO 1: Teamwork
- Active participation in team activities
- Completion of assigned tasks
- Quality of collaboration
- Communication effectiveness

### CLO 2: Documentation
- Completeness of documentation
- Clarity and structure
- Technical accuracy
- Professional presentation

### CLO 3: Requirements
- Accuracy of requirements analysis
- Completeness of constraint identification
- Quality of user stories
- Risk assessment quality

### CLO 4: Database
- Database design quality
- Connection implementation
- Data access layer quality
- Performance optimization

### CLO 5: Spring Ecosystem
- Spring Boot implementation
- Architecture quality
- Configuration management
- Integration success

### CLO 6: Web Services
- API design quality
- Service implementation
- Documentation completeness
- Integration testing

---

**Lưu ý**: Tài liệu này sẽ được cập nhật liên tục trong quá trình phát triển dự án để đảm bảo đáp ứng đầy đủ các yêu cầu CLO.