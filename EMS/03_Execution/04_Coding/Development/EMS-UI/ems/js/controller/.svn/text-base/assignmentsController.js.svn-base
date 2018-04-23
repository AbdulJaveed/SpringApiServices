(function() {
'use strict';

angular.module('myApp.AssignmentsController', []).controller('AssignmentsController', AssignmentsController)
.filter('range', function() {
  return function(input, min, max) {
    min = parseInt(min);
    max = parseInt(max);
    for (var i=min; i<=max; i++)
      input.push(i);
    return input;
  };
});
AssignmentsController.$inject = ['$scope', '$location', 'AssignmentsService','moment','FlashService','$rootScope','$timeout','configData','$localStorage','$route','SegmenthirarchyService','$filter','appConstants','employeeBasicInfoService'];
function AssignmentsController($scope, $location, AssignmentsService, moment, FlashService,  $rootScope, $timeout, configData, $localStorage, $route,SegmenthirarchyService, $filter,appConstants, employeeBasicInfoService) {


    var vm=this;
    $scope.usOrgCode = configData.usOrgCode;
	$localStorage.isReturn = false;
	
	vm.OsiRollUpsDept ={};
	vm.OsiRollUps = {};
	var queryJson = {};
	var jsonObj = [];
    var jsonField = {};
    
    var empLocationId;

	vm.getDropDownDataList = function(data,segmentName,index){
		if($scope.osiSegmentStructure.osiSegmentStructureDetailses[index].isSqlReqdForValidation === 1){
			vm.getDropDownData(data,segmentName,index);
		}
	}
	
	vm.getDropDownData = function(data,segmentName,index){
		
		if(index !== $scope.osiSegmentStructure.osiSegmentStructureDetailses.length){
			var headerValue = $scope.osiSegmentStructure.segmentStructureHdrDesc+"."+segmentName+".VAL";
		
			queryJson['id'] = $scope.osiSegmentStructure.osiSegmentStructureDetailses[index].segmentStructureDetailsId;
			
			jsonField[headerValue] = data;
			if(jsonObj.indexOf(jsonField) !== -1){
				jsonObj.splice( jsonObj.indexOf(jsonField), 1 );
				jsonObj.push(jsonField);
			}else
				jsonObj.push(jsonField);
			
			queryJson['dependents'] = jsonObj;
			
			// call service to get the data
			AssignmentsService.getSegmentDropDownData(JSON.stringify(queryJson)).then(function(data){
				vm.osiSegment[index] = data;
				
			}).catch(function(error){
                $rootScope.isTrascError = true;
                var msg = appConstants.exceptionMessage;
                if(error.data.httpStatus){ 
                    msg=error.data.errorMessage; 
                }
                FlashService.Error(msg);
                $timeout(function () {
                    $rootScope.isTrascError=false;
                }, 2000);
			});
			
		/*	vm.osiSegment[index] =[{
					orgId : "19",
					orgName : "Osi Technologies -- "+index
								
						},{
							orgId : "17",
							orgName : "Osi Consulting"
					},{
						orgId : "3",
						orgName : "Pilippines"
						
					}];*/
	}
}
	
	
    function initController(){
    	vm.osiSegment= {};
    	vm.empAssignmentsList = [];
        vm.empAssignment = {};
        $scope.orgLocations = [];
        $scope.jobs = [];
        $scope.grades = [];
        $scope.employeeLists = [];
        $scope.departmentList = [];
        $scope.searchParams = {};
        $scope.supervisor = {};
        $scope.deptId = null;
        vm.employeeId = $localStorage.employeeId;
        
        vm.searchDate = $localStorage.searchByDate;
        $scope.supervisorList = [];
        $scope.custRowItem = -1;
        $scope.searchParamsList = [];
        $scope.custRowsItem = -1;
        $scope.isContractor = false;
        $scope.currDate = null;
        $scope.probEndDate = null;
        $scope.getAllSegments();
        // get all job codes
        AssignmentsService.GetAllJobCodes(vm.employeeId).then(function (data) {
            $scope.jobs = data;

        });
        // get all grades
        AssignmentsService.GetAllGrades(vm.employeeId).then(function (data) {
            $scope.grades = data;
        });

        // get all org locations
        AssignmentsService.GetAllLocations(vm.employeeId).then(function (data) {
            $scope.orgLocations = data;
        });

        // get all department
        AssignmentsService.GetAllDepartment().then(function (data) {
            var keepGoing = true;
            if(keepGoing){
                angular.forEach(data, function (v) {
                    angular.forEach(v.osiLookupValueses, function (k) {
                        $scope.departmentList.push(k);
                        keepGoing = false;
                    });
                });
            }
        });


        var  urlPath = $location.path();
        if(urlPath.includes('employeeAssignments-sf')) {
            if($rootScope.globals.userDTO!==undefined){
                var loggedInEmpId = $rootScope.globals.userDTO.id;
                if( $localStorage.employeeId == loggedInEmpId ) {
                    $scope.iseditable = false; // TO-DO :: true if editable for self
                } else {
                    $scope.iseditable = false;
                }
            }
        } else {
            $scope.iseditable = true;
        }

        // get assignment by employee id
        var empId = vm.employeeId;
        console.log(empId);
        console.log(vm.searchDate);
        if(undefined !== empId && undefined !== vm.searchDate){
            var inputObject = {
              "employeeId" : empId,
              "searchDate" : vm.searchDate
            }
            AssignmentsService.FindAssignmentByEmployeeIdAndDate(inputObject).then(function success(data) {
            //AssignmentsService.FindAssignmentByEmployeeId(empId).then(function (data) {
                if(data.assignmentId != null)
                  $scope.isNew = false;
                else
                  $scope.isNew = true;
                if (data.gradeId !== null) {
                    AssignmentsService.getGradeById(data.gradeId).then(function(gradesData){
                        data.osiGrades = gradesData.gradeId;
                        if(gradesData.active !== 1){
                            $scope.grades.push(gradesData);
                        }
                    });
                }
                if (data.jobId !== null) {
                    AssignmentsService.getJobCodesById(data.jobId).then(function(jobCodeData){
                        data.osiJobCodes = jobCodeData.jobCodeId;
                        if(jobCodeData.active !== 1){
                            $scope.jobs.push(jobCodeData);
                        }
                    });
                }
                if (data.locationId !== null) {
                    empLocationId = data.locationId;
                    AssignmentsService.getOsiLocationById(data.locationId).then(function(locationData){
                        data.osiLocations = locationData.locationId;
                        if(locationData.active !== 1){
                            $scope.orgLocations.push(locationData);
                        }
                    });
                }

                if (data.probationEndDate !== null) {
                    var probationEndDate = moment(data.probationEndDate).format('YYYY-MM-DD');
                    data.probationEndDate = probationEndDate;
                } else {
                    data.probationEndDate = null;
                }

                // if(data.effectiveStartDate !== null){
                //     //var startDate = moment(data.effectiveStartDate).format('DD-MMM-YYYY');
                //     var startDate = moment(data.effectiveStartDate).format('YYYY-MM-DD');
                //     data.effectiveStartDate = startDate;
                // }
                // if(data.effectiveEndDate !== null){
                //     //var endDate = moment(data.effectiveEndDate).format('DD-MMM-YYYY');
                //     var endDate = moment(data.effectiveEndDate).format('YYYY-MM-DD');
                //     data.effectiveEndDate = endDate;
                // }
                $scope.searchParams.fullName = data.referralName;
                $scope.searchParams.employeeId = data.referredById;
                $scope.supervisor.fullName = data.supervisorName;
                $scope.supervisor.employeeId = data.supervisorId;

                angular.forEach($scope.departmentList, function (d) {
                    if (data.deptId !== null && d.id === data.deptId) {
                        $scope.deptId = d.id;
                    }
                });

                vm.empAssignment = data;
                vm.empAssignment.selectedPeriod = vm.empAssignment.probationUnit;
                vm.empAssignment.selectedPeriodValue = vm.empAssignment.probationUnitValue;
                vm.empAssignment.reasonForChange = vm.empAssignment.changeReason;
                console.log(vm.empAssignment);

                // to check probation period end date vs current date
                if(vm.empAssignment.probationEndDate !== undefined && vm.empAssignment.probationEndDate !== null) {
                    $scope.probEndDate = moment(vm.empAssignment.probationEndDate).toDate();
                } else {
                    $scope.probEndDate = moment(new Date()).toDate();
                }
                $scope.currDate = moment(new Date()).toDate();

               //to check if employee type is Contractor,probation period fields are not shown
                if((vm.empAssignment.employeeType !== undefined || null !== vm.empAssignment.employeeType) && (vm.empAssignment.employeeType.toLowerCase() === 'contractor' || vm.empAssignment.employeeType.toLowerCase() === 'intern')){
                    $scope.isContractor = true;
                    console.log($scope.isContractor);
                    if(data.contractStartDate !== null) {
                        var startDate = moment(data.contractStartDate).format('YYYY-MM-DD');
                         vm.empAssignment.contractStartDate = startDate;
                    }
                    if(data.contractEndDate !== null) {
                         var endDate = moment(data.contractEndDate).format('YYYY-MM-DD');
                         vm.empAssignment.contractEndDate = endDate;
                    }
                }
                if(vm.empAssignment.deptId !== undefined && vm.empAssignment.deptId !== null){
      	    		AssignmentsService.findRollUpsById(vm.empAssignment.deptId).then(function(data){
      	    			$scope.deptStructure = '';
      	    			vm.OsiRollUpsDept.segment=[];
      	    			if(data !== ''){
      	    				if(data.segment1 !== null && data.segment1 !== undefined){
      	    					$scope.deptStructure=data.segment1;
      	    					vm.OsiRollUpsDept.segment[0] = data.segment1;
      	    				}
      	    				
      	    				if(data.segment2 !== null  && data.segment2 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment2;
      	    					vm.OsiRollUpsDept.segment[1] = data.segment2;
      	    					vm.getDropDownData(data.segment1,$scope.osiSegmentStructure.osiSegmentStructureDetailses[0].segmentStructureDetailsDesc,1);
      	    				}
      	    				if(data.segment3 !== null  && data.segment3 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment3;
      	    					vm.OsiRollUpsDept.segment[2] = data.segment3;
      	    					vm.getDropDownData(data.segment2,$scope.osiSegmentStructure.osiSegmentStructureDetailses[1].segmentStructureDetailsDesc,2);
      	    				}
      	    				if(data.segment4 !== null && data.segment4 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment4;
      	    					vm.OsiRollUpsDept.segment[3] = data.segment4;
      	    					vm.getDropDownData(data.segment3,$scope.osiSegmentStructure.osiSegmentStructureDetailses[2].segmentStructureDetailsDesc,3);
      	    				}
      	    				if(data.segment5 !== null  && data.segment5 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment5;
      	    					vm.OsiRollUpsDept.segment[4] = data.segment5;
      	    					vm.getDropDownData(data.segment4,$scope.osiSegmentStructure.osiSegmentStructureDetailses[3].segmentStructureDetailsDesc,4);
      	    				}
      	    				if(data.segment6 !== null  && data.segment6 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment6;
      	    					vm.OsiRollUpsDept.segment[5] = data.segment6;
      	    					vm.getDropDownData(data.segment5,$scope.osiSegmentStructure.osiSegmentStructureDetailses[4].segmentStructureDetailsDesc,5);
      	    				}
      	    				if(data.segment7 !== null  && data.segment7 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment7;
      	    					vm.OsiRollUpsDept.segment[6] = data.segment7;
      	    					vm.getDropDownData(data.segment6,$scope.osiSegmentStructure.osiSegmentStructureDetailses[5].segmentStructureDetailsDesc,6);
      	    				}
      	    				if(data.segment8 !== null && data.segment8 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment8;
      	    					vm.OsiRollUpsDept.segment[7] = data.segment8;
      	    					vm.getDropDownData(data.segment7,$scope.osiSegmentStructure.osiSegmentStructureDetailses[6].segmentStructureDetailsDesc,7);
      	    				}
      	    				if(data.segment9 !== null && data.segment9 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment9;
      	    					vm.OsiRollUpsDept.segment[8] = data.segment9;
      	    					vm.getDropDownData(data.segment8,$scope.osiSegmentStructure.osiSegmentStructureDetailses[7].segmentStructureDetailsDesc,8);
      	    				}
      	    				if(data.segment10 !== null && data.segment10 !== undefined){
      	    					$scope.deptStructure=$scope.deptStructure+"."+data.segment10;
      	    					vm.OsiRollUpsDept.segment[9] = data.segment10;
      	    					vm.getDropDownData(data.segment9,$scope.osiSegmentStructure.osiSegmentStructureDetailses[8].segmentStructureDetailsDesc,9);
      	    				}
      	    			}
      	    			/*	vm.OsiRollUpsDept.segment[2] = data.segment3;
      	    				vm.OsiRollUpsDept.segment[3] = data.segment4;
      	    				vm.OsiRollUpsDept.segment[4] = data.segment5;
      	    				vm.OsiRollUpsDept.segment[5] = data.segment6;
      	    				vm.OsiRollUpsDept.segment[6] = data.segment7;
      	    				vm.OsiRollUpsDept.segment[7] = data.segment8;
      	    				vm.OsiRollUpsDept.segment[8] = data.segment9;
      	    				vm.OsiRollUpsDept.segment[9] = data.segment10;*/
      	    		}).catch(function(error){
                        $rootScope.isTrascError = true;
                        var msg = appConstants.exceptionMessage;
                        if(error.data.httpStatus){ 
                            msg=error.data.errorMessage; 
                        }
                        FlashService.Error(msg);
                        $timeout(function () {
                            $rootScope.isTrascError=false;
                        }, 2000);
      	    		});
      	    }
            }, function error(error){
		        	console.log(error.data);
		        	$rootScope.isTrascError = true;
					FlashService.Error(error.data.developerMessage);
					$timeout(function () {
						$rootScope.isTrascError=false;
					}, 2000);
		        });
        }
    };

    
    // get employee for referral list
    $scope.searchEmployee = function(e,empNumber){
        if(e.keyCode !== 40 && e.keyCode !== 38 && e.keyCode !== 13){
             $scope.searchParamsList = [];
            if(empNumber != null && empNumber != ""){

                AssignmentsService.SearchEmployeeByEmpName(empNumber).then(function(data){
                    console.log(data)
                    angular.forEach(data,function(v){
                        var emp = {
                                fullName : v.fullName,
                                employeeId : v.employeeId
                            };
                            console.log(v);
                        $scope.searchParamsList.push(emp);
                    });
                    $("#empId").focus();
                });
            }
        }else{
            if(e.keyCode === 40){
                if( $scope.searchParamsList[$scope.custRowsItem + 1] !== undefined){
                    if($scope.custRowsItem <  $scope.searchParamsList.length - 1){
                        $scope.custRowsItem++;
                    }
                }

            } else if(e.keyCode === 38){ // *Up key
                if( $scope.searchParamsList[$scope.custRowsItem - 1] !== undefined){
                    if($scope.custRowsItem > 0){
                        $scope.custRowsItem--;
                    }
                }

            } else if(e.keyCode === 13){ // *Enter key
                $scope.setCustRowsItem($scope.custRowsItem);
                 //$scope.searchParams.fullName = $scope.searchParamsList[$scope.custRowsItem].fullName;
                 //$scope.searchParamsList = [];
            }
        }

    };

    $scope.openSupervisorPopup = function(supervisorName){
    	if(supervisorName.length > 0 ){
    		$scope.getEmployeeListByName(supervisorName,'frmFunction');
    		
    		
    	}
    }
    $scope.searchEmployeePopup = function(referredBy){
    	if(referredBy.length > 0 ){
    		$scope.getEmployeeListByNameForRefferedBy(referredBy,'frmFunction');
    		
    		
    	}
    }
    $scope.getEmployeeListByNameForRefferedBy = function(referredBy,type){

    	  AssignmentsService.SearchEmployeeByEmpName(referredBy).then(function(data){
    		  $scope.searchParamsList=[];
              angular.forEach(data,function(v){
                  var emp = {
                          fullName : v.fullName,
                          employeeId : v.employeeId
                      };
                      console.log(v);
                  $scope.searchParamsList.push(emp);
              });
        
            if(type === 'frmFunction'){
            	if(data.length > 1)
            		$("#refferedBypopupModel").modal("show");
            	else if(data.length === 1)
            		$scope.setCustRowsItem(0);
            	else{
            		$rootScope.isTrascError = true;
                    FlashService.Error('No match found with the given Referred By name');
                    $scope.proceed = false;
                    $timeout(function() {
                        $rootScope.isTrascError = false;
                    }, 2000);
            	}
            		
            		
            }
        });
    }
    $scope.getEmployeeListByName = function(supervisorName,type){

        AssignmentsService.SearchEmployeeByEmpName(supervisorName).then(function(data){
        	 $scope.supervisorList=[];
            angular.forEach(data,function(v){
                var emp = {
                        fullName : v.fullName,
                        employeeId : v.employeeId
                    };
                $scope.supervisorList.push(emp);
            });
            if(type === 'frmFunction'){
            	if(data.length > 1)
            		$("#supervisorpopupModel").modal("show");
            	else if(data.length === 1)
            		$scope.setCustRowItem(0);
            	else{
            		$rootScope.isTrascError = true;
                    FlashService.Error('No match found with the given supervisor name');
                    $scope.proceed = false;
                    $timeout(function() {
                        $rootScope.isTrascError = false;
                    }, 2000);
            	}
            		
            		
            }
        });
    }
    //get employee for supervisor list
    $scope.searchSupervisor = function(e,empNumber){
        if(e.keyCode !== 40 && e.keyCode !== 38 && e.keyCode !== 13){
            $scope.supervisorList = [];
            if(empNumber != null && empNumber != ""){

                AssignmentsService.SearchEmployeeByEmpName(empNumber).then(function(data){
                    console.log(data)
                    angular.forEach(data,function(v){
                        var emp = {
                                fullName : v.fullName,
                                employeeId : v.employeeId
                            };
                            console.log(v);
                        $scope.supervisorList.push(emp);
                    });
                    console.log($scope.supervisorList)
                   /* $("#supId").focus();*/
                });
            }
        }else{
            if(e.keyCode === 40){
                if($scope.supervisorList[$scope.custRowItem + 1] !== undefined){
                    if($scope.custRowItem < $scope.supervisorList.length - 1){
                        $scope.custRowItem++;
                    }
                }

            } else if(e.keyCode === 38){ // *Up key
                if($scope.supervisorList[$scope.custRowItem - 1] !== undefined){
                    if($scope.custRowItem > 0){
                        $scope.custRowItem--;
                    }
                }

            } else if(e.keyCode === 13){ // *Enter key
                $scope.setCustRowItem($scope.custRowItem);
                 //$scope.supervisor.fullName = $scope.supervisorList[$scope.custRowItem].fullName;
                 //$scope.supervisorList = [];
            }
        }



    };

    $scope.setCustRowItem = function(index){
    	
        $scope.supervisor.fullName = $scope.supervisorList[index].fullName;
        $scope.supervisor.employeeId = $scope.supervisorList[index].employeeId;
        $("#supervisorpopupModel").modal("hide");
        $scope.supervisorList = [];
    };

    $scope.setCustRowsItem = function(index){
        $scope.searchParams.fullName = $scope.searchParamsList[index].fullName;
        $scope.searchParams.employeeId = $scope.searchParamsList[index].employeeId;
        $("#refferedBypopupModel").modal("hide");
        $scope.searchParamsList = [];
    };

    // probation duration
    $scope.changeSelectedDuration = function(){

        //var months =  angular.isUndefined(vm.empAssignment.monthsPeriod) ? 0 : vm.empAssignment.monthsPeriod;
        //var days =  angular.isUndefined(vm.empAssignment.daysPeriod) ? 0 : vm.empAssignment.daysPeriod;
        //vm.empAssignment.probationEndDate = moment().add(days, 'days').add(months, 'months').format('DD-MMM-YYYY');
        var n = vm.empAssignment.selectedPeriod;
        //var period =  angular.isUndefined(n) ? 0 : n.id;
        var value =  angular.isUndefined(vm.empAssignment.selectedPeriodValue) ? 0 : vm.empAssignment.selectedPeriodValue;

        //if(period !== 0){
        if(n !== undefined){
           //vm.empAssignment.probationEndDate = moment().add(value, n.momentProp).format('YYYY-MM-DD');
           vm.empAssignment.probationEndDate = moment().add(value, n).format('YYYY-MM-DD');
           console.log(vm.empAssignment.probationEndDate)
        }
    }

    $scope.validateFields = function(){
        $scope.validate(vm.empAssignment);
        if($scope.isNew ) {
          window.setTimeout(function(){
            if($scope.proceed){
              $scope.saveAssignment('update');
            }
        }, 200);
        }else {
          window.setTimeout(function(){
              if($scope.proceed){
                  $('#confirmationForAssignments').modal('show');
              }
          }, 200);
      }
    };

    $scope.validate = function(employeeAssignment){
        console.log(employeeAssignment);
        $scope.proceed = true;

        //var endDate = moment(employeeAssignment.effectiveEndDate).format('YYYY-MM-DD');
        var probDate = moment(employeeAssignment.probationEndDate).format('YYYY-MM-DD');
        if(null === employeeAssignment.deptId){
            $rootScope.isTrascError = true;
            FlashService.Error('Please select Department.');
            $scope.proceed = false;
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }else if(null === employeeAssignment.osiGrades){
            $rootScope.isTrascError = true;
            FlashService.Error('Please select Grade.');
            $scope.proceed = false;
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }else if(null === employeeAssignment.osiJobCodes || undefined === employeeAssignment.osiJobCodes){
            $rootScope.isTrascError = true;
            FlashService.Error('Please select Job.');
            $scope.proceed = false;
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }else if(null === employeeAssignment.osiLocations || undefined === employeeAssignment.osiLocations){
            $rootScope.isTrascError = true;
            FlashService.Error('Please select Location.');
            $scope.proceed = false;
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }else if(null === $scope.supervisor.employeeId){
            $rootScope.isTrascError = true;
            FlashService.Error('Please enter supervisor name.');
            $scope.proceed = false;
            $timeout(function() {
                    $rootScope.isTrascError = false;
            }, 2000);
        }else if((employeeAssignment.employeeType.toLowerCase() === 'contractor'  || employeeAssignment.employeeType.toLowerCase() === 'intern') && (employeeAssignment.contractStartDate === null || undefined === employeeAssignment.contractStartDate || employeeAssignment.contractStartDate === "" )){
            $rootScope.isTrascError = true;
            FlashService.Error('Please select Start Date.');
            $scope.proceed = false;
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }else if((employeeAssignment.employeeType.toLowerCase() === 'contractor' || employeeAssignment.employeeType.toLowerCase() === 'intern') && (employeeAssignment.contractEndDate === null || undefined === employeeAssignment.contractEndDate || "" === employeeAssignment.contractEndDate)){
            $rootScope.isTrascError = true;
            FlashService.Error('Please select End Date.');
            $scope.proceed = false;
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }else if(employeeAssignment.employeeType === 'Employee'
            && (employeeAssignment.onProbation != null || employeeAssignment.onProbation != 0)
            && (employeeAssignment.selectedPeriod == null || undefined == employeeAssignment.selectedPeriod)
            && (employeeAssignment.selectedPeriodValue == null || undefined == employeeAssignment.selectedPeriodValue)){
            $rootScope.isTrascError = true;
            FlashService.Error('Please select probation period.');
            $scope.proceed = false;
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }/*else if(endDate > probDate){
            $rootScope.isTrascError = true;
            FlashService.Error('End Date should not be greater than the Probationary End Date.');
            $scope.proceed = false;
            employeeAssignment.effectiveEndDate = null;
            $timeout(function() {
                $rootScope.isTrascError = false;
            }, 2000);
        }*/
        else{
        var checkForActiveGrade = false;
        var checkForActiveJobCode = false;
        var checkForActiveLocation = false;

        AssignmentsService.getJobCodesById(employeeAssignment.osiJobCodes).then(function(jobCodeData){
            if(jobCodeData.active === 1){
                checkForActiveJobCode = true;
                console.log(checkForActiveJobCode);
            }
            AssignmentsService.getGradeById(employeeAssignment.osiGrades).then(function(gradesData){
                if(gradesData.active === 1){
                    checkForActiveGrade = true;
                    console.log(checkForActiveGrade);
                }
                AssignmentsService.getOsiLocationById(employeeAssignment.osiLocations).then(function(locationData){
                    if(locationData.active === 1){
                        checkForActiveLocation = true;
                        console.log(checkForActiveLocation);
                    }

                    if(!checkForActiveGrade){
                        $rootScope.isTrascError = true;
                        FlashService.Error("Grade is inactive, please change it.");
                        $scope.proceed = false;
                        console.log($scope.proceed);
                        $timeout(function() {
                            $rootScope.isTrascError = false;
                        }, 2000);
                    }else if(!checkForActiveJobCode){
                        $rootScope.isTrascError = true;
                        FlashService.Error("Job is inactive, please change it.");
                        $scope.proceed = false;
                        console.log($scope.proceed);
                        $timeout(function() {
                            $rootScope.isTrascError = false;
                        }, 2000);

                    }else if(!checkForActiveLocation){
                        $rootScope.isTrascError = true;
                        FlashService.Error("Location is inactive, please change it.");
                        $scope.proceed = false;
                        console.log($scope.proceed);
                        $timeout(function() {
                            $rootScope.isTrascError = false;
                        }, 2000);
                    }
                });
            });
        });
      }
    };

    //save emp assignment
    vm.save = function(decision) {
        $scope.saveAssignment(decision);
    };

    //save emp assignment
    //$scope.saveAssignment = function(employeeAssignment){
    $scope.saveAssignment = function(decision){

        $rootScope.isTrascError = false;
        console.log(vm.empAssignment);
        var employeeAssignment = vm.empAssignment;
        if(vm.empAssignment.isManager == null || vm.empAssignment.isManager == undefined)
          vm.empAssignment.isManager = 0;
        var empAssignmnt = {
            assignmentId : vm.empAssignment.assignmentId,
            assignmentType : vm.empAssignment.employeeType,
            version : vm.empAssignment.version,
            employeeId : vm.employeeId,
            contractStartDate : employeeAssignment.contractStartDate,
            contractEndDate : employeeAssignment.contractEndDate,
            
            //effectiveStartDate : moment(employeeAssignment.effectiveStartDate).toDate(),
            effectiveEndDate : configData.maxEffecEndDate,
            isManager : employeeAssignment.isManager,
            supervisorId : $scope.supervisor.employeeId,
            gradeId : employeeAssignment.osiGrades,
            referredById : $scope.searchParams.employeeId,
            changeReason : employeeAssignment.reasonForChange,
            deptId : employeeAssignment.deptId,
            jobId : employeeAssignment.osiJobCodes,
            locationId : employeeAssignment.osiLocations,
            onProbation : employeeAssignment.onProbation,
            probationUnit : employeeAssignment.selectedPeriod,
            probationUnitValue : employeeAssignment.selectedPeriodValue,
            probationEndDate : moment(employeeAssignment.probationEndDate).toDate()
        };

        AssignmentsService.SaveAssignments(empAssignmnt, decision).then(function(data){
        	   data.osiGrades = data.gradeId; 
        	   data.osiJobCodes = data.jobId;
        	   data.osiLocations = data.locationId;
        	   if (data.probationEndDate !== null) {
        	                      var probationEndDate = moment(data.probationEndDate).format('YYYY-MM-DD');
        	                      data.probationEndDate = probationEndDate;
        	                  } else {
        	                      data.probationEndDate = null;
        	                  }

        	   $scope.searchParams.fullName = data.referralName;
        	                  $scope.searchParams.employeeId = data.referredById;
        	                  $scope.supervisor.fullName = data.supervisorName;
        	                  $scope.supervisor.employeeId = data.supervisorId;
        	    vm.empAssignment = data;
        	                  vm.empAssignment.selectedPeriod = vm.empAssignment.probationUnit;
        	                  vm.empAssignment.selectedPeriodValue = vm.empAssignment.probationUnitValue;
        	                  vm.empAssignment.reasonForChange = vm.empAssignment.changeReason;
        	   if(vm.empAssignment.probationEndDate !== undefined && vm.empAssignment.probationEndDate !== null) {
        	                      $scope.probEndDate = moment(vm.empAssignment.probationEndDate).toDate();
        	                  } else {
        	                      $scope.probEndDate = moment(new Date()).toDate();
        	                  }
        	                  $scope.currDate = moment(new Date()).toDate();
                              $rootScope.isTrascError = true;
                              var msg = appConstants.successMessage;
                              FlashService.Success(msg);
                              $timeout(function () {
                                  $rootScope.isTrascError=false;
                              }, 2000);
            }).catch(function(error){
        var msg = appConstants.exceptionMessage;
        if(error.data.httpStatus){ 
            msg=error.data.errorMessage; 
        }
        FlashService.Error(msg);
        $timeout(function () {
            $rootScope.isTrascError=false;
        }, 2000);
    });
    };

    //clear
    $scope.clear = function(){
      /*  vm.empAssignment = {};
        $scope.searchPaerams = {};
        $scope.supervisor = {};*/
    	window.location.href = "#/employees";
		$localStorage.isReturn = true;
    }

    //clear dates
    $scope.changeSelectedOnProbation = function(onProbi){
        console.log(vm.empAssignment)
        if(onProbi == 0){
//            vm.empAssignment.monthsPeriod = null;
//            vm.empAssignment.daysPeriod = null;

            vm.empAssignment.probationEndDate = null;
        }
    }

    //change on probation checkbox
    $scope.changeOnProbation = function(){
        vm.empAssignment.probationEndDate = null;
        vm.empAssignment.selectedPeriod = null;
        vm.empAssignment.selectedPeriodValue = null;
    }

    //lov for probation period
    $scope.probationPeriod = [{name: "Year(s)", id: 1, momentProp: 'years'}, {name:"Month(s)", id:2, momentProp: 'months'}, {name: "Day(s)", id:3, momentProp: 'days'}];

    $scope.changeProbation = function(type) {
      console.log(type);
    }
    
    $scope.getAllSegments = function(){
    	  SegmenthirarchyService
          .getSegmentStructure($localStorage.orgId)
          .then(
              function(data) {
            	  data.osiSegmentStructureDetailses = $filter('orderBy')(data.osiSegmentStructureDetailses, '-segmentStructureDetailsSeq', true);
            	  $scope.osiSegmentStructure = data;
            	  vm.getDropDownData('',$scope.osiSegmentStructure.osiSegmentStructureDetailses[0].segmentStructureDetailsDesc,0);
            	  
              }).catch(function(error) {
                $rootScope.isTrascError = true;
                var msg = appConstants.exceptionMessage;
                if(error.httpStatus){ 
                    msg=error.errorMessage; 
                }
                FlashService.Error(msg);
                $timeout(function () {
                    $rootScope.isTrascError=false;
                }, 2000);
          	});

    }
   vm.saveDeptData = function(){
	   vm.OsiRollUps.orgId = $localStorage.orgId;
	   vm.OsiRollUps.active =1;
	   $scope.deptStructure = '';
	   angular.forEach(vm.OsiRollUpsDept.segment, function(valueKey,index){
		if(index == 0){
			$scope.deptStructure = valueKey;
			vm.OsiRollUps.segment1 = valueKey;
		}else if(index == 1){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment2 = valueKey;
		}else if(index == 2){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment3 = valueKey;
		}else if(index == 3){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment4 = valueKey;
		}else if(index == 4){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment5 = valueKey;
		}else if(index == 5){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment6 = valueKey;
		}else if(index == 6){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment7 = valueKey;
		}else if(index == 7){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment8 = valueKey;
		}else if(index == 8){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment9 = valueKey;
		}else if(index == 9){
			$scope.deptStructure = $scope.deptStructure+"."+valueKey;
			vm.OsiRollUps.segment10 = valueKey;
		}
	   });
	   AssignmentsService.saveEmployeeRollUps(vm.OsiRollUps).then(function(data){
		   /*$("#DepartmentsRollup").modal("hide");*/
		   vm.OsiRollUps = data;
		   vm.empAssignment.deptId = data.rollupId;
		   $("#DepartmentsRollup").modal("hide");
          
	   }).catch(function(error){
        $rootScope.isTrascError = true;
        var msg = appConstants.exceptionMessage;
        if(error.data.httpStatus){ 
            msg=error.data.errorMessage; 
        }
        FlashService.Error(msg);
        $timeout(function () {
            $rootScope.isTrascError=false;
        }, 2000);
	   });
   };
  
   $scope.validateDeptData = function(){
	   $scope.isValidDeptData = true;
	   for(var i=0; i < $scope.osiSegmentStructure.osiSegmentStructureDetailses.length; i++){
		   if(vm.OsiRollUpsDept.segment !== undefined){
			   if(vm.OsiRollUpsDept.segment[i] === undefined || vm.OsiRollUpsDept.segment[i] === null  || vm.OsiRollUpsDept.segment[i] === ''){
				   if($scope.isValidDeptData){
					   $scope.isValidDeptData = false;
					   $scope.colNameDept = $scope.osiSegmentStructure.osiSegmentStructureDetailses[i].segmentStructureDetailsDesc;
				   }
			   }
		   }else{
			   $scope.isValidDeptData = false;
			   $scope.colNameDept = $scope.osiSegmentStructure.osiSegmentStructureDetailses[0].segmentStructureDetailsDesc;
		   }
		 
	   }
	   if(!$scope.isValidDeptData){
		   $scope.isValidDeptData = false;
		   $rootScope.isTrascErrorDept = true;
           FlashService.Error('Please select '+$scope.colNameDept+ '.');
          
           $timeout(function() {
               $rootScope.isTrascErrorDept = false;
           }, 3000);
	   }else{
		   vm.saveDeptData();
	   }
		
   }
    $scope.openRollUpsPopup = function(){
        vm.OsiRollUpsDept.segment=[];
        $('#orgName0').attr('disabled','disabled');
        $('#orgName1').attr('disabled','disabled');
        if($localStorage.osiOrganizations != undefined && $localStorage.osiOrganizations != null && $localStorage.osiOrganizations.length != 0) {
            vm.osiOrganizations = $localStorage.osiOrganizations;
        } else {
            employeeBasicInfoService.getAllOrganizations().then(function (result) {
        		console.log(result);
        		vm.osiOrganizations = result;
        	});
        }
        angular.forEach(vm.osiOrganizations, function(org, key) {
            if(org.orgId == $localStorage.orgId) {
                vm.OsiRollUpsDept.segment[0] = org.orgShortName;                
            }
        });
        if($scope.orgLocations != undefined && $scope.orgLocations != null && $scope.orgLocations.length != 0) {
            vm.osiLocations = $scope.orgLocations;
        }
        angular.forEach(vm.osiLocations, function(loc, key) {
            if(loc.locationId == empLocationId) {
                vm.OsiRollUpsDept.segment[1] = loc.locationName;
                vm.getDropDownDataList(vm.OsiRollUpsDept.segment[0], 'Organization', 1);
                vm.getDropDownDataList(vm.OsiRollUpsDept.segment[1], 'Location', 2);
            }
        });
	    	$("#DepartmentsRollup").modal("show");
	    	/*if(vm.empAssignment.deptId !== undefined && vm.empAssignment.deptId !== null){
	    		AssignmentsService.findRollUpsById(vm.empAssignment.deptId).then(function(data){
	    			vm.OsiRollUpsDept.segment=[];
	    				vm.OsiRollUpsDept.segment[0] = data.segment1;
	    				vm.OsiRollUpsDept.segment[1] = data.segment2;
	    				vm.OsiRollUpsDept.segment[2] = data.segment3;
	    				vm.OsiRollUpsDept.segment[3] = data.segment4;
	    				vm.OsiRollUpsDept.segment[4] = data.segment5;
	    				vm.OsiRollUpsDept.segment[5] = data.segment6;
	    				vm.OsiRollUpsDept.segment[6] = data.segment7;
	    				vm.OsiRollUpsDept.segment[7] = data.segment8;
	    				vm.OsiRollUpsDept.segment[8] = data.segment9;
	    				vm.OsiRollUpsDept.segment[9] = data.segment10;
	    		}).catch(function(error){
	    			
	    		});
	    }*/
    }
    initController();
    $scope.setLocationId = function() {
        //console.log(vm.empAssignment.osiLocations);
        empLocationId = vm.empAssignment.osiLocations;
    }
};

})();
