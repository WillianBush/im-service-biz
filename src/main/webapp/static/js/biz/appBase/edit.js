/**
 * 编辑-app基本信息js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		appBase: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../appBase/info?_' + $.now(),
		    	param: vm.appBase.id,
		    	success: function(data) {
		    		vm.appBase = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../appBase/update?_' + $.now(),
		    	param: vm.appBase,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})