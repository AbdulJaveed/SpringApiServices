'use strict';
angular.module('myApp.logincontroller', [])
        .controller('LoginCtrl', ['$scope', '$rootScope', '$window', '$location', '$http', '$sessionStorage', '$localStorage',
                                  'AuthenticationService', '$timeout','FlashService','$cookieStore','appConstants',
        function ($scope, $rootScope, $window, $location, $http, $sessionStorage, $localStorage, AuthenticationService, $timeout,FlashService,$cookieStore,appConstants) {
                $scope.slide = '';
                $scope.loginData = {};
                $scope.oldPassword;
                $rootScope.back = function () {
                    $scope.slide = 'slide-right';
                    $window.history.back();
                }
                $rootScope.go = function (path) {
                    $scope.slide = 'slide-left';
                    $location.url(path);
                }
                $rootScope.isheader = false;
                console.log("login contoller");
                $scope.resetform =function()
                {
                   var vm=$scope.vm;
                   vm.username="";
                   vm.password="";
                }
                $scope.clear = function() {
                	$scope.currentPassword=""
                	$scope.password="";
                	$scope.confirmPassword="";
                };

                $scope.submitPassword = function() {
                	if($scope.oldPassword===$scope.currentPassword){
                		if($scope.password===$scope.confirmPassword){
                			if($scope.password!==$scope.oldPassword){
                				var user={
                						password:AuthenticationService.Base64Encode($scope.password),
                				}
                				AuthenticationService.updatePassword(user).then(function(updateResult){
                					$scope.handleSuccess(updateResult);
                				}).catch(function(){
                					$scope.handleError(appConstants.exceptionMessage);
                            		$scope.clear();
                				})
                			}else{
                        		$scope.handleError( "New Password Cannot Be Current Password");
                        		$scope.clear();
                        	}
                		}else{
                    		$scope.handleError("New Password and Confirm password mismatch");
                    		$scope.clear();
                    	}
                	}else{
                		$scope.handleError("Current Password is Wrong");
                		$scope.clear();
                	}
                };
                $scope.handleSuccess=function(updateResult){
                	if(updateResult){
						$rootScope.isTrascError = true;
						FlashService.Success("Password Changed Successfully");
						$timeout(function() {
							$rootScope.isTrascError = false;
						}, 5000);
						$rootScope.globals = {
								userDTO: {
									token: ""
								}
						};
						$cookieStore.put('globals', $rootScope.globals);
						$scope.resetform();
						$('#changePassword').modal('hide');

					}
                }

                $scope.handleError=function(msg){
                	$rootScope.isTrascError = true;
                    FlashService.Error(msg);
                    $timeout(function () {
                    	$rootScope.isTrascError = false;
                    }, 5000);
                }

                $scope.login = function () {

                //  var browser=get_browser();
                   var vm=$scope.vm;
                   $rootScope.isTrascError = false;
                   var username=vm.username;
                   var password=vm.password;

                   var continuesave = true;
                  /*  if( browser.name!="Chrome")
                  {
                     $rootScope.isTrascError = true;
                       FlashService.Error("Your browser is not supported .Please use Chrome,version 57 or above");
                       continuesave = false;
                  }
                 if( browser.name=="Chrome")
                  {
                      if(browser.version < 57){
                       $rootScope.isTrascError = true;
                       FlashService.Error("Your browser is not supported .Please use Chrome,version 57 or above.Your current browser version is "+browser.version+"");
                       continuesave = false;
                   }
                  }

                  // alert(sessionStorage.TABID);
                   if($cookieStore.get("LOGGEDINBROWSERID")!=undefined)
                   {

                        $rootScope.isTrascError = true;
                       FlashService.Error("User has already logged in.Please logout to re-login again");
                       continuesave = false;

                   }*/

                   if(username===null ||username===undefined ||username===""){
                       $rootScope.isTrascError = true;
                       FlashService.Error("User Name is required.");
                       continuesave = false;
                   }else if(username!=null && username.indexOf("@osius.com")===-1){
                	   $rootScope.isTrascError = true;
                       FlashService.Error("Please enter valid OSI Email ID");
                       continuesave = false;
                   }
                   else if(password===null ||password===undefined ||password===""){
                       $rootScope.isTrascError = true;
                       FlashService.Error("Password is required.");
                       continuesave = false;
                   }
                   if(continuesave){
		   AuthenticationService.Login(vm.username, vm.password, function (result) {
            	/*if((result.hasDefaultPwd===undefined || result.hasDefaultPwd===0) && !result.errorCode){
            		$scope.oldPassword=vm.password;
            		$('#changePassword').modal({backdrop: 'static', keyboard: false});
            	}else{*/
            	    if (result.errorMessage==null || result.errorMessage==undefined || result.errorMessage=="") {
            			console.log("result.errorMessage");
            			$cookieStore.put("LOGGEDINBROWSERID",sessionStorage.TABID);
            			AuthenticationService.SetToken(result);
            			delete $localStorage.error;
            			if(result.menuTree!=null){
            				
            				$sessionStorage.menuTree=JSON.parse(result.menuTree);
            				var isEmployee = false;
            				angular.forEach($sessionStorage.menuTree,function(menuItem){
            					angular.forEach(menuItem.children,function(children){
                					if(children.url === "/employeesList")
                						isEmployee = true;
                				});
            				});
            				if(isEmployee){
            					 $localStorage.iseditable = false;
            				}
            				
            			} else {
            				$sessionStorage.menuTree = [];
                        }
                        $sessionStorage.menuTree.push({title: "My Profile", functionId:'2323', url:"/profile"});
            			$sessionStorage.menuTree.push({title: "Logout", functionId:'2323', url:"/logout"});
            			$localStorage.employeeId = result.id;
            			$localStorage.empId = undefined;
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
            			$localStorage.loggedInDate = today;
            			$localStorage.searchByDate = today;
            			
            			//  $location.path('/home');
            			// $window.location.reload(true);
            			$scope.loggedIn = $rootScope.globals.userDTO;
            			$window.location.href='#/employeeChart';
                             
            			//$location.path('/dashboard');
            			$timeout(function() {
            				$window.location.reload(true);
            			}, 400);
            			
            			
            			$rootScope.isLogin=true;
            			
            			
            		} else {
            			console.log(result.errorMessage);
            			$rootScope.isTrascError = true;
            			FlashService.Error(result.errorMessage);
            			//vm.error = 'Username or password is incorrect';
            			vm.username="";
            			vm.password = "";
            			vm.loading = false;
            		}
            	//}
            });
           }
         };
          $scope.forgotPasswordMail=function(){
               var vm=$scope.vm;
                   $rootScope.isTrascError = false;
                   var username=vm.username;
                   if(username===null ||username===undefined ||username===""){
                       $rootScope.isTrascError = true;
                       FlashService.Error("User Name is required.");
                   }
            else{
                $rootScope.isTrascError=false;
                AuthenticationService.forgotPassword(vm.username, function (result) {
                    console.log("result.errorMessage"+result.errorMessage);
                    AuthenticationService.SetToken(result);
                 if (result.errorMessage===null || result.errorMessage==undefined || result.errorMessage=="") {
                     $rootScope.isTrascError = true;
                     FlashService.Success("An email has been sent to your registered email with reset password");
                }else{
                       $rootScope.isTrascError = true;
                       FlashService.Error(result.errorMessage);
                }
                $timeout(function () {
                        $rootScope.isTrascError = false;
                        }, 3000);

         });
        }
      };




    }]);

function get_browser() {
    var ua=navigator.userAgent,tem,M=ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
    if(/trident/i.test(M[1])){
        tem=/\brv[ :]+(\d+)/g.exec(ua) || [];
        return {name:'IE',version:(tem[1]||'')};
        }
    if(M[1]==='Chrome'){
        tem=ua.match(/\bOPR|Edge\/(\d+)/)
        if(tem!=null)   {return {name:'Opera', version:tem[1]};}
        }
    M=M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];
    if((tem=ua.match(/version\/(\d+)/i))!=null) {M.splice(1,1,tem[1]);}
    return {
      name: M[0],
      version: M[1]
    };
 }
