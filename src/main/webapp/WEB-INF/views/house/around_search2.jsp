<%@page import="com.ssafy.happyhouse.model.dto.HospitalDto"%>
<%@page import="com.ssafy.happyhouse.model.dto.CoronaDto"%>
<%@page import="com.ssafy.happyhouse.model.dto.HouseDealDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,com.ssafy.happyhouse.model.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="dong" value="${dong}"/>
<%
	String root = request.getContextPath();
	String ct = (String) session.getAttribute("ct");
	String gg = (String) session.getAttribute("gungu");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>행복한 우리집</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		//alert(data);
		console.log("성공");

		$("tr:first").css('background', 'black').css('color', 'white');
		$("tr:odd").css('background', 'lightgray');
	});
</script>
<%
	ArrayList<HouseDealDto> tlist = (ArrayList) session.getAttribute("tradeinfo");
	/* var tlist = "${tlist}"; */
	ArrayList<CoronaDto> co = (ArrayList) session.getAttribute("corona");
	ArrayList<HospitalDto> hos = (ArrayList) session.getAttribute("hospital");
%>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsaVCFsmQlNdAaSqXeyE4J5kONYk145OI&callback=initMap"
	async defer></script>
<script type="text/javascript">
	//https://console.developers.google.com    key받기 위한 site
	var locations = [];
	<%if(tlist==null)
		{
			String a = "";
			double b = 37.55336077601693;
			double c = 126.98661013591058;
			String d = "";
			String msg = "imgs/house.JPG";
		}
		else{
		for(int i=0;i<tlist.size();i++)
		{
			if(tlist.get(i).getLat()==null || tlist.get(i).getLng()==null)
			{
				double b = 37.55336077601693;
				double c = 126.98661013591058;
				continue;
			}
			String a = tlist.get(i).getDealAmount();
			double b = Double.parseDouble(tlist.get(i).getLat());
			double c = Double.parseDouble(tlist.get(i).getLng());
			String d = tlist.get(i).getAptname();
			String msg = "imgs/house.JPG";%>
	locations.push(['<%=a%>',<%=b%>,<%=c%>,'<%=d%>','<%=msg%>']);
	<%}
		}%>

	var map = null;
	var latitude;
	var longitude;

	///////////////////////////////////////////////////맵 설정

	function initMap() {
		<%ArrayList<HouseDealDto> tlist2 = (ArrayList) session.getAttribute("tradeinfo");
		double b=0;
		double c=0;
			if (tlist2 == null || tlist2.size()==0) {
				b = 37.55336077601693;
				c = 126.98661013591058;
			}
			else if(tlist.get(0).getLat()==null || tlist.get(0).getLng()==null)
			{
				b = 37.55336077601693;
				c = 126.98661013591058;
			}
			else if(tlist.size()==1)
			{
				b = Double.parseDouble(tlist2.get(0).getLat());
				c = Double.parseDouble(tlist2.get(0).getLng());
			}
			else if(tlist.size()==2)
			{
				b = Double.parseDouble(tlist2.get(1).getLat());
				c = Double.parseDouble(tlist2.get(1).getLng());
			}
			else if(tlist.size()>2)
			{
				b = Double.parseDouble(tlist2.get(2).getLat());
				c = Double.parseDouble(tlist2.get(2).getLng());
			}%>
		//getLocation();
		var opt = {
			// google map에 중앙으로 표시할 좌표 설정
			center : {
				lat : <%=b%>,
				lng : <%=c%>
			},
			zoom : 14, //0~ 21  큰 값일 수록  zooming
		};
		//google.maps.Map(map을 그릴 영역, 옵션정보 );
		map = new google.maps.Map(document.getElementById("map"), opt);

		var infowindow = new google.maps.InfoWindow();

		var marker, i;
		for (i = 0; i < locations.length; i++) {
			console.log(locations[i][3])
			marker = new google.maps.Marker({
				id : i,
				title : locations[i][3],
				label : locations[i][0],
				icon : locations[i][4],
				position : new google.maps.LatLng(locations[i][1],
						locations[i][2]),
				map : map
			});

			google.maps.event.addListener(marker, 'click',(function(marker, i) {
						return function() {
							infowindow.setContent("<center>[" + locations[i][3]
									+ "]<br>거래가 : " + locations[i][0]
									+ "만원</center>");
							infowindow.open(map, marker);
						}
					})(marker, i));
			if (marker) {
				marker.addListener('click', function() {
					location.href = '<%=root%>/KMain?act=RealTrade&name=' + this.title;
					<%ArrayList<HouseDealDto> realtd = (ArrayList) session.getAttribute("real");%>
					
					var type = [ '', '아파트 매매', '아파트 전월세', '다세대 매매', '다세대 전월세' ];
					console.log('결과 도착')
					
					var list = <%=realtd%>;
					console.log(list)
					var contents = $('#houseInfo');
					contents.empty(); //기존에 내용이 있으면 비우기
					$('<h3 class="title">거래 정보</h3><div class="separator-2"></div><h5>'+ list[0].aptName + '</h5><hr>').appendTo(contents);
					$.each(list,function(index, item) {
										var html = '<div class="media margin-clear"><div class="media-body">'
										html += '<h6 class="media-heading">거래금액 :'
												+ item.dealAmount + '</h6>'
										html += '<h6 class="media-heading">전용면적: '
												+ item.area + '</h6>'
										html += '<h6 class="media-heading">거래구분 :'
												+ type[item.type] + '</h6>'
										html += '<p class="small margin-clear"><i class="fa fa-calendar pr-10"></i>'
												+ item.dealYear
												+ '.'
												+ item.dealMonth
												+ '.' + item.dealDay + '</p><hr>'
										$(html).appendTo(contents)
									})
					
					console.log('title:', this.title);
					console.log('lable:', this.label);
					map.setZoom(16);
					map.panTo(this.getPosition());
				});
			}
		}
	}
	
	//보이기
	 function div_show() {
	  document.getElementById("corona_s").style.display = "block";
	 }
	 
	 //숨기기
	 function div_hide() {
	  document.getElementById("corona_s").style.display = "none";
	 
	}
	function div_show2() {
		document.getElementById("hospital_s").style.display = "block";
	}

	//숨기기
	function div_hide2() {
		document.getElementById("hospital_s").style.display = "none";
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
				timeout : 60000,
				enabledHighAccuracy : true
			};
			navigator.geolocation.getCurrentPosition(showLocation,
					errorHandler, options);
		} else {
			alert("Sorry, browser does not support geolocation!");
		}
	}

	function moveMap(Lat, Lon, Zoomlevel) {
		map.setZoom(15);
		setTimeout(function() {
			var moveLatLon = new google.maps.LatLng(Lat, Lon);
			map.panTo(moveLatLon);
			map.setZoom(Zoomlevel);
		}, 1000);
	};

	$(function() {
		///////////////////////////////시, 도 선택 이벤트 처리 
		$('#city').change(function() {
			var city = $(this).val();
			console.log('city 선택', city)

			location.href = '<%=root%>/KMain?act=gungu&city2=' + city;
		})

		///////////////////////////////구, 군 선택 이벤트 처리 
		$('#gu').change(function() {
			var gu = $(this).val();
			console.log('gu 선택', gu)

			location.href = '<%=root%>/KMain?act=dong&gu2=' + gu;
		})

		///////////////////////////////동 선택 이벤트 처리
		$('#dong').change(function() {
			<%session.removeAttribute("hospital");%>
			var dong = $(this).val()
			location.href = '<%=root%>/KMain?act=trade&city=<%=ct%>&dongcode=<%=gg%>&dong='+dong;
			//$('#frm').submit();
		})
	})
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

	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	
	<div class="bg-dark section pt-4 pb-5">
		<div class="container-fluid ">
			<!-- filters start -->
			<div
				class="sorting-filters text-center mb-20 d-flex justify-content-center">
				<form class="form-inline" id="frm">
				<input type="hidden" name="act" value="trade">
					<!-- <input type="hidden" id="code"  name="code" value="code"/> -->
					<div class="form-group md-2">
						<select class="form-control bgPink" name="city" id="city">
							<option value="all">${ct}</option>
							<option value="서울특별시">서울특별시</option>
							<option value="경기도">경기도</option>
							<option value="인천광역시">인천광역시</option>
							<option value="부산광역시">부산광역시</option>
							<option value="대전광역시">대전광역시</option>
							<option value="대구광역시">대구광역시</option>
							<option value="울산광역시">울산광역시</option>
							<option value="세종특별자치시">세종시</option>
							<option value="광주광역시">광주광역시</option>
							<option value="강원도">강원도</option>
							<option value="충청북도">충청북도</option>
							<option value="경상북도">경상북도</option>
							<option value="경상남도">경상남도</option>
							<option value="전라북도">전라북도</option>
							<option value="전라남도">전라남도</option>
							<option value="제주특별자치도">제주도</option>
						</select>
					</div>
					<div class="form-group ml-2">
						<select class="form-control" name="dongcode" id="gu">
							<%
								ArrayList<String> guu = (ArrayList) session.getAttribute("gu");
							%>
							<option value=<%=gg%> >${gungu }</option>
							<%
								if (guu != null) {
														for (String g : guu) {
							%>
							<option value=<%=g%>><%=g%></option>
							<%
								}
													}
							%>
						</select>
					</div>
					<div class="form-group ml-2">
						<select class="form-control" name="dong" id="dong">
							<%
								ArrayList<String> dong = (ArrayList) session.getAttribute("dong");
													String dd = (String) session.getAttribute("dd");
							%>
								<option value="all">${dd }</option>
							<%
								if (dong != null) {
														for (String d : dong) {
							%>
							
							<option value=<%=d%>><%=d%></option>
							<%
								}
													}
							%>
						</select>
					</div>
				</form>
			</div>
			<!-- filters end -->
		</div>
	</div>
	<div class="container">
		<div class="row mt-5 mb-5">
			<div class="col-sm-4">
				<h2>거래 내역</h2>			
				<hr>
				  <c:if test="${tlist.size() == 0}">
				  <h4>거래내역이 없습니다</h4>
				  </c:if>
				  <c:if test="${tlist.size() != 0}">
	  			<c:forEach var="t" items="${tlist}">
	  			<div class="media-body">
					</div>
						<h4>
							<a href='#'>${t.aptname}</a>
						</h4>

						<h6 class="media-heading" id='deal'>
							거래금액 :	${t.dealAmount}만원
						</h6>
						<h6 class="media-heading" id='deal'>
							면적:	${t.area}</h6>
						<div>
							<p class="small margin-clear">
							<div class="fa fa-calendar pr-10"></div>
							${t.dealYear}.${t.dealMonth}.${t.dealDay}
							</p>
						</div>
						<hr>
	  		</c:forEach>

	  		</c:if>
			</div>
			
			
			<div class="col-sm-8" style="margin: 0 auto;">

				<!-- Google Map start -->
				<div class="main col-lg-8 order-lg-2 ml-xl-auto">

					<!-- page-title start -->
					<!-- ================ -->
					<div class="row grid-space-10">
						<div class='col-12 justify-content-center' id="map"
							style="width: 600px; height: 400px"></div>
					</div>
				</div>
				<br><input type="button" value="코로나 선별 진료소" id="cor" onclick = "div_show()"/>
				<input type="button" value="안심병원 검색" id="hos" onclick = "div_show2()"/>
				<div id="corona_s" style="display: none">
					<h2>코로나 선별 진료소</h2>===================================
					<%
						if(co != null)
									{
								for(CoronaDto corona : co){
					%>
						<br>
						<div><%=corona.getCname()%><br> <%=corona.getAddress()%><br>평일: <%=corona.getOtime()%><br>토요일: <%=corona.getOtime()%><br>일/공휴일: <%=corona.getOtime()%><br>번호: <%=corona.getPhone()%></div>
						===================================
					<%
 	} }
 				else{
 %>
						<br>근처 코로나 선별소가 없습니다.
					<%
							}
						%>
					<br>
					<input type="button" value="숨기기" id="cor_h" onclick = "div_hide()"/>
				</div>
				<div id="hospital_s" style="display: none">
					<h2>동네 안심병원</h2>===================================
					<%
						if(hos != null)
									{
									for(HospitalDto h : hos){
					%>
						<br>
						<div><%=h.getHname() %><br><%=h.getAddress() %><br><%=h.getType() %><br><%=h.getPhone() %><br> </div>
						===================================
					<%		}
						}else{
					%>
						<br>근처 안심 병원이 없습니다!
					<% 	
						}
					%>
					<br>
					<input type="button" value="숨기기" id="cor_h" onclick = "div_hide2()"/>
				</div>
			</div>
		</div>

	</div>
	<!-- 중앙 center contents start -->

 	<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>