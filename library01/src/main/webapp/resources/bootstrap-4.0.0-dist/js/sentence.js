console.log("----sentence js active-----");

var sentenceService = (function() {

	function addSentence(sentence, callback, error) {
	  console.log("sentence post activated");
	
	  $.ajax({
	     method : "POST",
	     url : "/sentence/new",            
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
    
    function getList(sentences, callback, error) {
        let bno = sentences.bno;
        let page = sentences.page || 1;

        $.getJSON("/sentence/" + bno + "/" + page + ".json",
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
		console.log("sentence get activated.");
		
		$.getJSON("/sentence/" + sno + ".json",
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
		console.log("sentence update activated.");

		let sno = sentence.sno;

		$.ajax({
			type: "put",
			url: "/sentence/" + sno,
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

	function removeSentence(sno, callback, error) {
		console.log("sentence delete activated");

		$.ajax({
			type: "delete",
			url: "/sentence/" + sno,
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