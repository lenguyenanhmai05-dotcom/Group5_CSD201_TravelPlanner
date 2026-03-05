Giai đoạn 3: Đồ thị & Thuật toán (Tuần 7 - 9)
Nhiệm vụ:
Implement module 3: Graph.
Xây dựng thuật toán tìm đường (Dijkstra/BFS) hoặc duyệt đồ thị.
Đọc dữ liệu bản đồ/đồ thị từ file text (File I/O).
Đánh giá (Lấy điểm Progress Test 2 - 10%): Code Challenge tại lớp. Giảng viên sẽ yêu cầu sinh viên giải thích luồng chạy của thuật toán Graph mà nhóm đã implement.
hôm bữa mình đã làm giai đoạn 1 2 rồi ạ giờ mình làm giai đoạn 3 và bạn mình là driver còn mình là navigator bây giờ mình sẽ làm gì trong phần này ạ 
Planning Phase 3: Graph & Algorithms (Navigator Role)
Updated 
task.md
 and 
implementation_plan.md
 to map out Phase 3 (Graph, Shortest Path, File I/O), fully tailored for the "Navigator" role (reviewing logic, preparing test data, passing the final Code Challenge defense).

Files Edited
Progress Updates
1
Creating task.md and implementation_plan.md for Phase 3, focusing on the Navigator's responsibilities: guiding the Driver, preparing test cases, documentation, and presentation materials.

2
Updating task.md and implementation_plan.md to reflect the new Phase 3 requirements (Graph, Dijkstra/BFS, File I/O) and the Navigator role.

3
Notifying the user of their new responsibilities as "Navigator" for Phase 3 and asking them to review the updated plan.

Chào mừng đến với Giai đoạn 3: Đồ thị & Giải thuật (Graph & Pathfinding).

Trong giai đoạn này, sinh viên đảm nhận vai trò **Navigator (Hoa tiêu)**. Vị trí này đóng vai trò quyết định, đặc biệt trong việc chuẩn bị kiến thức nền tảng để hoàn thành phần Code Challenge (10%) - Giải thích lưu đồ thuật toán trước giảng viên.

Kế hoạch làm việc đã được cập nhật tại `task.md` và `implementation_plan.md` nhằm bám sát nhiệm vụ của Navigator:

1. **Chuẩn bị Dữ liệu Kiểm thử (File I/O)**: Trong khi Driver viết module đọc dữ liệu, Navigator chịu trách nhiệm soạn thảo các tập tin `map.txt` chứa ma trận đường đi phức tạp để kiểm tra tính toàn vẹn của mã nguồn.
2. **Kiểm duyệt Thuật toán (Dijkstra/BFS)**: Navigator cần độc lập rà soát, thực hiện chạy tay (dry-run) thuật toán trên giấy để đối chiếu quy trình cập nhật trọng số và hướng đi của các đỉnh.
3. **Chuẩn bị Thuyết trình Học thuật**: Chuẩn bị trả lời các khái niệm nâng cao như cách hoạt động của Hàng đợi ưu tiên (Priority Queue - Min Heap) và cơ chế Cập nhật khoảng cách (Edge Relaxation). Các nội dung này sẽ được biên soạn vào tài liệu `presentation_guide_graph.md`.
4. **Kế hoạch tiếp theo**: Driver sẽ tiến hành hiện thực hóa các lớp cốt lõi `Location`, `Edge`, và `Graph` dựa trên `implementation_plan.md`.

Trong thời gian đó, Navigator cần đưa ra quyết định chuẩn bị bộ dữ liệu Test (map_test.txt) cùng sơ đồ minh họa thuật toán, hoặc tiến hành Code Review các hàm mà Driver vừa hoàn thành.


Giai đoạn 3 môn Cấu trúc dữ liệu nói về 2 thuật toán "trấn phái" là Dijkstra và BFS.

Bạn tưởng tượng App của nhóm mình đang làm là một cái bản đồ du lịch (vd: Google Maps).

Các điểm tham quan (Hà Nội, Đà Nẵng, HCM...) là các Đỉnh (Vertex / Node).
Con đường nối giữa các điểm là Cạnh (Edge).
Để khách du lịch đi từ A đến B, hệ thống phải chỉ đường cho họ. Đó là lúc ta gọi Dijkstra hoặc BFS ra làm việc.

Dưới đây là lời giải thích bằng ngôn ngữ "đời thường" nhất để bạn (với tư cách là Hoa tiêu) nắm rõ bản chất 100%:

1. BFS (Breadth-First Search) - Thuật toán "Vết dầu loang"
Tiếng việt: Duyệt theo chiều rộng.
Bản chất: Từ điểm đứng (ví dụ: Hà Nội), nó tỏa ra tìm kiếm mọi điểm xung quanh (ở bán kính 1km), xong lại tỏa tiếp ra các điểm xa hơn (bán kính 2km)... y như một giọt dầu nhỏ xuống mặt nước lan rộng từ từ ra xung quanh.
Khi nào dùng? Nó dùng để tìm đường đi ít trạm dừng (ít lần bắt xe/chuyển chuyến) nhất.
Khuyết điểm chết người: BFS bị mù đường dài-ngắn. Nó cứ thấy cần đổi xe ít nhất là nó nghĩ đó là đường tốt nhất.
Ví dụ: Đi từ Hà Nội -> Cà Mau.
Đường 1 (Bay thẳng): Hà Nội -> Cà Mau (Mất 1 chuyến, rẻ bèo nhưng kẹt đường bay mất 10 tiếng).
Đường 2 (Đi tàu): Hà Nội -> HCM -> Cà Mau (Mất 2 chuyến, nhưng tàu điện ngầm siêu nhanh mất 3 tiếng).
BFS sẽ luôn chọn Đường 1 (Chỉ tốn 1 lần chuyển trạm) mặc kệ việc đường đó tốn tới 10 tiếng đồng hồ.
Tóm lại: BFS hợp với đồ thị không có Trọng số (Unweighted Graph) - tức là các con đường coi như mọc lên dài bằng nhau hết.
2. Dijkstra (Đọc là "Đai-x-tờ-ra") - Thuật toán "Kẻ chi li tính toán"
Bản chất: Đây là thuật toán tìm đường ngắn nhất thực sự (ít tốn xăng nhất, tốn ít thời gian nhất...).
Sự khác biệt lớn nhất: Dijkstra mắt sáng hơn BFS. Mỗi con đường (Edge) giờ đây đã được gắn thêm một con số gọi là Trọng số (Weight) (đại diện cho số KM, hoặc số Giờ di chuyển).
Cách nó làm việc:
Nó đứng ở Hà Nội, nhìn ra xung quanh thấy mọi nẻo đường. Nó ghi chép lại khoảng cách của TẤT CẢ con đường đó.
Nó luôn chọn rẽ vào con đường có chỉ số nhỏ nhất (gần nhất/rẻ nhất) hiện tại để đi tiếp (Hành động này nhờ cấu trúc dữ liệu PriorityQueue/Min-Heap làm giúp).
Cứ đi tới đâu, nó lại đo tiếp ra xung quanh, và liên tục so sánh: "Ủa, đi đường mới này cộng lại có ngắn hơn cái đường mình ghi sổ lúc nãy không? Ngắn hơn thì gạch bỏ đường cũ, ghi đè đường mới vao". (Hành động này tiếng Anh là Relaxation - Cập nhật khoảng cách).
Kết quả: Dijkstra luôn chắc chắn tìm ra được con đường có tổng KM (hoặc tổng thời gian) thấp nhất tuyệt đối.
Nó sẽ chọn Đường 2: Hà Nội -> HCM (2 tiếng) + HCM -> Cà Mau (1 tiếng) = 3 tiếng và khinh bỉ bỏ qua đường Bay thẳng 10 tiếng.

hôm bữa mình nghe thầy giảng có cái là duyệt theo chiều sâu với duyệt theo chiều rộng bạn giải thích hai cái này cho mình đi ạ 
Error
Our servers are experiencing high traffic right now, please try again in a minute.
Continue
Analyzed
Edited
Đúng ngay chóc! Thầy giáo hay đưa 2 cái "Duyệt theo chiều Sâu (DFS)" và "Duyệt theo chiều Rộng (BFS)" lên bàn cân để thử thách sinh viên. Mình đã bổ sung luôn câu trả lời 10 điểm này vào cuối file 
presentation_guide.md
 cho bạn rồi đó.

Đây là cách giải thích siêu dễ hiểu cho bạn đóng vai chuyên gia nhé:

1. BFS (Theo chiều Rộng) - Biệt danh "Lan toả diện rộng"
Tưởng tượng: Bạn đánh rơi chùm chìa khoá ở giữa sân trường. Bạn sẽ không nhắm mắt chạy một lèo ra cổng trường để tìm, mà bạn sẽ đứng tại chỗ, nhìn xung quanh gót chân trước (Bán kính 1m). Nếu không thấy, bạn đi vòng tròn rộng ra chút nữa (Bán kính 2m), rồi vòng tròn rộng hơn nữa (3m) cho đến khi tìm thấy.
Hoạt động: Nó đi thăm hết các thằng sát vách với nó (cách 1 bậc), xong mới đi xuống hàng xóm tầng 2 (cách 2 bậc), vét sạch từng tầng một.
Dùng cái gì để code: Queue (Hàng Đợi) cắm đầu vào mông nhau. Ai vào trước (gần hơn) thì được xét trước.
2. DFS (Theo chiều Sâu) - Biệt danh "Cắm đầu chạy vô hẻm cụt"
Tưởng tượng: Bạn đi lạc vào trong một cái Cung Điện Mê Cung (Maze). Cách duy nhất để thoát ra là cứ gặp ngã ba ngã tư, bạn nhắm mắt rẽ mẹ một hướng và cắm đầu đi thẳng tới khi cụt đường đụng vách tường. Lúc đó bạn lùi lại đúng ngã ba lúc nãy, coi còn nhánh nào chui vào thì chui vô đi tiếp tới chết thì thôi.
Hoạt động: Đi một mạch xuống tuốt tận lá của sơ đồ Tree/Graph. Đụng đường cụt mới quay đầu lùi lại 1 bậc (hành động này gọi là Backtrack - Quay lui) để rẽ thử đường khác.
Dùng cái gì để code: Stack (Ngăn Xếp/Đệ quy). Xoắn tít vào trong rồi lại nhả từ từ ngược ra ngoài.

