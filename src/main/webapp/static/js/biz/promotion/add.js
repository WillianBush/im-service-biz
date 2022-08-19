/**
 * 新增-域名URLjs
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		appPromotion: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../promotion/save?_' + $.now(),
		    	param: vm.appPromotion,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
