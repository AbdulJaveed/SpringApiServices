(function () {
    'use strict';
    
    angular
            .module('myApp.CommonService',[])
            .factory('CommonService', CommonService); 
    
    CommonService.$inject = ['$q','$http', '$cookieStore', '$rootScope', '$timeout', '$localStorage', '$sessionStorage','$filter','configData'];
    
    function CommonService($q,$http,$cookieStore, $rootScope, $timeout, $localStorage, $sessionStorage,$filter,configData)
     {
         var service = {};
          service.Login = Login;
          var config = {headers: {'Auth_Token': $cookieStore.get('globals').userDTO.token}};
          service.getRoleSpecificItems = getRoleSpecificItems;
          service.getRoleSpecificItemsByDesc = getRoleSpecificItemsByDesc;
          service.getItemById = getItemById;
          service.getAllCategories = getAllCategories;
          service.getAllNonApprovalCategories = getAllNonApprovalCategories;
          service.getAllOperatingUnits = getAllOperatingUnits;   
          service.getAllSuppliers = getAllSuppliers;
          service.getSupplierById = getSupplierById;
          service.getHeadertype = getHeadertype;   
          service.getLinetype = getLinetype;  
          service.getDeliveryLocation = getDeliveryLocation;
          //service.getAllOsiInvOrgsById = getAllOsiInvOrgsById;
          service.getAllActiveUOMs = getAllActiveUOMs;
          service.getAllActiveCurrency = getAllActiveCurrency;
          service.getAllBuyers = getAllBuyers;
          service.getAllInventoryOrg = getAllInventoryOrg;
          service.getConfigParamByName = getConfigParamByName;
          service.getAllDepartment = getAllDepartment;
          service.getUserSpecficDepartments=getUserSpecficDepartments;
          service.deleteLineAttachment=deleteLineAttachment;
          service.getDBCompatibleDate = getDBCompatibleDate;
          service.getDateInPhillipinesFormat = getDateInPhillipinesFormat;
          service.getServerDateWithTimeStamp = getServerDateWithTimeStamp;
          service.checkDate = checkDate;
          service.isDateLessThanTodaysDate=isDateLessThanTodaysDate;
          service.getServerDateTime = getServerDateTime;
          service.getItemsByOperatingUnit = getItemsByOperatingUnit;
          service.getLegalEntities = getLegalEntities;
          service.getOperatingUnitsByLe = getOperatingUnitsByLe;
          service.getSpecificCategoryBySearch = getSpecificCategoryBySearch;
          service.getUserSpecficDepartments1 = getUserSpecficDepartments1;
          return service;
        
        function isDateLessThanTodaysDate(enteredDate){
            var enteredMs=new Date(enteredDate).getTime();
            var todaysMs=new Date(getDateInPhillipinesFormat(new Date().toISOString().substring(0, 10))).getTime();
            if(enteredMs<todaysMs)
                return true;
            else
                return false;
        };
        
        function getDBCompatibleDate(date){
        	var convertedDate = date ? $filter('date')(new Date(date), 'yyyy-MM-dd') : null; 
        	return convertedDate;
        };
        
        function getDateInPhillipinesFormat(serverDate) {
        	var convertedDate = serverDate ? $filter('date')(serverDate, "dd-MMM-yyyy") : null; 
    		return convertedDate;
    	}
    	
    	function getServerDateWithTimeStamp(clientDate) {
    		var convertedDate = clientDate ? new Date(clientDate).setHours(23, 59, 59) : null;
    		return convertedDate;
    	}
    	
    	function checkDate(startDate, endDate){
    		if(new Date(startDate).getTime() >= new Date(endDate).getTime())
    			return true;
    		else 
    			return false;
    	}
        
       function Login(transactionid, trxtype) {
            return $http.get('/POS/common/searchreturn/' + transactionid + '/trxType/' + trxtype).then(handleSuccess, handleError('Error getting transction'));
        } 
       
       function getRoleSpecificItems(invOrgId, trxType) { 
           return $http.get(configData.url + 'get-user-Approval-specific-items/'+invOrgId+'/'+trxType, config).then(handleSuccess, handleError('Error getting Get Items'));
       }
       
       function getRoleSpecificItemsByDesc(invOrgId, trxType, searchString) { 
           return $http.get(configData.url + 'get-user-Approval-specific-items/'+invOrgId+'/'+trxType+'/'+searchString, config).then(handleSuccess, handleError('Error getting Get Items'));
       }
       
       function getItemById(invOrgId, trxType, itemId) { 
           return $http.get(configData.url + 'getCompleteItemData/'+invOrgId+'/'+trxType+'/'+itemId, config).then(handleSuccess, handleError('Error getting Item Details'));
       }

       function getItemsByOperatingUnit(operatingUnits) { 
           return $http.post(configData.url + 'get-items-for-operating-units', operatingUnits, config).then(handleSuccess, handleError('Error getting Get Items'));
       }
       
       function getOperatingUnitsByLe(){
            return $http.get(configData.url + 'all-operating-units/', config).then(handleSuccess, handleError('Error getting Supplier'));
       }
       function getLegalEntities(){
            return $http.get(configData.url + 'legal-entities-by-user', config).then(handleSuccess, handleError('Error getting Supplier'));
       }
       function getAllCategories() { 
           return $http.get(configData.url + 'get-all-active-user-categories', config).then(handleSuccess, handleError('Error getting Categories'));
       }
       
       function getAllNonApprovalCategories(trxType) { 
    	   return $http.get(configData.url + 'get-all-active-user-nonapproval-categories/'+trxType, config).then(handleSuccess, handleError('Error getting Specific Categories'));
       } 

       function getAllOperatingUnits() {
           return $http.get(configData.url + 'user-specific-operating-units', config).then(handleSuccess, handleError('Error getting OperatingUnits'));
       }
       
       /*function getAllSuppliers(supplierDTO) { 
           return $http.post(configData.url + 'all-suppliers', supplierDTO, config).then(handleSuccess, handleError('Error getting Suppliers'));
       }*/
       
       function getAllSuppliers(supplierDTO) { 
           return $http.post(configData.url + 'get-suppliers-by-name', supplierDTO, config).then(handleSuccess, handleError('Error getting Suppliers'));
       }
       
       function getSupplierById(supplierId, siteId) { 
           return $http.get(configData.url + 'suppliers/'+supplierId+'/'+siteId, config).then(handleSuccess, handleError('Error getting Supplier'));
       }
       
       function getHeadertype() { 
           return $http.get(configData.url + 'header-type', config).then(handleSuccess, handleError('Error getting Headertype'));
       }
       
       function getLinetype() { 
           return $http.get(configData.url + 'line-type', config).then(handleSuccess, handleError('Error getting Linetype'));
       }
       
       function getDeliveryLocation(id) {
           return $http.get(configData.url + 'user-specific-inventories/'+id, config).then(handleSuccess, handleError('Error getting DeliveryLocation'));
       }
       
       function getAllActiveUOMs() { 
           return $http.get(configData.url + 'get-all-active-uoms', config).then(handleSuccess, handleError('Error getting ActiveUOMs'));
       }
       
       function getAllActiveCurrency() { 
           return $http.get(configData.url + 'currency', config).then(handleSuccess, handleError('Error getting ActiveCurrency'));
       }
       
       function getAllBuyers() {
           return $http.get(configData.url+'activeUsers', config).then(handleSuccess, handleError('Error getting osiUser List'));
       }
       
       function getAllInventoryOrg() {
           return $http.get(configData.url + 'user-specific-inventory-org', config).then(handleSuccess, handleError('Error getting Inventory Org'));
       }
       
       function getConfigParamByName(configDTO){
       	return $http.post(configData.url + 'getConfigParametersByName', configDTO, config).then(handleSuccess, handleError('Error in getting Config Parameter'));
       }
       
       function getAllDepartment() {
           return $http.get(configData.url + 'get-user-department', config).then(handleSuccess, handleError('Error getting departments of user'));
       }

       function getUserSpecficDepartments(userDTO) { 
           return $http.post(configData.url + 'getUserDepartments', userDTO, config).then(handleSuccess, handleError('Error getting departments'));
       }
       
        function getUserSpecficDepartments1() { 
           return $http.get(configData.url + 'get-departments-by-user', config).then(handleSuccess, handleError('Error getting departments'));
       }
       
       function deleteLineAttachment(data){
           return $http.post(configData.url+'deletePRorPOLineAttachments',data,config).then(handleSuccess, handleError('Error deleting attachment line data'));
       };
        
        function getServerDateTime(){
            return $http.get(configData.url + 'get-server-date-time', config).then(handleSuccess, handleError('Error getting server datetime'));
        }
        
        function getSpecificCategoryBySearch(trxType, searchString) { 
            return $http.get(configData.url + 'get-all-active-user-nonapproval-categories/'+trxType+'/'+searchString, config).then(handleSuccess, handleError('Error getting Get Items'));
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