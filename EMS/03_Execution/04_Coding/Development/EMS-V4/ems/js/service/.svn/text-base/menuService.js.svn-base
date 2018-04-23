(function () {
    'use strict';

    angular
            .module('myApp.MenuService', [])
            .factory('MenuService', MenuService);

    MenuService.$inject = ['$http', '$q', '$cookieStore', 'configData'];

    function MenuService($http, $q, $cookieStore, configData) {
        var service = {};
        var config = {headers: {'Auth_Token': $cookieStore.get('globals').userDTO.token}};
        service.getAllMenus = getAllMenus;
        service.getMenusListInitially = getMenusListInitially;
        service.getAllMenusList = getAllMenusList;
        service.saveMenu = saveMenu;
        service.updateMenu = updateMenu;
        service.deleteMenu = deleteMenu;
        service.searchMenu=searchMenu;
        service.getReportGroups = getReportGroups;
        return service;
        function getAllMenus() {
            return $http.get(configData.url + 'menus', config).then(handleSuccess, handleError);
        }
        function getMenusListInitially() {
            return $http.get(configData.url + 'menus/listInitially', config).then(handleSuccess, handleError);
        }
        function getAllMenusList() {
            return $http.get(configData.url + 'menus/list', config).then(handleSuccess, handleError);
        }
        function saveMenu(data) {
            return $http.post(configData.url + 'menus', data, config).then(handleSuccess, handleError);
        }
        function updateMenu(data) {
            return $http.put(configData.url + 'menus', data, config).then(handleSuccess, handleError);
        }
        function deleteMenu(data) {
            return $http.post(configData.url + 'menus/deleteMenu', data, config).then(handleSuccess, handleError);
        }
        
        function getReportGroups() {
            return $http.get(configData.url+'all-active-report-groups',config).then(handleSuccess, handleError);
        }
        
        function searchMenu(data){
             return $http.post(configData.url + 'menus/searchMenu', data, config).then(handleSuccess, handleError);
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
        
        function handleError(error) { 
        	return $q.reject(error); 
        }
    }

})();



