(function () {
    'use strict';

    angular
            .module('myApp.ReportsService', [])
            .factory('ReportsService', ReportsService);

    ReportsService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];
    function ReportsService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getReportsList = getReportsList;   
        service.getReportHistoryDetails = getReportHistoryDetails;
        service.downloadReport = downloadReport;
        service.downloadLogFile = downloadLogFile;
        service.unscheduleReport = unscheduleReport;
        service.moreInfo = moreInfo;
        return service;
       

        function getReportsList(reportGroupId) {
            return $http.get(configData.url+'all-reports-by-grpt-id/'+reportGroupId,config).then(handleSuccess, handleError('Report List Get Failed'));
        }
        
        function getReportHistoryDetails(data) {
         return $http.post(configData.url+'rm-hostory', data,config).then(handleSuccess, handleError('Get Reports Details Failed'));    
        }

        function downloadReport(requestId) {
            return $http.get(configData.url+'download-out-file/'+requestId,config).then(handleSuccess, handleError('Download report Failed'));
        }
        
        function downloadLogFile(requestId) {
            return $http.get(configData.url+'download-log-file/'+requestId,config).then(handleSuccess, handleError('Download log Failed'));
        }
        
        function unscheduleReport(requestId) {
            return $http.get(configData.url+'unschedule/'+requestId,config).then(handleSuccess, handleError('Unscheduling Failed'));
        }
        
        function moreInfo(requestId) {
            return $http.get(configData.url+'request-more-details/'+requestId,config).then(handleSuccess, handleError('Unable to get more info'));
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