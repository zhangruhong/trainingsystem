function addTerm() {
	var mydata = '{"name":"' + $("#nameInput").val() + '","description":"' + $('#descriptionInput').val() + '"}';
	$.ajax({
		type : "POST",
		url : "add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			if (data.success == true) {
				var terms = data.terms;
				location.reload(true);
				$("#actiontip")
						.html(
								"<div class='alert alert-success alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>Success to add</div>");

			} else {
				$("#actiontip")
						.html(
								"<div class='alert alert-warning alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>error！failed to add！Pls. contact davisz@synnex.com。</div>");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#actiontip")
					.html(
							"<div class='alert alert-danger alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>error！failed to add！Pls. contact davisz@synnex.com。</div>");
			alert("-" + XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus + "-" + errorThrown);
		}
	});
}

$(function(){
	//新建term模块验证
	$('#createTermForm').bootstrapValidator({
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
            }
        }
	}).on('success.form.bv', function(e) {
		$('#myModal').modal('hide');
		addTerm();
		return false;
    });
});