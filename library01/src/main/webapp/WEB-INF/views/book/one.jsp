<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<section class="container" style="border-bottom:solid;">
	<div class="col-lg-12">
		<div class="form-row" style="padding-top: 30px;">
			<div class="form-group col-md-6 text-center">
                <img src="${book.thumbnail }" style="width: 180px;">
            </div>
            <div class="form-group col-md-6 text-center" >
            	<p class="">
            		<h5 style="margin:auto 0;"><c:out value="${book.title }"/></h5>
            	</p>
                <p> 작가 : 
                <c:forEach items="${authors}" var="authors" >
                	<span><c:out value="${authors.authors }"/>.</span>
                </c:forEach>
                </p>
                <p>isbn : <c:out value="${book.isbn }"/></p>
                <P><a href="<c:out value="${book.url }"/>" target="_black">책 상세 정보 보기</a></P>
                <p>책 등록일 : <c:out value="${book.createdate }"/></p>
            </div>
         </div>
    </div>
</section>

<section class="container" style="border-bottom:solid; padding-bottom : 30px;">
	<div class="col-lg-12">
		<div class="form-row" style="padding-top: 30px;">
           	<c:if test="${review.mention eq null }">
               <div class="form-group col-lg-12" id="mention-group" style="padding-top: 30px;">
                 <label class="metion-label">서평</label>
                 <h4>서평이 없어요. 서평을 작성해주세요.</h4>
               </div>
               <div class="gorm-group col-lg-12">
                 <div class="form-inline float-right" id="buttonController">
                     <button class="btn btn-outline-success" id="modalAddActive" type="button">작성하기</button>
                 </div>
               </div>
            </c:if>
            <c:if test="${review.mention != null }">
               <div class="form-group col-lg-12" id="mention-group" style="padding-top: 30px;">
                 <label class="metion-label">서평</label>
                 <p><c:out value="${review.mention }" /></p>
               </div>
               <div class="gorm-group col-lg-12">
                 <div class="form-inline float-right" id="buttonController">
                     <button class="btn btn-outline-success" id="modalModifyActive" type="button" style="margin-right:10px;">수정</button>
                 </div>
               </div>
            </c:if>
		</div>
		<!-- form end -->
	</div>
</section>
<section class="container">
	<div class="col-lg-12">
		<!-- /.card -->
		<div class="card">
			<div class="card-header">
				<h5>문장 수집</h5>
			</div>
			<!-- /.card-heading -->
			<div class="card-body">
				<ul class="sentence-collection">
					<li class="left clearfix" data-sno=1>
						<div>
							<div class="header">
								<strong></strong>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<!-- /.card-body -->
		</div>
	</div>
</section>
		
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    
      <div class="modal-header">
        <h5 class="modal-title">서평</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="modalBootTitle" class="col-form-label">책 제목</label>
            <input type="text" class="form-control" id="modalBootTitle" value='<c:out value="${book.title }"/>' readonly>
          </div>
          <div class="form-group">
            <label for="mention-text" class="col-form-label">서평 작성</label>
            <textarea class="form-control" id="mention-text" rows="8"></textarea>
          </div>
        </form>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="modalRegisterBtn">서평 작성 완료</button>
        <button type="button" class="btn btn-danger" id="modalRemoveBtn">삭제</button>
        <button type="button" class="btn btn-primary" id="modalModifyBtn">서평 수정</button>
        <button type="button" class="btn btn-default" id="modalCloseBtn" data-dismiss="modal">취소</button>
      </div>
      
    </div>
  </div>
</div>

<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/one.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var bnoValue = '<c:out value="${book.bno}"/>';
		var reviewMention = '<c:out value="${review.mention}"/>';
		
		var modal = $(".modal");
		var mentionTextArea = modal.find("textarea");
		
		var modalModifyBtn = $("#modalModifyBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		
		$("#modalAddActive").on("click", function(e){
			mentionTextArea.val("");
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalRegisterBtn.show();
			
			modal.modal('show');
		});
		
		$("#modalModifyActive").on("click", function(e){
			console.log("modify clicked");
			
		/* 	oneService.getReview(bnoValue, function(review) {
				mention.val(review.mention);
			}); */
			mentionTextArea.val(reviewMention);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModifyBtn.show();
			modalRemoveBtn.show();
			
			modal.modal('show');
		});
		
		modalRegisterBtn.on("click", function(e) {
			
			var review = {
				bno : bnoValue,
				mention : mentionTextArea.val(),
				rating : 1
			};
			
			oneService.addReview(review, function(result){
				alert(result);
			});
			
			mentionTextArea.val("");
			modal.modal('hide');
			
			location.reload(true);
		});
		
		modalModifyBtn.on("click", function(e){
			
			var review = {
					bno : bnoValue,
					mention : mentionTextArea.val(),
					rating : 1
				};
			
			oneService.updateReview(review, function(result){
				alert(result);
			});
			
			mentionTextArea.val("");
			modal.modal('hide');
			
			location.reload(true);
		});
		
		modalRemoveBtn.on("click", function(e){
			oneService.deleteReview(bnoValue, function(result){
				alert(result);
			});
			location.reload(true);
		});
	});
</script>

<%@ include file="../includes/footer.jsp"%>