(function () {
    'use strict';
    
    angular
            .module('myApp.userProfileService',[])
            .factory('userProfileService', userProfileService); 
    
    userProfileService.$inject = [ '$http','$cookieStore', 'configData', '$q'];
    
        function userProfileService( $http, $cookieStore, configData, $q) {
        	var service = {};
            var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
           
            service.getAllorganizations = getAllorganizations;
            service.getLocationsByOrg = getLocationsByOrg;
            service.getBuildingsByLocation = getBuildingsByLocation;
            service.getCardTypesByOrg = getCardTypesByOrg;
            service.getEmployee = getEmployee;
            service.saveOsiEmployee = saveOsiEmployee;
            return service;
            
            function getAllorganizations(){
            	return $http.get(configData.url+'crud/organizations/getAllOrganizations').then(handleSuccess, handleError('Error getting Organizations'));
            }
            function getLocationsByOrg(orgId){
            	return $http.get(configData.url+'crud/organizations/getLocationsByOrg/'+orgId).then(handleSuccess, handleError('Error getting Locations'));
            }
            function getBuildingsByLocation(locId){
            	return $http.get(configData.url+'crud/organizations/getBuildingsByLocation/'+locId).then(handleSuccess, handleError('Error getting Buildings'));
            }
            function getCardTypesByOrg(orgId){
            	return $http.get(configData.url+'crud/organizations/getCardTypesByOrg/'+orgId).then(handleSuccess, handleError('Error getting Card types'));
            }
            function getEmployee(empId){
            	return $http.get(configData.url+'crud/osiemployees/getEmployee/'+empId).then(handleSuccess, handleError('Error getting Employee'));
            }
            
            /*function getUserByDTO(userDTO){
                return $http.post(configData.url+'getUserInfo/',userDTO,config).then(handleSuccess, handleError('Error getting User Details'));
            }*/
            function saveOsiEmployee(userDTO){
                return $http.post(configData.url+'crud/osiemployees/saveOsiEmployee',userDTO,config).then(handleSuccess, handleError('Error updating Employee'));
            }
            
            function handleSuccess(res) {
                var deferred = $q.defer();
                if (res.data.errorCode) {
                    deferred.reject(res.data);
                }else{
                    deferred.resolve(res.data);
                }
                return deferred.promise;
            } 

            function handleError(error) {
                return $q.reject(error);
            }
            
     }
    
})();    



