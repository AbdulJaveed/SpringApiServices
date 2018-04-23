/**
 * Employee Basic Info Controller
 */
'use strict';
angular.module('myApp.employeeBasicInfoController', [])
        .controller('employeeBasicInfoController', ['$scope', '$filter', '$document','$rootScope','$window', '$location', '$http', '$localStorage',
                    '$timeout','FlashService','$cookieStore','appConstants', 'FlexiFieldService',
                    'LookupService', 'employeeBasicInfoService', 'Upload', 'configData','organizationService',
        function ($scope, $filter, $document, $rootScope, $window, $location, $http, $localStorage, $timeout, FlashService, $cookieStore, appConstants, FlexiFieldService, LookupService, employeeBasicInfoService, Upload, configData,organizationService) {
        	var vm = this;
        	vm.basicInfo = {
        			osiEmpAttachments:[]
        	};
        	if(vm.basicInfo.employeeId == null || vm.basicInfo.employeeId == undefined)
						vm.basicInfo.effectiveEndDate = configData.maxEffecEndDate;
						vm.basicInfo.terminationDate = configData.maxEffecEndDate;
						$scope.usOrgCode = configData.usOrgCode;
        	vm.gendersList = [];
        	$localStorage.isReturn = false;
        	$scope.nonEditable = true;
        	 $localStorage.isSaveEdit = true;

        	vm.titlesList = [];
        	vm.empTypesList = [];
        	vm.nationalityList = [];
        	vm.osiOrganizations = [];
          vm.isChecked = isChecked;
          var flexValidFlag = false;
          vm.calculateAge = calculateAge;
          vm.parseDate = parseDate;
					//$scope.isContractor = true;
					//$scope.imageEditable = true;

          vm.bgCheckList = [{"name":"Yes", "value":"1"}, {"name":"No", "value":"0"}];
			var today = new Date();
			var dd = today.getDate()-1;
			var mm = today.getMonth()+1; //January is 0!

			var yyyy = today.getFullYear();
			if(dd<10){
			    dd='0'+dd;
			}
			if(mm<10){
			    mm='0'+mm;
			}
			var today = yyyy+'-'+mm+'-'+dd;

			vm.minDOH = today;


			initController();
			
        	//if($localStorage.iseditable)
          if($scope.iseditable)
						$scope.iseditable = $localStorage.iseditable;
						

          		vm.gendersList = $localStorage.gendersList;
          		vm.titlesList = $localStorage.titlesList;
        	  	vm.empTypesList = $localStorage.empTypesList;
        		vm.nationalityList = $localStorage.nationalityList;
        		vm.maritialstatusList = $localStorage.maritialstatusList;
        		vm.osiOrganizations = $localStorage.osiOrganizations;

        	/*LookupService.getLookupByLookupName('GENDER').then(function (result) {
        		console.log(result);
        		vm.gendersList = result.osiLookupValueses;
        	});
        	LookupService.getLookupByLookupName('TITLE').then(function (result) {
        		console.log(result);
        		vm.titlesList = result.osiLookupValueses;
        	});
        	LookupService.getLookupByLookupName('EMPLOYEE TYPE').then(function (result) {
        		console.log(result);
        		vm.empTypesList = result.osiLookupValueses;
        	});
        	LookupService.getLookupByLookupName('NATIONALITY').then(function (result) {
        		console.log(result);
        		vm.nationalityList = result.osiLookupValueses;
        	});
          LookupService.getLookupByLookupName('Maritial_Status').then(function(result) {
            console.log(result);
            vm.maritialstatusList = result.osiLookupValueses;
          });
        	employeeBasicInfoService.getAllOrganizations().then(function (result) {
        		console.log(result);
        		vm.osiOrganizations = result;
        	});*/
        	vm.setFullname = function () {
        		vm.basicInfo.fullName = '';
        		vm.basicInfo.fullName = (vm.basicInfo.firstName != undefined ? vm.titleCase(vm.basicInfo.firstName) : '')
        								+ ' ' +(vm.basicInfo.middleName != undefined ? vm.titleCase(vm.basicInfo.middleName)  : '')
        								+ ' ' +(vm.basicInfo.lastName != undefined ? vm.titleCase(vm.basicInfo.lastName)  : '');
        	}

        function isChecked() {
          console.log('in is checked');
          vm.basicInfo.onMilitaryService = vm.onMilitaryService ? 1 : 0;
        }
        vm.preview = {};
        var file = {};
        this.load = loadFile;
        function loadFile(fileUpload) {
            $rootScope.isTrascError = false;

            $rootScope.isTrascError = false;
            if (fileUpload) {
              if (fileUpload && (fileUpload.type == 'application/pdf' || fileUpload.type == 'image/png' ||
                  fileUpload.type == 'image/jpeg' || fileUpload.type == 'image/gif')) {
                if (fileUpload.size > 0 && fileUpload.size <= 5242880) {
                  file.fileType = fileUpload.type;
                  file.originalFileName = fileUpload.name;
                  file.duplicateFileName = vm.preview.duplicateFileName;
                  file.attachmentId = vm.preview.attachmentId;
                  Upload.base64DataUrl(fileUpload).then(function () {
                    var str = fileUpload.$ngfDataUrl;
                    console.log(fileUpload.type);
                    file.fileContent = str.substring(str.indexOf(',') + 1);
                    if (!(_.isEmpty(file))) {
                      /*if (fileUpload.type == 'application/pdf')
                        vm.preview.content = 'components/fileUpload/pdf.jpg';
                      else if(fileUpload.type == 'text/plain')
                      	vm.preview.content = 'components/fileUpload/txt.png';
                      else */
                        vm.preview.content = fileUpload;
                      vm.preview.name = fileUpload.name;
                      console.log(vm.preview.content);
                    }
                  });

                } else {
                  var msg = 'Image file size should be < 5MB';
                  vm.preview.content = undefined;
                  vm.preview.name = '';
                  file = {};
                  $rootScope.isTrascError = true;
                  $rootScope.flash = {
                    message: 'Image file size should be < 5MB',
                    type: 'error'
                  };
                  $timeout(function () {
                    $rootScope.isTrascError = false;
                  }, 1500);
                }
              } else {
                vm.preview.content = undefined;
                vm.preview.name = '';
                file = {};
                $rootScope.isTrascError = true;
                $rootScope.flash = {
                  message: 'Invalid File Format',
                  type: 'error'
                };
                $timeout(function () {
                  $rootScope.isTrascError = false;
                }, 1500);
              }
            }
          };
        vm.validateBasicInfo = function () {
        	var orgId = vm.basicInfo.orgId;
        	var empNumber = vm.basicInfo.employeeNumber;
        	var title = vm.basicInfo.title;

        	var fname = vm.basicInfo.firstName;
        	var mname = vm.basicInfo.middleName;
        	var lname = vm.basicInfo.lastName;
        	var flname = vm.basicInfo.fullName;
       // 	var esd = vm.basicInfo.effectiveStartDate;
        	var empType = vm.basicInfo.employeeType;
        	var gender = vm.basicInfo.gender;
        	var doh = vm.basicInfo.originalDateOfHire;
        	var nationality = vm.basicInfo.nationality;
        	var eed = vm.basicInfo.terminationDate;
        	var dateOfBirth = vm.basicInfo.dateOfBirth;
        	var maritalStatus = vm.basicInfo.maritalStatus;
          var bgCheck = vm.basicInfo.backgroundCheckStatus;
					var sysType = vm.basicInfo.systemType;

        	if(vm.empBasicInfo.$invalid) {
        		var invalid = false;
        		var field = undefined;
            var value = '';
        		if(orgId == undefined) {
        			invalid = true;
        			field = 'orgName';
              value = 'Organization';
        		}else if(empNumber == undefined) {
        			invalid = true;
        			field = 'employeeNumber';
              value = 'Employee Number';
        		}else if(title == undefined) {
        			invalid = true;
        			field = 'title';
              value = 'Title';
        		}else if(fname == undefined) {
        			invalid = true;
        			field = 'firstName';
              value = 'First Name';
        		}else if(lname == undefined) {
        			invalid = true;
        			field = 'lastName';
              value = 'Last Name';
        		}else if(eed == undefined) {
        			invalid = true;
        			field = 'esd';
              value = 'Termination Date';
        		}else if(empType == undefined) {
        			invalid = true;
        			field = 'employeeType';
              value = 'Employee Type';
        		}else if(gender == undefined) {
        			invalid = true;
        			field = 'gender';
              value = 'Gender';
        		}else if(doh == undefined) {
        			invalid = true;
        			field = 'doh';
              value = 'Date of Hire';
        		}/*else if(dob == undefined) {
        			invalid = true;
        			field = 'dob';
              value = 'Date Of Birth';
        		}*/else if(nationality == undefined) {
        			invalid = true;
        			field = 'nationality';
              value = 'Nationality';
        		}else if(maritalStatus == undefined) {
        			invalid = true;
        			field = 'Marital Status';
              value = 'Marital Status';
        		}/*else if(eed == undefined) {
        			invalid = true;
        			field = 'eed';
        		}*/else if(dateOfBirth == undefined) {
        			invalid = true;
        			field = 'Date Of Birth';
              value = 'Date Of Birth';
        		}else if(bgCheck){
              if(vm.basicInfo.backgroundDateCheck == undefined) {
                invalid = true;
                field = 'backgroundDateCheck';
                value = 'Background Date Check';
							}
            }/*else if(_.isEmpty(file) && vm.basicInfo.osiEmpAttachments.length == 0) {
        			invalid = true;
        			field = "image";
						}*/
						else if(sysType == undefined) {
							invalid = true;
							field = 'systemType';
							value = 'System Type';
						}
        		if(invalid) {
        			$('#'+field).focus();
        			$rootScope.isTrascError = true;
                    $rootScope.flash = {
                      message: 'Please enter the '+ value+' field',
                      type: 'error'
                    };
                    $timeout(function () {
                      $rootScope.isTrascError = false;
                    }, 5000);
        		}
        	}
        	return !invalid;
        };
        vm.save = function(decision) {

          console.log($localStorage.employeeId);
          vm.saveBasicInfo(decision);
        }

 vm.checkForValidDataOnSave = function(){

		var isValid = vm.validateBasicInfo();
		vm.validateFlexFields();
		if(isValid && flexValidFlag ) {
			vm.saveBasicInfo();
		}
 };
        vm.checkForValidData = function(){
        	//vm.saveFlexInfo();

    		var isValid = vm.validateBasicInfo();
    		vm.validateFlexFields();
    		if(isValid && flexValidFlag ) {
	    			organizationService.getOrgById(vm.basicInfo.orgId).then(function(data){

			    		if(data.active === 1) {
			    				$('#confirmationModal').modal('show');
			   			}else if(data.active === 0){
			   				$rootScope.isTrascError = true;
			   	            FlashService.Error("Organization is currently inactive");
			   	            $timeout(function () {
			   	            	$rootScope.isTrascError=false;
			   	            }, 5000);
			   			}
	    	});
    		}
		}
      vm.titleCase =  function(str) {
        	   var splitStr = str.toLowerCase().split(' ');
        	   for (var i = 0; i < splitStr.length; i++) {

        	       splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1).toLowerCase();
        	   }
        	  return splitStr.join(' ');
        	   
					}
				vm.setEmpStatus = function(){
					if(vm.empStatus)
						vm.basicInfo.employeeStatus = 1;
					else
					vm.basicInfo.employeeStatus = 0;
				}
        vm.saveBasicInfo = function (action) {
								if(($scope.imageEditable && !_.isEmpty(file)) || ($scope.imageEditable == undefined)) {
								
		        		if (!_.isEmpty(file)){
		        			 vm.basicInfo.osiEmpAttachments=[];
		        			 vm.basicInfo.osiEmpAttachments.push(file);
								}
								vm.basicInfo.employeeNumber = vm.basicInfo.employeeNumber.toUpperCase();
		        		vm.basicInfo.firstName = vm.basicInfo.firstName.toUpperCase();
		        		 if(vm.basicInfo.middleName !=undefined && vm.basicInfo.middleName !== null)
		        			 vm.basicInfo.middleName = vm.basicInfo.middleName.toUpperCase();
		        		 vm.basicInfo.lastName = vm.basicInfo.lastName.toUpperCase();
		        		 vm.basicInfo.fullName =  vm.titleCase(vm.basicInfo.fullName);
		        		 
                 if(vm.basicInfo.terminationDate == '')
                    vm.basicInfo.terminationDate = configData.maxEffecEndDate;
			        		 employeeBasicInfoService.saveBasicInfo(vm.basicInfo, action).then( function (result){

										$rootScope.isTrascError = true;
										var msg = appConstants.successMessage;
										FlashService.Success(msg);
										$timeout(function () {
			        					$rootScope.isTrascError=false;
			        					$localStorage.employeeId =result.employeeId;
										vm.basicInfo = result;
										//vm.basicInfo.nationality = parseInt(result.nationality);
										  $localStorage.employeeName = result.fullName;
						            	  $localStorage.employeeNumber = result.employeeNumber;
						            	  $localStorage.osiEmpAttachments = result.osiEmpAttachments;

										organizationService.getOrgById(result.orgId).then(function(data){
						        			$localStorage.orgName = data.orgName;
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
			        					var todayDate = yyyy+'-'+mm+'-'+dd;
			        					$localStorage.searchByDate = todayDate;

			        					var hrs = today.getHours();
			     						var mins = today.getMinutes();
			     						var sec = today.getSeconds();

			     						var today = yyyy+'-'+mm+'-'+dd+" "+hrs+":"+mins+":"+sec;
			        					var mydate = new Date(result.effectiveEndDate).getTime();

			        					var todayDate = new Date(today).getTime();
			        					console.log(result.effectiveEndDate+" -- "+mydate);
			        					console.log(today+" -- "+todayDate);

			   	            	     if((todayDate <= mydate && result.employeeType === 'Employee') || result.employeeType ==='EX EMPLOYEE'){
			   	            	    	 $localStorage.isSaveEdit= true;
			   	            	    	 $scope.nonEditable = true;

			        				}else if(todayDate > mydate){
			        					 $localStorage.isSaveEdit= false;
			   	            	    	 $scope.nonEditable = false;
											}
											if(vm.basicInfo.terminationDate == configData.maxEffecEndDate) {
												vm.basicInfo.terminationDate = '';
											}

											calculateAge();
			        			//		$window.location.reload();
			        			//		$location.path("/employeeInfo");
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

        	} else {
						$rootScope.isTrascError = true;
						var msg = 'Please Upload the Photo';
						FlashService.Error(msg);
						$timeout(function () {
							$rootScope.isTrascError=false;
						}, 2000);
					}
        }
        function initController(){
          console.log($localStorage);
          console.log('osi osi osi ::::::: ---   '+$localStorage.searchByDate);
          console.log('osi osi osi ::::::: ---   '+$localStorage.employeeId);
          employeeBasicInfoService.getAllCountries().then(function(data){
        	  vm.countryList = data;
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

          if($localStorage.employeeId)
            vm.isNew = false;
          else
            vm.isNew = true;

						var urlPath = $location.path();
        if(urlPath.includes('employeeInfo-sf')) {
          if($rootScope.globals.userDTO!==undefined){
            var loggedInEmpId = $rootScope.globals.userDTO.id;
            if( $localStorage.employeeId == loggedInEmpId ) {
							$scope.iseditable = true;
							$scope.imageEditable = true;
            } else {
							$scope.iseditable = false;
							$scope.imageEditable = false;
            }
          }
        } else if(urlPath.includes('profile')){
					$localStorage.employeeId = $rootScope.globals.userDTO.id;
				}else{
          $scope.iseditable = true;
        }
	        if($localStorage.employeeId && $localStorage.searchByDate) {
            var inputObject = { "employeeId" : $localStorage.employeeId , "searchDate" : $localStorage.searchByDate};
		        //employeeBasicInfoService.getBasicInfo($localStorage.employeeId).then(function success(result) {
            employeeBasicInfoService.getBasicInfoByIdAndDate(inputObject).then(function success(result) {
							vm.basicInfo = result;
						//	vm.basicInfo.nationality = parseInt(result.nationality);
							
							calculateAge();
		        	organizationService.getOrgById(result.orgId).then(function(data){
		        		if(data.active !==1){
		        			vm.osiOrganizations.push(data);

		        		}
	        			$localStorage.orgName = data.orgName;
	            	     $localStorage.employeeName = result.fullName;
	            	     $localStorage.employeeNumber = result.employeeNumber;
	            	     $localStorage.osiEmpAttachments = result.osiEmpAttachments;
	            	     	var today = new Date();
     						var dd = today.getDate();
     						var mm = today.getMonth()+1;
     						var hrs = today.getHours();
     						var mins = today.getMinutes();
     						var sec = today.getSeconds();
	     					var yyyy = today.getFullYear();
	     					if(dd<10){
	     					    dd='0'+dd;
	     					}
	     					if(mm<10){
	     					    mm='0'+mm;
	     					}
	     					var today = yyyy+'-'+mm+'-'+dd+" "+hrs+":"+mins+":"+sec;

	            	     var mydate = new Date(result.effectiveEndDate).getTime();
	            	     var todayDate = new Date(today).getTime();

	            	     	console.log(result.effectiveEndDate+" -- "+mydate);
     						console.log(today+" -- "+todayDate);

	            if((todayDate <= mydate && result.employeeType.toLowerCase() === 'employee') || result.employeeType.toLowerCase() ==='ex employee'){
	            	    	 $scope.nonEditable = true;
	            	    	 $localStorage.isSaveEdit= true;
		        	}else if(todayDate > mydate){
	            	    	 $scope.nonEditable = false;
	            	    	 $localStorage.isSaveEdit= false;
		        	}
              if(vm.basicInfo.terminationDate == configData.maxEffecEndDate) {
                vm.basicInfo.terminationDate = '';
              }
              /*if(result.employeeType.toLowerCase() === 'ex employee') {
                vm.basicInfo.terminationDate = undefined;
              }*/
              /*if(result.employeeType.toLowerCase() === 'contractor' || result.employeeType.toLowerCase() === 'intern') {
                $scope.isContractor = true;
                if(result.terminationDate == null)
                  vm.basicInfo.terminationDate = configData.maxEffecEndDate;;
              } else {
                $scope.isContractor = false;
              }*/
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
              if(vm.basicInfo.onMilitaryService === 1) {
                $('#onMilitaryService').prop('checked', true);
              } else {
                  $('#onMilitaryService').prop('checked', false);
              }
		        	if(result.osiEmpAttachments.length > 0) {
		        		vm.preview.content = result.osiEmpAttachments[0].fileContent;
		        		vm.preview.name = result.osiEmpAttachments[0].originalFileName;
		        		vm.preview.attachmentId = result.osiEmpAttachments[0].attachmentId;
		        	}else{
		        		vm.preview = {

		        		}
		        	};
              calculateAge();

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
        /*vm.showTerminateField = function(type) {
          if((vm.basicInfo.employeeType.toLowerCase() === 'contractor') ||
              (vm.basicInfo.employeeType.toLowerCase() === 'intern')) {
              $scope.isContractor = true;
            } else {
              $scope.isContractor = false;
            }
        }*/
        vm.clearBasicInfoData = function(){
					var urlPath = $location.path();
					if(urlPath.includes('-sf')) {
						window.location.href = "#/employeesList";
					} else {
						$localStorage.isReturn = true;    
						window.location.href = "#/employees";
					}        	    	
        }
        function calculateAge() {
          // birthday is a date
          console.log('in age calculator');
          if(vm.basicInfo.dateOfBirth != null){
	          var ageDifMs = Date.now() - parseDate(vm.basicInfo.dateOfBirth).getTime();
	          var ageDate = new Date(ageDifMs); // miliseconds from epoch
	          console.log( Math.abs(ageDate.getUTCFullYear() - 1970));
	          vm.basicInfo.ageYears = Math.abs(ageDate.getUTCFullYear() - 1970);
	        }
        }
        function parseDate(input) {
        var parts = input.match(/(\d+)/g);
        // new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
        return new Date(parts[0], parts[1]-1, parts[2]); // months are 0-based
      }

		// flexi fields code...
		vm.showFlexiFields = function() {
			console.log('INSIDE FLEXI FIELDS...');
			if(vm.basicInfo.orgId != undefined) {
				console.log(vm.basicInfo.orgId);
				$rootScope.orgId = vm.basicInfo.orgId;
				$scope.orgId = $rootScope.orgId;
				$scope.flexiFieldList = [];
//				    $scope.flexiName = $localStorage.flexNavigationName;
        if($localStorage.functionId != undefined && $localStorage.functionId != null && $rootScope.orgId != undefined && $rootScope.orgId != null)
            getFlexiFunctionInfo($localStorage.functionId ,$rootScope.orgId);
				/*//getFlexiFields();
				$('#EmpFlexiFieldModal').modal('show');*/
			}
		}

      var getFlexiFields = function() {
      FlexiFieldService.GetFlexiFields($scope.flexiName, $scope.orgId).then(function(data) {
            console.log(data);
            if(data !== null) {
              $scope.flexiFieldList = data;
              angular.forEach($scope.flexiFieldList ,function(value, key) {
                angular.forEach(value.flexiDataList, function(v, k) {
                  if(vm.basicInfo.employeeId != undefined) {
                    angular.forEach(vm.basicInfo, function(val, keys) {
                      if(keys === v.columnName.toLowerCase()) {
                    	  var data;
                       if(v.columnType.toLowerCase() === 'decimal')
                    	   data = parseFloat(vm.basicInfo[keys]);
                       else if(v.columnType.toLowerCase() === 'number')
                    	   data = parseInt(vm.basicInfo[keys]);
                       else
                    	   data = vm.basicInfo[keys];

                         v.value = data;
                      }
                  });
                }
              });
            });
            }
         }).catch(function(error){
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
      var getFlexiFunctionInfo = function(functionId, orgId) {
				var urlPath = $location.path().substring(1, $location.path().length);
        FlexiFieldService.getFunctionInfo(urlPath, orgId).then(function success(data) {
          console.log(data);
          if(data != undefined && data != '') {
            $scope.flexiName = data.catName;
            $scope.inlineOrPopup = data.inlineOrPopup;
            getFlexiFields();

			if($scope.inlineOrPopup.toLowerCase() === 'inline'){
				$("#EmpflexfeildModel").css("display","block");
			}else{
				$('#EmpFlexiFieldModal').modal('show');
			}
          }
        }, function error(error){
          console.log(error.data);
          $rootScope.isTrascError = true;
          FlashService.Error(error.data.errorMessage);
          $timeout(function () {
            $rootScope.isTrascError=false;
          }, 2000);
        });
      };
    $scope.executeScript = function(data) {
                eval(data);
		 }
      vm.saveFlexInfo = function () {
	        console.log($scope.flexiFieldList);
	        if(flexValidFlag) {
	          angular.forEach($scope.flexiFieldList ,function(value, key) {
	            angular.forEach(value.flexiDataList, function(v, k) {
	              if(vm.basicInfo.employeeId != undefined) {
	                angular.forEach(vm.basicInfo, function(val, keys) {
	                  if(keys === v.columnName.toLowerCase()) {
	                    console.log(keys+" : "+v.value);
	                    vm.basicInfo[keys] = v.value;
	                  }
	                });
	              }else {
	                vm.basicInfo[v.columnName.toLowerCase()] = v.value;
	              }
	            });
	          });
	          console.log(vm.basicInfo);
	          $('#EmpFlexiFieldModal').modal('hide');
	      }
      }

		vm.clearFlexFields = function () {
			console.log('clear flex');
			angular.forEach($scope.flexiFieldList ,function(v) {
				angular.forEach(v.flexiDataList, function(o) {
					var strElem = 'angular.element($document[0].querySelector(\'#'  + o.columnName + '\')).val(\'\')';
					console.log(eval(strElem));
				});
			});
		}
    vm.validateFlexFields = function () {
        console.log('validate flex');
        flexValidFlag = true;
        angular.forEach($scope.flexiFieldList ,function(v) {
          if(flexValidFlag) {
            var sortedList = $filter('orderBy')(v.flexiDataList, 'columnSeq', true);
            angular.forEach(sortedList, function(o) {
                var strElem = 'angular.element($document[0].querySelector(\'#'  + o.columnName + '\')).val()';
                console.log(o.isMandatory);
                if(o.isMandatory === 1 && flexValidFlag){
                    var de = eval(strElem);
                    if (!de) {
                        var errMsg = o.columnValue + ' is required!';
                        console.log(errMsg);
                        $rootScope.isTrascError = true;
                        FlashService.Error(errMsg);
                        $timeout(function() {
                            $rootScope.isTrascError = false;
                        }, 4000);
                        flexValidFlag = false;
                    } else {
                      flexValidFlag = true;
                    }
                }
            });
          }
        });
        if(flexValidFlag)
        	vm.saveFlexInfo();
		}
		//getFlexiFields();
		vm.systemTypes = [{name:"Desktop", value:"Desktop"}, {name:"Laptop", value:"Laptop"}];
 }
]);
