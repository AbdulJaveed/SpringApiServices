(function() {
    'use strict';
    angular.module('myApp.AuthenticationService', []).factory(
            'AuthenticationService', AuthenticationService);
    AuthenticationService.$inject = ['$http', '$cookieStore', '$rootScope',
        '$localStorage', '$sessionStorage', '$location',
        '$window', 'configData','$q'];
    function AuthenticationService($http, $cookieStore, $rootScope,
            $localStorage, $sessionStorage, $location, $window, configData,$q) {
        var service = {};
        service.Login = Login;
        service.forgotPassword=forgotPassword;
        service.SetToken = SetToken;
        service.ClearToken = ClearToken;
        service.Logout = Logout;
        service.updatePassword=updatePassword;
        service.availableOperations = availableOperations;
        service.Base64Encode = Base64Encode;
        service.Base64Decode = Base64Decode;
        service.getUserFunctions = getUserFunctions;
        service.getUserExFunctions = getUserExFunctions ;
        return service;
        
        function getUserFunctions(userId, callback) {
            $http.get(configData.url+'userFunctions/'+userId, config).success(function(response) {
                //SetToken(response);
                callback(response);
            }).error(function(error) {
                if (error == null) {
                    console.log("authenticate error");
                    $location.path('/error');
                    // $window.location.reload(true);
                }
            });
        }

        function getUserExFunctions(funcId, userId, callback) {
            return $http.get( configData.url + 'user-exl-operataions/'+ funcId+'/'+userId, config).success(function(response) {
                
            }).error(function(error) {
                if (error == null) {
                    console.log("authenticate error");
                    $location.path('/error');
                    // $window.location.reload(true);
                }
            });
        }

        function updatePassword(user){
            return $http.put(configData.url+'updateUserPassword',user,config).then(handleSuccess, handleError);
        }
        function handleSuccess(res) {
			   var deferred = $q.defer();
			   if (res.data.errorCode) {
				   deferred.reject(res.data);
			   }else{
				   deferred.resolve(res.data);
			   }
			   return deferred.promise;
		   	} 

         function handleError(error) {
		   		 return $q.reject(error);
         }
        function Login(username, password, callback) {
            $http.post(configData.url + 'validate-login', {
                userName: username,
                password: password
            }).success(function(response) {
                SetToken(response);
                callback(response);
            }).error(function(error) {
                if (error == null) {
                    console.log("authenticate error");
                    $location.path('/error');
                    // $window.location.reload(true);
                }
            });
        }
        function forgotPassword(username, callback) {
            $http.post(configData.url + 'forgot-password-mail', {
                userName: username,
            }).success(function(response) {
                SetToken(response);
                callback(response);
            }).error(function(error) {
                if (error == null) {
                    console.log("authenticate error");
                    $location.path('/error');
                    // $window.location.reload(true);
                }
            });
        }

        function SetToken(result) {
            $rootScope.globals = {
                userDTO: {
                    userName: result.userName,
                    firstName: result.firstName,
                    lastName: result.lastName,
                    empNumber: result.empNumber,
                    token: result.token,
                    emailId:result.emailId,
                    id:result.id,
                    hostOrIp:result.hostOrIp,
                    orgId:result.orgId,
                    orgCode:result.orgCode,
                    jobTitle: result.jobTitle,
                    photo: result.photoPath
                }
            };
            $sessionStorage.logintoken = true;
            $http.defaults.headers.common['Auth_Token'] = result.token;
            $cookieStore.put('globals', $rootScope.globals);
            $cookieStore.put('configJsUrl', configData.url);
        }

        function ClearToken() {
          
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common['Auth_Token'] = '';
            $rootScope.userName = "";
            $rootScope.isLogin = false;
            $sessionStorage.Login = false;
            $sessionStorage.logintoken =false;
            $sessionStorage.menuTree = "";
       
        }

        function Logout() {
          
            var ck = document.cookie.split(';');
            for (var i = 0; i < ck.length; i++) {
                var cval = ck[i].split('=');
                document.cookie = cval[0] + "=" + cval[1] + "; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
            }
          
            var token = {headers: {'Auth_Token': $http.defaults.headers.common['Auth_Token']}};
            $http.get(configData.url + 'logout', token).success(
                    function(response) {
                        // callback(response);
                        
                        console.log('logout--');
                       // $sessionStorage.clear();
                        
                        ClearToken();
                        $location.path('/login');
                       $window.location.reload(true);
                    }).error(function(response) {
                       
                ClearToken();
            });
        }

        function availableOperations(functionId, url) {
            if (url.indexOf('logout') != -1) {
                Logout();
                // $window.location.reload(true);
            } else if (functionId != null && functionId != 'undefined') {
                var token = {headers: {'Auth_Token': $cookieStore.get('globals').userDTO.token}};
                $location.path(url);
                $http.get(
                        configData.url + 'user-exl-operataions/'
                        + functionId, token).success(function(response) {
                    console.log('availOperations:- ' + response);
                    delete $localStorage.availOperations;
                    $localStorage.availOperations = response;
                }).error(function(response) {
                    console.log("error");
                    $location.path('/error');
                });
            }
        }

        function Base64Encode(input) {
        	if(input) {
        		var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
        		var output = "";
        		var chr1, chr2, chr3 = "";
        		var enc1, enc2, enc3, enc4 = "";
        		var i = 0;
        		do {
        			chr1 = input.charCodeAt(i++);
        			chr2 = input.charCodeAt(i++);
        			chr3 = input.charCodeAt(i++);
        			enc1 = chr1 >> 2;
        			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        			enc4 = chr3 & 63;
        			if (isNaN(chr2)) {
        				enc3 = enc4 = 64;
        			} else if (isNaN(chr3)) {
        				enc4 = 64;
        			}
        			output = output +
        			keyStr.charAt(enc1) +
        			keyStr.charAt(enc2) +
        			keyStr.charAt(enc3) +
        			keyStr.charAt(enc4);
        			chr1 = chr2 = chr3 = "";
        			enc1 = enc2 = enc3 = enc4 = "";
        		} while (i < input.length);
        		return output;
        	}
        }
        
        function Base64Decode(input) {
          if(input) {
			var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
			var output = "";
			var chr1, chr2, chr3 = "";
			var enc1, enc2, enc3, enc4 = "";
			var i = 0;
			// remove all characters that are not A-Z, a-z, 0-9, +, /, or =
			var base64test = /[^A-Za-z0-9\+\/\=]/g;
			if (base64test.exec(input)) {
				window
						.alert("There were invalid base64 characters in the input text.\n"
								+ "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n"
								+ "Expect errors in decoding.");
			}
			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

			do {
				enc1 = keyStr.indexOf(input.charAt(i++));
				enc2 = keyStr.indexOf(input.charAt(i++));
				enc3 = keyStr.indexOf(input.charAt(i++));
				enc4 = keyStr.indexOf(input.charAt(i++));

				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;

				output = output + String.fromCharCode(chr1);

				if (enc3 != 64) {
					output = output + String.fromCharCode(chr2);
				}
				if (enc4 != 64) {
					output = output + String.fromCharCode(chr3);
				}

				chr1 = chr2 = chr3 = "";
				enc1 = enc2 = enc3 = enc4 = "";

			} while (i < input.length);
			return output;
		} 
      }
    }
})();