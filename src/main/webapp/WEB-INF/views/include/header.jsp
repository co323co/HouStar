<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String croot = request.getContextPath();
%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>HEADER</title>
	
	 <!-- 로그인/회원가입 탑 -->
    <style>
      .nav-item{
        font-size: small;
    }

    dropdown-toggle{
      background-color: rgb(75, 73, 77);
          padding : 2px 10px;
          border-radius: 10%;
    }
      a div{
          background-color: rgb(75, 73, 77);
          padding : 2px 10px;
          border-radius: 10%;
      }
      
    </style>
   	<!-- 로그인 이벤트 처리 -->
    <script type="text/javascript">
        $(document).ready(function () {
            
            $("#bnt-login").on('click',function () {
               
                
                document.getElementById("bnt-login").submit();
                
            });


        });
        </script>
</head>
<body>
	<header>
	 <!-- 회원가입, 로그인 네비게이션-->
	    <div>
	        <nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="height: 35px;">
	            <div class="container justify-content-sm-end">
	                <ul class="navbar-nav">
	                  <li class="nav-item">
	                    
	                    <c:choose>
	                    	<c:when test="${empty userId }">
	                    	 <li class="nav-item">
        						<a id="enter" class="nav-link" href="<%=croot%>/insertform" style="color: white;">Sign Up</a>
       						</li>
	                    	<li class="nav-item">
		                    <div class="dropdown">
		                      <button id="login" type="button" class="btn dropdown-toggle" data-toggle="dropdown" style="color: white; height: 80%; font-size:small;">
		                          Login
		                      </button>
		                      <div class="dropdown-menu bg-dark p-3" style="color: white; font-size: small;">
			                      <form method="post" action="${croot }/user">
				                      <input type="hidden" name="act"value="login">
				                          <div class="form-group" style="width : 150px;">
				                              <label for="usr">아 이 디</label>
				                              <input name = "userId"type="text" class="form-control mb-2" id="userId">
				                              <label for="pwd">비밀번호</label>
				                              <input name = "userPwd" type="password" class="form-control" id="userPwd">
				                          </div>
				                          <button id="bnt-login" class="btn btn-light mb-2" style="font-size: small; padding: 2px 10px; display: block;">로 그 인</button>
				                          <button type="button" onClick="location.href='passfind'" class="btn btn-dark" style="font-size: small; padding: 2px 10px; background-color: rgb(73, 73, 90);">비밀번호 찾기</button>
				                  </form>
		                      </div>
		                   </div>
		                  </li>
	                    	</c:when>
	                    	<c:otherwise>
	                    	<li class="nav-item">
				         		
					        </li>
	                    	 <li class="nav-item">
				         		<a id="out" class="nav-link" href='${croot }/user' style= "color: white;">Logout</a>
					        </li>
					        <li class="nav-item">
					         	<a id="member" class="nav-link" href="${croot }/user/userInfo" style= "color: white;">User Info</a>
					        </li>
	                    	</c:otherwise>
	                    </c:choose>   
	                </ul>
	            </div>
	          </nav>
	    </div>
	      
	      
	      
	    <!-- 상단 네비게이션-->
	    <div>
	        <nav class="navbar navbar-expand-md bg-light navbar-light">
	            <div class="container">
	                <a class="navbar-brand" href="/">
	                    <img src="/img/header_logo.jpg" alt="Logo" style="width:120px;">
	                </a>
	            
	                <!-- Toggler/collapsibe Button -->
	                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	                  <span class="navbar-toggler-icon"></span>
	                </button>
	
	                <!-- Navbar links -->
	                <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
	                
	  
	                
	                  <ul class="navbar-nav">
	                  
	                   <c:choose>
	                    	<c:when test="${empty id }">
	                    	  <li class="nav-item">
		                      <a class="nav-link" href="<%=request.getContextPath()%>/notice">공지사항</a>
		                    </li>
		                    <li class="nav-item">
		                      <a class="nav-link" href="news">오늘의 뉴스</a>
		                    </li>
		                    <li class="nav-item">
					          <a class="nav-link" href="./around_search">주변 탐방</a>
					        </li>
				        
	                    	</c:when>
	                    	<c:otherwise>
	                    	<li class="nav-item">
		                      <a class="nav-link" href="<%=request.getContextPath()%>/notice">공지사항</a>
		                    </li>
		                    <li class="nav-item">
		                      <a class="nav-link" href="news">오늘의 뉴스</a>
		                    </li>
		                    <li class="nav-item">
					          <a class="nav-link" href="./around_search">주변 탐방</a>
					        </li>
					        
					        <li class="nav-item">
					          <a id="inter_submit" class="nav-link" href="<%=croot%>/inner_submit_form">관심 지역 등록</a>
					        </li>
					        <li class="nav-item ">
					          <a id="inter_search" class="nav-link"  href="<%=croot%>/inter_search">관심 지역 탐방</a>
					        </li>
	                    	</c:otherwise>
	                    </c:choose> 
	                  
	                  
	                    
				        
	                  </ul>
	                </div>
	            </div>
	          </nav>
	    </div>
	</header>
</body>
</html>
