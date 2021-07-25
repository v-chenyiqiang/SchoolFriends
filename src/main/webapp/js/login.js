/**
 * 
 */

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
function login(){
	$.post("/yz/login",{
			acc:$("#input_account").val(),
			pass:$("#input_pass01").val(),
		},
		function(data,status){
			if(status=="success"){
				if(data["code"]==1){
					type=data["type"];
					if(type=="redirect"){
						window.location.replace(data["msg"]);
					}
				}
				else{
					alert(data["msg"]);
				}
			}
			$("#sub").css("opacity","1");
		});
}
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

function  yz_login(){
	isacc=yz_acc();
	ispass01=yz_pass01();

	if((isacc==ispass01)&&isacc==true){
		return true;
	}
	else{
		return false;
	}
}

$(document).ready(function(){
	$("input").keyup(function(e){
		checkInput($(e.target).val(),e);
	});
	$("#sub").click(function(){
		iszj=$("#sub").css("opacity");
		if(iszj=="0.2"){
			return false;
		}
		is=yz_login();
		if(is){
			$("#sub").css("opacity","0.2");
			login();
		}
	});
});