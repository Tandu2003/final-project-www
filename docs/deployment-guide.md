# Hướng Dẫn Triển Khai - Website Bán Thiệp Trực Tuyến

## 🚀 Tổng Quan Triển Khai

Hướng dẫn này cung cấp các bước chi tiết để triển khai website bán thiệp trực tuyến lên môi trường production, bao gồm cấu hình server, database, và các dịch vụ hỗ trợ.

## 🏗️ Kiến Trúc Triển Khai

### Production Architecture
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Load Balancer │    │   Web Server    │    │   Database      │
│   (Nginx)       │◄──►│   (Spring Boot) │◄──►│   (PostgreSQL)  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Static Files  │    │   File Storage  │    │   Backup System  │
│   (CDN)         │    │   (Local/S3)    │    │   (Automated)    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Environment Setup
- **Development**: Local development environment
- **Staging**: Pre-production testing environment
- **Production**: Live production environment

## 🖥️ Yêu Cầu Server

### Minimum Requirements
- **CPU**: 2 cores, 2.4GHz
- **RAM**: 4GB
- **Storage**: 50GB SSD
- **OS**: Ubuntu 20.04 LTS / CentOS 8 / RHEL 8
- **Network**: 100 Mbps bandwidth

### Recommended Requirements
- **CPU**: 4 cores, 3.0GHz
- **RAM**: 8GB
- **Storage**: 100GB SSD
- **OS**: Ubuntu 22.04 LTS
- **Network**: 1 Gbps bandwidth

## 🛠️ Cài Đặt Server

### 1. Cập Nhật Hệ Thống
```bash
# Ubuntu/Debian
sudo apt update && sudo apt upgrade -y

# CentOS/RHEL
sudo yum update -y
```

### 2. Cài Đặt Java 17
```bash
# Ubuntu/Debian
sudo apt install openjdk-17-jdk -y

# CentOS/RHEL
sudo yum install java-17-openjdk-devel -y

# Verify installation
java -version
javac -version
```

### 3. Cài Đặt PostgreSQL
```bash
# Ubuntu/Debian
sudo apt install postgresql postgresql-contrib -y

# CentOS/RHEL
sudo yum install postgresql-server postgresql-contrib -y
sudo postgresql-setup --initdb

# Start and enable PostgreSQL
sudo systemctl start postgresql
sudo systemctl enable postgresql
```

### 4. Cài Đặt Nginx
```bash
# Ubuntu/Debian
sudo apt install nginx -y

# CentOS/RHEL
sudo yum install nginx -y

# Start and enable Nginx
sudo systemctl start nginx
sudo systemctl enable nginx
```

### 5. Cài Đặt Maven
```bash
# Ubuntu/Debian
sudo apt install maven -y

# CentOS/RHEL
sudo yum install maven -y

# Verify installation
mvn -version
```

## 🗄️ Cấu Hình Database

### 1. Tạo Database và User
```bash
# Switch to postgres user
sudo -u postgres psql

# Create database and user
CREATE DATABASE greeting_cards_prod;
CREATE USER greeting_cards_user WITH PASSWORD 'secure_password_here';
GRANT ALL PRIVILEGES ON DATABASE greeting_cards_prod TO greeting_cards_user;

# Exit psql
\q
```

### 2. Cấu Hình PostgreSQL
```bash
# Edit postgresql.conf
sudo nano /etc/postgresql/14/main/postgresql.conf

# Add/modify these settings:
listen_addresses = 'localhost'
port = 5432
max_connections = 100
shared_buffers = 256MB
effective_cache_size = 1GB
maintenance_work_mem = 64MB
checkpoint_completion_target = 0.9
wal_buffers = 16MB
default_statistics_target = 100
```

### 3. Cấu Hình Authentication
```bash
# Edit pg_hba.conf
sudo nano /etc/postgresql/14/main/pg_hba.conf

# Add this line for local connections:
local   all             greeting_cards_user                    md5
host    all             greeting_cards_user    127.0.0.1/32    md5
```

### 4. Restart PostgreSQL
```bash
sudo systemctl restart postgresql
```

## 📦 Build và Deploy Application

### 1. Clone Repository
```bash
# Clone the repository
git clone https://github.com/your-username/final-project-www.git
cd final-project-www
```

### 2. Build Application
```bash
# Clean and build
mvn clean package -Pprod

# Verify JAR file
ls -la target/final-project-www-0.0.1-SNAPSHOT.jar
```

### 3. Create Application Directory
```bash
# Create application directory
sudo mkdir -p /opt/greeting-cards
sudo chown $USER:$USER /opt/greeting-cards

# Copy JAR file
cp target/final-project-www-0.0.1-SNAPSHOT.jar /opt/greeting-cards/
```

### 4. Create Application User
```bash
# Create application user
sudo useradd -r -s /bin/false greeting-cards
sudo chown -R greeting-cards:greeting-cards /opt/greeting-cards
```

## ⚙️ Cấu Hình Application

### 1. Production Properties
```bash
# Create production properties file
sudo nano /opt/greeting-cards/application-prod.properties
```

```properties
# Production Configuration
spring.profiles.active=prod

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/greeting_cards_prod
spring.datasource.username=greeting_cards_user
spring.datasource.password=${DB_PASSWORD:secure_password_here}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server Configuration
server.port=8080
server.servlet.context-path=/
server.compression.enabled=true
server.compression.mime-types=text/html,text/xml,text/plain,text/css,text/javascript,application/javascript,application/json
server.compression.min-response-size=1024

# Thymeleaf Configuration
spring.thymeleaf.cache=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
spring.servlet.multipart.location=/tmp

# Email Configuration
spring.mail.host=${MAIL_HOST:smtp.gmail.com}
spring.mail.port=${MAIL_PORT:587}
spring.mail.username=${MAIL_USERNAME:your-email@gmail.com}
spring.mail.password=${MAIL_PASSWORD:your-app-password}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Logging Configuration
logging.level.iuh.fit.se.finalprojectwww=INFO
logging.level.org.springframework.security=WARN
logging.level.org.hibernate.SQL=WARN
logging.file.name=/var/log/greeting-cards/application.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

# Security Configuration
server.servlet.session.timeout=30m
server.servlet.session.cookie.secure=true
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.same-site=strict
```

### 2. Environment Variables
```bash
# Create environment file
sudo nano /opt/greeting-cards/.env
```

```bash
# Database
DB_PASSWORD=your_secure_database_password

# Email
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password

# Application
SPRING_PROFILES_ACTIVE=prod
```

### 3. Set Permissions
```bash
# Set proper permissions
sudo chown -R greeting-cards:greeting-cards /opt/greeting-cards
sudo chmod 600 /opt/greeting-cards/.env
sudo chmod 644 /opt/greeting-cards/application-prod.properties
```

## 🔧 Systemd Service Configuration

### 1. Create Service File
```bash
sudo nano /etc/systemd/system/greeting-cards.service
```

```ini
[Unit]
Description=Greeting Cards Web Application
After=network.target postgresql.service

[Service]
Type=simple
User=greeting-cards
Group=greeting-cards
WorkingDirectory=/opt/greeting-cards
ExecStart=/usr/bin/java -Xms512m -Xmx1024m -jar /opt/greeting-cards/final-project-www-0.0.1-SNAPSHOT.jar --spring.config.location=classpath:/application.properties,/opt/greeting-cards/application-prod.properties
ExecStop=/bin/kill -15 $MAINPID
Restart=always
RestartSec=10
StandardOutput=journal
StandardError=journal
SyslogIdentifier=greeting-cards

# Environment
EnvironmentFile=/opt/greeting-cards/.env

# Security
NoNewPrivileges=true
PrivateTmp=true
ProtectSystem=strict
ProtectHome=true
ReadWritePaths=/opt/greeting-cards
ReadWritePaths=/var/log/greeting-cards

[Install]
WantedBy=multi-user.target
```

### 2. Enable and Start Service
```bash
# Reload systemd
sudo systemctl daemon-reload

# Enable service
sudo systemctl enable greeting-cards

# Start service
sudo systemctl start greeting-cards

# Check status
sudo systemctl status greeting-cards
```

## 🌐 Nginx Configuration

### 1. Create Nginx Configuration
```bash
sudo nano /etc/nginx/sites-available/greeting-cards
```

```nginx
upstream greeting_cards_backend {
    server 127.0.0.1:8080;
}

server {
    listen 80;
    server_name yourdomain.com www.yourdomain.com;
    
    # Redirect HTTP to HTTPS
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    server_name yourdomain.com www.yourdomain.com;
    
    # SSL Configuration
    ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers ECDHE-RSA-AES256-GCM-SHA512:DHE-RSA-AES256-GCM-SHA512:ECDHE-RSA-AES256-GCM-SHA384:DHE-RSA-AES256-GCM-SHA384;
    ssl_prefer_server_ciphers off;
    ssl_session_cache shared:SSL:10m;
    ssl_session_timeout 10m;
    
    # Security Headers
    add_header X-Frame-Options "SAMEORIGIN" always;
    add_header X-XSS-Protection "1; mode=block" always;
    add_header X-Content-Type-Options "nosniff" always;
    add_header Referrer-Policy "no-referrer-when-downgrade" always;
    add_header Content-Security-Policy "default-src 'self' http: https: data: blob: 'unsafe-inline'" always;
    add_header Strict-Transport-Security "max-age=31536000; includeSubDomains" always;
    
    # Gzip Compression
    gzip on;
    gzip_vary on;
    gzip_min_length 1024;
    gzip_proxied expired no-cache no-store private must-revalidate auth;
    gzip_types text/plain text/css text/xml text/javascript application/x-javascript application/xml+rss application/javascript application/json;
    
    # Static Files
    location /static/ {
        alias /opt/greeting-cards/static/;
        expires 1y;
        add_header Cache-Control "public, immutable";
    }
    
    location /images/ {
        alias /opt/greeting-cards/images/;
        expires 1y;
        add_header Cache-Control "public, immutable";
    }
    
    # API Routes
    location /api/ {
        proxy_pass http://greeting_cards_backend;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_connect_timeout 30s;
        proxy_send_timeout 30s;
        proxy_read_timeout 30s;
    }
    
    # Application Routes
    location / {
        proxy_pass http://greeting_cards_backend;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_connect_timeout 30s;
        proxy_send_timeout 30s;
        proxy_read_timeout 30s;
    }
    
    # Error Pages
    error_page 404 /404.html;
    error_page 500 502 503 504 /50x.html;
    
    # Logging
    access_log /var/log/nginx/greeting-cards.access.log;
    error_log /var/log/nginx/greeting-cards.error.log;
}
```

### 2. Enable Site
```bash
# Create symbolic link
sudo ln -s /etc/nginx/sites-available/greeting-cards /etc/nginx/sites-enabled/

# Remove default site
sudo rm /etc/nginx/sites-enabled/default

# Test configuration
sudo nginx -t

# Reload Nginx
sudo systemctl reload nginx
```

## 🔒 SSL Certificate (Let's Encrypt)

### 1. Install Certbot
```bash
# Ubuntu/Debian
sudo apt install certbot python3-certbot-nginx -y

# CentOS/RHEL
sudo yum install certbot python3-certbot-nginx -y
```

### 2. Obtain SSL Certificate
```bash
# Get certificate
sudo certbot --nginx -d yourdomain.com -d www.yourdomain.com

# Test auto-renewal
sudo certbot renew --dry-run
```

### 3. Setup Auto-renewal
```bash
# Add to crontab
sudo crontab -e

# Add this line:
0 12 * * * /usr/bin/certbot renew --quiet
```

## 📊 Monitoring và Logging

### 1. Create Log Directory
```bash
sudo mkdir -p /var/log/greeting-cards
sudo chown greeting-cards:greeting-cards /var/log/greeting-cards
```

### 2. Log Rotation
```bash
sudo nano /etc/logrotate.d/greeting-cards
```

```
/var/log/greeting-cards/*.log {
    daily
    missingok
    rotate 30
    compress
    delaycompress
    notifempty
    create 644 greeting-cards greeting-cards
    postrotate
        systemctl reload greeting-cards
    endscript
}
```

### 3. System Monitoring
```bash
# Install monitoring tools
sudo apt install htop iotop nethogs -y

# Check system resources
htop
df -h
free -h
```

## 🔄 Backup Strategy

### 1. Database Backup Script
```bash
sudo nano /opt/greeting-cards/backup-db.sh
```

```bash
#!/bin/bash
BACKUP_DIR="/opt/greeting-cards/backups"
DB_NAME="greeting_cards_prod"
DB_USER="greeting_cards_user"
DATE=$(date +%Y%m%d_%H%M%S)

# Create backup directory
mkdir -p $BACKUP_DIR

# Create database backup
pg_dump -h localhost -U $DB_USER $DB_NAME > $BACKUP_DIR/db_backup_$DATE.sql

# Compress backup
gzip $BACKUP_DIR/db_backup_$DATE.sql

# Remove backups older than 30 days
find $BACKUP_DIR -name "db_backup_*.sql.gz" -mtime +30 -delete

echo "Database backup completed: db_backup_$DATE.sql.gz"
```

### 2. Application Backup Script
```bash
sudo nano /opt/greeting-cards/backup-app.sh
```

```bash
#!/bin/bash
BACKUP_DIR="/opt/greeting-cards/backups"
APP_DIR="/opt/greeting-cards"
DATE=$(date +%Y%m%d_%H%M%S)

# Create backup directory
mkdir -p $BACKUP_DIR

# Create application backup
tar -czf $BACKUP_DIR/app_backup_$DATE.tar.gz -C $APP_DIR .

# Remove backups older than 30 days
find $BACKUP_DIR -name "app_backup_*.tar.gz" -mtime +30 -delete

echo "Application backup completed: app_backup_$DATE.tar.gz"
```

### 3. Setup Automated Backups
```bash
# Make scripts executable
sudo chmod +x /opt/greeting-cards/backup-*.sh

# Add to crontab
sudo crontab -e

# Add these lines:
0 2 * * * /opt/greeting-cards/backup-db.sh
0 3 * * * /opt/greeting-cards/backup-app.sh
```

## 🚀 Deployment Script

### 1. Create Deployment Script
```bash
sudo nano /opt/greeting-cards/deploy.sh
```

```bash
#!/bin/bash
set -e

APP_DIR="/opt/greeting-cards"
BACKUP_DIR="/opt/greeting-cards/backups"
SERVICE_NAME="greeting-cards"

echo "Starting deployment..."

# Create backup before deployment
echo "Creating backup..."
$APP_DIR/backup-app.sh

# Stop service
echo "Stopping service..."
sudo systemctl stop $SERVICE_NAME

# Backup current JAR
echo "Backing up current JAR..."
cp $APP_DIR/final-project-www-0.0.1-SNAPSHOT.jar $BACKUP_DIR/final-project-www-0.0.1-SNAPSHOT.jar.backup.$(date +%Y%m%d_%H%M%S)

# Copy new JAR (assuming it's in the current directory)
echo "Copying new JAR..."
cp target/final-project-www-0.0.1-SNAPSHOT.jar $APP_DIR/

# Set permissions
sudo chown greeting-cards:greeting-cards $APP_DIR/final-project-www-0.0.1-SNAPSHOT.jar

# Start service
echo "Starting service..."
sudo systemctl start $SERVICE_NAME

# Wait for service to start
sleep 10

# Check service status
if sudo systemctl is-active --quiet $SERVICE_NAME; then
    echo "Deployment successful!"
    echo "Service status:"
    sudo systemctl status $SERVICE_NAME --no-pager
else
    echo "Deployment failed! Service is not running."
    echo "Service status:"
    sudo systemctl status $SERVICE_NAME --no-pager
    exit 1
fi
```

### 2. Make Script Executable
```bash
sudo chmod +x /opt/greeting-cards/deploy.sh
```

## 🔧 Maintenance Tasks

### 1. Update Application
```bash
# Pull latest changes
git pull origin main

# Build new version
mvn clean package -Pprod

# Deploy using script
sudo /opt/greeting-cards/deploy.sh
```

### 2. Database Maintenance
```bash
# Connect to database
sudo -u postgres psql greeting_cards_prod

# Run maintenance commands
VACUUM ANALYZE;
REINDEX DATABASE greeting_cards_prod;

# Exit
\q
```

### 3. System Updates
```bash
# Update system packages
sudo apt update && sudo apt upgrade -y

# Restart services if needed
sudo systemctl restart greeting-cards
sudo systemctl restart nginx
sudo systemctl restart postgresql
```

## 🚨 Troubleshooting

### Common Issues

#### 1. Service Won't Start
```bash
# Check service status
sudo systemctl status greeting-cards

# Check logs
sudo journalctl -u greeting-cards -f

# Check application logs
tail -f /var/log/greeting-cards/application.log
```

#### 2. Database Connection Issues
```bash
# Check PostgreSQL status
sudo systemctl status postgresql

# Test connection
psql -h localhost -U greeting_cards_user -d greeting_cards_prod

# Check PostgreSQL logs
sudo tail -f /var/log/postgresql/postgresql-14-main.log
```

#### 3. Nginx Issues
```bash
# Test Nginx configuration
sudo nginx -t

# Check Nginx status
sudo systemctl status nginx

# Check Nginx logs
sudo tail -f /var/log/nginx/greeting-cards.error.log
```

#### 4. SSL Certificate Issues
```bash
# Check certificate status
sudo certbot certificates

# Renew certificate manually
sudo certbot renew --force-renewal
```

## 📈 Performance Optimization

### 1. JVM Tuning
```bash
# Edit service file
sudo nano /etc/systemd/system/greeting-cards.service

# Update ExecStart with JVM options:
ExecStart=/usr/bin/java -Xms1g -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -jar /opt/greeting-cards/final-project-www-0.0.1-SNAPSHOT.jar
```

### 2. Database Optimization
```sql
-- Update postgresql.conf
shared_buffers = 512MB
effective_cache_size = 2GB
maintenance_work_mem = 128MB
checkpoint_completion_target = 0.9
wal_buffers = 32MB
default_statistics_target = 100
random_page_cost = 1.1
effective_io_concurrency = 200
```

### 3. Nginx Optimization
```nginx
# Add to nginx.conf
worker_processes auto;
worker_connections 1024;
keepalive_timeout 65;
client_max_body_size 10M;
```

## 🔐 Security Hardening

### 1. Firewall Configuration
```bash
# Install UFW
sudo apt install ufw -y

# Configure firewall
sudo ufw default deny incoming
sudo ufw default allow outgoing
sudo ufw allow ssh
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
sudo ufw enable
```

### 2. SSH Security
```bash
# Edit SSH config
sudo nano /etc/ssh/sshd_config

# Add/modify these settings:
PermitRootLogin no
PasswordAuthentication no
PubkeyAuthentication yes
Port 2222
```

### 3. System Security
```bash
# Install fail2ban
sudo apt install fail2ban -y

# Configure fail2ban
sudo nano /etc/fail2ban/jail.local
```

```ini
[DEFAULT]
bantime = 3600
findtime = 600
maxretry = 3

[sshd]
enabled = true
port = ssh
logpath = /var/log/auth.log

[nginx-http-auth]
enabled = true
port = http,https
logpath = /var/log/nginx/greeting-cards.error.log
```

## 📋 Deployment Checklist

### Pre-deployment
- [ ] Server requirements met
- [ ] All software installed and configured
- [ ] Database created and configured
- [ ] SSL certificate obtained
- [ ] Domain DNS configured
- [ ] Firewall configured

### Deployment
- [ ] Application built successfully
- [ ] Service configured and started
- [ ] Nginx configured and started
- [ ] SSL certificate working
- [ ] Application accessible via domain
- [ ] Database connection working
- [ ] Email functionality working

### Post-deployment
- [ ] Monitoring configured
- [ ] Backup scripts working
- [ ] Log rotation configured
- [ ] Security hardening applied
- [ ] Performance optimization applied
- [ ] Documentation updated

## 📞 Support và Maintenance

### Monitoring Commands
```bash
# Check service status
sudo systemctl status greeting-cards

# Check system resources
htop
df -h
free -h

# Check logs
sudo journalctl -u greeting-cards -f
tail -f /var/log/greeting-cards/application.log

# Check database
sudo -u postgres psql greeting_cards_prod -c "SELECT * FROM pg_stat_activity;"
```

### Emergency Procedures
```bash
# Emergency stop
sudo systemctl stop greeting-cards

# Emergency restart
sudo systemctl restart greeting-cards

# Rollback to previous version
sudo cp /opt/greeting-cards/backups/final-project-www-0.0.1-SNAPSHOT.jar.backup.YYYYMMDD_HHMMSS /opt/greeting-cards/final-project-www-0.0.1-SNAPSHOT.jar
sudo systemctl restart greeting-cards
```