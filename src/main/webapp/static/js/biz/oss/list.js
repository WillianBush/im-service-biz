/**
 * js
 */

$(function () {
    initialPage();
    getGrid();
});

function initialPage() {
    $(window).resize(function () {
        $('#dataGrid').bootstrapTable('resetView', {height: $(window).height() - 56});
    });
}

function getGrid() {
    $('#dataGrid').bootstrapTableEx({
        url: '../../oss/list?_' + $.now(),
        height: $(window).height() - 56,
        queryParams: function (params) {
            params.updateTime = $('#updateTime').val();
            params.createTime = $('#createTime').val();
            params.createBy = $('#createBy').val();
            params.updateBy = $('#updateBy').val();
            params.endpoint = $('#endpoint').val();
            params.apppoint = $('#apppoint').val();
            params.accessKeyId = $('#accessKeyId').val();
            params.bucketname = $('#bucketname').val();
            params.ossAccelerateDomain = $('#ossAccelerateDomain').val();
            params.accessKeySecret = $('#accessKeySecret').val();
            params.enabled = $('#enabled').val();
            params.ossGroupName = $('#ossGroupName').val();
            return removeEmptyField(params);
        },
        columns: [
            {checkbox: true},
            {
                title: "操作", width: "60px", formatter: function (value, row, index) {
                    var _html = '';
                    if (hasPermission('oss:edit')) {
                        _html += '<a href="javascript:;" onclick="vm.edit(\'' + row.id + '\')" title="编辑"><i class="fa fa-pencil"></i></a>';
                    }
                    if (hasPermission('oss:remove')) {
                        _html += '<a href="javascript:;" onclick="vm.remove(false,\'' + row.id + '\')" title="删除"><i class="fa fa-trash-o"></i></a>';
                    }
                    return _html;
                }
            },
            {field: "ossGroupName", title: "oss账号名", width: "100px"},
            {field: "ossAccelerateDomain", title: "加速域名", width: "220px"},
            {field: "endpoint", title: "endpoint", width: "100px"},
            {field: "apppoint", title: "apppoint", width: "100px"},
            {field: "accessKeyId", title: "accessKeyId", width: "100px"},
            {field: "accessKeySecret", title: "accessKeySecret", width: "100px"},
            {field: "bucketname", title: "bucketname", width: "100px"},
            {
                field: "enabled", title: "是否启用", width: "100px", formatter: function (index, row) {
                    if (row.enabled == 1) {
                        return "已启用"
                    }
                    if (row.enabled == 2) {
                        return "关闭"
                    }
                }
            },
            {field: "updateTime", title: "更新时间", width: "100px"},
            // {field: "createTime", title: "创建时间", width: "100px"},
            // {field: "createBy", title: "创建人", width: "100px"},
            {field: "updateBy", title: "更新人", width: "100px"}
        ]
    })
}

var vm = new Vue({
    el: '#dpLTE',
    data: {
        keyword: null
    },
    methods: {
        load: function () {
            $('#dataGrid').bootstrapTable('refresh');
        },
        save: function () {
            dialogOpen({
                title: '新增',
                url: 'biz/oss/add.html?_' + $.now(),
                width: '720px',
                height: '650px',
                yes: function (iframeId) {
                    top.frames[iframeId].vm.acceptClick();
                },
            });
        },
        edit: function (id) {
            dialogOpen({
                title: '编辑',
                url: 'biz/oss/edit.html?_' + $.now(),
                width: '720px',
                height: '650px',
                success: function (iframeId) {
                    top.frames[iframeId].vm.ossConfig.id = id;
                    top.frames[iframeId].vm.setForm();
                },
                yes: function (iframeId) {
                    top.frames[iframeId].vm.acceptClick();
                }
            });
        },
        remove: function (batch, id) {
            var ids = [];
            if (batch) {
                var ck = $('#dataGrid').bootstrapTable('getSelections');
                if (!checkedArray(ck)) {
                    return false;
                }
                $.each(ck, function (idx, item) {
                    ids[idx] = item.id;
                });
            } else {
                ids.push(id);
            }
            $.RemoveForm({
                url: '../../oss/remove?_' + $.now(),
                param: ids,
                success: function (data) {
                    vm.load();
                }
            });
        }
    }
})