(function() {
	'use strict';
angular.module('myApp.operationExclusionController', []).controller('operationExclusionController', operationExclusionController);
operationExclusionController.$inject = ['$q','$scope','$localStorage','appConstants','$timeout', 'FlashService','$location', '$window', '$rootScope', 'functionExclusionService', 'operationExclusionService', 'SharedDataService', 'ResponsibilityService','FunctionService'];

function operationExclusionController($q, $scope,$localStorage,appConstants,$timeout, FlashService,$location, $window, $rootScope, functionExclusionService, operationExclusionService, SharedDataService, ResponsibilityService,FunctionService) {
	var vm = this;
	vm.operationsList = {};
	vm.allFunctions = [];
	vm.user = {};
	vm.employeeId = $localStorage.employeeId;
	vm.empId = $localStorage.empId;
	vm.allOperations = [];
	vm.userUpdate = false;
	vm.userView = false;
	vm.updateUser = updateUser;
	$rootScope.confirmationHeading="";
	$rootScope.isOperationExclutionDataModified= false;
	vm.getNewObject = getNewObject;
	initController();
	 vm.deselectTabs=function(){
         $rootScope.tab1="";
         $rootScope.tab2="";
         $rootScope.tab3="";
         $rootScope.tab4="";
         $rootScope.tab5="";
         $rootScope.tab6="";
         $rootScope.personalInfoActive="";
     };
     
     vm.clearOperationExclusion = function(){
    	 initController();
     }
     vm.backToEmpList = function(){
    	 $localStorage.isOperationExclusion = true;
         $("#operation").removeClass("dynatree-active");
         $(".Employee_List").addClass("dynatree-active");
         
         window.location.href = "#/employees";
     }
	$rootScope.$on('FunctionsForUser', function(event, user, functions, userUpdate, userView) {
    	vm.userInfo = user;
    	vm.userUpdate = userUpdate;
    	vm.infoView=userView;
    	vm.allFunctions = angular.copy(functions);
    	
		var exclFuns = _.pluck(vm.userInfo.osiUserFuncExcls, 'osiFunctions');
		var exclFunIds = _.pluck(exclFuns, 'id');
    	vm.filterExcludedFuntions(exclFunIds);
    });
	
	$rootScope.$on('FunctionsForUpdatedUser', function(event, functions) {
		vm.allFunctions = angular.copy(functions);
    	initController(vm.allFunctions);
    });

	$rootScope.$on('ExcludedFunctions', function(event, exclFunctionIds) { 
		vm.filterExcludedFuntions(exclFunctionIds);
	});
	
	$rootScope.$on('openChangesEvent', function(event,rootEvent) {
            if(vm.operationExclForm!=undefined){
                if (vm.operationExclForm.$dirty)
			 vm.operationExclForm.$setPristine();
            }
	});
	 $rootScope.$on('customEvent', function (event, user, userUpdate, userView) {
         vm.userInfo = user;
         vm.userUpdate = userUpdate;
         vm.infoView= userView;
     	 vm.listOfFunctions = [];
		 vm.selectedOpnExcls = [];
     });
	 
	  function filterExcludedFuntions() {
		  var exclFunctionIds =[];
		  functionExclusionService.getExclFunctions( vm.employeeId).then(function(data){
			  
			  var exclFuns = _.pluck(data, 'osiFunctions');
				var exclFunIds = _.pluck(exclFuns, 'id');
				
			  exclFunctionIds = exclFunIds;
			  
			  var filteredListOfFunctions = vm.allFunctions.filter((fun) => (exclFunctionIds.findIndex((exclId) => (exclId == fun.id)) == -1));
				vm.listOfFunctions = _.map(_.groupBy(filteredListOfFunctions ,function(func){
					if(func != undefined){
						 return func.funcName;
					}
					}),function(grouped){
					  return grouped[0];
					});
				if(vm.userUpdate || vm.infoView || true) {
					
					if(vm.selectedOpnExcls.length) {
						vm.selectedOpnExcls = vm.selectedOpnExcls.filter(
								(opnExcl) => (exclFunctionIds.findIndex(
										(exclId) => (exclId == opnExcl.osiFunctions.id)) == -1));
					}

					updateUser();
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
		  
		/*var excludedIds = angular.copy(excludedIds);*/
		/*if(SharedDataService.getUserInfo()) {
			var user = SharedDataService.getUserInfo();
		} else {*/
		 
			var user = vm.userInfo;
		/*}*/
			
		
	 };
	
	 function getNewObject() {
            return {
				osiFunctions:"",
				availableOperationsNames:[],
				excludedOperationNames:[]
		};
	};
		
	function initController(functions) {
		
		if( vm.empId !==undefined &&  vm.empId  !== null){
			$("#userResponsibility").removeClass("dynatree-active");
			$("#functionExclusion").removeClass("dynatree-active");
			$localStorage.isOperationExclusion = false;
			ResponsibilityService.getResponsibilities( vm.employeeId).then(function(data){
				vm.userResp = data;
				   var selectedUserResp = angular.copy(vm.userResp);
		            var selectedResps = _.pluck(selectedUserResp, 'osiResponsibilities');
		            var respIds = _.pluck(selectedResps, 'id');
		            var respUser = {
		                userRespIds : respIds
		            };
		            functionExclusionService.getFunctionsByRespIds(respUser).then(function(result) {

		    			vm.selectedOpnExcls = [];
		    			vm.selectedOpnExcls.push(getNewObject());
		            	vm.allFunctions = result;
		        		vm.listOfFunctions = [];
		        		vm.listOfFunctions = _.map(_.groupBy(result ,function(func){
		        			if(func != undefined){
		        				 return func.funcName;
		        			}
		        			}),function(grouped){
		        			  return grouped[0];
		        			});
		        	
		        		operationExclusionService.getAllOperations().then(function (data) {
		        			    vm.allOperations = data;
		        			filterExcludedFuntions();
		        		
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
		}
		else {
			$("#operation").removeClass("dynatree-active");
			$(".Employee_List").addClass("dynatree-active");
			$localStorage.isOperationExclusion = true;
    		window.location.href = "#/employees";
		}
	}
	
	 function updateUser() {
		 operationExclusionService.getAllExcludedOperations(vm.employeeId).then(function(data){
			 var user ={
					 
			 };
			 user.osiUserOperationExcls = [];
			 if(data.length > 0) {
				 angular.forEach(data, function(value,key){
 	            	
 	                    angular.forEach(vm.allOperations, function(opr, index){
 	                        if(value.osiOperations.id == opr.id){ 	                        	
 	                        	user.osiUserOperationExcls.push(value);
 	                        }
 	                    });
				 });
				 
				 if(user.osiUserOperationExcls.length === 0) {
						vm.selectedOpnExcls = [];
						vm.selectedOpnExcls.push(getNewObject());
					} else {
						vm.mapDomainObjectToUi(user);
					}
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
		
	};
	
	vm.mapDomainObjectToUi = function(user) {
		var promises = [];
		var selectedOpnExcls = [];
		var functionList = [];
		angular.forEach(user.osiUserOperationExcls, function(opnExcl) {
			functionList.push(opnExcl.osiFunctions.id);
		});
		var uniqueFunctions = functionList.unique();
		angular.forEach(uniqueFunctions, function(funId) {
			var deferred = $q.defer();
			FunctionService.getFuncOperationsByFunctionId(funId).then(function(data) {
				var operationExclusionsTemp={
						osiFunctions:"",
						availableOperationsNames:[],
						excludedOperationNames:[]
				}; 
				angular.forEach(data, function(value){
					if(value.opUrl!= null){
						operationExclusionsTemp.availableOperationsNames.push(value.osiOperations.name);
					}
				});
				angular.forEach(user.osiUserOperationExcls, function(opnExcl) {
					if(funId === opnExcl.osiFunctions.id) {
						angular.forEach(vm.listOfFunctions, function(func) {
							if(funId === func.id) {
								operationExclusionsTemp.osiFunctions = angular.copy(func);
							}
							operationExclusionsTemp.id=opnExcl.id;
						});
						operationExclusionsTemp.excludedOperationNames.push(opnExcl.osiOperations.name);
					}
				});
				operationExclusionsTemp.availableOperationsNames = _.difference(operationExclusionsTemp.availableOperationsNames, operationExclusionsTemp.excludedOperationNames);
  				selectedOpnExcls.push(operationExclusionsTemp);
  				deferred.resolve();
			});
			promises.push(deferred.promise);
		});
	    $q.all(promises).then( function(results) {
	    	vm.selectedOpnExcls = angular.copy(selectedOpnExcls);
	    });
	};
	
	vm.addToExcludedALL = function(index) { 
		vm.selectedOpnExcls[index].excludedOperationNames =_.union(vm.selectedOpnExcls[index].excludedOperationNames, vm.selectedOpnExcls[index].availableOperationsNames);
		vm.selectedOpnExcls[index].availableOperationsNames = [];
		vm.clearFields();
		var userOperation=vm.getUserOperationObject();
		var excludedOperation = vm.getManipulatedUserOperationObject();
        if ( vm.difference(userOperation, excludedOperation).length>0 || vm.difference(excludedOperation, userOperation).length>0 )
        {
        	$rootScope.isOperationExclutionDataModified= true;
        }else{
            $rootScope.isOperationExclutionDataModified= false;
        }
	};
	
	vm.addToExcludedRepeat = function(selectedOptions,index) { 
		angular.forEach(selectedOptions, function(option){
			var indexOfOps = vm.selectedOpnExcls[index].availableOperationsNames.indexOf(option);
			vm.selectedOpnExcls[index].availableOperationsNames.splice(indexOfOps, 1);
			vm.selectedOpnExcls[index].excludedOperationNames.push(option);
		});
		vm.clearFields();
    	var userOperation=vm.getUserOperationObject();
		var excludedOperation=vm.getManipulatedUserOperationObject();
        if ( vm.difference(userOperation, excludedOperation).length>0 || vm.difference(excludedOperation, userOperation).length>0 )
        {
        	$rootScope.isOperationExclutionDataModified= true;
        }else{
            $rootScope.isOperationExclutionDataModified= false;
        }
	};
	
	vm.removeFromExcludedALL = function(index) {
        vm.selectedOpnExcls[index].availableOperationsNames = _.union(vm.selectedOpnExcls[index].availableOperationsNames, vm.selectedOpnExcls[index].excludedOperationNames);
        vm.selectedOpnExcls[index].excludedOperationNames = [];
		vm.clearFields();
		var userOperation=vm.getUserOperationObject();
		var excludedOperation=vm.getManipulatedUserOperationObject();
        if ( vm.difference(userOperation, excludedOperation).length>0 || vm.difference(excludedOperation, userOperation).length>0 )
        {
        	$rootScope.isOperationExclutionDataModified= true;
        }else{
            $rootScope.isOperationExclutionDataModified= false;
        }
    };
	
	vm.removeFromExcludedRepeat = function(excludedOptions,index) {
    	angular.forEach(excludedOptions, function(option){
    		var indexOfOps = vm.selectedOpnExcls[index].excludedOperationNames.indexOf(option);
    		vm.selectedOpnExcls[index].excludedOperationNames.splice(indexOfOps, 1);
    		vm.selectedOpnExcls[index].availableOperationsNames.push(option);
    	});
    	vm.clearFields();
    	var userOperation=vm.getUserOperationObject();
		var excludedOperation=vm.getManipulatedUserOperationObject();
        if ( vm.difference(userOperation, excludedOperation).length>0 || vm.difference(excludedOperation, userOperation).length>0 )
        {
        	$rootScope.isOperationExclutionDataModified= true;
        }else{
            $rootScope.isOperationExclutionDataModified= false;
        }
    };
	
	vm.addRow = function(index) {
		vm.selectedOpnExcls.push(getNewObject());
                vm.selectedOpnExcls[index].availableOperationsNames=[];
                vm.selectedOpnExcls[index].excludedOperationNames=[];
	};
	
	vm.removeRow = function(index) {
		vm.selectedOpnExcls.splice(index, 1);
		if (vm.selectedOpnExcls.length === 0) {
			vm.selectedOpnExcls = [{}];
		}
	};
	
	vm.clearFields = function() {
		vm.avaiableOps = [];
		vm.excludedOps = [];
	};
	
	vm.save = function() {
		var user = SharedDataService.getUserInfo();
		var osiUserOperationExcls = vm.mapUiToDomainObject(user);
		if(vm.employeeId !== undefined && vm.employeeId !== null){
				operationExclusionService.saveOperationExclusion(osiUserOperationExcls).then(function(data){
					
					$rootScope.isTrascError = true;
					FlashService.Success(appConstants.successMessage);
					$timeout(function() {
						$rootScope.isTrascError = false;
					}, 4000);
					
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
		}else{
			$rootScope.isTrascError = true;
			FlashService.Error("Please select any function");
			$timeout(function() {
				$rootScope.isTrascError = false;
			}, 4000);
		}
		/*SharedDataService.setUserInfo(user);
		$rootScope.isOperationExclutionDataModified= false;
        vm.operationExclForm.$setPristine();
		angular.element('[data-target="#invOrgAssignment-tab"]').tab('show');
		 vm.deselectTabs();
        $rootScope.tab5="selectedInfoTab";*/
	};
	
	vm.mapUiToDomainObject = function(user) {
		var osiUserOperationExcls = [];
		
			angular.forEach(vm.selectedOpnExcls, function(opnExcl){
				if( opnExcl.osiFunctions !== '' && opnExcl.osiFunctions !== undefined && opnExcl.osiFunctions !== null){
				angular.forEach(opnExcl.excludedOperationNames, function(excludedOpn){
					var singleOpnExcl = {};
					singleOpnExcl.osiFunctions = opnExcl.osiFunctions;
					
					singleOpnExcl.employeeId =  vm.employeeId;
					singleOpnExcl.id = opnExcl.id;
					var operation = _.find(vm.allOperations, function(opn){ return opn.name == excludedOpn; });
					var osiOperations = {id:operation.id, name:operation.name};
					singleOpnExcl.osiOperations = osiOperations;
					osiUserOperationExcls.push(singleOpnExcl);
				});
		}else{
			if(vm.employeeId !== undefined && vm.employeeId !== null){
				var singleOpnExcl = {};
				singleOpnExcl.employeeId =  vm.employeeId;
				osiUserOperationExcls.push(singleOpnExcl);
			}
		}
			});
		
		
			return osiUserOperationExcls;
	};
	
	vm.checkDuplicateAndGetOpns = function(selectedFunction, index) {
		if(selectedFunction) {
			var tempList = [];
			angular.forEach(vm.selectedOpnExcls, function(opnExcl) {
				var osiFun = opnExcl.osiFunctions;
				tempList.push(osiFun.funcName);
			});
			if(tempList.indexOf(selectedFunction.funcName) != tempList.lastIndexOf(selectedFunction.funcName)){
				vm.selectedOpnExcls[index].osiFunctions = '';
				$rootScope.isTrascError = true;
				FlashService.Error("Duplicate Function Selected");
				$timeout(function() {
					$rootScope.isTrascError = false;
				}, 4000);
		//		alert("Duplicate Function Selected");
			} else {
				vm.getAvailableOperation(selectedFunction, index);
			}
		}
	};
	vm.difference= function(array){
		   var rest = Array.prototype.concat.apply(Array.prototype, Array.prototype.slice.call(arguments, 1));

		   var containsEquals = function(obj, target) {
		    if (obj == null) return false;
		    return _.any(obj, function(value) {
		      return _.isEqual(value, target);
		    });
		  };
		  return _.filter(array, function(value){ return ! containsEquals(rest, value); });
	};
	vm.getUserOperationObject= function(){
		var user = SharedDataService.getUserInfo();
		var userOperation=[];
		angular.forEach(user.osiUserOperationExcls, function(userOper) {
			var funcName=userOper.osiFunctions.funcName;
			var operName=userOper.osiOperations.name;
			userOperation.push({functionName:funcName,operationName:operName});
		});
		return userOperation;
	}

	vm.getManipulatedUserOperationObject= function(){
		var user = SharedDataService.getUserInfo();
		var osiUserOperationExcls = vm.mapUiToDomainObject(user);
		var excludedOperation=[];
		angular.forEach(osiUserOperationExcls, function(userOper) {
			var funcName=userOper.osiFunctions.funcName;
			var operName=userOper.osiOperations.name;
			excludedOperation.push({functionName:funcName,operationName:operName});
		});
		return excludedOperation;
	}
	vm.previous = function() {
		var userOperation=[];
		userOperation=vm.getUserOperationObject();
		
		var excludedOperation=[];
		excludedOperation=vm.getManipulatedUserOperationObject();
		
        if ( vm.difference(userOperation, excludedOperation).length>0 || vm.difference(excludedOperation, userOperation).length>0 )
            {
        	 	vm.deselectTabs();
                $rootScope.tab4="selectedInfoTab";
                $rootScope.tab5="";
                $rootScope.tab6="";
                $rootScope.personalInfoActive="";
                $rootScope.confirmationHeading=appConstants.operationExclusion;
                $rootScope.isOperationExclutionDataModified= true;
                $rootScope.showTabSwitchModal();
        }else{
                angular.element('[data-target="#funcExclu-tab"]').tab('show'); 
                vm.deselectTabs();
                $rootScope.tab3="selectedInfoTab";
                $rootScope.isOperationExclutionDataModified= false;
         }
	};
	
	Array.prototype.diff = function(a) {
	    return this.filter(function(i) {return a.indexOf(i) < 0;});
	};
	
	Array.prototype.contains = function(v) {
	    for(var i = 0; i < this.length; i++) {
	        if(this[i] === v) return true;
	    }
	    return false;
	};

	Array.prototype.unique = function() {
	    var arr = [];
	    for(var i = 0; i < this.length; i++) {
	        if(!arr.contains(this[i])) {
	            arr.push(this[i]);
	        }
	    }
	    return arr; 
	};
        
    //This event is used to save changes we made in the current form . Event is immeted from userlist controller.
    $rootScope.$on('saveChangesEvent', function(event,rootEvent,tabname) 
    {
    	if(tabname==appConstants.tab4){
    		vm.deselectTabs();
			$rootScope.tab4="selectedInfoTab";
    	}
     });
    
    vm.nextPage = function(){
    	angular.element('[data-target="#invOrgAssignment-tab"]').tab('show');
      	vm.deselectTabs();
        $rootScope.tab5="selectedInfoTab";
    }
    
    vm.getAvailableOperation= function(func, num){
    	operationExclusionService.getExcludedOperations(func.id).then(function (excludedOpr) {
    		  vm.selectedOpnExcls[num].availableOperationsNames=[];
	            vm.selectedOpnExcls[num].excludedOperationNames=[];
	            FunctionService.getFuncOperationsByFunctionId(func.id).then(function(data) {
	 		        angular.forEach(data, function(value,key){

		                if(value.opUrl!=null ){
		                    angular.forEach(vm.allOperations, function(opr, index){
		                        if(value.osiOperations.id == opr.id){
		                            vm.selectedOpnExcls[num].availableOperationsNames.push(opr.name);
		                        }
		                    });
		                }
		            });
		            if(excludedOpr.length >0){
		            angular.forEach(excludedOpr, function(value,key){
	 	            	vm.selectedOpnExcls[num].osiFunctions = value.osiFunctions;
	 	                    angular.forEach(vm.allOperations, function(opr, index){
	 	                        if(value.osiOperations.id == opr.id){
	 	                        	
		                            vm.selectedOpnExcls[num].excludedOperationNames.push(opr.name);
		                            vm.selectedOpnExcls[num].id=opr.id;
	 	                        }
	 	                    });
	 	                    	
	    		});
		            }
		      
    		     vm.selectedOpnExcls[num].availableOperationsNames= vm.selectedOpnExcls[num].availableOperationsNames.diff( vm.selectedOpnExcls[num].excludedOperationNames);
    	     
		        });
    	});

};
}
})();
