(function () {
    'use strict';
    
    angular
            .module('myApp.userService',[])
            .factory('userService', userService); 
    
    userService.$inject = [ '$http','$cookieStore', 'configData','$q'];
    
        function userService( $http, $cookieStore, configData,$q) {
        	var service = {};
            var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
           
            var config1 = {  transformRequest: angular.identity,
            					headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token,
            								'Content-Type': undefined }
            			};
            service.getUserInitially = getUserInitially;
            service.getUser = getUser;
            service.getAllUser = getAllUser;
            service.saveUser = saveUser;
            service.updateUser = updateUser;
            service.deleteUser = deleteUser;
            service.uploadFile = uploadFile;
            service.getRoles = getRoles;
            service.getAllSearchedUsers = getAllSearchedUsers;
            service.getUserRoleAssignCount = getUserRoleAssignCount;
            service.getUsersInvOrgRoleDeptCombination = getUsersInvOrgRoleDeptCombination;
            service.getUsersDefaultPassword = getUsersDefaultPassword;
            service.resetUserPassword = resetUserPassword;
            return service;
            
            function getAllSearchedUsers(data){
                return $http.post(configData.url+'searchedUsers', data, config).then(handleSuccess, handleError);
            }
            function getUser(userId){
                return $http.get(configData.url+'users/'+userId,config).then(handleSuccess, handleError);
            }
            function getUserInitially() {
                return $http.get(configData.url+'usersInitially', config).then(handleSuccess, handleError);
            }
            function getAllUser() {
                return $http.get(configData.url+'users', config).then(handleSuccess, handleError);
            }
            function saveUser(data) {
                return $http.post(configData.url+'users', data, config).then(handleSuccess, handleError);
            }
            function updateUser(data) {
                return $http.put(configData.url+'users', data, config).then(handleSuccess, handleError);
            }
            function deleteUser(data) {
                return $http.post(configData.url+'deleteUsers', data, config).then(handleSuccess, handleError);
            }
          
            function uploadFile(data){
                return $http.post(configData.url+'uploadFile', data,config1).then(handleSuccess, handleError);
            }
            
            function getRoles(){
            	return $http.get(configData.url+'getRoles',config).then(handleSuccess, handleError);
            }
            
            function getUserRoleAssignCount(roleId){
                return $http.get(configData.url+'getRoleCountFromOsiApprovalHierarchy/'+roleId,config).then(handleSuccess, handleError);
            }
            function getUsersInvOrgRoleDeptCombination(){
                return $http.get(configData.url+'getRoleDepartmentInvCombination',config).then(handleSuccess, handleError);
            }
            function getUsersDefaultPassword(osiConfigParametersDTO) {
                return $http.post(configData.url+'getUsersDefaultPassword',osiConfigParametersDTO, config).then(handleSuccess, handleError);
            }
            function resetUserPassword(decodedPassword, data) {
                return $http.put(configData.url+'resetUserPassword/'+decodedPassword, data, config).then(handleSuccess, handleError);
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
		   		 return $q.reject(error);
            }

     }
    
})();    



