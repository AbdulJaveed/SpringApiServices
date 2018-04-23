'use strict';

angular.module('myApp.categoryController', [])
        .controller('categoryController',
    ['$scope', '$rootScope', '$window', '$location', '$http', '$sessionStorage','$routeParams',
        '$localStorage','menuEntryService','categoryService','FlashService','$timeout','appConstants',
    function ($scope, $rootScope, $window, $location, $http, $sessionStorage,$routeParams,$localStorage,menuEntryService,categoryService,FlashService,$timeout,appConstants) 
    {
      
    	var vm = this;
        vm.types=[{type:"Function"},{type:"Menu"}];
        
    	vm.category=[];
        vm.collumnname=[];
        vm.squences=[];
    	vm.categoryObj={};
        vm.subMenuOptions=[];
        $scope.successTextAlert = "";
        $scope.showSuccessAlert = false;
        $scope.failureTextAlert = "";
        $scope.showFailureAlert = false;
        var duplicateMenuentriesFlag =0;
        vm.colSrcType=["Text","LOV","Date"];
        vm.category = {
                orgId: '',
                catName:'',
                tblName:'',
                active:true,
    			osiCategoryFields:[{colName: '', colValue: '', colType: '',colSrcType:'',colSrc:'', nullable:'0',seq:'',srcpValid:''}]
    	};
    	//vm.columns = [{field:'Attribute1'},{field:'Attribute2'},{field:'Attribute3'},{field:'Attribute4'},{field:'Attribute5'}]
    	
        vm.resetcategory=function()
        {
            vm.category = {
                orgId: '',
                catName:'',
                tblName:'',
                active:true,
    			osiCategoryFields:[{colName: '', colValue: '', colType: '',colSrcType:'',colSrc:'', nullable:'0',seq:'',srcpValid:''}]
    	};
    	
        }
        
        vm.initController=function(){
            // console.log("menuEntriesController INIT CONTROLLER..");
    		vm.resetcategory();
        
            categoryService.getAllorganizations().then(function (data) {
                vm.organizations = data;
               
    });
    categoryService.getAllfunctions().then(function (data) {
        vm.functions = data;
       
});
    categoryService.getAlltableNames().then(function (data) {
        vm.tables = data.tblName;
       
});
categoryService.getCategoriesInitially().then(function (data) {
    angular.forEach(data,function(value) {	
        if(value.active == 1){
            value.status="Active";
        }else{
            value.status="Inactive";
        }
        if(value.orgId == 2){
           value.orgName="OSI";
       }else{
           value.orgName="OSI";
       }
       if(value.osiCategoryFields.length==0)
       {
           value.osiCategoryFields.push(vm.category.osiCategoryFields);
       }
    });
   
   vm.entryList=data;
   
   
   //$rootScope.allMenuEntries= angular.copy(data);
   
}).catch(function(error){
    $rootScope.isTrascError = true;
    var msg = appConstants.exceptionMessage;
    if(error.data.httpStatus){ 
        msg=error.data.errorMessage; 
    }
    FlashService.Error(msg);
    $timeout(function () {
        $rootScope.isTrascError=false;
    }, 2000);
   
   
   /*$scope.failureTextAlert =msg;
   $scope.showFailureAlert = true;
   $timeout(function () {
          $scope.showFailureAlert = false;
   }, 5000);*/
});    
          
        }
        
        
        vm.initController();


        vm.removeHeaderMenu=function(headerMenu){
            headerMenu=angular.fromJson(headerMenu);
            vm.iterator=vm.menus ;
            vm.subMenuOptions=[];
              angular.forEach(vm.iterator, function (value, key) 
                {
                    if(value.id!=headerMenu.id){
                        vm.subMenuOptions.push(value);
                    }
                });
        };
        vm.getColumnNamesByTableName = function(tablename)
        {
           
            var tableObj = {};
            tableObj.tableName=tablename;
            categoryService.getAllcolumnsByTableName(tableObj).then(function (data) {
             
                vm.columns = data;
               
    });
        };
      
        $timeout(function() {
            $scope.availOperations = $localStorage.availOperations;
        }, 400);
    	vm.addColumn=function(subMenu){
            
                
                    if(subMenu.colName!='' && subMenu.colValue!='' &&   subMenu.colType!='' && subMenu.colSrcType!='' && subMenu.nullable!=''&& subMenu.seq!='' )
                    {
                       // vm.collumnname.push(subMenu.colName);
                            vm.category.osiCategoryFields.push({colName: '', colValue: '', colType: '',colSrcType:'',colSrc:'', nullable:'0',seq:'0',srcpValid:''});
                    }
                    else
                    {
                        if(subMenu.seq=='' || subMenu.seq==0){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Sequence should be greater than zero");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        else if(subMenu.colName=='' || subMenu.colName==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please select Column Name");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        if(subMenu.colValue=='' || subMenu.colValue==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please Enter Column Value");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        if(subMenu.colType=='' || subMenu.colType==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please select Column Type");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        if(subMenu.colSrcType=='' || subMenu.colSrcType==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please select Column Source Type");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                       
                      
                        else if(subMenu.nullable==undefined || subMenu.nullable==""){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Select Nullable");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                       
                    }                           
                
           
    		
    	};
        
        vm.removeColumn = function (submenu)
        {
            if ( vm.category.osiCategoryFields.length > 1)
            {
                vm.category.osiCategoryFields.splice( vm.category.osiCategoryFields.indexOf(submenu), 1);
            } else
            {
                vm.category.osiCategoryFields = [{colName: '', colValue: '', colType: '',colSrcType:'',colSrc:'', nullable:'',seq:'',srcpValid:''}];
            }
        };
    	
    	vm.deleteEntry=function(entry){
            var id=entry.id;
            menuEntriesService.delete({id});
            vm.menuEntries.splice(vm.menuEntries.indexOf(entry),1);
        };
        
        
    	vm.save=function(entries)
        {
            var shouldSave=true;
            duplicateMenuentriesFlag =0;
            categoryService.getCategoriesInitially().then(function (data) {
                if (entries.orgId != ''  )
                {
                    if(entries.catName !='')
                    {
                       
                            angular.forEach(data,function(value) {	
                          if(value.catName.toUpperCase()==entries.catName.toUpperCase() && value.id!=entries.id && value.orgId==entries.orgId)
                          {
                          
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Function with name "+value.catName+" is alreday exist");
                            $timeout(function () {
                                $rootScope.isTrascError = false;
                             }, 5000);
                             shouldSave=false;
                          }
                        });
                
                        if(entries.tblName !='')
                         {
                         if(entries.inlineOrPopup !== undefined) {
			                    vm.entryArray = [];
			                   
			                    var colName=false;
			                    var colValue=false;
			                    var colType=false;
			                    var colSrcType=false;
			                    var colSrc=false;
			                    var nullable=false;
			                    var srcpValid=false
			                    var seqMissing=false;
			                    vm.categoryObj = {};
			                    vm.squences=[];
			                    vm.collumnname=[];
			                    vm.categoryObj.catName = entries.catName;
			                    vm.categoryObj.tblName = entries.tblName;
			                    vm.categoryObj.orgId = entries.orgId;
			                    vm.categoryObj.inlineOrPopup = entries.inlineOrPopup;
			                    
			                    if(entries.active)
			                    {
			                        vm.categoryObj.active = 1;
			                    }
			                   else
			                   {
			                    vm.categoryObj.active=0; 
			                   }
			                    vm.categoryObj.osiCategoryFields=[];
			                    
			                    angular.forEach(entries.osiCategoryFields, function (value, key) 
			                    {
			                        if((value.colName=="") || (value.colValue=="") || (value.colType=="")|| (value.colSrcType== "")||(value.seq==0) || (value.nullable== "") ||  (value.seq==""))
			                        {
			                            if((value.colName=="") )
			                            colName=true;
			                            if(value.colValue=="")
			                            colValue=true;
			                            if((value.colType=="") )
			                            colType=true;
			                            if(value.colSrcType=="")
			                            colSrcType=true;
			                            if(value.seq==0 || value.seq=="")
			                            seqMissing=true;
			                            if(value.nullable=="")
			                            nullable=true;
			                            
			                            shouldSave=false;
			                        }
			                        else{
			                            vm.collumnname.push(value.colName);
			                            vm.squences.push(value.seq);
			                           
			                            var column=[];
			                            column=value;
			                            vm.categoryObj.osiCategoryFields.push(column);
			                        }
			                       
			    
			                    });//forEach
			    
			    if(vm.squences.length>0)
			    {
			        var sorted_arr = vm.squences.slice().sort(); 
			    var results = [];
			    for (var i = 0; i < sorted_arr.length - 1; i++) {
			    if (sorted_arr[i + 1] == sorted_arr[i]) {
			    results.push(sorted_arr[i]);
			    
			    if(results.length>0)
			    {
			        $rootScope.isTrascError = true;    
			        FlashService.Error("Sequence should be unique.");
			        $timeout(function () {
			            $rootScope.isTrascError = false;
			         }, 5000);
			         shouldSave=false;
			    }
			    }
			    }
			    }
			    if(vm.collumnname.length>0)
			    {
			        var sorted_arr = vm.collumnname.slice().sort(); 
			    var results = [];
			    for (var i = 0; i < sorted_arr.length - 1; i++) {
			    if (sorted_arr[i + 1] == sorted_arr[i]) {
			    results.push(sorted_arr[i]);
			    
			    if(results.length>0)
			    {
			        $rootScope.isTrascError = true;    
			        FlashService.Error("Column names should be unique.");
			        $timeout(function () {
			            $rootScope.isTrascError = false;
			         }, 5000);
			         shouldSave=false;
			    }
			    }
			    }
			    }
			                    if(shouldSave){
			                      
			                        console.log(JSON.stringify( vm.categoryObj))
			      // console.log("Ready to save :"+JSON.stringify(vm.entryArray));
			                                
			      categoryService.saveCategory(vm.categoryObj).then(function (data) {
			         
			          $('#category_modal').modal('hide');
			          vm.resetcategory();
			          $scope.sendEvent();
			          $scope.$emit('saveChanges');
			          
			        $timeout(function () {
			            $scope.showSuccessAlert = false;
			            $scope.showFailureAlert = false;
			        }, 5000);
			    }).catch(function(error){
			        $rootScope.isTrascError = true;
                    var msg = appConstants.exceptionMessage;
                    if(error.data.httpStatus){ 
                        msg=error.data.errorMessage; 
                    }
                    FlashService.Error(msg);
                    $timeout(function () {
                        $rootScope.isTrascError=false;
                    }, 2000);
			        /*var msg=appConstants.exceptionMessage;
			        $rootScope.isTrascError = true;    
			        FlashService.Error(msg);
			        $timeout(function () {
			                $rootScope.isTrascError= false;
			        }, 3000);*/
			    });
			                    }            
			                    else{
			                        if(seqMissing){
			                            $rootScope.isTrascError = true;    
			                            FlashService.Error("Please enter valid Sequence.");
			                            $timeout(function () {
			                                $rootScope.isTrascError = false;
			                             }, 5000);
			                        }else if(colName){
			                            $rootScope.isTrascError = true;    
			                            FlashService.Error("Please select Column Name");
			                            $timeout(function () {
			                                $rootScope.isTrascError = false;
			                             }, 5000);
			                        }else if(colValue){
			                            $rootScope.isTrascError = true;    
			                            FlashService.Error("Please enter Column Value");
			                            $timeout(function () {
			                                $rootScope.isTrascError = false;
			                             }, 5000);
			                        }
			                        else if(colType){
			                            $rootScope.isTrascError = true;    
			                            FlashService.Error("Please select column type");
			                            $timeout(function () {
			                                $rootScope.isTrascError = false;
			                             }, 5000);
			                        }
			                        else if(colSrcType){
			                            $rootScope.isTrascError = true;    
			                            FlashService.Error("Please select column source type");
			                            $timeout(function () {
			                                $rootScope.isTrascError = false;
			                             }, 5000);
			                        }
			                        else if(nullable){
			                            $rootScope.isTrascError = true;    
			                            FlashService.Error("Please seelct is mandatory");
			                            $timeout(function () {
			                                $rootScope.isTrascError = false;
			                             }, 5000);
			                        }
			                        
			                    }
                }
            else{
            	$rootScope.isTrascError = true;    
                FlashService.Error("Please select display type. ");
                $timeout(function () {
                         $rootScope.isTrascError = false;
                 }, 5000);
            }
                    }
        else
        {
            $rootScope.isTrascError = true;    
            FlashService.Error("Please enter Table Name.");
            $timeout(function () {
                     $rootScope.isTrascError = false;
             }, 5000);
        }
    }
                else
                {
                    $rootScope.isTrascError = true;    
                    FlashService.Error("Please enter Function Name.");
                    $timeout(function () {
                             $rootScope.isTrascError = false;
                     }, 5000);
                }
                } 
                else {
                    $rootScope.isTrascError = true;    
                    FlashService.Error("Please select Organization.");
                    $timeout(function () {
                             $rootScope.isTrascError = false;
                     }, 5000);
                    
                }
                $timeout(function () {
                    $rootScope.isTrascError = false;
              }, 5000); 
            });
            
        }//Save
        
        vm.closeModal=function(){
            vm.resetMenuEntries();
             $('#category_modal').modal('hide');
        }
        
        vm.checkDuplicateSeq=function(seqNo,type,idx){
        
    		var tempSubmenus = angular.copy(vm.menuEntries.subMenus);
    		tempSubmenus.splice(idx, 1);
    		
    		angular.forEach(tempSubmenus, function (subMenu){
    			if(subMenu.selectedType === type) {
    				if(subMenu.seq === seqNo) {
    					vm.menuEntries.subMenus[idx].seq = 0;
    					$rootScope.isTrascError = true;    
                        FlashService.Error("Duplicate Sequence.");
                        $timeout(function () {
                            $rootScope.isTrascError = false;
                         }, 5000);
    				}
    			}
    		});
        		
        }
        
        vm.typeChanged = function(index) {
        	vm.menuEntries.subMenus[index].seq = 0;
        	vm.menuEntries.subMenus[index].osiMenusBySubMenuId = null;
        	vm.menuEntries.subMenus[index].osiFunctions = null;
        	vm.menuEntries.subMenus[index].menuPrompt = null;
        };
    
        $scope.sendEvent = function() {
		      $scope.$emit('reloadMenuEntryList');
        };
        vm.changesourcetype = function(type)
        {
            if(type=="Decimal" || type=="Varchar")
            {
                vm.colSrcType=["Text","LOV"];   
            }
            else if(type=="Date")
            {
                vm.colSrcType=["Date"];   
            }
            else
            {
                vm.colSrcType=["Text","LOV","Date"];                
            }
        }
        vm.removeselectedcol = function(submenu,index)
        {
          
           vm.collumnname.splice(index,0,submenu.colName);
           vm.collumnname.length=index+1;
          
            if(vm.collumnname.length>0)
            {
                var sorted_arr = vm.collumnname.slice().sort(); 
            var results = [];
        
            for (var i = 0; i < sorted_arr.length - 1; i++) {
             
               
            if (sorted_arr[i + 1] == sorted_arr[i]) {
              
            results.push(sorted_arr[i]);
            
            if(results.length>0)
            {
                submenu.columnName="";
                $rootScope.isTrascError = true;    
                FlashService.Error("Column names should be unique.");
                $timeout(function () {
                    $rootScope.isTrascError = false;
                 }, 5000);
                
            }
            }
            }
            }
           
        }
         vm.removeselectedcol = function(submenu,index)
        {
          
           vm.collumnname.splice(index,0,submenu.colName);
           vm.collumnname.length=index+1;
           
            if(vm.collumnname.length>0)
            {
                var sorted_arr = vm.collumnname.slice().sort(); 
            var results = [];
           
            for (var i = 0; i < sorted_arr.length - 1; i++) {
             
               
            if (sorted_arr[i + 1] == sorted_arr[i]) {
              
            results.push(sorted_arr[i]);
            
            if(results.length>0)
            {
               
                $rootScope.isTrascError = true;    
                FlashService.Error("Column names "+submenu.colName+" is already selected");
                $timeout(function () {
                    $rootScope.isTrascError = false;
                 }, 5000);
                submenu.columnName=""; 
            }
            }
            }
            }
           
        }
        vm.removeselectedseq = function(submenu,index)
        {
         
        
           vm.squences.splice(index,0,submenu.seq);
           vm.squences.length=index+1;
          
            if(vm.squences.length>0)
            {
                var sorted_arr = vm.squences.slice().sort(); 
            var results = [];
            for (var i = 0; i < sorted_arr.length - 1; i++) {
            if (sorted_arr[i + 1] == sorted_arr[i]) {
            results.push(sorted_arr[i]);
            
            if(results.length>0)
            {
                $rootScope.isTrascError = true;    
                FlashService.Error("Sequence number should be unique.");
                $timeout(function () {
                    $rootScope.isTrascError = false;
                 }, 5000);
                 submenu.seq=0;
            }
            }
            }
            }
           
        }
        
    }]);//();






