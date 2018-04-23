(function () {
    'use strict';

angular
  .module('myApp.employeeBasicInfoService', [])
  .factory('employeeBasicInfoService', employeeBasicInfoService);
employeeBasicInfoService.$inject = [ '$http', '$q', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','organizationService','FlashService'];
function employeeBasicInfoService($http, $q, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage, configData,organizationService,FlashService) {
	var service = {};
    var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
    service.getAllOrganizations = getAllOrganizations;
    service.saveBasicInfo = saveBasicInfo;
    service.getBasicInfo = getBasicInfo;
    service.getAllCountries = getAllCountries;
    service.getAllCountryVisas = getAllCountryVisas;
    service.getPassportInfo = getPassportInfo;
    service.getPassportInfoByEmployeeIdAndDate = getPassportInfoByEmployeeIdAndDate;
    service.savePassportInfo = savePassportInfo;
    service.getBasicInfoByIdAndDate = getBasicInfoByIdAndDate;
	service.loadEmployeeBasicInfo = loadEmployeeBasicInfo;
	
	service.getEmpDocumentsById = getEmpDocumentsById;
	service.saveAdditionaDocs = saveAdditionaDocs;
    
	return service;

	function getAllOrganizations() {
		return $http.get(configData.url+'organizations',config).then(handleSuccess, handleError('Error getting v1/organizations'));
	}
	function saveBasicInfo(data, action) {
		return $http.post(configData.url+'v1/employees/'+action,data, config).then(handleSuccess, handleError('Error getting v1/employees'));
	}
	function getBasicInfo(id) {
		return $http.get(configData.url+'v1/employees/'+id, config).then(handleSuccess, handleError('Error getting v1/employees'));
	}
  	function getBasicInfoByIdAndDate(inputObj) {
		return $http.post(configData.url+'v1/employees/retrieve', inputObj, config).then(handleSuccess, handleError('Error getting v1/employees'));
	}
  
  
	function loadEmployeeBasicInfo(){
 		if($localStorage.employeeId && $localStorage.searchByDate) {
			var inputObject = { "employeeId" : $localStorage.employeeId , "searchDate" : $localStorage.searchByDate};
			getBasicInfoByIdAndDate(inputObject).then(function success(result) {
					organizationService.getOrgById(result.orgId).then(function(data){
						$localStorage.orgId = result.orgId;
						$localStorage.orgName = data.orgName;
						 $localStorage.employeeName = result.fullName;
						 $localStorage.employeeNumber = result.employeeNumber;
						 $localStorage.osiEmpAttachments = result.osiEmpAttachments;
							
					}).catch(function(error){

					});
					
				}, function error(error){
					console.log(error.data);
					$rootScope.isTrascError = true;
					FlashService.Error(error.data.errorMessage);
					$timeout(function () {
						$rootScope.isTrascError=false;
					}, 2000);
				});
			}
 	}
  	function getAllCountries() {
		return $http.get(configData.url+'v1/employees/getAllCountries', config).then(handleSuccess, handleError('Error getting v1/employees/getAllCountries'));
	}
        function getAllCountryVisas(countryId) {
		return $http.get(configData.url+'v1/employees/getAllCountryVisasById/'+countryId, config).then(handleSuccess, handleError('Error getting v1/employees/getAllCountryVisas'));
	}
        function getPassportInfo(empId) {
		return $http.get(configData.url+'v1/employees/getPassportInfo/'+empId, config).then(handleSuccess, handleError('Error getting v1/getPassportInfo'));
	}
	function getPassportInfoByEmployeeIdAndDate(inputObject){
		return $http.post(configData.url+'v1/employees/getPassportInfo', inputObject, config).then(handleSuccess, handleError('Error getting v1/getPassportInfo'));
	}
	function savePassportInfo(data) {
			return $http.post(configData.url+'v1/employees/savePassportInfo',data, config).then(handleSuccess, handleError('Error getting v1/employees'));
	}

	// employee Documents
	function getEmpDocumentsById(id) {
		return $http.get(configData.url+'v1/employees/additonaldocs/'+id, config).then(handleSuccess, handleError('Error getting v1/empAddtionalDocuments'));
	}

	function saveAdditionaDocs(input) {
		return $http.post(configData.url+'v1/employees/saveadditionaldocs',input,config).then(handleSuccess, handleError('Error getting v1/empAddtionalDocuments'));
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
