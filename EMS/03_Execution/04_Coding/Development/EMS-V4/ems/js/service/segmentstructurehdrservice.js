/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';

    angular
            .module('myApp.SegmentstructurehdrService', [])
            .factory('SegmentstructurehdrService', SegmentstructurehdrService);

    SegmentstructurehdrService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData'];

    function SegmentstructurehdrService($http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } }; 
        service.getSegmentStructureHdrId = getSegmentStructureHdrId;
       return service;
        
        function getSegmentStructureHdrId(segmentStructureHdrDesc){
            
            return $http.get(configData.url+'get-one-segment-structure-id/'+segmentStructureHdrDesc, config).then(handleSuccess, handleError('Error getting segmentStructureHdrId'));
        }
        
        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {success: false, message: error};
            };
        }
        
    }

   

})();