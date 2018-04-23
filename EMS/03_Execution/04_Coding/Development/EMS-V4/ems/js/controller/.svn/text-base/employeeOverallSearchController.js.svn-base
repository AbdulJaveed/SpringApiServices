'use strict';
angular.module('myApp.employeeOverallSearchController', []).controller('EmployeeOverallSearchController',
EmployeeOverallSearchController);
EmployeeOverallSearchController.$inject = ['configData', '$scope', '$rootScope', 
	'$http', '$localStorage', 'EmployeesService', 'FlashService', '$timeout','appConstants' 
	, '$interval', '$q', 'uiGridTreeViewConstants','$compile'];
function EmployeeOverallSearchController(configData, $scope, $rootScope,  $http,
		$localStorage, EmployeesService, FlashService, $timeout,appConstants, 
		$interval, $q, uiGridTreeViewConstants, $compile) {
	var vm = this;
	$scope.usOrgCode = configData.usOrgCode;
configData
	var fakeI18n = function( title ){
	    var deferred = $q.defer();
	    $interval( function() {
	      deferred.resolve( 'col: ' + title );
	    }, 1000, 1);
	    return deferred.promise;
	};
	$scope.gridOptions = {
	};
	$scope.isAccessControl = false;
	if($localStorage.isUserResponsibility || $localStorage.isFunctionExclusion ||$localStorage.isOperationExclusion ){
		$scope.isAccessControl = true;
	 }
	$rootScope.isheader = true;
    $rootScope.isTrascError = false;
   	$scope.search = {};
	$scope.searchFieldsList = []; 
	if($localStorage.iseditable != undefined)
		$scope.iseditable = $localStorage.iseditable;
	else
	 	$scope.iseditable = true;

    $scope.rowSize = 5;
    $scope.successTextAlert = "";
    $scope.showSuccessAlert = false;
    $scope.employeeList = [];
	$scope.OsiEmployees = [];
    
    $scope.init = function () {
		//openorgNamePopUp();
		vm.searchFields = [{name:'skill',value:'skills'},
								{name:'certification',value:'certifications'},
								{name:'project',value:'projects'}
								];
		
    };
    vm.clearEmployeeSearch = function () {
    		$("#employeeSearchDate").val("");
    		$scope.searchempDate = '';
			$localStorage.searchByDate = undefined;
    }
    $scope.clearSearch = function () {
		console.log($scope.search);
		$scope.search={};
		
		angular.forEach($scope.searchFieldsList, function(val, key){
			$('#'+val+'Div').remove();
		});
		$scope.searchFieldsList = [];
		$scope.showButtons = false;
		$scope.showRemoveButton = false;
		$scope.gridOptions.data = [];
		$scope.gridOptions.selectedItems.length = 0;
    };
$scope.init();

vm.addSearchField = function () {
	//console.log($scope.searchField);
	if($scope.searchField) {
		var addFlag = false;
		var idx = $scope.searchFieldsList.length;
		if($scope.searchFieldsList.length == 0) {
			idx = idx + 1;
			$scope.searchFieldsList.push($scope.searchField+idx);
			addFlag = true;
			$scope.search[$scope.searchField+idx] = '';
		} else {
			//if(_.indexOf($scope.searchFieldsList,$scope.searchField) == -1){
				//console.log(findMaxIdx());
				idx = findMaxIdx();
				$scope.searchFieldsList.push($scope.searchField+idx);
				addFlag = true;
				$scope.search[$scope.searchField+idx] = '';
			//}
		}
		//console.log($scope.searchFieldsList);
		if(addFlag) {
                    
			var t = "<span><span style='display:table;margin-left: 15px;' id='"+$scope.searchField+idx+"Div' >"+
			"<span style='display:table-cell' ng-click=vm.remove('"+$scope.searchField+idx+"') class='glyphicon glyphicon-remove'></span>"+
			"<input class='form-control form-control-sm' name='"+$scope.searchField+idx+"' id='"+$scope.searchField+idx+"' placeholder='"+capitalizeFirstLetter($scope.searchField+idx)+"' type='text'>"+
			
			"</span></span>";
                                
			var tt = $compile(t)($scope);
			$('#dynamicSearchFields').append(tt);
			/*$('#dynamicSearchFields').append("<div class='col-lg-5' id='"+$scope.searchField+idx+"Div'>"+
			"<button ng-click=vm.remove('"+$scope.searchField+idx+"')><span class='glyphicon glyphicon-remove'></span></button>"+
			"<input class='form-control' name='"+$scope.searchField+idx+"' id='"+$scope.searchField+idx+"' placeholder='"+$scope.searchField+"' type='text'>"+
			"<br/></div>");
			*/

		}
	} else {
		$rootScope.isTrascError = true;
		FlashService.Error('Please Select A Search Field');
		$timeout(function () {
			$rootScope.isTrascError=false;
		}, 2000);
	}
	if($scope.searchFieldsList.length)
		$scope.showButtons = true;		
}
// capitalize the place holder
function capitalizeFirstLetter(input) {
    return input.charAt(0).toUpperCase() + input.substr(1).toLowerCase();
}
$scope.searchEmployees = function() {
	//console.log($scope.searchFieldsList);
	var finalSearchInput = {};
	var skillValues = '';
	var certificateValues = '';
	var projectValues = '';
	var invalidDataFlag = false;
	var invalidDataField = '';
	angular.forEach($scope.searchFieldsList, function(val, key){
		var tVal = $('#'+val).val();
		if(tVal.trim() == "") {
			invalidDataFlag = true;
			invalidDataField = val;
		}else {
			if(val.startsWith('skill')) {			
				skillValues = skillValues.concat('@', $('#'+val).val());
			} else if(val.startsWith('certification')) {
				certificateValues = certificateValues.concat('@', $('#'+val).val());
			} else if(val.startsWith('project')) {
				projectValues = projectValues.concat('@', $('#'+val).val());
			}
		}
	});
	if(skillValues.length) {
		skillValues = skillValues.trim().replace(/@/gi,"|");
		finalSearchInput['skills'] = skillValues;
	}
	if(certificateValues.length) {
		certificateValues = certificateValues.trim().replace(/@/gi,"|");
		finalSearchInput['certifications'] = certificateValues;
	}
	if(projectValues.length) {
		projectValues = projectValues.trim().replace(/@/gi,"|");
		finalSearchInput['projects'] = projectValues;
	}
	console.log(finalSearchInput.skills);
	console.log(finalSearchInput.certifications);
	console.log(finalSearchInput.projects);
	if( finalSearchInput.skills != undefined && finalSearchInput.skills.charAt( 0 ) === '|' )
		finalSearchInput.skills = finalSearchInput.skills.slice( 1 );
	if( finalSearchInput.certifications != undefined && finalSearchInput.certifications.charAt( 0 ) === '|' )
		finalSearchInput.certifications = finalSearchInput.certifications.slice( 1 );
	if( finalSearchInput.projects != undefined && finalSearchInput.projects.charAt( 0 ) === '|' )
		finalSearchInput.projects = finalSearchInput.projects.slice( 1 );
	console.log(finalSearchInput.skills);
	console.log(finalSearchInput.certifications);
	console.log(finalSearchInput.projects);
	// finalSearchInput[val] = $('#'+val).val();
	// console.log(skillValues);
	// console.log(certificateValues);
	// console.log(projectValues);
	// console.log(finalSearchInput);
	
	// angular.forEach(finalSearchInput, function(val,key) {
	// 	//console.log(val);
	// 	if(val.trim() == "") {
	// 		invalidDataFlag = true;
	// 		invalidDataField = key;
	// 	}
	// });
	if(invalidDataFlag) {
		$rootScope.isTrascError = true;
		var msg = 'Please Enter Valid Input or Remove The Field '+invalidDataField;
		FlashService.Error(msg);
		$timeout(function () {
			$rootScope.isTrascError=false;
		}, 3000);
	} else {
		$rootScope.isTrascError=false;
		EmployeesService.overallsearch(finalSearchInput).then(function (data) {
			console.log(data);
			if(data != "")
				$scope.gridOptions.data = data;
			else
			$scope.gridOptions.data = [];
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
}
$scope.showRemoveButton = false;
$scope.isExist = function() {
	if(_.indexOf($scope.searchFieldsList,$scope.searchField) != -1){
		$scope.showRemoveButton = true;
	}
}
function findMaxIdx() {
	var idx = 0;
	angular.forEach($scope.searchFieldsList, function(val, key) {
		if(val.indexOf($scope.searchField) != -1) {
			idx = parseInt(val.split($scope.searchField)[1]);
		}
	});
	return ++idx;
}
vm.remove = function(id) {
	//alert(id);
	$('#'+id+'Div').remove();
	var index = $scope.searchFieldsList.indexOf(id);
	if(index > -1)
		$scope.searchFieldsList.splice(index, 1);
	//console.log($scope.searchFieldsList);
	if(!$scope.searchFieldsList.length) {
		$scope.search = {};
		$scope.showButtons = false;
		$scope.showRemoveButton = false;
	}
}
vm.removeSearchField = function () {
	//console.log($scope.searchFieldsList);
	$('#'+$scope.searchField+'Div').remove();
	var index = $scope.searchFieldsList.indexOf($scope.searchField);
	if(index > -1)
		$scope.searchFieldsList.splice(index, 1);
	//console.log($scope.searchFieldsList);
	if(!$scope.searchFieldsList.length) {
		$scope.search = {};
		$scope.showButtons = false;
		$scope.showRemoveButton = false;
	}
}
	// UI- GRID CODE
		$scope.tempDef = [
			{ field: 'employeeNumber', displayName:"Employee No",enableCellEdit: false, width:100},
			{ field: 'firstName', displayName:"First Name",enableCellEdit: false,width:200},
			{ field: 'lastName', displayName:"Last Name" ,enableCellEdit: false,width:200},
			{ field: 'officeEmail',displayName:"Email Id",enableCellEdit: false, width:200 },
			//{field: 'mailStop',displayName:"Mail Stop",enableCellEdit: false,width:100},
			{field: 'osiOrganizationsDTO.orgName',displayName:"Organization",enableCellEdit: false,width:150},
			{field: 'locationName',displayName:"Location",enableCellEdit: false,width:100},
			{field: 'projects',displayName:"Project(s) Name",enableCellEdit: false,width:200, visible: false},
		];
		$scope.gridOptions = {
				exporterMenuCsv: true,
				enableGridMenu: true,
				gridMenuTitleFilter: fakeI18n,
				//paginationPageSizes: [25, 50, 75],
				//paginationPageSize: 10,
				enableFiltering: true,
				columnDefs: $scope.tempDef,
				exporterPdfDefaultStyle: {fontSize: 8},
				//exporterPdfTableStyle: {margin: [30, 30, 30, 30]},
				exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'blue'},
				exporterPdfOrientation: 'landscape',
				exporterPdfPageSize: 'A4',
				//exporterPdfMaxGridWidth: 500,

				onRegisterApi: function (gridApi) {
					$scope.grid1Api = gridApi;

					gridApi.core.on.columnVisibilityChanged( $scope, function( changedColumn ){
						$scope.columnChanged = { name: changedColumn.colDef.name, visible: changedColumn.colDef.visible };
					});
				}
		};
		$scope.cancelProfile = function(){
			if($localStorage.currentEmployeeId!=$localStorage.loggedInEmployeeId)
				$window.location.href='#/employeeInfo-sf';
			else
			$window.location.href='#/profile';
		}

}
