(function () {
    'use strict';

    angular
            .module('myApp.OsiSkilsService', [])
            .factory('OsiSkilsService', OsiSkilsService);

    OsiSkilsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiSkilsService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiSkils".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getAllOsiSkilss = getAllOsiSkilss;
        service.saveOsiSkils = saveOsiSkils;
        service.updateOsiSkils = updateOsiSkils;
        service.getOsiSkils = getOsiSkils;
        service.searchOsiSkilss = searchOsiSkilss;
        
        return service;       
        
        function getAllOsiSkilss() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiSkilss',config).then(handleSuccess, handleError('Error getting OsiSkils'));
        }
        
        function getOsiSkils(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiSkils/'+id,config).then(handleSuccess, handleError('Error getting OsiSkils'));
        }

        function saveOsiSkils(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiSkils', data,config).then(handleSuccess, handleError('Error saving OsiSkilss '));    
        }

        function updateOsiSkils(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiSkils', data,config).then(handleSuccess, handleError('Error saving OsiSkilss '));   
        }
        function searchOsiSkilss(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiSkils', data,config).then(handleSuccess, handleError('Error getting OsiSkils ')); 
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
