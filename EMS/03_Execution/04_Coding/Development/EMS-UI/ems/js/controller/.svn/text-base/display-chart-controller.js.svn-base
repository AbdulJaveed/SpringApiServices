 var orgChartApp = angular.module('organizationalChartHorizontal');

  //treeview controller
   orgChartApp.controller('displayChartController', /*['$scope'/*,'$http','myService'*///,
   function($scope, orgChartService, orgChartUtilityService, $rootScope, $location,$cookies,$http){
//	   var margin = {top: 20, right: 120, bottom: 20, left: 120};
//	   var margin = {top: 20, right: 120, bottom: 20, left: 120};
	   // var loggedInEmpId = $cookies.get('userId');
	   // var role = $cookies.get('role');
	   // console.log(loggedInEmpId + ':'+ role);


	var depthAndCount;

	var margin = {top: 60, right: 120, bottom: 60, left: 140},

    width = 960 - margin.right - margin.left//450 - margin.right - margin.left,//2000 - margin.right - margin.left;//960 - margin.right - margin.left,
    height = 900 - margin.top - margin.bottom;//1800 - margin.top - margin.bottom;
   //heigh 490

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

		$scope.employeeInfo="Employee Information <br><br><br>"
//		$scope.employeeInfo="Employee Id: "+ employeeData.id+"<br>";
//		$scope.employeeInfo+="Employee Name: "+ employeeData.name+"<br>";
//		$scope.employeeInfo+="Employee Department: "+ employeeData.department+"<br>";
//		$scope.employeeInfo+="Employee Position: "+ employeeData.position+"<br>";
//		$scope.employeeInfo+="Employee Supervisor: "+ employeeData.supervisorName+"<br>";
//		$scope.employeeInfo+="Date Started: "+ employeeData.dateHired.replace("12:00:00 AM","");
        return {
            title: 'Employee Information',//$scope.employeeInfo,//'Employee Information',
            items: [
                    { title: 'Employee Id:', value: d.employeeNumber},
                    { title: 'Employee Name:', value: d.name},
                    { title: 'Employee Department', value: d.department},
                    { title: 'Employee Position', value: d.position},
                    { title: 'Employee Supervisor', value: d.supervisorName},
                    { title: 'Date Hired', value: d.dateHired.replace("12:00:00 AM","")}
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
//            items: [
//                    { title: 'Employee Id:', value: d.id},
//                    { title: 'Employee Name:', value: d.name},
//                    { title: 'Employee Department', value: d.department},
//                    { title: 'Employee Position', value: d.position},
//                    { title: 'Employee Supervisor', value: d.supervisorName},
//                    { title: 'Date Hired', value: d.dateHired.replace("12:00:00 AM","")}
//            ],
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
//            items: [
//                    { title: 'Employee Id:', value: d.id},
//                    { title: 'Employee Name:', value: d.name},
//                    { title: 'Employee Department', value: d.department},
//                    { title: 'Employee Position', value: d.position},
//                    { title: 'Employee Supervisor', value: d.supervisorName},
//                    { title: 'Date Hired', value: d.dateHired.replace("12:00:00 AM","")}
//            ],
            theme: 'kodama',//'white_wolf',
            distance: 10,
            gravity: 'northeast',
           // target: d3.select(".node")//some_dom_node_or_selection,
        //    by: 'top'
        };
    };


	var div = d3.select("body")//.append("div")
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
		orgChartService.loadAllNodes();
		isViewAddUserProfile = ($rootScope["isViewAddUserProfile"] == "true") ? "visible" : "hidden" ;
		isViewEditUserProfile = ($rootScope["isViewEditUserProfile"] == "true") ? "visible" : "hidden" ;
	})

	$scope.$emit("loadAllNodes",{});


	//d3.json("../js/mbostock/sampleJson/flare.json", function(error, flare) {
	//  if (error) throw error;
	//console.log(flare);
	//  root = flare;
	//  root.x0 = height / 2;
	//  root.y0 = 0;
	//
	//  function collapse(d) {
	//    if (d.children) {
	//      d._children = d.children;
	//      d._children.forEach(collapse);
	//      d.children = null;
	//    }
	//  }
	//
	//  root.children.forEach(collapse);
	//  update(root);
	//});




	d3.select(self.frameElement).style("height", "100%");
	function update(source) {

//		var levelWidth = [width];//margin.right;
//		var childCount = function(level, d) {
//
//		  if(d.children && d.children.length > 0) {
//		    if(levelWidth.length <= level + 1) levelWidth.push(0);
//
//		    levelWidth[level+1] += d.children.length;
//		    d.children.forEach(function(d) {
//		      childCount(level + 1, d);
//		    });
//		  }
//		};
//		childCount(0, root);
//		var newHeight = d3.max(levelWidth) * 5.5; // 20 pixels per line
//		console.log("newHeight:"+newHeight);
//		tree = tree.size([newHeight, width]);
		///var childCount = function(level, d) {
		//}
		var depth = depthAndCount[0].value; //deepest node
		var highestNodeCount = depthAndCount[1].value; // highest count of nodes in the tree
		var noOfNodes=tree.nodes(root).reverse().length;

		console.log("depth:"+depth);
		console.log("Height:"+height);
		console.log("highestNodeCount:"+highestNodeCount);

/*		if(noOfNodes<=5){
			$('html, body').css({
				'overflow': 'hidden',
				'height': '100%'
			});
		}else{
			$('html, body').css({
				'overflow': 'auto',
				'height': 'auto'
			});

		}*/

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

		/*if(highestNodeCount > 1){
			highestNodeCount =((highestNodeCount*3)/4);
		}*/
		//var newHeight = height*highestNodeCount;
		/*if(highestNodeCount>5 && highestNodeCount<=16){
			var newHeight = height+(highestNodeCount*100);
		}else if(highestNodeCount>16){
			var newHeight = 2*height+(highestNodeCount*200);
		}
		else if(highestNodeCount<=5){
			var newHeight = height;
		}else{
			var newHeight = height+(highestNodeCount*200);
		}*/

		if(noOfNodes>10 && noOfNodes<=50){
			var newHeight = height+(noOfNodes*80);
		}else if(noOfNodes>50){
			var newHeight = 2*height+((noOfNodes/2)*170);
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
		if (noOfNodes>50){
			focus+=600;
		}
		console.log("focus"+focus);
		$('html, body').animate({scrollTop: focus+'px'}, 0);
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
	      .attr("dy", ".25em")
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
  			return "app/images/uploads/"+d.imageLocation;
  		  }else{
  			return d.imageLocation;
  		  }
  	  })
//  	  .attr("xlink:href", "images/avatar.png")
  	  .attr("width", "120")
  	  .attr("height", "100")
//  	  .attr("width", "45")
//  	  .attr("height", "40")
//  	  .attr("width", function(d){
//  		  	var width = 5;
////  		  	console.log("depth:"+d.depth);
//  		  if(d.depth>0){
////		  		console.log("d.parent.children.length:"+d.parent.children.length);
//
//		  		if(d.parent.children.length>=4 || d.depth>2){
//		  			width = 2.5;
//		  		}
//
//  		  	return width+"%";
//		  }
//  		return width+"%";
//  	   })
//
//  		//	  30)
//  	  .attr("height", function(d){
//  		  	var height = 5;
////  		  	console.log("depth:"+d.depth);
//  		  if(d.depth>0){
////		  		console.log("d.parent.children.length:"+d.parent.children.length);
//		  		if(d.parent.children.length>=4 || d.depth>2){
//		  			height = 2.5;
//		  		}
//  		  	return height+"%";
//		  }
//  		return height+"%";
//  	   })
  		//20)
      .attr("x", function(d) {
    	  var textElementWidth = d3.select("#employeeNameText").node().getBoundingClientRect().width;
    	 console.log("textElementWidth:"+textElementWidth);
    	  return d.children || d._children ? -120:10;//-100 : 15;
       })
      .attr("y", function(){
    	  var heightOfImage = $(this)[0].getBBox().height
//    	  if(heightOfImage == 20){
//	    	  var heightOfText = d3.select("#employeeNameText").node().getBoundingClientRect().height;
//	    	  console.log("height of text:");
//	    	  console.log(heightOfText);
////
////	    	  var heightOfImage = $(this)[0].getBBox().height;//d3.select("#avatar").node().getBoundingClientRect().height;
//	    	  console.log("height of image:");
//	    	  console.log(heightOfImage);
//
//	    	  console.log($(this)[0].getBBox().height);
//
//	    	  var y = (heightOfText-heightOfImage)/3
//	    	  return y+"em";//"-1.75em";
//    	  }


    	  return "-7.75em";//"-3.25em";

       })
       .call(modifiedClick())
       .call(d3.kodama.tooltip().format(makeTipData))

	  console.log($rootScope["isViewAddUserProfile"]);
	  console.log($rootScope["isViewEditUserProfile"])
	  isViewAddUserProfile = ($rootScope["isViewAddUserProfile"] == "true") ? "visible" : "hidden" ;
	  isViewEditUserProfile = ($rootScope["isViewEditUserProfile"] == "true") ? "visible" : "hidden" ;

	 /* nodeEnter.append("svg:image")
  	  .attr("id","add")
  	  .attr("xlink:href", "app/images/plus_sign1.png")
  	  .attr("width", 20)
  	  .attr("height", 30)
      .attr("x", function(d) { return d.children || d._children ? -50 : 10 ; })
      .attr("dy", "1.75em")
      .attr("visibility", isViewAddUserProfile)
      .call(d3.kodama.tooltip().format(addToolTipData))
      .on("click", function(d){
    	  console.log("add employee");
    	  $scope.$emit("AddNewEmployeeModal",{employee:d});
      });*/

	  /*nodeEnter.append("svg:image")
	  .attr("id","edit")
      .attr("x", function(d) {
    	  var addElementWidth = document.getElementById("add").getBoundingClientRect().width;
    	  console.log("width:"+ addElementWidth);
    	  return d.children || d._children ? -10-(addElementWidth*1.5) : addElementWidth*1.5;//50;
       })
      .attr("dy", "1.75em")
        	  .attr("xlink:href", "app/images/pencil.png")
  	  .attr("width", 17)
  	  .attr("height", 27)
  	  .attr("visibility", isViewEditUserProfile)
  	  .call(d3.kodama.tooltip().format(editToolTipData))
  	  .on("click", function(d){
    	  console.log("edit employee");
    	  console.log("d.supervisorName:"+d.supervisorName);
    	  $scope.$emit("EditEmployeeModal",{employeeId:d.id});
	   })*/


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
	 /* var levelWidth=[1];
		var childCount = function(level,d3){
			if(d3.childern && d3.childern.length > 0){
				if(levelWidth.length <= level+1) levelWidth.push(0);

				levelWidth[level+1] += d3.childern.length;
				d3.children.forEach(function(d3){
					childCount(level+1,d3);
				});
			}
		};
		console.log("childCount"+childCount)
		childCount(0,root);
		var newHeight = d3.max(levelWidth)*2000;
		tree = tree.size([newHeight, width]);*/
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

//	  d3.selectAll("#mainG").attr("transform", "translate(-100," + margin.top + ")");

	  $scope.$emit("loadSelectedNodes",{nodes:d});

	 // update(d);
	}

	 $scope.AddEmployeeFromModal = function(){
		 $scope.$emit("AddNewEmployeeModal",{employee:$scope.employeeProfile});
	 }

	 $scope.EditEmployeeFromModal = function(){
		 $scope.$emit("EditEmployeeModal",{employeeId:$scope.employeeProfile.id});
	 }

	function showViewProfile(d) {

		$scope.employeeProfile = d;
		removeNonViewableData(d);
		$scope.employeeProfileData.dateHired = d.dateHired.replace("12:00:00 AM","");
		$scope.employeeProfileData.grade_name = d.grade_name;
		 console.log($scope.employeeProfile);
		var effect = 'slide';
	    var options = { direction: 'right'};
	    var duration = 500;

	    var employeeId = $cookies.get('userId');
	    $scope.employeeId = employeeId;
	    $scope.getEpmsDetails($scope.employeeProfile.id);
	    $scope.getEmpProjectDetails($scope.employeeProfile.id);
		// var role = $cookies.get('role');
		// $scope.roleAdmin = role;
		//    console.log('-------------------------------Inside dispChartController Login employee Id: '+employeeId +'role: '+role);
		//    console.log('-------------------clickable employeeid: '+$scope.employeeProfile.id);
		//    console.log('------------------supervisor id: '+$scope.employeeProfile.supervisorId);
		//    console.log('-------------------------------Subordinates--------------'+$scope.employeeProfile.subordinate);
	  //   if(((role == 'rm') || (role == 'pm') || (role == 'user'))&&(employeeId != $scope.employeeProfile.id) && ((employeeId != $scope.employeeProfile.supervisorId))){
	  //   	$scope.isViewEpmsSheet = true;
	  //   	//$scope.isViewEditUserProfile=true;
	  //   	console.log('in if cs...');
	  //   	$('#epms').hide();
	  //   }else if((employeeId == $scope.employeeProfile.supervisorId)){
	  //   	$('#epms').show();
	  //   }

	    $('#employeeProfile').hide(effect, options, duration);
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
		//d3.kodama.tooltip();
		//d3_enter_selection
	    //.call(d3.kodama.tooltip());
		//generate data thru database
		//$scope.latest;
		orgChartService.generateEmployeeData(d.id);
	}

	$scope.$on("GenerateLatestEmployeeData",function(event, args){
		var employeeData = args.data;
		makeTipData(args.data,0);
//       return{ title: 'Employee Information',
//        items: [
//            { title: 'Employee Id', value: employeeData.id},
//            { title: 'Radius', value: employeeData.name}
//        ]};
//
//		$scope.employeeInfo="Employee Information \n\n"
//		$scope.employeeInfo="Employee Id: "+ employeeData.id+"\n";
//		$scope.employeeInfo+="Employee Name: "+ employeeData.name+"\n";
//		$scope.employeeInfo+="Employee Department: "+ employeeData.department+"\n";
//		$scope.employeeInfo+="Employee Position: "+ employeeData.position+"\n";
//		$scope.employeeInfo+="Employee Supervisor: "+ employeeData.supervisorName+"\n";
//		$scope.employeeInfo+="Date Hired: "+ employeeData.dateHired.replace("12:00:00 AM","");+"\n";
//		title.text($scope.employeeInfo);

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

	/*if(highestNodeCount<=5){
		$('html, body').css({
			'overflow': 'hidden',
			'height': '100%'
		});
	}else{
		$('html, body').css({
			'overflow': 'auto',
			'height': 'auto'
		});

	}*/

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

   $scope.$on("AddNewEmployeeModal",function(events, args){
	   var isFromDisplayChart = true;
	   orgChartService.generateAddNewModal(args.employee,isFromDisplayChart);
   });

   $scope.$on("EditEmployeeModal",function(events, args){
	   orgChartService.generateEditModal(args.employeeId);
   });

   $scope.$on("ViewEmployeeModal",function(events, args){
	   orgChartService.generateViewModal(args.employeeId);
   });

   $scope.PerfLinkRedirect = function() {
		$scope.absUrl = $location.absUrl().split("org-hierarchy.jsp",1)[0];

		//var perfUrl = $scope.absUrl + "#/epmsChart/" + $scope.employeeProfile.id;
		//var perfUrl = $scope.absUrl + "#/epmsTabs/" + $scope.employeeProfile.id;
		var perfUrl = $scope.absUrl + "#/epmsTabs/" + $scope.employeeProfile.id + "/default/11";
		//alert(perfUrl);
		window.top.location.href = perfUrl;
	}

   $scope.getEpmsDetails = function(empid){

	   $scope.statusFlag = false;
	   $http({
			url : 'hrm/epms/webservice/epmsTabs/get/employeeDetails/'+ empid,
			method : "GET"

		}).success(function(data, status, headers, config) {
			if(data.length != 0){
				console.log('1 ==== '+(data[0].supervisor_id == $cookies.get('userId')));
				console.log('2 ===== '+(empid == $cookies.get('userId')));

				if((data[0] != null) && ((data[0].supervisor_id == $cookies.get('userId')) || (empid == $cookies.get('userId'))))
					$scope.statusFlag = true;
				$scope.eawdata = data;
				return eawdata;
			}

		});
	   }
 $scope.getEmpProjectDetails = function(empid){

	   $http({
			url : 'hrm/epms/webservice/epmsTabs/get/prCategories/'+ empid,
			method : "GET"

		}).success(function(data, status, headers, config) {
			if(data.length != 0){
				$scope.prdata = data;
				return prdata;
			}
		});
	   }

});
