'use strict';
angular.module('myApp.osiResponsibilitiesController', [])
    .controller('osiResponsibilitiesController', ['$scope', '$rootScope', '$timeout', '$localStorage', 'osiResponsibilitiesService', 
                'MenuService', 'FlashService', 'CommonService', 'appConstants',
function($scope,  $rootScope, $timeout, $localStorage, osiResponcibilitiesService, MenuService, FlashService, CommonService, appConstants) {
    var vm = this;
    vm.osiResponcibilityObj = {};
    vm.myIndex = "";
    $scope.rowSize = 5;
    $scope.successTextAlert = "";
    $scope.showSuccessAlert = false;
    $scope.failureTextAlert = "";
    $scope.showFailureAlert = false;    
    $scope.isSelectedRow = null;
    $scope.selectedRowDetails = [];
    $scope.disable_Create = false;
    $scope.disable_Edit = true;
    $scope.disable_Delete = true;
    $scope.disable_View = true;
    $rootScope.isTrascError = false;
    $scope.todaysDate = new Date().toDateString();
    $scope.active=true;
    var dateError=0;
    $scope.searchResp = {
        respName: "",
        respDesc: ""
    };
    
    vm.initController = function() {
        vm.osiResponsibilitiesList = [];
        /* -- Commented for stop initial loading list
        osiResponcibilitiesService.getInitiallyResponsibility().then(function(data) {
            vm.osiResponsibilitiesList = data;
        	 angular.forEach( vm.osiResponsibilitiesList,function(value, key) {	
          		if(value.active == 1){
          			value.active="Active";
          		}else{
          			value.active="Inactive";
          		}
          	});
        }).catch(function(error){
        	var msg = appConstants.exceptionMessage;
	   		  if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  }
           
        	showError(msg);
        });
        */
        osiResponcibilitiesService.getReportGroups().then(function(data) {
            vm.osiReportGroupsList = data;
           //console.log(angular.fromJson(vm.osiReportGroupsList));
        }).catch(function(error){
        	var msg = appConstants.exceptionMessage;
	   		  if(error.httpStatus){ 
	   			  msg=error.errorMessage; 
	   		  }
        	//showError(msg);
        	
        });
        MenuService.getAllMenus().then(function(data) {
            vm.osiMenus = data;
            vm.availableMenuNames=[];
            angular.forEach(vm.osiMenus, function(value){
            	vm.availableMenuNames.push(value.menuName);
            });
            vm.allAvailableMenuNames= angular.copy(vm.availableMenuNames);
            vm.selectedMenuNames=[];
        }).catch(function(error){
        	var msg = appConstants.exceptionMessage;
	   		  if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  }
        	showError(msg);
        });
        
        osiResponcibilitiesService.getAllOsiResponcibilities().then(function(data){
            vm.allResponsibilities = data;
        }).catch(function(error){
        	var msg = appConstants.exceptionMessage;
	   		  if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  }
      	showError(msg);
        });
    };
    
    function showError(msg){
    	$scope.failureTextAlert = msg;
        $scope.showFailureAlert = true;
        $timeout(function () {
            $scope.showSuccessAlert = false;
            $scope.showFailureAlert = false;
        }, 5000);
    }
    $scope.sortKey = 'respName'; 
    $scope.sort = function (keyname) {
        if (vm.osiResponsibilitiesList !== null) {
            // set the sortKey to the param passed
            $scope.sortKey = keyname;
            // if true make it false and vice versa
            $scope.reverse = !$scope.reverse;
        }
    }
    
    $timeout(function () {
        // getting the available operations assigned for the logged in user
        $scope.availOperations = $localStorage.availOperations;
    }, 400);
    
    $scope.operationsGenericFunction = function (doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            createResponsibility();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewResponsibility($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editResponsibility($scope.selectedRowDetails, vm.myIndex);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

    
    $scope.selectRow = function (responsibility) {
    	
        // for checking single row selection
        $scope.isSelectedRow = responsibility.id;
        toggleButtons();
        // adding selected row details
        if (responsibility != undefined) {
            $scope.selectedRowDetails = {
                id: responsibility.id,
                respName: responsibility.respName,
                osiMenuResp: responsibility.osiMenuResp,
                description: responsibility.description,
                startDate: responsibility.startDate,
                endDate: responsibility.endDate,
                active : responsibility.active,
                /*osiMenus : {
                   id : responsibility.osiMenus.id,
                   menuName : responsibility.osiMenus.menuName,
                   description : responsibility.osiMenus.description,
                   active : responsibility.osiMenus.active
                }*/
                
            };
//            vm.selectedMenuNames = [];
//            vm.menurespData = [];
//            vm.selectedMenusObj = [];
//        	 vm.menurespData = responsibility.osiMenuResp
//        	angular.forEach(vm.menurespData, function(menuResp) {
//            	vm.selectedMenusObj.push(menuResp.osiMenus);
//            });
//        	 angular.forEach(vm.selectedMenusObj, function(menuObj) {
//             	vm.selectedMenuNames.push(menuObj.menuName);
//             });
//        	 vm.availableMenuNames = _.difference(vm.availableMenuNames,vm.selectedMenuNames);
        }
    }

    $scope.clearSelectedRow = function () {
        // clearing row selection
        $scope.isSelectedRow = null;
        toggleButtons();
    }

    function toggleButtons() {
        // edit, view and delete button toggle
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
    
    vm.isDuplicateName = function(name) {
        vm.duplicateCount = 0;
        vm.duplicate = false;
        angular.forEach(vm.osiResponsibilitiesList, function(responsibility) {
            if (name == responsibility.respName) {
                vm.duplicate = true;
                vm.duplicateCount++;
            }
        });
        return vm.duplicate;
    };

    $scope.searchResponsibilities = function() {
    	if($scope.searchResp.respName == ""){
            osiResponcibilitiesService.getAllOsiResponcibilities().then(function(data) {
                vm.osiResponsibilitiesList = data;
                //console.log("vm.osiResponsibilitiesList :"+vm.osiResponsibilitiesList.length);
                angular.forEach( vm.osiResponsibilitiesList,function(value, key) {
                     if(value.active == 1){
                             value.active="Active";
                     }else{
                             value.active="Inactive";
                     }
                });
            }).catch(function(error){
            	var msg = appConstants.exceptionMessage;
  	   		  if(error.data.httpStatus){ 
  	   			  msg=error.data.errorMessage; 
  	   		  }
          	showError(msg);
            });
    	}
    	else{if ($scope.searchResp.respName != "") {
            osiResponcibilitiesService.searchResponsibility($scope.searchResp).then(function(response) {
                vm.osiResponsibilitiesList = response;
                angular.forEach( vm.osiResponsibilitiesList,function(value, key) {	
                    var edate = new Date(value.endDate);
                    var dateConst = new Date(appConstants.endDate);
                    if(edate.getTime() == dateConst.getTime())
                        value.endDate = '';
                     if(value.active == 1){
                             value.active="Active";
                     }else{
                             value.active="Inactive";
                     }
                });
            }).catch(function(error){
            	var msg = appConstants.exceptionMessage;
  	   		  if(error.data.httpStatus){ 
  	   			  msg=error.data.errorMessage; 
  	   		  }
          	showError(msg);
            });
        } 
    	}
    };

    $scope.checkEmpty = function() {
        if ($scope.searchResp.respName == "" && $scope.searchResp.respDesc == "") {
            vm.initController();
        }
    };
    Date.prototype.isValid = function () {
        // An invalid date object returns NaN for getTime() and NaN is the only
        // object not strictly equal to itself.
        return this.getTime() === this.getTime();
    }; 
  
    
    vm.createOsiResponcibility = function() {
    	vm.checkDuplicate(vm.osiResponcibilityObj);
    	var startDate= new Date(vm.osiResponcibilityObj.startDate);
    	var endDate= new Date(vm.osiResponcibilityObj.endDate);
        if($scope.active){
            vm.osiResponcibilityObj.active=1;
        }else{
            vm.osiResponcibilityObj.active=0;
        }
         if(vm.osiResponcibilityObj.reportGrpId){
            vm.osiResponcibilityObj.reportGrpId = angular.fromJson(vm.osiResponcibilityObj.reportGrpId).rptGrpId;
        }
     vm.osiResponcibilityObj.osiMenus = angular.fromJson(vm.osiResponcibilityObj.osiMenus);
        
        if(vm.osiResponcibilityObj.respName == undefined || vm.osiResponcibilityObj.respName == ""){
        	$rootScope.isTrascError = true;
                FlashService.Error("Please Enter Responsibility Name..!");
                $timeout(function () {
            	$rootScope.isTrascError=false;
                }, 3000);
        }else if (vm.osiResponcibilityObj.startDate== undefined || vm.osiResponcibilityObj.startDate=="") {
                $rootScope.isTrascError = true;
		FlashService.Error("Please Enter start Date");
		$timeout(function () {
			$rootScope.isTrascError=false;
		}, 3000);
        }else if (vm.duplicateRespError) {
                $rootScope.isTrascError = true;
                FlashService.Error("Duplicate Responsibility Name, Please enter different Name..!");
                $timeout(function () {
            	$rootScope.isTrascError=false;
                }, 3000);
                $scope.todaysDate = new Date().toDateString();
        }else if (dateError ==1) {
                $rootScope.isTrascError = true;
                vm.osiResponcibilityObj.endDate="";
                FlashService.Error("End Date should be greater than Start Date");
                $timeout(function () {
            	$rootScope.isTrascError=false;
                }, 3000);
                $scope.todaysDate = new Date().toDateString();
        }else if(vm.selectedMenuNames.length == 0) {
        	 $rootScope.isTrascError = true;
             FlashService.Error("Please select Menu");
             $timeout(function () {
            	 $rootScope.isTrascError=false;
             }, 3000);
        	}else{
        		vm.osiResponcibilityObj.osiMenuResp=[];
        		angular.forEach(vm.selectedMenuNames, function(value){
        			var item = _.find( vm.osiMenus, function(item) {
        				return value == item.menuName; 
        			});
        			if(item != undefined){
        				var osiMenuRespObj={};
        				osiMenuRespObj.osiMenus=item;
        				vm.osiResponcibilityObj.osiMenuResp.push(osiMenuRespObj);
        			}
        		})
        		delete vm.osiResponcibilityObj.availableOsiMenus;
        		console.log(JSON.stringify(vm.osiResponcibilityObj));
        		$rootScope.isTrascError = false;
                if((vm.osiResponcibilityObj.startDate !=undefined) && (vm.osiResponcibilityObj.endDate ==undefined || new Date(vm.osiResponcibilityObj.endDate).isValid() == false)){
                	vm.osiResponcibilityObj.endDate= new Date(appConstants.endDate);
                }
                vm.osiResponcibilityObj.startDate = new Date(vm.osiResponcibilityObj.startDate);
                vm.osiResponcibilityObj.endDate = new Date(vm.osiResponcibilityObj.endDate);
                
                console.log("save resp---"+angular.toJson(vm.osiResponcibilityObj));
              
                osiResponcibilitiesService.saveOsiResponcibilities(vm.osiResponcibilityObj).then(function(data) {
                    if (data.httpStatus == 200) {
                        $('#rsp_modal').modal('hide');
                        $scope.successTextAlert = appConstants.successMessage;
                        $scope.showSuccessAlert = true;
                        vm.osiResponcibilityObj = {};
                        //vm.initController();
                        $scope.searchResponsibilities();
                    } else {
                        $('#rsp_modal').modal('hide');
                        $scope.failureTextAlert = "Failed To Create Responsibility";
                        $scope.showFailureAlert = true;
                    }
                    $timeout(function () {
                        $scope.showSuccessAlert = false;
                        $scope.showFailureAlert = false;
                    }, 5000);
                }).catch(function(error){
                	$('#rsp_modal').modal('hide');
                	var msg = appConstants.exceptionMessage;
      	   		  if(error.data.httpStatus){ 
      	   			  msg=error.data.errorMessage; 
      	   		  }
              	showError(msg);
                });
            }
        
    };

    vm.updateOsiResponcibility = function(osiResponcibilityObj) {
    	vm.checkDuplicate(osiResponcibilityObj);
        vm.osiResponsibilitiesList.splice(vm.index, 1);
        vm.osiResponcibilityObj.osiMenuResp=[];
		angular.forEach(vm.selectedMenuNames, function(value){
			var item = _.find( vm.osiMenus, function(item) {
				return value == item.menuName; 
			});
			if(item != undefined){
				var osiMenuRespObj={};
				osiMenuRespObj.osiMenus=item;
				vm.osiResponcibilityObj.osiMenuResp.push(osiMenuRespObj);
			}
		})
		delete vm.osiResponcibilityObj.availableOsiMenus;
        		console.log("updateing resp date---"+JSON.stringify(vm.osiResponcibilityObj));
        if($scope.active  == true){
            osiResponcibilityObj.active=1;
        }else{
            osiResponcibilityObj.active=0;
        }
        if(vm.osiResponcibilityObj.reportGrpId){
            vm.osiResponcibilityObj.reportGrpId = angular.fromJson(vm.osiResponcibilityObj.reportGrpId).rptGrpId;
        }
        if(vm.osiResponcibilityObj.respName == undefined || vm.osiResponcibilityObj.respName == ""){
        	$rootScope.isTrascError = true;
                FlashService.Error("Please Enter Responsibility Name..!");
                $timeout(function () {
            	$rootScope.isTrascError=false;
                }, 3000);
        }else if (vm.osiResponcibilityObj.startDate== undefined) {
                $rootScope.isTrascError = true;
		FlashService.Error("Please Enter valid Start Date");
		$timeout(function () {
			$rootScope.isTrascError=false;
		}, 3000);
        }else if (vm.osiResponcibilityObj.osiMenuResp == undefined) {
		$rootScope.isTrascError = true;
                FlashService.Error("Please select Menu");
		$timeout(function () {
			$rootScope.isTrascError=false;
		}, 3000);
        }else if (dateError == 1) {
                $rootScope.isTrascError = true;
                FlashService.Error("Invalid Date");
                $timeout(function() {
                        $rootScope.isTrascError = false;
                }, 3000);
			} else if(vm.duplicateRespError){
				$rootScope.isTrascError = true;
		        FlashService.Error("Duplicate Responsibility Name, Please enter different Name..!");
		        $timeout(function () {
		        	$rootScope.isTrascError=false;
		        }, 3000);
			}else if ((new Date(vm.osiResponcibilityObj.startDate).getTime() >= new Date(vm.osiResponcibilityObj.endDate).getTime())) {
                $rootScope.isTrascError = true;
                vm.osiResponcibilityObj.endDate="";
                FlashService.Error("End Date should be greater than Start Date");
                $timeout(function () {
                	  $rootScope.isTrascError= false;
                }, 3000);
                //responsibility.startDate = vm.formatDate(new Date(responsibility.startDate));
                vm.osiResponcibilityObj.startDate = CommonService.getDBCompatibleDate(new Date(vm.osiResponcibilityObj.startDate));
                vm.osiResponcibilityObj.endDate = CommonService.getDBCompatibleDate(new Date(vm.osiResponcibilityObj.endDate));
                $scope.todaysDate = new Date().toDateString();
            } else if(vm.selectedMenuNames.length == 0) {
        	 $rootScope.isTrascError = true;
             FlashService.Error("Please select Menu");
             $timeout(function () {
            	 $rootScope.isTrascError=false;
             }, 3000);
        	}
            else {
                if((vm.osiResponcibilityObj.startDate !=undefined) && (vm.osiResponcibilityObj.endDate ==undefined || new Date(vm.osiResponcibilityObj.endDate).isValid() == false)){
                	vm.osiResponcibilityObj.endDate= new Date(appConstants.endDate);
                }
                //osiResponcibilityObj.startDate = new Date(osiResponcibilityObj.startDate);
                //osiResponcibilityObj.endDate = new Date(osiResponcibilityObj.endDate);
                
                osiResponcibilitiesService.updateOsiResponcibilities(osiResponcibilityObj).then(function(data) {
                    if (data.httpStatus == 200) { 
                        $('#rsp_modal').modal('hide');
                        $scope.successTextAlert = appConstants.successMessage;
                        $scope.showSuccessAlert = true;
                        vm.osiResponcibilityObj = {};
                        //vm.initController();
                        $scope.searchResponsibilities();
                    } else {
                             $rootScope.isTrascError = true;
                       FlashService.Error("Responsibility Name Already Exist");
                       $timeout(function () {
                             $rootScope.isTrascError = false;
                       }, 5000);
    //                    $('#rsp_modal').modal('hide');
                        $scope.failureTextAlert = data.errorMessage;
                        $scope.showFailureAlert = true;
                    }
                    $timeout(function () {
                        $scope.showSuccessAlert = false;
                        $scope.showFailureAlert = false;
                    }, 5000);
                }).catch(function(error){
                	$rootScope.isTrascError = true;
                	var msg = appConstants.exceptionMessage;
        	   		  if(error.data.httpStatus){ 
        	   			  msg=error.data.errorMessage; 
        	   		  }
                	FlashService.Error(msg);
                    $timeout(function () {
                          $rootScope.isTrascError = false;
                    }, 5000);
                });
        }
           $scope.active=true;
    };

    vm.showActiveConfirmationPopup = function() {
    	if(!$scope.active){
    		$('#responsibility-delete-model').modal({backdrop: 'static', keyboard: false}); 
    	}
        
    }
    
//    vm.deleteOsiResponsibilities = function(osiResponcibilityObj) {
      vm.deleteResponsibility = function(osiResponcibilityObj) {
        vm.osiResponcibilityObj.osiMenus = angular.fromJson(vm.osiResponcibilityObj.osiMenus);
        osiResponcibilitiesService.deleteOsiResponcibilities(osiResponcibilityObj.id).then(function(data) {
            if (data.httpStatus == 200) {
                $('#responsibility-delete-model').modal('hide');
                $scope.successTextAlert = data.message;
                $scope.showSuccessAlert = true;
                vm.osiResponcibilityObj = {};
                vm.initController();
            } else {           
                $('#responsibility-delete-model').modal('hide');
                $scope.failureTextAlert = data.errorMessage;
                $scope.showFailureAlert = true;
            }
            $timeout(function () {
                $scope.showSuccessAlert = false;
                $scope.showFailureAlert = false;
            }, 5000);
        }).catch(function(error){
        	var msg = appConstants.exceptionMessage;
	   		  if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  }
        	showError(msg);
        });
    };

    vm.clear = function() {
        vm.osiResponcibilityObj = {};
        vm.selectedMenuNames = [];
        vm.availableMenuNames = vm.allAvailableMenuNames;
    };

    vm.openModal = function() {
        $('#rsp_modal').modal({backdrop: 'static', keyboard: false}); 
    };

    $scope.editSelectedRowDetails = function (responsibility) {
        var ops = $scope.availOperations;
        var found = false;
        for (var i = 0; i < ops.length; i++) {
            if (ops[i].name == 'Edit') {
                found = true;
                break;
            }
        }
        if (found) {
            editResponsibility(responsibility, vm.myIndex);
            $scope.isSelectedRow = responsibility.id;
            toggleButtons();
        }

        // check for Default End date 
        var dateConst = new Date(appConstants.endDate);
        dateConst.setHours(23);
        dateConst.setMinutes(59);
        dateConst.setSeconds(59);
        var edate = new Date(responsibility.endDate);
        if(edate.getTime() == dateConst.getTime())
            vm.osiResponcibilityObj.endDate = "";
    }
//    vm.editOsiResponsibilities = function(responsibility, indx) {


      vm.formatDate = function(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    }  
    
    vm.setMinDate = function(date) {
        vm.startDateOne = new Date(date);
        vm.startDateOne.setDate(vm.startDateOne.getDate() - 1);
        vm.startDateOne = vm.formatDate(new Date(vm.startDateOne));
    } 
      function editResponsibility(responsibility1, indx) {
          var responsibility = angular.copy(responsibility1);
        vm.index = indx;
        $scope.active= true;
        vm.edit = true;
        vm.view = false;
        $rootScope.flash = false;
        $scope.rspModelHeading = "Edit";
        vm.activeField= true;
        // responsibility.startDate = CommonService.getDateInPhillipinesFormat(new Date(responsibility.startDate));
        // responsibility.endDate = CommonService.getDateInPhillipinesFormat(new Date(responsibility.endDate));
        responsibility.startDate = CommonService.getDBCompatibleDate(new Date(responsibility.startDate));
        responsibility.endDate = CommonService.getDBCompatibleDate(new Date(responsibility.endDate));
        
        vm.startDateOne = new Date(responsibility.startDate);
        vm.startDateOne.setDate(vm.startDateOne.getDate() - 1);
        vm.startDateOne = vm.formatDate(new Date(vm.startDateOne));
        
        vm.osiResponcibilityObj = responsibility;
        vm.selectedMenuNames = [];
        vm.menurespData = [];
        vm.selectedMenusObj = [];
        vm.menurespData = responsibility.osiMenuResp
        
        angular.forEach(vm.menurespData, function(menuResp) {
            vm.selectedMenusObj.push(menuResp.osiMenus);
        });
        angular.forEach(vm.selectedMenusObj, function(menuObj) {
            vm.selectedMenuNames.push(menuObj.menuName);
        });
        
        vm.availableMenuNames = _.difference(vm.availableMenuNames,vm.selectedMenuNames);
       
        vm.openModal();
    };

//    vm.createOsiResponsibilities = function() {
      function createResponsibility() {
        $scope.active=true;
        vm.edit = false;
        vm.view = false;
        vm.activeField= false
        $rootScope.flash = false;
        $scope.rspModelHeading = "Create";
        vm.osiResponcibilityObj = {};
        vm.osiResponcibilityObj.startDate = "";
        vm.osiResponcibilityObj.endDate =  "";
        vm.openModal();
    };

//    vm.viewOsiResponsibilities = function(responsibility) {
      function viewResponsibility(responsibility) {
    	  $scope.active= true;
    	  vm.activeField= true;
        vm.view = true;
        vm.edit = false;
        $rootScope.flash = false;
        $scope.rspModelHeading = "View";
        responsibility.startDate = CommonService.getDateInPhillipinesFormat(new Date(responsibility.startDate));
        responsibility.endDate = CommonService.getDateInPhillipinesFormat(new Date(responsibility.endDate));
        vm.osiResponcibilityObj = responsibility;
        //console.log("vm.osiResponcibilityObj :"+JSON.stringify(vm.osiResponcibilityObj));
        
        vm.selectedMenuNames = [];
        vm.menurespData = [];
        vm.selectedMenusObj = [];
        vm.menurespData = responsibility.osiMenuResp
        
        angular.forEach(vm.menurespData, function(menuResp) {
            vm.selectedMenusObj.push(menuResp.osiMenus);
        });
        angular.forEach(vm.selectedMenusObj, function(menuObj) {
            vm.selectedMenuNames.push(menuObj.menuName);
        });
        
        vm.availableMenuNames = _.difference(vm.availableMenuNames,vm.selectedMenuNames);
//        if(vm.osiResponcibilityObj.active==1){
//            $scope.active=true;
//        }else{
//            $scope.active=false;
//        }
        vm.openModal();
    };
    
    vm.cancelDeActive = function (){
        $scope.active = true;
    };
    $scope.clearSearch = function () {
        $scope.searchResp.respName = "";
        $scope.searchResp.respDesc = "";
        $scope.clearSelectedRow();
        vm.initController();
    };
    
    /*$scope.validateQty = function (event) {
    var key = window.event ? event.keyCode : event.which;

    if (event.keyCode == 8 || event.keyCode == 46 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 173) {
        return true;
    }
    else if ( key < 48 || key > 57 ) {
    return false;
    }
    else return true;
    };*/
    vm.checkDate = function(osiResponcibilityObj) {
        if (osiResponcibilityObj.startDate == "") {
            vm.osiResponcibilityObj.endDate = "";
            //vm.functionOperations[index].opUrl = "";
        }

        if (osiResponcibilityObj.startDate == undefined) {
                startDateError = 1;
                $rootScope.isTrascError = true;
                console.log(osiResponcibilityObj.startDate);
                FlashService.Error("Invalid Start Date");
                $timeout(function() {
                        $rootScope.isTrascError = false;
                }, 3000);
                vm.osiResponcibilityObj.startDate = "";
        } else {
                startDateError = 0;
                $rootScope.isTrascError = false;
                vm.compareDate(osiResponcibilityObj);
        }
        if (new Date(osiResponcibilityObj.startDate).setHours(0,0,0,0) < new Date().setHours(0,0,0,0)) {
                $rootScope.isTrascError = true;
                FlashService.Error("Start date cannot be less than today's date");
                $timeout(function() {
                        $rootScope.isTrascError = false;
                }, 3000);
                vm.osiResponcibilityObj.startDate = "";
        }
}
    // vm.checkDate= function(osiResponcibilityObj){
    // 	 $rootScope.isTrascError = false;
    // 	if (osiResponcibilityObj.startDate === undefined) {
    // 		dateError = 1;
    // 		$rootScope.isTrascError = true;
    //             FlashService.Error("Please Enter valid Start Date");
    //             $timeout(function() {
    //                 $rootScope.isTrascError = false;
    //             }, 3000);
    //         vm.osiResponcibilityObj.startDate = "";
    // 	} else {
    // 		dateError = 0;
    // 		$rootScope.isTrascError = false;
    // 		vm.compareDate(osiResponcibilityObj);
    // 		/*if (osiResponcibilityObj.endDate != null
    // 				&& (new Date(osiResponcibilityObj.startDate)
    // 						.getTime() >= new Date(
    // 								osiResponcibilityObj.endDate).getTime())) {
    // 			 $rootScope.isTrascError = true;
    //                     FlashService.Error("Start date should be less than end date");
    // 			$timeout(function() {
    // 				$rootScope.isTrascError = false;
    // 			}, 3000);
    // 			vm.osiResponcibilityObj.startDate = "";
    // 			//vm.osiResponcibilityObj.endDate = "";
    // 		}*/
    // 	}
    // 	if (new Date(vm.osiResponcibilityObj.startDate).setHours(0,0,0,0) < new Date().setHours(0,0,0,0)) {
	// 		$rootScope.isTrascError = true;
	// 		FlashService.Error("Start date cannot be less than today's date");
	// 		$timeout(function() {
	// 			$rootScope.isTrascError = false;
	// 		}, 3000);
	// 		vm.osiResponcibilityObj.startDate = "";
	// 	}
    // 	//vm.compareDate(vm.osiResponcibilityObj);
    // 	vm.setMinDate(vm.osiResponcibilityObj.startDate); 
    // }
    vm.checkEndDate = function(osiResponcibilityObj) {
    	if (osiResponcibilityObj.endDate === undefined) {
    		//dateError = 1;
    		$rootScope.isTrascError = true;
                FlashService.Error("Please Enter valid End Date");
                $timeout(function() {
                    $rootScope.isTrascError = false;
                }, 3000);
            vm.osiResponcibilityObj.endDate = "";
    	}
    	vm.compareDate(vm.osiResponcibilityObj);
    	/*if (vm.osiResponcibilityObj.endDate != null
				&& (new Date(vm.osiResponcibilityObj.startDate).getTime() >= new Date(vm.osiResponcibilityObj.endDate).getTime())) {
			$rootScope.isTrascError = true;
			FlashService.Error("End date should be greater than start date");
			$timeout(function() {
				$rootScope.isTrascError = false;
			}, 3000);
			vm.osiResponcibilityObj.endDate = "";
		}*/
    	
	}
    vm.compareDate = function(osiResponcibilityObj) {
    	if (vm.osiResponcibilityObj.endDate != null
				&& ((new Date(vm.osiResponcibilityObj.startDate).getTime()) >= (new Date(vm.osiResponcibilityObj.endDate).getTime()))) {
			 $rootScope.isTrascError = true;
            FlashService.Error("End date should be greater than start date");
			$timeout(function() {
				$rootScope.isTrascError = false;
			}, 3000);
			//vm.osiResponcibilityObj.startDate = "";
			vm.osiResponcibilityObj.endDate = "";
		}
    }
    vm.selectAllMenus = function() {
    	vm.selectedMenuNames = _.union(vm.selectedMenuNames, vm.availableMenuNames);
		vm.availableMenuNames = [];
	};
	
	vm.selectMenu = function(menu) {
		if(!angular.isUndefined(menu)) {
			vm.availableMenuNames = _.difference(vm.availableMenuNames,menu);
			vm.selectedMenuNames = _.union(vm.selectedMenuNames,menu);
		}
	};
	
	vm.unselectAllMenus = function() {
		vm.availableMenuNames = _.union(vm.selectedMenuNames, vm.availableMenuNames);
		vm.selectedMenuNames = [];
	};
	
	vm.unselectMenu = function(menu) {
		if(!angular.isUndefined(menu)) {
			vm.selectedMenuNames = _.difference(vm.selectedMenuNames,menu);
			vm.availableMenuNames = _.union(vm.availableMenuNames,menu);
		}
	};
	vm.checkDuplicate= function(respObject){
		vm.allOsiResponsibilities= angular.copy(vm.allResponsibilities);
		angular.forEach(vm.allOsiResponsibilities, function(value, key){
			if(value.id == respObject.id){
				vm.allOsiResponsibilities.splice(key,1);
			}
		})
		var responsibilityName= respObject.respName;
		vm.duplicateResp= false;
		if(vm.allOsiResponsibilities != undefined){
			angular.forEach(vm.allOsiResponsibilities, function(value,key){
				if(value.respName == responsibilityName){
					vm.duplicateResp= true;
				}
			});
		}
		if(vm.duplicateResp){
			vm.duplicateRespError= true;
		}else{
			vm.duplicateRespError=false;
			}
	}
	
    vm.initController();
    //$scope.sort('respName');
}
  
]);