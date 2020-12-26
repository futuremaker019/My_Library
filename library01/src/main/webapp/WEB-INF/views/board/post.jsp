<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp"%>
<sec:authentication property="principal" var="userinfo"/>

<section class="container pt-3">
	<div class="col-lg-12">
		<!-- /.card -->
		<div class="card">
			<div class="card-body">
				<h3><c:out value="${post.title}"/></h3>
				<div class="mt-2">
					<span><c:out value="${post.writer}"/></span>
					<span class="float-right ml-5">조회수, 공감</span>
				</div>
				<div class="mt-2"><c:out value="${post.createdDate }"/></div>
			</div>
			<!-- /.card-heading -->
			<div class="card-body">
				<ul class="list-group">
					<li class='list-group-item'>
	                    <div>
	                        <c:out value="${post.content }" escapeXml="false"/>
	                    </div>
	                </li>
				</ul>
			</div>
			<!-- /.card-heading -->
			<div class="card-body">
				<ul class="list-group">
					<li class='list-group-item'>
	                    <div class="mr-3">
							<p class="">첨부파일</p>
						</div>
						<div class="mr-3" id="existedFileList">
						</div>
	                </li>
				</ul>
			</div>
			<!-- /.card-body -->
			<div class="mb-2 d-flex justify-content-end">
				<sec:authorize access="isAuthenticated()">
					<c:if test="${userinfo.username eq post.writer}">
						<button type="button" id="modifyBtn" class="btn btn-success mr-2">수정</button>
						<button type="button" id="deleteBtn" class="btn btn-danger mr-2">삭제</button>
					</c:if>
				</sec:authorize>
				<button type="button" id="listBtn" class="btn btn-primary mr-2">게시판</button>
			</div>
		</div>
	</div>
</section>

<section class="container pt-3">
	<div class="col-lg-12">
		<!-- /.card -->
		<div class="card">
			<div class="card-body">
				<h5 class="pt-3 pl-3">댓글</h5>
			</div>
			<div class="card-body">
				<textarea class="form-control" rows="5" placeholder="댓글을 작성해주세요."></textarea>
				<div class="mt-2">
					<button id="addReplyBtn" class="btn btn-success">댓글 등록하기</button>
					<div class="float-right">
						<span>0</span><span> / 2000</span>
					</div>
					
				</div>
			</div>
			<!-- /.card-heading -->
			<div class="card-body">
				<ul id="reply-list" class="list-group">
					<li class='list-group-item'>
                        <div class='header'>
                            <strong>댓글 작성자</strong>
                            <small class='ml-4 text-muted'>댓글 작성 날짜</small>
                            <div class='btn-group btn-group-sm float-right'>
                                <button class='btn btn-default' name='sentence-modify' data-sno='1'>수정하기</button> 
                                <button class='btn btn-default' name='sentence-delete' data-sno='1'>삭제하기</button>
                            </div>
                        </div>
                        <p class='mt-2'>댓글</p>
	                </li>
				</ul>
			</div>
			<!-- /.card-body -->
		</div>
	</div>
</section>

<form id='page-form' action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${criteria.pageNum }">
	<input type="hidden" name="amount" value="${criteria.amount }">	
</form>

<script src="/resources/bootstrap-4.0.0-dist/js/reply.js"></script>
<script src="/resources/bootstrap-4.0.0-dist/js/util.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var header = "${_csrf.headerName}";
	var token = "${_csrf.token}";
	
	var pageForm = $("#page-form");
	var deleteBtn = $("#deleteBtn");
	var modifyBtn = $("#modifyBtn");
	var listBtn = $("#listBtn");
	var addReplyBtn = $("#addReplyBtn");

	var replyList = $("#reply-list");
	
	var board_id = '<c:out value="${post.board_id}"/>';
	
	showReplies(1);
	
	listBtn.click(function(){
		pageForm.submit();
	});
	
	modifyBtn.on("click", function(){
		pageForm.attr("action", "/board/modify/" + board_id);
		pageForm.submit();
	});
	
	deleteBtn.on("click", function(){
		if(confirm("글을 지우시겠습니까?")){
			pageForm.attr("action", "/board/delete/" + board_id);
			pageForm.attr("method", "post");
			pageForm.append("<input type='hidden' name='${_csrf.parameterName }' value='${_csrf.token }'>");
			pageForm.submit();
		}
	})

	addReplyBtn.on("click", function(){
		console.log("clicked");
		// replyService.createReply(param, function(response){
		// 	console.log(response);
		// });
	});
	
	function showReplies(page) {
		replyService.getReplies({board_id : board_id, page : page}, function(replies){
			var str = "";
			if (replies == null || replies.length == 0){
				replyList.html("");
				return;
			}
			
			for(var i = 0; i < replies.length; i++) {
				str += "<li class='list-group-item'><div><strong>" + replies[i].replyer + "</strong>";
				str += "<small class='ml-4 text-muted'>" + util.displayTime(replies[i].createddate) + "</small>";
				str += "<div class='btn-group btn-group-sm float-right'>";
				str += "<button class='btn btn-default' name='sentence-modify' data-sno='1'>수정하기</button>";
				str += "<button class='btn btn-default' name='sentence-delete' data-sno='1'>삭제하기</button></div>";
				str += "</div><p class='mt-2'>" + replies[i].reply + "</p></li>";
			}
			
			replyList.html(str);
		})
	}

	
});
</script>
<script>
var existedFileList = $("#existedFileList");
var board_id = '<c:out value="${post.board_id}"/>';

$(document).ready(function(){
	(function(){
		$.getJSON("/files/" + board_id, function(data){
			str = "";
			var filePath;
			
			for (var i = 0; i < data.length; i++) {
				filePath = encodeURIComponent(data[i].uploadPath + "/" + data[i].uuid + "_" + data[i].fileName);
				str += "<div><a href='/file/download?fileName=" + filePath + "'>" + data[i].fileName + "</a></div>";
			}
			existedFileList.html(str);		
		});
	})();
});
</script>
<%@ include file="../includes/footer.jsp"%>