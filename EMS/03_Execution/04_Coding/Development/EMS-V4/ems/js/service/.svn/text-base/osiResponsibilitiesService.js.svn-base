(function () {
    'use strict';

    angular
            .module('myApp.osiResponsibilitiesService', [])
            .factory('osiResponsibilitiesService', osiResponsibilitiesService);
    osiResponsibilitiesService.$inject = ['$http','$q','$cookieStore','configData'];

    function osiResponsibilitiesService($http,$q,$cookieStore,configData)
    {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getInitiallyResponsibility = getInitiallyResponsibility;
        service.getOsiResponcibilities=getOsiResponcibilities;
        service.getAllOsiResponcibilities=getAllOsiResponcibilities;
        service.getAllOsiResponcibilitiesList = getAllOsiResponcibilitiesList;
        service.updateOsiResponcibilities=updateOsiResponcibilities;
        service.saveOsiResponcibilities=saveOsiResponcibilities;
        service.deleteOsiResponcibilities=deleteOsiResponcibilities;
        service.searchResponsibility = searchResponsibility;
        service.getReportGroups = getReportGroups;
        return service;
        
        function getInitiallyResponsibility(){
            return $http.get(configData.url+'intiallyResponsibilities',config).then(handleSuccess, handleError);
        }
        function getOsiResponcibilities(orgInvId){
            return $http.get(configData.url+'responsibilities/'+orgInvId,config).then(handleSuccess, handleError);
        }
        
        function getAllOsiResponcibilities(){
           return $http.get(configData.url+'responsibilities',config).then(handleSuccess, handleError);
        }
        
        function getAllOsiResponcibilitiesList(){
           return $http.get(configData.url+'userResponsibilities',config).then(handleSuccess, handleError);
        }
        
        function searchResponsibility(data){
             return $http.post(configData.url + 'responsibilities/searchResp', data, config).then(handleSuccess, handleError);
        }
        
        function saveOsiResponcibilities(data) {
            console.log("Saving:",JSON.stringify(data));
            return $http.post(configData.url+'responsibilities', data,config).then(handleSuccess, handleError);
        }
        
        function updateOsiResponcibilities(data) {
            return $http.put(configData.url+'responsibilities', data,config).then(handleSuccess, handleError);
        }
        
        function deleteOsiResponcibilities(data) {
            return $http.delete(configData.url+'responsibilities/'+data,config).then(handleSuccess, handleError);
        }
        
        function getReportGroups() {
            return $http.get(configData.url+'all-active-report-groups',config).then(handleSuccess, handleError);
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