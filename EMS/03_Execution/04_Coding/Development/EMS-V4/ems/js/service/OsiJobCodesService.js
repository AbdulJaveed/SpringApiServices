(function () {
    'use strict';

    angular
            .module('myApp.OsiJobCodesService', [])
            .factory('OsiJobCodesService', OsiJobCodesService);

    OsiJobCodesService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiJobCodesService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiJobCodes".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getAllOsiJobCodess = getAllOsiJobCodess;
        service.saveOsiJobCodes = saveOsiJobCodes;
        service.updateOsiJobCodes = updateOsiJobCodes;
        service.getOsiJobCodes = getOsiJobCodes;
        service.searchOsiJobCodess = searchOsiJobCodess;
        service.getAllorganizations=getAllorganizations;
        
        return service;       
        
        function getAllOsiJobCodess() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiJobCodess',config).then(handleSuccess, handleError('Error getting OsiJobCodes'));
        }
        
        function getOsiJobCodes(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiJobCodes/'+id,config).then(handleSuccess, handleError('Error getting OsiJobCodes'));
        }

        function saveOsiJobCodes(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiJobCodes', data,config).then(handleSuccess, handleError('Error saving OsiJobCodess '));    
        }

        function updateOsiJobCodes(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiJobCodes', data,config).then(handleSuccess, handleError('Error saving OsiJobCodess '));   
        }
        function searchOsiJobCodess(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiJobCodes', data,config).then(handleSuccess, handleError('Error getting OsiJobCodes ')); 
        } 
        function getAllorganizations(){
            return $http.get(configData.url+'orgnaizations',config).then(handleSuccess, handleError('Error getting all orgnaizations'));
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
