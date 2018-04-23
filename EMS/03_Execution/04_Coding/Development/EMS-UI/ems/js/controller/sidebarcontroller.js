'use strict';
angular.module('myApp.sidebarCtrl', []).controller('sidebarCtrl',
		['$scope', '$rootScope', '$window', '$location', '$http', '$sessionStorage', 'AuthenticationService','$localStorage',
				function($scope, $rootScope, $window, $location, $http, $sessionStorage, AuthenticationService,$localStorage) {
					$scope.slide = '';
					$scope.loginData = {};
					$rootScope.back = function() {
						$scope.slide = 'slide-right';
						$window.history.back();
					}
					$rootScope.go = function(path) {
						$scope.slide = 'slide-left';
						$location.url(path);
					}

					if ($rootScope.isLogin == true) {
						$sessionStorage.Login = true;
					}

					if ($sessionStorage.Login) {
						$rootScope.isLogin = true;
					} else {
						$rootScope.isLogin = false;
					}
					console.log("sidebar contoller:- " + $rootScope.isLogin);
					console.log("$sessionStorage.menuTree:- " +$sessionStorage.menuTree);
					delete $scope.basicTree;
					if ($rootScope.isLogin) {
						$scope.basicTree = $sessionStorage.menuTree;
					}
					$scope.functionNavigation = function(functionId, url) {
						$localStorage.functionId = functionId;
						console.log("functionId:- " + functionId + " && url:- " +url);
					/*	if($localStorage.employeeId== undefined &&
                  ((url==='/personalinfo') || (url==='/officeInfo')
                  || (url==='/medicalInfo') || (url==='/emergencyContacts')
                  || (url==='/skillDetails') || (url==='/certificationDetails')
                  || (url==='/passportInfo') || (url==='/empBankList')
                  || (url==='/employeeAssignments')
                  || (url==='/personalinfo-sf') || (url==='/officeInfo-sf')
                  || (url==='/medicalInfo-sf') || (url==='/emergencyContacts-sf')
                  || (url==='/skillDetails-sf') || (url==='/certificationDetails-sf')
                  || (url==='/passportInfo-sf') || (url==='/empBankList-sf')
                  || (url==='/employeeAssignments-sf')
								)) {
							 $('#alertEmployee').modal({show: true, backdrop: 'static'});
						}else {*/

          if(url!==undefined){
              AuthenticationService.availableOperations(functionId, url);
              $location.path(url);
          }
						/*}*/
					}

          $scope.reportGroupIdStorage = function(rptGrpId) {
					console.log("rptGrpId:- " + rptGrpId);
          $localStorage.rptGrpId = rptGrpId;
          console.log("$localStorage.rptGrpId - "+$localStorage.rptGrpId);
          return true;
					}
					/*$scope.selectNodeLabel = function(node, $event) {
						var url = node.url;
						var functionId = node.functionId;
						var isFolder = node.isFolder;
						if (!isFolder) {
							console.log("functionId:- " + functionId + " && url:- " +url);
			    			AuthenticationService.availableOperations(functionId, url);
							$location.path(url);
						}
					}*/
				}]);
