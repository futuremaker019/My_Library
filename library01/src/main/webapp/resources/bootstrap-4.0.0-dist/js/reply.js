console.log("reply.js activated.");

var replyService = (function() {
    function getReplies(param, callback, error) {
        console.log("get Replise");

        $.getJSON("/board/" + param.board_id + "/replies/page/" + param.page,
            function (data) {
                if(callback){
                    callback(data)
                }
            }
        )
        .fail(function (xhr, status, err) {
            if(error) {
            error(err);
            }
         });
    }

    function createReply(reply, callback, error) {
        console.log("add reply.");
        
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        $.ajax({
            type: "POST",
            url: "/replis/creation",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if(callback) {
                    callback(data);
                }
                console.log(data);
            }
        });
    }

    return {
        createReply : createReply,
        getReplies : getReplies
    }
})();