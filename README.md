# MinLish - English Learning App

MinLish là ứng dụng học tiếng Anh chuyên biệt cho việc ôn luyện **600 từ vựng TOEIC** thông qua thuật toán lặp lại ngắt quãng (**Spaced Repetition System - SRS**). Ứng dụng được xây dựng trên nền tảng Android hiện đại, tối ưu cho trải nghiệm người dùng và hiệu quả ghi nhớ.

![MinLish Dashboard](https://via.placeholder.com/800x400?text=MinLish+Preview)

## 🚀 Tính năng chính

- **600+ Từ vựng TOEIC chuẩn:** Dữ liệu được nạp tự động từ CSV với đầy đủ: Phiên âm, Nghĩa tiếng Việt, Giải thích tiếng Anh, Ví dụ minh họa, Hình ảnh và Audio.
- **Thuật toán SRS (SM-2):** Tự động tính toán thời điểm vàng để ôn tập lại từ vựng, giúp tối ưu hóa khả năng ghi nhớ dài hạn.
- **Học qua Flashcards:** Giao diện thẻ lật mượt mà, hỗ trợ phát âm (Audio) và đánh giá độ khó của từ (Again, Hard, Good, Easy).
- **Dashboard thông minh:** Theo dõi tiến độ học tập với các chỉ số thực tế:
    - **Accuracy (Độ chính xác):** Tỷ lệ trả lời đúng.
    - **Retention Rate (Tỷ lệ ghi nhớ):** Phần trăm từ vựng đã chuyển vào bộ nhớ dài hạn.
    - **Streak & XP:** Hệ thống Gamification khuyến khích học tập mỗi ngày.
- **Thư viện từ vựng (Vocabulary):** 
    - Quản lý từ vựng theo chủ đề (Expandable Topic).
    - Bộ lọc thông minh: Từ mới (New), Đang học (Learning), Đến hạn ôn (Due), Thành thạo (Mastered).
- **Offline First:** Toàn bộ dữ liệu được lưu trữ và xử lý local qua Room Database, không cần kết nối internet khi học.

## 🛠 Công nghệ sử dụng

- **Ngôn ngữ:** Kotlin
- **UI Framework:** Jetpack Compose (100%)
- **Kiến trúc:** Clean Architecture + MVVM
- **Dependency Injection:** Dagger Hilt
- **Database:** Room Persistence Library
- **Asynchronous:** Coroutines Flow
- **Navigation:** Compose Navigation
- **Local Storage:** Encrypted Shared Preferences, DataStore

## 📂 Cấu trúc thư mục

```text
com.minlish.app
├── data/           # Layer dữ liệu (Local DB, DAO, Entities, Repository Impl)
├── domain/         # Layer nghiệp vụ (Models, Repository Interfaces, Use Cases)
├── ui/             # Layer giao diện (Composables, ViewModels, Theme)
│   ├── dashboard/  # Màn hình thống kê
│   ├── flashcard/  # Màn hình học thẻ (Learn)
│   ├── vocabulary/ # Quản lý thư viện từ vựng
│   ├── auth/       # Đăng nhập/Đăng ký
│   └── navigation/ # Hệ thống điều hướng
└── di/             # Cấu hình Dependency Injection (Hilt)
```

## ⚙️ Cài đặt & Khởi chạy

1. **Clone repository:**
   ```bash
   git clone https://github.com/ndc87/Minlish_EnglishLeaningApp.git
   ```
2. **Mở dự án:** Sử dụng **Android Studio Jellyfish** (hoặc mới hơn).
3. **Build & Run:** 
   - Đảm bảo thiết bị chạy Android 8.0 (API 26) trở lên.
   - Ứng dụng sẽ tự động nạp 613 từ TOEIC trong lần chạy đầu tiên (Seeding process).

## 📝 Giấy phép
Dự án được phân phối dưới giấy phép MIT.

---
**MinLish** - *Học ít, nhớ nhiều, mục tiêu TOEIC trong tầm tay!*
