(function () {
    'use strict';

    angular.module('myApp.FlexiFieldService', []).factory('FlexiFieldService', FlexiFieldService);

    FlexiFieldService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','configData','$q'];
    function FlexiFieldService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,configData,$q) {
        var service = {};
        var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };

        service.GetFlexiFields = GetFlexiFields;
        service.getFunctionInfo = getFunctionInfo;
        return service;

        function GetFlexiFields(categoryName,orgId) {
            return $http.get(configData.url+'v1/flexidata/'+categoryName+'/'+orgId,config).then(handleSuccess, handleError('Error getting Flexi Fields '));
        }
        function getFunctionInfo(functionId, orgId) {
          return $http.get(configData.url+'v1/flexidata/functionInfo/'+functionId+'/'+orgId).then(handleSuccess, handleError('Error getting Flexi info '));
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
