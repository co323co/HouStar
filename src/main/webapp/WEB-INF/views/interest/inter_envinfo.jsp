<%@page import="com.ssafy.happyhouse.model.service.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ssafy.happyhouse.model.mapper.*"%>
<%@page import="com.ssafy.happyhouse.model.dto.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%
	String root = request.getContextPath();

%>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Happy House | 환경오염정보 </title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
	
		</script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsaVCFsmQlNdAaSqXeyE4J5kONYk145OI&callback=initMap" async defer></script>
							<script type="text/javascript">
								//https://console.developers.google.com    key받기 위한 site

								 var locations =[];
			

								var map = null;
								var latitude;
								var longitude;

							
///////////////////////////////////////////////////맵 설정
								function initMap() {
									//getLocation();
									var opt = {
										// google map에 중앙으로 표시할 좌표 설정
										center : {lat:37.6522545,lng: 127.0304855},
										zoom : 15,  //0~ 21  큰 값일 수록  zooming
									};
									//google.maps.Map(map을 그릴 영역, 옵션정보 );
									map = new google.maps.Map(document.getElementById("map"), opt);
									
									var infowindow = new google.maps.InfoWindow();
									
									/*
					    var marker, i;
					    for (i = 0; i < locations.length; i++) {  
					      marker = new google.maps.Marker({
					        id:i,
					        title : locations[i][3],
					        label : locations[i][0],
					        icon	:		locations[i][4],
					        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
					        map: map
					      });

					      google.maps.event.addListener(marker, 'click', (function(marker, i) {
					        return function() {
					          infowindow.setContent("<center>["+locations[i][3]+"]<br>거래가 : "+locations[i][0]+"만원</center>");
					          infowindow.open(map, marker);
					        }
					      })(marker, i));
					      if(marker)
					      {
					        marker.addListener('click', function() {
					        	 
					        	$.ajax({
																			 url: 'searchAllHouseDeal.do'
																			,data:{
																					dongcode : '11320',
																					aptname:this.title,
																			}
																			,dataType:'json'
																			,success : processSearchAptResult
																			,error   : function(err) {
																				console.log(err);
																			}
																		}) 
															console.log('title:',this.title);
															console.log('lable:',this.label);
					          map.setZoom(16);
					          map.panTo(this.getPosition());
					        });
					       }
					    }

						*/
								}
					
				function processSearchAptResult(jsondata, status, xhr){
					var type=['','아파트 매매','아파트 전월세','다세대 매매','다세대 전월세'];
					console.log('결과 도착')
					var list = jsondata;
					console.log(list)
					var contents = $('#houseInfo');
					contents.empty();				//기존에 내용이 있으면 비우기
						$('<h3 class="title">거래 정보</h3><div class="separator-2"></div><h5>'+list[0].aptName+'</h5><hr>').appendTo(contents);
						$.each(list, function(index, item) {
							var html='<div class="media margin-clear"><div class="media-body">'
							html+='<h6 class="media-heading">거래금액 :'+item.dealAmount+'</h6>'
							html+='<h6 class="media-heading">전용면적: '+item.area+'</h6>'
							html+='<h6 class="media-heading">거래구분 :'+type[item.type]+'</h6>'
							html+='<p class="small margin-clear"><i class="fa fa-calendar pr-10"></i>'+item.dealYear+'.'+item.dealMonth+'.'+item.dealDay+'</p><hr>'
							$(html).appendTo(contents)
						})
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
			}else if(err.code == 3){
				alert("Erro : Time out");
			}
		}
		function getLocation() {
			if (navigator.geolocation) {
				var options = {
					timeout : 60000,
					enabledHighAccuracy :true
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
			var moveLatLon = new google.maps.LatLng(Lat,Lon);
			map.panTo(moveLatLon);
			map.setZoom(Zoomlevel);
			}, 1000);
		};
		
								</script> 	
    	
	<%
	
		String boxidx = (String)request.getAttribute("boxidx");

		//BcodeService bserv = new BcodeServicelmpl(); //동코드 -> 이름 바꿔줄 서비스
		String userId = (String)(request.getSession().getAttribute("id")); //로그인중인 usrId 불러옴
		//List<String> favDongCodeList = (new InterestAreaDAOlmpl()).findArea(userId); ///해당 유저의 관심지역코드list 불러옴
		
	%>
	
    	<script type="text/javascript">
    		
    		$(document).ready(function () {
    			
// 		    	select버튼 이벤트(submit)
// 				$("#dongSelectBox").change(function (e) { 
					
// 					console.log("누름");
// 					var index = $("#dongSelectBox option").index($("#dongSelectBox option:selected"));
// 					var codeList = <%= favDongCodeList %>; 
// 					var code = codeList[index-1]; //0번째 인덱스는 [선택해주세요]가 해당됨. 그래서 하나씩 앞으로 가야함
// 					$("#code").val(code);
// 					$.ajax({
// 						type: "post",
// 						url: "envguidecheck",
// 						data:  {
// 							act : "selectFavDong",
// 							code : code,
// 						},
// 						dataType: "json",
// 						success: sendEnvList,
// 						error : function (error) {
// 							console.log(error);
// 						}
// 					});
					
// 				});
    			
				function sendEnvList(data, status, xhr)	 {
							console.log(data);
							if(data.length==0 || data==null) {
								$("#infos").html(`<div class="p-3 mb-2" style="border-radius: 5px">해당 동에 환경 정보가 없습니다!</div>`);
							} else{
								var list_html="";
								//JSONArray를 파싱해서 객체리스트로 넘겨줌, (맵같은 갬성)
								$.each(data, function (indexInArray, dto) { 
									// ` 를 백틱이라고 함
									list_html +=  `
											<div class="p-3 mb-2" style="border: 1px solid black; border-radius: 5px">
													<div class="mt-1">
														<span>업체명 : </span> 
														<span>` + dto.name + `</span>
													</div>
					
													<div class="mt-1">
														<span>업종명 : </span>  
														<span>` + dto.type + ` </span>
													</div>
					
													<div class="mt-1">
														<span>주소지 : </span>
														<span>` + dto.address + `</span>
													</div>
											</div>													
										`;
								});
								
								$("#infos").html(list_html);
							}
						};
				
			});
    	</script>
    </head>
    <body>


	<%@ include file="/WEB-INF/views/include/header.jsp"%>

	<div class="bg-dark section pt-4 pb-5">
		<div class="container-fluid ">
			<br>
		</div>
	</div>

	<div class="container">
		<div class="row mt-5 mb-5">
			<div class="col-sm-4">
				<h2>환경오염 정보</h2>
				<form id="selectform" method="post" action="<%=croot%>/envguidecheck">
					<input type="hidden" name="act" value="selectFavDong">
					<input type="hidden" name="code" id="code" value="">
					<input type="hidden" name="boxidx" id="boxidx" value="">
					<div class="form-group mt-2">
						<label for="dongSelectBox">🎈나의 관심지역🎈</label> 
						<select
							class="form-control" id="dongSelectBox" name="dongSelectBox">
							<option selected>선택해주세요</option>
<%-- 							 <% --%>
// 								for(String dong : bserv.findDongNames(favDongCodeList)) {
// 									out.println("<option>"+dong+"</option>");
// 								}
<%-- 							%>  --%>
						</select>
					</div>
					<div class="form-check-inline">
						<input name="check_green" id="check_green" type="checkbox" class="form-check-input" value="">
						<label for="check_green" class="form-check-label">녹지</label>
					</div>
					<div class="form-check-inline">
						<input name="check_wastewater" id="check_wastewater" type="checkbox" class="form-check-input" value="">
						<label for="check_wastewater" class="form-check-label">폐수 배출</label>
					</div>
					<div class="form-check-inline">
						<input name="check_airemissions" id="check_airemissions" type="checkbox" class="form-check-input" value="">
						<label for="check_airemissions" class="form-check-label">대기 배출</label>
					</div>
				</form>
				<br>
				<div id="infos" class="p-2">

				</div>
				
		
			</div>
			<!-- 구글맵 -->
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
			</div>
		</div>

	</div>
	<!-- 중앙 center contents start -->
        
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>>

    </body>
</html>