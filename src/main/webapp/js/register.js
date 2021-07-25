/**
 * 
 */
var yzm_js;
var yzm_js_count;
function send_yzm_djs(){
	yzm_js=setInterval("djs()",1000);
}
function  yz_acc(){
	var acc=$("#input_account").val();
	if(acc.length==0){
		$("#error_acc").text("账号不能为空");
		$("#yz_1").text("×");
		$("#yz_1").css("color","red")
		return false;
	}
	if(acc.length>20||acc.length<4){
		$("#error_acc").text("账号长度只能为4-20");
		$("#yz_1").text("×");
		$("#yz_1").css("color","red")
		return false;
	}
	$("#yz_1").text("√");
	$("#error_acc").text("");
	$("#yz_1").css("color","green");
	return true;
}
function  yz_pass01(){
	var pass01=$("#input_pass01").val();
	if(pass01.length==0){
		$("#error_pass01").text("密码不能为空");
		$("#yz_2").text("×");
		$("#yz_2").css("color","red")
		return false;
	}
	if(pass01.length>30||pass01.length<6){
		$("#error_pass01").text("密码长度只能为6-30");
		$("#yz_2").text("×");
		$("#yz_2").css("color","red")
		return false;
	}
	$("#yz_2").text("√");
	$("#error_pass01").text("");
	$("#yz_2").css("color","green");
	return true;
}
function  yz_pass02(){
	var pass02=$("#input_pass02").val();
	if(pass02.length==0){
		$("#error_pass02").text("密码不能为空");
		$("#yz_3").text("×");
		$("#yz_3").css("color","red")
		return false;
	}
	if(pass02.length>30||pass02.length<6){
		$("#error_pass02").text("密码长度只能为6-30");
		$("#yz_3").text("×");
		$("#yz_3").css("color","red")
		return false;
	}
	var pass01=$("#input_pass01").val();
	if(pass02!=pass01){
		$("#error_pass02").text("前后密码不一致");
		$("#yz_3").text("×");
		$("#yz_3").css("color","red")
		return false;
	}
	$("#yz_3").text("√");
	$("#yz_3").css("color","green");
	$("#error_pass02").text("");
	return true;
}


function  yz_yzm(){
	var yzm=$("#input_yzm").val();
	if(yzm.length==0){
		$("#error_yzm").text("验证码不能为空");
		$("#yz_5").text("×");
		$("#yz_5").css("color","red")
		return false;
	}
	if(yzm.length!=6){
		return false;
	}
	$("#yz_5").text("√");
	$("#yz_5").css("color","green")
	return true;
}

function  yz_logon(){
	isacc=yz_acc();
	ispass01=yz_pass01();
	ispass02=yz_pass02();
	isyzm=yz_yzm();
	if((isacc==ispass01)&&(ispass01==ispass02)){
		if($("#input_yzm").val().length!=6){
			$("#error_yzm").text("验证码错误");
			$("#yz_5").text("×");
			$("#yz_5").css("color","red")
			return false;
		}

		return true;
	}
	else{
		return false;
	}
}
function send_yzm(){
	$.post("/yz/send_yzm",{
		acc:$("#input_account").val(),
		email:$("#input_email").val()
	},
		function(data,status){
			if(status=="success"){
				if(data["code"]==1){
					alert("验证码已经发送(如长时间没收到,请检查是否被屏蔽垃圾信封里面)");
					$("#send_yzm").text("60");
					send_yzm_djs();
				}
				else{
					alert(data["msg"]);
				}
			}

		});
}

function logon(){
	$.post("/yz/update_acc",{
			acc:$("#input_account").val(),
			email:$("#input_email").val(),
			pass:$("#input_pass01").val(),
			yzm:$("#input_yzm").val()
		},
		function(data,status){
			if(status=="success"){
				if(data["code"]==1){
					alert(data["msg"]);
					window.location.replace("/chat.html");
				}
				else {
					alert(data["msg"]);
				}

			}
			$("#sub").css("opacity","1");
		});
}
///register/logon
/*验证中文
 * 
 */
function checkInput(str,e){
	var temp="";
	for(var i=0;i<str.length;i++) 
	     if(str.charCodeAt(i)>0&&str.charCodeAt(i)<255) 
	        temp+=str.charAt(i) 
	 temp=temp.replace(/\s/g,'');
	 if(str.length!=temp.length){
		 $(e.target).val(temp);
	 }
}
function leave_input(e){
	e_name=$(e.target).attr('id');
	if(e_name=='input_account'){
		yz_acc();
	}
	else if(e_name=='input_pass01'){
		yz_pass01();
	}
	else if(e_name=='input_pass02'){
		yz_pass02();
	}
	else if(e_name=='input_yzm'){
		yz_yzm();
	}
	else{
	}
}
function djs(){
	var nr=$("#send_yzm").text();
	if(yzm_js_count>=0){
		yzm_js_count++;
	}
	if(yzm_js_count>2){
		yzm_js_count=-1;
		$("#error_email").text("");
	}
	nr--;
	if(nr==-1){
		$("#send_yzm").text("重新发送");
		clearInterval(yzm_js);
	}
	else
	$("#send_yzm").text(nr);
}
$(document).ready(function(){
	$("#send_yzm").click(function(){
		var nr=$("#send_yzm").text();
		if(nr=='重新发送'||nr=='发送验证码'){

			send_yzm();
		}
		else{
			yzm_js_count=0;
			$("#error_email").text(nr+"秒才可以重新发送哦!");
		}
	});
	$("input:not(#input_name)").keyup(function(e){
		checkInput($(e.target).val(),e);	
	});
	$("input").blur(function (e){
		leave_input(e);
	});
	$("#sub").click(function (){

		iszj=$("#sub").css("opacity");
		if(iszj=="0.2"){
			return false;
		}

		is=yz_logon();
		if(is){
			$("#sub").css("opacity","0.2");
			logon();
		}
	});
});