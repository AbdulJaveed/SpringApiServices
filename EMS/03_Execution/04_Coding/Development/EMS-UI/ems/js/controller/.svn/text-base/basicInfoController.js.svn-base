(function () {
    'use strict';

    angular
        .module('myApp.basicInfoController',[])
        .controller('myApp.basicInfoController', basicInfoController);
    basicInfoController.$inject = ['$location', '$rootScope', '$scope', '$timeout','$localStorage', '$http', '$window'];
    function basicInfoController($location, $rootScope, $scope, $timeout,$localStorage, $http, $window) {
    		 var vm = this;
    		 vm.basicInfo = {};
    		 $scope.iseditable = false;
    		 vm.basicInfo.fullName =  $localStorage.employeeName;
    		 vm.basicInfo.employeeNumber = $localStorage.employeeNumber;
    		 vm.basicInfo.osiEmpAttachments =  $localStorage.osiEmpAttachments;
    		 vm.basicInfo.orgName = $localStorage.orgName;
    	        	 vm.preview = {};
    	        	if(vm.basicInfo.osiEmpAttachments!=undefined && vm.basicInfo.osiEmpAttachments.length > 0) {
    	        		vm.preview.content = vm.basicInfo.osiEmpAttachments[0].fileContent;
    	        		vm.preview.name = vm.basicInfo.osiEmpAttachments[0].originalFileName;
    	        		vm.preview.attachmentId = vm.basicInfo.osiEmpAttachments[0].attachmentId;
    	        	}
    }
})();
