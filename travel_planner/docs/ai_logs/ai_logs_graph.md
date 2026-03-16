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

# Nhật ký phát triển (AI Logs) - Giải thích chi tiết Class `Edge.java`

**Tên Class:** `Edge` (Cạnh của đồ thị)
**Thuộc module:** Thuật toán Đồ thị (Graph - Giai đoạn 3)
**Package:** `com.travelplanner.structures.graph`

---

## 1. Mục đích của Class `Edge`
Trong lý thuyết đồ thị (Graph Theory), một đồ thị được cấu tạo bởi 2 thành phần chính: **Đỉnh (Vertex/Node)** và **Cạnh (Edge)**.
Nếu class `Location` đại diện cho các đỉnh (ví dụ: Thành phố HCM, Đà Lạt), thì class `Edge` sinh ra để đóng vai trò là **những con đường nối liền các thành phố đó lại với nhau**.

Nó giúp trả lời 2 câu hỏi cốt lõi khi ta đứng ở một thành phố bất kỳ:
1. Từ đây có thể đi thẳng tới đâu? (`destinationId`)
2. Mất bao xa / bao lâu để tới đó? (`weight`)

---

## 2. Giải thích cụ thể nội dung Code

Dưới đây là mã nguồn của class `Edge` đã được triển khai và ý nghĩa của từng dòng:

```java
package com.travelplanner.structures.graph;

public class Edge {
    // Thuộc tính 1: Lưu trữ Điểm Đến (Tương lai là ở đâu?)
    private String destinationId;
    
    // Thuộc tính 2: Lưu trữ Trọng Số (Mất bao xa để đi tới đó?)
    private double weight;

    // Constructure (Hàm khởi tạo đồ thị cạnh nối)
    public Edge(String destinationId, double weight) {
        this.destinationId = destinationId;
        this.weight = weight;
    }

    // Các hàm Getters cơ bản
    public String getDestinationId() {
        return destinationId;
    }

    public double getWeight() {
        return weight;
    }

    // Hàm overriding in ra chuỗi cho thân thiện lúc Debug
    @Override
    public String toString() {
        return "->" + destinationId + " (" + weight + "km)";
    }
}
```

### Đi sâu vào từng thuộc tính (Variables):

#### A. `private String destinationId;`
*   **Kiểu dữ liệu:** `String` (Chuỗi). Thay vì dùng số `1, 2, 3`, nhóm sử dụng `String` để map trực tiếp tên các thành phố (vd: `"Da Lat"`, `"Nha Trang"`) nhằm phù hợp tuyệt đối với file dữ liệu đầu vào `map_data.txt`.
*   **Ý nghĩa:** Nó chỉ ra **đích đến** của con đường này là gì. 
*   **Câu hỏi thường gặp của Giảng viên:** *"Tại sao class Cạnh (Edge) chỉ có thuộc tính Điểm Đến (`destinationId`) mà lại KHÔNG CÓ Điểm Đi (`sourceId`)? Một con đường đáng lẽ phải có 2 đầu nối chứ?"*
    => **Câu trả lời đạt 10 điểm:** *"Dạ thưa thầy/cô, vì em sử dụng cấu trúc **Danh sách kề (Adjacency List)** `Map<String, List<Edge>>` trong class Graph. Tại cấu trúc đó, bản thân cái mảng/key gốc ĐÃ LÀ Điểm Đi rồi, nên bên trong thông tin Cạnh chỉ cần móc nối tới Điểm Đến là quá mức tối ưu về bộ nhớ (không bị lặp lại data thừa thãi)."*

#### B. `private double weight;`
*   **Kiểu dữ liệu:** `double`. Dùng kiểu số thực để có thể lưu độ chính xác cao của hệ kilomet (vd: `15.5 km`) và tính toán cho Dijkstra.
*   **Ý nghĩa:** Đại diện cho **Trọng số (Weight)** của đồ thị. Trong dự án TravelPlanner, nó có thể mang ý nghĩa là khoảng cách địa lý (Kilomet), hoặc thời gian di chuyển (Phút), hoặc chi phí di chuyển (VNĐ). Đây là mấu chốt để thuật toán Dijkstra có thể cân đo đong đếm xem rẽ hướng nào thì rẻ nhất / gần nhất.

### Đi sâu vào Hàm in (ToString override):

```java
@Override
public String toString() {
    return "->" + destinationId + " (" + weight + "km)";
}
```
*   **Tại sao lại viết thêm hàm này?** Việc Override hàm `toString()` cho class Edge là để phục vụ quá trình Debug (bắt lỗi) của Developer hiệu quả hơn. Thay vì khi in List ra nó trả về một dòng Rác Hash Memory khó hiểu kiểu `com.travelplanner...Edge@15db9742`, nó sẽ in đẹp đẽ ra thành `->Da Lat (300.0km)`. Giúp Tester và Dev nhìn Console hiểu đồ thị ngay lập tức.

---

## 3. Tổng kết logic cho Bài Nộp (Presentation Prep)

Nếu trình bày sơ đồ Class `Edge` kết hợp với Adjacency List, sinh viên chỉ cần vẽ lên bảng 1 ví dụ:
```
[HCM] có chứa 1 Danh sách:
       - Edge(destinationId: "Da Lat", weight: 300)
       - Edge(destinationId: "Vung Tau", weight: 100)
```
=> Nghĩa là: Từ HCM, chúng ta có 2 vòi rồng bạch tuộc (2 con đường) tỏa đi 2 nơi. 1 đường vươn tới "Da Lat" dài 300km, 1 đường vươn tới "Vung Tau" ngắn hơn chỉ 100km. Thuật toán Dijkstra lúc này sẽ dựa vô thuộc tính `weight` của Node `Vung Tau` rẻ hơn để chui vô vòng lặp xem xét ưu tiên đi lối `Vung Tau` trước.

# Nhật ký phát triển (AI Logs) - Giải thích chi tiết Class `Location.java`

**Tên Class:** `Location` (Đỉnh/Node của đồ thị)
**Thuộc module:** Thuật toán Đồ thị (Graph - Giai đoạn 3)
**Package:** `com.travelplanner.structures.graph`

---

## 1. Mục đích của Class `Location`
Trong bài toán ứng dụng Đồ thị (Graph) vào phần mềm Du lịch (TravelPlanner), mỗi **Đỉnh (Vertex / Node)** trên bản đồ đại diện cho một địa danh cụ thể. 

Class `Location` đóng vai trò là Khuôn đúc (Model/Entity) để sinh ra các trạm dừng chân đó (Ví dụ: Thành phố Hà Nội, Tỉnh Đà Lạt, Điểm du lịch Sapa). Nếu không có `Location`, những con đường (`Edge`) sẽ không biết kéo dài từ đâu đến đâu.

---

## 2. Giải thích cụ thể nội dung Code

Dưới đây là mã nguồn của class `Location` đã được triển khai và ý nghĩa của từng dòng:

```java
package com.travelplanner.structures.graph;

public class Location {
    // Thuộc tính 1: Mã định danh duy nhất (ID)
    private String id;
    
    // Thuộc tính 2: Tên hiển thị cho người dùng
    private String name;

    // Constructure (Hàm khởi tạo địa điểm)
    public Location(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Các hàm Getters cơ bản
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Hàm override in ra chuỗi cho thân thiện
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
```

### Đi sâu vào từng thuộc tính (Variables):

#### A. `private String id;`
*   **Kiểu dữ liệu:** `String`. Việc chuyển kiểu dữ liệu từ số nguyên (`int` 0, 1, 2) sang chuỗi (`String`) là một sự nâng cấp đáng giá của nhóm trong Giai đoạn 3.
*   **Ý nghĩa:** Nó hoạt động giống như Biển Số Xe hay Mã Khách Hàng - dùng để **định danh ĐỘC NHẤT** một địa điểm trên bản đồ.
*   **Điểm Pờ-rồ (Pro-tip) để nổ với Giảng viên:** *"Thay vì bắt người dùng (Tester) phải tra bảng xem Số 0 là Hà Nội, Số 1 là Đà Lạt để nhập tay tìm đường... Em đã thiết kế hệ thống đọc trực tiếp tên địa danh làm ID luôn (VD: `"HCM"`, `"Hai Phong"`). Việc này giúp cấu trúc đọc File I/O cực kì tự nhiên và việc nạp dữ liệu bằng tay cũng chống sai sót hoàn toàn."*

#### B. `private String name;`
*   **Ý nghĩa:** Đây là Tên Đầy Đủ (Display Name) để in ra giao diện cho đẹp. Ví dụ: ID là `"HCM"`, nhưng Name có thể là `"Thành phố Hồ Chí Minh"`. Hiện tại trong file `map_data.txt`, nhóm đang cho tính tiện lợi lên hàng đầu nên ép ID và Name giống y hệt nhau (VD: Đều là chuỗi `"Da Lat"`).

### Đi sâu vào Hàm in (ToString override):

```java
@Override
public String toString() {
    return name + " (" + id + ")";
}
```
*   Tương tự như bên `Edge`, việc bọc hàm `toString()` sẽ giúp đoạn code `System.out.println(myLocation)` tự động bung chữ ra màn hình dạng `"Thành phố Hồ Chí Minh (HCM)"` phân minh rõ ràng thay vì văng ra mã địa chỉ RAM thô thiển cục súc.

---

## 3. Tổng kết logic liên kết (Location + Edge)

Đứng trên góc độ cấu trúc Dữ Liệu `HashMap` lưu trong class cha `Graph`:
```java
private Map<String, Location> locations;
```
Biến này là một chiếc **Từ điển tra cứu nhanh (Lookup Table)**.
Khi thuật toán Dijkstra hay BFS cầm trong tay một cái túi ghi chữ *"Da Nang"*, nó chạy vào cái từ điển `locations` này tra từ khóa `"Da Nang"`, bùm một phát, cái HashMap nhả ra liền ngay lập tức (phức tạp Big-O là **O(1)**) toàn bộ Object `Location` chứa đầy đủ tên gốc lẫn thông tin mô tả chi tiết của Đà Nẵng để in ra màn hình.

Việc tách rời `Location` ra thành 1 Class riêng giúp chương trình sau này dễ dàng mở rộng (Scale-up). Nếu Phase 4 giáo viên yêu cầu mỗi tỉnh phải có thêm Kinh Độ & Vĩ Độ (Latitude/Longitude), nhóm chỉ việc thò tay vào file `Location.java` thêm 2 dòng code `double lat, lng` là xong, kiến trúc tìm đường `Graph` hầu như không bị ảnh hưởng! 🚀
