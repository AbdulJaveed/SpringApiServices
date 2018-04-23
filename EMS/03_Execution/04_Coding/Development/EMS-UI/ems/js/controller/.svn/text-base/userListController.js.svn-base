'use strict';

angular.module('myApp.userListController', []).controller('userListController', userListController);

userListController.$inject = ['$scope','appConstants','AuthenticationService',
    'SharedDataService','$route', '$timeout','$rootScope', '$window', '$location', 
    '$http', '$sessionStorage', 'userService', 'FlashService',
    '$localStorage','$filter','CommonService','functionExclusionService'];
function userListController($scope,appConstants,AuthenticationService,
    SharedDataService,$route,$timeout, $rootScope, $window, $location, $http, 
    $sessionStorage, userService, FlashService, $localStorage, $filter, CommonService, functionExclusionService) {
    var vm=this;
    $rootScope.disableOtherTabs=true;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $rootScope.inValidUser = true;
    $rootScope.inValidUserResp = true;
    $scope.basicSearchDiv = true;
    $scope.advancedSearchDiv = false;
    vm.isButtionDisableForEmail = true;
    vm.isButtionDisableForNmae = true;
    $scope.lookupdata = "";
    $scope.valuedata = [];
    $scope.user={};
    $scope.user.ids=[];
    $scope.rowSize = 8;
    vm.nextButton=true;
    $scope.userToEdit = {};
    $rootScope.confirmationHeading="";
    $scope.validUser = false;
    $scope.successTextAlert = "";
    $scope.showSuccessAlert = false;
    $scope.failureTextAlert = "";
    $scope.showFailureAlert = false;
    var selectedRowsIndexes = [];
    var selectedEvent='';
    $scope.rows = [];
    $scope.disable_Edit = true;
    $scope.disable_View = true;
    $scope.disable_Delete = true;
    $rootScope.userdata=[];
    $rootScope.isPersonalInfoDataModified= false;
    $rootScope.isUserResponsibilityDataModified= false;
    $rootScope.isFunctionExclusionDataModified= false;
    $rootScope.isOperationExclutionDataModified= false;
    $rootScope.isInvOrgAssignmentDataModified= false;
    $rootScope.isUserRolesAndDeptDataModified= false;
    $rootScope.isCategoryAndApproverModified = false;
    $rootScope.isCategoryAndApproverForPRModified = false;
    $rootScope.isCategoryAndApproverForPOModified = false;
    $rootScope.isCategoryAndApproverForMRModified = false;
    var dateDirtyError=0;
    var dateValidError=0;
    var  dirtyDateUserInfo = 0;
    $scope.searchUser={};
    vm.basicAdvancesearchVariable = false;
    vm.searchDisableVar = true;
    vm.basicAdvanceButtonHeading = "Advanced";
    vm.userInvOrgDeptRoleCombinationList =[];
    vm.isduplicateRoleOrgDept = false;
    vm.duplicateObject={};
    vm.existingInventoryOrganizations=[];
    vm.existingInvOrgIdArray=[];
    vm.existingDepartments=[];
    vm.existingDepartmentIds=[];
    vm.inProcess = false;
    
    $rootScope.go = function (path) {
        $location.url(path);
    };
    
    $scope.sort = function(keyname) {
        if ($scope.userdata != null) {
            $scope.sortKey = keyname;
            $scope.reverse = !$scope.reverse;
        }
    };
    
   $scope.prepareExistingArrays=function(){
	   userService.getUser($scope.userToEdit.id).then(function (result) {
	   var user = result;
	   vm.checkapprovalExisted(user.osiUserRoles[0].roleId);
	   vm.existingUserRoleId=user.osiUserRoles[0].roleId.roleId;
	   vm.existingInventoryOrganizations=user.osiInvOrgUsers;
       vm.existingDepartments=user.osiUserDepartment;
       vm.existingCombinationsArray=[];
       angular.forEach(vm.existingInventoryOrganizations, function(inventoryMainObject){
          	vm.existingInvOrgIdArray.push(inventoryMainObject.osiInventoryOrg.invOrgId);
        });
       angular.forEach(vm.existingDepartments, function(departmentObject){
             	vm.existingDepartmentIds.push(departmentObject.departmentId);
            });
	   });
   }
    
    $scope.sendEvent = function() {
        var user = {};
        userService.getUser($scope.userToEdit.id).then(function (result) {
            user = result;
            //console.log("user..."+JSON.stringify(user));
            convertDatesToPhillipinesFormat(user);
            user.password=AuthenticationService.Base64Decode(user.password);
            SharedDataService.setUserInfo(user);
            vm.getFunctionsByUserResp(user);
        }).catch(function(error){
        	var msg = appConstants.exceptionMessage;
   		  if(error.data.httpStatus){ 
   			  msg=error.data.errorMessage; 
   		  }
   			$rootScope.isTrascError = true;
            FlashService.Error(msg);
        	$scope.failureTextAlert =msg;        	
            $timeout(function () {
           	 $scope.failureTextAlert = false;
           	 $window.location.reload();
            }, 5000);
   		});;
    };
    
    vm.checkapprovalExisted = function (role){
       vm.currentUserRoleName=role.roleName;
	   userService.getUserRoleAssignCount(role.roleId).then(function (result) {
            var count=result;
            if(count>0){
            	$rootScope.isApprovalUserExist = true; 
        	}else{
        		$rootScope.isApprovalUserExist = false; 
        	}
        });
        
   };

    vm.getFunctionsByUserResp = function(user) {
        var selectedUserResp = angular.copy(user.osiRespUsers);
        var selectedResps = _.pluck(selectedUserResp, 'osiResponsibilities');
        var respIds = _.pluck(selectedResps, 'id');
        var respUser = {
            userRespIds : respIds
        };
        functionExclusionService.getFunctionsByRespIds(respUser).then(function(result) {
            $scope.$emit('customEvent', user, vm.userUpdate, vm.userView);
            $scope.$emit('FunctionsForUser', user, result, vm.userUpdate, vm.userView);
            vm.openTab();
        });
    };
	
     $scope.sendCreateEvent = function() {
        var user = {};
        var osiConfigParametersDTO = {'configName':'DEFAULT_PASSWORD'};
            userService.getUsersDefaultPassword(osiConfigParametersDTO).then(function (data) {
            user.password=AuthenticationService.Base64Decode(data.configValue);
            user.emailId="";
            user.mobileNumber="";
            SharedDataService.setUserInfo(user);
            $scope.$emit('customEvent', user, vm.userUpdate, vm.userView);
            vm.openTab();
        }).catch(function(error){
        	var msg = appConstants.exceptionMessage;
   		  if(error.data.httpStatus){ 
   			  msg=error.data.errorMessage; 
   		  }
            $rootScope.isTrascError = true;
            FlashService.Error(msg);
            $scope.failureTextAlert =msg;        	
            $timeout(function () {
             $scope.failureTextAlert = false;
             $window.location.reload();
            }, 5000);
	});
        

    };
    
    vm.openTab = function() {
    	vm.inProcess = true;
        $rootScope.isPersonalInfoDataModified= false;
        $rootScope.isUserResponsibilityDataModified= false;
        $rootScope.isFunctionExclusionDataModified= false;
        $rootScope.isOperationExclutionDataModified= false;
       /* $rootScope.isInvOrgAssignmentDataModified= false;
        $rootScope.isUserRolesAndDeptDataModified= false;*/
        /*$rootScope.isCategoryAndApproverModified = false;
        $rootScope.isCategoryAndApproverForPRModified = false;
        $rootScope.isCategoryAndApproverForPOModified = false;
        $rootScope.isCategoryAndApproverForMRModified = false;*/
        $('#userlist-model').modal({backdrop: 'static', keyboard: false}); 
        angular.element('[data-target="#info-tab"]').tab('show');
        $rootScope.tab1 = "selectedInfoTab";
    };
	    
    $scope.init = function () {
    	$rootScope.isApprovalUserExist=false;
        userService.getUserInitially().then(function (data) {
            angular.forEach(data,function(value, key) {	
                if(value.active == 1){
                    value.active="Active";
                }else{
                    value.active="Inactive";
                }
            });
            $scope.userdata = data;
            //$rootScope.userdata=data;
        });
        
        /*userService.getAllUser().then(function(data1){
        	 $rootScope.userdata=data1;
        });*/

        this.viewAlllookupsDetail();
        
        //AI: Commented this part because of performance issue.
        /*userService.getUsersInvOrgRoleDeptCombination().then(function (data) {
            vm.userInvOrgDeptRoleCombinationList = data;
        });*/
        
    };

    $scope.viewuser = function () {
    	var selectedRow = getSelectedRows();
        var user = selectedRow[0];
        $rootScope.buttonName="Next";
        if (selectedRow.length == 1) {
            vm.userView = true;
            vm.userUpdate = false;
            $rootScope.disableOtherTabs = false;
            user.confirmPassword = user.password;
            $scope.user = user;
            $scope.userToEdit = user;
            SharedDataService.setUserInfo($scope.user);
            $scope.sendEvent();
            vm.deselectTabs();
            $rootScope.tab1="selectedInfoTab";
            vm.openTab();
        }
    };

    $scope.deleteOsiUser = function ()  {
    	var selectedRow = getSelectedRows();
        $scope.user = selectedRow[0];
    	$scope.user.ids=[$scope.user.id];
    	userService.deleteUser($scope.user).then(function (data) {
            if (data.httpStatus == 200) {
               $scope.successTextAlert ="User Deleted Successfully";
               $scope.showSuccessAlert = true;
            } else {
                $scope.successTextAlert ="Failed TO Delete User";
                $scope.failureTextAlert = true;
            }
            $timeout(function () {
                $scope.showSuccessAlert = false;
                $scope.failureTextAlert = false;
                $window.location.reload();
            }, 3000);
        });
    };
    
    $scope.edituser = function () {
    	var selectedRow = getSelectedRows();
        $rootScope.buttonName="Save & Next";
        $scope.userToEdit = selectedRow[0];
        vm.existingInvOrgIdArray=[];
        vm.existingDepartmentIds=[];
        $scope.prepareExistingArrays();
    	vm.userView=false;
    	$rootScope.disableOtherTabs=false;
    	vm.userUpdate=true;
    	$scope.sendEvent();
    	vm.deselectTabs();
    	$rootScope.tab1="selectedInfoTab";
    };
    
    function getSelectedRows() {
        var selectedRows = [];
        angular.forEach(selectedRowsIndexes, function(rowIndex) {
            selectedRows.push(rowIndex);
        });
        return selectedRows;
    }
    
    $scope.openuserlistmodal = function (e) {
    	$rootScope.buttonName="Save & Next";
        $rootScope.adduser=true;
        $rootScope.disableOtherTabs=false;
        vm.userView=false;
    	vm.userUpdate=false;
    	$rootScope.inValidUser = true;
    	$rootScope.inValidUserResp = true;
    	$scope.sendCreateEvent();
    	vm.deselectTabs();
        $rootScope.tab1="selectedInfoTab";
    };

    $scope.viewAlllookupsDetail = function () {
        $rootScope.lookupHeader = {
            id: 0,
            lookupName: 0,
            lookupCode: 1,
            osiLookupValueses: []
        };
    };
    $scope.clearSelectedRow = function() {
	        // clearing row selection
	      resetSelection();
		  $scope.isSelectedRow = null;
		  toggleButtons();
	}
     $scope.basicAndAdvanceSearch = function(){
         vm.basicAdvancesearchVariable = !vm.basicAdvancesearchVariable;
         if(!vm.basicAdvancesearchVariable){
             vm.basicAdvanceButtonHeading = "Advanced";
             $scope.searchUser = {};
             vm.searchDisableVar = true;
         }else{
             vm.basicAdvanceButtonHeading = "Basic";
         }
     };
    
//    vm.searchEnable = function (){
//        if( _.isEmpty($scope.searchUser) || (($scope.searchUser.userName == "" || $scope.searchUser.userName == undefined)&& ($scope.searchUser.emailId == "" || $scope.searchUser.emailId ==  undefined) 
//                && ($scope.searchUser.empNumber == "" || $scope.searchUser.empNumber == undefined )&& ($scope.searchUser.firstName == "" || $scope.searchUser.firstName == undefined )
//                && ($scope.searchUser.lastName == "" || $scope.searchUser.lastName == undefined))){
//            vm.searchDisableVar = true;
//        }else{
//            vm.searchDisableVar = false;
//        }        
//    };
    $scope.searchUsers = function() {
        
        if(_.isEmpty($scope.searchUser) || (($scope.searchUser.userName == "" || $scope.searchUser.userName == undefined)&& ($scope.searchUser.emailId == "" || $scope.searchUser.emailId ==  undefined) 
                && ($scope.searchUser.empNumber == "" || $scope.searchUser.empNumber == undefined )&& ($scope.searchUser.firstName == "" || $scope.searchUser.firstName == undefined )
                && ($scope.searchUser.lastName == "" || $scope.searchUser.lastName == undefined))){
                
            userService.getAllUser().then(function (data) {
                angular.forEach(data,function(value) {	
                   if(value.active == 1){
                       value.active="Active";
                   }else{
                       value.active="Inactive";
                   }
                })
                $scope.userdata = data;
                $rootScope.userdata=data;
            }).catch(function(resp){
            	var msg = appConstants.exceptionMessage;
	       		  if(error.data.httpStatus){ 
	       			  msg=error.data.errorMessage; 
	       		  }
       			$rootScope.isTrascError = true;
                FlashService.Error(msg);
            	$scope.failureTextAlert =msg;
                $timeout(function () {
	               	 $scope.failureTextAlert = false;
	               	 $window.location.reload();
                }, 5000);
       		});
            this.viewAlllookupsDetail();
        } else{
            userService.getAllSearchedUsers($scope.searchUser).then(
                function(data) {
                    angular.forEach(data,function(value) {	
                    if(value.active == 1){
                            value.active="Active";
                    }else{
                            value.active="Inactive";
                    }
                })
                $scope.userdata = data;
            }).catch(function(resp){
            	var msg = appConstants.exceptionMessage;
       		  if(error.data.httpStatus){ 
       			  msg=error.data.errorMessage; 
       		  }
       			$rootScope.isTrascError = true;
                FlashService.Error(msg);
                $scope.failureTextAlert =msg;
                 $timeout(function () {
                	 $scope.showSuccessAlert = false;
                	 $scope.failureTextAlert = false;
                	 $window.location.reload();
                 }, 5000);
       		});;	
        }
        
    };
    
    $scope.clearSearch = function () {
        $scope.searchUser={};
        vm.searchDisableVar = true;
        $scope.clearSelectedRow();
        $scope.init();
    };
    
    $scope.init();
    
    $scope.categoryTab = function(){
    	 angular.element('[data-target="#purchaseRequisition-tab"]').tab('show');
    }
    
    $scope.selectRow = function(event, rowIndex) {
		selectedEvent = event;
		if (event.ctrlKey) {
//			changeSelectionStatus(rowIndex);
		} else if (event.shiftKey) {
			// selectWithShift(rowIndex);
		} else {
			selectedRowsIndexes = [ rowIndex ];
			toggleButtons();
		}
	};

	$scope.isRowSelected = function(rowIndex) {
		return selectedRowsIndexes.indexOf(rowIndex) > -1;
	};

	function changeSelectionStatus(rowIndex) {
		if ($scope.isRowSelected(rowIndex)) {
			unselect(rowIndex);
		} else {
			select(rowIndex);
		}
	}

	function unselect(rowIndex) {
		var rowIndexInSelectedRowsList = rowIndex;
		var unselectOnlyOneRow = 1;
		selectedRowsIndexes.splice(rowIndexInSelectedRowsList,
				unselectOnlyOneRow);
	}

	function select(rowIndex) {
		if (!$scope.isRowSelected(rowIndex)) {
			selectedRowsIndexes.push(rowIndex)
		}
	}

	function toggleButtons() {
		// edit, view and delete button toggle
		if (selectedRowsIndexes.length == 0) {
			$scope.disable_Edit = true;
			$scope.disable_View = true;
			$scope.disable_Delete = true;
		} else {
			$scope.disable_Edit = false;
			$scope.disable_View = false;
			$scope.disable_Delete = false;
		}
	}
	$scope.updateField=true;
	$scope.editSelectedRowDetails = function(event) {
		 $scope.updateField = false;
		var ops = $scope.availOperations;
		var found = false;
		for (var i = 0; i < ops.length; i++) {
			if (ops[i].name == 'Edit') {
				found = true;
				break;
			}
		}
		if (found) {
			$scope.edituser();
			toggleButtons();
			$scope.openNewTab(event);
		}
	}

	function resetSelection() {
		selectedRowsIndexes = [];
	}

	$timeout(function() {
		$scope.availOperations = $localStorage.availOperations;
	}, 400);

	$scope.operationsGenericFunction = function(doFunction, url) {
		$scope.opeationsURL = url;
		if (doFunction === "Create") {
			$scope.openuserlistmodal(selectedEvent);
			 $scope.openNewTab(selectedEvent);
		}
		if (doFunction === "View" && !$scope.disable_View) {
			$scope.viewuser();
			$scope.openNewTab(selectedEvent);
		}
		if (doFunction === "Edit" && !$scope.disable_Edit) {
			$scope.edituser();
			 $scope.openNewTab(selectedEvent);
		}
		if (doFunction === "Delete" && !$scope.disable_Delete) {
//			$scope.deleteOsiUser();
			showDeleteConfirmationPopup();
		}
	}
	
	function showDeleteConfirmationPopup() {
            $('#function-delete-model').modal({backdrop: 'static', keyboard: false});  
        }
    
    $rootScope.showTabSwitchModal=function() {
           $('#tab-switch-alert-modal').modal({backdrop: 'static', keyboard: false}); 
    };
    
    function isArrayElementMatched(el, index, arr) {
        if (index === 0){
            return true;
        }
        else {
            return ((el.invOrgId === arr[index - 1].invOrgId)&&(el.departmentId === arr[index - 1].departmentId)&&(el.roleId === arr[index - 1].roleId));
        }
    }
    
    $rootScope.checkDuplicateorgInvRole = function(user){
    	vm.isduplicateRoleOrgDept = false;
    	//console.log("User :"+JSON.stringify(user));
    if($rootScope.isApprovalUserExist){
                var invOrgDeptRoleObj={};
                
                //AI: Transfer the service call here from init.
                userService.getUsersInvOrgRoleDeptCombination().then(function (data) {
                    vm.userInvOrgDeptRoleCombinationList = data;
        
                
       if(!vm.userUpdate)
      {
    	   vm.existingInvOrgIdArray=[];
    	   vm.existingDepartmentIds=[];
    	   
      }else{
    	  vm.existingCombinationsArray=[];
    	  
    	  angular.forEach(vm.existingInvOrgIdArray,function(invOrgId){
              angular.forEach(vm.existingDepartmentIds,function(departmentId){
            	  
            	  invOrgDeptRoleObj={
                          invOrgId :invOrgId,
                          departmentId : departmentId,
                          roleId :vm.existingUserRoleId
                      };
            	  
            	  vm.existingCombinationsArray.push(invOrgDeptRoleObj);
                });
             });
    	  
    	 
    	  
      }
       var keepGoing = true;
        angular.forEach(user.osiInvOrgUsers,function(inventoryOrg){
              angular.forEach(user.osiUserDepartment,function(department){
            	  if(keepGoing  && user.osiUserRoles!=undefined)
            	  {
                        invOrgDeptRoleObj={
                            invOrgId : inventoryOrg.osiInventoryOrg.invOrgId,
                            departmentId : department.departmentId,
                            roleId :user.osiUserRoles[0].roleId.roleId
                        };
                   
                        var combinationExistArray=_.where(vm.userInvOrgDeptRoleCombinationList, invOrgDeptRoleObj);
                        if(vm.userUpdate)
                          {
                        
                        	
                         	var isExistingCombination=_.where(vm.existingCombinationsArray, invOrgDeptRoleObj);
                         	
                    		if((combinationExistArray.length>0)&&(isExistingCombination.length==0))
                    		{
                    			vm.duplicateObject={invOrgName : inventoryOrg.osiInventoryOrg.invOrgName,departmentCode : department.departmentCode,roleName:user.osiUserRoles[0].roleId.roleName};
                    			vm.isduplicateRoleOrgDept = true;
                    			keepGoing=false;
                    		
                    		}else{
                    			 vm.isduplicateRoleOrgDept=false;
                    		}
                          }else
                        	  {
                        	     
                        	     if(combinationExistArray.length>0)
                        	     {
                        	     vm.isduplicateRoleOrgDept = true;
                        	     vm.duplicateObject={roleName:user.osiUserRoles[0].roleId.roleName , invOrgName : inventoryOrg.osiInventoryOrg.invOrgName,departmentCode : department.departmentCode};
                        	     keepGoing=false;
                        	     }else
                        	     {
                        	    	 
                        	    	 vm.isduplicateRoleOrgDept=false;
                        	     }
                        	     
                        	  }
                    	
                    	
                        }
                	});
                });
                });
            }
        };
     
        
	vm.saveOsiUser = function() {		
		var inValidUser = $rootScope.inValidUser;
		var inValidUserResp = $rootScope.inValidUserResp;
                $scope.validUser = true;
                 dateDirtyError=0;
                 dateValidError=0;
                 dirtyDateUserInfo=0;
        $scope.formData = new FormData();
       
		var file=SharedDataService.getInfo("uploadfile");
		$scope.formData.append("uploadfile", file);
		if(inValidUser) {
			$rootScope.isTrascError = true;
            FlashService.Error("Please fill required fields in Personal info.");
            $timeout(function () {
            	$rootScope.isTrascError=false;
            }, 5000);
		}else if( $rootScope.passwordMismatch){
			$rootScope.isTrascError = true;
            FlashService.Error("New Password and Confirm password mismatch");
            $timeout(function () {
            	$rootScope.isTrascError=false;
            }, 5000);
		}
		else{	if(inValidUserResp) {
			$rootScope.isTrascError = true;
            FlashService.Error("Please fill required fields in User Responsibility.");
            $timeout(function () {
            	$rootScope.isTrascError=false;
            }, 5000);
		} else  {
			var user = angular.copy(SharedDataService.getUserInfo());
            isInvalidDate(user);
            console.log("$rootScope.isApprovalUserExist :"+$rootScope.isApprovalUserExist);
//            vm.checkapprovalExisted(user.osiUserRoles[0].roleId);
//            $rootScope.checkDuplicateorgInvRole(user);
            user.startDate = CommonService.getDBCompatibleDate(user.startDate);
       		user.endDate = CommonService.getDBCompatibleDate(user.endDate);            
			user.password=AuthenticationService.Base64Encode(user.password);
			if(user.osiRespUsers== undefined){
				$rootScope.isTrascError = true;
                FlashService.Error("Please save the responsibility");
                
                $timeout(function () {
                    $rootScope.isTrascError=false;
                }, 3000);
			} else if(vm.userUpdate === true) {
				angular.forEach($scope.userdata, function(value,key){
					if(value.id== user.id){
						$scope.userdata.splice(key, 1);
					}
				});
				vm.isValid(user);
                                vm.dirtyUserinfoDate(user);
				vm.setEndDate(user);
				
				if ((user.endDate != null) && (new Date(user.startDate).getTime() > new Date(user.endDate).getTime())) {
					$scope.validUser = false;
					$rootScope.isTrascError = true;
                                        FlashService.Error("Start Date is greater than End Date in User Info");
                                        $timeout(function () {
                                            $rootScope.isTrascError=false;
                                        }, 3000);
				}else if(dirtyDateUserInfo==1){
                                    $rootScope.isTrascError = true;
                                    FlashService.Error("Invalid Date in User Info");
                                    console.log("Date Invalid in info:"+$rootScope.isTrascError);
                                    $timeout(function () {
                                        $rootScope.isTrascError=false;
                                        }, 3000);
                                }
                                else if (dateDirtyError == 1) {
                                    $rootScope.isTrascError = true;
                                    FlashService.Error("Invalid Date in User Responsibility");
                                    $timeout(function() {
                                            $rootScope.isTrascError = false;
                                    }, 3000);
                                }
                                else if (dateValidError == 1) {
                                    $rootScope.isTrascError = true;
                                    FlashService.Error("Start date greater than End date in User Responsibility");
                                    $timeout(function() {
                                            $rootScope.isTrascError = false;
                                    }, 3000);
                                } else{
                                    if($scope.validUser){
		                    	  $rootScope.isTrascError=false;
                                          if($rootScope.isPersonalInfoDataModified || $rootScope.isUserResponsibilityDataModified || $rootScope.isFunctionExclusionDataModified || $rootScope.isOperationExclutionDataModified || $rootScope.isInvOrgAssignmentDataModified || 
                                             $rootScope.isCategoryAndApproverForPRModified || $rootScope.isCategoryAndApproverForPOModified || $rootScope.isCategoryAndApproverForMRModified ||
                                             $rootScope.isUserRolesAndDeptDataModified)
                                            {
                                                $rootScope.isTrascError = true;
                                                FlashService.Error("Please Save the changes before Update..!");
                                                $timeout(function() {
                                                        $rootScope.isTrascError = false;
                                                }, 5000);
                                            }else if(user.osiInvOrgUsers.length==0){
                                                        $rootScope.isTrascError = true;
                                                        FlashService.Error("Please fill mandatory field of Inventory Organization..!");
                                                        $timeout(function() {
                                                                $rootScope.isTrascError = false;
                                                        }, 5000);
                                            }else if(user.osiUserRoles.length == 0 || user.osiUserDepartment.length == 0){
                                                        $rootScope.isTrascError = true;
                                                        FlashService.Error("Please fill mandatory fields of Role amd Department..!");
                                                        $timeout(function() {
                                                                $rootScope.isTrascError = false;
                                                        }, 5000);

                                            }else{
                                                if(!vm.isduplicateRoleOrgDept){
                                            	convertDatesToServerCompatible(user);
                                                userService.updateUser(user).then(function (result) {
                                                	$scope.userToEdit = {};
                                            		user = result;
                                            		var msg =appConstants.successMessage;
                                            		if(file) {
                                            			vm.uploadImg(result);
                                            		}else{
                                            			vm.handleSuccess(msg);
                                            		}
                                                    }).catch(function(resp){
                                                    	var msg = appConstants.exceptionMessage;
                                               		  if(resp.data.httpStatus){ 
                                               			  msg=resp.data.errorMessage; 
                                               		  }
                                               			$rootScope.isTrascError = true;
                                                        FlashService.Error(msg);
                                                        $timeout(function () {
                                                        	$rootScope.isTrascError=false;
                                                        }, 5000);
                                               		});
                                                }else{
                                                	$rootScope.isTrascError = true;
                                                    var message="";
                                                    message="Selected inventory "+vm.duplicateObject.invOrgName+" and department "+vm.duplicateObject.departmentCode+" already assigned to another user for given role "+ $rootScope.roleNameForNewUser;
                                                    FlashService.Error(message+"..!");
                                                    $timeout(function() {
                                                            $rootScope.isTrascError = false;
                                                    }, 100000);
                                                }
                                        }
		                      }
                                }
        	} else {
        		vm.isValid(user);
        		vm.setEndDate(user);
        		
                         if($scope.validUser) {
                             if($rootScope.isPersonalInfoDataModified || 
                                $rootScope.isUserResponsibilityDataModified || 
                                $rootScope.isFunctionExclusionDataModified || 
                                $rootScope.isOperationExclutionDataModified || 
                                $rootScope.isInvOrgAssignmentDataModified || 
                                $rootScope.isCategoryAndApproverForPRModified || 
                                $rootScope.isCategoryAndApproverForPOModified || 
                                $rootScope.isCategoryAndApproverForMRModified ||
                                $rootScope.isUserRolesAndDeptDataModified)
                               {
                                   $rootScope.isTrascError = true;
                                   FlashService.Error("Please Save the changes before Submit..!");
                                   $timeout(function() {
                                           $rootScope.isTrascError = false;
                                   }, 5000);
                               }else if(user.osiInvOrgUsers == undefined){
                                        $rootScope.isTrascError = true;
                                        FlashService.Error("Please fill mandatory field of Inventory Organization..!");
                                        $timeout(function() {
                                                $rootScope.isTrascError = false;
                                        }, 3000);
                        
                                    }else if(user.osiInvOrgUsers.length == 0){
                                        $rootScope.isTrascError = true;
                                        FlashService.Error("Please fill mandatory field of Inventory Organization..!");
                                        $timeout(function() {
                                                $rootScope.isTrascError = false;
                                        }, 3000);

                                    }else if(user.osiUserRoles == undefined || user.osiUserDepartment == undefined){
                                        $rootScope.isTrascError = true;
                                        FlashService.Error("Please fill mandatory fields of Role and Department..!");
                                        $timeout(function() {
                                                $rootScope.isTrascError = false;
                                        }, 3000);

                                    }else if(user.osiUserRoles.length == 0 || user.osiUserDepartment.length == 0){
                                        $rootScope.isTrascError = true;
                                        FlashService.Error("Please fill mandatory fields of Role and Department..!");
                                        $timeout(function() {
                                                $rootScope.isTrascError = false;
                                        }, 3000);
                                    } else{
                                        if(!vm.isduplicateRoleOrgDept){
                                            convertDatesToServerCompatible(user);
	                                    userService.saveUser(user).then(function (result) {
	                                    	var msg = "User Created Successfully";
                                            if(file) {
                                                vm.uploadImg(result);
                                            } else {
                                                vm.handleSuccess(msg);
                                            }
                                            }).catch(function(error){
                                            	var msg = appConstants.exceptionMessage;
                                       		  if(error.data.httpStatus){ 
                                       			  msg=error.data.errorMessage; 
                                       		  }
                                       			$rootScope.isTrascError = true;
                                                FlashService.Error(msg);
                                                $timeout(function () {
                                                	$rootScope.isTrascError=false;
                                                }, 5000);
                                       		});
                                        }else{
                                        	$rootScope.isTrascError = true;
                                            var message="";
                                            message="Selected inventory "+vm.duplicateObject.invOrgName+" and department "+vm.duplicateObject.departmentCode+" already assigned to another user for given role "+ $rootScope.roleNameForNewUser;
                                            FlashService.Error(message+"..!");
                                            $timeout(function() {
                                                    $rootScope.isTrascError = false;
                                            }, 100000);
                                        }
                                    }
                                }
                            }
			}
		}
	}
	   Date.prototype.isValid = function () {
	        // An invalid date object returns NaN for getTime() and NaN is the only
	        // object not strictly equal to itself.
	        return this.getTime() === this.getTime();
	    }
            function isInvalidDate(user){
            angular.forEach(user.osiRespUsers, function(value, key) {
                if((((value.startDate != undefined) && (value.startDate !="")) && (new Date(value.startDate).isValid() == false)) ||
                               (((value.endDate != undefined) && (value.endDate !="" ) && (value.endDate != null))  && (new Date(value.endDate).isValid() == false))){
                    dateDirtyError=1;
                }
                if(value.endDate!=null && (new Date(value.startDate).getTime() > new Date(value.endDate).getTime())){
                    dateValidError=1;
                }
            });
        }
                
	   vm.setEndDate = function(user){
		   if(new Date(user.endDate).isValid() == false || user.endDate == null){
				user.endDate= new Date(appConstants.endDate);
			}
		   if(user.osiRespUsers!=undefined){
			   for(var i=0;i<user.osiRespUsers.length;i++){
				   if(user.osiRespUsers[i].endDate == "" || user.osiRespUsers[i].endDate == undefined){
					   user.osiRespUsers[i].endDate = new Date(appConstants.endDate);
				   }
			   }
		   }
	   }
	vm.uploadImg = function(userId) {
		$scope.formData.append("userId",userId);
		userService.uploadFile($scope.formData).then(function(data){
			vm.handleSuccess(data.message);
   		}).catch(function(error){
   			$rootScope.isTrascError = true;
   			var msg = appConstants.exceptionMessage;
	   		  if(error.data.httpStatus){ 
	   			  msg=error.data.errorMessage; 
	   		  }
            FlashService.Error(msg);
            $timeout(function () {
            	$rootScope.isTrascError=false;
            }, 5000);
   		});
		SharedDataService.setInfo("uploadfile",null);
	};
	
	vm.handleSuccess=function(msg){
		vm.close();
		  $scope.successTextAlert =msg;
        $scope.showSuccessAlert = true;
        $timeout(function () {
            $scope.showSuccessAlert = false;
            $window.location.reload();
        }, 2000);
	}
	
	vm.isDuplicateName=function(name){
        vm.duplicateNameCount=0;
        vm.duplicate=false;
        angular.forEach($scope.userdata, function(user){
           		  	if(name.toUpperCase() == user.userName.toUpperCase()){
                                 vm.duplicate=true;
                                 vm.duplicateNameCount++;
             		}
 		}); 
        return  vm.duplicate;         
    };
    vm.isDuplicateEmpId=function(empId){
        vm.duplicateempIdCount=0;
        vm.duplicate=false;
        angular.forEach($scope.userdata, function(user){
             		if(empId==user.empNumber){
                                 vm.duplicate=true;
                                 vm.duplicateempIdCount++;
             		}
 		}); 
        return  vm.duplicate;         
    };

    vm.isDuplicateEmail= function(email){
    	vm.duplicateEmailIdCount=0;
        vm.duplicate=false;
        angular.forEach($scope.userdata, function(user){
     		if(email==user.emailId){
                         vm.duplicate=true;
                         vm.duplicateEmailIdCount++;
     		}
		}); 
        return  vm.duplicate;
    }
	vm.isValid = function(user) {
        if ((user.endDate != null) && (new Date(user.startDate).getTime() > new Date(user.endDate).getTime())) {
               $scope.validUser = false;
               $rootScope.isTrascError = true;
               FlashService.Error("Start Date is greater than End Date in User Info");
		} else {
				vm.isDuplicateName(user.userName);
				vm.isDuplicateEmpId(user.empNumber);
				vm.isDuplicateEmail(user.emailId);
				if (vm.duplicateempIdCount > 0 || vm.duplicateNameCount > 0 || vm.duplicateEmailIdCount > 0) {
					if (vm.duplicateempIdCount > 0) {
						$scope.validUser = false;
						$rootScope.isTrascError = true;
			            FlashService.Error("Duplicate Emp No.");
			            vm.goToTab(appConstants.tab1);
					}
					if (vm.duplicateNameCount > 0) {
						$scope.validUser = false;
						$rootScope.isTrascError = true;
			            FlashService.Error("Duplicate User Name");
			            vm.goToTab(appConstants.tab1);
					}
					if(vm.duplicateEmailIdCount > 0){
						$scope.validUser = false;
						$rootScope.isTrascError = true;
			            FlashService.Error("Duplicate Email Id");
			            vm.goToTab(appConstants.tab1);
					}
				} else {
				$scope.validUser = true;
				}
		}
		$timeout(function () {
        	$rootScope.isTrascError=false;
        }, 3000);
	}     
        vm.dirtyUserinfoDate= function(user){
            if((((user.startDate != undefined) && (user.startDate !="")) && (new Date(user.startDate).isValid() == false)) ||
                               (((user.endDate != undefined) && (user.endDate !="" ))  && (new Date(user.endDate).isValid() == false))){
                    $scope.validUser = false;
                    dirtyDateUserInfo = 1;
                }
        }
	
	vm.close = function() {
		vm.inProcess = false;
		vm.userView=false;
		$('#userlist-model').modal('hide');
	}

	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		  var target = $(e.target).attr("href") // activated tab
	});
	
    vm.deselectTabs=function(){
          $rootScope.tab1="";
          $rootScope.tab2="";
          $rootScope.tab3="";
          $rootScope.tab4="";
          $rootScope.tab5="";
          $rootScope.tab6="";
          $rootScope.personalInfoActive="";
    };
    
    $scope.changeTab=function(rootEvent,tabname){
         //event.stopPropagation();
    	vm.tabname=tabname;
        //console.log("$rootScope.isPersonalInfoDataModified:"+$rootScope.isPersonalInfoDataModified);
    	if($rootScope.isPersonalInfoDataModified && tabname!=appConstants.tab1){
    		$rootScope.confirmationHeading=appConstants.personalInfo;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab1;
    	}
    	if($rootScope.isUserResponsibilityDataModified && tabname!=appConstants.tab2){
            $rootScope.confirmationHeading=appConstants.userResponsibility;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab2;
    	}
    	if($rootScope.isFunctionExclusionDataModified && tabname!=appConstants.tab3){
    		$rootScope.confirmationHeading=appConstants.functionExclusion;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab3;
    	}
    	if($rootScope.isOperationExclutionDataModified && tabname!=appConstants.tab4){
    		$rootScope.confirmationHeading=appConstants.operationExclusion;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab4;
    	}
    	/*if($rootScope.isInvOrgAssignmentDataModified && tabname!=appConstants.tab5){
    		$rootScope.confirmationHeading=appConstants.inventoryOrg;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab5;
    	}
    	if($rootScope.isUserRolesAndDeptDataModified && tabname!=appConstants.tab6){
    		$rootScope.confirmationHeading=appConstants.rolesAndDept;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab6;
    	}*/
        /*if($rootScope.isCategoryAndApproverForPRModified ){
    		$rootScope.confirmationHeading=appConstants.categoryApproverForPR;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab7;
    	}
    	if($rootScope.isCategoryAndApproverForPOModified ){
    		$rootScope.confirmationHeading=appConstants.categoryApproverForPO;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab8;
    	}
        if($rootScope.isCategoryAndApproverForMRModified ){
            $rootScope.confirmationHeading=appConstants.categoryApproverForMR;
            $rootScope.showTabSwitchModal();
            rootEvent.stopPropagation();
            vm.tabname=appConstants.tab9;
        }*/
        $scope.$emit('saveChangesEvent',rootEvent,vm.tabname);
             
     };
    $scope.openNewTab=function(rootEvent){
         //event.stopPropagation();
                 $scope.$emit('openChangesEvent',rootEvent);
    };
    
    $rootScope.selectBack = function(tabname)
    {
    	 $rootScope.catTab1="";
    	 $rootScope.catTab2="";
    	 $rootScope.catTab3="";
        if(tabname==="catTab1"){
        	 $rootScope.catTab1="selectedInfoTab";
        }
        if(tabname==="catTab2"){
        	$rootScope.catTab2="selectedInfoTab";
        }
        if(tabname==="catTab3"){
        	 $rootScope.catTab3="selectedInfoTab";
         }
    };
    vm.goToTab= function(tab){

    	vm.deselectTabs();
    	
	    switch (tab) {
	          case appConstants.tab1:
			        angular.element('[data-target="#info-tab"]').tab('show');
				  	$rootScope.tab1="selectedInfoTab";
	          		break;
	          
	          case appConstants.tab2:
	          		angular.element('[data-target="#userResp-tab"]').tab('show');
	          		$rootScope.tab2="selectedInfoTab";
	          	  	break;
	          
	          case appConstants.tab3:
	          		angular.element('[data-target="#funcExclu-tab"]').tab('show');
	          		$rootScope.tab3="selectedInfoTab";
	          		break;
	          
	          case appConstants.tab4:
	          		angular.element('[data-target="#operation-tab"]').tab('show');
	          		$rootScope.tab4="selectedInfoTab";
	          		break;
	          
	         /* case appConstants.tab5:
	          		angular.element('[data-target="#invOrgAssignment-tab"]').tab('show');
	          		$rootScope.tab5="selectedInfoTab";
	              	break;
	          
	          case appConstants.tab6:
	          		angular.element('[data-target="#rolesAndDepartment-tab"]').tab('show');
	          		$rootScope.tab6="selectedInfoTab";
            		break;*/
            		
	          /*case appConstants.tab7:
	          		angular.element('[data-target="#purchaseRequisition-tab"]').tab('show');
	          		$rootScope.tab6="selectedInfoTab";
	          		break;
          		
	          case appConstants.tab8:
	          		angular.element('[data-target="#purchaseOrder-tab"]').tab('show');
	          		$rootScope.tab6="selectedInfoTab";
	          		break;
          		
	          case appConstants.tab9:
	          		angular.element('[data-target="#materialRequisition-tab"]').tab('show');
	          		$rootScope.tab6="selectedInfoTab";
	          		break;*/
	    }

    }
    
    function convertDatesToServerCompatible(userToUpdate) {
    	userToUpdate.startDate = CommonService.getDBCompatibleDate(userToUpdate.startDate);
    	userToUpdate.endDate = CommonService.getDBCompatibleDate(userToUpdate.endDate);
    	angular.forEach(userToUpdate.osiRespUsers, function(userResp) {
    		userResp.startDate = CommonService.getDBCompatibleDate(userResp.startDate);
    		userResp.endDate = CommonService.getDBCompatibleDate(userResp.endDate);
        });
    }
    
    function convertDatesToPhillipinesFormat(userToUpdate) {
    	userToUpdate.startDate = CommonService.getDateInPhillipinesFormat(userToUpdate.startDate);
    	userToUpdate.endDate = CommonService.getDateInPhillipinesFormat(userToUpdate.endDate);
    	angular.forEach(userToUpdate.osiRespUsers, function(userResp) {
    		userResp.startDate = CommonService.getDateInPhillipinesFormat(userResp.startDate);
    		userResp.endDate = CommonService.getDateInPhillipinesFormat(userResp.endDate);
        });
    }
}
    
