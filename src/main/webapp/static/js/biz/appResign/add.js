/**
 * 新增-重签后的app信息js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		appResigned: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../appResign/save?_' + $.now(),
		    	param: vm.appResigned,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
