$(document).ready(function() {
    
    //更新密码
    function updPwd(old_pwd,new_pwd,con_pwd){

        old_pwd = encrypt(old_pwd, $("#token").val());
        new_pwd = encrypt(new_pwd, $("#token").val());
        $.ajax({
            url:"/main/updatePwd",
            dataType:"json",
            type:"POST",
            data: {old_pwd:old_pwd,new_pwd:new_pwd,con_pwd:con_pwd},
            success:function(r){
                if(r.resultCode!=0){
                    $("#up_pwd").attr('disabled',false);
                    $("#up_pwd").val("提 交");
                    Msg.error(r.resultMsg);
                }else{
                    $("#up_pwd").attr('disabled',false);
                    $("#up_pwd").val("提 交");
                    Msg.alert("修改密码成功，请重新登录",function () {
                        window.location.href = '/login';
                    })

                }
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
                $("#up_pwd").attr('disabled',false);
                $("#up_pwd").val("提 交");
                Msg.error('修改密码失败!');
            }
        });
    }


    
});