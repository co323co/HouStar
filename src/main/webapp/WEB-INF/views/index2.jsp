<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%
	String root = request.getContextPath();
	String ct = (String) session.getAttribute("ct");
	String gg = (String) session.getAttribute("gungu");
%>  

<!DOCTYPE html>
<html>


<head>
  <meta charset="UTF-8">
  <title>Happy House | 메인화면</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script>

    //https://console.developers.google.com    key받기 위한 site

    var locations = [];

    locations = [
      ['도봉구', 37.6658609, 127.0317674],
      ['은평구', 37.6176125, 126.9227004],
      ['동대문구', 37.5838012, 127.0507003],
      ['동작구', 37.4965037, 126.9443073],
      ['금천구', 37.4600969, 126.9001546],
      ['구로구', 37.4954856, 126.858121],
      ['종로구', 37.5990998, 126.9861493],
      ['강북구', 37.6469954, 127.0147158],
      ['중랑구', 37.5953795, 127.0939669],
      ['강남구', 37.4959854, 127.0664091],
      ['강서구', 37.5657617, 126.8226561],
      ['중구', 37.5579452, 126.9941904],
      ['강동구', 37.5492077, 127.1464824],
      ['광진구', 37.5481445, 127.0857528],
      ['마포구', 37.5622906, 126.9087803],
      ['서초구', 37.4769528, 127.0378103],
      ['성북구', 37.606991, 127.0232185],
      ['노원구', 37.655264, 127.0771201],
      ['송파구', 37.5048534, 127.1144822],
      ['서대문구', 37.5820369, 126.9356665],
      ['양천구', 37.5270616, 126.8561534],
      ['영등포구', 37.520641, 126.9139242],
      ['관악구', 37.4653993, 126.9438071],
      ['성동구', 37.5506753, 127.0409622],
      ['용산구', 37.5311008, 126.9810742]
    ];

    var map = null;
    var latitude;
    var longitude;


    ///////////////////////////////////////////////////event 설정 및 초기 설정 
    $(function() {
		///////////////////////////////시, 도 선택 이벤트 처리 
		$('#city').change(function() {
			var city = $(this).val();
			console.log('city 선택', city)

			location.href = '${root}/KMain?act=gungu&city=' + city;
		})

		///////////////////////////////구, 군 선택 이벤트 처리 d
		$('#gu').change(function() {
			var gu = $(this).val();
			console.log('gu 선택', gu)

			location.href = '${root}/KMain?act=dong&gu=' + gu;
		})

		///////////////////////////////동 선택 이벤트 처리
		$('#dong').change(function() {
			var dong = $(this).val()
			location.href = '${root}/KMain?act=trade&city=<%=ct%>&dongcode=<%=gg%>&dong='+dong;
			
			//$('#frm').submit();
		})
	})

    ///////////////////////////////////////////////////맵 설정


    var lat = 37.606991
    var lng = 127.0232185
    var zoom = 11


    function initMap() {
      //getLocation();
      var opt = {
        // google map에 중앙으로 표시할 좌표 설정
        center: { lat: lat, lng: lng },
        zoom: zoom,  //0~ 21  큰 값일 수록  zooming
      };
      //google.maps.Map(map을 그릴 영역, 옵션정보 );
      map = new google.maps.Map(document.getElementById("map"), opt);

      var infowindow = new google.maps.InfoWindow();

      var marker, i;
      for (i = 0; i < locations.length; i++) {
        marker = new google.maps.Marker({
          id: i,
          title: locations[i][0],
          label: locations[i][0],
          position: new google.maps.LatLng(locations[i][1], locations[i][2]),
          map: map
        });

        google.maps.event.addListener(marker, 'click', (function (marker, i) {
          return function () {
            infowindow.setContent(locations[i][0]);
            infowindow.open(map, marker);
          }
        })(marker, i));
        if (marker) {
          marker.addListener('click', function () {
            console.log(this.title);
            map.setZoom(15);
            map.panTo(this.getPosition());
          });
        }
      }
    }



    /// 현재 위치를 최초위치로.

    function showLocation(position) {
      latitude = position.coords.latitude;
      longitude = position.coords.longitude;
      //alert("Latitude : " + latitude + " Longitude: " + longitude);
      //현재 위치 정보를 center로 지정하기 위해 객체 생성
      var hear = new google.maps.LatLng(latitude, longitude);
      map.panTo(hear); //위치 정보를 통해 맵에 표시 

      /* 	
        for (var i = 0; i < results.features.length; i++) {
var coords = results.features[i].geometry.coordinates;
var latLng = new google.maps.LatLng(coords[1],coords[0]);
var marker = new google.maps.Marker({
position: latLng,
map: map
});
}
         */
      /* 	//맵에서 위치 표시하기 위한 정보 
        var marker = new google.maps.Marker({
          position: hear,
          title:"I'm hear!",
          map: map
        }); */

    }

    function errorHandler(err) {
      if (err.code == 1) {
        alert("Error: Access is denied!");
      } else if (err.code == 2) {
        alert("Error: Position is unavailable!");
      } else if (err.code == 3) {
        alert("Erro : Time out");
      }
    }
    function getLocation() {
      if (navigator.geolocation) {
        var options = {
          timeout: 60000,
          enabledHighAccuracy: true
        };
        navigator.geolocation.getCurrentPosition(showLocation,
          errorHandler, options);
      } else {
        alert("Sorry, browser does not support geolocation!");
      }
    }
  </script>

  <style>
    .banner.dark-translucent-bg {
      min-height: 250px;
      padding-top: 60px;
      padding-bottom: 20px;
    }
  </style>
</head>

<body>

  <%@ include file="/WEB-INF/views/include/header.jsp" %>
  <%@ include file="/WEB-INF/views/include/banner.jsp" %>
  
  <%@ include file="/WEB-INF/views/include/index_selectdong.jsp" %>

	
  <div class="container mt-5">
    <div class="row">

      <!-- 중앙 center contents start -->
      <div class="col-md-12" style="margin:0 auto; ">
  
        <!-- Google Map start -->
        <div id="map" style="width: 100%; height: 300px; margin: auto;"></div>
        <script defer
          src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsaVCFsmQlNdAaSqXeyE4J5kONYk145OI&callback=initMap"></script>
        <script>
          var multi ={ lat: 37.55336077601693, lng: 126.98661013591058 };
          var map;
          var markers = [];
          var infoWindow;
  
          function handleLocationError(browserHasGeolocation, infoWindow, pos) {
            infoWindow.setPosition(pos);
            infoWindow.setContent(browserHasGeolocation ?
              'Error: Geolocation 서비스 실패.' :
              'Error: Geolocation을 지원하지 않는 브라우저..');
            infoWindow.open(map);
          }
  
          function addMarker(props) {
            const marker = new google.maps.Marker({
              position: new google.maps.LatLng(parseFloat(props.coords.lat), parseFloat(props.coords.lng)),
              map: map
            });
            map.setCenter(marker.getPosition());
            map.setZoom(15);
  
            if (props.iconImage) {
              marker.setIcon(props.iconImage);
            }
  
            if (props.content) {
              infoWindow = new google.maps.InfoWindow({
                content: props.content
              });
  
            }
  
            marker.addListener('click', function () {
              map.setZoom(17);
              map.setCenter(marker.getPosition());
              bounceMarker(marker);
            });
            markers.push(marker);
            setMapOnAll(map);
          }
  
          function bounceMarker(marker) {
            if (marker.getAnimation() !== null) {
              marker.setAnimation(null);
            } else {
              marker.setAnimation(google.maps.Animation.BOUNCE);
            }
          }
  
          function deleteMarkers() {
            clearMarkers();
            markers = [];
          }
  
          function clearMarkers() {
            setMapOnAll(null);
          }
  
          function setMapOnAll(map) {
            for (let i = 0; i < markers.length; i++) {
              markers[i].setMap(map);
            }
          }
        </script>
        <div style="height: 30px;"></div>
        <div class="row grid-space-10">
          <div class='col-md-4'>
            <img src='img/sideAD.jpg'>
          </div>
          <div class="col-md-4">
            <h3 class="title">주택 관련 기사</h3>
            <div class="separator-2"></div>
            <ul class="nav flex-column list-style-icons">
  
              <li class="nav-item"><a class="nav-link " href="#" title="<인사> 신동아건설">
                  <인사> 신동아건설
                </a></li>
  
              <li class="nav-item"><a class="nav-link " href="#"
                  title="12.16 대책 후 ‘매수·매도자 모두 일단 관망세’">12.16 대책 후 ‘매수·매도자 모두 일단 관망세’</a></li>
  
              <li class="nav-item"><a class="nav-link " href="#" title="구미시, 낙동강 변 국가3산단에 민간공원 조성">구미시,
                  낙동강 변 국가3산단에 민간공원 조성</a></li>
  
              <li class="nav-item"><a class="nav-link " href="#"
                  title="‘은행’과 ‘보험사’의 주택담보대출 조건, 어떻게 다를까?">‘은행’과 ‘보험사’의 주택담보대출 조건, 어떻게 다를까?</a></li>
  
            </ul>
            <div class="separator-2"></div>
          </div>
          <div class="col-md-4">
            <h3 class="title">오늘의 뉴스</h3>
            <div class="separator-2"></div>
            <ul>
  
              <li><a href="#" class="small margin-clear" title="KB국민은행, ‘디지털헌금바구니’ 출시">KB국민은행,
                  ‘디지털헌금바구니’ 출시</a></li>
  
              <li><a href="#" class="small margin-clear"
                  title="존슨콘트롤즈, OpenBlue 공개 “135년 쌓아온 빌딩 전문성과 최첨단 기술 결합”">존슨콘트롤즈, OpenBlue 공개 “135년 쌓아온
                  빌딩 전문성과 최첨단 기술
                  결합”</a></li>
  
              <li><a href="#" class="small margin-clear"
                  title="산업통상자원부, 업종별 비대면·온라인 수출 기반 본격 가동!">산업통상자원부, 업종별 비대면·온라인 수출 기반 본격 가동</a></li>
  
              <li><a href="#" class="small margin-clear"
                  title="장상인 대표, 제6회 INAK사회공헌대상 프레스클럽부문 ‘시민봉사대상’ 수상">장상인 대표, 제6회 INAK사회공헌대상 프레스클럽부문
                  ‘시민봉사대상’ 수상</a></li>
  
              <li><a href="#" class="small margin-clear" title="삼성전자, 한샘과 공동사업 강화 위한 업무협약 체결">삼성전자, 한샘과
                  공동사업 강화 위한 업무협약 체결</a></li>
  
            </ul>
  
          </div>
  
        </div>
      </div>
    </div>

  </div>
  
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>

</html>