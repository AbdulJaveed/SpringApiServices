(function () {
    'use strict';

    angular
            .module('myApp.officeInfoService',[])
            .factory('officeInfoService', officeInfoService);

    officeInfoService.$inject = [ '$http','$cookieStore', 'configData', '$q'];

        function officeInfoService( $http, $cookieStore, configData, $q) {
        	var service = {};
            var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };


            service.saveOsiEmployee = saveOsiEmployee;
            service.findOsiEmployee = findOsiEmployee;
            service.findOsiEmployeeContacts = findOsiEmployeeContacts;
            service.findOsiEmployeeByIdAndDate = findOsiEmployeeByIdAndDate;
            return service;


            function saveOsiEmployee(osiEmployee,action){
                return $http.post(configData.url+'saveOsiEmployeesOfficeInfo/'+action,osiEmployee,config).then(handleSuccess, handleError('Error updating Employee'));
            }
            function findOsiEmployee(empId){
            	return $http.get(configData.url+'findOsiEmployees/'+empId,config).then(handleSuccess, handleError('Error getting Employee'));
            }

            function findOsiEmployeeContacts(empId){
            	return $http.get(configData.url+'findOsiContacts/'+empId,config).then(handleSuccess, handleError('Error getting Employee'));
            }

            function findOsiEmployeeByIdAndDate(inputObject){
            	return $http.post(configData.url+'findOsiEmployeesByIdAndDate', inputObject,config).then(handleSuccess, handleError('Error getting Employee'));
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
