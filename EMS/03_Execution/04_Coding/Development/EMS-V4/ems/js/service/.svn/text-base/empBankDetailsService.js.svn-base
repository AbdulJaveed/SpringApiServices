(function () {
    'use strict';

    angular
            .module('myApp.empBankDetailsService', [])
            .factory('empBankDetailsService', empBankDetailsService);
    empBankDetailsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];

    function empBankDetailsService($http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData)
    {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        
        service.getAllEmpBankDetails=getAllEmpBankDetails;
        service.getEmpBankDetailsById=getEmpBankDetailsById;
        service.getEmpBankDetailsByEmployeeIdAndDate = getEmpBankDetailsByEmployeeIdAndDate;
        return service;    
        
        
        function getAllEmpBankDetails(){
            return $http.get(configData.url+'bankDetailsList',config).then(handleSuccess, handleError('Error getting Bank Details List'));
        }
        
        function getEmpBankDetailsById(id) {
            return $http.get(configData.url + 'getEmpBankDetailsById/' + id, config).then(handleSuccess, handleError('Error getting Bank Details List'));
        }
        
        function getEmpBankDetailsByEmployeeIdAndDate(inputObject){
            return $http.post(configData.url + 'empBankDetailsList', inputObject, config).then(handleSuccess, handleError('Error getting Bank Details List'));
        }
                      
        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {success: false, message: error};
            };
        }

    }

})();




