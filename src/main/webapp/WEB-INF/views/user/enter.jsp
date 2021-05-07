<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String root = request.getContextPath();

%>     
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Happy House | 회원가입</title>
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
                
                  <div class="font-weight-bold mb-3 mt-3 pl-2 pb-2">Join Member</div>
                    <form class="form-horizontal was-validated" method="post" action="<%=root%>/user/register">
                    
                  <input type="hidden" name="act" value="insertmember">
                    
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="userId">아이디<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input name ="userId" type="text" class="form-control" placeholder="" id="userId" required minlength="2">
                          </div>
                        </div>
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="userPwd">비밀번호<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input name ="userPwd" type="text" class="form-control" placeholder="영문 숫자 포함 6자리 이상" id="userPwd" required minlength="2">
                          </div>
                        </div>
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="userName">이름<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input name ="userName" type="text" class="form-control" placeholder="User Name" id="userName" required minlength="2">
                          </div>
                        </div>
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="userAddress">주소<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input name ="userAddress" type="text" class="form-control" placeholder="address" id="userAddress" required>
                          </div>
                        </div>
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="userPhone">전화번호<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input name ="userPhone" type="text" class="form-control" placeholder="010-xxxx-xxxx" id="userPhone" required minlength="12">
                          </div>
                        </div>
                        <div class="form-group row text-right mr-5">
                          <div class="ml-md-auto col-md-9">
                            <button type="submit" class="btn btn-group btn-warning btn-animated">등록 <i class="fa fa-check"></i>
                            </button>
                          </div>
                        </div>
                    </form>
              </div>
            </div>
        </div>

 	 <%@ include file="/WEB-INF/views/include/footer.jsp" %>>          


    </body>
</html>