
/**
 * Employee Basic Info Controller
 */
'use strict';
angular.module('myApp.emergencyContactsController', [])
        .controller('emergencyContactsController', ['$scope', '$rootScope', '$window', '$location', '$http',
                                  '$timeout','FlashService','$cookieStore','appConstants',
                                  'LookupService', 'emergencyContactsService', 'Upload', 'configData',
		function ($scope, $rootScope, $window, $location, $http, $timeout, FlashService, $cookieStore, appConstants, LookupService,
			emergencyContactsService, Upload, configData) {
			var vm = this;
			$scope.usOrgCode = configData.usOrgCode;
			$scope.usOrg = $localStorage.usOrg;
			$scope.testMessage= "message::::";
			var empId =  $cookieStore.get('globals').userDTO.id;
			var empNumber =  $cookieStore.get('globals').userDTO.empNumber;
			$scope.successMssage = "";
			 $localStorage.isReturn = false;

			$scope.osiSkills = [] ;
			$scope.osiSkillSearchResult = [];

			$scope.getOsiSkillsList = function(){
				employeeSkillService.getAllSkills().then(function (result) {
					console.log(result);
					$scope.osiSkills = result;
				});
			}
			$scope.clearContacts = function(){
				window.location.href = "#/employees";
	    		 $localStorage.isReturn = true;
			}
			$scope.saveSkills =  function(){
				console.log("SSSSSSSSSSSSSSS" + $scope.osiUser );
				$scope.osiUser.employeeId = empId;
				$scope.osiUser.createdBy = empNumber;
				$scope.osiUser.updatedBy = empNumber;

				employeeSkillService.saveEmpSkills($scope.osiUser).then(function (result) {
					$scope.isTrascError=true;
					$scope.showSuccessAlert=true;
					$scope.successTextAlert = appConstants.successMessage;
					FlashService.Success(appConstants.successMessage);
					$timeout(function () {
						$scope.showSuccessAlert=false;
						$scope.isTrascError=false;
					}, 2000);
				}, function error(error){
		        	console.log(error.data);
		        	$rootScope.isTrascError = true;
					FlashService.Error(error.data.errorMessage);
					$timeout(function () {
						$rootScope.isTrascError=false;
					}, 2000);
		        });
			}


			$scope.searchkills =  function(){
				console.log("SSSSSSSSSSSSSSS" + $scope.osiUser );
				$scope.osiUser.employeeId = empId;


				employeeSkillService.searchEmpSkills($scope.osiUser).then(function (result) {
					console.log(result);
				$scope.osiSkillSearchResult = result;

				});
			}

			$scope.getOsiSkillsList();

      var  urlPath = $location.path();
      if(urlPath.includes('emergencyContacts-sf')) {
          if($rootScope.globals.userDTO!==undefined){
              var loggedInEmpId = $rootScope.globals.userDTO.id;
              if( $localStorage.employeeId == loggedInEmpId ) {
                  $scope.iseditable = false; // TO-DO :: true if editable for self
              } else {
                  $scope.iseditable = false;
              }
          }
      } else {
          $scope.iseditable = true;
      }
        }
]);
