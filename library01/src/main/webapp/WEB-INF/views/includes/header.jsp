<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>MyLibrary</title>
  <link rel="stylesheet" href="/resources/bootstrap-4.0.0-dist/css/bootstrap.min.css">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:100,400|Ubuntu&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="/resources/bootstrap-4.0.0-dist/css/styles.css">

  <!-- Font Awesome -->
  <script src="https://kit.fontawesome.com/c0be56fbbf.js" crossorigin="anonymous"></script>
  
  <!-- jquery-3.5.1 -->
  <script src="/resources/bootstrap-4.0.0-dist/jquery/jquery-3.5.1.js"></script>
</head>

<body>
	 <section id="title">
		<div class="container">
			<!-- Nav Bar -->
			<nav class="navbar navbar-expand-lg navbar-dark">
				<a class="navbar-brand" href="/book">My Library</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarToggler"
					aria-controls="navbarToggler" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarToggler">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="/book/search">Search</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="#Editing">Editing</a>
						</li>
					</ul>
				</div>			
			</nav>
		</div>
	</section>
