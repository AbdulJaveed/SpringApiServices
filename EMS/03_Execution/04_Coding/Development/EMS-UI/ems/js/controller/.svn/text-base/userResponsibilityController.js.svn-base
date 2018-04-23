
(function() {
'use strict';
angular.module('myApp.userResponsibilityController', []).controller(
        'userResponsibilityController', userResponsibilityController);
userResponsibilityController.$inject = ['$timeout', 'appConstants','$location', '$window','$localStorage', 'ResponsibilityService', 'osiResponsibilitiesService', '$scope', 'SharedDataService', '$rootScope', '$filter', 'CommonService','FlashService','functionExclusionService', 'employeeBasicInfoService', 'organizationService','configData'];
function userResponsibilityController($timeout, appConstants,$location, $window,$localStorage, ResponsibilityService, osiResponsibilitiesService, $scope, SharedDataService, $rootScope, $filter, CommonService, FlashService, functionExclusionService, employeeBasicInfoService,organizationService,configData) {

	var vm=this;
	
	vm.employeeId = $localStorage.employeeId;
	vm.empId = $localStorage.empId;
	$scope.submitEnable = true;
    $scope.result = [];
    $scope.resp = [];
    $scope.dateError = false;
    $scope.osiUser = undefined;
    $scope.responsibilities = [];
    $scope.selectedResp = [];
    $scope.wrongSelectedResp = false;
    $rootScope.confirmationHeading="";
    $scope.userResp = [{}];
    $scope.userUpdate = false;
    $scope.userView = false;
    $scope.checkDefaultValue = false;
    $scope.todaysDate = new Date().toDateString();
	 $rootScope.isTrascError = false;
    var  dateError=0;
    vm.EndDateError=0;
    $scope.opened = {};
    vm.getResponsibilities = getResponsibilities;
    vm.getOsiUserResponsibilities = getOsiUserResponsibilities;
    $scope.open = function (index) {
        $scope.opened[index] = true;

    };
    $scope.getDatePicker = function(id){
    	document.getElementById(id).focus();
    }
    $rootScope.isUserResponsibilityDataModified= false;

    initController();
    $rootScope.$on('customEvent', function(event, user,userUpdate,userView) {
		    	 $scope.osiUser=user;
		    	 $scope.userUpdate=userUpdate;
		    	 $scope.userView=userView;

//                         if(!userUpdate && !userView){
//                             $rootScope.isUserResponsibilityDataModified= false;
//                         }
    });
    
	$scope.$watch('vm.respForm.$invalid', function() {
		$rootScope.inValidUserResp = vm.respForm.$invalid;
	}, true);
	
	$scope.$watch('vm.respForm.$dirty', function() {
			$rootScope.isUserResponsibilityDataModified= true;
            $rootScope.confirmationHeading=appConstants.userResponsibility;
	}, true);
	
    $rootScope.$on('openChangesEvent', function(event,rootEvent) 
    {
        if(vm.respForm!= undefined){
            vm.respForm.$setPristine();
        }
    	//vm.respForm.$setPristine();
    });
    vm.deselectTabs=function(){
        $rootScope.tab1="";
        $rootScope.tab2="";	
        $rootScope.tab3="";
        $rootScope.tab4="";
        $rootScope.tab5="";
        $rootScope.tab6="";
        $rootScope.personalInfoActive="";
        $localStorage.isUserResponsibility = false;
    };
    function initController() {
    	if(vm.empId !==  undefined && vm.empId !== null){
    		$localStorage.isUserResponsibility = false;
	    	if($scope.userUpdate === true || $scope.userView ===true){
	    		vm.userResp = $scope.osiUser.osiRespUsers;
	    		for(var i = 0; i < vm.userResp.length; i++) {
	    			$scope.userResp[i].startDate = vm.formatDate(new Date(vm.userResp[i].startDate));
	    			$scope.userResp[i].endDate = vm.formatDate(new Date(vm.userResp[i].endDate));
	    		}
	    			
	    		if(vm.userResp.length === 0) {
	    			vm.userResp = [{startDate:'',endDate:''}];
	    			
	    			
	    		}
	    	} else {
	    		vm.userResp = [{startDate:'',endDate:''}];
            }
            
                      
	    	vm.getResponsibilities();
	    	vm.getOsiUserResponsibilities();
    	}else{
    		$localStorage.isUserResponsibility = true;
    		$("#userResponsibility").removeClass("dynatree-active");
    		$(".Employee_List").addClass("dynatree-active");
    		
    		window.location.href = "#/employees";
    	}
    };
    vm.backToEmpList = function(){
        $localStorage.isUserResponsibility = true;
        $("#userResponsibility").removeClass("dynatree-active");
        $(".Employee_List").addClass("dynatree-active");
        
        window.location.href = "#/employees";
    }
    vm.clearUserResponsibilities = function(){
    	initController();
    }
    function getOsiUserResponsibilities(){
    	if(vm.employeeId !== undefined && vm.employeeId !== null){
	    	ResponsibilityService.getResponsibilities(vm.employeeId).then(function(data){
	    		if(data.length > 0)
	    			vm.userResp = data;
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
	    		/*$rootScope.isTrascError = true;
				FlashService.Error("Error occured while saving");
				$timeout(function() {
					$rootScope.isTrascError = false;
				}, 4000);*/
	    	});
    	}
    	
    }
    vm.formatDate = function(date) {
    	if((date === undefined)|| (date === null) || (date === "")){
            return null;
        }
        else{
            var formattedDate= $filter('date')(new Date(date), 'dd-MMM-yyyy');
        }
        return formattedDate;
    }  

    $scope.eopened = {};
    $scope.eopen = function (index) {
        $scope.eopened[index] = true;

    }
    ;

    $scope.previous = function() {
        $timeout(function () {
            if (vm.respForm.$dirty)
            {
            	vm.deselectTabs();
                $rootScope.tab2="selectedInfoTab";
                $rootScope.isUserResponsibilityDataModified= true;
                $rootScope.confirmationHeading=appConstants.userResponsibility;
                $rootScope.showTabSwitchModal();
            }
            else{
            	$rootScope.isUserResponsibilityDataModified= false;
                angular.element('[data-target="#info-tab"]').tab('show');
                vm.deselectTabs();
                $rootScope.tab1="selectedInfoTab";
            }
        });
    }

    $scope.next = function() {
    	vm.respForm.$setPristine();
    	$rootScope.isUserResponsibilityDataModified= false;
    	$rootScope.inValidUserResp = vm.respForm.$invalid;
        dateError=0;
        var emptyError= false;
        angular.forEach(vm.userResp, function(value, key){
        	if(value.osiResponsibilities == null || value.startDate == null || value.startDate == ''){
        		emptyError= true;
        	}
        })
        if(emptyError){
        	$rootScope.isTrascError = true;
			FlashService.Error("Please fill required fields");
			$timeout(function() {
				$rootScope.isTrascError = false;
			}, 5000);
        }else{
            isInvalidDate();
        	angular.forEach(vm.userResp, function(value, key) {
        		$scope.checkDuplicateResp(value.osiResponsibilities);
        	});
        	if (vm.userResp.endDate!= undefined && vm.userResp.endDate !="" && vm.respForm.$invalid) {
			$rootScope.isTrascError = true;
			FlashService.Error("Invalid End Date");
			$timeout(function() {
				$rootScope.isTrascError = false;
			}, 5000);
		}else if (dateError == 1) {
                            $rootScope.isTrascError = true;
                            FlashService.Error("Invalid Date");
                            $timeout(function() {
                                    $rootScope.isTrascError = false;
                            }, 3000);
                }else
                {
			if ($scope.wrongSelectedResp) {
				$rootScope.isTrascError = true;
				FlashService.Error("You can not enter same Responsibility more than one time");
				$timeout(function() {
					$rootScope.isTrascError = false;
				}, 4000);
			} 
			else {
				var userRespList = [];
				angular.forEach(vm.userResp, function(value, key) {
					var userRespObj = {};
					
					userRespObj.employeeId =  vm.employeeId;
					userRespObj.id=value.id;
					if (value.defaultResp == null) {
						value.defaultResp = 0;
					}
					
					userRespObj.defaultResp = value.defaultResp;
					userRespObj.createdBy = value.createdBy;
					userRespObj.createdDate = value.createdDate;
					
					userRespObj.osiResponsibilities = {};
					userRespObj.osiResponsibilities.id = angular.fromJson(value.osiResponsibilities.id);
					userRespObj.startDate = value.startDate;
					userRespObj.endDate = value.endDate;
					userRespList.push(userRespObj);
					
				});
				
				ResponsibilityService.setResponsibilities(userRespList).then(function(data){
					
					$rootScope.isTrascError = true;
					FlashService.Success(appConstants.successMessage);
					$timeout(function() {
						$rootScope.isTrascError = false;
					}, 4000);
					
				}).catch(function(data){
					
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

				/*var selectedUserResp = angular.copy(userRespList);
				var selectedResps = _.pluck(selectedUserResp, 'osiResponsibilities');
				var respIds = _.pluck(selectedResps, 'id');
				var respUser = {
					userRespIds : respIds
				};
				
				functionExclusionService.getFunctionsByRespIds(respUser).then(function(result) {
					$scope.$emit('FunctionsForUpdatedUser', result);
					$scope.osiUser.osiRespUsers = userRespList;
					SharedDataService.setUserInfo($scope.osiUser);
					$rootScope.isUserResponsibilityDataModified= false;
                    vm.respForm.$setPristine();
					$timeout(function() {
						angular.element('[data-target="#funcExclu-tab"]').tab('show');
						vm.deselectTabs();
						$rootScope.tab3 = "selectedInfoTab";
					});
				});*/
			}
		}
        } 	
	};
	/*function getFormattedDate(dateStr) {
    	if(dateStr==null)
    		return null;
    	else {
	    	var dArr = dateStr.split("-");  
	    	return dArr[2]+ "-" + dArr[0] + "-" + dArr[1];
    	}
    }*/
        function isInvalidDate(){
            angular.forEach($scope.userResp, function(value, key) {
                if((((value.startDate != undefined) && (value.startDate !="")) && (new Date(value.startDate).isValid() == false)) ||
                               (((value.endDate != undefined) && (value.endDate !="" ))  && (new Date(value.endDate).isValid() == false))){
                    dateError=1;
                }  
            });
        }
                
        Date.prototype.isValid = function () {
            // An invalid date object returns NaN for getTime() and NaN is the only
            // object not strictly equal to itself.
            return this.getTime() === this.getTime();
        };
        
    $scope.addRow = function (userResp) {   
      
        if (((userResp.osiResponsibilities != null) && (userResp.startDate != undefined )) && vm.EndDateError !=1)
        {
        	  vm.userResp.push({id:null,createdBy:null,osiResponsibilities: null, defaultResp:null, startDate: '', endDate: ''});
        }else
        {
     	   $rootScope.isTrascError = true;
           FlashService.Error("Please fill all necessary fields and Check Dates.");
           $timeout(function () {
           	$rootScope.isTrascError=false;
           }, 3000);
	
        }
    };
    
     function getResponsibilities() {
        osiResponsibilitiesService.getAllOsiResponcibilitiesList().then(function (result) {
            $scope.responsibilities = result;
        });
    };

    $scope.removeRow = function (index) {        
        vm.userResp.splice(index, 1);
        if(vm.userResp.length === 0){
            vm.userResp=[{startDate:''}];
        }
    };
    
    $scope.updateSelection = function(position, entities) {
      angular.forEach($scope.userResp, function(subscription, index) {
        if (position != index) 
          subscription.defaultResp = false;
      });
    };            
    
    $scope.checkDuplicateResp = function(selResp,index){                
        angular.forEach(vm.userResp, function(value, key) {
            $scope.selectedResp.push(value.osiResponsibilities.respName);
        });
        if($scope.selectedResp.indexOf(selResp.respName)!=$scope.selectedResp.lastIndexOf(selResp.respName)){
                $scope.wrongSelectedResp = true;
            	$rootScope.isTrascError = true;
				FlashService.Error("You can not enter same Responsibility more than one time");
				//$scope.userResp[index]='';
				$timeout(function() {
					$rootScope.isTrascError = false;
				}, 4000);
        }
        else{
            $scope.wrongSelectedResp = false;
        }
        $scope.selectedResp = [];
    }
    
    //This event is handled to save the changes user done. Event emmited from userlist controller.
    $rootScope.$on('saveChangesEvent', function(event,rootEvent,tabname) 
	{
    	if(tabname==appConstants.tab2){
            vm.deselectTabs();
            $rootScope.tab2="selectedInfoTab";
            if(vm.respForm!= undefined){
    		if (vm.respForm.$dirty)
    		{
    			rootEvent.stopPropagation();
    			$rootScope.showTabSwitchModal();
    		}
            }
    	}
     });
    vm.dateChanged = function(index){
    	$scope.userResp[index].endDate="";
    }
    vm.startDateError= false;
    vm.checkDate = function(userResp) {
    	if(userResp.startDate == undefined){
    		$rootScope.isTrascError = true;
            FlashService.Error("Invalid Start Date");
            $timeout(function () {
                $rootScope.isTrascError = false;
            }, 3000);
            userResp.startDate = "";
    	}
    	if((new Date(userResp.startDate).setHours(0,0,0,0)) < (new Date().setHours(0,0,0,0))){
            $rootScope.isTrascError = true;
            FlashService.Error("Start date cannot be less than today's date");
            $timeout(function () {
                $rootScope.isTrascError = false;
            }, 3000);
            userResp.startDate = "";

    }
    	vm.compareDate(userResp);
    }
    vm.checkEndDate= function(userResp){
    	if(userResp.endDate == undefined){
    		$rootScope.isTrascError = true;
            FlashService.Error("Invalid End Date");
            $timeout(function() {
   				$rootScope.isTrascError = false;
   			}, 3000);
            userResp.endDate = "";
    	}    	 
    	vm.compareDate(userResp);
    }
    vm.compareDate = function(userResp){
    	userResp.endDate = configData.maxEffecEndDate;
    	if (userResp.endDate != null
   				&& (new Date(userResp.startDate).getTime() >= new Date(userResp.endDate).getTime())) {
   			$rootScope.isTrascError = true;
   			FlashService.Error("End date should be greater than start date");
   			$timeout(function() {
   				$rootScope.isTrascError = false;
   			}, 3000);
   			userResp.endDate = "";
   		}
    }
    vm.nextPage = function(){
    	angular.element('[data-target="#funcExclu-tab"]').tab('show');
    	vm.deselectTabs();
		$rootScope.tab3 = "selectedInfoTab";
    }
    };
})();