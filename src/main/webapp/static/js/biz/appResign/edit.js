/**
 * 编辑-重签后的app信息js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		appResigned: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../appResign/info?_' + $.now(),
		    	param: vm.appResigned.id,
		    	success: function(data) {
		    		vm.appResigned = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../appResign/update?_' + $.now(),
		    	param: vm.appResigned,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})