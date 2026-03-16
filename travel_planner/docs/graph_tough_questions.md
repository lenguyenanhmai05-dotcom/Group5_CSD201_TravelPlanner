# 10 Câu hỏi "Hóc búa" về Graph & Dijkstra (Kèm câu trả lời) 🧠

Đây là danh sách những câu hỏi khó mà thầy cô thường dùng để "thử lửa" sinh viên.

### Câu 1: Tại sao em lại dùng thuật toán Dijkstra mà không dùng BFS để tìm đường?
*   **Trả lời:** BFS (Breadth-First Search) chỉ tìm được đường đi có **ít số trạm nhất** (ít cạnh nhất). Trong khi đó, Dijkstra tìm được đường đi có **tổng số km nhỏ nhất**. Vì các con đường có độ dài khác nhau nên chỉ có Dijkstra mới chính xác cho bài toán Travel Planner.

### Câu 2: Trong code Dijkstra, tại sao em phải dùng `PriorityQueue`? Dùng `Queue` thường có được không?
*   **Trả lời:** `PriorityQueue` giúp ta luôn lấy ra node có khoảng cách nhỏ nhất hiện tại để xét (Greedy approach). Nếu dùng `Queue` thường, thứ tự xét sẽ là ngẫu nhiên, dẫn đến kết quả tìm kiếm không tối ưu hoặc thuật toán sẽ chạy chậm hơn rất nhiều vì phải xét đi xét lại một node nhiều lần.

### Câu 3: Thuật toán của em có chạy được nếu bản đồ có "trọng số âm" (ví dụ quãng đường -10km) không?
*   **Trả lời:** Không ạ. Thuật toán Dijkstra không hoạt động đúng nếu có cạnh trọng số âm (vì nó đã mặc định node lấy ra từ PriorityQueue là ngắn nhất rồi). Tuy nhiên, thực tế quãng đường km thì luôn dương, nên với bài toán bản đồ này thì Dijkstra là lựa chọn tối ưu nhất.

### Câu 4: Độ phức tạp thời gian (Time Complexity) của thuật toán này là bao nhiêu?
*   **Trả lời:** Độ phức tạp là $O((E + V) \log V)$, trong đó $E$ là số cạnh (đường đi) và $V$ là số đỉnh (địa điểm). Việc dùng `PriorityQueue` giúp giảm thời gian tìm node gần nhất xuống $\log V$.

### Câu 5: Nếu file `map_test.txt` của em có hàng triệu dòng, code này có gặp vấn đề gì không?
*   **Trả lời:** Code sử dụng `BufferedReader` để đọc từng dòng nên sẽ không bị tràn bộ nhớ khi đọc file. Tuy nhiên, `HashMap` và `ArrayList` sẽ phình to ra theo số lượng node. Nếu dữ liệu cực lớn, ta có thể cần dùng database thay vì load hết vào RAM.

### Câu 6: Trong file `GraphTest.java`, tại sao em lại dùng `ByteArrayOutputStream` để test?
*   **Trả lời:** Vì các hàm của em in kết quả ra màn hình bằng `System.out.println`. Để máy có thể tự động kiểm tra (Assert) xem code in ra có đúng không, em phải "đánh chặn" (redirect) luồng in đó vào một mảng byte để đọc lại bằng code.

### Câu 7: Làm sao em xử lý được đồ thị vô hướng (đi qua đi lại được) trong khi code của em có class `Edge`?
*   **Trả lời:** Trong hàm `addEdge`, em thêm cạnh 2 lần: một lần từ A sang B và một lần ngược lại từ B sang A. Điều này biến các đường nối trở thành hai chiều.

### Câu 8: Điều gì xảy ra nếu đồ thị bị ngắt quãng (ví dụ có 2 hòn đảo không có cầu nối)?
*   **Trả lời:** Thuật toán của em có kiểm tra điều này. Nếu sau khi quét hết mà khoảng cách đến đích vẫn là `Double.MAX_VALUE`, code sẽ in ra thông báo: *"No path exists"* (Không có đường đi).

### Câu 9: Tại sao em lại tách `GraphTest` và `GraphOutputTest` ra riêng?
*   **Trả lời:** `GraphTest` dùng để kiểm tra tính đúng đắn kỹ thuật (Unit Test) với dữ liệu giả lập. `GraphOutputTest` dùng để trình diễn (Demo) kết quả thực tế trên bản đồ. Việc tách ra giúp code sạch sẽ và dễ trình bày hơn.

### Câu 10: Nếu muốn thay đổi thuật toán từ Dijkstra sang tìm tất cả các đường đi có thể, em sẽ sửa ở đâu?
*   **Trả lời:** Em sẽ đổi hàm `dijkstra` sang dùng thuật toán **DFS (Depth-First Search)** kết hợp với Backtracking để liệt kê toàn bộ các đường thay vì chỉ tìm đường ngắn nhất.

## PHẦN 2: CÁC CÂU HỎI VỀ KỸ THUẬT TEST (GraphTest & GraphOutputTest)

### Câu 11: Tại sao em lại cần `@BeforeEach` và `@AfterEach` trong file `GraphTest.java`?
*   **Trả lời:** `@BeforeEach` giúp khởi tạo một đồ thị mới cho mỗi test case để chúng không bị chồng chéo dữ liệu. `@AfterEach` dùng để khôi phục lại `System.out` và `System.err` về trạng thái mặc định của hệ thống, tránh làm ảnh hưởng đến các phần test khác của Java.

### Câu 12: Tại sao em phải gọi lệnh `outContent.reset()` trước khi kiểm tra Dijkstra?
*   **Trả lời:** Vì trước đó em đã gọi hàm `loadFromFile`, hàm này đã in ra dòng "Successfully loaded...". Nếu không `reset()`, biến `outContent` sẽ chứa cả dòng đó, khiến việc so sánh kết quả đường đi của Dijkstra bị sai lệch hoặc khó kiểm tra chính xác.

### Câu 13: Điều gì xảy ra nếu file `map_test.txt` bị xóa hoặc đổi tên?
*   **Trả lời:** Em đã sử dụng khối `try-catch` với `IOException`. Nếu file không tồn tại, chương trình sẽ không bị "văng" lỗi (crash) mà sẽ in ra thông báo lỗi đỏ: *"File reading error: ... (The system cannot find the file specified)"* để người dùng biết.

### Câu 14: Tại sao em lại chạy lệnh `mvn test -Dtest=GraphOutputTest` mà không gõ `mvn test` cho gọn?
*   **Trả lời:** Lệnh `-Dtest=GraphOutputTest` giúp em chỉ chạy duy nhất file trình diễn này thôi. Nếu dùng `mvn test`, Maven sẽ chạy toàn bộ các file test trong dự án (bao gồm cả BST, LinkedList...), làm màn hình console bị tràn ngập thông tin, rất khó để thầy cô theo dõi phần trình diễn Graph của em.

### Câu 15: Cờ hiệu `-Dsurefire.useFile=false` có tác dụng gì trong lệnh Maven?
*   **Trả lời:** Mặc định Maven sẽ lưu kết quả in (System.out) vào các file log trong thư mục `target`. Cờ hiệu này ép Maven phải "đẩy" toàn bộ kết quả đó trực tiếp ra màn hình console để mọi người có thể nhìn thấy đường đi của thuật toán ngay lập tức.

### Câu 16: Trong `GraphTest`, em kiểm tra đồ thị vô hướng như thế nào?
*   **Trả lời:** Trong hàm `testAddLocationAndEdge`, em kiểm tra cả 2 chiều: A nối với B và B nối với A. Nếu in Graph ra mà thấy cả 2 chiều đều có thông tin kết nối thì chứng tỏ hàm `addEdge` đã xử lý đúng tính chất vô hướng.

### Câu 17: Tại sao trong `testAddEdgeInvalidNode`, em lại kiểm tra luồng lỗi `errContent`?
*   **Trả lời:** Vì khi người dùng thêm đường nối cho một địa điểm chưa tồn tại, code của em in thông báo lỗi vào `System.err`. Em kiểm tra luồng này để đảm bảo chương trình có đưa ra cảnh báo đúng cho người dùng thay vì im lặng và bỏ qua lỗi.

### Câu 18: Nếu em sửa đổi một khoảng cách trong file `map_test.txt`, test nào sẽ bị ảnh hưởng?
*   **Trả lời:** File `GraphOutputTest` sẽ bị ảnh hưởng trực tiếp vì nó đọc dữ liệu từ file này để tính toán. Nếu khoảng cách thay đổi đủ lớn làm đổi lộ trình ngắn nhất, kết quả in ra sẽ khác đi. Ngược lại, `GraphTest` (phần dùng dữ liệu giả Quy Nhon City - Dieu Tri) sẽ không bị ảnh hưởng vì nó dùng dữ liệu "cứng" trong code.

### Câu 19: Tại sao trong `GraphOutputTest` em lại chia ra các "Phase 1, 2, 3"?
*   **Trả lời:** Để làm cho buổi thuyết trình có cấu trúc rõ ràng ạ. Phase 1 là nạp dữ liệu, Phase 2 là show cấu trúc dữ liệu (Adjacency List), Phase 3 là trình diễn thuật toán. Điều này giúp thầy cô thấy được logic xử lý dữ liệu của em theo từng bước.

### Câu 20: Trong `GraphTest`, tại sao em lại đặt ID và Name của địa điểm khác nhau (ví dụ: Quy Nhon và Quy Nhon City)?
*   **Trả lời:** Để kiểm tra xem code của em có nhầm lẫn giữa "Mã định danh" (ID) và "Tên hiển thị" (Name) không. Khi in ra, nếu nó hiện đúng "Quy Nhon City" (Name) thay vì "Quy Nhon" (ID) thì chứng tỏ hệ thống quản lý thông tin địa điểm chính xác.
