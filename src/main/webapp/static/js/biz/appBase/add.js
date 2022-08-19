/**
 * 新增-app基本信息js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		appBase: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../appBase/save?_' + $.now(),
		    	param: vm.appBase,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
