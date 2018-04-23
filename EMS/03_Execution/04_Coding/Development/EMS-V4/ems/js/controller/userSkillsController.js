'use strict';
angular.module('myApp.userSkillsController', [])
        .controller('userSkillsController', ['$scope', '$rootScope', '$window', '$location', '$http',
                                  '$timeout','FlashService','$cookieStore','appConstants',
                                  'LookupService', 'employeeSkillService', 'Upload', 'configData', '$localStorage','personalInfoService','filterFilter',
		function ($scope, $rootScope, $window, $location, $http, $timeout, FlashService, $cookieStore, appConstants, LookupService,
			employeeSkillService, Upload, configData,$localStorage,personalInfoService,filterFilter) {
      var empId = $localStorage.employeeId;
      $scope.usOrgCode = configData.usOrgCode;
      $scope.usOrg = $localStorage.usOrg;
      var vm = this;
			$scope.rowSize = 10;
      var filtered = [];
			$scope.selectedPrimPrefs=1;
			 $scope.nonEditable = $localStorage.isSaveEdit;
            $scope.showFailureAlert=false;
            $localStorage.isReturn = false;
			$scope.successMssage = "";
			$scope.yearsOfExp = true;
			$scope.osiSkills = [];
			$scope.osiSkillSearchResult = [];
            $scope.osiCertificates=[];
			$scope.osiCertificateSearchResult = [];
			$scope.osiUser={};
			$scope.osiUserCertificates={};
			$scope.osiUserCertificates={};
			$scope.seqs=[{"id":"1","value":"true"},{"id":"0","value":"false"}];
			$scope.disable_Edit = true;
			$scope.disable_View = true;
      $scope.loggedInEmpId = $rootScope.globals.userDTO.id;
			$scope.isContactsaved=false;
            $scope.employeeId = $localStorage.employeeId;
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
      $scope.updateEmergencyContact = false;
      $scope.enbleEdit = false;
      $scope.empContactsInfo = [{"seq":"","name":"","relation":"","countryCode":"","mobileNumber":"","altrMobileNumber":""},{"seq":"","name":"","relation":"","countryCode":"","mobileNumber":"","altrMobileNumber":""}];
      $scope.isSkillLoaded = true;
      $scope.isCertificateLoaded = true;
			var yyyy = today.getFullYear();
			if(dd<10){
			    dd='0'+dd;
			}
			if(mm<10){
			    mm='0'+mm;
			}
			var today = yyyy+'-'+mm+'-'+dd;

			$scope.minDOH = today;
			$timeout(function() {
        $scope.availOperations = $localStorage.availOperations;
        $scope.availOperations = [];
        $scope.availOperations.push({"name":"Create", "url":"/create"}, {"name":"Edit", "url":"/edit"}, {"name":"View", "url":"/view"});
			}, 400);

      vm.clearMedicalInfo = function(){
        window.location.href = "#/employees";
        $localStorage.isReturn = true;
      }

      $scope.backToEmpList = function(){
				/*initController();*/
				window.location.href = "#/employees";
				$localStorage.isReturn = true;
			}

      $scope.backToEmpListsf = function(){
				/*initController();*/
				window.location.href = "#/employeesList";
				$localStorage.isReturn = true;
      }

      $scope.cancelSkills = function() {
        $scope.emp = {};
        $scope.skillNotFound=false;
        $("#refferedBypopupModel").modal("hide");
        $scope.init();
       //$scope.getOsiSkillsList();
    	$("#mySkillModal").modal("hide");
      }
			$scope.getOsiSkillsList = function(){
				employeeSkillService.getAllSkills().then(function (result) {
					console.log(result);
					$scope.osiSkills = result;
				});
			}
			$scope.saveSkills =  function(){

				$scope.osiUser.employeeId = empId;
				$scope.osiUser.createdBy = empId;
				$scope.osiUser.updatedBy = empId;
        $scope.osiUser.skillName=$scope.osiUser.skillName

        if($scope.osiUser.isVerified)
          $scope.osiUser.verified = "true";
        else
          $scope.osiUser.verified = "false";
        if($scope.osiUser.monthsOfExp == undefined)
          $scope.osiUser.monthsOfExp = 0;
        if($scope.osiUser.monthsOfExp != null && (0 <= $scope.osiUser.monthsOfExp) && ($scope.osiUser.monthsOfExp <= 12)) {
            employeeSkillService.getSkillsById($scope.osiUser.skillId).then(function (data) {
              if(!data[0].active) {
                $scope.showFailureAlertPopup=true;
                $scope.failureTextAlert="Selected Skill is Not Active";
                $timeout(function(){
                  $scope.showFailureAlertPopup=false;
                }, 3000);
              } else {

              employeeSkillService.saveEmpSkills($scope.osiUser).then(function (result) {
                console.log(result);
                $scope.showSuccessAlertPopup=true;
                $scope.successTextAlert =appConstants.successMessage;
                $scope.clearSkills();
                $scope.searchSkills();
                $window.location.reload();
                $timeout(function(){
                  $scope.showSuccessAlertPopup=false;
                  $('#mySkillModal').modal('hide');
                }, 2000);


              }).catch(function (result) {
            	  var msg = appConstants.exceptionMessage;
                if(result.data.httpStatus){ 
                  msg=result.data.message; 
                }
                $scope.showFailureAlertPopup=true;
                $scope.failureTextAlert = msg;

                $timeout(function(){
                  $scope.showFailureAlertPopup=false;

                }, 3000);
              });
            }
          });
        }else {
          $scope.showFailureAlertPopup=true;
          $scope.failureTextAlert = 'Please enter a valid Months of Experience';
          $timeout(function(){
            $scope.showFailureAlertPopup=false;
          }, 3000);
        }
			}
			$scope.searchSkills =  function(){
        $scope.IsAllChecked = false;
        $scope.osiUser.employeeId = empId;
				employeeSkillService.searchEmpSkills($scope.osiUser).then(function (result) {
				//console.log("dfdfdfd --- ");
				//console.log(result);
        $scope.osiSkillSearchResult = result;
        angular.forEach($scope.osiSkillSearchResult, function(value, key) {
          if(value.verified == "true")
            value.isVerified = true;
          else
            value.isVerified = false;
        });
        $scope.CheckUncheckHeader();
				});
			}
			$scope.searchSkills();
			//$scope.getOsiSkillsList();
      $scope.clearSkills=function(){
			     $scope.osiUser=[];
        //   $scope.getOsiSkillsList();
			}


        	/*<!--Certificates -->*/
            $scope.getOsiCertificatesList = function(){
              $scope.preview = {};

				employeeSkillService.getAllCertificates().then(function (result) {
					$scope.osiCertificates = result;
				});
			}

            $scope.saveCertificates =  function(){

              if (!_.isEmpty(file)){
                $scope.osiUserCertificates.osiEmpAttachments=[];
                console.log($scope.tempAttachment);
                $scope.tempAttachment={};
                $scope.tempAttachment.fileContent = file.fileContent;
                $scope.tempAttachment.fileType = file.fileType;
                $scope.tempAttachment.originalFileName = file.originalFileName;
                $scope.osiUserCertificates.osiEmpAttachments.push($scope.tempAttachment);
              }

				$scope.osiUserCertificates.employeeId = empId;
				$scope.osiUserCertificates.createdBy = empId;
				$scope.osiUserCertificates.updatedBy = empId;
                if($scope.osiUserCertificates.isVerified)
                    $scope.osiUserCertificates.verified = "true";
                else
                    $scope.osiUserCertificates.verified = "false";
        employeeSkillService.getAllCertificatesById($scope.osiUserCertificates.certificationId).then(function (data) {
          if(!data[0].active) {
            $scope.showFailureAlertPopup=true;
            $scope.failureTextAlert=" Selected certification is Not Active";
            $timeout(function(){
              $scope.showFailureAlertPopup=false;
            }, 3000);
          } else {

				employeeSkillService.saveEmpCertificates($scope.osiUserCertificates).then(function (result) {
					$scope.isTrascError=true;
					$scope.showSuccessAlertPopup=true;
					$scope.successTextAlert = appConstants.successMessage;
                    $scope.clearCertifications();
                    $scope.tempAttachment = {};
          $scope.searchCertificates();
          window.location.reload();
          $timeout(function(){
            $scope.showSuccessAlertPopup=false;
            $('#myModalCertificate').modal('hide');

          }, 2000);

				}).catch(function (error) {
					var msg = appConstants.exceptionMessage;
					if(error.data.httpStatus){ 
			 		  msg=error.data.message; 
			 		}
                    $scope.showFailureAlertPopup=true;
                    $scope.failureTextAlert=msg;
                    $timeout(function(){
                        $scope.showFailureAlertPopup=false;

                      }, 3000);
				});
      }
			});
    }
      $scope.searchCertificates =  function(){
        $scope.IsAllCheckedCert = false;

				$scope.osiUserCertificates.employeeId = empId.toString();
				employeeSkillService.searchEmpCertificates($scope.osiUserCertificates).then(function (result) {
        $scope.osiCertificateSearchResult = result;

        angular.forEach($scope.osiCertificateSearchResult, function(value, key) {
          if(value.verified == "true")
            value.isVerified = true;
          else
            value.isVerified = false;
        });
        $scope.CheckUncheckHeaderCert();
				});
			}
			//$scope.getOsiCertificatesList();
			$scope.searchCertificates();
      $scope.clearCertifications=function(){
			     $scope.osiUserCertificates=[];
           //$scope.getOsiCertificatesList();
      }
      $scope.getIssuedBy = function(data){
        $scope.osiUserCertificates.issuedBy = data.issuedBy;
        }
      $scope.cancelcertificates = function() {
    	 // $scope.getOsiCertificatesList();
    	 $("#myModalCertificate").modal("hide");
       $scope.init();
       $scope.emp = {};
       $scope.certificationNotFound=false;
       $('#certificationId').siblings('.dropdown-menu').show();
      }

			$scope.selectRow = function (item) {
			$scope.osiUser = item;
			$scope.osiUser.empSkillId = item.empSkillId;
			$scope.disable_Edit = false;
			$scope.disable_View = false;

			$scope.isSelectedRow = item.empSkillId;
			}
			$scope.selectCertRow= function (item) {
        var tempCerAttachment = {};
        $scope.osiUserCertificates = item;
        $scope.osiUserCertificates.empCertificationId = item.empCertificationId;
        $scope.disable_Edit = false;
        $scope.disable_View = false;

        $scope.isCertSelectedRow = item.empCertificationId;

        if(item.attachmentId != null) {
          $scope.preview = {};
          angular.forEach(item.osiEmpAttachments, function(val,key){
            if(val.attachmentId === item.attachmentId){
              $scope.preview.content = val.fileContent;
              $scope.preview.name = val.originalFileName;
              tempCerAttachment.fileContent = val.fileContent;
              tempCerAttachment.attachmentId = val.attachmentId;
              tempCerAttachment.attachmentType = val.attachmentType;
              tempCerAttachment.duplicateFileName = val.duplicateFileName;
              tempCerAttachment.fileType = val.fileType;
              tempCerAttachment.objectId = val.objectId;
              tempCerAttachment.objectType = val.objectType;
              tempCerAttachment.originalFileName = val.originalFileName;
              tempCerAttachment.createdBy = val.createdBy;
              tempCerAttachment.creationDate = val.creationDate;
              tempCerAttachment.lastUpdateDate = val.lastUpdateDate;
              tempCerAttachment.lastUpdatedBy = val.lastUpdatedBy;
            }
          });
          $scope.tempAttachment = tempCerAttachment;
        }
			}

/*<!-- Emergency Contacts-->*/
            $scope.editEmergencyContacts = function(){

                var  urlPath = $location.path();
                if(urlPath.includes('emergencyContacts-sf')) {
                  if($rootScope.globals.userDTO!==undefined){
                    var loggedInEmpId = $rootScope.globals.userDTO.id;
                    if( $localStorage.employeeId == loggedInEmpId ) {
                    	 $scope.enbleEdit = true;
                         $scope.updateEmergencyContact = true;
                    } else {
                    	 $scope.enbleEdit = false;
                    }
                  }
                } else {
                  $scope.enbleEdit = true;
                }


            }

            $scope.updateEmergencyContacts = function(contactsInfo){
                employeeSkillService.editEmergencyContacts(contactsInfo).then(function (result) {
					$scope.isTrascError=true;
					$scope.showSuccessAlert=true;
					$scope.successTextAlert =appConstants.successMessage;
					 $timeout(function () {
	                    	$scope.showSuccessAlert=false;
	               		}, 2000);

				}).catch(function (error) {
                    $scope.showFailureAlert=true;
                    var msg = appConstants.exceptionMessage;
            		if(error.data.httpStatus){ 
             		  msg=error.data.errorMessage; 
             		}
                    $scope.failureTextAlert=msg;
                    $timeout(function () {
                    	$scope.showFailureAlert=false;
               		}, 2000);
				});
            }

        	$scope.getRelations=function(){
        		LookupService.getLookupByLookupName('RELATION').then(function (result) {
        			$scope.relationsList = result.osiLookupValueses;
         	});
        }
		$scope.loadEmergencyContacts=function(){

			employeeSkillService.getAllContacts(empId).then(function (result) {


                if(result.length > 0 ){
                     $scope.empContactsInfo = result;
                    for(var i=0;i<$scope.empContactsInfo.length;i++){
                    	 if($scope.empContactsInfo[i].seq == "0"  )
                    	   $scope.empContactsInfo[i].seq = false;
                    	 else
                    		 $scope.empContactsInfo[i].seq = true;
                  }
                    $scope.enbleEdit = true;
                }else{

                }


			});


		}

        $scope.checkPrefrence = function(id){
            if(id == 0 ){
                $scope.empContactsInfo[0].seq = true;
                 $scope.empContactsInfo[1].seq = false;


            }else if(id == 1){
                  $scope.empContactsInfo[0].seq = false;
                 $scope.empContactsInfo[1].seq = true;
            }
        }
        $scope.getRelations();
		$scope.loadEmergencyContacts();
		   $scope.editEmergencyContacts();

         $scope.saveEmergencyContacts = function(){

            var saveFlag = false;
            for(var i=0;i<$scope.empContactsInfo.length;i++){
                $scope.empContactsInfo[i].employeeId = empId;
                $scope.empContactsInfo[i].createdBy = empId;
                $scope.empContactsInfo[i].updatedBy = empId;
                if($scope.empContactsInfo[i].seq == false)
                    $scope.empContactsInfo[i].seq = 0;
                else {
                  $scope.empContactsInfo[i].seq = 1;
                  saveFlag = true;
                }

            }
            console.log($scope.empContactsInfo);
            console.log(saveFlag);
            if(saveFlag){
              var validEC = false;
              var msg ='';
              angular.forEach($scope.empContactsInfo, function(field,key) {
                if(field.seq) {
                  if(field.name != null && field.name.trim() != '') {

                      if(field.countryCode != null && field.countryCode != '') {
                        if(field.mobileNumber != null && field.mobileNumber != '' /*&& field.mobileNumber.length >= 10*/) {
                        if(field.relation != null && field.relation.trim() != '') {
                          validEC = true;
                        } else {
                          if(field.seq == 1){
                            field.seq=true;
                          }
                          msg = 'Please Select RelationShip for Primary Contact';
                        }
                      } else {
                        if(field.seq == 1){
                          field.seq=true;
                        }
                        msg = 'Please Enter Valid Mobile Number for Primary Contact';
                      }
                    } else {
                      if(field.seq == 1){
                        field.seq=true;
                      }
                      msg = 'Please Select Country Code for Primary Contact';
                    }
                  } else {
                    if(field.seq == 1){
                      field.seq=true;
                    }
                    msg = 'Please Enter Name for Primary Contact';
                  }
                }
              });
              console.log('valid --- '+validEC);
              if(!validEC) {
                $scope.showFailureAlert=true;
                $scope.failureTextAlert=msg;

                $timeout(function () {
                  $scope.showFailureAlert=false;
               }, 5000);
              } else {
                console.log('Saving the Contacts Info......');
                employeeSkillService.saveEmpContacts($scope.empContactsInfo).then(function (result) {
                  $scope.showSuccessAlert=true
                            $scope.isTrascError=true;
                  $scope.successTextAlert = appConstants.successMessage;
                  FlashService.Success(appConstants.successMessage);
                  $scope.searchCertificates();
                              $scope.enbleEdit = true;

                              $timeout(function () {
                                $scope.isTrascError=false;
                                $window.location.reload();

                             }, 2000);


                }).catch(function (error) {
                  var msg = appConstants.exceptionMessage;
                  if(error.data.httpStatus){ 
                     msg=error.data.errorMessage; 
                   }
                            $scope.showFailureAlert=true;
                            $scope.failureTextAlert=msg;

                            $timeout(function () {
                              $scope.showFailureAlert=false;
                           }, 2000);

                            $scope.selectedPrimPrefs="";
                  $scope.selectedSecPrefs="";
                });
              }
            } else {
              var msg = 'Please Fill Atleast One Mandatory Contact';
              $scope.showFailureAlert=true;
              $scope.failureTextAlert=msg;

              $timeout(function () {
                $scope.showFailureAlert=false;
             }, 2000);
            }
				/**/
			}



        $scope.clearContacts=function(){
        /*	$scope.empContactsInfo = [{"seq":"","name":"","relation":"","countryCode":"","mobileNumber":"","altrMobileNumber":""},{"seq":"","name":"","relation":"","countryCode":"","mobileNumber":"","altrMobileNumber":""}];
*/
        	window.location.href = "#/employeeInfo";
   		 $localStorage.isReturn = true;
			}
			$scope.operationsGenericFunction = function(doFunction, url,modelId,flag) {
				// passing selected operation url
				$scope.opeationsURL = url;
				if (doFunction === "Create") {
            $scope.isView = false;
					$scope.iseditable = true;
					$scope.disable_Edit = true;
					$scope.disable_View = true;
          $scope.osiUserCertificates={};
          $scope.headerName = 'Add';
					$scope.osiUser = {};
					$scope.isCertSelectedRow = "";
                    $scope.isSelectedRow = "";
          $scope.tempAttachment = {};
          $scope.preview.content = "";
          $scope.preview.name = '';
          $scope.issuedBy = '';
					createFunction(modelId);
				}

				if (doFunction === "Edit" && !$scope.disable_Edit) {
          $scope.headerName='Edit';
            $scope.isView = false;
                     if(flag == 'skills'){
                  //        $scope.getOsiSkillsList();
                      $scope.emp.skillName = $scope.osiUser.skillName;
                    		if($scope.osiUser.verified == 'true')
                          $scope.osiUser.isVerified = true;
                          else {
                            $scope.osiUser.isVerified = false;
                          }
                    }else{
                      $scope.getOsiCertificatesList();
                      $scope.issuedBy = $scope.osiUserCertificates.issuedBy;
                      $scope.emp.certificationName = $scope.osiUserCertificates.certificationName;
                          if($scope.osiUserCertificates.verified == "true")
                        	  $scope.osiUserCertificates.isVerified = true;
                          else
                        	  $scope.osiUserCertificates.isVerified =false;
                    }
					createFunction2(modelId);
				}
				if (doFunction === "View" && !$scope.disable_View) {

          $scope.iseditable = false;
          $scope.headerName='View';
          $scope.isView = true;
          if(flag == 'skills') {
          //  $scope.getOsiSkillsList();
              $scope.emp.skillName = $scope.osiUser.skillName;
          }

          else if(flag == 'certificate') {
            $scope.getOsiCertificatesList();
            $scope.emp.certificationName = $scope.osiUserCertificates.certificationName;
            $scope.issuedBy = $scope.osiUserCertificates.issuedBy;
          }
					/*if(flag == 'skills'){
                    	$scope.iseditable = false;
                        if($scope.osiUser.verified == "true")
                       	 	$scope.osiUser.isVerified = true;
                        else
                       	 $scope.osiUser.isVerified = false;
                   }else{
                	   $scope.iseditable = false;
                         if($scope.osiUserCertificates.verified == "true")
                       	  $scope.osiUserCertificates.isVerified = true;
                         else
                       	  $scope.osiUserCertificates.isVerified =false;
                   }*/
					createFunction2(modelId);
				}

			}
            $scope.editSelectedRowDetails =function(modelId,flag){
              $scope.headerName = 'Edit';
              $scope.isView = false;
              $scope.enableSaveButton = true;
              if(flag == 'certificate') {
                if($scope.isCertificateLoaded){
                  $scope.getOsiCertificatesList();
                  $scope.isCertificateLoaded = false;
                 }
              //console.log($scope.osiUserCertificates.certificationId);
              $scope.emp.certificationName = $scope.osiUserCertificates.certificationName;
              $scope.issuedBy = $scope.osiUserCertificates.issuedBy;
              var flagVal =true;
              angular.forEach($scope.osiCertificates, function(data,index){
                if($scope.osiUserCertificates.certificationId === data.certificationId){
                  flagVal = false;
                }

              });
              if(flagVal){
                  $scope.osiCertificates.push($scope.osiUserCertificates);
              }

            } else if(flag == 'skills'){
              $scope.helpEnabled = true;
              $scope.skillNotFound = false;
              if($scope.isSkillLoaded){
                $scope.getOsiSkillsList();
                $scope.isSkillLoaded = false;
               }
              $scope.emp.skillName = $scope.osiUser.skillName;
              var flagVal =true;
              angular.forEach($scope.osiSkills, function(data,index){
                if($scope.osiUser.skillId === data.skillId){
                  flagVal = false;
                }

              });
              if(flagVal){
                  $scope.osiSkills.push($scope.osiUser);
                 
              }
            }

            if(flag == 'skills'){
                //$scope.getOsiSkillsList();
               if($scope.osiUser.verified == 'true')
                 $scope.osiUser.isVerified = true;
                 else {
                   $scope.osiUser.isVerified = false;
                 }
           }else{
              //$scope.getOsiCertificatesList();
                 if($scope.osiUserCertificates.verified == "true")
                   $scope.osiUserCertificates.isVerified = true;
                 else
                   $scope.osiUserCertificates.isVerified =false;
           }
	           createFunction2(modelId);
            }

            function createFunction2(modelId) {

      				$('#'+modelId).modal({backdrop: 'static', keyboard: false});
      			}

			function createFunction(modelId) {
        if (modelId === "mySkillModal") {
         // $scope.getOsiSkillsList();
         if($scope.isSkillLoaded){
          $scope.getOsiSkillsList();
          $scope.isSkillLoaded = false;
         }
        } else {
          if($scope.isCertificateLoaded){
            $scope.getOsiCertificatesList();
            $scope.isCertificateLoaded = false;
           }
        }
				$('#'+modelId).modal({backdrop: 'static', keyboard: false});
			}
      $scope.isVerified = false;
      $scope.findSuperviosrId = function(empId) {
        employeeSkillService.findSuperviosrId(empId).then(function(result){
          $scope.supervisorId = result;
          var loggedInEmpId = $rootScope.globals.userDTO.id;
          if($scope.supervisorId == loggedInEmpId)
            $scope.isVerified = true;
          else
            $scope.isVerified = false;
        });
      }
      $scope.findSuperviosrId($localStorage.employeeId);
      $scope.init = function () {
       var  urlPath = $location.path();
       var funcId = "";
       if(urlPath.includes('certificationDetails-sf')){
          funcId = $rootScope.profileAcess.certificationFuncId;
       }else if(urlPath.includes('certificationDetails')){
          funcId = $rootScope.profileAcessHR.certificationFuncId;
       }else if(urlPath.includes('skillDetails-sf')){
          funcId = $rootScope.profileAcess.skillFuncId;
       }else if(urlPath.includes('skillDetails')){
          funcId = $rootScope.profileAcessHR.skillFuncId;
       }
          //AuthenticationService.availableOperations(18,urlPath);
          $http.get(
            configData.url + 'user-exl-operataions/'
            + funcId+'/'+$rootScope.globals.userDTO.id).success(function(response) {
            console.log('availOperations:- ' + response);
            //delete $localStorage.availOperations;
            //$localStorage.availOperations = response;
            $scope.availOperations = response;
        }).error(function(response) {
            console.log("error");
            $location.path('/error');
        });
        
        //$scope.availOperations = $localStorage.availOperations;
        console.log($scope.availOperations);


       if(urlPath.includes('certificationDetails-sf') || urlPath.includes('skillDetails-sf') || urlPath.includes('emergencyContacts-sf')) {
         if($rootScope.globals.userDTO!==undefined){
           var loggedInEmpId = $rootScope.globals.userDTO.id;
           if( $localStorage.employeeId == loggedInEmpId ) {
             $scope.iseditable = true; // TO-DO :: true if editable for self
             $scope.isVerified = false;
             //$scope.enbleEdit = false;
           } else {
             $scope.iseditable = false;
              //$scope.isVerified = true;
             //$scope.enbleEdit = true;
           }
         }
       } else {
         $scope.iseditable = true;
       }
       personalInfoService.getAllCountryList().then(function(data){

              $scope.countryCodeList = data;
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

     $scope.init();


     //Certificate attachment code

  $scope.preview = {};
	var file = {};
	$scope.load = loadFile;
	function loadFile(fileUpload) {
			$rootScope.isTrascErrorForPersonal = false;

			$rootScope.isTrascErrorForPersonal = false;
			if (fileUpload) {
        if (fileUpload && (fileUpload.type == 'image/png'
              || fileUpload.type == 'image/jpeg' || fileUpload.type == 'image/gif')) {
					if (fileUpload.size > 0 && fileUpload.size <= 5242880) {
						file.fileType = fileUpload.type;
						file.originalFileName = fileUpload.name;
						file.duplicateFileName = $scope.preview.duplicateFileName;
						file.attachmentId = $scope.preview.attachmentId;
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
									$scope.preview.content = fileUpload;
                  $scope.preview.name = fileUpload.name;
								console.log($scope.preview.content);
							}
						});

					} else {
						var msg = 'Image file size should be less than 5MB';
						$scope.preview = undefined;
						$scope.preview = {};
						$scope.showFailureAlertPopup=true;
            $scope.failureTextAlert = msg;
            $timeout(function(){
              $scope.showFailureAlertPopup=false;
            }, 3000);
					}
				} else {
					vm.preview= undefined;
					vm.preview = {};
					file = {};
					$scope.showFailureAlertPopup=true;
          $scope.failureTextAlert = 'Invalid File Format';
          $timeout(function(){
            $scope.showFailureAlertPopup=false;
          }, 3000);
				}
			}
		};

		$scope.downloadResume = function() {
      var dlnk = document.getElementById('resume');
      dlnk.setAttribute("download", $scope.tempAttachment.originalFileName);
      //document.getElementById('resume1').setAttribute("title","")
			dlnk.href = $scope.tempAttachment.fileContent;
			dlnk.click();
    };

    $scope.emp = {};
    $scope.enableSaveButton = true;
    $scope.setSkillId =  function(item)
    {
        $scope.osiUser.skillId = item.skillId;
        $scope.osiUser.skillDescription = item.skillDescription;
        $scope.skillNotFound=false;
        $scope.enableSaveButton = true;
        $scope.helpEnabled = true;
    }
    $scope.closeSkillLevels = function(){
      $('#skillLevels').hide();
    }

    $scope.getSkillLevels = function(){
      $('#skillLevels').modal({backdrop: 'static', keyboard: false});
    }

    $scope.getSkillGroup = function(searchString) {
      console.log(searchString);
      $scope.skillNotFound=false;
      var totalSkills = $scope.osiSkills;
      //var filtered = filterFilter(totalSkills, searchString);

      filtered = [];
      filtered.length = 0;
      totalSkills.forEach(function(skill) {
        var keepSearch = true;
        if (skill.skillName.toLowerCase().match(searchString.toLowerCase())) {
          filtered.push(skill);
        } else {
          skill.osiSkillTagsList.forEach(function(skillTag) {
            /*If you found one tag matched dont search*/
            if (keepSearch) {
              if (skillTag.tagName.toLowerCase().match(searchString.toLowerCase())) {
                filtered.push(skill);
                keepSearch = false;
              }else{
                $scope.skillNotFound=true;
                $scope.helpEnabled = false;
              }
            }
          });
        }

      });

   var results = _.chain(filtered).groupBy('groupId').each(function(item){
     console.log(item);
     return item;
   }).map(function(g){
     g[0].firstInGroup = true;  // the first item in each group
     var i= 0;
     g.forEach(function(gr){
       if (i != 0) {
         gr.firstInGroup = false;
       }
       i++;
     });
     return g;
   }).flatten().value();

      return results;
    }

    $scope.getSkillsData = function() {
      var totalSkills = $scope.osiSkills;
      if($('#skillId').val().length==0) {
        $scope.skillNotFound=false;
        $scope.helpEnabled = false;
    	}
      $scope.skillsData  = [];
      if($scope.emp.skillName != undefined && $scope.emp.skillName.length >=2) {

        angular.forEach(totalSkills, function(skil, key) {

          if(skil.skillName.toLowerCase().indexOf($scope.emp.skillName.toLowerCase()) != -1) {
            $scope.skillsData.push(skil);
          }
        });
        if(!$scope.skillsData.length) {
          //$scope.osiUser.skillId = undefined;
          $scope.skillNotFound=true;
          $scope.helpEnabled = false;
          $scope.moveInProgress = true;
          $scope.enableSaveButton = false;
        } else {
          $('#skillId').siblings('.dropdown-menu').show();
        }
      }
    }
    // remove the suggesstions drop down
    $scope.removeSuggestions = function(type) {
      if(type == 'skills'){
        $("#refferedBypopupModel").modal("hide");
        $scope.skillNotFound = false;
      } else if(type == 'certificates') {
        $("#refferedBypopupModel").modal("hide");
        $scope.certificationNotFound = false;
      }
      $scope.moveInProgress = true;
    }
    $scope.setCertificationId =  function(item)
    {
      $scope.osiUserCertificates.certificationId = item.certificationId;
        $("#refferedBypopupModel").modal("hide");
        $scope.certificationNotFound=false;
        $scope.enableSaveButton = true;
        $scope.issuedBy = item.issuedBy;
    }

    $scope.getCertificationGroup = function(searchString) {


      var totalCertifications = $scope.osiCertificates;
      //var filtered = filterFilter(totalSkills, searchString);
console.log(totalCertifications);
      filtered = [];
      filtered.length = 0;
      totalCertifications.forEach(function(certification) {
        var keepSearch = true;
        if (certification.certificationName.toLowerCase().match(searchString.toLowerCase())) {
          filtered.push(certification);
        } else {
          certification.osiCertificationTagsList.forEach(function(certificationTag) {
            /*If you found one tag matched dont search*/
            if (keepSearch) {
              if (certificationTag.tagName.toLowerCase().match(searchString.toLowerCase())) {
                filtered.push(certification);
                keepSearch = false;
              }
            }
          });
        }

      });

   var results = _.chain(filtered).groupBy('groupId').each(function(item){
     console.log(item);
     return item;
   }).map(function(g){
     g[0].firstInGroup = true;  // the first item in each group
     var i= 0;
     g.forEach(function(gr){
       if (i != 0) {
         gr.firstInGroup = false;
       }
       i++;
     });
     return g;
   }).flatten().value();

      return results;
    }

    $scope.getCertificationsData = function() {
      var totalCertifications = $scope.osiCertificates;
      if($('#certificationId').val().length==0) {
    		$scope.certificationNotFound=false;
    	}
      $scope.certificationsData  = [];
      if($scope.emp.certificationName != undefined && $scope.emp.certificationName.length >=2) {

        angular.forEach(totalCertifications, function(certificate, key) {
          //console.log(certificate);
          if(certificate.certificationName.toLowerCase().indexOf($scope.emp.certificationName.toLowerCase()) != -1) {
            $scope.certificationsData.push(certificate);
          }
        });
        if(!$scope.certificationsData.length) {
           $scope.certificationNotFound=true;
           //$scope.osiUserCertificates.certificationId = undefined;
           $scope.moveInProgress = true;
           $scope.enableSaveButton = false;
        } else {
          $('#certificationId').siblings('.dropdown-menu').show();
        }
      }
    }

    // $scope.verifiedSkillsList = [];
    // $scope.selectAll = function() {
    //   //alert($scope.totalVerified);
    //   console.log($scope.osiSkillSearchResult);
    //   if($scope.totalVerified) {
    //     $scope.verifiedSkillsList = [];
    //     angular.forEach($scope.osiSkillSearchResult, function(values, key) {
    //         values.verified = "true";
    //         var obj = {};
    //         obj[values.empSkillId] = true;
    //         $scope.verifiedSkillsList.push(obj);
    //     })
    //   } else {
    //     $scope.verifiedSkillsList = [];
    //     angular.forEach($scope.osiSkillSearchResult, function(values, key) {
    //       values.verified = "false";
    //       var obj = {};
    //       obj[values.empSkillId] = false;
    //       $scope.verifiedSkillsList.push(obj);
    //   })
    //   }
    //   console.log($scope.verifiedSkillsList);
    // };
    // $scope.select = function(flag, empskillid) {

    //   var obj = {};
    //   obj[empskillid] = flag;
    //   if($scope.verifiedSkillsList != undefined && $scope.verifiedSkillsList.length != 0) {
    //       if(_.findIndex($scope.verifiedSkillsList, empskillid) != -1) {
    //         $scope.verifiedSkillsList.splice(_.findIndex($scope.verifiedSkillsList, empskillid), 1);
    //         $scope.verifiedSkillsList.push(obj);
    //       } else {
    //         $scope.verifiedSkillsList.push(obj);
    //       }
    //     } else {
    //     $scope.verifiedSkillsList.push(obj);
    //   }
    //   console.log($scope.verifiedSkillsList);
    // }

    $scope.CheckUncheckAll = function () {
      for (var i = 0; i < $scope.osiSkillSearchResult.length; i++) {
          $scope.osiSkillSearchResult[i].isVerified = $scope.IsAllChecked;
      }
    };
    $scope.CheckUncheckHeader = function () {
      $scope.IsAllChecked = true;
      for (var i = 0; i < $scope.osiSkillSearchResult.length; i++) {
          if (!$scope.osiSkillSearchResult[i].isVerified) {
              $scope.IsAllChecked = false;
              break;
          }
      };
    };

    $scope.saveVerifiedSkills = function() {
      console.log($scope.osiSkillSearchResult);
      angular.forEach($scope.osiSkillSearchResult, function(value, key) {
        if(value.isVerified)
          value.verified = "true";
        else
          value.verified = "false";
      });
      employeeSkillService.updateVerifiedSkills($scope.osiSkillSearchResult).then(function (result) {
        $scope.showSuccessAlert=true;
        $scope.successTextAlert ="Selected Skill(s) are Verified successfully";
      //  $window.location.reload();
        $timeout(function(){
          $scope.showSuccessAlert=false;
          $('#mySkillModal').modal('hide');
        }, 2000);
      }).catch(function (result) {
        var msg = appConstants.exceptionMessage;
        if(result.data.httpStatus){ 
          msg=result.data.message; 
        }
        $scope.showFailureAlert=true;
        $scope.failureTextAlert = msg;

        $timeout(function(){
          $scope.showFailureAlert=false;

        }, 3000);
      });
    };

    $scope.CheckUncheckAllCert = function () {
      for (var i = 0; i < $scope.osiCertificateSearchResult.length; i++) {
          $scope.osiCertificateSearchResult[i].isVerified = $scope.IsAllCheckedCert;
      }
    };
    $scope.CheckUncheckHeaderCert = function () {
      $scope.IsAllCheckedCert = true;
      for (var i = 0; i < $scope.osiCertificateSearchResult.length; i++) {
          if (!$scope.osiCertificateSearchResult[i].isVerified) {
              $scope.IsAllCheckedCert = false;
              break;
          }
      };
    };

    $scope.saveVerifiedCert = function() {
      console.log($scope.osiCertificateSearchResult);
      angular.forEach($scope.osiCertificateSearchResult, function(value, key) {
        if(value.isVerified)
          value.verified = "true";
        else
          value.verified = "false";
      });
      employeeSkillService.updateVerifiedCert($scope.osiCertificateSearchResult).then(function (result) {
        $scope.showSuccessAlert=true;
        $scope.successTextAlert ="Selected Certification(s) are Verified successfully";;
        $window.location.reload();
        $timeout(function(){
          $scope.showSuccessAlert=false;
          //$('#mySkillModal').modal('hide');
        }, 2000);
      }).catch(function (result) {
        var msg = appConstants.exceptionMessage;
        if(result.data.httpStatus){ 
          msg=result.data.message; 
        }
        $scope.showFailureAlert=true;
        $scope.failureTextAlert = msg;

        $timeout(function(){
          $scope.showFailureAlert=false;

        }, 3000);
      });
    }
$scope.cancelProfile = function(){
		if($localStorage.currentEmployeeId!=$localStorage.loggedInEmployeeId)
			$window.location.href='#/employeeInfo-sf';
		else
		$window.location.href='#/profile';
  }
  $scope.backToEmployeeList = function(){
   
    if($rootScope.profileAcessHR.profile)
    $window.location.href='#/employeeInfo';
 else if($rootScope.profileAcessHR.profiles)
 $window.location.href='#/employeeInfos';
}
    $scope.changeMonths=function (){
      if($scope.osiUser.yearOfExperience != null && $scope.osiUser.yearOfExperience != ''){
        $scope.yearsOfExp = true ;
        $scope.osiUser.monthsOfExp = 0;
      }
      else{
        $scope.yearsOfExp = false ;
        $scope.osiUser.monthsOfExp = '';
      }

    }
}
]);
