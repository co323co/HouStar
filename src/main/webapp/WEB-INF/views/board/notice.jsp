<%@page import="com.ssafy.happyhouse.model.mapper.NoticeDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 	List<NoticeDao> noticeList = (List)request.getAttribute("notices");
  	String root = request.getContextPath();
 %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Happy House | 공지사항</title>
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
       <br>
       <div class="container" >
          <!-- 중앙 메인 컨테이너-->
          <div class="row justify-content-center" > 
            <div class="col-sm-11">
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th></th>
                  </tr>
                </thead>
        
       			<c:if test="${not empty noticeList}">
					<c:forEach var="notice" items="${noticeList}">
		                <tbody>
		                  <tr>
		                    <td>${notice.id}</td>
		                    <td>${notice.title}</td>
		                    <td>${notice.views}</td>
		                    <td><button onClick="location.href='<%=root%>/notice?act=mvnoticeview&nid=${notice.id}'" type="button" class="btn btn-secondary ">조회</button></td>
		                  </tr>
		                </tbody>
					</c:forEach>
				</c:if>
              </table>
              
              <form method="post" action="<%=root%>/notice" class="form-inline">
              	<input type="hidden" name="act" value="mvnotice">
                <input type="text" name="search_value" class="form-control mb-2 mr-sm-2" placeholder="제목으로 검색하기">
                <button type="submit" class="btn btn-primary mb-2">검색</button>
                <button onClick="location.href='notice_submit.jsp'" type="button" class="btn btn-primary mb-2 ml-3">공지 등록</button>
              </form>
            
            </div>      
          </div>
        </div>
       
       <%@ include file="/WEB-INF/views/include/footer.jsp" %>>
    </body>
</html>