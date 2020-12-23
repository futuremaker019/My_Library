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

<script type="text/javascript">
$(document).ready(function(){
	$("#listBtn").click(function(){
		location.href = "/board/list";
	})
});
</script>

<%@ include file="../includes/footer.jsp"%>