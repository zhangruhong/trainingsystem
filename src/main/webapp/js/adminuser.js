function addtranee() {
	var mydata = '{"loginname":"' + $("#nameInput_trainee").val() + '","phoneno":"' + $('#phoneInput_trainee').val() + '","email":"' + $('#emailInput_trainee').val() + '","role":"2"}';
	$.ajax({
		type : "POST",
		url : "add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			if (data.success == true) {
				location.reload(true);
			} else {
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert("-" + XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus + "-" + errorThrown);
		}
	});
};
function addtraner() {
	var mydata = '{"loginname":"' + $("#nameInput_trainer").val() + '","phoneno":"' + $('#phoneInput_trainer').val() + '","email":"' + $('#emailInput_trainer').val() + '","role":"1"}';
	$.ajax({
		type : "POST",
		url : "add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			if (data.success == true) {
				location.reload(true);
			} else {
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert("-" + XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus + "-" + errorThrown);
		}
	});
};

$(function(){
	//新建trainee模块验证
	$('#traineeaddform').bootstrapValidator({
		message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	name: {
                validators: {
                    notEmpty: {
                        message: 'The term name is required and can\'t be empty'
                    }
                }
            },
            phone: {
            	validators: {
            		notEmpty: {
            			message: 'The term name is required and can\'t be empty'
            		},
            		regexp: {
                        regexp: /^[0-9_\.]+$/,
                        message: 'The phone can only consist of number'
                    }
            	}
            },
            email: {
            	validators: {
            		notEmpty: {
            			message: 'The term name is required and can\'t be empty'
            		},
            		emailAddress: {
                        message: 'The input is not a valid email address'
                    }
            	}
            }
        }
	}).on('success.form.bv', function(e) {
		$('#addtraineemodel').modal('hide');
		addtranee();
		return false;
    });
	//新建trainer模块验证
	$('#traineraddform').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			name: {
                validators: {
                    notEmpty: {
                        message: 'The term name is required and can\'t be empty'
                    }
                }
            },
            phone: {
            	validators: {
            		notEmpty: {
            			message: 'The term name is required and can\'t be empty'
            		},
            		regexp: {
                        regexp: /^[0-9_\.]+$/,
                        message: 'The phone can only consist of number'
                    }
            	}
            },
            email: {
            	validators: {
            		notEmpty: {
            			message: 'The term name is required and can\'t be empty'
            		},
            		emailAddress: {
                        message: 'The input is not a valid email address'
                    }
            	}
            }	
		}
	}).on('success.form.bv', function(e) {
		$('#addtrainermodel').modal('hide');
		addtraner();
		return false;
	});
});