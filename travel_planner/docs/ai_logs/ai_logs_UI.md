
Building Phase 4 Web UI
All files compiled and server launched successfully on http://localhost:8080. Javalin started in 563ms, serving static files from /public. Now visually verifying the web app in browser.

Files Edited
Progress Updates
1
Adding Javalin dependency to pom.xml and creating backend API controllers

2
Modifying pom.xml to add Javalin, then creating all API controllers and the WebApp main class

3
Adding getHead() to TourLinkedList, creating MapController, WebApp.java, and the HTML/CSS/JS frontend

4
Creating MapController.java, WebApp.java launcher, and the vanilla HTML/CSS/JS frontend files

5
Creating the Vanilla HTML/CSS/JS frontend files (index.html, style.css, app.js) in resources/public

6
Building the project with Maven, then launching WebApp.java to verify the web server starts correctly

7
Fixing Maven exec command syntax for PowerShell and launching the web server

8
Verifying the web app looks correct and all APIs work by opening the browser

Giao diện Web đã chạy thành công 100% tại http://localhost:8080! 🎉





Tóm tắt những gì đã xây dựng:

Module	API	Giao diện	Kết quả
🗺️ Graph (Dijkstra)	GET /api/map/route	Tab Tìm đường	90km Quy Nhơn → Sông Cầu → Chí Thạnh → Tuy Hòa ✅
📅 Linked List	GET/POST/DELETE /api/tour	Tab Lịch trình	Thêm/xóa địa điểm, hiển thị kết nối ✅
👤 BST	GET/POST /api/customer	Tab Khách hàng	Tìm C001 → Nguyen Van An ✅
Để chạy lại bất cứ lúc nào:

mvn exec:java "-Dexec.mainClass=com.travelplanner.app.WebApp"
Rồi mở trình duyệt vào http://localhost:8080

Bạn muốn mình push code lên GitHub luôn không, hay có cần chỉnh thêm gì cho giao diện không ạ?