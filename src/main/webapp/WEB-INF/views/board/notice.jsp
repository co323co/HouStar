<%@page import="com.ssafy.happyhouse.model.mapper.NoticeDao"%> <%@page import="java.util.List"%> <%@
page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ include
file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Happy House | 공지사항</title>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!--게시글 목록 받아오는 부분-->
    <script type="text/javascript">
      $(document).ready(function () {
        loadList();

        function loadList() {
          $.ajax({
            type: "get",
            url: "${croot}/notice/api/list",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (response) {
              console.log(response);
              makeList(response);
            },
            error: function (xhr, status, msg) {
              console.log("상태값 : " + status + " / Http 에러메시지 : " + msg);
            },
          });
        }

        function makeList(list) {
          let content = "";
          $("#list").empty();
          list.forEach((notice) => {
            content += `
                  <tr>
                      <td>${"${notice.id}"}</td>
                      <td>${"${notice.title}"}</td>
                      <td>${"${notice.views}"}</td>
                      <td><button onClick="location.href='<%=croot%>/notice?act=mvnoticeview&nid=$'${
                        notice.id
                      }'}'" type="button" class="btn btn-secondary ">조회</button></td>
                  </tr>
                  `;
          });

          $("#list").append(content);
        }
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
    <br />
    <div class="container">
      <!-- 중앙 메인 컨테이너-->
      <div class="row justify-content-center">
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
            <tbody id="list"></tbody>
          </table>

          <form method="post" action="<%=croot%>/notice/search" class="form-inline">
            <input
              type="text"
              name="search_value"
              class="form-control mb-2 mr-sm-2"
              placeholder="제목으로 검색하기"
            />
            <button type="submit" class="btn btn-primary mb-2">검색</button>
            <button
              onClick="location.href='<%=croot%>/notice/writeForm'"
              type="button"
              class="btn btn-primary mb-2 ml-3"
            >
              공지 등록
            </button>
          </form>
        </div>
      </div>
    </div>

    <%@ include file="/WEB-INF/views/include/footer.jsp" %>>
  </body>
</html>
