<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="java.util.*" %>

<%@ include file="../views/includes/header.jsp"%>
	
	<div class="text-center">
		<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage() }"/></h2>
		<h2><c:out value="${msg }"/></h2>
		<h5>컨텐츠 수정 및 삭제는 관리자에게 문의하세요.</h5>
	</div>
	
<%@ include file="../views/includes/footer.jsp"%>