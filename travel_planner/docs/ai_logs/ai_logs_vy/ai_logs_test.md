# Tìm hiểu về Unit Test

## 1. Test là gì? (Định nghĩa bình dân)
Test trong lập trình không phải là "ngồi bấm bấm thử phần mềm".
Mà Test ở đây là "Dùng code để kiểm tra code".

Hãy tưởng tượng bạn Driver là người xây nhà.
Còn bạn Navigator (Tester) là người nghiệm thu.
 * Bạn không tin lời ông Driver nói là "nhà ngon lắm".
 * Bạn phải mang thước ra đo, mang búa ra gõ thử xem tường có nứt không.
 * Cái file `TourLinkedListTest.java` chính là cái "búa" và cái "thước" đó.

## 2. Nguyên lý hoạt động của Test (3 bước)
Mọi bài test trong file của bạn đều chạy theo quy tắc "So sánh đáp án" giống hệt giáo viên chấm bài thi:
 * **Bước 1 - Giả lập (Input):** Bạn tạo ra một đề bài.
   * *Ví dụ:* Tạo một danh sách có A và B.
 * **Bước 2 - Thực thi (Action):** Bạn bắt code của Driver chạy.
   * *Ví dụ:* Gọi lệnh `remove("A")` (Xóa A).
 * **Bước 3 - Khẳng định (Assert):** Bạn so sánh Kết quả thực tế với Đáp án mẫu.
   * *Đáp án mẫu (Kỳ vọng):* Xóa A xong thì phải còn B.
   * *Thực tế:* Code chạy ra cái gì?
   * Nếu **Thực tế == Đáp án mẫu** -> **XANH (Passed)** -> Code ngon.
   * Nếu **Thực tế != Đáp án mẫu** -> **ĐỎ (Failed)** -> Code dởm/Lỗi.

## 3. Tại sao trong bài này lại cần Test?
Vì bài của bạn làm về Linked List (Danh sách liên kết).
 * Cái này giống như một sợi dây xích.
 * Nếu code sai một tí (ví dụ quên nối dây khi chèn vào giữa), thì cả sợi dây xích bị đứt.
 * Mắt thường nhìn code rất khó thấy đứt ở đâu.
 * Nên bạn cần chạy Test để máy tính dò xem các "mắt xích" (node) có nối liền mạch không.

## 4. Cách nói cho giáo viên hiểu (Tóm tắt)
Khi thầy hỏi "Test để làm gì?", bạn chỉ cần trả lời ngắn gọn:
> "Thưa thầy, Test là để đảm bảo chất lượng. Thay vì tin tưởng code chạy đúng, em viết các kịch bản kiểm thử tự động để máy tính xác nhận rằng: Dù thêm, sửa hay xóa, cấu trúc dữ liệu vẫn hoạt động chính xác và không bị lỗi."

Bạn hiểu đơn giản vậy là tự tin rồi!
