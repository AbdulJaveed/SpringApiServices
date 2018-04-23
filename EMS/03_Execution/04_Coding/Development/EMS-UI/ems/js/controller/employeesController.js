'use strict';
angular.module('myApp.employeesController', []).controller('EmployeesController',
		EmployeesController);
EmployeesController.$inject = ['configData', '$q', '$scope', '$rootScope', '$window', '$location', '$cookieStore',
    '$http', '$localStorage', 'EmployeesService', 'FlashService', '$timeout','appConstants','LookupService','employeeBasicInfoService', 'organizationService'];
function EmployeesController(configData, $q, $scope, $rootScope, $window, $location, $http, $cookieStore,
        $localStorage, EmployeesService, FlashService, $timeout,appConstants,LookupService,employeeBasicInfoService,organizationService, Upload) {
    var vm = this;
	$scope.usOrgCode = configData.usOrgCode;
	$scope.isAccessControl = false;
	if($localStorage.isUserResponsibility || $localStorage.isFunctionExclusion ||$localStorage.isOperationExclusion ){
		$scope.isAccessControl = true;
	 }
	 
	 
    /*LookupService.getLookupByLookupName('GENDER').then(function (result) {
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
	});*/

    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.employeeList = "";
    $scope.iseditable = true;
    if($localStorage.isReturn === undefined){
    	$localStorage.isReturn = false;
    }
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
			$scope.isSelectedRow = OsiEmployees.employeeId;
	     toggleButtons();
	     $localStorage.iseditable = true;
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
			 $(".Basic_Information").addClass("dynatree-active");
		 	$(".Employee_List").removeClass("dynatree-active");
			 $location.path("/employeeInfo");
			 //$localStorage.functionId = 11;
			 $localStorage.orgId = OsiEmployees.orgId;
			 $("#employeeInfo").click();
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
	    EmployeesService.searchOsiEmployeess(searchData).then(function (data) {
					console.log(data);
	        $scope.employeeList = data;
	        /*$scope.closeorgNameModal();*/
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
    vm.searchEmployee = function (searchempDate,fromOsiEmployeesBtn) {
    	$localStorage.searchByDate = searchempDate;
    	$scope.searchData = {employeeNumber:$scope.employeeNumber,firstName:$scope.firstName,lastName:$scope.lastName,officeEmail:$scope.officeEmail,searchDate:searchempDate};
		var searchData = JSON.stringify($scope.searchData);
		if(fromOsiEmployeesBtn === 'fromOsiEmployeesBtn')
			vm.search(searchData);
		else{
			$scope.closeorgNameModal();
		}
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
   	     	$("#dynatree-id-_17").find("span:first").addClass("dynatree-active");
	 		$("#dynatree-id-_16").find("span:first").removeClass("dynatree-active");
   	     	window.location.href = "#/employeeInfo";
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
				 $("#employeeInfo").addClass("dynatree-active");
			 	$(".Employee_List").removeClass("dynatree-active");
				 $location.path("/employeeInfo");
				 //$localStorage.functionId = 11;
				 $("#employeeInfo").click();
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
    	$localStorage.searchByDate =$scope.searchempDate;
		$scope.searchData = {employeeNumber:$scope.employeeNumber,firstName:$scope.firstName,lastName:$scope.lastName,officeEmail:$scope.officeEmail};
		var searchData = JSON.stringify($scope.searchData);
		openorgNamePopUp('OnSearchBtnClick','fromOsiEmployeesBtn');
    };

    $scope.clearSearch = function () {
        $scope.employeeNumber='';$scope.firstName='';$scope.lastName='';$scope.officeEmail='';$scope.searchempDate='';
        $scope.clearSelectedRow();
		$localStorage.employeeId = $rootScope.globals.userDTO.id;
		$localStorage.searchByDate = $localStorage.loggedInDate;
		$scope.searchempDate = $localStorage.loggedInDate;
		vm.searchEmployee($localStorage.searchByDate,'fromOsiEmployeesBtn');
    };

 //open Search PopUp
 function openorgNamePopUp(OnSearchBtnClick,fromOsiEmployeesBtn) {

	 if(!$localStorage.isReturn && OnSearchBtnClick !=='OnSearchBtnClick'){
		 $('#orgNameModal').modal({
			 show: true,
			 backdrop: 'static',
			 keyboard: false
		 });
	}else{
		vm.searchEmployee($localStorage.searchByDate,fromOsiEmployeesBtn);
	}
	 $scope.selectedorgName = null;
	 $scope.addButton = false;
	 if($localStorage.searchByDate)
		 $scope.searchempDate = $localStorage.searchByDate;
	 else {
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
		 var today = yyyy+'-'+mm+'-'+dd;
		 	$scope.searchempDate = today;
		 }
 }

//closing search popUp on Clicking 'CLOSE' and 'X' buttons.
$scope.closeorgNameModal = function() {
	$('#orgNameModal').modal('hide');
};
$scope.init();

}
