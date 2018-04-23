(function () {
    'use strict';

    angular.module('myApp.AssignmentsService', []).factory('AssignmentsService', AssignmentsService);

    AssignmentsService.$inject = ['$http', '$cookieStore','configData','$q'];
    function AssignmentsService($http, $cookieStore, configData,$q) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };

        service.GetAllAssignments = GetAllAssignments;
        service.SaveAssignments = SaveAssignments;
	service.GetAllJobCodes = GetAllJobCodes;
        service.GetAllGrades = GetAllGrades;
        service.GetAllEmployees = GetAllEmployees;
        service.GetAllLocations = GetAllLocations;
        service.GetAllDepartment = GetAllDepartment;
        service.SearchEmployeeByEmpName = SearchEmployeeByEmpName;
        service.SearchSupervisorByNumber = SearchSupervisorByNumber;
        service.FindAssignmentByEmployeeId = FindAssignmentByEmployeeId;
        service.FindAssignmentByEmployeeIdAndDate = FindAssignmentByEmployeeIdAndDate;
        //service.SearchEmployeeById = SearchEmployeeById;
        service.getJobCodesById = getJobCodesById;
        service.getGradeById = getGradeById;
        service.getOsiLocationById = getOsiLocationById;
        service.saveEmployeeRollUps = saveEmployeeRollUps;
        service.getSegmentDropDownData = getSegmentDropDownData;
        service.findRollUpsById = findRollUpsById;
        return service;

	function GetAllAssignments() {
            return $http.get(configData.url+'assignments/getInitialAssignmentsList',config).then(handleSuccess, handleError('Error getting all assignments.'));
	}
	
	function getSegmentDropDownData(data) {
        return $http.post(configData.url+'assignments/loadRollupsPopup',data,config).then(handleSuccess, handleError('Error getting all assignments.'));
	}
	function saveEmployeeRollUps(data) {
        return $http.post(configData.url+'assignments/saveRollups',data,config).then(handleSuccess, handleError('Error getting all assignments.'));
}
        function SaveAssignments(data, action) {
            return $http.post(configData.url+'assignments/saveAssignments/'+action,data, config).then(handleSuccess, handleError('Error getting saving assignments.'));
	}

        function GetAllJobCodes(empId) {
            return $http.get(configData.url+'assignments/getAllJobCodes/' + empId,config).then(handleSuccess, handleError('Error getting all jobs.'));
	}

        function GetAllGrades(empId) {
            return $http.get(configData.url+'assignments/getAllGrades/' + empId,config).then(handleSuccess, handleError('Error getting all grades.'));
	}
        function getGradeById(gradeId) {
            return $http.get(configData.url+'assignments/findGradesById/'+gradeId,config).then(handleSuccess, handleError('Error getting all grades.'));
	}
        function getJobCodesById(jobCodeId) {
            return $http.get(configData.url+'assignments/findJobCodesById/'+jobCodeId,config).then(handleSuccess, handleError('Error getting all grades.'));
	}
        function getOsiLocationById(locationId) {
            return $http.get(configData.url+'assignments/findOsiLocationsById/'+locationId,config).then(handleSuccess, handleError('Error getting all grades.'));
	}
        function GetAllEmployees() {
            return $http.get(configData.url+'assignments/getAllEmployees',config).then(handleSuccess, handleError('Error getting all employees.'));
	}

        function GetAllLocations(empId) {
            return $http.get(configData.url+'assignments/getAllLocations/' + empId,config).then(handleSuccess, handleError('Error getting all org lcoations.'));
	}

        function GetAllDepartment() {
            return $http.get(configData.url+'assignments/getAllDepartment',config).then(handleSuccess, handleError('Error getting all department.'));
	}

        function SearchEmployeeByEmpName(data) {
            return $http.post(configData.url+'assignments/searchEmployeeByEmpName/' + data,config).then(handleSuccess, handleError('Error getting employees for referrals.'));
	}

        function SearchSupervisorByNumber(data) {
            return $http.post(configData.url+'assignments/searchSupervisorByNumber/' + data,config).then(handleSuccess, handleError('Error getting supervisor.'));
	}

//        function SearchEmployeeById(empId) {
//            return $http.post(configData.url+'assignments/searchEmployeeById/'+empId,config).then(handleSuccess, handleError('Error searching employee by employee id.'));
//	}

        function FindAssignmentByEmployeeId(empId) {
            return $http.get(configData.url+'assignments/findAssignmentById/'+empId,config).then(handleSuccess, handleError('Error searching assignments by employee id.'));
	      }

        function FindAssignmentByEmployeeIdAndDate(inputObject) {
            return $http.post(configData.url+'assignments/findAssignmentById', inputObject,config).then(handleSuccess, handleError('Error searching assignments by employee id.'));
	      }

        function findRollUpsById(rollUpId) {
            return $http.get(configData.url+'assignments/findRollUpsById/'+rollUpId,config).then(handleSuccess, handleError('Error searching assignments by employee id.'));
	      }
        

        function handleSuccess(res) {
            var deferred = $q.defer();
            if(res.data.errorCode) {
                   console.log(res.data.errorCode+' - '+res.data.errorMessage);
                   deferred.reject(res.data);
            } else {
                   deferred.resolve(res.data);
            }
            return deferred.promise;
        }

        function handleError(error) {
            return $q.reject(error);
        }


    }
})();
