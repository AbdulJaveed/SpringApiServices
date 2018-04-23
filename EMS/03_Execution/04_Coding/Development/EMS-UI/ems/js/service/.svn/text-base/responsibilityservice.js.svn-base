(function () {
    'use strict';

    angular
            .module('myApp.ResponsibilityService', [])
            .factory('ResponsibilityService', ResponsibilityService);

    ResponsibilityService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage', 'configData','$q'];

    function ResponsibilityService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage, configData,$q) {
        var service = {};
        var config = {headers: {'Auth_Token': $cookieStore.get('globals').userDTO.token}};
        service.setResponsibilities=setResponsibilities;
        service.getResponsibilities=getResponsibilities;
        service.deleteResponsibilities=deleteResponsibilities;
        return service;

        function getResponsibilities(OsiUserObject) {
            return  $http.get(configData.url+'get-all-user-responsibilities/'+OsiUserObject, config).then(handleSuccess, handleError('Error getting segmentStructureHdrId'));
            
            
        }
        
       function setResponsibilities (userResponsibilities) { 
    	   console.log(JSON.stringify(userResponsibilities));
            return  $http.post(configData.url+'post-user-responsibilities',userResponsibilities, config).then(handleSuccess, handleError('Error getting segmentStructureHdrId'));  
        }
        
        function deleteResponsibilities(id){
            return $http.delete(configData.url+'/delete-user-responsibility/'+id, config).then(handleSuccess, handleError('Error getting segmentStructureHdrId'));
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

        function handleError(error) { 
         	
         }


    }



})();