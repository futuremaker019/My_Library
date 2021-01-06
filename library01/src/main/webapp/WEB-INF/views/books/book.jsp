<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<section class="container border-bottom">
	<div class="col-lg-12">
		<div class="form-row" style="padding-top: 30px;">
			<div class="form-group col-md-6 text-center">
                <img src="${book.thumbnail }" style="width: 180px;" class="img-thumbnail">
            </div>
            <div class="form-group col-md-6 text-center" >
           		<h5 style="margin:auto 0;"><c:out value="${book.title }"/></h5>
                <p> 작가 : 
                <c:forEach items="${authors}" var="authors" >
                	<span><c:out value="${authors.author }"/>.</span>
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
			<h5>리뷰</h5>
			<c:if test="${review.review_id eq null }"> 
				<div class="form-group col-lg-12" id="content-group" style="padding-top: 30px;">
				  <small class='text-muted pb-3 d-inline-block'>작성일자 : </small>
				  <p>리뷰을 작성해주세요.</p>
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
			<!-- /.card-heading -->
			<div class="card-body">
				<h5 class="pt-3 pl-3">문장 수집</h5>
			</div>
			<div class="card-body">
				<ul id="collection-list" class="list-group">
					<li class='list-group-item'>
                        <div class='header'>
                            <small class='text-muted'>댓글 작성 날짜</small>
                            <div class='btn-group btn-group-sm float-right'>
                                <button class='btn btn-default' name='collection-modify' data-sno='1'>수정하기</button> 
                                <button class='btn btn-default' name='collection-delete' data-sno='1'>삭제하기</button>
                            </div>
                        </div>
                        <p class='mt-2'>문장</p>
	                </li>
				</ul>
			</div>
			<!-- /.card-body -->
			<div class="card-body">
				<textarea class="form-control" rows="5" placeholder="문장을 작성해주세요." id="collection"></textarea>
				<div class="mt-2">
					<button id="add-collection-btn" class="btn btn-success">등록하기</button>
					<div id="collection-text-counter" class="float-right">
						<span id="text-counter">0</span><span> / 1400</span>
					</div>
				</div>
			</div>
			<!-- /.collection 입력 -->
		</div>
	</div>
</section>
		
<div class="modal fade" id="modalContents" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    
      <div class="modal-header">
        <h5 class="modal-title">리뷰</h5>
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
        <button type="button" class="btn btn-primary" id="modalRegisterBtn">리뷰 작성 완료</button>
        <button type="button" class="btn btn-danger" id="modalRemoveBtn">리뷰 삭제</button>
        <button type="button" class="btn btn-primary" id="modalModifyBtn">리뷰 수정</button>
        <button type="button" class="btn btn-default" id="modalCloseBtn" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<form role="form" action="/books/remove" method="post">
	<input type="hidden" name="book_id" value='<c:out value="${book.book_id}"/>'>
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
</form>

<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/review.js"></script>
<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/collection.js"></script>
<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/util.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	
	var book_id = '<c:out value="${book.book_id}"/>';
	var loginUserId = "${authentication.principal.member.userId}";
	
	var modal = $(".modal");
	var modalTitle = $(".modal-title");
	
	var reviewGroup = $("#review-group");
	var modalTextArea = $("#summernote");
	var collection_textarea = $("#collection");
	
	var modalModifyBtn = $("#modalModifyBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	
	var add_collection_btn = $("#add-collection-btn");
	var collection_list = $("#collection-list")
	
	showList(1);
	
	// Ajax spring security header, token
	$(document).ajaxSend(function(e, xhr, options){
		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	});
	
	// 서평 작성 모달 활성화 클릭 이벤트 - 모달 호출
	$("#add-review-modal-active-btn").on("click", function(e){
		modalTextArea.summernote('code', "");
		$(".modal-title").text("서평");
		modal.find("button[id != 'modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		modal.modal('show');
	});
	
	// 서평 수정 모달 호출 클릭 이벤트
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
			book_id : book_id,
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
				book_id : book_id,
				content : modalTextArea.val(),
				rating : 1
			};
		
		reviewService.updateReview(review, csrfHeaderName, csrfTokenValue, function(review){
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
		if(confirm("리뷰을 삭제하시겠습니까?")) {
			reviewService.deleteReview(book_id, function(result){
				let str = "";
				
				str += "<span><small class='text-muted pb-3 d-inline-block'>리뷰일자 :</small></span>"; 
				str += "<p>리뷰을 작성해주세요.</p>";
				
				$("#content-group").html(str);	
			});
		} else {
			return;
		}
		
		refresh();
	});
	
	add_collection_btn.on("click", function(){
		if(!collection_textarea.val()){
			alert("문장을 작성해주세요.");
			return;
		}
		
		// 로그인 하지 않고 댓글을 등록하면 로그인 페이지로 이동힌다.
		if(loginUserId == ""){
			if(confirm("로그인 후 댓글을 남길수 있습니다. 로그인 하시겠습니까?")){
				location.href = "/member/login";
			}
			collection_textarea.val("");
			return;
		}
		
		if(collection_textarea.val().length > 1400) {
			alert("한글은 최대 1400자까지 가능합니다.");
		}

		let param = {
			book_id : book_id,
			content : collection_textarea.val()
		}

		collectionService.addCollection(param, csrfHeaderName, csrfTokenValue, function(collection){
			let str = "";
			
			str += "<li class='list-group-item'>"
			str += "<div class='header'>"
			str += "<small class='text-muted'>" + util.displayTime(collection.modifieddate) + "</small>"
			str += "<div class='btn-group btn-group-sm float-right'>"
			str += "<button class='btn btn-default' id='collection-modify-activate-btn' data-collectionid='" + collection.collection_id + "'>수정하기</button>" 
			str += "<button class='btn btn-default' id='collection-delete-btn' data-collectionid='" + collection.collection_id + "'>삭제하기</button>"
			str += "</div></div><p class='mt-2'>" + collection.content + "</p></div></li>"
		
			collection_list.append(str);
			collection_textarea.val("");
			
			$("#text-counter").text("0");
		});
	});	
	
	
	//글자수 제한 
	collection_textarea.keyup(function(e){
		var content = $(this).val();
		$("#text-counter").text(content.length);
		
		if(content.length >= 1400) {
			$(this).val(content.substring(0, 1400));
			$("#text-counter").text($(this).val().length);
			alert("한글은 최대 1400자까지 가능합니다.");
			return false;
		}
	})
	
	collection_list.on("click", "#collection-modify-activate-btn", function(){
		let collection_id = $(this).data("collectionid");
		let targetLi = $(this).closest("li");
		
		console.log("clicked");
		console.log(collection_id);
		
		collectionService.getCollection(collection_id, function(collection){
			let str ="";
			
	 		str += "<textarea class='form-control' rows='5' id='collection-content'>" + collection.content + "</textarea>";
			str += "<div class='mt-2'>";
			str += "<button id='collection-modify-btn' class='btn btn-info' data-collectionid='" + collection.collection_id + "'>수정완료</button>";
			str += "<button id='collection-cancel-btn' class='btn btn-default'>취소</button>";
			str += "<div class='float-right'><span>0</span><span> / 2000</span></div></div>";
			
			targetLi.html(str);
		});
	});
	
	collection_list.on("click", "#collection-modify-btn", function(){
		let collection_id = $(this).data("collectionid");
		
		let param = {
			collection_id : collection_id,
			content : collection_list.find("#collection-content").val()
		}
		
		console.log(param);
		
		collectionService.updateCollection(param, csrfHeaderName, csrfTokenValue, function(collection){
			showList();
		})
	});
	
	collection_list.on("click", "#collection-delete-btn", function(){
		let collection_id = $(this).data("collectionid");
		let targetli = $(this).closest("li");
		
		if(confirm("삭제하시겠습니까?")){
			collectionService.removeCollection(collection_id, csrfHeaderName, csrfTokenValue, function(result){
				targetli.remove();
			});	
		}
	});
	
	collection_list.on("click", "#collection-cancel-btn", function(){
		showList();
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
	function showList() {
		let str ="";
		
		collectionService.getList(book_id, function(collections) {
			if (collections == null || collections.length == 0){
				collection_list.html("");
				return;
			}
			
			for(let i = 0, len = collections.length || 0; i < len; i++) {
				str += "<li class='list-group-item'>"
				str += "<div class='header'>"
				str += "<small class='text-muted'>" + util.displayTime(collections[i].modifieddate) + "</small>"
				str += "<div class='btn-group btn-group-sm float-right'>"
				str += "<button class='btn btn-default' id='collection-modify-activate-btn' data-collectionid='" + collections[i].collection_id + "'>수정하기</button>" 
				str += "<button class='btn btn-default' id='collection-delete-btn' data-collectionid='" + collections[i].collection_id + "'>삭제하기</button>"
				str += "</div></div><p class='mt-2'>" + collections[i].content + "</p></div></li>"
			}
			
			collection_list.html(str);
		});
	}
	
	function refresh(){
		window.location.reload();
	}
	
	$('#summernote').summernote({
	    placeholder: '내용을 작성해주세요.',
	    height: 200,
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