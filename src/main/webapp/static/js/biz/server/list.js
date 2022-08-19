/**
 * 服务器基本信息js
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
		url: '../../server/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
            params.serverIp = $('#serverIp').val();
            params.serverName = $('#serverName').val();
            return removeEmptyField(params);
		},
		columns: [
			// {checkbox: false},
            {title : "操作",width: "80px", formatter : function(value, row, index) {
                    var _html = '';
                    if (hasPermission('server:edit')) {
                        _html += '<a href="javascript:;" onclick="vm.edit(\''+row.id+'\')" title="编辑"><i class="fa fa-pencil"></i></a>';
                    }
                    if (hasPermission('server:remove')) {
                        _html += '<a href="javascript:;" onclick="vm.remove(false,\''+row.id+'\')" title="删除"><i class="fa fa-trash-o"></i></a>';
                    }
                    return _html;
                }
            },
            {field : "serverIp", title : "服务器IP", width : "100px"},
            {field : "serverName", title : "服务器名", width : "100px"},
            {field : "sshAccountName", title : "SSH账号", width : "100px"},
            {field : "sshAccountType", title : "证书类型", width : "60px", formatter: function (index, row) {
                    if (row.sshAccountType == 1) {
                        return "密码"
                    }
                    if (row.sshAccountType == 2) {
                        return "证书"
                    }
                }},
            {field : "sshAccountPassword", title : "SSH密码", width : "100px"},
            {field : "createTime", title : "创建时间", width : "255px"},
            {field : "updateTime", title : "更新时间", width : "255px"},
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
				title: '新增服务器基本信息',
				url: 'biz/server/add.html?_' + $.now(),
				width: '420px',
				height: '350px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function(id) {
            dialogOpen({
                title: '编辑服务器基本信息',
                url: 'biz/server/edit.html?_' + $.now(),
                width: '420px',
                height: '350px',
                success: function(iframeId){
                    top.frames[iframeId].vm.server.id = id;
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
                url: '../../server/remove?_' + $.now(),
                param: ids,
                success: function(data) {
                    vm.load();
                }
            });
        }
	}
})