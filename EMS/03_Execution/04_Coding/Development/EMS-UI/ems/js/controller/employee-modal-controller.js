'use strict';

angular.module('employeeModalController', [])
        .controller('employeeModalController',
    ['$scope', '$rootScope', '$cookies', '$location', '$http', '$localStorage', 'ngDialog',

  function($scope, $http, ngDialog,$rootScope, $cookies,$location){
	$scope.newEmployee = function(args, isFromDisplayChart) {
		$scope.supervisorNameText=args.employee.name;
		$scope.supervisorId=args.employee.id;
		$scope.departmentNameText=args.employee.department;
		$scope.departmentId = args.employee.departmentId;

		$scope.isFromOrgChart = true;
		$scope.isNewEmployee=true;
    	$cookies.put("empSkillCount",4);
    	$cookies.put("empCertCount",4);
    	$cookies.put("empDependentCount",1);

		ngDialog.open({
			 template: 'app/views/ems/create-profile.jsp',
			 showClose : true,
			 closeByDocument : true,
			 scope : $scope,
			 controller : 'profileController'
		});
		 console.log("popup");

	};

	$scope.$on("AddNewEmployee",function(events, args, isFromDisplayChart){
		console.log(isFromDisplayChart);
		$scope.newEmployee(args, isFromDisplayChart);
	});

	$scope.editEmployee = function(args) {
		$scope.employeeId = args.employeeId;
		console.log($scope.absUrl = $location.absUrl().split("org-hierarchy.jsp",1)[0]);
		var perfUrl = $scope.absUrl + "#/updateProfile/" + $scope.employeeId
		console.log(perfUrl);
		$scope.isFromOrgChart = true;
		$scope.isNewEmployee=false;
		/*ngDialog.open({
			template : 'app/views/ems/update-profile.jsp',
			showClose : true,
			closeByDocument : true,
			scope : $scope,
			controller : 'profileController'
		});*/
		window.top.location.href = perfUrl;
		console.log("popup");
	};

	$scope.$on("EditEmployee",function(events, args){
		console.log(args);
		$scope.editEmployee(args);
	});

	$scope.viewEmployee = function(args) {
		$scope.employeeId = args.employeeId;
		$scope.isFromOrgChart = true;
		$scope.isNewEmployee=false;
		$scope.isView=true;
		ngDialog.open({
			template : 'app/views/ems/view-profile-orgChart.jsp',
			showClose : false,
			closeByDocument : true,
			scope : $scope,
			controller : 'profileController',
			className : 'ngdialog-theme-viewemployee',
			name : 'view-employee-modal',
		});
		console.log("popup");
	};

	$scope.$on("ViewEmployee",function(events, args){
		//console.log($rootScope);
		//console.log(args.data);
		//$scope.viewEmployee(args);
	});
}]);
