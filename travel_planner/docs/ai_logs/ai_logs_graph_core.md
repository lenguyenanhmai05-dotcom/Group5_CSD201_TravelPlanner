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
