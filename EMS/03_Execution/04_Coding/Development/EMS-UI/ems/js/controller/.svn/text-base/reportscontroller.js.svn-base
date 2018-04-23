'use strict';
angular.module('myApp.ReportsController', []).controller('ReportsController',
        ReportsController);
ReportsController.$inject = ['$scope', '$rootScope', '$window', '$location',
    '$http', '$localStorage', 'ReportsService', 'FlashService', '$timeout','$filter'];
function ReportsController($scope, $rootScope, $window, $location, $http,
        $localStorage, ReportsService, FlashService, $timeout,$filter) {
    var vm = this;
    vm.today=new Date().toDateString();
    $scope.reportsList = "";
    //$scope.requestDate = $filter('date')(new Date(), "yyyy-MM-dd");
    
    //var childArgId="";
    

    $rootScope.go = function (path) {
         $("#success-modal").modal("hide");
        $location.url(path);
    }
    
    $scope.init = function () {
        console.log("$localStorage.rptGrpId from reports controller - "+$localStorage.rptGrpId);
        var groupId = $localStorage.rptGrpId;//1;//
        ReportsService.getReportsList(groupId).then(function(data){
            $scope.reportsList = data;
            //console.log("reportsList "+JSON.stringify($scope.reportsList));
        });
        };
        
       $scope.backToHistory = function(){
            $("#success-modal").modal("hide");
             $timeout(function () {
         $location.url("/reportshistory");  
    }, 400);
           
       }; 
//        $scope.reportsList=[
//  {
//    "reportId": 1,
//    "reportName": null,
//    "userReportName": "Active Users",
//    "srcFileName": null,
//    "createdBy": null,
//    "creationDate": null,
//    "lastUpdatedBy": null,
//    "lastUpdateDate": null,
//    "reportType": null,
//    "outputType": null,
//    "selectedValues": null,
//    "parentId": null,
//    "businessGroupId": null
//  },
//  {
//    "reportId": 2,
//    "reportName": null,
//    "userReportName": "Test Report",
//    "srcFileName": null,
//    "createdBy": null,
//    "creationDate": null,
//    "lastUpdatedBy": null,
//    "lastUpdateDate": null,
//    "reportType": null,
//    "outputType": null,
//    "selectedValues": null,
//    "parentId": null,
//    "businessGroupId": null
//  }
//];
    
    
//    $scope.getFields = function(){
//        console.log("reportId"+$scope.reportId);
//        if($scope.reportId!==null && $scope.reportId!=="" && $scope.reportId!==undefined){
//            $("#reportArgs > tbody").empty();
//	var tdIds="";
//	childArgId = "";
//           var data="<tr><td width='44%' id='USER_ID'><label>User Name<span class='mandatory'>*</span></label></td><td id='tdIdUSER_ID'><select  checkRequired='required'  class='selectOptions' multiple size='2'  name='Value_USER_ID' id='Id_USER_ID' onchange='getChildList(this);'><option value='-1' selected='selected'>Select</option><option value='1'>smadmin</option><option value='2'>plachi</option><option value='3'>tbhargav</option><option value='4'>kmanoj</option></td></tr><tr><td width='44%' id='OPERATING_ID'><label>Operating Unit<span class='mandatory'>*</span></label></td><td id='tdIdOPERATING_ID'><select  checkRequired='required'  class='selectOptions' multiple size='2'  name='Value_OPERATING_ID' id='Id_OPERATING_ID' onchange='getChildList(this);'><option value='-1' selected='selected'>Select</option></td></tr><tr><td width='44%' id='RESP_ID'><label>Responsibility<span class='mandatory'>*</span></label></td><td id='tdIdRESP_ID'><select  checkRequired='required'  class='selectOptions' multiple size='2'  name='Value_RESP_ID' id='Id_RESP_ID' onchange='getChildList(this);'><option value='-1' selected='selected'>Select</option><option value='1'>Inventory</option><option value='2'>Configuration</option><option value='3'>Administration</option><option value='4'>My Tasks</option></td></tr><tr><td width='44%' id='MENU_ID'><label>Menu<span class='mandatory'>*</span></label></td><td id='tdIdMENU_ID'><select  checkRequired='required'  class='selectOptions' multiple size='2'  name='Value_MENU_ID' id='Id_MENU_ID' onchange='getChildList(this);'><option value='-1' selected='selected'>Select</option><option value='1'>Administration</option><option value='2'>Configuration</option><option value='3'>Inventory</option><option value='4'>My Tasks</option></td></tr><tr><td width='44%' id='USER_ACTIVE_ORDER_BY'><label>Order By<span class='mandatory'>*</span></label></td><td id='tdIdUSER_ACTIVE_ORDER_BY'><select  checkRequired='required'  class='selectOptions' multiple size='2'  name='Value_USER_ACTIVE_ORDER_BY' id='Id_USER_ACTIVE_ORDER_BY' ><option value='-1' selected='selected'>Select</option><option value='USER'>USER</option><option value='RESP'>RESP</option><option value='OU'>OU</option></td></tr>";
//           var dateFormat = "";
//			
//			$("#reportArgs tbody").append(data.replace(/&lt;/g,'<').replace(/&gt;/g,'>'));
//			 var table = $("#reportArgs tbody");
//			    table.find('tr').each(function (i) {
//			    	tdIds = $(this).find('td').attr('id');
//			            childArgId = childArgId+tdIds+",";
//			           if(tdIds.indexOf('date_')!=-1){
//			        	   tdIds = tdIds.substring(5,tdIds.length);
//			        	   dateFormat = $("#Id_"+tdIds).attr('dateFormat');
//				            $('#Id_'+tdIds).datepicker({
//						          dateFormat: dateFormat,
//						          onSelect: function (selected, evnt) {
//						        	 getChildList(this);
//						          }
//						      });
//			           }
//			    });
//        }else{
//            document.getElementById("reportArgs").getElementsByTagName("tbody")[0].innerHTML="";
//        }
//    }

    
    $scope.init();
}