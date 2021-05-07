<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String root = request.getContextPath();

%>     
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Happy House | 로그인</title>
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
      </head>
      <body>
      
        <%@ include file="/WEB-INF/views/include/header.jsp" %>
          
        <div class="container" >
          <!-- 중앙 메인 컨테이너-->
          <div class="row justify-content-center" >
            
              <div class="col-8 mt-5 mb-5 " style="background-color: gainsboro; border-radius: 10px;">
                
                  <div class="font-weight-bold mb-3 mt-3 pl-2 pb-2">
               	    <span class="mr-3">Login</span><span class="badge badge-pill badge-danger">ID, PASSWORD를 확인해주세요</span>
                  </div>
                 
                  <br>
                    <form class="form-horizontal" method="post" action="<%=root%>/ayj">
                    
                   		<input type="hidden" name="act" value="login">
                    
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="userid">아이디<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input name ="userid" type="text" class="form-control" placeholder="" id="userid">
                          </div>
                        </div>
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="userpwd">비밀번호<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input name ="userpwd" type="text" class="form-control" placeholder="영문 숫자 포함 6자리 이상" id="userpwd">
                          </div>
                        </div>
                        <div class="form-group row text-right mr-5">
                          <div class="ml-md-auto col-md-9">
                            <button type="submit" class="btn btn-group btn-warning btn-animated">로그인</button>
                            <button type="button" class="btn btn-group btn-info btn-animated" onClick="location.href='passfind.jsp'">비밀번호 찾기</button>
                          </div>
                        </div>
                      </form>
                      <br>
              </div>
            </div>
        </div>

 	 <%@ include file="/WEB-INF/views/include/footer.jsp" %>>          


    </body>
</html>