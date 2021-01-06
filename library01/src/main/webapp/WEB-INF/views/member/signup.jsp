<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<section class="container">
    <div class="d-flex justify-content-center mt-5">
    	<div class="col-lg-6">
	        <form role="form" action="/member/signup" method="post">
	            <div class="pt-4 pb-5">
	            	<div class="">
	            		<label for="userid">ID</label>
	                	<div class="input-group mb-3">
						  	<input type="text" class="form-control" placeholder="ID" id="userid" name="userId">
						  	<div class="input-group-append">
						    	<button class="btn btn-outline-secondary" type="button" id="id-duplication-check-btn">중복 확인</button>
						  	</div>
						</div>
						<p class="d-flex justify-content-end" id="id-msg">ID를 입력하세요.</p>
	            	</div>
	                <div class="form-group mb-3">
	                    <label for="password">패스워드</label>
	                    <input type="password" class="form-control" id="password" name="password" placeholder="패스워드">
	                    <P id="password-msg" class="mt-2 d-flex justify-content-end" >10자이상 20자이하 영문, 숫자, 특수문자 혼합</P>
	                </div>
	                <div class="form-group">
	                    <label for="password-varification">패스워드 확인</label>
	                    <input type="password" class="form-control" id="password-verification" placeholder="패스워드 확인">
	                    <P id="password-verification-msg" class="mt-2 d-flex justify-content-end">패스워드를 확인하세요.</P>
	                </div>
	                <div class="border-bottom mb-4">
	            		<label for="email">Email</label>
	                	<div class="input-group mb-3">
						  	<input type="text" class="form-control" id="email" name="email" placeholder="email">
						  	<div class="input-group-append">
						    	<button class="btn btn-outline-secondary" type="button" id="email-send-verificationCode-btn">중복확인</button>
						  	</div>
						</div>
						<p class="d-flex justify-content-end" id="email-msg">이메일을 입력하세요.</p>
						
						<!-- 이메일 인증 추가 요망 -->
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

<script src="/resources/bootstrap-4.0.0-dist/js/member.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var header = "${_csrf.headerName}";
		var token = "${_csrf.token}";
		
		var isIdVerified = false;
		var isEmailVerified = false;
		
		var userId = $("#userid");
		var email = $("#email");
		var password = $("#password");
		var password_verification = $("#password-verification");
		
		var id_msg = $("#id-msg");
		var email_msg = $("#email-msg");
		var password_msg = $("#password-msg");
		var password_verification_msg = $("#password-verification-msg");
		
		var id_format = /^(?=.*[a-z])(?=.*[0-9]).{5,15}$/;
		var password_format = /^(?=.*[a-zA-Z])(?=.*[!@#$%^~*+=-])(?=.*[0-9]).{10,20}$/;
		var email_format = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		
		userId.focus();
		
		$("#signupBtn").click(function(e){
			if(!$("#userid").val()){
				id_msg.text("아이디를 입력해주세요.").css("color", "red");
				$("#userid").focus();
				return false;
			}
			if(!id_format.test($("#userid").val())){
				id_msg.text("ID는 5자이상 15자 이하 영문소문자, 숫자로 혼합해야 합니다.").css("color", "red");
				$("#userid").focus();
				return false;
			} 
			
			if(!$("#password").val()){
				password_msg.text("패스워드를 입력해주세요.").css("color", "red");
				$("#password").focus();
				return false;
			}
			if(!password_format.test($("#password").val())){
				password_msg.text("비밀번호는 10자이상 20자이하, 영문, 숫자, 특수문자를 혼합해야 합니다.").css("color", "red");
				$("#password").focus();
				return false;
			}
			
			if(!$("#password-verification").val()){
				password_verification_msg.text("패스워드를 확인해주세요.").css("color", "red");
				$("#password-verification").focus();
				return false;
			}
			
			if($("#password").val() != $("#password-verification").val()){
				password_verification_msg.text("패스워드를 확인해주세요.").css("color", "red");
                $("#password-verification").focus();
                return false;
            }
			
			if(!$("#email").val()){
				email_msg.text("이메일을 입력해주세요.").css("color", "red");
				$("#email").focus();
				return false;
			}
			if(!email_format.test($("#email").val())){
				email_msg.text("이메일 형식에 맞지 않습니다.").css("color", "red");
				$("#email").focus();
				return false;
			}
			
			if(!isIdVerified){
                alert("아이디 중복체크를 해주세요.");
                return false;
            }
			if(!isEmailVerified){
                alert("이메일 중복체크를 해주세요.");
                return false;
            }
			
			$("form").submit();
		});
		
		$("#id-duplication-check-btn").click(function(){
			if(!$("#userid").val()){
				id_msg.text("아이디를 입력해주세요.").css("color", "red");
				userId.focus();
				return false;
			}	
			if(!id_format.test($("#userid").val())){
				id_msg.text("ID는 5자이상 15자 이하 영문소문자, 숫자로 혼합해야 합니다.").css("color", "red");
				userId.focus();
				return false;
			} 
			
			var userId = { 'userId':  $("#userid").val() };
			
			memberService.verifyUserId(userId, header, token, function(data){
                if(data) {
                	id_msg.text("아이디가 사용중입니다.").css("color", "red");
                	return false;
                } else {
                	id_msg.text("사용가능한 아이디입니다.").css("color", "green");
                    isIdVerified = true;	
                }
			});
		})
		
		$("#email-send-verificationCode-btn").click(function(){
			if(!$("#email").val()){
				email_msg.text("이메일을 입력해주세요.").css("color", "red");
				email.focus();
				return false;
			}
			if(!email_format.test($("#email").val())){
				email_msg.text("이메일 형식에 맞지 않습니다.").css("color", "red");
				email.focus();
				return false;
			}
			
			var email = { 'email': $("#email").val() };
			
			memberService.verifyEmail(email, header, token, function(data){
                if(data) {
                	email_msg.text("이메일이 사용중입니다.").css("color", "red");
                	return false;
                } else {
                	email_msg.text("사용가능한 이메일입니다.").css("color", "green");
                    isEmailVerified = true;	
                }
			});
		});
		
		password.on("keyup", function(){
            if (password_format.test(password.val())) {
                password_msg.text("적합").css("color", "green");
            } else {
            	password_msg.text("부적합").css("color", "red");
            }
        });

        password_verification.on("change", function(){
            if ($("#password").val() != $("#password-verification").val()) {
                password_verification_msg.text("불일치").css("color", "red");
            } else {
                password_verification_msg.text("일치").css("color", "green");
            }
        });
	});
</script>

<%@ include file="../includes/footer.jsp"%>