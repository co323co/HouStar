<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Happy House | 사이트맵</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
	  .col div{
		  display: block;
	  }
	  .col{
		  border-right: 1px solid silver;
		  margin-right: 20px;
	  }
	  h4{
		  margin-bottom: 20px;
	  }

  </style>
</head>
<body>
  	<div class="container text-center mt-5 mb-5">
  		<h1 class="mt-4">사이트맵</h1>
  		<div class="row mt-5 text-left">
			<div class="col">
				<h4>회원관리</h4>
				<div><a href="${croot}/user/loginform">로그인</a></div>
				<div><a href="${croot}/user/insertform">회원가입</a></div>
				<a href="${croot}/user/passfindform">비밀번호찾기</a>
				<div><a href="${croot}/notice/board">공지사항</a> -- 게시판 </div>
				<div><a href="${croot}/user/mvadmin">회원관리</a> -- 관리자 전용 </div>
			</div>
			<div class="col">
				<h4>거래정보</h4>
				<div><a href="${croot}/housedeal/list">주변 탐방</a> -- 거래 내역 정보 </div>
			</div>
			<div class="col">
				<h4>관심지역(회원 전용)</h4>
				<div><a href="${croot}/interest/registform">관심 지역 등록</a></div>
				<div><a href="${croot}/interest/searchPage">관심 지역 탐방</a> -- 주변 상가 정보 </div>
				<div><a href="${croot}/interest/envInfoPage">관심 지역 환경오염 정보</a></div>
			</div>
			<div class="col">
				<h4>기타</h4>
				<div><a href="${croot}/news">오늘의 뉴스</a></div>
			</div>
		</div>

  	</div>
	
    <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>