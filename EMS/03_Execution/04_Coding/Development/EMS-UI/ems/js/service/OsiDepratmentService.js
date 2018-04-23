(function () {
    'use strict';

    angular
            .module('myApp.OsiDepratmentService', [])
            .factory('OsiDepratmentService', OsiDepratmentService);

    OsiDepratmentService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function OsiDepratmentService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var domain = "OsiDepratment".toLowerCase();
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllOsiDepratments = getAllOsiDepratments;
        service.saveOsiDepratment = saveOsiDepratment;
        service.updateOsiDepratment = updateOsiDepratment;
        service.getOsiDepratment = getOsiDepratment;
        service.searchOsiDepratments = searchOsiDepratments;

        service.getAllDeptGradesByDeptId = getAllDeptGradesByDeptId;
        service.getAllGradesByOrgId = getAllGradesByOrgId;
        service.saveDeptGrades = saveDeptGrades;

        return service;

        function getAllOsiDepratments() {
            return $http.get(configData.url+'crud/'+domain+'/getAllOsiDepratments',config).then(handleSuccess, handleError('Error getting OsiDepratment'));
        }

        function getOsiDepratment(id) {
            return $http.get(configData.url+'crud/'+domain+'/getOsiDepratment/'+id,config).then(handleSuccess, handleError('Error getting OsiDepratment'));
        }

        function saveOsiDepratment(data) {
         return $http.post(configData.url+'crud/'+domain+'/createOsiDepratment', data,config).then(handleSuccess, handleError('Error saving OsiDepratments '));
        }

        function updateOsiDepratment(data) {
            return $http.put(configData.url+'crud/'+domain+'/updateOsiDepratment', data,config).then(handleSuccess, handleError('Error saving OsiDepratments '));
        }
        function searchOsiDepratments(data){
            return $http.post(configData.url+'list/'+domain+'/searchOsiDepratment', data,config).then(handleSuccess, handleError('Error getting OsiDepratment '));
        }

        function getAllDeptGradesByDeptId(deptId){
            return $http.get(configData.url+'crud/'+domain+'/getOsiDeptGradesByDeptId/'+deptId, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function getAllGradesByOrgId(orgId){
            return $http.get(configData.url+'crud/'+domain+'/getAllGradesByOrgId/'+orgId, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
        }

        function saveDeptGrades(data) {
            return $http.post(configData.url+'crud/'+domain+'/createOsiDeptGrades', data, config).then(handleSuccess, handleError('Error getting OsiCostCenter '));
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
