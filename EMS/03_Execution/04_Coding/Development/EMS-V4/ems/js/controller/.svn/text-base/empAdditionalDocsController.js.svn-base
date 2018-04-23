'use strict';
angular.module('myApp.EmpAdditionalDocsController', []).controller('EmpAdditionalDocsController',
EmpAdditionalDocsController);
		EmpAdditionalDocsController.$inject = ['AuthenticationService', 'configData', '$q', '$scope', '$rootScope', '$window', '$location', '$cookieStore',
    '$http', '$localStorage', 'EmployeesService', 'FlashService', '$timeout','appConstants','LookupService','employeeBasicInfoService','Upload'];
function EmpAdditionalDocsController(AuthenticationService, configData, $q, $scope, $rootScope, $window, $location, $http, $cookieStore,
        $localStorage, EmployeesService, FlashService, $timeout,appConstants,LookupService,employeeBasicInfoService, Upload) {
    var vm = this;
	$scope.usOrgCode = configData.usOrgCode;
	$scope.isAccessControl = false;
	if($localStorage.isUserResponsibility || $localStorage.isFunctionExclusion ||$localStorage.isOperationExclusion ){
		$scope.isAccessControl = true;
	 }
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
    $scope.empAdditionalDocsList = [];
	$scope.additionalDocs = {
		attachments : {}
	};
    $scope.sort = function (keyname) {
        if ($scope.employeeList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }
	function loadEmployeeBasicInfo(){
		if($localStorage.employeeId && $localStorage.searchByDate) {
			var inputObject = { "employeeId" : $localStorage.employeeId};
				employeeBasicInfoService.getEmpDocumentsById($localStorage.employeeId).then(function success(result) {
					console.log(result);
					$scope.empAdditionalDocsList = result;
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
	$scope.viewSelectedRowDetails = function(empDocs){
		$scope.headername = 'View Document';
		//$('#EmployeeAdditionalDocs-model').modal('show');
		$('#EmployeeAdditionalDocs-model').modal({backdrop: 'static', keyboard: false});
		$scope.isSelectedRow = empDocs.docId;
		toggleButtons();
		$scope.iseditable = false;
		$scope.isUpdatable = false;
		$scope.additionalDocs.docId = empDocs.docId;
		$scope.additionalDocs.description = empDocs.description;
		$scope.additionalDocs.employeeId = empDocs.employeeId;
		$scope.additionalDocs.attachmentId = empDocs.attachmentId;
		$scope.additionalDocs.attachments = empDocs.attachments;
		vm.preview.content = empDocs.attachments.fileContent;
		vm.preview.name = empDocs.attachments.originalFileName;
	}

	$scope.editSelectedRowDetails = function(empDocs){
		$scope.headername = 'Edit Document';
		//$('#EmployeeAdditionalDocs-model').modal('show');
		$('#EmployeeAdditionalDocs-model').modal({backdrop: 'static', keyboard: false});
		$scope.isSelectedRow = empDocs.docId;
		toggleButtons();
		$scope.iseditable = true;
		$scope.isUpdatable = true;
		$scope.additionalDocs.docId = empDocs.docId;
		$scope.additionalDocs.description = empDocs.description;
		$scope.additionalDocs.employeeId = empDocs.employeeId;
		$scope.additionalDocs.attachmentId = empDocs.attachmentId;
		$scope.additionalDocs.attachments = empDocs.attachments;
		vm.preview.content = empDocs.attachments.fileContent;
		vm.preview.name = empDocs.attachments.originalFileName;
	}
	var  urlPath = $location.path();
	var funcId = "";
	if(urlPath.includes('additionalDocs-sf')){
	   funcId = $rootScope.profileAcess.additionalFuncId;
	}else if(urlPath.includes('additionalDocs')){
	   funcId = $rootScope.profileAcessHR.additionalFuncId;
	}

	AuthenticationService.getUserExFunctions(funcId, $rootScope.globals.userDTO.id).then(function(data){
		$scope.availOperations = data.data;
		console.log($scope.availOperations);
	});
  /*  $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400); */
    $scope.init = function () {
		
		
		loadEmployeeBasicInfo();
    };
	
	$scope.selectRow = function (item) {
        // for checking single row selection
        $scope.isSelectedRow = item.docId;
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
    }
	$scope.operationsGenericFunction = function (doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            //createOsiEmployees();
        	toggleButtons();
			$localStorage.iseditable = true;
			$scope.iseditable = true;
			$scope.isUpdatable = false;
			$scope.headername = 'Upload Document';
			//$('#EmployeeAdditionalDocs-model').modal('show');
			$('#EmployeeAdditionalDocs-model').modal({backdrop: 'static', keyboard: false});
        }
        if (doFunction === "View" && !$scope.disable_View) {
			$scope.viewSelectedRowDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
			$scope.headername = 'Edit Document';
        	$scope.editSelectedRowDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	$scope.cancelProfile = function(){
		if($localStorage.currentEmployeeId!=$localStorage.loggedInEmployeeId)
			$window.location.href='#/employeeInfo-sf';
		else
		$window.location.href='#/profile';
	}
	$scope.backToEmployeeList = function(){
        $window.location.href='#/employeeInfo';
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
        $scope.isUpdatable=true;
    };

//closing search popUp on Clicking 'CLOSE' and 'X' buttons.
$scope.closeorgNameModal = function() {
	$('#orgNameModal').modal('hide');
};
$scope.isValid = false;
function validate() {
	var msg = '';
	if($scope.additionalDocs.description == undefined 
		|| $scope.additionalDocs.description == '' 
		|| $scope.additionalDocs.description == null){
			$scope.isValid = false;
			msg = 'Please Enter Document Description';
	} else if(_.isEmpty(file) && !$scope.additionalDocs.attachments) {
		$scope.isValid = false;
		msg = 'Please Upload a File';
	} else {
		//!()
		$scope.isValid = true;
		msg = '';
	}

	if(!$scope.isValid) {
		$rootScope.isTrascError = true;
		FlashService.Error(msg);
		$timeout(function () {
			$rootScope.isTrascError = false;
		}, 2000);
	}
}
$scope.clearData = function() {
	$scope.additionalDocs = {};
	vm.preview = {};
	$scope.iseditable = true;
}
$scope.saveAdditionaDocs = function(object) {
	console.log(object);
	validate();
	if($scope.isValid) {
		console.log('form valid..............');
		console.log(file);
		$scope.additionalDocs.employeeId = $localStorage.employeeId;
		if(!_.isEmpty(file)) {
			$scope.additionalDocs.attachments = {};
			$scope.additionalDocs.attachments.fileContent = file.fileContent;
			$scope.additionalDocs.attachments.fileType = file.fileType;
			$scope.additionalDocs.attachments.originalFileName = file.originalFileName;
			$scope.additionalDocs.attachments.fileContent = file.fileContent;
			$scope.additionalDocs.attachments.objectId = $localStorage.employeeId;
		}
		console.log($scope.additionalDocs);
		employeeBasicInfoService.saveAdditionaDocs($scope.additionalDocs).then(function(data){
			$scope.showSuccessAlert = true;
			var msg = appConstants.successMessage;
			$scope.successTextAlert = msg;
			$('#EmployeeAdditionalDocs-model').modal('hide');
			$timeout(function () {
				$scope.showSuccessAlert=false;
				loadEmployeeBasicInfo();
				$scope.additionalDocs = {};
				vm.preview = {};
			}, 2000);

		}).catch(function(error){
			var msg = appConstants.exceptionMessage;
			if(error.data.httpStatus){ 
				msg=error.data.errorMessage; 
				}
			//vm.displayErrorMsg(msg,'error');
		});
	}
}


$scope.download = function (item, index) {
	console.log(item);
	var dlnk = document.getElementById('resume'+index);
	dlnk.setAttribute("download", item.attachments.originalFileName);
	dlnk.href = item.attachments.fileContent;
	dlnk.click();
};

//Resume attachment code

vm.preview = {};
var file = {};
this.load = loadFile;
function loadFile(fileUpload) {
		$rootScope.isTrascErrorForPersonal = false;
		if (fileUpload) {
			if (fileUpload && (fileUpload.type == 'image/jpeg' || fileUpload.type == 'image/jpg' || fileUpload.type == 'image/png' ||fileUpload.type == 'image/gif' 
				|| fileUpload.type == 'text/xml' 
				|| fileUpload.type == 'application/pdf' 
				|| fileUpload.type == 'text/plain'
				|| fileUpload.type == 'application/msword'
				|| fileUpload.type == 'application/vnd.ms-excel'
				|| fileUpload.type == 'application/vnd.openxmlformats-officedocument.wordprocessingml.document')) {
				if (fileUpload.size > 0 && fileUpload.size <= 5242880) {
					file.fileType = fileUpload.type;
					file.originalFileName = fileUpload.name;
					file.duplicateFileName = vm.preview.duplicateFileName;
					file.attachmentId = vm.preview.attachmentId;
					Upload.base64DataUrl(fileUpload).then(function () {
						var str = fileUpload.$ngfDataUrl;
						console.log(fileUpload.type);
						file.fileContent = str.substring(str.indexOf(',') + 1);
						if (!(_.isEmpty(file))) {
							/*if (fileUpload.type == 'application/pdf')
								vm.preview.content = 'components/fileUpload/pdf.jpg';
							else if(fileUpload.type == 'text/plain')
								vm.preview.content = 'components/fileUpload/txt.png';
							else */
								vm.preview.content = fileUpload;
							vm.preview.name = fileUpload.name;
							console.log(vm.preview.content);
						}
					});

				} else {
					var msg = 'Image file size should be less than 5MB';
					vm.preview = {};
					vm.preview.content = undefined;
					vm.preview.name = '';
					file = {};
					$rootScope.isTrascError = true;
					$rootScope.flash = {
						message: 'Image file size should be less than 5MB',
						type: 'error'
					};
					$timeout(function () {
						$rootScope.isTrascError = false;
					}, 1500);
				}
			} else {
				vm.preview = {};
				vm.preview.content = undefined;
				vm.preview.name = '';
				file = {};
				$rootScope.isTrascError = true;
				$rootScope.flash = {
					message: 'Invalid File Format',
					type: 'error'
				};
				$timeout(function () {
					$rootScope.isTrascError = false;
				}, 1500);
			}
		}
	};
$scope.init();
}