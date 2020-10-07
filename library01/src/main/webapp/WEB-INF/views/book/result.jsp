<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<section>
	<div class="row">
		<div class="col-lg-12">
			<div style="text-align: center">
				<h2>Books in My Library</h2>
			</div>
			<div>
				<h4>검색하세요.</h4>
			</div>
			<form id="searchForm" action="/book/result" method="get">
				<select name="type">
					<option value="">검색종류</option>
					<option value="T">제목</option>
					<option value="I">ISBN</option>
					<option value="A">작가</option>
					<option value="TI">제목 or ISBN</option>
					<option value="TA">제목 or 작가</option>
					<option value="TIA">제목 or ISBN or 작가</option>
				</select>
				<input type="text" name="keyword" placeholder="검색 키워드 입력"/>
				<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }">
				<input type="hidden" name="amount" value="${pageMaker.criteria.amount }">
				<button class="btn btn-default">Search</button>
			</form>
		</div>
	</div>
</section>

<section style="margin-bottom: 0px;">
	<div class="row">
		<c:forEach items="${searchBookList}" var="list">
			<div class="book-column col-lg-3 col-md-4 col-sm-6" style="margin-bottom: 20px">
				<a class="thumbnail" href='/book/one?bno=<c:out value="${list.bno }"/>' >
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

<form id='pagingForm' action="/book/result" method="get">
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
