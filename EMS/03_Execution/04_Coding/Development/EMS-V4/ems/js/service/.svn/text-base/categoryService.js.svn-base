(function () {
    'use strict';

    angular
            .module('myApp.categoryService', [])
            .factory('categoryService', categoryService);
            categoryService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];

    function categoryService($http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q)
    {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
       
        service.getCategoriesInitially = getCategoriesInitially;
        service.getAllCategories=getAllCategories;
        service.updateCategory=updateCategory;
        service.getAllorganizations=getAllorganizations;
        service.getAllfunctions=getAllfunctions;
        service.saveCategory=saveCategory;
        service.updateCategory=updateCategory;
        service.searchCategories=searchCategories;
        service.getAlltableNames=getAlltableNames;
        service.getAllcolumnsByTableName=getAllcolumnsByTableName;
         service.deferred = $q.defer();
        return service;
        
        
        
        function getCategoriesInitially(){
           
            return $http.get(configData.url+'categories',config).then(handleSuccess, handleError);
        }
        
        function getAllCategories(){
           return $http.get(configData.url+'categories',config).then(handleSuccess, handleError('Error getting all categories'));
        }
        function getAllfunctions(){
            return $http.get(configData.url+'functions',config).then(handleSuccess, handleError('Error getting all functions'));
         }
        function getAllorganizations(){
            return $http.get(configData.url+'orgnaizations',config).then(handleSuccess, handleError('Error getting all orgnaizations'));
         }
         function getAllcolumnsByTableName(data){
            return $http.post(configData.url+'tablecolumns',data,config).then(handleSuccess, handleError('Error getting all orgnaizations'));
         }
         function getAlltableNames(){
            return $http.get(configData.url+'tablenames',config).then(handleSuccess, handleError('Error getting all table names'));
         }
        function saveCategory(data) {
           // console.log("Saving:",JSON.stringify(data));
            return $http.post(configData.url+'categories', data,config).then(handleSuccess, handleError('Error while saving category. '));
        }
        function searchCategories(data){
            return $http.post(configData.url+'searchCategories',data, config).then(handleSuccess, handleError('Error searching Menu Entry. '));
        }
        function updateCategory(data) {
            //console.log("Updating:",JSON.stringify(data));
            return $http.put(configData.url+'categories', data,config).then(handleSuccess, handleError('Error while updating menu entry. '));
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