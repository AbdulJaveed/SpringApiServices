'use strict';

angular.module('myApp.userProfileController', []).controller('userProfileController', userProfileController);

userProfileController.$inject = ['$sessionStorage', '$q', '$scope', '$rootScope', '$cookieStore', '$timeout', '$window',  
		'userProfileService', 'OsiEmployeesService', 'appConstants', 'FlashService'];
function userProfileController($sessionStorage, $q, $scope, $rootScope, $cookieStore, $timeout, $window,  
		userProfileService, OsiEmployeesService, appConstants, FlashService) {
    
	   $scope.slide = '';
       $scope.loginData = {};                  
       console.log("ROOTSCOPE LOGIN "+$rootScope.isLogin); 
//       $rootScope.basicTree = $sessionStorage.menuTree;
       if($rootScope.isLogin==true) {
          $sessionStorage.Login =true;
       }
       if($sessionStorage.Login) {
          $rootScope.isLogin =true;
       } else {
          $rootScope.isLogin =false;
       }
	
    var vm=this;
    vm.userObj={ 
                id : $cookieStore.get('globals').userDTO.id 
               };
    
    vm.osiUser = {
    		organization : {
    			orgName:'',
    			id:''
    		}
    };
    $scope.images = [];
   // $scope.cards = [{type:"Aadhar"},{type:"PAN"}];
    $scope.osiUser={};
    vm.currentUser = {};
    vm.editableMobNo = false; 
    $scope.profilePic= false;
    vm.userUpdate = true;
    vm.submitted = true;
    vm.infoView = false;
    vm.isFileError=false;
    $scope.userInfoModified = false;
	$scope.userIdcardsModified = false;
    $scope.formData = new FormData();
    $scope.osiUser={};
    initController();
    $scope.tab1Valid = false;
    $scope.tab2Valid = false;
    $scope.tab3Valid = false;
      
    function initController(){
    	$('#pattern').css("display","none");
    	$('#maxSize').css("display","none");
    	
    	vm.currentUserId=$cookieStore.get('globals').userDTO.id;
    	console.log(vm.currentUserId);
    	$scope.osiTitles = [
        	{"title":"MR", "value":"MR"},
        	{"title":"Mrs", "value":"Mrs"},
        	{"title":"Ms", "value":"Ms"}
    	];
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
    	userProfileService.getAllorganizations().then(function (data) {
    		$scope.osiOrganizations = data;
    	}).catch(function(error){
    		
            
            $scope.showFailureAlert = true;
           
   		  if(error.data.httpStatus){ 
   			$scope.failureTextAlert=error.data.errorMessage; 
   		  }else{
   			 $scope.failureTextAlert = appConstants.exceptionMessage;
   		  }
   		
   		  $timeout(function () {
   			$scope.showFailureAlert = false;
   		  }, 2000);
    	});
    	userProfileService.getEmployee(vm.currentUserId).then(function (data) {
    		vm.osiUser = data;
    		console.log(JSON.stringify(vm.osiUser));
    		if(vm.osiUser != undefined && vm.osiUser != '')
    			//console.log(vm.osiUser.osiOrganizations.id);
    		if(vm.osiUser.osiOrganizations.id) {
    			vm.getLocations(vm.osiUser.osiOrganizations.id);
    			vm.getCards(vm.osiUser.osiOrganizations.id);
    		}
    		if(vm.osiUser.osiLocations.locationId) {
    			vm.getBuildings(vm.osiUser.osiLocations.locationId);
    		}
    	
    	}).catch(function(error){
    		 $scope.showFailureAlert = true;
             
      		  if(error.data.httpStatus){ 
      			$scope.failureTextAlert=error.data.errorMessage; 
      		  }else{
      			 $scope.failureTextAlert = appConstants.exceptionMessage;
      		  }
      		
      		  $timeout(function () {
      			$scope.showFailureAlert = false;
      		  }, 2000);
    	});
    	
    	$scope.myImage = 'img/profilePic.jpg';
    		
    };
    vm.getLocations = function(orgId) {
  	  console.log(orgId);
  	  if(orgId !=  undefined){
  		  userProfileService.getLocationsByOrg(orgId).then(function (data) {
  			  $scope.osiLocations = data;
  		  });
  	  } else{
  		  vm.osiUser.osiLocations.locationId = undefined;
  		  vm.osiUser.osiBuildings.buildingId = undefined;
  	  }
    };
    vm.getCards = function(orgId) {
  	  userProfileService.getCardTypesByOrg(orgId).then(function (data) {
  		  $scope.cards = data;
  		  //console.log(JSON.stringify(data));
  		  vm.idCards();
  		});  
    };
    
    vm.getBuildings = function(locId) {
  	  if(locId !=  undefined){
  		  userProfileService.getBuildingsByLocation(locId).then(function (data) {
  	  		$scope.osiBuildings = data;
  		  });
  	  } else {
  		  vm.osiUser.osiBuildings.buildingId = undefined;
  	  }
    }
    
    vm.idCards = function() {
  	  var tempCards = vm.osiUser.osiEmpIdProofses;
  	vm.osiUser.osiEmpIdProofses = [];
  	  //console.log(tempCards);
  	  angular.forEach($scope.cards, function(values, key) {
  		  var gotIt = false;
  		  angular.forEach(tempCards, function(v, k) {
  			  if(!gotIt){
  				  if(values.cardTypeName === v.cardType){
  					  gotIt = true;
  					  var insertObj = {};
  		  			  insertObj.cardType = values.cardTypeName;
  		  			  insertObj.name = v.name;
  		  			  insertObj.dob = v.dob;
  		  			  insertObj.idNumber =v.idNumber;
  		  			  insertObj.isMandatory = values.isMandatory;
  		  			  insertObj.idProofId = v.idProofId;
  		  			  insertObj.fatherName = v.fatherName;
  		  			  if(v.cardType=='PANCARD'){
  		  				 insertObj.maxLength = 10;
  		  				 insertObj.textValidation = 'no-special-char-space';
					  }else if(v.cardType=='AADHAR'){
	  		  				 insertObj.maxLength = 12;
	  		  				 insertObj.textValidation = 'valid-onlynumber';
					  }else{
						  insertObj.maxLength = 50;
	  		  				 insertObj.textValidation = 'no-special-char-space';
					  }
  		  			 
  					  //console.log(values);
  		  			 insertObj.osiEmpIdProofCopieses = v.osiEmpIdProofCopieses;
  					  vm.osiUser.osiEmpIdProofses.push(insertObj);
  				  }
  			  }
  		  })
  		  if(!gotIt) {
  			  var insertObj = {};
  			  insertObj.cardType = values.cardTypeName;
  			  insertObj.name = '';
  			  insertObj.dob = '';
  			  insertObj.idNumber = '';
  			  insertObj.isMandatory = values.isMandatory;
  			  insertObj.idProofId = '';
  			  insertObj.fatherName = '';
  			 if(values.cardTypeName=='PANCARD'){
	  				 insertObj.maxLength = 10;
	  				 insertObj.textValidation = 'no-special-char-space';
			  }else if(values.cardTypeName=='AADHAR'){
		  				 insertObj.maxLength = 12;
		  				 insertObj.textValidation = 'valid-onlynumber';
			  }else{
				  insertObj.maxLength = 50;
		  				 insertObj.textValidation = 'no-special-char-space';
			  }
  			  insertObj.osiEmpIdProofCopieses = [];
  			  vm.osiUser.osiEmpIdProofses.push(insertObj);
  		  }
  		  
  	  });
    }
    
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
    var today = yyyy+'/'+mm+'/'+dd;
    $scope.today = today;
    vm.setDOB = function (dob){
    	angular.forEach(vm.osiUser.osiEmpIdProofses, function(val,key){
    		if(val.isMandatory)
    			vm.osiUser.osiEmpIdProofses[key].dob = dob;
    	});
    }
    // Validations Tab wise
    function tab1LoadData() {
    	$scope.tab1Valid = false;
    	$('#required').css("display","none");
    	$('#pattern').css("display","none");
    	$('#maxSize').css("display","none");
    	var attachmentObj = {};
    	if(vm.osiUser.osiEmpAttachmentses && vm.osiUser.osiEmpAttachmentses.length>0){
    		attachmentObj.attachmentId = vm.osiUser.osiEmpAttachmentses[0].attachmentId;
    	}
    	vm.osiUser.osiEmpAttachmentses = [];
    	attachmentObj.fileContent = $("#photo").val();
    	attachmentObj.originalFileName = $("#originalFileName").val();
    	attachmentObj.fileType = $("#fileType").val();
    	
    	if(attachmentObj.fileContent != "" && attachmentObj.originalFileName != "" && attachmentObj.fileType != "") {
    		$("#required").css("display","none");
    		vm.osiUser.osiEmpAttachmentses.push(attachmentObj);
    	}else {
    		$("#required").css("display","block");
    	}
    	//console.log(vm.osiUserInfo.mobileNo);
    	if(vm.osiUserInfo.$valid && (attachmentObj.fileContent != "" && attachmentObj.originalFileName != "" && attachmentObj.fileType != "")) {
			//$rootScope.inValidUser = vm.osiUserInfo.$invalid;
			angular.forEach($scope.osiOrganizations, function(value, key) {
				if(vm.osiUser.osiOrganizations.id == value.id){
					vm.osiUser.osiOrganizations = value;
				} 
			});
			angular.forEach($scope.osiLocations, function(value, key) {
				if(vm.osiUser.osiLocations.locationId == value.locationId){
					vm.osiUser.osiLocations = value;
				} 
			});
			angular.forEach($scope.osiBuildings, function(value, key) {
					if(vm.osiUser.osiBuildings.buildingId == value.buildingId){
						vm.osiUser.osiBuildings = value;
					} 
			 });
			if(vm.osiUserInfo.mobileNo.$modelValue != ""){
				$scope.tab1Valid = true;
				if(!$scope.tab2Valid){
    				angular.element('[data-target="#tab2"]').tab('show');
					 $(".tabss").removeClass("btn-primary");
					 $(".tabss").addClass("btn-default");
					 $(".tabss").css("color", "black");
					 $("#idCardsTab").addClass("btn-primary");
					 $("#idCardsTab").css("color", "white");
    			}
			} else {
				$scope.tab1Valid = false;
				vm.validateForm("Mobile"," is required");
				//vm.osiUser.mobileNo.$error.required = true;
				vm.osiUserInfo.mobileNo.$error.pattern = true;
			}
    	}else{
    		$scope.tab1Valid = false;
    		if(vm.osiUser.employeeId =='' || vm.osiUser.employeeId == undefined)
    			vm.validateForm("Employee Id"," is required");
    		else if(vm.osiUser.title == '' || vm.osiUser.title == undefined)
    			vm.validateForm("Title"," is required");
    		else if(vm.osiUser.firstName== '' || vm.osiUser.firstName== undefined)
    			vm.validateForm("First Name"," is required");
    		else if(vm.osiUser.lastName== '' || vm.osiUser.lastName== undefined)
    			vm.validateForm("Last Name"," is required");
    		//else if(vm.osiUser.middleName== '' || vm.osiUser.middleName== undefined)
    			//vm.validateForm("Middle Name","field is required");
    		else if(vm.osiUser.companyMailId== '' || vm.osiUser.companyMailId== undefined)
    			vm.validateForm("company Mail Id"," is required");
    		else if(vm.osiUser.personalMailId== '' || vm.osiUser.personalMailId== undefined){
    			vm.validateForm("Personal Mail Id"," is required");
    		}else if(vm.osiUser.dob== '' || vm.osiUser.dob== undefined)
    			vm.validateForm("Date Of Birth"," is required");
    		else if(vm.osiUser.bloodGroup== '' || vm.osiUser.bloodGroup== undefined)
    			vm.validateForm("Blood Group"," is required");
    		else if(vm.osiUser.mobileNumber== '' || vm.osiUser.mobileNumber== undefined)
    			vm.validateForm("Enter Valid ","Mobile Number");
    		//else if(vm.osiUser.phoneExtension== '' || vm.osiUser.phoneExtension== undefined)
    			//vm.validateForm("Phone Extension","field is required");
    		else if(vm.osiUser.osiOrganizations.id== '' || vm.osiUser.osiOrganizations.id== undefined)
    			vm.validateForm("Organization"," is required");
    		else if(attachmentObj.fileContent == "" && attachmentObj.originalFileName == "" && attachmentObj.fileType == "")
    			vm.validateForm("","Please Upload Photo");
    		else {
    			if(!$scope.tab2Valid){
    				angular.element('[data-target="#tab2"]').tab('show');
					 $(".tabss").removeClass("btn-primary");
					 $(".tabss").addClass("btn-default");
					 $(".tabss").css("color", "black");
					 $("#idCardsTab").addClass("btn-primary");
					 $("#idCardsTab").css("color", "white");
    			}
    		}
    		
    	}
    	
    	return $scope.tab1Valid;
    };
    function tab2LoadData() {
    	$scope.tab2Valid = false;
    	var inx = 0;
   		var proofCopies = [];
   		angular.forEach(vm.osiUser.osiEmpIdProofses, function(empProofs) {
   			 var proofs = {};
   			 if(vm.osiUser.osiEmpIdProofses[inx].osiEmpIdProofCopieses && vm.osiUser.osiEmpIdProofses[inx].osiEmpIdProofCopieses.length>0){
   				 proofs.attachmentId = vm.osiUser.osiEmpIdProofses[inx].osiEmpIdProofCopieses[0].attachmentId;
   			}
   			 proofs.fileContent = $("#photo"+(inx+1)).val();
   			 proofs.originalFileName = $("#originalFileName"+(inx+1)).val();
   			 proofs.fileType = $("#fileType"+(inx+1)).val();
   			 vm.osiUser.osiEmpIdProofses[inx].osiEmpIdProofCopieses = [];
   			 if(proofs.fileContent != "" && proofs.originalFileName != "" && proofs.fileType != "") {
   					//$("#required"+(inx+1)).css("display","none");
   					vm.osiUser.osiEmpIdProofses[inx].osiEmpIdProofCopieses.push(proofs);
   					proofCopies.push(true);
   			 }else{
   				 if(vm.osiUser.osiEmpIdProofses[inx].isMandatory != 0) {
   					// $("#required"+(inx+1)).css("display","block");
   					 proofCopies.push(false);
   				 }else{
   					 proofCopies.push(true);
   				 } 
   			 }
   			 inx++;
   		});
   		var c = 0;
   		angular.forEach(proofCopies, function(value, key){
   			if(value)
   				c++;
   		});
		if(c==proofCopies.length) {
			
		}
    	if(vm.osiUserIds.$invalid){
    		var x = 0;
    		angular.forEach(vm.osiUserIds.$error.required, function(val, index){
    			if((val.$name).startsWith('dob')){
    				x++;
    			}
    		});
    		/*if(x == vm.osiUserIds.$error.required.length){
    			vm.osiUserIds.$invalid = false;
    			vm.osiUserIds.$valid = true;
    		}*/
    	}
    	/*if(vm.osiUserIds.$valid && (c==proofCopies.length))
    		$scope.tab2Valid = true;*/
    	/*else{
    	*/	
    	
    	var counter = proofCopies.length;
    	var count=1;
    	var tempFlag = true;
    	var row=1;
    		angular.forEach(vm.osiUser.osiEmpIdProofses, function(empProofs,key) {
    			console.log(empProofs);
    		//	if(empProofs.isMandatory == 1 && tempFlag) {
	    			/*if(empProofs.name =='' || empProofs.name == undefined){
		    			vm.validateForm("Card Name "," is required at "+ empProofs.cardType);
		    			tempFlag = false;
	    			} else if(empProofs.fatherName == '' || empProofs.fatherName == undefined){
		    			vm.validateForm("Father Name"," is required at "+empProofs.cardType);
		    			tempFlag = false;
    				}else*/ 
	    			if(tempFlag && (empProofs.idNumber!="" || empProofs.fatherName!="" || (empProofs.dob!=undefined  && empProofs.dob!="")) && (empProofs.name== '' || empProofs.name== undefined)){
		    			vm.validateForm("Enter Name ", "at row["+row+"]");
		    			tempFlag = false;
					}else if(tempFlag && (empProofs.idNumber!="" || empProofs.name!="" || (empProofs.dob!=undefined  && empProofs.dob!="")) && (empProofs.fatherName== '' || empProofs.fatherName== undefined)){
		    			vm.validateForm("Enter Father Name ", "at row["+row+"]");
		    			tempFlag = false;
					}else if(tempFlag && (empProofs.name!="" || empProofs.fatherName!="" || (empProofs.dob!=undefined  && empProofs.dob!="")) && (empProofs.idNumber== '' || empProofs.idNumber== undefined)){
		    			vm.validateForm("Enter Valid ", empProofs.cardType+" Number");
		    			tempFlag = false;
    				}else if(tempFlag && (empProofs.idNumber!="" || empProofs.name!="" || empProofs.fatherName!="") && (empProofs.dob== '' || empProofs.dob== undefined)){
		    			vm.validateForm("Select Date of Birth ", "at row["+row+"]");
		    			tempFlag = false;
					}/*else if(empProofs.dob== '' || empProofs.dob== undefined){
		    			vm.validateForm("Date of Birth"," is required at "+ empProofs.cardType);
		    			tempFlag = false;
    				}else if(vm.osiUser.osiEmpIdProofses[key].osiEmpIdProofCopieses.length <= 0){
		    			vm.validateForm("Attachment"," is required at "+ empProofs.cardType);
		    			tempFlag = false;
    				}*/else{
    					count++;
    				}
	    			/*else{vm.osiUser.osiEmpIdProofses[key].isMandatory || 
	    				if((c==proofCopies.length))
	    		    		$scope.tab2Valid = true;
	    		    	
	    			}*/		
	    			row++;
    			
    		});
    		if(tempFlag){
    			$scope.tab2Valid = true;
    			if(!$scope.tab1Valid){
    				angular.element('[data-target="#tab1"]').tab('show');
    				 $(".tabss").removeClass("btn-primary");
    				 $(".tabss").addClass("btn-default");
    				 $(".tabss").css("color", "black");
    				 $("#detailsTab").addClass("btn-primary");
    				 $("#detailsTab").css("color", "white");
    			}
    		} else
    			$scope.tab2Valid = false;
    	/*}*/
    		
    	return $scope.tab2Valid;
    	
    };
    vm.next=function(tab){
    	if(tab == 'tab1') {
    		tab1LoadData();
	    	
    		if($scope.tab1Valid) {
				 $timeout(function() {
					 angular.element('[data-target="#tab2"]').tab('show');
					 $(".tabss").removeClass("btn-primary");
					 $(".tabss").addClass("btn-default");
					 $(".tabss").css("color", "black");
					 $("#idCardsTab").addClass("btn-primary");
					 $("#idCardsTab").css("color", "white");
				 });
				 //$scope.tab1Valid = true;
	    	}/* else {
	    		$rootScope.isTrascError = true;
				FlashService.Error("Please fill required fields...");
	            $timeout(function () {
	            	$rootScope.isTrascError=false;
	            }, 5000);
			}*/
    	} else if(tab == 'tab2') {
    		
    		tab2LoadData();
    	
    		if($scope.tab2Valid) {
	    		$timeout(function() {
					 angular.element('[data-target="#tab3"]').tab('show');
					 $(".tabss").removeClass("btn-primary");
					 $(".tabss").addClass("btn-default");
					 $(".tabss").css("color", "black");
					 $("#bankDetailsTab").addClass("btn-primary");
					 $("#bankDetailsTab").css("color", "white");
				 });
	    		//$scope.tab2Valid = true;
    		}/*else {
	    		$rootScope.isTrascError = true;
				FlashService.Error("Please fill required fields...");
	            $timeout(function () {
	            	$rootScope.isTrascError=false;
	            }, 5000);
			}*/
    	}
	}
    vm.previous=function(tab){
    	if(tab == 'tab2') {
	    	 angular.element('[data-target="#tab1"]').tab('show');
			 $(".tabss").removeClass("btn-primary");
			 $(".tabss").addClass("btn-default");
			 $(".tabss").css("color", "black");
			 $("#detailsTab").addClass("btn-primary");
			 $("#detailsTab").css("color", "white");
    	} else if(tab == 'tab3') {
    		angular.element('[data-target="#tab2"]').tab('show');
			 $(".tabss").removeClass("btn-primary");
			 $(".tabss").addClass("btn-default");
			 $(".tabss").css("color", "black");
			 $("#idCardsTab").addClass("btn-primary");
			 $("#idCardsTab").css("color", "white");
    	}
    }
    
 vm.validateForm = function(fieldName, errorMsg){
	 $rootScope.isTrascError = true;
		FlashService.Error(fieldName+" "+errorMsg);
		$timeout(function () {
			$rootScope.isTrascError=false;
		}, 5000);
 }
vm.saveOsiUser = function(){
	//$('#pattern').css("display","none");
	//$('#maxSize').css("display","none");

	
	var tab1 = tab1LoadData();
	var tab2 = tab2LoadData();
	console.log('tab1 -- '+tab1);
	console.log('tab2 -- '+tab2);
	//console.log(vm.osiUserBankDetails.$valid);
	//if($scope.tab1Valid && $scope.tab2Valid /*&& vm.osiUserBankDetails.$valid*/) {
	if(tab1 && tab2){
		vm.osiUser.bankDetails = null;
		userProfileService.saveOsiEmployee(vm.osiUser).then(function (result) {
			$rootScope.isTrascError = true;
			$window.location.href='#/userProfile';
			FlashService.Success(appConstants.exceptionMessage);
			$timeout(function () {
				$rootScope.isTrascError=false;
				$window.location.reload();
			}, 1000);
		}).catch(function(resp){
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
	} /*else {
		$rootScope.isTrascError = true;
		FlashService.Error("Please fill required fields...");
		$timeout(function () {
			$rootScope.isTrascError=false;
		}, 5000);
	}*/
};
vm.nextPage = function(){
	  angular.element('[data-target="#tab2"]').tab('show');
	  vm.deselectTabs();
    $rootScope.tab2="selectedInfoTab";
}
vm.deselectTabs=function(tabId, tabIndx){
	 angular.element('[data-target="#'+tabIndx+'"]').tab('show');
	 $(".tabss").removeClass("btn-primary");
	 $(".tabss").addClass("btn-default");
	 $(".tabss").css("color", "black");
	 $("#"+tabId).addClass("btn-primary");
	 $("#"+tabId).css("color", "white");
};

vm.checkDate = function() {
	  if(vm.osiUser.osiEmpIdProofses[0].dob == undefined){
 		 $rootScope.isTrascError = true;
          FlashService.Error("Please enter a valid Date");
          $timeout(function() {
				$rootScope.isTrascError = false;
			}, 3000);
          vm.osiUser.osiEmpIdProofses[0].dob = "";
 	}
}

    // -- ORG Search popup
	$scope.searchorgNameString = '';
	$scope.populatedorgName = {}; //Object to be populated on selecting any from search results

	$scope.populateorgName = function(orgNameObj){
		$scope.osiUser.organization = {};
		    if (orgNameObj instanceof Array) {
		    	/*$scope.osiUser.organization.orgName = orgNameObj[0].orgName;
		    	$scope.osiUser.organization.id = orgNameObj[0].id;*/
		    	vm.osiUser.organization.orgName = orgNameObj[0].orgName;
		    	vm.osiUser.organization.id = orgNameObj[0].id;
		    }else{
		    	/*$scope.osiUser.organization.orgName = orgNameObj.orgName;
		    	$scope.osiUser.organization.id = orgNameObj.id;*/
		    	vm.osiUser.organization.orgName = orgNameObj.orgName;
		    	vm.osiUser.organization.id = orgNameObj.id;
		    }
	}

	//on ng-blur of search input box and clicking search 'icon'
	$scope.showorgNameSearchModal = function(enteredText) {
		 $scope.searchorgNameString = enteredText;
		 if($scope.populatedorgName.id) {
			 $scope.searchorgNameString = '';
			 openorgNamePopUp();
		 } else if($scope.searchorgNameString ? $scope.searchorgNameString.length : false) {
			 getorgNamesBySearch($scope.searchorgNameString).then(function (data) {
				 if(data ? data.length == 1 : false) {
					 $scope.populateorgName(data);
					 /*getCompleteorgNameObject(data[0].id).then(function (orgNameObj) {
						 $scope.populateorgName(orgNameObj); //CALL to populate selected Object to specific model.
						 return;
					 });*/
				 } else {
					 $scope.resultedorgNames = data;
					 openorgNamePopUp();
				 }
			 });
		 }
	 };
	 
	 //open Search PopUp
	 function openorgNamePopUp() {
		 $('#orgNameModal').modal({
			 show: true,
			 backdrop: 'static',
			 keyboard: false
		 }); 
		 $scope.selectedorgName = null;
		 $scope.addButton = false;
	 }

	//closing search popUp on Clicking 'CLOSE' and 'X' buttons.
	$scope.closeorgNameModal = function() {
		$('#orgNameModal').modal('hide'); 
	};

	//call on clicking 'SEARCH' button
	$scope.searchorgName = function(searchorgNameString) {
		getorgNamesBySearch(searchorgNameString).then(function (data) {
			$scope.resultedorgNames = data;
		});
		$scope.selectedorgName = null;
		$scope.addButton = false;
	};

	//call on clicking 'CLEAR' button
	$scope.clearorgNameSearch = function() {
		$scope.resultedorgNames = [];
		$scope.searchorgNameString = '';
		$scope.addButton = false;
	};

	//function call on selecting/clicking(single) any orgName from search results
	$scope.selectorgNameIndex = function (rowIndex) {
		$scope.addButton = true; 
		$scope.selectedorgName = rowIndex;
	};

	//function call of selecting an orgName and Clicking 'OK' button
	$scope.selectorgName = function () {
		var orgName = $scope.resultedorgNames[$scope.selectedorgName];
		$scope.setorgName(orgName);
	};

	//function call on double-click of one of the search results
	$scope.setorgName = function(orgName) {
		 if(!_.isEmpty(orgName)) {
				$scope.closeorgNameModal();
				$scope.populateorgName(orgName); //CALL to populate selected Object to specific model.
			}
		/*getCompleteorgNameObject(orgName.id).then(function (orgNameObj) {
			if(!_.isEmpty(orgNameObj)) {
				$scope.closeorgNameModal();
				$scope.populateorgName(orgNameObj); //CALL to populate selected Object to specific model.
			}
		});*/
	};

	// SERVICE CALL TO GET SEARCH RESULTS
	function getorgNamesBySearch(searchorgNameString) {
		var deferred = $q.defer();
		// SERVICE CALL TO GET SEARCH RESULTS
		var searchData = JSON.stringify({'orgName':searchorgNameString});
		OsiEmployeesService.getorgNamesByStr(searchData).then(function (data) {
			if(data) {
				data = data.length > 500 ? data.slice(0, 501) : data; 
			}
			deferred.resolve(data);
		}).catch(function(error) {
			if(error!= undefined){
					if(error.data.errorMessage != undefined)
					{
						showError(error.data.errorMessage);
					}
			}else{
				showError(exceptionMessage);
			}
		});
		return deferred.promise;
	}

	// SERVICE CALL TO GET OBJECT of SELECTED orgName
	function getCompleteorgNameObject(orgNameId) {
		var deferred = $q.defer();
		// SERVICE CALL TO GET OBJECT of SELECTED orgName
		OsiEmployeesService.getorgNameById(orgNameId).then(function(orgNameObj) {
			if(!_.isEmpty(orgNameObj)) {
				deferred.resolve(orgNameObj);
			}
		}).catch(function(error) {
			if(error!= undefined){
				if(error.data.errorMessage != undefined)
				{
					showLineError(error.data.errorMessage);
				}
			}else{
				showLineError(exceptionMessage);
			}
		});
		return deferred.promise;
	}
	
}
