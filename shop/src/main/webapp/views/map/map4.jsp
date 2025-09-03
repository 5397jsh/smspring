<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  #map1{
    width:  900px;
    height: 600px;
    border: 2px solid red;
  }
</style>
<script>
  let map1 = {
    init:function (){
      let mapContainer = document.getElementById('map1');
      let mapOption = {
        center: new kakao.maps.LatLng(36.799136, 127.075880), // 지도의 중심좌표
        level: 7 // 지도의 확대 레벨
      }
      let map = new kakao.maps.Map(mapContainer, mapOption);
      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      let mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      let zoomControl = new kakao.maps.ZoomControl();
      map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // 마커를 생성합니다
      let markerPosition  = new kakao.maps.LatLng(36.799136, 127.075880);
      let marker = new kakao.maps.Marker({
        position: markerPosition,
        map:map
      });

      // Infowindow
      let iwContent = 'Info Window';
      let infowindow = new kakao.maps.InfoWindow({
        content : iwContent
      });

      // Event
      kakao.maps.event.addListener(marker, 'mouseover',function (){
        infowindow.open(map,marker);
      });
      kakao.maps.event.addListener(marker, 'mouseout',function (){
        infowindow.close();
      });
      kakao.maps.event.addListener(marker, 'click',function (){
        location.href='<c:url value="/cust/get"/>'
      });

    }
  }

  $(function (){
    map1.init();
  })
</script>

<div class="col-sm-10">
  <h2>Map1</h2>
  <div id="map1"></div>
</div>
