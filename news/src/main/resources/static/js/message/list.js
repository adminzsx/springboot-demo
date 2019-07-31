var monitorTable;
/**
 * 初始化列表数据
 * @returns
 */
function initData() {
	monitorTable = $('#monitorTable').DataTable({
		serverSide: true,
		searching :false,
		paging: true,
		destroy: true,
		ajax: {
			url : "/message/messages"
        },
		"columns": [
			{"data" : "id","text":"center",title : "消息编号"},
			{"data" : "content","text":"center",title : "消息内容"},
			/*{"data" : "state",title : "状态",defaultContent:"", "render": function ( data, type, row ) {
				var stateStr = "";
				if(data == 1) {
					stateStr = "正常";
				}else if(data == 101) {
					stateStr = "设备离线";
				}else if(data == 102) {
					stateStr = "设备息屏";
				}else {
					stateStr = "未知状态码：" + data;
				}
				return stateStr; 
			}},*/
			{"data" : "createtime",title : "创建时间","render" : function(data, type, row) {
				var dateee = new Date(data).toJSON();
				return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
			}},
//			{"data" : "id",title : "操作","render": function ( data, type, row ) {
//				var editObj = '<a title="编辑" href="javascript:;" onclick="member_edit(' + data + ')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>';
//				var delObj = '<a title="删除" href="javascript:;" onclick="member_del(' + data + ')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>';
//            return editObj+delObj;
//			}}
		]
	});
}
function reload() {
	monitorTable.ajax.reload();
}
$(function() {
	initData();
});
