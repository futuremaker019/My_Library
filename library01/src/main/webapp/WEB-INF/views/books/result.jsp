<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<section>
	<div class="row">
		<div class="col-lg-12">
			<form id="searchForm" action="/books/result" method="get">
				<div class="form-row">
					<div class="col-md-4">
						<select name="type" class="custom-select">
							<option value="">검색종류</option>
							<option value="T">제목</option>
							<option value="I">ISBN</option>
							<option value="A">작가</option>
							<option value="TI">제목 or ISBN</option>
							<option value="TA">제목 or 작가</option>
							<option value="TIA">제목 or ISBN or 작가</option>
						</select>
					</div>
					<div class="col-md-4">
						<input type="text" class="form-control" name="keyword" placeholder="검색 키워드 입력"/>
					</div>
					<div class="col-md-4">
						<button class="btn btn-default">Search</button>
						<span class="float-right mr-4"><a href="/books" class="btn btn-primary">내 서재</a></span>
					</div>
					<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }">
					<input type="hidden" name="amount" value="${pageMaker.criteria.amount }">
				</div>
			</form>
		</div>
	</div>
</section>

<section style="margin-bottom: 0px;">
	<div class="row">
		<c:forEach items="${searchBookList}" var="list">
			<div class="book-column col-lg-3 col-md-4 col-sm-6" style="margin-bottom: 20px">
				<a class="thumbnail" href='/books/<c:out value="${list.book_id }"/>' >
					<img src="<c:out value='${list.thumbnail }'/>" id="collections" class="card img-thumbnail" style="" >
				</a>
			</div>
		</c:forEach>
	</div>
	<!-- end-card with book list -->
</section>

<section>
	<div class="row">
		<div class="col-lg-12">
			<ul class="pagination float-right">
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
</section>
<!-- end-pagination -->

<form id='pagingForm' action="/books/result" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }">
	<input type="hidden" name="amount" value="${pageMaker.criteria.amount }">
	<input type="hidden" name="type" value="${pageMaker.criteria.type }">
	<input type="hidden" name="keyword" value="${pageMaker.criteria.keyword }">	
</form>

<script type="text/javascript">
	$(document).ready(function(){
		var pagingForm = $("#pagingForm");
		var searchForm = $("#searchForm");
		
		$(".page-item a").on("click", function(e){
			e.preventDefault();
			
			console.log("click");
			
			pagingForm.find("input[name='pageNum']").val($(this).attr("href"));	
			pagingForm.submit();
		});
		
		$("#searchForm button").on("click", function(e){
			if (!searchForm.find("option:selected").val()) {
				alert("검색 종류를 선택하세요.");
				return false;
			}
			
			if (!searchForm.find("input[name='keyword']").val()) {
				alert("키워드를 선택하세요.");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			
			searchForm.submit();
		});
	});
</script>

<%@ include file="../includes/footer.jsp"%>
