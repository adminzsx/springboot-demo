<#--页面标题-->
<#assign title='修改密码'/>
<#--页面布局宏-->
<#--<#include "/layout/master.ftl"/>-->

<#--自定义CSS文件-->
<#--<#macro pageCss>-->
<#--grid+分页组件-->
<link rel="stylesheet" href="/css/securitypwd.css">
<style>
    .pwstrength_viewport_progress {
        padding-top: 7px;
        height: 34px;
        line-height: 34px;
        vertical-align: bottom;
    }

    .pwstrength_viewport_progress .progress {
        width: 250px;
        height: 24px;
        margin-left: 10px;
        margin-bottom: 2px;
        float: left;
    }

    .pagination > li > a, .pagination > li > span {
        position: relative;
        float: left;
        padding: 0px 4px;
        margin-left: -1px;
        line-height: 1.42857143;
        color: #337ab7;
        text-decoration: none;
        background-color: #fff;
        border: 1px solid #ddd;
    }
</style>
<#--</#macro>-->

<#-- 自定义js文件 -->
<#--<#macro pageJS>-->
<#--密码强度校验-->
<script src="/js/utils.js"></script>
<script src="/js/crypto-js-min.js"></script>
<script src="/js/plugins/pwdlength.js"></script>
<script src="/js/password.js"></script>
<script>

    $(document).ready(function () {
        /*密码强度验证*/
        var options = {};
        options.ui = {
            container: "#pwd-container",
            showVerdictsInsideProgressBar: true,
            viewports: {
                progress: ".pwstrength_viewport_progress"
            }
        };
        options.common = {
            debug: true,
            onLoad: function () {
                $('#messages').text('Start typing password');
            }
        };
        $('#new_pwd').pwstrength(options);

        //verdicts

        $('#new_pwd').bind('input propertychange', function () {
            if ($('#new_pwd').val() != "") {
                //alert($('#new_pwd').verdictText)
                $('#pwstrength_viewport').show();
            } else {
                $('#pwstrength_viewport').hide();
            }
        });

        $("#cancel_pwd").on('click', function () {
            window.location.href = '/main/index';
        })

    })

</script>
<#--</#macro>-->

<#-- 自定义内容 -->
<#--<#macro content>-->
<div class="nav-tabs-custom">
    <#--<ul class="nav nav-tabs">
        <li class="active"><a href="#tab1" data-toggle="tab" aria-expanded="true">变更密码</a></li>
    </ul>
    <div style="margin-left: 1%;">
        <p id="tip"></p>
    </div>-->
    <div class="tab-content">
    	<h4>修改密码</h4>
    	<div style="margin-left: 1%;">
	        <p id="tip"></p>
	    </div>
        <div class="tab-pane active" id="tab1">
            <div class="form-group mod-step-detail" style="position:relative; padding-top:40px;">
                <input type="hidden" id="token" value="${Session['set_pwd_token']}">
                <#if firstLogin??>
                    <div class="pass-input-container" style="font-size:15px;margin-left: 10%">
                        <a style="color: red;">第一次登录需修改密码</a>
                    </div>
                </#if>
                <input type="hidden" id="daxie" value="${data.uppercase}">
                <input type="hidden" id="xiaoxie" value="${data.lowercase}">
                <input type="hidden" id="shuzi" value="${data.num}">
                <input type="hidden" id="teshu" value="${data.specialchar}">

            <#--<form class="form-horizontal" id="password">-->
                <div class="pass-input-container">
                    <label class="pass-input-title" for="old_pwd">当前密码</label>
                    <input type="password" class="form-control pass-input left pass-input-error" name="old_pwd"
                           id="old_pwd" value="" autocomplete="off">
                    <div class="clear"></div>
                </div>

                <!--禁止自动带入用户名-->
                <div style="height:0px;border:none;overflow:hidden;">
                    <input autocomplete="off" type="text" maxlength="10"/>
                </div>

                <div id="pwd-container" class="pass-input-container">
                    <label class="pass-input-title" for="new_pwd">新密码</label>
                    <input type="password" class="form-control pass-input left pass-input-error" name="new_pwd"
                           id="new_pwd" value="" autocomplete="off">
                    <div id="pwstrength_viewport" class="pwstrength_viewport_progress" style="display:none"></div>
                    <div class="clear"></div>
                </div>

                <!--禁止自动带入用户名-->
                <div style="height:0px;border:none;overflow:hidden;">
                    <input autocomplete="off" type="text" maxlength="10"/>
                </div>

                <div class="pass-input-container">
                    <label class="pass-input-title" for="con_pwd">确认新密码</label>
                    <input type="password" class="form-control  pass-input left" name="password_again" id="con_pwd"
                           value="" autocomplete="off">
                    <div class="clear"></div>
                </div>
                <div class="m_l80 col-md-5">
                    <button id="up_pwd" class="btn btn-primary"><i class="fa fa-save"></i> 保 存</button>
                    <#--<button id="cancel_pwd" name="cancel_pwd" class="btn btn-default btn-flat"><i
                            class="fa fa-close"></i> 取 消
                    </button>-->
                </div>
            <#--</form>-->
            </div>
        </div>
    </div>
</div>

<#--</#macro>-->
