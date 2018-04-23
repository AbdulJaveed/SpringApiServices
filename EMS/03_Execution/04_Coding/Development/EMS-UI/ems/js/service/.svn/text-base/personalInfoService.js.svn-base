(function () {
    'use strict';

    angular
            .module('myApp.PersonalInfoService', [])
            .factory('personalInfoService', PersonalInfoService);

    PersonalInfoService.$inject = ['$http', '$q', '$cookieStore', 'configData'];

    function PersonalInfoService($http, $q, $cookieStore, configData) {
        var service = {};
        var config = {headers: {'Auth_Token': $cookieStore.get('globals').userDTO.token}};
        service.savePersonalInfo = savePersonalInfo;
        service.getAllCountryList = getAllCountryList;
        service.getAllStatesListByCountry = getAllStatesListByCountry;
        service.saveAddress = saveAddress;
        service.getPersonalInfo = getPersonalInfo;
        service.getPersonalInfoByIdAndDate = getPersonalInfoByIdAndDate;
        service.getCountryCodesList = getCountryCodesList;
        service.saveContactsList = saveContactsList;

        return service;

        function getCountryCodesList(){
        	return $http.get(configData.url + 'countries/countryCodes', config).then(handleSuccess, handleError);
        }
        function savePersonalInfo(personalInfo, action) {
          return $http.post(configData.url + 'v1/employees/personalinfo/'+action, personalInfo, config).then(handleSuccess, handleError);

        }
        function saveContactsList(contacts) {
          return $http.post(configData.url + 'contacts/list', contacts, config).then(handleSuccess, handleError);

        }
        function saveAddress(address) {

            return $http.post(configData.url + 'addresses', address, config).then(handleSuccess, handleError);

        }


        function getAllCountryList() {
          return $http.get(configData.url + 'countries', config).then(handleSuccess, handleError);
        }

        function getAllStatesListByCountry(country) {
          return $http.get(configData.url + 'states/bycountry/'+country, config).then(handleSuccess, handleError);
        }
        function getPersonalInfo(employeeId) {
          return $http.get(configData.url + 'v1/employees/personalInfo/'+employeeId, config).then(handleSuccess, handleError);
        }
        function getPersonalInfoByIdAndDate(inputObject) {
          return $http.post(configData.url + 'v1/employees/personalInfoByIdAndDate', inputObject, config).then(handleSuccess, handleError);
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
        	return $q.reject(error); 
        }
    }

})();
