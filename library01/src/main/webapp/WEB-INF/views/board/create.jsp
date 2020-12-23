<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div>
	<div class="border-bottom" style="margin: 20px 0;">
		<h4>게시판 글쓰기</h4>
	</div>
		<form id="f1" action="/board/posting" method="post">
			<div class="form-group">
				<input type="text" id="titleInput" class="form-control" name="title" placeholder="글 제목을 작성해주세요.">
			</div> 
			<div>
				<textarea id="summernote" name="content"></textarea>
			</div>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
		<div class="form-group">
			<input type="file" class="form-control" name="uploadFile" id="upload" multiple>
			<button type="button" id="submitBtn" class="btn btn-primary">글 등록</button>
		</div>
		<div id=fileList>
		</div>
		<div class="d-flex justify-content-end mt-3">
			
		</div>
	</div>
</div>

<script>
var header = "${_csrf.headerName}";
var token = "${_csrf.token}";

var f1 = $("#f1");

var filesTempArr = [];

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
            f1.append(str).submit();
        },
        err: function (err) {
            alert(err.status);
        }
    });
}

$(document).ready(function() {
    $("#upload").on("change", addFiles);
	
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
            f1.submit();
        }
    });
    
	$('#summernote').summernote({
	    placeholder: '내용을 작성해주세요.',
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
	    ],
	    /* callbacks: {
            onImageUpload: function (files, editor, welEditable) {
            	console.log(files[0]);
            	sendFile(files[0], this);
            	
                for (var i = 0; i < files.length; i++) {
                   	sendFile(files[i], this);
                
            }
        } */
	});
	
	function sendFile(file, el) {
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        var form_data = new FormData();
        form_data.append('file', file);

        $.ajax({
            data: form_data,
            type: "POST",
            url: '/image',
            cache: false,
            enctype: 'multipart/form-data',
            contentType: false,
            processData: false,
            success: function (url) {
            	console.log(url);
                $(el).summernote('editor.insertImage', url);
                $('#imageBoard > ul').append('<li><img src="'+url+'" width="480" height="auto"/></li>');
            }
        });
    }
});
</script>

<%@ include file="../includes/footer.jsp"%>