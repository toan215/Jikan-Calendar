# ⚠️ Migration: application.properties → .env

## Tóm tắt thay đổi

Dự án đã **migrate từ `application.properties` sang `.env`** để tăng cường bảo mật và tương thích với deployment.

## ❌ File đã XÓA (không còn dùng nữa):

- `src/main/resources/application.properties`

## ✅ File MỚI cần dùng:

- `.env` (file chứa credentials thực tế - **KHÔNG commit lên Git**)
- `.env.example` (file mẫu - có thể commit)

## 📝 Các file đã được cập nhật:

### 1. `ConfigLoader.java`

- Đã migrate từ `Properties` sang `Dotenv`
- Giờ đọc từ `.env` file thay vì `application.properties`
- Tự động fallback sang system environment variables (cho production)

### 2. Các service SỬ DỤNG ConfigLoader:

- ✅ `WheatherService.java` - Dùng `API_WEATHER`
- ✅ `EmbeddingService.java` - Dùng `API_TOKEN`
- ✅ `LLM.java` - Dùng `GEMINI_API_KEY`

**Không cần sửa** các file này vì chúng vẫn gọi `ConfigLoader.get()` như bình thường.

## 🔧 Hướng dẫn setup cho dev:

### Bước 1: Tạo file `.env`

```bash
cp .env.example .env
```

### Bước 2: Điền thông tin vào `.env`

Mở file `.env` và điền các giá trị thực tế:

```bash
# Database Configuration
DB_TYPE=postgres
DB_HOST=your-supabase-host.com
DB_PORT=5432
DB_NAME=postgres
DB_USER=postgres
DB_PASSWORD=your_actual_password
DB_SCHEMA=public

# API Keys (điền keys thật vào đây)
OPENAI_API_KEY=sk-...
QDRANT_API_KEY=...
QDRANT_CLUSTER_URL=https://...
API_WEATHER=...
API_TOKEN=hf_...
GEMINI_API_KEY=...
EMAIL_USERNAME=your@email.com
EMAIL_PASSWORD=your_app_password
```

## 🚀 Deploy lên Render:

**KHÔNG cần** file `.env` trên Render. Thay vào đó:

1. Vào Render Dashboard → Service → Environment
2. Thêm từng biến môi trường:

   - `DB_TYPE` = `postgres`
   - `DB_HOST` = `your-host`
   - `OPENAI_API_KEY` = `sk-...`
   - ... (tất cả variables trong `.env.example`)

3. Render sẽ tự động set system environment variables
4. `ConfigLoader` sẽ tự động đọc từ system env nếu không tìm thấy `.env`

## 🔐 Bảo mật:

### ✅ Đã làm:

1. Xóa `application.properties` khỏi Git tracking
2. Thêm `.env` vào `.gitignore`
3. Tạo `.env.example` làm template (không chứa credentials thật)

### ⚠️ CẦN LÀM NGAY:

**API keys cũ đã bị lộ trên GitHub history!**

Bạn cần **regenerate tất cả keys**:

- [ ] OpenAI API Key → https://platform.openai.com/api-keys
- [ ] Qdrant API Key
- [ ] HuggingFace Token → https://huggingface.co/settings/tokens
- [ ] Gemini API Key → https://aistudio.google.com/app/apikey
- [ ] Weather API Key → https://www.weatherapi.com/my/
- [ ] Email App Password

## 📚 Tài liệu tham khảo:

- dotenv-java: https://github.com/cdimascio/dotenv-java
- Render Environment Variables: https://render.com/docs/environment-variables
