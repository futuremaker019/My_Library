var sentenceService = (function() {

	function addSentence(sentence, csrfHeaderName, csrfTokenValue, callback, error) {
	  $.ajax({
	     method : "POST",
	     url : "/sentence",            
	     beforeSend : function(xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
         },
	     data: JSON.stringify(sentence),
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
        $.getJSON("/books/" + book_id + "/sentences",
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
	
	function getSentence(sno, callback, error) {
		$.getJSON("/sentences/" + sno,
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
	
	function updateSentence(sentence, callback, error) {
		let sentence_id = sentence.sentence_id;

		$.ajax({
			type: "put",
			url: "/sentences/" + sentence_id,
			data: JSON.stringify(sentence),
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

	function removeSentence(sentence_id, callback, error) {
		$.ajax({
			type: "delete",
			url: "/sentences/" + sentence_id,
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
        addSentence : addSentence,
        getList : getList,
		getSentence : getSentence,
		updateSentence : updateSentence,
		removeSentence : removeSentence
	};
})();