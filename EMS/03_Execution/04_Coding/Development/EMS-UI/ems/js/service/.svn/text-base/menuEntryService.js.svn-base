(function () {
    'use strict';

    angular
            .module('myApp.menuEntryService', [])
            .factory('menuEntryService', menuEntryService);
    menuEntryService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];

    function menuEntryService($http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q)
    {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
        service.getOneMenuEntry=getOneMenuEntry;
        service.getMenuEntriesInitially = getMenuEntriesInitially;
        service.getAllMenuEntries=getAllMenuEntries;
        service.saveMenuEntry=saveMenuEntry;
        service.updateMenuEntry=updateMenuEntry;
        service.deleteMenuEntry=deleteMenuEntry;
        service.getLookupByLookupName=getLookupByLookupName;
        service.getAllMenus=getAllMenus;
        service.searchEntries=searchEntries;
        service.getAllFunctions=getAllFunctions;
         service.deferred = $q.defer();
        return service;
        
        function getOneMenuEntry(orgInvId){
            return $http.get(configData.url+'menu-entries/'+orgInvId,config).then(handleSuccess, handleError('Error getting menu entry.'));
        }
        
        function getMenuEntriesInitially(){
           
            return $http.get(configData.url+'menu-entries-initially',config).then(handleSuccess, handleError);
        }
        
        function getAllMenuEntries(){
           return $http.get(configData.url+'menu-entries',config).then(handleSuccess, handleError('Error getting all menu entries'));
        }
        
        function saveMenuEntry(data) {
           // console.log("Saving:",JSON.stringify(data));
            return $http.post(configData.url+'menu-entries-multiple', data,config).then(handleSuccess, handleError('Error while saving menu entry. '));
        }
        
        function updateMenuEntry(data) {
            //console.log("Updating:",JSON.stringify(data));
            return $http.put(configData.url+'menu-entries', data,config).then(handleSuccess, handleError('Error while updating menu entry. '));
        }
        
        function deleteMenuEntry(data){
            return $http.post(configData.url+'deleteMenuEntry',data, config).then(handleSuccess, handleError('Error while deleting Menu Entry.'));
        }
        
         function searchEntries(data){
            return $http.post(configData.url+'searchEntries',data, config).then(handleSuccess, handleError('Error searching Menu Entry. '));
        }
        
        function getAllMenus(){
           return $http.get(configData.url+'menus',config).then(handleSuccess, handleError('Error getting all menus.'));
        }
        
        function getAllFunctions(){
           return $http.get(configData.url+'functions',config).then(handleSuccess, handleError('Error getting all functions.'));
        }
        
        
        function getLookupByLookupName(data){
            console.log("getLookupByLookupName:"+data);
            return $http.get(configData.url+'getLookupByLookupName/'+data,config).then(handleSuccess, handleError('Error getting OrgInventoryItem'));
        }
        
        
        
       /* function handleSuccess(res) {
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
            return $q.reject(error); 
        } 
    }

})();