'use strict';
angular.module('myApp.PersonalInfoController', []).controller('PersonalInfoController',
  PersonalInfoController);



PersonalInfoController.$inject = ['configData', '$scope', '$rootScope', '$window', '$location','$localStorage', 'personalInfoService', 'FlashService', 'LookupService', '$timeout', 'appConstants', 'Upload'];

function PersonalInfoController(configData, $scope, $rootScope, $window, $location, $localStorage, personalInfoService, FlashService, LookupService, $timeout, appConstants, Upload) {
	var vm = this;
	$scope.usOrgCode = configData.usOrgCode;
	$scope.usOrg = $localStorage.usOrg;
  vm.reportsList = "";
  vm.saveAddress = saveAddress;
  vm.copyEmailAddress = copyEmailAddress;
  vm.clearForm = clearForm;
  vm.getEmailAddressCountry = getEmailAddressCountry;
  vm.getPerminentAddressCountry = getPerminentAddressCountry;
  vm.getStatesByCountryIdForEmailAddress = getStatesByCountryIdForEmailAddress;
  vm.getStatesByCountryIdForPerminentAddress = getStatesByCountryIdForPerminentAddress;
  vm.copyAddressLine1 = copyAddressLine1;
  vm.copyAddressLine2 = copyAddressLine2;

  vm.copyCity = copyCity;
  vm.copyZipCode = copyZipCode;
  $localStorage.isReturn = false;
  vm.checkMandatoryFileds = checkMandatoryFileds;
  vm.perminent = { };
  vm.emailing = { };
  $scope.nonEditable = $localStorage.isSaveEdit;
  vm.perminent.osiCountries = {};
  vm.emailing.osiCountries = {};
  vm.emailing.osiStates = {};
  $scope.verifyAddressData = false;
  vm.perminent.osiStates = {};
  vm.contacts = {};
  vm.getPersonalInfo = getPersonalInfo;
vm.checkForValidData = checkForValidData;
  vm.isChecked = isChecked;
  vm.savePersonalInfo = savePersonalInfo;

  vm.calculateAge = calculateAge;
  vm.save = save;

  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth()+1; //January is 0!

  var yyyy = today.getFullYear();
  if(dd<10){
      dd='0'+dd;
  }
  if(mm<10){
      mm='0'+mm;
  }
  var today = yyyy+'-'+mm+'-'+dd;
  vm.today = today;

  function calculateAge() {
    // birthday is a date
    console.log('in age calculator');
    var ageDifMs = Date.now() - parseDate(vm.personalInfo.dateOfBirth).getTime();
    var ageDate = new Date(ageDifMs); // miliseconds from epoch
    console.log( Math.abs(ageDate.getUTCFullYear() - 1970));
    vm.personalInfo.ageYears = Math.abs(ageDate.getUTCFullYear() - 1970);
  }

  function parseDate(input) {
  var parts = input.match(/(\d+)/g);
  // new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
  return new Date(parts[0], parts[1]-1, parts[2]); // months are 0-based
}


  function isChecked() {
    console.log('in is checked');
    vm.personalInfo.onMilitaryService = vm.onMilitaryService ? 1 : 0;
  }

  function checkMandatoryFileds() {
    if (vm.emailing.addressLine1 == undefined || vm.emailing.addressLine1 == '' ||
      vm.emailing.city == undefined || vm.emailing.city == '' ||
      vm.emailing.stateId == undefined || vm.emailing.stateId == '' ||
      vm.emailing.countryId == undefined || vm.emailing.countryId == '' ||
      vm.emailing.zipcode == undefined || vm.emailing.zipcode == '' ||
      vm.perminent.addressLine1 == undefined || vm.perminent.addressLine1 == '' ||
      vm.perminent.city == undefined || vm.perminent.city == '' ||
      vm.perminent.stateId == undefined || vm.perminent.stateId == '' ||
      vm.perminent.countryId == undefined || vm.perminent.countryId == '' ||
      vm.perminent.zipcode == undefined || vm.perminent.zipcode == '') {
      return true;
    } else {
      return false;
    }
  }



  function copyZipCode() {
    if (vm.sameAsAbove) {
      vm.perminent.zipcode = vm.emailing.zipcode;
    }
  }

  function copyCity() {
    if (vm.sameAsAbove) {
      vm.perminent.city = vm.emailing.city;
    }
  }

  function copyAddressLine2() {
    if (vm.sameAsAbove) {
      vm.perminent.addressLine2 = vm.emailing.addressLine2;
    }
  }


  function copyAddressLine1() {
    if (vm.sameAsAbove) {
      vm.perminent.addressLine1 = vm.emailing.addressLine1;
    }
  }


  function getStatesByCountryIdForPerminentAddress() {
    personalInfoService.getAllStatesListByCountry(vm.perminent.countryId).then(function(data) {
      if(data.length > 0){
    	  vm.stateList = data;
    	  vm.perminent.stateId = data[0].stateId;
      }/*else{
    	  vm.stateList =[];
    	  vm.perminent.stateId="";
      }*/
    }).catch(function(error) {
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

  function getStatesByCountryIdForEmailAddress() {
		if(vm.emailing.countryId){
    personalInfoService.getAllStatesListByCountry(vm.emailing.countryId).then(function(data) {
    	 if(data.length > 0){
    		 vm.emalingState = data;
		     // vm.emailing.stateId = data[0].stateId;
    	 }/*else{
    		 vm.emalingState=[];
    		 vm.emailing.stateId="";
    	 }*/
      //copyEmailAddress();

    }).catch(function(error) {
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
		vm.emalingState = "";
	}
  }


  function getEmailAddressCountry() {
    personalInfoService.getAllCountryList().then(function(data) {
      //set to emailing country also.
      vm.emalingAddCountry = data;
      //vm.emailing.countryId = data[0].countryId;
      //vm.getStatesByCountryIdForEmailAddress(data[0].countryId);
    }).catch(function(error) {
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

  function getPerminentAddressCountry() {
    personalInfoService.getAllCountryList().then(function(data) {
      vm.countryList = data;
      //set to emailing country also.
      vm.emalingAddCountry = data;
      //vm.perminent.countryId = data[0].countryId;
      //vm.getStatesByCountryIdForPerminentAddress(data[0].countryId);
    }).catch(function(error) {
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

  function getStatesByCountryIdForPerminentAddressAndSetState() {
    personalInfoService.getAllStatesListByCountry(vm.perminent.countryId).then(function(data) {
      vm.stateList = data;
     // vm.perminent.stateId = vm.emailing.stateId;
    }).catch(function(error) {
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



  //now initialize popup with countries.
  vm.openAddressPopUp = function() {

    getEmailAddressCountry();
    getPerminentAddressCountry();

  }

  function clearForm() {
    console.log('in trest');
    console.log(vm.personalInfo);
    /*vm.personalInfo = [];*/
    /*vm.address = [];*/
  /*  $scope.init();*/
    $localStorage.isReturn = true;
		if($rootScope.profileAcessHR.profile)
    $window.location.href='#/employeeInfo';
 else if($rootScope.profileAcessHR.profiles)
 $window.location.href='#/employeeInfos';
  }

  vm.clearFormAddress = function(){
	  $("#addressModal").modal("hide");
  }

  /**
   * Function for copying perminent address to present address
   */
  function copyEmailAddress() {
    console.log(vm.sameAsAbove);
    if (vm.sameAsAbove) {
			if(!vm.emailing.addressLine1 || !vm.emailing.city || !vm.emailing.stateId|| !vm.emailing.countryId|| !vm.emailing.zipcode){
				vm.displayErrorMsgforAddress('Please fill the complete Mailing Address','error',true);
				$scope.verifyAddressData = false;
				vm.sameAsAbove = false;
			}
      //copy email address to perminent address.
      vm.perminent.addressLine1 = vm.emailing.addressLine1;
      vm.perminent.addressLine2 = vm.emailing.addressLine2;
      vm.perminent.city = vm.emailing.city;
      vm.perminent.stateId = vm.emailing.stateId;
      vm.perminent.countryId = vm.emailing.countryId;
      vm.perminent.zipcode = vm.emailing.zipcode;
			//getStatesByCountryIdForEmailAddress();
			if(vm.emailing.countryId)
      	getStatesByCountryIdForPerminentAddressAndSetState();

    } else {
      //put null in each field if false
      vm.perminent.addressLine1 = '';
      vm.perminent.addressLine2 = '';
      vm.perminent.city = '';
      vm.perminent.stateId = '';
      vm.perminent.countryId = '';
      vm.perminent.zipcode = '';
      getPerminentAddressCountry();
    }
  }

  $rootScope.go = function(path) {
    $("#success-modal").modal("hide");
    $location.url(path);
  }

  $scope.init = function() {
		$scope.osiBloodGroups = [
			{"title":"A+", "value":"A+"},
			{"title":"AB+", "value":"AB+"},
			{"title":"B+", "value":"B+"},
			{"title":"O+", "value":"O+"},
			{"title":"A-", "value":"A-"},
			{"title":"AB-", "value":"AB-"},
			{"title":"B-", "value":"B-"},
			{"title":"O-", "value":"O-"}
		];

	  vm.personalInfo ={};

	  var  urlPath = $location.path();
	  if(urlPath.includes('personalinfo-sf')) {
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

	  vm.openAddressPopUp();
	  // modified for get employee info bassed on search date
	  //vm.getPersonalInfo($localStorage.employeeId);
	  if($localStorage.employeeId && $localStorage.searchByDate) {
		  var inputObject = { "employeeId" : $localStorage.employeeId , "searchDate" : $localStorage.searchByDate};
		  vm.getPersonalInfo(inputObject);
	  }

	  personalInfoService.getAllCountryList().then(function(data){

		  vm.countryCodeList = data;
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

  $scope.backToHistory = function() {
    $("#success-modal").modal("hide");
    $timeout(function() {
      $location.url("/reportshistory");
    }, 400);
  };

  function save(decision) {
    console.log('decision is: '+decision);
    savePersonalInfo(decision);
  }
   function checkForValidData(){
  	vm.validateForm();
  	/*vm.validateAddress(false);*/
  	 if($scope.verifyData){
  		 $('#confirmationModal').modal('show');
  }
  }
  function savePersonalInfo(action) {
		if (!_.isEmpty(file)){
			vm.personalInfo.osiEmpAttachments=[];
			vm.personalInfo.osiEmpAttachments.push(file);
		}
    var globalPersonalinfoObj = {
        "perminent" : "",
        "emailing" : "",
        "osiEmployees" : "",
        "contacts" : [],

    };

    //now save Contacts to address table.
			var contactsList = [];
			if (vm.personalInfo.mobileNumber != undefined) {
			  console.log('in mobile number saving');
			  var contacts = {};
			  contacts.contactNumber = vm.personalInfo.mobileNumber;
			  contacts.countryCode = vm.personalInfo.countryCodeforMobile;
			  contacts.contactType = "personal";
			  contacts.contactId = vm.personalInfo.mobileContactId;
			  //personalInfoService.saveContacts(contacts);
			  contactsList.push(contacts);
			}

			if (vm.personalInfo.homeNumber != undefined) {
			  var contacts1 = {};
				contacts1.contactNumber = vm.personalInfo.homeNumber;
				if(vm.personalInfo.countryCodeforHome!=null)
					contacts1.countryCode = vm.personalInfo.countryCodeforHome;
				else
				contacts1.countryCode = "";
			  contacts1.contactType = "home";
			  contacts1.contactId = vm.personalInfo.homeContactId;
			  contactsList.push(contacts1);
			  //  personalInfoService.saveContacts(contacts1);
			}
			if (vm.personalInfo.alternateNumber != undefined) {
			  var contacts2 = {};
				contacts2.contactNumber = vm.personalInfo.alternateNumber;
				if(vm.personalInfo.countryCodeforHome!=null)
				contacts2.countryCode = vm.personalInfo.countryCodeforAlternateNumber;
			else
				contacts2.countryCode = "";
			  contacts2.contactType = "alternate";
			  contacts2.contactId = vm.personalInfo.alterContactId;
			  //  personalInfoService.saveContacts(contacts2);
			  contactsList.push(contacts2);
			}

			if(!$scope.usOrg){
				vm.personalInfo.personalEmail = "";
				vm.personalInfo.bloodType = "";
				vm.personalInfo.previousLastName = "";
				vm.personalInfo.totalExp = 0;
				vm.personalInfo.osiEmpAttachments = null;
			}
			globalPersonalinfoObj.perminent = vm.perminent;
			globalPersonalinfoObj.emailing = vm.emailing;
			globalPersonalinfoObj.contacts = contactsList;
			globalPersonalinfoObj.osiEmployees = vm.personalInfo;
			if($localStorage.employeeId != undefined)
			  globalPersonalinfoObj.osiEmployees.employeeId = $localStorage.employeeId;
			console.log(globalPersonalinfoObj);

			personalInfoService.savePersonalInfo(globalPersonalinfoObj, action).then(function(data) {
			  console.log(data);
			  console.log('after personal personal info saved');
			  /*vm.personalInfo = {};
			  vm.emailing = {};
			  vm.perminent = {};*/
			  var msg =appConstants.successMessage;
				  $rootScope.isTrascErrorForPersonal = true;
					$rootScope.isTrascError = true;
					FlashService.Success(msg);
				  $timeout(function() {
					$rootScope.isTrascErrorForPersonal = false;
					 $window.location.reload();
				  }, 2000);

			  console.log(data);
			}).catch(function(error) {
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
vm.validateForm = function(){
	$scope.verifyData = true;
	// if(vm.personalInfo.dateOfBirth === undefined || vm.personalInfo.dateOfBirth == null ||  vm.personalInfo.dateOfBirth === ''){
	// 	vm.displayErrorMsg('Date of birth Field is required','error');
	// 	$scope.verifyData = false;
	// }else
  if(($scope.usOrg) && (vm.personalInfo.personalEmail === undefined ||vm.personalInfo.personalEmail == null || vm.personalInfo.personalEmail === '')){
		vm.displayErrorMsg('Please Enter Valid Personal Email','error');
		$scope.verifyData = false;
	}/*else if(vm.personalInfo.countryCodeforHome === undefined || vm.personalInfo.countryCodeforHome == null || vm.personalInfo.countryCodeforHome ==''){
		vm.displayErrorMsg('Home Number Country Code Field is required','error');
		$scope.verifyData = false;
	}else if(vm.personalInfo.homeNumber === undefined || vm.personalInfo.homeNumber == null || vm.personalInfo.homeNumber == ''){
		vm.displayErrorMsg('Home Number Field is required','error');
		$scope.verifyData = false;*/
	else if(vm.personalInfo.countryCodeforMobile === undefined || vm.personalInfo.countryCodeforMobile == null || vm.personalInfo.countryCodeforMobile ==''){
		vm.displayErrorMsg('Please Select Country code for Mobile Number','error');
		$scope.verifyData = false;
	}else if(vm.personalInfo.mobileNumber === undefined || vm.personalInfo.mobileNumber == null || vm.personalInfo.mobileNumber ===''){
		vm.displayErrorMsg('Please Enter Valid Mobile Number','error');
		$scope.verifyData = false;
	}
  // else if(vm.personalInfo.maritalStatus === undefined || vm.personalInfo.maritalStatus == null || vm.personalInfo.maritalStatus ===''){
	// 	vm.displayErrorMsg('Marital Status field is required','error');
	// 	$scope.verifyData = false;
	// }
	else if(($scope.usOrg) && (vm.personalInfo.totalExp === undefined || vm.personalInfo.totalExp == null || vm.personalInfo.totalExp === '')) {
		vm.displayErrorMsg('Please Enter Valid Total Experience','error');
		$scope.verifyData = false;
	}

};
vm.displayErrorMsg = function(message,type){
	 $rootScope.isTrascErrorForPersonal = true;
	 $rootScope.flash = {
               message: message,
               type: type
           };
		$timeout(function () {
			$rootScope.isTrascErrorForPersonal=false;
		}, 5000);


}

 vm.validateAddress = function(flag){

	  $scope.verifyAddressData = true;
	  if(vm.emailing.addressLine1 === undefined || vm.emailing.addressLine1 == null || vm.emailing.addressLine1 ===''){
			vm.displayErrorMsgforAddress('Please Enter Address Line1','error',flag);
			$scope.verifyAddressData = false;
		}
		else if(vm.emailing.city === undefined || vm.emailing.city == null || vm.emailing.city ===''){
			vm.displayErrorMsgforAddress('Please Enter City','error',flag);
			$scope.verifyAddressData = false;
		}else if(vm.emailing.countryId === undefined || vm.emailing.countryId == null || vm.emailing.countryId ===''){
			vm.displayErrorMsgforAddress('Please Select Country','error',flag);
			$scope.verifyAddressData = false;
		}
		else if(vm.emailing.stateId === undefined || vm.emailing.stateId == null || vm.emailing.stateId ===''){
			vm.displayErrorMsgforAddress('Please  Select State','error',flag);
			$scope.verifyAddressData = false;
		}
		
		else if(vm.emailing.zipcode === undefined || vm.emailing.zipcode == null || vm.emailing.zipcode ===''){
			vm.displayErrorMsgforAddress('Please Enter ZIP code','error',flag);
			$scope.verifyAddressData = false;
		}

	  //perminant address
		else if(vm.perminent.addressLine1 === undefined || vm.perminent.addressLine1 == null || vm.perminent.addressLine1 ===''){
			vm.displayErrorMsgforAddress('Please Enter Address Line1','error',flag);
			$scope.verifyAddressData = false;
		}
		else if(vm.perminent.city === undefined || vm.perminent.city == null || vm.perminent.city ===''){
			vm.displayErrorMsgforAddress('Please Enter City','error',flag);
			$scope.verifyAddressData = false;
		}else if(vm.perminent.countryId === undefined || vm.perminent.countryId == null || vm.perminent.countryId ===''){
			vm.displayErrorMsgforAddress('Please  Select Country','error',flag);
			$scope.verifyAddressData = false;
		}
		else if(vm.perminent.stateId === undefined || vm.perminent.stateId == null || vm.perminent.stateId ===''){
			vm.displayErrorMsgforAddress('Please  Select State','error',flag);
			$scope.verifyAddressData = false;
		}
		
		else if(vm.perminent.zipcode === undefined || vm.perminent.zipcode == null || vm.perminent.zipcode ===''){
			vm.displayErrorMsgforAddress('Please Enter ZIP code','error',flag);
			$scope.verifyAddressData = false;
		}


  }
vm.displayErrorMsgforAddress = function(message,type,flag){
	if(flag){
		 $rootScope.isTrascError = true;
		 $rootScope.flash = {
	              message: message,
	              type: type
	          };
			$timeout(function () {
				$rootScope.isTrascError=false;
			}, 5000);

	}
}
  //function that check login details
  function saveAddress() {
/*	   vm.validateAddress(true);
   if($scope.verifyAddressData){*/
	    vm.perminent.objectType = 'Osi_Employees';
	    vm.emailing.objectType = 'Osi_Employees';
	    $('#addressModal').modal('hide');
	/*  }
    console.log(vm.perminent);
    console.log(vm.emailing);*/
  }

  function saveContacts() {
    console.log('in saving contacts method');
  }
  //  modified for as part of find employee info based on search date
  //function getPersonalInfo(employeeId) {
  function getPersonalInfo(inputObject) {
    personalInfoService.getPersonalInfoByIdAndDate(inputObject).then(function(data) {
      console.log(data);
      vm.personalInfo.dateOfBirth = data.employee.dateOfBirth;
      // if(vm.personalInfo.dateOfBirth != undefined)
        // calculateAge();
      // if( data.employee.maritalStatus != null &&  data.employee.maritalStatus !== undefined)
    	//   vm.personalInfo.maritalStatus = data.employee.maritalStatus.toUpperCase();
      vm.personalInfo.personalEmail = data.employee.personalEmail;
      vm.personalInfo.previousLastName = data.employee.previousLastName;
      vm.personalInfo.knownAs = data.employee.knownAs;
      vm.personalInfo.employeeId = data.employee.employeeId;
			vm.personalInfo.version = data.employee.version;
			vm.personalInfo.totalExp = data.employee.totalExp;
			if(data.employee.bloodType!=null)
				vm.personalInfo.bloodType = data.employee.bloodType;
      angular.forEach(data.contacts, function(values, key) {
        console.log(values);
        if(values.contactType == 'personal' || values.contactType == 'PERSONAL') {
          vm.personalInfo.mobileContactId = values.contactId;
          vm.personalInfo.mobileNumber = values.contactNumber;
          vm.personalInfo.countryCodeforMobile = values.countryCode;
        } else if(values.contactType == 'alternate' || values.contactType == 'ALTERNATIVE') {
          vm.personalInfo.alterContactId = values.contactId;
          vm.personalInfo.alternateNumber = values.contactNumber;
          vm.personalInfo.countryCodeforAlternateNumber = values.countryCode;
        } else if(values.contactType == 'home' || values.contactType == 'HOME') {
          vm.personalInfo.homeContactId = values.contactId;
          vm.personalInfo.homeNumber = values.contactNumber;
          vm.personalInfo.countryCodeforHome = values.countryCode;
        }
      });
      if(data.permanantAddress !== undefined && data.permanantAddress !== null){
		      vm.perminent.addressId = data.permanantAddress.addressId;
		      vm.perminent.addressLine1 = data.permanantAddress.addressLine1;
		      vm.perminent.addressLine2 = data.permanantAddress.addressLine2;
		      vm.perminent.city = data.permanantAddress.city;
					vm.perminent.countryId = data.permanantAddress.countryId;
					getStatesByCountryIdForPerminentAddress();
					vm.perminent.zipcode = data.permanantAddress.zipcode;
					$timeout(function () {
						vm.perminent.stateId = data.permanantAddress.stateId;
					}, 500);
		      
      }
      if(data.permanantAddress !== undefined && data.permanantAddress !== null){
		      vm.emailing.addressId = data.mailingAddress.addressId;
		      vm.emailing.addressLine1 = data.mailingAddress.addressLine1;
		      vm.emailing.addressLine2 = data.mailingAddress.addressLine2;
		      vm.emailing.city = data.mailingAddress.city;
					vm.emailing.countryId = data.mailingAddress.countryId;
					getStatesByCountryIdForEmailAddress();
					vm.emailing.zipcode = data.mailingAddress.zipcode;
					$timeout(function () {
					  vm.emailing.stateId = data.mailingAddress.stateId;
					}, 500);
					if(vm.perminent.addressLine1===vm.emailing.addressLine1 && vm.perminent.addressLine2===vm.emailing.addressLine2
					&& vm.perminent.city===vm.emailing.city && vm.perminent.countryId===vm.emailing.countryId && vm.perminent.zipcode===vm.emailing.zipcode
				&& vm.perminent.stateId ===vm.emailing.stateId){
					vm.sameAsAbove = true;
				}
		    
			}
			vm.personalInfo.resumeId = data.employee.resumeId;
			vm.personalInfo.resumeExists = data.employee.resumeExists;
			vm.personalInfo.resumeLastUpdated = data.employee.resumeLastUpdated;
			if(data.employee.resumeExists === 1) {
				angular.forEach(data.employee.osiEmpAttachments, function(attachment, key){
					if(attachment.attachmentId === data.employee.resumeId) {
						vm.preview.name = attachment.originalFileName;
						vm.personalInfo.osiEmpAttachments=[];
						var resume = {};
						resume.fileContent = attachment.fileContent;
						resume.attachmentId = attachment.attachmentId;
						resume.attachmentType = attachment.attachmentType;
						resume.duplicateFileName = attachment.duplicateFileName;
						resume.fileType = attachment.fileType;
						resume.objectId = attachment.objectId;
						resume.objectType = attachment.objectType;
						resume.originalFileName = attachment.originalFileName;
						resume.createdBy = attachment.createdBy;
						resume.creationDate = attachment.creationDate;
						resume.lastUpdateDate = attachment.lastUpdateDate;
						resume.lastUpdatedBy = attachment.lastUpdatedBy;
						vm.personalInfo.osiEmpAttachments.push(resume);
					}
				});
			}



    }).catch(function(error) {
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

	$scope.init();
	
	//Resume attachment code

	vm.preview = {};
	var file = {};
	this.load = loadFile;
	function loadFile(fileUpload) {
			$rootScope.isTrascErrorForPersonal = false;

			$rootScope.isTrascErrorForPersonal = false;
			if (fileUpload) {
				if (fileUpload && (fileUpload.type == 'application/pdf' || fileUpload.type == 'application/msword' || fileUpload.type == 'application/vnd.openxmlformats-officedocument.wordprocessingml.document')) {
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
						$rootScope.isTrascErrorForPersonal = true;
						$rootScope.flash = {
							message: 'Image file size should be < 5MB',
							type: 'error'
						};
						$timeout(function () {
							$rootScope.isTrascErrorForPersonal = false;
						}, 1500);
					}
				} else {
					vm.preview.content = undefined;
					vm.preview.name = '';
					file = {};
					$rootScope.isTrascErrorForPersonal = true;
					$rootScope.flash = {
						message: 'Invalid File Format',
						type: 'error'
					};
					$timeout(function () {
						$rootScope.isTrascErrorForPersonal = false;
					}, 1500);
				}
			}
		};

		$scope.downloadResume = function() {
			var dlnk = document.getElementById('resume');
			dlnk.setAttribute("download", vm.personalInfo.osiEmpAttachments[0].originalFileName);
			dlnk.href = vm.personalInfo.osiEmpAttachments[0].fileContent;
			dlnk.click();
		};
		$scope.cancelProfile = function(){
			if($localStorage.currentEmployeeId!=$localStorage.loggedInEmployeeId)
				$window.location.href='#/employeeInfo-sf';
			else
			$window.location.href='#/profile';
		}
}
