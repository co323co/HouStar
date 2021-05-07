<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
      <script>
        $(document).ready(function () {
          
            $("#delete").on('click',function () {
                alert("회원 탈퇴 되었습니다.");
                location.href = "index.jsp"
              });

        });
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
       
        <div class="container" >
          <!-- 중앙 메인 컨테이너-->
          <div class="row justify-content-center" >
            
              <div class="col-sm-8 mt-5 mb-1 " style="background-color: gainsboro; border-radius: 10px;">
                
                  <div class="font-weight-bold mb-3 mt-3 pl-2 pb-2">회원 정보 확인</div>
                        <div class="row">
                          <label class="col-sm-3 text-md-right" for="Id">아이디<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <label >${members.userId }</label>
                          </div>
                        </div>
                        <div class="row">
                          <label class="col-sm-3 text-md-right" for="Id">비밀번호<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <label >${members.userPwd }</label>
                          </div>
                        </div>
                        <div class="row">
                          <label class="col-sm-3 text-md-right" for="Id">이름<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <label >${members.userName }</label>
                          </div>
                        </div>
                        <div class="row">
                          <label class="col-sm-3 text-md-right" for="Id">주소<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <label >${members.userAddress }</label>
                          </div>
                        </div>
                        <div class="row">
                          <label class="col-sm-3 text-md-right" for="Id">전화번호<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <label >${members.userPhone }</label>
                          </div>
                        </div>
                        <br>
			            <div class="text-right pr-3 pb-3">
			              <button onClick="location.href='<%=root%>/ayj?act=updateform'" type="button" class="btn btn-dark" >수정</button>
			              <!-- 수정하면 새로운 페이지에서 수정-->
			              <button onClick="location.href='<%=root%>/ayj?act=deletemember'" id = "delete" type="button" class="btn btn-dark" >삭제(탈퇴)</button>
			              <!-- 삭제하면 알림창 뛰워주고 메인으로 가게만들기-->
						<br>
         			   </div>
              </div>
            
            </div>
        </div>
       
        <%@ include file="/WEB-INF/views/include/footer.jsp" %>>
          
    </body>
</html>