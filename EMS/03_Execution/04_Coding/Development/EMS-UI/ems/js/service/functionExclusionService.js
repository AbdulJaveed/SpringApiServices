(function () {
    'use strict';

    angular
            .module('myApp.functionExclusionService', [])
            .factory('functionExclusionService', functionExclusionService);
    functionExclusionService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];

    function functionExclusionService($http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData)
    {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        
        service.getAllFunctions=getAllFunctions;   
        service.getFunctionsByRespIds = getFunctionsByRespIds;
        service.getExclFunctions = getExclFunctions;
        service.deleteFunctionExclusion = deleteFunctionExclusion;
        service.saveFunctionExclusion = saveFunctionExclusion;
        
        return service;    
        
        function getFunctionsByRespIds(respUser){
        	return $http.post(configData.url+'functionsByRespIds',respUser,config).then(handleSuccess, handleError('Error getting operation'));
        }
        
        function getAllFunctions(){
            return $http.get(configData.url+'functions',config).then(handleSuccess, handleError('Error getting Functions'));
        }
        
        function getExclFunctions(id){
            return $http.get(configData.url+'osi_user_func_excl/'+id,config).then(handleSuccess, handleError('Error getting Excluded Functions'));
        }
        
        
        function deleteFunctionExclusion(id){
            return $http.delete(configData.url+'osi_user_func_excl/'+id).then(handleSuccess, handleError('Error deleting Excluded Functions'));
        }
        
        function saveFunctionExclusion(data){
            return $http.post(configData.url+'osi_user_func_excl',data,config).then(handleSuccess, handleError('Error saving excluded Functions'));
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




