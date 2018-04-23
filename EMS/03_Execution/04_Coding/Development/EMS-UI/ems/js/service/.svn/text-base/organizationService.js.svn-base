(function () {
    'use strict';

    angular.module('myApp.organizationService', []).factory('organizationService', organizationService);

    organizationService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData', '$q'];
    function organizationService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData, $q) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getOrganizationList = getOrganizationList;
        service.addOrganization = addOrganization;
        service.getCurrencies = getCurrencies;
        service.addAddresses = addAddresses;
        service.getOrgById = getOrgById;
        service.getOrgLocById = getOrgLocById;
        service.getOrgLists = getOrgLists;
        service.getLocById = getLocById;
        service.getCountries = getCountries;
        service.getStates = getStates;
        service.getEmpData = getEmpData;
        service.getRegions=getRegions;
        service.getTimezones=getTimezones;
        return service;

        function getOrganizationList(data) {

            return $http.post(configData.url+'orgSearch', data, config).then(handleSuccess, handleError);

        }

         function addOrganization(orgData) {

           return $http.post(configData.url+'organizations', orgData, config).then(handleSuccess, handleError);
         }

        function addAddresses(addLocation) {

            return $http.post(configData.url+'locations', addLocation, config).then(handleSuccess, handleError);
        }

        function  getOrgById(orgId) {

           return $http.get(configData.url+'organizationsById/'+orgId, config).then(handleSuccess, handleError);

        }

          function  getEmpData(data) {
        	  return $http.post(configData.url+'contactPersonSearch', data, config).then(handleSuccess, handleError);

          //return $http.get(configData.url+'contactPersonSearch/'+empName, config).then(handleSuccess, handleError);

         // return  [{"empId":1, "empName":"Deluxe Bicycle"},{"empId":2 , "empName" :"Super Deluxe Trampoline"}, {"empId":3, "empName" : "Super Duper Scooter"}];

        }

        function  getLocById(locationId) {

           return $http.get(configData.url+'locationsById/'+locationId, config).then(handleSuccess, handleError);

        }

       function getOrgLocById(orgId) {

           return $http.get(configData.url + 'locations/' + orgId, config).then(handleSuccess, handleError);

        }

        function getCurrencies() {

           return $http.get(configData.url + 'getOsiCurrencyInfo', config).then(handleSuccess, handleError);

        }
        function getCountries() {

           return $http.get(configData.url + 'countries', config).then(handleSuccess, handleError);

        }

         function getOrgLists() {

           return $http.get(configData.url + 'organizations', config).then(handleSuccess, handleError);

        }

        function getStates(countryId) {

           return $http.get(configData.url + 'states/bycountry/' + countryId, config).then(handleSuccess, handleError);

        }

        function getRegions(){
        	return $http.get(configData.url + 'regions', config).then(handleSuccess, handleError);
        }

        function getTimezones(){
        	return $http.get(configData.url + 'timezones', config).then(handleSuccess, handleError);
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
