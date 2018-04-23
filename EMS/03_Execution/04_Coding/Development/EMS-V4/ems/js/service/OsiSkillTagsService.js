(function () {
    'use strict';

    angular
            .module('myApp.OsiSkillTagsService', [])
            .factory('OsiSkillTagsService', OsiSkillTagsService);

    OsiSkillTagsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiSkillTagsService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiSkillTags".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllOsiSkillTags = getAllOsiSkillTags;
        service.saveOsiSkillTags = saveOsiSkillTags;
        service.updateOsiSkillTags = updateOsiSkillTags;
        service.getOsiSkillTags = getOsiSkillTags;
        service.searchOsiSkillTags = searchOsiSkillTags;
        service.getActiveOsiSkillTags = getActiveOsiSkillTags;

        return service;

        function getAllOsiSkillTags() {
            return $http.get(configData.url+'osiskilltags/',config).then(handleSuccess, handleError('Error getting OsiSkillTags'));
        }

        function getOsiSkillTags(id) {
            return $http.get(configData.url+'osiskilltags/'+id,config).then(handleSuccess, handleError('Error getting OsiSkillTags'));
        }

        function saveOsiSkillTags(data) {
         return $http.post(configData.url+'osiskilltags/', data,config).then(handleSuccess, handleError('Error saving OsiSkillTags '));
        }

        function updateOsiSkillTags(data) {
            return $http.put(configData.url+'osiskilltags/'+data.tagId, data,config).then(handleSuccess, handleError('Error saving OsiSkillTags '));
        }
        function searchOsiSkillTags(data){
            return $http.post(configData.url+'osiskilltags/list', data,config).then(handleSuccess, handleError('Error getting OsiSkillTags '));
        }
        function getActiveOsiSkillTags() {
            return $http.get(configData.url+'osiskilltags/activelist',config).then(handleSuccess, handleError('Error getting OsiSkillTags'));
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
