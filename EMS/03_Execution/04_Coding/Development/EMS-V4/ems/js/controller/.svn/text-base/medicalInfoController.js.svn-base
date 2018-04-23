(function () {
    'use strict';

    angular
        .module('myApp.medicalInfoController',[])
        .controller('medicalInfoController', medicalInfoController);
    medicalInfoController.$inject = ['configData', '$location','$route', '$rootScope', '$scope','$timeout','$localStorage', '$http', '$window','medicalInfoService','organizationService', 'appConstants', 'FlashService'];
    function medicalInfoController(configData, $location, $route,$rootScope, $scope, $timeout,$localStorage, $http, $window,medicalInfoService,organizationService, appConstants, FlashService) {

		$scope.usOrgCode = configData.usOrgCode;
    		 var vm = this;
    		 $localStorage.isReturn = false;
    		 var today = new Date();
    			var dd = today.getDate();
    			var mm = today.getMonth()+1; //January is 0!
    			 $scope.nonEditable = $localStorage.isSaveEdit;
    			var yyyy = today.getFullYear();

    			var today = yyyy+'-'+mm+'-'+dd;
    			vm.maxDate = today;
    		 vm.employeeId   = $localStorage.employeeId;
    		initController();

			function initController(){
					$scope.osiBloodGroups = [
					{"title":"A+", "value":"A+"},
					{"title":"AB+", "value":"AB+"},
					{"title":"B+", "value":"B+"},
					{"title":"O+", "value":"O+"},
					{"title":"A-", "value":"A-"},
					{"title":"AB-", "value":"AB-"},
					{"title":"B-", "value":"B-"},
					{"title":"O-", "value":"O-"}
				];

        var  urlPath = $location.path();
        if(urlPath.includes('medicalInfo-sf')) {
          if($rootScope.globals.userDTO!==undefined){
            var loggedInEmpId = $rootScope.globals.userDTO.id;
            if( $localStorage.employeeId == loggedInEmpId ) {
              $scope.iseditable = true;
            } else {
              $scope.iseditable = false;
            }
          }
        } else {
          $scope.iseditable = true;
        }

        console.log('medical info controller ;;;;; '+$localStorage.employeeId + $localStorage.searchByDate);
        if($localStorage.employeeId && $localStorage.searchByDate) {
          var inputObject = { "employeeId" : $localStorage.employeeId , "searchDate" : $localStorage.searchByDate};
      
            medicalInfoService.findOsiEmployeeByIdAndDate(inputObject).then(function(data){
							vm.osiEmployees = data;
							
						}).catch(function(error){
							var msg = appConstants.exceptionMessage;
							if(error.data.httpStatus){ 
								msg=error.data.errorMessage; 
							}
							FlashService.Error(msg);
							$timeout(function () {
								$rootScope.isTrascError=false;
							}, 2000);
/*							var msg="Error occured while fetching records."
					            if(error.data){ 
					            	msg=msg+'(Error:'+error.data.status+')'; 
					            }else if(error.status){ 
					            	msg=msg+ '(Error:'+error.status+')'; 
					            } 
							vm.displayErrorMsg(msg,'error');
*/						});
					}
			};

		vm.clearMedicalInfo = function(){
			/*initController();*/
			window.location.href = "#/employees";
			$localStorage.isReturn = true;
		}
		vm.checkForValidData = function(){
			vm.validateData(vm.osiEmployees);
				if($scope.verifyData){

					 $('#confirmationModelForMedical').modal('show');
			}
		}
		vm.save = function(decision) {

	          vm.saveOsiEmployee(decision);
	        }
			vm.saveOsiEmployee =  function(decision){

				if(vm.employeeId !== undefined &&  vm.employeeId !==null){
					vm.osiEmployees.employeeId=vm.employeeId;
				}

					medicalInfoService.saveOsiEmployeeMedicalInfo(vm.osiEmployees, decision).then(function(data){

						$rootScope.isTrascError = true;
						var msg = appConstants.successMessage;
						FlashService.Success(msg);
						$timeout(function () {
							$rootScope.isTrascError=false;
						}, 2000);


					}).catch(function(error){
						var msg = appConstants.exceptionMessage;
						if(error.data.httpStatus){ 
							msg=error.data.errorMessage; 
						}
						FlashService.Error(msg);
						$timeout(function () {
							$rootScope.isTrascError=false;
						}, 2000);
					});

			};
			vm.validateData = function(osiEmployee){
				$scope.verifyData = true;
				if(  osiEmployee.bloodType === '' || osiEmployee.bloodType ===undefined || osiEmployee.bloodType ===null){
					vm.displayErrorMsg('Blood group Field is required','error');
					$scope.verifyData = false;
				}
			};
			vm.displayErrorMsg = function(message,type){
				 $rootScope.isTrascError = true;
				 $rootScope.flash = {
			                message: message,
			                type: type
			            };
					$timeout(function () {
						$rootScope.isTrascError=false;
					}, 5000);


			}
    }
})();
