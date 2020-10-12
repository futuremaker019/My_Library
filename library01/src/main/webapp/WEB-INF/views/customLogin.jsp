<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../views/includes/header.jsp"%>


<section class="container">
    <div class="row justify-content-md-center pt-5">
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
                <button class="btn btn-success">Login</button>
            </div>
            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
         </form>
    </div>
</section>

<script type="text/javascript">
	$(document).ready(function(){
		$(".btn-success").on("click", function(e){
			e.preventDefault();
			$("form").submit();
		});
	});
</script>


<%@ include file="../views/includes/footer.jsp"%>