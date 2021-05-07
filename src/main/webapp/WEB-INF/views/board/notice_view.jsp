<%@page import="com.ssafy.happyhouse.model.dto.NoticeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	NoticeDto notice = (NoticeDto)request.getAttribute("notice");
%> 
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
                  <div class="font-weight-bold mb-3 mt-3">공지 확인</div>
                  		<c:if test="${not empty notice}">
	                        <div class="row">
	                          <label class="col-sm-3 text-md-right">제목</label>
	                          <div class="col-sm-8">
	                            <label>${notice.title}</label>
	                          </div>
	                        </div>
	                        <div class="row">
	                          <label class="col-sm-3 text-md-right">내용</label>
	                          <div class="col-sm-8">
	                            <label>${notice.content}</label>
	                          </div>
	                        </div>
                       </c:if>
                       <br>
              </div>
            </div>
            <div class="text-center mt-1">
              <button onclick="location.href='${root}/notice?act=mv_noticeupdate&nid=${notice.id}'" type="button" class="btn btn-dark">수정</button>
              <button onclick="location.href='${root}/notice?act=delete_notice&nid=${notice.id}'" type="button" class="btn btn-dark">삭제</button>

            </div>
        </div>
       
		<%@ include file="/WEB-INF/views/include/footer.jsp" %>>

    </body>
</html>