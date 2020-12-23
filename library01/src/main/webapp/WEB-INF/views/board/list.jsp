<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div class="">
		<div class="mt-5">
			<div class="mb-3">
				<h3 style="text-align: center;">게시판 (의견을 남겨주세요.)</h3>
			</div>
			<table class="table table-bordered table-hover">
				<colgroup>
					<col style="width:40px">
					<col style="width:220px">
					<col style="width:50px">
					<col style="width:50px">
					<col style="width:20px">
				</colgroup>
				<thead class="" style="background-color:#353b48; color:#fff; text-align: center;">
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boardList}" var="board">
						<tr>
							<td><c:out value="${board.board_id}"/></td>
							<td>
								<a id="post" href='/board/post/<c:out value="${board.board_id }"/>'>
									<c:out value="${board.title }"/>
								</a>
							</td>
							<td><c:out value="${board.writer }"/></td>
							<td><c:out value="${board.createdDate }"/></td>
							<td>10</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="d-flex justify-content-end">
			<button type="button" id="createPostBtn" class="btn btn-info">글쓰기</button>
		</div>
	</div>
</div>

<form id="form" action="/board/list" method="get">
	
</form>

<script>
$(document).ready(function(){
	$("#createPostBtn").click(function(){
		location.href = "/board/create";
	});
});
</script>

<%@ include file="../includes/footer.jsp"%>