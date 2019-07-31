/*避免console.log错误*/
if(typeof(console) == 'undefined')
	console = {log:function(){}}

/**
 * 全局的消息提示
 * 弹窗消息工具
 * 支持提醒,错误,警告,支持自定义延迟时间,消息位置等
 * @type {}
 */
var Msg ={
	/**
	 * 提醒消息
	 * @param {} msg 消息提内容
	 * @param {} option {duration:延迟关闭时间 0不关闭, title:标题, location:位置(right,left,center)}
	 * @return {}
	 */
	info : function(msg, option){
		var option = $.extend(option||{}, {title: msg||'无'});
		if(!option.message)
			option.message = "";
		return $.growl.notice(option);
	},

	/**
	 * 错误消息
	 * @param {} msg 消息提内容
	 * @param {} option {duration:延迟关闭时间 0不关闭, title:标题, location:位置(right,left,center)}
	 * @return {}
	 */
	error : function(msg, option){
		var opt;
		if(typeof(option) == "string"){
			var a = {
				title: (msg || ''),
				message: option
			};
			console.log(a);
			opt = a;
		}else{
			var a = {
				title: (msg || '错误')
			};
			opt = $.extend(option||{}, {title: msg || '无' });
		}

		if(!opt.title)
			opt.title = "错误";

		if(!opt.message)
			opt.message = "";
		console.log(opt);
		var g= $.growl.error(opt);
		return g;
	},

	/**
	 * 警告消息
	 * @param {} msg 消息提内容
	 * @param {} option {duration:延迟关闭时间 0不关闭, title:标题, location:位置(right,left,center)}
	 * @return {}
	 */
	warning : function(msg, option){
		var option = $.extend(option||{}, {title: msg||'无' })
		if(!option.message)
			option.message = "";
		return $.growl.warning(option);
	},

	/**
	 * 确认
	 */
	confirm: function(text, callback, modalId){
        var cid = '#' + (modalId || 'confirmDialog');
        $(cid).find('.modal-body').empty().append(text||'您确认操作吗？');
        $(cid).modal('show');
        $(cid + ' .btn-danger').unbind("click").click(function(){
            $(cid).modal('hide');
            if(callback){
                callback();
            }
        });
    },
    alert: function(text, callback){
        $("#alertDialog").find('.modal-body').empty().append(text);
        $("#alertDialog").modal('show');
        $('#alertDialog .btn-danger').unbind("click").click(function(){
            $("#alertDialog").modal('hide');
            if(callback){
                callback();
            }
        });
    }
}


/**
 * Ajax请求时间
 * @type {}
 */
var AjaxUtils= {

	/**
	 *
	 * @param {} url
	 * @param {} params
	 * @param {} success
	 * @author yebo
	 */
	post: function(url, params, success, error){
		this.asyncReq(url, params, success, error)
	},
	
	
	postJson: function(url,params, suc, err){
		if(url == null || url == ''){
			Msg.error('请求地址为空');
			return;
		}
		$.ajax({
			url:url,
			dataType:"json",
			contentType:"application/json", 
			type:"POST",
			timeout:30000,
			data: params,
			success:function(r,request){
				// SESSION失效判断
				if(r.resultCode==0){
					if (suc)
						suc(r)
				}else{
					if(err)
						err(r);
					else
						Msg.error(r? r.resultMsg: '提交失败');
				}
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				if(XMLHttpRequest.responseText && XMLHttpRequest.responseText.indexOf('登录')>=0){
					Msg.confirm("对话超时，请重新登录",function () {
						window.location.href="/login";
					})
				}
				if(err)
					err(XMLHttpRequest,textStatus,errorThrown);
			}
		});
	},

	/**
	 * Ajax Get Method
	 */
	get: function(url, params, success, error){
		var options = { data : params, type:'GET'};
		return this.ajax(url, options, success, error, $.noop)
	},

	/**
	 * 异步Ajax请求
	 *
	 * @param {Object}
	 *            url http服务的地址
	 * @param {Object}
	 *            params json对象
	 * @param {Object}
	 *            callback 正常回调callback
	 * @param {Object}
	 *            failed 回调failed
	 *  @author yebo
	 */
	asyncReq: function(url,p,suc,err,bef){
		if(url == null || url == ''){
			Msg.error('请求地址为空');
			return;
		}
		$.ajax({
			url:url,
			dataType:"json",
			type:"POST",
			timeout:30000,
			data: p,
			success:function(r,request){
				// SESSION失效判断
				if(r.resultCode==0){
					if (suc)
						suc(r)
				}else{
					if(err)
						err(r);
					else
						Msg.error(r? r.resultMsg: '提交失败');
				}
			},
			beforeSend:function(r){
				if(bef)
					bef(r)
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				if(XMLHttpRequest.responseText.indexOf('登录')>=0){
					Msg.confirm("对话超时，请重新登录",function () {
						window.location.href="/login";
					})
				}
				if(err)
					err(XMLHttpRequest,textStatus,errorThrown);
			}
		});
	},
	/**
	 * 同步请求
	 */
	req: function(url,p,suc,err,bef){
		if(url == null || url == ''){
			Msg.error('请求地址为空');
			return;
		}
		$.ajax({
			url:url,
			dataType:"json",
			type:"POST",
			timeout:30000,
			data: p,
			async:false,
			success:function(r,request){
				// SESSION失效判断
				if(r.resultCode==0){
					if (suc)
						suc(r)
				}else{
					if(err)
						err(r);
					else
						Msg.error(r? r.resultMsg: '提交失败');
				}
			},
			beforeSend:function(r){
				if(bef)
					bef(r)
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				if(XMLHttpRequest.responseText.indexOf('登录')>=0){
					Msg.confirm("对话超时，请重新登录",function () {
						window.location.href="/login";
					})
				}
				if(err)
					err(XMLHttpRequest,textStatus,errorThrown);
			}
		});
	},

	/**
	 * ajax全局初始化
	 * @author ligh
	 */
	init: function(config){
		//全局设置
		$.ajaxSetup($.extend({
			cache: true,
			global: false, //全局监控
			type: "POST",
			dataType :"json"
		}, config||{}));
	},

	/**
	 * ajax请求
	 * @author ligh
	 * @param {} url
	 * @param {} options 请求参数,参数包含成功,失败事件，则后面回调参数无效
	 * @param {} success 成功回调函数
	 * @param {} error   失败回调函数
	 * @return {}
	 */
	ajax: function(url, options, success, error, complete){
		var def = $.Deferred();
		var opt = $.extend(
			{
				url: Url.getWebRoot(url),
				success: function(data, textStatus, jqXHR){
					if(data!=null && data.resultCode==0)
					{
						(success||$.noop).call(null, data, this)
						if(def) def.resolve();
					}
					else
					{
						(error||$.noop).call(null, data, this)
						if(def) def.reject();
					}
				},
				error: function(){
					if(def) def.reject();
				},
				complete: function(XHR, TS){
					(complete||$.noop).call(null, XHR, TS, this)
				},
				statusCode: {
					404: function() {
						Msg.error('page not found');
					}
				}

			},
			options||{}
		)
		$.ajax(opt)

		return def.promise();
	}
}




/** 日期公用方法 */
var DateUtil = {
	/**
	 * 日期范围空间初始化方法
	 *
	 * @param {} from 开始ID
	 * @param {} to 结束ID
	 * @param {} fromday 开始值
	 * @param {} endday 结束值
	 * @param {} dep 结束深度 year到月 month到天
	 * @param {} dep 开始深度 year到月 month到天
	 */
	dateRange : function(from,to,fromday,endday,fomart,dep,st) {
		function startChange() {
			var startDate = start.value(),
				endDate = end.value();
			if (startDate) {
				startDate = new Date(startDate);
				startDate.setDate(startDate.getDate());
				end.min(startDate);
			} else if (endDate) {
				start.max(new Date(endDate));
			} else {
				endDate = new Date();
				start.max(endDate);
				end.min(endDate);
			}
		}

		/** 发布日期 结束*/
		function endChange() {
			var endDate = end.value(),
				startDate = start.value();
			if (endDate) {
				endDate = new Date(endDate);
				endDate.setDate(endDate.getDate());
				start.max(endDate);
			} else if (startDate) {
				end.min(new Date(startDate));
			} else {
				endDate = new Date();
				start.max(endDate);
				end.min(endDate);
			}
		}

		/** 发布日期开始按钮*/
		var start = $("#"+from).kendoDatePicker({
			change: startChange,
			format: fomart||"yyyy-MM-dd",
			start: st||'month',
			depth: dep||"year",
			value: fromday
		}).data("kendoDatePicker");

		/** 发布日期结束按钮*/
		var end = $("#"+to).kendoDatePicker({
			change: endChange,
			format: fomart||"yyyy-MM-dd",
			start: st||'month',
			depth: dep||"year",
			value:endday
		}).data("kendoDatePicker");
		start.max(end.value());
		end.min(start.value());
	},
	/**
	 *   功能:实现日期加减功能.
	 *   参数:interval,字符串表达式，表示要添加的时间间隔.
	 *   参数:number,数值表达式，表示要添加的时间间隔的个数.
	 *   参数:date,时间对象.
	 *   返回:新的时间对象.
	 *
	 *   var newDate = dateAdd( "d", 5, new Date());
	 */
	dateAdd:function (interval, number, date) {
		switch (interval) {
			case "y ": {
				date.setFullYear(date.getFullYear() + number);
				return date;
				break;
			}
			case "q ": {
				date.setMonth(date.getMonth() + number * 3);
				return date;
				break;
			}
			case "m ": {
				date.setMonth(date.getMonth() + number);
				return date;
				break;
			}
			case "w ": {
				date.setDate(date.getDate() + number * 7);
				return date;
				break;
			}
			case "d ": {
				date.setDate(date.getDate() + number);
				return date;
				break;
			}
			case "h ": {
				date.setHours(date.getHours() + number);
				return date;
				break;
			}
			case "m ": {
				date.setMinutes(date.getMinutes() + number);
				return date;
				break;
			}
			case "s ": {
				date.setSeconds(date.getSeconds() + number);
				return date;
				break;
			}
			default: {
				date.setDate(date.getDate() + number);
				return date;
				break;
			}
		}
	},
	/**
	 * 字符串转换为日期类型,兼容IE浏览器new Date问题
	 * @param {} time
	 * @return {}
	 */
	getDate: function(time){
//		var se = CommonUtil.browser()
//		if (!se.ie && !se.firefox) {
//	        return new Date(time);
//	    }
		var arr = time.split(/-|:| |\/|,/);//time.split(time.match(/\D+/g)[0]);
		var date = new Date(arr[0], arr[1] - 1, arr[2]);
		if(arr[3])
			date.setHours(arr[3])
		if(arr[4])
			date.setMinutes(arr[4])
		if(arr[5])
			date.setSeconds(arr[5])
		return date;
	},

	format : function(data, fmt){
		var o = {
			"M+" : data.getMonth()+1,                 //月份
			"d+" : data.getDate(),                    //日
			"h+" : data.getHours(),                   //小时
			"m+" : data.getMinutes(),                 //分
			"s+" : data.getSeconds(),                 //秒
			"q+" : Math.floor((data.getMonth()+3)/3), //季度
			"S"  : data.getMilliseconds()             //毫秒
		};
		if(/(y+)/.test(fmt))
			fmt=fmt.replace(RegExp.$1, (data.getFullYear()+"").substr(4 - RegExp.$1.length));
		for(var k in o)
			if(new RegExp("("+ k +")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		return fmt;
	},

	copy: function(data){
		var dt = new Date();
		dt.setTime(data.getTime());
		return dt;
	}
}


/*全局的AjaxForm
 * 表单验证、ajax提交
 */
FormUtils = {
	$reset: function(id){
		var scp = $('#' + id).scope();
		if(scp){
			for(v in scp){
				if(v.indexOf('$') <0){
					delete scp[v];
				}
			}
			scp.$digest();
		}
	},
	getValues : function(form){
		//$(formId).find('input')
		var values = $.extend({},$(form).data('formdata'));
		$(form).find('input,select,checkbox,radio,textarea')
			.each(function(i,element){
				var val = null;
				switch (element.tagName) {
					case "INPUT":
						if(typeof $(element).attr("type") =='undefined')
							return true;
						var type = $(element).attr("type").toUpperCase();
						switch (type) {
							case "CHECKBOX":
								val = $(element).prop('checked')?$(element).val() : null;
								break;

							case "RADIO":
								val = $(element).prop('checked')?$(element).val() : null; //keys for all radio[name] should match
								break;

							default:
								val = $(element).val();
								break;
						}
						break;

					case "SELECT":
						val = $.trim($(element).val());
						break;

					case "TEXTAREA":
						val = $(element).val();
						break;
				}

				if((element.name && val != null) || val===0 || val===false){
					if(values[element.name] && $.isArray(values[element.name]))
                        $.isArray(values[element.name])?values[element.name].push(val):values[element.name]=[values[element.name],val];
					else
						values[element.name]=val;
				}
			})
		return values;
	},
	/*提交到JSON格式*/
	jsonPost: function(jsonData,url,success,error){
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(jsonData),
			success : function(r){
				if(r.resultCode==0){
					if(success)
						success(r)
				}else{
					Msg.error(r.resultMsg);
					if(error)
						error(r)
				}
			},
			error : function(r){
				if(error)
					error(r)
			}
		})
	},

	/*提交到form*/
	post: function(form,url,success){
		var data = FormUtils.getValues(form);
		$.post(url,
			data,
			function(r){
				if(r.resultCode==0){
					success(r)
				}else{
					Msg.error(r.resultMsg)
				}
			});
	},

	/*提交到form*/
	restPost: function(form,url,success){
		var data = FormUtils.getValues(form);
		FormUtils.jsonPost(data,url,success);
	},

	/**表单赋值**/
	setValue: function(form, data){
		$(form).data('formdata',data);
		$(form).find('input,select,checkbox,radio,textarea')
			.each(function(i, element){
				var ele$ = $(element);
				var val = FormUtils._getVal(ele$, data);
				if(val instanceof Function)
					return true;

				switch (element.tagName) {
					case "INPUT":
						if(ele$.attr("type")==null){
                            break;
						}
						var type = ele$.attr("type").toUpperCase();
						switch (type) {
							case "CHECKBOX":
								if(val){
									if(ele$.iCheck)
										ele$.iCheck('check');
									ele$.attr('checked',true);
								}else{
									if(ele$.iCheck)
										ele$.iCheck('uncheck');
									ele$.removeAttr('checked');
								}
								break;

							case "RADIO":
								if(val)ele$.val(val);
								break;
							case "TEXT":
								if(val)
									ele$.val(val);
								else
									ele$.val('');
								break;
							case "FILE":
                                ele$.val('');
                                break;
							default:
								if(val!=null)
									ele$.val(val);
								break;
						}
						break;

					case "SELECT":
						if(val)$(element).val(val);
						break;

					case "TEXTAREA":
						if(val)$(element).val(val);
						break;
				}
			});
	},
	_getVal: function(element, data){
		var name = element.attr("name");
		if(name!=null && name!='' && (data||{})[name]!=null)
		{
			var val = (data||{})[name];
			if(val instanceof Function)
			{
				(val||$.noop).call(null, $(element))
				return Function
			}
			else if(val instanceof Object)
			{
				return val.id
			}else{
				return val;
			}
		}
		return false;
	},

	create: function(url, array, target){
		var form = $('#_seq_radom_form');
		if(form && form.length>0){
			form.empty();
		}else{
			form = $('<form id="_seq_radom_form"></form>');
			$('body').append(form)
		}
		var csrf_token = $("meta[name='_csrf']").attr("content");
		var csrf_header = $("meta[name='_csrf_header']").attr("content");
		form.attr('method', 'get');
		form.attr('target', target||'_self');
		form.attr("action", Url.getWebRoot(url));
		if($.isArray(array))
			$.each(array, function(i, d){
				var data = d.split(',');
				if(typeof data=='undefined' || data.length<2)
					throw new Error('form data validate is false :'+data);
				var i = d.indexOf(',')
				form.append($('<input name="'+d.substr(0,i)+'" type=text value="'+d.substring(i+1)+'" />'));
			});
		form.append($('<input name="'+csrf_header+'" type=text value="'+csrf_token+'" />'));
		form.submit();
	}
};

var getBrowsVersion = function(){
	var browser=navigator.appName
	var b_version=navigator.appVersion
	var version=b_version.split(";");
	var trim_Version=version[1].replace(/[ ]/g,"");
	if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE8.0")
	{
		return "IE8"
	}
	return null;
};

GridUtils = {
	getHeight : function () {
		var h = ('IE8' == getBrowsVersion()?80:230);
		return $(window).height() - h  - $('#list-sch-form').height() + 'px';
	},
	build : function(_opts){

		var opts = $.extend({
			url: '',
			metaCls: null,
			autoLoad:true,
			checkbox: false,
			checkWidth: 10,
			cToggled: $.noop,
            confirmDeleting:true,
			height : GridUtils.getHeight(),
			grid:{
				id : "grid",
				fields: [
					{ name: "Name", type: "text", width: 150 },
					{ name: "Age", type: "number", width: 50 },
					{ name: "Address", type: "text", width: 200 },
					{ name: "Country", type: "text" },
					{ name: "Married", type: "checkbox", title: "Is Married" }
				]
			},
			page:{
				id:"pageBar"
			},
			getQ: function(){
				return {};
			}
		},_opts);

		/*checkbox*/
		if(opts.checkbox){
			opts.grid.fields.length=(opts.grid.fields.length+1)
			for(var i=opts.grid.fields.length-1;i>0;--i)
				opts.grid.fields[i]=opts.grid.fields[i-1];
			var __all_chk = $('<input type="checkbox" name="__all_chk" class="minimal" style="display:none;">')
			__all_chk.on('change',function(){
				var checked = this.checked;
				$("#" + opts.grid.id).find('input[type=checkbox][name=__item_chk]').each(function(i,o){
					if(checked)
						$(o).iCheck('check')
				})
			});
			opts.grid.fields[0]={
				type: "checkbox",
				title: __all_chk,
				width: opts.checkWidth,
				sorting: false,
				itemTemplate:function(i,o){
					return $('<input type="checkbox" name="__item_chk" class="minimal">').data('i',o).on("ifChanged",function(e){
						var $ck =$(e.target);
						var $tr = $ck.parent().parent().parent();
						if(e.target.checked){
							if($tr.is("tr")){
								$tr.addClass("jsgrid-chose-row");
							}
						}else{
							if($tr.is("tr")){
								$tr.removeClass("jsgrid-chose-row");
							}
						}
						if(opts.onSelect && typeof opts.onSelect =="function"){
							opts.onSelect(o,e.target.checked,e);
						}
					});
				}
			}
		}

		/*总控制器*/
		var ctrl = {
			url : opts.url,
			q: {},
			loadData : function( page , q, callback){
				if(page)
					pgOpt.currentPage = page;
				else
					page = pgOpt.currentPage;

				ctrl.q = q? q : ctrl.q;
				$.extend(ctrl.q, opts.getQ());

				var start = page;
				var limit =  pgOpt.pageSize;

				var success = function(r){
					var total = 0;
					if(r.resultObj &&r.resultObj.total){
						total = r.resultObj.total;
					}

					pgOpt.totalPages = Math.ceil(total / pgOpt.pageSize);
					if(pgOpt.totalPages ==0)
						pgOpt.totalPages =1;
					if(pgOpt.currentPage > pgOpt.totalPages){
                        pgOpt.currentPage =  pgOpt.totalPages
					}
					$('#' +opts.page.id).bootstrapPaginator(pgOpt);
					if(callback){
						callback(r);
					}
				};

				var error = function(r){
					Msg.error(r? r.resultMsg: '提交失败');
					$('#' +opts.page.id).bootstrapPaginator(pgOpt);
					if(callback){
						callback(r);
					}
				};

				if(opts.metaCls){
					var fields = ["id"];
					$.each(opts.grid.fields,function(i,o){
						if(o && o.name)
							fields.push(o.name)
					});

					var param = {
						"cls":opts.metaCls,
						"q": ctrl.q,
						"sort": opts.sort,
						"fields":fields,
						"start":start,
						"limit":limit,
						"pagesize":pgOpt.pageSize,
						"page":page
					};
					AjaxUtils.post(ctrl.url,
						{q: $.toJSON(param)},success,error);
				}else{
					ctrl.q.start = start;
					ctrl.q.limit = limit;

					AjaxUtils.post(ctrl.url,ctrl.q,success,error);
				}
			}
		};

		/**分页初始化*/
		var pgOpt = $.extend({
			bootstrapMajorVersion:3,
			pageSize: 20,
			currentPage: 1,
			numberOfPages: 10,
			totalPages:1,
			onPageClicked: function(e,oe,t,page){
				pgOpt.currentPage = page;
				$("#" + opts.grid.id).jsGrid('search')
			}
		},opts.page)

		/*grid初始化*/
		var gridCtrl = {
			loadData: function(q) {
				var d = $.Deferred();
				ctrl.loadData((q && q.page)?q.page:null, q, function(r){
					var loadedData = (r.resultObj!=null && r.resultObj.list!=null)?r.resultObj.list:null;
					d.resolve(loadedData, r);
				});
				return d.promise();
			},
			insertItem: function(insertingClient) {},
			updateItem: function(updatingClient)  {},
			deleteItem: function(deletingClient) {}
		};

		var onDataLoaded = function(args){
			if(!opts.checkbox){
				return;
			}
			var o = $('#'+args.grid.id).data('JSGrid')
			$('#'+args.grid.id).find('input[name=__item_chk]').iCheck({
				checkboxClass: 'icheckbox_minimal-blue',
				radioClass: 'iradio_minimal-blue',
				increaseArea: '20%'
			}).on('ifToggled',function(e){
				if(opts.cToggled)
				{
					var data = $(this).data('i');
					var objects = o.getObject();
					opts.cToggled(this.checked, data, args, objects[o._getIndex(data)])
				}
			});

			var ckFst = $('#'+args.grid.id).find('input[type="checkbox"][name=__all_chk]:first');
			if(!(ckFst && ckFst.data('iCheck'))){

				$('#'+args.grid.id).find('input[name=__all_chk]').iCheck({
					checkboxClass: 'icheckbox_minimal-blue',
					radioClass: 'iradio_minimal-blue',
					increaseArea: '20%'
				})

				$('#'+args.grid.id).find('input[type="checkbox"][name=__all_chk]:first').on('ifClicked',function(){
					var isChk = !this.checked
					$('#'+args.grid.id).find('input[type="checkbox"][name=__item_chk]').each(function(){
						$(this).iCheck(isChk?'check':'uncheck')
					})
				});
			}
			ckFst.iCheck('uncheck')
		};

		var	gridOpts= $.extend(true, {
			height: opts.height?opts.height:"480px",
			width: "100%",
			autoload:false,
            confirmDeleting: _opts.confirmDeleting,
			sorting: true,
			paging: false,
			loadMessage:'载入中',
			pagerFormat: "Pages: {first} {prev} {pages} {next} {last} &nbsp;&nbsp; {pageIndex} of {pageCount}",
			pagePrevText: "上一页",
			pageNextText: "下一页",
			pageFirstText: "首页",
			pageLastText: "末页",
			noDataContent: "无数据",
			controller: gridCtrl,
			fields: opts.grid.fields,
		}, opts.grid, {
			getSelects:function(name){
				var selects = new Array();
				$('#'+this.id).find('input[type="checkbox"][name=__item_chk]').each(function(){
					if($(this).is(':checked'))
						selects.push(name?$(this).data('i')[name]:$(this).data('i'));
				})
				return selects;
			},
			getSelectsObject:function(){
				var o = this;
				var selects = new Array();
				var objects = o.getObject();
				$('#'+this.id).find('input[type="checkbox"][name=__item_chk]').each(function(){
					if($(this).is(':checked'))
					{
						var e = {data:$(this).data('i'), object:$(this)}
						var v = objects[o._getIndex($(this).data('i'))]
						if(v){
							$.extend(true, e, { tr : v })
						}

						selects.push(e);
					}
				})
				return selects;
			},
			_getIndex:function(data){
				var d = $('#'+this.id).data('JSGrid').data
				var k = -1
				$.each(d, function(i,o){
					if(o==data)
					{
						k = i
						return false;
					}
				})
				return k;
			},
			getObject: function(){
				var d = $('#'+this.id).data('JSGrid').data;
				var b = $('#'+this.id).data('JSGrid')._body.find('tr');
				var l = []
				$.each(d,function(i,o){
					l.push({
						data:o,
						html:b[i],
						input:FormUtils.getValues(b[i])
					})
				})
				return l;
			},
			onRefreshed:function(grid){
				onDataLoaded(grid);
				if(opts.grid.onDataLoaded)
					opts.grid.onDataLoaded(grid.grid);
			},
			empty:function(){
				$('#'+this.id).find('.jsgrid-row, .jsgrid-alt-row').remove();
				if(opts.grid.empty)
					opts.grid.empty();
			},
			getCheckBox:function(index){

			}
		});

		if(_opts.noHeight==true)
			delete gridOpts.height;

		$('#'+ opts.page.id).bootstrapPaginator(pgOpt)

		$("#" + opts.grid.id).jsGrid(gridOpts);

		if(opts.autoLoad)
			$("#" + opts.grid.id).jsGrid('search');


	},
	buildBtnGroup:function(_opts){

		var opts = $.extend({
			width: "35px",
			edit: function(){

			},
			del: function(){

			}
		},_opts);

		return { title:'操作', width: opts.width,
			itemTemplate: function(value, item){
				var objEdit = $('<a href="javascript:void(0);" class="btn btn-sm btn-flat"><i class="fa fa-delete"></i>编辑</a>');
				var objDel = $('<a href="javascript:void(0);" class="btn btn-sm btn-flat"><i class="fa fa-delete"></i>删除</a>');
				objDel.on('click',function(){
					$('#confirmDialog').modal('show');
					$('#confirmDialog .btn-primary').unbind("click").click(function(){
						$('#confirmDialog').modal('hide');
						opts.del(item);
					})
				})

				objEdit.on('click',function(){
					opts.edit(item);
				})

				return [objEdit,objDel];
			}
		}
	}
}

$(function(){

	/**数据ID**/
	$.fn.s2Val = function(v){
		if(typeof v !='undefined')
			return $(this).select2('val', v);
		else
			return $(this).select2('val');
	}

	/**数据对象**/
	$.fn.s2Data = function(v){
		if(typeof v !='undefined'){
			if(v.id!='undefined' && v.id !=null && v.id !=''){
				return $(this).select2('data', v);
			}
		}
		else
			return $(this).select2('data')
	}
});

String.format = function(format){
	var args = $.makeArray(arguments);
	args.shift(0);
	format = format.replace(/\{(\d+)\}/g, function(m, i){
		return args[i];
	});
	return format;
};

/**权限公用方法**/
RightUtils = {
	/**跳转订单详情**/
	hasBtn: function (btnName) {
		var buttons_str= $("#hidbuttons").val();
		return (buttons_str.indexOf(btnName)>=0);
	}
};
