(function () {
    'use strict';

    angular
            .module('myApp.empBankDetailsConfigService', [])
            .factory('empBankDetailsConfigService', empBankDetailsConfigService);
    empBankDetailsConfigService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];

    function empBankDetailsConfigService($http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData)
    {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        
        service.saveEmpBankDetails=saveEmpBankDetails;
        service.getAllEmpBankDetails=getAllEmpBankDetails;
         
        return service;    
        
        
        function saveEmpBankDetails(data){
            console.log(data);
            return $http.post(configData.url+'saveEmpBankDetails',data,config).then(handleSuccess, handleError('Error saving Employee Bank Details'));
        }
        
        function getAllEmpBankDetails(){
            return $http.get(configData.url+'bankDetailsList',config).then(handleSuccess, handleError('Error getting Bank Details List'));
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




