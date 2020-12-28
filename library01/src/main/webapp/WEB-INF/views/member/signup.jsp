<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<section class="container">
    <div class="row justify-content-md-center pt-5">
    	<div class="col-lg-8">
	        <form role="form" action="/signup" method="post">
	            <div>
	                <div class="form-group">
	                    <label for="userid">ID</label>
	                    <input type="text" class="form-control" id="userid" name="userId" placeholder="아이디">
	                </div>
	                <div class="form-group">
	                    <label for="password">Password</label>
	                    <input type="password" class="form-control" id="password" name="userPw" placeholder="패스워드">
	                </div>
	                <div class="form-group">
	                    <label for="email">Email</label>
	                    <input type="text" class="form-control" id="email" name="email" placeholder="email">
	                </div>
	                <button class="btn btn-success col-lg-12" id="signupBtn">회원가입</button>
	            </div>
	            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	         </form>
         </div>
    </div>
</section>

<script type="text/javascript">
	$(document).ready(function(){
		$("#signupBtn").on("click", function(e){
			$("form").submit();
		});
	});
</script>

<%@ include file="../includes/footer.jsp"%>