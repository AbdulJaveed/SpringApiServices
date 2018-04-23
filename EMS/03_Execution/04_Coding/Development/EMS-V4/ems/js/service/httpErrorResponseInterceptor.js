(function () {
    'use strict';
 
    var myApp = angular
        .module('myApp.httpErrorResponseInterceptor',[]);
myApp.factory('httpErrorResponseInterceptor', ['$q', '$location',
  function($q, $location) {
    return {
      response: function(responseData) {
        return responseData;
      },
      responseError: function error(response) {
        switch (response.status) {
          default:
            $location.path('/error');
        }

        return $q.reject(response);
      }
    };
  }
]);
 
})();