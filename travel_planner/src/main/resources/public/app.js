/* ═══════════════════════════════════════════════════════════
   Travel Planner — app.js
   CSD201 Group 5 — Fetch API connecting to Java backend
   ═══════════════════════════════════════════════════════════ */

const BASE = '';  // Same origin — Javalin serves both API and HTML

// ─── City GPS Coordinates (for Leaflet map) ───────────────────────────────────
const CITY_COORDS = {
    'Hue': [16.4637, 107.5909],
    'Da Nang': [16.0544, 108.2022],
    'Hoi An': [15.8801, 108.3380],
    'Quang Nam': [15.5736, 108.4736],
    'Quang Ngai': [15.1213, 108.8047],
    'Quy Nhon': [13.7765, 109.2235],
    'Tay Son': [13.9544, 108.9889],
    'Song Cau': [13.4407, 109.2153],
    'Chi Thanh': [13.3028, 109.1942],
    'Tuy Hoa': [13.0955, 109.3212],
    'Nha Trang': [12.2388, 109.1967],
    // --- Khu vực nhà user (Tuyến thực tế qua QL19) ---
    'Nha Minh':   [13.884554, 108.983122], // Bình Nghi
    'Tx. An Nhon': [13.8687, 109.0565], // Dọc QL19
    'Thap Banh It': [13.8427, 109.1174], // Ngã 3
    'Phuoc Thuan': [13.8180, 109.1866], // Tuyến QL19B
    'DT638': [13.8199, 109.1364], // Ngã 4
    'Nhon Phu': [13.7935, 109.1812],
    'Nhon Binh': [13.7850, 109.2100], 
    'San Bay Phu Cat': [13.9550, 109.0436], // Sân bay Phù Cát
    'Dai Hoc FPT': [13.791557, 109.229158],
};

// Hardcoded adjacency list (mirrors map_test.txt) for BFS client-side
const GRAPH_ADJ = {
    'Hue': [{ to: 'Da Nang', w: 100 }],
    'Da Nang': [{ to: 'Hue', w: 100 }, { to: 'Hoi An', w: 30 }, { to: 'Quang Nam', w: 70 }],
    'Hoi An': [{ to: 'Da Nang', w: 30 }, { to: 'Quang Nam', w: 65 }],
    'Quang Nam': [{ to: 'Da Nang', w: 70 }, { to: 'Hoi An', w: 65 }, { to: 'Quang Ngai', w: 130 }],
    'Quang Ngai': [{ to: 'Quang Nam', w: 130 }, { to: 'Quy Nhon', w: 175 }],
    'Quy Nhon': [{ to: 'Quang Ngai', w: 175 }, { to: 'Song Cau', w: 20 }, { to: 'Chi Thanh', w: 60 }, { to: 'Tay Son', w: 50 }, { to: 'San Bay Phu Cat', w: 30 }, { to: 'Dai Hoc FPT', w: 5 }],
    'Tay Son': [{ to: 'Quy Nhon', w: 50 }, { to: 'Tx. An Nhon', w: 20 }],
    'Song Cau': [{ to: 'Quy Nhon', w: 20 }, { to: 'Chi Thanh', w: 30 }, { to: 'Tuy Hoa', w: 100 }],
    'Chi Thanh': [{ to: 'Quy Nhon', w: 60 }, { to: 'Song Cau', w: 30 }, { to: 'Tuy Hoa', w: 40 }],
    'Tuy Hoa': [{ to: 'Song Cau', w: 100 }, { to: 'Chi Thanh', w: 40 }, { to: 'Nha Trang', w: 120 }],
    'Nha Trang': [{ to: 'Tuy Hoa', w: 120 }],
    // --- Khu vực nhà user (Tuyến thực tế) ---
    'Nha Minh': [{to: 'Tx. An Nhon', w: 12}, {to: 'San Bay Phu Cat', w: 20}],
    'Tx. An Nhon': [{to: 'Nha Minh', w: 12}, {to: 'Thap Banh It', w: 8}, {to: 'Tay Son', w: 20}],
    'Thap Banh It': [{to: 'Tx. An Nhon', w: 8}, {to: 'Phuoc Thuan', w: 8}, {to: 'DT638', w: 6}],
    'Phuoc Thuan': [{to: 'Thap Banh It', w: 8}, {to: 'Dai Hoc FPT', w: 5}],
    'DT638': [{to: 'Thap Banh It', w: 6}, {to: 'Nhon Phu', w: 6}, {to: 'Nhon Binh', w: 8}],
    'Nhon Phu': [{to: 'DT638', w: 6}, {to: 'Dai Hoc FPT', w: 4}],
    'Nhon Binh': [{to: 'DT638', w: 8}, {to: 'Dai Hoc FPT', w: 3}],
    'San Bay Phu Cat': [{to: 'Nha Minh', w: 20}, {to: 'Dai Hoc FPT', w: 35}, {to: 'Quy Nhon', w: 30}],
    'Dai Hoc FPT': [{to: 'Phuoc Thuan', w: 5}, {to: 'Nhon Phu', w: 4}, {to: 'Nhon Binh', w: 3}, {to: 'San Bay Phu Cat', w: 35}, {to: 'Quy Nhon', w: 5}],
};

// ─── BFS (Fewest hops) client-side ────────────────────────────────────────────
function bfsFindPath(start, end) {
    const queue = [[start]];
    const visited = new Set([start]);
    while (queue.length > 0) {
        const path = queue.shift();
        const last = path[path.length - 1];
        if (last === end) return path;
        const neighbors = GRAPH_ADJ[last] || [];
        for (const { to } of neighbors) {
            if (!visited.has(to)) {
                visited.add(to);
                queue.push([...path, to]);
            }
        }
    }
    return null;
}

function calcPathDistance(path) {
    let total = 0;
    for (let i = 0; i < path.length - 1; i++) {
        const edgeObj = (GRAPH_ADJ[path[i]] || []).find(e => e.to === path[i + 1]);
        if (edgeObj) total += edgeObj.w;
    }
    return total;
}

// ─── UI Helper Functions ──────────────────────────────────────────────────────
function showSkeleton(elementId, type = 'list') {
    const el = document.getElementById(elementId);
    if (!el) return;
    
    let html = '';
    if (type === 'list') {
        for (let i = 0; i < 3; i++) {
            html += `
                <div class="tour-item skeleton" style="opacity: 0.7">
                    <div class="skeleton-avatar" style="background: #e2e8f0"></div>
                    <div class="tour-item-info">
                        <div class="skeleton skeleton-title"></div>
                        <div class="skeleton skeleton-text"></div>
                    </div>
                </div>
            `;
            if (i < 2) html += '<div class="tour-connector" style="opacity:0.2">↓</div>';
        }
    } else if (type === 'card') {
        html = `
            <div class="customer-card skeleton" style="opacity: 0.7; height: 80px">
                <div class="skeleton-avatar" style="background: #e2e8f0"></div>
                <div style="flex:1">
                    <div class="skeleton skeleton-title"></div>
                    <div class="skeleton skeleton-text" style="width: 40%"></div>
                </div>
            </div>
        `;
    } else if (type === 'map') {
        html = `
            <div class="result-success skeleton" style="opacity: 0.7; height: 120px">
                <div class="skeleton skeleton-title"></div>
                <div class="skeleton skeleton-text"></div>
                <div class="skeleton skeleton-text" style="width: 60%"></div>
            </div>
        `;
    }
    el.innerHTML = html;
    el.classList.remove('hidden');
}

// ─── Leaflet map state ────────────────────────────────────────────────────────
let leafletMap = null;
let dijkstraLayer = null;
let bfsLayer = null;
let markersLayer = null;
let animTimer = null;

function initLeafletMap() {
    if (leafletMap) return;
    leafletMap = L.map('leaflet-map').setView([13.44, 109.21], 9);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors',
        maxZoom: 18
    }).addTo(leafletMap);
}

// ─── Feature 3: Animate Dijkstra route segment-by-segment ────────────────────
function animateRoute(coords, onDone) {
    if (animTimer) clearInterval(animTimer);
    if (dijkstraLayer) { leafletMap.removeLayer(dijkstraLayer); dijkstraLayer = null; }

    let i = 1;
    dijkstraLayer = L.polyline([coords[0]], {
        color: '#4f8ef7', weight: 5, opacity: 0.95, dashArray: '8,6'
    }).addTo(leafletMap);

    animTimer = setInterval(() => {
        if (i >= coords.length) {
            clearInterval(animTimer);
            if (onDone) onDone();
            return;
        }
        dijkstraLayer.addLatLng(coords[i]);
        i++;
    }, 350);
}

// ─── Feature 1+2: Draw both routes + rich popups ─────────────────────────────
function drawRouteOnMap(dijkstraSteps, startCity, endCity) {
    // Clear all old layers
    [dijkstraLayer, bfsLayer, markersLayer].forEach(l => { if (l) leafletMap.removeLayer(l); });
    dijkstraLayer = bfsLayer = markersLayer = null;

    const dijkstraCoords = dijkstraSteps.map(c => CITY_COORDS[c]).filter(Boolean);
    if (dijkstraCoords.length < 2) return;

    // ── FEATURE 3: Animate Dijkstra first, then draw BFS on top ──────────────
    const dijkstraDist = calcPathDistance(dijkstraSteps);
    const bfsPath = bfsFindPath(startCity, endCity);

    animateRoute(dijkstraCoords, () => {
        // BFS drawn AFTER animation → sits on top of Dijkstra line
        if (bfsPath && bfsPath.length >= 2) {
            if (bfsLayer) { leafletMap.removeLayer(bfsLayer); }
            const bfsCoords = bfsPath.map(c => CITY_COORDS[c]).filter(Boolean);
            const bfsDist = calcPathDistance(bfsPath);
            bfsLayer = L.polyline(bfsCoords, {
                color: '#f87171', weight: 5, opacity: 0.9, dashArray: '14, 8'
            }).addTo(leafletMap);
            bfsLayer.bindTooltip(
                `🔴 BFS (ít trạm nhất): ${bfsPath.join(' → ')} = ${bfsDist}km`,
                { permanent: false, direction: 'top' }
            );
        }

        // Update legend
        document.getElementById('map-legend').style.display = 'flex';
        document.getElementById('legend-bfs-dist').textContent =
            bfsPath ? `BFS: ${bfsPath.join('→')} = ${calcPathDistance(bfsPath)}km` : 'BFS: N/A';
        document.getElementById('legend-dijk-dist').textContent =
            `Dijkstra: ${dijkstraSteps.join('→')} = ${dijkstraDist}km`;

        // Move Dijkstra line to top so it's not hidden by BFS
        if (dijkstraLayer) dijkstraLayer.bringToFront();
    });


    // ── FEATURE 2: Rich marker popups ────────────────────────────────────────
    markersLayer = L.layerGroup();
    let cumulativeDist = 0;
    dijkstraSteps.forEach((city, i) => {
        const coord = CITY_COORDS[city];
        if (!coord) return;

        if (i > 0) {
            const prev = dijkstraSteps[i - 1];
            const edge = (GRAPH_ADJ[prev] || []).find(e => e.to === city);
            if (edge) cumulativeDist += edge.w;
        }

        const isStart = i === 0;
        const isEnd = i === dijkstraSteps.length - 1;
        const color = isStart ? '#34d399' : isEnd ? '#f87171' : '#4f8ef7';
        const size = (isStart || isEnd) ? 16 : 12;

        const icon = L.divIcon({
            html: `<div style="
                background:${color};width:${size}px;height:${size}px;
                border-radius:50%;border:3px solid white;
                box-shadow:0 2px 8px rgba(0,0,0,0.6);
                cursor:pointer;
            "></div>`,
            iconSize: [size, size], iconAnchor: [size / 2, size / 2], className: ''
        });

        // Next stop info
        const nextCity = dijkstraSteps[i + 1];
        const nextEdge = nextCity ? (GRAPH_ADJ[city] || []).find(e => e.to === nextCity) : null;
        const nextInfo = nextEdge
            ? `<br><span style="color:#8892aa">→ Tiếp theo: <b>${nextCity}</b> (${nextEdge.w}km)</span>`
            : `<br><span style="color:#34d399">✅ Điểm đến cuối cùng</span>`;

        const roleLabel = isStart ? '🟢 Xuất phát' : isEnd ? '🔴 Đích đến' : '📍 Trung gian';

        const popup = L.popup({ className: 'custom-popup' }).setContent(`
            <div style="font-family:Inter,sans-serif;min-width:160px">
                <div style="font-weight:700;font-size:1rem;margin-bottom:6px">${city}</div>
                <div style="font-size:0.8rem">${roleLabel}</div>
                <div style="font-size:0.8rem;margin-top:4px">
                    📏 Cách xuất phát: <b>${cumulativeDist} km</b>
                </div>
                ${nextInfo}
            </div>
        `);

        L.marker(coord, { icon }).addTo(markersLayer).bindPopup(popup);
    });
    markersLayer.addTo(leafletMap);

    // Fit map to Dijkstra route bounds
    leafletMap.fitBounds(L.latLngBounds(dijkstraCoords), { padding: [40, 40] });
}


// ─── Tab Navigation ───────────────────────────────────────────────────────────
function switchTab(name) {
    document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
    document.querySelectorAll('.panel').forEach(p => p.classList.remove('active'));
    document.getElementById('tab-' + name).classList.add('active');
    document.getElementById('panel-' + name).classList.add('active');
    if (name === 'tour') loadTour();
}

// ─── Utility ──────────────────────────────────────────────────────────────────
function showResult(elementId, html, isError = false) {
    const el = document.getElementById(elementId);
    el.className = 'result-area ' + (isError ? 'result-error' : 'result-success');
    el.innerHTML = html;
}

// ═══════════════════════════════════════════════════════════
// TAB 1: MAP / GRAPH / DIJKSTRA
// ═══════════════════════════════════════════════════════════

function quickRoute(start, end) {
    document.getElementById('map-start').value = start;
    document.getElementById('map-end').value = end;
    findRoute();
}

async function findRoute() {
    const start = document.getElementById('map-start').value.trim();
    const end = document.getElementById('map-end').value.trim();
    const resultEl = document.getElementById('map-result');
    const mapVizContainer = document.getElementById('leaflet-map-container');

    if (!start || !end) {
        showResult('map-result', '⚠️ Vui lòng nhập cả điểm xuất phát và điểm đến.', true);
        return;
    }

    try {
        showSkeleton('map-result', 'map');
        mapVizContainer.classList.add('hidden');
        
        const res = await fetch(`${BASE}/api/map/route?start=${encodeURIComponent(start)}&end=${encodeURIComponent(end)}`);

        if (!res.ok) {
            showResult('map-result', `❌ ${data.error}`, true);
            document.getElementById('leaflet-map-container').classList.add('hidden');
            return;
        }

        const data = await res.json();
        const stepsHtml = data.steps.map((city, i) =>
            (i > 0 ? '<span class="route-arrow">→</span>' : '') +
            `<span class="route-city">${city}</span>`
        ).join('');

        showResult('map-result', `
            <div class="route-distance">📍 ${data.distance}</div>
            <p style="margin-bottom:12px;color:#8892aa;font-size:0.85rem">Lộ trình tối ưu bằng thuật toán Dijkstra:</p>
            <div class="route-path">${stepsHtml}</div>
        `);

        // Populate dynamic info box at bottom
        const infoBox = document.getElementById('dynamic-info-box');
        const mapViz = document.getElementById('dynamic-map-viz');
        const mapHint = document.getElementById('dynamic-map-hint');

        let vizHtml = '';
        const steps = data.steps;
        for (let i = 0; i < steps.length; i++) {
            vizHtml += `<div class="city-node">${steps[i]}</div>`;
            if (i < steps.length - 1) {
                const edgeObj = (GRAPH_ADJ[steps[i]] || []).find(e => e.to === steps[i + 1]);
                const w = edgeObj ? edgeObj.w : '?';
                vizHtml += `<div class="edge-label">${w}km</div>`;
            }
        }

        mapViz.innerHTML = vizHtml;
        mapHint.innerHTML = `💡 Dijkstra chọn: ${steps.join(' → ')} = <strong>${data.distance}</strong>`;
        infoBox.style.display = 'block';

        // Show and draw the Leaflet map
        const mapContainer = document.getElementById('leaflet-map-container');
        mapContainer.classList.remove('hidden');
        initLeafletMap();
        // Trigger resize in case map was hidden before
        setTimeout(() => {
            leafletMap.invalidateSize();
            drawRouteOnMap(data.steps, start, end);
        }, 100);

    } catch (e) {
        showResult('map-result', '❌ Không thể kết nối đến server. Hãy đảm bảo WebApp.java đang chạy.', true);
    }
}


// ═══════════════════════════════════════════════════════════
// TAB 2: TOUR / LINKED LIST
// ═══════════════════════════════════════════════════════════

async function loadTour() {
    const listEl = document.getElementById('tour-list');
    try {
        // Only show skeleton if list isn't already loaded or we want explicit refresh
        if (listEl.innerHTML.includes('empty-state') || listEl.innerHTML === '') {
            showSkeleton('tour-list', 'list');
        }
        
        const res = await fetch(`${BASE}/api/tour`);
        const stops = await res.json();

        if (!stops || stops.length === 0) {
            listEl.innerHTML = `
                <div class="empty-state">
                    <span class="empty-state-icon">📅</span>
                    <p>Lịch trình đang trống. Hãy thêm địa điểm mới ở trên!</p>
                </div>
            `;
            return;
        }

        const icons = ['🏙️', '🏖️', '🌊', '⛰️', '🌴', '🦋', '🗺️'];
        let html = '';
        stops.forEach((stop, i) => {
            html += `
                <div class="tour-item" id="item-${stop.id}">
                    <div class="tour-item-icon">${icons[i % icons.length]}</div>
                    <div class="tour-item-info">
                        <div class="tour-item-name">${stop.name}</div>
                        <div class="tour-item-desc">${stop.description || 'Không có mô tả'}</div>
                    </div>
                    <span class="tour-item-price">${stop.price > 0 ? stop.price + 'K' : 'Free'}</span>
                    <button class="btn btn-danger" onclick="removeTourStop('${stop.id}', '${stop.name}')">✕</button>
                </div>
            `;
            if (i < stops.length - 1) {
                html += `<div class="tour-connector">↓</div>`;
            }
        });
        listEl.innerHTML = html;
    } catch (e) {
        listEl.innerHTML = '<div class="loading" style="color:#f87171">❌ Không thể kết nối. Hãy chạy WebApp.java trước!</div>';
    }
}

async function addTourStop() {
    const id = document.getElementById('tour-id').value.trim();
    const name = document.getElementById('tour-name').value.trim();
    const desc = document.getElementById('tour-desc').value.trim();
    const price = parseFloat(document.getElementById('tour-price').value) || 0;

    if (!id || !name) { alert('Vui lòng nhập ID và Tên địa điểm!'); return; }

    try {
        const res = await fetch(`${BASE}/api/tour/add`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id, name, description: desc, price })
        });
        if (res.ok) {
            document.getElementById('tour-id').value = '';
            document.getElementById('tour-name').value = '';
            document.getElementById('tour-desc').value = '';
            document.getElementById('tour-price').value = '';
            loadTour();
        } else {
            const data = await res.json();
            alert('Lỗi: ' + data.error);
        }
    } catch (e) {
        alert('Không thể kết nối server!');
    }
}

async function removeTourStop(id, name) {
    if (!confirm(`Xóa địa điểm "${name}" khỏi lịch trình?`)) return;
    try {
        const res = await fetch(`${BASE}/api/tour/${id}`, { method: 'DELETE' });
        if (res.ok) {
            loadTour();
        } else {
            const data = await res.json();
            alert('Lỗi: ' + data.error);
        }
    } catch (e) { alert('Không thể kết nối server!'); }
}

// ═══════════════════════════════════════════════════════════
// TAB 3: CUSTOMER / BST
// ═══════════════════════════════════════════════════════════

function quickSearch(id) {
    document.getElementById('customer-search-id').value = id;
    searchCustomer();
}

async function searchCustomer() {
    const id = document.getElementById('customer-search-id').value.trim();
    const resultEl = document.getElementById('customer-search-result');

    if (!id) {
        showResult('customer-search-result', '⚠️ Vui lòng nhập ID khách hàng!', true);
        return;
    }

    try {
        showSkeleton('customer-search-result', 'card');
        const res = await fetch(`${BASE}/api/customer/${encodeURIComponent(id)}`);
        const data = await res.json();

        if (!res.ok) {
            showResult('customer-search-result', `❌ ${data.error}`, true);
            return;
        }

        showResult('customer-search-result', `
            <div class="customer-card">
                <div class="customer-avatar">👤</div>
                <div>
                    <div class="customer-name">${data.name}</div>
                    <div class="customer-detail">ID: ${data.id} | 📞 ${data.phone || 'N/A'}</div>
                </div>
            </div>
        `);
    } catch (e) {
        showResult('customer-search-result', '❌ Không thể kết nối server!', true);
    }
}

async function addCustomer() {
    const id = document.getElementById('cust-id').value.trim();
    const name = document.getElementById('cust-name').value.trim();
    const phone = document.getElementById('cust-phone').value.trim();

    if (!id || !name) { alert('Vui lòng nhập đầy đủ ID và Họ tên!'); return; }

    try {
        const res = await fetch(`${BASE}/api/customer/add`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id, name, phone })
        });
        const data = await res.json();
        if (res.ok) {
            showResult('customer-add-result', `✅ ${data.message}`);
            document.getElementById('cust-id').value = '';
            document.getElementById('cust-name').value = '';
            document.getElementById('cust-phone').value = '';
        } else {
            showResult('customer-add-result', `❌ ${data.error}`, true);
        }
    } catch (e) {
        showResult('customer-add-result', '❌ Không thể kết nối server!', true);
    }
}

// ─── On page load: init Map tab ───────────────────────────────────────────────
document.addEventListener('DOMContentLoaded', () => {
    // Map tab is active by default
});
