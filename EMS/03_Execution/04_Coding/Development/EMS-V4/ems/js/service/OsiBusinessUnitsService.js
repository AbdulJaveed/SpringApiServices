(function () {
    'use strict';

    angular
            .module('myApp.OsiBusinessUnitsService', [])
            .factory('OsiBusinessUnitsService', OsiBusinessUnitsService);

    OsiBusinessUnitsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiBusinessUnitsService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiBusinessUnits".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllOsiBusinessUnitss = getAllOsiBusinessUnitss;
        service.saveOsiBusinessUnits = saveOsiBusinessUnits;
        service.updateOsiBusinessUnits = updateOsiBusinessUnits;
        service.getOsiBusinessUnits = getOsiBusinessUnits;
        service.searchOsiBusinessUnitss = searchOsiBusinessUnitss;

        service.getAllBUGradesBybuId = getAllBUGradesBybuId;
        service.getAllGradesByOrgId = getAllGradesByOrgId;
        service.saveCcGrades = saveCcGrades;

        service.getBUGradesHistory = getBUGradesHistory;

        return service;

        function getAllOsiBusinessUnitss() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiBusinessUnitss',config).then(handleSuccess, handleError('Error getting OsiBusinessUnits'));
        }

        function getOsiBusinessUnits(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiBusinessUnits/'+id,config).then(handleSuccess, handleError('Error getting OsiBusinessUnits'));
        }

        function saveOsiBusinessUnits(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiBusinessUnits', data,config).then(handleSuccess, handleError('Error saving OsiBusinessUnitss '));
        }

        function updateOsiBusinessUnits(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiBusinessUnits', data,config).then(handleSuccess, handleError('Error saving OsiBusinessUnitss '));
        }
        function searchOsiBusinessUnitss(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiBusinessUnits', data,config).then(handleSuccess, handleError('Error getting OsiBusinessUnits '));
        }

        function getAllBUGradesBybuId(buId){
            return $http.get(configData.url+'crud/'+domain+'/getOsiBUGradesBybuId/'+buId, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function getAllGradesByOrgId(orgId){
            return $http.get(configData.url+'crud/'+domain+'/getAllGradesByOrgId/'+orgId, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function saveCcGrades(data) {
            return $http.post(configData.url+'crud/'+domain+'/createOsiBUGrades', data, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function getBUGradesHistory(buId, orgId, gradeId){
            return $http.get(configData.url+'crud/'+domain+'/getOsiBUGradesHistory/'+buId+'/'+orgId+'/'+gradeId).then(handleSuccess, handleError('Error getting grades history'));
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
