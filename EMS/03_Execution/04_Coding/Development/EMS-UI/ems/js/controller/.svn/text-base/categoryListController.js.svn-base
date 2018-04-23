'use strict';

angular.module('myApp.categoryListController', [])
        .controller('categoryListController',
    ['$scope', '$rootScope', '$window', '$location', '$http', '$sessionStorage','$routeParams',
        '$localStorage','menuEntryService','categoryService','FlashService','$timeout', 'appConstants',
    function ($scope, $rootScope, $window, $location, $http, $sessionStorage,$routeParams,$localStorage,menuEntryService,categoryService,FlashService,$timeout, appConstants) 
    {
             
        var vm = this;
        vm.category=[];
        vm.collumnname=[];
        vm.squences=[];
        vm.colSrcType=["Text","LOV","Date"];
        vm.isMandatory=[{id:1,name:"YES"},{id:0,name:"NO"}];
    	vm.categoryObj={}; 
    	vm.types=[{type:"Function"},{type:"Menu"}];
        vm.view=false;
        //vm.selectedType = "M";
        vm.category = {
            orgId: '',
            catName:'',
            tblName:'',
            active:1,
            osiCategoryFields:[{colName: '', colValue: '', colType: '',colSrcType:'',colSrc:'', nullable:'0',seq:'',srcpValid:''}]
    };
         //   vm.columns = [{field:'Attribute1'},{field:'Attribute2'},{field:'Attribute3'},{field:'Attribute4'},{field:'Attribute5'}]
            
        $scope.rowSize=8;
    
        $scope.successTextAlert = "";
	    $scope.showSuccessAlert = false;
	    $scope.failureTextAlert = "";
	    $scope.showFailureAlert = false;
	    vm.isButtionDisableForPrompt = true;
	    vm.isButtionDisableForMenuName = true;
	var selectedRowsIndexes = [];
	var selectedEvent='';
	$scope.rows = [];
	$scope.disable_Edit = true;
	$scope.disable_View = true;
	$scope.disable_Delete = true;
	
        
        
         vm.initController=function(){
             vm.menuEntry={};
             //console.log("INIT CONTROLLER menuEntriesListController..");
          
    categoryService.getAllfunctions().then(function (data) {
        vm.functions = data;
       
});
    categoryService.getAlltableNames().then(function (data) {
        vm.tables = data.tblName;
       
});
    categoryService.getAllorganizations().then(function (organizationData) {
        vm.organizations = organizationData;
       
        /* -- Commented for stop initial loading list
             categoryService.getCategoriesInitially().then(function (data) {
            	 angular.forEach(data,function(value1) {	
             		if(value1.active == 1){
             			value1.status="Active";
             		}else{
             			value1.status="Inactive";
                     }
                     if(value1.osiCategoryFields.length==0)
                     {
                         value1.osiCategoryFields.push(vm.category.osiCategoryFields);
                     }
                     angular.forEach( vm.organizations,function(org) {	
                      if(org.orgId==value1.orgId)
                      {
                       value1.orgName=org.orgName;
                      }
                     });
                   
                 });
                
                vm.entryList=data;
                
             }); 
            */                
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
            });
            
             menuEntryService.getAllMenus().then(function (data) {
                        vm.menus = data;
                        vm.subMenuOptions=vm.menus;
                       // console.log("menus :",JSON.stringify(vm.menus));
            });
            
             menuEntryService.getAllFunctions().then(function (data) {
                        vm.functions = data;
                       // console.log("functions :",JSON.stringify(vm.functions));
            });
            menuEntryService.getAllMenuEntries().then(function(data){
                $rootScope.allMenuEntries = data;
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
            });
        };
        vm.initController();
        
        vm.showMenuEntryModal = function()
        {        vm.view=false;
                   $('#category_modal').modal({backdrop: 'static', keyboard: false});                
        };
        
         $scope.sort = function(keyname) {
            if (vm.entryList != null) {
                // set the sortKey to the param passed
                $scope.sortKey = keyname;
                // if true make it false and vice versa
                $scope.reverse = !$scope.reverse;
            }
        }
            
        $scope.selectRow = function(event, rowIndex) {
		selectedEvent = event;
		if (event.ctrlKey) {
//			changeSelectionStatus(rowIndex);
		} else if (event.shiftKey) {
			// selectWithShift(rowIndex);
		} else {
			selectedRowsIndexes = [ rowIndex ];
			toggleButtons();
		}
	};

	$scope.isRowSelected = function(rowIndex) {
		return selectedRowsIndexes.indexOf(rowIndex) > -1;
	};

	function changeSelectionStatus(rowIndex) {
		if ($scope.isRowSelected(rowIndex)) {
			unselect(rowIndex);
		} else {
			select(rowIndex);
		}
	}

	function unselect(rowIndex) {
		var rowIndexInSelectedRowsList = rowIndex;
		console.log(rowIndexInSelectedRowsList);
		var unselectOnlyOneRow = 1;
		selectedRowsIndexes.splice(rowIndexInSelectedRowsList,
				unselectOnlyOneRow);
	}

	function select(rowIndex) {
		if (!$scope.isRowSelected(rowIndex)) {
			console.log("select");
			selectedRowsIndexes.push(rowIndex)
		}
	}
	 $scope.clearSelectedRow = function() {
	        // clearing row selection
	        $scope.isSelectedRow = null;
	        toggleButtons();
	    }
	 
	function toggleButtons() {
		console.log("selectedRowsIndexes:--" + selectedRowsIndexes.length);
		// edit, view and delete button toggle
		if (selectedRowsIndexes.length == 0) {
			$scope.disable_Edit = true;
			$scope.disable_View = true;
			$scope.disable_Delete = true;
		} else {
			$scope.disable_Edit = false;
			$scope.disable_View = false;
			$scope.disable_Delete = false;
		}
	}

	$scope.editSelectedRowDetails = function() {
		console.log('*****called double click***');
		var ops = $scope.availOperations;
		var found = false;
		for (var i = 0; i < ops.length; i++) {
			if (ops[i].name == 'Edit') {
				found = true;
				break;
			}
		}
		console.log("found:- " + found);
		if (found) {
			vm.showUpdateEntryModal();
			toggleButtons();
		}
	}
//
	function resetSelection() {
		selectedRowsIndexes = [];
	}

	$timeout(function() {
		$scope.availOperations = $localStorage.availOperations;
	}, 400);

	$scope.operationsGenericFunction = function(doFunction, url) {
		$scope.opeationsURL = url;
		if (doFunction === "Create") {
			vm.showMenuEntryModal(selectedEvent);
		}
		if (doFunction === "View" && !$scope.disable_View) {
			vm.showViewEntryModal();
		}
		if (doFunction === "Edit" && !$scope.disable_Edit) {
			vm.showUpdateEntryModal();
		}
		if (doFunction === "Delete" && !$scope.disable_Delete) {
			showDeleteConfirmationPopup();
		}
	} 
          function getSelectedRows() {
		var selectedRows = [];
		angular.forEach(selectedRowsIndexes, function(rowIndex) {
			selectedRows.push(rowIndex);
		});
		return selectedRows;
    }
 
        $scope.clearSelectedRow = function() {
            // clearing row selection
            resetSelection();
            toggleButtons();
    	}
        vm.showViewEntryModal=function(){
          
            categoryService.getAllorganizations().then(function (data) {
                vm.organizations = data;
               
               
    });
            //console.log("showViewEntryModal:",JSON.stringify(entry));
           var selectedRow = getSelectedRows();
		 vm.category = selectedRow[0];
         var tablename= vm.category.tblName;
         var tableObj = {};
         tableObj.tableName=tablename;
         categoryService.getAllcolumnsByTableName(tableObj).then(function (data) {
          
             vm.columns = data;
            
 });
            vm.view=true;//used to disable ui controlls.
           
        
            $('#category_Update_modal').modal({backdrop: 'static', keyboard: false}); 
        }
        
        vm.showUpdateEntryModal=function(){
            
            vm.view=false;
            vm.selectedType = "";
          
            //console.log("Update:",JSON.stringify(entry));
            //vm.menuEntry=entry;
            var selectedRow = getSelectedRows();
            vm.category = selectedRow[0];
            var tablename= vm.category.tblName;
            for(var i=0;i< vm.category.osiCategoryFields.length;i++)
            {
                vm.category.osiCategoryFields[i].remove=false;
            }
                var tableObj = {};
                tableObj.tableName=tablename;
                categoryService.getAllcolumnsByTableName(tableObj).then(function (data) {
                 
                    vm.columns = data;
                   
        });
      
         if(vm.category.active==1)
         {
            vm.category.active=true;    
         }
         else
         {
            vm.category.active=false;    
         }
            categoryService.getAllorganizations().then(function (data) {
                vm.organizations = data;
               
    });
            $('#category_Update_modal').modal({backdrop: 'static', keyboard: false}); 
        }
        vm.addColumn=function(subMenu){
           
                
                    if(subMenu.colName!='' && subMenu.colValue!='' &&   subMenu.colType!='' && subMenu.colSrcType!='' && (subMenu.nullable==0 || subMenu.nullable==1)  && subMenu.seq!='' )
                    {
                        
                            vm.category.osiCategoryFields.push({colName: '', colValue: '', colType: '',colSrcType:'',colSrc:'', nullable:'0',seq:'0',srcpValid:'',remove:true});
                    }
                    else
                    {

if(subMenu.seq=='' || subMenu.seq==0 || subMenu.seq==undefined){
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
                       else if(subMenu.colValue=='' || subMenu.colValue==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please Enter Column Value");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                       else if(subMenu.colType=='' || subMenu.colType==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please select Column Type");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                        else if(subMenu.colSrcType=='' || subMenu.colSrcType==undefined){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please select Column Source Type");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                     
                      
                      else  if(subMenu.nullable!=0 && subMenu.nullable!=1 ){
                            $rootScope.isTrascError = true;    
                            FlashService.Error("Please Select Is Mandatory");
                            $timeout(function () {
                                     $rootScope.isTrascError = false;
                             }, 5000);
                        }
                      
                    }                           
                
           
    		
    	};
    	vm.update=function(entry){
        
            var update=true;
            var object={};
            vm.squences=[];
            vm.collumnname=[];
            var categoriesList=[];
            categoryService.getCategoriesInitially().then(function (data) {
                categoriesList=data;
                if(entry.orgId == '' || entry.orgId==undefined || entry.orgId ==null){
                    $rootScope.isTrascError = true;    
                    FlashService.Error("Please select Organization Name");
                    $timeout(function () {
                        $rootScope.isTrascError= false;
                    }, 3000);
                    update=false;
       }
       else if(entry.catName == '' || entry.catName==undefined || entry.catName ==null){
       
           $rootScope.isTrascError = true;    
           FlashService.Error("Please Enter Funtion Name");
           $timeout(function () {
               $rootScope.isTrascError= false;
           }, 3000);
           update=false;
           
}

else if(entry.tblName == '' || entry.tblName==undefined || entry.tblName ==null){
$rootScope.isTrascError = true;    
FlashService.Error("Please select Table Name");
$timeout(function () {
   $rootScope.isTrascError= false;
}, 3000);
update=false;
}
else if(entry.inlineOrPopup == '' || entry.inlineOrPopup==undefined || entry.inlineOrPopup ==null){
	$rootScope.isTrascError = true;    
	FlashService.Error("Please select display type");
	$timeout(function () {
	   $rootScope.isTrascError= false;
	}, 3000);
	update=false;
	}
else if(entry.catName != '' || entry.catName!=undefined || entry.catName !=null)
{


   angular.forEach(categoriesList,function(value) {	
      
    if(value.catName.toUpperCase()==entry.catName.toUpperCase() && value.id!=entry.id && value.orgId==entry.orgId)
    {

      $rootScope.isTrascError = true;    
      FlashService.Error("Function with name "+value.catName+" is alreday exist");
      $timeout(function () {
          $rootScope.isTrascError = false;
       }, 5000);
       update=false;
    }
  });

}

object.id=entry.id;
object.orgId=entry.orgId;
object.catName=entry.catName;
object.tblName=entry.tblName;
object.inlineOrPopup=entry.inlineOrPopup;

object.createdDate=entry.createdDate;
if(entry.active)
{
    object.active=1;
}
else
{
    object.active=0;  
}
object.osiCategoryFields=[];
var i=0;
angular.forEach(entry.osiCategoryFields, function (value, key) 
{


   
     
        if(value.seq==0 || value.seq=="" || value.seq== undefined)
        {
            $rootScope.isTrascError = true;    
            FlashService.Error("Please Enter Valid Seq");
            $timeout(function () {
                $rootScope.isTrascError= false;
            }, 3000);
            update=false;
        }
        else if(value.colName=="" || value.colName==undefined )
        {
          
            $rootScope.isTrascError = true;    
            FlashService.Error("Please select Column Name");
            $timeout(function () {
                $rootScope.isTrascError= false;
            }, 3000);
            update=false;
        }
  
        else if(value.colValue==""  || value.colValue==undefined)
        {
            $rootScope.isTrascError = true;    
            FlashService.Error("Please Enter Column Value");
            $timeout(function () {
                $rootScope.isTrascError= false;
            }, 3000);
            update=false;
        }
        
        else if(value.colType=="" || value.colType==undefined)
        {
            $rootScope.isTrascError = true;    
            FlashService.Error("Please select Column Type");
            $timeout(function () {
                $rootScope.isTrascError= false;
            }, 3000);
            update=false;
        }
        
        else if(value.colSrcType=="" || value.colType==undefined)
        {
            $rootScope.isTrascError = true;    
            FlashService.Error("Please select Column Source Type");
            $timeout(function () {
                $rootScope.isTrascError= false;
            }, 3000);
            update=false;
        }
       
       
     
    
        else if(value.nullable!=0 && value.nullable!=1)
        {
        
            $rootScope.isTrascError = true;    
            FlashService.Error("Please select Is Mandatory");
            $timeout(function () {
                $rootScope.isTrascError= false;
            }, 3000);
            update=false;
        }
    else{
   
        vm.collumnname.push(value.colName);
        vm.squences.push(value.seq);
       var obj={};
       obj.seq=value.seq;
      obj.colName=value.colName;
      obj.colValue=value.colValue;
      obj.colType=value.colType;
      obj.colSrcType=value.colSrcType;
      obj.colSrc=value.colSrc;
      obj.nullable=value.nullable;
      obj.srcpValid=value.srcpValid;
     
      
        
        
     
        object.osiCategoryFields.push(obj);
     
    }
   

});
   
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
         update=false;
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
         update=false;
    }
    }
    }
    }
    if(update)
    {
    
        categoryService.updateCategory(object).then(function (data) {
            $scope.isTrascError=true;
            $scope.showSuccessAlert=true;
            $scope.successTextAlert = appConstants.successMessage;
            $timeout(function () {
                $scope.showSuccessAlert=false;
                $scope.isTrascError=false;
            }, 2000);
              
                $('#category_Update_modal').modal('hide');
                vm.initController();
              /*  if (data.httpStatus == 200)
                {
                   
                } else {
                       $rootScope.isTrascError = true;    
                       FlashService.Error(data.errorMessage);
                }*/
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
        });
    }
    else{
//                    $rootScope.isTrascError = true;    
//                    FlashService.Error("Please,fill all mandatory fields ");
//                    $timeout(function () {
//                   	 $rootScope.isTrascError= false;
//                    }, 3000);
    }
   
    $scope.clearSelectedRow(); 
            });
        
         
        
         

    	};
        
        
        vm.checkForDuplicateEntry=function(entry){
            var duplicate=false;
            var allMenuEntriesForUpdate = $rootScope.allMenuEntries;
            var index = allMenuEntriesForUpdate.indexOf(_.findWhere($rootScope.allMenuEntries, {id:entry.id}));
            allMenuEntriesForUpdate.splice(index,1);
            //Checking for duplicattion with existing records.
            angular.forEach(allMenuEntriesForUpdate,function(dbEntry){
                    if((dbEntry.osiMenusByMenuId.menuName==entry.osiMenusByMenuId.menuName)){
                        
                            if(dbEntry.osiMenusBySubMenuId != null && entry.osiMenusBySubMenuId!=undefined){
                                console.log("dbEntry.active :"+dbEntry.active+"  entry.active :"+entry.active);
                                    if((dbEntry.osiMenusBySubMenuId.menuName==entry.osiMenusBySubMenuId.menuName)){
                                        duplicate =true;
                                    }
                            }else if(dbEntry.osiFunctions!=null && entry.osiFunctions != undefined){
                                    if((dbEntry.osiFunctions.funcName==entry.osiFunctions.funcName)){
                                        duplicate =true;
                                    }
                            }
                    }
            });
            return duplicate;
        };
        
         function showDeleteConfirmationPopup() {
            $('#menuEntry-delete-model').modal({backdrop: 'static', keyboard: false}); 
        }
        
        vm.deleteEntry=function(){
            //var id=entry.id;
            var selectedRow = getSelectedRows();
		vm.menuEntry = selectedRow[0];
            //vm.deletedEntry=entry;
            
            menuEntryService.deleteMenuEntry(vm.menuEntry).then(function(result){
               // console.log("deleted:",JSON.stringify(result));
//                $('#menuEntry-delete-model').modal('hide');
//            	 $scope.successTextAlert ="Menu Entries Deleted Successfully";
//                 $scope.showSuccessAlert = true;
//                //vm.entryList.splice(vm.entryList.indexOf(vm.deletedEntry),1);
//                vm.initController();
//                $timeout(function () {
//                	$scope.showSuccessAlert = false;
//                }, 3000);
//                
                if (result.httpStatus == 200) {
                    $('#menuEntry-delete-model').modal('hide');
                    $scope.successTextAlert = result.message;
                    $scope.showSuccessAlert = true;
                    vm.initController();
                } else {
                    $('#menuEntry-delete-model').modal('hide');
                    $rootScope.isTrascError = true;
                    FlashService.Error(result.errorMessage);
                    $scope.continuesave = false;
//                   vm.initController();
                }
                $timeout(function() {
                    $scope.showSuccessAlert = false;
 		}, 5000);
                
            });
        }
        $scope.closeModal=function(){
            vm.initController();
             $('#category_Update_modal').modal('hide');
        }
        vm.closeModal=function(){
            vm.initController();
             $('#category_Update_modal').modal('hide');
        }
        
         $rootScope.$on('reloadMenuEntryList', function(event) {
                       // console.log("event caught in list controller");
		    	 vm.initController();
        });
        
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
        
        vm.makeEnableButtionPrompt = function(value){
        	if(value){
        		vm.isButtionDisableForPrompt = false;
        	}else{
        		vm.isButtionDisableForPrompt = true;
        	}
        }
        
        vm.makeEnableButtionForMenuName = function(value){
        	if(value){
        		vm.isButtionDisableForMenuName = false;
        	}else{
        		vm.isButtionDisableForMenuName = true;
        	}
        }
        
        vm.searchEntry=function(entry){
        	if((vm.searchEntryObj.orgId == undefined || vm.searchEntryObj.orgId == "")
                    &&(vm.searchEntryObj.catName == undefined || vm.searchEntryObj.catName == "")
                    && (vm.searchEntryObj.tblName == undefined || vm.searchEntryObj.tblName == "") ){
                //vm.initController();
                
                categoryService.getCategoriesInitially().then(function (data) {
                    angular.forEach(data,function(value) {	
                        if(value.active == 1){
                            value.status="Active";
                        }else{
                            value.status="Inactive";
                        }
                        angular.forEach( vm.organizations,function(org) {	
                            if(org.orgId==value.orgId)
                            {
                             value.orgName=org.orgName;
                            }
                           });
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
               });
            }
            else{
               
                categoryService.searchCategories(entry).then(function (data) {
                    angular.forEach(data,function(value, key) {	
                		if(value.active == 1){
                			value.status="Active";
                		}else{
                			value.status="Inactive";
                        }
                        angular.forEach( vm.organizations,function(org) {	
                            if(org.orgId==value.orgId)
                            {
                             value.orgName=org.orgName;
                            }
                           });
                	});
                    vm.entryList=data;
                });
            }
            
        };
        $rootScope.$on('saveChanges', function() {
            $scope.isTrascError=true;
            $scope.showSuccessAlert=true;
            $scope.successTextAlert = appConstants.successMessage;
            $timeout(function () {
                $scope.showSuccessAlert=false;
                $scope.isTrascError=false;
            }, 2000);
        });
        $scope.clearSearch = function () {
        	vm.searchEntryObj = {};
                $scope.clearSelectedRow();
                vm.isButtionDisableForPrompt = true;
        	    vm.isButtionDisableForMenuName = true;
        	    vm.searchEntryObj=
                {
                    osiMenusByMenuId: {}, seq: '', osiMenusBySubMenuId: {}, prompt: '', osiFunctions: {}
                };
                vm.initController();
        };

        $scope.showDeactivateConfirmationPopup=function() {
        	if(!vm.active){
        		$scope.confirmationHeading="Deactivate"
        	}
        	else{
        		$scope.confirmationHeading="Activate"
        	}
        	$('#menuEntry-delete-model').modal({backdrop: 'static', keyboard: false}); 
          }
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
        vm.noAction = function(){
        	if(vm.active){
            	vm.active=false;
            }else{
                vm.active=true;
            }
        }
        vm.removeselectedcol = function(submenu,index)
        {
            for(var i=0;i<vm.category.osiCategoryFields.length;i++)
            {
                vm.collumnname.push(vm.category.osiCategoryFields[i].colName);
            }
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
                submenu.columnName="-1"; 
            }
            }
            }
            }
           
        }
        vm.removeselectedseq = function(submenu,index)
        {
            for(var i=0;i<vm.category.osiCategoryFields.length;i++)
            {
                vm.squences.push(vm.category.osiCategoryFields[i].seq);
            }
      
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
    }]);