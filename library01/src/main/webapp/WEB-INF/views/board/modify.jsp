<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div>
		<div class="border-bottom" style="margin: 20px 0;">
			<h4>게시판 글 수정</h4>
		</div>
		<form id="form" action='/board/modify/${post.board_id }' method="post">
			<div class="form-group">
				<input type="text" id="titleInput" class="form-control" name="title" value="${post.title }">
			</div> 
			<div>
				<textarea id="summernote" name="content"><c:out value="${post.content }"/></textarea>
			</div>
			<input type="hidden" name="pageNum" value="${criteria.pageNum }">
			<input type="hidden" name="amount" value="${criteria.amount }">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
		<div class="form-group">
			<input type="file" class="form-control" name="uploadFile" id="upload" multiple>
		</div>
		<div class="border-bottom" id="existedFileList">
			<c:forEach items="${attachments }" var="attachment">
				<div>
					<c:out value="${attachment.fileName }"/>
					<a href="#"
						data-fileid="${attachment.id }" 
						data-filename="${attachment.fileName }" 
						data-uploadpath="${attachment.uploadPath }"
						data-uuid="${attachment.uuid }">
						[삭제]	
					</a>
				</div>
			</c:forEach>
			<div id="fileList">
			</div>
		</div>
		<div class="mt-3 mb-5 d-flex justify-content-end">
			<button type="button" id="submitBtn" class="btn btn-warning">수정 완료</button>
			<button type="button" id="listBtn" class="btn btn-primary ml-2">리스트로 돌아가기</button>
		</div>
	</div>
</div>
<form id='page-form' action="/board" method="get">
	<input type="hidden" name="pageNum" value="${criteria.pageNum }">
	<input type="hidden" name="amount" value="${criteria.amount }">	
</form>

<script>
var header = "${_csrf.headerName}";
var token = "${_csrf.token}";

var form = $("#form");
var pageForm = $("#page-form");

var filesTempArr = [];

//파일 추가
function addFiles(e) {
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);
    
    var filesArrLen = filesArr.length;
    var filesTempArrLen = filesTempArr.length;

    for (var i = 0; i < filesArrLen; i++) {
        filesTempArr.push(filesArr[i]);
        $("#fileList").append("<div>" + filesArr[i].name + "<a href='#' onclick=\"deleteFile(event, " + (filesTempArrLen + i) + ");\">[삭제]</a></div>");
    }
    $(this).val('');
}

function deleteFile(eventParam, orderParam) {
    eventParam.preventDefault();
    filesTempArr.splice(orderParam, 1);
    var innerHtmlTemp = "";
    var filesTempArrLen = filesTempArr.length;
    for (var i = 0; i < filesTempArrLen; i++) {
        innerHtmlTemp += "<div>" + filesTempArr[i].name + "<a href='#' onclick=\"deleteFile(event, " + i + ");\">[삭제]</a></div>"
    }
    $("#fileList").html(innerHtmlTemp);
}

// 파일 업로드
function submitWithUploadFiles() {
    var formData = new FormData();
    for (var i = 0, filesTempArrLen = filesTempArr.length; i < filesTempArrLen; i++) {
        formData.append("uploadFile", filesTempArr[i]);
    }
    
    var str = "";
    
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $.ajax({
        type: "POST",
        url: "/upload",
        data: formData,
        processData: false,
        contentType: false,
        dataType:'json',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                str += "<input type='hidden' name='attachments[" + i + "].fileName' value='" + data[i].fileName + "'>";
                str += "<input type='hidden' name='attachments[" + i + "].uuid' value='" + data[i].uuid + "'>";
                str += "<input type='hidden' name='attachments[" + i + "].uploadPath' value='" + data[i].uploadPath + "'>";
            }
            form.append(str).submit();
        },
        err: function (err) {
            alert(err.status);
        }
    });
}

$(document).ready(function(){
	$("#upload").on("change", addFiles);
	
	$("#existedFileList a").on("click", function(e){
		e.preventDefault();
		
		var targetDiv = $(this).closest("div");
		
		
		var fileId = $(this).data("fileid");
		var fileName = $(this).data("filename");
		var uploadPath = $(this).data("uploadpath");
		var uuid = $(this).data("uuid");
		
		fileName = uploadPath + "\\" + uuid + "_" + fileName;
		
		$(document).ajaxSend(function (e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
		
		$.ajax({
			url: '/file/delete',
			type: 'POST',
			data: {fileName: fileName, id:fileId},
			success: function(result) {
				console.log(result);
				targetDiv.remove();
			},
			error:function(request,status,error){
	            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		})
	});
	
	$("#submitBtn").on("click", function (e) {
        e.preventDefault();

        if (!$("#titleInput").val()) {
            alert("제목을 입력해주세요.");
            $("#titleInput").focus();
            return false;
        }
        
        if (!$("#summernote").val()) {
            alert("본문을 입력해주세요.");
            $("#summernote").focus();
            return false;
        }
        if (filesTempArr.length > 0) {
        	submitWithUploadFiles();
        } else {
        	form.submit();
        }
    });
	
	$('#summernote').summernote({
	    height: 400,
	    minHeight: null,
	    maxHeight: null,
	    focus: true,
	    toolbar: [
	      ['style', ['style']],
	      ['font', ['bold', 'underline', 'clear']],
	      ['color', ['color']],
	      ['para', ['ul', 'ol', 'paragraph']],
	      ['table', ['table']],
	      ['insert', ['link', 'picture', 'video']],
	      ['view', ['codeview', 'help']]
	    ]
	});
	
	$("#listBtn").click(function(){
		pageForm.submit();
	});
});
</script>

<%@ include file="../includes/footer.jsp"%>