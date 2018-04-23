
(function() {
	'use strict';

	angular.module('myApp.functionExclusionController', []).controller(
			'functionExclusionController', functionExclusionController);
	functionExclusionController.$inject = [ '$timeout','appConstants','$location','$localStorage', '$window', '$scope', '$rootScope', 'functionExclusionService', 'SharedDataService','ResponsibilityService','FlashService'];
	function functionExclusionController($timeout,appConstants, $location, $localStorage,$window, $scope, $rootScope,functionExclusionService, SharedDataService,ResponsibilityService,FlashService) {
	
		var vm = this;
		vm.temp = {};
		vm.excludedOpts = [];
		vm.merged = [];
		vm.empId = $localStorage.empId;
		vm.merge = [];
		vm.employeeId = $localStorage.employeeId;
		vm.availableOptions = [];
		vm.submitSuccess = false;
        vm.exclFunctionIds = [];
		vm.osiuserList = [];
        vm.alert = true;
        vm.userRespIds = [];
        vm.userUpdate = false;
        vm.infoView = false;
        $rootScope.confirmationHeading="";
        $rootScope.isFunctionExclusionDataModified= false;
    	if(vm.empId !== undefined && vm.empId !== null){
    		$("#userResponsibility").removeClass("dynatree-active");
    		$("#operation").removeClass("dynatree-active");
    		$localStorage.isFunctionExclusion = false;
	        vm.getFunctionsByUserResp = getFunctionsByUserResp;
	        vm.getFunctionsByUserResp();
    	}else{
    		$("#functionExclusion").removeClass("dynatree-active");
    		$(".Employee_List").addClass("dynatree-active");
        	$localStorage.isFunctionExclusion = true;
    		window.location.href = "#/employees-resp";
        }
        vm.deselectTabs=function(){
            $rootScope.tab1="";
            $rootScope.tab2="";
            $rootScope.tab3="";
            $rootScope.tab4="";
            $rootScope.tab5="";
            $rootScope.tab6="";
            $rootScope.personalInfoActive="";
        };
        $rootScope.$on('customEvent', function (event, user, userUpdate, userView) {
            vm.userInfo = user;
            
            vm.userUpdate = userUpdate;
            vm.infoView= userView;
            if(!userUpdate && !userView){
            	vm.allFunctionsNames = [];
                vm.availableFunctionsNames = [];
                vm.allFunctionsIds = [];
                vm.exclFunctionsNames = [];
            }
            
        });
        function initController(userFunctions) {
        
        	vm.allFunctions = angular.copy(userFunctions);
            vm.exclFunctionsNames = [];
            vm.allFunctionsNames = [];
            vm.availableFunctionsNames = [];
            vm.allFunctionsIds = [];
            vm.exclFunctionsNames.length = 0;
            vm.allFunctionsNames.length = 0;
            vm.availableFunctionsNames.length = 0;
            vm.allFunctionsIds.length = 0;
            angular.forEach(vm.allFunctions, function (value,key) {
            	vm.allFunctionsNames.push(value.funcName);
            	vm.allFunctionsIds.push(value.id);
            });
            
            vm.allFunctionsNames.sort(); 
            
            if(vm.userUpdate || vm.infoView || true){
            	if(vm.user || true) {
            		functionExclusionService.getExclFunctions(vm.employeeId).then(function(result) {
    	                vm.excludedFunctions = angular.copy(result);
	            		vm.exclFunctionIds = [];
	            		vm.exclFunctionsNames = [];
	            		angular.forEach(vm.excludedFunctions, function (exclFun) {
	            			angular.forEach(vm.allFunctions, function (value) {
	            				if(value.id === exclFun.osiFunctions.id) {
	            					vm.exclFunctionsNames.push(value.funcName);
	            					vm.exclFunctionIds.push(value.id);
	            				}
	                        });
	            		});
	            		
	            			vm.exclFunctionsNames= _.uniq(vm.exclFunctionsNames);
	            		
	            			vm.availableFunctionsNames = vm.allFunctionsNames.diff(vm.exclFunctionsNames);
	            		
	            			vm.availableFunctionIds = vm.allFunctionsIds.diff(vm.exclFunctionIds);
            		  });
            	}
            } else {
            	//vm.availableFunctionsNames = vm.allFunctionsNames;// gives duplicate values

            		vm.availableFunctionsNames= _.uniq(vm.allFunctionsNames);
            
            		vm.availableFunctionIds = vm.allFunctionsIds;
            }
        
		}

        $rootScope.$on('FunctionsForUser', function(event, user, functions, userUpdate, userView) {
        	var userFunctions  = angular.copy(functions);
        	vm.user = user;
        	vm.userUpdate = userUpdate;
        	vm.infoView = userView;
        	
        });
        
        vm.clearUserFunctionExclusions = function(){
        	vm.getFunctionsByUserResp();
        }
        vm.backToEmpList = function(){
			$localStorage.isFunctionExclusion = true;
			$localStorage.isUserResponsibility = true;
            $("#functionExclusion").removeClass("dynatree-active");
            $(".Employee_List").addClass("dynatree-active");
            if($localStorage.globalSearch){
				window.location.href = "#/employees-resp-global";
			}else
            	window.location.href = "#/employees-resp";
        }
        function getFunctionsByUserResp() {
        	if(vm.employeeId !== undefined && vm.employeeId !== null){
	        	ResponsibilityService.getResponsibilities(vm.employeeId).then(function(data){
	            			vm.userResp = data;
	            			   var selectedUserResp = angular.copy(vm.userResp);
	            	            var selectedResps = _.pluck(selectedUserResp, 'osiResponsibilities');
	            	            var respIds = _.pluck(selectedResps, 'id');
	            	            var respUser = {
	            	                userRespIds : respIds
	            	            };
	            	            functionExclusionService.getFunctionsByRespIds(respUser).then(function(result) {
	            	               
	            	                initController(result);
	            	            });
	            	}).catch(function(data){
	            		
	            		$rootScope.isTrascError = true;
	        			FlashService.Error("Error occured while saving");
	        			$timeout(function() {
	        				$rootScope.isTrascError = false;
	        			}, 4000);
	            	});
        	}
        };
        
        $rootScope.$on('FunctionsForUpdatedUser', function(event, functions) {
        	var userFunctions  = angular.copy(functions);
        	initController(userFunctions);
        });
        
        $rootScope.$on('openChangesEvent', function(event,rootEvent) {
        	vm.exclFunctionsNames=[];
        	vm.excludedFuncs = [];
                if($scope.myForm!=undefined){
                    if ($scope.myForm.$dirty)
       				$scope.myForm.$setPristine();
                }
       			 
       	});
		$scope.previous = function() {
			 var user = SharedDataService.getUserInfo();
			vm.functionNames=[];
			 if(user.osiUserFuncExcls!=undefined){
				 angular.forEach(user.osiUserFuncExcls, function (val, key){
					 vm.functionNames.push(val.osiFunctions.funcName);
				 });
			 }
			 //vm.functionNames = _.pluck(_.filter(vm.allFunctions, function(func){ return _.contains( vm.functionIdArray,func.id)}),"funcName");
             if (_.difference(vm.exclFunctionsNames, vm.functionNames).length>0 ||_.difference(vm.functionNames,vm.exclFunctionsNames).length>0)
             {
            	   vm.deselectTabs();
                   $rootScope.tab3="selectedInfoTab";
                   $rootScope.confirmationHeading=appConstants.functionExclusion;
                   $rootScope.isFunctionExclusionDataModified= true;
                   $rootScope.showTabSwitchModal();
             }
             else{
                   angular.element('[data-target="#userResp-tab"]').tab('show');
                   vm.deselectTabs();
                   $rootScope.isFunctionExclusionDataModified= false;
                   $rootScope.tab2="selectedInfoTab";
             }
		};
		vm.excludeFunction = function(func) {
			if(!angular.isUndefined(func)) {
				vm.availableFunctionsNames = _.difference(vm.availableFunctionsNames,func);
				vm.exclFunctionsNames = _.union(vm.exclFunctionsNames,func);
				var user= SharedDataService.getUserInfo();
				vm.functionNames=[];
				angular.forEach(user.osiUserFuncExcls, function(value, key){
						vm.functionNames.push(_.find(vm.exclFunctionsNames, function(func){ return func == value.osiFunctions.funcName}));
			 	})
				if(_.difference(vm.exclFunctionsNames, vm.functionNames).length>0 ||_.difference(vm.functionNames,vm.exclFunctionsNames).length>0){
					   $rootScope.isFunctionExclusionDataModified= true;
				}else{
					 $rootScope.isFunctionExclusionDataModified= false;
				}
				vm.clearFields();
			}
		};

		vm.excudedAllFunction = function() {
			vm.exclFunctionsNames = _.union(vm.exclFunctionsNames, vm.availableFunctionsNames);
			vm.availableFunctionsNames = [];
			var user= SharedDataService.getUserInfo();
			vm.functionNames=[];
			angular.forEach(user.osiUserFuncExcls, function(value, key){
					vm.functionNames.push(_.find(vm.exclFunctionsNames, function(func){ return func == value.osiFunctions.funcName}));
		 	})
			if(_.difference(vm.exclFunctionsNames, vm.functionNames).length>0 ||_.difference(vm.functionNames,vm.exclFunctionsNames).length>0){
				   $rootScope.isFunctionExclusionDataModified= true;
			}else{
				 $rootScope.isFunctionExclusionDataModified= false;
			}
			vm.clearFields();
		};
              
		vm.includeFunction = function(func) {
			if(!angular.isUndefined(func)) {
				vm.exclFunctionsNames = _.difference(vm.exclFunctionsNames,func);
				vm.availableFunctionsNames = _.union(vm.availableFunctionsNames,func);
				var user= SharedDataService.getUserInfo();
				vm.functionNames=[];
				angular.forEach(user.osiUserFuncExcls, function(value, key){
					vm.functionNames.push(_.find(vm.exclFunctionsNames, function(func){ return func == value.osiFunctions.funcName}));
				})
				if(_.difference(vm.exclFunctionsNames, vm.functionNames).length>0 ||_.difference(vm.functionNames,vm.exclFunctionsNames).length>0){
					   $rootScope.isFunctionExclusionDataModified= true;
				}else{
					 $rootScope.isFunctionExclusionDataModified= false;
				}
				vm.clearFields();
			}
		};

		vm.includeAllFunction = function() {
			vm.availableFunctionsNames =  _.uniq(vm.allFunctionsNames);
			vm.exclFunctionsNames = [];
			var user= SharedDataService.getUserInfo();
			vm.functionNames=[];
			angular.forEach(user.osiUserFuncExcls, function(value, key){
					vm.functionNames.push(_.find(vm.exclFunctionsNames, function(func){ return func == value.osiFunctions.funcName}));
		 	})
			if(_.difference(vm.exclFunctionsNames, vm.functionNames).length>0 ||_.difference(vm.functionNames,vm.exclFunctionsNames).length>0){
				   $rootScope.isFunctionExclusionDataModified= true;
			}else{
				 $rootScope.isFunctionExclusionDataModified= false;
			}
			vm.clearFields();
		};
		
		vm.clearFields = function() {
			vm.availableFuncs = [];
			vm.excludedFuncs = [];
		};

		$scope.functionSubmit = function() {
			vm.diff=0;
			var exclFunctionIds = [];
			angular.forEach(vm.exclFunctionsNames, function(value){
				angular.forEach(vm.allFunctions, function(val){
					if(val.funcName == value)
						exclFunctionIds.push(val.id);
				});
			});
//			vm.functionIdArray=exclFunctionIds;
			$scope.$emit('ExcludedFunctions', exclFunctionIds);
			vm.availableFunctionIds = vm.allFunctionsIds.diff(exclFunctionIds);
			vm.funExclList = [];
			var user = SharedDataService.getUserInfo();
			if(exclFunctionIds.length > 0){
				angular.forEach(exclFunctionIds, function(value) {
					vm.OsiUserFuncExcl = { osiFunctions : { id:value},
						createdBy :  vm.employeeId,
						createdDate : new Date(),
						employeeId: vm.employeeId	};
					
					console.log(vm.excludedFunctions);
					
					angular.forEach(vm.excludedFunctions, function(exlcFuncion){
						console.log(exlcFuncion.id);
						if(exlcFuncion.osiFunctions.id == value )
							vm.OsiUserFuncExcl.id = exlcFuncion.id;
					});
					
					vm.funExclList.push(vm.OsiUserFuncExcl);
				});
			}else{
				if(vm.employeeId !== null && vm.employeeId !== undefined){
					vm.OsiUserFuncExcl = {
							employeeId: vm.employeeId	};
					vm.funExclList.push(vm.OsiUserFuncExcl);
				}
			}
			user.osiUserFuncExcls = vm.funExclList;
			if(vm.employeeId !== null && vm.employeeId !== undefined){
				functionExclusionService.saveFunctionExclusion(user.osiUserFuncExcls).then(function(data){
					
					$rootScope.isTrascError = true;
					var msg = appConstants.successMessage;
					FlashService.Success(msg);
					$timeout(function () {
						$rootScope.isTrascError=false;
					}, 2000);
					
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
				FlashService.Error(appConstants.exceptionMessage);
				$timeout(function() {
					$rootScope.isTrascError = false;
				}, 4000);
			}
			functionExclusionService.getExclFunctions( vm.employeeId).then(function(result) {
                vm.excludedFunctions = angular.copy(result);
			});
			SharedDataService.setUserInfo(user);
			/*SharedDataService.setUserInfo(user);
            $scope.myForm.$setPristine();
            angular.element('[data-target="#operation-tab"]').tab('show');
   			vm.deselectTabs();
            $rootScope.tab4="selectedInfoTab";*/
            $rootScope.isFunctionExclusionDataModified= false;
		};

		$scope.cancel = function() {
                    $window.location.reload();
		};

		Array.prototype.diff = function(a) {
			return this.filter(function(i) {
				return a.indexOf(i) < 0;
			});
		};
		
		vm.sortingFn = function( name ) {
			console.log('in sort ', name);
		    return name.toLowerCase();
		};
                
		 $rootScope.$on('saveChangesEvent', function(event,rootEvent,tabname) 
	     {
			  if(tabname=="tab3"){
						vm.deselectTabs();
			    		$rootScope.tab3="selectedInfoTab";
			    		var user = SharedDataService.getUserInfo();
			  }
		});
        
        vm.nextPage = function(){
        	angular.element('[data-target="#operation-tab"]').tab('show');
        	vm.deselectTabs();
        	$rootScope.tab4="selectedInfoTab";
        }
        
        
	}
        
        //This event is used to save changes we made in the current form . Event is immeted from userlist controller.
        
})();