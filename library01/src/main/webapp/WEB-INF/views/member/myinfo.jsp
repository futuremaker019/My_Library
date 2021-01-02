<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<div class="container mt-5">
	<div class="form-row d-flex">
		<div class="form-group col-md-6 pl-5 pr-5">
			<h5>내 서재 현황</h5>
			<ul class="list-group list-group-flush border-top">
			  <li class="list-group-item d-flex justify-content-between">내서제 책 총 수
			  	<span class="badge badge-light badge-pill">14</span>
			  </li>
			  <li class="list-group-item d-flex justify-content-between">서평 작성 총 수
			  	<span class="badge badge-light badge-pill">14</span>
			  </li>
			  <li class="list-group-item d-flex justify-content-between">문장 수집 작성 총 수
			  	<span class="badge badge-light badge-pill">14</span>
			  </li>
			  <li class="list-group-item d-flex justify-content-between">즐겨찾기 목록의 책 수
			  	<span class="badge badge-light badge-pill">14</span>
			  </li>
			  <li class="list-group-item">Vestibulum at eros</li>
			</ul>
		</div>
		<div class="form-group col-md-6 pl-5 pr-5">
			<h5>게시판 현황</h5>
			<ul class="list-group list-group-flush border-top">
			  <li class="list-group-item d-flex justify-content-between">게시판 글 작성 갯수
			  	<span class="badge badge-light badge-pill">14</span>
			  </li>
			  <li class="list-group-item d-flex justify-content-between">댓글 착성 갯수
			  	<span class="badge badge-light badge-pill">14</span>
			  </li>
			  <li class="list-group-item">Morbi leo risus</li>
			  <li class="list-group-item">Porta ac consectetur ac</li>
			  <li class="list-group-item">Vestibulum at eros</li>
			</ul>
		</div>
	</div>
</div>

<div class="container mt-5 pt-4 border-top">
	<div class="form-row d-flex justify-content-left">
		<div class="col-auto">
			<p class="mt-2" >패스워드 변경</p>
	    </div>
	    <div class="col-auto">
			<input type="password" class="form-control mb-2" id="password-existed" placeholder="기존 패스워드">
			<span id="password-existed-msg" >패스워드 입력</span>
	    </div>
	    <div class="col-auto">
	      <button type="button" class="btn btn-primary mb-2" id="password-existed-check-btn">패스워드 확인</button>
	    </div>
	    <div class="col-auto">
			<input type="password" class="form-control mb-2" id="password-change" placeholder="변경 패스워드">
			<span id="password-change-msg">10-20자, 영문, 숫자, 문자조합</span>
	    </div>
	    <div class="col-auto">
			<input type="password" class="form-control mb-2" id="password-verification" placeholder="변경 패스워드 확인">
			<span id="password-verification-msg">패스워드 확인</span>
	    </div>
	    <div class="col-auto">
	      	<button type="button" class="btn btn-warning mb-2 d-block" id="password-change-btn">변경 완료</button>
	      	<span id="password-change-btn-msg"></span>
	    </div>
  	</div>
  	<div class="form-row d-flex justify-content-left mt-3 mb-4 pt-4 pb-3 border-top">
		<div class="col-auto">
			<p class="mt-2 mr-3">이메일 변경</p>
	    </div>
	    <div class="col-auto">
			<input type="email" class="form-control-plaintext mb-2" id="email" value='<c:out value="${member.email }"/>' style="width:380px" readonly>
	    </div>
	    <div class="col-auto">
			<input type="email" class="form-control mb-2" id="email-change" placeholder="이메일 " style="width:380px">
			<span id="email-change-msg">이메일을 입력하세요.</span>
	    </div>
	    <div class="col-auto">
	      	<button type="button" class="btn btn-primary mb-2" id="email-existed-check-btn">중복확인</button>
	    </div>
	    <div class="col-auto">
	      	<button type="button" class="btn btn-warning mb-2 d-block" id="email-change-btn">이메일 변경</button>
	      	<span id="email-change-btn-msg"></span>
	    </div>
  	</div>
</div>

<script src="/resources/bootstrap-4.0.0-dist/js/member.js"></script>
<script>
$(document).ready(function(){
	var header = "${_csrf.headerName}";
	var token = "${_csrf.token}";
	
	var password_existed = $("#password-existed");
	
	var password_existed_msg = $("#password-existed-msg");
	var password_change_msg = $("#password-change-msg");
	var password_verification_msg = $("#password-verification-msg");
	var password_change_btn_msg = $("#password-change-btn-msg");
	
	var email_change_msg = $("#email-change-msg");
	var email_change_btn_msg = $("#email-change-btn-msg");
	
	var isPasswordVerified = false;
	var isEmailVerified = false;
	
	var password_format = /^(?=.*[a-zA-Z])(?=.*[!@#$%^~*+=-])(?=.*[0-9]).{10,20}$/;
	var email_format = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	$("#password-existed-check-btn").click(function(){
		if(!$("#password-existed").val()){
			password_existed_msg.text("패스워드를 입력해주세요.").css("color", "red");
			$("#password-existed").focus();
			return false;
		}
		
		var password = {'password' : $("#password-existed").val()};
		
		memberService.verifyPassword(password, header, token, function(data){
			if(!data) {
				password_existed_msg.text("패스워드를 확인해주세요.").css("color", "red");
				return false;
			}
			password_existed_msg.text("일치").css("color", "green");
			isPasswordVerified = true;
		});
	});
	
	$("#password-change-btn").click(function(e){
		if(!$("#password-existed").val()){
			password_existed_msg.text("패스워드를 입력해주세요.").css("color", "red");
			$("#password-existed").focus();
			return false;
		}
		if(!password_format.test($("#password-change").val())){
			password_change_msg.text("10-20자, 영문, 숫자, 문자조합 필요").css("color", "red");
			$("#password-change").focus();
			return false;
		}
		
		if(!$("#password-verification").val()){
			password_verification_msg.text("패스워드를 확인해주세요.").css("color", "red");
			$("#password-verification").focus();
			return false;
		}
		
		if($("#password-change").val() != $("#password-verification").val()){
			password_verification_msg.text("패스워드가 일치하지 않습니다.").css("color", "red");
            $("#password-change").focus();
            return false;
        }
		
		if(!isPasswordVerified){
            alert("패스워드 중복체크를 해주세요.");
            return false;
        }
		
		var changedPassword = {'changedPassword' : $("#password-change").val()};
		
		memberService.changePassword(changedPassword, header, token, function(data){
			if(data){
				password_change_msg.text("10-20자, 영문, 숫자, 문자조합").css("color", "black");
				password_verification_msg.text("패스워드 확인").css("color", "black");
				password_change_btn_msg.text("변경 완료").css("color", "green");
			}
		});
	});
	
	$("#email-existed-check-btn").click(function(){
		if(!$("#email-change").val()){
			email_change_msg.text("이메일을 입력해주세요.").css("color", "red");
			$("#email-change").focus();
			return false;
		}
		if(!email_format.test($("#email-change").val())){
			email_change_msg.text("이메일 형식에 맞지 않습니다.").css("color", "red");
			$("#email-change").focus();
			return false;
		} 
		
		var email = {'email' : $("#email-change").val()}
		
		memberService.verifyEmail(email, header, token, function(data){
			if(data) {
				email_change_msg.text("이메일이 존재합니다.").css("color", "red");
				return false;
			}
			email_change_msg.text("사용가능한 이메일입니다.").css("color", "green");
			isEmailVerified = true;
		});
	});
	
	$("#email-change-btn").click(function(){
		if(!$("#email-change").val()){
			email_change_msg.text("이메일을 입력해주세요.").css("color", "red");
			$("#email-change").focus();
			return false;
		}
		
		if(!isEmailVerified){
            alert("이메일 중복체크를 해주세요.");
            return false;
        }
		
		var changedEmail = {'changedEmail' : $("#email-change").val()};
		
		memberService.changeEmail(changedEmail, header, token, function(data){
			if(data) {
				email_change_msg.text("이메일을 입력하세요.").css("color", "black");
				email_change_btn_msg.text("변경 완료").css("color", "green");
			}
		});
	});
});
</script>

<%@ include file="../includes/footer.jsp"%>