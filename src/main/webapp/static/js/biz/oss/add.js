/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		ossConfig: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../oss/save?_' + $.now(),
		    	param: vm.ossConfig,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
