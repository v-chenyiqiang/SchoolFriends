/**
 *
 * 通用性强的Js函数
 */

/*
解析日期
 */
function dealTime(time){
	var dateTypeDate = "";
	var date = new Date();
	date.setTime(longTypeDate);
	dateTypeDate += date.getFullYear(); //年
	dateTypeDate += "-" + getMonth(date); //月
	dateTypeDate += "-" + getDay(date); //日
	dateTypeDate += " " + getHours(date); //时
	dateTypeDate += ":" + getMinutes(date);  //分
	dateTypeDate += ":" + getSeconds(date);  //分
	return dateTypeDate;
}
