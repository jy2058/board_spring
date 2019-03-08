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
	<%@ include file="/WEB-INF/views/module/header.jsp" %>

    <div class="container-fluid">
      <div class="row">
      	<%@ include file="/WEB-INF/views/module/left.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">게시글 조회</h1>
          <form id="frm" action="${pageContext.servletContext.contextPath }/post" method="post" class="form-horizontal" role="form">
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label">제목</label>
				<div class="col-sm-7">
					<label class="control-label">${postVo.post_title }</label>
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">내용</label>
				<div class="col-sm-7">
					<label class="control-label">${postVo.post_con }</label>
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">첨부파일</label>
				<div class="col-sm-7">
					<c:forEach items="${fileList }" var="file">
						<%-- <input type="text" class="form-control" value="${file.filename }" readonly /> --%>
						<a href="${pageContext.servletContext.contextPath }/fileDownload?file_num=${file.file_num }">${file.filename }</a>
					</c:forEach>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-9 col-sm-10">
					<c:if test="${postVo.userid == userVo.userId }">
						<button id="updBtn" type="button" class="btn btn-default">수정</button>
						<button id="delBtn" type="button" class="btn btn-default">삭제</button>
					</c:if>
					<button id="replyBtn" type="button" class="btn btn-default">답글</button>
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">댓글</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="comment"/>
					<table class="table table-striped">
					<c:forEach items="${commentList }" var="comment">
						<tr>
							<c:choose>
								<c:when test="${comment.del == '1' }">
									<td colspan="3">삭제된 댓글입니다.</td>
								</c:when>
								<c:otherwise>
									<td>${comment.userid }</td>
									<td>${comment.comment_con }</td>
									<td><fmt:formatDate value="${comment.comment_dt }" pattern="yyyy-MM-dd kk:mm" /></td>
									<c:if test="${comment.userid == userVo.userId }">
										<td>
											<button class="delCommentBtn btn btn-default" type="button"  
												data-commentnum="${comment.comment_num }">삭제</button>
										</td>
									</c:if>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				</div>
				<div class="col-sm-2">
					<button id="commentBtn" type="button" class="btn btn-default">등록</button>
				</div>
			</div>
			<input type="hidden" id="post_num" name="post_num" value="${postVo.post_num }" />
			<input type="hidden" id="comment_num" name="comment_num" />
			<input type="hidden" id="info" name="info" />
		</form>
        </div>
      </div>
    </div>

	<form id="updFrm" action="${pageContext.servletContext.contextPath }/postModifyForm" method="get" class="form-horizontal" role="form">
		<input type="hidden" id="post_num" name="post_num" value="${postVo.post_num }" />
	</form>
	<form id="replyFrm" action="${pageContext.servletContext.contextPath }/postReplyForm" method="get" class="form-horizontal" role="form">
		<input type="hidden" id="post_num" name="post_num" value="${postVo.post_num }" />
		<input type="hidden" id="board_num" name="board_num" value="${postVo.board_num }" />
	</form>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	<script>
		$(document).ready(function() {
			$("#updBtn").on("click", function() {
				$("#updFrm").submit();
			});
			
			$("#delBtn").on("click", function() {
				$("#info").val("delete");
				$("#frm").submit();
			});
			
			$("#replyBtn").on("click", function() {
				$("#replyFrm").submit();
			});
			
			$("#commentBtn").on("click", function() {
				$("#info").val("comment");
				$("#frm").submit();
			});
			
			$(".delCommentBtn").on("click", function() {
				var comment_num = $(this).data("commentnum");
				
				$("#comment_num").val(comment_num);
				$("#info").val("deleteComment");
				$("#frm").submit();
			});
		});
	</script>
  </body>
</html>
    