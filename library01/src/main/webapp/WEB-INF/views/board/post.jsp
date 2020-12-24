<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div class="row border-bottom">
		<div class="col mb-3">
			<h3><c:out value="${post.title}"/></h3>
				<div class="mt-2">
					<span><c:out value="${post.writer}"/></span>
					<span class="float-right ml-5">조회수, 공감</span>
				</div>
			<div class="mt-2"><c:out value="${post.createdDate }"/></div>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<div>
				<c:out value="${post.content }" escapeXml="false"/>
			</div>			
		</div>
	</div>
	<div class="row border-top">
		<div class="col mt-3">
			<div class="float-right">
				<button type="button" id="modifyBtn" class="btn btn-success">수정</button>
				<button type="button" id="listBtn" class="btn btn-primary">게시판</button>
			</div>
		</div>
	</div>
</div>

<form id='page-form' action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${criteria.pageNum }">
	<input type="hidden" name="amount" value="${criteria.amount }">	
</form>

<script type="text/javascript">
$(document).ready(function(){
	var pageForm = $("#page-form");
	var board_id = '<c:out value="${post.board_id}"/>';
	
	$("#listBtn").click(function(){
		pageForm.submit();
	});
	
	$("#modifyBtn").on("click", function(e){
		pageForm.attr("action", "/board/modify/" + board_id);
		pageForm.submit();
	});
});
</script>

<%@ include file="../includes/footer.jsp"%>