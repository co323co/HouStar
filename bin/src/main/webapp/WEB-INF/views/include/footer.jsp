<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>footer</title>
	<!--footer : 하단부 css-->
	<style>
	  /* footer */
	  img {
	  display: block;
	  max-width: 100%;
	  height: auto;
	  }
	
	  /*Footer*/
	  .footer {
	  font-size: 14px;
	  line-height: 1.5;
	  }
	
	  .footer h2.title {
	  font-size: 22px;
	  text-transform: none;
	  margin-bottom: 10px;
	  }
	
	  /*Subfooter*/
	  .subfooter {
	  font-size: 14px;
	  line-height: 1.3;
	  color: #999999;
	  }
	
	  .subfooter p {
	  margin-bottom: 0;
	  }
	
	  .subfooter.default-bg {
	  color: #ffffff;
	  }
	
	
	  .footer-top {
	      padding: 20px 0;
	  }
	  
	  .footer {
	      padding: 40px 0;
	      background-color: #fafafa;
	  }
	  
	  .footer-content {
	      padding: 20px 0;
	  }
	  
	  .subfooter {
	      background-color: #f5f5f5;
	      padding: 25px 0;
	      border-top: 1px solid #eaeaea;
	  }
	
	  a {
	      color: #09afdf;
	  }
	
	  /* Lists
	  ---------------------------------- */
	  ul {
	  list-style: square;
	  }
	
	  .list-icons,
	  .list {
	  list-style: none;
	  padding: 0;
	  }
	
	  .list-icons li,
	  .list li {
	  padding: 5px 0;
	  }
	
	  .list-icons li i {
	  min-width: 25px;
	  text-align: center;
	  }
	
	  .list-inline {
	  margin-top: 9px;
	  margin-bottom: 8px;
	  }
	
	  .header-top .list-inline {
	  display: inline-block;
	  }
	
	  /* Medium desktop devices and tablets */
	  @media (min-width: 992px) and (max-width: 1199px) {
	  .header-top .list-inline > li {
	      padding: 0;
	  }
	  }
	</style>
</head>
<body>
	<!--footer : 하단부 html -->
	<br>
	<footer id="footer" class="clearfix ">
	
		 <!-- .footer start -->
		 <!-- ================ -->
		 <div class="footer">
		   <div class="container">
		     <div class="footer-inner">
		       <div class="row">
		        <div class="col-md-1">
		           <div class="footer-content">
		             <img alt="" src="/img/footer_logo.png" width="80">
		           </div>
		         </div>
		         <div class="col-md-8">
		           <div class="footer-content">
		               
		             <h2 class="title">Happy House</h2>
		             <div class="separator-2"></div>
		            
		             <p>아파트 실거래가 정보, 관심지역 상가·환경오염정보를 제공합니다</p>
		             <p>010-8277-8658</p>
		             <p><a href="#">co323co@gmail.com</a></p>
		           </div>
		         </div>
		       </div>
		     </div>
		   </div>
		 </div>
		 <div class="text-center mt-5" style="margin-bottom:0">
		              <p>Copyright by SSAFY. All rights reserved.</p>
		 </div>
	</footer>
</body>
</html>