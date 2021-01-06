var searchService = (function () {
  
   function api_getBooks(searchValue, callback, error) {
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

   function addBook(item, csrfHeaderName, csrfTokenValue, callback, error) { 
      $.ajax({
         type: 'post',
         url: '/books/addbook',
         beforeSend : function(xhr) {
         	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
         },
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

   function verifyBook(param, csrfHeaderName, csrfTokenValue, callback, error) {
		$.ajax({
		     type: 'post',
		     url: '/books/verification',
		     beforeSend : function(xhr) {
		     	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
		     },
		     data : JSON.stringify(param),
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

	   return {
	      api_getBooks : api_getBooks,
	      api_getBook :api_getBook,
	      addBook : addBook,
	      verifyBook : verifyBook
	   };
})();