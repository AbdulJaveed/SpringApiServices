'use strict';
angular.module('myApp.OsiGradesController', []).controller('OsiGradesController',
        OsiGradesController);
OsiGradesController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiGradesService', 'FlashService', '$timeout','appConstants'];
function OsiGradesController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiGradesService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.gradeList = "";
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
    $scope.editGradeName=null;
    $scope.editSeq=null;
    $scope.gradeList = [];
	$scope.OsiGrades = [];  
    $scope.sort = function (keyname) {
        if ($scope.gradeList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiGrades){
     editOsiGradesDetails(OsiGrades);
     $scope.isSelectedRow = OsiGrades.gradeId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
    	var searchData = JSON.stringify({});
        /*
        OsiGradesService.searchOsiGradess(searchData).then(function (data) {
            $scope.gradeList = data;
            $scope.sort('gradeName');
            $scope.reverse=false;
        }).catch(function(error){
            var msg=appConstants.exceptionMessage;
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
    	OsiGradesService.getAllorganizations().then(function(data){
    		$scope.organizations=data;
    	}).catch(function(error){
    		var msg=appConstants.exceptionMessage;
            if(error.data.httpStatus){ 
   			  msg=error.data.errorMessage; 
   		  	}
            $scope.failureTextAlert = msg;
            $scope.showFailureAlert = true;
            $timeout(function () {
                    $scope.showFailureAlert= false;
            }, 3000);
        });
    	OsiGradesService.getAllOsiTitles().then(function(data){
    		$scope.titles=data;
    	}).catch(function(error){
    		var msg=appConstants.exceptionMessage;
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
        $scope.isSelectedRow = item.gradeId;
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
            createOsiGrades();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiGradesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiGradesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }
	
	 function viewOsiGradesDetails(OsiGrades) {
	        $scope.headername = "View Grade";
	        $rootScope.isTrascError = false;
	        OsiGradesService.getOsiGrades(OsiGrades.gradeId).then(function (data) {
	            $scope.OsiGrades = data;
	        }).catch(function(error){
	        	var msg=appConstants.exceptionMessage;
	            if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  	}
	            $scope.failureTextAlert = msg;
	            $scope.showFailureAlert = true;
	            $timeout(function () {
	                    $scope.showFailureAlert= false;
	            }, 3000);
	        });
			$scope.OsiGrades.gradeId = $scope.OsiGrades.gradeId;$scope.OsiGrades.gradeName = $scope.OsiGrades.gradeName;$scope.OsiGrades.gradeDescription = $scope.OsiGrades.gradeDescription;$scope.OsiGrades.seq = $scope.OsiGrades.seq;$scope.OsiGrades.costPerHour = $scope.OsiGrades.costPerHour;$scope.OsiGrades.costPerMonth = $scope.OsiGrades.costPerMonth;$scope.OsiGrades.costPerHour = $scope.OsiGrades.costPerHour;$scope.OsiGrades.costPerMonth = $scope.OsiGrades.costPerMonth;$scope.OsiGrades.active = $scope.OsiGrades.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiGrades-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiGradesDetails(OsiGrades) {
        $scope.headername = "Edit Grade";
        $rootScope.isTrascError = false;
        OsiGradesService.getOsiGrades(OsiGrades.gradeId).then(function (data) {
            $scope.OsiGrades = data;
            $scope.OsiGrades.costPerHour = $scope.OsiGrades.costPerHour.toFixed(2);
            $scope.OsiGrades.costPerMonth = $scope.OsiGrades.costPerMonth.toFixed(2);
            $scope.OsiGrades.revPerHour = $scope.OsiGrades.revPerHour.toFixed(2);
            $scope.OsiGrades.revPerMonth = $scope.OsiGrades.revPerMonth.toFixed(2);
            $scope.editGradeName=data.gradeName;
            $scope.editSeq=data.seq;
        }).catch(function(error){
        	var msg=appConstants.exceptionMessage;
            if(error.data.httpStatus){ 
   			  msg=error.data.errorMessage; 
   		  	}
            $scope.failureTextAlert = msg;
            $scope.showFailureAlert = true;
            $timeout(function () {
                    $scope.showFailureAlert= false;
            }, 3000);
        });
		$scope.OsiGrades.gradeId = $scope.OsiGrades.gradeId;$scope.OsiGrades.gradeName = $scope.OsiGrades.gradeName;$scope.OsiGrades.gradeDescription = $scope.OsiGrades.gradeDescription;$scope.OsiGrades.seq = $scope.OsiGrades.seq;$scope.OsiGrades.costPerHour = $scope.OsiGrades.costPerHour;$scope.OsiGrades.costPerMonth = $scope.OsiGrades.costPerMonth;$scope.OsiGrades.revPerHour = $scope.OsiGrades.revPerHour;$scope.OsiGrades.revPerMonth = $scope.OsiGrades.revPerMonth;$scope.OsiGrades.active = $scope.OsiGrades.active;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiGrades-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiGrades() {
        $scope.headername = "Create Grade";
        $rootScope.isTrascError = false;
        $scope.iseditable = true; 
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiGrades.gradeId = '';$scope.OsiGrades.gradeName = '';$scope.OsiGrades.gradeDescription = '';$scope.OsiGrades.seq = '';$scope.OsiGrades.costPerHour = '';$scope.OsiGrades.costPerMonth = '';$scope.OsiGrades.revPerHour = '';$scope.OsiGrades.revPerMonth = '';$scope.OsiGrades.active = 0;$scope.OsiGrades.orgId = '';$scope.OsiGrades.titleId = '';
       $('#OsiGrades-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiGradess = function () {
		$scope.searchData = {gradeName:$scope.gradeName}; var searchData = JSON.stringify($scope.searchData);
		OsiGradesService.searchOsiGradess(searchData).then(function (data) {
                $scope.gradeList = data;
                angular.forEach($scope.gradeList, function(grade, k){
                    angular.forEach($scope.organizations, function(org, key) {
                        if(org.orgId == grade.orgId) {
                            $scope.gradeList[k].orgName = org.orgName;
                        }
                    });
                })       
            }).catch(function(error){
            	var msg=appConstants.exceptionMessage;
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

    $scope.clearSearch = function () {
        $scope.gradeName='';
        $scope.clearSelectedRow();
        $scope.init();
    };
    
    function validateDublicateGradeEntry(OsiGrades){
    	if($scope.headername=='Edit Grade' && $scope.editGradeName!=null  && $scope.editGradeName.toUpperCase()== OsiGrades.gradeName.toUpperCase() && $scope.editSeq==OsiGrades.seq){
    		//Do Nothing
    	}
    	if($scope.headername=='Edit Grade' && $scope.editGradeName!=null && $scope.editGradeName.toUpperCase()!= OsiGrades.gradeName.toUpperCase()){
    		for(var i=0;i<$scope.gradeList.length;i++){
        		if(OsiGrades.orgId==$scope.gradeList[i].orgId && OsiGrades.gradeName.toUpperCase()==$scope.gradeList[i].gradeName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Grade name already exists for this organization.'); 
        			 $scope.continuesave = false;
    				 break;
    			}
    		}
    	}
    	if($scope.headername=='Edit Grade' && $scope.editSeq!=OsiGrades.seq){
    		for(var i=0;i<$scope.gradeList.length;i++){
        		if(OsiGrades.orgId==$scope.gradeList[i].orgId && OsiGrades.seq==$scope.gradeList[i].seq){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Sequence already exists for this organization.'); 
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}
    	if(!($scope.headername=='Edit Grade')){
    		for(var i=0;i<$scope.gradeList.length;i++){
        		if(OsiGrades.orgId==$scope.gradeList[i].orgId && OsiGrades.gradeName.toUpperCase()==$scope.gradeList[i].gradeName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Grade name already exists for this organization.'); 
        			 $scope.continuesave = false;
    				 break;
    			}else if(OsiGrades.orgId==$scope.gradeList[i].orgId && OsiGrades.seq==$scope.gradeList[i].seq){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Sequence already exists for this organization.'); 
    				$scope.continuesave = false;
    				break;
    			}
    		}    		
    	}
    }
    
    $scope.saveOsiGrades = function (OsiGrades) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiGrades.gradeName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Grade'); 
 $scope.continuesave = false;}else if(!$scope.OsiGrades.seq){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Sequence'); 
 $scope.continuesave = false;}else if(!$scope.OsiGrades.costPerHour){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Cost Per Hour'); 
 $scope.continuesave = false;}else if(!$scope.OsiGrades.costPerMonth){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Cost Per Month'); 
 $scope.continuesave = false;}else if(!$scope.OsiGrades.costPerHour){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Revenue Per Hour'); 
 $scope.continuesave = false;}else if(!$scope.OsiGrades.costPerMonth){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Revenue Per Month'); 
 $scope.continuesave = false;}else if(!$scope.OsiGrades.orgId){ 
	 $rootScope.isTrascError = true;
	 FlashService.Error('Please Select Organization'); 
	 $scope.continuesave = false;}else if(!$scope.OsiGrades.titleId){ 
		 $rootScope.isTrascError = true;
		 FlashService.Error('Please Select Title'); 
		 $scope.continuesave = false;}else{
			 validateDublicateGradeEntry(OsiGrades);
		 }/*else if(!$scope.OsiGrades.active){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Active'); 
 $scope.continuesave = false;}*/

        if ($scope.continuesave) {
            if (!$scope.OsiGrades.gradeId) {
            	
            	$scope.OsiGrades = {
                    	"id":$scope.OsiGrades.gradeId,"gradeName":$scope.OsiGrades.gradeName,"gradeDescription":$scope.OsiGrades.gradeDescription,"seq":$scope.OsiGrades.seq,"costPerHour":$scope.OsiGrades.costPerHour,"costPerMonth":$scope.OsiGrades.costPerMonth,"revPerHour":$scope.OsiGrades.revPerHour,"revPerMonth":$scope.OsiGrades.revPerMonth,"active":$scope.OsiGrades.active,"orgId":$scope.OsiGrades.orgId,"titleId":$scope.OsiGrades.titleId	
                    };
                OsiGradesService
                        .saveOsiGrades($scope.OsiGrades)
                        .then(
                                function (result) {
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiGrades-model').modal('hide');
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
                OsiGradesService
                        .updateOsiGrades($scope.OsiGrades)
                        .then(
                                function (result) {
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiGrades-model').modal('hide');
                                        $scope.init();
                                        $scope.successTextAlert =  appConstants.successMessage;
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
    
	
	
    $scope.init();
}
