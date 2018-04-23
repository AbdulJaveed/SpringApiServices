(function () {
    'use strict';
 
    angular
        .module('myApp.authController',[])
        .controller('authController', authController);
    authController.$inject = ['$location', '$rootScope', '$scope', '$localStorage', '$http', '$window', 'AuthenticationService'];
    function authController($location, $rootScope, $scope, $localStorage, $http, $window, AuthenticationService) {
    		$scope.loggedIn = $rootScope.globals.userDTO;
    		
    		 var vm = this;
    		delete $scope.basicTree;
    		if($scope.loggedIn){
    			$scope.basicTree = $localStorage.menuTree;
    		}
    		$scope.availableOperations = availableOperations;
    		function availableOperations(functionId, url){
    			AuthenticationService.availableOperations(functionId, url);
    		}
    }
})();