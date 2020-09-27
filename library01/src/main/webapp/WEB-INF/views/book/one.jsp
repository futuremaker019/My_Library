<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<section class="container">
	<div class="col-lg-12">
		<div class="form-row" style="padding-top: 30px;">
			<div class="form-group col-md-6 text-center">
                <img src="${book.thumbnail }" style="width: 180px;">
            </div>
            <div class="form-group col-md-6 text-center" >
            	<p class="">
            		<h5 style="margin:auto 0;">제목 : <c:out value="${book.title }"/></h5>
            	</p>
                <p> 작가 : 
                <c:forEach items="${authors}" var="authors" >
                	<span><c:out value="${authors.authors }"/>.</span>
                </c:forEach>
                </p>
                <p>isbn : <c:out value="${book.isbn }"/></p>
                <P><a href="<c:out value="${book.url }"/>">책 상세 정보 보기</a></P>
            </div>
		</div>
	</div>
</section>
<form id='actionForm' action="/book" method="get">

</form>

<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>

<%@ include file="../includes/footer.jsp"%>