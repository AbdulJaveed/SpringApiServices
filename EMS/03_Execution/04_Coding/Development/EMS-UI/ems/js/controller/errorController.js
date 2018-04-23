(function () {
    'use strict';
 
    angular
        .module('myApp.errorController',[])
        .controller('errorController', errorController);
    errorController.$inject = ['AuthenticationService'];
    function errorController(AuthenticationService) {
		 var vm = this;
			AuthenticationService.ClearToken();
			//$window.location.reload(true);
    }
})();