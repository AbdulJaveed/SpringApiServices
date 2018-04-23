var app = angular.module('orgChartService', ['ngDialog','ngRoute','ngCookies']);
app.service('orgChartService', function($http,$rootScope,$routeParams,$window,$cookies,$location,configData) {
	var url = configData.url+'v1/employees';
	var accessFields = {};
	//var employeeId = $cookies.get('userId');

	//Activated access field
	//AccessFieldService.activateAcessFields();

	function getParameterByName(name, url) {
	    if (!url) url = window.location.href;
	    name = name.replace(/[\[\]]/g, "\\$&");
	    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
	        results = regex.exec(url);
	    if (!results) return null;
	    if (!results[2]) return '';
	    return decodeURIComponent(results[2].replace(/\+/g, " "));
	}

	var employeeId = getParameterByName('employeeId', url);
	console.log(employeeId);

	this.loadAllNodes = function(employeeId){
		 var urlx = url +'/all/get';
		 var dataObject  = {"isTreeView":"false", "employeeId":employeeId}
 		 $http.post(urlx,dataObject).then(function success(data, status, headers, config) {
			  $rootScope.$broadcast("GenerateTree",{data:data.data, showGrandChildren:true});
		 }, function error(data) {
			 console.log(data);
			//called asynchronously if an error occurs
			//or server returns response with an error status.
		 });
		console.log("broad cast end");
	}

	this.loadSelectedNodes = function(json){
		var targetName = json.name
		var urlx = url + '/all/get';
		var dataObject  = {"isTreeView":"false", "employeeId":json.id}
		var dataObjectWithName = {"isTreeView":"false", "employeeName":targetName}
		console.log("selected Nodes1:"+json.parent);
		if(json.parent === undefined){
			 $http({
				 method : 'POST',
				 url : urlx,
				 data : dataObject
			 })
			 //$event.stopPropagation()
			 .success(function(data, status, headers, config) {
				 console.log(data);
				 console.log("data:"+json.parent);


				  $rootScope.$broadcast("GenerateTree",{data:data, showGrandChildren:true});

			 }).error(function(data, status, headers, config) {
				//called asynchronously if an error occurs
				//or server returns response with an error status.
			 });

			return;
		}

		 $http({
			 method : 'POST',
			 url : urlx,
			 data : dataObjectWithName
		 }).success(function(data, status, headers, config) {
			 console.log(data);
			  $rootScope.$broadcast("GenerateTree",{data:data, showGrandChildren:true});

		 }).error(function(data, status, headers, config) {
			//called asynchronously if an error occurs
			//or server returns response with an error status.
		 });
	}


	this.generateEmployeeData=function(employeeId){
//		var latestData;
		var urlx = url + '/all/get';
		var dataObject  = {"isTreeView":"false", "employeeId":employeeId}
		 $http({
			 method : 'POST',
			 url : urlx,
			 data : dataObject
		 }).success(function(data, status, headers, config) {
			 console.log(data);

			// return data;
			$rootScope.$broadcast("GenerateLatestEmployeeData",{data:data});

		 }).error(function(data, status, headers, config) {
			//called asynchronously if an error occurs
			//or server returns response with an error status.
		 });
//		 console.log("latestData:"+latestData.id);
//		 return latestData;
	}

	this.getAllEmployees = function(supervisorId) {
		var urlx = url + '/all/get';
		var dataObject  = {"displayAll":true}
		repeatSelect: null,
	console.log("supervisor:"+supervisorId);
		$http({
			method : 'POST',
			url : urlx,
			data : dataObject
		}).success(function(data, status, headers, config) {
//			$rootScope.$broadcast("GetAllSpells",{data:data}); //TODO: enable this later
			var supervisorListSelectElement = angular.element( document.querySelector( '#supervisorListSelect' ) );
			supervisorListSelectElement.append("<option value='0'> "+"None"+ "</option>");
			angular.forEach(data, function(employee, key){
//				console.log(employee);
				supervisorListSelectElement.append("<option value='"+employee.id+"'>"+employee.name+" </option>");
			});
			$("#supervisorListSelect option[value='" + supervisorId + "']").attr("selected","selected");
			//var supervisorListSelectElement = angular.element( document.querySelector( '#spellListSelect' ) );
			//supervisorListSelectElement.append($scope.spells);
		}).error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});

	};

	this.generateAddNewModal = function(employee, isFromDisplayChart){
		$rootScope.$broadcast("AddNewEmployee",{employee:employee,isFromDisplayChart : isFromDisplayChart});
	}

	this.generateEditModal = function(employeeId){
		$rootScope.$broadcast("EditEmployee",{employeeId:employeeId});
	}

	this.generateViewModal = function(employeeId){
		$rootScope.$broadcast("ViewEmployee",{employeeId:employeeId});
	}

});
