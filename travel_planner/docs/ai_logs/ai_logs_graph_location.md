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
