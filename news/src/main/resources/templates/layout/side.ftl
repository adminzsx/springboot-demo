<link href="/css/toastr.css" rel="stylesheet" type="text/css" />
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 人员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="/employee/toListPage" data-title="服务人员管理" href="javascript:void(0)">服务人员管理</a></li>
				</ul>
			</dd>
		</dl>
		<#if (userRole)=='ROLE_admin'>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 设备管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="/monitor/toListPage" data-title="设备监控" href="javascript:void(0)">设备监控</a></li>
				</ul>
			</dd>
		</dl>
		</#if>
		<#if (userRole)=='ROLE_admin'>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe622;</i> 消息中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<#--<li><a data-href="/message/toMessages" data-title="消息告警" href="javascript:void(0)">消息告警</a></li>-->
					<li><a data-href="/message/toMsgPage" data-title="设备告警" href="javascript:void(0)">设备告警</a></li>
				</ul>
			</dd>
		</dl>
		</#if>
		<#if (userRole)=='ROLE_admin'>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe622;</i> 支付功能<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="/pay/toListPage" data-title="支付记录查询" href="javascript:void(0)">支付记录查询</a></li>
				</ul>
			</dd>
		</dl>
		</#if>
		<#if (userRole)=='ROLE_admin'>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe622;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="/user/toUserListPage" data-title="用户管理" href="javascript:void(0)">用户管理</a></li>
				</ul>
			</dd>
		</dl>
		</#if>
		<#if (userRole)=='ROLE_admin'>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe622;</i> 客户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="/customer/toCustomerListPage" data-title="客户管理" href="javascript:void(0)">客户管理</a></li>
				</ul>
			</dd>
		</dl>
		</#if>
</div>
 <input type="hidden" id="userRole" value="<#if Session['userRole']?exists>${Session['userRole']}</#if>">
</aside>

<script>
	var mqttUsername = '${Session.mqttUsername}';
	var mqttPassword = '${Session.mqttPassword}';
	var mqttClientIp = '${Session.mqttClientIp}';
	var mqttClientPort = ${Session.mqttClientPort};
</script>
<script src="/js/message/paho-mqtt.js"></script>
<script src="/js/message/utility.js"></script>
<script src="/js/message/toastr.js"></script>