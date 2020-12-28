<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../includes/header.jsp"%>

<section class="container">
	<div class="row justify-content-md-center">
		<div class="col-lg-8">
	    <!-- <div class="justify-content-md-center pt-5"> -->
	        <form role="form" action="/login" method="post">
	            <div>
	            	<p><c:out value="${error }"/></p>
	                <div class="form-group">
	                    <label for="idInput">ID</label>
	                    <input type="text" class="form-control" id="idInput" name="username" placeholder="아이디">
	                </div>
	                <div class="form-group">
	                    <label for="passwordInput">Password</label>
	                    <input type="password" class="form-control" id="passwordInput" name="password" placeholder="패스워드">
	                </div>
	                <button type="button" class="btn btn-success col-lg-12" id="loginBtn">로그인</button>
	                <button type="button" class="btn btn-info col-lg-12 mt-3" id="signupBtn">회원가입</button>
	            </div>
	            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	         </form>
	    </div>
    </div>
</section>

<script type="text/javascript">
	$(document).ready(function(){
		$("#idInput").focus();
		
		$("#loginBtn").on("click", function(e){
			$("form").submit();
		});
		
		$("#signupBtn").on("click", function(){
			window.location.href = "/signup";			
		});
	});
</script>


<%@ include file="../includes/footer.jsp"%>