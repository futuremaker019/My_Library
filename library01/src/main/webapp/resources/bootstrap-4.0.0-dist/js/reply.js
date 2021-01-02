const replyService = (function() {
    function getReplies(param, callback, error) {
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

    function createReply(param, header, token, callback, error) {
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        $.ajax({
            type: "POST",
            url: "/reply/creation",
            data: JSON.stringify(param),
            contentType: "application/json; charset=utf-8",
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                if(callback) {
                    callback(data);
                }
            }
        });
    }

    function deleteReply(reply_id, header, token, callback, error) {
        $.ajax({
            type: "delete",
            url: "/replies/" + reply_id,
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                if(callback) {
                    callback(data);
                }
            }
        });
    }

    function modifyReply(param, header, token, callback, error) {
        $.ajax({
            type: "put",
            url: "/replies/" + param.reply_id,
            data : JSON.stringify(param),
            contentType : "application/json; charset=utf-8",
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                if(callback) {
                    callback(data);
                }
            }
        });
    }

    return {
        createReply : createReply,
        getReplies : getReplies,
        deleteReply : deleteReply,
        modifyReply : modifyReply
    }
})();