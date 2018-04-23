(function() {
    'use strict';
    angular
        .module('myApp.ReportsGroupService',[])
        .factory('ReportsGroupService', ReportsGroupService);
    ReportsGroupService.$inject = [ '$http', '$cookieStore', 'configData'];
    
    function ReportsGroupService($http, $cookieStore, configData) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getReportsGroupList = getReportsGroupList;
        service.getCompleteReprotsList = getCompleteReprotsList;
        service.getSingleReportGroup = getSingleReportGroup;
        service.saveReportGroup = saveReportGroup;
        service.updateReportGroup = updateReportGroup;
        return service;
       
        function getReportsGroupList(data) {
         return $http.post(configData.url+'all-report-groups', data,config).then(handleSuccess, handleError('Error getting report group list '));    
        }
        
        function getCompleteReprotsList() {
         return $http.get(configData.url+'all-reports',config).then(handleSuccess, handleError('Error getting all reports '));    
        }
        
        function getSingleReportGroup(data) {
         return $http.get(configData.url+'report-group/'+data,config).then(handleSuccess, handleError('Error getting single report group'));    
        }
        
        function saveReportGroup(data) {
         return $http.post(configData.url+'report-group', data,config).then(handleSuccess, handleError('Error saving report group '));    
        }
        
        function updateReportGroup(data) {
         return $http.put(configData.url+'report-group', data,config).then(handleSuccess, handleError('Error updating report group '));    
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