'use strict';

angular.module('orgHierarchyController', [])
        .controller('orgHierarchyController',
    ['$http', '$sessionStorage','$scope', '$rootScope', '$cookies', '$location', '$timeout', '$localStorage', 'orgChartService', 'orgChartUtilityService', 'employeeBasicInfoService', 'LookupService' ,
    function($http, $sessionStorage, $scope,$rootScope,$cookies, $location, $timeout, $localStorage, orgChartService, orgChartUtilityService, employeeBasicInfoService, LookupService )
    {
      if($rootScope.isLogin==true) {
        $sessionStorage.Login =true;  
     }                
 
     if($sessionStorage.Login) {
       employeeBasicInfoService.loadEmployeeBasicInfo();
       
       LookupService.getLookupByLookupName('GENDER').then(function (result) {
       $localStorage.gendersList = result.osiLookupValueses;
   });
   LookupService.getLookupByLookupName('TITLE').then(function (result) {
     $localStorage.titlesList = result.osiLookupValueses;
   });
   LookupService.getLookupByLookupName('EMPLOYEE TYPE').then(function (result) {
     $localStorage.empTypesList = result.osiLookupValueses;
   });
   LookupService.getLookupByLookupName('NATIONALITY').then(function (result) {
     $localStorage.nationalityList = result.osiLookupValueses;
   });
     LookupService.getLookupByLookupName('Maritial_Status').then(function(result) {
       $localStorage.maritialstatusList = result.osiLookupValueses;
     });
   employeeBasicInfoService.getAllOrganizations().then(function (result) {
     $localStorage.osiOrganizations = result;
   });
        $rootScope.isLogin =true;  
     } else {
        $rootScope.isLogin =false;
     }
     console.log("dashboard contoller "+$sessionStorage.Login);
	//$rootScope.absUrl = $location.absUrl().split("#",1)[0];
	//$rootScope.url =  $rootScope.absUrl + "org-hierarchy.html?employeeId="+$localStorage.employeeId; //TODO default to current login
  var employeeId = $rootScope.globals.userDTO.id;
  //orgChartService.loadAllNodes(employeeId);

 $scope.getEmployeeProfile = function(empId){
   $localStorage.employeeId = empId;
   window.location.href = "#/employeeInfo-sf";
 }


  var depthAndCount;

  var margin = {top: 40, right: 120, bottom: 20, left: 120},
      width = 960 - margin.right - margin.left,
      height = 1000 - margin.top - margin.bottom;


  var i = 0,
      duration = 750,
      root;

  var tree = d3.layout.tree()
      .size([height, width]);

  var diagonal = d3.svg.diagonal()
      .projection(function(d) { return [d.y, d.x]; });

  var defaultThemeConfig =
    {
    frame: {
          "font-size": "10px",
          color: "black",
          padding: '1px',
          "background-color": "#fff",
          border: "1px solid #ccc",
          border: "1px solid rgba(0,0,0,.2)",
          "border-radius": "6px",
          "-webkit-box-shadow:": "0 5px 10px rgba(0,0,0,.2)",
          "box-shadow": "0 5px 10px rgba(0,0,0,.2)"
      },
      title: {'text-align': 'left', 'padding': '4px'},
      item_title: {'text-align': 'left', 'color': 'black'},
      item_value: {'padding': '1px 2px 1px 10px', 'color': 'black'}
    };

  $('#employeeProfile').hide();

  d3.kodama.themeRegistry('kodama', defaultThemeConfig);
  //	d3.kodama.theme("kodama")


    var makeTipData = function(d, i){
      var employeeData = d;
      console.log(new Date(d.dateHired));
      var hiredDate = new Date(d.dateHired);
			var dd = hiredDate.getDate();
			var mm = hiredDate.getMonth()+1; //January is 0!

			var yyyy = hiredDate.getFullYear();
			if(dd<10){
			    dd='0'+dd;
			}
			if(mm<10){
			    mm='0'+mm;
			}
			d.dateHired = yyyy+'-'+mm+'-'+dd;

    $scope.employeeInfo="Employee Information <br><br><br>"
        return {
            title: 'Employee Information',//$scope.employeeInfo,//'Employee Information',
            items: [
                    { title: 'Employee Id:', value: d.employeeNumber},
                    { title: 'Employee Name:', value: d.name},
                    { title: 'Employee Department', value: d.deptStructure},
                    { title: 'Employee Position', value: d.position},
                    { title: 'Employee Supervisor', value: d.supervisorName},
                    { title: 'Date of Hired', value: d.dateHired}
                    //{ title: 'Date Hired', value: d.dateHired.replace("12:00:00 AM","")}
            ],
            theme: 'white_wolf',//'kodama',//'white_wolf',
            distance: 10,
            gravity: 'northeast',
           // target: d3.select(".node")//some_dom_node_or_selection,
        //    by: 'top'
        };
    };


    var addToolTipData = function(d, i){
        return {
            title: 'Add new employee',
            theme: 'kodama',//'white_wolf',
            distance: 10,
            gravity: 'northwest',
           // target: d3.select(".node")//some_dom_node_or_selection,
        //    by: 'top'
        };
    };

    var editToolTipData = function(d, i){
        return {
            title: 'Edit employee',
            theme: 'kodama',//'white_wolf',
            distance: 10,
            gravity: 'northeast',
           // target: d3.select(".node")//some_dom_node_or_selection,
        //    by: 'top'
        };
    };


  var div = d3.select("tt")//.append("div")
  //	.attr("class","col-xs-4 col-md-4");
  var svg = div
    .append("svg")
    .attr("id","svg")
      .attr("width", width + margin.right + margin.left)
      .attr("height", height + margin.top + margin.bottom)
  //	    .attr("preserveAspectRatio","none")
    .append("g")
      .attr("id","mainG")
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

  //	populateJson("../js/mbostock/sampleJson/flare.json");
  //	console.log("here");


  $scope.$on("GenerateTree",function(event, args){
     console.log(args.showGrandChildren);

     if(args.isUpdateTreeData){
       console.log("isUpdateTreeData:"+args.isUpdateTreeData)
      //svg.select("#employeeNameText").remove();
       svg.selectAll("*").remove();
     }

     svg.selectAll("*").remove();

     populateJson(args.data,args.showGrandChildren);
  });

  var isViewAddUserProfile = "";
  var isViewEditUserProfile = "";
  $scope.isViewEpmsSheet = false;


  $scope.$on("loadAllNodes",function(){
    orgChartService.loadAllNodes(employeeId);
    //isViewAddUserProfile = ($rootScope["isViewAddUserProfile"] == "true") ? "visible" : "hidden" ;
    //isViewEditUserProfile = ($rootScope["isViewEditUserProfile"] == "true") ? "visible" : "hidden" ;
  })

  $scope.$emit("loadAllNodes",{});

  d3.select(self.frameElement).style("height", "800px");
  function update(source) {

    var depth = depthAndCount[0].value; //deepest node
    var highestNodeCount = depthAndCount[1].value; // highest count of nodes in the tree
    var noOfNodes=tree.nodes(root).reverse().length;

    console.log("depth:"+depth);
    console.log("Height:"+height);
    console.log("highestNodeCount:"+highestNodeCount);

    $('html, body').css({
      'overflow': 'hidden',
      'height': 'auto'
    });

    if(depth>5 || noOfNodes>50){
      var newWidth = width*depth*1/2;
    }else if(depth=5){
      var newWidth = (width*depth*1/2)-400;
    }else {
      var newWidth = width*depth*1/3;;
    }

    if(noOfNodes>=10 && noOfNodes<=50){
      var newHeight = height+(noOfNodes*40);
    }else if(noOfNodes>50){
      var newHeight = 2*height+((noOfNodes/2)*170);
    }
    else if(noOfNodes >=5 && noOfNodes<10){
      if(noOfNodes < 8)
        var newHeight = height/2+((noOfNodes-2)*30);
      else
        var newHeight = height/2+((noOfNodes)*50);
    }else {//if(noOfNodes<=5){
      var newHeight = height/2+(noOfNodes*30);//height changed for image size reduced and height reduced
    }/*else{
      var newHeight = height+(highestNodeCount*200);
    }*/

    d3.selectAll("svg")
    .attr("width", newWidth)
      .attr("height", newHeight);

    //d3.select("#svg").node()
    //var focus = (newHeight/2)-($(window).height()/2);
/*    console.log("window height"+$(window).height());
    console.log("newHeight height"+newHeight);*/
    var focus =(newHeight-$(window).height())/2;// newHeight/2//(newHeight/2)-($(window).height()/2);
    if (noOfNodes>50){
      focus+=600;
    }
    console.log("focus"+focus);
   /* $('html, body').animate({scrollTop: 10 +'px'}, 0);*/
    d3.select(self.frameElement).style("height", newHeight + "px");
  //		$("#nodeId"+source.id).mouseover();
    //console.log($("#svg").scrollTop());
  //		.atr("")
    tree.size([newHeight, newWidth]);
  //		.attr("width", width)
  //	    .attr("height", height);
  //		svg.selectAll('g.node').mouseover();
    // Compute the new tree layout.
    //compute the new height

    var nodes = tree.nodes(root).reverse(),
        links = tree.links(nodes);

    // Normalize for fixed-depth.
    if(noOfNodes<=50){
      nodes.forEach(function(d) { d.y = d.depth * 250; });
    }else {
      nodes.forEach(function(d) { d.y = d.depth * 200; });
    }



    // Update the nodesï¿½
    var node = svg.selectAll("g.node")
        .data(nodes, function(d) { return d.id || (d.id = ++i); });

    // Enter any new nodes at the parent's previous position.
    var nodeEnter = node.enter().append("g")
        .attr("id",function(d) { return "nodeId"+d.id || "nodeId"+(d.id = ++i); })
        .attr("class", "node")
        .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; });


    nodeEnter.append("circle")
        .attr("r", 1e-6)
        .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; })
        .call(modifiedClick())
          //.call(d3.kodama.tooltip().format(makeTipData))

  //  console.log("")
    nodeEnter.append("text")
        .attr("id","employeeNameText")
        .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
        .attr("dy", "2.0em")
  //	      .attr("dy", ".35em")
        .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
        .text(function(d) {return d.name;})
        .style("fill-opacity", 1e-6)
        .call(modifiedClick())
          //.call(d3.kodama.tooltip().format(makeTipData))


    nodeEnter.append("svg:image")
      .attr("id","avatar")
      .attr("xlink:href", function(d){
        if(d.imageLocation == 'femaleAvatar.png' || d.imageLocation == 'maleAvatar.png' ){
        return "/ems/images/"+d.imageLocation;
        }else{
        return d.imageLocation;
        }
      })
  //  	  .attr("xlink:href", "images/avatar.png")
      .attr("width", "50")//height changed for image size reduced and height reduced
      .attr("height", "50")//height changed for image size reduced and height reduced
      .attr("x", function(d) {
        var textElementWidth = d3.select("#employeeNameText").node().getBoundingClientRect().width;
       console.log("textElementWidth:"+textElementWidth);
        //return d.children || d._children ? -120:10;//-100 : 15;
        return d.children || d._children ? -60:10;
       })
      .attr("y", function(){
        var heightOfImage = $(this)[0].getBBox().height
        //return "-7.75em";//"-3.25em";
        return "-3.75em";
       })
       .call(modifiedClick())
       .call(d3.kodama.tooltip().format(makeTipData))

    //console.log($rootScope["isViewAddUserProfile"]);
    //console.log($rootScope["isViewEditUserProfile"])
    //isViewAddUserProfile = ($rootScope["isViewAddUserProfile"] == "true") ? "visible" : "hidden" ;
    //isViewEditUserProfile = ($rootScope["isViewEditUserProfile"] == "true") ? "visible" : "hidden" ;

    // Transition nodes to their new position.
    var nodeUpdate = node.transition()
        .duration(duration)
        .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

    nodeUpdate.select("circle")
        .attr("r", 4.5)
        .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

    nodeUpdate.select("text")
        .style("fill-opacity", 1);

    // Transition exiting nodes to the parent's new position.
    var nodeExit = node.exit().transition()
        .duration(duration)
        .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
        .remove();

    nodeExit.select("circle")
        .attr("r", 1e-6);

    nodeExit.select("text")
        .style("fill-opacity", 1e-6);

    // Update the linksï¿½
    var link = svg.selectAll("path.link")
        .data(links, function(d) { return d.target.id; });

    // Enter any new links at the parent's previous position.
    link.enter().insert("path", "g")
        .attr("class", "link")
        .attr("d", function(d) {
          var o = {x: source.x0, y: source.y0};
          return diagonal({source: o, target: o});
        });

    // Transition links to their new position.
    link.transition()
        .duration(duration)
        .attr("d", diagonal);

    // Transition exiting nodes to the parent's new position.
    link.exit().transition()
        .duration(duration)
        .attr("d", function(d) {
          var o = {x: source.x, y: source.y};
          return diagonal({source: o, target: o});
        })
        .remove();

    // Stash the old positions for transition.
    nodes.forEach(function(d) {
      d.x0 = d.x;
      d.y0 = d.y;
    });
  }

  // Toggle children on click.
  function click(d) {
    if (d.children) {
      d._children = d.children;
      d.children = null;
    } else {
      d.children = d._children;
      d._children = null;
    }
    $scope.$emit("loadSelectedNodes",{nodes:d});

   // update(d);
  }

   // $scope.AddEmployeeFromModal = function(){
   //   $scope.$emit("AddNewEmployeeModal",{employee:$scope.employeeProfile});
   // }
   //
   // $scope.EditEmployeeFromModal = function(){
   //   $scope.$emit("EditEmployeeModal",{employeeId:$scope.employeeProfile.id});
   // }

  function showViewProfile(d) {

    $scope.employeeProfile = d;
    removeNonViewableData(d);
    $scope.employeeProfileData.dateHired = ''+d.dateHired;
    //$scope.employeeProfileData.dateHired = d.dateHired.replace("12:00:00 AM","");
    //$scope.employeeProfileData.grade_name = d.grade_name;
     console.log($scope.employeeProfile);
    var effect = 'slide';
      var options = { direction: 'right'};
      var duration = 500;
      $scope.employeeProfileData.supervisorId = d.supervisorId;
      $scope.employeeProfileData.empId = d.id;
      //var employeeId = $cookies.get('userId');
      $scope.employeeId = employeeId;
      //$scope.getEpmsDetails($scope.employeeProfile.id);
      //$scope.getEmpProjectDetails($scope.employeeProfile.id);

      //$('#employeeProfile').hide(effect, options, duration);
      $('#employeeProfile').show(effect, options, duration);
      $scope.$apply();
  }

  function removeNonViewableData(d){
    /*if(d.id == 2){
      $scope.employeeProfileData = _.omit(d, ['_children', 'children', 'depth', 'parent', 'subordinate', 'id',  'x', 'x0', 'y', 'y0']);
    }else{
      $scope.employeeProfileData = _.omit(d, ['_children', 'children', 'depth', 'level', 'imageLocation', 'parent', 'subordinate', 'supervisorId', 'departmentId', 'id', 'x', 'x0', 'y', 'y0']);
    }*/
    $scope.employeeProfileData = _.omit(d, ['_children', 'children', 'depth', 'level', 'imageLocation', 'parent', 'subordinate', 'supervisorId', 'departmentId', 'id', 'x', 'x0', 'y', 'y0']);
  }

  $scope.camelCaseToWord = function(str) {
    return str.replace(/([A-Z])/g, ' $1').replace(/^./, function(str){ return str.toUpperCase(); })
     }

  $scope.hideViewProfile = function(){
      var effect = 'slide';
      var options = { direction: 'right' };
      var duration = 500;
      $('#employeeProfile').hide(effect, options, duration);
  }

  function modifiedClick(d, i) {
      var event = d3.dispatch('click', 'dblclick');
      function cc(selection) {
          var down,
              tolerance = 5,
              last,
              wait = null;
          // euclidean distance
          function dist(a, b) {
              return Math.sqrt(Math.pow(a[0] - b[0], 2), Math.pow(a[1] - b[1], 2));
          }
          selection.on('mousedown', function(s) {
              down = d3.mouse(document.body);
              last = +new Date();
          });
          selection.on('mouseup', function(d) {
              if (dist(down, d3.mouse(document.body)) > tolerance) {
                  return;
              } else {
                  if (wait) {
                    window.clearTimeout(wait);
                      wait = null;
                      event.dblclick(d3.event);
                  $('#employeeProfile').hide();
                  click(d);
                  } else {
                    wait = window.setTimeout((function(e) {
                          return function() {
                              event.click(e);
                              wait = null;
                              showViewProfile(d);
                              //$scope.$emit("ViewEmployeeModal",{employeeId:d.id});
                          };
                      })(d3.event), 300);
                  }
              }
          });
      };
      return d3.rebind(cc, event, 'on');
  }

  function showToolTip(d){
    orgChartService.generateEmployeeData(d.id);
  }

  $scope.$on("GenerateLatestEmployeeData",function(event, args){
    var employeeData = args.data;
    makeTipData(args.data,0);
  });
  function populateChildren(d) {
      if (d.children) {
        d._children = d.children;
        d.children = null;f
      } else {
        d.children = d._children;
        d._children = null;
      }
      update(d);

  }
  function reSizeTree(noOfNodes){
  var depth = depthAndCount[0].value; //deepest node
  var highestNodeCount = depthAndCount[1].value; // highest count of nodes in the tree

  console.log("depth:"+depth);
  console.log("highestNodeCount:"+highestNodeCount);

  $('html, body').css({
    'overflow': 'hidden',
    'height': '100%'
  });

  var newWidth = width*depth*1/2;
  /*if(highestNodeCount > 1){
    highestNodeCount =((highestNodeCount*3)/4);
  }*/
  //var newHeight = height*highestNodeCount;
  if(noOfNodes>5 && noOfNodes<=16){
    var newHeight = height+(noOfNodes*100);
  }else if(noOfNodes>16){
    var newHeight = 2*height+((noOfNodes/2)*200);
  }
  else {//if(noOfNodes<=5){
    var newHeight = height;
  }/*else{
    var newHeight = height+(highestNodeCount*200);
  }*/

  d3.selectAll("svg")
  .attr("width", newWidth)
    .attr("height", newHeight);

  //d3.select("#svg").node()
  //var focus = (newHeight/2)-($(window).height()/2);
  console.log("window height"+$(window).height());
  console.log("newHeight height"+newHeight);
  var focus =(newHeight-$(window).height())/2;// newHeight/2//(newHeight/2)-($(window).height()/2);
  $('html, body').animate({scrollTop: focus+'px'}, 0);
  d3.select(self.frameElement).style("height", newHeight + "px");

  //	$("#nodeId"+source.id).mouseover();
  //console.log($("#svg").scrollTop());
  //	.atr("")
  tree.size([newHeight, newWidth]);
  }

  function populateJson(jsonValue,showGrandChild){
      root = jsonValue;
      root.x0 = height / 2;
      root.y0 = 0;
      depthAndCount = orgChartUtilityService.getMaxNumberOfChildren(root);
      console.log("depthAndCount:");
      console.log(depthAndCount);
      $scope.childArray=[];
      function collapse(d) {

        if (d.subordinate) {
          d._children = d.subordinate;
          d._children.forEach(collapse);
          d.subordinate = null;
        }
        $scope.childArray.push(d);
      }
      //console.log("rootchild"+root.chilldren)
      if(root.children != undefined){
        console.log("noofChild"+root.children.size);
        root.children.forEach(collapse);
        update(root);
        if(showGrandChild){
          console.log("populateGrandchild")
          angular.forEach($scope.childArray, function(child, key) {
          populateChildren(child);

          });
          console.log("noofnodes"+tree.nodes(root).reverse().length);
          //reSizeTree(tree.nodes(root).reverse().length);
        }
     }

  }

   $scope.$on("loadSelectedNodes", function(events, args){
    orgChartService.loadSelectedNodes(args.nodes);
   });

   // $scope.$on("AddNewEmployeeModal",function(events, args){
   //   var isFromDisplayChart = true;
   //   orgChartService.generateAddNewModal(args.employee,isFromDisplayChart);
   // });
   //
   // $scope.$on("EditEmployeeModal",function(events, args){
   //   orgChartService.generateEditModal(args.employeeId);
   // });
   //
   // $scope.$on("ViewEmployeeModal",function(events, args){
   //   orgChartService.generateViewModal(args.employeeId);
   // });

   // Employee List Page navigation
   $rootScope.orgList = function(){
		$location.path("/employeeChartList");
	};

}]);
