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
                	<button class="btn btn-primary btn-info" onclick="location.href='/books'">내 서재</button>
                	<button id="bookRemoveBtn" class="btn btn-danger btn-info ml-4">책 삭제</button>
                </div>
            </div>
         </div>
    </div>
</section>

<section class="container border-bottom" style="padding-bottom : 30px;">
	<div class="col-lg-12">
		<div class="form-row" id="review-group" style="padding-top: 30px;">
			<h5>서평</h5>
			<c:if test="${review.review_id eq null }"> 
				<div class="form-group col-lg-12" id="content-group" style="padding-top: 30px;">
				  <small class='text-muted pb-3 d-inline-block'>작성일자 : </small>
				  <p>서평을 작성해주세요.</p>
				</div>
				<div class="form-group col-lg-12">
				  <div class="form-inline float-right" id="modify-review-btn-div">
				      <button class="btn btn-success" id="add-review-modal-active-btn" type="button">리뷰 작성</button>
				  </div>
				</div>
			</c:if>
			<c:if test="${review.review_id ne null }">
				<div class="form-group col-lg-12" id="content-group" style="padding-top: 30px;">
				  <span><small class='text-muted pb-3 d-inline-block'>작성일자 : <c:out value="${review.modifieddate }"/></small></span>
				  <div><c:out value="${review.content }" escapeXml="false"/></div>
				</div>
				<div class="form-group col-lg-12">
				  <div class="form-inline float-right" id="modify-review-btn-div">
				      <button class="btn btn-info" id="modify-review-modal-active-btn" type="button">리뷰 수정</button>
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
          <div class="">
            <textarea id="summernote" name="content-text" rows="5"></textarea>
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

<form role="form" action="/books/remove" method="post">
	<input type="hidden" name="bno" value='<c:out value="${book.bno}"/>'>
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
</form>

<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/review.js"></script>
<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/sentence.js"></script>
<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/util.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var book_id = '<c:out value="${book.bno}"/>';
	
	var modal = $(".modal");
	var modalTitle = $(".modal-title");
	
	var reviewGroup = $("#review-group");
	var modalTextArea = $("#summernote");
	
	var sentenceCollectionUL = $(".sentence-collection");
	
	var modalModifyBtn = $("#modalModifyBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalSentenceAddBtn = $("#modalSentenceAddBtn");
	var modalSentenceRemoveBtn = $("#modalSentenceRemoveBtn");
	var modalSentenceModifyBtn = $("#modalSentenceModifyBtn");
	
	showList(1);
	
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	
	// Ajax spring security header
	$(document).ajaxSend(function(e, xhr, options){
		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	});
	
	// 서평 작성 모달 활성화 클릭 이벤트
	$("#add-review-modal-active-btn").on("click", function(e){
		modalTextArea.val("");
		modal.find("button[id != 'modalCloseBtn']").hide();
		modalRegisterBtn.show();
		
		modal.modal('show');
	});
	
	// 서평 수정 모달 활성화 클릭 이벤트
	reviewGroup.on("click", "#modify-review-modal-active-btn", function(){
		reviewService.getReview(book_id, function(review){
			modalTextArea.summernote('code', review.content);
		});
		
		modal.find("button[id != 'modalCloseBtn']").hide();
		modalModifyBtn.show();
		modalRemoveBtn.show();
		
		modal.modal('show');
	});
	
	//서평 등록 버튼 클릭 이벤트
	modalRegisterBtn.on("click", function(e) {
		let review = {
			bno : book_id,
			content : modalTextArea.val(),
			rating : 1
		};
		
		reviewService.addReview(review, function(review){
			let str = "";
			let btnStr = "";
			
			str += "<span><small class='text-muted pb-3 d-inline-block'>작성일자 : "+util.displayTime(review.modifieddate)+"</small></span>"; 
			str += "<p>"+review.content+"</p>";
			
			btnStr += "<button class='btn btn-info' id='modify-review-modal-active-btn' type='button'>리뷰 수정</button>"
			
			$("#content-group").html(str);
			$("#modify-review-btn-div").html(btnStr);
		});
		
		modalTextArea.val("");
		modal.modal('hide');
	});
	
	// 서평 수정 버튼 클릭 이벤트
	modalModifyBtn.on("click", function(e){
		let review = {
				bno : book_id,
				content : modalTextArea.val(),
				rating : 1
			};
		
		reviewService.updateReview(review, csrfHeaderName, csrfTokenValue, function(review){
			/* var reviewContent = "";
			
			reviewContent = review.content;
			reviewContent = reviewContent.replace("/(?:\r\n|\r|\n)/g", "<br />"); */
			
			let str = "";
			
			str += "<span><small class='text-muted pb-3 d-inline-block'>작성일자 : "+util.displayTime(review.modifieddate)+"</small></span>"; 
			str += "<div>"+review.content +"</div>";
			
			$("#content-group").html(str);
		});
		
		modalTextArea.val("");
		modal.modal('hide');
	});
	
	// 서평 삭제 버튼 클릭 이벤트
	modalRemoveBtn.on("click", function(e){
		if(confirm("서평을 삭제하시겠습니까?")) {
			reviewService.deleteReview(book_id, function(result){
				console.log(result);
				
				let str = "";
				
				str += "<span><small class='text-muted pb-3 d-inline-block'>작성일자 :</small></span>"; 
				str += "<p>서평을 작성해주세요.</p>";
				
				$("#content-group").html(str);	
			});
		} else {
			return;
		}
		
		refresh();
	});
	
	// 문장 등록하기 버튼 클릭 이벤트
	$("#modalSentenceAddActive").on("click", function(e) {
		modalTextArea.val("");
		$(".modal-title").text("문장수집");
		modal.find("button[id != modalCloseBtn]").hide();
		modalSentenceAddBtn.show();
		
		modal.modal('show');
	});
	
	// 문장 작성 완료 버튼 클릭 이벤트
	modalSentenceAddBtn.on("click", function(e){
		let sentence = {
			bno : book_id,
			sentence : modalTextArea.val()
		};
		
		sentenceService.addSentence(sentence, function(result) {
			alert(result);
		});
		
		modalTextArea.val("");
		modal.modal('hide');
		
		refresh();
	});
	
	// 문장 수정 버튼 클릭 이벤트
	modalSentenceModifyBtn.on("click", function(e){
		let sentence = {
			sno : modal.data("sno"),
			sentence : modalTextArea.val()
		};
		
		sentenceService.updateSentence(sentence, function(result) {
			alert(result);
		});
		
		modalTextArea.val("");
		modal.modal('hide');
		
		refresh();
	});
	
	// 문장 수정하기 버튼 클릭 이벤트
	$("ul").on("click", "button[id='sentenceModifyBtn']", function(e){
           let sno = $(this).data("sno");

           sentenceService.getSentence(sno, function(sentence){
           	modalTextArea.val(sentence.sentence);
           });
		
           modal.data("sno", sno);
           modalTitle.text("문장수집");
           modal.find("button[id != modalCloseBtn]").hide();
           modalSentenceModifyBtn.show();

           modal.modal('show');
       });
	
	// 문장수집 삭제하기 버튼 클릭 이벤트
	$("ul").on("click", "button[id='sentenceDeleteBtn']", function(e){
           let sno = $(this).data("sno");
           
		if(confirm("문장수집을 삭제하시겠습니까?")) {
			sentenceService.removeSentence(sno, function(result){
                alert(result);
            });
		} else {
			return;
		}
           
		refresh();
       });
	
	// 책 삭제하기 버튼 클릭 이벤트
	 $("#bookRemoveBtn").on("click", function(e){
		if(confirm("책을 삭제하시겠습니까?")) {
			$("form").submit();
		} else {
			return;
		}
	});
	
	// 문장 수집 불러오기
	function showList(page) {
		let str ="";
		
		sentenceService.getList({bno:book_id, page:page}, function(list) {
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
	
	function refresh(){
		window.location.reload();
	}
	
	$('#summernote').summernote({
	    placeholder: '내용을 작성해주세요.',
	    height: 400,
	    minHeight: null,
	    maxHeight: null,
	    focus: true,
	    isNotSplitEdgePoint: true,
	    toolbar: [
	      ['style', ['style']],
	      ['font', ['bold', 'underline', 'clear']],
	      ['color', ['color']],
	      ['para', ['ul', 'ol', 'paragraph']],
	      ['table', ['table']],
	      ['insert', ['link', 'picture', 'video']],
	      ['view', ['codeview', 'help']]
	    ]
	});
});
</script>

<%@ include file="../includes/footer.jsp"%>