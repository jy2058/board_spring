<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<h1 class="page-header">게시판 관리</h1>
				<form id="frm" action="${pageContext.request.contextPath }/boardManage" method="post"
						class="form-horizontal" role="form">
					<div class="table-responsive">
			            <table class="table table-striped">
			            	<thead>
			            		<tr>
			            			<td>
			            				<label for="board_name">게시판 이름</label>
			            			</td>
			            			<td>
			            				<input type="text" class="form-control" id="board_name" name="board_name"
											placeholder="게시판 이름" />
			            			</td>
			            			<td>
			            				<select id="sbUse" name="sbUse" class="form-control">
											<option value="0">미사용</option>
											<option value="1" selected>사용</option>
										</select>
			            			</td>
			            			<td>
										<button id="insBtn" type="button" class="btn btn-default">생성</button>
			            			</td>
			            		</tr>
			            	</thead>
			            	<c:forEach items="${boardList }" var="board">
				              	<tbody>
				              		<tr>
										<td>게시판 이름</td>
										<td>
											<input type="text" class="form-control" id="board_name${board.board_num }" 
												placeholder="게시판 이름" value="${board.board_name }" />
										</td>
										<td>
											<select id="sbUse${board.board_num }" class="form-control">
												<option value="0" 
													<c:if test="${board.use == '0' }">selected</c:if>
												>미사용</option>
												<option value="1" 
													<c:if test="${board.use == '1' }">selected</c:if>
												>사용</option>
											</select>
										</td>
										<td>
											<button type="button" class="updBtn btn btn-default" 
											data-boardnum="${board.board_num }">수정</button>
										</td>
				              		</tr>
				        		</tbody>
				            </c:forEach>
			            </table>
		          	</div>
		          	<input type="hidden" id="updBoardNum" name="updBoardNum" />
		          	<input type="hidden" id="updBoardName" name="updBoardName" />
		          	<input type="hidden" id="updBoardUse" name="updBoardUse" />
		          	<input type="hidden" id="info" name="info" />
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
			$("#insBtn").on("click", function() {
				if ($("#board_name").val().trim() == "") {
					alert("게시판 이름을 입력해 주세요");
					$("#board_name").focus();
					return false;
				}

				$("#info").val("insert");
				$("#frm").submit();
			});

			$(".updBtn").on("click", function() {
				var boardnum = $(this).data("boardnum");
				var boardname = $("#board_name" + boardnum).val();
				var boarduse = $("select[id=sbUse" + boardnum + "]").val();

				$("#updBoardNum").val(boardnum);
				$("#updBoardName").val(boardname);
				$("#updBoardUse").val(boarduse);
				$("#info").val("update");
				$("#frm").submit();
			});
		});
	</script>
</body>
</html>