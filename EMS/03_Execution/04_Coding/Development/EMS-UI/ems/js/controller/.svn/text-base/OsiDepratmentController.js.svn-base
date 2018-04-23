'use strict';
angular.module('myApp.OsiDepratmentController', []).controller('OsiDepratmentController',
        OsiDepratmentController);
OsiDepratmentController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiDepratmentService', 'FlashService', '$timeout','appConstants'];
function OsiDepratmentController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiDepratmentService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.practiceList = "";
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
    $scope.editDeptShortName=null;
    $scope.practiceList = [];
	$scope.OsiDepratment = [];
    $scope.sort = function (keyname) {
        if ($scope.practiceList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiDepratment){
     editOsiDepratmentDetails(OsiDepratment);
     $scope.isSelectedRow = OsiDepratment.deptId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        var searchData = JSON.stringify({});
        /* --Commented for stop initial loading list
    	OsiDepratmentService.searchOsiDepratments(searchData).then(function (data) {
            $scope.practiceList = data;
            $scope.sort('deptShortName');
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
        $scope.isSelectedRow = item.deptId;
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
            createOsiDepratment();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiDepratmentDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiDepratmentDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiDepratmentDetails(OsiDepratment) {
	        $scope.headername = "View Department";
	        $rootScope.isTrascError = false;
	        OsiDepratmentService.getOsiDepratment(OsiDepratment.deptId).then(function (data) {
                $scope.OsiDepratment = data;                
                $scope.deptId = OsiDepratment.deptId;
                initController(OsiDepratment.deptId);
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
			$scope.OsiDepratment.deptId = $scope.OsiDepratment.deptId;$scope.OsiDepratment.deptShortName = $scope.OsiDepratment.deptShortName;$scope.OsiDepratment.deptLongName = $scope.OsiDepratment.deptLongName;$scope.OsiDepratment.active = $scope.OsiDepratment.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiDepratment-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiDepratmentDetails(OsiDepratment) {
        $scope.headername = "Edit Department";
        $rootScope.isTrascError = false;
        OsiDepratmentService.getOsiDepratment(OsiDepratment.deptId).then(function (data) {
            $scope.OsiDepratment = data;
            $scope.editDeptShortName=data.deptShortName;

            $scope.deptId = OsiDepratment.deptId;
            initController(OsiDepratment.deptId);
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
		$scope.OsiDepratment.deptId = $scope.OsiDepratment.deptId;$scope.OsiDepratment.deptShortName = $scope.OsiDepratment.deptShortName;$scope.OsiDepratment.deptLongName = $scope.OsiDepratment.deptLongName;$scope.OsiDepratment.active = $scope.OsiDepratment.active;
        $scope.isUpdatable=true;
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
       $('#OsiDepratment-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiDepratment() {
        $scope.headername = "Create Department";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiDepratment.deptId = '';$scope.OsiDepratment.deptShortName = '';$scope.OsiDepratment.deptLongName = '';$scope.OsiDepratment.active = 0;
       $('#OsiDepratment-model').modal({show: true, backdrop: 'static'});

       vm.costingInfo = [];
       vm.osiOrganizations = $localStorage.osiOrganizations;
       vm.costingInfo.push({deptId:$scope.deptId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct:null});
    };

    $scope.searchOsiDepratments = function () {
		$scope.searchData = {deptShortName:$scope.deptShortName,deptLongName:$scope.deptLongName}; var searchData = JSON.stringify($scope.searchData);
		OsiDepratmentService.searchOsiDepratments(searchData).then(function (data) {
                $scope.practiceList = data;
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
        $scope.deptShortName='';$scope.deptLongName='';
        $scope.clearSelectedRow();
        $scope.init();
    };

    function validateDuplicateDept(OsiDepratment){
    	if($scope.headername=='Edit Practice' && $scope.editDeptShortName.toUpperCase()!=OsiDepratment.deptShortName.toUpperCase()){
    		for(var i=0;i<$scope.practiceList.length;i++){
        		if(OsiDepratment.deptShortName.toUpperCase()==$scope.practiceList[i].deptShortName.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Practice Short Name already exists.');
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}
    	if($scope.headername!='Edit Practice'){
    		for(var i=0;i<$scope.practiceList.length;i++){
        		if(OsiDepratment.deptShortName.toUpperCase()==$scope.practiceList[i].deptShortName.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Practice Short Name already exists.');
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}
    }

    $scope.saveOsiDepratment = function (OsiDepratment) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiDepratment.deptShortName){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Practice Short Name');
 $scope.continuesave = false;}else if(!$scope.OsiDepratment.deptLongName){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Practice Long Name');
 $scope.continuesave = false;}else{
	 validateDuplicateDept(OsiDepratment);
 }/*else if(!$scope.OsiDepratment.active){
 $rootScope.isTrascError = true;
 FlashService.Error('Please Enter Active');
 $scope.continuesave = false;}*/

        if ($scope.continuesave) {
            if (!$scope.OsiDepratment.deptId) {

            	$scope.OsiDepratment = {
                    	"id":$scope.OsiDepratment.deptId,"deptShortName":$scope.OsiDepratment.deptShortName,"deptLongName":$scope.OsiDepratment.deptLongName,"active":$scope.OsiDepratment.active
                    };
                OsiDepratmentService
                        .saveOsiDepratment($scope.OsiDepratment)
                        .then(
                                function (result) {
                                    if(result.deptId != undefined && result.deptId != null) {
                                	// if (result.response.indexOf("Error")==-1) {
                                    //     $('#OsiDepratment-model').modal('hide');
                                    //     $scope.init();
                                    //     $scope.successTextAlert = appConstants.successMessage;
                                    //     $scope.showSuccessAlert = true;
                                    var saveGrades = false;
                                    if(vm.costingInfo.length == 1) {
                                        if(vm.costingInfo[0].costPerHour != null || vm.costingInfo[0].costPerMonth != null || vm.costingInfo[0].gradeId != null 
                                            || vm.costingInfo[0].orgId != null || vm.costingInfo[0].internalCostOverheadPct != null) {
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
                                        $rootScope.isTrascError=false;
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
                OsiDepratmentService
                        .updateOsiDepratment($scope.OsiDepratment)
                        .then(
                                function (result) {
                                    if(result.deptId != undefined && result.deptId != null) { 
                                	// if (result.response.indexOf("Error")==-1) {
                                    //     $('#OsiDepratment-model').modal('hide');
                                    //     $scope.init();
                                    //     $scope.successTextAlert = appConstants.successMessage;
                                    //     $scope.showSuccessAlert = true;
                                    //     $scope.clearSelectedRow();
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
                                        $rootScope.isTrascError = false;
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
    function initController(deptId) {
      vm.osiOrganizations = $localStorage.osiOrganizations;
      if(deptId == undefined)
        deptId = $scope.deptId;
      OsiDepratmentService.getAllDeptGradesByDeptId(deptId).then(function (data) {
            console.log('after getting the response : ');
            console.log(data);
            vm.costingInfo = data;
            if(data.length != 0) {
            angular.forEach(data, function(val, key) {
              if(val.orgId)
                $scope.getAllGradesByOrgId(val.orgId, key);
            });
          } else {
             vm.costingInfo.push({deptId:$scope.deptId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct: null});
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
    /*$scope.openCostingModal = function(deptId) {
      $scope.deptId = deptId;
      initController(deptId);
      console.log('Department Id: '+ deptId);
      $('#costingModal').modal('show');
      console.log($localStorage.osiOrganizations);
    };*/
    $scope.addRow = function (costing) {
        if (costing.orgId != null && costing.gradeId != null && costing.costPerHour != null 
            && costing.costPerMonth != null && costing.internalCostOverheadPct != null)
        {
        	  vm.costingInfo.push({deptId:$scope.deptId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct: null});
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
            vm.costingInfo = [{orgId : null, gradeId : null, costPerMonth:null, costPerHour:null, deptId:$scope.deptId, internalCostOverheadPct: null}];
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
            OsiDepratmentService.getAllGradesByOrgId(orgId).then(function (data) {
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
                if(val.deptId == undefined || val.deptId == null)
                val.deptId = $scope.deptId;
                if(val.orgId != null && val.gradeId != null && val.costPerHour != null 
                    && val.costPerMonth != null && val.internalCostOverheadPct != null) {
                $scope.isValid = true;
                } else {
                $scope.isValid = false;
                }
            })
        } else {
            var val = vm.costingInfo[0];
            if(val.deptId == undefined || val.deptId == null)
                val.deptId = $scope.deptId;
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
        OsiDepratmentService.saveDeptGrades(vm.costingInfo).then(function (data) {
          console.log('saved successfully');
          console.log(data);
          vm.costingInfo = data;
          //$('#costingModal').modal('hide');
          $scope.init();
          $scope.clearSelectedRow();

          $rootScope.isTrascError=true;
          FlashService.Success(appConstants.successMessage);
           $timeout(function () {
            $('#OsiDepratment-model').modal('hide');
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
