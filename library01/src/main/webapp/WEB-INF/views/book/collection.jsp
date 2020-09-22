<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<!-- Pricing -->
<section id="pricing">
	<div class="row" style="text-align: center">
		<h2>Books in My Library</h2>
	</div>	
	<div class="row">
		<c:forEach items="${bookList}" var="list">
			<div class="book-column col-lg-3 col-md-4 col-sm-6" style="margin-bottom: 40px">
				<div class="card">
					<div class="card-header" style="margin: auto">
						<a class="thumbnail" href="#" >
							<img src="<c:out value='${list.thumbnail }'/>">
						</a>
					</div>
					<div class="card-body">
						<p class="card-text"><c:out value="${list.title }"/></p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</section>

<%@ include file="../includes/footer.jsp"%>
