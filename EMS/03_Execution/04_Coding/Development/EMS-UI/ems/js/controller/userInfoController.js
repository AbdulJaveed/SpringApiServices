'use strict';
 angular.module('myApp.userInfoController', [])
        .controller('userInfoController', [
    '$scope','appConstants','SharedDataService','userService','$cookieStore',
    '$timeout','$rootScope', '$window', '$location', '$http', '$sessionStorage',
    'CommonService','FlashService', 'MenuService', 'AuthenticationService',
function ($scope,appConstants,SharedDataService,userService,$cookieStore,$timeout, 
    $rootScope, $window, $location, $http, $sessionStorage, CommonService, 
    FlashService, MenuService, AuthenticationService) {
            
		 var vm=this;
		 vm.today=new Date().toDateString();
		 vm.nextButton=true;
		 vm.duplicateNameCount=0;
		 vm.duplicateempIdCount=0;
		 vm.submitted = false;
		 vm.temPassword=""; 
		 vm.focusOnDate = true;
		 var output = document.getElementById('output');;
		 $rootScope.passwordMismatch= false;
//		 $rootScope.isTrascError = false;
		 vm.isFileError=false;
		 $rootScope.confirmationHeading="";
	    	vm.userUpdate=false;
	    	var dateError=0;
	    	$scope.validUser = false;
         $rootScope.isPersonalInfoDataModified= false;

         vm.deselectTabs=function(){
             $rootScope.tab1="";
             $rootScope.tab2="";
             $rootScope.tab3="";
             $rootScope.tab4="";
             $rootScope.tab5="";
             $rootScope.tab6="";
             $rootScope.personalInfoActive="";
         };
         
		 $rootScope.$on('customEvent', function(event, user,userUpdate,userView) {
			 	 output = document.getElementById('output');
			 	 output.src = 'img/profilePic.jpg';
			     vm.userInfo={};
			     vm.confirmPassword="";
                             //vm.osiUser.$setPristine();
                             if(vm.osiUser!= undefined){
                                 vm.osiUser.$setPristine();
                             }
			     vm.userInfo=user;
			     
			     if(vm.userInfo.osiAttachmentses) {
			    	 if(vm.userInfo.osiAttachmentses.length > 0) {
			    		 output.src = "data:image/jpg;base64,"+vm.userInfo.osiAttachmentses[0].attachment; 
			    	 }
                                 else {
                                    //output.src = "http://arswiki.info/twiki/pub/Main/UserProfileHeader/default-user-profile.jpg";
                                    output.src = 'img/profilePic.jpg';
                                }
			     } else {
                                //output.src = "http://arswiki.info/twiki/pub/Main/UserProfileHeader/default-user-profile.jpg";
                                output.src = 'img/profilePic.jpg';
                             }
		    	 vm.confirmPassword = vm.userInfo.password;
		    	 vm.temPassword=vm.userInfo.password;
		    	 vm.userUpdate=userUpdate;
		    	 vm.infoView=userView;
		    	 if(vm.userUpdate == false && vm.infoView == false)
		    		 vm.userInfo.startDate = '';
		    	 vm.submitted = false;
		  });
		 $scope.$watch('vm.osiUser.$invalid', function() {
			 $rootScope.inValidUser = vm.osiUser.$invalid;
			
		 }, true);
		 
		 $scope.$watch('vm.userInfo', function() {
			 if (vm.osiUser.$dirty){
				 $rootScope.confirmationHeading=appConstants.personalInfo;
				 $rootScope.isPersonalInfoDataModified= true;
			 }
			
		 }, true);
		 
		 $rootScope.$on('openChangesEvent', function(event,rootEvent) 
		 {
                    //vm.osiUser.$setPristine();
		    	  if(vm.osiUser!= undefined){
                                 vm.osiUser.$setPristine();
                             }
		 });		   
		 
		 $scope.$watch('vm.confirmPassword', function() {
			 if(vm.userInfo!= undefined){
				 if(vm.userInfo.password != vm.confirmPassword) {
					 $rootScope.passwordMismatch= true;
				 }else{
					 $rootScope.passwordMismatch= false;
				 }
			 }
		 }, true);
		 vm.next=function(){
			 if(vm.userInfo.id){
				 angular.forEach($rootScope.userdata, function(value,key){
						if(value.id== vm.userInfo.id){
							$rootScope.userdata.splice(key, 1);
						}
					});
			 }
			 vm.isValid(vm.userInfo);
			$rootScope.inValidUser = vm.osiUser.$invalid;
                        dateError=0;
                        isInvalidDate();
			 if(vm.osiUser.$invalid) {
				 $rootScope.isTrascError = true;
		            FlashService.Error("Please fill required fields...");
		            $timeout(function () {
		            	$rootScope.isTrascError=false;
                            }, 5000);
			 }else
			if(vm.userInfo.password != vm.confirmPassword) {
				 $rootScope.isTrascError = true;
		            FlashService.Error("Password mismatch");
		            $timeout(function () {
		            	$rootScope.isTrascError=false;
                            }, 5000);
			}
                        else if (dateError == 1) {
                            $rootScope.isTrascError = true;
                            FlashService.Error("Invalid Date");
                            $timeout(function() {
                                    $rootScope.isTrascError = false;
                            }, 3000);
                        }
			 else {
				 if($scope.validUser){
				 //vm.userInfo.startDate = CommonService.getDBCompatibleDate(vm.userInfo.startDate);
				 //vm.userInfo.endDate = CommonService.getDBCompatibleDate(vm.userInfo.endDate);
				 SharedDataService.setUserInfo(vm.userInfo); 
				 $rootScope.isPersonalInfoDataModified= false;
                 vm.osiUser.$setPristine();
				 $timeout(function() {
					 angular.element('[data-target="#userResp-tab"]').tab('show');
					 vm.deselectTabs();
                     $rootScope.tab2="selectedInfoTab";
				 });
			 }
			 }              
		 }
		 /*function getFormattedDate(dateStr) {
		    	if(dateStr==null || dateStr == undefined)
		    		return null;
		    	else {
			    	var dArr = dateStr.split("-");  
			    	return dArr[2]+ "-" + dArr[0] + "-" + dArr[1];
		    	}
		    }*/
                function isInvalidDate(){
                    if((((vm.userInfo.startDate != undefined) && (vm.userInfo.startDate !="")) && (new Date(vm.userInfo.startDate).isValid() == false)) ||
                                         (((vm.userInfo.endDate != undefined) && (vm.userInfo.endDate !="" ))  && (new Date(vm.userInfo.endDate).isValid() == false))){
                            dateError=1;
                    }
                }
                
                Date.prototype.isValid = function () {
                    // An invalid date object returns NaN for getTime() and NaN is the only
                    // object not strictly equal to itself.
                    return this.getTime() === this.getTime();
                };
	 
		 var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
		 
		 var file=null;
	     vm.formData=undefined;
	        
	     $scope.upload=function(files) {
	    	 if(files[0].name.toLowerCase().includes(".png") || files[0].name.toLowerCase().includes(".jpg") || files[0].name.toLowerCase().includes(".jpeg") || files[0].name.toLowerCase().includes(".jpeg/jfif") || files[0].name.toLowerCase().includes(".gif") || files[0].name.toLowerCase().includes(".bmp")){
	    		 var reader = new FileReader();
	    		 reader.onload = function(){
	    			 var output = document.getElementById('output');
	    			 output.src = reader.result;
	    		 };
	    		 reader.readAsDataURL(event.target.files[0]);
	    		 if (files && ( files.length==1 )) { 
	    			 for (var i = 0; i < files.length;i++) { 
	    				 var uploadfile = files[0]; 
	    			 }
	    			 
	    		 } 
	    		 SharedDataService.setInfo("uploadfile",uploadfile);
	    	 }else{
	    		 output.src = 'img/profilePic.jpg';
	    		 vm.isFileError = true;
	    		 $scope.$digest();
	    		/* $rootScope.isTrascError = true;
	    		 $rootScope.$digest();
                 FlashService.Error("Please Select Image File Only");*/
                 $timeout(function() {
                	 vm.isFileError = false;
//                	 $rootScope.isTrascError = false;
	     		 }, 5000);
	    	 }
	  	  };  
	      
           //This event is handled to save the changes user done. Event emmited from userlist controller.
          $rootScope.$on('saveChangesEvent', function(event,rootEvent,tabname) 
          {
        	  			if(tabname==appConstants.tab1){
        	  				vm.deselectTabs();
        	  				$rootScope.tab1="selectedInfoTab";
                                                if(vm.osiUser!=undefined){
                                                   if (vm.osiUser.$dirty)
                                                    {
        	  					rootEvent.stopPropagation();
                                                        $rootScope.confirmationHeading=appConstants.personalInfo;
                                                        $rootScope.isPersonalInfoDataModified= true;
        	  					$rootScope.showTabSwitchModal();
        	  					//alert("You have unsaved changes in personal info , please save changes before moving");
                                                    }else{
                                                        $rootScope.isPersonalInfoDataModified= false;
                                                    } 
                                                }
        	  				
        	  			}
          });
          vm.startDateError= false;
          vm.checkDate = function() {
            	if(vm.userInfo.startDate == ""){
            		vm.userInfo.endDate = "";
            	}
            	if(vm.userInfo.startDate == undefined){
            		 $rootScope.isTrascError = true;
                     FlashService.Error("Please enter a valid Start Date");
                     $timeout(function() {
  	     				$rootScope.isTrascError = false;
  	     			}, 3000);
  	              vm.userInfo.startDate = "";
            	}
              if((new Date(vm.userInfo.startDate).setHours(0,0,0,0)) < (new Date().setHours(0,0,0,0))){
            	  $rootScope.isTrascError = true;
                  FlashService.Error("Start date cannot be less than today's date");
                  $timeout(function () {
                      $rootScope.isTrascError = false;
                  }, 3000);
                  vm.userInfo.startDate = "";
              }
              vm.compareDate();
            }
          
          vm.checkEndDate = function() {
        	  if(vm.userInfo.endDate == undefined){
 	      		 $rootScope.isTrascError = true;
 	               FlashService.Error("Please enter a valid End Date");
 	               $timeout(function() {
 	     				$rootScope.isTrascError = false;
 	     			}, 3000);
 	              vm.userInfo.endDate = "";
 	      	}
        	  vm.compareDate();        	  
          }
          
          vm.compareDate = function() {
              if (vm.userInfo.endDate != null && ((new Date(vm.userInfo.startDate).getTime()) >= (new Date(vm.userInfo.endDate).getTime()))) {
                 $rootScope.isTrascError = true;
                 FlashService.Error("End date should be greater than start date");
                 $timeout(function () {
                     $rootScope.isTrascError = false;
                 }, 3000);
                 vm.userInfo.endDate = "";
            }
          }
          vm.nextPage = function(){
        	  angular.element('[data-target="#userResp-tab"]').tab('show');
        	  vm.deselectTabs();
              $rootScope.tab2="selectedInfoTab";
          }
      	vm.isDuplicateName=function(name){
            vm.duplicateNameCount=0;
            vm.duplicate=false;
            angular.forEach($rootScope.userdata, function(user){
                                if(user.userName!=undefined && name!= undefined){
                                    if(name.toUpperCase() == user.userName.toUpperCase()){
                                     vm.duplicate=true;
                                     vm.duplicateNameCount++;
                 		}
                                }
                 		
     			}); 
            return  vm.duplicate;         
        };
        vm.isDuplicateEmpId=function(empId){
            vm.duplicateempIdCount=0;
            vm.duplicate=false;
            angular.forEach($rootScope.userdata, function(user){
                 		if(empId==user.empNumber){
                                     vm.duplicate=true;
                                     vm.duplicateempIdCount++;
                 		}
     			}); 
            return  vm.duplicate;         
        };

        vm.isDuplicateEmail= function(email){
        	vm.duplicateEmailIdCount=0;
            vm.duplicate=false;
            angular.forEach($rootScope.userdata, function(user){
         		if(email==user.emailId){
                             vm.duplicate=true;
                             vm.duplicateEmailIdCount++;
         		}
    		}); 
            	return  vm.duplicate;
        }
    	vm.isValid = function(user) {
                    if ((user.endDate != null) && (new Date(user.startDate).getTime() > new Date(user.endDate).getTime())) {
                        $scope.validUser = false;
                        $rootScope.isTrascError = true;
                        FlashService.Error("Start Date is greater than End Date in User Info");
    		} else {
    			vm.isDuplicateName(user.userName);
    			vm.isDuplicateEmpId(user.empNumber);
    			vm.isDuplicateEmail(user.emailId);
    			if (vm.duplicateempIdCount > 0 || vm.duplicateNameCount > 0 || vm.duplicateEmailIdCount > 0) {
    				if (vm.duplicateempIdCount > 0) {
    					$scope.validUser = false;
    					$rootScope.isTrascError = true;
    		            FlashService.Error("Duplicate Emp No.");
    				}
    				if (vm.duplicateNameCount > 0) {
    					$scope.validUser = false;
    					$rootScope.isTrascError = true;
    		            FlashService.Error("Duplicate User Name");
    				}
    				if(vm.duplicateEmailIdCount > 0){
    					$scope.validUser = false;
    					$rootScope.isTrascError = true;
    		            FlashService.Error("Duplicate Email Id");
    				}
    			} else {
    				$scope.validUser = true;
    			}
    		}
    		$timeout(function () {
            	$rootScope.isTrascError=false;
            }, 3000);
    	};
        
        vm.reset = function(){
            var osiConfigParametersDTO = {'configName':'DEFAULT_PASSWORD'};
            userService.getUsersDefaultPassword(osiConfigParametersDTO).then(function (data) {
                var encodedPassword = data.configValue;
                var decodedPassword = AuthenticationService.Base64Decode(encodedPassword);
                var osiUserDTO ={'id':vm.userInfo.id, 'emailId':vm.userInfo.emailId, 'password':encodedPassword };
                userService.resetUserPassword(decodedPassword, osiUserDTO).then(function (data1) {
                   // console.log("RESET DATA========="+JSON.stringify(data1));
                    var msg = "User password reset succesfully";
                    $rootScope.isTrascError = true;
                    FlashService.Success(msg);
                    $scope.successTextAlert =msg;        	
                    $timeout(function () {
                        $rootScope.isTrascError = false;
                        $window.location.reload();
                    }, 5000);
                });
            }).catch(function(error){
            	var msg = appConstants.exceptionMessage;
       		  if(error.data.httpStatus){ 
       			  msg=error.data.errorMessage; 
       		  }
                $rootScope.isTrascError = true;
                FlashService.Error(msg);
                $scope.failureTextAlert =msg;        	
                $timeout(function () {
                 $scope.failureTextAlert = false;
                 $window.location.reload();
                }, 5000);
            });
        };

      }]); 
 