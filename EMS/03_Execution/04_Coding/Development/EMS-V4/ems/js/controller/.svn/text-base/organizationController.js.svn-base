'use strict';
angular.module('myApp.organizationController', []).controller('organizationController',
        organizationController);
organizationController.$inject = ['$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'organizationService', 'FlashService', '$timeout','$filter','LookupService', 'appConstants','personalInfoService',];
function organizationController($scope, $rootScope, $window, $location, $http,
        $localStorage, organizationService, FlashService, $timeout,$filter,LookupService, appConstants,personalInfoService) {
    var vm = this;
  
  
    vm.today=new Date().toDateString();
    $scope.rowSize = 5;
    $scope.isSelectedRow = null;
    $scope.isLocSelectedRow = null;
    $scope.disable_Create = false;
    $scope.disable_Edit = true;
    $scope.disable_Delete = true;
    $scope.disable_View = true;
    $scope.disable_Loc_Create = false;
    $scope.disable_Loc_Edit = true;
    $scope.disable_Loc_View = true;
    $scope.orgView = true;
    $scope.locView = true; 
    $scope.showSuccessAlert=false;
    $scope.showErrorAlert=false;
    $scope.primaryLocName=null;
    $scope.primaryLocData=null;
    $scope.showPrimaryPresent=false;
    $scope.selectedRowDetails = [];
    $scope.selectedLocRowDetails = [];
    $scope.locBtnStatus = true;
    $scope.toolTipMsg = false;
    $scope.toolLocTipMsg = false;
    $scope.showWarning=false;
    $scope.empNotFound=false;
    $scope.duplicateOrgName=false;
    $scope.rowOrgName=null;
    $scope.hideShortCode = false;
    $scope.org = {
        basic:{}
    };
    $scope.weekDays = [{name:"MONDAY", value:"MONDAY"},{name:"TUESDAY",value:"TUESDAY"},{name:"WEDNESDAY",value:"WEDNESDAY"},
    {name:"THURSDAY ",value:"THURSDAY "},{name:"FRIDAY",value:"FRIDAY"},{name:"SATURDAY",value:"SATURDAY"},{name:"SUNDAY",value:"SUNDAY"}];
    $scope.org.basic.contactPersonName = null;
      $rootScope.go = function (path) {
        $location.url(path);
    }
    vm.closeOrganizationModal=function(){
    	$scope.clearSelectedRow();
         $('#organizationModal').modal('hide');
    }
    vm.closeLocationModal=function(){
    	$scope.clearLocSelectedRow();
         $('#LocationModal').modal('hide');
    }
     $scope.sort = function(keyname) {
        if (vm.orgLists != null) {
            // set the sortKey to the param passed
            $scope.sortKey = keyname;
            // if true make it false and vice versa
            $scope.reverse = !$scope.reverse;
        }
    }
    $timeout(function() {
        // getting the available operations assigned for the logged in user
        $scope.availOperations = $localStorage.availOperations;
     //   $scope.availOperations = [{name:"Create"},{name:"View"},{name:"Edit"}];
      
    }, 400);
    function changeLocationOperations(){
        $timeout(function () {
                  if($scope.orgModelHeading==='View')
        $scope.lavailOperations = [{name:"View"}];
        else{
            $scope.lavailOperations = [{name:"Create"},{name:"View"},{name:"Edit"}];
        }
    }, 500);
    }
    $scope.selectRow = function(item) {  
        // for checking single row selection
        $scope.toolTipMsg = false;  
        $scope.isSelectedRow = item.orgId;
        toggleButtons();
        // adding selected row details
        if (item != undefined) {

        $rootScope.orgId = item.orgId;     
        $scope.rowOrgName=item.orgName;

            $scope.selectedRowDetails = item;
        }
    }

  $scope.selectLocRow = function(item) {
        // for checking single row selection
        $scope.toolLocTipMsg = false;
        $scope.isLocSelectedRow = item.locationId;
        toggleLocButtons();
        // adding selected row details
        if (item != undefined) {

  /* organizationService.getLocById($scope.isLocSelectedRow).then(function(data){
            $scope.org.loc = data;
          
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });*/
        	
        	$scope.selectedLocRowDetails = item;
        	$scope.org.loc = item;
        	
        }
    }
  
    $scope.clearSelectedRow = function() {
    	//$('.dropdown-menu').hide();
    	$('#contactPersonId').siblings('.dropdown-menu').hide();
    	$('#org_loc_add_primary').prop("disabled", false);
    	$scope.empNotFound=false;
    	 $scope.showPrimaryPresent=false;  
        $scope.isSelectedRow = null;
        $scope.primaryLocName=null;
        toggleButtons();
    }
    
     $scope.clearLocSelectedRow = function() {
    	 /*if($scope.orgModelHeading !== "Create" && $scope.locModelHeading !== "Create" && $scope.primaryLocName!== undefined && $scope.primaryLocName!== null && $scope.org.loc.locationName!=$scope.primaryLocName){
    		 $('#org_loc_add_primary').prop("disabled", false);
    	 }*/
    	 $scope.showPrimaryPresent=false;
    	 $scope.locModelHeading = null;
        $scope.isLocSelectedRow = null;
        if($scope.orgModelHeading !== "Create"){
        	$scope.getOrgById($rootScope.orgId);
        }
        toggleLocButtons();
    }
    $scope.operationsGenericFunction = function(doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            $scope.orgView = true;
            $scope.clearSelectedRow();
            $scope.org= {};
            $scope.toolTipMsg = false;
            $scope.disable_Edit = true;
            createOrganization();
            changeLocationOperations();
          //  $scope.orgSearch();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            $scope.orgView = false;
            $scope.toolTipMsg = false;  
            viewOrganization($scope.selectedRowDetails);
            changeLocationOperations();
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            $scope.orgView = true;
            $scope.toolTipMsg = false;    
            editOrganization($scope.selectedRowDetails);
            changeLocationOperations();
        } 
        if (doFunction === "Edit" && $scope.disable_Edit || doFunction === "View" && $scope.disable_Edit) {
            $scope.toolTipMsg = true;          
        } 

        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }
     $scope.operationsLocation = function(doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            $scope.clearLocSelectedRow();
            $scope.disable_Loc_Edit = true;
            $scope.locBtnStatus = true;
            $scope.createLocation();
            $scope.locView = true;
            $scope.toolLocTipMsg = false;
        }
        if (doFunction === "View" && !$scope.disable_Loc_View) {
            $scope.locView = false;
            $scope.locBtnStatus = false;
            $scope.toolLocTipMsg = false;
            viewLocation($scope.selectedLocRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Loc_Edit) {
             $scope.locView = true;
            $scope.locBtnStatus = true;
            $scope.toolLocTipMsg = false;
            editLocation($scope.selectedLocRowDetails);

        }
        if (doFunction === "Edit" && $scope.disable_Loc_Edit || doFunction === "View" && $scope.disable_Loc_View) {
       
            $scope.toolLocTipMsg = true;

        }

        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }
    
     $('#contactPersonId').on('typeahead:selected', function (e, datum) {

});

     function modifyPrimaryLoc(org){
    	 if(org.loc.isPrimary==1 && org.loc.locationName!=$scope.primaryLocName){
    		 $scope.primaryLocData.isPrimary=0;
    		 organizationService.addAddresses($scope.primaryLocData).then(function(data){
    			 $scope.getOrgById($rootScope.orgId);
    			 
            }).catch(function(err){
                showError(appConstants.exceptionMessage);
            });
    	 }else{
    		 $scope.getOrgById($rootScope.orgId);
    	 }
     }
     
     // Add New Location 

     $scope.addLocation = function(org){
    	 var formStatus=checkOrgForm(org);
    	 var locFormStatus=checkLocForm(org);
     	if(formStatus==true && locFormStatus==true){
     	org.loc.osiOrganizations=org.basic;
          organizationService.addAddresses(org.loc).then(function(data){
        	  
        	  $scope.locshowPrimaryPresent=false;  
        	  if(data.httpStatus==200){
        		 
        		  if($scope.locModelHeading == "Edit"){
        			  modifyPrimaryLoc(org);
        			  $('#LocationModal').modal('toggle');
        	       		 $scope.clearLocSelectedRow();
                            $scope.orgSearch();
        	       		  	$scope.locshowSuccessAlert=true;
        	       		  	$scope.successTextAlert=appConstants.successMessage;
        	       		  	$timeout(function () {
        	       	            $scope.locshowSuccessAlert = false;
        	       	            $scope.locshowErrorAlert = false;
        	       	   }, 5000);
        			  
        		  }else{
        			  $scope.org= {};
        			  $scope.init();
        			  $('#LocationModal').modal('toggle');
        			  $('#organizationModal').modal('toggle');
                      $scope.clearSelectedRow();
                      $scope.orgSearch();
        			  $scope.locshowSuccessAlert=true;
        			  if($scope.locModelHeading == "Create" && $scope.orgModelHeading == "Edit"){
        			  $scope.successTextAlert=appConstants.successMessage;}
        			  else{
        				  $scope.successTextAlert="Successfully added organization";
        			  }
        			  $timeout(function () {
        	            $scope.locshowSuccessAlert = false;
        	            $scope.locshowErrorAlert = false;
        			  }, 5000);
        		  }
        	  }
         }).catch(function(err){
        	 if(err.data.errorMessage!=undefined){
        		 showError(err.data.errorMessage);
        	 }else{
        		 showError(appConstants.exceptionMessage);
        	 }
         });
     	}
     };

    function createOrganization() {
        $scope.orgModelHeading = "Create";
        $scope.org.basic = {};
        $scope.org.basic.active=0;
        vm.orgLocLists = {};
        $scope.hideShortCode = false;
        $timeout(function () {
        vm.pOrgLists = vm.orgLists1;   
        $('#organizationModal').modal({backdrop: 'static', keyboard: false});
    }, 500);      
       
    }
     
    function checkLocForm(orgData){
    	if(orgData.loc.locationName==null || orgData.loc.locationName==undefined || orgData.loc.locationName==""){
    		$scope.locshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Location place";
			$timeout(function () {
   	            $scope.locshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.loc.osiRegionsId==undefined || orgData.loc.osiRegionsId.regionId==undefined || orgData.loc.osiRegionsId.regionId==null || orgData.loc.osiRegionsId.regionId==undefined || orgData.loc.osiRegionsId.regionId==""){
    		$scope.locshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Location Region";
			$timeout(function () {
   	            $scope.locshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.loc.osiTimezonesId==undefined || orgData.loc.osiTimezonesId.tzId==undefined || orgData.loc.osiTimezonesId.tzId==null || orgData.loc.osiTimezonesId.tzId==undefined || orgData.loc.osiTimezonesId.tzId==""){
    		$scope.locshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Location Timezone";
			$timeout(function () {
   	            $scope.locshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.loc.osiAddresses==undefined || orgData.loc.osiAddresses.addressLine1==null || orgData.loc.osiAddresses.addressLine1==undefined || orgData.loc.osiAddresses.addressLine1==""){
    		$scope.locshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Address line1";
			$timeout(function () {
   	            $scope.locshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.loc.osiAddresses.countryId==null || orgData.loc.osiAddresses.countryId==undefined || orgData.loc.osiAddresses.countryId==""){
    		$scope.locshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Country";
			$timeout(function () {
   	            $scope.locshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.loc.osiAddresses.stateId==null || orgData.loc.osiAddresses.stateId==undefined || orgData.loc.osiAddresses.stateId==""){
    		$scope.locshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select State";
			$timeout(function () {
   	            $scope.locshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.loc.osiAddresses.city==null || orgData.loc.osiAddresses.city==undefined || orgData.loc.osiAddresses.city==""){
    		$scope.locshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter City";
			$timeout(function () {
   	            $scope.locshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.loc.osiAddresses.zipcode==null || orgData.loc.osiAddresses.zipcode==undefined || orgData.loc.osiAddresses.zipcode==""){
    		$scope.locshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Zipcode";
			$timeout(function () {
   	            $scope.locshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else{
    		return true;
    	}
    }
    
    function checkDuplicateOrg(orgData){
    	if($scope.orgModelHeading == "Edit" && $scope.rowOrgName!==undefined && $scope.rowOrgName!==null && $scope.rowOrgName==orgData.basic.orgName){
    		$scope.duplicateOrgName=false;
		}else{    	
    	for(var i=0;i<vm.orgLists.length;i++){
    		if(orgData.basic.orgName.toUpperCase()==vm.orgLists[i].orgName.toUpperCase()){
				$scope.duplicateOrgName=true;
				break;
			}
		}
		}
    }
     
    function checkOrgForm(orgData){    	
    	if(orgData.basic.orgName!=null && orgData.basic.orgName!=undefined && orgData.basic.orgName!=""){
    		checkDuplicateOrg(orgData);	
    	}
 		if($scope.duplicateOrgName==true){
 			$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Organization name already exists";
			$timeout(function () {
				$scope.duplicateOrgName=false;
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
 		}
 		else if(orgData.basic.orgName==null || orgData.basic.orgName==undefined || orgData.basic.orgName==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Organization Name";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.website==null || orgData.basic.website==undefined || orgData.basic.website==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Website";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(!(/^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/.test(orgData.basic.website))){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Valid Website URL";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.orgShortName==null || orgData.basic.orgShortName==undefined || orgData.basic.orgShortName==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Short Name";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.emailId==null || orgData.basic.emailId==undefined || orgData.basic.emailId==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Contact Email";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(!(/^([0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9})$/.test(orgData.basic.emailId))){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Valid Email";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.countryCode==null || orgData.basic.countryCode==undefined || orgData.basic.countryCode==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Country Code";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.phoneNumber==null || orgData.basic.phoneNumber==undefined || orgData.basic.phoneNumber==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Phone Number";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}/*else if(orgData.basic.contactPersonName==null || orgData.basic.contactPersonName==undefined || orgData.basic.contactPersonName==""){
    		$scope.showErrorAlert = true;
			$scope.ErrorTextAlert="Contact person is required.";
			$timeout(function () {
   	            $scope.showErrorAlert = false;
   		  	}, 3000);
			return false;
    	}*/else if(orgData.basic.faxCode==null || orgData.basic.faxCode==undefined || orgData.basic.faxCode==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Fax Code";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.faxNumber==null || orgData.basic.faxNumber==undefined || orgData.basic.faxNumber==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Fax Number";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.parentOrgId==null || orgData.basic.parentOrgId==undefined || orgData.basic.parentOrgId==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Parent Organization";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.totalHrsPerYear==null || orgData.basic.totalHrsPerYear==undefined || orgData.basic.totalHrsPerYear==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Total Working Hours";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.overheadPct==null || orgData.basic.overheadPct==undefined || orgData.basic.overheadPct==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Over Head %";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.costCalc==null || orgData.basic.costCalc==undefined || orgData.basic.costCalc==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Cost Calculation";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.interEmpOverheadPct==null || orgData.basic.interEmpOverheadPct==undefined || orgData.basic.interEmpOverheadPct==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Enter Internal Over Head %";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.baseCurrencyId==null || orgData.basic.baseCurrencyId==undefined || orgData.basic.baseCurrencyId==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Base Currency";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.reportingCurrencyId==null || orgData.basic.reportingCurrencyId==undefined || orgData.basic.reportingCurrencyId==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Reporting Currency";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else if(orgData.basic.startDayOfWeek==null || orgData.basic.startDayOfWeek==undefined || orgData.basic.startDayOfWeek==""){
    		$scope.orgshowErrorAlert = true;
			$scope.ErrorTextAlert="Please Select Start Day of Week";
			$timeout(function () {
   	            $scope.orgshowErrorAlert = false;
   		  	}, 3000);
			return false;
    	}else{
    		return true;
    	}
    }

    $scope.createLocation = function(orgData) {
    	$('#contactPersonId').siblings('.dropdown-menu').hide();
    	$scope.empNotFound=false;
        $scope.getEmpId();
        $scope.locView = true;
    	if(orgData!==undefined){
    		var formStatus=checkOrgForm(orgData);
    	}else{
    		formStatus=true;
    	}
    	if(formStatus==true){
        $scope.locModelHeading = "Create";
        $scope.org.loc = {};
        if($scope.orgModelHeading == "Create" && $scope.locModelHeading == "Create"){
        	$scope.org.loc.isPrimary = 1;
        	$scope.org.loc.active = 1;
        	$('#org_loc_add_primary').prop('checked', true).prop("disabled", true);
        	$('#org_loc_add_active').prop('checked', true).prop("disabled", true);
        	
        }
        $('#LocationModal').modal({backdrop: 'static', keyboard: false});
    	}
    }

    function editOrganization(data) {

        $scope.orgModelHeading = "Edit";
        $scope.getOrgById(data.orgId);
        $scope.hideShortCode = true;
        $('#organizationModal').modal({backdrop: 'static', keyboard: false});
        // $scope.clearSelectedRow();
    }
    function viewOrganization(data) {
        $scope.hideShortCode = true;
        $scope.orgModelHeading = "View";
        $scope.getOrgById(data.orgId);
        $('#organizationModal').modal({backdrop: 'static', keyboard: false});
        // $scope.clearSelectedRow();
    }
    function editLocation(data) {
    	$('#contactPersonId').siblings('.dropdown-menu').hide();
    	$scope.empNotFound=false;
    	$scope.locModelHeading = "Edit";
            $scope.orgModelHeading = "Edit";
            $scope.getLocById(data.locationId);
            $('#LocationModal').modal({backdrop: 'static', keyboard: false});
            // $scope.clearSelectedRow();
    }
    function viewLocation() {
    	$('#contactPersonId').siblings('.dropdown-menu').hide();
    	$scope.empNotFound=false;
            $scope.locModelHeading = "View";

            $('#LocationModal').modal({backdrop: 'static', keyboard: false});
            // $scope.clearSelectedRow();
    }
    
    $scope.chngClick = function(click){
        $scope.count = click;
    }

    $scope.editSelectedRowDetails = function(item) {
        changeLocationOperations();
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
           // editFunction(angular.copy(item));
            $scope.isSelectedRow = item.orgId;
            toggleButtons();
             $scope.orgView = true;
            editOrganization($scope.selectedRowDetails);
        }
         $scope.clicked = false;
    }

    $scope.editSelectedLocRowDetails = function(item) {
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
           // editFunction(angular.copy(item));
            $scope.isLocSelectedRow = item.id;
            toggleLocButtons();
            editLocation($scope.selectedLocRowDetails);
        }
        // added - recently - for view
        if(!$scope.disable_Edit)
            $scope.locBtnStatus = true;
        $scope.locView = true;
        // added - recently - for view
    }

    function toggleButtons() {
        if ($scope.isSelectedRow == null) {
            $scope.disable_Edit = true;
            $scope.disable_View = true;
            $scope.disable_Delete = true;
        } else {
            $scope.disable_Edit = false;
            $scope.disable_View = false;
            $scope.disable_Delete = false;
        }
    }

     function toggleLocButtons() {
        if ($scope.isLocSelectedRow == null) {

            $scope.disable_Loc_Edit = true;
            $scope.disable_Loc_View = true;
            $scope.disable_Loc_Delete = true;


        } else {

            $scope.disable_Loc_Edit = false;
            $scope.disable_Loc_View = false;
            $scope.disable_Loc_Delete = false;  

        }
    }
    $scope.init = function() {
    	$scope.showPrimaryPresent=false;
    	$scope.primaryLocName=null;
    	//$('#org_loc_add_primary').prop("disabled", false);
    	var searchData = {
    	      
    	    };
    	LookupService.getLookupByLookupName('ORG_COST').then(function (result) {
			$scope.costList = result.osiLookupValueses;
 	});
    	personalInfoService.getAllCountryList().then(function(data){
  		  
  		  vm.countryCodeList = data;
  	  }).catch(function(error){
  		  var msg = "Error occured while getting country codes."
  			  $rootScope.isTrascError = true;
  			  FlashService.Error(msg);
  			  $timeout(function() {
  				$rootScope.isTrascError = false;
  			  }, 5000);
  	  });
        organizationService.getCurrencies().then(function(data){
            $scope.currencies = data;
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });

        organizationService.getCountries().then(function(data){
            $scope.countries = data;
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });
        
        organizationService.getRegions().then(function(data){
        	$scope.regions=data;
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });
        
        organizationService.getTimezones().then(function(data){
        	$scope.timezones=data;
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });
        vm.orgLists = [];
       
        organizationService.getOrganizationList(searchData).then(function(data){
            vm.orgLists1 = data;
            $scope.sort('orgName');
            $scope.reverse=false;
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });
       
    }

    $scope.getEmpId = function(){
    	if($scope.empData !== undefined){
    	  for(var i=0; i<$scope.empData.length; i++){

       	   if($scope.empData[i].fullName ==   $scope.org.basic.contactPersonName){
                  $scope.org.basic.contactPersonId = $scope.empData[i].employeeId;
              }
           }
    	}
   }

$('#contactPersonId').typeahead({
itemSelected:function(data,value,text){

},
//your other stuffs
});

$scope.setPersonId =  function(item)
{
    $scope.org.basic.contactPersonId = item.employeeId;
    $scope.org.basic.contactPersonName = item.fullName;
    $("#refferedBypopupModel").modal("hide");
}

    $scope.getEmpData = function(){
    	if($('#contactPersonId').val().length==0) {
    		$scope.empNotFound=false;
    	}
         $scope.empData  = [];
       
        //$scope.empData = organizationService.getEmpData($scope.org.basic.contactPersonName);

        if($scope.org.basic.contactPersonName.length >=3) {
        	$('#contactPersonId').siblings('.dropdown-menu').show();
        	 var empSearch={};
           empSearch.empName=$scope.org.basic.contactPersonName;
        	empSearch.orgId=  $rootScope.orgId.toString();
             organizationService.getEmpData(empSearch).then(function(data){
            	 	if(data==null || data==""){
            	 		$scope.empNotFound=true;
            	 	}else{
            	 		$scope.empData = data;
            	 		$scope.empNotFound=false;
            	 	}
                    
                }).catch(function(err){
                    //showError(appConstants.exceptionMessage);
                });

        }
    }
    // Retrieve states base on countryId

    $scope.$watch("org.loc.osiAddresses.countryId", function(countryId, oldVal) {

        if (countryId !== oldVal && countryId!== undefined && countryId!== null  ) {
            organizationService.getStates(countryId).then(function(data){
                $scope.states = data;
            }).catch(function(err){
                showError(appConstants.exceptionMessage);
            });
        }

    });
    
    $scope.$watch("org.loc.isPrimary",function(isPrimary){
    	if(isPrimary==1 && $scope.primaryLocName!== null && $scope.primaryLocName!== undefined && $scope.org.loc.locationName!=$scope.primaryLocName){
    		$scope.locshowPrimaryPresent=true;
    		//$('#org_loc_add_primary').prop("disabled", false);
    	}
    	else{
    		$scope.locshowPrimaryPresent=false;
    		//$('#org_loc_add_primary').prop("disabled", true);
    	}
    	if(isPrimary==1){
    		$('#org_loc_add_active').prop("checked",true).prop("disabled", true);
    	}else if(isPrimary==0 && $scope.primaryLocName!== null && $scope.primaryLocName!== undefined && $scope.org.loc.locationName!=$scope.primaryLocName){
    		
            $('#org_loc_add_primary').prop("disabled", false);
            if($scope.org.loc.active)
                $('#org_loc_add_active').prop("checked",true).prop("disabled", false);
            else
            $('#org_loc_add_active').prop("checked",false).prop("disabled", false);
    	}
    	if(isPrimary==1 && $scope.primaryLocName!== null && $scope.primaryLocName!== undefined && $scope.org.loc.locationName==$scope.primaryLocName){
    	$('#org_loc_add_primary').prop("disabled", true);
    	}
    	if(isPrimary==undefined){
    		$('#org_loc_add_primary').prop("disabled", false);
    		$('#org_loc_add_active').prop("checked",false).prop("disabled", false);
    	}
    });

    // Add New Organization 

    $scope.addOrganization = function(orgData, type){
    	var formStatus=checkOrgForm(orgData);
    	if(formStatus==true){
    		$scope.getEmpId(); 
        
    		if($scope.orgModelHeading=='Edit'){
        	$scope.locModelHeading = "Edit";
        	if(type == 'basic') {

            organizationService.addOrganization(orgData.basic).then(function(data){
               $rootScope.orgId = data.message;
               if(data.httpStatus==200){
                    $scope.orgSearch();
         		  $('#organizationModal').modal('toggle');
         		  $scope.orgshowSuccessAlert=true;
                   $scope.successTextAlert=appConstants.successMessage;
                   $scope.hideShortCode = true;
         		  $timeout(function () {
         	            $scope.orgshowSuccessAlert = false;
         	            $scope.orgshowErrorAlert = false;
         	        }, 5000);
         	  }
            }).catch(function(err){
                showError(appConstants.exceptionMessage);
            });

        	} else {

            organizationService.addOrganization(orgData.basic).then(function(data){
               $rootScope.orgId = data.message;
               if(data.httpStatus==200){
            	   //$scope.org= {};
                   //$scope.init();
                   $scope.orgSearch();
         		  $('#organizationModal').modal('toggle');
         		 $scope.clearSelectedRow();
         		  $scope.orgshowSuccessAlert=true;
         		  $scope.successTextAlert=appConstants.successMessage;
         		  $timeout(function () {
         	            $scope.orgshowSuccessAlert = false;
         	            $scope.orgshowErrorAlert = false;
         	        }, 5000);
         	  }
            }).catch(function(err){
                $scope.orgshowErrorAlert = true;
                $scope.ErrorTextAlert=err.data.developerMessage;
                $timeout(function () {
                      $scope.orgshowErrorAlert = false;
                  }, 3000);
            });

            orgData.objectType = "Organization";
            orgData.orgId =  $rootScope.orgId;
            if(orgData.address){
                $scope.osiOrgAddresseses.push(orgData.address);

             organizationService.addAddresses($scope.osiOrgAddresseses).then(function(data){
            }).catch(function(err){
                showError(appConstants.exceptionMessage);
            });
        }

        }}
    	}
    };
    function getParentOrganizations(orgId){
        vm.pOrgLists = [];
        for(var i=0;i<vm.orgLists.length;i++){
    		if(orgId!=vm.orgLists[i].orgId){
				vm.pOrgLists.push(vm.orgLists[i]);
			}
        }
        console.log(vm.pOrgLists);
    }
    // Retrive data base on OrganizationId

    $scope.getOrgById = function(orgId){
        
        $rootScope.orgId = orgId;
        $timeout(function() {
            getParentOrganizations(orgId);
        }, 500);
     
        organizationService.getOrgById(orgId).then(function(data){
            $scope.org.basic = data;
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });

        organizationService.getOrgLocById(orgId).then(function(data){
            vm.orgLocLists = data;
            for(var i=0;i<vm.orgLocLists.length;i++){
               	if(vm.orgLocLists[i].isPrimary==1){
               		$scope.primaryLocName=vm.orgLocLists[i].locationName;
               		$scope.primaryLocData=vm.orgLocLists[i];
               	}
               }
            if($scope.orgModelHeading !== "Create" && $scope.locModelHeading !== "Create" && $scope.primaryLocName!== undefined && $scope.primaryLocName!== null && $scope.org.loc!=undefined && $scope.org.loc.isPrimary==1 && $scope.org.loc.locationName==$scope.primaryLocName){
  	    		 $('#org_loc_add_primary').prop("disabled", true);
  	    	 }
            
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });

    };
    
    // Retrieve data base on locationId 

    $scope.getLocById = function(locationId){
        
       // $scope.orgId = locationId;
        /*organizationService.getLocById(locationId).then(function(data){
            $scope.org.loc = data;
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });*/
    	organizationService.getOrgLocById(locationId).then(function(data){
            //vm.orgLocLists = data;
            for(var i=0;i<vm.orgLocLists.length;i++){
               	if(vm.orgLocLists[i].isPrimary==1){
               		$scope.primaryLocName=vm.orgLocLists[i].locationName;
               		$scope.primaryLocData=vm.orgLocLists[i];
               	}
               }
            if($scope.orgModelHeading !== "Create" && $scope.locModelHeading !== "Create" && $scope.primaryLocName!== undefined && $scope.primaryLocName!== null && $scope.org.loc!=undefined && $scope.org.loc.isPrimary==1 && $scope.org.loc.locationName==$scope.primaryLocName){
  	    		 $('#org_loc_add_primary').prop("disabled", true);
  	    	 }
            
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });

    };
    
    // organization serach result 

    $scope.orgSearch = function(searchData){
    	if(searchData == undefined) {
            searchData = {};
        }
        var orgName1 = "";
        var country1 = "";
        var location1 = "";
        if($scope.org && $scope.org.search && $scope.org.search.orgName){
            orgName1 = $scope.org.search.orgName;
        }
        if($scope.org && $scope.org.search && $scope.org.search.country){
            country1 = $scope.org.search.country;
        }
        if($scope.org && $scope.org.search && $scope.org.search.location){
            location1 = $scope.org.search.location;
        }
        searchData = {orgName: orgName1, country:country1, location:location1};
        organizationService.getOrganizationList(JSON.stringify(searchData)).then(function(data){
            vm.orgLists = data;
        }).catch(function(err){
            showError(appConstants.exceptionMessage);
        });
    };
 
     function showError(msg){
        $scope.ErrorTextAlert = msg;
        $scope.showErrorAlert = true;
        $timeout(function () {
            $scope.showSuccessAlert = false;
            $scope.showErrorAlert = false;
        }, 5000);
    }
    
    
    $scope.warnPrimaryIn=function(){
    	if( $scope.locModelHeading !== "View" && $('#org_loc_add_primary').prop('disabled') && $('#org_loc_add_primary').prop('checked')){
    		$scope.showWarning=true;
    	}
    }
    $scope.warnPrimaryOut=function(){
    		$scope.showWarning=false;
    }
    
    $scope.init();

    $scope.searchEmployeePopup = function(contactPerson){
    	if(contactPerson.length > 0 ){
    		$scope.getEmployeeListByNameForRefferedBy(contactPerson,'frmFunction');
    		
    		
    	}
    }
    $scope.setCustRowsItem = function(index){
        $scope.setPersonId($scope.searchParamsList[index]);
    }
    $scope.getEmployeeListByNameForRefferedBy = function(contactPerson,type){
        var empSearch={};
        
            empSearch.empName=contactPerson;
            empSearch.orgId=  $rootScope.orgId.toString();
               organizationService.getEmpData(empSearch).then(function(data){
                $scope.searchParamsList=[];
                angular.forEach(data,function(v){
                    var emp = {
                            fullName : v.fullName,
                            employeeId : v.employeeId
                        };
                        console.log(v);
                    $scope.searchParamsList.push(emp);
                });
                if(type === 'frmFunction'){
                       if(data.length > 1)
                       $("#refferedBypopupModel").modal("show");
                   else if(data.length === 1)
                       $scope.setPersonId($scope.searchParamsList[0]);
                   else{
                       $rootScope.isTrascError = true;
                       FlashService.Error('No match found with the given Referred By name');
                       $scope.proceed = false;
                       $timeout(function() {
                           $rootScope.isTrascError = false;
                       }, 2000);
                   }
                }
                  }).catch(function(err){
                    $rootScope.orgshowErrorAlert = true;
                       $scope.ErrorTextAlert = 'No match found with the given Contact Person';
                       $scope.proceed = false;
                       $timeout(function() {
                           $rootScope.orgshowErrorAlert = false;
                       }, 3000);
                  });
    }
}
