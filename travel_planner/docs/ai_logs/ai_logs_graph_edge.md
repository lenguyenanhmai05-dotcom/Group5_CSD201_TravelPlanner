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
