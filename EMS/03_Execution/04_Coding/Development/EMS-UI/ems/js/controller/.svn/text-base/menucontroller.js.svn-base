'use strict';
angular.module('myApp.menucontroller', [])
    .controller('MenuController', ['$scope', '$rootScope', '$window', '$location', '$http', '$localStorage', 'FlashService', 
                                   'MenuService', '$timeout', 'appConstants', 
function($scope, $rootScope, $window, $location, $http, $localStorage, FlashService, MenuService, $timeout, appConstants) {
        $scope.slide = '';
        $rootScope.isTrascError = false;
        $rootScope.menus = [];
        $scope.menu = {};
        $scope.rowSize = 8;
        var selectedRowsIndexes = [];
        $rootScope.isheader = false;
        $scope.rows = [];
        $scope.searchMenu = {
            menuName: "",
            description: ""
        };
        $scope.sortKey = 'menuName'; 
        $scope.makeEditDisable = false;
        $scope.makeViewDisable = false;
        $scope.disable_Create = false;
    	$scope.disable_Edit = true;
    	$scope.disable_Delete = true;
    	$scope.disable_View = true;
    	$scope.successTextAlert = "";
    	$scope.showSuccessAlert = false;
    	$scope.showActive= true;
        $scope.showActiveInCreate= false;
        
        $scope.sort = function(keyname) {
            if($scope.menus != null) {
    		// set the sortKey to the param passed
    		$scope.sortKey = keyname; 
    		// if true make it false and vice versa
    		$scope.reverse = !$scope.reverse;
            }
    	}
        
        $timeout(function() {
            // getting the available operations assigned for the logged in user
            $scope.availOperations = $localStorage.availOperations;
// console.log("$scope.availOperations--"+JSON.stringify($scope.availOperations));
    	}, 400);
        
        
        var elmtNoSpace = document.getElementById('noSpaces');

        elmtNoSpace.addEventListener('keydown', function (event) {
            if (elmtNoSpace.value.length === 0 && event.which === 32) {
                event.preventDefault();
            }
        });

        
        $scope.init = function() {
            MenuService.getReportGroups().then(function(data) {
                $scope.osiReportGroupsList = data;
            }).catch(function(resp){
            	$scope.showErrorAlert = false;
            	$scope.ErrorTextAlert = appConstants.exceptionMessage;
    			$timeout(function() {
                    $scope.showErrorAlert = false;
                }, 3000);
            });
            /*  -- Commented for stop initial loading list
            MenuService.getMenusListInitially().then(function(data) {
               
                angular.forEach(data, function(value, key) {
                    if(value.active==1){
                        value.active = "Active";
                    }else{
                        value.active = "Inactive";
                    }
                    // this.push(value);
                });
                 $scope.menus = data;
                 console.log("Menu array length :"+$scope.menus.length);
                 // console.log(JSON.stringify($scope.menus));
                angular.forEach(data, function(value, key) {
                    this.push(value);
                }, $scope.rows);
            }).catch(function(resp){
            	$scope.ErrorTextAlert=appConstants.exceptionMessage;
            	$scope.showErrorAlert = true;
    			$timeout(function() {
                    $scope.showErrorAlert = false;
                }, 3000);
            });
            */            
        };

        $scope.searchMenu1 = function() {
        	if($scope.searchMenu === undefined || ($scope.searchMenu.menuName === "")){
                    MenuService.getAllMenusList().then(function(data) {
                        angular.forEach(data, function(value, key) {
                            if(value.active==1){
                                value.active = "Active";
                            }else{
                                value.active = "Inactive";
                            }
                        });
                         $scope.menus = data;
                         console.log("Menu array length :"+$scope.menus.length);
                        angular.forEach(data, function(value, key) {
                            this.push(value);
                        }, $scope.rows);
                    });   
        	}
        	else{
                    MenuService.searchMenu($scope.searchMenu).then(function(response) {
                	angular.forEach(response, function(value, key) {
                            if(value.active==1){
                                value.active = "Active";
                            }else{
                                value.active = "Inactive";
                            }
                            // this.push(value);
                        });
                        $scope.menus = response;
                    
                    }).catch(function(resp){
                    	$scope.ErrorTextAlert=appConstants.exceptionMessage;
                    	$scope.showErrorAlert = true;
            			$timeout(function() {
                            $scope.showErrorAlert = false;
                        }, 3000);
                    });
        	}
        }
        
        $scope.operationsGenericFunction = function(doFunction, url) {
    		// passing selected operation url
    		$scope.opeationsURL = url;
    		if (doFunction === "Create") {
                    createMenu();
    		}
    		if (doFunction === "View" && !$scope.disable_View) {
                    viewMenu();
    		}
    		if (doFunction === "Edit" && !$scope.disable_Edit) {
                    editMenu();
    		}
    		/*
			 * if (doFunction === "Delete" && !$scope.disable_Delete) {
			 * showDeleteConfirmationPopup(); }
			 */
    	}
        
        function toggleButtons() {
            console.log("selectedRowsIndexes:--"+selectedRowsIndexes.length);
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

        $scope.selectRow = function(event, rowIndex) {
            if (event.ctrlKey) {
                changeSelectionStatus(rowIndex);
            } else if (event.shiftKey) {
                // selectWithShift(rowIndex);
            } else {
                selectedRowsIndexes = [rowIndex];
                toggleButtons();
            }
        };

        function selectWithShift(rowIndex) {
            var lastSelectedRowIndexInSelectedRowsList = selectedRowsIndexes.length - 1;
            var lastSelectedRowIndex = selectedRowsIndexes[lastSelectedRowIndexInSelectedRowsList];
            var selectFromIndex = Math.min(rowIndex, lastSelectedRowIndex);
            var selectToIndex = Math.max(rowIndex, lastSelectedRowIndex);
            selectRows(selectFromIndex, selectToIndex);
        }

        function getSelectedRows() {
            var selectedRows = [];
            angular.forEach(selectedRowsIndexes, function(rowIndex) {
                selectedRows.push(rowIndex);
            });
            return selectedRows;
        }

        function getFirstSelectedRow() {
            var firstSelectedRowIndex = selectedRowsIndexes[0];
            return $scope.rows[firstSelectedRowIndex];
        }

        function selectRows(selectFromIndex, selectToIndex) {
            for (var rowToSelect = selectFromIndex; rowToSelect <= selectToIndex; rowToSelect++) {
                select(rowToSelect);
            }
        }

        function changeSelectionStatus(rowIndex) {
            if ($scope.isRowSelected(rowIndex)) {
                unselect(rowIndex);
            } else {
                select(rowIndex);
            }
        }

        function select(rowIndex) {
            if (!$scope.isRowSelected(rowIndex)) {
                console.log("select");
                selectedRowsIndexes.push(rowIndex)
            }
        }

        function unselect(rowIndex) {
            var rowIndexInSelectedRowsList = rowIndex;
            console.log(rowIndexInSelectedRowsList);
            var unselectOnlyOneRow = 1;
            selectedRowsIndexes.splice(rowIndexInSelectedRowsList, unselectOnlyOneRow);
        }

        function resetSelection() {
            selectedRowsIndexes = [];
        }

        $scope.isRowSelected = function(rowIndex) {
            return selectedRowsIndexes.indexOf(rowIndex) > -1;
        };

        $rootScope.back = function() {
            $scope.slide = 'slide-right';
            $window.history.back();
        };
        
        $rootScope.go = function(path) {
            $scope.slide = 'slide-left';
            $location.url(path);
        };

// $scope.openmenumodal = function() {
        function createMenu() {
            $scope.showActiveInCreate = true;
            $scope.showActive= false;
            $scope.headername = "Create Menu";
            $scope.menu = {};
            $scope.activeInCreate=true;
            $scope.iseditable = true;
            $scope.isUpdatable = false;
            $scope.viewButton = true;
            $rootScope.flash = false;
            $('#menu_model').modal({backdrop: 'static', keyboard: false}); 
        };

        $scope.saveRow = function(menu) {
            $scope.menu = menu;
            $scope.menuName = $scope.menu.menuName;
            $scope.menuDesc = $scope.menu.description;
            if($scope.activeInCreate){
            	$scope.menu.active=1;
            }else{
            	$scope.menu.active=0;
            }
            $scope.continuesave = true;
            
            if ($scope.menuName == undefined || $scope.menuName.trim() == '') {
                $rootScope.isTrascError = true; 
                FlashService.Error("Please enter Menu Name");
                $timeout(function() {
                    $rootScope.isTrascError = false;                                      
                }, 5000);
                $scope.continuesave = false; 
            }            
            else {
                $scope.continuesave = true;
                $rootScope.isTrascError = false;
            }
// alert($scope.menu.reportGrpId);
// if($scope.menu.reportGrpId){
// $scope.menu.reportGrpId = angular.fromJson($scope.menu.reportGrpId).rptGrpId;
// }
            if ($scope.continuesave) {
            	console.log(menu.menuName);
                menu.menuName = menu.menuName.trim();
                MenuService.saveMenu(menu).then(function(data) {
                    resetSelection();
                    if (data.httpStatus == 200) {
                        $('#menu_model').modal('hide');
                    	$scope.successTextAlert = data.message;
    			$scope.showSuccessAlert = true;
                        $scope.init();
                    } else {
                        $rootScope.isTrascError = true;
                        FlashService.Error(data.errorMessage);
                        $scope.continuesave = false;
                    }
                    $timeout(function() {
                        $scope.showSuccessAlert = false;
                        $rootScope.isTrascError = false;
                    }, 3000);
                }).catch(function(resp){
                	if(resp.data.errorCode == "ERR_1032"){
                		$rootScope.isTrascError = true;
                		FlashService.Error(resp.data.errorMessage);
               			$scope.continuesave = false;
               			$timeout(function() {
               				$rootScope.isTrascError = false;
               			}, 3000);
                	}else{
                		$rootScope.isTrascError = true;
                		FlashService.Error(appConstants.exceptionMessage);
                		$scope.continuesave = false;
                		$timeout(function() {
                			$rootScope.isTrascError = false;
                		}, 3000);
                	}
                });
            }
            $scope.activeInCreate=true;
        };

        function viewMenu() {
        	$scope.showActive= true;
                $scope.showActiveInCreate = false;
            $scope.headername = "View Menu";
            var selectedRow = getSelectedRows();
            if (selectedRow.length == 1) {
                $scope.menu = selectedRow[0];
                console.log("Menu :"+JSON.stringify($scope.menu));
                if($scope.menu.active=="Active"){
                	$scope.active=true;
                }
                else{
                	$scope.active=false;
                }
                // alert($scope.menu.reportGrpId);
                $scope.iseditable = false;
                $scope.viewButton = false;
                $rootScope.flash = false;
                $('#menu_model').modal({backdrop: 'static', keyboard: false}); 
// resetSelection();
            } else {
                $scope.makeViewDisable = true;
            }
        };

        $scope.editSelectedRowDetails = function() {
    		var ops = $scope.availOperations;
    		var found = false;
    		for(var i = 0; i < ops.length; i++) {
    		    if (ops[i].name == 'Edit') {
    		        found = true;
    		        break;
    		    }
    		}
    		console.log("found:- " + found);
    		if(found) {
                    editMenu();
                    toggleButtons();
    		}
    	}
        
// $scope.editMenu = function() {
        function editMenu() {
        	$scope.showActive= true;
                $scope.showActiveInCreate = false;
            var selectedRow = getSelectedRows();
            $scope.headername = "Edit Menu";
            if (selectedRow.length == 1) {
                $scope.menu = angular.copy(selectedRow[0]);
                $scope.menu.menuName = $scope.menu.menuName.trim();
                if($scope.menu.active=="Active"){
                	$scope.active=true;
                }
                else{
                	$scope.active=false;
                }
                $scope.isUpdatable = true;
                $scope.iseditable = true;
                $scope.viewButton = true;
                $rootScope.flash = false;
                $('#menu_model').modal({backdrop: 'static', keyboard: false}); 
// resetSelection();
            } else {
                $scope.makeEditDisable = true;
            }
        };

        $scope.updateRow = function(menu) {
            $scope.menu = menu;
            $rootScope.flash = false;
            $scope.menuName = $scope.menu.menuName;
            if($scope.active){
            	$scope.menu.active=1;
            }else{
            	$scope.menu.active=0;
            }
            if (($scope.menuName == undefined) || ($scope.menuName == "")) {
                $rootScope.isTrascError = true;
                FlashService.Error("Please enter Menu Name");
                $scope.continuesave = false;
            } else {
                $scope.continuesave = true;
                $rootScope.isTrascError = false;
            }
// if($scope.menu.reportGrpId){
// $scope.menu.reportGrpId = angular.fromJson($scope.menu.reportGrpId).rptGrpId;
// }
            if ($scope.continuesave) {
                MenuService.updateMenu(menu).then(function(data) {
                	console.log("Menu :"+JSON.stringify(data));
                    if (data.httpStatus == 200) {
                        $scope.menuId = null;
                        $('#menu_model').modal('hide');
                        $scope.successTextAlert = appConstants.successMessage;
     			$scope.showSuccessAlert = true;
                        $scope.init();
                        $scope.reset();
                    } else {
// $scope.init();
                        $rootScope.isTrascError = true;
                        FlashService.Error(data.errorMessage);
                        $scope.continuesave = false;
                    }
                    $timeout(function() {
     			$scope.showSuccessAlert = false;
                    }, 5000);
                    resetSelection();
                }).catch(function(resp){
                	if(resp.data.errorCode == "ERR_1032"){
                		$rootScope.isTrascError = true;
                		FlashService.Error(resp.data.errorMessage);
               			$scope.continuesave = false;
               			$timeout(function() {
               				$rootScope.isTrascError = false;
               			}, 3000);
                	}else{
                		$rootScope.isTrascError = true;
                		FlashService.Error(appConstants.exceptionMessage);
                		$scope.continuesave = false;
                		$timeout(function() {
                			$rootScope.isTrascError = false;
                		}, 3000);
                	}
                });
            }
        };
        
        $scope.showDeleteConfirmationPopup=function(menu) {
        	 if($scope.active){
                     $scope.confirmationHeading="Activate";
        		 $('#menu-delete-model').modal('show');
        	 }else{
                     $scope.confirmationHeading="Deactivate";
                 }
                  $('#menu-delete-model').modal('show');
        }
    
        $scope.deleteMenu = function() {
        	 $('#menu-delete-model').modal('hide');
        	   $scope.updateRow($scope.menu);
        }
        $scope.activeField = function() {
            if($scope.active){
        	$scope.active=false;
            }else{
                $scope.active=true;
            }
       }

        $scope.reset = function() {
            $scope.menu = {};
        };
        
        $scope.cancelModel = function() {
            $scope.init();
        }
        
        $scope.clearSelectedRow = function() {
            // clearing row selection
            resetSelection();
            toggleButtons();
    	}
        $scope.clearSearch = function () {
        	$scope.searchMenu.menuName = "";
                $scope.clearSelectedRow();
        	 $scope.init();
        };

        
        $scope.init();
        // $scope.sort('menuName');
    }]);