/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		ossConfig: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../oss/info?_' + $.now(),
		    	param: vm.ossConfig.id,
		    	success: function(data) {
		    		vm.ossConfig = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../oss/update?_' + $.now(),
		    	param: vm.ossConfig,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})