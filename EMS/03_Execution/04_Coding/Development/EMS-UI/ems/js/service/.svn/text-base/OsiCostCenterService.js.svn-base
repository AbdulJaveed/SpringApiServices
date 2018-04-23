(function () {
    'use strict';

    angular
            .module('myApp.OsiCostCenterService', [])
            .factory('OsiCostCenterService', OsiCostCenterService);

    OsiCostCenterService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiCostCenterService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiCostCenter".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllOsiCostCenters = getAllOsiCostCenters;
        service.saveOsiCostCenter = saveOsiCostCenter;
        service.updateOsiCostCenter = updateOsiCostCenter;
        service.getOsiCostCenter = getOsiCostCenter;
        service.searchOsiCostCenters = searchOsiCostCenters;
        service.getAllCCGradesByccId = getAllCCGradesByccId;
        service.getAllGradesByOrgId = getAllGradesByOrgId;
        service.saveCcGrades = saveCcGrades;

        return service;

        function getAllOsiCostCenters() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiCostCenters',config).then(handleSuccess, handleError('Error getting OsiCostCenter'));
        }

        function getOsiCostCenter(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiCostCenter/'+id,config).then(handleSuccess, handleError('Error getting OsiCostCenter'));
        }

        function saveOsiCostCenter(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiCostCenter', data,config).then(handleSuccess, handleError('Error saving OsiCostCenters '));
        }

        function updateOsiCostCenter(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiCostCenter', data,config).then(handleSuccess, handleError('Error saving OsiCostCenters '));
        }
        function searchOsiCostCenters(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiCostCenter', data,config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function getAllCCGradesByccId(ccId){
            return $http.get(configData.url+'crud/'+domain+'/getOsiCCGradesByccId/'+ccId, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function getAllGradesByOrgId(orgId){
            return $http.get(configData.url+'crud/'+domain+'/getAllGradesByOrgId/'+orgId, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function saveCcGrades(data) {
            return $http.post(configData.url+'crud/'+domain+'/createOsiCCGrades', data, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
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
