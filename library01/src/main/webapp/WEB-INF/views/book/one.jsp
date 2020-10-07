<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<section class="container border-bottom">
	<div class="col-lg-12">
		<div class="form-row" style="padding-top: 30px;">
			<div class="form-group col-md-6 text-center">
                <img src="${book.thumbnail }" style="width: 180px;">
            </div>
            <div class="form-group col-md-6 text-center" >
            	<p>
            		<h5 style="margin:auto 0;"><c:out value="${book.title }"/></h5>
            	</p>
                <p> 작가 : 
                <c:forEach items="${authors}" var="authors" >
                	<span><c:out value="${authors.authors }"/>.</span>
                </c:forEach>
                </p>
                <p>isbn : <c:out value="${book.isbn }"/></p>
                <P><a href="<c:out value="${book.url }"/>" target="_black">책 상세 정보 보기</a></P>
                <p>책 출판일 : <c:out value="${book.datetime }"/></p>
                <p>내 서재 등록일 : <c:out value="${book.createdate }"/></p>
                <div>
                	<button class="btn btn-primary btn-info" onclick="location.href='/book'">내 서재</button>
                	<button id="bookRemoveBtn" class="btn btn-danger btn-info ml-4">책 삭제</button>
                </div>
            </div>
         </div>
    </div>
</section>

<section class="container border-bottom" style="padding-bottom : 30px;">
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

<section class="container pt-3">
	<div class="col-lg-12">
		<!-- /.card -->
		<div class="card">
			<div class="card-header">
				<h5 class="d-inline-block">문장 수집</h5>
				<button id="modalSentenceAddActive" class="btn btn-primary float-right">문장 등록하기</button>
			</div>
			<!-- /.card-heading -->
			<div class="card-body">
				<ul class="list-group">
					<li class='list-group-item'>
	                    <div>
	                        <div class='header'>
	                            <strong>p123</strong>
	                            <small class='ml-4 text-muted'>2020-10-05 13:13</small>
	                            <div class='btn-group btn-group-sm float-right'>
	                                <button class='btn btn-default' name='sentence-modify' data-sno='1'>수정하기</button> 
	                                <button class='btn btn-default' name='sentence-delete' data-sno='1'>삭제하기</button>
	                            </div>
	                        </div>
	                        <p class='mt-2'>나는 너에게 너는 나에게</p>
	                    </div>
	                </li>
				</ul>
			</div>
			<!-- /.card-body -->
		</div>
	</div>
</section>
		
<div class="modal fade" id="modalContents" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
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
            <label for="mention-text" class="col-form-label">작성</label>
            <textarea class="form-control" id="mention-text" rows="8"></textarea>
          </div>
        </form>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="modalRegisterBtn">서평 작성 완료</button>
        <button type="button" class="btn btn-danger" id="modalRemoveBtn">서평 삭제</button>
        <button type="button" class="btn btn-primary" id="modalModifyBtn">서평 수정</button>
        <button type="button" class="btn btn-primary" id="modalSentenceAddBtn">문장 작성 완료</button>
        <button type="button" class="btn btn-danger" id="modalSentenceRemoveBtn">문장 삭제</button>
        <button type="button" class="btn btn-primary" id="modalSentenceModifyBtn">문장 수정</button>
        <button type="button" class="btn btn-default" id="modalCloseBtn" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<form role="form" action="/book/remove" method="post">
	<input type="hidden" name="bno" value='<c:out value="${book.bno}"/>'>
</form>

<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/one.js"></script>
<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/sentence.js"></script>
<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/util.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var bnoValue = '<c:out value="${book.bno}"/>';
		var reviewMention = '<c:out value="${review.mention}"/>';
		
		var modal = $(".modal");
		var modalTitle = $(".modal-title");
		var textArea = modal.find("textarea");
		var sentenceCollectionUL = $(".sentence-collection");
		
		var modalModifyBtn = $("#modalModifyBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalSentenceAddBtn = $("#modalSentenceAddBtn");
		var modalSentenceRemoveBtn = $("#modalSentenceRemoveBtn");
		var modalSentenceModifyBtn = $("#modalSentenceModifyBtn");
		
		showList(1);
		
		// 서평 작성 모달 활성화 클릭 이벤트
		$("#modalAddActive").on("click", function(e){
			textArea.val("");
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalRegisterBtn.show();
			
			modal.modal('show');
		});
		
		// 서평 수정 모달 활성화 클릭 이벤트
		$("#modalModifyActive").on("click", function(e){
			textArea.val(reviewMention);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModifyBtn.show();
			modalRemoveBtn.show();
			
			modal.modal('show');
		});
		
		//서평 등록 버튼 클릭 이벤트
		modalRegisterBtn.on("click", function(e) {
			let review = {
				bno : bnoValue,
				mention : textArea.val(),
				rating : 1
			};
			
			reviewService.addReview(review, function(result){
				alert(result);
			});
			
			textArea.val("");
			modal.modal('hide');
			
			location.reload(true);
		});
		
		// 서평 수정 버튼 클릭 이벤트
		modalModifyBtn.on("click", function(e){
			let review = {
					bno : bnoValue,
					mention : textArea.val(),
					rating : 1
				};
			
			reviewService.updateReview(review, function(result){
				alert(result);
			});
			
			textArea.val("");
			modal.modal('hide');
			
			location.reload(true);
		});
		
		// 서평 삭제 버튼 클릭 이벤트
		modalRemoveBtn.on("click", function(e){
			reviewService.deleteReview(bnoValue, function(result){
				alert(result);
			});
			location.reload(true);
		});
		
		// 문장 등록하기 버튼 클릭 이벤트
		$("#modalSentenceAddActive").on("click", function(e) {
			textArea.val("");
			$(".modal-title").text("문장수집");
			modal.find("button[id != modalCloseBtn]").hide();
			modalSentenceAddBtn.show();
			
			modal.modal('show');
		});
		
		// 문장 작성 완료 버튼 클릭 이벤트
		modalSentenceAddBtn.on("click", function(e){
			let sentence = {
				bno : bnoValue,
				sentence : textArea.val()
			};
			
			sentenceService.addSentence(sentence, function(result) {
				alert(result);
			});
			
			textArea.val("");
			modal.modal('hide');
			
			window.location.reload();
		});
		
		// 문장 수정 버튼 클릭 이벤트
		modalSentenceModifyBtn.on("click", function(e){
			let sentence = {
				sno : modal.data("sno"),
				sentence : textArea.val()
			};
			
			sentenceService.updateSentence(sentence, function(result) {
				alert(result);
			});
			
			textArea.val("");
			modal.modal('hide');
			
			window.location.reload();
		});
		
		// 수정하기 버튼 클릭 이벤트
		$("ul").on("click", "button[id='sentenceModifyBtn']", function(e){
            var sno = $(this).data("sno");

            sentenceService.getSentence(sno, function(sentence){
                textArea.val(sentence.sentence);
            });
			
            modal.data("sno", sno);
            modalTitle.text("문장수집");
            modal.find("button[id != modalCloseBtn]").hide();
            modalSentenceModifyBtn.show();

            modal.modal('show');
        });
		
		// 삭제하기 버튼 클릭 이벤트
		$("ul").on("click", "button[id='sentenceDeleteBtn']", function(e){
            var sno = $(this).data("sno");
           
            sentenceService.removeSentence(sno, function(result){
                alert(result);
            });
            
            window.location.reload();
        });
		
		 $("#bookRemoveBtn").on("click", function(e){
			$("form").submit();
			alert("Book removed successfully");
		});
		
		/* $("#bookRemoveBtn").on("click", function(e){
			bookService.removeBook(bnoValue, function(result){
				alert(result);
			});
			window.location.reload();
			window.location.href='/book';
		}) */
		
		// 문장 수집 불러오기
		function showList(page) {
			let str ="";
			
			sentenceService.getList({bno:bnoValue, page:page}, function(list) {
				for(let i = 0, len = list.length || 0; i < len; i++) {
					str += "<li class='list-group-item'><div>"
					str += "<div class='header'>"
					str += "<small class='text-muted'>" + util.displayTime(list[i].modifieddate) + "</small>"
					str += "<div class='btn-group btn-group-sm float-right'>"
					str += "<button class='btn btn-default' id='sentenceModifyBtn' data-sno='" + list[i].sno + "'>수정하기</button>" 
					str += "<button class='btn btn-default' id='sentenceDeleteBtn' data-sno='" + list[i].sno + "'>삭제하기</button>"
					str += "</div></div><p class='mt-2'>" + list[i].sentence + "</p></div></li>"
				}
				
				$(".list-group").html(str);
			});
		}
	});
</script>

<%@ include file="../includes/footer.jsp"%>