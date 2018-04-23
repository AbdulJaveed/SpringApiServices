/**
 * Passport Info Controller
 */
'use strict';
angular.module('myApp.passportInfoController', [])
        .controller('passportInfoController', ['$scope', '$rootScope', '$location', '$timeout','FlashService',
                                'appConstants', 'LookupService', 'employeeBasicInfoService', 'configData',
                                '$q', '$localStorage', '$window',
        function ($scope, $rootScope, $location, $timeout, FlashService, appConstants, LookupService, employeeBasicInfoService, configData, $q, $localStorage, $window) {
            var vm = this;
            $scope.usOrgCode = configData.usOrgCode;
            $scope.usOrg = $localStorage.usOrg;
            //vm.employeeId = 1; //Change this for dynamic passing of employeeId
            vm.employeeId = $localStorage.employeeId;
            vm.searchByDate = $localStorage.searchByDate;
            $localStorage.isReturn = false;
            vm.origPassportInfo = {};
            $scope.nonEditable = $localStorage.isSaveEdit;
            vm.passportInfo = {
                passportNumber : "",
                issuingAuthority : "",
                nationality : "",
                placeOfIssue : ""
            };
            vm.visaInfoList = [];
//            var visaInfo = {
//                visaNumber : "",
//                issuanceAuthority : "",
//                placeOfIssue : "",
//                entryType : ""
//            };
//            vm.visaInfoList.push(visaInfo);

            vm.nationalityList = [];
            vm.countryVisaList = [];
            vm.issuingAuthorityList = [];
            vm.entryTypeList = [];

            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1;
            var yyyy = today.getFullYear();
            if(dd<10){
                dd='0'+dd;
            }
            if(mm<10){
                mm='0'+mm;
            }
            vm.today = yyyy+'-'+mm+'-'+dd;

            vm.initController = function() {
                if(vm.employeeId && vm.searchByDate) {
                    var inputObj = { "employeeId" : vm.employeeId , "searchDate" : vm.searchByDate};

                    var  urlPath = $location.path();
                    if(urlPath.includes('passportInfo-sf')) {
                      if($rootScope.globals.userDTO!==undefined){
                        var loggedInEmpId = $rootScope.globals.userDTO.id;
                        if( $localStorage.employeeId == loggedInEmpId ) {
                          $scope.iseditable = true;
                        } else {
                          $scope.iseditable = false;
                        }
                      }
                    } else {
                      $scope.iseditable = true;
                    }

                    initData().then(function() {
                        console.log(vm.employeeId);
                        //employeeBasicInfoService.getPassportInfo(vm.employeeId).then(function success(result) {
                        employeeBasicInfoService.getPassportInfoByEmployeeIdAndDate(inputObj).then(function success(result) {
                            console.log(result);
                            vm.origPassportInfo = result;
                            vm.passportInfo = {
                                passportNumber : result.passportNumber,
                                //nationality : parseInt(result.nationality),
                                issueDate : result.passportDateOfIssue,
                                expiryDate : result.passportDateOfExpiry,
                                passportIssuanceAuthority : parseInt(result.passportIssuanceAuthority),
                                placeOfIssue : result.passportPlaceOfIssue,
                                secondPassportExists : result.secondPassportExists
                            };
                            angular.forEach(result.osiEmpVisaDetails, function(visaDetails) {
                                vm.visaInfo = {
                                    visaId : visaDetails.visaId,
                                    visaNumber : visaDetails.visaNumber,
                                    visaIssueDate : visaDetails.dateOfIssue,
                                    visaExpiryDate : visaDetails.dateOfExpiry,
                                    visaIssuanceAuthority : parseInt(visaDetails.issuanceAuthority),
                                    visaPlaceOfIssue : visaDetails.placeOfIssue,
                                    visaType : visaDetails.visaType,
                                    visaEntryType : visaDetails.singleMultiple
                                };
                                vm.visaInfoList.push(vm.visaInfo);
                            });
                            if(vm.passportInfo.passportNumber === "" ||
                               vm.passportInfo.passportNumber === null ||
                               vm.passportInfo.passportNumber === undefined) {
                                vm.passportInfo.issueDate = vm.today;
                                vm.passportInfo.expiryDate ="";
                            }
                            if(vm.visaInfoList.length === 0) {
                                var visaInfo = {
                                		visaNumber : "",
                                        visaExpiryDate:"",
                                        visaType:"",
                                        visaEntryType:"",
                                        visaIssuanceAuthority:""
                                };
                                vm.visaInfoList.push(visaInfo);
                            }
                        }, function error(error){
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
                        console.log(vm.passportInfo);
                    }).catch(function(error) {
                        console.log('ERROR in Getting All Data');
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
            };
            vm.clearPassport = function (){
            	window.location.href = "#/employees";
       		 $localStorage.isReturn = true;
            }
            function initData() {
                var deferred = $q.defer();
                var urlCalls = [//employeeBasicInfoService.getAllCountries(),
                               /* LookupService.getLookupByLookupName('NATIONALITY'),*/
                                /*employeeBasicInfoService.getAllCountryVisas(countryId),*/
                				employeeBasicInfoService.getAllCountries(),
                                LookupService.getLookupByLookupName('ISSUING_AUTHORITY'),
                                LookupService.getLookupByLookupName('ENTRY_TYPE')
                               ];
                $q.all(urlCalls).then(function (result) {
                    //vm.nationalityList = result[0];
                    vm.nationalityList = $localStorage.nationalityList;
                    vm.countryList = result[0];
                    vm.issuingAuthorityList = result[1].osiLookupValueses;
                    vm.entryTypeList = result[2].osiLookupValueses;
                    deferred.resolve();
                });
                return deferred.promise;
            }
            vm.getCountryVisasById = function(countryId){
            	console.log("cpodfd "+countryId);
            	employeeBasicInfoService.getAllCountryVisas(countryId).then(function(result){
            		console.log("dsfddfdf");
            		console.log(result);
            		vm.countryVisaList = result;
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
            vm.submitPassportInfo = function () {
                var invalid = vm.validateInfo();
        	if(!invalid) {
                    var passportInfo = vm.origPassportInfo;
                    passportInfo.passportNumber = vm.passportInfo.passportNumber;
                    //passportInfo.nationality = vm.passportInfo.nationality;
                    passportInfo.passportDateOfIssue = vm.passportInfo.issueDate;
                    passportInfo.passportDateOfExpiry = vm.passportInfo.expiryDate;
                    passportInfo.passportIssuanceAuthority = vm.passportInfo.passportIssuanceAuthority;
                    passportInfo.passportPlaceOfIssue = vm.passportInfo.placeOfIssue;
                    passportInfo.secondPassportExists = vm.passportInfo.secondPassportExists;
                    passportInfo.effectiveEndDate = configData.maxEffecEndDate;

                   /* angular.forEach(vm.origPassportInfo.osiEmpVisaDetails, function(empVisaDetails) {
                        angular.forEach(vm.visaInfoList, function(visaDetails) {
                            if(visaDetails.visaNumber === empVisaDetails.visaNumber) {
                                visaDetails.visaId = empVisaDetails.visaId;
                                visaDetails.countryOfVisa = empVisaDetails.countryOfVisa;
                            	if(visaDetails.visaId == undefined){
	                            	invalid = true;
	                            	 $rootScope.isTrascError = true;
	                                 $rootScope.flash = {
	                                     message: 'Visa Number already Exist',
	                                     type: 'error'
	                                 };
	                                 $timeout(function () {
	                                   $rootScope.isTrascError = false;
	                                 }, 1500);
                            	}
                            }
                        });
                    });*/
                   /* vm.visaInfoIds = [];
                    
                    angular.forEach(vm.visaInfoList, function(visaDetails) {
                        
                    	 vm.visaInfoIds.push(visaDetails.visaNumber);
                          
                        
                    });

        			vm.uniqVisaIds= _.uniq(vm.visaInfoIds);
        		
        			if(vm.uniqVisaIds.length !== vm.visaInfoIds.length){
	        				invalid = true;
	                   	 $rootScope.isTrascError = true;
	                        $rootScope.flash = {
	                            message: 'Visa Number already Exist',
	                            type: 'error'
	                        };
	                        $timeout(function () {
	                          $rootScope.isTrascError = false;
	                        }, 1500);
        			}*/
                    if(!invalid){
	                    vm.empVisaInfoList = [];
	                    angular.forEach(vm.visaInfoList, function(visaDetails) {
	                        console.log(visaDetails);
	
	                        if(visaDetails.visaId === undefined) {
	                            visaDetails.visaId = 0;
	                        }
	
	                        /*if(visaDetails.visaNumber !== undefined &&
	                           visaDetails.visaNumber !== null &&
	                           visaDetails.visaNumber !== "") {*/
	                            vm.visaInfo = {
	                                visaId : visaDetails.visaId,
	                                visaNumber : visaDetails.visaNumber,
	                                dateOfIssue : visaDetails.visaIssueDate,
	                                dateOfExpiry : visaDetails.visaExpiryDate,
	                                issuanceAuthority : visaDetails.visaIssuanceAuthority,
	                                placeOfIssue : visaDetails.visaPlaceOfIssue,
	                                visaType : visaDetails.visaType,
	                                singleMultiple : visaDetails.visaEntryType,
	                                countryOfVisa : visaDetails.countryOfVisa
	                            };
	                            vm.empVisaInfoList.push(vm.visaInfo);
	                      /*  }*/
	                    });
	                    passportInfo.osiEmpVisaDetails = vm.empVisaInfoList;
	
	                    employeeBasicInfoService.savePassportInfo(passportInfo).then( function (result){
	                        $rootScope.isTrascError = true;
	                        //FlashService.Success(result.messge);
	                        FlashService.Success(appConstants.successMessage);
	                        $timeout(function () {
	                            $rootScope.isTrascError=false;
	                            $window.location.reload();
	                        }, 2000);
	                    }).catch(function(error){
	                    	var msg = appConstants.exceptionMessage;
		               		  if(error.data.httpStatus){ 
		               			  msg=error.data.errorMessage; 
		               		  }
	                        $rootScope.isTrascError = true;
	                        FlashService.Error(msg);
	                        $scope.failureTextAlert = msg;
	                        $timeout(function () {
	                            $scope.failureTextAlert = false;
	                        }, 5000);
	                    });
                }
        	}
            };

            vm.validateInfo = function () {
                var invalid = false;
                var invalidVisaDate = false;
                var field = "";
                if(vm.passportInfo.passportNumber === null ||
                   vm.passportInfo.passportNumber === undefined ||
                   vm.passportInfo.passportNumber === "" || !vm.passportInfo.passportNumber) {
                	 invalid = true;
                     field = "Please Enter Passport Number";
                }else if(vm.passportInfo.expiryDate === null ||
                    vm.passportInfo.expiryDate === undefined ||
                    vm.passportInfo.expiryDate === "" || !vm.passportInfo.expiryDate) {
              invalid = true;
              field = "Please Select Date of Expiry";
          } 
              else if(vm.passportInfo.passportIssuanceAuthority === null ||
                       vm.passportInfo.passportIssuanceAuthority === undefined ||
                       vm.passportInfo.passportIssuanceAuthority === "" || !vm.passportInfo.passportIssuanceAuthority) {
                        invalid = true;
                        field = "Please Select Nationality";
                    } /*else if(vm.passportInfo.issueDate === null ||
                              vm.passportInfo.issueDate === undefined ||
                              vm.passportInfo.issueDate === "") {
                        invalid = true;
                        field = "Date of Issue";
                    } *//*else if(vm.passportInfo.issuingAuthority === null ||
                              vm.passportInfo.issuingAuthority === undefined ||
                              vm.passportInfo.issuingAuthority === "") {
                        invalid = true;
                        field = "Issuing Authority";
                    } else if(vm.passportInfo.placeOfIssue === null ||
                              vm.passportInfo.placeOfIssue === undefined ||
                              vm.passportInfo.placeOfIssue === "") {
                        invalid = true;
                        field = "Place of Issue";
                    }*/
             

                angular.forEach(vm.visaInfoList, function(visaDetails) {
                    if(visaDetails.visaIssuanceAuthority == undefined ||
                        visaDetails.visaIssuanceAuthority == null || visaDetails.visaIssuanceAuthority===""){
                        visaDetails.visaIssuanceAuthority = null;
                    } if(visaDetails.visaExpiryDate === null ||
                        visaDetails.visaExpiryDate === undefined || visaDetails.visaExpiryDate===""){
                        visaDetails.visaExpiryDate = null;
                    }
                    
                    if(visaDetails.visaType === null ||
                        visaDetails.visaType === undefined || visaDetails.visaType===""){
                        visaDetails.visaType = null;
                    }
                    if(visaDetails.visaEntryType === null ||
                        visaDetails.visaEntryType === undefined ||visaDetails.visaEntryType===""){
                        visaDetails.visaEntryType = null;
                    }
                    if(visaDetails.visaIssuanceAuthority === null && (visaDetails.visaExpiryDate != null ||
                    visaDetails.visaType != null  || visaDetails.visaEntryType != null)) {
                        invalid = true;
                        field = "Please Select Country of Visa";
                    }
                       /* if(visaDetails.visaIssueDate === null ||
                           visaDetails.visaIssueDate === undefined ||
                           visaDetails.visaIssueDate === "") {
                            invalid = true;
                            field = "Date of Issue";
                        } */
                        else  if( visaDetails.visaType === null && (visaDetails.visaExpiryDate != null 
                            || visaDetails.visaIssuanceAuthority != null  || visaDetails.visaEntryType != null)) {
                            invalid = true;
                            field = "Please Enter Visa Type";
                        }  else if(visaDetails.visaEntryType === null && (visaDetails.visaExpiryDate != null 
                            ||  visaDetails.visaIssuanceAuthority != null || visaDetails.visaType != null)) {
                            invalid = true;
                            field = "Please Select Entry Type";
                        } 
                        else if( visaDetails.visaExpiryDate === null && (visaDetails.visaIssuanceAuthority != null
                            || visaDetails.visaType != null  || visaDetails.visaEntryType != null)) {
                            invalid = true;
                            field = "Please Select Date of Expiry";
                        } /*else if(visaDetails.visaIssuanceAuthority === null ||
                                  visaDetails.visaIssuanceAuthority === undefined ||
                                  visaDetails.visaIssuanceAuthority === "") {
                            invalid = true;
                            field = "Issuance Authority";
                        } else if(visaDetails.visaPlaceOfIssue === null ||
                                  visaDetails.visaPlaceOfIssue === undefined ||
                                  visaDetails.visaPlaceOfIssue === "") {
                            invalid = true;
                            field = "Place of Issue";
                        } */

                        if(visaDetails.visaIssueDate > visaDetails.visaExpiryDate) {
                            invalid = true;
                            invalidVisaDate = true;
                        }
                   // }
                });

                if(invalid) {
                    /*if(invalidVisaDate) {
                        $rootScope.isTrascError = true;
                        $rootScope.flash = {
                            message: "Visa's date of issue should be less than date of expiry!",
                            type: 'error'
                        };
                        $timeout(function () {
                            $rootScope.isTrascError = false;
                        }, 1500);
                    } else {*/
                        $('#'+field).focus();
                        $rootScope.isTrascError = true;
                        $rootScope.flash = {
                            message: field,
                            type: 'error'
                        };
                        $timeout(function () {
                          $rootScope.isTrascError = false;
                        }, 1500);
                    /*}*/
        	}

               /* if(!invalid) {
                    if(vm.passportInfo.issueDate > vm.passportInfo.expiryDate) {
                        invalid = true;
                        $rootScope.isTrascError = true;
                        $rootScope.flash = {
                            message: "Passport's date of issue should be less than date of expiry!",
                            type: 'error'
                        };
                        $timeout(function () {
                            $rootScope.isTrascError = false;
                        }, 1500);
                    }
                }*/

        	return invalid;
            };

            vm.clearPassportInfo = function () {
                vm.passportInfo.passportNumber = "";
                vm.passportInfo.nationality = "";
                vm.passportInfo.issueDate = null;
                vm.passportInfo.expiryDate = null;
                vm.passportInfo.issuingAuthority = "";
                vm.passportInfo.placeOfIssue = "";
                vm.passportInfo.secondPassportExists = 0;

                vm.visaInfoList = [];
                var visaInfo = {
                		visaNumber : "",
                        visaExpiryDate:"",
                        visaType:"",
                        visaEntryType:"",
                        visaIssuanceAuthority:""
                };
                vm.visaInfoList.push(visaInfo);
            };

            vm.addVisa = function() {
            	
            	
            	var visaList = _.last(vm.visaInfoList);
            	
            	if(visaList.visaIssuanceAuthority !="" && visaList.visaExpiryDate !="" && visaList.visaType != "" && visaList.visaEntryType !="" &&
            			visaList.visaIssuanceAuthority !=undefined && visaList.visaExpiryDate !=undefined && visaList.visaType != undefined && visaList.visaEntryType !=undefined		
            	){
                var visaInfo = {
                    visaNumber : "",
                    visaExpiryDate:"",
                    visaType:"",
                    visaEntryType:"",
                    visaIssuanceAuthority:""
                    
                };
                vm.visaInfoList.push(visaInfo);
            	}else{
            		 $rootScope.isTrascError = true;
                     $rootScope.flash = {
                         message: "Please enter all mandatory fields",
                         type: 'error'
                     };
                     $timeout(function () {
                         $rootScope.isTrascError = false;
                     }, 1500);
            	}
                
            };

            vm.removeVisa = function(i) {
                //if(vm.visaInfoList.length === 1 && i === 0) {
                if(vm.visaInfoList.length === 1) {
                    vm.visaInfoList = [];
                    var visaInfo = {
                    		visaNumber : "",
                            visaExpiryDate:"",
                            visaType:"",
                            visaEntryType:"",
                            visaIssuanceAuthority:""
                    };
                    vm.visaInfoList.push(visaInfo);
                } else {
                    vm.visaInfoList.splice(i, 1);
                }
            };
            $scope.cancelProfile = function(){
                if($localStorage.currentEmployeeId!=$localStorage.loggedInEmployeeId)
                    $window.location.href='#/employeeInfo-sf';
                else
                $window.location.href='#/profile';
            }
            $scope.cancelEmployeeInfo = function(){
                
                if($rootScope.profileAcessHR.profile)
                $location.path("/employeeInfo");
           else if($rootScope.profileAcessHR.profiles)
               $location.path("/employeeInfos");
            }
            vm.initController();
        }

]);
