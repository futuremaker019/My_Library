<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>MyLibrary</title>
  
  <!-- BootStrap config -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  
  <!-- slide css -->
  <link rel="stylesheet" type="text/css" href="/resources/slick/slick.css"/>
  <!-- Add the new slick-theme.css if you want the default styling -->
  <link rel="stylesheet" type="text/css" href="/resources/slick/slick-theme.css"/>	

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:100,400|Ubuntu&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="/resources/css/styles.css">

  <!-- Font Awesome -->
  <script src="https://kit.fontawesome.com/c0be56fbbf.js" crossorigin="anonymous"></script>
  
  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.1.0.js" integrity="sha256-slogkvB1K3VOkzAI8QITxV3VzpOnkeNVsKvtkYLMjfk=" crossorigin="anonymous"></script>
  
  <!-- summernote config -->
  <script src="/resources/summernote-0.8.18-dist/summernote-lite.js"></script>
  <link rel="stylesheet" href="/resources/summernote-0.8.18-dist/summernote-lite.css">
  
</head>

<body>
	 <section id="title">
		<div class="container">
			<!-- Nav Bar -->
			<nav class="navbar navbar-expand-lg navbar-dark pb-1 pt-0">
				<a class="navbar-brand pt-4" href="/">My Library</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarToggler"
					aria-controls="navbarToggler" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse pt-4" id="navbarToggler">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="/books">내 서재</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="/books/search">책 검색</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="/board">게시판</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="/member/info">내 정보</a>
						</li>
					</ul>
				</div>
			</nav>
			<div class="bg-dark">
               <ul class="list-inline text-right">
               	<sec:authorize access="isAuthenticated()">
                    <li class="list-inline-item">
                    	<span class="text-right">안녕하세요. 
                    		<sec:authentication property="principal.username"/> 님. 
                   		</span>
                   	</li>
                    <li class="list-inline-item">
                    	<form action="/member/logout" method="post">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
							<button class="btn btn-link" id="logoutBtn" style="color:white;">로그아웃</button>
						</form>
                   	</li>
                   </sec:authorize>
                   <sec:authorize access="isAnonymous()">
                  		<li class="list-inline-item">
                  			<button class="btn btn-link" onclick="location.href='/member/login'" style="color:white;">로그인</button>
              			</li>
              			<li class="list-inline-item">
                  			<button class="btn btn-link" onclick="location.href='/member/signup'" style="color:white;">회원가입</button>
              			</li>
               	   </sec:authorize>
               </ul>
           </div>
		</div>
	</section>
