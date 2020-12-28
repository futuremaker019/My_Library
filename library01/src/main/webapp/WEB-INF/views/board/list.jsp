<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div class="">
		<div class="mt-5">
			<div class="mb-3">
				<h3 style="text-align: center;">의견을 남겨주세요.</h3>
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
						<tr id="post">
							<td><c:out value="${board.board_id}"/></td>
							<td>
								<a href='<c:out value="${board.board_id }"/>' style="display:block;">
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
		<div class="">
			<div class="col-lg-12 ">
				<ul class="pagination d-flex justify-content-center">
					<c:if test = "${pageMaker.previous }">
						<li class = "page-item previous">
							<a class="page-link" href = "${pageMaker.startPage - 1 }">Previous</a>
						</li>
					</c:if>
					
					<c:forEach var="number" begin="${pageMaker.startPage }" end = "${pageMaker.endPage }">
						<li class="page-item ${pageMaker.criteria.pageNum == number ? 'active' : '' }">
							<a class="page-link" href="${number }">${number }</a>				
						</li>
					</c:forEach>
					
					<c:if test="${pageMaker.next }">
						<li class="page-item next">
							<a class="page-link" href="${pageMaker.endPage + 1 }">Next</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
		<!-- end-pagination -->
	</div>
</div>

<form id='page-form' action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }">
	<input type="hidden" name="amount" value="${pageMaker.criteria.amount }">	
</form>

<script src="/resources/bootstrap-4.0.0-dist/js/util.js"></script>
<script>
$(document).ready(function(){
	var pageForm = $("#page-form");
	
	$("#createPostBtn").click(function(){
		location.href = "/board/create";
	});
	
	$(".page-item a").on("click", function(e){
		e.preventDefault();
		
		pageForm.find("input[name='pageNum']").val($(this).attr("href"));	
		pageForm.submit();
		
		util.scrollToTop();
	});
	
	$("#post a").on("click", function(e){
		e.preventDefault();
		pageForm.attr("action", "/board/post/" + $(this).attr("href"));
		pageForm.submit();
	});
});
</script>

<%@ include file="../includes/footer.jsp"%>