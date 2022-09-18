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
        url: '../../appBase/list?_' + $.now(),
		height: $(window).height()-56,
        singleSelect : true,
		queryParams: function(params){
            params.appName = $('#appName').val();
            return removeEmptyField(params);
		},
		columns: [
			{checkbox: true},
            {field : "appName", title : "app", width : "100px"},
            {field : "appAndroidOriginPackageName", title : "安卓原始包名", width : "100px"},
            {field : "appIosOriginPackageName", title : "ios包名", width : "100px"}
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
        apply: function() {
            var row = $('#dataGrid').bootstrapTable('getSelections');
			var advertiseDomain = $("#advertiseDomain").val()
			var qqChecked = $("#qqChecked").val()
			var shortLink =  $("#shortLink").val()
            if (row.length > 0) {
                var appName = row[0].appName;
                $.ConfirmAjax({
                    msg : "申请"+appName+"的推广链接？",
                    url: '../../promotion/applyUrlV2?appName='+appName+'&advertiseDomain='+advertiseDomain+'&qqChecked='+qqChecked+"&shortLink="+shortLink+'&_' + $.now(),
                    success: function(data) {
                        vm.load();
                    }
                });
            }

        }
	}
})