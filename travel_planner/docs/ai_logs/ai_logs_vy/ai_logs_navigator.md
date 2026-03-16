Ok, mình **nói rất chi tiết – từng việc cụ thể – theo đúng dự án Travel Planner của nhóm bạn**, để bạn **biết rõ Navigator làm gì mỗi ngày**, không còn mơ hồ nữa nhé 👇
(Bạn có thể dùng để **báo leader / viết report / trả lời thầy**)

---

# 🧭 NAVIGATOR LÀM GÌ TRONG DỰ ÁN TRAVEL PLANNER?

> **Driver = người gõ code**
> **Navigator = người suy nghĩ, kiểm tra, định hướng, đảm bảo code đúng & đủ yêu cầu**

Navigator **KHÔNG ngồi chơi**, mà làm việc **song song – rất quan trọng**.

---

## I. NAVIGATOR LÀM GÌ TRƯỚC KHI CODE?

### 1️⃣ Đọc kỹ yêu cầu đề bài

Navigator phải hiểu rõ:

* **Chỉ dùng Linked List tự cài**
* Không được dùng `java.util.LinkedList`
* Phải có **thêm / xóa bất kỳ vị trí**
* Phải xử lý **edge case**

📌 Việc cụ thể:

* Gạch đầu dòng yêu cầu
* Nhắc Driver: “Hàm này thiếu edge case”, “Hàm kia chưa đúng đề”

---

### 2️⃣ Lên logic trước khi Driver code

Navigator sẽ:

* Vẽ sơ đồ Linked List trên giấy
* Xác định:

  * `head` thay đổi khi nào?
  * Node đầu / giữa / cuối khác nhau ra sao?

📌 Ví dụ Navigator nói với Driver:

> “Xóa node đầu thì cập nhật `head = head.next`,
> xóa node giữa thì cần `prev.next = current.next`”

---

## II. NAVIGATOR LÀM GÌ TRONG KHI CODE?

### 3️⃣ Review từng hàm Linked List

Khi Driver code xong 1 hàm, Navigator kiểm tra:

#### Ví dụ: `removeByName(String name)`

Navigator phải hỏi:

* Nếu list rỗng → sao?
* Nếu node cần xóa là **node đầu**?
* Nếu node cần xóa là **node cuối**?
* Nếu không tìm thấy → chương trình có crash không?

📌 Nếu phát hiện lỗi:

> “Thiếu case xóa node đầu → sẽ NullPointerException”

---

### 4️⃣ Soi lỗi logic (KHÔNG PHẢI lỗi cú pháp)

Navigator **không quan tâm dấu chấm phẩy**, mà quan tâm:

* Con trỏ `next` có đúng không
* Node có bị “mất” không
* Có tạo vòng lặp vô hạn không

📌 Ví dụ lỗi Navigator hay bắt:

* Quên cập nhật `prev`
* Duyệt `while (current.next != null)` → bỏ sót node cuối
* Xóa xong nhưng không `return`

---

## III. NAVIGATOR LÀM GÌ SAU KHI CODE XONG?

### 5️⃣ Viết & đề xuất TEST CASE (RẤT QUAN TRỌNG)

Navigator chịu trách nhiệm **nghĩ test**, Driver chạy code.

#### Navigator phải đưa ra test như:

```text
Test 1: Xóa khi danh sách rỗng
Test 2: Xóa khi chỉ có 1 node
Test 3: Xóa node đầu
Test 4: Xóa node cuối
Test 5: Xóa node giữa
```

📌 Navigator kiểm tra:

* Output in ra có đúng không?
* Có crash không?
* Có đúng yêu cầu đề không?

---

### 6️⃣ Xác nhận kết quả cuối

Navigator nói câu này với nhóm:

> “Logic OK – edge case đủ – đúng yêu cầu Progress Test 1”
👉 Lúc này mới **được phép commit**

---

## IV. NAVIGATOR & AI INTERACTION LOG

### 7️⃣ Navigator KHÔNG viết prompt, nhưng làm gì?

Navigator sẽ:

* Đọc code AI sinh ra
* Phát hiện:

  * AI thiếu edge case nào
  * AI dùng cách chưa tối ưu
* Ghi vào AI log:

> “AI-generated code did not handle the case when the list has only one node.”

📌 Điều này giúp thầy thấy:
👉 **Nhóm không phụ thuộc mù quáng vào AI**

---

## V. NAVIGATOR TRONG BÁO CÁO GHI GÌ?

Bạn có thể ghi **đoạn này (chuẩn report)**:

> As a Navigator, I was responsible for reviewing the Linked List logic, identifying edge cases, proposing and validating test cases, and ensuring that the implementation met the CSD201 requirements. I also reviewed AI-generated code and documented issues and fixes in the AI interaction log.

---

## VI. TÓM LẠI – NẾU HỎI: “NAVIGATOR LÀM GÌ?”

👉 Bạn trả lời gọn như sau:

> Navigator chịu trách nhiệm kiểm tra logic Linked List, phát hiện lỗi trong các trường hợp đặc biệt, đề xuất test case, đảm bảo code đúng yêu cầu đề bài và xác nhận chất lượng trước khi commit.

---

Nếu bạn muốn, mình có thể:

* 🔥 Viết **checklist Navigator theo từng ngày**
* 🔥 Giả lập **thầy hỏi Navigator – bạn trả lời thế nào**
* 🔥 Soạn **bảng phân công Navigator để dán vào report**

👉 Chỉ cần nói: **“Cho mình checklist Navigator chi tiết”**