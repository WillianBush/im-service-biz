/**
 * 编辑-域名URLjs
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		appPromotion: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../promotion/info?_' + $.now(),
		    	param: vm.appPromotion.id,
		    	success: function(data) {
		    		vm.appPromotion = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../promotion/update?_' + $.now(),
		    	param: vm.appPromotion,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})