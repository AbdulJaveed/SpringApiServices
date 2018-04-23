'use strict';
angular.module('myApp.OsiEmployeesController', []).controller('OsiEmployeesController',
        OsiEmployeesController);
OsiEmployeesController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiEmployeesService', 'FlashService', '$timeout','appConstants'];
function OsiEmployeesController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiEmployeesService, FlashService, $timeout,appConstants) {
    var vm = this;
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

$scope.editSelectedRowDetails = function(OsiEmployees){
     editOsiEmployeesDetails(OsiEmployees);
     $scope.isSelectedRow = OsiEmployees.id;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
    	var searchData = JSON.stringify({});
    	OsiEmployeesService.searchOsiEmployeess(searchData).then(function (data) {
            $scope.employeeList = data;
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
    };

	$scope.selectRow = function (item) {
        // for checking single row selection
        $scope.isSelectedRow = item.id;
        toggleButtons();
        if (item != undefined) {
            $scope.selectedRowDetails = item;
        } 
    }
	
    $scope.clearSelectedRow = function () {
        $scope.isSelectedRow = null;
        toggleButtons();
    }

	$scope.operationsGenericFunction = function (doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            createOsiEmployees();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiEmployeesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiEmployeesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }
	
	 function viewOsiEmployeesDetails(OsiEmployees) {
	        $scope.headername = "View Employee";
	        $rootScope.isTrascError = false;
	        OsiEmployeesService.getOsiEmployees(OsiEmployees.id).then(function (data) {
	            $scope.OsiEmployees = data;
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
			$scope.OsiEmployees.id = $scope.OsiEmployees.id;$scope.OsiEmployees.employeeId = $scope.OsiEmployees.employeeId;$scope.OsiEmployees.version = $scope.OsiEmployees.version;$scope.OsiEmployees.organization=[];$scope.OsiEmployees.organization.orgName = $scope.OsiEmployees.organization.orgName;$scope.OsiEmployees.title = $scope.OsiEmployees.title;$scope.OsiEmployees.firstName = $scope.OsiEmployees.firstName;$scope.OsiEmployees.lastName = $scope.OsiEmployees.lastName;$scope.OsiEmployees.middleName = $scope.OsiEmployees.middleName;$scope.OsiEmployees.companyMailId = $scope.OsiEmployees.companyMailId;$scope.OsiEmployees.mobileNumber = $scope.OsiEmployees.mobileNumber;$scope.OsiEmployees.locationName = $scope.OsiEmployees.locationName;$scope.OsiEmployees.seatingLocation = $scope.OsiEmployees.seatingLocation;$scope.OsiEmployees.phoneExtension = $scope.OsiEmployees.phoneExtension;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiEmployees-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiEmployeesDetails(OsiEmployees) {
        $scope.headername = "Edit Employee";
        $rootScope.isTrascError = false;
        OsiEmployeesService.getOsiEmployees(OsiEmployees.id).then(function (data) {
            $scope.OsiEmployees = data;
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
		$scope.OsiEmployees.id = $scope.OsiEmployees.id;$scope.OsiEmployees.employeeId = $scope.OsiEmployees.employeeId;$scope.OsiEmployees.version = $scope.OsiEmployees.version;$scope.OsiEmployees.organization=[];$scope.OsiEmployees.organization.orgName = $scope.OsiEmployees.organization.orgName;$scope.OsiEmployees.title = $scope.OsiEmployees.title;$scope.OsiEmployees.firstName = $scope.OsiEmployees.firstName;$scope.OsiEmployees.lastName = $scope.OsiEmployees.lastName;$scope.OsiEmployees.middleName = $scope.OsiEmployees.middleName;$scope.OsiEmployees.companyMailId = $scope.OsiEmployees.companyMailId;$scope.OsiEmployees.mobileNumber = $scope.OsiEmployees.mobileNumber;$scope.OsiEmployees.locationName = $scope.OsiEmployees.locationName;$scope.OsiEmployees.seatingLocation = $scope.OsiEmployees.seatingLocation;$scope.OsiEmployees.phoneExtension = $scope.OsiEmployees.phoneExtension;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiEmployees-model').modal({show: true, backdrop: 'static'});
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
        $scope.OsiEmployees.id = '';$scope.OsiEmployees.employeeId = '';$scope.OsiEmployees.version = '';$scope.OsiEmployees.organization=[];$scope.OsiEmployees.organization.orgName = '';$scope.OsiEmployees.title = '';$scope.OsiEmployees.firstName = '';$scope.OsiEmployees.lastName = '';$scope.OsiEmployees.middleName = '';$scope.OsiEmployees.companyMailId = '';$scope.OsiEmployees.mobileNumber = '';$scope.OsiEmployees.locationName = '';$scope.OsiEmployees.seatingLocation = '';$scope.OsiEmployees.phoneExtension = '';
       $('#OsiEmployees-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiEmployeess = function () {
		$scope.searchData = {employeeId:$scope.employeeId,firstName:$scope.firstName,lastName:$scope.lastName,companyEmailId:$scope.companyEmailId}; var searchData = JSON.stringify($scope.searchData);
		OsiEmployeesService.searchOsiEmployeess(searchData).then(function (data) {
                $scope.employeeList = data;
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
    };

    $scope.clearSearch = function () {
        $scope.employeeId='';$scope.firstName='';$scope.lastName='';$scope.companyEmailId='';
        $scope.clearSelectedRow();
        $scope.employeeList="";
    };
   
    $scope.saveOsiEmployees = function (OsiEmployees) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiEmployees.organization.orgName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Organization'); 
 $scope.continuesave = false;}else if(!$scope.OsiEmployees.title){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Title'); 
 $scope.continuesave = false;}else if(!$scope.OsiEmployees.firstName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter First Name'); 
 $scope.continuesave = false;}else if(!$scope.OsiEmployees.lastName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Last Name'); 
 $scope.continuesave = false;}else if(!$scope.OsiEmployees.middleName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Middle Name'); 
 $scope.continuesave = false;}else if(!$scope.OsiEmployees.companyMailId){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Company Mail ID'); 
 $scope.continuesave = false;}else if(!$scope.OsiEmployees.mobileNumber){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Mobile Number'); 
 $scope.continuesave = false;}else if(!$scope.OsiEmployees.locationName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Campus'); 
 $scope.continuesave = false;}
        if(!$scope.OsiEmployees.version || $scope.OsiEmployees.version==''){$scope.OsiEmployees.version = 0;}if(!$scope.OsiEmployees.organization.version || $scope.OsiEmployees.organization.version==''){$scope.OsiEmployees.organization.version = 0;}
        if ($scope.continuesave) {
            if (!$scope.OsiEmployees.id) {
            	
            	$scope.OsiEmployees = {
                    	"id":$scope.OsiEmployees.id,"employeeId":$scope.OsiEmployees.employeeId,"version":$scope.OsiEmployees.version,"organization":$scope.OsiEmployees.organization,"title":$scope.OsiEmployees.title,"firstName":$scope.OsiEmployees.firstName,"lastName":$scope.OsiEmployees.lastName,"middleName":$scope.OsiEmployees.middleName,"companyMailId":$scope.OsiEmployees.companyMailId,"mobileNumber":$scope.OsiEmployees.mobileNumber,"locationName":$scope.OsiEmployees.locationName,"seatingLocation":$scope.OsiEmployees.seatingLocation,"phoneExtension":$scope.OsiEmployees.phoneExtension	
                    };
                OsiEmployeesService
                        .saveOsiEmployees($scope.OsiEmployees)
                        .then(
                                function (result) {
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiEmployees-model').modal('hide');
                                        $scope.init();
                                        $scope.successTextAlert = appConstants.successMessage;
                                        $scope.showSuccessAlert = true;
                                    } else {
                                        $rootScope.isTrascError = true;
                                        FlashService.Error(result.response);
                                        $scope.continuesave = false;
                                    }
                                    $timeout(function () {
                                        $scope.showSuccessAlert = false;
                                    }, 5000);
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
            } else {
                OsiEmployeesService
                        .updateOsiEmployees($scope.OsiEmployees)
                        .then(
                                function (result) {
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiEmployees-model').modal('hide');
                                        $scope.init();
                                        $scope.successTextAlert = appConstants.successMessage;
                                        $scope.showSuccessAlert = true;
                                        $scope.clearSelectedRow();
                                    } else {
                                        $rootScope.isTrascError = true;
                                        FlashService.Error(result.response);
                                        $scope.continuesave = false;
                                    }
                                    $timeout(function () {
                                        $scope.showSuccessAlert = false;
                                    }, 5000);
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
            }
    }
};
    
	$scope.searchorgNameString = '';
$scope.populatedorgName = {}; //Object to be populated on selecting any from search results

$scope.populateorgName = function(orgNameObj){
	$scope.OsiEmployees.organization = {};
	    if (orgNameObj instanceof Array) {
	    	$scope.OsiEmployees.organization.orgName = orgNameObj[0].orgName;
	    	$scope.OsiEmployees.organization.id = orgNameObj[0].id;
	    }else{
	    	$scope.OsiEmployees.organization.orgName = orgNameObj.orgName;
	    	  $scope.OsiEmployees.organization.id = orgNameObj.id;
	    }
}

//on ng-blur of search input box and clicking search 'icon'
$scope.showorgNameSearchModal = function(enteredText) {
	 $scope.searchorgNameString = enteredText;
	 if($scope.populatedorgName.id) {
		 $scope.searchorgNameString = '';
		 openorgNamePopUp();
	 } else if($scope.searchorgNameString ? $scope.searchorgNameString.length : false) {
		 getorgNamesBySearch($scope.searchorgNameString).then(function (data) {
			 if(data ? data.length == 1 : false) {
				 $scope.populateorgName(data);
				 /*getCompleteorgNameObject(data[0].id).then(function (orgNameObj) {
					 $scope.populateorgName(orgNameObj); //CALL to populate selected Object to specific model.
					 return;
				 });*/
			 } else {
				 $scope.resultedorgNames = data;
				 openorgNamePopUp();
			 }
		 });
	 }
 };
 
 //open Search PopUp
 function openorgNamePopUp() {
	 $('#orgNameModal').modal({
		 show: true,
		 backdrop: 'static',
		 keyboard: false
	 }); 
	 $scope.selectedorgName = null;
	 $scope.addButton = false;
 }

//closing search popUp on Clicking 'CLOSE' and 'X' buttons.
$scope.closeorgNameModal = function() {
	$('#orgNameModal').modal('hide'); 
};

//call on clicking 'SEARCH' button
$scope.searchorgName = function(searchorgNameString) {
	getorgNamesBySearch(searchorgNameString).then(function (data) {
		$scope.resultedorgNames = data;
	});
	$scope.selectedorgName = null;
	$scope.addButton = false;
};

//call on clicking 'CLEAR' button
$scope.clearorgNameSearch = function() {
	$scope.resultedorgNames = [];
	$scope.searchorgNameString = '';
	$scope.addButton = false;
};

//function call on selecting/clicking(single) any orgName from search results
$scope.selectorgNameIndex = function (rowIndex) {
	$scope.addButton = true; 
	$scope.selectedorgName = rowIndex;
};

//function call of selecting an orgName and Clicking 'OK' button
$scope.selectorgName = function () {
	var orgName = $scope.resultedorgNames[$scope.selectedorgName];
	$scope.setorgName(orgName);
};

//function call on double-click of one of the search results
$scope.setorgName = function(orgName) {
	 if(!_.isEmpty(orgName)) {
			$scope.closeorgNameModal();
			$scope.populateorgName(orgName); //CALL to populate selected Object to specific model.
		}
	/*getCompleteorgNameObject(orgName.id).then(function (orgNameObj) {
		if(!_.isEmpty(orgNameObj)) {
			$scope.closeorgNameModal();
			$scope.populateorgName(orgNameObj); //CALL to populate selected Object to specific model.
		}
	});*/
};

// SERVICE CALL TO GET SEARCH RESULTS
function getorgNamesBySearch(searchorgNameString) {
	var deferred = $q.defer();
	// SERVICE CALL TO GET SEARCH RESULTS
	var searchData = JSON.stringify({'orgName':searchorgNameString});
	OsiEmployeesService.getorgNamesByStr(searchData).then(function (data) {
		if(data) {
			data = data.length > 500 ? data.slice(0, 501) : data; 
		}
		deferred.resolve(data);
	}).catch(function(error) {
		if(error!= undefined){
				if(error.errorMessage != undefined)
				{
					showError(error.errorMessage);
				}
		}else{
			showError(exceptionMessage);
		}
	});
	return deferred.promise;
}

// SERVICE CALL TO GET OBJECT of SELECTED orgName
function getCompleteorgNameObject(orgNameId) {
	var deferred = $q.defer();
	// SERVICE CALL TO GET OBJECT of SELECTED orgName
	OsiEmployeesService.getorgNameById(orgNameId).then(function(orgNameObj) {
		if(!_.isEmpty(orgNameObj)) {
			deferred.resolve(orgNameObj);
		}
	}).catch(function(error) {
		if(error!= undefined){
			if(error.errorMessage != undefined)
			{
				showLineError(error.errorMessage);
			}
		}else{
			showLineError(exceptionMessage);
		}
	});
	return deferred.promise;
}

	
    $scope.init();
}
