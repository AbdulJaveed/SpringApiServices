'use strict';
angular.module('myApp.OsiSkilsController', []).controller('OsiSkilsController',
        OsiSkilsController);
OsiSkilsController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiSkilsService', 'FlashService', '$timeout','appConstants'];
function OsiSkilsController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiSkilsService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.skillList = "";
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
    $scope.editSkillName=null;
    $scope.editSkillVersion=null;
	$scope.duplicateSkillEntry=false;
    $scope.skillList = [];
	$scope.OsiSkils = [];  
    $scope.sort = function (keyname) {
        if ($scope.skillList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiSkils){
     editOsiSkilsDetails(OsiSkils);
     $scope.isSelectedRow = OsiSkils.skillId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiSkilsService.searchOsiSkilss(searchData).then(function (data) {
            $scope.skillList = data;
            $scope.sort('skillName');
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
    };

	$scope.selectRow = function (item) {
        // for checking single row selection
        $scope.isSelectedRow = item.skillId;
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
            createOsiSkils();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiSkilsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiSkilsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }
	
	 function viewOsiSkilsDetails(OsiSkils) {
	        $scope.headername = "View Skill";
	        $rootScope.isTrascError = false;
	        OsiSkilsService.getOsiSkils(OsiSkils.skillId).then(function (data) {
	            $scope.OsiSkils = data;
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
			$scope.OsiSkils.skillId = $scope.OsiSkils.skillId;$scope.OsiSkils.skillName = $scope.OsiSkils.skillName;$scope.OsiSkils.skillDisplayName = $scope.OsiSkils.skillDisplayName;$scope.OsiSkils.skillDescription = $scope.OsiSkils.skillDescription;$scope.OsiSkils.skillVersion = $scope.OsiSkils.skillVersion;$scope.OsiSkils.active = $scope.OsiSkils.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiSkils-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiSkilsDetails(OsiSkils) {
        $scope.headername = "Edit Skill";
        $rootScope.isTrascError = false;
        OsiSkilsService.getOsiSkils(OsiSkils.skillId).then(function (data) {
            $scope.OsiSkils = data;
            $scope.editSkillName=data.skillName;
            $scope.editSkillVersion=data.skillVersion;
            $scope.OsiSkils.skillVersion=$scope.OsiSkils.skillVersion.toFixed(2);
        }).catch(function(error){
            /*var errmsg=appConstants.exceptionMessage;
            if(error.errorMessage!=undefined){
                errmsg=error.errorMessage;
            }
            $scope.failureTextAlert = errmsg;*/
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
		$scope.OsiSkils.skillId = $scope.OsiSkils.skillId;$scope.OsiSkils.skillName = $scope.OsiSkils.skillName;$scope.OsiSkils.skillDisplayName = $scope.OsiSkils.skillDisplayName;$scope.OsiSkils.skillDescription = $scope.OsiSkils.skillDescription;$scope.OsiSkils.skillVersion = $scope.OsiSkils.skillVersion;$scope.OsiSkils.active = $scope.OsiSkils.active;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiSkils-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiSkils() {
        $scope.headername = "Create Skill";
        $rootScope.isTrascError = false;
        $scope.iseditable = true; 
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiSkils.skillId = '';$scope.OsiSkils.skillName = '';$scope.OsiSkils.skillDisplayName = '';$scope.OsiSkils.skillDescription = '';$scope.OsiSkils.skillVersion = '';$scope.OsiSkils.active = 0;
       $('#OsiSkils-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiSkilss = function () {
		$scope.searchData = {skillName:$scope.skillName}; var searchData = JSON.stringify($scope.searchData);
		OsiSkilsService.searchOsiSkilss(searchData).then(function (data) {
                $scope.skillList = data;
            }).catch(function(error){
               /* var errmsg=appConstants.exceptionMessage;
                if(error.errorMessage!=undefined){
                    errmsg=error.errorMessage;
                }
                $scope.failureTextAlert = errmsg;*/
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

    $scope.clearSearch = function () {
        $scope.skillName='';
        $scope.clearSelectedRow();
        $scope.init();
    };
    
	  function validateDuplicateSkillEntry(OsiSkils){
	    	if($scope.headername=='Edit Skill' && $scope.editSkillName!=null && $scope.editSkillName.toUpperCase()== OsiSkils.skillName.toUpperCase() ){
	    		//Do Nothing
	    	}
	    	if($scope.headername =='Create Skill' && $scope.editSkillName!=null && $scope.editSkillName.toUpperCase()== OsiSkils.skillName.toUpperCase()){
	    		for(var i=0;i<$scope.skillList.length;i++){
	        		if(OsiSkils.skillVersion==$scope.skillList[i].skillVersion && OsiSkils.skillName.toUpperCase()==$scope.skillList[i].skillName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Skill name already exists for this version.'); 
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}
	    	if($scope.headername =='Edit Skill' && $scope.editSkillName!=null &&  $scope.editSkillName.toUpperCase()!= OsiSkils.skillName.toUpperCase()){
	    		for(var i=0;i<$scope.skillList.length;i++){
	        		if(OsiSkils.skillVersion==$scope.skillList[i].skillVersion && OsiSkils.skillName.toUpperCase()==$scope.skillList[i].skillName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Skill name already exists for this version.'); 
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}

	    }
    $scope.saveOsiSkils = function (OsiSkils) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiSkils.skillName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Skill'); 
 $scope.continuesave = false;}else if(!$scope.OsiSkils.skillDisplayName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Skill Display Name'); 
 $scope.continuesave = false;}/*else if(!$scope.OsiSkils.skillVersion){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Skill Version'); 
 $scope.continuesave = false;}else if(!$scope.OsiSkils.active){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Active'); 
 $scope.continuesave = false;}*/
 else{validateDuplicateSkillEntry(OsiSkils);}     
        if ($scope.continuesave) {
            if (!$scope.OsiSkils.skillId) {
            	
            	$scope.OsiSkils = {
                    	"skillId":$scope.OsiSkils.skillId,"skillName":$scope.OsiSkils.skillName,"skillDisplayName":$scope.OsiSkils.skillDisplayName,"skillDescription":$scope.OsiSkils.skillDescription,"skillVersion":$scope.OsiSkils.skillVersion,"active":$scope.OsiSkils.active	
                    };
                OsiSkilsService
                        .saveOsiSkils($scope.OsiSkils)
                        .then(
                                function (result) {
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiSkils-model').modal('hide');
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
                                   /* var errmsg=appConstants.exceptionMessage;
                                    if(error.errorMessage!=undefined){
                                        errmsg=error.errorMessage;
                                    }*/
                                	var msg = appConstants.exceptionMessage;
                      	   		  if(error.data.httpStatus){ 
                      	   			  msg=error.data.errorMessage; 
                      	   		  }
                                    $rootScope.isTrascError = true;
                                    FlashService.Error(msg);
                                    $timeout(function () {
                                            $rootScope.isTrascError= false;
                                    }, 3000);
                                    
                                });
            } else {
                OsiSkilsService
                        .updateOsiSkils($scope.OsiSkils)
                        .then(
                                function (result) {
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiSkils-model').modal('hide');
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
                                	var msg = appConstants.exceptionMessage;
                        	   		  if(error.data.httpStatus){ 
                        	   			  msg=error.data.errorMessage; 
                        	   		 }
                                    $rootScope.isTrascError = true;
                                    FlashService.Error(msg);
                                    $timeout(function () {
                                            $rootScope.isTrascError= false;
                                    }, 3000);
                                    
                                });
            }
    }
};
    
	
	
    $scope.init();
}
