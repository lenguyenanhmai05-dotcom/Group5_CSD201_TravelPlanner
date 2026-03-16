rồi bây giờ để test cái này BẮT BUỘC: Viết bộ Unit Test (bằng JUnit hoặc code main test thủ công) để chứng minh code AI viết không bị lỗi NullPointerException. Phân vai (khuyến khích luân phiên):
Driver: Người trực tiếp tương tác với AI, gõ code, chạy thử.
Navigator: Người rà soát code (Code Review), viết Unit Test, đảm bảo code xử lý được các trường hợp biên (Edge cases).
Quản lý mã nguồn (Source Control - BẮT BUỘC):
Tất cả source code phải được lưu trữ trên GitHub.
Quy định Commit:
Phải có lịch sử commit liên tục (tối thiểu 10 commits/thành viên cho mỗi giai đoạn).
Commit message phải rõ ràng (Ví dụ: Feat: Add BST delete function, Fix: NullPointer in Queue, tránh dùng Update code, Fix bug).
Đánh giá đóng góp: Giảng viên sẽ kiểm tra GitHub Insights để xác định ai là người làm thật. Nếu chỉ một người commit -> Người còn lại nhận 0 điểm.
tới chỗ test này đủ 10 commit làm sao ạ 

Ok mình hiểu nỗi hoảng này 😭 nhưng tin mình đi: đoạn “test + 10 commit” này làm được, KHÔNG khó, chỉ cần chia nhỏ cho đúng cách là ổn ngay. Mình chỉ bạn từng bước + từng commit luôn nha.

1️⃣ Trước hết: thầy cho phép 2 CÁCH TEST

“Viết bộ Unit Test (bằng JUnit HOẶC code main test thủ công)”

👉 Với tình trạng hiện tại của bạn (VS Code + chưa set Maven/JUnit ổn định):
✅ CHỌN: test thủ công bằng main()
→ HOÀN TOÀN HỢP LỆ

JUnit là khuyến khích, không bắt buộc.

2️⃣ Mục tiêu của TEST là gì?

Không phải test cho sang, mà để chứng minh:

❌ Code AI viết KHÔNG bị NullPointerException
✅ Xử lý được edge cases

Ví dụ edge cases:

List rỗng

Xoá phần tử không tồn tại

Insert sau ID không có

Gọi size() khi list rỗng

3️⃣ Tạo file TEST RIÊNG (commit mới)
📁 File mới: TourLinkedListTest.java
package com.travelplanner.app;

import com.travelplanner.entities.TourLocation;
import com.travelplanner.structures.TourLinkedList;

public class TourLinkedListTest {

    public static void main(String[] args) {

        System.out.println("=== MANUAL UNIT TEST ===");

        // TEST 1: List rỗng
        TourLinkedList list = new TourLinkedList();
        System.out.println("Test empty list print:");
        System.out.println(list.print()); // KHÔNG được crash

        // TEST 2: Remove trên list rỗng
        System.out.println("Remove on empty list:");
        System.out.println(list.removeById("XXX")); // false

        // TEST 3: Insert after khi list rỗng
        System.out.println("Insert after on empty list:");
        System.out.println(list.insertAfter("DN",
                new TourLocation("HA", "Hoi An", "Desc", 400)));
        // TEST 4: Add First null safety
        list.addFirst(new TourLocation("HCM", "Ho Chi Minh", "Desc", 0));
        System.out.println(list.print());
        
         // TEST 5: Insert after ID không tồn tại
        System.out.println("Insert after non-existing ID:");
        System.out.println(list.insertAfter("ABC",
                new TourLocation("DN", "Da Nang", "Desc", 500)));
        
        // TEST 6: Size

        System.out.println("Size = " + list.size());

        System.out.println("=== ALL CHECKS SUCCESSFUL (NO EXCEPTIONS) ===");

        }
    }

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