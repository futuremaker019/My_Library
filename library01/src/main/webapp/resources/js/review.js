var reviewService = (function() {

	function addReview(review, callback, error) {
	  $.ajax({
	     method : "POST",
	     url : "/review/",            
	     data: JSON.stringify(review),
	     contentType : "application/json; charset=utf-8"
	  })
	  .done(function (data, status, xhr) {
	     if (callback) {
	        callback(data);
	     }
	  })
	  .fail(function(xhr, status, err) {
	     if (error) {
	        error(err);
	     }   
	  });
	}
	
	function getReview(book_id, callback, error) {
		$.getJSON("/review/" + book_id + ".json",
			function(data) {
				if(callback) {
					callback(data);
				}
		}).fail(function(xhr, status, err) {
			if (error) {
				error(err)
			}
		});
	}
	
	function updateReview(review, csrfHeaderName, csrfTokenValue, callback, error) {
		let book_id = review.book_id;

		$.ajax({
			type: "put",
			url: "/review/" + book_id,
			data: JSON.stringify(review),
			contentType : "application/json; charset=utf-8"
		})
		.done(function (data, status, xhr) {
			if (callback) {
			   callback(data);
			}
		})
		.fail(function (xhr, status, err) {
			if (error) {
			error(err);
			}   
		});
	}

	function deleteReview(book_id, callback, error) {
		$.ajax({
			type: "delete",
			url: "/review/" + book_id,
		})
		.done(function (data, status, xhr) {
			if (callback) {
			   callback(data);
			}
		})
		.fail(function(xhr, status, err) {
			if (error) {
			error(err);
			}   
		});
	}
	
	return {
		addReview : addReview,
		getReview : getReview,
		updateReview : updateReview,
		deleteReview : deleteReview
	};
})();

