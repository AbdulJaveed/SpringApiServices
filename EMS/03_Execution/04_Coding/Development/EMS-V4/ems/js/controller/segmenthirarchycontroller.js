'use strict';
angular.module('myApp.SegmenthirarchyController', []).controller(
    'SegmenthirarchyController', SegmenthirarchyController);
SegmenthirarchyController.$inject = ['$scope', '$rootScope', '$window',
    '$location', '$http', '$localStorage', '$timeout',
    'LookupService', 'FlashService', 'SegmenthirarchyService', 'appConstants'
];

function SegmenthirarchyController($scope, $rootScope, $window, $location,
    $http, $localStorage, $timeout, LookupService, FlashService,
    SegmenthirarchyService, appConstants) {
    $rootScope.isheader = true;
    $rootScope.isTrascError = false;
    $scope.segmentdata = "";
    $scope.segmentHeaderData = "";
    $scope.segmentid = null;
    $scope.iseditable = true;
    $scope.disableduringedit =true;
    $scope.rowSize = 5;
    $scope.oneSegmentData = "";
    $scope.isSelectedRow = null;
    $scope.selectedRowDetails = [];
    $scope.disable_Create = false;
    $scope.disable_Edit = true;
    $scope.disable_Delete = true;
    $scope.disable_View = true;
    $scope.successTextAlert = "";
    $scope.showSuccessAlert = false;

    $scope.sort = function(keyname) {
        if ($scope.segmentdata != null) {
            // set the sortKey to the param passed
            $scope.sortKey = keyname;
            // if true make it false and vice versa
            $scope.reverse = !$scope.reverse;
        }
    }

    $timeout(function() {
        // getting the avialable operations assigned for the logged in user
        $scope.availOperations = $localStorage.availOperations;
        console.log("$scope.availOperations--" + JSON.stringify($scope.availOperations));
    }, 400);

    $scope.init = function() {
        $rootScope.segmentDetails = [];
        
        LookupService.getAllActiveLookups().then(function(data) {
        	$scope.lookupdata = data;
        	/*SegmenthirarchyService.getAllSegments().then(function(data) {
                $scope.segmentdata = data;
            }).catch(function(error) {
            	$rootScope.isTrascErr = true;
            	var msg = appConstants.exceptionMessage;
	       		  if(error.data.httpStatus){ 
	       			  msg=error.data.errorMessage; 
	       		  }
            		FlashService.Error(msg);
                $timeout(function () {
                $rootScope.isTrascErr=false;
                }, 5000);
        	}); */
        }).catch(function(error) {
        	$rootScope.isTrascErr = true;
        	var msg = appConstants.exceptionMessage;
     		  if(error.data.httpStatus){ 
     			  msg=error.data.errorMessage; 
     		  }
      		FlashService.Error(msg);
            $timeout(function () {
            $rootScope.isTrascErr=false;
            }, 5000);
    	});
        // -- Commented for stop initial loading list
        /*SegmenthirarchyService.getAllSegments().then(function(data) {
            console.log('segmentdata:- ' + JSON.stringify(data));
            $scope.segmentdata = data;
        }).catch(function(data) {
        	$rootScope.isTrascErr = true;
        	if(data.errorMessage !== undefined && data.errorMessage !== null)
        		FlashService.Error(data.errorMessage);
        	else
        		FlashService.Error(appConstants.exceptionMessage);
            $timeout(function () {
            $rootScope.isTrascErr=false;
            }, 5000);
    	});*/
        this.viewAllsegmentDetail();
    };

    $scope.selectRow = function(segment) {
        // for checking single row selection
        $scope.isSelectedRow = segment.segmentStructureHdrId;
        toggleButtons();
        // adding selected row details
        if (segment != undefined) {
            setSegmentDetails(segment);
        }
    }

    $scope.clearSelectedRow = function() {
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

    function setSegmentDetails(segment) {
        $scope.selectedRowDetails = {
            segmentStructureHdrId: segment.segmentStructureHdrId,
            segmentStructureHdrDesc: segment.segmentStructureHdrDesc,
            osiSegmentStructureDetailses: []
        };
        //    	console.log("osiSegmentStructureDetailses:- "+$scope.osiSegmentStructureDetailses.length);
        if ($scope.osiSegmentStructureDetailses != undefined && $scope.osiSegmentStructureDetailses.length > 0) {
            for (var k = 0; k < segment.osiSegmentStructureDetailses.length; k++) {
                var seqno = $scope.osiSegmentStructureDetailses[k].segmentStructureDetailsSeq;
                var segname = $scope.osiSegmentStructureDetailses[k].segmentStructureDetailsDesc;
                var lookup = $scope.osiSegmentStructureDetailses[k].lovDataForValidation;
                var isSqlReqdForValidations = $scope.detailslookupvalues[k].isSqlReqdForValidation;
                var sqlQueryForValidation = $scope.detailslookupvalues[k].sqlQueryForValidation;
                var isSqlReqdForValidation = false;
                if(isSqlReqdForValidations===1){
                    isSqlReqdForValidation = true;
                }
                $scope.osiSegmentStructureDetailses.push({
                    'segmentStructureDetailsSeq': seqno,
                    'segmentStructureDetailsDesc': segname,
                    'lovDataForValidation': lookup,
                    'isSqlReqdForValidation': isSqlReqdForValidation,
                    'sqlQueryForValidation1': sqlQueryForValidation,
                    'isSqlReqdForValidations': isSqlReqdForValidations,
                    'isselected': 'N',
                    'isCbkeditable':'N'
                });
            }
        }
        //        console.log("selected row details:- " + JSON.stringify($scope.selectedRowDetails));
    }

    $scope.operationsGenericFunction = function(doFunction, url, segmentdata) {
        // passing selected operation url
        $scope.opeationsURL = url;
        if (doFunction === "Create") {
            createSegment();
        }
        if (doFunction === "View" && !$scope.disable_View) {
            viewSegment($scope.selectedRowDetails);
        }
        if (doFunction === "Edit" && !$scope.disable_Edit) {
            editSegment($scope.selectedRowDetails);
        }
        if (doFunction === "Delete" && !$scope.disable_Delete) {
            showDeleteConfirmationPopup();
        }
    }

    $scope.getSegmentHeaderDescData = function(segment) {
        $scope.headerDesc = segment.segmentStructureHdrDesc;
        SegmenthirarchyService.getSegmentHeaders(segment.segmentStructureHdrId).then(function(data) {
            $scope.segmentHeaderData = data;
            if ($scope.segmentHeaderData.length > 0) {
                angular.forEach($scope.segmentHeaderData, function(value, key) {
                    if ($scope.headerDesc === value.name) {
                        $scope.headername = {
                            "businessGroupId": null,
                            "name": value.name,
                            "value": value.value
                        };
                    }
                });
            }
        }).catch(function(error) {
        	$rootScope.isTrascError = true;
        	$rootScope.isTrascErr = true;
        	var msg = appConstants.exceptionMessage;
     		  if(error.data.httpStatus){ 
     			  msg=error.data.errorMessage; 
     		  }
      		FlashService.Error(msg);
            $timeout(function () {
            $rootScope.isTrascError = false;
            $rootScope.isTrascErr = false;
            }, 5000);
    	});
    };

    $scope.getSegmentHeaderDetailsData = function(segment) {
        $scope.segmentid = segment.segmentStructureHdrId;
        $scope.valuedata="";
        SegmenthirarchyService
            .getSegmentStructureByHdrId(segment.segmentStructureHdrId)
            .then(
                function(data) {
                    $scope.oneSegmentData = data;
                    $scope.detailslookupvalues = [];
                    $scope.valuedata = [];
                    if($scope.oneSegmentData.osiSegmentStructureDetailses)
                        $scope.detailslookupvalues = $scope.oneSegmentData.osiSegmentStructureDetailses;
                    if ($scope.detailslookupvalues.length > 0) {
                      
                        for (var k = 0; k < $scope.detailslookupvalues.length; k++) {
                            var seqno = $scope.detailslookupvalues[k].segmentStructureDetailsSeq;
                            var segname = $scope.detailslookupvalues[k].segmentStructureDetailsDesc;
                            var lookup = $scope.detailslookupvalues[k].lovDataForValidation;
                            var isSqlReqdForValidations = $scope.detailslookupvalues[k].isSqlReqdForValidation;
                            var sqlQueryForValidation = $scope.detailslookupvalues[k].sqlQueryForValidation;
                            var isSqlReqdForValidation = false;
                            if(isSqlReqdForValidations===1){
                                isSqlReqdForValidation = true;
                            }
                            $scope.valuedata.push({
                                'seqno': seqno,
                                'segname': segname,
                                'lookup': lookup,
                                'isSqlReqdForValidation': isSqlReqdForValidation,
                                'isSqlReqdForValidations': isSqlReqdForValidations,
                                'isselected': 'N',
                                'isCbkeditable':'N',
                                'sqlQuery':sqlQueryForValidation
                            });
                        }
                        var sortedArray = $scope.valuedata.sort(function(a,b){
                            return a.seqno >b.seqno?1:a.seqno <b.seqno?-1:0
                           });
                           console.log(sortedArray);
                    }else{
                        $scope.valuedata.push({
                            'seqno': '',
                            'segname': '',
                            'lookup': '',
                            'isSqlReqdForValidation': true,
                            'isSqlReqdForValidations':1,
                            'isselected': 'N',
                            'isCbkeditable':'N',
                            'sqlQuery':''
                        });
                    }
                }).catch(function(error) {
                	$rootScope.isTrascError = true;
                	$rootScope.isTrascErr = true;
                	var msg = appConstants.exceptionMessage;
	  	       		/*  if(error.data.httpStatus){ 
	  	       			  msg=error.data.errorMessage; 
	  	       		  } */
              		FlashService.Error(msg);
                    $timeout(function () {
                    $rootScope.isTrascError = false;
                    $rootScope.isTrascErr = false;
                    }, 5000);
            	});
    };

    //	$scope.viewSegment = function(segment) {
    function viewSegment(segment) {
        $scope.heading = "View Segment Structure";
        $rootScope.isTrascError = false;
        $scope.iseditable = false;
        $scope.disableduringedit =false;
        $scope.isUpdatable=false;
        $scope.selectAll=false;
        $scope.getSegmentHeaderDescData(segment);
        $scope.getSegmentHeaderDetailsData(segment);
        $('#segment_model').modal({show: true, backdrop: 'static'});
    };

    $scope.editSelectedRowDetails = function(segment, segmentdata) {
        var ops = $scope.availOperations;
        var found = false;
        var selectedData = [];
        for (var i = 0; i < ops.length; i++) {
            if (ops[i].name == 'Edit') {
                found = true;
                break;
            }
        }
        for (var k = 0; k < segmentdata.length; k++) {
            if (segmentdata[k].segmentStructureHdrId == segment.segmentStructureHdrId) {
                selectedData = segmentdata[k];
                break;
            }
        }
        //		console.log("selectedData:- " + JSON.stringify(selectedData));
        if (found && selectedData.segmentStructureHdrId == segment.segmentStructureHdrId) {
            editSegment(selectedData);
            $scope.isSelectedRow = segment.segmentStructureHdrId;
            toggleButtons();
        }
    }

    //	$scope.editSegment = function(segment) {
    function editSegment(segment) {
        $scope.heading = "Edit Segment Structure";
        $rootScope.isTrascError = false;
        $scope.iseditable = true;
        $scope.isUpdatable=true;
        $scope.selectAll=false;
        $('#allSelect').prop('checked', false);
        $scope.getSegmentHeaderDescData(segment);
        $scope.getSegmentHeaderDetailsData(segment);
        $('#segment_model').modal({show: true, backdrop: 'static'});
    };

    //	$scope.opensegmentmodal = function(e) {
    function createSegment() {
        $scope.heading = "Create Segment Structure";
        $scope.cancontinue = true;
        $rootScope.isTrascError = false;
        $scope.selectAll=true;  
        $('#allSelect').prop('checked', false);
        $scope.iseditable = true;
        $scope.disableduringedit =false;
        $scope.isUpdatable=false;
        $scope.segmentid = null;
        $scope.valuedata = [];
        $scope.valuedata.push({
            'seqno': "",
            'segname': "",
            'lookup': "",
            'isSqlReqdForValidation': false,
            'isSqlReqdForValidations': 0,
            'sqlQuery': "",
            'isselected': 'N',
            'isCbkeditable':'Y'
        });
        $scope.oneSegmentData = "";
        $scope.detailslookupvalues = [];
        $scope.headername = "";
        $scope.seqno = "";
        $scope.segname = "";
        $scope.lookup = "";
        $('#allSelect').prop('checked', false);
        SegmenthirarchyService.getSegmentHeaders(0).then(function(data) {
        //    alert(JSON.stringify(data));
            $scope.segmentHeaderData="";
            
            if(!data.errorCode){
             $scope.segmentHeaderData = data;
            // alert(JSON.stringify($scope.segmentHeaderData));
              $('#segment_model').modal({show: true, backdrop: 'static'});
          }else{
              $('#segment_model').modal({show: true, backdrop: 'static'});
          }
        }).catch(function(error) {
        	$rootScope.isTrascError = true;
        	$rootScope.isTrascErr = true;
        	var msg = appConstants.exceptionMessage;
     		/*  if(error.data.httpStatus){ 
     			  msg=error.data.errorMessage; 
     		  } */
      		FlashService.Error(msg);
            $timeout(function () {
            $rootScope.isTrascError=false;
            $rootScope.isTrascErr = false;
            }, 5000);
    	});
      
        
    };

    function showDeleteConfirmationPopup() {
        $('#segment-delete-model').modal('show');
    }

    $scope.addRow = function() {
        $scope.cancontinue = true;
        if ($scope.valuedata.length > 0) {
            for (var indx = 0; indx < $scope.valuedata.length; indx++) {
                //alert(indx);
                //alert(JSON.stringify($scope.valuedata[indx]));
                if (angular.isUndefined($scope.valuedata[indx].seqno) || $scope.valuedata[indx].seqno===null || $scope.valuedata[indx].seqno==="") {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Please enter sequence no. at row: "+(indx+1));
                    $scope.cancontinue = false;
                    
                } else if (angular.isUndefined($scope.valuedata[indx].segname) || $scope.valuedata[indx].segname === null || $scope.valuedata[indx].segname==="" ) {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Please enter segment name at row: "+(indx+1));
                    $scope.cancontinue = false;
                    break;
                } else if ((angular.isUndefined ($scope.valuedata[indx].lookup) || $scope.valuedata[indx].lookup=== null || $scope.valuedata[indx].lookup==="" ) && $scope.valuedata[indx].isSqlReqdForValidations===0){
                    $rootScope.isTrascError = true;
                    FlashService.Error("Please select lookup at row: "+(indx+1));
                    $scope.cancontinue = false;
                    break;
                } else if((angular.isUndefined ($scope.valuedata[indx].sqlQuery) || $scope.valuedata[indx].sqlQuery=== null || $scope.valuedata[indx].sqlQuery==="") && $scope.valuedata[indx].isSqlReqdForValidations===1) {
                    $rootScope.isTrascError = true;
                    FlashService.Error("Please select SQL Query at row: "+(indx+1));
                    $scope.cancontinue = false;
                    break;
                } 
                else {
                    $scope.cancontinue = true;
                    $rootScope.isTrascError = false;
                }
            }

            if ($scope.cancontinue) {
              /* if($scope.segmentid!==null){ 
                    var segmentHdr={
                       segmentStructureHdrId:$scope.segmentid,
                       segHdrName:$scope.headerDesc
                    };
                 SegmenthirarchyService.segmentAlreadyInuse(segmentHdr).then(
                  function(data) {
                    if(data!==null && data.editable===0){
                        if(data.segHdrName==="category"){
                             $rootScope.isTrascError = true;
                             FlashService.Error("Category already created with this Segment Structure.So do not add another Segment");
                             $scope.cancontinue = false;
                        }
                        else{
                          $rootScope.isTrascError = true;
                             FlashService.Error("COA already created with this Segment Structure.So do not add another Segment");
                             $scope.cancontinue = false;  
                        }
                    }
                });
                }
              else{*/
                $scope.valuedata.push({
                    'seqno': "",
                    'segname': "",
                    'lookup': "",
                    'isSqlReqdForValidation': false,
                    'isSqlReqdForValidations': 0,
                    'sqlQuery': "",
                    'isselected': 'N',
                    'isCbkeditable':'Y',
                });
            //}
        }
        } else {
            $scope.valuedata.push({
                'seqno': "",
                'segname': "",
                'lookup': "",
                'isSqlReqdForValidation': false,
                'isSqlReqdForValidations': 0,
                'sqlQuery': "",
                'isselected': 'N',
                'isCbkeditable':'Y'
            });
        }
        $timeout(function () {
            $rootScope.isTrascErr=false;
        }, 5000);
    };

    $scope.removeRow = function() {
        // remove the row specified in index
        var newDataList = [];
        //$scope.selectedAll = false;
        angular.forEach($scope.valuedata, function(selected) {
            if (!selected.selected) {
                newDataList.push(selected);
            }
        });
        $scope.valuedata = newDataList;
    };
    // newly added for select all
    $scope.toggleAll = function() {
        var toggleStatus = $scope.isAllSelected;
        angular.forEach($scope.valuedata, function(itm){ itm.selected = toggleStatus; });
    }
    $scope.optionToggled = function(){
        $scope.isAllSelected = $scope.valuedata.every(function(itm){ return itm.selected; })
    }
    // $scope.removeselected = function() {
    //     var newDataList = [];
    //     if ($scope.allbox.selected) {
    //         for (var indx = 0; indx < $scope.valuedata.length; indx++) {
    //             console.log("inside this");
    //             var isselected = "Y";
    //             if($scope.valuedata[indx].isCbkeditable=="N") {
    //                isselected = "N"; 
    //             }
    //             newDataList.push({
    //                 'seqno': $scope.valuedata[indx].seqno,
    //                 'segname': $scope.valuedata[indx].segname,
    //                 'lookup': $scope.valuedata[indx].lookup,
    //                 'isSqlReqdForValidation': $scope.valuedata[indx].isSqlReqdForValidation,
    //                 'isSqlReqdForValidations': $scope.valuedata[indx].isSqlReqdForValidations,
    //                 'sqlQuery': $scope.valuedata[indx].sqlQuery,
    //                 'isselected':isselected,
    //                 'isCbkeditable':$scope.valuedata[indx].isCbkeditable
    //             });
    //         }
    //     } else {
    //         for (var indx = 0; indx < $scope.valuedata.length; indx++) {
    //             console.log("inside this");
    //             newDataList.push({
    //                 'seqno': $scope.valuedata[indx].seqno,
    //                 'segname': $scope.valuedata[indx].segname,
    //                 'lookup': $scope.valuedata[indx].lookup,
    //                 'isSqlReqdForValidation': $scope.valuedata[indx].isSqlReqdForValidation,
    //                 'isSqlReqdForValidations': $scope.valuedata[indx].isSqlReqdForValidations,
    //                 'sqlQuery': $scope.valuedata[indx].sqlQuery,
    //                 'isselected': 'N',
    //                 'isCbkeditable':$scope.valuedata[indx].isCbkeditable
    //             });
    //         }
    //     }
    //     $scope.valuedata = newDataList;
    // };

    $scope.searchSegments = function() {
        var searchName = $scope.segmentName;
        $scope.continueSearch = true;
        $rootScope.isTrascError = false;
        if ($scope.continueSearch) {
            $scope.searchData = {
                segmentStructureHdrDesc: searchName
            };
            var segmentSearchData = JSON.stringify($scope.searchData);
            SegmenthirarchyService.searchSegments(segmentSearchData).then(
                function(data) {
                    //alert(JSON.stringify(data));
                    $scope.segmentdata = data;
                }).catch(function(error) {
                	$rootScope.isTrascErr = true;
                	$scope.segmentdata.length = 0;
                	var msg = appConstants.exceptionMessage;
	  	       		  if(error.data.httpStatus){ 
	  	       			  msg=error.data.errorMessage; 
	  	       		  }
              		FlashService.Error(msg);
                    $timeout(function () {
                    $rootScope.isTrascErr=false;
                    }, 5000);
            	});
        }
    };
    
    $scope.clearSearch = function() {
        $scope.segmentName = "";
        $scope.clearSelectedRow();
        $scope.init();
        $scope.segmentdata="";
    };

    $scope.viewAllsegmentDetail = function() {
        $rootScope.segmentHeader = {
            segmentStructureHdrId: 0,
            segmentStructureHdrDesc: null,
            osiSegmentStructureDetailses: [],
        };
    };
    $scope.showLookup = function(indx) {
        //alert(indx);
        var isSql = $("#isSqlReq"+indx).is(':checked');
        if(isSql){
            console.log("SQL:: "+$scope.valuedata[indx]);
            $scope.valuedata[indx].isSqlReqdForValidations=1;
            $scope.valuedata[indx].isSqlReqdForValidation=true;
            console.log($scope.valuedata[indx]);
           // $("#lookupD"+indx).hide();
           // $("#sqlD"+indx).show(); 
        }else{
            console.log("look:: "+$scope.valuedata[indx]);
            $scope.valuedata[indx].isSqlReqdForValidations=0;
            $scope.valuedata[indx].isSqlReqdForValidation=false;
            console.log($scope.valuedata[indx]);
         // $("#sqlD"+indx).hide(); 
         // $("#lookupD"+indx).show();
        }
     };
    $scope.savesegment = function() {
        $scope.continuesave = true;
        var headerValue = "";
        for (var keyName in $scope.headername) {
            var key = keyName;
            if (key == "value") {
                var value = $scope.headername[keyName];
                headerValue = value;
            }
        }
        if (headerValue.length == 0) {
            $rootScope.isTrascError = true;
            FlashService.Error("Please select segment header.");
            $scope.continuesave = false;
        } else {
            $scope.continuesave = true;
            $rootScope.isTrascError = false;
        }
        if ($scope.continuesave) {
            $rootScope.segmentHeader.segmentStructureHdrId = $scope.segmentid;
            $rootScope.segmentHeader.segmentStructureHdrDesc = headerValue;
            $rootScope.segmentDetails = [];
            //$rootScope.segmentDetails.push($scope.tempselecteditems1);
            console.log("$scope.valuedata.length" + JSON.stringify($scope.valuedata));
            if($scope.valuedata.length > 0) {
                for (var indx = 0; indx < $scope.valuedata.length; indx++) {
                    $scope.seqno1 = $scope.valuedata[indx].seqno;
                    $scope.segname1 = $scope.valuedata[indx].segname;
                    $scope.lookup1 = $scope.valuedata[indx].lookup;
                    $scope.sqlQueryForValidation1 = $scope.valuedata[indx].sqlQuery;
                    $scope.isSqlReqdForValidations1 = $scope.valuedata[indx].isSqlReqdForValidations;
                   // alert((angular.isUndefined ($scope.lookup1) || $scope.lookup1=== null || $scope.lookup1=== "" ) +"::"+($scope.isSqlReqdForValidations1===0));
                    if (angular.isUndefined($scope.seqno1) || $scope.seqno1===null || $scope.seqno1 === "") {
                        $rootScope.isTrascError = true;
                        FlashService.Error("Please enter sequence no. at row: "+(indx+1));
                        $scope.continuesave = false;
                        break;
                    } else if (angular.isUndefined($scope.segname1) || $scope.segname1===null || $scope.segname1 === "") {
                        $rootScope.isTrascError = true;
                        FlashService.Error("Please enter segment name at row: "+(indx+1));
                        $scope.continuesave = false;
                        break;
                    }else if ((angular.isUndefined ($scope.lookup1) || $scope.lookup1=== null || $scope.lookup1=== "" ) && ($scope.isSqlReqdForValidations1===0)){
                        $rootScope.isTrascError = true;
                        FlashService.Error("Please select lookup at row: "+(indx+1));
                        $scope.cancontinue = false;
                        $scope.continuesave = false;
                        break;
                    } else if((angular.isUndefined ($scope.sqlQueryForValidation1) || $scope.sqlQueryForValidation1=== null || $scope.sqlQueryForValidation1=== "") && ($scope.isSqlReqdForValidations1===1)) {
                        $rootScope.isTrascError = true;
                        FlashService.Error("Please select SQL Query at row: "+(indx+1));
                        $scope.cancontinue = false;
                        $scope.continuesave = false;
                        break;
                    } else {
                        $rootScope.isTrascError = false;
                        $scope.continuesave = true;
                        $scope.tempselecteditems = {
                            segmentStructureDetailsDesc: $scope.segname1,
                            segmentStructureDetailsSeq: $scope.seqno1,
                            lovDataForValidation: $scope.lookup1,
                            sqlQueryForValidation: $scope.sqlQueryForValidation1,
                            isSqlReqdForValidation: $scope.isSqlReqdForValidations1
                        };
                        $rootScope.segmentDetails.push($scope.tempselecteditems);
                    }
                }
            }
            $rootScope.segmentHeader.osiSegmentStructureDetailses = $rootScope.segmentDetails;
            var segmentdata = JSON.stringify($rootScope.segmentHeader);
            console.log("lookupdata" + segmentdata);
            if ($scope.continuesave) {
                
                for (var indx1 = 0; indx1 < $scope.valuedata.length; indx1++) {
                    for (var indx2 = 0; indx2 < $scope.valuedata.length; indx2++) {
                        if (indx1 !== indx2)
                        {
                            if ($scope.valuedata[indx1].seqno == $scope.valuedata[indx2].seqno)
                            {
                                $rootScope.isTrascError = true;
                                FlashService.Error("Entered duplicate Sequence number.");
                                $scope.continuesave = false;
                                break;
                            }
                            else if ($scope.valuedata[indx1].segname == $scope.valuedata[indx2].segname){
                                $rootScope.isTrascError = true;
                                FlashService.Error("Entered duplicate Segment Name");
                                $scope.continuesave = false;
                                break;
                            }
                            else if ($scope.valuedata[indx1].lookup == $scope.valuedata[indx2].lookup && $scope.valuedata[indx1].isSqlReqdForValidation===0 && $scope.valuedata[indx2].isSqlReqdForValidation===0){
                                $rootScope.isTrascError = true;
                                FlashService.Error("Selected duplicate Lookup.");
                                $scope.continuesave = false;
                                break;
                            }
                            else{
                                 $scope.continuesave = true;
                                 $rootScope.isTrascError = false;
                            }
                        }

                    }
                    if($scope.continuesave==false){
                        break;
                    }

                }
          if ($scope.continuesave){       
                //alert("$scope.segmentid"+$scope.segmentid);
                if ($scope.segmentid == null) {
                    SegmenthirarchyService.savesegment(segmentdata).then(
                        function(result) {
                            $('#segment_model').modal('hide');
                            if (result.httpStatus == 200) {
                                $scope.successTextAlert = appConstants.successMessage;//result.message;
                                $scope.showSuccessAlert = true;
                            } else {
                                $rootScope.isTrascError = true;
                                FlashService.Error(result.errorMessage);
                                $scope.continuesave = false;
                            }
                            //$scope.init();
                            $scope.searchSegments();
                            $timeout(function() {
                                $scope.showSuccessAlert = false;
                            }, 5000);
                        }).catch(function(error) {
                        	$rootScope.isTrascError = true;
                        	var msg = appConstants.exceptionMessage;
	                   		  if(error.data.httpStatus){ 
	                   			  msg=error.data.errorMessage; 
	                   		  }
                   		  FlashService.Error(msg);
                            $timeout(function () {
                            $rootScope.isTrascError=false;
                            }, 5000);
                    	});
                } else {
                    SegmenthirarchyService.updatesegment(segmentdata).then(
                        function(result) {
                            $('#segment_model').modal('hide');
                            if (result.httpStatus == 200) {
                                $scope.successTextAlert = appConstants.successMessage;//result.message;
                                $scope.showSuccessAlert = true;
                                $scope.segmentdata = "";
                                $scope.segmentid == null;
                            } else {
                                $rootScope.isTrascError = true;
                                FlashService.Error(result.errorMessage);
                                $scope.continuesave = false;
                            }
                            //$scope.init();
                            $scope.searchSegments();
                            $timeout(function() {
                                $scope.showSuccessAlert = false;
                            }, 5000);
                        }).catch(function(error) {
                        	$rootScope.isTrascError = true;
                        	var msg = appConstants.exceptionMessage;
	                   		  if(error.data.httpStatus){ 
	                   			  msg=error.data.errorMessage; 
	                   		  }
                   		  FlashService.Error(msg);
                            $timeout(function () {
                            $rootScope.isTrascError=false;
                            }, 5000);
                    	});
                }
            }
        }
     }
     $timeout(function () {
     $rootScope.isTrascError=false;
     }, 3000);
    };
    $scope.init();
    $scope.sort('segmentStructureHdrDesc');

}