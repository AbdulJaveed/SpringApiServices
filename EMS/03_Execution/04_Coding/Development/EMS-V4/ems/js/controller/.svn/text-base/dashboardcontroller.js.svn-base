
'use strict';
angular.module('myApp.dashboardcontroller', [])
        .controller('DashboardCtrl', ['$scope', '$rootScope', '$window', '$location', '$http', '$sessionStorage', '$routeParams', 
                                      '$localStorage', 'LookupService','employeeBasicInfoService',
function ($scope, $rootScope, $window, $location, $http, $sessionStorage, $routeParams, $localStorage,LookupService,employeeBasicInfoService) {
        $scope.slide = '';
        $scope.loginData = {};                  
        console.log("ROOTSCOPE LOGIN "+$rootScope.isLogin); 
//        $rootScope.basicTree = $sessionStorage.menuTree;
        if($rootScope.isLogin==true) {
           $sessionStorage.Login =true;  
        }                
    
        if($sessionStorage.Login) {
        	employeeBasicInfoService.loadEmployeeBasicInfo();
          
        	LookupService.getLookupByLookupName('GENDER').then(function (result) {
		    	$localStorage.gendersList = result.osiLookupValueses;
			});
			LookupService.getLookupByLookupName('TITLE').then(function (result) {
				$localStorage.titlesList = result.osiLookupValueses;
			});
			LookupService.getLookupByLookupName('EMPLOYEE TYPE').then(function (result) {
				$localStorage.empTypesList = result.osiLookupValueses;
			});
			LookupService.getLookupByLookupName('NATIONALITY').then(function (result) {
				$localStorage.nationalityList = result.osiLookupValueses;
			});
			  LookupService.getLookupByLookupName('Maritial_Status').then(function(result) {
			    $localStorage.maritialstatusList = result.osiLookupValueses;
			  });
			employeeBasicInfoService.getAllOrganizations().then(function (result) {
				$localStorage.osiOrganizations = result;
			});
           $rootScope.isLogin =true;  
        } else {
           $rootScope.isLogin =false;
        }
        console.log("dashboard contoller "+$sessionStorage.Login);
}]);