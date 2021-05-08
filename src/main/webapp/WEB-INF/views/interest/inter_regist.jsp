<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>

	<%@ include file="/WEB-INF/views/include/header.jsp"%>

	<!-- 이제 등록할건데 형꺼 값 가져오는거 써서 저장하고 등록버튼 누르면 세션으로 넘겨준다. 이후에 탐방가면 초기값을 세션에서 가져온걸로 보여줄거 -->

	<div class="container">
		<!-- 중앙 메인 컨테이너-->
		<div class="row justify-content-center">

			<div class="col-8 mt-5 mb-5"
				style="background-color: gainsboro; border-radius: 10px;">

				<div class="font-weight-bold mb-3 mt-3">관심 지역</div>
				
				<!-- 시군구 선택 자바스크립트 코드 -->
				<script>
				//맨 처음에 시 목록을 불러옴
				let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){
					$.get("${root}/housemap/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});//each
						}//function
						, "json"
					);//get
				});
				
				//ready
				$(document).ready(function(){
					//sido change
					$("#sido").change(function() {
						$.get("${root}/housemap/gugun/"+$("#sido").val()
								,function(data, status){
									$("#gugun").empty();
									$("#gugun").append('<option value="0">선택</option>');
									$.each(data, function(index, vo) {
										$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
									});//each
								}//function
								, "json"
						);//get
					});
					
					//gugun change
					$("#gugun").change(function() {
						$.get("${root}/housemap/dong/"+$("#gugun").val()
								,function(data, status){
									$("#dong").empty();
									$("#dong").append('<option value="0">선택</option>');
									$.each(data, function(index, vo) {
										$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
									});//each
								}//function
								, "json"
						);//get
					});


					//등록 버튼 눌렀을 때
					$("#btn_regist").click(function (e) { 
						
						if($("#sido option:selected").val()=="non"){
							alert("동을 선택해주세요! (거래정보가 있는 동만 등록할 수 있습니다)");
							return;
						}

						let info = {
							sido : $("#sido option:selected").text(),
							gugun : $("#gugun option:selected").text(),
							dong : $("#dong option:selected").text()
						}
						$.ajax({
							type: "GET",
							url: "${croot}/api/interest/covert",
							contentType: 'application/json',
							data: info,
							dataType: "json",
							success: function (map) {
								code = map.code;
								console.log(code);
								if(code){	//제대로 선택 다 했으면
									registInterest(code);
								}
							},
							error:function(xhr,status,msg){
								console.log("상태값 : " + status + " Http에러메시지 : "+msg);
							}
						});
					});

					function registInterest(code){
						$.ajax({
							type: "POST",
							url: "${croot}/api/interest",
							data: code,
							dataType : "json",
							contentType: 'application/json',
							success: function (response) {
								console.log(response);
								console.log({ code : code });
								alert("등록 성공!");
							},
							error:function(xhr,status,msg){
								console.log("상태값 : " + status + " Http에러메시지 : "+msg);
								console.log({ code : code });
								alert("이미 등록된 지역입니다");
							}
						});
					}

				});//ready
				
			</script>
				<!-- 시군구 select -->
				<div class="section pt-4 pb-4 mt-2 mb-5">
					<div class="sorting-filters text-center mb-20 d-flex justify-content-center">
						<div class="form-group mr-1">
							<select class="form-control bgPink" name="sido" id="sido">
								<option value="non">선택</option>
							</select>
						</div>
						<div class="form-group md-5">
							<select class="form-control" name="gugun" id="gugun">
								<option value="non">선택</option>
							</select>
						</div>
						<div class="form-group ml-1">
							<select class="form-control" name="dong" id="dong">
								<option value="non">선택</option>
							</select>
						</div>
						<button id="btn_regist" class="form-group ml-4 btn btn-warning btn-animated">등록</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>>
</body>
</html>