(function () {
    'use strict';

    angular
            .module('myApp.OsiCertificationTagsService', [])
            .factory('OsiCertificationTagsService', OsiCertificationTagsService);

    OsiCertificationTagsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiCertificationTagsService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiCertificationTags".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllOsiCertificationTags = getAllOsiCertificationTags;
        service.saveOsiCertificationTags = saveOsiCertificationTags;
        service.updateOsiCertificationTags = updateOsiCertificationTags;
        service.getOsiCertificationTags = getOsiCertificationTags;
        service.searchOsiCertificationTags = searchOsiCertificationTags;
        service.getAllActiveOsiCertificationTags = getAllActiveOsiCertificationTags;
        return service;

        function getAllOsiCertificationTags() {
            return $http.get(configData.url+'osicertificationtags/',config).then(handleSuccess, handleError('Error getting OsiCertificationTags'));
        }

        function getOsiCertificationTags(id) {
            return $http.get(configData.url+'osicertificationtags/'+id,config).then(handleSuccess, handleError('Error getting OsiCertificationTags'));
        }

        function saveOsiCertificationTags(data) {
         return $http.post(configData.url+'osicertificationtags/', data,config).then(handleSuccess, handleError('Error saving OsiCertificationTags '));
        }

        function updateOsiCertificationTags(data) {
            return $http.put(configData.url+'osicertificationtags/'+data.tagId, data,config).then(handleSuccess, handleError('Error saving OsiCertificationTags '));
        }
        function searchOsiCertificationTags(data){
            return $http.post(configData.url+'osicertificationtags/list', data,config).then(handleSuccess, handleError('Error getting OsiCertificationTags '));
        }
        function getAllActiveOsiCertificationTags() {
            return $http.get(configData.url+'osicertificationtags/activelist',config).then(handleSuccess, handleError('Error getting OsiCertificationTags'));
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
