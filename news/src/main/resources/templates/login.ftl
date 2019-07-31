<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link href="/plugins/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="/plugins/h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="/plugins/h-ui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/plugins/h-ui/icons/iconfont.css" rel="stylesheet" type="text/css" />
<link href="/css/login.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript" src="/js/crypto-js-min.js" ></script>
<title>后台管理系统</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<!-- <div class="header"> </div> -->
<div class="headText">上海滩 后台管理系统</div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
  	<div class="err-wrap" style="<#if (!error??)||(error=='')>display:none </#if>">
        <span id="err-msg">${error}</span>
    </div>
    <form class="form form-horizontal" action="/loginin" method="post" id="loginForm">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="username" name="username" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" id="j_captcha" name="j_captcha" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img src="/captcha/generate" id="imageVerifyBox"> <a id="kanbuq" href="javascript:refreshcode();">看不清，换一张</a> </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
           <input type="hidden" id="token" value="<#if Session['token']?exists>${Session['token']}</#if>">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input type="button" class="btn btn-success radius size-L" id="login" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">版权归诚迈科技（南京）股份有限公司所有</div>
<script type="text/javascript" src="/plugins/jquery.min.js"></script>
<script type="text/javascript" src="/plugins/h-ui/js/H-ui.min.js"></script>
<#-- jQuery Growl -->
<link rel="stylesheet" href="/plugins/jquery.growl.css"/>
<script src="/plugins/jquery.growl.js" ></script>
<script type="text/javascript" src="/js/utils.js" ></script>
<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>