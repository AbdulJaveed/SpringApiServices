(function () {
    'use strict';

    angular
            .module('myApp.LookupService', [])
            .factory('LookupService', LookupService);

    LookupService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function LookupService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getAllLookups = getAllLookups;
        service.saveLookups = saveLookups;
        service.updateLookups = updateLookups;
        service.searchLookups = searchLookups;
        service.isInactivateLookup=isInactivateLookup;
        service.getAllActiveLookups=getAllActiveLookups;
        service.getLookupByLookupName = getLookupByLookupName;
        return service;       

        function getAllLookups() {
            return $http.get(configData.url+'getLookup',config).then(handleSuccess, handleError('Error getting lookups'));
        }
        function getAllActiveLookups() {
            return $http.get(configData.url+'getActiveLookups',config).then(handleSuccess, handleError('Error getting Active lookups'));
        }

        function saveLookups(data) {
         return $http.post(configData.url+'createLookup', data,config).then(handleSuccess, handleError('Error saving lookups '));    
        }

        function updateLookups(data) {
            return $http.put(configData.url+'editLookup', data,config).then(handleSuccess, handleError('Error saving lookups '));   
        }
        function searchLookups(data){
            return $http.post(configData.url+'searchLookup', data,config).then(handleSuccess, handleError('Error saving lookups ')); 
        }
        function isInactivateLookup(lookupId) {
            return $http.get(configData.url+'lookup-status-flag/'+lookupId,config).then(handleSuccess, handleError('Error getting lookups'));
        }
        function getLookupByLookupName(lookupName) {
            return $http.get(configData.url+'getLookupByLookupName/'+lookupName,config).then(handleSuccess, handleError('Error getting lookups'));
        }
       /* function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {success: false, message: error};
            };
        }*/
        
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