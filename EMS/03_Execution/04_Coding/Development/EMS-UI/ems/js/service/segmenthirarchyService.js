(function () {
    'use strict';

    angular
            .module('myApp.SegmenthirarchyService', [])
            .factory('SegmenthirarchyService', SegmenthirarchyService);

    SegmenthirarchyService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData', '$q'];

    function SegmenthirarchyService($http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData, $q) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getAllSegments = getAllSegments;
        service.savesegment = savesegment;
        service.updatesegment = updatesegment;
        service.getSegmentHeaders=getSegmentHeaders;
        service.getSegmentStructure=getSegmentStructure;
        service.searchSegments=searchSegments;
        service.segmentAlreadyInuse=segmentAlreadyInuse;
        service.getSegmentStructureDetails = getSegmentStructureDetails;
        service.getSegmentStructureByHdrId = getSegmentStructureByHdrId;
        return service;

        function getAllSegments() {

            return $http.get(configData.url+'getall-segment-structure',config).then(handleSuccess, handleError('Error getting segments'));
        }
        function savesegment(data) {
            return $http.post(configData.url+'create-segment-structure', data,config).then(handleSuccess, handleError('Error saving lookups '));

        }

        function updatesegment(data) {

            return $http.put(configData.url+'update-segment-structure', data,config).then(handleSuccess, handleError('Error saving lookups '));

        }
        function getSegmentHeaders(segmentStructureHdrId){
             return $http.get(configData.url+'key-flex-fields/'+segmentStructureHdrId,config).then(handleSuccess, handleError('Error in getting segment headers'));
        }
        function getSegmentStructure(segmentStructHdrId){
             return $http.get(configData.url+'get-one-segment-structure-id/'+segmentStructHdrId,config).then(handleSuccess, handleError('Error in getting segment Strucrure'));
        }

        function getSegmentStructureByHdrId(segmentStructHdrId){
            return $http.get(configData.url+'getone-segment-structure/'+segmentStructHdrId,config).then(handleSuccess, handleError('Error in getting segment Strucrure'));
       }

         function searchSegments(data){
            return $http.post(configData.url+'searchSegment', data,config).then(handleSuccess, handleError('Error searching Segments '));
        }
        function segmentAlreadyInuse(data){
            return $http.post(configData.url+'segment-already-inuse', data,config).then(handleSuccess, handleError('Error in checking Segment dependency '));
        }

        function getSegmentStructureDetails(businessGroupId){
             return $http.get(configData.url+'get-one-segment-structure-id/'+businessGroupId,config).then(handleSuccess, handleError('Error in getting segment Strucrure'));
        }
        /*function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {success: false, message: error};
            };
        }*/
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
        	console.log(error);
        	var deferred = $q.defer();
        	deferred.reject(error);
        	return deferred.promise;
        }
    }

})();
