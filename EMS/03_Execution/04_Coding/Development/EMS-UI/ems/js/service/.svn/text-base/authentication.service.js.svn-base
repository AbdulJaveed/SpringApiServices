(function () {
    'use strict';
 
    angular
        .module('myApp.AuthenticationService',[])
        .factory('AuthenticationService', AuthenticationService);
 
    AuthenticationService.$inject = ['$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','$location', '$window'];
    function AuthenticationService($http, $cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage, $location, $window) {
        var service = {};
 
        service.Login = Login;
        service.SetToken = SetToken;
        service.ClearToken = ClearToken;
        service.Logout =Logout;
        service.availableOperations = availableOperations;
        return service;
        function Login(username, password, callback) {
          
            $http.post('http://localhost:8090/restservices/api/validate-login', { userName: username, password: password })
                .success(function (response) {
                    callback(response);
            }).error(function (error) {
            	if(error==null){
            		console.log("error");
            		$location.path('/error');
                  //   $window.location.reload(true);
            	}
            });
 
        }

        function SetToken(result) {
			$rootScope.globals = {
                userDTO: {userName:result.userName, firstName:result.firstName, lastName:result.lastName, empNumber:result.empNumber, token:result.token}
            };
            $http.defaults.headers.common['Auth_Token'] = result.token;
           $cookieStore.put('globals', $rootScope.globals);
        }
 
        function ClearToken() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common['Auth_Token'] = '';
        }
        function Logout() {
        	$http.get('http://localhost:8090/restservices/api/logout')
            .success(function (response) {
               // callback(response);
            	console.log('logout--')
                ClearToken();
                $location.path('/login');
                $window.location.reload(true);
            }).error(function (response) {
            	AuthenticationService.ClearToken();
               // callback(response);
            });
            
        }
        function availableOperations(functionId, url){
        	if(url.indexOf('logout')!=-1){
        		Logout();
        		//$window.location.reload(true);
        	}else if(functionId!=null && functionId!='undefined'){
        		$location.path(url);
        	$http.get('http://localhost:8080/api/user-exl-operataions/'+functionId)
            .success(function (response) {
            	delete $localStorage.availOperations;
            	$localStorage.availOperations = response;
            });
        	}
        }
    }
 
})();