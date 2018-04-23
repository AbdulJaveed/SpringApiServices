'use strict';
angular.module('myApp.ReportsHistoryController', []).controller('ReportsHistoryController',
        ReportsHistoryController);
ReportsHistoryController.$inject = ['$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'ReportsService', 'FlashService', '$timeout', '$filter'];
function ReportsHistoryController($scope, $rootScope, $window, $location, $http,
        $localStorage, ReportsService, FlashService, $timeout, $filter) {
    var vm = this;
    vm.today = new Date().toDateString();
    $scope.reportsList = "";
    $scope.rowSize = 6;
    $scope.selectedRows = "";
    $scope.searchCriteria = {};
    $scope.isOutFileDisable=true;
    $scope.isLogFileDisable=true;
    $scope.isMoreInfoDisable=true;
    $scope.isUnshceduleDisable=true;
    $scope.moreInfo={};


    $rootScope.go = function(path) {
        $location.url(path);
    };

    $scope.sort = function(keyname) {
        if ($scope.reportsHistory != null) {
            // set the sortKey to the param passed
            $scope.sortKey = keyname;
            // if true make it false and vice versa
            $scope.reverse = !$scope.reverse;
        }
    };

    $scope.init = function() {
        console.log("$localStorage.rptGrpId from reports controller - " + $localStorage.rptGrpId);
        var groupId = $localStorage.rptGrpId;//1;//
        ReportsService.getReportsList(groupId).then(function(data) {
            $scope.reportsList = data;
            //alert("reportsList "+JSON.stringify($scope.reportsList));
        });
        var getData = {
            "rptGroupId":groupId
        };
        ReportsService.getReportHistoryDetails(getData).then(function(data) {
            $scope.reportsHistory = data;
            $scope.reportsHistory.sort(function (a, b) {
                   return (a.requestId - b.requestId);
            });
        });
    };


    $scope.clearSelectedRow = function() {
        $scope.selectedRows = "";
        $scope.isOutFileDisable=true;
        $scope.isLogFileDisable=true;
        $scope.isMoreInfoDisable=true;
        $scope.isUnshceduleDisable=true;
    };

    $scope.clearSearch = function() {
        $scope.requestId = "";
        $scope.reportId = "";
        $scope.requestProcess = "";
        $scope.startDate = null;
        $scope.endDate = null;
        //alert($scope.endDate);
        $scope.clearSelectedRow();
        $scope.init();
    };
    $scope.selectRow = function(report) {
        $scope.isOutFileDisable=true;
        $scope.isLogFileDisable=true;
        $scope.isMoreInfoDisable=true;
        $scope.isUnshceduleDisable=true;
        $scope.selectedRows = report;
        if(report.requestStatus.toUpperCase().includes('COMPLETE') && report.requestStatus.toUpperCase().includes('NORMAL')){
            $scope.isOutFileDisable=false;
        }
        if(report.requestStatus.toUpperCase().includes('COMPLETE')){
            $scope.isLogFileDisable=false;
        }
        $scope.isMoreInfoDisable=false;
        ///alert(JSON.stringify(report));
        if(report.scheduleStaus!=null && report.scheduleStaus=="S"){
            //alert("HOllaaa");
            $scope.isUnshceduleDisable=false;
        }
    };
    $scope.isRowSelected = function(report) {
        //alert(report.requestId);
        if (report!=null && report.requestId!=null &&report.requestId == $scope.selectedRows.requestId) {
            return true;
        } else {
            return false;
        }
    };
    $scope.formatDate = function(date) {
        if(date!="" && date!=null){
            var dateOut = new Date(date);
            return dateOut;
        }else{
            return "";
        }
    };
    $scope.downloadReport = function() {
        ReportsService.downloadReport($scope.selectedRows.requestId).then(function(data) {
            if (data.errorMessage != null && data.errorMessage != "" && data.errorMessage != undefined) {
                $scope.failureTextAlert = data.errorMessage;
                $scope.showFailureAlert = true;
                $timeout(function() {
                    $scope.showSuccessAlert = false;
                    $scope.showFailureAlert = false;
                }, 2000);
            } else {
                $scope.genricDownload(data.downloadFile, data.downloadFileName);
            }
        });
    };

    $scope.downloadLogFile = function() {
        ReportsService.downloadLogFile($scope.selectedRows.requestId).then(function(data) {
            if (data.errorMessage != null && data.errorMessage != "" && data.errorMessage != undefined) {
                $scope.failureTextAlert = data.errorMessage;
                $scope.showFailureAlert = true;
                $timeout(function() {
                    $scope.showSuccessAlert = false;
                    $scope.showFailureAlert = false;
                }, 2000);
            } else {
                $scope.genricDownload(data.downloadFile, data.downloadFileName);
            }
        });
    };

    $scope.unscheduleReport = function() {
        ReportsService.unscheduleReport($scope.selectedRows.requestId).then(function(data) {
            if (data.errorMessage != null && data.errorMessage != "" && data.errorMessage != undefined) {
                $scope.failureTextAlert = data.errorMessage;
                $scope.showFailureAlert = true;
                $timeout(function() {
                    $scope.showSuccessAlert = false;
                    $scope.showFailureAlert = false;
                }, 2000);
            } else {
                $scope.successTextAlert = data.message;
                $scope.showSuccessAlert = true;
                $timeout(function() {
                    $scope.init();                
                    $scope.showSuccessAlert = false;
                    $scope.showFailureAlert = false;
                }, 2000);
            }
        });
    };
    $scope.generateReport = function(){
      $location.url("/reports");  
    };
    $scope.moreInfoRequest = function() {
        $("#more-info-modal").modal("hide");
        //alert($scope.selectedRows.requestId);
        ReportsService.moreInfo($scope.selectedRows.requestId).then(function(data) {
            if (data.errorMessage != null && data.errorMessage != "" && data.errorMessage != undefined) {
                //alert(JSON.stringify(data));
                $scope.failureTextAlert = data.errorMessage;
                $scope.showFailureAlert = true;
                $timeout(function() {
                    $scope.showSuccessAlert = false;
                    $scope.showFailureAlert = false;
                }, 2000);
            } else {
                $scope.moreInfo=data;
                $("#more-info-modal").modal("show");
            }
        });
    };

    $scope.genricDownload = function(fileContent, fileName) {
        var byteCharacters = atob(fileContent);
        var byteNumbers = new Array(byteCharacters.length);
        for (var i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        var byteArray = new Uint8Array(byteNumbers);

        var blob1 = new Blob([byteArray], {type: "application/octet-stream"});

        saveAs(blob1, fileName);
    };
    
    $scope.searchReports = function(){
        $scope.clearSelectedRow();
        $scope.searchCriteria = {};
        if ($scope.requestId != null && $scope.requestId != "" && $scope.requestId != undefined) {
            $scope.searchCriteria.requestId=$scope.requestId;
        }
        if ($scope.reportId != null && $scope.reportId != "" && $scope.reportId != undefined) {
            $scope.searchCriteria.reportId=$scope.reportId;
        }
        if ($scope.requestProcess != null && $scope.requestProcess != "" && $scope.requestProcess != undefined) {
            $scope.searchCriteria.requestProcess=$scope.requestProcess;
        }
        if ($scope.startDate != null && $scope.startDate != "" && $scope.startDate != undefined) {
            $scope.searchCriteria.startDate=$scope.startDate;
        }
        if ($scope.endDate != null && $scope.endDate != "" && $scope.endDate != undefined) {
            $scope.searchCriteria.endDate=$scope.endDate;
        }
        $scope.searchCriteria.rptGroupId = $localStorage.rptGrpId;
        //console.log(JSON.stringify($scope.searchCriteria));
        ReportsService.getReportHistoryDetails(JSON.stringify($scope.searchCriteria)).then(function(data) {
            $scope.reportsHistory = data;
            $scope.reportsHistory.sort(function (a, b) {
                   return (new Date(a.requestSubmitionDate).getTime() - new Date(b.requestSubmitionDate).getTime());
            });
            //console.log(JSON.stringify($scope.reportsHistory));
        });
    }

    $scope.init();
    //$scope.sort('requestId');
}