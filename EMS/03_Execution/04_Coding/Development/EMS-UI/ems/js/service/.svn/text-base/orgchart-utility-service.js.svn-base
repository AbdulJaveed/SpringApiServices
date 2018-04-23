var orgChartApp = angular.module('orgChartUtilityService', []);
orgChartApp.service('orgChartUtilityService', function($http,$rootScope) {
	var nodeArray=[];
	var depth=0;
	var countChildrenNodesArray=[];
	var countChildrenNodes=0;
	var highestChildrenCount=0;

	var initializeKeyData = function(){
		 nodeArray=[];
		 depth=0;
		 countChildrenNodesArray=[];
		 countChildrenNodes=0;
		 highestChildrenCount=0;
	}
	this.getMaxNumberOfChildren = function(node){
		initializeKeyData();
//		console.log("push nodes to array");
		pushNodeToArray(node);
//		console.log("nodeArray.length:"+nodeArray.length);

		//get the deepest level- the deepest level will be used as a basis of the width
		getDeepestNodeDepth(nodeArray);
		if(depth==0){
			depth=1;
		}

		//count the totalNumber of children based on their depth and then push them into an array
		countChildrenNodesByDepth(nodeArray, depth);
//		console.log("countChildrenNodesArray.length:"+countChildrenNodesArray.length);

		//compare all the children count and then get the highestChildrenCount which will be the basis of height
		getHighestChildrenCount(countChildrenNodesArray);
		if(highestChildrenCount==0){
			highestChildrenCount=1;
		}
		console.log("depth:"+depth);
		console.log("highestChildrenCount:"+highestChildrenCount);
		var depthAndCount = [{key:"depth", value:depth}, {key:"highestCount", value:highestChildrenCount}];
		return depthAndCount;
	}

	var pushNodeToArray = function(node){
		var childrenNodes = node.children;
		if(node.children==undefined){
			childrenNodes = node.subordinate;
		}
		angular.forEach(childrenNodes, function(childNode, key){
			pushNodeToArray(childNode);
		});
//		console.log("node");
//		console.log(node);
		nodeArray.push(node);

	}

	var getDeepestNodeDepth = function(arrayOfNodes){
		for(var i=0; i < arrayOfNodes.length; i++){
//			console.log("i:"+i)
			var nexCount = i+1;
//			console.log(arrayOfNodes[i].level);
			if(i == 0){
				;
				var firstDepth = arrayOfNodes[i].level;

				var nextDepth = arrayOfNodes[nexCount].level;

				if(nextDepth === undefined){
					nextDepth = 0;
				}

				if(firstDepth === undefined){
					firstDepth = 0;
				}
//				console.log("firstDepth:"+firstDepth);
//				console.log("nextDepth:"+nextDepth);

				if(firstDepth >= nextDepth){
					depth=firstDepth;
				}
				else{
					depth = nextDepth;
				}
				//console.log("depth:"+depth);
			}

			else if(i>0 && i <=(arrayOfNodes.length)){
				var firstDepth = depth;
				var nextDepth = arrayOfNodes[nexCount-1].level;

				if(nextDepth === undefined){
					nextDepth = 0;
				}

				if(firstDepth === undefined){
					firstDepth = 0;
				}

//				console.log("firstDepth:"+firstDepth);
//				console.log("nextDepth:"+nextDepth);

				if(firstDepth >= nextDepth){
					depth=firstDepth;
				}else{
					depth = nextDepth;
				}

			}

		}
	}

	var countChildrenNodesByDepth = function(arrayOfNodes, deepestLevel){
			for(i=0; i<=deepestLevel; i++){
				countChildrenNodes=0;
				angular.forEach(arrayOfNodes, function(node, key){
					var nodeLevel = node.level;
					if(nodeLevel==i){
						countChildrenNodes++;
					}
				});
//				console.log("countChildrenNodes:"+countChildrenNodes);
				countChildrenNodesArray.push(countChildrenNodes);
			}
	}


	var getHighestChildrenCount = function(childrenNodesCountArray){
		console.log(childrenNodesCountArray);

		for(var i=0; i < childrenNodesCountArray.length; i++){
//			console.log("i:"+i);
			var nexCount = i+1;
			highestChildrenCount = childrenNodesCountArray[i];
			if(i == 0){
//				console.log("childrenNodesCountArray[i]:"+childrenNodesCountArray[i]);
//				console.log("childrenNodesCountArray[i++]:"+childrenNodesCountArray[i++]);
				var nextChildrenCount = childrenNodesCountArray[nexCount];
				if(highestChildrenCount<=nextChildrenCount){
					highestChildrenCount = nextChildrenCount;
				}
			}
			else if(i>0 && i <=(childrenNodesCountArray.length)){
//				console.log("childrenNodesCountArray[i]:"+childrenNodesCountArray[i]);

//				console.log("childrenNodesCountArray[i++]:"+childrenNodesCountArray[i++]);
				var nextChildrenCount = childrenNodesCountArray[nexCount];
				if(highestChildrenCount<=nextChildrenCount){
					highestChildrenCount = nextChildrenCount;
				}
			}

		}
//		console.log("highestChildrenCount:"+highestChildrenCount);

	}
});
