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
          
        	//들어오자마자 회원정보 뿌리기 
        	$.ajax({ 
			url:'${root}/api/user' ,
			type:'GET',		
			dataType:'json',
			success:function(users) {
				makeList(users);
			},
			error:function(xhr,status,msg){
				console.log("상태값 : " + status + " Http에러메시지 : "+msg);
			},
			statusCode: {
				404: function(){
					alert("페이지없다");
					//location.href = "error/e404"; -> 나중에 페이지만들자
				},
				500: function(){
					alert("서버에문제");
					//location.href = "error/e500";
				}
			}
		});
        	
    		// 회워 정보 수정 보기.
    		// 수정 버튼의 parent에서 tr을 찾고 data-id를 얻어와라.
    		$(document).on("click", ".modiBtn", function() {
    			$("#view").css("display", "none"); // 기존 view는 안보이게
    			$("#mview").css("display", ""); // 수정에 해당하는 뷰는 보이게
    		});
        	
    		// 회워 정보 수정 실행.
    		//수정버튼 누르면 수정뷰 보이기
        	//수정누르면 수정된걸로 업데이트
    		$(document).on("click", ".modifyBtn", function() {
    		//	let mid = $(this).parents("div").attr("data-id");
    			let mid = $("#target").text();
    			console.log(mid);
    			console.log(mid);
    			let modifyinfo = JSON.stringify({
    						"userId" : mid, 
    						"userPwd" : $("#userPwd" + mid).val(), 
    						"userName" : $("#userName" + mid).val(), 
    						"userAddress" : $("#userAddress" + mid).val(),
    						"userPhone" : $("#userPhone" + mid).val()
    					   });
    			$.ajax({
    				url:'${root}/api/user',  
    				type:'PUT',
    				contentType:'application/json;charset=utf-8',
    				dataType:'json',
    				data: modifyinfo,
    				success:function(users) {
    					makeList(users);
    					console.log(modifyinfo);
    				},
    				error:function(xhr,status,msg){
    					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
    				}
    			});
    		});
    		
    		// 회워 탈퇴.
    		$(document).on("click", ".delBtn", function() {
    			if(confirm("정말 회원 탈퇴하시겠습니까??")) {
    				let delid = $("#target").text();
    				
    			}
    		});
			
    		// 회워 정보 수정 취소.
    		$(document).on("click", ".cancelBtn", function() {
    			let mid = $(this).parents("tr").attr("data-id");
    			$("#view").css("display", "");
    			$("#mview").css("display", "none");
    		});
    		
        	function makeList(users){
        		$("#userlist").empty();
        		$(users).each(function(index, user) {
        			let str = ` 
        				 <div id ="view" class="col-sm-8 mt-5 mb-1 " style="background-color: gainsboro; border-radius: 10px;"  data-id="${'${user.userId}'}">
                        
                        <div class="font-weight-bold mb-3 mt-3 pl-2 pb-2">회원 정보 확인</div>
                              <div class="row">
                                <label class="col-sm-3 text-md-right" for="Id">아이디<span class="text-danger small">*</span></label>
                                <div class="col-sm-8">
                                  <label id="target">${'${user.userId}'}</label>
                                </div>
                              </div>
                              <div class="row">
                                <label class="col-sm-3 text-md-right" for="Id">비밀번호<span class="text-danger small">*</span></label>
                                <div class="col-sm-8">
                                  <label >${'${user.userPwd}'}</label>
                                </div>
                              </div>
                              <div class="row">
                                <label class="col-sm-3 text-md-right" for="Id">이름<span class="text-danger small">*</span></label>
                                <div class="col-sm-8">
                                  <label >${'${user.userName}'}</label>
                                </div>
                              </div>
                              <div class="row">
                                <label class="col-sm-3 text-md-right" for="Id">주소<span class="text-danger small">*</span></label>
                                <div class="col-sm-8">
                                  <label >${'${user.userAddress}'}</label>
                                </div>
                              </div>
                              <div class="row">
                                <label class="col-sm-3 text-md-right" for="Id">전화번호<span class="text-danger small">*</span></label>
                                <div class="col-sm-8">
                                  <label >${'${user.userPhone}'}</label>
                                </div>
                              </div>
                              <br>
      			            <div class="pl-5 pr-3 pb-3">
      			              <button type="button" class="modiBtn btn btn-dark" >수정</button>
      			              <!-- 수정하면 새로운 페이지에서 수정-->
      			            <button type="button" onClick="location.href='${root}/user/delete'"class="btn btn-dark" >삭제(탈퇴)</button>
      			            <!--<button type="button" onClick="location.href='user/delete'"class="delBtn btn btn-dark" >삭제(탈퇴)</button> !-->
      						<br>
               			   </div>
                    </div> 
                    <div id ="mview" class="col-sm-8 mt-5 mb-1 " style="background-color: gainsboro; border-radius: 10px; display: none;"  data-id="${'${user.userId}'}">                
                    <div class="font-weight-bold mb-3 mt-3 pl-2 pb-2">회원 정보 확인</div>
                          <div class="row">
                            <label class="col-sm-3 text-md-right" for="Id">아이디<span class="text-danger small">*</span></label>
                            <div class="col-sm-8">
                              <label id="target" >${'${user.userId}'}</label>
                            </div>
                          </div>
                          <div class="row">
                            <label class="col-sm-3 text-md-right" for="Id">비밀번호<span class="text-danger small">*</span></label>
                            <div class="col-sm-8">
                            <input type="text" name="userPwd" id="userPwd${'${user.userId}'}" value="${'${user.userPwd}'}">                         
                            </div>
                          </div>
                          <div class="row">
                            <label class="col-sm-3 text-md-right" for="Id">이름<span class="text-danger small">*</span></label>
                            <div class="col-sm-8">
                              <input type="text" name="userName" id="userName${'${user.userId}'}" value="${'${user.userName}'}">        
                            </div>
                          </div>
                          <div class="row">
                            <label class="col-sm-3 text-md-right" for="Id">주소<span class="text-danger small">*</span></label>
                            <div class="col-sm-8">
                              <input type="text" name="userAddress" id="userAddress${'${user.userId}'}" value="${'${user.userAddress}'}">        
                            </div>
                          </div>
                          <div class="row">
                            <label class="col-sm-3 text-md-right" for="Id">전화번호<span class="text-danger small">*</span></label>
                            <div class="col-sm-8">
                              <input type="text" name="userPhone" id="userPhone${'${user.userId}'}" value="${'${user.userPhone}'}">        
                            </div>
                          </div>
                          <br>
  			            <div class="pl-5 pr-3 pb-3">
  			              <button type="button" class="modifyBtn btn btn-dark" >수정</button>
  			            <button type="button" class="cancelBtn btn btn-dark ">취소</button>
  			              <!-- 수정하면 새로운 페이지에서 수정-->
  			          
  						<br>
           			   </div>
                </div>
        				`;
        			$("#userlist").append(str);
        		});//each
        	}

        	//삭제 누르면 회원정보 삭제 하고 빈 표 보이기 
            $("#delete").on('click',function () {
                alert("회원 탈퇴 되었습니다.");
              
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
          <div class="row justify-content-center " align="center"> 
            <div id="userlist" class="col-sm-8 mt-5 mb-1  " style="background-color: gainsboro; border-radius: 10px; "></div>
            	<!--
            	    <button type="button" class="cancelBtn btn btn-dark" >삭제(탈퇴)</button> -->	
            </div>
            <div align="center" class="ml-4">
          <%--   <a id="out" class="nav-link" href='${root }/user/detele'>"탈퇴"</a> --%>
            </div>
            
        </div>
       
        <%@ include file="/WEB-INF/views/include/footer.jsp" %>>
          
    </body>
</html>