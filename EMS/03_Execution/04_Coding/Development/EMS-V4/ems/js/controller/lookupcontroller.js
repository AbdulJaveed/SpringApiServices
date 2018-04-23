'use strict';
angular.module('myApp.LookupController', []).controller('LookupController',
        LookupController);
LookupController.$inject = ['$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'LookupService', 'FlashService', '$timeout','appConstants'];
function LookupController($scope, $rootScope, $window, $location, $http,
        $localStorage, LookupService, FlashService, $timeout,appConstants) {
    var vm = this;
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.iscodeeditable =true;
    $scope.lookupdata = "";
    $scope.valuedata = [];
    $scope.lookupid = null;
    $scope.iseditable = true;
    $scope.rowSize = 5;
    $scope.isSelectedRow = null;
    $scope.selectedRowDetails = [];
    $scope.disable_Create = false;
    $scope.disable_Edit = true;
    $scope.disable_Delete = true;
    $scope.disable_View = true;
    $scope.successTextAlert = "";
    $scope.showSuccessAlert = false;

    $rootScope.go = function (path) {
        $location.url(path);
    }

    $scope.sort = function (keyname) {
        if ($scope.lookupdata != null) {
            // set the sortKey to the param passed
            $scope.sortKey = keyname;
            // if true make it false and vice versa
            $scope.reverse = !$scope.reverse;
        }
    }

    $timeout(function () {
        // getting the available operations assigned for the logged in user
        $scope.availOperations = $localStorage.availOperations;
    }, 400);

    $scope.init = function () {
        $rootScope.lookupDetails = [];
        /* -- Commented for stop initial loading list
        LookupService.getAllLookups().then(function (data) {
            $scope.lookupdata = data;
        }).catch(function(error){
            
            var errmsg=appConstants.exceptionMessage;
            if(error.errorMessage!=undefined){
                errmsg=error.errorMessage;
            }
            $scope.failureTextAlert = errmsg;
            $scope.showFailureAlert = true;
            $timeout(function () {
                    $scope.showFailureAlert= false;
            }, 3000);
            });
        */
        this.viewAlllookupsDetail();
    };

    $scope.selectRow = function (item) {
        // for checking single row selection
        $scope.isSelectedRow = item.id;
        toggleButtons();
        // adding selected row details
        if (item != undefined) {
            setLookupDetails(item);
        }
    }

    $scope.clearSelectedRow = function () {
        // clearing row selection
        $scope.isSelectedRow = null;
        toggleButtons();
    }

    function toggleButtons() {
        //		console.log("$scope.isSelectedRow:--"+$scope.isSelectedRow);
        // edit, view and delete button toggle
        if ($scope.isSelectedRow == null) {
            $scope.disable_Edit = true;
            $scope.disable_View = true;
            $scope.disable_Delete = true;
        } else {
            $scope.disable_Edit = false;
            $scope.disable_View = false;
            $scope.disable_Delete = false;
        }
    }

    function setLookupDetails(item) {
        //alert(JSON.stringify(item));
        $scope.selectedRowDetails = {
            id: item.id,
            lookupName: item.lookupName,
            lookupCode: item.lookupCode,
            active:item.active,
            osiLookupValueses: []
        };
        for (var k = 0; k < item.osiLookupValueses.length; k++) {
            var itemcode = item.osiLookupValueses[k].lookupValue;
            var itemdesc = item.osiLookupValueses[k].lookupDesc;
            var seq=item.osiLookupValueses[k].lookupSeqNum;
            $scope.selectedRowDetails.osiLookupValueses.push({
                'lookupValue': itemcode,
                'lookupDesc': itemdesc,
                'isselected': 'N',
                'lookupSeqNum':seq
            });
        }
        //        console.log("selected row details:- " + JSON.stringify($scope.selectedRowDetails));
    }

    $scope.operationsGenericFunction = function (doFunction, url) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            createLookup();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewLookupDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editLookupDetails($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

    //    $scope.viewitem = function (item) {
    function viewLookupDetails(item) {
        $scope.headername = "View Lookup";
        $rootScope.isTrascError = false;
        $scope.lookupid = item.id;
        $scope.lookupname = item.lookupName;
        $scope.lookupcode = item.lookupCode;
        $scope.selectAll=false;
        $scope.isUpdatable=false;
        //$scope.viewButton=false;
         if(item.active==1)
        {
            $('#cbk1').prop('checked',true);
            $scope.isActive=true;
        }else{
         $('#cbk1').prop('checked',false); 
          $scope.isActive=false;
        }
        $scope.iseditable = false;
        $scope.iscodeeditable =false;
        $scope.detailslookupvalues = item.osiLookupValueses;
        $scope.valuedata = [];
        for (var k = 0; k < item.osiLookupValueses.length; k++) {
            var itemcode = item.osiLookupValueses[k].lookupValue;
            var itemdesc = item.osiLookupValueses[k].lookupDesc;
            var seq=item.osiLookupValueses[k].lookupSeqNum;
            $scope.valuedata.push({
                'lookupvalue': itemcode,
                'lookupdesc': itemdesc,
                'isselected': 'N',
                'lookupSeqNum':seq,
                'id':item.osiLookupValueses[k].id
            });
        }
        $('#lookup-model').modal({show: true, backdrop: 'static'});
    }

    $scope.editSelectedRowDetails = function (item, lookupdata) {
        var ops = $scope.availOperations;
        var found = false;
        var selectedData = [];
        for (var i = 0; i < ops.length; i++) {
            if (ops[i].name == 'Edit') {
                found = true;
                break;
            }
        }
        for (var k = 0; k < lookupdata.length; k++) {
            if (lookupdata[k].id == item.id) {
                selectedData = lookupdata[k];
                break;
            }
        }
        //		console.log("selectedData:- " + JSON.stringify(selectedData));
        if (found && selectedData.id == item.id) {
            editLookupDetails(selectedData);
            $scope.isSelectedRow = item.id;
            toggleButtons();
        }
    }

    //    $scope.edititem = function (item) {
    function editLookupDetails(item) {
        $scope.headername = "Edit Lookup";
        $rootScope.isTrascError = false;
        $scope.lookupid = item.id;
        $scope.lookupname = item.lookupName;
        $scope.lookupcode = item.lookupCode;
        $scope.selectAll=false;
        $scope.isUpdatable=true;
        if(item.active===1)
        {
            $('#cbk1').prop('checked',true);
            $scope.isActive=true;
        }else{
            $('#cbk1').prop('checked',false);  
             $scope.isActive=false;
        }
        
        $scope.iseditable = true;
        $scope.iscodeeditable =false;
        $scope.detailslookupvalues = item.osiLookupValueses;
        $('#allSelect').prop('checked', false);
        $scope.valuedata = [];
        console.log("$scope.detailslookupvalues"
                + $scope.detailslookupvalues.length);
        for (var k = 0; k < item.osiLookupValueses.length; k++) {            
            var itemcode = item.osiLookupValueses[k].lookupValue;
            var itemdesc = item.osiLookupValueses[k].lookupDesc;
            var seq=item.osiLookupValueses[k].lookupSeqNum;
            console.log("itemcode:- " + itemcode + " & itemdesc:- " + itemdesc);
            $scope.valuedata.push({
                'lookupvalue': itemcode,
                'lookupdesc': itemdesc,
                'isselected': 'N',
                'isCbkeditable':'Y',
                'lookupSeqNum':seq,
                'id':item.osiLookupValueses[k].id
            });            
        }
       $('#lookup-model').modal({show: true, backdrop: 'static'});
    }

    //    $scope.openlookupmodal = function (e) {
    function createLookup() {
        $('#cbk1').prop('checked',true);
        $scope.headername = "Create Lookup";
        $rootScope.isTrascError = false;
        $scope.iseditable = true; 
        $scope.iscodeeditable =true;
        $scope.isUpdatable=false;
        $scope.lookupid = null;
        $('#allSelect').prop('checked', false);
        $scope.selectAll=true;
        $scope.valuedata = [];
        $scope.valuedata.push({
            'lookupvalue': "",
            'lookupdesc': "",
            'isselected': 'N',
            'isCbkeditable':'Y',
            'lookupSeqNum': ""
        });
        $scope.lookupname = "";
        $scope.lookupcode = "";
        $scope.lookupvalue = "";
        $scope.lookupdesc = "";
        $scope.isActive=true;
       $('#lookup-model').modal({show: true, backdrop: 'static'});
    }
    ;

    function showDeleteConfirmationPopup() {
        $('#lookup-delete-model').modal('show');
    }

    $scope.addRow = function () {
        $scope.cancontinue = true;
        if ($scope.valuedata.length > 0) {
            for (var indx = 0; indx < $scope.valuedata.length; indx++) {
                if($scope.valuedata[indx].lookupSeqNum.length === 0) {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Please enter lookup sequence.");
                    $scope.cancontinue = false;
                } else if ($scope.valuedata[indx].lookupvalue.length === 0) {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Please enter lookup value.");
                    $scope.cancontinue = false;
                } else if ($scope.valuedata[indx].lookupdesc.length === 0) {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Please enter lookup description.");
                    $scope.cancontinue = false;
                } else {
                    $scope.cancontinue = true;
                }
            }
            if ($scope.cancontinue) {
                $scope.valuedata.push({
                    'lookupvalue': "",
                    'lookupdesc': "",
                    'isselected': 'N',
                    'isCbkeditable':'Y',
                    'lookupSeqNum': ""
                });
            }
        } else {
            $scope.valuedata.push({
                'lookupvalue': "",
                'lookupdesc': "",
                'isselected': 'N',
                'isCbkeditable':'Y',
                'lookupSeqNum': ""
            });
        }
    };

    $scope.removeRow = function () {
        // remove the row specified in index
        var newDataList = [];
        // $scope.selectedAll = false;
        angular.forEach($scope.valuedata, function (selected) {
            if (!selected.selected) {
                newDataList.push(selected);
            }
        });
        $scope.valuedata = newDataList;
        if($scope.valuedata.length ==0){
        	 $scope.valuedata.push({
               'lookupvalue': "",
               'lookupdesc': "",
               'isselected': 'N',
               'isCbkeditable':'Y',
               'lookupSeqNum': ""
           });
        }
        $('#allSelect').prop('checked', false);
    };
    // newly added for select all
    $scope.toggleAll = function() {
        var toggleStatus = $scope.isAllSelected;
        angular.forEach($scope.valuedata, function(itm){ itm.selected = toggleStatus; });
    }
    $scope.optionToggled = function(){
        $scope.isAllSelected = $scope.valuedata.every(function(itm){ return itm.selected; })
    }
    // $scope.removeselectall = function () {
    //     console.log("Clicked" + $scope.allbox.selected);
    //     var newDataList = [];
    //     if ($scope.allbox.selected) {
    //         for (var indx = 0; indx < $scope.valuedata.length; indx++) {
    //             var isselected = "Y";
    //             if($scope.valuedata[indx].isCbkeditable=="N") {
    //                isselected = "N"; 
    //             }
    //             newDataList.push({
    //                 'lookupvalue': $scope.valuedata[indx].lookupvalue,
    //                 'lookupdesc': $scope.valuedata[indx].lookupdesc,
    //                 'isselected': 'Y',
    //                 'isCbkeditable':$scope.valuedata[indx].isCbkeditable,
    //                 'lookupSeqNum': $scope.valuedata[indx].lookupSeqNum
    //             });
    //         }
    //     } else {
    //         for (var indx = 0; indx < $scope.valuedata.length; indx++) {
    //             newDataList.push({
    //                 'lookupvalue': $scope.valuedata[indx].lookupvalue,
    //                 'lookupdesc': $scope.valuedata[indx].lookupdesc,
    //                 'isselected': 'N',
    //                 'isCbkeditable':$scope.valuedata[indx].isCbkeditable,
    //                 'lookupSeqNum': $scope.valuedata[indx].lookupSeqNum
    //             });
    //         }
    //     }
    //     $scope.valuedata = newDataList;
    // };

    $scope.searchLookups = function () {
        var searchName = $scope.name;
        var searchCode = $scope.code;
        $scope.continueSearch = true;
        $rootScope.isTrascError = false;
        if ($scope.continueSearch) {
            $scope.searchData = {
                lookupName: searchName,
                lookupCode: searchCode
            };
            var lookupdata = JSON.stringify($scope.searchData);
            LookupService.searchLookups(lookupdata).then(function (data) {
                $scope.lookupdata = data;
            }).catch(function(error){
                var errmsg=appConstants.exceptionMessage;
                if(error.errorMessage!=undefined){
                    errmsg=error.errorMessage;
                }
                $scope.failureTextAlert = errmsg;
                $scope.showFailureAlert = true;
                $timeout(function () {
                        $scope.showFailureAlert= false;
                }, 3000);
            });
        }
    };

    $scope.clearSearch = function () {
        $scope.name = "";
        $scope.code = "";
        $scope.clearSelectedRow();
        $scope.lookupdata="";
    };

    $scope.viewAlllookupsDetail = function () {
        $rootScope.lookupHeader = {
            id: 0,
            lookupName: 0,
            lookupCode: 1,
            active:1,
            osiLookupValueses: []
        };
    };   
    $scope.isInactivateLookup=function(){
        var active=$('#cbk1').prop('checked');
        if(!active){
           if($scope.lookupid!=null){
            LookupService.isInactivateLookup($scope.lookupid).then(function (data) {
               if(data.inactivable==1){
                 $('#cbk1').prop('checked',false);  
               }else{
                 $rootScope.isTrascError = true;
                 FlashService.Error("Cannot deactivate this lookup because it is used by some other function");
                 $('#cbk1').prop('checked',true); 
               }
             });
            } 
         }
    };
    
    $scope.saveLookup = function () {
        var lookupname = $scope.lookupname;
        var lookupcode = $scope.lookupcode;
        var isactive =0;
        $scope.continuesave = true;
        if (angular.isUndefined(lookupname) || lookupname === null || lookupname==="" ) {
            $rootScope.isTrascError = true;
            FlashService.Error("Please enter lookup name.");
            $scope.continuesave = false;
        }else if (lookupname.search(/[a-zA-Z0-9]/) === -1){  
            $rootScope.isTrascError = true;
            FlashService.Error("Please enter a valid lookup name");
            $scope.continuesave = false;
         }else if (angular.isUndefined(lookupcode) || lookupcode.length ===null ||lookupcode==="") {
            $rootScope.isTrascError = true;
            FlashService.Error("Please enter lookup code.");
            $scope.continuesave = false;
        }else if (lookupcode.search(/[a-zA-Z0-9]/) === -1){
            $rootScope.isTrascError = true;
            FlashService.Error("Please enter a valid lookup code");
            $scope.continuesave = false;
        } else if($('#cbk1').prop('checked')) {
          isactive =1;
        } 
        else {
            $scope.continuesave = true;
            $rootScope.isTrascError = false;
        }
        if ($scope.continuesave) {
            $rootScope.lookupHeader.id = $scope.lookupid;
            $rootScope.lookupHeader.lookupName = lookupname;
            $rootScope.lookupHeader.lookupCode = lookupcode;
            $rootScope.lookupHeader.active = isactive;
            $rootScope.lookupDetails = [];
            console.log("$scope.valuedata.length" + $scope.valuedata.length);
            if($scope.valuedata.length > 0) {
                
                for (var indx1 = 0; indx1 < $scope.valuedata.length; indx1++) {

                    for (var indx2 = 0; indx2 < $scope.valuedata.length; indx2++) {

                        if (indx1 !== indx2)
                        {
                            if ($scope.valuedata[indx1].lookupdesc === $scope.valuedata[indx2].lookupdesc)
                            {
                                $rootScope.isTrascError = true;
                                FlashService.Error("Duplicate description.");
                                $scope.continuesave = false;
                            }
                        }

                    }

                }
                
                for (var indx = 0; indx < $scope.valuedata.length; indx++) {
                    $scope.lookupvalue1 = $scope.valuedata[indx].lookupvalue;
                    $scope.lookupdesc1 = $scope.valuedata[indx].lookupdesc;
                    $scope.lookupSeqNum = $scope.valuedata[indx].lookupSeqNum;
                    if ($scope.lookupvalue1.length == 0) {
                        $rootScope.isTrascError = true;
                        FlashService.Error("Please enter Lookup value.");
                        $scope.continuesave = false;
                    } 
                    else if ($scope.lookupdesc1.length == 0) {
                        $rootScope.isTrascError = true;
                        FlashService.Error("Please enter Lookup description.");
                        $scope.continuesave = false;
                    }
                    else if($scope.valuedata[indx].lookupdesc!= undefined && $scope.valuedata[indx].lookupdesc.length > 500) {
                        $rootScope.isTrascError = true;
                        FlashService
                                .Error("Lookup description should not accept more than 500 characters.");
                        $scope.continuesave = false;
                    } else if ($scope.lookupSeqNum.length == 0) {
                        $rootScope.isTrascError = true;
                        FlashService.Error("Please enter Lookup sequence.");
                        $scope.continuesave = false;
                    }
                    else {
                        $scope.tempselecteditems = {
                            id:$scope.valuedata[indx].id,
                            lookupSeqNum:$scope.valuedata[indx].lookupSeqNum,
                            lookupValue: $scope.lookupvalue1,
                            lookupDesc: $scope.lookupdesc1
                        };
                        $rootScope.lookupDetails.push($scope.tempselecteditems);
                    }
                }
            } else {
                $rootScope.isTrascError = true;
                FlashService.Error("Please enter lookup value.");
                $scope.continuesave = false;
            }
            $rootScope.lookupHeader.osiLookupValueses = $rootScope.lookupDetails;
            var lookupdata = JSON.stringify($rootScope.lookupHeader);
            console.log("lookupdatdda::::::::::" + lookupdata);
            //$window.location.href ='lookup/';
            // $rootScope.go("lookup/"); 
            if ($scope.continuesave) {
                if ($scope.lookupid == null) {
                    LookupService
                            .saveLookups(lookupdata)
                            .then(
                                    function (result) {
                                        if (result.httpStatus == 200) {
                                            $('#lookup-model').modal('hide');
                                            $scope.init();
                                            $scope.successTextAlert = appConstants.successMessage;
                                            $scope.showSuccessAlert = true;
                                            $scope.searchLookups();
                                        } else {
                                            $rootScope.isTrascError = true;
                                            FlashService.Error(result.errorMessage);
                                            $scope.continuesave = false;
                                        }
                                        $timeout(function () {
                                            $scope.showSuccessAlert = false;
                                        }, 5000);
                                    }).catch(function(error){
                                        var errmsg=appConstants.exceptionMessage;
                                        if(error.errorMessage!=undefined){
                                            errmsg=error.errorMessage;
                                            $rootScope.isTrascError = true;
                                        }
                                        
                                        FlashService.Error(errmsg);
                                        $timeout(function () {
                                                $rootScope.isTrascError= false;
                                        }, 3000);
                                        
                                    });
                } else {
                    LookupService
                            .updateLookups(lookupdata)
                            .then(
                                    function (result) {
                                        if (result.httpStatus == 200) {
                                            $('#lookup-model').modal('hide');
                                            $scope.init();
                                            $scope.successTextAlert = appConstants.successMessage;
                                            $scope.showSuccessAlert = true;
                                            $scope.searchLookups();
                                            $scope.clearSelectedRow();
                                        } else {
                                            $rootScope.isTrascError = true;
                                            FlashService.Error(result.errorMessage);
                                            $scope.continuesave = false;
                                        }
                                        $timeout(function () {
                                            $scope.showSuccessAlert = false;
                                        }, 5000);
                                    }).catch(function(error){
                                        var errmsg=appConstants.exceptionMessage;
                                        if(error.errorMessage!=undefined){
                                            errmsg=error.errorMessage;
                                            $rootScope.isTrascError = true;
                                        }                                       
                                        FlashService.Error(errmsg);
                                        $timeout(function () {
                                                $rootScope.isTrascError= false;
                                        }, 3000);
                                        
                                    });
                }
            }

        }
    };
    $scope.init();
    //$scope.sort('lookupCode');
}