(function () {
    'use strict';

    angular
            .module('myApp.employeesServiceSelf', [])
            .factory('EmployeesServiceSelf', EmployeesServiceSelf);

    EmployeesServiceSelf.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function EmployeesServiceSelf($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiEmployees".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.searchOsiEmployeess = searchOsiEmployeess;
        return service;

        function searchOsiEmployeess(data){
            return $http.post(configData.url+'v1/employees//searchEmployees-self', data,config).then(handleSuccess, handleError('Error getting OsiEmployees '));
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
