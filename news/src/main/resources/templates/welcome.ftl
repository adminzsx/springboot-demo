<link rel="stylesheet" type="text/css" href="/plugins/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/plugins/h-ui/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/plugins/h-ui/icons/iconfont.css" />


<script type="text/javascript" src="/plugins/jquery.min.js"></script>
<script type="text/javascript" src="/plugins/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/plugins/h-ui/js/H-ui.admin.js"></script>
<script type="text/javascript" src="/plugins/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/utils.js"></script>
<script type="text/javascript" src="/js/message/list.js"></script>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="reload()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe68f;</i> 刷新</a>
		</span>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort" id="monitorTable">
		<thead>
			<tr class="text-c">
				<th width="20%">消息编号</th>
				<th width="50%">消息内容</th>
				<th width="30%">创建时间</th>
			</tr>
		</thead>
		<tbody id="tableData">
			
		</tbody>
	</table>
	<div id="messagediv" style="top: 85%; left: 80%; display: block;"></div>
	</div>
</div>

