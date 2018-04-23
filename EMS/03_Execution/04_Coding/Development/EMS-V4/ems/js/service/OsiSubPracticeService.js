(function () {
    'use strict';

    angular
            .module('myApp.OsiSubPracticeService', [])
            .factory('OsiSubPracticeService', OsiSubPracticeService);

    OsiSubPracticeService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiSubPracticeService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "osisubpractice".toLowerCase();
        var domain1 = "OsiDepratment".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllOsiSubPractice = getAllOsiSubPractice;
        service.saveOsiSubPractice = saveOsiSubPractice;
        service.updateOsiSubPractice = updateOsiSubPractice;
        service.getOsiSubPractice = getOsiSubPractice;
        service.searchOsiSubPractices = searchOsiSubPractices ;

        service.getAllSubPracticeGradesBysubPracticeId = getAllSubPracticeGradesBysubPracticeId;
        service.getAllGradesByOrgId = getAllGradesByOrgId;
        service.saveCcGrades = saveCcGrades;

        service.getSubPracticesGradesHistory = getSubPracticesGradesHistory;

        return service;

        function getAllOsiSubPractice() {
            return $http.get(configData.url+domain,config).then(handleSuccess, handleError('Error getting OsiSubPractice'));
        }

        function getOsiSubPractice(id) {
            return $http.get(configData.url+domain+'/'+id,config).then(handleSuccess, handleError('Error getting OsiSubPractice'));
        }

        function saveOsiSubPractice(data) {
         return $http.post(configData.url+domain, data,config).then(handleSuccess, handleError('Error saving OsiSubPractice '));
        }

        function updateOsiSubPractice(data) {
            return $http.put(configData.url+domain+'/'+data.subPracticeId, data,config).then(handleSuccess, handleError('Error saving OsiSubPractice '));
        }
        function searchOsiSubPractices(data){
            return $http.post(configData.url+domain+'/list', data,config).then(handleSuccess, handleError('Error getting OsiSubPractice '));
        }

        function getAllSubPracticeGradesBysubPracticeId(subPracticeId){
            return $http.get(configData.url+domain+'/getOsiSubPracticeGradesBysubPracticeId/'+subPracticeId, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function getAllGradesByOrgId(orgId){
            return $http.get(configData.url+'crud/'+domain1+'/getAllGradesByOrgId/'+orgId, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function saveCcGrades(data) {
            return $http.post(configData.url+domain+'/createOsiSubPracticeGrades', data, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function getSubPracticesGradesHistory(subPracticeId, orgId, gradeId){
            return $http.get(configData.url+domain+'/getOsiSubPracticeGradesHistory/'+subPracticeId+'/'+orgId+'/'+gradeId).then(handleSuccess, handleError('Error getting grades history'));
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
