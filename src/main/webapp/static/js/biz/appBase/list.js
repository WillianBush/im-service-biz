/**
 * app基本信息js
 */

$(function () {
	initialPage();
	getGrid();
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid').bootstrapTable('resetView', {height: $(window).height()-56});
	});
}

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url: '../../appBase/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
            params.appName = $('#appName').val();
            return removeEmptyField(params);
		},
		columns: [
			// {checkbox: true},
            {title : "操作",width: "60px", formatter : function(value, row, index) {
                    var _html = '';
                    if (hasPermission('appBase:edit')) {
                        _html += '<a href="javascript:;" onclick="vm.edit(\''+row.id+'\')" title="编辑"><i class="fa fa-pencil"></i></a>';
                    }
                    if (hasPermission('appBase:remove')) {
                        _html += '<a href="javascript:;" onclick="vm.remove(false,\''+row.id+'\')" title="删除"><i class="fa fa-trash-o"></i></a>';
                    }
                    return _html;
                }
            },
            {field : "id", title : "app编号", width : "50px"},
            {field : "appName", title : "app", width : "100px"},
            // {field : "appAndroidOriginAddress", title : "安卓原包地址", width : "100px"},
            // {field : "appIosOriginAddrss", title : "ios原包地址", width : "100px"},
            // {field : "appAndroidOriginPackageName", title : "安卓原始包名", width : "100px"},
            // {field : "appIosOriginPackageName", title : "ios包名", width : "100px"},
            // {field : "appAndroidKeystoreName", title : "安卓证书名", width : "100px"},
            // {field : "appAndroidKeystorePassword", title : "安卓证书密码", width : "100px"},
            // {field : "appAndroidKeystoreAddress", title : "安卓证书地址", width : "100px"},
            {field : "appHomePageServerIds", title : "服务器", width : "100px"},
            {field : "serverNames", title : "落地页服务器名", width : "100px"},
            {field : "serverIp", title : "服务器IP", width : "100px"},
            {field : "createTime", title : "创建时间", width : "180px"},
            {field : "updateTime", title : "更新时间", width : "180px"},
            {field : "createBy", title : "创建人", width : "100px"},
            {field : "updateBy", title : "更新人", width : "100px"}
		]
	})
}

var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		save: function() {
			dialogOpen({
				title: '新增app基本信息',
				url: 'biz/appBase/add.html?_' + $.now(),
				width: '420px',
				height: '350px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function(id) {
            dialogOpen({
                title: '编辑app基本信息',
                url: 'biz/appBase/edit.html?_' + $.now(),
                width: '420px',
                height: '350px',
                success: function(iframeId){
                    top.frames[iframeId].vm.appBase.id = id;
                    top.frames[iframeId].vm.setForm();
                },
                yes: function(iframeId){
                    top.frames[iframeId].vm.acceptClick();
                }
            });
        },
        remove: function(batch, id) {
            var ids = [];
            if (batch) {
                var ck = $('#dataGrid').bootstrapTable('getSelections');
                if (!checkedArray(ck)) {
                    return false;
                }
                $.each(ck, function(idx, item){
                    ids[idx] = item.id;
                });
            } else {
                ids.push(id);
            }
            $.RemoveForm({
                url: '../../appBase/remove?_' + $.now(),
                param: ids,
                success: function(data) {
                    vm.load();
                }
            });
        }
	}
})