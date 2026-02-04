### Cập nhật Giai đoạn 2: Kiểm thử & Hiệu năng (04/02/2026)

#### 1. Kết quả Benchmark (10,000 Khách hàng)
- ArrayList (Dò tìm tuyến tính): ~732.85 ms
- Binary Search Tree (Tìm kiếm nhị phân): ~3.05 ms
- Kết luận: BST nhanh hơn 240.04 lần. Cấu trúc cây giúp giảm độ phức tạp từ O(n) xuống O(log n).

#### 2. Kết quả Unit Test (JUnit 5)
Mình đã triển khai bộ Test BinarySearchTreeTest.java với 8 trường hợp kiểm thử quan trọng:
- testSearchInEmptyTree: Đảm bảo không lỗi NPE khi cây rỗng.
- testInsertAndSearch: Kiểm tra tính đúng đắn của dữ liệu.
- testInsertDuplicate: Xử lý trùng lặp.
- testDeleteLeafNode: Xoá nút lá.
- testDeleteNodeWithOneChild: Xoá nút có 1 con.
- testDeleteNodeWithTwoChildren: Xoá nút có 2 con.
- testDeleteNonExistent: Đảm bảo không crash khi xoá ID không có thật.
- testInOrderTraversal: Kiểm tra cấu trúc phân cấp.

Kết quả chạy Test: 8/8 Passed.
