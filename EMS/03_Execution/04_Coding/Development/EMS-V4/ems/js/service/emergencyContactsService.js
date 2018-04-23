myApp.emergencyContactsService

(function () {
    'use strict';

angular
  .module('myApp.emergencyContactsService', [])
  .factory('emergencyContactsService', emergencyContactsService);
emergencyContactsService.$inject = [ '$http', '$q', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];
function emergencyContactsService($http, $q, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage, configData) {
var service = {};
    var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
    service.getAllSkills = getAllSkills;
    service.saveEmpSkills = saveEmpSkills;
    service.getEmpSkills = getEmpSkills;
    service.searchEmpSkills = searchEmpSkills;
return service;

function getAllSkills() {
return $http.get(configData.url+'skills/get',config).then(handleSuccess, handleError('Error getting v1/organizations'));
}
function saveEmpSkills(data) {
return  $http.post(configData.url+'skills/add',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
}
function getEmpSkills(id) {
//return /*$http.get(configData.url+'v1/employees/'+id, config).then(handleSuccess, handleError('Error getting v1/employees'));*/
}
    
    function searchEmpSkills(data) {
return $http.post(configData.url+'skills/search',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
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
