<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div>
		<div>
			<div class="form-group">
				<p>타이틀이 들어갈곳</p>
			</div>
			<div class="form-group">
				
			</div>
			<div>
				<textarea id="summernote"></textarea>
			</div>
		</div>
	</div>
</div>
<script>
	$('#summernote').summernote({
	    placeholder: '내용을 작성해주세요.',
	    height: 200,
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
	      ['view', ['fullscreen', 'codeview', 'help']]
	    ]
	  });
</script>

<%@ include file="../includes/footer.jsp"%>