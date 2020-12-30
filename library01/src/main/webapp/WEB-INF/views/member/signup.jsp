<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<section class="container">
    <div class="d-flex justify-content-center">
    	<div class="col-lg-6">
	        <form role="form" action="/member/signup" method="post">
	            <div class="pt-4 pb-5">
	            	<div class="">
	            		<label for="userid">ID</label>
	                	<div class="input-group mb-3">
						  	<input type="text" class="form-control" placeholder="ID" id="userid" name="userId">
						  	<div class="input-group-append">
						    	<button class="btn btn-outline-secondary" type="button" id="id-check-duplication-btn">중복 확인</button>
						  	</div>
						</div>
						<p class="d-flex justify-content-end" id="id-msg">ID를 입력하세요.</p>
	            	</div>
	                <div class="form-group">
	                    <label for="password">패스워드</label>
	                    <input type="password" class="form-control" id="password" name="userPw" placeholder="패스워드">
	                    <P id="password-msg" class="mt-2 float-right" >패스워드를 입력하세요.</P>
	                </div>
	                <div class="form-group mb-5">
	                    <label for="password-varification">패스워드 확인</label>
	                    <input type="password" class="form-control" id="password-varification" placeholder="패스워드 확인">
	                    <P id="password-varification-msg" class="mt-2 float-right">패스워드를 확인하세요.</P>
	                </div>
	                <div class="border-bottom mb-4">
	            		<label for="email">Email</label>
	                	<div class="input-group mb-3">
						  	<input type="text" class="form-control" id="email" name="email" placeholder="email">
						  	<div class="input-group-append">
						    	<button class="btn btn-outline-secondary" type="button" id="email-send-verificationCode-btn">중복확인</button>
						  	</div>
						</div>
						<p class="d-flex justify-content-end" id="id-msg">이메일을 입력하세요.</p>
						<!-- <label for="email-verification">인증번호 확인</label>
	                	<div class="input-group mb-3">
						  	<input type="text" class="form-control" id="email-verification" placeholder="인증번호 입력">
						  	<div class="input-group-append">
						    	<button class="btn btn-outline-secondary" type="button" id="email-send-verificationCode-btn">인증하기</button>
						  	</div>
						</div>
						<p class="d-flex justify-content-end" id="email-verification-msg" style="margin-top:0;">인증번호를 입력하세요.</p> -->
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
		$("#userid").focus();
		
		$("#signupBtn").on("click", function(e){
			$("form").submit();
		});
	});
</script>

<%@ include file="../includes/footer.jsp"%>