/**
 * 
 */
function mouse_over_type(e){
	_name=$(e.target).attr('id');
	$("#"+_name).css("color","#000");
	$("#"+_name).css("background-color","#FFF");
	$("#"+_name).css("border-top","4px solid #f77825");
}


function mouse_out_type(e){
	_name=$(e.target).attr('id');
	$("#"+_name).css("color","#666");
	$("#"+_name).css("background-color","#BFAB86");
	$("#"+_name).css("border-top","");
}
$(document).ready(function(){
	var ymnr=$("#nr").val();
	$(".a_type").mouseover(function(e){
		_name=$(e.target).attr('id');
		if(_name!=ymnr)
		mouse_over_type(e);
	});
	$(".a_type").mouseout(function(e){
		_name=$(e.target).attr('id');
		if(_name!=ymnr)
		mouse_out_type(e);
	});

});