(function() {
'use strict';
angular.module('autoCompleteController', []).controller('autoCompleteController', autoCompleteController);

angular.module('clientAutoComplete', [])
	.controller('autoCompleteController', autoCompleteController)
	.directive('clientAutoComplete', clientAutoComplete);

autoCompleteController.$inject = [];
clientAutoComplete.$inject = ['$filter','$timeout'];


function autoCompleteController() {
}

function clientAutoComplete($filter,$timeout) {
            return {
                restrict: 'A',
                scope: {
                	obj: "=object",
                    dataSource: "=data",
                    field:'='
                },
                controller:'autoCompleteController',
                controllerAs: 'vm',
                bindToController: {
                	callback: '&'
                },
                link: postLink,
            };
            
            function postLink(scope, elem, attrs, vm) {
                elem.autocomplete({
                    source: function (request, response) {
                        // term has the data typed by the user
                        var params = request.term;
                        // simulates api call with odata $filter
                        var data = scope.dataSource;
                        var column = scope.field; 
                        if (data) {
                            switch (column) {
                            case 'item':
                            	var validItems = _.reject(data, function(item){ return item.itemDescription == ''; });
                                var result = $filter('filter')(validItems, {itemName: params});
                                angular.forEach(result, function (item) {
                                    item['value'] = item['itemDescription'];
                                });
                                var uniqueResult = _.uniq(result, function(item, key, itemDescription) { 
                                    return item.itemName;
                                });
                                response(uniqueResult);
                                break;
                            case 'categories':
                                var result = $filter('filter')(data, {categoryName: params});
                                angular.forEach(result, function (item) {
                                    item['value'] = item['categoryName'];
                                });
                                response(result);
                                break;
                            case 'suggestedVendors':
                                var result = $filter('filter')(data, {supplierName: params});
                                angular.forEach(result, function (item) {
                                    item['value'] = item['supplierName'];
                                });
                                response(result);
                                break;
                            case 'buyer':
                                //var result1 = $filter('filter')(data, {firstName: params});
                                //var result = _.union(result1, $filter('filter')(data, {lastName: params}));
                                var result = $filter('filter')(data, {buyerName: params});
                                angular.forEach(result, function (item) {
                                    item['value'] = item['firstName'] +' '+ item['lastName'];
                                });
                                response(result);
                                break;
                            case 'category':
                                break;
                            case 'irItem':
                                var result = $filter('filter')(data, {itemDescription: params});
                                angular.forEach(result, function (item) {
                                    item['value'] = item['itemDescription'];
                                });
                                var uniqueResult = _.uniq(result, function(item, key, itemDescription) { 
                                    return item.itemDescription;
                                });
                                response(uniqueResult);
                                break;
                            default:
                            }
                        }
                    },
                    minLength: 1,                       
                    select: function (event, ui) {
                       // force a digest cycle to update the views
                       var column = scope.field; 
                       console.log(column);
                       scope.$apply(function(){
                    	   delete ui.item.label;
                           
                           switch (column) {
                              case 'irItem':
                            	  ui.item.value = ui.item.itemCode;
                            	  break;
                              case 'item':
                            	  ui.item.value = ui.item.itemCode;
                            	  break;
                              default:
                           }
                           
                    	   scope.obj = ui.item;
                    	   $timeout(function() {
                    		   // callback function when a option is selected
                                   
                    		   scope.vm.callback({itemObj : scope.obj});
                    	   }, 200);
                       });
                    },
                });
            }
}
})();