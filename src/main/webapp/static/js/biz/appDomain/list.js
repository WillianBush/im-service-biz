/**
 * js
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
		url: '../../appDomain/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
            params.appBaseName = $('#appBaseName').val();
            params.domainType = $('#domainType').val();
            params.domainEnable = $('#domainEnable').val();
            params.isBlocked = $('#isBlocked').val();
            params.qqChecked = $('#qqChecked').val();
            return removeEmptyField(params);
		},
		columns: [
			{checkbox: true},
            {title : "操作", width : "100px", formatter : function(value, row, index) {
                    var _html = '';
                    if (hasPermission('appDomain:edit')) {
                        _html += '<a href="javascript:;" onclick="vm.edit(\''+row.id+'\')" title="编辑"><i class="fa fa-pencil"></i></a>';
                    }
                    if (hasPermission('appDomain:remove')) {
                        _html += '<a href="javascript:;" onclick="vm.remove(false,\''+row.id+'\')" title="删除"><i class="fa fa-trash-o"></i></a>';
                    }
                    return _html;
                }
            },
            {field : "domainType", title : "域名类型", width : "100px", formatter: function (index, row) {
                    if (row.domainType == 1) {
                        return "落地页域名"
                    }
                    if (row.domainType == 2) {
                        return "短域名"
                    }
                }},
            {field : "domainName", title : "域名", width : "100px"},
            {field : "appBaseName", title : "app原包名", width : "100px"},
            {field : "androidResignedPackageName", title : "安卓重签后的包名", width : "100px"},
            {field : "appResignedId", title : "安卓重签后的id", width : "100px"},
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
				title: '新增',
				url: 'biz/appDomain/add.html?_' + $.now(),
				width: '420px',
				height: '350px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function(id) {
            dialogOpen({
                title: '编辑',
                url: 'biz/appDomain/edit.html?_' + $.now(),
                width: '420px',
                height: '350px',
                success: function(iframeId){
                    top.frames[iframeId].vm.appDomain.id = id;
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
                url: '../../appDomain/remove?_' + $.now(),
                param: ids,
                success: function(data) {
                    vm.load();
                }
            });
        }
	}
})