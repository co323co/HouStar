<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String root = request.getContextPath();

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
        <style>
            .banner.dark-translucent-bg {
            min-height: 250px;
            padding-top: 60px;
            padding-bottom: 20px;
            }
        </style>
        
        <script>
        
    ///////////////////////////////////////////////////event 설정 및 초기 설정 
    $(function() {
		///////////////////////////////시, 도 선택 이벤트 처리 
		$('#city').change(function() {
			var city = $(this).val();
			console.log('city 선택', city)
			location.href = '<%=root%>/ayj?act=gungu&city=' + city;
		})

		///////////////////////////////구, 군 선택 이벤트 처리 d
		$('#gu').change(function() {
			var gu = $(this).val();
			console.log('gu 선택', gu)

			location.href = '<%=root%>/ayj?act=dong&gu=' + gu;
		})

		///////////////////////////////동 선택 이벤트 처리
		$('#dong').change(function() {
			$('#frm').submit();
		})
	})

  </script>
        
        
        
      </head>
      <body>
         
         <%@ include file="/WEB-INF/views/include/header.jsp" %>
       
       
       <!-- 이제 등록할건데 형꺼 값 가져오는거 써서 저장하고 등록버튼 누르면 세션으로 넘겨준다. 이후에 탐방가면 초기값을 세션에서 가져온걸로 보여줄거 -->
       
        <div class="container" >
          <!-- 중앙 메인 컨테이너-->
          <div class="row justify-content-center" >
            
              <div class="col-8 mt-5 mb-5 " style="background-color: gainsboro; border-radius: 10px;">
                
                  <div class="font-weight-bold mb-3 mt-3">관심 지역</div>
                    <form class="form-horizontal" method="post" action="<%=root%>/ayj">
                    <input type="hidden" name="act" value="inter_submit">
                    
                    
                    
                    
                        <!-- <input type="hidden" id="code"  name="code" value="code"/> -->
			          <div class="form-group md">
			            <select class="form-control bgPink" name="city" id="city">
			            <%
			           	String ct = (String) session.getAttribute("ct");
					  	%>
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
			          <div class="form-group md-1">
						<select class="form-control" name="dongcode" id="gu">
							<%
								ArrayList<String> guu = (ArrayList) session.getAttribute("gu");
								String gg = (String) session.getAttribute("gungu");
							%>
							<option value="all">${gungu }</option>
							<%
								if(guu !=null){
								for (String g : guu) {
							%>
							<option value=<%=g%>><%=g%></option>
							<%
								}
								}
							%>
						</select>
						</div>
						<div class="form-group md-1">
							<select class="form-control" name="dong" id="dong">
								<%
									ArrayList<String> dong = (ArrayList) session.getAttribute("dong");
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
						<div class="ml-md-auto col-md-9">
		                <button type="submit" class="btn btn-group btn-warning btn-animated"> 등록 <i class="fa fa-check"></i>
		                </button>
             	 	</div>
             	 </form>
              </div>
            </div>
        </div>
          
	  <%@ include file="/WEB-INF/views/include/footer.jsp" %>>
    </body>
</html>