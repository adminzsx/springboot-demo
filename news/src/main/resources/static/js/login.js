$(function() {
	$("#login").click(function(){
		var username=$("#username").val();
        var password=$("#password").val();
        var j_captcha=$("#j_captcha").val();
        if($.trim(username).length==0){
            Msg.error("登录名不能为空");
            return;
        }
        if($.trim(password).length==0){
        	Msg.error("密码不能为空");
            return;
        }
        if($.trim(j_captcha).length!=4){
        	Msg.error("请填写完整的验证码");
            return;
        }
        var aesEncrypt = encrypt($("#password").val(), $("#token").val());
        $("#password").val(aesEncrypt);
        $("#loginForm").submit();
    });
	$("body").keydown(function(event) {
		if (event.keyCode == "13") {//keyCode=13是回车键
			$("#login").click();
		}
	});
})
function refreshcode() {
    document.getElementById("imageVerifyBox").src = "/captcha/generate?s=" + Math.random();
}