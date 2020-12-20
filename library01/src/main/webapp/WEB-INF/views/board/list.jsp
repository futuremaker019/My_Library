<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div class="">
		<div class="">
			<div class="">
				<div>
					<p>의견을 남겨주세요.</p>
				</div>
				<table class="table table-bordered table-hover">
					<colgroup>
						<col style="width:40px">
						<col style="width:200px">
						<col style="width:50px">
						<col style="width:80px">
						<col style="width:80px">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">작성일</th>
							<th scope="col">수정일</th>
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
								<td>조회수</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<form id="form" action="/board/list" method="get">
	
</form>

<script>
$(document).ready(function(){
	
	// RequestParam을 이용해서 페이지를 이동했다.
	// 내가 적용하는 방법은 pathvariable을 사용하는 방식이다.
	// 차라리 href에 바로 적용하는게 어떨까? 
	/* $("#post").on("click", function(e){
		e.preventDefault();
		
	}) */
});
</script>

<%@ include file="../includes/footer.jsp"%>