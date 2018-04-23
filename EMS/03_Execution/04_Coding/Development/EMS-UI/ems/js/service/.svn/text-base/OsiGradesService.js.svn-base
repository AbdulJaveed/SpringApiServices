(function () {
    'use strict';

    angular
            .module('myApp.OsiGradesService', [])
            .factory('OsiGradesService', OsiGradesService);

    OsiGradesService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiGradesService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiGrades".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getAllOsiGradess = getAllOsiGradess;
        service.saveOsiGrades = saveOsiGrades;
        service.updateOsiGrades = updateOsiGrades;
        service.getOsiGrades = getOsiGrades;
        service.searchOsiGradess = searchOsiGradess;
        service.getAllorganizations=getAllorganizations;
        service.getAllOsiTitles=getAllOsiTitles;
        
        return service;       
        
        function getAllOsiGradess() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiGradess',config).then(handleSuccess, handleError('Error getting OsiGrades'));
        }
        
        function getOsiGrades(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiGrades/'+id,config).then(handleSuccess, handleError('Error getting OsiGrades'));
        }

        function saveOsiGrades(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiGrades', data,config).then(handleSuccess, handleError('Error saving OsiGradess '));    
        }

        function updateOsiGrades(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiGrades', data,config).then(handleSuccess, handleError('Error saving OsiGradess '));   
        }
        function searchOsiGradess(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiGrades', data,config).then(handleSuccess, handleError('Error getting OsiGrades ')); 
        } 
        function getAllorganizations(){
            return $http.get(configData.url+'orgnaizations',config).then(handleSuccess, handleError('Error getting all orgnaizations'));
        }
        function getAllOsiTitles(){
            return $http.get(configData.url+'list/'+domain+'/getAllOsiTitles',config).then(handleSuccess, handleError('Error getting all orgnaizations'));
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
