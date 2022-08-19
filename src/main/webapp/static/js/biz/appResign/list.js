/**
 * 重签后的app信息js
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
		url: '../../appResign/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
            params.appBaseName = $('#appBaseName').val();
            return removeEmptyField(params);
		},
		columns: [
			// {checkbox: true},
            {title : "操作",width: "60px", formatter : function(value, row, index) {
                    var _html = '';

                    if (hasPermission('appResign:remove')) {
                        _html += '<a href="javascript:;" onclick="vm.remove(false,\''+row.id+'\')" title="删除"><i class="fa fa-trash-o"></i></a>';
                    }
                    return _html;
                }
            },
            {field : "appBaseName", title : "app", width : "100px"},
            {field : "androidResignedDownloadAddress", title : "安卓重签后下载地址", width : "100px"},
            {field : "androidDownloadTimes", title : "安卓下载次数", width : "100px"},
            {field : "iosDownloadAddrss", title : "iOS下载地址", width : "100px"},
            {field : "iosDownloadTimes", title : "ios下载次数", width : "100px"},
            {field : "appBaseId", title : "appId", width : "100px", visible:false},
            {field : "createTime", title : "创建时间", width : "180px"},
            {field : "updateTime", title : "更新时间", width : "180px"},
            {field : "androidResignedPackageName", title : "安卓重签后的报名", width : "100px", visible:false},
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
				title: '新增重签后的app信息',
				url: 'biz/appResign/add.html?_' + $.now(),
				width: '420px',
				height: '350px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function(id) {
            dialogOpen({
                title: '编辑重签后的app信息',
                url: 'biz/appResign/edit.html?_' + $.now(),
                width: '420px',
                height: '350px',
                success: function(iframeId){
                    top.frames[iframeId].vm.appResigned.id = id;
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
                url: '../../appResign/remove?_' + $.now(),
                param: ids,
                success: function(data) {
                    vm.load();
                }
            });
        }
	}
})