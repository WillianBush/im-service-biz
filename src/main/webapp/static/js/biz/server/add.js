/**
 * 新增-服务器基本信息js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		server: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../server/save?_' + $.now(),
		    	param: vm.server,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
