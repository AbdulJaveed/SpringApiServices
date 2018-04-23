'use strict';
angular.module('myApp.OsiSubPracticeController', []).controller('OsiSubPracticeController',
        OsiSubPracticeController);
OsiSubPracticeController.$inject = ['$q', '$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'OsiSubPracticeService', 'FlashService', '$timeout','appConstants','employeeBasicInfoService'];
function OsiSubPracticeController($q, $scope, $rootScope, $window, $location, $http,
        $localStorage, OsiSubPracticeService, FlashService, $timeout,appConstants,employeeBasicInfoService) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.subPracticeList = "";
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
    $scope.editsubPracticeShortName=false;
    $scope.subPracticeList = [];
	$scope.OsiSubPractices = [];
    $scope.sort = function (keyname) {
        if ($scope.subPracticeList != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    }

$scope.editSelectedRowDetails = function(OsiSubPractices){
     editOsiSubPracticesDetails(OsiSubPractices);
     $scope.isSelectedRow = OsiSubPractices.subPracticeId;
     toggleButtons();
}
    $timeout(function () {
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        getOrganizations();
        var searchData = JSON.stringify({});
        /* -- Commented for stop initial loading list
    	OsiSubPracticeService.searchOsiSubPractices(searchData).then(function (data) {
            $scope.subPracticeList = data;
            $scope.sort('subPracticeShortName');
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
        $scope.isSelectedRow = item.subPracticeId;
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
            createOsiSubPractices();
            getOrganizations();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewOsiSubPracticesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editOsiSubPracticesDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

	 function viewOsiSubPracticesDetails(OsiSubPractices) {
	        $scope.headername = "View Sub Practice";
	        $rootScope.isTrascError = false;
	        OsiSubPracticeService.getOsiSubPractice(OsiSubPractices.subPracticeId).then(function (data) {
                $scope.OsiSubPractices = data;
                $scope.subPracticeId = OsiSubPractices.subPracticeId;
                initController(OsiSubPractices.subPracticeId);
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
			$scope.OsiSubPractices.subPracticeId = $scope.OsiSubPractices.subPracticeId;$scope.OsiSubPractices.subPracticeShortName = $scope.OsiSubPractices.subPracticeShortName;$scope.OsiSubPractices.subPractceLongName = $scope.OsiSubPractices.subPractceLongName;$scope.OsiSubPractices.active = $scope.OsiSubPractices.active;
	        $scope.isUpdatable=false;
	        $scope.iseditable = false;
	        $scope.iscodeeditable =true;
	       $('#OsiSubPractices-model').modal({show: true, backdrop: 'static'});
	    }
	 function editOsiSubPracticesDetails(OsiSubPractices) {
        getOrganizations();
        $scope.headername = "Edit Sub Practice";
        $rootScope.isTrascError = false;
        OsiSubPracticeService.getOsiSubPractice(OsiSubPractices.subPracticeId).then(function (data) {
            $scope.OsiSubPractices = data;
            $scope.editsubPracticeShortName=data.subPracticeShortName;

            $scope.subPracticeId = OsiSubPractices.subPracticeId;
            initController(OsiSubPractices.subPracticeId);
            $scope.OsiSubPractices.subPracticeId = $scope.OsiSubPractices.subPracticeId;$scope.OsiSubPractices.subPracticeShortName = $scope.OsiSubPractices.subPracticeShortName;$scope.OsiSubPractices.subPractceLongName = $scope.OsiSubPractices.subPractceLongName;$scope.OsiSubPractices.active = $scope.OsiSubPractices.active;
            $scope.isUpdatable=true;
            $scope.iseditable = true;
            $scope.iscodeeditable =false;
           $('#OsiSubPractices-model').modal({show: true, backdrop: 'static'});
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

	 function createOsiSubPractices() {
        $scope.headername = "Create Sub Practice";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.OsiSubPractices.subPracticeId = '';$scope.OsiSubPractices.subPracticeShortName = '';$scope.OsiSubPractices.subPractceLongName = '';$scope.OsiSubPractices.active = 0;
       $('#OsiSubPractices-model').modal({show: true, backdrop: 'static'});

       vm.costingInfo = [];
       vm.osiOrganizations = $localStorage.osiOrganizations;
       vm.costingInfo.push({subPracticeId:$scope.subPracticeId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct:null});
    };

    $scope.searchOsiSubPractices = function () {
		$scope.searchData = {subPracticeShortName:$scope.subPracticeShortName,subPractceLongName:$scope.subPractceLongName}; var searchData = JSON.stringify($scope.searchData);
		OsiSubPracticeService.searchOsiSubPractices (searchData).then(function (data) {
                $scope.subPracticeList = data;
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
        $scope.subPracticeShortName='';$scope.subPractceLongName='';
        $scope.clearSelectedRow();
        $scope.subPracticeList="";
    };

    function validateDuplicateOsiSubPractices(OsiSubPractices){
    	if($scope.headername=='Edit Sub Practice' && $scope.editsubPracticeShortName.toUpperCase()!=OsiSubPractices.subPracticeShortName.toUpperCase()){
    		for(var i=0;i<$scope.subPracticeList.length;i++){
        		if(OsiSubPractices.subPracticeShortName.toUpperCase()==$scope.subPracticeList[i].subPracticeShortName.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Sub Practice Short Name already exists.');
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}
    	if($scope.headername!='Edit Sub Practice'){
    		for(var i=0;i<$scope.subPracticeList.length;i++){
        		if(OsiSubPractices.subPracticeShortName.toUpperCase()==$scope.subPracticeList[i].subPracticeShortName.toUpperCase()){
    				$rootScope.isTrascError = true;
    				FlashService.Error('Sub Practice Short Name already exists.');
    				$scope.continuesave = false;
    				break;
    			}
    		}
    	}
    }

    $scope.saveOsiSubPractices = function (OsiSubPractices) {
        $scope.continuesave = true;
        $rootScope.isTrascError = false;
        if(!$scope.OsiSubPractices.subPracticeShortName){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter Sub Practice Short Name');
            $scope.continuesave = false;
        } else if(!$scope.OsiSubPractices.subPractceLongName) {
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter Sub Practice Long Name');
            $scope.continuesave = false;
        } else{
	        validateDuplicateOsiSubPractices(OsiSubPractices);
        }/*else if(!$scope.OsiSubPractices.active){
            $rootScope.isTrascError = true;
            FlashService.Error('Please Enter Active');
            $scope.continuesave = false;
        }*/

        if ($scope.continuesave) {
            if (!$scope.OsiSubPractices.subPracticeId) {

            	$scope.OsiSubPractices = {
                    	"id":$scope.OsiSubPractices.subPracticeId,"subPracticeShortName":$scope.OsiSubPractices.subPracticeShortName,"subPractceLongName":$scope.OsiSubPractices.subPractceLongName,"active":$scope.OsiSubPractices.active
                    };
                OsiSubPracticeService
                        .saveOsiSubPractice($scope.OsiSubPractices)
                        .then(
                                function (result) {
                                    if(result.subPracticeId != undefined && result.subPracticeId != null) {
                                	//if (result.response.indexOf("Error")==-1) {
                                        //$('#OsiSubPractices-model').modal('hide');
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
                                                    $scope.searchOsiSubPractices();
                                                }, 1000);
                                            }
                                        } else {
                                            saveGrades = true;
                                        }
                                        if(saveGrades) {
                                            angular.forEach(vm.costingInfo, function(value, key) {
                                                if(value.subPracticeId == undefined || value.subPracticeId == null || value.subPracticeId !== result.subPracticeId) {
                                                    value.subPracticeId = result.subPracticeId;
                                                }
                                            });
                                            console.log(vm.costingInfo);
                                            if(vm.saveCcGrades()){
                                                $timeout(function () {
                                                    $scope.showSuccessAlert = false;
                                                    $rootScope.isTrascError=false;
                                                    $('#OsiSubPractices-model').modal('hide');
                                                    //$window.location.reload();
                                                    $scope.searchOsiSubPractices();
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
                OsiSubPracticeService
                        .updateOsiSubPractice($scope.OsiSubPractices)
                        .then(
                                function (result) {
                                    if(result.subPracticeId != undefined && result.subPracticeId != null) { 
                                	//if (result.response.indexOf("Error")==-1) {
                                        // $('#OsiSubPractices-model').modal('hide');
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
                                                    $('#OsiSubPractices-model').modal('hide');
                                                    //$window.location.reload();
                                                    $scope.searchOsiSubPractices();
                                                }, 1000);
                                            }
                                        } else {
                                            saveGrades = true;
                                        }
                                        if(saveGrades) {
                                            angular.forEach(vm.costingInfo, function(value, key) {
                                                if(value.subPracticeId == undefined || value.subPracticeId == null) {
                                                    value.subPracticeId = result.subPracticeId;
                                                }
                                            });
                                            console.log(vm.costingInfo);
                                            if(vm.saveCcGrades()){
                                                $timeout(function () {
                                                    $scope.showSuccessAlert = false;
                                                    $rootScope.isTrascError=false;
                                                    //$window.location.reload();
                                                    $scope.searchOsiSubPractices();
                                                }, 1000);
                                            }
                                        } else {
                                            $rootScope.isTrascError=true;
                                            FlashService.Success(appConstants.successMessage);
                                             $timeout(function () {
                                              $('#OsiSubPractices-model').modal('hide');
                                               $rootScope.isTrascError=false;
                                               //$window.location.reload();
                                               $scope.searchOsiSubPractices();
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
    function initController(subPracticeId) {
      vm.osiOrganizations = $localStorage.osiOrganizations;
      if(subPracticeId == undefined)
        subPracticeId = $scope.subPracticeId;
      OsiSubPracticeService.getAllSubPracticeGradesBysubPracticeId(subPracticeId).then(function (data) {
            console.log('after getting the response : ');
            console.log(data);
            vm.costingInfo = data;
            if(data.length != 0) {
            angular.forEach(data, function(val, key) {
              if(val.orgId)
                $scope.getAllGradesByOrgId(val.orgId, key);
            });
          } else {
             vm.costingInfo.push({subPracticeId:$scope.subPracticeId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct: null});
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
    /*$scope.openCostingModal = function(subPracticeId) {
      $scope.subPracticeId = subPracticeId;
      initController(subPracticeId);
      console.log('Sub Practice Id: '+ subPracticeId);
      $('#costingModal').modal('show');
      console.log($localStorage.osiOrganizations);
    };*/
    $scope.addRow = function (costing) {
        if (costing.orgId != null && costing.gradeId != null && costing.costPerHour != null 
            && costing.costPerMonth != null && costing.internalCostOverheadPct != null)
        {
        	  vm.costingInfo.push({subPracticeId:$scope.subPracticeId,orgId:null,gradeId: null, costPerHour:null, costPerMonth: null, internalCostOverheadPct: null});
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
            vm.costingInfo = [{orgId : null, gradeId : null, costPerMonth:null, costPerHour:null, internalCostOverheadPct:null, subPracticeId:$scope.subPracticeId}];
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
            OsiSubPracticeService.getAllGradesByOrgId(orgId).then(function (data) {
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
                if(val.subPracticeId == undefined || val.subPracticeId == null)
                val.subPracticeId = $scope.subPracticeId;
                if(val.orgId != null && val.gradeId != null && val.costPerHour != null 
                    && val.costPerMonth != null && val.internalCostOverheadPct != null) {
                $scope.isValid = true;
                } else {
                $scope.isValid = false;
                }
            })
        } else {
            var val = vm.costingInfo[0];
            if(val.subPracticeId == undefined || val.subPracticeId == null)
                val.subPracticeId = $scope.subPracticeId;
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
        OsiSubPracticeService.saveCcGrades(vm.costingInfo).then(function (data) {
          vm.costingInfo = data;
          //$('#costingModal').modal('hide');

          $scope.init();
          $scope.clearSelectedRow();

          $rootScope.isTrascError=true;
          FlashService.Success(appConstants.successMessage);
           $timeout(function () {
            $('#OsiSubPractices-model').modal('hide');
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
// SubPracticeGrades History
    $scope.closeCcGradeHistModal = function() {
        $('#CCGradeHistModal').modal('hide');
    }
    $scope.closeModal = function() {
        $('#CCGradeHistModal').modal('hide');
        $('#OsiSubPractices-model').modal('hide');
        $scope.clearSelectedRow();
    }
    $scope.getccGradesHistory = function(costing){
        console.log('history fetching..');
        if(costing.subPracticeId != undefined && costing.orgId != undefined && costing.gradeId != undefined) {
            OsiSubPracticeService.getSubPracticesGradesHistory(costing.subPracticeId, costing.orgId, costing.gradeId).then(function (data) {
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
                $scope.locModelHeading = "Sub Practice Costing History";
               
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
