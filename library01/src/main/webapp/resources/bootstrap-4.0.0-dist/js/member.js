var memberService = (function() {
	console.log("memberService activated");
	function verifyUserId(userId, header, token, callback, error) {
		$.ajax({
            type: "POST",
            url: "/member/identification",
            data: userId,
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                if(callback){
                	callback(data);
            	}
            }
 		});
	}
	
	function verifyEmail(email, header, token, callback, error){
		$.ajax({
            type: "POST",
            url: "/member/verification/email",
            data: email,
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                if(callback){
                	callback(data);
            	}
            }
 		});
	}

	return {
     	verifyUserId : verifyUserId,
     	verifyEmail : verifyEmail    
    }
})();