'use strict';
angular.module('myApp.OsiCertificationTagsController', []).controller('OsiCertificationTagsController',
        OsiCertificationTagsController);
OsiCertificationTagsController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiCertificationTagsService', 'FlashService', '$timeout','appConstants'];
function OsiCertificationTagsController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiCertificationTagsService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.certificationTagList = "";
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
	$scope.duplicateCertificationEntry=false;
    $scope.certificationTagList = [];
	$scope.OsiCertificationTags = [];
    $scope.sort = function (keyname) {
        if ($scope.certificationTagList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiCertificationTags){
     editOsiCertificationTagsDetails(OsiCertificationTags);
     $scope.isSelectedRow = OsiCertificationTags.tagId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiCertificationTagsService.searchOsiCertificationTagss(searchData).then(function (data) {
            $scope.certificationTagList = data;
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
            createOsiCertificationTags();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiCertificationTagsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiCertificationTagsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiCertificationTagsDetails(OsiCertificationTags) {
	        $scope.headername = "View Certification";
	        $rootScope.isTrascError = false;
	        OsiCertificationTagsService.getOsiCertificationTags(OsiCertificationTags.tagId).then(function (data) {
	            $scope.OsiCertificationTags = data;
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
			$scope.OsiCertificationTags.tagId = $scope.OsiCertificationTags.tagId;$scope.OsiCertificationTags.tagName = $scope.OsiCertificationTags.tagName;$scope.OsiCertificationTags.description = $scope.OsiCertificationTags.description;$scope.OsiCertificationTags.isActive = $scope.OsiCertificationTags.isActive;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiCertificationTags-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiCertificationTagsDetails(OsiCertificationTags) {
        $scope.headername = "Edit Certification";
        $rootScope.isTrascError = false;
        OsiCertificationTagsService.getOsiCertificationTags(OsiCertificationTags.tagId).then(function (data) {
            $scope.OsiCertificationTags = data;
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
		$scope.OsiCertificationTags.tagId = $scope.OsiCertificationTags.tagId;$scope.OsiCertificationTags.tagName = $scope.OsiCertificationTags.tagName;$scope.OsiCertificationTags.description = $scope.OsiCertificationTags.description;$scope.OsiCertificationTags.isActive = $scope.OsiCertificationTags.isActive;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiCertificationTags-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiCertificationTags() {
        $scope.headername = "Create Certification Tags";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiCertificationTags.tagId = '';$scope.OsiCertificationTags.tagName = '';$scope.OsiCertificationTags.description = '';$scope.OsiCertificationTags.isActive = 0;
       $('#OsiCertificationTags-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiCertificationTags = function () {
		$scope.searchData = {tagName:$scope.certificationTagName}; var searchData = JSON.stringify($scope.searchData);
		OsiCertificationTagsService.searchOsiCertificationTags(searchData).then(function (data) {
                $scope.certificationTagList = data;
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
        $scope.certificationTagName='';
        $scope.certificationTagList = '';
        $scope.clearSelectedRow();
        $scope.init();
    };

	  function validateDuplicateCertificationEntry(OsiCertificationTags){
	    	if($scope.headername=='Edit Certification' && $scope.editTagName!=null && $scope.editTagName.toUpperCase()== OsiCertificationTags.tagName.toUpperCase() ){
	    		//Do Nothing
	    	}
	    	if($scope.headername =='Create Certification' && $scope.editTagName!=null && $scope.editTagName.toUpperCase()== OsiCertificationTags.tagName.toUpperCase()){
	    		for(var i=0;i<$scope.certificationTagList.length;i++){
	        		if(OsiCertificationTags.description==$scope.certificationTagList[i].description && OsiCertificationTags.tagName.toUpperCase()==$scope.certificationTagList[i].tagName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Certification Tag Name already exists');
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}
	    	if($scope.headername =='Edit Certification' && $scope.editTagName!=null &&  $scope.editTagName.toUpperCase()!= OsiCertificationTags.tagName.toUpperCase()){
	    		for(var i=0;i<$scope.certificationTagList.length;i++){
	        		if(OsiCertificationTags.description==$scope.certificationTagList[i].description && OsiCertificationTags.tagName.toUpperCase()==$scope.certificationTagList[i].tagName.toUpperCase()){
	        			 $rootScope.isTrascError = true;
	        			 FlashService.Error('Certification Tag Name already exists');
	        			 $scope.continuesave = false;
	    				 break;
	    			}
	    		}
	    	}

	    }
    $scope.saveOsiCertificationTags = function (OsiCertificationTags) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiCertificationTags.tagName){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Certification Tag Name');
 $scope.continuesave = false;
}
 /*
 else if(!$scope.OsiCertificationTags.description){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Description');
 $scope.continuesave = false;}else if(!$scope.OsiCertificationTags.description){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Certification Version');
 $scope.continuesave = false;}else if(!$scope.OsiCertificationTags.active){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Active');
 $scope.continuesave = false;}*/
 else{validateDuplicateCertificationEntry(OsiCertificationTags);}
        if ($scope.continuesave) {
            if (!$scope.OsiCertificationTags.tagId) {

            	$scope.OsiCertificationTags = {
                    	"tagId":$scope.OsiCertificationTags.tagId,"tagName":$scope.OsiCertificationTags.tagName,"description":$scope.OsiCertificationTags.description,"isActive":$scope.OsiCertificationTags.isActive
                    };
                OsiCertificationTagsService
                        .saveOsiCertificationTags($scope.OsiCertificationTags)
                        .then(
                                function (result) {
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiCertificationTags-model').modal('hide');
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
                                        $scope.searchOsiCertificationTags();
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
                OsiCertificationTagsService
                        .updateOsiCertificationTags($scope.OsiCertificationTags)
                        .then(
                                function (result) {
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiCertificationTags-model').modal('hide');
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
                                        $scope.searchOsiCertificationTags();
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
