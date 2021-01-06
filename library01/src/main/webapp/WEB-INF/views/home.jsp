<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ include file="includes/header.jsp"%>

<div class="container border-bottom pb-5 mt-5" >
	<h3 style="text-align:center;">카카오 검색 API를 이용한  책 관리 사이트</h3>
	<ul class="list-group list-group-flush mt-5">
	    <li class="list-group-item">- 사이트 이용을 위해 회원가입 및 로그인을 해주세요 (비밀번호는 암호화되어 보관됩니다.)</li>
	    <li class="list-group-item">- 상단의 책 검색에 접속하여 원하는 책을 찾아 서재에 저장하세요.</li>
	    <li class="list-group-item">- 내 서재에서는 책 상세보기, 리뷰, 문장수집, 삭제가 가능합니다.</li>
	    <li class="list-group-item">- 게시판에 의견을 남겨주세요(첨부파일 추가 가능)</li>
	    <li class="list-group-item">- 게시판 글에 댓글을 남길수도 있습니다.</li>
	    <li class="list-group-item">- 내 정보에 들어가면 패스워드, 이메일 변경이 가능합니다.</li>
	    <li class="list-group-item">- 내 정보에서 책 보유수, 리뷰 작성 수, 문장 수집 작성 수, 게시판 작성 글 수, 댓글 수를 확인할 수 있습니다.</li>
	</ul>
</div>

<div class="container" style="margin: 50px auto 100px">
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
					<a href='/board/${post.board_id }'>
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