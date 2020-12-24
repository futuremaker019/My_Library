<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ include file="includes/header.jsp"%>

<div style="text-align:center;">
	<h2>Hello there!</h2>
	<h3>I hope you don't feel blue today</h3>
	<P>  The time on the server is ${serverTime}. </P>
</div>
<%@ include file="includes/footer.jsp"%>