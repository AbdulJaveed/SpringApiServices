/*
    angular.treeview.js
*/
(function(l){l.module("angularTreeview",[]).directive("treeModel",function($compile, $rootScope, $sessionStorage) {
return {
restrict:"A",
link:function(a,g,c) {
    try {
var MainFolderSubMenuId=-1,MainSecondSubMenuId=-1,temp=-1,e=c.treeModel,h=c.nodeLabel||"label",d=c.nodeChildren||"children",
k='<ul><li data-ng-repeat="node in '+e+'"><i class="collapsed" data-ng-show="node.'+d+'.length && node.collapsed" data-ng-click="selectNodeHead(node, $event)"></i><i class="expanded" data-ng-show="node.'+d+'.length && !node.collapsed" data-ng-click="selectNodeHead(node, $event)"></i><i class="normal" data-ng-hide="node.'+
d+'.length"></i> <span data-ng-class="node.selected" data-ng-click="selectNodeLabel(node, $event)">{{node.'+h+'}}</span><div data-ng-hide="node.collapsed" data-tree-model="node.'+d+'" data-node-id='+(c.nodeId||"id")+" data-node-label="+h+" data-node-children="+d+"></div></li></ul>";
e&&e.length&&(c.angularTreeview?(a.$watch(e,function(m,b) {

$rootScope.menuList = $sessionStorage.menuTree;
angular.forEach($rootScope.menuList, function(value, key) {   
	angular.forEach(value, function(value1, key1) {
	   if(key1=='menuId') {
		   value.collapsed=true;
	   }
//	  console.log("value--"+JSON.stringify(value));
	  if(value.children != undefined && value.children.length != 0) {
		  angular.forEach(value.children, function(value2, key2) {
			  value2.collapsed=true;
		  });
	  }	  
    });
});
    
g.empty().html($compile(k)(a))
},!1),a.selectNodeHead=a.selectNodeHead||
function(a,b) {
var menuId="";
var subMenuId=-1;
var childLenght=-1;
var notMainSubMenuId=-1;
var notMainFolderSubMenuId=-1;
var isChild=true;
var tempChild=-1;
var samfun=-1;
	angular.forEach(a, function(value, key) {
		if(a.menuId==0) {
			subMenuId=a.subMenuId;  // this block is for mainfolder click
		// alert("subMenuId "+subMenuId);
			temp=-1;
			MainFolderSubMenuId=subMenuId;  // main folder subMenuId for Global assignment
		} else {
			tempChild=MainFolderSubMenuId;
			notMainSubMenuId=a.subMenuId;
			isChild=false;
			// MainSecondSubMenuId=notMainSubMenuId; //this block is for subfolder click
		}	
    });
	
	angular.forEach($rootScope.menuList, function(value, key) {
		angular.forEach(value, function(value1, key1) {
		   if(key1=='subMenuId') {
			   if(value.menuId==0) {
				   if(subMenuId==value1) {
		         // alert("same folder"); //to skip collapsed for same folder
				   } else if(subMenuId==-1&&notMainSubMenuId!=-1) {
									// to skip collapsed for child folder									
				   } else {
					   value.collapsed=true;
				   }
				   // alert(" key1 "+key1+" value1"+value1);
			   }  
		  }
		  
		  if(key1=='children'&&value1.length!=-1) {
			  if(MainFolderSubMenuId==tempChild) {
			  // alert("MainFolderSubMenuId "+MainFolderSubMenuId);
			  // alert("MainFolderSubMenuId "+MainFolderSubMenuId+" tempChild
				// "+tempChild+"notMainSubMenuId "+notMainSubMenuId+"value
				// subMenuId"+value.subMenuId);
				  angular.forEach(value1, function(value3, key3) {  
					  angular.forEach(value3, function(value4, key4) {
						  // alert(" key "+key4+" value "+value4);		  
						  if(key4=="menuId") {
							  if(MainFolderSubMenuId == value3.menuId && value3.functionId == notMainSubMenuId) {
								  samfun=value3.functionId;
								  // alert(value3.functionId);
							  } else {
								  // alert(value3.functionId);
								  value3.collapsed=true;
							  }
						  }		  
					  });
				  });
			  }
		  }		
	    });
	});
	
b.stopPropagation&&b.stopPropagation();
b.preventDefault&&b.preventDefault();
b.cancelBubble=!0;b.returnValue=!1;
a.collapsed=!a.collapsed
},a.selectNodeLabel=a.selectNodeLabel||
function(c,b) {
b.stopPropagation&&b.stopPropagation();
b.preventDefault&&b.preventDefault();
b.cancelBubble=!0;b.returnValue=!1;
a.currentNode&&a.currentNode.selected&&(a.currentNode.selected=void 0);
c.selected="selected";a.currentNode=c
}):g.html($compile(k)(a)))
    } catch(Error) {
    	console.log("Error occured in tree");    
    }
}
}})})(angular);