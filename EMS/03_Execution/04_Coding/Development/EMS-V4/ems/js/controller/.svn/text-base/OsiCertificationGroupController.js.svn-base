'use strict';
angular.module('myApp.OsiCertificationGroupController', []).controller('OsiCertificationGroupController',
        OsiCertificationGroupController);
OsiCertificationGroupController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiCertificationGroupsService', 'FlashService', '$timeout','appConstants'];
function OsiCertificationGroupController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiCertificationGroupService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.certificationGroupList = "";
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
    $scope.editGroupName=null;
    $scope.editCertificationVersion=null;
	$scope.duplicateCertificationEntry=false;
    $scope.certificationGroupList = [];
	$scope.OsiCertificationGroup = [];
    $scope.sort = function (keyname) {
        if ($scope.certificationGroupList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiCertificationGroup){
     editOsiCertificationGroupDetails(OsiCertificationGroup);
     $scope.isSelectedRow = OsiCertificationGroup.groupId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiCertificationGroupService.searchOsiCertificationGroups(searchData).then(function (data) {
            $scope.certificationGroupList = data;
            $scope.sort('groupName');
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
        $scope.isSelectedRow = item.groupId;
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
            createOsiCertificationGroup();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiCertificationGroupDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiCertificationGroupDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiCertificationGroupDetails(OsiCertificationGroup) {
	        $scope.headername = "View Certification Group";
	        $rootScope.isTrascError = false;
	        OsiCertificationGroupService.getOsiCertificationGroups(OsiCertificationGroup.groupId).then(function (data) {
	            $scope.OsiCertificationGroup = data;
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
			$scope.OsiCertificationGroup.groupId = $scope.OsiCertificationGroup.groupId;$scope.OsiCertificationGroup.groupName = $scope.OsiCertificationGroup.groupName;$scope.OsiCertificationGroup.certificationDisplayName = $scope.OsiCertificationGroup.certificationDisplayName;$scope.OsiCertificationGroup.certificationDescription = $scope.OsiCertificationGroup.certificationDescription;$scope.OsiCertificationGroup.certificationVersion = $scope.OsiCertificationGroup.certificationVersion;$scope.OsiCertificationGroup.active = $scope.OsiCertificationGroup.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiCertificationGroup-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiCertificationGroupDetails(OsiCertificationGroup) {
        $scope.headername = "Edit Certification Group";
        $rootScope.isTrascError = false;
        OsiCertificationGroupService.getOsiCertificationGroups(OsiCertificationGroup.groupId).then(function (data) {
            $scope.OsiCertificationGroup = data;
            $scope.editGroupName=data.groupName;
            $scope.editGroupDescription=data.groupDescription;

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
		$scope.OsiCertificationGroup.groupId = $scope.OsiCertificationGroup.groupId;$scope.OsiCertificationGroup.groupName = $scope.OsiCertificationGroup.groupName;$scope.OsiCertificationGroup.isActive = $scope.OsiCertificationGroup.isActive;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiCertificationGroup-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiCertificationGroup() {
        $scope.headername = "Create Certification Group";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiCertificationGroup.groupId = '';$scope.OsiCertificationGroup.groupName = '';$scope.OsiCertificationGroup.groupDescription = '';$scope.OsiCertificationGroup.isActive = 0;
       $('#OsiCertificationGroup-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiCertificationGroups = function () {
		$scope.searchData = {groupName:$scope.certificationGroupName}; var searchData = JSON.stringify($scope.searchData);
		OsiCertificationGroupService.searchOsiCertificationGroups(searchData).then(function (data) {
                $scope.certificationGroupList = data;
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
        $scope.certificationGroupName='';
        $scope.certificationGroupList = '';
        $scope.clearSelectedRow();
        $scope.init();
    };

	  function validateDuplicateCertificationEntry(OsiCertificationGroup){
	    	if($scope.headername=='Edit Certification Group' && $scope.editGroupName!=null && $scope.editGroupName.toUpperCase()== OsiCertificationGroup.groupName.toUpperCase() ){
	    		//Do Nothing
	    	}
	    	if($scope.headername =='Create Certification Group' && $scope.editGroupName!=null && $scope.editGroupName.toUpperCase()== OsiCertificationGroup.groupName.toUpperCase()){
	    		for(var i=0;i<$scope.certificationGroupList.length;i++){
	        		if(OsiCertificationGroup.groupDescription==$scope.certificationGroupList[i].groupDescription && OsiCertificationGroup.groupName.toUpperCase()==$scope.certificationGroupList[i].groupName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Certification Group name already exists.');
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}
	    	if($scope.headername =='Edit Certification Group' && $scope.editGroupName!=null &&  $scope.editGroupName.toUpperCase()!= OsiCertificationGroup.groupName.toUpperCase()){
	    		for(var i=0;i<$scope.certificationGroupList.length;i++){
	        		if(OsiCertificationGroup.groupDescription==$scope.certificationGroupList[i].groupDescription && OsiCertificationGroup.groupName.toUpperCase()==$scope.certificationGroupList[i].groupName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Certification Group name already exists.');
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}

	    }
    $scope.saveOsiCertificationGroups = function (OsiCertificationGroup) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiCertificationGroup.groupName){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Certification Group Name');
 $scope.continuesave = false;
}
/* else if(!$scope.OsiCertificationGroup.groupDescription){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Certification Group Description');
 $scope.continuesave = false;}else if(!$scope.OsiCertificationGroup.certificationVersion){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Certification Version');
 $scope.continuesave = false;}else if(!$scope.OsiCertificationGroup.active){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Active');
 $scope.continuesave = false;}*/
 else{validateDuplicateCertificationEntry(OsiCertificationGroup);}
        if ($scope.continuesave) {
            if (!$scope.OsiCertificationGroup.groupId) {

            	$scope.OsiCertificationGroup = {
                    	"groupId":$scope.OsiCertificationGroup.groupId,"groupName":$scope.OsiCertificationGroup.groupName,"groupDescription":$scope.OsiCertificationGroup.groupDescription,"isActive":$scope.OsiCertificationGroup.isActive
                    };
                OsiCertificationGroupService
                        .saveOsiCertificationGroups($scope.OsiCertificationGroup)
                        .then(
                                function (result) {
                                  console.log(result);
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiCertificationGroup-model').modal('hide');
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
                                        //$window.location.reload();
                                        $scope.searchOsiCertificationGroups();
                                    }, 1000);
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
                OsiCertificationGroupService
                        .updateOsiCertificationGroups($scope.OsiCertificationGroup)
                        .then(
                                function (result) {
                                  console.log(result);
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiCertificationGroup-model').modal('hide');
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
                                        //$window.location.reload();
                                        $scope.searchOsiCertificationGroups();
                                    }, 1000);
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
