(function () {
    'use strict';

angular
  .module('myApp.employeeSkillService', [])
  .factory('employeeSkillService', employeeSkillService);
employeeSkillService.$inject = [ '$http', '$q', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];
function employeeSkillService($http, $q, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage, configData) {
var service = {};
    var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
    service.getAllSkills = getAllSkills;
    service.saveEmpSkills = saveEmpSkills;
    service.getEmpSkills = getEmpSkills;
    service.searchEmpSkills = searchEmpSkills;
     service.getAllCertificates = getAllCertificates;
    service.saveEmpCertificates = saveEmpCertificates;
    service.getEmpCertificates = getEmpCertificates;
    service.searchEmpCertificates = searchEmpCertificates;
    service.saveEmpContacts = saveEmpContacts;
    service.getAllContacts=getAllContacts;
    service.editEmergencyContacts=editEmergencyContacts;

    service.getAllCertificatesById = getAllCertificatesById;
    service.getSkillsById = getSkillsById;

    service.updateVerifiedSkills = updateVerifiedSkills;
    service.updateVerifiedCert = updateVerifiedCert;
    service.findSuperviosrId = findSuperviosrId;

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

function getAllCertificates() {
return $http.get(configData.url+'certification/get',config).then(handleSuccess, handleError('Error getting v1/organizations'));
}
function getSkillsById(id) {
return $http.get(configData.url+'skills/get/'+id, config).then(handleSuccess, handleError('Error getting skills'));
}
function getAllCertificatesById(id) {
return $http.get(configData.url+'certification/get/'+id, config).then(handleSuccess, handleError('Error getting certifications'));
}
function saveEmpCertificates(data) {

return  $http.post(configData.url+'certification/add',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
}
function getEmpCertificates(id) {
//return /*$http.get(configData.url+'v1/employees/'+id, config).then(handleSuccess, handleError('Error getting v1/employees'));*/
}

    function searchEmpCertificates(data) {
    	return $http.post(configData.url+'certification/search',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
}

function saveEmpContacts(data) {
    return  $http.post(configData.url+'emergencyContacts/save',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
    }


function getAllContacts(id) {

	return $http.get(configData.url+'emergencyContacts/get/'+id,config).then(handleSuccess, handleError('Error getting v1/organizations'));
	}

    function editEmergencyContacts(data) {
    return  $http.post(configData.url+'emergencyContacts/update',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
    }
    function updateVerifiedSkills(data) {
        return  $http.post(configData.url+'skills/verifySkills',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
    }
    function updateVerifiedCert(data) {
        return  $http.post(configData.url+'certification/verifyCertifications',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
    }
    function findSuperviosrId(employeeId) {
        return $http.get(configData.url+'assignments/findSuperviosrId/'+employeeId, config).then(handleSuccess, handleError('Error getting v1/employees'));
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
