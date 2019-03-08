<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
	    <li><a href="<%=request.getContextPath() %>/main">메인</a></li>
		<c:if test="${userVo != null }">
			<li><a href="<%=request.getContextPath() %>/boardManage">게시판 관리</a></li>
	     	<c:forEach items="${boardList }" var="board">
	     		<c:if test="${board.use == '1' }">
	      			<li><a href="${pageContext.request.contextPath }/boardPagingList?boardnum=${board.board_num }">${board.board_name }</a></li>
	     		</c:if>
	     	</c:forEach>    	
		</c:if>
	</ul>
</div>