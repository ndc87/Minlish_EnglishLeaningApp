# Bảng Đối Chiếu Yêu Cầu và Thực Thi Kỹ Thuật (SRS Mapping)

Tài liệu này thống kê cách thức triển khai và các file tương ứng cho từng yêu cầu chức năng của dự án MinLish.

## 1. Các Module Chính

| Module | Cách thức thể hiện (Implementation) | File tương ứng |
| :--- | :--- | :--- |
| **User Management** | Quản lý Auth, Hồ sơ và Lưu trữ an toàn. | `AuthViewModel.kt`, `ProfileScreen.kt`, `EncryptedAuthStorage.kt` |
| **Vocabulary Management** | CRUD từ vựng, Topic và Import CSV. | `VocabScreen.kt`, `AddWordScreen.kt`, `CsvWordProvider.kt` |
| **Learning Engine (SRS)** | Thuật toán SM-2 và Flow học 3 bước. | `CalculateSM2IntervalUseCase.kt`, `FlashcardViewModel.kt` |
| **Practice Module** | Trắc nghiệm củng cố từ đã học. | `PracticeScreen.kt`, `PracticeViewModel.kt` |
| **Analytics & Progress** | Thống kê số liệu và Hệ thống danh hiệu. | `GetAnalyticsUseCase.kt`, `DashboardScreen.kt`, `DashboardViewModel.kt` |
| **Notification System** | Nhắc học định kỳ qua Worker ngầm. | `StudyReminderWorker.kt`, `NotificationHelper.kt` |

---

## 2. Chi Tiết Chức Năng Hệ Thống

### 3.1 User Management
- **Đăng ký / Đăng nhập:** Giao diện Compose + Reposity xử lý Token.
    - `LoginScreen.kt`, `RegisterScreen.kt`, `AuthRepositoryImpl.kt`
- **Hồ sơ người dùng:** Chỉnh sửa Tên, Mục tiêu, Trình độ.
    - `ProfileScreen.kt`, `UserEntity.kt`, `UserDao.kt`

### 3.2 Vocabulary Management
- **Tạo bộ từ vựng (Topic):** Phân loại từ theo Topic.
    - `TopicEntity.kt`, `TopicDao.kt`, `TopicSelectionScreen.kt`
- **Thêm từ vựng (11 trường):** Form nhập liệu đầy đủ thông tin chi tiết.
    - `AddWordScreen.kt`, `CardEntity.kt`, `AddNewWordUseCase.kt`
- **Import / Export:** Đọc file CSV từ assets và nạp vào DB.
    - `CsvWordProvider.kt`, `DatabaseSeeder.kt`, `FileImportExportUtil.kt`

### 3.3 Learning Module (Trọng tâm)
- **Flashcard Learning:** Lật thẻ 3D (Mặt trước: từ, Mặt sau: nghĩa + ví dụ + ảnh).
    - `FlashcardScreen.kt` -> `FlashcardStudyContent`
- **Spaced Repetition (SRS):** Thuật toán SM-2 tính `nextDate` và `easeFactor`.
    - `CalculateSM2IntervalUseCase.kt`, `ReviewEntity.kt`, `ReviewDao.kt`
- **Daily Learning Plan:** Đếm số từ đã học hôm nay và từ cần ôn.
    - `GetAnalyticsUseCase.kt` (Logic tính `todayCount`, `dueCount`)

### 3.4 Progress Tracking
- **Dashboard:** Hiển thị Streak, XP và các chỉ số thông minh.
    - `DashboardScreen.kt`, `DashboardViewModel.kt`
- **Accuracy & Retention Rate:** Tính % đúng và % ghi nhớ thực tế.
    - `GetAnalyticsUseCase.kt` -> `AppAnalytics` data class
- **Hệ thống danh hiệu (Gamification):** Badge tự động sáng khi đạt mốc.
    - `DashboardViewModel.kt` -> `updateBadges()`, `Badge` data class

### 3.5 Notification System
- **Push Notification:** Gửi thông báo định kỳ hàng ngày.
    - `StudyReminderWorker.kt`, `MainActivity.kt` (Logic schedule)
    - `NotificationHelper.kt` (Tạo channel và build notification)

---

## 3. Yêu Cầu Phi Chức Năng

### 4.1 Performance
- **Load < 2s:** 
    - Xử lý tính toán ở luồng nền: `Dispatchers.Default` trong UseCases.
    - Truy vấn DB tối ưu qua Flow và Indexing: `CardDao.kt`, `ReviewDao.kt`.
    - Load ảnh mượt mà: Tích hợp thư viện **Coil** trong `VocabScreen.kt` và `FlashcardScreen.kt`.

### 4.2 Security
- **JWT & Encryption:** 
    - `EncryptedAuthStorage.kt` (Sử dụng Android Security Crypto để lưu Token).
    - `AuthInterceptor.kt` (Tự động đính kèm token vào Header API).

### 4.3 Usability
- **Giao diện hiện đại:** 
    - 100% Jetpack Compose với Material 3.
    - Playful Design: Bo tròn 16dp, màu sắc rực rỡ, icon sinh động.
    - `DashboardScreen.kt`, `PracticeScreen.kt` (Giao diện thẻ trắc nghiệm).

---
*Tài liệu này xác nhận mã nguồn đã thực thi đầy đủ các yêu cầu đề ra.*
