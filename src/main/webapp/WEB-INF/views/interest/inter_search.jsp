<%@page import="com.ssafy.happyhouse.model.dto.MartDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String root = request.getContextPath();
	List<MartDto> tlist = (List) session.getAttribute("mart");
	List<String> mlist = (List) session.getAttribute("martlist");
	String sel = null;
	sel = (String) request.getAttribute("sel");
	if(sel==null) sel ="음식";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>행복한 우리집</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
			String a = tlist.get(i).getDoro();
			double b = Double.parseDouble(tlist.get(i).getLat());
			double c = Double.parseDouble(tlist.get(i).getLng());
			String d = tlist.get(i).getMartname();
			String e = tlist.get(i).getBictitle();
			String msg = "imgs/house.JPG";%>
	locations.push(['<%=a%>',<%=b%>,<%=c%>,'<%=d%>','<%=msg%>','<%=e%>']);
	<%}
		}%>

	var map = null;
	var latitude;
	var longitude;

	///////////////////////////////////////////////////맵 설정

	function initMap() {
		<%List<MartDto> tlist2 = (List) session.getAttribute("mart");
		double b=0;
		double c=0;
			if (tlist2 == null) {
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
			zoom : 17, //0~ 21  큰 값일 수록  zooming
		};
		//google.maps.Map(map을 그릴 영역, 옵션정보 );
		map = new google.maps.Map(document.getElementById("map"), opt);
		console.log(21312312312312);
		var infowindow = new google.maps.InfoWindow();

		var marker, i;
		for (i = 0; i < locations.length; i++) {
			
			if("<%=sel%>" != locations[i][5]) continue;
			
			console.log(locations[i][3])
			marker = new google.maps.Marker({
				id : i,
				title : locations[i][3],
				label : "",
				icon : locations[i][4],
				position : new google.maps.LatLng(locations[i][1],
						locations[i][2]),
				map : map
			});

			google.maps.event.addListener(marker, 'click',(function(marker, i) {
						return function() {
							infowindow.setContent("<center>[" + locations[i][3]
									+ "]<br>" + locations[i][0]
									+ "</center>");
							infowindow.open(map, marker);
						}
					})(marker, i));
			if (marker) {
				marker.addListener('click', function() {
					var type = [ '', '아파트 매매', '아파트 전월세', '다세대 매매', '다세대 전월세' ];
					console.log('결과 도착')
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
	
	function processSearchAptResult(jsondata, status, xhr) {
		
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
			
			location.href = '<%=root%>/ayj?act=citychange&city=' + city;
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
    
         <%@ include file="/WEB-INF/views/include/header.jsp" %>
        
        <div class="bg-dark section pt-4 pb-5">
            <div class="container-fluid ">
                  <!-- filters start -->
             <div class="sorting-filters text-center mb-20 d-flex justify-content-center">
             <form class="form-inline" id="frm" >
             
                      <!-- <input type="hidden" id="code"  name="code" value="code"/> -->
                      <div class="form-group md">
                        <select class="form-control bgPink" name="city" id="city">
                          <option value="all">상가 종류</option>
                          <option value="음식">음식</option>
                          <option value="학문/교육">학문/교육</option>
                          <option value="소매">소매</option>
                          <option value="생활서비스">생활서비스</option>
                          <option value="부동산">부동산</option>
                          <option value="관광/여가/오락">관광/여가/오락</option>
                          <option value="숙박">숙박</option>
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

				<button type="button" class="btn btn-success mb-5" onClick="location.href='inter_air.jsp'">대기 오염 정보 확인하러 가기</button>
				
				<%
									if (mlist == null) {
								%>
				<h4>관심지역이 없습니다</h4>
				<%
					} else {
								for (int i = 0; i < mlist.size(); i++) {
				%>
				<div class="media-body"></div>
				<a class="mr-5"
					href="<%=root%>/ayj?act=inter_search&dong=<%=mlist.get(i)%>">
					관심지역 -> <%=mlist.get(i)%>
				</a> <a
					href="<%=root%>/ayj?act=inter_delete&dong=<%=mlist.get(i)%>">
					삭제하기 </a>
				<hr>
				<%
					}
							}
				%>



				<h2>주변 상가 정보</h2>
            <br>
            <div>
				<%
					if(tlist==null || tlist.size() == 0){
				%>
					<h4>주변 상가가 없습니다</h4>
				<%
					}else{
						for (MartDto h : tlist) {
				%>
				<%if(sel.equals(h.getBictitle())){%>
				<div class="media-body">
				</div>
					<h6 class="media-heading" id='deal'>
						가게 이름  : <%=h.getMartname()%>
					</h6>
					<h6 class="media-heading" id='deal'>
						주소 : <%=h.getDoro()%></h6>
					
					<br>
					<hr>
					<br>
					<%} %>
				<%}
				}
				%>
			</div>
            
            
          
          </div>
          <div class="col-sm-8" style="margin:0 auto; " >

            <!-- Google Map start -->
          <div class="main col-lg-8 order-lg-2 ml-xl-auto">

        <!-- page-title start -->
        <!-- ================ -->
                                                  <div class="row grid-space-10">
                            <div  class='col-12 justify-content-center' id="map" style="width: 600px; height: 400px"></div>
        </div> 
    </div>
  </div>
  </div>

  </div>
        <!-- 중앙 center contents start -->
        
      <%@ include file="/WEB-INF/views/include/footer.jsp" %>>

    </body>
</html>