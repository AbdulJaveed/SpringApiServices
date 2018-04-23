'use strict';
angular.module('myApp.OsiSkillTagsController', []).controller('OsiSkillTagsController',
        OsiSkillTagsController);
OsiSkillTagsController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiSkillTagsService', 'FlashService', '$timeout','appConstants'];
function OsiSkillTagsController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiSkillTagsService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.skillTagList = "";
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
    $scope.editTagName=null;
    $scope.editDescription=null;
	$scope.duplicateSkillEntry=false;
    $scope.skillTagList = [];
	$scope.OsiSkillTags = [];
    $scope.sort = function (keyname) {
        if ($scope.skillTagList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiSkillTags){
     editOsiSkillTagsDetails(OsiSkillTags);
     $scope.isSelectedRow = OsiSkillTags.tagId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiSkillTagsService.searchOsiSkillTagss(searchData).then(function (data) {
            $scope.skillTagList = data;
            $scope.sort('tagName');
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
        $scope.isSelectedRow = item.tagId;
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
            createOsiSkillTags();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiSkillTagsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiSkillTagsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiSkillTagsDetails(OsiSkillTags) {
	        $scope.headername = "View Skill Tags";
	        $rootScope.isTrascError = false;
	        OsiSkillTagsService.getOsiSkillTags(OsiSkillTags.tagId).then(function (data) {
	            $scope.OsiSkillTags = data;
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
			$scope.OsiSkillTags.tagId = $scope.OsiSkillTags.tagId;$scope.OsiSkillTags.tagName = $scope.OsiSkillTags.tagName;$scope.OsiSkillTags.description = $scope.OsiSkillTags.description;$scope.OsiSkillTags.isActive = $scope.OsiSkillTags.isActive;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiSkillTags-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiSkillTagsDetails(OsiSkillTags) {
        $scope.headername = "Edit Skill Tags";
        $rootScope.isTrascError = false;
        OsiSkillTagsService.getOsiSkillTags(OsiSkillTags.tagId).then(function (data) {
            $scope.OsiSkillTags = data;
            $scope.editTagName=data.tagName;
            $scope.editDescription=data.description;

        }).catch(function(error){
            /*var errmsg=appConstants.exceptionMessage;
            if(error.errorMessage!=undefined){
                errmsg=error.errorMessage;
            }
            $scope.failureTextAlert = errmsg;*/
        	var msg = appConstants.exceptionMessage;
          console.log(error);
	   		  if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  }
          $scope.failureTextAlert = msg;
            $scope.showFailureAlert = true;
            $timeout(function () {
                    $scope.showFailureAlert= false;
            }, 3000);
        });
		$scope.OsiSkillTags.tagId = $scope.OsiSkillTags.tagId;$scope.OsiSkillTags.tagName = $scope.OsiSkillTags.tagName;$scope.OsiSkillTags.description = $scope.OsiSkillTags.description;$scope.OsiSkillTags.isActive = $scope.OsiSkillTags.isActive;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiSkillTags-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiSkillTags() {
        $scope.headername = "Create Skill Tags";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiSkillTags.tagId = '';$scope.OsiSkillTags.tagName = '';$scope.OsiSkillTags.description = '';$scope.OsiSkillTags.isActive = 0;
       $('#OsiSkillTags-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiSkillTags = function () {
		$scope.searchData = {tagName:$scope.skillTagName}; var searchData = JSON.stringify($scope.searchData);
		OsiSkillTagsService.searchOsiSkillTags(searchData).then(function (data) {
                $scope.skillTagList = data;
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
        $scope.skillTagName='';
        $scope.skillTagList = '';
        $scope.clearSelectedRow();
        $scope.init();
    };

	  function validateDuplicateSkillEntry(OsiSkillTags){
	    	if($scope.headername=='Edit Skill' && $scope.editTagName!=null && $scope.editTagName.toUpperCase()== OsiSkillTags.tagName.toUpperCase() ){
	    		//Do Nothing
	    	}
	    	if($scope.headername =='Create Skill' && $scope.editTagName!=null && $scope.editTagName.toUpperCase()== OsiSkillTags.tagName.toUpperCase()){
	    		for(var i=0;i<$scope.skillTagList.length;i++){
	        		if(OsiSkillTags.description==$scope.skillTagList[i].description && OsiSkillTags.tagName.toUpperCase()==$scope.skillTagList[i].tagName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Skill name already exists for this version.');
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}
	    	if($scope.headername =='Edit Skill' && $scope.editTagName!=null &&  $scope.editTagName.toUpperCase()!= OsiSkillTags.tagName.toUpperCase()){
	    		for(var i=0;i<$scope.skillTagList.length;i++){
	        		if(OsiSkillTags.description==$scope.skillTagList[i].description && OsiSkillTags.tagName.toUpperCase()==$scope.skillTagList[i].tagName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Skill name already exists for this version.');
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}

	    }
    $scope.saveOsiSkillTags = function (OsiSkillTags) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiSkillTags.tagName){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Skill Tag Name');
 $scope.continuesave = false;}
 else{validateDuplicateSkillEntry(OsiSkillTags);}
        if ($scope.continuesave) {
            $scope.OsiSkillTags = {
                "tagId":$scope.OsiSkillTags.tagId,"tagName":$scope.OsiSkillTags.tagName,"description":$scope.OsiSkillTags.description,"isActive":$scope.OsiSkillTags.isActive
            };
            if (!$scope.OsiSkillTags.tagId) {
                OsiSkillTagsService
                        .saveOsiSkillTags($scope.OsiSkillTags)
                        .then(
                                function (result) {
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiSkillTags-model').modal('hide');
                                       // $scope.init();
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
                                        $scope.searchOsiSkillTags();
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
                                    }, 1000);

                                });
            } else {
                OsiSkillTagsService
                        .updateOsiSkillTags($scope.OsiSkillTags)
                        .then(
                                function (result) {
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiSkillTags-model').modal('hide');
                                    
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
                                        $scope.searchOsiSkillTags();
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
