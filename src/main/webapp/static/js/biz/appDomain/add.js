/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		appDomain: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../appDomain/save?_' + $.now(),
		    	param: vm.appDomain,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
