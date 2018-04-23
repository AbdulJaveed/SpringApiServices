(function () {
    'use strict';

    angular
        .module('myApp.officeInfoController',[])
        .controller('officeInfoController', officeInfoController);
    officeInfoController.$inject = ['configData', '$location', '$rootScope', '$scope', '$timeout','$localStorage', '$http', '$window','officeInfoService','organizationService','appConstants', 'FlashService'];
    function officeInfoController(configData, $location, $rootScope, $scope, $timeout,$localStorage, $http, $window,officeInfoService,organizationService,appConstants, FlashService) {

    	
			 var vm = this;
			 $scope.usOrg = $localStorage.usOrg;
    		 $scope.nonEditable = $localStorage.isSaveEdit;
				 $scope.usOrgCode = configData.usOrgCode;
    		 vm.officeEmailValue = true;
    		 vm.employeeId =$localStorage.employeeId;
    		 initController();
    		 $localStorage.isReturn = false;
    		 function initController(){

           var  urlPath = $location.path();
           if(urlPath.includes('officeInfo-sf')) {
             if($rootScope.globals.userDTO!==undefined){
               var loggedInEmpId = $rootScope.globals.userDTO.id;
               if( $localStorage.employeeId == loggedInEmpId ) {
                 $scope.iseditable = false; // TO-DO:: true if editable
               } else {
                 $scope.iseditable = false;
               }
             }
           } else if(urlPath.includes('officeInfo-it')){
			   if(vm.employeeId == null || vm.employeeId == undefined || vm.employeeId == $rootScope.globals.userDTO.id) {
					$localStorage.isEmpOffice = true;
					$localStorage.isUserResponsibility = false;
					$localStorage.isFunctionExclusion = false;
					$localStorage.isOperationExclusion = false;
					window.location.href = "#/employees-office";
			   } else {
					$localStorage.isEmpOffice = false;
					$scope.iseditable = true;
			   }
		   } else {
             $scope.iseditable = true;
           }

    			 if(vm.employeeId !== undefined && vm.employeeId !==null){
    			 officeInfoService.findOsiEmployeeContacts(vm.employeeId).then(function(data){

    				 vm.osiEmployeeContacts = data;

					}).catch(function(error){
						var msg = appConstants.exceptionMessage;
						if(error.data.httpStatus){ 
				 		  msg=error.data.errorMessage; 
				 		}
						vm.displayErrorMsg(msg,'error');
					});

          console.log('medical info controller ;;;;; '+$localStorage.employeeId + $localStorage.searchByDate);
          if($localStorage.employeeId && $localStorage.searchByDate) {
            var inputObject = { "employeeId" : $localStorage.employeeId , "searchDate" : $localStorage.searchByDate};
            console.log(inputObject);
    			  //officeInfoService.findOsiEmployee(vm.employeeId).then(function(data){
            officeInfoService.findOsiEmployeeByIdAndDate(inputObject).then(function(data){
    				 vm.osiEmployees = data;
    				 if(data.officeEmail !== null)
    					 vm.officeEmailValue =false;
    				 
    				 if(data.fteCapacity === null || data.fteCapacity ===  undefined){
    					 vm.osiEmployees.fteCapacity = 40;
						 }
						 if(vm.osiEmployees.userName){
								$scope.disableUsername=true;
						 }
    				
					}).catch(function(error){
						var msg = appConstants.exceptionMessage;
						if(error.data.httpStatus){ 
				 		  msg=error.data.errorMessage; 
				 		}
						vm.displayErrorMsg(msg,'error');
					});
    		 };
       }
    }
    		 vm.save = function(decision) {

   	          vm.saveOsiEmployee(decision);
   	        }
    		 vm.clearOfficeInfoData = function(){
			/*	 initController();*/
				if($location.path().includes('officeInfo-it')) {
					$localStorage.isEmpOffice = true;
					$localStorage.isUserResponsibility = false;
					$localStorage.isFunctionExclusion = false;
					$localStorage.isOperationExclusion = false;
					window.location.href = "#/employees-office";
				} else {
					if($rootScope.profileAcessHR.profile)
					$location.path("/employeeInfo");
			   else if($rootScope.profileAcessHR.profiles)
				   $location.path("/employeeInfos");
	    		//	window.location.href = "#/employeeInfo";
					$localStorage.isReturn = true;
				}
    		 }

    vm.checkForValidData = function(){
		 vm.validateData(vm.osiEmployees);
		 if($scope.verifyData){
			 $('#confirmationModelForOffice').modal('show');
		 }

	 }
			vm.saveOsiEmployee =  function(decision){

				if(vm.employeeId !== undefined &&  vm.employeeId !==null){
					vm.osiEmployees.employeeId=vm.employeeId;
				}
				
					if(vm.osiEmployees.serialNumber != undefined)
						vm.osiEmployees.serialNumber = vm.osiEmployees.serialNumber.toUpperCase();
					/*vm.osiEmployees.osiEmployeeContacts.contactNumber = $scope.contactNumber;
					vm.osiEmployees.osiEmployeeContacts.contactId = $scope.contactId;*/
					if(vm.osiEmployeeContacts === "")
						vm.osiEmployees.osiEmployeeContacts = {};
					else
						vm.osiEmployees.osiEmployeeContacts = vm.osiEmployeeContacts;
					officeInfoService.saveOsiEmployee(vm.osiEmployees,decision).then(function(data){

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
						vm.displayErrorMsg(msg,'error');
					});

			};
			vm.validateData = function(osiEmployee){
				$scope.verifyData = true;
				if(osiEmployee.userName === '' || osiEmployee.userName ===undefined  || osiEmployee.userName ===null){
					vm.displayErrorMsg('Please Enter Username','error');
					$scope.verifyData = false;
				}else if(osiEmployee.officeEmail === '' || osiEmployee.officeEmail ===undefined || osiEmployee.officeEmail ===null){
					vm.displayErrorMsg('Please Enter Valid Email','error');
					$scope.verifyData = false;
				}/*else if(osiEmployee.mailStop === '' || osiEmployee.mailStop ===undefined){
					vm.displayErrorMsg('Mail Stop Field is required');
					$scope.verifyData = false;
				}*/else if(osiEmployee.fteCapacity === '' || osiEmployee.fteCapacity ===undefined){
					vm.displayErrorMsg('Please Enter Hours per Week','error');
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
			$scope.cancelProfile = function(){
				if($localStorage.currentEmployeeId!=$localStorage.loggedInEmployeeId)
					$window.location.href='#/employeeInfo-sf';
				else
				$window.location.href='#/profile';
			}
    }
})();
