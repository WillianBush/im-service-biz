/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		domain: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../domain/save?_' + $.now(),
		    	param: vm.domain,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
