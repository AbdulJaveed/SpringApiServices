(function () {
    'use strict';

    angular
            .module('myApp.operationExclusionService', [])
            .factory('operationExclusionService', operationExclusionService);
    operationExclusionService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];

    function operationExclusionService($http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData)
    {
        var service = {};
       
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getFunctionOperations=getFunctionOperations;   
        service.getAllOperations=getAllOperations;
        service.getExcludedOperations=getExcludedOperations;
        service.deleteOperationExclusion=deleteOperationExclusion;
        service.getAllExcludedOperations = getAllExcludedOperations;
        service.saveOperationExclusion=saveOperationExclusion; 
        
        return service;   
        
        function getAllOperations(){
            return $http.get(configData.url+'operataions',config).then(handleSuccess, handleError('Error getting Operations'));
        }
        
        function getExcludedOperations(funcId, userId){
            return $http.get(configData.url+'osi-user-operation-excl/'+funcId+'/'+userId,config).then(handleSuccess, handleError('Error getting Excluded Operations'));
        }
        function getAllExcludedOperations(userId){
            return $http.get(configData.url+'osi-all-user-operation-excl/'+userId,config).then(handleSuccess, handleError('Error getting Excluded Operations'));
        }
        function getFunctionOperations(id,id2){
           return $http.get(configData.url+'user-exl-operataions/'+id+'/'+id2,config).then(handleSuccess, handleError('Error getting Function Operations'));
        }
        
        function deleteOperationExclusion(id,id2){
           return $http.delete(configData.url+'osi-user-operation-excl/'+id+'/'+id2,config).then(handleSuccess, handleError('Error deleting Operation'));
        }
        
        function saveOperationExclusion(data){
           return $http.post(configData.url+'osi-user-operation-excl',data,config).then(handleSuccess, handleError('Error saving excluded Operation'));
        }
                      
        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {success: false, message: error};
            };
        }

    }

})();




