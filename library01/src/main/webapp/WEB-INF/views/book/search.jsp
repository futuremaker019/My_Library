<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<input class="form-control mr-sm-2" id="bookName" type="search" placeholder="책 제목을 입력하세요" aria-label="Search">
		<button class="btn btn-outline-success my-2 my-sm-0" id="search" type="submit">Search</button>
	</div>
</div>
 
<div class="row">
    <div class="col-lg-12">
        <!-- <form class="panel panel-default" name="searchForm" method="POST" action="/book/new"> -->
            <table class="table table-bordered table-hover">
                <colgroup>
                    <col width="90">
                    <col width="*">
                    <col width="120">
                    <col width="120">
                </colgroup>
                <tbody class="contents-items">
                </tbody>
            </table>
             <!-- /. panel-body -->
        <!-- </form> -->
    </div>
</div>

<form id="operationForm">
	<input type='hidden' name='isbn' value="">
    <input type='hidden' name='title' value="">
    <input type='hidden' name='thumbnail' value="">
    <input type='hidden' name='authors' value="">
    <input type='hidden' name='publisher' value="">
    <input type='hidden' name='url' value="">
    <input type='hidden' name='datetime' value="">
</form>

<script type="text/javascript" src="/resources/bootstrap-4.0.0-dist/js/search.js"></script>
<script>
    $(document).ready(function () {
        console.log("===============");
        console.log("JS TEST");

        var contents = $(".contents-items");
        var operationForm = $("#operationForm");
        
        // 도서 이름 검색으로 리스트를 불러옴
        $("#search").click(function(){

            var searchValue = $("#bookName").val();

            searchService.api_getBooks(searchValue, function(list){
                console.log(list);

                var content = list.documents;

                let strTag = '';

                if (content == null) {
                    return;
                }

                for (var i = 0, len = content.length || 0; i < len; i++) {
                    strTag += "<tr data-isbn='" + content[i].isbn + "'>"
                    strTag += "<td><div><img class='thumbnail' src=" + content[i].thumbnail + "></div></td>"
                    strTag += "<td class='details'><div><strong>"+ content[i].title +"</strong></div>"
                    strTag += "<div>" + content[i].authors + "</div>"
                    strTag += "<div>" + content[i].publisher + "</div>"
                    strTag += "<div><a href='" + content[i].url + "' target='_black'>책 정보</a></div>"
                    strTag += "<div>" + content[i].datetime.slice(0, 10) + "</div></td>"
                    strTag += "<td class='info'><button name='addMyLib' class='addMyLib btn btn-primary' data-isbn='" + content[i].isbn + "'>서재에 담기</button></td>"
                    strTag += "<td class='option'><button class='LinkUrl btn btn-warning'>상세보기</button></td></tr>"
                }
                contents.html(strTag);
            });
        });

        // 리스트에서 아이템 선택시 해당 isbn을 추출하여 database에 저장
        $(".table").on("click", "button[name='addMyLib']", function(){

            var ApiIsbn = $(this).data("isbn");
            console.log(ApiIsbn);

            var dividedIsbn =  ApiIsbn.split(" ");
            
            var isbn = '';
            if (dividedIsbn[0] != '') {
               isbn = dividedIsbn[0];
            } else {
               isbn = dividedIsbn[1];
            }
            
            searchService.api_getBooks(isbn, function (selectedItem) {
                console.log("isbn : " + isbn);
                console.log(selectedItem);
                
                var content = selectedItem.documents;
                
                var book = {
                        title : content[0].title,
                        isbn : isbn,
                        publisher : content[0].publisher,
                        thumbnail : content[0].thumbnail,
                        url : content[0].url,
                        registeredDate : content[0].datetime.slice(0, 10)
                    }

                var author =  [
                		{
		                	isbn : isbn,
		                    authors : content[0].authors[0]
	                	},{
		                	isbn : isbn,
		                    authors : content[0].authors[1]
	                	}
                    ]

                searchService.addBook(book, function (result) {
                    alert(result);
                });

                searchService.addAuthor(author, function (result) {
                    alert(result);
                });
               
                // let strTag = '';

                /* strTag += "<input type='hidden' name='title' value='"+ content[0].title +"'>"
                strTag += "<input type='hidden' name='authors' value='" + content[0].authors + "'>"
                strTag += "<input type='hidden' name='isbn' value='" + content[0].isbn + "'>"
                strTag += "<input type='hidden' name='publisher' value='" + content[0].publisher + "'>"
                strTag += "<input type='hidden' name='thumbnail' value='" + content[0].thumbnail + "'>"
                strTag += "<input type='hidden' name='url' value='" + content[0].url + "'>"
                strTag += "<input type='hidden' name='datetime' value='" + content[0].datetime.slice(0, 10) + "'>"

                operationForm.empty();
                operationForm.html(strTag);

                var itemTitle = operationForm.find("input[name='title']");
                var itemAuthors = operationForm.find("input[name='authors']");
                var itemIsbn = operationForm.find("input[name='isbn']");
                var itempublisher = operationForm.find("input[name='publisher']");
                var itemThumbnail = operationForm.find("input[name='thumbnail']");
                var itemUrl = operationForm.find("input[name='url']");
                var itemDateTime = operationForm.find("input[name='datetime']"); */
            });
        });
    });
    </script>

<%@ include file="../includes/footer.jsp"%>