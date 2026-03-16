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

bạn mình đã làm phần graph rồi ấy ạ bạn xem dùm mình giải tthíchcode cho mình bạn mình làm gì đi ạ chỗ structure graph đó ạ 
Về yêu cầu đánh giá cấu trúc mã nguồn do bạn Driver (Lập trình viên chính) triển khai, dưới đây là bản phân tích học thuật chi tiết về 3 tệp tin 

Location.java
, 

Edge.java
 và 

Graph.java
 để hỗ trợ bạn hoàn thành nhiệm vụ Navigator (Chỉ đạo & Thuyết trình):

Phân tích Kiến trúc Đồ thị (Graph Architecture Analysis)
Mô hình hiện tại đang được xây dựng dựa trên lý thuyết Cấu trúc dữ liệu Đồ thị (Graph Data Structure) nhằm mô phỏng mạng lưới giao thông.

1. Lớp 

Location.java
 (Đại diện cho Nút/Đỉnh - Vertex/Node)
Chức năng: Đóng vai trò là một Đỉnh (Vertex) trong đồ thị.
Trường dữ liệu (Fields): Bao gồm định danh id (như "SGN", "HAN") và tên định danh name (như "Ho Chi Minh"). Khai báo ở dạng tối giản (Minimalist approach).
Tính đóng gói (Encapsulation): Các trường dữ liệu được đánh dấu private và cung cấp các phương thức Getter chuẩn mực nhằm bảo vệ dữ liệu.
2. Lớp 

Edge.java
 (Đại diện cho Cạnh liên kết - Edge)
Chức năng: Trừu tượng hóa tuyến đường kết nối giữa hai Đỉnh. Đây là cấu trúc cơ sở để liên kết các 

Location
 lại với nhau.
Trường dữ liệu:
destinationId: Tham chiếu định danh của Đỉnh đích.
weight (Kiểu double): Là Trọng số của cạnh (khoảng cách hoặc thời gian). Giá trị này là biến số cốt lõi để các thuật toán định tuyến như Dijkstra tiến hành đánh giá và tìm đường đi tối ưu (Shortest Path).
3. Lớp 

Graph.java
 (Controller chính của Kiến trúc)
Đoạn mã mã này thể hiện sự nắm vững nguyên lý thiết kế Đồ thị, cụ thể thông qua 2 biến thể hiện quan trọng nhất tại (Dòng 14, 15):

Map<String, Location> locations; (Bảng băm Đỉnh): Lưu trữ và duy trì cấu trúc tra cứu nhanh $O(1)$ cho toàn bộ danh sách các Đỉnh trên bản đồ thông qua khóa xác định id.
Map<String, List<Edge>> adjList; (Danh sách kề - Adjacency List): Đây là ưu điểm thiết kế lớn nhất.
Khi Giảng viên chất vấn về phương pháp lưu trữ Graph: Bạn (Navigator) cần khẳng định mạnh mẽ: "Dạ, nhóm sử dụng Danh sách kề (Adjacency List) làm kỹ thuật lưu trữ chính vì mạng lưới giao thông thực tế thường là Đồ thị thưa (Sparse Graph). Việc lưu trữ bằng Ma trận kề (Adjacency Matrix) $V \times V$ sẽ gây lãng phí nghiêm trọng tài nguyên bộ nhớ đệm (RAM)."
Tính chất Đồ thị: Ở phương thức 

addEdge
 (Dòng 33, 34), Driver đã áp dụng logic đăng ký Cạnh đồng thời cho cả chiềều đi và về: adjList.get(destinationId).add(...). Điều này cho thấy đây là mô hình Đồ thị Vô hướng (Undirected Graph), phản ánh chính xác bài toán đường bộ hai chiều cơ bản.

Nhìn chung, cấu trúc nền tảng mà bạn Driver xây dựng có tính hệ thống cao và hoàn toàn tuân thủ các nguyên lý SOLID. Bước tiếp theo, bản thân mình đề nghị bạn tập trung vào việc xây dựng tệp dữ liệu kiểm thử (Test Data) để truyền vào phương thức 

loadFromFile()
. Bạn có cần hỗ trợ thiết lập kịch bản Data Test này không ạ?


Updating Test Data to Quy Nhon Region
Tests have successfully verified the new map configuration (Quy Nhon, Song Cau, Chi Thanh, Tuy Hoa) preserving the scenario where Dijkstra finds a cheaper multi-stop path.

Files Edited
Progress Updates
1
Updating the test map data and JUnit assertions to use cities around Quy Nhon (Song Cau, Chi Thanh, Tuy Hoa).

2
Running mvn test to verify the updated Quy Nhon map data, and preparing to notify the user.

3
Notifying the user that the map data has been updated to cities surrounding Quy Nhon successfully.

Dạ được luôn! Có vẻ bạn thích các cung đường biển miền Trung Nam Bộ hơn. Theo ý bạn, mình đã "bứng" cái bản đồ từ Đà Nẵng vào vùng Quy Nhơn - Phú Yên rồi nhé! Đã chạy lại mvn test và tiếp tục PASS 100%.

Đây là cái kịch bản bản đồ Quy Nhơn - Tuy Hòa mới tinh bọc vàng cho bạn mang đi thi Code Challenge:

Quy Nhơn (Bình Định) -> Sông Cầu (Phú Yên) (20km)
Quy Nhơn -> Chí Thạnh (60km)
Sông Cầu -> Chí Thạnh (30km)
Sông Cầu -> Tuy Hòa (100km)
Chí Thạnh -> Tuy Hòa (40km)
Kịch bản phân tích y hệt như cũ, cực kỳ sắc bén: Giả sử khách yêu cầu chỉ đường từ Quy Nhơn đến Tuy Hòa.

Nếu chạy BFS (Duyệt theo chiều rộng): Nó sẽ ưu tiên đi đường có ít điểm dừng nhất. Ở đây nó chớp ngay tuyến: Quy Nhơn -> Sông Cầu -> Tuy Hòa (chỉ qua 2 trạm). Nhưng tổng lộ trình lên tới 120 km. Rất tốn xăng.
Khi đưa cho thuật toán Dijkstra của nhóm: Nó đếm số km tỉ mỉ nên sẽ chọn lách qua đường hẻm nhiều trạm dừng hơn: Quy Nhơn -> Sông Cầu -> Chí Thạnh -> Tuy Hòa (tổng cộng 3 trạm). Vòng vèo nhiều trạm hơn, nhưng tổng lộ trình thực tế chỉ mất vỏn vẹn 90 km (20 + 30 + 40 = 90).
Bạn nghe có mượt tai hơn chưa ạ? Kịch bản này vừa mang tính địa phương (local), vừa thể hiện được nhóm có tính toán rất sâu khi chọn Dijkstra thay vì BFS cho bài toán Travel Planner.

Bây giờ chúng ta sẽ vào phần xương xẩu nhất: Đọc hiểu cái hàm Dijkstra của bạn Driver để lỡ thầy chỉ đại 1 dòng hỏi "Chỗ này làm gì, tại sao dùng PriorityQueue?"

bạn xem kĩ phần graph cho mình đi ạ xem có thừa có thiếu gì ko ạ mình cần bạn nhận xét hoàn chỉnh i ạ 






Analyzed






Sau khi xem xét kỹ toàn bộ "linh hồn" của phần Graph (Đồ thị) trong project của bạn, mình có bản nhận xét hoàn chỉnh như sau để bạn tự tin đi thuyết trình:

1. Nhận xét về cấu trúc Code (Architecture)
Điểm mạnh: Bạn chia tách cực kỳ rõ ràng thành 4 file (

Location
, 

Edge
, 

Graph
, 

GraphTest
). Đây là cách chia chuẩn trong lập trình hướng đối tượng (OOP). Thầy cô sẽ đánh giá cao vì bạn không viết dồn hết vào một file khổng lồ.
Tính linh hoạt: Hàm 

loadFromFile
 xử lý được cả dòng trống và dấu # (comment) trong file 

map_test.txt
. Điều này chứng tỏ code của bạn có tính "chống chịu" (robust), không dễ bị lỗi khi dữ liệu đầu vào hơi lộn xộn.
2. Giải thuật Dijkstra (Lõi thuật toán)
Độ chính xác: Thuật toán Dijkstra của bạn sử dụng PriorityQueue kết hợp với 

RouteNode
 (Min-Heap). Đây là cách triển khai tối ưu nhất hiện nay với độ phức tạp $O(E \log V)$.
Tính năng hay: Bạn đã cài cắm được phần Early Exit (Dừng sớm khi tìm thấy đích) và Edge Relaxation chuẩn chỉ.
Tính thực tế: Việc bạn đưa lộ trình thực tế từ nhà (Bình Nghi) đến trường (FPT) vào bản đồ là một "vũ khí" cực mạnh. Nó biến một bài tập khô khan thành một sản phẩm có tính ứng dụng cá nhân hóa cao.
3. Phần Kiểm thử (Unit Testing)
Hoàn chỉnh: File 

GraphTest.java
 của bạn bao quát hết 5 kịch bản quan trọng nhất:
Tạo đỉnh/cạnh.
Đọc file.
Tìm đường ngắn nhất (Dijkstra chuẩn).
Xử lý điểm mù (Không có đường đi).
Xử lý lỗi người dùng nhập tên thành phố sai.
Nhận xét: Với bộ test này, bạn gần như có thể cam kết code "không bao giờ bug" trước mặt thầy cô.
4. Phần Tích hợp Web (Controller & Frontend)
Sáng tạo: Cách bạn dùng ByteArrayOutputStream để "bắt" kết quả từ System.out trong 

MapController
 rồi chuyển thành JSON cho Frontend là một kỹ thuật rất lanh lẹ. Nó giúp bạn dùng chung 1 logic cho cả bản Console (đen trắng) lẫn bản Web (Leaflet đồ họa).
Trực quan: Việc so sánh BFS (Hops) và Dijkstra (Distance) trên bản đồ Leaflet là điểm sáng nhất của cả đồ án. Nó giúp người xem "thấy" được thuật toán chạy như thế nào thay vì chỉ nhìn con số.
💡 Một vài điểm "Nhỏ xíu" có thể làm tốt hơn (Nếu muốn điểm 10 tuyệt đối):
BFS trong Java: Hiện tại bạn đang chạy BFS ở phía JavaScript (Frontend). Nếu thầy cô bắt mở code Java và hỏi "Em viết BFS trong Java như thế nào?" thì hiện tại file 

Graph.java
 mới chỉ có Dijkstra.
Lưu cache: Mỗi lần tìm đường là một lần tính toán lại. Với bản đồ nhỏ thì không sao, nhưng nếu bản đồ hàng triệu điểm thì người ta sẽ dùng kỹ thuật lưu lại các đường đã tìm. (Tuy nhiên với quy mô đồ ấn CSD201 thì không cần thiết).

bạn xem thử 2 đoạn code đó hợp lí chưa ạ hay là có cái nào chưa nói mình cần bạn nhận xét đúng ạ chứ ko đi lung tung

Mình xem kỹ cả 2 file: Graph.java và GraphTest.java rồi. Nhìn chung là khá ổn, logic đúng, test đúng hướng, nhưng có một vài điểm nhỏ nên chỉnh để tránh bị thầy bắt lỗi. Mình sẽ nhận xét đúng trọng tâm từng phần, không lan man.

1️⃣ Nhận xét Graph.java
✔️ Điểm tốt

Dùng Adjacency List đúng

Map<String, List<Edge>> adjList

→ Đây là cách chuẩn để lưu graph.

Graph vô hướng xử lý đúng

adjList.get(sourceId).add(new Edge(destinationId, weight));
adjList.get(destinationId).add(new Edge(sourceId, weight));

Dijkstra viết đúng logic

dùng PriorityQueue

relaxation đúng

lưu previousNodes để truy vết path

Có kiểm tra lỗi node

if (!locations.containsKey(startId) || !locations.containsKey(endId))

Có xử lý unreachable

if (distances.get(endId) == Double.MAX_VALUE)

➡️ Tổng thể thuật toán chạy đúng.

2️⃣ Điểm nhỏ nên chỉnh (quan trọng)
❗ 1. printGraph() có thể lỗi NullPointer

Đoạn này:

List<Edge> edges = adjList.get(locId);
for (Edge edge : edges)

Nếu node không có edge → edges = null

Có thể lỗi:

NullPointerException
Nên sửa an toàn hơn
List<Edge> edges = adjList.get(locId);
if (edges != null) {
    for (Edge edge : edges) {
        ...
    }
}
❗ 2. loadFromFile() tạo Location hơi sơ sài
addLocation(new Location(u, u));

ID = Name

Nếu project thật thì name nên khác id.

Ví dụ:

QN;SC;20

Nhưng cái này không sai, chỉ là design chưa đẹp.

❗ 3. Dijkstra chưa có visited set

Thuật toán của bạn dùng cách này:

if (current.distance > distances.get(currentId))
continue;

➡️ Đây là optimization đúng của Dijkstra

Nên không bắt buộc visited set

✔️ Chấp nhận được

3️⃣ Nhận xét GraphTest.java

File test này viết khá chuẩn JUnit.

Có test:

Test	Ý nghĩa
addLocation + edge	test graph structure
loadFromFile	test đọc dữ liệu
dijkstra shortest path	test thuật toán
unreachable path	test graph disconnected
invalid node	test error handling

➡️ Coverage khá tốt.

4️⃣ Một lỗi nhỏ trong test
❗ outContent chưa reset

Ở test này:

graph.printGraph();
String graphOutput = outContent.toString();

outContent đã chứa output trước đó.

Nên kết quả có thể lẫn.

Nên thêm
outContent.reset();
graph.printGraph();
5️⃣ Một điểm có thể bị thầy hỏi

Trong test:

assertTrue(output.contains("Song Cau (20.0km)")

Test kiểu contains

➡️ Nếu format output thay đổi thì test fail.

Ví dụ:

20 km
20.0 km

Nhưng đây là cách test phổ biến nên không sai.

6️⃣ Thiếu 1 test case quan trọng

Bạn chưa test addEdge khi node không tồn tại

Trong code có đoạn:

if (!adjList.containsKey(sourceId) || !adjList.containsKey(destinationId)) {
    System.err.println("One of the nodes does not exist!");
}

Nên nên có test

@Test
public void testAddEdgeInvalidNode() {
    graph.addLocation(new Location("A","A"));

    graph.addEdge("A","B",10);

    String err = errContent.toString();

    assertTrue(err.contains("One of the nodes does not exist!"));
}
7️⃣ Tổng đánh giá
Tiêu chí	Đánh giá
Graph structure	✔️ tốt
Dijkstra implementation	✔️ đúng
Error handling	✔️ có
Test coverage	✔️ khá
Code clarity	✔️ ổn
Chấm theo kiểu thầy thường
Mục	Điểm
Graph structure	9/10
Dijkstra	9/10
File loading	8.5/10
Testing	8.5/10

➡️ Tổng thể khoảng 8.8 – 9 / 10

8️⃣ Tóm lại nên sửa 3 chỗ nhỏ
1️⃣ printGraph() tránh NullPointer
2️⃣ outContent.reset() trước khi printGraph trong test
3️⃣ thêm test addEdge invalid node

30 Câu hỏi "Cân não" cho 6 bài Test Graph 🚀
Tài liệu này chia nhỏ từng bài trong 6 bài test của file 
GraphTest.java
, kèm theo 5 câu hỏi "xoáy" cho mỗi bài (Tổng cộng 30 câu).

🧪 Bài Test 1: 
testAddLocationAndEdge
Mô tả: Tự tạo Quy Nhơn (City) và Diêu Trì (Station) rồi nối chúng lại với 10.5km.

Tại sao em lại dùng locA và locB thay vì nhập thẳng tên vào hàm 
addEdge
?
Trả lời: Dạ, để chứng minh là hệ thống quản lý được các đối tượng 
Location
 (có cả ID và Name) chứ không chỉ là các chuỗi ký tự đơn thuần.
Nếu thầy xóa dòng graph.addLocation(locB) nhưng vẫn giữ dòng graph.addEdge(...) thì chuyện gì xảy ra?
Trả lời: Bài test sẽ báo lỗi đỏ (Fail) và in ra: "One of the nodes does not exist!" vì hàm 
addEdge
 yêu cầu cả 2 đầu phải tồn tại trước.
Tại sao em lại phải assertTrue tới 2 lần cho cùng một con đường 10.5km?
Trả lời: Vì đây là đồ thị vô hướng. Em phải đảm bảo A nối với B thì B cũng phải tự động nối ngược lại với A.
Nếu thầy đổi 10.5 thành -5.0, kết quả contains có còn đúng không?
Trả lời: Dạ vẫn đúng về mặt văn bản (nó sẽ hiện -5.0km), nhưng nó sẽ chứng tỏ dữ liệu của thầy bị sai logic địa lý.
Dấu ngoặc đơn 
(10.5km)
 trong code assertTrue lấy từ đâu ra?
Trả lời: Dạ nó được định dạng trong hàm 
printGraph()
 ở file 
Graph.java
 ạ. Em phải viết đúng y hệt từng dấu cách và ngoặc thì test mới pass.
🧪 Bài Test 2: 
testLoadFromFile
Mô tả: Đọc dữ liệu từ file 
map_test.txt
 và kiểm tra xem Quy Nhơn có nối với Sông Cầu (20km) không. 6. Nếu trong file 
map_test.txt
 thầy đổi ; thành , thì bài test này sẽ bị gì?

Trả lời: Hàm split(";") sẽ không cắt được dữ liệu, dẫn đến mảng parts bị thiếu phần tử và in ra lỗi: "Invalid line format".
Tại sao em lại phải dùng outContent.reset() ở dòng 63?
Trả lời: Để xóa dòng chữ "Successfully loaded..." đi, giúp cho việc kiểm tra nội dung 
printGraph()
 phía sau được sạch sẽ, không bị lẫn lộn.
Sao em biết chắc chắn Quy Nhơn phải nối với Sông Cầu (20.0km) mà test?
Trả lời: Vì em dựa vào nội dung em đã soạn trong file 
map_test.txt
. Đây là cách test dựa trên dữ liệu đầu vào cố định.
Nếu file 
map_test.txt
 có 1000 dòng, hàm 
printGraph()
 có làm bài test bị chậm không?
Trả lời: Có ạ, vì in ra màn hình console là tác vụ tốn thời gian nhất. Trong thực tế, người ta thường test dữ liệu lớn mà không in ra console.
Lệnh assertTrue dòng 67 kiểm tra cái gì?
Trả lời: Kiểm tra xem sau khi load file, node "Quy Nhon" có thực sự tồn tại trong danh sách kề (Adjacency List) hay chưa.
🧪 Bài Test 3: 
testDijkstraShortestPath
Mô tả: Tìm đường Quy Nhơn -> Tuy Hòa, kết quả phải là 90.0km và đúng lộ trình qua Sông Cầu, Chí Thạnh. 11. Tại sao em không chọn đường đi thẳng Quy Nhơn -> Tuy Hòa cho nhanh? - Trả lời: Vì theo dữ liệu trong file, đường đó dài hơn 90km. Dijkstra luôn ưu tiên tổng quãng đường ngắn nhất, không quan tâm phải qua bao nhiêu trạm. 12. Nếu thầy sửa đường Sông Cầu -> Chí Thạnh từ 30km lên 300km thì sao? - Trả lời: Dijkstra sẽ tự động tìm một con đường khác (ví dụ đi vòng đường khác) để đảm bảo tổng số km là nhỏ nhất có thể lúc đó. 13. Kết quả 90.0 km (có dấu cách) và 90.0km (viết liền) có quan trọng không?

Trả lời: Rất quan trọng ạ! Bài test của em dùng contains("Total distance: 90.0 km"), nếu thiếu một dấu cách là bài test báo lỗi ngay mặc dù thuật toán chạy đúng.
Làm sao thuật toán biết được thứ tự: Quy Nhon -> Song Cau -> Chi Thanh -> Tuy Hoa?
Trả lời: Nhờ vào biến previousNodes lưu lại "vết" của các trạm đã đi qua, sau đó em dùng Collections.reverse() để lộn ngược lại từ đích về đầu.
Nếu thầy muốn tìm đường từ Tuy Hòa về Quy Nhơn (ngược lại) thì kết quả có khác không?
Trả lời: Dạ không, vì đây là đồ thị vô hướng, đường đi và khoảng cách hai chiều là như nhau.
🧪 Bài Test 4: 
testDijkstraUnreachablePath
Mô tả: Test Kỳ Co và Eo Gió (không có đường nối). 16. Tại sao không nối đường cho Kỳ Co và Eo Gió? - Trả lời: Dạ để giả lập tình huống hai địa điểm bị chia cắt (ví dụ đảo), giúp kiểm tra xem code có bị lỗi "vòng lặp vô tận" khi không tìm thấy đích hay không. 17. Tại sao kết quả mong đợi lại chứa chữ "Beach"? - Trả lời: Vì lúc 
addLocation
, em đặt tên (Name) là "Ky Co Beach". Code in ra sử dụng Name để thân thiện với người dùng. 18. Chuyện gì xảy ra trong bộ nhớ khi không tìm thấy đường? - Trả lời: Biến khoảng cách (distance) của Eo Gió vẫn sẽ giữ giá trị Double.MAX_VALUE (Vô cực) như lúc khởi tạo. 19. Thầy muốn đổi thành "No route found" thay vì "No path exists" được không? - Trả lời: Được ạ, nhưng em phải sửa đồng bộ cả ở file 
Graph.java
 và file Test này thì nó mới Pass. 20. Tại sao em lại khai báo 2 node này ngay trong hàm @Test mà không để ở 
setUp
? - Trả lời: Để giữ cho các bài test khác không bị "rác". Hai node này chỉ sinh ra để phục vụ duy nhất bài test "ngõ cụt" này thôi.

🧪 Bài Test 5: 
testDijkstraInvalidNode
Mô tả: Tìm đường Sài Gòn -> Hà Nội (không có tên trong bản đồ). 21. Tại sao em lại dùng errContent thay vì outContent? - Trả lời: Vì thông báo lỗi được em in ra bằng lệnh System.err.println. Đây là luồng dành riêng cho lỗi trong Java. 22. Nếu thầy nhập "quy nhon" (viết thường) thì nó có báo lỗi này không? - Trả lời: Có ạ, vì trong bản đồ em lưu là "Quy Nhon". Java phân biệt chữ Hoa/Thường nên nó sẽ coi là node không tồn tại. 23. Tại sao em lại chọn SÀI GÒN và HÀ NỘI để test? - Trả lời: Vì em biết chắc chắn 2 thành phố này không có trong file 
map_test.txt
 của em, dùng để test độ nhạy của việc bắt lỗi. 24. Nếu code không in ra dòng chữ này thì bài test sẽ hiện màu gì? - Trả lời: Sẽ hiện màu Đỏ (Fail) kèm thông báo: "Should print error for invalid nodes". 25. Làm sao em "bắt" được cái luồng lỗi System.err này vào biến của em? - Trả lời: Nhờ dòng code System.setErr(new PrintStream(errContent)) trong hàm 
setUp
 ạ.

🧪 Bài Test 6: 
testAddEdgeInvalidNode
Mô tả: Thêm cạnh từ A (có thật) đến B (không có thật). 26. Hàm 
addEdge("A", "B", 10)
 có làm chương trình bị treo không? - Trả lời: Dạ không, nhờ có lệnh if (!adjList.containsKey...) em đã chặn lại và thoát hàm sớm bằng lệnh return. 27. Tại sao em lại tạo node "A" mà không tạo node "B"? - Trả lời: Để tạo ra tình huống "đầu thừa đuôi thẹo", kiểm tra xem hàm có tỉnh táo để nhận ra một trong hai đầu bị thiếu hay không. 28. Nếu thầy thêm graph.addLocation(new Location("B", "B")) vào trước đó thì test này sẽ thế nào? - Trả lời: Bài test sẽ bị Fail, vì lúc này lỗi sẽ không còn xảy ra nữa, mà code của em lại đang mong chờ phải có lỗi. 29. Thông báo "One of the nodes does not exist!" in ra ở đâu? - Trả lời: In ra màn hình lỗi (System.err). 30. Tại sao em không kiểm tra khoảng cách 10km ở bài test này? - Trả lời: Vì khi node không tồn tại, con đường 10km đó còn chưa kịp được tạo ra trong bộ nhớ, nên không có gì để kiểm tra khoảng cách ạ.

🧪 Phần 3: 10 Câu hỏi về Trình diễn (GraphOutputTest.java)
Mô tả: File này chứa hàm 
runGraphDemo
 để in kết quả đẹp mắt ra console.

Tại sao trong 
GraphOutputTest
 em lại viết code trong một hàm @Test thay vì hàm 
main
?

Trả lời: Để tận dụng trình chạy test của Maven. Chỉ cần gõ lệnh mvn test, Maven sẽ tự động lo việc biên dịch và chạy demo mà em không cần phải cài đặt cấu hình phức tạp hay tìm file .class thủ công.
Nếu thầy muốn dừng việc in toàn bộ danh sách kề (Adjacency List) thì em xóa dòng nào?

Trả lời: Dạ, em sẽ comment (khoá) dòng graph.printGraph(); ở dòng số 19 lại ạ. Lúc đó chương trình sẽ chỉ in kết quả tìm đường thôi.
Tại sao trong Route 4 em lại sử dụng graph.addLocation trực tiếp trong code?

Trả lời: Vì Kỳ Co và Eo Gió là các trạm lẻ em muốn thêm nhanh để demo trường hợp lỗi. Em không nhất thiết phải vào tận file 
.txt
 để sửa, giúp việc demo linh hoạt và nhanh hơn.
Lệnh System.out.println trong file này có khác gì lệnh trong file 
Graph.java
 không?

Trả lời: Về kỹ thuật thì giống nhau, nhưng trong file này em dùng nó để tạo ra các "tiêu đề" như [Phase 1], [Phase 2] giúp thầy cô dễ theo dõi diễn biến của chương trình hơn.
Ở Route 6 (Add Edge With Missing node), tại sao em lại khởi tạo Graph graph2 = new Graph() mà không dùng lại graph cũ?

Trả lời: Em tạo đồ thị mới để nó hoàn toàn trống rỗng. Nhờ vậy khi em thêm cạnh lỗi, thầy cô sẽ thấy rõ ràng là nó báo lỗi do thiếu dữ liệu, không bị nhầm lẫn với hàng chục tỉnh thành khác đã load trước đó.
Tại sao em lại comment (vô hiệu hoá) Route 2 và Route 3?

Trả lời: Để tiết kiệm thời gian demo ạ. Nếu thầy cô muốn xem thêm lộ trình Huế -> Nha Trang, em chỉ cần xóa dấu // đi là xong ngay, rất tiện lợi.
Trong file này có dùng ByteArrayOutputStream để "đánh chặn" dữ liệu không?

Trả lời: Dạ không. Ở file demo này, em muốn kết quả được in trực tiếp ra màn hình cho mọi người xem, nên em không cần phải redirect luồng in vào biến nào cả.
Nếu trong Phase 1 (Nạp dữ liệu) bị lỗi, các Phase sau có chạy tiếp không?

Trả lời: Có chạy tiếp ạ, nhưng vì dữ liệu nạp vào bị trống nên các Phase sau sẽ báo lỗi là "Địa điểm không tồn tại".
Dấu gạch ngang === OPTIMAL RECOMMENDED ROUTE === in ra để làm gì?

Trả lời: Dạ để tạo điểm nhấn thị giác ạ. Nó giúp người xem phân biệt được đâu là thông tin log hệ thống và đâu là kết quả quan trọng nhất mà thuật toán đã tìm ra.
Nếu em muốn in ra thêm thời gian chạy của thuật toán Dijkstra (tính bằng mili giây), em sẽ thêm code vào đâu?

Trả lời: Em sẽ dùng System.currentTimeMillis() trước và sau lệnh graph.dijkstra(...), sau đó trừ đi để ra thời gian thực hiện và in ra console ạ.
