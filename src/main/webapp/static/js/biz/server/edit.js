/**
 * 编辑-服务器基本信息js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		server: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../server/info?_' + $.now(),
		    	param: vm.server.id,
		    	success: function(data) {
		    		vm.server = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../server/update?_' + $.now(),
		    	param: vm.server,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})