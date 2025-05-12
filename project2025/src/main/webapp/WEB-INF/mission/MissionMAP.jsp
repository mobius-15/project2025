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
</style>

</head>
<body>
<h2>Mission Map Overview</h2>

<div id="map"></div>

<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
<script>
const map = L.map('map').setView([33.123, 132.456], 5); 
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { attribution: '© OpenStreetMap contributors' }).addTo(map); 

  const carrier = {
    name: "${ctx.carrier.name}",
    lat: parseFloat("${ctx.carrier.latitude}"),
    lon: parseFloat("${ctx.carrier.longitude}") 
    };
  if (!isNaN(carrier.lat) && !isNaN(carrier.lon)) {
  	L.marker([carrier.lat, carrier.lon], { title: carrier.name })
  	.addTo(map)
  	.bindPopup(`<b>Carrier:</b> ${carrier.name}`);
  }
  
  map.on('click', function(e) {
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
	    });
	    }  
	});  
  
  // Flight Plan Route
const waypoints = [
  <c:forEach var="wp" items="${ctx.flightPlan.waypoints}" varStatus="status">
  [${wp.latitude}, ${wp.longitude}]<c:if test="${!status.last}">,</c:if>
  </c:forEach>
];
const lastWP = waypoints.length > 0 ? waypoints[waypoints.length] : null;

// Leader
const leader = {
  modex: "${ctx.aircraft.modex}",
  lat: waypoints[1][0],
  lon: waypoints[1][1]
};
if (!isNaN(leader.lat) && !isNaN(leader.lon) && leader.lat !== 0 && leader.lon !== 0) {
	  L.circleMarker([leader.lat, leader.lon], { radius: 6, color: 'blue' })
	    .addTo(map)
	    .bindPopup(`<b>Leader:</b> ${leader.modex}`);
	}
// Wingmen
const baseLat = leader.lat;
const baseLon = leader.lon;
const offsetDistance = 0.02; // ずらす距離（緯度・経度の小単位）

const wingmen = [
  <c:forEach var="wing" items="${ctx.wingmen}" varStatus="status">
  {
    modex: "${wing.modex}",
    lat: baseLat + Math.cos(${status.index} * 30 * Math.PI / 180) * offsetDistance,
    lon: baseLon + Math.sin(${status.index} * 30 * Math.PI / 180) * offsetDistance
  }<c:if test="${!status.last}">,</c:if>
  </c:forEach>
];

wingmen.filter(w => !isNaN(w.lat) && !isNaN(w.lon))
.forEach(w => {
  L.circleMarker([w.lat, w.lon], { radius: 5, color: 'green' })
    .addTo(map)
    .bindPopup(`<b>Wingman:</b> ${w.modex}`);
});


if (waypoints.length > 0) {
	  L.polyline(waypoints, { color: 'blue', weight: 3 }).addTo(map);

	
	  waypoints.forEach((pos,idx) => {
		  console.log("WP座標:", pos, "インデックス:", idx + 1);
	    L.marker(pos)
	    	.addTo(map)
	    	.bindPopup(`<b>WP ${idx +1}</b>`);
	  });
	}


  // Targets
   const targets = [
  <c:forEach var="target" items="${ctx.targetPoints}" varStatus="status">
  { name: "${target.name}", lat: ${target.lat}, lon: ${target.lon} }<c:if test="${!status.last}">,</c:if>
  </c:forEach>
];
targets.filter(t => !isNaN(t.lat) && !isNaN(t.lon))
  .forEach(tgt => {
    L.marker([tgt.lat, tgt.lon], { icon: L.icon({ iconUrl: 'https://maps.google.com/mapfiles/ms/icons/red-dot.png', iconSize: [32, 32] }) })
      .addTo(map)
      .bindPopup(`<b>Target:</b> ${tgt.name}`);
  });
  
  map.on('click', function(e) {
    const targetName = prompt("Target Name:");
    if (!targetName) return;

    const newTarget = {
        name: targetName,
        lat: e.latlng.lat,
        lon: e.latlng.lng
<!--        radius: 0-->
    };

    fetch('TargetServlet', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newTarget)
    })
    .then(response => {
        if (response.ok) {
            alert("Target added: " + targetName);
            location.reload(); // 再読み込みして ctx.targetPoints を再取得
        } else {
            alert("Failed to add target.");
        }
    })
    
});
  

  // Auto Fit Bounds
const allPoints = [
  ...(!isNaN(carrier.lat) && !isNaN(carrier.lon) && carrier.lat !== 0 && carrier.lon !== 0 ? [[carrier.lat, carrier.lon]] : []),
  ...(!isNaN(leader.lat) && !isNaN(leader.lon) && leader.lat !== 0 && leader.lon !== 0 ? [[leader.lat, leader.lon]] : []),
  ...wingmen.filter(w => !isNaN(w.lat) && !isNaN(w.lon) && w.lat !== 0 && w.lon !== 0).map(w => [w.lat, w.lon]),
  ...waypoints,
  ...targets.filter(t => !isNaN(t.lat) && !isNaN(t.lon) && t.lat !== 0 && t.lon !== 0).map(t => [t.lat, t.lon])
];

if (allPoints.length > 0) {
	map.fitBounds(allPoints, { padding: [20, 20] });
}

</script>
<p>Leader Latitude: ${ctx.flightPlan.waypoints[0].latitude},Longitude: ${ctx.flightPlan.waypoints[0].longitude}</p>
<p>Carrier Latitude: ${ctx.carrier.latitude},Longitude: ${ctx.carrier.longitude}</p>
<form action="CarrierInfo" method="get">
    <input type="hidden" name="action" value="review">
    <button type="submit">Back to Review</button>
</form>

</body>
</html>
