$(function(){
	//修改密码模块
	$('#updatePasswordForm').bootstrapValidator({
		message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	oldPd: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and can\'t be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 12,
                        message: 'The password must be more than 6 and less than 12 characters long'
                    }
                }
            },		
            newPd: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and can\'t be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 12,
                        message: 'The password must be more than 6 and less than 12 characters long'
                    }
                }
            },		
            confirmPd: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and can\'t be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 12,
                        message: 'The password must be more than 6 and less than 12 characters long'
                    },
                    identical: {
                        field: 'newPd',
                        message: 'The password and its confirm are not the same'
                    }
                }
            }
        }
	});	
	//
});