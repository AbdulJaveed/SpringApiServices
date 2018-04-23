'use strict';

var app = angular.module('employeeOrgListCtrl', []);
        app.controller('employeeOrgListCtrl',
		['$scope', '$rootScope', '$routeParams', '$localStorage','$timeout','$location','$http','$route','$interval', '$q','uiGridTreeViewConstants','configData'
		, 'appConstants', 'SegmenthirarchyService','$filter',

function($scope, $rootScope, $routeParams, $localStorage,$timeout,$location,$http,$route,$interval, $q,uiGridTreeViewConstants,configData, appConstants, SegmenthirarchyService,$filter) {
	$scope.usOrgCode = configData.usOrgCode;
	var fakeI18n = function( title ){
	    var deferred = $q.defer();
	    $interval( function() {
	      deferred.resolve( 'col: ' + title );
	    }, 1000, 1);
	    return deferred.promise;
	  };
	  $scope.gridOptions = {
	  };

	  $scope.chart = function(){
			$location.path("/employeeChart");
		};

		$scope.msg = {};
		// fixed column definitions
		$scope.tempDef = [
			{ field: 'employee_number', displayName:"Employee Number",enableCellEdit: false, width:140},
			{ field: 'full_name', displayName:"Employee Name",enableCellEdit: false,width:200},
			{ field: 'job_code_name', displayName:"Job Name" ,enableCellEdit: false,width:250},
			{field: 'supervisor_name',displayName:"Supervisor Name",enableCellEdit: false,width:200},
			{ field: 'office_email',displayName:"Email Id",enableCellEdit: false, width:250 },
		];
	  $scope.gridOptions = {
				exporterMenuCsv: true,
			    enableGridMenu: true,
			    gridMenuTitleFilter: fakeI18n,
				//paginationPageSizes: [25, 50, 75],
			    //paginationPageSize: 10,
		enableFiltering: true,
		columnDefs: $scope.tempDef,

		onRegisterApi: function (gridApi) {
		$scope.grid1Api = gridApi;

		/*$interval( function() {
		    gridApi.core.addToGridMenu( gridApi.grid, [{ title: 'Dynamic item', order: 100}]);
		  }, 0, 1);*/
     /*$scope.gridApi.treeBase.on.rowExpanded($scope, function(row) {
         if( row.entity.$$hashKey === $scope.gridOptions.data[50].$$hashKey && !$scope.nodeLoaded ) {

         }
       });*/

		 gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
			 var rowIndex = -1;
			    var hash = rowEntity.$$hashKey;

			    var data = $scope.gridOptions.data;     // original rows of data
			    for (var ndx = 0; ndx < data.length; ndx++) {
			        if (data[ndx].$$hashKey == hash) {
			            rowIndex = ndx;
			            break;
			        }
			    }

			    $http({
					url : 'hrm/commons/webservice/epmsOrgList/updateEmpOrgList?empnumber='+data[rowIndex].employee_number+'&filedname='+colDef.name+'&value='+newValue,
					method : "PUT"
				}).success(function(data, status, headers, config) {

					console.log(data);




				});
	            $scope.msg.lastCellEdited = 'edited row id:' + rowIndex + ' Column:' + colDef.name +  ' newValue:' + newValue + ' oldValue:' + oldValue ;
	            $scope.$apply();
	          });
		  gridApi.core.on.columnVisibilityChanged( $scope, function( changedColumn ){
		    $scope.columnChanged = { name: changedColumn.colDef.name, visible: changedColumn.colDef.visible };
		  });
		}
		};
	$rootScope.showLanding = true;
	$rootScope.showEmployeeSearch = false;
	$rootScope.showOrgEmployeeSearch = false;
	$scope.isData = true;
	$rootScope.role="";
	$rootScope.admin=false;
	$scope.role=$rootScope.role;
	$scope.rollups = [];
	//Idle.watch();

	//console.log(angular.fromJson(window.localStorage.getItem('ngIdle.ngIdleStorage')));
	var loggedInEmpId = $localStorage.employeeId;

	if(loggedInEmpId == null || loggedInEmpId === undefined){
		$location.path("/login");
	}else{
	$scope.getEmpOrgList = function(empId){ 
		SegmenthirarchyService.getSegmentStructure($localStorage.orgId).then(
			function(data) {
				console.log(data);
				if(data){
					data = $filter('orderBy')(data.osiSegmentStructureDetailses, '-segmentStructureDetailsSeq', true);
					angular.forEach(data, function(value, key){
						console.log(value.segmentStructureDetailsDesc);
						$scope.rollups[key] = value.segmentStructureDetailsDesc;
					});
					console.log($scope.rollups);
					prepareColDef();
					console.log($scope.tempDef);
				}
			});
		
		var employeeId;
		console.log(empId);
		if(empId != undefined){
			employeeId = empId;
		}else{
			employeeId = loggedInEmpId;
		}
		$http({
			//url : 'hrm/commons/webservice/epmsOrgList/getEmpOrgList/'+employeeId,
      url : configData.url + 'orghierarchy/getEmpOrgList/'+employeeId,
			method : "GET"
		}).success(function(data, status, headers, config) {
			console.log(data);

			console.log(data.length);
			if(data.length != 0){
				 $http({
						//url : 'hrm/commons/webservice/epmsOrgList/getLocationList',
            url : configData.url + 'orghierarchy/getLocationList',
						method : "GET"
					}).success(function(locations, status, headers, config) {
	            $scope.locations=locations;

					});

				for(var i=0;i<data.length;i++) {

  				//data[i].location=data[i].campus+"-"+data[i].location;
          data[i].location=data[i].city;
          //data[i].office_email = data[i].office_email.toLowerCase();
					data[i].$$treeLevel = (data[i].level)-0;
					/*if($rootScope.admin) {
						if(data[i].employee_id==loggedInEmpId) {
							data[i].role=true;
						} else {
							data[i].role=true;
						}
					} else {
						data[i].role=false;
					}*/
        }

				$rootScope.length=data.length;
				$scope.orgList = data;
				$scope.isData = true;
				$scope.filterStreams(data);
				$scope.gridOptions.data = data;
				$scope.gridOptions.role= $rootScope.role;
				$rootScope.data=data;
			}/*else{
				$rootScope.isTrascError = true;
				var msg = appConstants.exceptionMessage;
				if(error.data.httpStatus){ 
					msg=error.data.errorMessage; 
				}
				FlashService.Error(msg);
				$timeout(function () {
					$rootScope.isTrascError=false;
				}, 2000);
			} */
		});

    };
	}
	$scope.showMe=function(value)
	{

		//alert($rootScope.data.length);
		for(var i=0;i<$rootScope.data.length;i++)
			{
			if(value==$rootScope.data[i].employee_number)
				{
				//alert($rootScope.data[i].employee_id);
				 // $window.location.href = "#/updateProfile/"+$rootScope.data[i].employee_id;
				  window.open("#/updateProfile/"+$rootScope.data[i].employee_id,"_self");


				}
			}
				}
	 $scope.expandAll = function(){
		    $scope.gridApi.treeBase.expandAllRows();
		  };
		  $scope.toggleRow = function( rowNum ){
		//alert("in");
		grid.api.expandable.toggleRowExpansion(rowNum);
			//    $scope.gridApi.treeBase.toggleRowTreeState($scope.gridApi.grid.renderContainers.body.visibleRowCache[rowNum]);

			  };

	$scope.filterStreams = function(data) {
		console.log('filterStreams function called....');
		//console.log(data);
		var streamsData = [];
		angular.forEach(data,function(value,key){
			var jsonData = {};
			//console.log(value.stream_id+':'+value.stream_name);
			jsonData['stream_id'] = value.stream_id;
			jsonData['stream_name'] = value.stream_name;
			streamsData.push(jsonData);
		});

		$scope.streams = streamsData;
		//console.log($scope.streams);
	}

	// Prepare Column Definitions for Data Grid
	function prepareColDef () {
		if($scope.rollups != undefined) {
			angular.forEach($scope.rollups, function(col, key) {
				var tempObject = {};
				tempObject.field = 'segment'+(key+1);
				tempObject.displayName = col;
				tempObject.enableCellEdit = false;
				tempObject.width = 100;
				$scope.tempDef.push(tempObject);
			});
		}
	}

}]);

app.filter('unique', function() {
	   return function(collection, keyname) {
	      var output = [],
	          keys = [];
	      //console.log("unique...."+keyname);
	      angular.forEach(collection, function(item) {
	          var key = item[keyname];
	          if(keys.indexOf(key) === -1) {
	              keys.push(key);
	              output.push(item);
	          }
	      });
	      //console.log("unique....outcome..."+JSON.stringify(output))
	      return output;
	   };
});
