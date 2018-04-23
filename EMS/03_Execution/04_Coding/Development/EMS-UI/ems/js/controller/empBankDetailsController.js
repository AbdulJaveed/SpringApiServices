(function() {
	'use strict';

	angular.module('myApp.empBankDetailsController', []).controller(
			'empBankDetailsController', empBankDetailsController);
	empBankDetailsController.$inject = [ 'configData', '$timeout','appConstants', '$location', '$window', '$scope', '$rootScope', 'empBankDetailsService', 'SharedDataService', '$localStorage', '$uibModal', '$route'];
	function empBankDetailsController(configData, $timeout,appConstants, $location, $window, $scope, $rootScope, empBankDetailsService, SharedDataService, $localStorage, $uibModal, $route) {
        var vm = this;
        $scope.usOrgCode = configData.usOrgCode;
		vm.temp = {};
		vm.excludedOpts = [];
		vm.merged = [];
		vm.merge = [];
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
        $scope.selectedRowsIndexes = [];

        $scope.successTextAlert = "";
    	$scope.showSuccessAlert = false;

        if($localStorage.employeeId && $localStorage.searchByDate) {
            var inputObj = { "employeeId" : $localStorage.employeeId , "searchDate" : $localStorage.searchByDate};
            //empBankDetailsService.getAllEmpBankDetails().then(function(data){
            empBankDetailsService.getEmpBankDetailsByEmployeeIdAndDate(inputObj).then(function(data){
                    $scope.bankList = data;
                    console.log(data);
                    vm.bankList = data;
                    $rootScope.empBankList = data;
            });
        }

        $timeout(function() {
        // getting the available operations assigned for the logged in user
        $scope.availOperations = $localStorage.availOperations;
        console.log($scope.availOperations);
        }, 400);


        $scope.operationsGenericFunction = function(doFunction, url) {
            // passing selected operation url
            $rootScope.isTrascError = false;
            $scope.opeationsURL = url;
            if (doFunction === 'Create') {
                console.log("CreateMod");
                createEmpBankDetTrx();
            }
            if (doFunction === "View" && !$scope.disable_View) {
                console.log("ViewMod");
                viewEmpBankDetTrx($scope.selectedRowsIndexes[0]);
            }
            if (doFunction === "Edit" && !$scope.disable_Edit) {
                console.log("EditMod");
                editEmpBankDetTrx($scope.selectedRowsIndexes[0]);
            }

        };

        $scope.editSelectedRowDetails = function(empBankDetails) {
					if($scope.iseditable) {
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
                editEmpBankDetTrx(empBankDetails);
                $scope.isSelectedRow = empBankDetails.id;
                toggleButtons();
            }
					}
        };


        function createEmpBankDetTrx() {
            console.log("222");
            openEmpBankDetailsModal(null,null);
        }

        $scope.isRowSelected = function (rowIndex) {
            return $scope.selectedRowsIndexes.indexOf(rowIndex) > -1;
        };

        $scope.selectRow = function (rowIndex) {
            console.log("selectrow");
            console.log(rowIndex);
            $scope.selectedRowsIndexes = [rowIndex];
            toggleButtons();
        };

        function toggleButtons() {
            // edit, view and delete button toggle
            if ($scope.selectedRowsIndexes.length == 0) {
                console.log("eqZero");
                $scope.disable_Edit = true;
                $scope.disable_View = true;
            } else {
                console.log("elseNotZero");
                $scope.disable_Edit = false;
                $scope.disable_View = false;
            }
        }


        function editEmpBankDetTrx(empBankDetails){
            if(empBankDetails) {
                console.log(empBankDetails);
                empBankDetailsService.getEmpBankDetailsById(empBankDetails.id).then(function(data){
                    if(data.errorCode!=null && data.errorCode!=undefined && data.errorCode!=''){
                        $rootScope.isTrascError = true;
                        FlashService.Error(data.errorCode + ": " + data.errorMessage);
                        $timeout(function() {
                                $rootScope.isTrascError = false;
                        }, 4000);
                    }else{
                        console.log("editopenmodal");
                        console.log(data);
                        openEmpBankDetailsModal(data,null);
                    }

                });
            }
        }
    //todo
        function viewEmpBankDetTrx(empBankDetails){
            if(empBankDetails) {
                empBankDetailsService.getEmpBankDetailsById(empBankDetails.id).then(function(data){
                    if(data.errorCode!=null && data.errorCode!=undefined && data.errorCode!=''){
                        $rootScope.isTrascError = true;
                        FlashService.Error(data.errorCode + ": " + data.errorMessage);
                        $timeout(function() {
                                $rootScope.isTrascError = false;
                        }, 4000);
                    }else{
                        openEmpBankDetailsModal(data, true);
                    }

                });
            }
        }
    //todo
        function openEmpBankDetailsModal(empBankDetailsObject, viewStatus) {
            console.log(empBankDetailsObject);
            var empBankModalInstance = $uibModal.open({
                templateUrl : 'views/empBankDetails.html',
                controller : 'empBankDetailsConfigController',
                controllerAs : 'vm',
                backdrop: 'static',
                keyboard: false,
                windowClass: 'empBank',
                bindToController: true,
                resolve: {
                    empBankDetails: function () {
                        if(empBankDetailsObject) {
                            return empBankDetailsObject;
                        }
                    },empBankDetailsView: function () {
                    return viewStatus;
                }

                }
            });

            empBankModalInstance.result.then(function (data) {
                console.log(data);
                if(angular.isString(data)) {
                        $rootScope.isTrascError = true;
                        FlashService.Success(data);
                        $timeout(function() {
                                $rootScope.isTrascError = false;
                        }, 4000);
                }
                $route.reload();
            });
        }

				vm.init = function(){
					var  urlPath = $location.path();
					if(urlPath.includes('empBankList-sf')) {
						if($rootScope.globals.userDTO!==undefined){
							var loggedInEmpId = $rootScope.globals.userDTO.id;
							if( $localStorage.employeeId == loggedInEmpId ) {
								$scope.iseditable = true; // TO-DO :: true if editable for self
							} else {
								$scope.iseditable = false;
							}
						}
					} else {
						$scope.iseditable = true;
					}

				};
				vm.init();

    }

        //This event is used to save changes we made in the current form . Event is immeted from userlist controller.

})();
