<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<input class="form-control mr-sm-2" id="bookSearch" type="search" placeholder="책 제목을 입력하세요" aria-label="Search">
		<button class="btn btn-outline-success my-2 my-sm-0" id="search" type="submit">Search</button>
	</div>
</div>
 
<div class="row">
    <div class="col-lg-12">
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
        <div class="page-control ">
            <ul class="pagination float-right">
            </ul>
        </div>
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
    var bookSearch = $("#bookSearch");
    var pageCounter = $(".page-control");
    var searchButton = $("#search");

    var pageNum = 1;
    
    searchButton.on("click", function() {

        // 검색창 validation
        if(!bookSearch.val()) {
            alert("키워드를 입력하세요.");
            return;
        }

        pageNum = 1;
        var searchValue = { query : bookSearch.val(), page : pageNum};

        showList(searchValue);
    });
    //end search opration

    pageCounter.on("click", "li a", function(e) {
        e.preventDefault();
        console.log("page click");

        var targetPageNum = $(this).attr("href");

        console.log("target Page Number: " + targetPageNum);

        pageNum = targetPageNum;

        var searchValue = {query : bookSearch.val(), page : pageNum};

        showList(searchValue);
    })
    
    // 도서 이름 검색으로 리스트를 불러옴
    function showList(searchValue) {
        console.log("showList activated.");

        searchService.api_getBooks(searchValue, function(list){
            console.log(list);

            var content = list.documents;
            var pageableCount = list.meta.pageable_count;

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

            showListPage(pageableCount);
        });
    }

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
        
        searchService.api_getBook(isbn, function (selectedItem) {
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

            var authors = [];

            for (var i = 0; i < content[0].authors.length; i++) {
                authors.push({isbn : isbn, authors : content[0].authors[i]});
            }

             searchService.addBook(book, function (result) {
                alert(result);
            });

            searchService.addAuthor(authors, function (result) {
                alert(result);
            });
        });
    });
    // end table operation

    function showListPage(pageableCount) {
        console.log("showListPage activated");

        var endNum = Math.ceil(pageNum / 10.0) * 10;
        var startNum = endNum - 9;
        var prev = startNum != 1; // startNum이 1이 아니면 true / 1이면 false
        var next = false;

        if (endNum * 10 >= pageableCount) {
            endNum = Math.ceil(pageableCount / 10.0);
        }

        if (endNum * 10 < pageableCount) {
            next = true;
        }

        var str = "<div><ul class='pagination float-right'>";
        
        if (prev) {
            str += "<li class='page-item'><a class='page-link' href='"+ (startNum - 1) +"'>Previous</a></li>";
        }

        for (var i = startNum; i <= endNum; i++) {
            var active = pageNum == i? "active" : "";

            str += "<li class='page-item " + active + "'><a class='page-link' href='" + i + "'>"+ i +"</a><li>";
        }

        if (next) {
            str += "<li class='page-item'><a class='page-link' href='"+ (endNum + 1) +"'>Next</a></li>";
        }

        str += "</ul></div>";
        
        pageCounter.html(str);
    }
});
</script>

<%@ include file="../includes/footer.jsp"%>