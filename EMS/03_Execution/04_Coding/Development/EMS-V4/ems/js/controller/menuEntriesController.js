    'use strict';

angular.module('myApp.menuEntriesController', [])
        .controller('menuEntriesController',
    ['$scope', '$rootScope', '$window', '$location', '$http', '$sessionStorage','$routeParams',
        '$localStorage','menuEntryService','FlashService','$timeout','appConstants',
    function ($scope, $rootScope, $window, $location, $http, $sessionStorage,$routeParams,$localStorage,menuEntryService,FlashService,$timeout,appConstants) 
    {
    	var vm = this;
    	vm.types=[{type:"Function"},{type:"Menu"}];
    	vm.menus=[];
    	vm.functions=[];
    	vm.menuObj={};
        vm.subMenuOptions=[];
        $scope.successTextAlert = "";
        $scope.showSuccessAlert = false;
        $scope.failureTextAlert = "";
        $scope.showFailureAlert = false;
        var duplicateMenuentriesFlag =0;
        vm.menuEntries = {
    			headerMenu: '',
    			subMenus:[{selectedType: '', seq: '', osiMenusBySubMenuId: '',menuPrompt:'',osiFunctions:'', active:true}]
    	};
    	
    	
        vm.resetMenuEntries=function()
        {
            vm.menuEntries = {
                headerMenu: '',
                subMenus: [{selectedType: 'Menu', seq: 0, osiMenusBySubMenuId: '', menuPrompt: '', osiFunctions: '', active:true}]
            };
        }
        
        vm.initController=function(){
            // console.log("menuEntriesController INIT CONTROLLER..");
    		vm.resetMenuEntries();
        
             menuEntryService.getAllMenus().then(function (data) {
                        vm.menus = data;
                        vm.subMenuOptions=vm.menus ;
            });
            
             menuEntryService.getAllFunctions().then(function (data) {
                        vm.functions = data;
                        //console.log("functions :",JSON.stringify(vm.functions));
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
        
    	vm.addMenu=function(subMenu){
            //console.log(JSON.stringify(subMenu));
            //subMenu.osiMenusByMenuId=angular.fromJson(subMenu.osiMenusByMenuId);
                var type=subMenu.selectedType;
                if(type=="Menu" )
                {
                    if(subMenu.selectedType!='' && subMenu.seq!='' &&  ( subMenu.osiMenusBySubMenuId!=undefined && subMenu.osiMenusBySubMenuId!="")  && subMenu.menuPrompt!='')
                    {
                            vm.menuEntries.subMenus.push({ selectedType: '', seq: '', osiMenusBySubMenuId: '',menuPrompt:'',osiFunctions:'', active:true});
                    }
                    else
                    {

                        if(subMenu.selectedType=='' || subMenu.selectedType==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please, select type.");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        else if(subMenu.seq==''){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Sequence should be greater than zero");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        else if(subMenu.osiMenusBySubMenuId==undefined || subMenu.osiMenusBySubMenuId==""){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Select Sub-Menu.");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        else if(subMenu.menuPrompt==''){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Enter valid prompt.");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                    }                           
                }
                else if(type=="Function" )
                {
                    if(subMenu.selectedType!='' && subMenu.seq!='' &&  ( subMenu.osiFunctions!=undefined && subMenu.osiFunctions!="")  && subMenu.menuPrompt!='')
                    {
                            vm.menuEntries.subMenus.push({ selectedType: '', seq: '', osiMenusBySubMenuId: '',menuPrompt:'',osiFunctions:'', active:true});
                    }
                     else
                    {

                        if(subMenu.selectedType=='' || subMenu.selectedType==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please, select type.");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        else if(subMenu.seq==''){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Enter valid sequence.");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        else if(subMenu.osiFunctions==undefined || subMenu.osiFunctions==""){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Select Function.");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        else if(subMenu.menuPrompt==''){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Enter valid prompt.");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                    }
                }
    		
    	};
        
        vm.removeMenu = function (submenu)
        {
            if (vm.menuEntries.subMenus.length > 1)
            {
                vm.menuEntries.subMenus.splice(vm.menuEntries.subMenus.indexOf(submenu), 1);
            } else
            {
                vm.menuEntries.subMenus = [{selectedType: 'Menu', seq: 0, osiMenusBySubMenuId: '', osiFunctions: '', menuPrompt: '', active:true}];
            }
        };
    	
    	vm.deleteEntry=function(entry){
            var id=entry.id;
            menuEntriesService.delete({id});
            vm.menuEntries.splice(vm.menuEntries.indexOf(entry),1);
        };
        
        
    	vm.save=function(entries)
        {
            duplicateMenuentriesFlag =0;
            if (entries.headerMenu != '')
            {
                vm.entryArray = [];
                var shouldSave=true;
                var menuOrFunctionMissing=false;
                var promptMissing=false;
                var seqMissing=false;
                angular.forEach(entries.subMenus, function (value, key) 
                {
                    if((value.osiMenusBySubMenuId=="") && (value.osiFunctions=="") || (value.menuPrompt=="")|| (value.menuPrompt== undefined)||(value.seq==0))
                    {
                        if((value.osiMenusBySubMenuId=="") && (value.osiFunctions==""))
                            menuOrFunctionMissing=true;
                        if(value.menuPrompt=="" || value.menuPrompt== undefined)
                            promptMissing=true;
                        if(value.seq==0)
                            seqMissing=true;
                        
                        shouldSave=false;
                    }
                    else{
                         vm.menuObj = {};
                        vm.menuObj.seq = value.seq;
                        vm.menuObj.prompt = value.menuPrompt;
                        console.log("value.menuPrompt :"+value.menuPrompt);
                        vm.menuObj.osiMenusByMenuId = angular.fromJson(entries.headerMenu);
                    	if(value.active){
                    		vm.menuObj.active=1;
                    	}else{
                    		vm.menuObj.active=0;
                    	}
                        if (value.osiFunctions != '')
                        {
                            vm.menuObj.osiFunctions = angular.fromJson(value.osiFunctions);
                        } else
                        {
                            vm.menuObj.osiMenusBySubMenuId = angular.fromJson(value.osiMenusBySubMenuId);
                        }
                        vm.entryArray.push(vm.menuObj);
                    }
                   

                });//forEach

                if(shouldSave){
                            angular.forEach($rootScope.allMenuEntries,function(data, key) {	
                                angular.forEach(vm.entryArray,function(entryArray,key){
                                    if(duplicateMenuentriesFlag == 0){
                                        if(data.osiMenusByMenuId.menuName==entryArray.osiMenusByMenuId.menuName){

                                            if(data.osiMenusBySubMenuId != null && entryArray.osiMenusBySubMenuId!=undefined){
                                                    if((data.osiMenusBySubMenuId.menuName==entryArray.osiMenusBySubMenuId.menuName)){
                                                        duplicateMenuentriesFlag =1;
                                                    }else{
                                                        duplicateMenuentriesFlag =0;
                                                    }
                                                }else if(data.osiFunctions!=null && entryArray.osiFunctions != undefined){
                                                    if(data.osiFunctions.funcName==entryArray.osiFunctions.funcName){
                                                        duplicateMenuentriesFlag =1;
                                                    }
                                                   else{
                                                       duplicateMenuentriesFlag =0;
                                                   }
                                                }

                                        }else{
                                            duplicateMenuentriesFlag =0;
                                        }
                                    }
                                });
                           });
                           
                           //Checking duplicate combinition
                            var isDuplicateCombination=false;
                            var occuranceCounter=0;
                            angular.forEach(vm.entryArray,function(outer_elem) {
                                occuranceCounter=0;
                                if(outer_elem.osiMenusBySubMenuId!==undefined || outer_elem.osiMenusBySubMenuId!=null){
                                    angular.forEach(vm.entryArray,function(inner_elem) {
                                        if(inner_elem.osiMenusBySubMenuId!==undefined){
                                            if((outer_elem.prompt==inner_elem.prompt)&& (outer_elem.osiMenusByMenuId.id===inner_elem.osiMenusByMenuId.id) &&
                                                    (outer_elem.osiMenusBySubMenuId.id===inner_elem.osiMenusBySubMenuId.id)){
                                                occuranceCounter++;
                                                if(occuranceCounter===2){
                                                    isDuplicateCombination=true;
                                                }
                                            }
                                        }
                                    });
                                }else{
                                    if(outer_elem.osiFunctions!==undefined || outer_elem.osiFunctions!=null){
                                        angular.forEach(vm.entryArray,function(inner_elem) {
                                            if(inner_elem.osiFunctions!==undefined){
                                                if((outer_elem.prompt==inner_elem.prompt)&& (outer_elem.osiMenusByMenuId.id===inner_elem.osiMenusByMenuId.id) &&
                                                        (outer_elem.osiFunctions.id===inner_elem.osiFunctions.id)){
                                                    occuranceCounter++;
                                                    if(occuranceCounter===2){
                                                        isDuplicateCombination=true;
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }
                                
                            });
                            
                    if(isDuplicateCombination){
                        $rootScope.isTrascError = true;    
                        FlashService.Error("Duplicate menu entry combination found.");
                        $timeout(function () {
                            $rootScope.isTrascError = false;
                         }, 3000);
                    }else if(duplicateMenuentriesFlag==1){
                        $rootScope.isTrascError = true;    
                        FlashService.Error("Header-Menu and Menu/Function combination allready exist");
                        $timeout(function () {
                            $rootScope.isTrascError = false;
                         }, 3000);
                    }else{
                           // console.log("Ready to save :"+JSON.stringify(vm.entryArray));
                            
                            menuEntryService.saveMenuEntry(vm.entryArray).then(function (data) {
                            if(data.httpStatus == 200)
                            {
                                $('#menu_Entries_modal').modal('hide');
                                vm.resetMenuEntries();
                                $scope.sendEvent();
                                $scope.$emit('saveChanges');
                                $scope.$emit('MenuEntryList');
                            }else {
                                $scope.failureTextAlert =appConstants.exceptionMessage;
                                $scope.showFailureAlert = true;
                            }
                            $timeout(function () {
                                $scope.showSuccessAlert = false;
                                $scope.showFailureAlert = false;
                            }, 5000);
                        }).catch(function(error){
                            
                            var msg=appConstants.exceptionMessage;
                            $rootScope.isTrascError = true;    
                            FlashService.Error(msg);
                            $timeout(function () {
                                    $rootScope.isTrascError= false;
                            }, 3000);
                        });
                    }
                }            
                else{
                	if(seqMissing){
                        $rootScope.isTrascError = true;    
                        FlashService.Error("Please enter valid Sequence.");
                        $timeout(function () {
                            $rootScope.isTrascError = false;
                         }, 5000);
                    }else if(menuOrFunctionMissing){
                        $rootScope.isTrascError = true;    
                        FlashService.Error("Please select Menu/Function.");
                        $timeout(function () {
                            $rootScope.isTrascError = false;
                         }, 5000);
                    }else if(promptMissing){
                        $rootScope.isTrascError = true;    
                        FlashService.Error("Please enter valid Prompt.");
                        $timeout(function () {
                            $rootScope.isTrascError = false;
                         }, 5000);
                    }
                    
                }
                
            } else {
                $rootScope.isTrascError = true;    
                FlashService.Error("Please select Header Menu.");
                $timeout(function () {
                         $rootScope.isTrascError = false;
                 }, 5000);
                
            }
            $timeout(function () {
           	 $rootScope.isTrascError = false;
          }, 5000);
        }//Save
        
        vm.closeModal=function(){
            vm.resetMenuEntries();
             $('#menu_Entries_modal').modal('hide');
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
        
    }]);//();



