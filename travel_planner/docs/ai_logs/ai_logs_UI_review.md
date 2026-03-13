# Nhật ký phát triển (AI Logs) - Giai đoạn 4: Review Toàn bộ Giao diện Web (UI Review)

**Người thực hiện:** Nhóm 5  
**Mô-đun:** 4 (Web UI — index.html / style.css / app.js)  
**Mục tiêu:** Tổng hợp toàn bộ quá trình xây dựng, cải tiến và thiết kế lại giao diện Web của dự án Travel Planner.

---

## 1. Giải thích Code Giao diện (UI Code Explanation)

### 1.1 Cấu trúc file `index.html`

```html
<link href="https://fonts.googleapis.com/css2?family=Inter..." rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
<link rel="stylesheet" href="style.css">
```

- **Inter font** — Font chữ hiện đại thay cho font mặc định của trình duyệt
- **Leaflet CSS** — Style cho bản đồ tương tác
- **style.css** — Style tự viết của nhóm

### 1.2 Cấu trúc 3 Tab

```html
<nav class="tab-nav">
    <button onclick="switchTab('map')">🗺️ Tìm Đường (Graph)</button>
    <button onclick="switchTab('tour')">📅 Lịch Trình (Linked List)</button>
    <button onclick="switchTab('customer')">👤 Khách Hàng (BST)</button>
</nav>
```

Tab nào active thì có class `active`, các tab còn lại ẩn đi. Hàm `switchTab(name)` trong `app.js` xử lý logic này.

### 1.3 Hàm `findRoute()` — Tìm đường Dijkstra

```javascript
async function findRoute() {
    const start = document.getElementById('map-start').value.trim();
    const end   = document.getElementById('map-end').value.trim();

    if (!start || !end) {
        showResult('map-result', '⚠️ Vui lòng nhập đủ 2 điểm.', true);
        return;
    }

    const res  = await fetch(`/api/map/route?start=${encodeURIComponent(start)}&end=${encodeURIComponent(end)}`);
    const data = await res.json();
    // Render kết quả + vẽ bản đồ Leaflet
    drawRouteOnMap(data.steps, start, end);
}
```

**Luồng:** User nhập → Validate → Gọi Java API → Dijkstra chạy → Trả JSON → Render HTML + vẽ bản đồ

### 1.4 Hàm `loadTour()` — Load Linked List

```javascript
stops.forEach((stop, i) => {
    html += `<div class="tour-item">...</div>`;
    if (i < stops.length - 1) html += `<div class="tour-connector">↓</div>`;
});
```

- `icons[i % icons.length]` — Modulo để lặp icon
- Connector `↓` chỉ thêm **giữa** các item, không thêm sau item cuối

### 1.5 BFS client-side trong `app.js`

```javascript
function bfsFindPath(start, end) {
    const queue   = [[start]];
    const visited = new Set([start]);
    while (queue.length > 0) {
        const path = queue.shift();
        const last = path[path.length - 1];
        if (last === end) return path;
        for (const { to } of GRAPH_ADJ[last] || []) {
            if (!visited.has(to)) { visited.add(to); queue.push([...path, to]); }
        }
    }
    return null;
}
```

BFS dùng Queue (FIFO) → luôn tìm đường **ít trạm dừng nhất**, vẽ màu đỏ đứt trên bản đồ để so sánh với Dijkstra.

### 1.6 Giải thích `style.css`

```css
:root {
    --bg-primary: #0f1117;
    --bg-secondary: #1a1d27;
    --accent: #4f8ef7;
}
.result-success { border-left: 3px solid #34d399; background: rgba(52,211,153,0.08); }
.result-error   { border-left: 3px solid #f87171; background: rgba(248,113,113,0.08); }
```

Dùng CSS Variables để quản lý màu sắc tập trung, dễ đổi theme sau này.

---

## 2. Nghiên cứu tích hợp Leaflet.js

### 2.1 Tại sao chọn Leaflet.js?

| Thư viện | Ưu điểm | Nhược điểm |
|----------|---------|-----------|
| **Leaflet.js** | Nhẹ (~40KB), miễn phí, không cần API key | Ít tính năng hơn Google Maps |
| Google Maps API | Mạnh, quen thuộc | Cần API key, tính phí |
| Mapbox | Đẹp, nhiều style | Cần API key |

**Quyết định:** Chọn Leaflet.js — phù hợp môn học, không cần đăng ký tài khoản.

### 2.2 Khởi tạo bản đồ

```javascript
leafletMap = L.map('leaflet-map').setView([13.44, 109.21], 9);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors', maxZoom: 18
}).addTo(leafletMap);
```

### 2.3 Animation lộ trình Dijkstra

```javascript
animTimer = setInterval(() => {
    dijkstraLayer.addLatLng(coords[i]); i++;
}, 350); // Vẽ từng đoạn 350ms/bước
```

### 2.4 Vấn đề & cách xử lý

| Vấn đề | Giải pháp |
|--------|----------|
| Map không hiển thị khi tab bị ẩn | Gọi `leafletMap.invalidateSize()` sau khi hiện |
| Đường cũ không xóa khi tìm đường mới | Gọi `leafletMap.removeLayer()` trước khi vẽ |
| Map khởi tạo nhiều lần | Kiểm tra `if (leafletMap) return;` trước init |

---

## 3. Cải tiến UI (UI Update)

### 3.1 Nút Swap ⇄ — Đổi chiều tuyến đường

```javascript
function swapRoute() {
    const s = document.getElementById('map-start');
    const e = document.getElementById('map-end');
    [s.value, e.value] = [e.value, s.value];
}
```

### 3.2 Thêm Quick Route Chips

Bổ sung các chip tuyến đường địa phương hay dùng:
- `Đà Nẵng → Quy Nhơn` *(mới)*
- `Hội An → Nha Trang` *(mới)*
- `Nhà Mình → ĐH FPT` *(tuyến thực tế)*

### 3.3 Tổng chi phí Linked List

```javascript
const total = stops.reduce((sum, s) => sum + s.price, 0);
// Hiển thị: "💰 Tổng chi phí: 450K₫"
```

### 3.4 Inline validation thay alert()

```javascript
// Cũ: alert('Vui lòng nhập ID!');
// Mới: showInlineError('tour-error', 'Vui lòng nhập ID!');
```

### 3.5 Nút Xóa khách hàng BST

Sau khi tìm thấy khách hàng, hiện nút `Xóa` gọi `DELETE /api/customer/{id}` — demo trực tiếp xóa node BST.

---

## 4. Thiết kế lại giao diện — Tông Trắng Hồng (UI Redesign)

### 4.1 Lý do thay đổi

Phiên bản 1.0 dùng dark mode (đen + xanh). Nhóm quyết định chuyển sang **tông Trắng - Hồng** hiện đại, phù hợp hơn với chủ đề Travel và thân thiện hơn với người dùng.

### 4.2 Bảng màu mới (Color Palette)

| Biến | Màu cũ (Dark) | Màu mới (White-Pink) | Vai trò |
|------|---------------|---------------------|---------|
| `--bg-primary` | `#0f1117` | `#fef6f8` | Nền trang chính |
| `--bg-secondary` | `#1a1d27` | `#ffffff` | Nền card |
| `--accent` | `#4f8ef7` | `#e8547a` | Màu nhấn chính |
| `--text-primary` | `#e2e8f0` | `#2d2d3a` | Màu chữ |
| `--border` | `rgba(255,255,255,0.06)` | `#f3d6de` | Viền |

### 4.3 Logo mới

Thay emoji `✈️` bằng logo file `nou.png` (logo trường FPT Quy Nhơn) để chuyên nghiệp hơn:

```html
<img src="nou.png" alt="FPT Logo" style="height:40px;" />
```

### 4.4 Gradient Header

```css
.header {
    background: linear-gradient(135deg, #e8547a 0%, #f7a8c4 50%, #fce4ec 100%);
}
```

Gradient từ hồng đậm → hồng nhạt → trắng hồng tạo hiệu ứng sang trọng, không "sến".

---

## 5. Tổng kết — 10 Commit UI

| # | Commit Message | Nội dung |
|---|---------------|----------|
| 1 | `feat(ui): init Web UI with 3-tab layout` | Tạo index.html, style.css, app.js |
| 2 | `feat(map): connect Dijkstra API and display route` | Kết nối API, hiển thị kết quả |
| 3 | `feat(map): add Leaflet map with animated Dijkstra route` | Bản đồ Leaflet, animation đường xanh |
| 4 | `feat(map): add BFS comparison layer on map` | Vẽ thêm đường BFS đỏ để so sánh |
| 5 | `feat(map): add swap button and more quick route chips` | Nút ⇄, thêm chips tuyến đường |
| 6 | `feat(tour): add step numbers, connectors and total cost` | Số thứ tự, ↓ connector, tổng chi phí |
| 7 | `feat(customer): add delete button and quick search chips` | Nút xóa BST, thêm chip C002 C004 |
| 8 | `style(ui): update logo to nou.png` | Đổi logo sang ảnh trường FPT |
| 9 | `style(ui): redesign to white-pink theme` | Đổi toàn bộ theme sang trắng hồng |
| 10 | `docs(ui): add UI review and code explanation log` | File log tổng hợp này |

---

## 6. Kết quả kiểm thử thủ công

| Chức năng | Test Case | Kết quả |
|-----------|-----------|---------|
| Dijkstra | Quy Nhon → Tuy Hoa | ✅ 90km, 3 chặng |
| Dijkstra | Hue → Nha Trang | ✅ 450km, 5 chặng |
| Dijkstra | Node không tồn tại | ✅ Hiện thông báo lỗi |
| Linked List | Thêm địa điểm mới | ✅ Cập nhật list ngay |
| Linked List | Xóa địa điểm | ✅ Cập nhật list ngay |
| BST | Tìm C001 | ✅ Nguyen Van An |
| BST | Tìm ID không tồn tại | ✅ Báo "Không tìm thấy" |
| BST | Thêm khách hàng mới | ✅ Insert vào BST |
| Leaflet Map | Hiển thị sau khi Dijkstra | ✅ Animation đường xanh |
| Leaflet Map | BFS overlay | ✅ Đường đỏ đứt hiện sau animation |
bây giừo mình tiếp tục chỉnh UI 
sao đã có k rồi lại có Đ sau chữ k nữa là sao ạ 





Mình thấy vấn đề rồi! Trong UI đang hiển thị 200Kđ — tức là K (nghìn) + ₫ (đồng) bị thừa. Chỉ cần 200K là đủ rõ nghĩa. Sửa nhanh trong 

app.js
:

0 results
0 results
Analyzed
Thấy rồi! Dòng 341 đang dùng 'K₫'. Sửa thành 'K' thôi:

Edited
+1
-1
Đã sửa xong! Bây giờ giá hiển thị sẽ là 200K, 100K... thay vì 200Kđ thừa ký tự.

Bạn refresh trình duyệt (Ctrl + Shift + R) là thấy ngay — không cần restart server vì đây là file tĩnh (JavaScript). 🎉

sao mình thấy cái màu hồng nó hơi bị chói, kiểu mà màu trắng  vs màu hồng nó hơi sáng quá bạn xem dùm mình nói cho mình biết nên chỉnh thành màu gì cho thích hợp ạ

Để tư vấn đúng, mình coi 

style.css
 trước:

Analyzed




Mình đã đọc 

style.css
 xong. Vấn đề là 2 màu accent đang quá tươi và bảo hòa:

css
--accent:  #ff6b9d;  /* Hồng neon — CHÓi */
--accent2: #fb7185;  /* Hồng đỏ — cũng CHÓi */
Dưới đây là 3 hướng chỉnh tùy theo gu của bạn:

🎨 Option 1 — Dusty Rose (hồng trầm, sang trọng nhất)
css
--accent:  #b5687e;   /* Hồng đất - muted, không chói */
--accent2: #c97d91;   /* Hồng phấn trầm */
--border:  #e8d0d8;   /* Viền xám hồng nhẹ */
🎨 Option 2 — Mauve Pink (hồng tím nhạt, trendy)
css
--accent:  #a56b8a;   /* Hồng tím - hơi sang */
--accent2: #c084a0;   /* Mauve sáng hơn */
--border:  #e2ccd8;   /* Viền tím hồng */
🎨 Option 3 — Warm Blush (hồng kem nhẹ, dễ nhìn nhất)
css
--accent:  #d4748f;   /* Hồng kem - vừa đủ nổi, không chói */
--accent2: #e8909f;   /* Hồng nhạt hơn */
--border:  #f2dde3;   /* Viền gần như không thấy */
💡 Gợi ý của mình: Dùng Option 1 (Dusty Rose) — màu #b5687e nhìn chuyên nghiệp, phù hợp với nền trắng, không bị "sến" hay chói mắt. Bạn chọn cái nào thì mình sửa code vào 

style.css
 cho?