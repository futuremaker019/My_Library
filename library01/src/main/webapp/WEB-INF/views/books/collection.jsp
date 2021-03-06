<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<section>
	<div class="row">
		<div class="col-lg-12">
			<div>
				<label style="margin: 0 0 0 5px;">내 서재의 책을 검색하세요.</label>
			</div>
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
						<input class="form-control" type="text" name="keyword" placeholder="검색 키워드 입력"/>
					</div>
					<div class="col-md-4">
						<button class="btn btn-default">Search</button>
						<span class="float-right mr-4"><a href="/books/edit" class="btn btn-warning">책 삭제하기</a></span>
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
		<c:forEach items="${bookList}" var="list">
			<div class="book-column col-lg-3 col-md-4 col-sm-6" style="margin-bottom: 20px">
				<a class="thumbnail" href='/books/<c:out value="${list.book_id }"/>' >
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

<form id='actionForm' action="/books" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }">
	<input type="hidden" name="amount" value="${pageMaker.criteria.amount }">	
</form>

<script type="text/javascript">
	$(document).ready(function(){
		var bookDeletedResult = '<c:out value="${result}"/>';
		var actionForm = $("#actionForm");
		var searchForm = $("#searchForm");
		
		if (bookDeletedResult != '') {
			alert(bookDeletedResult);
		}
		
		$(".page-item a").on("click", function(e){
			e.preventDefault();
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));	
			actionForm.submit();
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
		
		/* var content = ''; 
			
		content += "<div><a class='btn btn-primary' href='/books/<c:out value="${book.book_id }"/>'>상세</a>";
		content += "<button class='btn btn-danger ml-2' id='bookDeleteBtn'>삭제</button></div>"; */
		
		/* $('[data-toggle="popover"]').popover({
            placement : 'right',
            trigger : 'manual',
            html : true,
            content : content
        }).on("mouseenter", function(){
            var _this = this;
            $(_this).popover("show");
            $(".popover").on("mouseleave", function () {
                $(_this).popover('hide');
            })
        }).on("mouseleave", function () {
            var _this = this;
            setTimeout(function () {
                if (!$(".popover:hover").length) {
                    $(_this).popover("hide");
                }
            }, 300);
        }); */
	});
</script>

<%@ include file="../includes/footer.jsp"%>
