/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
'use strict';
 angular.module('myApp.FlexiFieldController', [])
        .controller('FlexiFieldController', [
    '$scope','appConstants','FlexiFieldService','$cookieStore',
    '$timeout','$rootScope', '$window', '$location', '$http', '$sessionStorage',
    'CommonService','FlashService', 'MenuService', 'AuthenticationService',
function ($scope,appConstants,FlexiFieldService,$cookieStore,$timeout, 
    $rootScope, $window, $location, $http, $sessionStorage, CommonService, 
    FlashService, MenuService, AuthenticationService) {
        
       var vm=this;
        
       $scope.orgId = $rootScope.orgId;
       $scope.flexiFieldList = [];
       $scope.flexiName = 'EMPLOYEES';
       
       console.log('INSIDE FLEXI FIELDS...');
       
       var getFlexiFields = function() {
          FlexiFieldService.GetFlexiFields($scope.flexiName,1).then(function(data) {
             console.log(data);
              if(data !== null) {
                $scope.flexiFieldList = data;
             }
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
       
       $scope.executeScript = function(data) {
           eval(data);
       }
       getFlexiFields();
}]); 
 

