var memberService = (function() {
	console.log("memberService activated");
	function verifyUserId(userId, header, token, callback, error) {
		$.ajax({
            type: "POST",
            url: "/member/verification/userid",
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
	
	function verifyPassword(password, header, token, callback, error) {
		$.ajax({
            type: "POST",
            url: "/member/verification/password",
            data: password,
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
	
	function changePassword(password, header, token, callback, error) {
		$.ajax({
            type: "POST",
            url: "/member/password",
            data: password,
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
	
	function changeEmail(changedEmail, header, token, callback, error) {
		$.ajax({
            type: "POST",
            url: "/member/email",
            data: changedEmail,
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
     	verifyEmail : verifyEmail,
     	verifyPassword : verifyPassword,
     	changePassword : changePassword,
     	changeEmail : changeEmail     
    }
})();