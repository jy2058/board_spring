<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath() %>/css/dashboard.css" rel="stylesheet">
</head>

<body>
	<%@ include file="/WEB-INF/views/module/header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/WEB-INF/views/module/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">${boardVo.board_name }</h1>
				<form class="form-horizontal" role="form">
					<div class="table-responsive">
			            <table class="table table-striped">
			            	<thead>
				            	<tr>
				                	<th>글번호</th>
				                  	<th>제목</th>
				                 	<th>작성자</th>
				                  	<th>작성일</th>
				             	</tr>
			            	</thead>
			              	<tbody>
				            	<c:forEach items="${postList }" var="post">
				              		<tr class="postTr" data-postnum="${post.post_num }" data-del="${post.del }">
										<td>${post.post_num }</td>
										<td>
					              			<c:choose>
					              				<c:when test="${post.del == '1' }">삭제된 게시글입니다.</c:when>
					              				<c:otherwise>${post.post_title }</c:otherwise>
					              			</c:choose>
										</td>
										<td>${post.userid }</td>
										<td><fmt:formatDate value="${post.post_dt }" pattern="yyyy-MM-dd" /></td>
				              		</tr>
				              	</c:forEach>
				        	</tbody>
			            </table>
		          	</div>
				</form>
	            <form action="${pageContext.request.contextPath }/postForm" method="get">
					<input type="hidden" id="board_num" name="board_num" value="${boardVo.board_num }" />
					<button type="submit" class="btn btn-default">새 글 등록</button>
	            </form>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

	<script>
		$(document).ready(function() {
			$(".postTr").on("click", function() {
				var post_num = $(this).data("postnum");
				var del = $(this).data("del");
				
				if (del == 0) {
					$("#post_num").val(post_num);
					$("#frm").submit();
				} else {
					alert("삭제된 게시글은 조회할 수 없습니다.")
				}
			});
		});
	</script>
	<form id="frm" action="${pageContext.servletContext.contextPath }/post" method="get">
		<input type="hidden" id="post_num" name="post_num" />
		<input type="hidden" id="board_num" name="board_num" value="${boardVo.board_num }" />
  	</form>
</body>
</html>