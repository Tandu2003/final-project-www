# Spotless Configuration

Project này đã được cấu hình với Spotless để đảm bảo code formatting nhất quán.

## Cấu hình hiện tại

- **Java**: Sử dụng Google Java Format với style GOOGLE
- **Tự động loại bỏ**: Unused imports và format annotations
- **Tự động áp dụng**: Trong phase `process-sources`

## Các lệnh Spotless

### Kiểm tra formatting
```bash
./mvnw spotless:check
```
Kiểm tra xem code có tuân thủ formatting rules không mà không thay đổi file.

### Áp dụng formatting
```bash
./mvnw spotless:apply
```
Tự động format tất cả code theo rules đã cấu hình.

### Xem diff
```bash
./mvnw spotless:diff
```
Xem những thay đổi mà Spotless sẽ thực hiện.

## EditorConfig

Project cũng bao gồm file `.editorconfig` để hỗ trợ IDE formatting:
- Java: 2 spaces indentation, max line length 120
- XML: 4 spaces indentation
- Properties: 4 spaces indentation
- UTF-8 encoding, LF line endings

## Tích hợp với IDE

### IntelliJ IDEA
1. Cài đặt plugin "EditorConfig"
2. Enable "Reformat code" trong Settings > Tools > Actions on Save

### Eclipse
1. Cài đặt plugin "EditorConfig"
2. Enable "Format source code" trong Preferences > Java > Editor > Save Actions

### VS Code
1. Cài đặt extension "EditorConfig for VS Code"
2. Enable "Format on Save" trong Settings

## CI/CD Integration

Để tích hợp với CI/CD pipeline, thêm step kiểm tra:

```yaml
- name: Check code formatting
  run: ./mvnw spotless:check
```

Nếu build fail do formatting issues, chạy:
```bash
./mvnw spotless:apply
```
và commit lại changes.