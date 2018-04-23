'use strict';

angular.module('myApp.uploadController', []).controller('uploadController', uploadController);

uploadController.$inject = [ '$q', '$scope', '$rootScope', '$cookieStore', '$timeout', '$window', 'Upload', 'configData', '$http'];
function uploadController($q, $scope, $rootScope, $cookieStore, $timeout, $window, Upload, configData, $http) {
	
	
	$scope.getImage = function() {
		
		$http.get(configData.url+"crud/upload/getImage").then(function(data){
			console.log(data.data.fileContent);
			$scope.picFile = 'data:image/png;base64,'+data.data.fileContent;
		},function(res){
			console.log(res.data);
		});
	}
	$scope.uploadPic = function(picFile){
		console.log(picFile);
		var imgDetails = {};
		imgDetails.originalFileName = picFile.name;
		imgDetails.fileType = picFile.type;
		Upload.base64DataUrl(picFile).then(function(){
			$scope.picFile.result="true";
			console.log(JSON.stringify(picFile.$ngfDataUrl));
			var str =picFile.$ngfDataUrl; 
			
			imgDetails.fileContent = str.substring(str.indexOf(",")+1);
			
			
			var config = { headers: { 'Auth_Token': $cookieStore.get('globals').userDTO.token } };
			$http.post(configData.url+"crud/upload/image", imgDetails).then(handleSuccess, handleError('Error getting Organizations'));
			function handleSuccess(res) {
	            var deferred = $q.defer();
	            if (res.data.errorCode) {
	                deferred.reject(res.data);
	            }else{
	                deferred.resolve(res.data);
	            }
	            return deferred.promise;
	        } 
			
		});
		console.log(imgDetails);
		

        function handleError(error) {
            return $q.reject(error);
        }
	}
	
}