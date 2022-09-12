/**
 * 域名URLjs
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
		url: '../../promotion/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
            params.appName = $('#appName').val();
            params.promotionDomain = $('#promotionDomain').val();
            params.isBlocked = $('#isBlocked').val();
            params.expireTime = $('#expireTime').val();
            return removeEmptyField(params);
		},
		columns: [
			{checkbox: true},
            {
                title: "操作", width: "80px", formatter: function (value, row, index) {
                    var _html = '';
                    if (hasPermission('promotion:edit')) {
                        _html += '<a href="javascript:;" onclick="vm.edit(\'' + row.id + '\')" title="编辑"><i class="fa fa-pencil"></i></a>';
                    }
                    if (hasPermission('promotion:remove')) {
                        _html += '<a href="javascript:;" onclick="vm.remove(false,\'' + row.id + '\')" title="删除"><i class="fa fa-trash-o"></i></a>';
                    }
                    return _html;
                }
            },
            {field : "appName", title : "app名字", width : "100px"},
            {field : "promotionDomain", title : "推广域名", width : "100px"},
            // {field : "domainIsBlocked", title : "是否可用", width : "100px"},
            {field : "promotionUrl", title : "推广url", width : "100px",visible:false},
            {field : "promotionUrl", title : "推广链接", formatter: function (index, row) {
                if (!row.promotionUrl || row.promotionUrl == ""){
                    return row.promotionDomain
                }
                    return row.promotionDomain +"/" +row.promotionUrl
                }},
            {field : "appBaseId", title : "AppID原包", width : "100px",visible:false},
            {field : "appResignedD", title : "重签后的AppID", width : "100px",visible:false},
            {field : "createTime", title : "创建时间", width : "180px"},
            {field : "expireTime", title : "过期时间", width : "180px"}
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
				title: '新增域名URL',
				url: 'biz/promotion/add.html?_' + $.now(),
				width: '420px',
				height: '350px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function(id) {
            dialogOpen({
                title: '编辑域名URL',
                url: 'biz/promotion/edit.html?_' + $.now(),
                width: '420px',
                height: '600px',
                success: function(iframeId){
                    top.frames[iframeId].vm.appPromotion.id = id;
                    top.frames[iframeId].vm.setForm();
                },
                yes: function(iframeId){
                    top.frames[iframeId].vm.acceptClick();
                }
            });
        },
        excel: function () {
            var params ={};
            params.appName = $('#appName').val();
            params.promotionDomain = $('#promotionDomain').val();
            params.isBlocked = $('#isBlocked').val();
            params.expireTime = $('#expireTime').val();
            params.pageNumber = 1;
            params.sortOrder = "asc";

            var form=$("<form>");
            form.attr("style","display:none");
            form.attr("enctype","application/json");
            form.attr("method","get");
            form.attr("action",'../../promotion/csv?_' + $.now());
            if ("" !== params.appName || params.appName !== null) {
                form.append($("<input name='appName' value='"+params.appName+"'/>"));
            }
            if ("" !== params.promotionDomain || params.promotionDomain !== null) {
                form.append($("<input name='promotionDomain' value='"+params.promotionDomain+"'/>"));
            }
            if ("" !== params.isBlocked || params.isBlocked !== null) {
                form.append($("<input name='isBlocked' value='"+params.isBlocked+"'/>"));
            }
            if ("" !== params.expireTime || params.expireTime !== null) {
                form.append($("<input name='expireTime' value='"+params.expireTime+"'/>"));
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
                url: '../../promotion/remove?_' + $.now(),
                param: ids,
                success: function(data) {
                    vm.load();
                }
            });
        },
        applyUrl:function () {
            dialogOpen({
                title: '申请推广链接',
                url: 'biz/promotion/applyUrl.html?_' + $.now(),
                width: '800px',
                height: '420px',
                yes: function (iframeId) {
                    top.frames[iframeId].vm.apply();
                }
            });
        }
	}
})