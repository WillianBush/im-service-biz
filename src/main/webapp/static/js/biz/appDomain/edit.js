/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		appDomain: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../appDomain/info?_' + $.now(),
		    	param: vm.appDomain.id,
		    	success: function(data) {
		    		vm.appDomain = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../appDomain/update?_' + $.now(),
		    	param: vm.appDomain,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})