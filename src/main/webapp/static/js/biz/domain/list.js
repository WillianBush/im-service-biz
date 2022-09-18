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
		url: '../../domain/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
            params.domainType = $('#domainType').val();
            params.domainName = $('#domainName').val();
            params.domainEnable = $('#domainEnable').val();
            params.isBlocked = $('#isBlocked').val();
            params.appName = $('#appName').val();
            return removeEmptyField(params);
		},
		columns: [
            {title : "操作", width : "80px", formatter : function(value, row, index) {
                    var _html = '';
                    // if (hasPermission('domain:edit')) {
                    //     _html += '<a href="javascript:;" onclick="vm.edit(\''+row.id+'\')" title="编辑"><i class="fa fa-pencil"></i></a>';
                    // }
                    if (hasPermission('domain:remove')) {
                        _html += '<a href="javascript:;" onclick="vm.remove(false,\''+row.id+'\')" title="删除"><i class="fa fa-trash-o"></i></a>';
                    }
                    return _html;
                }
            },
			{checkbox: true},
            {field : "domainType", title : "域名类型", width : "100px", formatter: function (index, row) {
                    if (row.domainType == 1) {
                        return "落地页域名"
                    }
                    if (row.domainType == 2) {
                        return "短域名"
                    }
                    if (row.domainType == 3) {
                        return "长连接"
                    }
                }},
            {field : "qqChecked", title : "备案类型", width : "90px", formatter: function (index, row) {
                    if (row.qqChecked == 1) {
                        return "普通域名"
                    }
                    if (row.qqChecked == 2) {
                        return "QQ绿标"
                    }
                }},
            {field : "domainName", title : "域名", width : "100px"},
            {field : "appName", title : "app名字", width : "100px"},
            {field : "domainEnable", title : "是否启用", width : "80px", formatter: function (index, row) {
                    if (row.domainEnable == 1) {
                        return "已启用"
                    }
                    if (row.domainEnable == 2) {
                        return "未启用"
                    }
                }
                },
            {field : "isBlocked", title : "是否被封", width : "80px", formatter: function (index, row) {
                    if (row.isBlocked == 1) {
                        return "正常"
                    }
                    if (row.isBlocked == 2) {
                        return "不可用"
                    }
                }
                },
            // {field : "httpsTomcatJksAddress", title : "域名证书地址", width : "255px"},
            // {field : "httpsTomcatPass", title : "域名证书密码", width : "255x"},
            {field : "createTime", title : "创建时间", width : "100px"},
            {field : "updateTime", title : "更新时间", width : "100px"},
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
				url: 'biz/domain/add.html?_' + $.now(),
				width: '800px',
				height: '700px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function(id) {
            dialogOpen({
                title: '编辑',
                url: 'biz/domain/edit.html?_' + $.now(),
                width: '600px',
                height: '400px',
                success: function(iframeId){
                    top.frames[iframeId].vm.domain.id = id;
                    top.frames[iframeId].vm.setForm();
                },
                yes: function(iframeId){
                    top.frames[iframeId].vm.acceptClick();
                }
            });
        },
        excel: function () {
            var params ={};
            params.domainType = $('#domainType').val();
            params.domainName = $('#domainName').val();
            params.appName = $('#appName').val();
            params.domainEnable = $('#domainEnable').val();
            params.isBlocked = $('#isBlocked').val();
            params.pageNumber = 1;
            params.sortOrder = "asc";

            var form=$("<form>");
            form.attr("style","display:none");
            form.attr("enctype","application/json");
            form.attr("method","get");
            form.attr("action",'../../domain/csv?_' + $.now());
            if ("" !== params.domainType || params.domainType !== null) {
                form.append($("<input name='domainType' value='"+params.domainType+"'/>"));
            }
            if (params.domainName !== null) {
                form.append($("<input name='domainName' value='"+params.domainName+"'/>"));
            }
            if (params.appName !== null) {
                form.append($("<input name='appName' value='"+params.appName+"'/>"));
            }
            if (params.domainEnable !== null) {
                form.append($("<input name='domainEnable' value='"+params.domainEnable+"'/>"));
            }
            if (params.isBlocked !== null) {
                form.append($("<input name='isBlocked' value='"+params.isBlocked+"'/>"));
            }

            form.append($("<input name='pageNumber' value='"+params.pageNumber+"'/>"));
            form.append($("<input name='sortOrder' value='"+params.sortOrder+"'/>"));
            $("body").append(form);
            form.submit();
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
                url: '../../domain/remove?_' + $.now(),
                param: ids,
                success: function(data) {
                    vm.load();
                }
            });
        }
	}
})