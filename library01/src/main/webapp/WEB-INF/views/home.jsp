<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ include file="includes/header.jsp"%>

<div style="text-align:center;">
	<h2>Hello there!</h2>
	<h3>I hope you don't feel blue today</h3>
	<P>  The time on the server is ${serverTime}. </P>
</div>

<div class="container mt-5">
	<div class="form-row d-flex">
		<div class="form-group col-md-6 pl-5 pr-5">
			<div class="books-image">
				<c:if test="${images eq null }">
					<div><img src="/resources/image/image1.jpg" id="main-img" class="img-thumbnail"></div>
					<div><img src="/resources/image/image2.jpg" id="main-img" class="img-thumbnail"></div>
					<div><img src="/resources/image/image3.jpg" id="main-img" class="img-thumbnail"></div>
					<div><img src="/resources/image/image4.jpg" id="main-img" class="img-thumbnail"></div>
				</c:if>
				<c:if test="${images ne null }">
					<c:forEach items="${images }" var="image">
						<div><img src="<c:out value='${image.thumbnail }'/>" id="main-img" class="img-thumbnail"></div>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="form-group col-md-6 pl-5 pr-5">
			<h5>게시판</h5>
			<ul class="list-group list-group-flush border-top">
				<c:forEach items="${posts }" var="post">
					<a href='/board/post/${post.board_id }'>
						<li class="list-group-item d-flex justify-content-between"><c:out value='${post.title }'/>
							<span class="badge badge-light badge-pill"><c:out value='${post.updatedDate }'/></span>
						</li>
					</a>
			  	</c:forEach>
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/resources/slick/slick.min.js"></script>
<!-- <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script> -->
<script>
$(document).ready(function(){
	$('.books-image').slick({
		slidesToShow: 3,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 2000
	});
});
</script>

<%@ include file="includes/footer.jsp"%>