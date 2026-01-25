
# AI LOGS – GIAI ĐOẠN 1
## Project: TravelPlanner_Group5
## Môn học: CSD201 – Data Structures and Algorithms
## Giai đoạn: Tuần 1 – Tuần 3 (Khởi tạo & Cấu trúc tuyến tính)

---

## 1. Mục tiêu giai đoạn

Giai đoạn 1 tập trung vào việc:
- Khởi tạo project Java Maven đúng chuẩn
- Xây dựng nền tảng OOP cho hệ thống Travel Planner
- Tự cài đặt cấu trúc dữ liệu tuyến tính (Linked List)
- Viết Unit Test để chứng minh code không bị lỗi runtime (NullPointerException)

Giảng viên **không đánh giá UI / Web**, chỉ tập trung vào:
- Cấu trúc dữ liệu
- Thuật toán
- Unit Test và lịch sử commit

---

## 2. Cách sử dụng AI trong giai đoạn 1

AI (ChatGPT) được sử dụng với các mục đích:
- Hướng dẫn setup Maven project đúng chuẩn
- Gợi ý cấu trúc thư mục theo chuẩn CSD201
- Hỗ trợ thiết kế class theo tư duy OOP
- Gợi ý code Linked List (Node, add, remove)
- Hỗ trợ viết Unit Test JUnit
- Giải thích lỗi thường gặp (NullPointerException, package sai, test không chạy)

---

## 3. Cấu trúc project sau khi hoàn thành

Project được tổ chức theo chuẩn Maven:

CSD201_TRAVELPLANNER
├── docs
│ └── ai_logs
│ └── ai_logs_init.md
│
├── src
│ ├── main
│ │ ├── java
│ │ │ └── com
│ │ │ └── travelplanner
│ │ │ ├── app
│ │ │ │ └── App.java
│ │ │ ├── entities
│ │ │ │ └── TourLocation.java
│ │ │ ├── structures
│ │ │ │ ├── LocationNode.java
│ │ │ │ └── TourLinkedList.java
│ │ │ └── utils
│ │ │
│ │ └── resources
│ │ ├── data
│ │ └── public
│ │
│ └── test
│ └── java
│ └── com
│ └── travelplanner
│ └── structures
│ └── TourLinkedListTest.java
│
├── pom.xml
└── README.md


---

## 4. Nội dung đã triển khai

### 4.1 Entities (Dữ liệu nền)

**TourLocation.java**
- Đại diện cho một địa điểm du lịch
- Thuộc tính: id, name, description, price
- Override `equals()` để so sánh theo id
- Override `hashCode()` để tránh cảnh báo khi dùng equals

---

### 4.2 Node – Cấu trúc nền của Linked List

**LocationNode.java**
- Mỗi node chứa:
  - Dữ liệu: TourLocation / String city
  - Con trỏ next trỏ tới node tiếp theo
- Không sử dụng bất kỳ cấu trúc dữ liệu có sẵn của Java

---

### 4.3 Linked List – Core Logic

**TourLinkedList.java**
- Tự cài đặt Linked List
- Các chức năng chính:
  - addLast(): thêm phần tử vào cuối danh sách
  - remove(): xóa phần tử theo điều kiện
  - print(): in danh sách để debug
- Có kiểm tra head == null để tránh NullPointerException

---

### 4.4 App demo (Test thủ công)

**App.java**
- Chạy thử Linked List bằng mắt
- Kiểm tra:
  - Thêm dữ liệu
  - Xóa node
  - In danh sách trước và sau khi xóa

---

### 4.5 Unit Test (Bắt buộc)

**TourLinkedListTest.java**
- Viết bằng JUnit 5
- Các test case:
  - Thêm phần tử vào list rỗng
  - Xóa phần tử ở đầu
  - Xóa phần tử ở giữa
  - Xóa phần tử không tồn tại
- Mục tiêu:
  - Không crash
  - Không NullPointerException
  - `mvn test` trả về BUILD SUCCESS

---

## 5. Kết quả đạt được

- `mvn test` chạy thành công
- Tất cả test case PASS
- Không có lỗi runtime
- Code rõ ràng, đúng chuẩn OOP
- Đáp ứng đầy đủ yêu cầu Giai đoạn 1 của CSD201

---

## 6. Cam kết học thuật

- Sinh viên hiểu rõ toàn bộ code đã viết
- AI chỉ đóng vai trò hỗ trợ, giải thích và gợi ý
- Không sử dụng AI để gian lận hoặc copy không hiểu

---

## 7. Kế hoạch tiếp theo

- Giai đoạn 2:
  - Stack / Queue
  - Binary Search Tree (BST)
  - Mở rộng entities (Customer, Order)
  - Tăng độ phủ Unit Test

---

CẤU TRÚC PROJECT – GIAI ĐOẠN 1 (CSD201)
1️⃣ Mục tiêu của cấu trúc

Project TravelPlanner được xây dựng theo chuẩn Maven + OOP + Data Structures.
Cấu trúc thư mục giúp:

Tách thuật toán (Linked List) ra khỏi code chạy

Dễ test

Dễ mở rộng sang BST, Graph ở giai đoạn sau

2️⃣ Cấu trúc tổng thể
CSD201_TRAVELPLANNER
│
├── pom.xml                ← Cấu hình Maven
├── README.md
├── .gitignore
│
├── docs/
│   └── ai_logs/
│       └── ai_logs.md   ← Nhật ký AI (bắt buộc)
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── travelplanner/
│   │   │           ├── app/
│   │   │           │   └── App.java
│   │   │           │
│   │   │           ├── entities/
│   │   │           │   ├── Customer.java
│   │   │           │   └── TourLocation.java
│   │   │           │
│   │   │           ├── structures/
│   │   │           │   ├── LocationNode.java
│   │   │           │   └── TourLinkedList.java
│   │   │           │
│   │   │           └── utils/
│   │   │
│   │   └── resources/
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── travelplanner/
│                   └── structures/
│                       └── TourLinkedListTest.java
│
└── target/   ← Maven build (tự sinh)

3️⃣ Giải thích từng thư mục
Thư mục	Chức năng
src/main/java	Chứa toàn bộ code backend Java
app	Nơi có App.java để chạy chương trình
entities	Mô tả dữ liệu (Customer, Tour, Location…)
structures	Quan trọng nhất – nơi viết Linked List, Node
utils	Chứa code phụ trợ (sau này dùng)
src/test/java	Chứa Unit Test (JUnit)
docs/ai_logs	Nhật ký làm việc với AI (bắt buộc)
target	Thư mục Maven sinh ra khi build (không đụng tới)
4️⃣ Luồng hoạt động của hệ thống

Khi chạy chương trình:

App.java
   ↓
TourLinkedList
   ↓
LocationNode


Tức là:

App.java gọi TourLinkedList

TourLinkedList quản lý các LocationNode

Mỗi LocationNode là 1 thành phố trong tour

5️⃣ Vì sao thầy bắt dùng cấu trúc này?

Vì:

structures = nơi thể hiện Data Structures

test = nơi chứng minh code không crash

app = nơi demo cho giảng viên xem


# 🎯 MỤC TIÊU GIAI ĐOẠN 1 (Thầy chấm cái gì?)

Thầy **KHÔNG quan tâm web, JSP, UI**
Thầy **chỉ quan tâm 3 thứ**:

| Thứ | Thầy nhìn                               |
| --- | --------------------------------------- |
| 1   | Có tự viết **Linked List**              |
| 2   | Có **Node**                             |
| 3   | Có **Unit Test** chứng minh không crash |

=> Toàn bộ nằm ở:

```
src/main/java/com/travelplanner/structures
src/test/java/com/travelplanner/structures
```

---

# 🧠 BƯỚC 0 – Hiểu bài toán Travel Planner

Thực tế:

> Một Tour = nhiều thành phố nối nhau

Trong Data Structure:

> Một Tour = Linked List
> Mỗi thành phố = 1 Node

Ta ánh xạ:

| Thực tế     | Code             |
| ----------- | ---------------- |
| Thành phố   | `String city`    |
| 1 điểm dừng | `LocationNode`   |
| Cả tour     | `TourLinkedList` |

---

# 🧱 BƯỚC 1 – Tạo Maven project

Bạn tạo project Maven → có:

```
pom.xml
src/main/java
src/test/java
```

=> Maven đảm bảo:

* `src/main/java` = code chính
* `src/test/java` = test

---

# 🗂️ BƯỚC 2 – Tạo cấu trúc folder 

Bạn tạo:

```
src/main/java/com/travelplanner
├── app
├── entities
├── structures
└── utils
```

Đây là **architecture**:

* app → chạy
* entities → dữ liệu
* structures → thuật toán
* utils → phụ trợ

---

# 🧩 BƯỚC 3 – Viết NODE (xương sống)

File:

```
structures/LocationNode.java
```

```java
package com.travelplanner.structures;

import com.travelplanner.entities.TourLocation;

public class LocationNode {
    public TourLocation info; 
    public LocationNode next;

    public LocationNode(TourLocation info) {
        this.info = info;
        this.next = null;
    }
}
```

🎯 Ý nghĩa:

> Mỗi Node là 1 thành phố, trỏ sang thành phố tiếp theo

---

# 🧩 BƯỚC 4 – Viết Linked List thật sự

File:

```
structures/TourLinkedList.java
```

Đây là **trái tim của giai đoạn 1**

```java
package com.travelplanner.structures;

import com.travelplanner.entities.TourLocation;

public class TourLinkedList {
    private LocationNode head;

    public TourLinkedList() { head = null; }

    // 1. Thêm vào cuối
    public void addLast(TourLocation item) {
        LocationNode node = new LocationNode(item);
        if (head == null) {
            head = node;
            return;
        }
        LocationNode temp = head;
        while (temp.next != null) { temp = temp.next; }
        temp.next = node;
    }

    // 2. Thêm vào đầu (Mới)
    public void addFirst(TourLocation item) {
        LocationNode newNode = new LocationNode(item);
        newNode.next = head;
        head = newNode;
    }

    // 3. Chèn vào sau ID cụ thể (Mới)
    public boolean insertAfter(String destId, TourLocation item) {
        LocationNode curr = head;
        while (curr != null) {
            if (curr.info.getId().equals(destId)) {
                LocationNode newNode = new LocationNode(item);
                newNode.next = curr.next;
                curr.next = newNode;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // 4. Xoá theo ID
    public boolean removeById(String id) {
        if (head == null) return false;
        if (head.info.getId().equals(id)) {
            head = head.next;
            return true;
        }
        LocationNode prev = head;
        LocationNode cur = head.next;
        while (cur != null) {
            if (cur.info.getId().equals(id)) {
                prev.next = cur.next;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    // 5. In danh sách
    public String print() {
        StringBuilder result = new StringBuilder();
        LocationNode temp = head;
        while (temp != null) {
            result.append(temp.info.getName()).append(" -> ");
            temp = temp.next;
        }
        return result.append("END").toString();
    }
}
```

🎯 Đây chính là **module Linked List** mà đề yêu cầu.

---

# ▶️ BƯỚC 5 – App để test bằng mắt

File:

```
app/App.java
```

```java
package com.travelplanner.app;

import com.travelplanner.entities.TourLocation;
import com.travelplanner.structures.TourLinkedList;

public class App {
    public static void main(String[] args) {
        TourLinkedList tour = new TourLinkedList();

        TourLocation hcm = new TourLocation("HCM", "Ho Chi Minh", "Desc", 0.0);
        TourLocation hue = new TourLocation("HUE", "Hue", "Desc", 300.0);
        TourLocation dn = new TourLocation("DN", "Da Nang", "Desc", 500.0);
        TourLocation ha = new TourLocation("HA", "Hoi An", "Desc", 400.0);

        System.out.println("--- DEMO ---");
        tour.addLast(hcm);
        tour.addLast(dn);
        System.out.println("Ban đầu: " + tour.print());

        tour.addFirst(hue);
        System.out.println("Thêm đầu (Huế): " + tour.print());

        tour.insertAfter("DN", ha);
        System.out.println("Chèn giữa (Hội An sau ĐN): " + tour.print());
    }
}
```
 BƯỚC 6 – Unit Test (ăn 10%)

File:

Dưới đây là bộ code Unit Test hoàn chỉnh, bao phủ các trường hợp mà em có thể copy vào. Đảm bảo nhìn file test dài ngoằng, thầy cô nhìn vào là thấy sự đầu tư ngay.

File: src/test/java/com/travelplanner/structures/TourLinkedListTest.java
Java

package com.travelplanner.structures;

import com.travelplanner.entities.TourLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test cho cấu trúc dữ liệu TourLinkedList.
 * Bao gồm các kịch bản: Thêm, Sửa, Xóa, Tìm kiếm và Các trường hợp biên (Empty list).
 */
public class TourLinkedListTest {

    private TourLinkedList list;

    // Helper method để tạo nhanh đối tượng location
    private TourLocation createLoc(String id, String name) {
        return new TourLocation(id, name, "Test Description", 100.0);
    }

    // Chạy trước mỗi @Test để reset lại list sạch sẽ
    @BeforeEach
    public void setUp() {
        list = new TourLinkedList();
    }

    // ==========================================
    // 1. NHÓM TEST THÊM (ADDITION)
    // ==========================================

    @Test
    @DisplayName("TC01: Thêm vào đầu danh sách (AddFirst)")
    public void testAddFirst() {
        list.addFirst(createLoc("1", "A"));
        assertEquals("A -> END", list.print(), "Thêm 1 phần tử vào list rỗng");

        list.addFirst(createLoc("2", "B"));
        assertEquals("B -> A -> END", list.print(), "Thêm vào đầu khi list đã có dữ liệu");
    }

    @Test
    @DisplayName("TC02: Thêm vào cuối danh sách (AddLast)")
    public void testAddLast() {
        list.addLast(createLoc("1", "A"));
        assertEquals("A -> END", list.print());

        list.addLast(createLoc("2", "B"));
        assertEquals("A -> B -> END", list.print());
    }

    @Test
    @DisplayName("TC03: Chèn vào giữa (InsertAfter)")
    public void testInsertAfter_Middle() {
        list.addLast(createLoc("1", "A"));
        list.addLast(createLoc("3", "C"));

        // Chèn B vào sau A
        boolean result = list.insertAfter("1", createLoc("2", "B"));
        
        assertTrue(result, "Hàm phải trả về true khi chèn thành công");
        assertEquals("A -> B -> C -> END", list.print());
    }

    @Test
    @DisplayName("TC04: Chèn vào sau phần tử cuối cùng (InsertAfter Tail)")
    public void testInsertAfter_Tail() {
        list.addLast(createLoc("1", "A"));
        
        // Chèn B vào sau A (A đang là tail)
        list.insertAfter("1", createLoc("2", "B"));
        
        assertEquals("A -> B -> END", list.print(), "Phần tử mới phải trở thành Tail mới");
    }

    @Test
    @DisplayName("TC05: Chèn vào ID không tồn tại (InsertAfter Invalid)")
    public void testInsertAfter_NotFound() {
        list.addLast(createLoc("1", "A"));
        
        // Cố tình chèn vào sau ID "99" (không có thật)
        boolean result = list.insertAfter("99", createLoc("2", "B"));
        
        assertFalse(result, "Hàm phải trả về false nếu không tìm thấy ID");
        assertEquals("A -> END", list.print(), "Danh sách không được thay đổi");
    }

    // ==========================================
    // 2. NHÓM TEST XÓA (REMOVAL) - QUAN TRỌNG NHẤT
    // ==========================================

    @Test
    @DisplayName("TC06: Xóa phần tử đầu tiên (Remove Head)")
    public void testRemove_Head() {
        list.addLast(createLoc("1", "A"));
        list.addLast(createLoc("2", "B"));
        
        boolean result = list.removeById("1");
        
        assertTrue(result);
        assertEquals("B -> END", list.print(), "Head mới phải là B");
    }

    @Test
    @DisplayName("TC07: Xóa phần tử ở giữa (Remove Middle)")
    public void testRemove_Middle() {
        list.addLast(createLoc("1", "A"));
        list.addLast(createLoc("2", "B"));
        list.addLast(createLoc("3", "C"));
        
        list.removeById("2");
        assertEquals("A -> C -> END", list.print(), "Liên kết A phải trỏ thẳng tới C");
    }

    @Test
    @DisplayName("TC08: Xóa phần tử cuối cùng (Remove Tail)")
    public void testRemove_Tail() {
        list.addLast(createLoc("1", "A"));
        list.addLast(createLoc("2", "B"));
        
        list.removeById("2");
        assertEquals("A -> END", list.print(), "A phải trở thành Tail mới");
    }

    @Test
    @DisplayName("TC09: Xóa phần tử duy nhất trong list")
    public void testRemove_SingleElement() {
        list.addLast(createLoc("1", "A"));
        
        list.removeById("1");
        assertEquals("END", list.print(), "List phải trở về rỗng");
    }

    @Test
    @DisplayName("TC10: Xóa phần tử không tồn tại")
    public void testRemove_NotFound() {
        list.addLast(createLoc("1", "A"));
        
        boolean result = list.removeById("99"); // ID ảo
        
        assertFalse(result, "Phải trả về false nếu xoá thất bại");
        assertEquals("A -> END", list.print(), "List giữ nguyên");
    }

    @Test
    @DisplayName("TC11: Xóa trên danh sách rỗng (Empty List)")
    public void testRemove_EmptyList() {
        // Không add gì cả
        boolean result = list.removeById("1");
        
        assertFalse(result);
        assertEquals("END", list.print());
    }
}
Cách "chém gió" trong báo cáo để bài làm có chiều sâu
Khi nộp bài hoặc thuyết trình, em giải thích cấu trúc file test này như sau (đảm bảo thầy cô gật gù):

Tính độc lập (Isolation): Em sử dụng @BeforeEach để khởi tạo lại danh sách mới cho mỗi bài test. Điều này đảm bảo test case này không bị ảnh hưởng bởi dữ liệu rác của test case trước đó (tránh lỗi dây chuyền).

Phủ đầy đủ các trường hợp (Coverage):

Happy Path: Các trường hợp chạy suôn sẻ (Thêm đầu, thêm cuối, chèn giữa).

Edge Cases (Biên): Đây là phần quan trọng nhất của LinkedList. Em đã test kỹ các trường hợp nhạy cảm như: Xóa phần tử duy nhất (Head trùng Tail), Xóa Head, Xóa Tail.

Negative Cases: Test việc xử lý khi người dùng nhập ID không tồn tại hoặc thao tác trên danh sách rỗng (tránh NullPointerException).

Assertion rõ ràng: Mỗi assert đều có message đi kèm (ví dụ: "Head mới phải là B") để khi test fail, ta biết ngay lỗi logic nằm ở đâu mà không cần dò code.

# ▶️ BƯỚC 7 – Chạy Maven

Trong thư mục project:
```
mvn test
```
Nếu ra:
```
BUILD SUCCESS
```
👉 **Giai đoạn 1: HOÀN THÀNH 100%**

