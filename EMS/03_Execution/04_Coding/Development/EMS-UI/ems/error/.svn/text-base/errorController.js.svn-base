(function () {
    'use strict';
 
    angular
        .module('app.errorController',[])
        .controller('errorController', errorController);
    errorController.$inject = ['AuthenticationService'];
    function errorController(AuthenticationService) {
		 var vm = this;
			AuthenticationService.ClearToken();
			//$window.location.reload(true);
    }
})();