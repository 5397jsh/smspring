<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  #map1{
    width:auto;
    height:400px;
    border:2px solid red;
  }
</style>
<script>
  let map1 = {
    addr:null,
    map:null,
    markers: [], // 지도에 표시된 마커들을 관리할 배열
    init:function(){
      this.makeMap();
      // '병원' 버튼 클릭 이벤트
      $('#btn1').click(()=>{
        this.getHospitals();
      });
      // '편의점' 버튼 클릭 이벤트 (기존 로직 유지)
      $('#btn2').click(()=>{
        this.addr ? this.getData(20) : alert('주소를 찾을수 없어요');
      });
    },

    makeMap: function(){
      let mapContainer = document.getElementById('map1');
      let mapOption = {
        center: new kakao.maps.LatLng(37.538453, 127.053110),
        level: 5
      }
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      let mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      let zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition((position)=>{
          let lat = position.coords.latitude;  // 위도
          let lng = position.coords.longitude; // 경도
          let locPosition = new kakao.maps.LatLng(lat, lng);
          this.goMap(locPosition);
        });
      } else {
        alert('지원하지 않습니다.');
      }
    },
    goMap: function(locPosition){
      // 현재 위치 마커를 생성합니다
      let marker = new kakao.maps.Marker({
        map: this.map,
        position: locPosition
      });
      // 현재 위치로 지도 중심을 이동합니다.
      this.map.panTo(locPosition);

      let geocoder = new kakao.maps.services.Geocoder();
      // 간단 주소 호출
      geocoder.coord2RegionCode(locPosition.getLng(), locPosition.getLat(), this.addDisplay1.bind(this));
      // 상세 주소 호출
      geocoder.coord2Address(locPosition.getLng(), locPosition.getLat(), this.addDisplay2.bind(this));
    },
    addDisplay1:function(result, status){
      if (status === kakao.maps.services.Status.OK) {
        $('#addr1').html(result[0].address_name);
        this.addr = result[0].address_name;
      }
    },
    addDisplay2:function(result, status){
      if (status === kakao.maps.services.Status.OK) {
        var detailAddr = result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
        detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
        $('#addr2').html(detailAddr);
      }
    },
    // 기존 getData 함수는 편의점 찾기 등에 활용할 수 있도록 그대로 둡니다.
    getData:function(type){
      $.ajax({
        url:'/getaddrshop',
        data:{addr:this.addr, type:type},
        success:(result)=>{alert(result)}
      });
    },
    // --- 기능 추가 부분 ---
    // 병원 데이터를 가져와 지도에 표시하는 함수
    getHospitals: function() {
      $.ajax({
        url: '/gethospitals', // 우리가 만든 Controller의 URL
        method: 'GET',
        dataType: 'json', // 받아올 데이터 타입을 json으로 명시
        success: (hospitals) => {
          // 1. 기존에 표시되던 마커들을 모두 제거합니다.
          this.clearMarkers();

          // 2. 받아온 병원 데이터(배열)를 순회하며 마커를 생성합니다.
          hospitals.forEach(h => {
            // 마커를 생성할 위치 객체
            let position = new kakao.maps.LatLng(h.lat, h.lng);

            // 마커 객체 생성
            let marker = new kakao.maps.Marker({
              position: position
            });

            // 생성한 마커를 지도에 표시
            marker.setMap(this.map);

            // 생성된 마커를 this.markers 배열에 추가하여 관리
            this.markers.push(marker);

            // (선택) 마커에 표시할 정보창(인포윈도우) 생성
            let infowindow = new kakao.maps.InfoWindow({
              content: `<div style="padding:5px; font-size:12px;">${h.title}</div>`
            });

            // 마커에 마우스오버/아웃 이벤트를 등록합니다.
            kakao.maps.event.addListener(marker, 'mouseover', () => {
              infowindow.open(this.map, marker);
            });
            kakao.maps.event.addListener(marker, 'mouseout', () => {
              infowindow.close();
            });
          });
        },
        error: (err) => {
          console.error("병원 정보를 가져오는 데 실패했습니다.", err);
          alert("병원 정보를 가져오는 중 오류가 발생했습니다.");
        }
      });
    },
    // 지도 위의 마커들을 제거하는 함수
    clearMarkers: function() {
      this.markers.forEach(marker => {
        marker.setMap(null); // 마커를 지도에서 보이지 않게 함
      });
      this.markers = []; // 마커 배열 초기화
    }
  }

  // 페이지 로드 시 map1 객체 초기화 함수 실행
  $(function(){
    map1.init()
  })
</script>


<div class="col-sm-10">
  <h2>Map1</h2>
  <h3 id="addr1"></h3>
  <h3 id="addr2"></h3>
  <button id="btn1" class="btn btn-primary">병원</button>
  <button id="btn2" class="btn btn-primary">편의점</button>
  <div id="map1"></div>
</div>
