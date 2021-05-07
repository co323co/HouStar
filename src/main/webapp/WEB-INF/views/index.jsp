<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="GoogleAPIKey" value="AIzaSyCsaVCFsmQlNdAaSqXeyE4J5kONYk145OI" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Happy House | 메인화면</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<%@ include file="/WEB-INF/views/include/banner.jsp"%>

	<section id="select_map">
		<!-- 시군구 선택 자바스크립트 코드 -->
		<script>
				//맨 처음에 시 목록을 불러옴
				let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){
					$.get("${root}/housemap/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});//each
						}//function
						, "json"
					);//get
				});
				
				//ready
				$(document).ready(function(){
					//sido change
					$("#sido").change(function() {
						$.get("${root}/housemap/gugun/"+$("#sido").val()
								,function(data, status){
									$("#gugun").empty();
									$("#gugun").append('<option value="0">선택</option>');
									$.each(data, function(index, vo) {
										$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
									});//each
								}//function
								, "json"
						);//get
					});
					
					//gugun change
					$("#gugun").change(function() {
						$.get("${root}/housemap/dong/"+$("#gugun").val()
								,function(data, status){
									$("#dong").empty();
									$("#dong").append('<option value="0">선택</option>');
									$.each(data, function(index, vo) {
										$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
									});//each
								}//function
								, "json"
						);//get
					});
					
					//dong change
					$("#dong").change(function() {
						
						location.href="${root}/housedeal/list";
						
// 						$.get("${root}/map"
// 								,{act:"apt", dong:$("#dong").val()}
// 								,function(data, status){
// 									$("#searchResult").empty();
// 									$.each(data, function(index, vo) {
// 										let str = "<tr class="+colorArr[index%3]+">"
// 										+ "<td>" + vo.no + "</td>"
// 										+ "<td>" + vo.dong + "</td>"
// 										+ "<td>" + vo.aptName + "</td>"
// 										+ "<td>" + vo.jibun + "</td>"
// 										+ "<td>" + vo.code
// 										+ "</td><td id='lat_"+index+"'></td><td id='lng_"+index+"'></td></tr>";
// 										$("tbody").append(str);
// 										$("#searchResult").append(vo.dong+" "+vo.aptName+" "+vo.jibun+"<br>");
// 									});//each
// 									geocode(data);
// 								}//function
// 								, "json"
// 						);//get
					});
				});//ready
				function geocode(jsonData) {
					let idx = 0;
					$.each(jsonData, function(index, vo) {
						let tmpLat;
						let tmpLng;
						$.get("https://maps.googleapis.com/maps/api/geocode/json"
								,{	key:'${GoogleAPIKey}'
									, address:vo.dong+"+"+vo.aptName+"+"+vo.jibun
								}
								, function(data, status) {
									//alert(data.results[0].geometry.location.lat);
									tmpLat = data.results[0].geometry.location.lat;
									tmpLng = data.results[0].geometry.location.lng;
									$("#lat_"+index).text(tmpLat);
									$("#lng_"+index).text(tmpLng);
									addMarker(tmpLat, tmpLng, vo.aptName);
								}
								, "json"
						);//get
					});//each
				}
		</script>
		<!-- 시군구 select -->
		<div class="bg-dark section pt-4 pb-4">
			<div class="sorting-filters text-center mb-20 d-flex justify-content-center">
				<div class="form-group mr-2">
					<select class="form-control bgPink" name="sido" id="sido">
						<option value="0">선택</option>
					</select>
				</div>
				<div class="form-group md-5">
					<select class="form-control" name="gugun" id="gugun">
						<option value="0">선택</option>
					</select>
				</div>
				<div class="form-group ml-2">
					<select class="form-control" name="dong" id="dong">
						<option value="0">선택</option>
					</select>
				</div>
			</div>
		</div>

		<!-- map -->
		<div class="container mt-5">
			<div id="map" style="width: 100%; height: 400px; margin: auto;"></div>
		</div>
		
		<!-- map 자바스크립트 코드 -->
		<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
				<script defer src="https://maps.googleapis.com/maps/api/js?key=${GoogleAPIKey}&callback=initMap"></script>
				<script>
					var multi = {lat: 37.5665734, lng: 126.978179};
					var map;
					function initMap() {
						map = new google.maps.Map(document.getElementById('map'), {
							center: multi, zoom: 12
						});
						var marker = new google.maps.Marker({position: multi, map: map});
					}
					function addMarker(tmpLat, tmpLng, aptName) {
						var marker = new google.maps.Marker({
							position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
							label: aptName,
							title: aptName
						});
						marker.addListener('click', function() {
							map.setZoom(17);
							map.setCenter(marker.getPosition());
							callHouseDealInfo();
						});
						marker.setMap(map);
					}
					function callHouseDealInfo() {
						alert("you can call HouseDealInfo");
					}
				</script>
	</section>
		
	<!-- 하단 부분 -->
	<div class="container mt-3">
		<div class="row">
			<!-- 중앙 center contents start -->
			<div class="col-md-12" style="margin: 0 auto;">
				<div class="row grid-space-10">
					<div class='col-md-4'>
						<img src='img/sideAD.jpg'>
					</div>
					<div class="col-md-4">
						<h3 class="title">주택 관련 기사</h3>
						<div class="separator-2"></div>
						<ul class="nav flex-column list-style-icons">
							<li class="nav-item"><a class="nav-link " href="#"
								title="<인사> 신동아건설"> <인사> 신동아건설 </a></li>
							<li class="nav-item"><a class="nav-link " href="#"
								title="12.16 대책 후 ‘매수·매도자 모두 일단 관망세’">12.16 대책 후 ‘매수·매도자 모두 일단 관망세’</a></li>
							<li class="nav-item"><a class="nav-link " href="#"
								title="구미시, 낙동강 변 국가3산단에 민간공원 조성">구미시, 낙동강 변 국가3산단에 민간공원 조성</a></li>
							<li class="nav-item"><a class="nav-link " href="#"
								title="‘은행’과 ‘보험사’의 주택담보대출 조건, 어떻게 다를까?">‘은행’과 ‘보험사’의 주택담보대출조건, 어떻게 다를까?</a></li>
						</ul>
						<div class="separator-2"></div>
					</div>
					<div class="col-md-4">
						<h3 class="title">오늘의 뉴스</h3>
						<div class="separator-2"></div>
						<ul>
							<li><a href="#" class="small margin-clear"
								title="KB국민은행, ‘디지털헌금바구니’ 출시">KB국민은행, ‘디지털헌금바구니’ 출시</a></li>

							<li><a href="#" class="small margin-clear"
								title="존슨콘트롤즈, OpenBlue 공개 “135년 쌓아온 빌딩 전문성과 최첨단 기술 결합”">존슨콘트롤즈, OpenBlue 공개 “135년 쌓아온 빌딩 전문성과 최첨단 기술 결합”</a></li>

							<li><a href="#" class="small margin-clear"
								title="산업통상자원부, 업종별 비대면·온라인 수출 기반 본격 가동!">산업통상자원부, 업종별 비대면·온라인 수출 기반 본격 가동</a></li>

							<li><a href="#" class="small margin-clear"
								title="장상인 대표, 제6회 INAK사회공헌대상 프레스클럽부문 ‘시민봉사대상’ 수상">장상인 대표,제6회 INAK사회공헌대상 프레스클럽부문 ‘시민봉사대상’ 수상</a></li>

							<li><a href="#" class="small margin-clear"
								title="삼성전자, 한샘과 공동사업 강화 위한 업무협약 체결">삼성전자, 한샘과 공동사업 강화 위한 업무협약 체결</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
  
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>