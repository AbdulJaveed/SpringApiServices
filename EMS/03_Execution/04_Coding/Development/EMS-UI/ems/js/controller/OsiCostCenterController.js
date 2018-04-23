'use strict';
angular.module('myApp.OsiCostCenterController', []).controller('OsiCostCenterController',
        OsiCostCenterController);
OsiCostCenterController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiCostCenterService', 'FlashService', '$timeout','appConstants'];
function OsiCostCenterController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiCostCenterService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.costCenterList = "";
    $scope.iseditable = true;
    $scope.rowSize = 5;
    $scope.isSelectedRow = null;
    $scope.selectedRowDetails = [];
    $scope.disable_Create = false;
    $scope.disable_Edit = true;
    $scope.disable_Delete = true;
    $scope.disable_View = true;
    $scope.editCostCenterName=null;
    $scope.duplicateCostCenter=false;
    $scope.successTextAlert = "";
    $scope.showSuccessAlert = false;
    $scope.costCenterList = [];
	$scope.OsiCostCenter = [];
    $scope.sort = function (keyname) {
        if ($scope.costCenterList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiCostCenter){
     editOsiCostCenterDetails(OsiCostCenter);
     $scope.isSelectedRow = OsiCostCenter.ccId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiCostCenterService.searchOsiCostCenters(searchData).then(function (data) {
            $scope.costCenterList = data;
            $scope.sort('ccShortName');
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
        $scope.isSelectedRow = item.ccId;
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
            createOsiCostCenter();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiCostCenterDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiCostCenterDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiCostCenterDetails(OsiCostCenter) {
	        $scope.headername = "View Practice";
	        $rootScope.isTrascError = false;
	        OsiCostCenterService.getOsiCostCenter(OsiCostCenter.ccId).then(function (data) {
                $scope.OsiCostCenter = data;
                $scope.ccid = OsiCostCenter.ccId;
                initController(OsiCostCenter.ccId);
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
			$scope.OsiCostCenter.ccId = $scope.OsiCostCenter.ccId;$scope.OsiCostCenter.ccShortName = $scope.OsiCostCenter.ccShortName;$scope.OsiCostCenter.ccLongName = $scope.OsiCostCenter.ccLongName;$scope.OsiCostCenter.active = $scope.OsiCostCenter.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiCostCenter-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiCostCenterDetails(OsiCostCenter) {
        $scope.headername = "Edit Practice";
        $rootScope.isTrascError = false;
        OsiCostCenterService.getOsiCostCenter(OsiCostCenter.ccId).then(function (data) {
            $scope.OsiCostCenter = data;
            $scope.editCostCenterName=data.ccShortName;

            $scope.ccid = OsiCostCenter.ccId;
            initController(OsiCostCenter.ccId);
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
		$scope.OsiCostCenter.ccId = $scope.OsiCostCenter.ccId;$scope.OsiCostCenter.ccShortName = $scope.OsiCostCenter.ccShortName;$scope.OsiCostCenter.ccLongName = $scope.OsiCostCenter.ccLongName;$scope.OsiCostCenter.active = $scope.OsiCostCenter.active;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiCostCenter-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiCostCenter() {
        $scope.headername = "Create Practice";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiCostCenter.ccId = '';$scope.OsiCostCenter.ccShortName = '';$scope.OsiCostCenter.ccLongName = '';$scope.OsiCostCenter.active = 0;
       $('#OsiCostCenter-model').modal({show: true, backdrop: 'static'});
       vm.costingInfo = [];
       vm.osiOrganizations = $localStorage.osiOrganizations;
       vm.costingInfo.push({ccId:$scope.ccid,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct: null});
    };

    $scope.searchOsiCostCenters = function () {
		$scope.searchData = {ccShortName:$scope.ccShortName,ccLongName:$scope.ccLongName}; var searchData = JSON.stringify($scope.searchData);
		OsiCostCenterService.searchOsiCostCenters(searchData).then(function (data) {
                $scope.costCenterList = data;
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
        $scope.ccShortName='';$scope.ccLongName='';
        $scope.clearSelectedRow();
        $scope.init();
    };
    function validateDuplicateCostCenter(OsiCostCenter){
    	if($scope.headername=='Edit Cost Center' && $scope.editCostCenterName.toUpperCase()== OsiCostCenter.ccShortName.toUpperCase() ){
    		//Do Nothing
    	}
    	if($scope.headername =='Create Cost Center' /*&& $scope.editCostCenterName.toUpperCase()== OsiCostCenter.ccShortName.toUpperCase()*/){
    		for(var i=0;i<$scope.costCenterList.length;i++){
        		if(OsiCostCenter.ccShortName.toUpperCase()==$scope.costCenterList[i].ccShortName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Short name already exists.');
        			 $scope.continuesave = false;
    				 break;
    			}
    		}
    	}
    	if($scope.headername =='Edit Cost Center' && $scope.editCostCenterName.toUpperCase()!=  OsiCostCenter.ccShortName.toUpperCase()){
    		for(var i=0;i<$scope.costCenterList.length;i++){
        		if( OsiCostCenter.ccShortName.toUpperCase()==$scope.costCenterList[i].ccShortName.toUpperCase()){
        			 $rootScope.isTrascError = true;
        			 FlashService.Error('Short name already exists.');
        			 $scope.continuesave = false;
    				 break;
    			}
    		}
    	}

    }
    $scope.saveOsiCostCenter = function (OsiCostCenter) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiCostCenter.ccShortName){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter CC Short Name');
 $scope.continuesave = false;}else if(!$scope.OsiCostCenter.ccLongName){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter CC Long Name');
 $scope.continuesave = false;}/*else if(!$scope.OsiCostCenter.active){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Active');
 $scope.continuesave = false;}*/
 else{validateDuplicateCostCenter(OsiCostCenter);}

        if ($scope.continuesave) {
            if (!$scope.OsiCostCenter.ccId) {

            	$scope.OsiCostCenter = {
                    	"ccId":$scope.OsiCostCenter.ccId,"ccShortName":$scope.OsiCostCenter.ccShortName,"ccLongName":$scope.OsiCostCenter.ccLongName,"active":$scope.OsiCostCenter.active
                    };
                OsiCostCenterService
                        .saveOsiCostCenter($scope.OsiCostCenter)
                        .then(
                                function (result) {
                                    if(result.ccId != undefined && result.ccId != null) {
                                	// if (result.response.indexOf("Error")==-1) {
                                    //    $('#OsiCostCenter-model').modal('hide');
                                    var saveGrades = false;
                                    if(vm.costingInfo.length == 1) {
                                        if(vm.costingInfo[0].costPerHour != null 
                                            || vm.costingInfo[0].costPerMonth != null 
                                            || vm.costingInfo[0].gradeId != null 
                                            || vm.costingInfo[0].orgId != null 
                                            || vm.costingInfo[0].internalCostOverheadPct != null) {
                                            saveGrades = true;
                                        } else {
                                            $rootScope.isTrascError = true;
                                            FlashService.Success(appConstants.successMessage);
                                        }
                                    } else {
                                        saveGrades = true;
                                    }
                                    if(saveGrades) {
                                        angular.forEach(vm.costingInfo, function(value, key) {
                                            if(value.ccId == undefined || value.ccId == null) {
                                                value.ccId = result.ccId;
                                            }
                                        });
                                        console.log(vm.costingInfo);
                                        vm.saveCcGrades();
                                    }
                                        //$scope.init();
                                        //$scope.successTextAlert = appConstants.successMessage;
                                        //$scope.showSuccessAlert = true;
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
                OsiCostCenterService
                        .updateOsiCostCenter($scope.OsiCostCenter)
                        .then(
                                function (result) {
                                    if(result.ccId != undefined && result.ccId != null) {
                                	//if (result.response.indexOf("Error")==-1) {
                                        //$('#OsiCostCenter-model').modal('hide');
                                        //$scope.init();
                                        //$scope.successTextAlert = appConstants.successMessage;
                                        //$scope.showSuccessAlert = true;
                                        //$scope.clearSelectedRow();
                                        var saveGrades = false;
                                        if(vm.costingInfo.length == 1) {
                                            if(vm.costingInfo[0].costPerHour != null || vm.costingInfo[0].costPerMonth != null || vm.costingInfo[0].gradeId != null 
                                                || vm.costingInfo[0].orgId != null || vm.costingInfo[0].internalCostOverheadPct != null) {
                                                saveGrades = true;
                                            } else if(vm.costingInfo[0].costPerHour == null 
                                                && vm.costingInfo[0].costPerMonth == null 
                                                && vm.costingInfo[0].gradeId == null 
                                                && vm.costingInfo[0].orgId == null 
                                                && vm.costingInfo[0].internalCostOverheadPct == null) {
                                                console.log('remove.....');
                                                console.log(vm.costingInfo);
                                                saveGrades = true;
                                            } else {
                                                $rootScope.isTrascError = true;
                                                FlashService.Success(appConstants.successMessage);
                                            }
                                        } else {
                                            saveGrades = true;
                                        }
                                        if(saveGrades) {
                                            angular.forEach(vm.costingInfo, function(value, key) {
                                                if(value.ccId == undefined || value.ccId == null) {
                                                    value.ccId = result.ccId;
                                                }
                                            });
                                            console.log(vm.costingInfo);
                                            vm.saveCcGrades();
                                        }
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

    // Cost Center Grades Mapping
    vm.costingInfo = [];
    function initController(ccid) {
      vm.osiOrganizations = $localStorage.osiOrganizations;
      if(ccid == undefined)
        ccid = $scope.ccid;
      OsiCostCenterService.getAllCCGradesByccId(ccid).then(function (data) {
            console.log('after getting the response : ');
            console.log(data);
            vm.costingInfo = data;
            if(data.length != 0) {
            angular.forEach(data, function(val, key) {
              if(val.orgId)
                $scope.getAllGradesByOrgId(val.orgId, key);
            });
          } else {
             vm.costingInfo.push({ccId:$scope.ccid,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct: null});
          }
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
    vm.costingInfo = [];
    /*$scope.openCostingModal = function(ccid) {
      $scope.ccid = ccid;
      initController(ccid);
      console.log('Cost center Id: '+ ccid);
      $('#costingModal').modal('show');
      console.log($localStorage.osiOrganizations);
    };*/
    $scope.addRow = function (costing) {
        if (costing.orgId != null && costing.gradeId != null && costing.costPerHour != null 
            && costing.costPerMonth != null && costing.internalCostOverheadPct != null)
        {
        	  vm.costingInfo.push({ccId:$scope.ccid,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct:null});
            console.log(vm.costingInfo);
        }else {
     	   $rootScope.isTrascError = true;
           FlashService.Error("Please fill all necessary fields.");
           $timeout(function () {
           	$rootScope.isTrascError=false;
           }, 3000);
        }
    };

    $scope.removeRow = function (index) {
        vm.costingInfo.splice(index, 1);
        if(vm.costingInfo.length === 0){
            vm.costingInfo = [{orgId : null, gradeId : null, costPerMonth:null, costPerHour:null, ccId:$scope.ccid,  internalCostOverheadPct:null}];
        }
    };

    vm.clearCostingInfo = function(){
    	initController();
    };
    $scope.selectedCosting = [];
    $scope.selectedCostingGrade = [];
    $scope.wrongSelectedResp = false;
    $scope.checkDuplicateCosting = function(selOrgId, selGradeId, index){

        angular.forEach(vm.costingInfo, function(value, key) {
            $scope.selectedCosting.push(value.orgId);
            $scope.selectedCostingGrade.push(value.gradeId);
        });
        if(($scope.selectedCosting.indexOf(selOrgId)!=$scope.selectedCosting.lastIndexOf(selOrgId))
            && ($scope.selectedCostingGrade.indexOf(selGradeId)!=$scope.selectedCostingGrade.lastIndexOf(selGradeId))
            ){
                $scope.wrongSelectedResp = true;
            	$rootScope.isTrascError = true;
				FlashService.Error("You can not enter same Costing more than one time");
				$timeout(function() {
					$rootScope.isTrascError = false;
				}, 4000);
        }
        else{
            $scope.wrongSelectedResp = false;
        }
        $scope.selectedCosting = [];
        $scope.selectedCostingGrade = [];
    }
    vm.osiGrades = [];
    $scope.getAllGradesByOrgId = function (orgId, index) {
        if(orgId != undefined) {
            OsiCostCenterService.getAllGradesByOrgId(orgId).then(function (data) {
                console.log('Graddess ..');
                console.log(data);
                //vm.osiGrades = data;
                vm.osiGrades[index] = data;
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
            if($localStorage.osiOrganizations != undefined)
            angular.forEach($localStorage.osiOrganizations, function(values, key) {
                if(values.orgId == orgId)
                    vm.costingInfo[index].internalCostOverheadPct = values.interEmpOverheadPct;
            })
        } else{
            vm.costingInfo[index].internalCostOverheadPct = null;
        }
    }

    $scope.setValues = function(gradeId, index) {
      console.log(gradeId);
      angular.forEach(vm.osiGrades[0], function(val, key) {
        if(val.gradeId == gradeId) {
          vm.costingInfo[index].costPerHour = val.costPerHour;
          vm.costingInfo[index].costPerMonth = val.costPerMonth;
        }
      })

    };
    $scope.validateCcGrades = function() {
        $scope.isValid = false;
        if(vm.costingInfo.length != 1) {
            angular.forEach(vm.costingInfo, function(val, key) {
                if(val.ccId == undefined || val.ccId == null)
                val.ccId = $scope.ccid;
                if(val.orgId != null && val.gradeId != null && val.costPerHour != null 
                    && val.costPerMonth != null && val.internalCostOverheadPct != null) {
                $scope.isValid = true;
                } else {
                $scope.isValid = false;
                }
            })
        } else {
            var val = vm.costingInfo[0];
            if(val.ccId == undefined || val.ccId == null)
                val.ccId = $scope.ccid;
            if(val.orgId == null && val.gradeId == null && val.costPerHour == null 
                && val.costPerMonth == null && val.internalCostOverheadPct == null) {
            $scope.isValid = true;
            } else if(val.orgId != null && val.gradeId != null && val.costPerHour != null 
                && val.costPerMonth != null && val.internalCostOverheadPct != null) {
            $scope.isValid = true;
            } else {
            $scope.isValid = false;
            }
        }
    }
    vm.saveCcGrades = function() {
      console.log(vm.costingInfo);
      $scope.validateCcGrades();
      if($scope.isValid) {
        OsiCostCenterService.saveCcGrades(vm.costingInfo).then(function (data) {
          console.log('saved successfully');
          console.log(data);
          vm.costingInfo = data;
          //$('#costingModal').modal('hide');
        
          
          $scope.init();
          $scope.clearSelectedRow();

          $rootScope.isTrascError=true;
          FlashService.Success(appConstants.successMessage);
           $timeout(function () {
            $('#OsiCostCenter-model').modal('hide');
             $rootScope.isTrascError=false;
           },3000);
        }).catch(function(error) {
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
        $rootScope.isTrascError=true;
        FlashService.Error("Please Fill Mandatory Fields");
         $timeout(function () {
           $rootScope.isTrascError=false;
         },3000);
      }
    }
}
