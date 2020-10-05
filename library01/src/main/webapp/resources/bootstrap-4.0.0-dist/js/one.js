console.log("----one js active-----");

var oneService = (function() {

	function addReview(review, callback, error) {
	  console.log("review post activated");
	
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
		console.log("review get activated.");
		
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
	
	function updateReview(review, callback, error) {
		console.log("review update activated.");

		let bno = review.bno;

		$.ajax({
			type: "put",
			url: "/review/" + bno,
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
		console.log("review delete activated");

		$.ajax({
			type: "delete",
			url: "/review/" + bno,
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