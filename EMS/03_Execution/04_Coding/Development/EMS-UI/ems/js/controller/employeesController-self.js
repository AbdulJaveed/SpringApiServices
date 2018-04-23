'use strict';
angular.module('myApp.employeesControllerSelf', []).controller('EmployeesControllerSelf',
		EmployeesControllerSelf);
EmployeesControllerSelf.$inject = ['configData', '$q', '$scope', '$rootScope', '$window', '$location', '$cookieStore',
    '$http', '$localStorage', 'EmployeesServiceSelf', 'FlashService', '$timeout','appConstants','LookupService','employeeBasicInfoService', 'organizationService'];
function EmployeesControllerSelf(configData, $q, $scope, $rootScope, $window, $location, $http, $cookieStore,
        $localStorage, EmployeesServiceSelf, FlashService, $timeout,appConstants,LookupService,employeeBasicInfoService,organizationService,Upload) {
	var vm = this;
	$scope.usOrgCode = configData.usOrgCode;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.employeeList = "";
    $scope.iseditable = true;
    $scope.rowSize = 5;
    $scope.isSelectedRow = null;
    $scope.selectedRowDetails = [];
    $scope.disable_Create = false;
    $scope.disable_Edit = true;
    $scope.disable_Delete = true;
    $scope.disable_View = true;
    $scope.successTextAlert = "";
    $scope.showSuccessAlert = false;
    $scope.employeeList = [];
	$scope.OsiEmployees = [];
    $scope.sort = function (keyname) {
        if ($scope.employeeList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }
    function loadEmployeeBasicInfo(){
		if($localStorage.employeeId && $localStorage.searchByDate) {
			var inputObject = { "employeeId" : $localStorage.employeeId , "searchDate" : $localStorage.searchByDate};
				//employeeBasicInfoService.getBasicInfo($localStorage.employeeId).then(function success(result) {
			employeeBasicInfoService.getBasicInfoByIdAndDate(inputObject).then(function success(result) {

					organizationService.getOrgById(result.orgId).then(function(data){
						$localStorage.orgId = result.orgId;
						$localStorage.orgName = data.orgName;
						 $localStorage.employeeName = result.fullName;
						 $localStorage.employeeNumber = result.employeeNumber;
						 $localStorage.osiEmpAttachments = result.osiEmpAttachments;
						 console.log(" --- - "+$localStorage.isUserResponsibility);
						 console.log(" --- - "+$localStorage.isFunctionExclusion);
						  console.log(" --- - "+$localStorage.isOperationExclusion);
						 if($localStorage.isUserResponsibility){

							 		window.location.href = "#/userResponsibility";
							 		$("#userResponsibility").addClass("dynatree-active");
							 		$(".Employee_List").removeClass("dynatree-active");
						     }
						     else if($localStorage.isFunctionExclusion){

							 		$location.path("/functionExclusion");
							 		$("#functionExclusion").addClass("dynatree-active");
							 		$(".Employee_List").removeClass("dynatree-active");
						     }
						     else if($localStorage.isOperationExclusion){

							 		window.location.href = "#/operation";
							 		$("#operation").addClass("dynatree-active");
							 		$(".Employee_List").removeClass("dynatree-active");
						     }
					}).catch(function(error){
						$rootScope.isTrascError = true;
						var msg = appConstants.exceptionMessage;
						if(error.data.httpStatus){ 
							msg=error.data.errorMessage; 
						}
						FlashService.Error(msg);
						$timeout(function () {
							$rootScope.isTrascError=false;
						}, 2000);
					});

				}, function error(error){
					$rootScope.isTrascError = true;
					var msg = appConstants.exceptionMessage;
					if(error.data.httpStatus){ 
						msg=error.data.errorMessage; 
					}
					FlashService.Error(msg);
					$timeout(function () {
						$rootScope.isTrascError=false;
					}, 2000);
				});
			}
	}
	$scope.editSelectedRowDetails = function(OsiEmployees){
		console.log("name osi employees ");
		console.log(OsiEmployees);
	     //editOsiEmployeesDetails(OsiEmployees);
	     $scope.isSelectedRow = OsiEmployees.employeeId;
	     toggleButtons();
	     $localStorage.iseditable = false;
	     $localStorage.employeeId = OsiEmployees.employeeId;
	     $localStorage.empId = OsiEmployees.employeeId;
	     if($localStorage.isUserResponsibility){
			loadEmployeeBasicInfo();
	     }
	     else if($localStorage.isFunctionExclusion){
			loadEmployeeBasicInfo();
	     }
	     else if($localStorage.isOperationExclusion){
			loadEmployeeBasicInfo();
	     }
		 else{
			 $("#employeeInfo-sf").addClass("dynatree-active");
		 	$(".Employee_List").removeClass("dynatree-active");
			 $location.path("/employeeInfo-sf");
			//$localStorage.functionId = 25;
			$("#employeeInfo-sf").click();
		 }
	}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);
    $scope.init = function () {
    	openorgNamePopUp();
    };
    vm.search = function(searchData) {
    	$scope.employeeList = [];
	    EmployeesServiceSelf.searchOsiEmployeess(searchData).then(function (data) {
					console.log(data);
	        $scope.employeeList = data;
	        $scope.closeorgNameModal();
	        $scope.availOperations = $localStorage.availOperations;
					console.log($rootScope.globals.userDTO);
					if($rootScope.globals.userDTO!==undefined){
						$scope.isSelectedRow = $rootScope.globals.userDTO.id;
					}
					console.log($scope.isSelectedRow);
	    }).catch(function(error){
	    	$scope.closeorgNameModal();
	    	$rootScope.isTrascError = true;
			var msg = appConstants.exceptionMessage;
			if(error.data.httpStatus){ 
				msg=error.data.errorMessage; 
			}
			FlashService.Error(msg);
			$timeout(function () {
				$rootScope.isTrascError=false;
			}, 2000);
	    });
    }
		//main search
    vm.searchEmployee = function (searchempDate) {
    	$localStorage.searchByDate = searchempDate;
    	$scope.searchData = {employeeNumber:$scope.employeeNumber,firstName:$scope.firstName,lastName:$scope.lastName,officeEmail:$scope.officeEmail,searchDate:searchempDate};
		var searchData = JSON.stringify($scope.searchData);
		console.log(searchData);
		vm.search(searchData);
		console.log($localStorage.searchByDate);
    }
	$scope.selectRow = function (item) {
        // for checking single row selection
        $scope.isSelectedRow = item.employeeId;
				$localStorage.employeeId = item.employeeId;
        toggleButtons();
        if (item != undefined) {
            $scope.selectedRowDetails = item;
        }
    }

    $scope.clearSelectedRow = function () {
        $scope.isSelectedRow = null;
        vm.osiUser = {
        		id:''
        };
        toggleButtons();
    }
    vm.clearEmployeeSearch = function () {
    	$("#employeeSearchDate").val("");
    	$scope.searchempDate = '';
		$localStorage.searchByDate = undefined;
    }
	$scope.operationsGenericFunction = function (doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            //createOsiEmployees();
        	toggleButtons();
        	$localStorage.iseditable = true;
   	     	$localStorage.employeeId = undefined;
   	     	window.location.href = "#/employeeInfo-sf";
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiEmployeesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            //editOsiEmployeesDetails($scope.selectedRowDetails);
        	$scope.editSelectedRowDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiEmployeesDetails(OsiEmployees) {
		 	$localStorage.iseditable = false;
		 	$localStorage.employeeId = OsiEmployees.employeeId;
	     	/*window.location.href = "#/employeeInfo-sf";*/
		 	 $localStorage.empId = OsiEmployees.employeeId;
		     if($localStorage.isUserResponsibility){
				loadEmployeeBasicInfo();
		     }
		     else if($localStorage.isFunctionExclusion){
				loadEmployeeBasicInfo();
		     }
		     else if($localStorage.isOperationExclusion){
				loadEmployeeBasicInfo();
		     }
			 else{
				 $("#employeeInfo-sf").addClass("dynatree-active");
			 	$(".Employee_List").removeClass("dynatree-active");
				 $location.path("/employeeInfo-sf");
				//$localStorage.functionId = 25;
				$("#employeeInfo-sf").click();
			 }
	    }
	 function editOsiEmployeesDetails(OsiEmployees) {
        $scope.headername = "Edit Employee";
        $rootScope.isTrascError = false;
    }


    function toggleButtons() {
        if ($scope.isSelectedRow == null) {
            $scope.disable_Edit = true;
            $scope.disable_View = true;
            $scope.disable_Delete = true;
        } else {
            $scope.disable_Edit = false;
            $scope.disable_View = false;
            $scope.disable_Delete = false;
        }
    }

	 function createOsiEmployees() {
        $scope.headername = "Create Employee";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
    };

    $scope.searchOsiEmployeess = function () {
		$scope.searchData = {employeeNumber:$scope.employeeNumber,firstName:$scope.firstName,lastName:$scope.lastName,officeEmail:$scope.officeEmail};
		var searchData = JSON.stringify($scope.searchData);
		console.log(searchData);
		openorgNamePopUp();
    };

    $scope.clearSearch = function () {
        $scope.employeeNumber='';$scope.firstName='';$scope.lastName='';$scope.officeEmail='';$scope.searchempDate='';
        $scope.clearSelectedRow();
		$localStorage.employeeId = $rootScope.globals.userDTO.id;
		$localStorage.searchByDate = $localStorage.loggedInDate;
        //$scope.init();
    };

 //open Search PopUp
 function openorgNamePopUp() {
	 /*if(!$localStorage.isReturn){
		 $('#orgNameModal').modal({
			 show: true,
			 backdrop: 'static',
			 keyboard: false
		 });
	}else{*/

	/*}*/
	 $scope.selectedorgName = null;
	 $scope.addButton = false;
	 /*if($localStorage.searchByDate)
		 $scope.searchempDate = $localStorage.searchByDate;
	 else {*/
		 var today = new Date();
		 var dd = today.getDate();
		 var mm = today.getMonth()+1; //January is 0!

		 var yyyy = today.getFullYear();
		 if(dd<10){
		     dd='0'+dd;
		 }
		 if(mm<10){
		     mm='0'+mm;
		 }
		 var today = yyyy+'-'+mm+'-'+dd+' ';
		 $scope.searchempDate = today;
		 $localStorage.searchByDate = today;
		 vm.searchEmployee($localStorage.searchByDate);
		/* }*/
 }

//closing search popUp on Clicking 'CLOSE' and 'X' buttons.
$scope.closeorgNameModal = function() {
	$('#orgNameModal').modal('hide');
};
$scope.init();

}
