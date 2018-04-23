'use strict';

angular.module('myApp.menuEntriesListController', [])
        .controller('menuEntriesListController',
    ['$scope', '$rootScope', '$window', '$location', '$http', '$sessionStorage','$routeParams',
        '$localStorage','menuEntryService','FlashService','$timeout', 'appConstants',
    function ($scope, $rootScope, $window, $location, $http, $sessionStorage,$routeParams,$localStorage,menuEntryService,FlashService,$timeout, appConstants) 
    {
               
    	var vm = this;
    	vm.types=[{type:"Function"},{type:"Menu"}];
        vm.view=false;
        //vm.selectedType = "M";
        vm.menuEntry={};
        vm.searchEntryObj=
            {
                osiMenusByMenuId: {}, seq: '', osiMenusBySubMenuId: {}, prompt: '', osiFunctions: {}
            };
        
        
        $scope.rowSize=5;
    	//////////////////////////////////////////////////////////////
        $scope.successTextAlert = "";
	    $scope.showSuccessAlert = false;
	    $scope.failureTextAlert = "";
	    $scope.showFailureAlert = false;
	    vm.isButtionDisableForPrompt = true;
	    vm.isButtionDisableForMenuName = true;
	var selectedRowsIndexes = [];
	var selectedEvent='';
	$scope.rows = [];
	$scope.disable_Edit = true;
	$scope.disable_View = true;
	$scope.disable_Delete = true;
	
        
        
         vm.initController=function(){
             vm.menuEntry={};
             vm.entryList=[];
             //console.log("INIT CONTROLLER menuEntriesListController..");
            /*  -- Commented for stop initial loading list
            menuEntryService.getMenuEntriesInitially().then(function (data) {
            	 angular.forEach(data,function(value) {	
             		if(value.active == 1){
             			value.active="Active";
             		}else{
             			value.active="Inactive";
             		}
             	});
                vm.entryList=data;
                //$rootScope.allMenuEntries= angular.copy(data);
                
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
             menuEntryService.getAllMenus().then(function (data) {
                        vm.menus = data;
                        vm.subMenuOptions=vm.menus;
                       // console.log("menus :",JSON.stringify(vm.menus));
            });
            
             menuEntryService.getAllFunctions().then(function (data) {
                        vm.functions = data;
                       // console.log("functions :",JSON.stringify(vm.functions));
            });
            menuEntryService.getAllMenuEntries().then(function(data){
                $rootScope.allMenuEntries = data;
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
        vm.initController();
        
        vm.showMenuEntryModal = function()
        {        vm.view=false;
                   $('#menu_Entries_modal').modal({backdrop: 'static', keyboard: false});                
        };
        $scope.sortKey = 'seq'; 
         $scope.sort = function(keyname) {
            if (vm.entryList != null) {
                // set the sortKey to the param passed
                $scope.sortKey = keyname;
                // if true make it false and vice versa
                $scope.reverse = !$scope.reverse;
            }
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
		console.log(rowIndexInSelectedRowsList);
		var unselectOnlyOneRow = 1;
		selectedRowsIndexes.splice(rowIndexInSelectedRowsList,
				unselectOnlyOneRow);
	}

	function select(rowIndex) {
		if (!$scope.isRowSelected(rowIndex)) {
			console.log("select");
			selectedRowsIndexes.push(rowIndex)
		}
	}
	 $scope.clearSelectedRow = function() {
	        // clearing row selection
	        $scope.isSelectedRow = null;
	        toggleButtons();
	    }
	 
	function toggleButtons() {
		console.log("selectedRowsIndexes:--" + selectedRowsIndexes.length);
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

	$scope.editSelectedRowDetails = function() {
		console.log('*****called double click***');
		var ops = $scope.availOperations;
		var found = false;
		for (var i = 0; i < ops.length; i++) {
			if (ops[i].name == 'Edit') {
				found = true;
				break;
			}
		}
		console.log("found:- " + found);
		if (found) {
			vm.showUpdateEntryModal();
			toggleButtons();
		}
	}
//
	function resetSelection() {
		selectedRowsIndexes = [];
	}

	$timeout(function() {
		$scope.availOperations = $localStorage.availOperations;
	}, 400);

	$scope.operationsGenericFunction = function(doFunction, url) {
		$scope.opeationsURL = url;
		if (doFunction === "Create") {
			vm.showMenuEntryModal(selectedEvent);
		}
		if (doFunction === "View" && !$scope.disable_View) {
			vm.showViewEntryModal();
		}
		if (doFunction === "Edit" && !$scope.disable_Edit) {
			vm.showUpdateEntryModal();
		}
		if (doFunction === "Delete" && !$scope.disable_Delete) {
			showDeleteConfirmationPopup();
		}
	} 
          function getSelectedRows() {
		var selectedRows = [];
		angular.forEach(selectedRowsIndexes, function(rowIndex) {
			selectedRows.push(rowIndex);
		});
		return selectedRows;
	}
        $scope.clearSelectedRow = function() {
            // clearing row selection
            resetSelection();
            toggleButtons();
    	}
        vm.showViewEntryModal=function(){
            //console.log("showViewEntryModal:",JSON.stringify(entry));
           var selectedRow = getSelectedRows();
		vm.menuEntry = selectedRow[0];
                if(vm.menuEntry.active=="Active"){
                    vm.active=true;
                }else{
                    vm.active=false;
                }
            vm.view=true;//used to disable ui controlls.
            //vm.menuEntry=entry;
            vm.selectedType = "Menu";
            if (vm.menuEntry.osiFunctions != null)
            {
                //console.log("function id",vm.menuEntry.osiFunctions.id);
                vm.selectedType = "Function";
            } else {
               // console.log("osiMenusBySubMenuId id",vm.menuEntry.osiMenusBySubMenuId.id);
                vm.selectedType = "Menu";
            }
            $('#menu_Entries_Update_modal').modal({backdrop: 'static', keyboard: false}); 
        }
        
        vm.showUpdateEntryModal=function(){
            vm.view=false;
            vm.selectedType = "";
         
            //console.log("Update:",JSON.stringify(entry));
            //vm.menuEntry=entry;
            var selectedRow = getSelectedRows();
            var entry = selectedRow[0];
            vm.menuEntry =angular.copy(entry);
            vm.removeHeaderMenu(vm.menuEntry.osiMenusByMenuId);
            if(vm.menuEntry.active=="Active" || vm.menuEntry.active==1){
            	vm.active=true;
            }else{
            	vm.active=false;
            }
            if (vm.menuEntry.osiFunctions != null)
            {
                vm.selectedType = "Function";
                
            } else {
                vm.selectedType = "Menu";
            }
             
            $('#menu_Entries_Update_modal').modal({backdrop: 'static', keyboard: false}); 
        }
    	
    	vm.update=function(entry){
            var update=true;
    		var object={};
    		//console.log("IN at Update:",JSON.stringify(entry));
    		object.id=entry.id;
    		object.osiMenusByMenuId=angular.fromJson(entry.osiMenusByMenuId);
    		object.osiFunctions=null;
    		object.osiMenusBySubMenuId=null;
                if(!(vm.selectedType=="Menu") && !(vm.selectedType=="Function")){
                         $rootScope.isTrascError = true;    
                         FlashService.Error("Please select Type.");
                         $timeout(function () {
                        	 $rootScope.isTrascError= false;
                         }, 3000);
                         update=false;
    		}
    		if(vm.selectedType=="Function"){
    			object.osiFunctions=angular.fromJson(entry.osiFunctions);
    		}
    		if(vm.selectedType=="Menu"){
    			object.osiMenusBySubMenuId=angular.fromJson(entry.osiMenusBySubMenuId);
    		}
    		
    		object.seq=entry.seq;
    		object.prompt=entry.prompt;
    		object.startDate=entry.startDate;
    		object.endDate=entry.endDate;
    		object.createdBy=entry.createdBy;
    		object.createdDate=entry.createdDate;
    		object.updatedBy=entry.updatedBy;
    		object.updatedDate=entry.updatedDate;
              //  object.active=1;
    		if(vm.active){
    			object.active=1;
    		}else{
    			object.active=0;
    		}
    		
               // console.log("Updating:",JSON.stringify(object));
               if(object.seq==0 || object.seq=="" || object.seq== undefined)
               {
                    $rootScope.isTrascError = true;    
                    FlashService.Error("Please enter valid Sequence.");
                    $timeout(function () {
                            $rootScope.isTrascError= false;
                    }, 3000);
                    update=false;
               }
               
               if(object.prompt=="" || object.prompt== undefined)
               {
                   $rootScope.isTrascError = true;    
                    FlashService.Error("Please enter valid Prompt.");
                    $timeout(function () {
                            $rootScope.isTrascError= false;
                    }, 3000);
                    update=false;
               }
                
                if((object.osiFunctions==null)&&(object.osiMenusBySubMenuId==null))
                {
                    $rootScope.isTrascError = true;    
                    FlashService.Error("Please select Menu/Function.");
                    $timeout(function () {
                            $rootScope.isTrascError= false;
                    }, 3000);
                    update=false;
                }
                
                if(vm.checkForDuplicateEntry(object)){
                    $rootScope.isTrascError = true;    
                    FlashService.Error("This Menu Entry combinition already exists, please make other combinition.");
                    $timeout(function () {
                            $rootScope.isTrascError= false;
                    }, 3000);
                    update=false;
                }
               
                
                if(update)
                {
                    menuEntryService.updateMenuEntry(object).then(function (data) {
                            // console.log("Updated :", JSON.stringify(data));
                            if (data.httpStatus == 200)
                            {
                            	$rootScope.isTrascError = true;
                            	var msg = appConstants.successMessage;
                            	FlashService.Success(msg);
                            	$timeout(function () {
                            	 $rootScope.isTrascError=false;
                            	}, 2000);
                                //vm.initController();
                                vm.searchEntry();
                                $('#menu_Entries_Update_modal').modal('hide');
                            } else {
                                   $rootScope.isTrascError = true;    
                                   FlashService.Error(data.errorMessage);
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
                else{
//                    $rootScope.isTrascError = true;    
//                    FlashService.Error("Please,fill all mandatory fields ");
//                    $timeout(function () {
//                   	 $rootScope.isTrascError= false;
//                    }, 3000);
                }
               
                $scope.clearSelectedRow(); 
    	};
        
        
        vm.checkForDuplicateEntry=function(entry){
            var duplicate=false;
            var allMenuEntriesForUpdate = $rootScope.allMenuEntries;
            var index = allMenuEntriesForUpdate.indexOf(_.findWhere($rootScope.allMenuEntries, {id:entry.id}));
            allMenuEntriesForUpdate.splice(index,1);
            //Checking for duplicattion with existing records.
            angular.forEach(allMenuEntriesForUpdate,function(dbEntry){
                    if((dbEntry.osiMenusByMenuId.menuName==entry.osiMenusByMenuId.menuName)){
                        
                            if(dbEntry.osiMenusBySubMenuId != null && entry.osiMenusBySubMenuId!=undefined){
                                console.log("dbEntry.active :"+dbEntry.active+"  entry.active :"+entry.active);
                                    if((dbEntry.osiMenusBySubMenuId.menuName==entry.osiMenusBySubMenuId.menuName)){
                                        duplicate =true;
                                    }
                            }else if(dbEntry.osiFunctions!=null && entry.osiFunctions != undefined){
                                    if((dbEntry.osiFunctions.funcName==entry.osiFunctions.funcName)){
                                        duplicate =true;
                                    }
                            }
                    }
            });
            return duplicate;
        };
        
         function showDeleteConfirmationPopup() {
            $('#menuEntry-delete-model').modal({backdrop: 'static', keyboard: false}); 
        }
        
        vm.deleteEntry=function(){
            //var id=entry.id;
            var selectedRow = getSelectedRows();
		vm.menuEntry = selectedRow[0];
            //vm.deletedEntry=entry;
            
            menuEntryService.deleteMenuEntry(vm.menuEntry).then(function(result){
               // console.log("deleted:",JSON.stringify(result));
//                $('#menuEntry-delete-model').modal('hide');
//            	 $scope.successTextAlert ="Menu Entries Deleted Successfully";
//                 $scope.showSuccessAlert = true;
//                //vm.entryList.splice(vm.entryList.indexOf(vm.deletedEntry),1);
//                vm.initController();
//                $timeout(function () {
//                	$scope.showSuccessAlert = false;
//                }, 3000);
//                
                if (result.httpStatus == 200) {
                    $('#menuEntry-delete-model').modal('hide');
                    $scope.successTextAlert = result.message;
                    $scope.showSuccessAlert = true;
                    vm.initController();
                } else {
                    $('#menuEntry-delete-model').modal('hide');
                    $rootScope.isTrascError = true;
                    FlashService.Error(result.errorMessage);
                    $scope.continuesave = false;
//                   vm.initController();
                }
                $timeout(function() {
                    $scope.showSuccessAlert = false;
 		}, 5000);
                
            });
        }
        
        vm.closeModal=function(){
             $('#menu_Entries_Update_modal').modal('hide');
        }
        
         $rootScope.$on('reloadMenuEntryList', function(event) {
                       // console.log("event caught in list controller");
		    	 vm.initController();
        });
        $rootScope.$on('MenuEntryList', function(event) {
            vm.searchEntry();
        });
        
        vm.removeHeaderMenu=function(headerMenu){
            headerMenu=angular.fromJson(headerMenu);
            vm.iterator=vm.menus ;
            vm.subMenuOptions=[];
              angular.forEach(vm.iterator, function (value, key) 
                {
                    if(value.id!=headerMenu.id){
                        vm.subMenuOptions.push(value);
                    }
                });
        };
        
        vm.makeEnableButtionPrompt = function(value){
        	if(value){
        		vm.isButtionDisableForPrompt = false;
        	}else{
        		vm.isButtionDisableForPrompt = true;
        	}
        }
        
        vm.makeEnableButtionForMenuName = function(value){
        	if(value){
        		vm.isButtionDisableForMenuName = false;
        	}else{
        		vm.isButtionDisableForMenuName = true;
        	}
        }
        
        vm.searchEntry=function(entry){
        	if((vm.searchEntryObj.prompt == undefined || vm.searchEntryObj.prompt == "")
        			&&(vm.searchEntryObj.osiMenusByMenuId.menuName == undefined || vm.searchEntryObj.osiMenusByMenuId.menuName == "")){
                //vm.initController();
                
                menuEntryService.getAllMenuEntries().then(function (data) {
                    angular.forEach(data,function(value, key) {	
             		if(value.active == 1){
             			value.active="Active";
             		}else{
             			value.active="Inactive";
             		}
                    });
                    vm.entryList=data;
                    $rootScope.allMenuEntries= data;
                });
            }
            else{
                menuEntryService.searchEntries(entry).then(function (data) {
                    angular.forEach(data,function(value, key) {	
                		if(value.active == 1){
                			value.active="Active";
                		}else{
                			value.active="Inactive";
                		}
                	});
                    vm.entryList=data;
                });
            }
            
        };
        $rootScope.$on('saveChanges', function() {
            $scope.successTextAlert =appConstants.successMessage;
            $scope.showSuccessAlert = true;
            $timeout(function () {
                $scope.showSuccessAlert = false;
                $scope.showFailureAlert = false;
            }, 6000);
        });
        $scope.clearSearch = function () {
        	vm.searchEntryObj = {};
                $scope.clearSelectedRow();
                vm.isButtionDisableForPrompt = true;
        	    vm.isButtionDisableForMenuName = true;
        	    vm.searchEntryObj=
                {
                    osiMenusByMenuId: {}, seq: '', osiMenusBySubMenuId: {}, prompt: '', osiFunctions: {}
                };
                vm.initController();
        };

        $scope.showDeactivateConfirmationPopup=function() {
        	if(!vm.active){
        		$scope.confirmationHeading="Deactivate"
        	}
        	else{
        		$scope.confirmationHeading="Activate"
        	}
        	$('#menuEntry-delete-model').modal({backdrop: 'static', keyboard: false}); 
          }
        
        vm.noAction = function(){
        	if(vm.active){
            	vm.active=false;
            }else{
                vm.active=true;
            }
        }
    }]);