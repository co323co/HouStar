<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
	String root = request.getContextPath();

%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
     	<script>
    	$(document).ready(function() {
    		
    		
    		
    		
			$("#searchBtn").click(function() {
				
				if($("#sword").val() == "") {
					alert("모든 목록 조회!!");
				} 
				$("#searchform").attr("action", "${root}/admin/search").submit();
			});
    		
    		
    		//회원 목록
    		$.ajax({
    			url:'${root}/admin/user',  
    			type:'GET',
    			contentType:'application/json;charset=utf-8',
    			dataType:'json',
    			success:function(users) {
    				makeList(users);
    			},
    			error:function(xhr,status,msg){
    				console.log("상태값 : " + status + " Http에러메시지 : "+msg);
    			},
    			statusCode: {
    				500: function() {
    					alert("서버에러.");
    					// location.href = "/error/e500";
    				},
    				404: function() {
    					alert("페이지없다.");
    					// location.href = "/error/e404";
    				}
    			}	
    		});
    		
    		$("#registerBtn").click(function() {
    			let registerinfo = JSON.stringify({
    				"userId" : $("#userId").val(), 
    				"userPwd" : $("#userPwd").val(), 
    				"userName" : $("#userName").val(), 
    				"userAddress" : $("#userAddress").val(), 
    				"userPhone" : $("#userPhone").val()
    			   });
    			$.ajax({
    				url:'${root}/admin/user',  
    				type:'POST',
    				contentType:'application/json;charset=utf-8',
    				dataType:'json',
    				data: registerinfo,
    				success:function(users) {
    					$("#userId").val('');
    					$("#userPwd").val('');
    					$("#userName").val('');
    					$("#email").val('');
    					$("#userPhone").val('');
    					$("#userRegModal").modal("hide");
    					makeList(users);
    				},
    				error:function(xhr,status,msg){
    					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
    				}
    			});
    		});
    		
    		$(document).on("dblclick", "tr.view", function() {
    			let vid = $(this).attr("data-id");
    			console.log(vid);
    			$.ajax({
    				url:'${root}/admin/user/' + vid,  
    				type:'GET',
    				contentType:'application/json;charset=utf-8',
    				success:function(user) {
    					$("#vid").text(user.userId);
    					$("#vname").text(user.userPwd);
    					$("#vemail").text(user.userName);
    					$("#vaddress").text(user.userAddress);
    					$("#vjoindate").text(user.userPhone);
    					$("#userViewModal").modal();
    				},
    				error:function(xhr,status,msg){
    					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
    				}				
    			});			
    		});
    		
    		// 회워 정보 수정 보기.
    		$(document).on("click", ".modiBtn", function() {
    			let mid = $(this).parents("tr").attr("data-id");
    			$("#view_" + mid).css("display", "none");
    			$("#mview_" + mid).css("display", "");
    		});
    		
    		// 회워 정보 수정 실행.
    		$(document).on("click", ".modifyBtn", function() {
    			let mid = $(this).parents("tr").attr("data-id");
    			let modifyinfo = JSON.stringify({
    						"userId" : mid, 
    						"userPwd" : $("#userPwd" + mid).val(), 
    						"userName" : $("#userName" + mid).val(), 
    						"userAddress" : $("#userAddress" + mid).val(),
    						"userPhone" : $("#userPhone" + mid).val()
    					   });
    			$.ajax({
    				url:'${root}/admin/user',  
    				type:'PUT',
    				contentType:'application/json;charset=utf-8',
    				dataType:'json',
    				data: modifyinfo,
    				success:function(users) {
    					makeList(users);
    				},
    				error:function(xhr,status,msg){
    					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
    				}
    			});
    		});
    		
    		// 회워 정보 수정 취소.
    		$(document).on("click", ".cancelBtn", function() {
    			let mid = $(this).parents("tr").attr("data-id");
    			$("#view_" + mid).css("display", "");
    			$("#mview_" + mid).css("display", "none");
    		});
    		
    		// 회워 탈퇴.
    		$(document).on("click", ".delBtn", function() {
    			if(confirm("정말 삭제?")) {
    				let delid = $(this).parents("tr").attr("data-id");
    				$.ajax({
    					url:'${root}/admin/user/' + delid,  
    					type:'DELETE',
    					contentType:'application/json;charset=utf-8',
    					dataType:'json',
    					success:function(users) {
    						makeList(users);
    					},
    					error:function(xhr,status,msg){
    						console.log("상태값 : " + status + " Http에러메시지 : "+msg);
    					}
    				});
    			}
    		});
    	});
    	
    	function makeList(users) {
    		$("#userlist").empty();
    		$(users).each(function(index, user) {
    			let str = ` 
    			<tr id="view_${'${user.userId}'}" class="view" data-id="${'${user.userId}'}">
    				<td>${'${user.userId}'}</td>
    				<td>${'${user.userPwd}'}</td>
    				<td>${'${user.userName}'}</td>
    				<td>${'${user.userAddress}'}</td>
    				<td>${'${user.userPhone}'}</td>
    				<td>
    					<button type="button" class="modiBtn btn btn-outline-primary btn-sm">수정</button>
    					<button type="button" class="delBtn btn btn-outline-danger btn-sm">삭제</button>
    				</td>
    			</tr>
    			<tr id="mview_${'${user.userId}'}" data-id="${'${user.userId}'}" style="display: none;">
    				<td>${'${user.userId}'}</td>
    				<td><input type="text" name="userPwd" id="userPwd${'${user.userId}'}" value="${'${user.userPwd}'}"></td>
    				<td><input type="text" name="userName" id="userName${'${user.userId}'}" value="${'${user.userName}'}"></td>
    				<td><input type="text" name="userAddress" id="userAddress${'${user.userId}'}" value="${'${user.userAddress}'}"></td>
    				<td><input type="text" name="userPhone" id="userPhone${'${user.userId}'}" value="${'${user.userPhone}'}"></td>    				
    				<td>
    					<button type="button" class="modifyBtn btn btn-primary btn-sm">수정</button>
    					<button type="button" class="cancelBtn btn btn-danger btn-sm">취소</button>
    				</td>
    			</tr>
    			`;
    			$("#userlist").append(str);
    		});//each
    	}
     	</script>
</head>
<body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <div  class="container" align="center">
     <br>
        <br>
    <h2>회원 목록 관리</h2>
    
    <div align="right"><button type="button" class="modiBtn btn btn-outline-info" data-toggle="modal" data-target="#userRegModal">등록</button></div>
    <br> <br>
    <!-- 검색 폼 -->
     <form id="searchform" method="get" class="form-inline" action="">
	
	  <table class="table table-borderless">
	  	<tr>
	  		<td align="right">
		  	  <select class="form-control" name="key" id="skey">
			    <option value="userid" selected="selected">아이디</option>
			    <option value="articleno">이름</option>
			    <option value="subject">주소</option>
			  </select>
			  <input type="text" class="form-control" placeholder="검색어 입력." name="word" id="sword">
			  <button type="button" id="searchBtn" class="btn btn-primary">검색</button>
			</td>
	  	</tr>
	  </table>
	  </form>
    <br> <br>
    <div align="center">
    <table class="table table-hover text-center">
  		<colgroup>
            <col width="120">
            <col width="120">
            <col width="120">
            <col width="*">
            <col width="180">
            <col width="130">
        </colgroup>
    	<thead>
	      	<tr>
	        	<th class="text-center">아이디</th>
	        	<th class="text-center">비밀번호</th>
	        	<th class="text-center">이름</th>
	        	<th class="text-center">주소</th>
	        	<th class="text-center">전화번호</th>
	        	<th class="text-center">수정/삭제</th>
	      	</tr>
    	</thead>
    	<tbody id="userlist"></tbody>
	</table>
	</div>
    </div>
    
    
    
<!-- 회원 등록 모달 -->
<div class="modal" id="userRegModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">회원등록</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="memberform" method="post" action="">
		<input type="hidden" name="act" id="act" value="">
			<div class="form-group" align="left">
				<label for="name">userId</label>
				<input type="text" class="form-control" id="userId" name="userId" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">userPwd</label>
				<input type="text" class="form-control" id="userPwd" name="userPwd" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="userName" name="userName" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="email">이메일</label><br>
				<input type="text" class="form-control" id="userAddress" name="userAddress" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">주소</label>
				<input type="text" class="form-control" id="userPhone" name="userPhone" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn">회원가입</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
      </div>
    </div>
  </div>
</div>
    
    
    
    
    
    <!-- 회원 정보 모달 -->
<div class="modal" id="userViewModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">회원정보</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
       	<table class="table table-bordered">
            <colgroup>
                <col width="120">
                <col width="*">
                <col width="120">
                <col width="*">
            </colgroup>
            <tbody>
            <tr>
                <th class="text-center">ID</th>
                <td class="text-left" id="vid"></td>
                <th class="text-center">비밀번호</th>
                <td class="text-left" id="vname"></td>
            </tr>
            <tr>
            	<th class="text-center">이름</th>
                <td class="text-left" id="vemail"></td>
                <th class="text-center">주소</th>
                <td class="text-left" id="vjoindate"></td>
            </tr>
            <tr>
                <th class="text-center">전화번호</th>
                <td class="text-left" colspan="3" id="vaddress"></td>
            </tr>
            </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
    <%@ include file="/WEB-INF/views/include/footer.jsp" %>>
</body>
</html>