<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<section style="margin-bottom: 0px;">
	<div class="row">
		<div class="mb-3">
			<button id="bulk-delete" class='btn btn-danger'>일괄 삭제</button>
			<a href="/books" class='btn btn-primary'>내 서재로 가기</a>
		</div>
		<table class="table table-bordered table-hover">
            <colgroup>
                <col width="20">
                <col width="90">
                <col width="*">
                <col width="120">
            </colgroup>
            <thead class="" style="background-color:#353b48; color:#fff; text-align: center;">
					<tr>
						<th scope="col"><input type="checkbox" style='zoom:2.0;' id="checkAll"></th>
						<th scope="col">이미지</th>
						<th scope="col">상세 정보</th>
						<th scope="col">선택</th>
					</tr>
				</thead>
            <tbody class="contents-items">
            <c:forEach items="${bookList}" var="list">
            	<tr class="content">
            		<td>
            			<input type="checkbox" name="check" style='zoom:2.0; margin-top:10px;' data-bookid="${list.bno }">
            		</td>
                	<td>
                		<div>
                			<img class='thumbnail' src="<c:out value='${list.thumbnail }'/>">
               			</div>
          			</td>
                	<td class='details'>
                		<div><strong><c:out value='${list.title }'/></strong></div>
                		<div><c:out value='${list.authors }'/></div>
                		<div><c:out value='${list.publisher }'/></div>
                		<div><c:out value='${list.datetime }'/></div>
                		<div><c:out value='${list.createdate }'/></div>
               		</td>
                	<td class='info'>
                		<div class='btnBundle'>
                			<button id="deleteBookBtn" class='btn btn-danger mt-4' data-bookid="${list.bno }">책 삭제</button>
                		</div>
               		</td>
            	</tr>
           	</c:forEach>
            </tbody>
        </table>
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

<form id='pageForm' action="/books/editing" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum }">
	<input type="hidden" name="amount" value="${pageMaker.criteria.amount }">	
</form>
<form id='deleteForm' action="/books/remove" method="post">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
</form>

<script type="text/javascript">
	$(document).ready(function(){
		var header = "${_csrf.headerName}";
		var token = "${_csrf.token}";
		
		var pageForm = $("#pageForm");
		var deleteForm = $("#deleteForm");
		
		var checkAll = $("#checkAll");
		var check = $("input[name='check']");
		
		var deleteBookBtn = $("#deleteBookBtn");
		var bulkDelete = $("#bulk-delete");
		
		$(".page-item a").on("click", function(e){
			e.preventDefault();
			
			pageForm.find("input[name='pageNum']").val($(this).attr("href"));	
			pageForm.submit();
		});
		
		checkAll.click(function(){
			if(checkAll.prop("checked")){
				check.prop("checked", true);
			} else {
				check.prop("checked", false);
			}
		});
		
		$(".contents-items").on("click", "#deleteBookBtn", function(){
			var book_id = $(this).data("bookid");
			
			if(confirm("책을 삭제하시겠습니까?")){
				$.ajax({
		            type: "delete",
		            url: "/books/" + book_id,
		            beforeSend : function(xhr) {
		                xhr.setRequestHeader(header, token);
		            },
		            success: function (data) {
		                console.log(data);
		            }
		        });
			}
			
			location.reload(true);
		});
		
		bulkDelete.click(function(){
			var bookIdArr = [];
			
			$("input[name='check']:checked").each(function(){
				bookIdArr.push($(this).data("bookid"));
			});
			
			console.log(bookIdArr);
			
			var bookid = {bnos : bookIdArr}
			
			if(confirm("책을 삭제하시겠습니까?")){
				$.ajax({
		            type: "delete",
		            url: "/books/edit",
		            contentType:"application/json; charset=utf-8",
		            data : JSON.stringify(bookid),
		            beforeSend : function(xhr) {
		                xhr.setRequestHeader(header, token);
		            },
		            success: function (data) {
		                console.log(data);
		            }
		        });
			}
			location.reload(true); 
		});
	});
</script>

<%@ include file="../includes/footer.jsp"%>
