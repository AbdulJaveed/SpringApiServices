(function() {
	'use strict';

	angular.module('myApp.empBankDetailsConfigController', []).controller(
			'empBankDetailsConfigController', empBankDetailsConfigController);
	empBankDetailsConfigController.$inject = [ 'configData', '$timeout','appConstants', '$location', '$window', '$scope', '$rootScope','empBankDetailsService', 'empBankDetailsConfigService', 'SharedDataService', '$localStorage', 'empBankDetails', '$uibModalInstance', 'FlashService'];
	function empBankDetailsConfigController(configData, $timeout,appConstants, $location, $window, $scope, $rootScope,empBankDetailsService, empBankDetailsConfigService, SharedDataService, $localStorage, empBankDetails, $uibModalInstance , FlashService) {
        var vm = this;
        $scope.usOrgCode = configData.usOrgCode;
		vm.temp = {};
		vm.excludedOpts = [];
		vm.merged = [];
		vm.merge = [];
		vm.availableOptions = [];
        vm.submitSuccess = false;
        $scope.nonEditable = $localStorage.isSaveEdit;

        vm.exclFunctionIds = [];
		vm.osiuserList = [];
        vm.alert = true;
        vm.userRespIds = [];
        vm.userUpdate = false;
        vm.infoView = false;
        $rootScope.confirmationHeading="";
        $rootScope.isFunctionExclusionDataModified= false;
        $rootScope.isTrascError = false;
        $scope.iseditable = true;


        var mappedJsonToUi = function(data){
            vm.empBankDetails = data;

        };

        vm.init = function(){
					var  urlPath = $location.path();
					if(urlPath.includes('empBankList-sf')) {
						if($rootScope.globals.userDTO!==undefined){
							var loggedInEmpId = $rootScope.globals.userDTO.id;
							if( $localStorage.employeeId == loggedInEmpId ) {
								$scope.iseditable = false; // TO-DO :: true if editable for self
							} else {
								$scope.iseditable = false;
							}
						}
					} else {
						$scope.iseditable = false;
					}
            setTitle();
            if(empBankDetails){
               mappedJsonToUi(empBankDetails);
            }

        };
        vm.empBankDetails = {
    	accountHolderName : '',
        accountNumber : '',
        bankName : '',
        branchName : '',
        ifscCode : '',
        active : ''
        };

        function setTitle() {
            if(empBankDetails) {
                vm.title = 'Edit';
            } else {
                vm.title = 'Create';
            }
        }

        vm.close = function(){
            $uibModalInstance.close();
        };
        $scope.isValidBankDetails = false;
        function validateBankDetails() {
            var msg = '';
            if(vm.empBankDetails.accountHolderName != null && vm.empBankDetails.accountHolderName.trim() != '') {
                if(vm.empBankDetails.accountNumber != null && vm.empBankDetails.accountNumber.trim() != '') {
                    if(vm.empBankDetails.bankName != null && vm.empBankDetails.bankName.trim() != '') {
                        if(vm.empBankDetails.branchName != null && vm.empBankDetails.branchName.trim() != '') {
                            if(vm.empBankDetails.ifscCode != null && vm.empBankDetails.ifscCode.trim() != '') {
                                $scope.isValidBankDetails = true;
                            } else {
                                msg = 'Please enter valid IFSC Code';
                            }
                        } else {
                            msg = 'Please enter valid Branch Name';
                        }
                    } else {
                        msg = 'Please enter valid Bank Name';
                    }
                } else {
                    msg = 'Please enter valid Account Number';
                }
            } else {
                msg = 'Please enter valid Account Holder Name';
            }
            if(!$scope.isValidBankDetails) {
                $rootScope.isTrascError = true;
                FlashService.Error(msg);
                $timeout(function() {
                    $rootScope.isTrascError = false;
                }, 2000);
            }
        }
    	vm.saveEmpBankDetails = function(){
            console.log(vm.empBankDetails);
            validateBankDetails();
            if($scope.isValidBankDetails) {
                console.log($rootScope.empBankList);

                var isDuplicateDtls = false;
                angular.forEach($rootScope.empBankList, function(empBankDetails) {
                    if(empBankDetails.id !== vm.empBankDetails.id &&
                    empBankDetails.accountNumber === vm.empBankDetails.accountNumber &&
                    empBankDetails.bankName === vm.empBankDetails.bankName) {
                    isDuplicateDtls = true;
                    }
                });

                if(isDuplicateDtls) {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Account Number and Bank Name combination should be unique!");
                    $timeout(function() {
                        $rootScope.isTrascError = false;
                    }, 2000);
                } else {
                    vm.empBankDetails.employeeId = $localStorage.employeeId;
                    empBankDetailsConfigService.saveEmpBankDetails(vm.empBankDetails).then(function(data){
                                console.log(data);
                                if(data != null){
                                    $uibModalInstance.close(data);
                                    $rootScope.isTrascError = true;
                                    var msg = appConstants.successMessage;
                                    FlashService.Success(msg);
                                    $timeout(function () {
                                        $rootScope.isTrascError=false;
                                    }, 2000);
                                }
                        }, function error(error){
                            console.log(error.data);
                            $rootScope.isTrascError = true;
                            FlashService.Error(error.data.errorMessage);
                            $timeout(function () {
                                $rootScope.isTrascError=false;
                            }, 2000);
                        });
                }
            }
        };

        vm.init();

    }
})();
