(function () {
    'use strict';

    angular
            .module('myApp.OsiEmployeesService', [])
            .factory('OsiEmployeesService', OsiEmployeesService);

    OsiEmployeesService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiEmployeesService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiEmployees".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getAllOsiEmployeess = getAllOsiEmployeess;
        service.saveOsiEmployees = saveOsiEmployees;
        service.updateOsiEmployees = updateOsiEmployees;
        service.getOsiEmployees = getOsiEmployees;
        service.searchOsiEmployeess = searchOsiEmployeess;
        service.getorgNamesByStr = getorgNamesByStr;
service.getorgNameById = getorgNameById;

        return service;       
        
        function getAllOsiEmployeess() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiEmployeess',config).then(handleSuccess, handleError('Error getting OsiEmployees'));
        }
        
        function getOsiEmployees(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiEmployees/'+id,config).then(handleSuccess, handleError('Error getting OsiEmployees'));
        }

        function saveOsiEmployees(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiEmployees', data,config).then(handleSuccess, handleError('Error saving OsiEmployeess '));    
        }

        function updateOsiEmployees(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiEmployees', data,config).then(handleSuccess, handleError('Error saving OsiEmployeess '));   
        }
        function searchOsiEmployeess(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiEmployees', data,config).then(handleSuccess, handleError('Error getting OsiEmployees ')); 
        } 
        
         function getorgNameById(id) { return $http.get(configData.url+'crud/'+domain+'/getDomain/'+id,config).then(handleSuccess, handleError('Error getting Organization')); }
 function getorgNamesByStr(data) { return $http.post(configData.url+'search/'+domain+'/orgname/searchOrgName/',data,config).then(handleSuccess, handleError('Error getting Organization')); }

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
