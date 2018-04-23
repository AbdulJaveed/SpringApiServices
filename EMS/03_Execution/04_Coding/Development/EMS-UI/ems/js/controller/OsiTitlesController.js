'use strict';
angular.module('myApp.OsiTitlesController', []).controller('OsiTitlesController',
        OsiTitlesController);
OsiTitlesController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiTitlesService', 'FlashService', '$timeout','appConstants'];
function OsiTitlesController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiTitlesService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.titleList = "";
    $scope.iseditable = true;
    $scope.rowSize = 5;
    $scope.isSelectedRow = null;
    $scope.selectedRowDetails = [];
    $scope.disable_Create = false;
    $scope.disable_Edit = true;
    $scope.disable_Delete = true;
    $scope.disable_View = true;
    $scope.successTextAlert = "";
    $scope.editTitleName=null;
    $scope.editTitleCode=null;
	$scope.duplicateTitleCode=false;
    $scope.showSuccessAlert = false;
    $scope.titleList = [];
	$scope.OsiTitles = [];  
    $scope.sort = function (keyname) {
        if ($scope.titleList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiTitles){
     editOsiTitlesDetails(OsiTitles);
     $scope.isSelectedRow = OsiTitles.titleId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiTitlesService.searchOsiTitless(searchData).then(function (data) {
            $scope.titleList = data;
            $scope.sort('titleCode');
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
   /* 	OsiTitlesService.getAllorganizations().then(function(data){
    		$scope.organizations=data;
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
        });*/
    };

	$scope.selectRow = function (item) {
        // for checking single row selection
        $scope.isSelectedRow = item.titleId;
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
            createOsiTitles();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiTitlesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiTitlesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }
	
	 function viewOsiTitlesDetails(OsiTitles) {
	        $scope.headername = "View Title";
	        $rootScope.isTrascError = false;
	        OsiTitlesService.getOsiTitles(OsiTitles.titleId).then(function (data) {
	            $scope.OsiTitles = data;
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
			$scope.OsiTitles.titleId = $scope.OsiTitles.titleId;$scope.OsiTitles.titleShortName = $scope.OsiTitles.titleShortName;$scope.OsiTitles.titleLongName = $scope.OsiTitles.titleLongName;$scope.OsiTitles.titleCode = $scope.OsiTitles.titleCode;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiTitles-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiTitlesDetails(OsiTitles) {
        $scope.headername = "Edit Title";
        $rootScope.isTrascError = false;
        OsiTitlesService.getOsiTitles(OsiTitles.titleId).then(function (data) {
            $scope.OsiTitles = data;
            $scope.editTitleName=data.titleShortName;
            $scope.editTitleCode=data.titleCode;
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
		$scope.OsiTitles.titleId = $scope.OsiTitles.titleId;$scope.OsiTitles.titleShortName = $scope.OsiTitles.titleShortName;$scope.OsiTitles.titleLongName = $scope.OsiTitles.titleLongName;$scope.OsiTitles.titleCode = $scope.OsiTitles.titleCode;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiTitles-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiTitles() {
        $scope.headername = "Create Title";
        $rootScope.isTrascError = false;
        $scope.iseditable = true; 
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiTitles.titleId = '';$scope.OsiTitles.titleShortName = '';$scope.OsiTitles.titleLongName = '';$scope.OsiTitles.titleCode = '';
       $('#OsiTitles-model').modal({show: true, backdrop: 'static'});
    };

    $scope.searchOsiTitless = function () {
		$scope.searchData = {titleCode:$scope.titleCode,titleShortName:$scope.titleShortName,titleLongName:$scope.titleLongName}; var searchData = JSON.stringify($scope.searchData);
		OsiTitlesService.searchOsiTitless(searchData).then(function (data) {
                $scope.titleList = data;
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

    $scope.clearSearch = function () {
        $scope.titleCode='';$scope.titleShortName='';$scope.titleLongName='';
        $scope.clearSelectedRow();
        $scope.init();
    };
    function validateDuplicateTitleEntry(OsiTitles){
    
    	
    	if($scope.headername=='Edit Title' && $scope.editTitleName!=null && $scope.editTitleName.toUpperCase()== OsiTitles.titleShortName.toUpperCase() && $scope.editTitleCode.toUpperCase()== OsiTitles.titleCode.toUpperCase()){
    		//Do Nothing
    	}
    	if($scope.headername=='Edit Title' && $scope.editTitleName!=null  && $scope.editTitleName.toUpperCase()!= OsiTitles.titleShortName.toUpperCase() ){
    		for(var i=0;i<$scope.titleList.length;i++){
        		if( OsiTitles.titleShortName.toUpperCase()==$scope.titleList[i].titleShortName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Title Short Name already exists.'); 
        			 $scope.continuesave = false;
    				 break;
    			}
    		}
    	}
    	if($scope.headername=='Edit Title' && $scope.editTitleName!=null && $scope.editTitleCode.toUpperCase()!= OsiTitles.titleCode.toUpperCase() ){
    		for(var i=0;i<$scope.titleList.length;i++){
        		if( OsiTitles.titleCode.toUpperCase()==$scope.titleList[i].titleCode.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Title Code already exists.'); 
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}
  /*  	
    	if($scope.headername=='Edit OsiTitles' &&  $scope.editTitleName.toUpperCase()== OsiTitles.titleShortName.toUpperCase() ){
    		for(var i=0;i<$scope.titleList.length;i++){
        		if( OsiTitles.titleCode.toUpperCase()==$scope.titleList[i].titleCode.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Title Code already exists.'); 
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}*/
    	if(!($scope.headername=='Edit Title')){
    		for(var i=0;i<$scope.titleList.length;i++){
    			if( OsiTitles.titleShortName.toUpperCase()==$scope.titleList[i].titleShortName.toUpperCase()){
       			 $rootScope.isTrascError = true;
       			 FlashService.Error('Title Short Name already exists.'); 
       			 $scope.continuesave = false;
   				 break;
   			}else if( OsiTitles.titleCode.toUpperCase()==$scope.titleList[i].titleCode.toUpperCase()){
				$rootScope.isTrascError = true;
				FlashService.Error('Title Code already exists.'); 
				$scope.continuesave = false;
				break;
			}
    		}    		
    	}

    }
    $scope.saveOsiTitles = function (OsiTitles) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiTitles.titleShortName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Title Short Name'); 
 $scope.continuesave = false;}else if(!$scope.OsiTitles.titleLongName){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Title Long Name'); 
 $scope.continuesave = false;}else if(!$scope.OsiTitles.titleCode){ 
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Title Code'); 
 $scope.continuesave = false;}
 else{validateDuplicateTitleEntry(OsiTitles);}
        
        if ($scope.continuesave) {
            if (!$scope.OsiTitles.titleId) {
            	
            	$scope.OsiTitles = {
                    	"id":$scope.OsiTitles.titleId,"titleShortName":$scope.OsiTitles.titleShortName,"titleLongName":$scope.OsiTitles.titleLongName,"titleCode":$scope.OsiTitles.titleCode
                    };
                OsiTitlesService
                        .saveOsiTitles($scope.OsiTitles)
                        .then(
                                function (result) {
                                	 if (result.response.indexOf("Error")==-1) {
                                        $('#OsiTitles-model').modal('hide');
                                        $scope.init();
                                        $scope.successTextAlert = result.response;
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
                OsiTitlesService
                        .updateOsiTitles($scope.OsiTitles)
                        .then(
                                function (result) {
                                	if (result.response.indexOf("Error")==-1) {
                                        $('#OsiTitles-model').modal('hide');
                                        $scope.init();
                                        $scope.successTextAlert = result.response;
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
