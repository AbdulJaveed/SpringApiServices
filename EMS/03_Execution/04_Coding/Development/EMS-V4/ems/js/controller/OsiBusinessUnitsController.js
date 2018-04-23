'use strict';
angular.module('myApp.OsiBusinessUnitsController', []).controller('OsiBusinessUnitsController',
        OsiBusinessUnitsController);
OsiBusinessUnitsController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiBusinessUnitsService', 'FlashService', '$timeout','appConstants','employeeBasicInfoService'];
function OsiBusinessUnitsController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiBusinessUnitsService, FlashService, $timeout,appConstants,employeeBasicInfoService) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.businessUnitList = "";
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
    $scope.editBuShortName=false;
    $scope.businessUnitList = [];
	$scope.OsiBusinessUnits = [];
    $scope.sort = function (keyname) {
        if ($scope.businessUnitList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiBusinessUnits){
     editOsiBusinessUnitsDetails(OsiBusinessUnits);
     $scope.isSelectedRow = OsiBusinessUnits.buId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        getOrganizations();
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiBusinessUnitsService.searchOsiBusinessUnitss(searchData).then(function (data) {
            $scope.businessUnitList = data;
            $scope.sort('buShortName');
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
        $scope.isSelectedRow = item.buId;
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
            createOsiBusinessUnits();
            getOrganizations();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiBusinessUnitsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiBusinessUnitsDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiBusinessUnitsDetails(OsiBusinessUnits) {
	        $scope.headername = "View Business Unit";
	        $rootScope.isTrascError = false;
	        OsiBusinessUnitsService.getOsiBusinessUnits(OsiBusinessUnits.buId).then(function (data) {
                $scope.OsiBusinessUnits = data;
                $scope.buId = OsiBusinessUnits.buId;
                initController(OsiBusinessUnits.buId);
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
			$scope.OsiBusinessUnits.buId = $scope.OsiBusinessUnits.buId;$scope.OsiBusinessUnits.buShortName = $scope.OsiBusinessUnits.buShortName;$scope.OsiBusinessUnits.buLongName = $scope.OsiBusinessUnits.buLongName;$scope.OsiBusinessUnits.active = $scope.OsiBusinessUnits.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiBusinessUnits-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiBusinessUnitsDetails(OsiBusinessUnits) {
        getOrganizations();
        $scope.headername = "Edit Business Unit";
        $rootScope.isTrascError = false;
        OsiBusinessUnitsService.getOsiBusinessUnits(OsiBusinessUnits.buId).then(function (data) {
            $scope.OsiBusinessUnits = data;
            $scope.editBuShortName=data.buShortName;

            $scope.buId = OsiBusinessUnits.buId;
            initController(OsiBusinessUnits.buId);
            $scope.OsiBusinessUnits.buId = $scope.OsiBusinessUnits.buId;$scope.OsiBusinessUnits.buShortName = $scope.OsiBusinessUnits.buShortName;$scope.OsiBusinessUnits.buLongName = $scope.OsiBusinessUnits.buLongName;$scope.OsiBusinessUnits.active = $scope.OsiBusinessUnits.active;
            $scope.isUpdatable=true;
            $scope.iseditable = true;
            $scope.iscodeeditable =false;
           $('#OsiBusinessUnits-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiBusinessUnits() {
        $scope.headername = "Create Business Unit";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiBusinessUnits.buId = '';$scope.OsiBusinessUnits.buShortName = '';$scope.OsiBusinessUnits.buLongName = '';$scope.OsiBusinessUnits.active = 0;
       $('#OsiBusinessUnits-model').modal({show: true, backdrop: 'static'});

       vm.costingInfo = [];
       vm.osiOrganizations = $localStorage.osiOrganizations;
       vm.costingInfo.push({buId:$scope.buId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct:null});
    };

    $scope.searchOsiBusinessUnitss = function () {
		$scope.searchData = {buShortName:$scope.buShortName,buLongName:$scope.buLongName}; var searchData = JSON.stringify($scope.searchData);
		OsiBusinessUnitsService.searchOsiBusinessUnitss(searchData).then(function (data) {
                $scope.businessUnitList = data;
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
        $scope.buShortName='';$scope.buLongName='';
        $scope.clearSelectedRow();
        $scope.businessUnitList="";
    };

    function validateDuplicateBu(OsiBusinessUnits){
    	if($scope.headername=='Edit Business Unit' && $scope.editBuShortName.toUpperCase()!=OsiBusinessUnits.buShortName.toUpperCase()){
    		for(var i=0;i<$scope.businessUnitList.length;i++){
        		if(OsiBusinessUnits.buShortName.toUpperCase()==$scope.businessUnitList[i].buShortName.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Business Unit Short Name already exists.');
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}
    	if($scope.headername!='Edit Business Unit'){
    		for(var i=0;i<$scope.businessUnitList.length;i++){
        		if(OsiBusinessUnits.buShortName.toUpperCase()==$scope.businessUnitList[i].buShortName.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Business Unit Short Name already exists.');
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}
    }

    $scope.saveOsiBusinessUnits = function (OsiBusinessUnits) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiBusinessUnits.buShortName){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter BU Short Name');
            $scope.continuesave = false;
        } else if(!$scope.OsiBusinessUnits.buLongName) {
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter BU Long Name');
            $scope.continuesave = false;
        } else{
	        validateDuplicateBu(OsiBusinessUnits);
        }/*else if(!$scope.OsiBusinessUnits.active){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter Active');
            $scope.continuesave = false;
        }*/

        if ($scope.continuesave) {
            if (!$scope.OsiBusinessUnits.buId) {

            	$scope.OsiBusinessUnits = {
                    	"id":$scope.OsiBusinessUnits.buId,"buShortName":$scope.OsiBusinessUnits.buShortName,"buLongName":$scope.OsiBusinessUnits.buLongName,"active":$scope.OsiBusinessUnits.active
                    };
                OsiBusinessUnitsService
                        .saveOsiBusinessUnits($scope.OsiBusinessUnits)
                        .then(
                                function (result) {
                                    if(result.buId != undefined && result.buId != null) {
                                	//if (result.response.indexOf("Error")==-1) {
                                        //$('#OsiBusinessUnits-model').modal('hide');
                                        //$scope.init();
                                        //$scope.successTextAlert = appConstants.successMessage;
                                        //$scope.showSuccessAlert = true;
                                        var saveGrades = false;
                                        if(vm.costingInfo.length == 1) {
                                            if(vm.costingInfo[0].costPerHour != null || vm.costingInfo[0].costPerMonth != null || vm.costingInfo[0].gradeId != null 
                                                || vm.costingInfo[0].orgId != null || vm.costingInfo[0].internalCostOverheadPct != null) {
                                                saveGrades = true;
                                            } else {
                                                $rootScope.isTrascError = true;
                                                FlashService.Success(appConstants.successMessage);
                                                $timeout(function () {
                                                    $scope.showSuccessAlert = false;
                                                    $rootScope.isTrascError=false;
                                                    //$window.location.reload();
                                                    $('#OsiBusinessUnits-model').modal('hide');
                                                    $scope.searchOsiBusinessUnitss();
                                                }, 1000);
                                            }
                                        } else {
                                            saveGrades = true;
                                        }
                                        if(saveGrades) {
                                            angular.forEach(vm.costingInfo, function(value, key) {
                                                if(value.buId == undefined || value.buId == null || value.buId !== result.buId) {
                                                    value.buId = result.buId;
                                                }
                                            });
                                            console.log(vm.costingInfo);
                                            if(vm.saveCcGrades()){
                                                $timeout(function () {
                                                    $scope.showSuccessAlert = false;
                                                    $rootScope.isTrascError=false;
                                                    $('#OsiBusinessUnits-model').modal('hide');
                                                    //$window.location.reload();
                                                    $scope.searchOsiBusinessUnitss();
                                                }, 1000);
                                            }
                                        }
                                    } else {
                                        $rootScope.isTrascError = true;
                                        FlashService.Error(result.response);
                                        $scope.continuesave = false;
                                    }
                                    
                                }).catch(function(error){
                                	$rootScope.isTrascError = true;
                             		var msg = appConstants.exceptionMessage;
                            		if(error.data.httpStatus){ 
                             		  msg=error.data.developerMessage; 
                             		}
                             		FlashService.Error(msg);
                             		$timeout(function () {
                             		  $rootScope.isTrascError=false;
                             		}, 2000);

                                });
            } else {
                OsiBusinessUnitsService
                        .updateOsiBusinessUnits($scope.OsiBusinessUnits)
                        .then(
                                function (result) {
                                    if(result.buId != undefined && result.buId != null) { 
                                	//if (result.response.indexOf("Error")==-1) {
                                        // $('#OsiBusinessUnits-model').modal('hide');
                                        // $scope.init();
                                        // $scope.successTextAlert = appConstants.successMessage;
                                        // $scope.showSuccessAlert = true;
                                        // $scope.clearSelectedRow();
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
                                                saveGrades = false;
                                            } else {
                                                $rootScope.isTrascError = true;
                                                FlashService.Success(appConstants.successMessage);
                                                $timeout(function () {
                                                    $scope.showSuccessAlert = false;
                                                    $rootScope.isTrascError=false;
                                                    $('#OsiBusinessUnits-model').modal('hide');
                                                    //$window.location.reload();
                                                    $scope.searchOsiBusinessUnitss();
                                                }, 1000);
                                            }
                                        } else {
                                            saveGrades = true;
                                        }
                                        if(saveGrades) {
                                            angular.forEach(vm.costingInfo, function(value, key) {
                                                if(value.buId == undefined || value.buId == null) {
                                                    value.buId = result.buId;
                                                }
                                            });
                                            console.log(vm.costingInfo);
                                            if(vm.saveCcGrades()){
                                                $timeout(function () {
                                                    $scope.showSuccessAlert = false;
                                                    $rootScope.isTrascError=false;
                                                    //$window.location.reload();
                                                    $scope.searchOsiBusinessUnitss();
                                                }, 1000);
                                            }
                                        } else {
                                            $rootScope.isTrascError=true;
                                            FlashService.Success(appConstants.successMessage);
                                             $timeout(function () {
                                              $('#OsiBusinessUnits-model').modal('hide');
                                               $rootScope.isTrascError=false;
                                               //$window.location.reload();
                                               $scope.searchOsiBusinessUnitss();
                                             },1000);
                                        }
                                    } else {
                                        $rootScope.isTrascError = true;
                                        FlashService.Error(result.response);
                                        $scope.continuesave = false;
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
    }
};



    $scope.init();


    // Cost Center Grades Mapping
    vm.costingInfo = [];
    function initController(buId) {
      vm.osiOrganizations = $localStorage.osiOrganizations;
      if(buId == undefined)
        buId = $scope.buId;
      OsiBusinessUnitsService.getAllBUGradesBybuId(buId).then(function (data) {
            console.log('after getting the response : ');
            console.log(data);
            vm.costingInfo = data;
            if(data.length != 0) {
            angular.forEach(data, function(val, key) {
              if(val.orgId)
                $scope.getAllGradesByOrgId(val.orgId, key);
            });
          } else {
             vm.costingInfo.push({buId:$scope.buId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct: null});
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
    /*$scope.openCostingModal = function(buId) {
      $scope.buId = buId;
      initController(buId);
      console.log('Business Unit Id: '+ buId);
      $('#costingModal').modal('show');
      console.log($localStorage.osiOrganizations);
    };*/
    $scope.addRow = function (costing) {
        if (costing.orgId != null && costing.gradeId != null && costing.costPerHour != null 
            && costing.costPerMonth != null && costing.internalCostOverheadPct != null)
        {
        	  vm.costingInfo.push({buId:$scope.buId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct: null});
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
            vm.costingInfo = [{orgId : null, gradeId : null, costPerMonth:null, costPerHour:null, internalCostOverheadPct:null, buId:$scope.buId}];
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
                $scope.isDuplicate = true;
        }
        else{
            $scope.wrongSelectedResp = false;
            $scope.isDuplicate = false;
        }
        $scope.selectedCosting = [];
        $scope.selectedCostingGrade = [];
    }
    vm.osiGrades = [];
    $scope.getAllGradesByOrgId = function (orgId, index) {
        if(orgId != undefined) {
            OsiBusinessUnitsService.getAllGradesByOrgId(orgId).then(function (data) {
                console.log('Graddess ..');
                console.log(data);
                vm.osiGradesHistory = data;
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
                    if(vm.costingInfo[index].internalCostOverheadPct == null)
                        vm.costingInfo[index].internalCostOverheadPct = values.interEmpOverheadPct;
            })
        } else{
            vm.costingInfo[index].internalCostOverheadPct = null;
        }
    }

    $scope.setValues = function(gradeId, index) {
      console.log(gradeId);
      angular.forEach(vm.osiGrades[index], function(val, key) {
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
                if(val.buId == undefined || val.buId == null)
                val.buId = $scope.buId;
                if(val.orgId != null && val.gradeId != null && val.costPerHour != null 
                    && val.costPerMonth != null && val.internalCostOverheadPct != null) {
                $scope.isValid = true;
                } else {
                $scope.isValid = false;
                }
            })
        } else {
            var val = vm.costingInfo[0];
            if(val.buId == undefined || val.buId == null)
                val.buId = $scope.buId;
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
        OsiBusinessUnitsService.saveCcGrades(vm.costingInfo).then(function (data) {
          vm.costingInfo = data;
          //$('#costingModal').modal('hide');

          $scope.init();
          $scope.clearSelectedRow();

          $rootScope.isTrascError=true;
          FlashService.Success(appConstants.successMessage);
           $timeout(function () {
            $('#OsiBusinessUnits-model').modal('hide');
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
        return true;
      } else {
        $rootScope.isTrascError=true;
        FlashService.Error("Please Fill Mandatory Fields");
         $timeout(function () {
           $rootScope.isTrascError=false;
         },3000);
         return false;
      }
    }
// BuGrades History
    $scope.closeCcGradeHistModal = function() {
        $('#CCGradeHistModal').modal('hide');
    }
    $scope.closeModal = function() {
        $('#CCGradeHistModal').modal('hide');
        $('#OsiBusinessUnits-model').modal('hide');
        $scope.clearSelectedRow();
    }
    $scope.getccGradesHistory = function(costing){
        console.log('history fetching..');
        if(costing.buId != undefined && costing.orgId != undefined && costing.gradeId != undefined) {
            OsiBusinessUnitsService.getBUGradesHistory(costing.buId, costing.orgId, costing.gradeId).then(function (data) {
                $scope.ccGradeHistList = data;
                console.log(vm.osiOrganizations);
                console.log(vm.osiGradesHistory);
                angular.forEach($scope.ccGradeHistList, function(grade, k){
                    angular.forEach(vm.osiOrganizations, function(org, key) {
                        if(org.orgId == grade.orgId) {
                            $scope.ccGradeHistList[k].orgName = org.orgName;
                        }
                    });
                    angular.forEach(vm.osiGradesHistory, function(gr, key) {
                        if(gr.gradeId == grade.gradeId) {
                            $scope.ccGradeHistList[k].gradeName = gr.gradeName;
                        }
                    });
                }) 
                $scope.locModelHeading = "Business Unit Costing History";
               
                $('#CCGradeHistModal').modal({backdrop: 'static', keyboard: false});
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
        }
    }

    function getOrganizations(){
        employeeBasicInfoService.getAllOrganizations().then(function (result) {
            $localStorage.osiOrganizations = result;
          });
    };
}
