<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Select Carrier Position</title>
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
<style>
#map {
	height: 500px;
	width: 100%;
	margin-bottom: 10px;
}

.mode-buttons {
	margin-bottom: 10px;
	padding: 10px;
	background-color: #f0f0f0;
	border-radius: 5px;
}

.mode-btn {
	padding: 8px 16px;
	margin-right: 10px;
	border: 2px solid #ccc;
	background-color: white;
	cursor: pointer;
	border-radius: 4px;
}

.mode-btn.active {
	background-color: #007bff;
	color: white;
	border-color: #007bff;
}

.mode-btn:hover {
	background-color: #e9ecef;
}

.mode-btn.active:hover {
	background-color: #0056b3;
}

#current-mode {
	font-weight: bold;
	color: #007bff;
	margin-left: 10px;
}
</style>
</head>
<body>
<h2>Mission Map Overview</h2>

<div class="mode-buttons">
	<button id="carrier-mode" class="mode-btn active" onclick="setMode('carrier')">Move Carrier</button>
	<button id="target-mode" class="mode-btn" onclick="setMode('target')">Add Target</button>
	<span id="current-mode">Current Mode: Move Carrier</span>
</div>

<div id="map"></div>

<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
<script>
// グローバル変数
let map;
let currentMode = 'carrier';
let markers = {
    carrier: null,
    aircraft: [],
    waypoints: [],
    targets: []
};

// モード設定関数
function setMode(mode) {
    currentMode = mode;
    
    document.querySelectorAll('.mode-btn').forEach(btn => btn.classList.remove('active'));
    document.getElementById(mode + '-mode').classList.add('active');
    
    const modeText = mode === 'carrier' ? 'Move Carrier' : 'Add Target';
    document.getElementById('current-mode').textContent = 'Current Mode: ' + modeText;
    
    const mapElement = document.getElementById('map');
    if (mode === 'target') {
        mapElement.style.cursor = 'crosshair';
    } else {
        mapElement.style.cursor = 'pointer';
    }
}

// 座標検証関数
function validateCoordinates(lat, lon) {
    return !isNaN(lat) && !isNaN(lon) && lat >= -90 && lat <= 90 && lon >= -180 && lon <= 180;
}

// マップ初期化
function initializeMap() {
    map = L.map('map').setView([33.123, 132.456], 5);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(map);
    
    // マップクリックイベント
    map.on('click', function(e) {
        if (currentMode === 'carrier') {
            handleCarrierMove(e);
        } else if (currentMode === 'target') {
            handleTargetAdd(e);
        }
    });
}

// キャリアマーカー追加
function addCarrierMarker() {
    const carrier = {
        name: "${ctx.carrier.name}",
        lat: parseFloat("${ctx.carrier.latitude}"),
        lon: parseFloat("${ctx.carrier.longitude}")
    };
    
    if (validateCoordinates(carrier.lat, carrier.lon)) {
        markers.carrier = L.marker([carrier.lat, carrier.lon], { title: carrier.name })
            .addTo(map)
            .bindPopup(`<b>Carrier:</b> ${carrier.name}`);
    }
}

// キャリア移動処理
function handleCarrierMove(e) {
    if (confirm("Move carrier to this location?")) {
        const newCoords = {
            lat: e.latlng.lat,
            lon: e.latlng.lng
        };

        fetch('MapServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newCoords)
        })
        .then(response => {
            if (response.ok) {
                alert("Carrier position updated.");
                location.reload();
            } else {
                alert("Failed to update carrier.");
            }
        })
        .catch(error => {
            console.error('Error updating carrier:', error);
            alert("Error updating carrier position.");
        });
    }
}

// ターゲット追加処理
function handleTargetAdd(e) {
    const targetName = prompt("Enter Target Name:");
    if (!targetName || targetName.trim() === '') {
        alert("Target name is required.");
        return;
    }

    const newTarget = {
        name: targetName.trim(),
        lat: e.latlng.lat,
        lon: e.latlng.lng
    };

    console.log('Sending target data:', newTarget);

    fetch('TargetServlet', {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(newTarget)
    })
    .then(response => {
        console.log('Response status:', response.status);
        if (!response.ok) {
            return response.text().then(text => {
                throw new Error(`Server error: ${response.status} - ${text}`);
            });
        }
        return response.json();
    })
    .then(data => {
        console.log('Success response:', data);
        if (data.status === 'ok') {
            alert("Target added successfully: " + targetName);
            // リロードの代わりに、ターゲットマーカーを直接追加
            addNewTargetMarker(newTarget);
        } else {
            alert("Failed to add target: " + (data.message || 'Unknown error'));
        }
    })
    .catch(error => {
        console.error('Error adding target:', error);
        alert("Failed to add target: " + error.message);
    });
}

// ウェイポイント追加
function addWaypoints() {
    const waypoints = [
        <c:forEach var="wp" items="${ctx.flightPlan.waypoints}" varStatus="status">
        [${wp.latitude}, ${wp.longitude}]<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    if (waypoints.length > 0) {
        // フライトプランルートを描画
        L.polyline(waypoints, { 
            color: 'blue', 
            weight: 3, 
            opacity: 0.7,
            dashArray: '5, 10'
        }).addTo(map);

		//WPを順番に追加
        for (let i = 0; i < waypoints.length; i++) {
            const pos = waypoints[i];
            const wpNumber = i + 1; // 1から開始する番号
            
            if (validateCoordinates(pos[0], pos[1])) {
                const wpMarker = L.marker(pos, {
                    icon: L.divIcon({
                        className: 'waypoint-marker',
                        html: '<div style="background-color: blue; color: white; border-radius: 50%; width: 25px; height: 25px; text-align: center; line-height: 25px; font-weight: bold;">' + wpNumber + '</div>',
                        iconSize: [25, 25]
                    })
                })
                .addTo(map)
                .bindPopup('<b>Waypoint ' + wpNumber + '</b><br>Lat: ' + pos[0].toFixed(6) + '<br>Lon: ' + pos[1].toFixed(6));
                
                markers.waypoints.push(wpMarker);
            }
        }
    }
}


// 航空機追加
function addAircraft() {
    const waypoints = [
        <c:forEach var="wp" items="${ctx.flightPlan.waypoints}" varStatus="status">
        [${wp.latitude}, ${wp.longitude}]<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
    
    if (waypoints.length === 0) return;
    
    // Leader(自機)
    const leaderPos = waypoints.length > 1 ? waypoints[1] : waypoints[0];
    if (validateCoordinates(leaderPos[0], leaderPos[1])) {
        const leaderMarker = L.circleMarker([leaderPos[0], leaderPos[1]], { 
            radius: 8, 
            color: 'blue', 
            fillColor: 'lightblue',
            fillOpacity: 0.8,
            weight: 2
        })
        .addTo(map)
        .bindPopup(`<b>Leader:</b> ${ctx.aircraft.modex}<br>Position: ${leaderPos[0].toFixed(6)}, ${leaderPos[1].toFixed(6)}`);
        
        markers.aircraft.push(leaderMarker);
    }

    // Wingman
    const baseLat = leaderPos[0];
    const baseLon = leaderPos[1];
    const offsetDistance = 0.01;

    <c:if test="${not empty ctx.wingmen}">
    const wingmen = [
        <c:forEach var="wing" items="${ctx.wingmen}" varStatus="status">
        {
            modex: "${wing.modex}",
            lat: baseLat + Math.cos(${status.index} * 45 * Math.PI / 180) * offsetDistance,
            lon: baseLon + Math.sin(${status.index} * 45 * Math.PI / 180) * offsetDistance
        }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    wingmen.forEach(w => {
        if (validateCoordinates(w.lat, w.lon)) {
            const wingmanMarker = L.circleMarker([w.lat, w.lon], { 
                radius: 6, 
                color: 'green',
                fillColor: 'lightgreen',
                fillOpacity: 0.8,
                weight: 2
            })
            .addTo(map)
            .bindPopup(`<b>Wingman:</b> ${w.modex}<br>Position: ${w.lat.toFixed(6)}, ${w.lon.toFixed(6)}`);
            
            markers.aircraft.push(wingmanMarker);
        }
    });
    </c:if>
}

// Target
function addNewTargetMarker(target) {
    if (validateCoordinates(target.lat, target.lon)) {
        const targetMarker = L.marker([target.lat, target.lon], { 
            icon: L.icon({ 
                iconUrl: 'https://maps.google.com/mapfiles/ms/icons/red-dot.png', 
                iconSize: [32, 32],
                iconAnchor: [16, 32],
                popupAnchor: [0, -32]
            }) 
        })
        .addTo(map)
        .bindPopup(`<b>Target:</b> ${target.name}<br>Position: ${target.lat.toFixed(6)}, ${target.lon.toFixed(6)}`);
        
        markers.targets.push(targetMarker);
    }
}



// Map自動調整
function fitMapBounds() {
    const allPoints = [];
    
    // Position(vessel)
    if (markers.carrier) {
        allPoints.push(markers.carrier.getLatLng());
    }
    
    // Position(plane)
    markers.aircraft.forEach(aircraft => {
        allPoints.push(aircraft.getLatLng());
    });
    
    // Position(wp)
    markers.waypoints.forEach(wp => {
        allPoints.push(wp.getLatLng());
    });
    
    // Position(target)
    markers.targets.forEach(target => {
        allPoints.push(target.getLatLng());
    });

    if (allPoints.length > 0) {
        map.fitBounds(allPoints, { 
            padding: [30, 30],
            maxZoom: 12
        });
    }
}

// 初期化
document.addEventListener('DOMContentLoaded', function() {
    initializeMap();
    addCarrierMarker();
    addWaypoints();
    addAircraft();
    addTargets();
    fitMapBounds();
    setMode('carrier');
});
</script>

<p>Leader Latitude: ${ctx.flightPlan.waypoints[0].latitude}, Longitude: ${ctx.flightPlan.waypoints[0].longitude}</p>
<p>Carrier Latitude: ${ctx.carrier.latitude}, Longitude: ${ctx.carrier.longitude}</p>

<form action="CarrierInfo" method="get">
    <input type="hidden" name="action" value="review">
    <button type="submit">Back to Review</button>
</form>

</body>
</html>