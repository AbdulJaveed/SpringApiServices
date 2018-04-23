(function () {
    'use strict';

    angular
            .module('myApp.OsiCertificationsService', [])
            .factory('OsiCertificationsService', OsiCertificationsService);

    OsiCertificationsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiCertificationsService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiCertifications".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getAllOsiCertificationss = getAllOsiCertificationss;
        service.saveOsiCertifications = saveOsiCertifications;
        service.updateOsiCertifications = updateOsiCertifications;
        service.getOsiCertifications = getOsiCertifications;
        service.searchOsiCertificationss = searchOsiCertificationss;
        
        return service;       
        
        function getAllOsiCertificationss() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiCertificationss',config).then(handleSuccess, handleError('Error getting OsiCertifications'));
        }
        
        function getOsiCertifications(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiCertifications/'+id,config).then(handleSuccess, handleError('Error getting OsiCertifications'));
        }

        function saveOsiCertifications(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiCertifications', data,config).then(handleSuccess, handleError('Error saving OsiCertificationss '));    
        }

        function updateOsiCertifications(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiCertifications', data,config).then(handleSuccess, handleError('Error saving OsiCertificationss '));   
        }
        function searchOsiCertificationss(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiCertifications', data,config).then(handleSuccess, handleError('Error getting OsiCertifications ')); 
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
