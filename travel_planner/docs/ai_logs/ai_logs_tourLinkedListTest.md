OK, làm đÚNG Ý BẠN luôn nha 👍
👉 Tạo test trong src/test/java là CHUẨN CHỈNH hơn, thầy nhìn vào là thấy bạn hiểu Maven + testing liền.

Giờ mình chỉ RÕ TỪNG BƯỚC, copy làm theo là chạy được ngay.

1️⃣ Cấu trúc thư mục TEST (bạn đang làm ĐÚNG)

Bạn đã có:

src
 ├─ main
 │   └─ java
 │       └─ com
 │           └─ travelplanner
 │               ├─ app
 │               ├─ entities
 │               └─ structures
 └─ test
     └─ java
         └─ com
             └─ travelplanner


👉 OK 100%
Bây giờ ta tạo thêm package con test cho rõ ràng.

2️⃣ Tạo package & file test
📁 Chuột phải vào:
src/test/java/com/travelplanner

👉 New Package:
test

👉 New Java Class:
TourLinkedListTest

3️⃣ Code TourLinkedListTest.java (KHÔNG JUnit – test thủ công)

📌 Đặt đúng package:

package com.travelplanner.test;


📄 Toàn bộ file:

package com.travelplanner.test;

import com.travelplanner.entities.TourLocation;
import com.travelplanner.structures.TourLinkedList;

public class TourLinkedListTest {

    public static void main(String[] args) {

        System.out.println("=== MANUAL UNIT TEST FOR TourLinkedList ===");

        // TEST 1: List rỗng
        TourLinkedList list = new TourLinkedList();
        System.out.println("Test empty list print:");
        System.out.println(list.print());

        // TEST 2: Remove trên list rỗng
        System.out.println("Remove on empty list:");
        System.out.println(list.removeById("XXX"));

        // TEST 3: Insert after khi list rỗng
        System.out.println("Insert after on empty list:");
        System.out.println(
                list.insertAfter("DN",
                        new TourLocation("HA", "Hoi An", "Desc", 400))
        );
          // TEST 4: Add First
        list.addFirst(new TourLocation("HCM", "Ho Chi Minh", "Desc", 0));
        System.out.println("After addFirst:");
        System.out.println(list.print());

        // TEST 5: Insert after ID không tồn tại
        System.out.println("Insert after non-existing ID:");
        System.out.println(
                list.insertAfter("ABC",
                        new TourLocation("DN", "Da Nang", "Desc", 500))
        );

        // TEST 6: Add Last
        list.addLast(new TourLocation("HUE", "Hue", "Desc", 300));
        System.out.println("After addLast:");
        System.out.println(list.print());

        // TEST 7: Remove head
        list.removeById("HCM");
        System.out.println("After remove HCM:");
        System.out.println(list.print());

        // TEST 8: Size
        System.out.println("Size = " + list.size());

        System.out.println("=== ALL TESTS COMPLETED (NO EXCEPTION) ===");
    }
}