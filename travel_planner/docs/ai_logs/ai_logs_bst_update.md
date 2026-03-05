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


nghệ thuật cái con khỉ á bạn đừng có gộp dùm mình cái viết lại chỗ đó đi 
Refactoring BST Deletion Logic
Separated Case 1 (leaf) and Case 2 (one child) in 

BinarySearchTree.java
's deletion method for clarity, and revised the 
presentation_guide.md
 to explain the new, straightforward logic.

Files Edited
Progress Updates
1
Un-merging the deletion logic in BinarySearchTree.java to make Cases 1, 2, and 3 explicit per the user's request.

2
Refactoring the deleteRec method in BinarySearchTree.java to explicitly separate Case 1 and Case 2, and replacing the "merged" explanation in the presentation guide with a clear 3-case breakdown.

3
Updating the presentation guide to reflect the newly refactored, explicitly separated deletion logic.

4
Replacing the merged Case 1 & 2 explanation in the presentation guide with the explicitly separated Case 1 and Case 2 logic.

5
Notifying the user that the code has been successfully refactored and the presentation guide has been updated.

Bình tĩnh bạn ơi, thầy căng thì mình "cương" lại thôi! Lỗi của mình là nghĩ cách gộp code đó thông minh thì mang đi khoe, chứ không ngờ đi thi thì tính an toàn, dễ hiểu phải đặt lên hàng đầu.

Mình đã làm theo đúng như ý bạn rồi nhé:

Sửa lại code BinarySearchTree.java:

Mình đã đập bỏ cái đoạn gộp thần thánh kia đi.
Dòng 103-110 bây giờ đã phân định rạch ròi:
Case 1 (Node Lá): if (root.left == null && root.right == null) { return null; }
Case 2 (Node 1 con): Xử lý bên trái, hoặc bên phải riêng rẽ.
Giờ thì thầy vặn chỗ nào bạn chỉ thẳng dòng đó!
Viết lại "phao" trong presentation_guide.md:

Mình cũng xoá xạch bài giải thích cái đoạn code gộp lúc nãy đi luôn.
Thay vào đó, ở phần cuối file (mục 🤯 Giải thích đoạn code), mình viết thành "Giải thích đoạn code Xoá (Trường hợp 1 & Trường hợp 2)" bám sát theo code vừa sửa.
Văn phong trình bày với thầy vẫn cực kì tự tin, rành mạch:
Case 1: Rút dây, ném null về cho Garbage Collector hốt xác.
Case 2: Ném đứa cháu thẳng lên cho ông nội bắt lấy.
Bạn check lại code file BinarySearchTree.java ở dòng 103 và mở presentation_guide.md soát lại xem bây giờ đã ưng ý để lên thớt chưa nhé! Cần tháo gỡ hay giải thích góc kẹt nào nữa cứ quẳng cho mình.
