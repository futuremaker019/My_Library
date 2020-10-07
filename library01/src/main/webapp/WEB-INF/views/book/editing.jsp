<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<section style="margin-bottom: 0px;">
	<div class="row">
		<c:forEach items="${bookList}" var="list">
			<div class="book-column col-lg-3 col-md-4 col-sm-6" style="margin-bottom: 20px">
				<a class="thumbnail" href='/book/one?bno=<c:out value="${list.bno }"/>' >
					<img src="<c:out value='${list.thumbnail }'/>" id="collections" class="card img-thumbnail">
				</a>
			</div>
		</c:forEach>
	</div>
	<!-- end-card with book list -->
</section>
<!-- end book list -->

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

<form id='actionForm' action="/book" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }">
	<input type="hidden" name="amount" value="${pageMaker.criteria.amount }">	
</form>

<script type="text/javascript">
	$(document).ready(function(){
		var actionForm = $("#actionForm");
		var searchForm = $("#searchForm");
		
		$(".page-item a").on("click", function(e){
			e.preventDefault();
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));	
			actionForm.submit();
		});
		
		
	});
</script>

<%@ include file="../includes/footer.jsp"%>