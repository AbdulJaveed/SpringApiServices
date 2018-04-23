'use strict';
angular.module('myApp.OsiCertificationsController', []).controller('OsiCertificationsController',
        OsiCertificationsController);
OsiCertificationsController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiCertificationsService','OsiCertificationGroupsService', 'OsiCertificationTagsService', 'FlashService', '$timeout','appConstants'];
function OsiCertificationsController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiCertificationsService, OsiCertificationGroupsService, OsiCertificationTagsService,  FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.certificationList = "";
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
    $scope.editCertificationName=null;
    $scope.editCertificationCode=null;
	$scope.duplicateCertification=false;
  $scope.OsiCertificationTags = [];
  $scope.OsiCertificationGroups = [];
    $scope.certificationList = [];
	$scope.OsiCertifications = [];
    $scope.sort = function (keyname) {
        if ($scope.certificationList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiCertifications){
     editOsiCertificationsDetails(OsiCertifications);
     $scope.isSelectedRow = OsiCertifications.certificationId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiCertificationsService.searchOsiCertificationss(searchData).then(function (data) {
            $scope.certificationList = data;
            $scope.sort('certificationName');
            $scope.reverse=false;
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
        */
    };

	$scope.selectRow = function (item) {
        // for checking single row selection
        $scope.isSelectedRow = item.certificationId;
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
            createOsiCertifications();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiCertificationsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiCertificationsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiCertificationsDetails(OsiCertifications) {
	        $scope.headername = "View Certification";
	        $rootScope.isTrascError = false;
	        OsiCertificationsService.getOsiCertifications(OsiCertifications.certificationId).then(function (data) {
	            $scope.OsiCertifications = data;
              OsiCertificationGroupsService.getAllActiveOsiCertificationGroups().then(function(data) {
                $scope.OsiCertificationGroups = data;
                $scope.OsiCertificationGroups.groupId = $scope.OsiCertifications.osiCertificationGroupId;
              });
              OsiCertificationTagsService.getAllActiveOsiCertificationTags().then(function(data){
                $scope.OsiCertificationTags = data;
                $scope.OsiCertificationTags.tagId = [];
                $scope.OsiCertificationTags.forEach(function(tag){
                  $scope.OsiCertifications.osiCertificationTags.forEach(function(certificationTag){
                    if (certificationTag.tagId === tag.tagId) {
                      $scope.OsiCertificationTags.tagId.push(tag.tagId);
                    }
                  });
                });
              });
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

			$scope.OsiCertifications.certificationId = $scope.OsiCertifications.certificationId;$scope.OsiCertifications.certificationName = $scope.OsiCertifications.certificationName;$scope.OsiCertifications.certificationCode = $scope.OsiCertifications.certificationCode;$scope.OsiCertifications.certificationAddInfo = $scope.OsiCertifications.certificationAddInfo;$scope.OsiCertifications.issuedBy = $scope.OsiCertifications.issuedBy;$scope.OsiCertifications.active = $scope.OsiCertifications.active;
      $scope.OsiCertificationGroups.groupId = $scope.OsiCertifications.osiCertificationGroupId;
      $scope.OsiCertificationTags.tagId = $scope.OsiCertifications.certificationTagId;
          $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiCertifications-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiCertificationsDetails(OsiCertifications) {
        $scope.headername = "Edit Certification";
        $rootScope.isTrascError = false;
        OsiCertificationsService.getOsiCertifications(OsiCertifications.certificationId).then(function (data) {
            $scope.OsiCertifications = data;
            $scope.editCertificationName=data.certificationName;
            $scope.editCertificationCode=data.certificationCode;
            OsiCertificationGroupsService.getAllActiveOsiCertificationGroups().then(function(data) {
              $scope.OsiCertificationGroups = data;
              $scope.OsiCertificationGroups.groupId = $scope.OsiCertifications.osiCertificationGroupId;
            });
            OsiCertificationTagsService.getAllActiveOsiCertificationTags().then(function(data2){
              $scope.OsiCertificationTags = data2;
              $scope.OsiCertificationTags.tagId = [];
              $scope.OsiCertificationTags.forEach(function(tag) {
                $scope.OsiCertifications.osiCertificationTags.forEach(function(certificationTag){
                  if (certificationTag.tagId === tag.tagId) {
                    $scope.OsiCertificationTags.tagId.push(tag.tagId);
                  }
                });
              });
            });
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
		$scope.OsiCertifications.certificationId = $scope.OsiCertifications.certificationId;$scope.OsiCertifications.certificationName = $scope.OsiCertifications.certificationName;$scope.OsiCertifications.certificationCode = $scope.OsiCertifications.certificationCode;$scope.OsiCertifications.certificationAddInfo = $scope.OsiCertifications.certificationAddInfo;$scope.OsiCertifications.issuedBy = $scope.OsiCertifications.issuedBy;$scope.OsiCertifications.active = $scope.OsiCertifications.active;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiCertifications-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiCertifications() {
        $scope.headername = "Create Certification";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiCertifications.certificationId = '';$scope.OsiCertifications.certificationName = '';$scope.OsiCertifications.certificationCode = '';$scope.OsiCertifications.certificationAddInfo = '';$scope.OsiCertifications.issuedBy = '';$scope.OsiCertifications.active = 0;
        OsiCertificationGroupsService.getAllActiveOsiCertificationGroups().then(function(data) {
          $scope.OsiCertificationGroups = data;
        });
        OsiCertificationTagsService.getAllActiveOsiCertificationTags().then(function(data){
          $scope.OsiCertificationTags = data;
        });
       $('#OsiCertifications-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiCertificationss = function () {
		$scope.searchData = {certificationName:$scope.certificationName,certificationCode:$scope.certificationCode,issuedBy:$scope.issuedBy}; var searchData = JSON.stringify($scope.searchData);
		OsiCertificationsService.searchOsiCertificationss(searchData).then(function (data) {
                $scope.certificationList = data;
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
        $scope.certificationName='';$scope.certificationCode='';$scope.issuedBy='';
        $scope.clearSelectedRow();
        $scope.certificationList= "";
    };
 function validateDuplicateCertification(OsiCertifications){


    	if($scope.headername=='Edit Certification' && $scope.editCertificationCode!=null  && $scope.editCertificationName.toUpperCase()== OsiCertifications.certificationName.toUpperCase() && $scope.editCertificationCode.toUpperCase()== OsiCertifications.certificationCode.toUpperCase()){
    		//Do Nothing
    	}
    	if($scope.headername=='Edit Certification' && $scope.editCertificationCode!=null  && $scope.editCertificationName.toUpperCase()!= OsiCertifications.certificationName.toUpperCase() ){
    		for(var i=0;i<$scope.certificationList.length;i++){
        		if( OsiCertifications.certificationName.toUpperCase()==$scope.certificationList[i].certificationName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Certification Name already exists.');
        			 $scope.continuesave = false;
    				 break;
    			}
    		}
    	}
    	if($scope.headername=='Edit Certification' && $scope.editCertificationCode!=null && $scope.editCertificationCode.toUpperCase()!= OsiCertifications.certificationCode.toUpperCase() ){
    		for(var i=0;i<$scope.certificationList.length;i++){
        		if( OsiCertifications.certificationCode.toUpperCase()==$scope.certificationList[i].certificationCode.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Certification Code already exists.');
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}

    	if(!($scope.headername=='Edit Certification')){
    		for(var i=0;i<$scope.certificationList.length;i++){
        		if( OsiCertifications.certificationName.toUpperCase()==$scope.certificationList[i].certificationName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Certification Name already exists.');
        			 $scope.continuesave = false;
    				 break;
   			}else if( OsiCertifications.certificationCode.toUpperCase()==$scope.certificationList[i].certificationCode.toUpperCase()){
				$rootScope.isTrascError = true;
				FlashService.Error('Certification Code already exists.');
				$scope.continuesave = false;
				break;
			}
    		}
    	}

    }
    $scope.saveOsiCertifications = function (OsiCertifications) {

      OsiCertifications.osiCertificationTags= [];

      OsiCertifications.osiCertificationGroupId = $scope.OsiCertificationGroups.groupId;
      if ($scope.OsiCertificationTags.tagId != undefined) {
        $scope.OsiCertificationTags.tagId.forEach(function(tagId){
          $scope.OsiCertificationTags.forEach(function(certificationTag){
            if (certificationTag.tagId === tagId) {
                OsiCertifications.osiCertificationTags.push(certificationTag);
            }
          });
        });
      }
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiCertifications.certificationName){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter Certification');
            $scope.continuesave = false;
        }else if(!$scope.OsiCertificationGroups.groupId){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Select Certification Group');
            $scope.continuesave = false;} /*else if(!$scope.OsiCertifications.certificationCode){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter Certification Code');
            $scope.continuesave = false;}else if(!$scope.OsiCertifications.issuedBy){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter Issued By');
            $scope.continuesave = false;}/*else if(!$scope.OsiCertifications.active){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter Active');
            $scope.continuesave = false;}*/
        else{validateDuplicateCertification(OsiCertifications);}
        if ($scope.continuesave) {
            if (!$scope.OsiCertifications.certificationId) {

            	$scope.OsiCertifications = {
                    	"certificationId":$scope.OsiCertifications.certificationId,"certificationName":$scope.OsiCertifications.certificationName,"certificationCode":$scope.OsiCertifications.certificationCode,"certificationAddInfo":$scope.OsiCertifications.certificationAddInfo,"issuedBy":$scope.OsiCertifications.issuedBy,"active":$scope.OsiCertifications.active,"osiCertificationGroupId":OsiCertifications.osiCertificationGroupId,"osiCertificationTags": OsiCertifications.osiCertificationTags
                    };
                OsiCertificationsService
                        .saveOsiCertifications($scope.OsiCertifications)
                        .then(
                                function (result) {
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiCertifications-model').modal('hide');
                                        $scope.init();
                                        $scope.successTextAlert = appConstants.successMessage;
                                        $scope.showSuccessAlert = true;
                                        $scope.searchOsiCertificationss();
                                    } else {
                                        $rootScope.isTrascError = true;
                                        FlashService.Error(result.response);
                                        $scope.continuesave = false;
                                    }
                                    $timeout(function () {
                                        $scope.showSuccessAlert = false;
                                        //$window.location.reload();
                                    }, 1000);
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
                OsiCertificationsService
                        .updateOsiCertifications($scope.OsiCertifications)
                        .then(
                                function (result) {
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiCertifications-model').modal('hide');
                                        //$scope.init();
                                        $scope.successTextAlert = appConstants.successMessage;
                                        $scope.showSuccessAlert = true;
                                        $scope.clearSelectedRow();
                                        $scope.searchOsiCertificationss();
                                    } else {
                                        $rootScope.isTrascError = true;
                                        FlashService.Error(result.response);
                                        $scope.continuesave = false;
                                    }
                                    $timeout(function () {
                                        $scope.showSuccessAlert = false;
                                        //$window.location.reload();
                                        //$scope.searchOsiCertificationss();
                                    },1000);
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
