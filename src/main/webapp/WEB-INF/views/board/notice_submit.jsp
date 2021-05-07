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
        <style>
            .banner.dark-translucent-bg {
            min-height: 250px;
            padding-top: 60px;
            padding-bottom: 20px;
            }
        </style>
        <script type="text/javascript">
			 $(document).ready(function () {
            
            	$("#bnt-btn-post").on('click',function () {
                document.getElementById("btn-post").submit();
            });


        });
        </script>
      </head>
      <body>
             
         <%@ include file="/WEB-INF/views/include/header.jsp" %>

       
        <div class="container" >
          <!-- 중앙 메인 컨테이너-->
          <div class="row justify-content-center" >
            
              <div class="col-8 mt-5 mb-5 " style="background-color: gainsboro; border-radius: 10px;">
                
                  <div class="font-weight-bold mb-3 mt-3">공지 사항 등록</div>
                  
                    <form method="post" action="<%=root%>/notice" class="form-horizontal was-validated">
    					<input type="hidden" name="act" value="post_notice"> 
    					                
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="title">제목<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="제목 입력" id="title" name="title" required>
                          </div>
                        </div>
                        
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="content">내용<span class="text-danger small" >*</span></label>
                          <div class="col-sm-8">
                          <textarea class="form-control" rows="5" id="content" name="content" required></textarea>
                          </div>
                        </div>
                        
                        <div class="form-group row">
                          <div class="ml-md-auto col-md-9">
                            <button id="btn-post" class="btn btn-group btn-warning btn-animated">
                            	등록
                            </button>
                          </div>
                        </div>

                  	</form>
                      
              </div>
            </div>
        </div>

	  <%@ include file="/WEB-INF/views/include/footer.jsp" %>

    </body>
</html>