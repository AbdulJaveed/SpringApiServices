(function () {
    'use strict';

    angular
            .module('myApp.OsiSkillGroupsService', [])
            .factory('OsiSkillGroupsService', OsiSkillGroupsService);

    OsiSkillGroupsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiSkillGroupsService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiSkillGroups".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllOsiSkillGroups = getAllOsiSkillGroups;
        service.saveOsiSkillGroups = saveOsiSkillGroups;
        service.updateOsiSkillGroups = updateOsiSkillGroups;
        service.getOsiSkillGroups = getOsiSkillGroups;
        service.searchOsiSkillGroups = searchOsiSkillGroups;
        service.getAllActiveOsiSkillGroups = getAllActiveOsiSkillGroups;
        return service;

        function getAllOsiSkillGroups() {
            return $http.get(configData.url+'osiskillgroups/',config).then(handleSuccess, handleError('Error getting OsiSkillGroups'));
        }

        function getOsiSkillGroups(id) {
            return $http.get(configData.url+'osiskillgroups/'+id,config).then(handleSuccess, handleError('Error getting OsiSkillGroups'));
        }

        function saveOsiSkillGroups(data) {
         return $http.post(configData.url+'osiskillgroups/', data,config).then(handleSuccess, handleError('Error saving OsiSkillGroups '));
        }

        function updateOsiSkillGroups(data) {
            return $http.put(configData.url+'osiskillgroups/'+data.groupId, data,config).then(handleSuccess, handleError('Error saving OsiSkillGroups '));
        }
        function searchOsiSkillGroups(data){
            return $http.post(configData.url+'osiskillgroups/list', data,config).then(handleSuccess, handleError('Error getting OsiSkillGroups '));
        }
        function getAllActiveOsiSkillGroups() {
            return $http.get(configData.url+'osiskillgroups/activelist',config).then(handleSuccess, handleError('Error getting OsiSkillGroups'));
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
