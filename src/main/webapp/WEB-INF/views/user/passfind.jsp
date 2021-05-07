<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String root = request.getContextPath();
	String findPwd = (String)request.getAttribute("findpwd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>행복한 우리집</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style>
.banner.dark-translucent-bg {
	min-height: 250px;
	padding-top: 60px;
	padding-bottom: 20px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<!-- 중앙 메인 컨테이너-->
		<div class="row justify-content-center">

			<div class="col-8 mt-5 mb-5"
				style="background-color: gainsboro; border-radius: 10px;">

				<div class="font-weight-bold mb-3 mt-3 pl-2 pb-2">
				 <span class="mr-3">비밀번호 찾기</span>
				 <%
				 	if(findPwd!=null && findPwd.equals("fail")) {
				 %>
				 <span class="badge badge-pill badge-danger">해당 정보의 사용자가 없습니다!</span>
				 <% } %>
				</div>
				<!-- 비밀번호 찾기 전 or 실패-->
				<div style="display: <%= (findPwd==null)||(findPwd.equals("fail"))? "block":"none" %>">
					<form method="post" action="<%=root%>/ayj" class="form-horizontal was-validated">
						<input type="hidden" name="act" value="findpassword"> 
						<div class="form-group row">
							<label class="col-sm-3 text-md-right" for="id">아이디<span
								class="text-danger small">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" placeholder="ID" id="id" name="id" required minlength="2">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 text-md-right" for="name">이름<span
								class="text-danger small">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" placeholder="이름" id="name" name="name" required minlength="2">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 text-md-right" for="phone">전화번호<span
								class="text-danger small">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									placeholder="010-xxxx-xxxx" id="phone" name="phone"  required minlength="12">
							</div>
						</div>
						<div class="form-group row text-right mr-5">
							<div class="ml-md-auto col-md-9">
								<button type="submit"
									class="btn btn-group btn-info btn-animated">비밀번호 찾기</button>
							</div>
						</div>
					</form>
				</div>

				<!-- 비밀번호 찾기 후 -->
				<div class="text-center" style="display: <%= (findPwd!=null)&&(!findPwd.equals("fail"))? "block":"none" %>">
					<h5>Your Password is</h5>
					<h3 class="text-info"><%= findPwd %></h3>
					<br>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>