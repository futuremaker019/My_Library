var collectionService = (function() {

	function addCollection(collection, csrfHeaderName, csrfTokenValue, callback, error) {
	  $.ajax({
	     method : "POST",
	     url : "/collection",            
	     beforeSend : function(xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
         },
	     data: JSON.stringify(collection),
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
    
    function getList(book_id, callback, error) {
        $.getJSON("/books/" + book_id + "/collections",
            function (data, textStatus, jqXHR) {
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
	
	function getCollection(collection_id, callback, error) {
		$.getJSON("/collections/" + collection_id,
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
	
	function updateCollection(collection, csrfHeaderName, csrfTokenValue, callback, error) {
		let collection_id = collection.collection_id;

		$.ajax({
			type: "put",
			url: "/collections/" + collection_id,
			data: JSON.stringify(collection),
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

	function removeCollection(collection_id, csrfHeaderName, csrfTokenValue, callback, error) {
		$.ajax({
			type: "delete",
			url: "/collections/" + collection_id,
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
        addCollection : addCollection,
        getList : getList,
		getCollection : getCollection,
		updateCollection : updateCollection,
		removeCollection : removeCollection
	};
})();