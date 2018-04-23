(function () {
    'use strict';
 
    angular
        .module('myApp.commonController',[])
        .controller('commonController', commonController);
    commonController.$inject = ['$http','$location', '$window','$scope', '$localStorage','$rootScope','$timeout'];
    function commonController($http, $location, $window,$scope, $localStorage, $rootScope, $timeout) {
        var vm = this;
        initController();
        $timeout(function() {
        	$scope.availOperations = $localStorage.availOperations;
        }, 400);
        
        function initController() {
        }
    }
})();