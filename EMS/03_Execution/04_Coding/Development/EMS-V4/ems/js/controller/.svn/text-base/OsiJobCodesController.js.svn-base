'use strict';
angular.module('myApp.OsiJobCodesController', []).controller('OsiJobCodesController',
        OsiJobCodesController);
OsiJobCodesController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiJobCodesService', 'FlashService', '$timeout','appConstants'];
function OsiJobCodesController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiJobCodesService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.jobCodeList = "";
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
    $scope.editJobName=null;
    $scope.jobCodeList = [];
	$scope.OsiJobCodes = [];  
    $scope.duplicateJobCode=false;
    $scope.sortKey = 'jobCodeName';
    $scope.sort = function (keyname) {
        if ($scope.jobCodeList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiJobCodes){
     editOsiJobCodesDetails(OsiJobCodes);
     $scope.isSelectedRow = OsiJobCodes.jobCodeId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiJobCodesService.searchOsiJobCodess(searchData).then(function (data) {
            $scope.jobCodeList = data;
            $scope.sort('jobCodeName');
            $scope.reverse=false;
        }).catch(function(error){
        	var msg = appConstants.exceptionMessage;
	   		  if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  }
            $scope.failureTextAlert = msg;
            $scope.showFailureAlert = true;
            $timeout(function () {
                    $scope.showFailureAlert= false;
            }, 3000);
        });
        */
    	OsiJobCodesService.getAllorganizations().then(function(data){
    		$scope.organizations=data;
    	}).catch(function(error){
    		var msg = appConstants.exceptionMessage;
	   		  if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  }
	   		  $scope.failureTextAlert = msg;
            $scope.showFailureAlert = true;
            $timeout(function () {
                    $scope.showFailureAlert= false;
            }, 3000);
        });
    	
    };

	$scope.selectRow = function (item) {
        // for checking single row selection
        $scope.isSelectedRow = item.jobCodeId;
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
            createOsiJobCodes();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiJobCodesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiJobCodesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }
	
	 function viewOsiJobCodesDetails(OsiJobCodes) {
	        $scope.headername = "View Job Title";
	        $rootScope.isTrascError = false;
	        OsiJobCodesService.getOsiJobCodes(OsiJobCodes.jobCodeId).then(function (data) {
	            $scope.OsiJobCodes = data;
	        }).catch(function(error){
	        	var msg = appConstants.exceptionMessage;
		   		  if(error.data.httpStatus){ 
		   			  msg=error.data.errorMessage; 
		   		  }
		   		  $scope.failureTextAlert = msg;
	            $scope.showFailureAlert = true;
	            $timeout(function () {
	                    $scope.showFailureAlert= false;
	            }, 3000);
	        });
			$scope.OsiJobCodes.jobCodeId = $scope.OsiJobCodes.jobCodeId;$scope.OsiJobCodes.jobCodeName = $scope.OsiJobCodes.jobCodeName;$scope.OsiJobCodes.jobCodeDescription = $scope.OsiJobCodes.jobCodeDescription;$scope.OsiJobCodes.active = $scope.OsiJobCodes.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiJobCodes-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiJobCodesDetails(OsiJobCodes) {
        $scope.headername = "Edit Job Title";
        $rootScope.isTrascError = false;
        
        OsiJobCodesService.getOsiJobCodes(OsiJobCodes.jobCodeId).then(function (data) {
            $scope.OsiJobCodes = data;
            $scope.editJobName=data.jobCodeName;
        }).catch(function(error){
            var errmsg=appConstants.exceptionMessage;
            if(error.errorMessage!=undefined){
                errmsg=error.errorMessage;
            }
            $scope.failureTextAlert = errmsg;
            $scope.showFailureAlert = true;
            $timeout(function () {
                    $scope.showFailureAlert= false;
            }, 3000);
        });
		$scope.OsiJobCodes.jobCodeId = $scope.OsiJobCodes.jobCodeId;$scope.OsiJobCodes.jobCodeName = $scope.OsiJobCodes.jobCodeName;$scope.OsiJobCodes.jobCodeDescription = $scope.OsiJobCodes.jobCodeDescription;$scope.OsiJobCodes.active = $scope.OsiJobCodes.active;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiJobCodes-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiJobCodes() {
        $scope.headername = "Create Job Title";
        $rootScope.isTrascError = false;
        $scope.iseditable = true; 
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiJobCodes.jobCodeId = '';$scope.OsiJobCodes.version = '';$scope.OsiJobCodes.jobCodeName = '';$scope.OsiJobCodes.jobCodeDescription = '';$scope.OsiJobCodes.active = 0;$scope.OsiJobCodes.orgId = '';
       $('#OsiJobCodes-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiJobCodess = function () {
		$scope.searchData = {jobCodeName:$scope.jobCodeName}; var searchData = JSON.stringify($scope.searchData);
		OsiJobCodesService.searchOsiJobCodess(searchData).then(function (data) {
                $scope.jobCodeList = data;
            }).catch(function(error){
                var errmsg=appConstants.exceptionMessage;
                if(error.errorMessage!=undefined){
                    errmsg=error.errorMessage;
                }
                $scope.failureTextAlert = errmsg;
                $scope.showFailureAlert = true;
                $timeout(function () {
                        $scope.showFailureAlert= false;
                }, 3000);
            });
    };

    $scope.clearSearch = function () {
        $scope.jobCodeName='';
        $scope.clearSelectedRow();
        $scope.jobCodeList = "";
      //  $scope.init();
    };
    function validateDuplicateJobEntry(OsiJobCodes){
    	if($scope.headername=='Edit Job Title' && $scope.editJobName!=null && $scope.editJobName.toUpperCase()== OsiJobCodes.jobCodeName.toUpperCase() ){
    		//Do Nothing
    	}
    	if($scope.headername =='Create Job Title' && $scope.editJobName!=null  && $scope.editJobName.toUpperCase()== OsiJobCodes.jobCodeName.toUpperCase()){
    		for(var i=0;i<$scope.jobCodeList.length;i++){
        		if(OsiJobCodes.orgId==$scope.jobCodeList[i].orgId && OsiJobCodes.jobCodeName.toUpperCase()==$scope.jobCodeList[i].jobCodeName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Job name already exists for this organization.'); 
        			 $scope.continuesave = false;
    				 break;
    			}
    		}
    	}
    	if($scope.headername =='Edit Job Title' && $scope.editJobName!=null  && $scope.editJobName.toUpperCase()!= OsiJobCodes.jobCodeName.toUpperCase()){
    		for(var i=0;i<$scope.jobCodeList.length;i++){
        		if(OsiJobCodes.orgId==$scope.jobCodeList[i].orgId && OsiJobCodes.jobCodeName.toUpperCase()==$scope.jobCodeList[i].jobCodeName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Job name already exists for this organization.'); 
        			 $scope.continuesave = false;
    				 break;
    			}
    		}
    	}

    }
    $scope.saveOsiJobCodes = function (OsiJobCodes) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiJobCodes.jobCodeName){ 
			 $rootScope.isTrascError = true;
			 FlashService.Error('Please Enter Job Title'); 
			 $scope.continuesave = false;
			 
        }   else if($scope.duplicateJobCode==true){
        	$rootScope.isTrascError = true;
        	 FlashService.Error('Job Title already Exists'); 
        	 $scope.continuesave = false;
        
        } else if(!$scope.OsiJobCodes.orgId){ 
	 $rootScope.isTrascError = true;
	 FlashService.Error('Please Select Organization');  
	 $scope.continuesave = false;}
 else{validateDuplicateJobEntry(OsiJobCodes);}/*else if(!$scope.OsiJobCodes.active){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Active'); 
 $scope.continuesave = false;}*/
        if(!$scope.OsiJobCodes.version || $scope.OsiJobCodes.version==''){$scope.OsiJobCodes.version = 0;}
        if ($scope.continuesave) {
            if (!$scope.OsiJobCodes.jobCodeId) {
            	
            	$scope.OsiJobCodes = {
                    	"jobCodeId":$scope.OsiJobCodes.jobCodeId,"version":$scope.OsiJobCodes.version,"jobCodeName":$scope.OsiJobCodes.jobCodeName,"jobCodeDescription":$scope.OsiJobCodes.jobCodeDescription,"active":$scope.OsiJobCodes.active,"orgId":$scope.OsiJobCodes.orgId	
                    };
                OsiJobCodesService
                        .saveOsiJobCodes($scope.OsiJobCodes)
                        .then(
                                function (result) {
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiJobCodes-model').modal('hide');
                                        $scope.init();
                                        $scope.successTextAlert = result.response;
                                        $scope.showSuccessAlert = true;
                                        $scope.searchOsiJobCodess();
                                    } else {
                                        $rootScope.isTrascError = true;
                                        FlashService.Error(appConstants.successMessage);
                                        $scope.continuesave = false;
                                    }
                                    $timeout(function () {
                                        $scope.showSuccessAlert = false;
                                    }, 5000);
                                }).catch(function(error){
                                    var errmsg=appConstants.exceptionMessage;
                                    if(error.errorMessage!=undefined){
                                        errmsg=error.errorMessage;
                                    }
                                    $rootScope.isTrascError = true;
                                    FlashService.Error(errmsg);
                                    $timeout(function () {
                                            $rootScope.isTrascError= false;
                                    }, 3000);
                                    
                                });
            } else {
                OsiJobCodesService
                        .updateOsiJobCodes($scope.OsiJobCodes)
                        .then(
                                function (result) {
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiJobCodes-model').modal('hide');
                                        $scope.init();
                                        $scope.successTextAlert = appConstants.successMessage;
                                        $scope.showSuccessAlert = true;
                                        $scope.searchOsiJobCodess();
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
                                    var errmsg=appConstants.exceptionMessage;
                                    if(error.errorMessage!=undefined){
                                        errmsg=error.errorMessage;
                                    }
                                    $rootScope.isTrascError = true;
                                    FlashService.Error(errmsg);
                                    $timeout(function () {
                                            $rootScope.isTrascError= false;
                                    }, 3000);
                                    
                                });
            }
    }
};
    
	
	
    $scope.init();
}
