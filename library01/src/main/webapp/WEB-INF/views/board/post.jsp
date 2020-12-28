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
			<div class="card-body" id="reply-input">
				<textarea class="form-control" rows="5" placeholder="댓글을 작성해주세요." id="reply"></textarea>
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

	var replyInput = $("#reply-input");
	var replyList = $("#reply-list");
	var replyContent = $("#reply");
	
	var board_id = '<c:out value="${post.board_id}"/>';
	var loginUserId = "${authentication.principal.member.userId}";
	
	showReplies(1);
	
	listBtn.click(function(){
		pageForm.submit();
	});
	
	modifyBtn.on("click", function(){
		pageForm.attr("action", "/board/modify/" + board_id);
		pageForm.submit();
	});
	
	deleteBtn.on("click", function(){
		if(confirm("글을 삭제하시겠습니까?")){
			pageForm.attr("action", "/board/delete/" + board_id);
			pageForm.attr("method", "post");
			pageForm.append("<input type='hidden' name='${_csrf.parameterName }' value='${_csrf.token }'>");
			pageForm.submit();
		}
	})

	addReplyBtn.on("click", function(){
		if(!replyContent.val()){
			alert("댓글을 작성해주세요.");
			return;
		}
		
		if(loginUserId == ""){
			if(confirm("로그인 후 댓글을 남길수 있습니다. 로그인 하시겠습니까?")){
				location.href = "/member/login";
			}
			replyContent.val("");
			return;
		}

		let param = {
			board_id : board_id,
			reply : replyContent.val()
		}

		replyService.createReply(param, header, token, function(reply){
			let str = "";
			
			str += "<li class='list-group-item'><div><strong>" + reply.replier + "</strong>";
			str += "<small class='ml-4 text-muted'>" + util.displayTime(reply.updateddate) + "</small>";
			str += "<div class='btn-group btn-group-sm float-right'>";
			str += "<button class='btn btn-default' id='replyActiveModifyBtn' data-replyid='"+reply.reply_id+"' "
				+ "data-reply='" + reply.reply + "' data-replier='"+reply.replier+"' >수정하기</button>";
			str += "<button class='btn btn-default' id='replyDeleteBtn' data-replyid='"+reply.reply_id+"' data-replier='"+reply.replier+"'>삭제하기</button></div>";
			str += "</div><p class='mt-2'>" + reply.reply + "</p></li>";
			
			replyList.append(str);
			replyContent.val("");
		});
	});
	
	replyList.on("click", "#replyDeleteBtn", function(){
		let reply_id = $(this).data("replyid");
		let replier = $(this).data("replier");
		let targetli = $(this).closest("li");
		
		if(loginUserId != replier) {
			alert("해당 댓글은 삭제할 수 없습니다.");
			return;
		}
		
		if(confirm("댓글을 삭제하시겠습니까?")){
			replyService.deleteReply(reply_id, header, token, function(result){
				targetli.remove();
			});	
		}
	})
	
	replyList.on("click", "#replyActiveModifyBtn", function(){
		let reply_id = $(this).data("replyid");
		let replier = $(this).data("replier");
		let reply = $(this).data("reply");
		let targetLi = $(this).closest("li");
		
		if(loginUserId != replier) {
			alert("해당 댓글은 수정할 수 없습니다.");
			return;
		}
		
		let str ="";
		
 		str += "<textarea class='form-control' rows='3' id='modify-reply'>" + reply + "</textarea>";
		str += "<div class='mt-2'>";
		str += "<button id='replyModifyBtn' class='btn btn-info' data-replyid='" + reply_id + "'>수정완료</button>";
		str += "<button id='replyModifyCancelBtn' class='btn btn-default'>취소</button>";
		str += "<div class='float-right'><span>0</span><span> / 2000</span></div></div>";
		
		targetLi.html(str);
	})
	
	replyList.on("click", "#replyModifyBtn", function() {
		let reply_id = $(this).data("replyid");
		let modifyReply = replyList.find("#modify-reply").val();
		let targetLi = $(this).closest("li");
		
		let param = {
			reply_id : reply_id,
			reply : modifyReply
		}
		
		replyService.modifyReply(param, header, token, function(reply) {
			showReplies(1);
		});
	});
	
	replyList.on("click", "#replyModifyCancelBtn", function(modifiedReply){
		showReplies(1);
	});
	
	function showReplies(page) {
		let param = {
			board_id : board_id,
			page : page
		}
		
		replyService.getReplies(param, function(replies){
			let str = "";
			if (replies == null || replies.length == 0){
				replyList.html("");
				return;
			}
			
			for(let i = 0; i < replies.length; i++) {
				str += "<li class='list-group-item'><div><strong>" + replies[i].replier + "</strong>";
				str += "<small class='ml-4 text-muted'>" + util.displayTime(replies[i].updateddate) + "</small>";
				str += "<div class='btn-group btn-group-sm float-right'>";
				str += "<button class='btn btn-default' id='replyActiveModifyBtn' data-replyid='"+replies[i].reply_id+"' " 
					+ "data-reply='"+replies[i].reply+"' data-replier='"+replies[i].replier+"'>수정하기</button>";
				str += "<button class='btn btn-default' id='replyDeleteBtn' data-replyid='"+replies[i].reply_id+"' "
					+ "data-replier='"+replies[i].replier+"'>삭제하기</button></div>";
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