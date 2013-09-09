/* jquery functions 4 webinterface */
$(document).ready(function() {
		$('.taskform').ajaxForm({
			success : function() {
				alert("Send");
			}
		});
	});