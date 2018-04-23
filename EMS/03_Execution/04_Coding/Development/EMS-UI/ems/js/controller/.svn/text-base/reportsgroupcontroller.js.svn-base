'use strict';
angular.module('myApp.ReportsGroupController', [])
        .controller('ReportsGroupController', ReportsGroupController);
ReportsGroupController.$inject = ['$scope', '$rootScope', '$window', '$location',
    '$timeout', '$localStorage', 'FlashService', 'ReportsGroupService'];
function ReportsGroupController($scope, $rootScope, $window, $location, $timeout,
        $localStorage, FlashService, ReportsGroupService) {
    var vm = this;
//    $rootScope.isheader = true;
//    $rootScope.isTrascError = false;
    $scope.rowSize = 8;
    $scope.isSelectedRow = null;
    $scope.reportsGroupList = "";
    $scope.rptGrp = "";
    $scope.totalavailableReports = [];
    $scope.availableReports = [];
    $scope.includedReports = [];
    $scope.isActive = false;
    $rootScope.go = function(path) {
        $location.url(path);
    }
//	var validationError=0;
//	var dateError=0;
    $scope.sort = function(keyname) {
        if ($scope.reportsGroupList != null) {
            // set the sortKey to the param passed
            $scope.sortKey = keyname;
            // if true make it false and vice versa
            $scope.reverse = !$scope.reverse;
        }
    }

    $timeout(function() {
        // getting the available operations assigned for the logged in user
        $scope.availOperations = $localStorage.availOperations;
        $scope.sort('rptGrpName');
    }, 400);

    $scope.operationsGenericFunction = function(doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            createReportGroup();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewReportGroup($scope.isSelectedRow);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editReportGroup($scope.isSelectedRow);
        }
    };

    function createReportGroup() {
        $scope.isActive=false;
        $scope.availableReports = [];
        $scope.includedReports = [];
        if ($scope.totalavailableReports.length > 0) {
            angular.forEach($scope.totalavailableReports, function(report) {
                $scope.availableReports.push(report);
            });
        }
        $scope.rptGrp = "";
        $scope.rptGrpHeading = "Create";
        $scope.iseditable = true;
        vm.showSave = true;
        vm.showUpdate = false;
        $('#reports-group-modal').modal({backdrop: 'static', keyboard: false});
    }
    ;

    function editReportGroup(id) {
        var check = getReportGroup(id);
        if (check) {
            $scope.rptGrpHeading = "Edit";
            $scope.iseditable = true;
            vm.showSave = false;
            vm.showUpdate = true;
            $('#reports-group-modal').modal({backdrop: 'static', keyboard: false});
        }
    }
    ;

    function viewReportGroup(id) {
        var check = getReportGroup(id);
        if (check) {
            $scope.rptGrpHeading = "View";
            $scope.iseditable = false;
            vm.showSave = false;
            vm.showUpdate = false;
            $('#reports-group-modal').modal({backdrop: 'static', keyboard: false});
        }
    }
    ;

    function getReportGroup(id) {
        $scope.availableReports = [];
        $scope.includedReports = [];
        $scope.rptGrp = "";
        ReportsGroupService.getSingleReportGroup(id).then(function(data) {
            $scope.rptGrp = data;
            //alert($scope.rptGrp);
            if($scope.rptGrp.active==1){
                $scope.isActive=true;
            }else{
                $scope.isActive=false;
            }
            if ($scope.totalavailableReports.length > 0) {
                angular.forEach($scope.totalavailableReports, function(report) {
                    $scope.availableReports.push(report);
                });
            }
            if ($scope.rptGrp.osiRptGrpRpts.length > 0) {
                angular.forEach($scope.rptGrp.osiRptGrpRpts, function(report) {
                    $scope.includedReports.push(report.osiReports)
                });
                if (!angular.isUndefined($scope.includedReports) && $scope.includedReports.length > 0) {
                    for (var i = 0; i < $scope.includedReports.length; i++) {
                        for (var j = 0; j < $scope.availableReports.length; j++) {
                            if ($scope.availableReports[j].reportId === $scope.includedReports[i].reportId) {
                                $scope.availableReports.splice(j, 1);
                                break;
                            }
                        }
                    }
                }
            }
        });
        return true;
    }
    ;

    $scope.saveReportGroup = function(reportGroup) {
        if (reportGroup.rptGrpName != null && reportGroup.rptGrpName != "" && reportGroup.rptGrpName != undefined) {
            if ($scope.includedReports.length >= 1 && $scope.includedReports.length != undefined) {
                reportGroup.osiRptGrpRpts = [];
                //alert($scope.isActive);
                if ($scope.isActive) {
                    reportGroup.active = 1;
                } else {
                    reportGroup.active = 0;
                }
                angular.forEach($scope.includedReports, function(report) {
                    var rptGrpObject = {"rptGrpRptsId": ""};
                    rptGrpObject.osiReports = report;
                    reportGroup.osiRptGrpRpts.push(rptGrpObject)
                });
                if (reportGroup.rptGrpId != null && reportGroup.rptGrpId != "" && reportGroup.rptGrpId != undefined) {
                    ReportsGroupService.saveReportGroup(JSON.stringify(reportGroup)).then(function(data) {
                       if (data.errorMessage != null && data.errorMessage != "" && data.errorMessage != undefined) {
                           $rootScope.isTrascError = true;
                           FlashService.Error(data.errorMessage);
                           $timeout(function() {
                               $rootScope.isTrascError = false;
                           }, 2000);
                       } else {
                           $scope.successTextAlert = data.message;
                           $scope.showSuccessAlert = true;
                           $timeout(function() {
                               $scope.init();                
                               $scope.showSuccessAlert = false;
                               $scope.showFailureAlert = false;
                           }, 2000);
                           $('#reports-group-modal').modal("hide");
                        $scope.init();
                       }

                   });
               }else{
                    ReportsGroupService.updateReportGroup(JSON.stringify(reportGroup)).then(function(data) {
                       if (data.errorMessage != null && data.errorMessage != "" && data.errorMessage != undefined) {
                           $rootScope.isTrascError = true;
                           FlashService.Error(data.errorMessage);
                           $timeout(function() {
                               $rootScope.isTrascError = false;
                           }, 2000);
                       } else {
                           $scope.successTextAlert = data.message;
                           $scope.showSuccessAlert = true;
                           $timeout(function() {
                               $scope.init();                
                               $scope.showSuccessAlert = false;
                               $scope.showFailureAlert = false;
                           }, 2000);
                           $('#reports-group-modal').modal("hide");
                        $scope.init();
                       }
                    });
                }
                //alert(JSON.stringify(reportGroup));
            } else {
                $rootScope.isTrascError = true;
                FlashService.Error("Please select atleast one report");
                $timeout(function() {
                    $rootScope.isTrascError = false;
                }, 2000);
            }

        } else {
            $rootScope.isTrascError = true;
            FlashService.Error("Please Enter Report Group Name");
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }
        
    };

    $scope.selectRow = function(id) {
        // for checking single row selection
        $scope.isSelectedRow = id;
        toggleButtons();
    };

    $scope.clearSelectedRow = function() {
        $scope.isSelectedRow = null;
        toggleButtons();
    };

    function toggleButtons() {
        if ($scope.isSelectedRow == null) {
            $scope.disable_Edit = true;
            $scope.disable_View = true;
        } else {
            $scope.disable_Edit = false;
            $scope.disable_View = false;
        }
    }

    $scope.init = function() {
        $scope.reportsGroupList = "";
        var getData = {};
        ReportsGroupService.getReportsGroupList(getData).then(function(data) {
            $scope.reportsGroupList = data;
            $scope.clearSelectedRow();
        });
        ReportsGroupService.getCompleteReprotsList().then(function(data) {
            $scope.totalavailableReports = data;
            $scope.availableReports = data;
        });
    };

    $scope.clearSearch = function() {
        $scope.rptGrpName = "";
        $scope.clearSelectedRow();
        $scope.init();
    };

    $scope.searchReportsGroup = function() {
        $scope.reportsGroupList = "";
        $scope.clearSelectedRow();
        $scope.searchCriteria = {};
        if ($scope.rptGrpName != null && $scope.rptGrpName != "" && $scope.rptGrpName != undefined) {
            $scope.searchCriteria.rptGrpName = $scope.rptGrpName;
        }
        ReportsGroupService.getReportsGroupList(JSON.stringify($scope.searchCriteria)).then(function(data) {
            $scope.reportsGroupList = data;
            $scope.clearSelectedRow();
        });
    };

//***multi select related****//
    $scope.includeAllReports = function() {
        if ($scope.iseditable) {
//            if ($scope.approvesupplier === "N") {
//                $rootScope.isTrascError = true;
//                FlashService.Error("Please select user accredited supplier in main tab");
//                $scope.cancontinue = false;
//            } else {
            if ($scope.availableReports.length > 0) {
                angular.forEach($scope.availableReports, function(report) {
                    $scope.includedReports.push(report);
                });
//                    var seen = {};
//                    var duplicateSuppliers = [];
//                    //You can filter based on Id or Name based on the requirement
//                    var uniqueSuppliers = $scope.includedSuppliers.filter(function(item) {
//                        if (seen.hasOwnProperty(item.supplierSiteId)) {
//                            duplicateSuppliers.push(item.supplierName);
//                            return false;
//                        } else {
//                            seen[item.supplierSiteId] = true;
//                            return true;
//                        }
//                    });
//                    if (duplicateSuppliers.length > 0) {
//                        alert("Supplier already exist:- " + JSON.stringify(duplicateSuppliers));
//                    }
                //$scope.includedSuppliers = uniqueSuppliers;
                $scope.availableReports = [];
                //clearFields();
                $scope.selectedReports = [];
                $scope.existingReports = [];
            }
        }
        //}
    };

    $scope.excludeAllReports = function() {
        if ($scope.iseditable && $scope.includedReports.length > 0) {
            $scope.availableReports = [];
            angular.forEach($scope.totalavailableReports, function(report) {
                $scope.availableReports.push(report);
            });
//            var seen = {};
//            //You can filter based on Id or Name based on the requirement
//            var uniqueSuppliers = $scope.availableSuppliersNames.filter(function(item) {
//                if (seen.hasOwnProperty(item.supplierSiteId)) {
//                    return false;
//                } else {
//                    seen[item.supplierSiteId] = true;
//                    return true;
//                }
//            });
//            $scope.availableSuppliersNames = uniqueSuppliers;
            $scope.includedReports = [];
            //clearFields();
            $scope.selectedReports = [];
            $scope.existingReports = [];
        }
    };

    $scope.includeSelectedReport = function(reports) {
        if ($scope.iseditable) {

            if (!angular.isUndefined(reports) && reports.length > 0) {
                for (var i = 0; i < angular.fromJson(reports).length; i++) {
                    for (var j = 0; j < $scope.availableReports.length; j++) {
                        if ($scope.availableReports[j].reportId === angular.fromJson(reports[i]).reportId) {
                            $scope.availableReports.splice(j, 1);
                            break;
                        }
                    }
                    $scope.includedReports.push(angular.fromJson(reports[i]));
                }

            } else {
                $rootScope.isTrascError = true;
                FlashService.Error("Please select any report");
                $timeout(function() {
                    $rootScope.isTrascError = false;
                }, 2000);
            }
            $scope.selectedReports = [];
            $scope.existingReports = [];
        }

    };

    $scope.excludeSelectedReports = function(reports) {
        if ($scope.iseditable) {
            if (!angular.isUndefined(reports) && reports.length > 0) {
                for (var i = 0; i < angular.fromJson(reports).length; i++) {
                    for (var j = 0; j < $scope.includedReports.length; j++) {
                        if ($scope.includedReports[j].reportId === angular.fromJson(reports[i]).reportId) {
                            $scope.includedReports.splice(j, 1);
                            break;
                        }
                    }
                    $scope.availableReports.push(angular.fromJson(reports[i]));
                }
                $scope.selectedReports = [];
                $scope.existingReports = [];
            } else {
                $rootScope.isTrascError = true;
                FlashService.Error("Please select any report");
                $timeout(function() {
                    $rootScope.isTrascError = false;
                }, 2000);
            }
        }
    };

    $scope.init();

}