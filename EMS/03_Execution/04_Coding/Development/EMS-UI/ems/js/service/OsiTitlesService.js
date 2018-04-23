(function () {
    'use strict';

    angular
            .module('myApp.OsiTitlesService', [])
            .factory('OsiTitlesService', OsiTitlesService);

    OsiTitlesService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiTitlesService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiTitles".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getAllOsiTitless = getAllOsiTitless;
        service.saveOsiTitles = saveOsiTitles;
        service.updateOsiTitles = updateOsiTitles;
        service.getOsiTitles = getOsiTitles;
        service.searchOsiTitless = searchOsiTitless;
      /*  service.getAllorganizations=getAllorganizations;*/
        
        return service;       
        
        function getAllOsiTitless() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiTitless',config).then(handleSuccess, handleError('Error getting OsiTitles'));
        }
        
        function getOsiTitles(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiTitles/'+id,config).then(handleSuccess, handleError('Error getting OsiTitles'));
        }

        function saveOsiTitles(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiTitles', data,config).then(handleSuccess, handleError('Error saving OsiTitless '));    
        }

        function updateOsiTitles(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiTitles', data,config).then(handleSuccess, handleError('Error saving OsiTitless '));   
        }
        function searchOsiTitless(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiTitles', data,config).then(handleSuccess, handleError('Error getting OsiTitles ')); 
        } 
     /*   function getAllorganizations(){
            return $http.get(configData.url+'orgnaizations',config).then(handleSuccess, handleError('Error getting all orgnaizations'));
        }
        */
        
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
