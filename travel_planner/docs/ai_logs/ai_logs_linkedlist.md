Trạng thái hiện tại của dự án

Hiện tại dự án đã có:

Class TourLocation (entity dữ liệu cho địa điểm du lịch)

Cấu trúc package đã được tạo theo chuẩn Maven

Theo đúng lộ trình Giai đoạn 1 – Cấu trúc tuyến tính (Linked List), bước tiếp theo cần thực hiện là xây dựng Node cho Linked List.

Bước tiếp theo cần thực hiện
1. Tạo Node cho Linked List

Mục tiêu
Xây dựng cấu trúc Node để lưu trữ một TourLocation và liên kết với Node tiếp theo.
Đây là bước bắt buộc khi tự cài đặt Linked List (không sử dụng thư viện có sẵn của Java).

2. Tạo file LocationNode.java

Đường dẫn

src/main/java/com/travelplanner/structures/LocationNode.java


Nội dung

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

3. Commit sau khi hoàn thành

Sau khi tạo xong LocationNode.java, thực hiện commit với nội dung:

git add src/main/java/com/travelplanner/structures/LocationNode.java
git commit -m "feat: add LocationNode for linked list"


Commit này thể hiện rõ:

Việc xây dựng cấu trúc Node cho Linked List

Đúng với yêu cầu tự cài đặt cấu trúc dữ liệu trong môn CSD201

Bước tiếp theo sau đó

Sau khi hoàn thành Node, bước kế tiếp sẽ là:

Tạo class TourLinkedList

Khai báo con trỏ head

Chuẩn bị cài đặt các thao tác cơ bản (add, remove, insert)

Khi hoàn thành bước hiện tại, có thể tiếp tục với bước kế tiếp trong lộ trình.