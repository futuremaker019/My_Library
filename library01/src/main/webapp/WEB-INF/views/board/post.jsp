<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp"%>
<sec:authentication property="principal" var="userinfo"/>

<div class="container">
	<div class="row border-bottom">
		<div class="col mb-3">
			<h3><c:out value="${post.title}"/></h3>
				<div class="mt-2">
					<span><c:out value="${post.writer}"/></span>
					<span class="float-right ml-5">조회수, 공감</span>
				</div>
			<div class="mt-2"><c:out value="${post.createdDate }"/></div>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<c:out value="${post.content }" escapeXml="false"/>
		</div>
	</div>
	<div class="row border-top pt-3" >
		<div class="mr-3">
			<p class="">첨부파일</p>
		</div>
		<div class="mr-3" id="existedFileList">
		</div>
	</div>
	<div class="w-100"></div>
	<div class="row border-top justify-content-end">
		<div class="mt-3">
			<sec:authorize access="isAuthenticated()">
				<c:if test="${userinfo.username eq post.writer}">
					<button type="button" id="modifyBtn" class="btn btn-success">수정</button>
				</c:if>
			</sec:authorize>
			<button type="button" id="listBtn" class="btn btn-primary">게시판</button>
		</div>
	</div>
</div>

<form id='page-form' action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${criteria.pageNum }">
	<input type="hidden" name="amount" value="${criteria.amount }">	
</form>

<script type="text/javascript">
$(document).ready(function(){
	var header = "${_csrf.headerName}";
	var token = "${_csrf.token}";
	
	var pageForm = $("#page-form");
	var board_id = '<c:out value="${post.board_id}"/>';
	
	$("#listBtn").click(function(){
		pageForm.submit();
	});
	
	$("#modifyBtn").on("click", function(e){
		pageForm.attr("action", "/board/modify/" + board_id);
		pageForm.submit();
	});
	/* $("#existedFileList a").on("click", function(e){
		e.preventDefault();
		
		var fileId = $(this).data("fileid");
		var fileName = $(this).data("filename");
		var uploadPath = $(this).data("uploadpath");
		var uuid = $(this).data("uuid");
		
		console.log(fileId);
		console.log(fileName);
		console.log(uploadPath);
		console.log(uuid);
		
		fileName = uploadPath + "\\" + uuid + "_" + fileName;
		console.log("fileName : " + fileName);
		
		$(document).ajaxSend(function (e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
		
		$.ajax({
			url: '/file/download',
			type: 'GET',
			data: {fileName: fileName},
			success: function(result) {
				console.log(result);
				alert(result);
			},
			error:function(request,status,error){
	            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		})
	}); */
});
</script>
<script>
var existedFileList = $("#existedFileList");
var board_id = '<c:out value="${post.board_id}"/>';

$(document).ready(function(){
	(function(){
		$.getJSON("/files/" + board_id, function(data){
			console.log(data);
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