<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<div class="container">
	<div class="">
		<div class="">
			<h3><c:out value="${post.title}"/></h3>
			<div><span><c:out value="${post.writer}"/></span></div>
			<div><span>조회수, 공감</span></div>
		</div>		
		<div class="">
			<div>
				<p><c:out value="${post.content }"/></p>
			</div>			
		</div>
		<div class="form-group">
			<button type="button" class="btn btn-success">Modify</button>
			<button type="button" class="btn btn-primary">List</button>
		</div>
		<div class="">
		
		</div>
	</div>
</div>

<script type="text/javascript">

</script>

<%@ include file="../includes/footer.jsp"%>