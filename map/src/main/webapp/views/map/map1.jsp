<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Final Debugging Map</title>
  <style>
    #map { width: 100%; height: 500px; border: 2px solid red; }
  </style>
</head>
<body>
<h3 id="addr1">주소를 찾고 있습니다...</h3>
<h3 id="addr2"></h3>
<div id="map"></div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9122c6ed65a3629b19d62bab6d93ffaf&libraries=services"></script>

<script>
  // map1.jsp의 <script> 태그 안쪽을 이 최종 코드로 교체하세요.

  $(document).ready(function() {
    var mapContainer = document.getElementById('map');
    var mapOption = {
      center: new kakao.maps.LatLng(37.566826, 127.0786567),
      level: 8
    };
    var map = new kakao.maps.Map(mapContainer, mapOption);
    var markers = [];
    var infowindow = new kakao.maps.InfoWindow({
      removable: true
    });

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(onSuccess, onError);
    } else {
      alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
    }

    function onSuccess(position) {
      var lat = position.coords.latitude;
      var lng = position.coords.longitude;
      var userPosition = new kakao.maps.LatLng(lat, lng);

      map.setCenter(userPosition);
      new kakao.maps.Marker({ map: map, position: userPosition });
      new kakao.maps.Circle({
        map: map,
        center: userPosition,
        radius: 3000,
        strokeWeight: 3, strokeColor: '#007BFF', strokeOpacity: 0.8,
        fillColor: '#007BFF', fillOpacity: 0.15
      });

      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.coord2Address(lng, lat, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          $('#addr1').html(result[0].address.address_name);
        }
      });

      var requestUrl = "/nearby-places?lat=" + lat + "&lng=" + lng;

      $.ajax({
        url: requestUrl,
        method: 'GET',
        dataType: 'json',
        success: function(places) {
          markers.forEach(function(m) { m.setMap(null); });
          markers = [];

          places.forEach(function(p) {
            var markerPosition = new kakao.maps.LatLng(p.lat, p.lng);
            var marker = new kakao.maps.Marker({ map: map, position: markerPosition });
            markers.push(marker);

            kakao.maps.event.addListener(marker, 'click', function() {
              displayInfowindow(p.target, p.title, marker);
            });
          });
        },
        error: function(err) {
          console.error("서버 요청 실패:", err);
        }
      });
    }

    function displayInfowindow(targetId, title, marker) {
      infowindow.setContent('<div style="padding:5px;font-size:12px;">' + title + '<br>상세정보 로딩 중...</div>');
      infowindow.open(map, marker);

      $.ajax({
        url: '/contents/' + targetId,
        method: 'GET',
        dataType: 'json',
        success: function(details) {
          // ✨ ✨ ✨ [최종 수정된 부분] ✨ ✨ ✨
          // 1. 상세 설명 텍스트를 변수에 저장합니다.
          //    만약 details.details가 비어있다면(null), "상세 정보가 없습니다." 라는 글자를 대신 사용합니다.
          var description = details.details || "상세 정보가 없습니다.";

          // 2. 안전한 방식으로 HTML 내용을 만듭니다.
          var content = '<div style="padding:10px;min-width:200px;line-height:1.5;">' +
                  '<div style="font-weight:bold;font-size:14px;margin-bottom:5px;">' + details.title + '</div>' +
                  '<div>' + description + '</div>' +
                  '</div>';

          infowindow.setContent(content);
        },
        error: function() {
          infowindow.setContent('<div style="padding:5px;font-size:12px;">상세정보를 불러오는 데 실패했습니다.</div>');
        }
      });
    }

    function onError(error) {
      alert("위치 정보를 가져오는 데 실패했습니다.");
    }
  });
</script>
</body>
</html>