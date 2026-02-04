Theme 1: Travel Planner (Hệ thống Quản lý Tour & Bản đồ)
Mô tả: Ứng dụng hỗ trợ đặt tour du lịch và tìm đường đi.
Yêu cầu kỹ thuật:
Hành trình Tour (Linked List): Lưu danh sách địa điểm tham quan. Yêu cầu tính năng thêm/xóa địa điểm bất kỳ trong tour.
Dữ liệu Khách hàng (BST): Quản lý thông tin khách đặt vé bằng cây nhị phân tìm kiếm (theo ID hoặc Tên).
Bản đồ (Graph): Tìm đường đi ngắn nhất giữa 2 thành phố (Dijkstra).
mình làm dự án này và mình đã hoàn thành giai đoạn 1 này 
Giai đoạn 1: Khởi tạo & Cấu trúc Tuyến tính (Tuần 1 - 3)
Nhiệm vụ:
Tạo GitHub Repository, mời giảng viên vào làm Collaborator.
Xây dựng Class cơ bản (Node, Product/User/Item...).
Implement module 1 của đề tài: Linked List / Stack / Queue.
BẮT BUỘC: Viết bộ Unit Test (bằng JUnit hoặc code main test thủ công) để chứng minh code AI viết không bị lỗi NullPointerException.
Đánh giá (Lấy điểm Progress Test 1 - 10%): Chấm dựa trên độ phủ của Test case và lịch sử commit trên GitHub.

và bây giờ là tiếp tục giai đoạn 2 
Giai đoạn 2: Cấu trúc Cây & Hiệu năng (Tuần 4 - 6)
Nhiệm vụ:
Implement module 2: Binary Search Tree (BST).
Chuyển đổi cấu trúc dữ liệu từ List sang Tree để tối ưu tìm kiếm.
Báo cáo so sánh: Đo thời gian chạy (Benchmark) tìm kiếm trên 10.000 phần tử giữa List và BST.
Đánh giá (Lấy điểm Assignment 1 - 10%): Chấm dựa trên báo cáo phân tích hiệu năng, AI Log và sự hiểu biết về đệ quy.
bây giờ bạn thảo luận thôi từ từ hãy code nha bạn nói tất tần tật những gì mà cần phải làm trong giai đoạn 2 i ạ 

Thought for 4s




Analyzed
c:\Group5_CSD201_TravelPlanner\travel_planner
Analyzed
c:\Group5_CSD201_TravelPlanner\travel_planner\src\main\java

Thought for 5s


Exploring Phase 1 Codebase
Explored src/main/java/com/travelplanner. Found entities/TourLocation.java and structures/TourLinkedList.java. Identified missing Customer entity which is required for Phase 2.

Files Edited
Task
Progress Updates
1
Analyzing existing data structures and project layout

2
Reading key files (App.java, TourLinkedList.java) to assess current implementation coverage.

Creating Implementation Plan for Phase 2
Confirmed Phase 1 codebase structure. Identified TourLocation and 
TourLinkedList
. Confirmed lack of Customer entity and BST structure. Checked for existing tests.

Files Edited
Implementation Plan
Progress Updates
1
Writing implementation_plan.md to outline the BST implementation and benchmarking strategy.

Tôi đã phân tích codebase hiện tại của bạn và lên kế hoạch chi tiết cho Giai đoạn 2: Cấu trúc Cây & Hiệu năng.

Dưới đây là những gì chúng ta sẽ cần làm (tất cả đã được tóm tắt trong file implementation_plan.md):

1. Chuẩn bị Dữ liệu (Models)
Hiện tại bạn đã có TourLocation cho List, nhưng Giai đoạn 2 yêu cầu quản lý Khách hàng.

Cần tạo class Customer: Sẽ lưu thông tin như ID (quan trọng để sắp xếp trong cây), Tên, và thông tin đặt vé.
Class này cần implement Comparable để sau này cây BST biết so sánh node nào lớn hơn/nhỏ hơn dựa theo ID.
2. Xây dựng Cấu trúc Cây (Binary Search Tree)
Chúng ta cần viết module này từ đầu (trong package com.travelplanner.structures), gồm:

Node<T>: Tương tự như Linked List node, nhưng thay vì next, node này sẽ có left (liên kết trái - nhỏ hơn) và right (liên kết phải - lớn hơn).
BinarySearchTree: Class quản lý chính.
insert(Customer c): Dùng đệ quy. Nếu ID nhỏ hơn root -> qua trái, lớn hơn -> qua phải.
search(String id): Tìm kiếm đệ quy. Đây là bước "ăn tiền" so với List để đạt độ phức tạp O(log n).
delete(String id): (Nâng cao) Xử lý xoá node (cần thiết nếu khách huỷ vé).
traversal(): Duyệt cây (In-order, Pre-order) để in danh sách khách hàng đã sắp xếp.
3. Bài toán Hiệu năng (Benchmark)
Để chứng minh với giảng viên rằng Tree tốt hơn List, chúng ta cần số liệu thực tế:

Tạo Data giả: Viết 1 script sinh tự động 10,000 khách hàng (ID ngẫu nhiên).
Chạy Test:
Đưa 10k khách vào ArrayList.
Đưa 10k khách vào BST.
Thực hiện tìm kiếm 100-1000 ID ngẫu nhiên trên cả 2 cấu trúc.
Đo thời gian bằng System.nanoTime().
Kết quả kỳ vọng: BST sẽ tìm nhanh hơn List rất nhiều (List phải duyệt từ đầu đến cuối, BST chỉ cần đi vài bước rẽ nhánh).
Bạn xem qua kế hoạch này nhé. Nếu bạn đồng ý với hướng đi này, mình sẽ bắt đầu code phần Customer và khung sườn BST trước.

Implementation Plan
Open
