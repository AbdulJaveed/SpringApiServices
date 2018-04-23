'use strict';
angular.module('myApp.OsiSkillGroupController', []).controller('OsiSkillGroupController',
        OsiSkillGroupController);
OsiSkillGroupController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiSkillGroupsService', 'FlashService', '$timeout','appConstants'];
function OsiSkillGroupController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiSkillGroupService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.skillGroupList = "";
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
    $scope.editSkillVersion=null;
	$scope.duplicateSkillEntry=false;
    $scope.skillGroupList = [];
	$scope.OsiSkillGroup = [];
    $scope.sort = function (keyname) {
        if ($scope.skillGroupList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiSkillGroup){
     editOsiSkillGroupDetails(OsiSkillGroup);
     $scope.isSelectedRow = OsiSkillGroup.groupId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiSkillGroupService.searchOsiSkillGroups(searchData).then(function (data) {
            $scope.skillGroupList = data;
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
            createOsiSkillGroup();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiSkillGroupDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiSkillGroupDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiSkillGroupDetails(OsiSkillGroup) {
	        $scope.headername = "View Skill Group";
	        $rootScope.isTrascError = false;
	        OsiSkillGroupService.getOsiSkillGroups(OsiSkillGroup.groupId).then(function (data) {
	            $scope.OsiSkillGroup = data;
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
			$scope.OsiSkillGroup.groupId = $scope.OsiSkillGroup.groupId;$scope.OsiSkillGroup.groupName = $scope.OsiSkillGroup.groupName;$scope.OsiSkillGroup.skillDisplayName = $scope.OsiSkillGroup.skillDisplayName;$scope.OsiSkillGroup.skillDescription = $scope.OsiSkillGroup.skillDescription;$scope.OsiSkillGroup.skillVersion = $scope.OsiSkillGroup.skillVersion;$scope.OsiSkillGroup.active = $scope.OsiSkillGroup.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiSkillGroup-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiSkillGroupDetails(OsiSkillGroup) {
        $scope.headername = "Edit Skill Group";
        $rootScope.isTrascError = false;
        OsiSkillGroupService.getOsiSkillGroups(OsiSkillGroup.groupId).then(function (data) {
            $scope.OsiSkillGroup = data;
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
		$scope.OsiSkillGroup.groupId = $scope.OsiSkillGroup.groupId;$scope.OsiSkillGroup.groupName = $scope.OsiSkillGroup.groupName;$scope.OsiSkillGroup.isActive = $scope.OsiSkillGroup.isActive;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiSkillGroup-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiSkillGroup() {
        $scope.headername = "Create Skill Group";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiSkillGroup.groupId = '';$scope.OsiSkillGroup.groupName = '';$scope.OsiSkillGroup.groupDescription = '';$scope.OsiSkillGroup.isActive = 0;
       $('#OsiSkillGroup-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiSkillGroups = function () {
		$scope.searchData = {groupName:$scope.skillGroupName}; var searchData = JSON.stringify($scope.searchData);
		OsiSkillGroupService.searchOsiSkillGroups(searchData).then(function (data) {
                $scope.skillGroupList = data;
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
        $scope.skillGroupName='';
        $scope.skillGroupList = '';
        $scope.clearSelectedRow();
        $scope.init();
    };

	  function validateDuplicateSkillEntry(OsiSkillGroup){
	    	if($scope.headername=='Edit Skill Group' && $scope.editGroupName!=null && $scope.editGroupName.toUpperCase()== OsiSkillGroup.groupName.toUpperCase() ){
	    		//Do Nothing
	    	}
	    	if($scope.headername =='Create Skill Group' && $scope.editGroupName!=null && $scope.editGroupName.toUpperCase()== OsiSkillGroup.groupName.toUpperCase()){
	    		for(var i=0;i<$scope.skillGroupList.length;i++){
	        		if(OsiSkillGroup.groupDescription==$scope.skillGroupList[i].groupDescription && OsiSkillGroup.groupName.toUpperCase()==$scope.skillGroupList[i].groupName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Skill Group name already exists for this version.');
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}
	    	if($scope.headername =='Edit Skill Group' && $scope.editGroupName!=null &&  $scope.editGroupName.toUpperCase()!= OsiSkillGroup.groupName.toUpperCase()){
	    		for(var i=0;i<$scope.skillGroupList.length;i++){
	        		if(OsiSkillGroup.groupDescription==$scope.skillGroupList[i].groupDescription && OsiSkillGroup.groupName.toUpperCase()==$scope.skillGroupList[i].groupName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Skill Group name already exists for this version.');
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}

	    }
    $scope.saveOsiSkillGroups = function (OsiSkillGroup) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiSkillGroup.groupName){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Skill Group Name');
 $scope.continuesave = false;
} 
 else{validateDuplicateSkillEntry(OsiSkillGroup);}
        if ($scope.continuesave) {

            $scope.OsiSkillGroup = {
                "groupId":$scope.OsiSkillGroup.groupId,"groupName":$scope.OsiSkillGroup.groupName,"groupDescription":$scope.OsiSkillGroup.groupDescription,"isActive":$scope.OsiSkillGroup.isActive
            };
            if (!$scope.OsiSkillGroup.groupId) {

                OsiSkillGroupService
                        .saveOsiSkillGroups($scope.OsiSkillGroup)
                        .then(
                                function (result) {
                                  console.log(result);
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiSkillGroup-model').modal('hide');
                                        //$scope.init();
                                        $scope.successTextAlert = appConstants.successMessage;
                                        $scope.showSuccessAlert = true;
                                        //$scope.searchOsiSkillGroups();
                                    } else {
                                        $rootScope.isTrascError = true;
                                        FlashService.Error(result.response);
                                        $scope.continuesave = false;
                                    }
                                    $timeout(function () {
                                        $scope.showSuccessAlert = false;
                                        //$window.location.reload();
                                        $scope.searchOsiSkillGroups();
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
                OsiSkillGroupService
                        .updateOsiSkillGroups($scope.OsiSkillGroup)
                        .then(
                                function (result) {
                                  console.log(result);
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiSkillGroup-model').modal('hide');
                                        //$scope.init();
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
                                        $scope.searchOsiSkillGroups();
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
                                    }, 1000);

                                });
            }
    }
};



    $scope.init();
}
