# Nhật ký phát triển (AI Logs) - Giai đoạn 3: Bàn giao module Graph cho Tester

**Người thực hiện:** Developer Nhóm 5
**Mô-đun:** 3 (Graph - Thuật toán tìm đường & I/O File)
**Trạng thái Code Lõi:** Hoàn thành 100%

---

## 1. Công việc Developer đã hoàn thành
- **Model hoá dữ liệu:** Chuyển đổi thành công dữ liệu bản đồ dạng chữ (`HCM`, `Da Lat`...) sang đối tượng Class trong Java. Các biến định danh đã được định kiểu chuẩn `String`.
- **Thiết kế Graph Entities:** Code xong bộ khung `Location.java` (đỉnh) và `Edge.java` (cạnh).
- **Thuật toán Đồ thị (Core Logic):** Cài đặt thành công thuật toán tìm đường ngắn nhất **Dijkstra** sử dụng cấu trúc cực kỳ tối ưu `PriorityQueue` (Min-Heap) ngay trong class `Graph.java`. Thuật toán lưu vết mượt mà các điểm đi qua.
- **Tiêu chuẩn I/O:** Xây dựng hàm `loadFromFile` xử lý tự động bóc tách file text dạng `<City_A>;<City_B>;<Distance>`. Đã dán vào file thực tế `map_data.txt`.

## 2. Kế hoạch Bàn giao (Handoff to Tester)
Phần lõi Thuật toán đã được verify chuẩn logic Data Structures. Theo đúng chuẩn quy trình làm việc nhóm, Developer sẽ tiến hành bàn giao mảng Kiểm thử (Test) cho Role Tester của nhóm.

**Nhiệm vụ của Tester:**
- **Không chỉnh sửa:** Các file nằm trong package `com.travelplanner.structures.graph` và file `map_data.txt` vì bộ khung đã chốt.
- **Tạo luồng Integration Test (Main App):** Tester tự tạo file `GraphApp.java` (nằm trong package `com.travelplanner.app`).
- **Nội dung code Test thủ công:** Viết hàm `public static void main` để gọi class `Graph`, dùng method `loadFromFile` để nạp bản đồ, và chạy gọi hàm `dijkstra("HCM", "Ha Noi")` để in kết quả đường đi Console ra demo cho Giảng viên xem lấy 10% điểm.
- *(Tùy chọn - Không bắt buộc ngay lúc này):* Nếu nhóm có thời gian và muốn điểm Bonus hoặc chống lỗi Regresssion sau này, Tester có thể tự code thêm một file `GraphTest.java` (dùng thư viện JUnit giống như cách làm của `BinarySearchTreeTest.java`).

---
**Ghi chú cho Giảng viên (nếu đọc Log):** Nhóm áp dụng triệt để phân chia công việc theo Role. Developer tập trung lo Logic/Thuật toán/Tối ưu Big-O. Tester tập trung lo I/O Console, Demo và các Case ngoại lệ.
