# Nhật ký phát triển (AI Logs) - Giai đoạn 3: Đồ thị & Thuật toán (Tuần 7 - 9)

**Người thực hiện:** Nhóm 5
**Mô-đun:** 3 (Graph)
**Mục tiêu:** Xây dựng thuật toán tìm đường (Dijkstra/BFS), đọc dữ liệu bản đồ từ file text và chuẩn bị cho bài kiểm tra Progress Test 2 (10%).

---

## 1. Phân tích bài toán & Mô hình hóa (Data Modeling)

Phân tích yêu cầu bài toán TravelPlanner ứng dụng cấu trúc Đồ thị (Graph):
- **Đỉnh (Vertex / Node):** Thể hiện các địa điểm du lịch (Thành phố, danh lam thắng cảnh, khách sạn, v.v.). Mỗi đỉnh có ID và Tên.
- **Cạnh (Edge):** Thể hiện các tuyến đường di chuyển giữa các địa điểm.
- **Loại đồ thị:** Đồ thị vô hướng (Undirected) và có trọng số (Weighted). Trọng số ở đây đại diện cho khoảng cách (km) hoặc thời gian di chuyển (phút).
- **Cấu trúc lưu trữ:** Sử dụng **Danh sách kề (Adjacency List)** để tối ưu điểm mạnh về bộ nhớ ($O(V+E)$) do bản đồ thực tế có tính chất phân tán.

## 2. Kế hoạch triển khai (Hành động đầu tiên)

Trước khi tiến hành code thuật toán tìm đường (Dijkstra/BFS), nhóm cần thực hiện các công việc nền tảng sau:

### Bước 2.1: Vẽ bản đồ thực tế trên giấy
- Lấy 5 - 10 địa điểm thực tế (ví dụ: Hà Nội, Hạ Long, Ninh Bình, Sapa, Hải Phòng...).
- Gán ID (bắt đầu từ số nguyên `0`) cho từng địa điểm.
- Vẽ các tuyến đường (cạnh) kết nối các node với nhau kèm theo độ dài quãng đường tương ứng.

### Bước 2.2: Thiết kế File dữ liệu đầu vào (`map_data.txt`)
Yêu cầu bài toán là đọc dữ liệu từ một file text cấu trúc. Định dạng đề xuất:
- **Dòng 1:** Tổng số địa điểm (số đỉnh `N`).
- **N dòng tiếp theo:** Tên các địa điểm (tương ứng với các ID từ `0` đến `N-1`).
- **Dòng tiếp theo:** Tổng số tuyến đường (số cạnh `M`).
- **M dòng tiếp theo:** Định dạng `<ID_Điểm_A> <ID_Điểm_B> <Trọng_số>` biểu diễn tuyến đường kết nối và khoảng cách.

### Bước 2.3: Xây dựng cấu trúc dữ liệu nền tảng (Base Classes)
- **`Location` Class:** Chứa `id` và `name`.
- **`Edge` Class:** Chứa `destinationId` và `weight`.
- **`Graph` Class:** Chứa danh sách kề (`Map<Integer, List<Edge>>`) và thông tin địa điểm (`Map<Integer, Location>`).
- Cài đặt hàm `loadFromFile(String filePath)` để đọc `map_data.txt` vào và build đồ thị. In ra màn hình console (System.out) cấu trúc đồ thị sinh ra để xác nhận load file thành công.

## 3. Lựa chọn thuật toán cốt lõi

Thuật toán chính được phân tích và cân nhắc:
- **BFS (Breadth-First Search):** Phù hợp để tìm đường đi qua ít trạm trung chuyển nhất (đồ thị không trọng số). Dễ cài đặt.
- **Dijkstra:** Cực kì phù hợp để tìm đường đi ngắn nhất hoặc tốn ít thời gian nhất trên đồ thị có trọng số dương. Thích hợp chuẩn xác với Use Case lên lịch trình di chuyển của dự án.
-> **Quyết định đề xuất:** Sử dụng **Dijkstra**.

## 4. Chuẩn bị tài liệu bảo vệ Progress Test 2
- Viết sẵn tài liệu / vẽ Excel để dry-run (chạy tay thuật toán bằng bút) luồng của Dijkstra.
- Nắm rõ cách dữ liệu được nạp vào RAM dưới dạng **Adjacency List**.
- Giải thích được tác dụng cấu trúc mảng ghi nhớ dấu vết (`previous_node`) phục vụ cho việc xuất lộ trình các điểm đã đi qua ra UI.

Để bắt đầu làm phần Graph này một cách mượt mà nhất, việc ĐẦU TIÊN bạn cần làm tuyệt đối KHÔNG PHẢI là mở máy lên gõ public class Graph hay code thuật toán ngay.

Việc đầu tiên bạn phải làm là Mô hình hóa dữ liệu (Data Modeling) trên giấy và File Text.

Dưới đây là chi tiết từng bước cho "Việc làm đầu tiên" của Developer đối với module này:

Bước 1.1: Vẽ bản đồ du lịch của nhóm ra giấy
Thuật toán Graph sinh ra là để duyệt đỉnh và cạnh. Nếu không có "bản đồ", bạn sẽ không tưởng tượng được code chạy đúng hay sai.

Bạn hãy lấy một tờ giấy, chọn ra khoảng 5 đến 7 địa điểm du lịch (ví dụ: 0: Hà Nội, 1: Hạ Long, 2: Ninh Bình, 3: Sapa, 4: Hải Phòng). Lưu ý: Bắt buộc gán ID là số nguyên (bắt đầu từ 0) cho dễ làm mảng/list.
Chấm 5-7 điểm đó lên giấy.
Vẽ các đoạn thẳng nối các điểm đó lại với nhau (không phải điểm nào cũng nối với nhau, chỉ nối các tuyến đường có thể đi trực tiếp).
Viết lên đoạn thẳng đó 1 con số: Khoảng cách (ví dụ 150km) hoặc Thời gian đi (ví dụ 120 phút).
=> Xong bước này, bạn có một Đồ thị vô hướng, có trọng số trên giấy.

Bước 1.2: Chuyển bản vẽ đó thành File Text (map.txt)
Giảng viên yêu cầu Đọc dữ liệu từ file text. Do đó, bạn cần chốt Format (Định dạng) của file này. Hãy thiết kế nó càng dễ đọc bằng máy tính càng tốt.

Format gợi ý dễ code nhất cho sinh viên:

text
5               // Dòng 1: Tổng số lượng địa điểm (Đỉnh / Vertices)
Hà Nội          // Dòng 2 -> 6: Tên các địa điểm (Tương ứng ID từ 0 đến 4)
Hạ Long 
Ninh Bình 
Sapa 
Hải Phòng
6               // Dòng 7: Tổng số lượng tuyến đường (Cạnh / Edges)
0 1 150         // Dòng 8 trở đi: [ID_Đỉnh_A] [ID_Đỉnh_B] [Trọng_số/Khoảng_cách]
0 2 95          // (Ý nghĩa: Từ Hà Nội (0) đi Ninh Bình (2) mất 95km)
0 3 320
1 4 70         
0 4 120
2 4 150
Bạn tạo một file ví dụ là map_data.txt trong thư mục project của bạn (thường là để ở thư mục src/main/resources/ hoặc folder data/ cạnh code).

Bước 1.3: Lên khung cho Class Graph (Chỉ thiết kế khung, chưa code thuật toán)
Sau khi có file text, giờ mới là lúc bạn bật IDE (VS Code/IntelliJ) lên. Nhưng khoan code thuật toán, hãy code phần Cấu trúc lưu trữ và Đọc File trước.

Bạn cần tạo các class sau:

Class Location (hoặc City / Destination): Chỉ đơn giản là chứa cấu trúc của 1 Điểm đến.

Chứa int id
Chứa String name
Class Edge (Tuyến đường):

Chứa int destinationId (đi đến đâu)
Chứa double weight (khoảng cách/thời gian bằng bao nhiêu)
Class Graph (Cốt lõi): Bạn phải chọn cấu trúc dữ liệu để lưu. Ở đây mình khuyên dùng Danh sách kề (Adjacency List - dùng HashMap hoặc Array of LinkedLists) vì nó dễ duyệt cạnh.

Biến: Map<Integer, List<Edge>> adjList; (Lưu danh sách dính liền của từng đỉnh).
Biến: Map<Integer, Location> locations; (Lưu từ điển ID -> Tên địa điểm để lúc sau in ra tên cho đẹp).
Viết hàm loadFromFile(String filePath):

Viết code dùng Scanner hoặc BufferedReader để đọc cái file map_data.txt ở Bước 1.2.
Đọc số 5 -> tạo 5 cái Location.
Đọc số 6 -> lặp 6 lần, mỗi lần đọc 3 số u v weight, đưa vào cái adjList.
Vừa lúc code hàm đọc file, bạn in ra System.out xem nó nhảy đúng không.
Tóm tắt "Việc làm đầu tiên":
Lấy giấy bút vẽ đồ thị mẫu. (10 phút)
Gõ cái đồ thị đó thành file map.txt. (5 phút)
Code các Class Data Models (Location, Edge). (15 phút)
Code class Graph có chứa đúng 1 hàm: Đọc file text và in thử ra màn hình console xem load thành công chưa. (30 phút)
Sau khi hàm đọc file in ra console dữ liệu bản đồ giống hệt trên giấy, bạn mới coi như xong việc đầu tiên. Lúc này mới bắt đầu Google cách code Dijkstra hay BFS chèn vào Class Graph.

Bạn thấy luồng công việc này đã đủ rõ ràng để xắn tay áo lên bắt đầu dự án chưa? Nếu bạn muốn tự tạo file .txt ngay bây giờ, mình có thể hướng dẫn xem file đó hợp lý chưa.

# Nhật ký phát triển (AI Logs) - Giải thích chi tiết Class `Graph.java` (Lõi Thuật Toán)

**Tên Class:** `Graph` (Đồ thị)
**Thuộc module:** Thuật toán Đồ thị (Graph - Giai đoạn 3)
**Package:** `com.travelplanner.structures.graph`

---

## 1. Tầm quan trọng của Class `Graph.java`
Nếu `Location` là "Thành phố" và `Edge` là "Con đường", thì `Graph` chính là **Bản Đồ Tổng (The Map)** và là **Google Maps (Người dẫn đường)**.
Đây là Class khó nhất, chiếm 10% điểm Progress Test 2, đóng vai trò:
1. Nắm giữ toàn bộ dữ liệu Đỉnh và Cạnh trong RAM.
2. Cung cấp API đọc dữ liệu từ file Text (`loadFromFile`).
3. Chứa trái tim thuật toán: Hàm tìm đường cực hạn **Dijkstra kết hợp Priority Queue**.

---

## 2. Giải thích Cấu trúc dữ liệu lưu trữ (Data Structure)

```java
private Map<String, Location> locations;
private Map<String, List<Edge>> adjList;
```

Đây là điểm nhấn ăn tiền nhất của toàn bộ bài báo cáo môn CSD201: **Danh sách kề (Adjacency List) bằng HashMap**.

*   **`locations`:** Bộ từ điển Tra Cứu (O(1)). Đưa cho nó cái Tên ID (`"HCM"`), nó trả về toàn bộ Object Location của HCM.
*   **`adjList`:** Đây là cốt lõi của **Adjacency List**. 
    *   **Giảng viên hỏi:** *"Tại sao em không dùng Ma trận bậc 2 (Mảng 2 chiều int[V][V]) như sách giáo khoa dạy căn bản?"*
    *   **Trả lời lấy điểm 10:** *"Dạ thưa thầy/cô, đồ thị bản đồ là Đồ thị thưa (Sparse Graph) - Một tiểu bang hay thành phố chỉ giáp với 3-4 thành phố lân cận chứ không giáp với toàn bộ quốc gia. Dùng Ma Trận 2 chiều sẽ gây lãng phí bộ nhớ khổng lồ cho các số 0 (không có đường đi). Dùng `Map<String, List<Edge>>` là tối ưu tuyệt đối bộ nhớ $O(V+E)$ theo chuẩn kỹ thuật phần mềm hiện đại."*

---

## 3. Giải thích Thuật toán Dijkstra (Phần Thuyết Trình)

Đoạn code Dijkstra của nhóm không phải là bộ quét mảng $O(V^2)$ cồng kềnh chậm chạp, mà là bản Hack não **Dijkstra Priority Queue** $O(E \log V)$. Khi lên bảng dry-run (chạy tay), sinh viên bám theo 3 món Đồ nghề sau:

### Bộ 3 Món Đồ Nghề Khởi Tạo:
```java
Map<String, Double> distances = new HashMap<>();
Map<String, String> previousNodes = new HashMap<>();
PriorityQueue<RouteNode> pq = new PriorityQueue<>();
```
1.  **Sổ Ghi Chép Cự Ly (`distances`):** Khởi tạo tất cả bằng `Double.MAX_VALUE` (vô cực) vì mình chưa biết đường đến đó. Riêng điểm xuất phát (Ví dụ: "HCM") được gán bằng `0.0`.
2.  **Sổ Theo Dấu (`previousNodes`):** Mục đích để sau khi tìm xong, mình biết đường truy ngược lại từng trạm dừng chân (Path Tracking).
3.  **Hàng Đợi Ưu Tiên (`pq - PriorityQueue`):** Trái tim của sự tối ưu. Thay vì for-loop tìm thằng nhỏ nhất mòn mỏi, `PriorityQueue` luôn nổi (pop) phần tử có cự ly NGẮN NHẤT hất lên đầu một cách tự động chỉ tốn $O(1)$.

### Vòng Lặp Vắt Kiệt (The While Loop) & Relaxation:
```java
while (!pq.isEmpty()) {
    RouteNode current = pq.poll();
    ...
    // Relaxation (Cập nhật)
    if (newDist < distances.get(neighborId)) {
        distances.put(neighborId, newDist);
        previousNodes.put(neighborId, currentId);
        pq.add(new RouteNode(neighborId, newDist));
    }
}
```
*   **Cách giải thích bảng:**
    *"Vòng lặp sẽ bốc cái điểm gần nhất trong Hàng Đợi ra. Đứng từ điểm đó, em quét tất cả những thằng hàng xóm (neighbor). Nếu tổng quãng đường từ gốc bay tới chỗ em đứng -> cộng với con đường bước sang nhà thằng hàng xóm (`newDist`) mà nó NGẮN HƠN cái kỷ lục cũ đang ghi trong Sổ (`distances.get(neighborId)`) -> Thì em xé nháp kỷ lục cũ, ghi đè kỷ lục mới vào sổ, đồng thời Note lại trong Sổ Theo Dấu là 'Muốn tới nhà này nhanh nhất, phải đi ngang qua tao'. Cuối cùng quăng cái thông tin mới này vào Hàng Đợi ưu tiên để xét vòng lặp tiếp theo."*

*   **Bắt bài Giảng Viên (Câu hỏi khó):** *"Đoạn code `if (current.distance > distances.get(currentId)) continue;` có ý nghĩa gì?"*
    -> **Trả lời:** *"Dạ thưa cô, đây là tính năng Lazy Deletion (Xóa lười). Trong Priority Queue thỉnh thoảng sẽ bị đọng lại những kỷ lục cũ chưa kịp xóa (vì PQ không hỗ trợ xóa phần tử bất kì nhanh). Code này tự động vớt rác: Nếu thấy cự ly rút ra mà DÀI HƠN kỷ lục chốt sổ rồi, thì bỏ qua không thèm xử lý tiếp để đỡ tốn RAM (Optimization)."*

---

## 4. Path Tracking - Truy vết Lộ Trình (Hàm `printPath`)
```java
List<String> path = new ArrayList<>();
String current = endId;
while (current != null) {
    path.add(current);
    current = previousNodes.get(current);
}
Collections.reverse(path);
```
Sau khi bão Dijkstra quét qua xong, Sổ Theo Dấu (`previousNodes`) đã lưu đầy đủ thông tin.
*   **Cách thức:** Nhóm code đi lùi từ Đích. Ví dụ: Từ "Ha Noi", tra sổ thấy trước đó là "Hue". Rồi từ "Hue", tra sổ tiếp thấy trước đó là "Da Nang"... Cứ truy cho vòng lặp đến khi `null` (chạm vô điểm Xuất phát).
*   Vì danh sách bị ngược từ Đích về Nguồn, nên lệnh `Collections.reverse(path)` sẽ lật ngược lại giúp mảng chạy xuôi từ Đầu đến Đích một cách cực kỳ gọn gàng.
