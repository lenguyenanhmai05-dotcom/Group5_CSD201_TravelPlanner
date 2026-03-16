# Cẩm nang Thuyết trình: Cấu trúc dữ liệu Graph 🗺️

Tài liệu này giúp bạn tự tin giải thích phần Graph cho thầy cô, tập trung vào tại sao chúng ta chọn code như vậy và cách nó vận hành.

## 1. Kiến trúc hệ thống
*   **Cấu trúc lưu trữ:** Chúng ta sử dụng **Adjacency List (Danh sách kề)** thông qua `Map<String, List<Edge>>`.
    *   *Tại sao?* Vì bản đồ các tỉnh miền Trung là đồ thị thưa (ít đường nối chéo), dùng danh sách kề sẽ tiết kiệm bộ nhớ hơn nhiều so với Ma trận kề (Matrix).
*   **Loại đồ thị:** Đồ thị vô hướng (Undirected Graph). Mỗi khi thêm một con đường, code sẽ tự động thêm 2 chiều để đi lại được cả hai đầu.

## 2. Thuật toán Dijkstra (Tìm đường ngắn nhất)
*   **Mục tiêu:** Tìm quãng đường có tổng số km nhỏ nhất (không phải số trạm ít nhất).
*   **Cơ chế vận hành:**
    1.  Khởi tạo tất cả khoảng cách là vô cực (`Double.MAX_VALUE`).
    2.  Dùng **PriorityQueue (Hàng đợi ưu tiên)** để luôn chọn trạm nào gần nhất để xét trước.
    3.  **Relaxation (Thư giãn cạnh):** Nếu tìm thấy đường mới ngắn hơn đường cũ đã lưu, ta cập nhật lại.

## 3. Cách giải thích các file Test
*   **GraphTest:** Là các bài đo kiểm tự động (Unit Test). Nó dùng dữ liệu giả lập (như "Dieu Tri", "Quy Nhon City") để xác nhận các hàm `addLocation`, `addEdge` hoạt động chính xác trước khi chạy thật.
*   **GraphOutputTest:** Là phần Demo trình diễn. Nó đọc file `map_test.txt` và in kết quả ra màn hình Console để thầy cô thấy được đường đi cụ thể: *Tên trạm -> Khoảng cách -> Tổng km*.

---
*Lưu ý: Luôn nhấn mạnh rằng code của bạn có xử lý các trường hợp lỗi như: địa điểm không tồn tại hoặc không có đường nối giữa 2 điểm.*
