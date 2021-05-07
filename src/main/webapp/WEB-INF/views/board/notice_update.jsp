<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>행복한 우리집</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script type="text/javascript">
          $(document).ready(function () {
              $("#btn_modify").click(function (e) { 
                let id = '${notice.id}';
                let title = $("#title").val();
                let content = $("#content").val();
                let views =  '${notice.views}';
                let userId =  '${notice.userId}';
                    
                let noticeInfo = JSON.stringify(
                  {
                     id:id,
                     title:title,
                     content:content,
                     views:views,
                     userId:userId 
                  }
                );

                console.log(noticeInfo);

                $.ajax({
                  type: "PUT",
                  url: "${root}/notice",
                  data: noticeInfo,
                  dataType: "json",
                  contentType: 'application/json',
                  success: function (map) {
                    location.href='${croot}/'+map.url;
                  },  
                  error: function (xhr, status, msg) {
                    console.log("상태값 : " + status + " / Http 에러메시지 : " + msg);
                  }
                });

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
            
              <div class="col-8 mt-5 mb-5 " style="background-color: gainsboro; border-radius: 10px;">
                
                  <div class="font-weight-bold mb-3 mt-3">공지 내용 수정</div>
                    <form class="form-horizontal">
    					<input type="hidden" name="nid" value=<%=request.getParameter("nid")%>> 
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="title">제목<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="제목" id="title" name="title" value=${notice.title}>
                          </div>
                        </div>
                        <div class="form-group row">
                          <label class="col-sm-3 text-md-right" for="content">내용<span class="text-danger small">*</span></label>
                          <div class="col-sm-8">
                             <textarea class="form-control" rows="5" id="content" name="content">${notice.content}</textarea>
                          </div>
                        </div>
                       
                        <div class="form-group row">
                          <div class="ml-md-auto col-md-9">
                            <button id="btn_modify" type="button" class="btn btn-group btn-warning btn-animated">
                		              수정하기 <i class="fa fa-check"></i>
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