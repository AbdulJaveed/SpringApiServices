(function () {
    'use strict';

    angular
            .module('myApp.employeesService', [])
            .factory('EmployeesService', EmployeesService);

    EmployeesService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function EmployeesService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiEmployees".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.searchOsiEmployeess = searchOsiEmployeess;
        service.overallsearch = overallsearch;
        return service;       
        
        function searchOsiEmployeess(data){
            return $http.post(configData.url+'v1/employees/searchEmployees', data,config).then(handleSuccess, handleError('Error getting OsiEmployees ')); 
        }

        function overallsearch(data){
            return $http.post(configData.url+'v1/employees/overallSearch', data,config).then(handleSuccess, handleError('Error getting OsiEmployees ')); 
        }
        
        function handleSuccess(res) {
            var deferred = $q.defer();
            if(res.data.errorCode) {
                   console.log(res.data.errorCode+' - '+res.data.errorMessage);
                   deferred.reject(res.data);
            } else {
                   deferred.resolve(res.data);
            }
            return deferred.promise;
        } 

        function handleError(error) { 
            return $q.reject(error); 
        } 


    }
})();