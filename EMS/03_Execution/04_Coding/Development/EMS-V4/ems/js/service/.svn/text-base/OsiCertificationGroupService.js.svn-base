(function () {
    'use strict';

    angular
            .module('myApp.OsiCertificationGroupsService', [])
            .factory('OsiCertificationGroupsService', OsiCertificationGroupsService);

    OsiCertificationGroupsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiCertificationGroupsService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiCertificationGroups".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllOsiCertificationGroups = getAllOsiCertificationGroups;
        service.saveOsiCertificationGroups = saveOsiCertificationGroups;
        service.updateOsiCertificationGroups = updateOsiCertificationGroups;
        service.getOsiCertificationGroups = getOsiCertificationGroups;
        service.searchOsiCertificationGroups = searchOsiCertificationGroups;
        service.getAllActiveOsiCertificationGroups = getAllActiveOsiCertificationGroups;

        return service;

        function getAllOsiCertificationGroups() {
            return $http.get(configData.url+'osicertificationgroups/',config).then(handleSuccess, handleError('Error getting OsiCertificationGroups'));
        }

        function getOsiCertificationGroups(id) {
            return $http.get(configData.url+'osicertificationgroups/'+id,config).then(handleSuccess, handleError('Error getting OsiCertificationGroups'));
        }

        function saveOsiCertificationGroups(data) {
         return $http.post(configData.url+'osicertificationgroups/', data,config).then(handleSuccess, handleError('Error saving OsiCertificationGroups '));
        }

        function updateOsiCertificationGroups(data) {
            return $http.put(configData.url+'osicertificationgroups/'+data.groupId, data,config).then(handleSuccess, handleError('Error saving OsiCertificationGroups '));
        }
        function searchOsiCertificationGroups(data){
            return $http.post(configData.url+'osicertificationgroups/list', data,config).then(handleSuccess, handleError('Error getting OsiCertificationGroups '));
        }
        function getAllActiveOsiCertificationGroups() {
            return $http.get(configData.url+'osicertificationgroups/activelist',config).then(handleSuccess, handleError('Error getting OsiCertificationGroups'));
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
