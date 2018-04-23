'use strict';
angular.module('myApp.FunctionController', []).controller('FunctionController',
        FunctionController);
FunctionController.$inject = ['$scope', '$rootScope', '$window', '$location',
    '$timeout', '$filter', '$localStorage', 'FlashService', 'FunctionService', 'appConstants'];
function FunctionController($scope, $rootScope, $window, $location, $timeout, $filter,
        $localStorage, FlashService, FunctionService, appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.rowSize = 10;
    vm.operationArray = [];
    var operation;
    vm.functions = [];
    vm.funcOPerations = [];
    vm.selectedFuncOPerations = [];
    vm.functionOperations = {};
    vm.functionOperations.startDate = new Date();
    $scope.successTextAlert = "";
    $scope.showSuccessAlert = false;
    $scope.showSuccessAlert1 = false;
    $scope.isSelectedRow = null;
    $scope.selectedRowDetails = [];
    $scope.disable_Create = false;
    $scope.disable_Edit = true;
    $scope.disable_Delete = true;
    $scope.disable_View = true;
    vm.isButtionDisable = true;
    vm.isButtionDisableForName = true;
    vm.functionDetails={};
    vm.editAndView= true;
    vm.createActive= false;
    vm.editActive= false;
    $scope.activeEdit=true;
    $scope.activeCreate=true;
    var startDateError=0;
    var endDateError=0;
	var urlError=0;
    $rootScope.go = function(path) {
        $location.url(path);
    }
	var validationError=0;
    var dateError=0;
    $scope.sortKey = 'funcName'; 
    $scope.sort = function(keyname) {
        if (vm.functions != null) {
            // set the sortKey to the param passed
            $scope.sortKey = keyname;
            // if true make it false and vice versa
            $scope.reverse = !$scope.reverse;
        }
    }

    $timeout(function() {
        // getting the available operations assigned for the logged in user
        $scope.availOperations = $localStorage.availOperations;
    }, 400);
    
    $scope.todaysDate = $filter('date')(new Date(), 'dd-MMM-yyyy');
    //$scope.todaysDate = new Date().toDateString();
    function loadAllFunctions() {
        /*  -- Commented for stop initial loading list
        FunctionService.getFunctionsListInitially().then(function(data) {
        	angular.forEach(data,function(value, key) {	
        		if(value.active == 1){
        			value.active="Active";
        		}else{
        			value.active="Inactive";
        		}
        	});
            vm.functions = data;
        }).catch(function(){
        	showError(appConstants.exceptionMessage);
        });
        */
        FunctionService.getAllOpetations().then(function(data) {
            vm.operations = data;
        }).catch(function(){
        	showError(appConstants.exceptionMessage);
        });
    }
    
    function showError(msg){
    	$scope.failureTextAlert = msg;
        $scope.showFailureAlert = true;
        $timeout(function () {
            $scope.showSuccessAlert = false;
            $scope.showFailureAlert = false;
        }, 5000);
    }
    
    $scope.operationsGenericFunction = function(doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            createFunction();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewFunction($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editFunction($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

    $scope.selectRow = function(item) {
        // for checking single row selection
        $scope.isSelectedRow = item.id;
        toggleButtons();
        // adding selected row details
        if (item != undefined) {
            $scope.selectedRowDetails = {
                id: item.id,
                funcName: item.funcName,
                funcType: item.funcType,
                funcValue: item.funcValue,
                parameters: item.parameters,
                active: item.active
            };
        }
    }

    $scope.clearSelectedRow = function() {
        $scope.isSelectedRow = null;
        toggleButtons();
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

      function createFunction() {
        $scope.funModelHeading = "Create";
        $scope.viewClearButton = true;
        vm.functionDetails = {};
        vm.funcOperations = {};
        $scope.iseditable = true;
        $scope.lookupid = null;
        $scope.valuedata = [];
        vm.showSave = true;
        vm.showUpdate = false;
        vm.createActive= true;
        vm.editActive= false;
        vm.functionOperations = vm.operations;
        for(var i = 0; i < vm.operations.length;  i++) {
        	//vm.functionOperations[i].startDate = $filter('date')(new Date(),'dd-MMM-yyyy');
        	//vm.functionOperations[i].endDate = $filter('date')(new Date(),'dd-MMM-yyyy');	
                vm.functionOperations[i].startDate = "";
                vm.functionOperations[i].endDate = "";
        }
        $('#function-model').modal({backdrop: 'static', keyboard: false});
    }

   vm.save = function() {
	 
		dateError = 0;
		validateFuncOperation();
		isInvalidDate();
		if($scope.activeCreate == true){
                    vm.functionDetails.active = 1;
		}else{
                    vm.functionDetails.active = 0;
		}
		if (vm.functionDetails.funcName == undefined || vm.functionDetails.funcName == "") {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Please Enter Function Name");
                    $timeout(function() {
                            $rootScope.isTrascError = false;
                    }, 2000);
                }else if(vm.functionDetails.funcType == undefined || vm.functionDetails.funcType == ""){

                        $rootScope.isTrascError = true;
                        FlashService.Error("Please Enter Function Type");
                        $timeout(function() {
                                $rootScope.isTrascError = false;
                        }, 2000);
                } else if(vm.functionDetails.funcValue == undefined || vm.functionDetails.funcValue == ""){

                        $rootScope.isTrascError = true;
                        FlashService.Error("Please Enter Function Value");
                        $timeout(function() {
                                $rootScope.isTrascError = false;
                        }, 2000);
                }else if(validationError == 0){

                        $rootScope.isTrascError = true;
                        FlashService.Error("Please Enter Function Operation-Url and Operation-Start Date");
                        $timeout(function() {
                                $rootScope.isTrascError = false;
                        }, 2000);
                } else {
			if(urlError==1){
				$rootScope.isTrascError = true;
				FlashService.Error("Please enter proper URL");
				$timeout(function() {
					$rootScope.isTrascError = false;
				}, 3000);
			
			} else {
				if (startDateError == 1) {
					$rootScope.isTrascError = true;
//					FlashService.Error("Invalid Date");
					$timeout(function() {
						$rootScope.isTrascError = false;
					}, 3000);
				}else{
				vm.operationArray = [];
				vm.funOperation = [];
				prepareDataToSave();
				FunctionService
						.saveFunction(vm.functionDetails)
						.then(
								function(result) {// function
									if (result.id != null) {
										angular
												.forEach(
														vm.operationArray,
														function(value, key) {
															value.osiFunctions = result;
															if(value.startDate!= undefined || value.startDate != ""){
																value.startDate = new Date(
																		value.startDate);
																value.endDate = new Date(
																		value.endDate);
															}
															
															saveOperation(value);
														});
										$('#function-model').modal('hide');
										loadAllFunctions();
										$scope.successTextAlert = appConstants.successMessage;
										$scope.showSuccessAlert = true;
									} else {
										$rootScope.isTrascError = true;
										FlashService.Error("Duplicate function name");
									}
									$timeout(function() {
										$scope.showSuccessAlert = false;
									}, 5000);
								}).catch(function(error){
									if(error.errorCode == "ERR_1036"){
			                    		$rootScope.isTrascError = true;
			                    		FlashService.Error(error.errorMessage);
			                   			$timeout(function() {
			                   				$rootScope.isTrascError = false;
			                   			}, 3000);
			                    	}else{
			                    		$rootScope.isTrascError = true;
			                    		FlashService.Error(appConstants.exceptionMessage);
			                    		$timeout(function() {
			                    			$rootScope.isTrascError = false;
			                    		}, 3000);
			                    	}
								});
			}
			}
		}
	}
    function saveOperation(operationArray) {// funOperation
        FunctionService.saveFunOperation(operationArray).then(function(result) {
            vm.functionDetails = {};
            loadAllFunctions();
        }).catch(function(){
        	showError(appConstants.exceptionMessage);
        });
    }
    
    
    function validateFuncOperation(){
    	validationError=0;
        $rootScope.isTrascError = false;
    	 for (var j in vm.functionOperations) {
    		 if(vm.functionOperations[j].opUrl != undefined && vm.functionOperations[j].opUrl !=""){
        			 if(vm.functionOperations[j].startDate != undefined && vm.functionOperations[j].startDate !=""){
        				validationError=1;
        			 }else{
        				 validationError=0;
        				 $rootScope.isTrascError = true;
        		            FlashService.Error("Please Enter Selected URL`s Start Date");
        		            $timeout(function () {
        		            	$rootScope.isTrascError=false;
        		            }, 3000);
        			 }

    		 }else{
    			 
    			 if(vm.functionOperations[j].startDate != undefined && vm.functionOperations[j].startDate !=""){
    				 validationError=0;
    				 $rootScope.isTrascError = true;
 		            FlashService.Error("Please Enter URL for Selected DATE ");
 		            $timeout(function () {
 		            	$rootScope.isTrascError=false;
 		            }, 3000);

    			 }
    		 }
    }
    	 if(validationError == 1){
    		 $rootScope.isTrascError=false;
    	 }
    }
    function prepareDataToSave() {
        angular
                .forEach(
                        vm.operations,
                        function(value, key) {
                            for (var j in vm.functionOperations) {
                            	if(value.id == vm.functionOperations[j].id ){
                            		
                            	if(((vm.functionOperations[j].startDate != undefined) && (vm.functionOperations[j].endDate == undefined)) || ((vm.functionOperations[j].startDate != "") && (vm.functionOperations[j].endDate == ""))){
                            		vm.functionOperations[j].endDate= new Date(appConstants.endDate);

                            	}
                                    vm.funOperation = {};
                                    operation = value;
                                    vm.funOperation.startDate = vm.functionOperations[j].startDate;
                                    vm.funOperation.endDate = vm.functionOperations[j].endDate;
                                    vm.funOperation.opUrl = vm.functionOperations[j].opUrl;
                                    vm.funOperation.osiOperations = value;
                                    vm.operationArray.push(vm.funOperation);
                                
                            }
                            }
                        });
    }

      function viewFunction(item) {
    	vm.editActive= true;
    	vm.createActive= false;
    	$rootScope.isTrascError = false;
        $scope.funModelHeading = "View";
        $scope.viewClearButton = false;
        $scope.iseditable = false;
        vm.showUpdate = false;
        vm.showSave = false;
        $rootScope.isTrascError = false;
        if(item.active == "Active"){
        	$scope.activeEdit= true;
        }else{
        	$scope.activeEdit=false;
        }
        FunctionService.getFuncOperationsByFunctionId(item.id).then(
                function(data) {
                    if (data.length == 0) {
                        vm.functionOperations = vm.operations;
                        vm.functionDetails = item;
                    } else {
                        vm.selectedFuncOPerations = data;
                        setData(vm.selectedFuncOPerations, item);
                    }
                }).catch(function(){
                	$rootScope.isTrascError = true;
					FlashService.Error(appConstants.exceptionMessage);
					$timeout(function() {
						$rootScope.isTrascError = false;
					}, 5000);
                })
        $('#function-model').modal({backdrop: 'static', keyboard: false});
    }
    
    function setData(items, functiondetails) {
        $rootScope.isTrascError = false;
        vm.functionDetails = functiondetails;
        vm.startDate = [];
        vm.endDate = [];
        vm.url = [];
        var xyz = angular.copy(vm.operations);
        vm.functionOperations = [];
        for (var i = 0; i < vm.operations.length; i++) {
            var urlObj = {};
            for (var j = 0; j < items.length; j++) {
                urlObj.name = vm.operations[i].name;
                if (vm.operations[i].id == items[j].osiOperations.id && items[j].opUrl != "") {
                    urlObj.startDate = $filter('date')(new Date(items[j].startDate), 'dd-MMM-yyyy');
                    urlObj.endDate = $filter('date')(new Date(items[j].endDate), 'dd-MMM-yyyy');
                    urlObj.opUrl = items[j].opUrl;
                }
            }
            vm.functionOperations.push(urlObj);
        }
        for (var i = 0; i < vm.functionOperations.length; i++) {
        	if(vm.functionOperations[i].opUrl == null || vm.functionOperations[i].opUrl == "" || vm.functionOperations[i].opUrl  == undefined) {
        		vm.functionOperations[i].startDate = "";
        		vm.functionOperations[i].endDate = "";
        	}
        		
        }
        	
    }

      $scope.editSelectedRowDetails = function(item) {
        var ops = $scope.availOperations;
        $rootScope.isTrascError = false;
        var found = false;
        for (var i = 0; i < ops.length; i++) {
            if (ops[i].name == 'Edit') {
                found = true;
                break;
            }
        }
        if (found) {
            editFunction(angular.copy(item));
            $scope.isSelectedRow = item.id;
            toggleButtons();
        }
    }
    
      function editFunction(item) {
    	vm.editActive= true;
    	vm.createActive= false;
    	$rootScope.isTrascError = false;
        $scope.funModelHeading = "Edit";
        $scope.viewClearButton = true;
        vm.functionOperations=[];
        if(item.active == "Active"){
        	$scope.activeEdit= true;
        }else{
        	$scope.activeEdit=false;
        }
        FunctionService
                .getFuncOperationsByFunctionId(item.id)
                .then(
                        function(data) {
                            if (data.length == 0) {
                                vm.functionOperations = vm.operations;
                                vm.functionDetails = item;
                            } else {
                                vm.selectedFuncOPerations = data;
                                vm.functionOperations = data;
                                setData(vm.selectedFuncOPerations, item);
                                for (var i = 0; i < data.length; i++) {
                                	if(data[i].id != undefined){
                                		vm.functionOperations[i].id = data[i].id;
//                                    vm.functionOperations[i].osiOperations = data[i].osiOperations;
                                		vm.functionOperations[i].osiFunctions = data[i].osiFunctions;
                                		vm.functionOperations[i].businessGroupId = data[i].businessGroupId;
                                        vm.functionOperations[i].createdBy = data[i].createdBy;
                                        vm.functionOperations[i].createdDate = data[i].createdDate;
                                	}
                                }
                            }
                        }).catch(function(error){
                        	$rootScope.isTrascError = true;
        					FlashService.Error(appConstants.exceptionMessage);
        					$timeout(function() {
        						$rootScope.isTrascError = false;
        					}, 5000);
                        });
        $scope.iseditable = true;
        $rootScope.isTrascError = true;
        vm.showSave = false;
        vm.showUpdate = true;
        $('#function-model').modal({backdrop: 'static', keyboard: false}); 
    }
    
    vm.funcOperation = [];
   function isInvalidDate(){
	   for(var i in vm.functionOperations){
	   if((((vm.functionOperations[i].startDate != undefined) && (vm.functionOperations[i].startDate !="")) && (new Date(vm.functionOperations[i].startDate).isValid() == false)) ||
  				(((vm.functionOperations[i].endDate != undefined) && (vm.functionOperations[i].endDate !="" ))  && (new Date(vm.functionOperations[i].endDate).isValid() == false))){
		   dateError=1;
	   		}
	   else{
		   if((((new Date(vm.functionOperations[i].startDate).getDate()) > 31) || ((new Date(vm.functionOperations[i].startDate).getMonth()) > 11) || ((new Date(vm.functionOperations[i].startDate).getYear()) > 200) || ((new Date(vm.functionOperations[i].startDate).getYear()) < 0))||
				   (((new Date(vm.functionOperations[i].endDate).getDate()) > 31) || ((new Date(vm.functionOperations[i].endDate).getMonth()) > 11) || ((new Date(vm.functionOperations[i].endDate).getYear()) > 200) || ((new Date(vm.functionOperations[i].endDate).getYear()) < 0)) ){
			   dateError=1;
		   }
	   }
	   }
   }
    vm.update = function() {
    	dateError = 0;
		validateFuncOperation();
		isInvalidDate();
        $rootScope.isTrascError = false;
        if($scope.activeEdit == false){
        	vm.functionDetails.active=0;
        }else{
        	vm.functionDetails.active=1;
        }
    	
        if(vm.functionDetails.funcName == undefined || vm.functionDetails.funcName == "")
            {
             $rootScope.isTrascError = true;
             FlashService.Error("Please Enter Function Name");
             $timeout(function () {
             	$rootScope.isTrascError=false;
             }, 3000);
	}else if(vm.functionDetails.funcType == undefined || vm.functionDetails.funcType == ""){
             $rootScope.isTrascError = true;
             FlashService.Error("Please Enter Function Type");
             $timeout(function () {
             	$rootScope.isTrascError=false;
             }, 3000);
        }else if(vm.functionDetails.funcValue == undefined || vm.functionDetails.funcValue ==""){
            $rootScope.isTrascError = true;
             FlashService.Error("Please Enter Function Value");
             $timeout(function () {
             	$rootScope.isTrascError=false;
             }, 3000);
        }else if(validationError== 0){
             $rootScope.isTrascError = true;
             FlashService.Error("Please Enter Function Operation-Url and Operation-Start Date");
             $timeout(function () {
             	$rootScope.isTrascError=false;
             }, 3000);
        } else {
        	if (dateError == 1) {
				$rootScope.isTrascError = true;
				FlashService.Error("Invalid Date");
				$timeout(function() {
					$rootScope.isTrascError = false;
				}, 3000);
			} else {
				if(urlError==1){
					$rootScope.isTrascError = true;
					FlashService.Error("Please enter proper URL");
					$timeout(function() {
						$rootScope.isTrascError = false;
					}, 3000);
				}else if (startDateError == 1) {
					$rootScope.isTrascError = true;
//					FlashService.Error("Invalid Date");
					$timeout(function() {
						$rootScope.isTrascError = false;
					}, 3000);
				}else{
            FunctionService.updateFunction(vm.functionDetails).then(
                    function(result) {
                       if (result.id != null) {
                            angular.forEach(vm.functionOperations, function(value,
                                    key) {
                            	var osiFunctions;
                            	var osiOperations;
										 if(osiFunctions == undefined ){
											 value.osiFunctions=result;
										 }
								
										 if((value.startDate != undefined) && ((value.endDate== undefined) || (value.endDate == ""))){
											 value.endDate= new Date(appConstants.endDate);

										 } 
										 if(value.startDate != undefined || value.startDate != undefined !=""){
											 value.startDate = $filter('date')(new Date(value.startDate), 'dd-MMM-yyyy');
                                                                                         value.endDate = $filter('date')(new Date(value.endDate), 'dd-MMM-yyyy');
										 }
			                              
			                               if(value.osiOperations == undefined){
			                            	   for(var i=0; i<vm.operations.length; i++){
			                            		   if(value.name == vm.operations[i].name){
			                            			   value.osiOperations=  vm.operations[i];
			                            		   }
			                            	   }
			                               }
			                               delete value.name;
			                               value.startDate = new Date(value.startDate);
			                               value.endDate = new Date(value.endDate);
			                            	   FunctionService.updateFuncOperation(value)
			                            	   .then(
			                            			   function(result) {
			                            				   $('#function-model')
			                            				   .modal('hide');
			                            				   vm.clear();
			                            			   }).catch(function(error){
			                            				   showError(appConstants.exceptionMessage);
			                            			   });
                            });
                            $('#function-model').modal('hide');
                            loadAllFunctions();
                            $scope.successTextAlert = appConstants.successMessage;
                            $scope.showSuccessAlert = true;
                        } else {                            
                            $rootScope.isTrascError = true;
                            FlashService.Error("Function name already exist");
                        }
                        $timeout(function () {
                            $scope.showSuccessAlert = false;
                            $rootScope.isTrascError=false;
                        }, 5000);
                    }).catch(function(error){
                    	if(error.errorCode == "ERR_1036"){
                    		$rootScope.isTrascError = true;
                    		FlashService.Error(error.errorMessage);
                   			$timeout(function() {
                   				$rootScope.isTrascError = false;
                   			}, 3000);
                    	}else{
                    		$rootScope.isTrascError = true;
                    		FlashService.Error(appConstants.exceptionMessage);
                    		$timeout(function() {
                    			$rootScope.isTrascError = false;
                    		}, 3000);
                    	}
                    });
        }
		}
        }
        $scope.clearSelectedRow();
        $scope.activeEdit= true;
    }
    
    Date.prototype.isValid = function () {
        // An invalid date object returns NaN for getTime() and NaN is the only
        // object not strictly equal to itself.
        return this.getTime() === this.getTime();
    }; 
    function showDeleteConfirmationPopup() {
    	$('#function-delete-model').modal('show'); 
    }
    vm.deleteFunction = function() {
   	 $('#function-delete-model').modal('hide');
//   	 vm.update(); 
   }

    vm.clear = function() {
        vm.functionDetails = {};
        for (var i = 0; i < vm.functionOperations.length; i++) {
            vm.functionOperations[i].opUrl = "";
            vm.functionOperations[i].startDate = "";
            vm.functionOperations[i].endDate = "";
            $rootScope.isTrascError=false;
        }
    }
    vm.noRecord=true;
    vm.makeEnableButtion = function(value){
    	if(value){
    		vm.isButtionDisable = false;
    	}else{
    		vm.isButtionDisable = true;
    	}
    }
    
    vm.makeEnableButtionForName = function(value){
    	if(value){
    		vm.isButtionDisableForName = false;
    	}else{
    		vm.isButtionDisableForName = true;
    	}
    }
    
    vm.search = function() {
        if((vm.searchBy == undefined) || ((vm.searchBy.funcName == undefined || vm.searchBy.funcName == "") && (vm.searchBy.funcValue == undefined || vm.searchBy.funcValue == ""))){
            
            FunctionService.getAllFunctionsList().then(function(data) {
                angular.forEach(data,function(value, key) {	
                        if(value.active == 1){
                                value.active="Active";
                        }else{
                                value.active="Inactive";
                        }
                });
                vm.functions = data;
            });
        }
           else {
                FunctionService.searchFunction(vm.searchBy).then(function(result) {
                    if(result.length==0){
                            vm.functions=[];

                    }else{
                            angular.forEach(result,function(value, key) {	
                                    if(value.active == 1){
                                            value.active="Active";
                                    }else{
                                            value.active="Inactive";
                                    }
                            });
                            vm.functions = result;
                    }
                }).catch(function(){
                	showError(appConstants.exceptionMessage);
                });
            }
        }
    
    

        vm.checkDate = function(index) {
            if (vm.functionOperations[index].startDate == "") {
                vm.functionOperations[index].endDate = "";
                //vm.functionOperations[index].opUrl = "";
            }

            if (vm.functionOperations[index].startDate == undefined) {
                    startDateError = 1;
                    $rootScope.isTrascError = true;
                    console.log(vm.functionOperations);
                    console.log(vm.functionOperations[index]);
                    console.log(vm.functionOperations[index].startDate);
                    FlashService.Error("Invalid Start Date");
                    $timeout(function() {
                            $rootScope.isTrascError = false;
                    }, 3000);
                    vm.functionOperations[index].startDate = "";
            } else {
                    startDateError = 0;
                    $rootScope.isTrascError = false;
                    /*if (vm.functionOperations[index].endDate != null
                                    && (new Date(vm.functionOperations[index].startDate).getTime() >= new Date(vm.functionOperations[index].endDate).getTime())) {
                            $rootScope.isTrascError = true;
                            FlashService.Error("Start date must be less than end date.");
                            $timeout(function() {
                                    $rootScope.isTrascError = false;
                            }, 3000);
                            vm.functionOperations[index].startDate = "";
                            vm.functionOperations[index].endDate = "";
                    }*/
                    vm.compareDate(index);
            }
            if (new Date(vm.functionOperations[index].startDate).setHours(0,0,0,0) < new Date().setHours(0,0,0,0)) {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Start date cannot be less than today's date");
                    $timeout(function() {
                            $rootScope.isTrascError = false;
                    }, 3000);
                    vm.functionOperations[index].startDate = "";
            }
	}

	vm.checkEndDate = function(index) {
		if (vm.functionOperations[index].endDate == undefined) {
			//startDateError = 1;
			$rootScope.isTrascError = true;
			FlashService.Error("Invalid End Date");
			$timeout(function() {
				$rootScope.isTrascError = false;
			}, 3000);
			vm.functionOperations[index].endDate = "";
		}
		vm.compareDate(index);
		/*if (vm.functionOperations[index].endDate != null
				&& (new Date(vm.functionOperations[index].startDate).getTime() >= new Date(vm.functionOperations[index].endDate).getTime())) {
			$rootScope.isTrascError = true;
			FlashService.Error("End date should be greater than start date");
			$timeout(function() {
				$rootScope.isTrascError = false;
			}, 3000);
			vm.functionOperations[index].endDate = "";
		}*/
	}
	vm.compareDate = function(index) {
		if (vm.functionOperations[index].endDate != null
				&& (new Date(vm.functionOperations[index].startDate).getTime() >= new Date(vm.functionOperations[index].endDate).getTime())) {
			$rootScope.isTrascError = true;
			FlashService.Error("End date should be greater than start date");
			$timeout(function() {
				$rootScope.isTrascError = false;
			}, 3000);
			vm.functionOperations[index].endDate = "";
		}
	}
    vm.checkUrl= function(index){
    	if(vm.functionOperations[index].opUrl==""){
    		vm.functionOperations[index].startDate = "";
            vm.functionOperations[index].endDate = "";
    	}
    	if(vm.functionOperations[index].opUrl=="/"){
    		urlError=1;
    	}else{
    		urlError=0;
    	}
    	
    	
    }
    loadAllFunctions();
    //$scope.sort('funcName');
    vm.selectRow = function($event, item){
    vm.ItemToDelete=item;
    }
    $scope.clearSearch = function () {
    	vm.searchBy={};
        $scope.clearSelectedRow();
        loadAllFunctions();
    };
    $scope.showDeleteConfirmationPopup=function(item) {
   	 if($scope.activeEdit == false){
   		vm.activateValue= "Diactivate";
   	 }else{
   		vm.activateValue= "Activate"; 
   	 }
   	 $('#function-delete-model').modal('show');

   }
    vm.noAction = function(){
    	if($scope.activeEdit == false){
    		$scope.activeEdit = true;
    	}else{
    		$scope.activeEdit = false;
    	}
    	
    }
    $scope.$watch('vm.functionOperations', function (newVal, oldVal) {
//    	alert("watch");
    }, true);

}