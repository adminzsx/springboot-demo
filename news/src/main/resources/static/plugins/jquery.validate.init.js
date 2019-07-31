/**
 * 中文化提示
 */
$.extend( $.validator.messages, {
    required: "必填项目",
	remote: "请修正该项目",
	email: "请输入正确格式的邮件地址",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的ISO日期格式",
	number: "请输入合法的数字",
	digits: "请输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "两次输入的值必须相同",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: jQuery.validator.format("最多只能输入{0}个字符"),
	minlength: jQuery.validator.format("请最少输入{0}个字符"),
	rangelength: jQuery.validator.format("请输入长度介于 {0} 和  {1} 之间的字符"),
	range: jQuery.validator.format("请输入一个介于 {0} 和 {1}之间的值"),
	max: jQuery.validator.format("不能大于{0}"),
	min: jQuery.validator.format("不能小于{0}")
});

/**
 * 重写
 */
jQuery.extend(jQuery.validator.defaults, {
    errorElement : 'span',
    errorClass : 'help-block',
	highlight : function( element, errorClass, validClass ) {
	 	$(element).closest('.form-group').addClass('has-error');
	},
    success : function(label) {
        label.closest('.form-group').removeClass('has-error');
        label.remove();
	}
});

/**
 * 调试模式
 */
$.validator.setDefaults({
	debug:true
});

// 自定义校验方法
/**
 * 以英文字母开头，只能包括英文字母、数字、下划线
 */
jQuery.validator.addMethod("loginname", function(value, element) {
	return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]*$/.test(value); // ^[u0391-uFFE5w]+$/
																			// 中文数字字母下划线
}, "以英文字母开头，只能包括英文字母、数字、下划线");

jQuery.validator.addMethod("strnum", function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}, "只能包含英文字母和数字");

jQuery.validator.addMethod("zip", function(value, element) {
	return this.optional(element) || /^[0-9]{6}$/.test(value);
}, "请填写正确的邮政编码");

jQuery.validator.addMethod("chinese", function(value, element) {
	return this.optional(element) || /^[\u4E00-\u9FA5]+$/.test(value);
}, "请输入汉字");

jQuery.validator.addMethod("idcard", function(value, element) {
	return this.optional(element) || isIdCardNo(value);
}, "请填写正确的身份证号码");

jQuery.validator.addMethod("nospec", function(value, element) {
	return this.optional(element) || /^[u0391-uFFE5w]+$/.test(value);
}, "不能包含特殊符号");

jQuery.validator.addMethod("mob", function(value, element) {
	var length = value.length;
	return this.optional(element) || (length == 11 && /(^1[3-9][0-9]{9}$)/.test(value));
}, "请输入正确的手机号码");

// 电话号码验证
jQuery.validator.addMethod("tel", function(value, element) {
	return this.optional(element) || /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(value);
}, "请输入正确的固定电话号码");

jQuery.validator.addMethod("telOrMob", function(value, element) {
    return this.optional(element) || /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^1[2-9][0-9]{9}$)/.test(value);
}, "请输入正确的固定电话号码或手机号码");

// 金额 10亿级带2位小数
jQuery.validator.addMethod("money", function(value, element) {
	return this.optional(element) || /^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/.test(value);
}, "请输入正确金额");

// 字母数字下划线 8~16
jQuery.validator.addMethod("password", function(value, element) {
	return this.optional(element) || /^((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/.test(value);
}, "至少包含字母、数字或特殊符号的2种组合且长度不少于8");

//字母数字混合 8~20
jQuery.validator.addMethod("sim_password", function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9]^.{6,20}$/.test(value);
}, "密码为6-20个字母或数字组成，区分大小写");

//字母数字混合 6~32位
jQuery.validator.addMethod("ipCheck", function (value, element) {
	return this.optional(element) || /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/.test(value);
}, "请填写合法的IP地址");

//字母数字混合 6~32位
jQuery.validator.addMethod("ipRangeCheck", function (value, element) {
	return this.optional(element) || /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/.test(value);
}, "请填写合法的IP地址");

// 竖线风格
jQuery.validator.addMethod("vLineSplit", function (value, element) {
	return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5]+(\|[a-zA-Z0-9\u4e00-\u9fa5]+)*$/.test(value);
}, "只允许字母、数字、汉字。多个时请用竖线分割");

// 1位数字或大写字母
jQuery.validator.addMethod("oneNumOrLetter", function(value, element) {
	return this.optional(element) || /^[1-9A-Z]?$/.test(value);
}, "1到9的数字或1位大写字母");

// 营业员账户密码6位数字
jQuery.validator.addMethod("salesmanPwd", function(value, element) {
	return this.optional(element) || /^[0-9]{6}$/.test(value);
}, "请输入6位数字");

jQuery.validator.addMethod("strNumCn", function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(value);
}, "请输入数字、字母或汉字");


jQuery.validator.addMethod("strTime", function(value, element) {
	return this.optional(element) || /([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])/.test(value);
}, "请按照格式填写");


jQuery.validator.addMethod("nameCheck", function(value, element)
{
    return this.optional(element) || (/^[A-Za-z0-9\u4e00-\u9fa5]+$/.test(value));},"名字只能是数字、英文、汉字");

jQuery.validator.addMethod("companyCheck", function(value, element)
{
    return this.optional(element) || (/^[|A-Za-z0-9\u4e00-\u9fa5]+$/.test(value));},"名字只能是数字、英文、汉字");

jQuery.validator.addMethod("timeQuantum", function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(value);
}, "请输入正确的时间段");


jQuery.validator.addMethod("resolution", function(value, element) {
	return this.optional(element) || /^\b[1-9]\d{2,3}\b\*\b[1-9]\d{2,3}\b$/.test(value);
}, "请输入正确的分辨率,形如 1920*1080");
//经度
jQuery.validator.addMethod("longitude", function(value, element)
{
    return this.optional(element) || (/^((\d|[1-9]\d|1[0-7]\d)[,](\d|[0-5]\d)[′](\d|[0-5]\d)(\.\d{1,2})?[\″]?$)/.test(value));
},"经度格式不正确");

//纬度
jQuery.validator.addMethod("latitude", function(value, element)
{
    return this.optional(element) || ( /^(([\u5317\u7eac])+[：]+(\d|[1-8]\d)[°](\d|[0-5]\d)[′](\d|[0-5]\d)(\.\d{1,2})?[\″]?$)/.test(value));
},"纬度格式不正确");

//百度地图坐标
jQuery.validator.addMethod("mapPoint", function(value, element)
{
    return this.optional(element) || (/^([1-9]{2})[.](\d{1,13})[,]([1-9]{2})[.](\d{1,13})$/.test(value));
},"坐标格式不正确");

//大于
jQuery.validator.addMethod("gt", function(value, element,param) {
	var target = $(param[0]);
	return Number(value) > Number(target.val());
}, $.validator.format("输入值必须大于{1}!"));
//小于
jQuery.validator.addMethod("lt", function(value, element,param) {
	var target = $(param[0]);
	var max =  Number(target.val());
	if(max == 0)
		return true;
	return Number(value) < Number(target.val());
}, $.validator.format("输入值必须小于{1}!"));

jQuery.validator.addMethod("mac", function(value, element) {
	return this.optional(element) || /^([A-Fa-f0-9]{2}-){5}[A-Fa-f0-9]{2}$/.test(value);
}, "请输入正确的MAC地址");

//小于
jQuery.validator.addMethod("lt", function(value, element,param) {
	var target = $(param[0]);
	var max =  Number(target.val());
	if(max == 0)
		return true;
	return Number(value) < Number(target.val());
}, $.validator.format("输入值必须小于{1}!"));

//select2下拉框校验
jQuery.validator.addMethod("select2Valid", function(value, element) {
    if(value==null || value==""){
        return false;
    }
    return true
}, "请选择");

/**
 * 身份证号码验证方法
 */
function isIdCardNo(num) {
	var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
		return false;
	}
	// check and set value
	for(i=0;i<intStrLen;i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}
	if (intStrLen == 18) {
		// check date
		var date8 = idNumber.substring(6,14);
		if (isDate8(date8) == false) {
			return false;
		}
		// calculate the sum of the products
		for(i=0;i<17;i++) {
		lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = parityBit[lngProduct % 11];
		// check last digit
		if (varArray[17] != intCheckDigit) {
			return false;
		}
	}else{ // length is 15
		// check date
		var date6 = idNumber.substring(6,12);
		if (isDate6(date6) == false) {
			return false;
		}
	}
	return true;
}

function isDate6(sDate) {
	if(!/^[0-9]{6}$/.test(sDate)) {
		return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	if (year < 1700 || year > 2500) return false
	if (month < 1 || month > 12) return false
	return true
}

function isDate8(sDate) {
	if(!/^[0-9]{8}$/.test(sDate)) {
		return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	day = sDate.substring(6, 8);
	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]
	if (year < 1700 || year > 2500) return false
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12) return false
	if (day < 1 || day > iaMonthDays[month - 1]) return false
	return true
}
