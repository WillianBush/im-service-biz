/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		domain: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../domain/info?_' + $.now(),
		    	param: vm.domain.id,
		    	success: function(data) {
		    		vm.domain = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../domain/update?_' + $.now(),
		    	param: vm.domain,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})