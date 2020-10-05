console.log("search module activated.");

var searchService = (function () {
  
   function api_getBooks(searchValue, callback, error) {
      console.log("api_getBooks activated");
      $.ajax({
         method : "GET",
         url : "https://dapi.kakao.com/v3/search/book?",            
         data: { query : searchValue.query, page: searchValue.page },
         headers: { Authorization : "KakaoAK bda6b0bfc981c23fb7389b1f667df58f"}           
      })
      .done(function (data, status, xhr) {
         if (callback) {
            callback(data);
         }
      })
      .fail(function(xhr, status, er) {
         if (error) {
            error(er);
         }   
      });
   }

   function api_getBook(searchValue, callback, error) {
      console.log("api_getBooks activated");
      $.ajax({
         method : "GET",
         url : "https://dapi.kakao.com/v3/search/book?",            
         data: { query : searchValue },
         headers: { Authorization : "KakaoAK bda6b0bfc981c23fb7389b1f667df58f"}           
      })
      .done(function (data, status, xhr) {
         if (callback) {
            callback(data);
         }
      })
      .fail(function(xhr, status, er) {
         if (error) {
            error(er);
         }   
      });
   }

   function addBook(item, callback, error) { 
      console.log("add book activated.");

      $.ajax({
         type: 'post',
         url: '/api/addbook',
         data : JSON.stringify(item),
         contentType: "application/json; charset=utf-8",
      })
      .done(function(result, status, xhr){
         if (callback) {
            callback(result);
         }
      })
      .fail(function(xhr, status, err){
         if(error) {
            error(err);
         }
      });
   }

   function addAuthor(item, callback, error) { 
      console.log("add author activated.");

      $.ajax({
         type: 'post',
         url: '/api/addauthors/',
         data : JSON.stringify(item),
         contentType: "application/json; charset=utf-8",
      })
      .done(function(result, status, xhr){
         if (callback) {
            callback(result);
         }
      })
      .fail(function(xhr, status, err){
         if(error) {
            error(err);
         }
      });
   }

   function getList(param, callback, error) {
      console.log("getList activated.");

      $.getJSON("/book/",
         function (data) {
            if (callback) {
               callback(data);
            }
         })
      .fail(function (xhr, status, err) {
         if(error) {
         error(err);
         }
      });
   }

   return {
      api_getBooks : api_getBooks,
      api_getBook :api_getBook,
      addBook : addBook,
      addAuthor : addAuthor,
      getList : getList
   };
})();