(function() {
    'use strict';
    angular
        .module('myApp.FunctionService',[])
        .factory('FunctionService', FunctionService);
    FunctionService.$inject = [ '$http', '$q', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];
    
    function FunctionService($http, $q, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getAllFunctions = getAllFunctions;
        service.getFunctionsListInitially = getFunctionsListInitially;
        service.getAllFunctionsList = getAllFunctionsList;
        service.saveFunction = saveFunction;
        service.getAllOpetations = getAllOpetations;
        service.saveFunOperation= saveFunOperation;
        service.getFuncOperationsByFunctionId=getFuncOperationsByFunctionId;
        service.updateFunction=updateFunction;
        service.updateFuncOperation=updateFuncOperation;
        service.deleteFunction= deleteFunction;
        service.searchFunction=searchFunction;
        return service;
       
        function getAllFunctions() {
            return $http.get(configData.url+'functions',config).then(handleSuccess, handleError);
        }
        
        function getFunctionsListInitially() {
            return $http.get(configData.url+'functionsInitially/list',config).then(handleSuccess, handleError);
        }

        function getAllFunctionsList() {
            return $http.get(configData.url+'functions/list',config).then(handleSuccess, handleError);
        }
        
        function saveFunction(data) {
         return $http.post(configData.url+'createFunction', data,config).then(handleSuccess, handleError);    
        }

        function updateLookups(data) {
        return $http.put(configData.url+'editLookup', data,config).then(handleSuccess, handleError);   
        }
        
        function getAllOpetations(){
        	return $http.get(configData.url+'operataions',config).then(handleSuccess, handleError);
        }
        
        function saveFunOperation(data){
        	return $http.post(configData.url+'funcOperations', data,config).then(handleSuccess, handleError);
        }

        function getFuncOperationsByFunctionId(id) {
            return $http.get(configData.url+'getFuncOperationsByFunctionId/'+id,config).then(handleSuccess, handleError);
        }
        function updateFunction(data) {
            return $http.put(configData.url+'updateFunction',data, config).then(handleSuccess, handleError);
        }
        function updateFuncOperation(data) {
            return $http.put(configData.url+'updateFuncOperations',data, config).then(handleSuccess, handleError);
        }
        
        function deleteFunction(id){
        	return $http.post(configData.url+'deleteFunction', id,config).then(handleSuccess, handleError);
        }
      
        function searchFunction(data){
        	return $http.post(configData.url+'searchFunction', data,config).then(handleSuccess, handleError);
        }
        
        function handleSuccess(res) {
	    	var deferred = $q.defer();
	    	if(res.data.errorCode) {
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