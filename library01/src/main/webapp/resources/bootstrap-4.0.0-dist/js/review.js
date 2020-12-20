var reviewService = (function() {

	function addReview(review, callback, error) {
	  $.ajax({
	     method : "POST",
	     url : "/review/new",            
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
	
	function getReview(bno, callback, error) {
		$.getJSON("/review/" + bno + ".json",
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
		let bno = review.bno;

		$.ajax({
			type: "put",
			url: "/review/admin/" + bno,
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

	function deleteReview(bno, callback, error) {
		$.ajax({
			type: "delete",
			url: "/review/admin/" + bno,
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

const bookService = (function() {
	function removeBook(bno, callback, error) {
		$.ajax({
			type: "delete",
			url: "/book/remove/" + bno
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
		removeBook : removeBook
	};
})();

