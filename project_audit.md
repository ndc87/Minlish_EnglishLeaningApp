# Bảng Tổng Hợp Trạng Thái Dự Án MinLish

Dưới đây là bảng tổng hợp các tính năng đã hoàn thiện và các phần còn thiếu dựa trên bản mô tả yêu cầu của dự án.

## 1. Các Module Chính

| STT | Module | Trạng Thái | Chi Tiết |
| :--- | :--- | :--- | :--- |
| 1 | User Management | ⚠️ Hoàn thiện một phần | Đã có đăng ký/đăng nhập email, hồ sơ người dùng cơ bản. |
| 2 | Vocabulary Management | ✅ Hoàn thiện | Đã có CRUD từ vựng, quản lý theo chủ đề (Topics), Import/Export CSV. |
| 3 | Learning Engine (SRS) | ✅ Hoàn thiện | Thuật toán SM-2 đã hoạt động, logic tính ngày ôn tập chính xác. |
| 4 | Practice Module | ⚠️ Đang phát triển | Màn hình Practice đã có khung nhưng cần thêm các dạng bài tập khác ngoài Flashcard. |
| 5 | Analytics & Progress | ⚠️ Hoàn thiện một phần | Đã có thống kê XP, Streak, Biểu đồ hoạt động. |
| 6 | Notification System | ✅ Hoàn thiện | Đã có Worker nhắc học hàng ngày qua Push Notification. |

---

## 2. Chi Tiết Chức Năng

### 3.1 User Management
| Chức Năng | Trạng Thái | Ghi Chú |
| :--- | :--- | :--- |
| Đăng ký / Đăng nhập (Email) | ✅ Đã có | Sử dụng JWT (giả lập) và Encrypted Storage. |
| Google Login | ❌ **Chưa có** | Cần tích hợp Google Sign-In SDK. |
| Hồ sơ (Tên, Mục tiêu, Level) | ✅ Đã có | Lưu trong `UserEntity`. |

### 3.2 Vocabulary Management
| Chức Năng | Trạng Thái | Ghi Chú |
| :--- | :--- | :--- |
| Tạo bộ từ vựng (Topic/Tags) | ✅ Đã có | Quản lý qua `TopicEntity`. |
| Thêm từ vựng (Đầy đủ các trường) | ⚠️ **Thiếu trường** | Hiện tại thiếu: Pronunciation, Collocation, Related words. |
| Import / Export (CSV/Excel) | ✅ Đã có | Đã triển khai `FileImportExportUtil`. |

### 3.3 Learning Module (Trọng tâm)
| Chức Năng | Trạng Thái | Ghi Chú |
| :--- | :--- | :--- |
| Flashcard (Lật thẻ 3D) | ✅ Đã có | Theo đúng quy chuẩn `compose_design.md`. |
| Spaced Repetition (SM-2) | ✅ Đã có | Nút Again, Hard, Good, Easy hoạt động tốt. |
| Daily Learning Plan | ✅ Đã có | Có mục tiêu từ vựng mỗi ngày và danh sách từ đến hạn. |

### 3.4 Progress Tracking
| Chức Năng | Trạng Thái | Ghi Chú |
| :--- | :--- | :--- |
| Dashboard (XP, Streak) | ✅ Đã có | Hiển thị trực quan trên Dashboard. |
| Accuracy (% đúng) | ❌ **Chưa có** | Cần thêm logic tính toán dựa trên `LearningLog`. |
| Retention Rate (Tỷ lệ ghi nhớ) | ❌ **Chưa có** | Cần thêm logic truy vấn. |
| Level Estimation | ⚠️ Cơ bản | Hiện chỉ lưu level do user chọn, chưa tự động đánh giá. |

### 3.5 Notification System
| Chức Năng | Trạng Thái | Ghi Chú |
| :--- | :--- | :--- |
| Nhắc học hàng ngày (Push) | ✅ Đã có | Sử dụng WorkManager. |
| Nhắc từ đến hạn ôn | ✅ Đã có | Thông báo hiển thị số lượng từ cần ôn. |
| Email Notification | ❌ **Chưa có** | Ưu tiên tập trung vào Mobile Notification. |

---

## 3. Yêu Cầu Phi Chức Năng
| Yêu Cầu | Trạng Thái | Đánh Giá |
| :--- | :--- | :--- |
| Performance (Load < 2s) | ✅ Đạt | App chạy mượt, sử dụng Lazy Components trong Compose. |
| Security (JWT, Bcrypt) | ✅ Đạt | Đã có AuthInterceptor và lưu trữ an toàn bằng Security-Crypto. |
| Usability (UI/UX) | ✅ Đạt | Đã refactor theo hướng Duolingo + Anki (hiện đại, trực quan). |

---

## 4. Kế Hoạch Bổ Sung (Đề xuất)
1. **Bổ sung dữ liệu**: Thêm các trường Phiên âm (Pronunciation) và Từ liên quan vào Database.
2. **Tính toán Analytics**: Viết thêm UseCase để tính % Accuracy và Retention Rate để lấp đầy Dashboard.
3. **Google Login**: Tích hợp khi cần đưa lên môi trường Production.
