var osi = angular.module(
		'myApp',
		[ 'myApp.config', 'ngRoute', 'ngResource', 'ngCookies', 'ngStorage',
      'ui.bootstrap', 'ngResource', 'ngFileUpload', 'checklist-model',
      '720kb.datepicker', 'blockUI', 'clientAutoComplete', 'infinite-scroll',
      'angularUtils.directives.dirPagination', 'myApp.controllers',
      'myApp.logincontroller', 'myApp.dashboardcontroller',
				'myApp.sidebarCtrl', 'myApp.AuthenticationService','myApp.FlashService',
				'myApp.menucontroller','myApp.SharedDataService','myApp.CommonService',
      'myApp.MenuService', 'myApp.userListController',
      'myApp.userInfoController', 'myApp.userService',
				'myApp.osiResponsibilitiesController','myApp.LookupService','myApp.LookupController',
				'myApp.osiResponsibilitiesService','myApp.SegmenthirarchyController','myApp.SegmentstructurehdrService',
				'myApp.userResponsibilityController','myApp.SegmenthirarchyService',
      'myApp.ResponsibilityService',
      'myApp.functionExclusionController',
      'myApp.functionExclusionService',
      'myApp.operationExclusionController',
      'myApp.operationExclusionService', 'myApp.menuEntryService',
      'myApp.menuEntriesListController',
      'myApp.menuEntriesController',
	  'myApp.categoryListController',
	  'myApp.categoryController','myApp.categoryService',
      'myApp.FunctionController', 'myApp.FunctionService',
				'myApp.organizationController',
				'myApp.organizationService',
				'myApp.ReportsController','myApp.ReportsService','myApp.ReportsHistoryController','myApp.ReportsGroupController','myApp.ReportsGroupService',
				'ngAnimate','base64', 'ngMessages','ngMask',
                'myApp.userProfileController', 'myApp.userProfileService','ui.multiselect'
				,'myApp.uploadController', 'myApp.FlexiFieldService',
				'myApp.basicInfoController',
                'myApp.employeeBasicInfoController', 'myApp.employeeBasicInfoService'
				,'myApp.medicalInfoController','myApp.medicalInfoService','myApp.officeInfoController','myApp.officeInfoService','myApp.functionExclusionController'
				,'myApp.employeesController','myApp.employeesService',
				'myApp.employeeSkillService','myApp.userSkillsController',
      'myApp.PersonalInfoController','myApp.PersonalInfoService','myApp.empBankDetailsController','myApp.empBankDetailsService',
                'myApp.empBankDetailsConfigController','myApp.empBankDetailsConfigService',
            'myApp.passportInfoController', 'myApp.AssignmentsController','myApp.AssignmentsService','angularMoment'
          ,'myApp.employeesControllerSelf','myApp.employeesServiceSelf',
          'myApp.OsiJobCodesController', 'myApp.OsiJobCodesService',
          'myApp.OsiGradesController', 'myApp.OsiGradesService',
          'myApp.OsiTitlesController', 'myApp.OsiTitlesService',
          'myApp.OsiCertificationsController', 'myApp.OsiCertificationsService',
          'myApp.OsiSkilsController', 'myApp.OsiSkilsService',
          'myApp.OsiCostCenterController', 'myApp.OsiCostCenterService',
          'myApp.OsiBusinessUnitsController', 'myApp.OsiBusinessUnitsService',
          'myApp.OsiSubPracticeController','myApp.OsiSubPracticeService',
          'myApp.OsiDepratmentController', 'myApp.OsiDepratmentService'
					,'orgHierarchyController', 'orgChartService', 'orgChartUtilityService','employeeModalController'
					,'employeeOrgListCtrl'
					,'ui.grid','ngTouch','ui.grid.resizeColumns','ui.grid.pagination','ui.grid.pinning','ui.grid.exporter'
          ,'ui.grid.edit','ui.grid.expandable', 'ui.grid.selection','ui.grid.moveColumns','ui.grid.treeView'
          , 'myApp.EmpAdditionalDocsController', 'myApp.employeeOverallSearchController', 	'myApp.OsiSkillTagsController','myApp.OsiSkillTagsService',
					'myApp.OsiSkillGroupController','myApp.OsiSkillGroupsService',
					'myApp.OsiCertificationTagsController','myApp.OsiCertificationTagsService',
					'myApp.OsiCertificationGroupController','myApp.OsiCertificationGroupsService'
        ])



		.config(config)
		.directive(
    'validDecimalNumber',
    function() {
      return {
				require : '?ngModel',
				link : function(scope, element, attrs, ngModelCtrl) {
          if (!ngModelCtrl) {
            return;
          }
          ngModelCtrl.$parsers.push(function(val) {
            if (angular.isUndefined(val)) {
              var val = '';
            }
            var clean = val.replace(/[^-0-9\.]/g, '');
            var negativeCheck = clean.split('-');
            var decimalCheck = clean.split('.');
            if (!angular.isUndefined(negativeCheck[1])) {
              negativeCheck[1] = negativeCheck[1].slice(0,
                negativeCheck[1].length);
              clean = negativeCheck[0] + '-' + negativeCheck[1];
              if (negativeCheck[0].length > 0) {
                clean = negativeCheck[0];
              }
            }

            if (!angular.isUndefined(decimalCheck[1])) {
              decimalCheck[1] = decimalCheck[1].slice(0, 2);
              if((0 <= decimalCheck[1]) && (decimalCheck[1] <= 11))
                clean = decimalCheck[0] + '.' + decimalCheck[1];
              else
                clean = (++decimalCheck[0]) + '.' + 0;
            }
            if (val !== clean) {
              ngModelCtrl.$setViewValue(clean);
              ngModelCtrl.$render();
            }
            return clean;
          });
                               element.bind('keypress', function (event) {
            if (event.keyCode === 32) {
              event.preventDefault();
            }
                                 });
                               element.bind('keypress', function (event) {
            var n = event.keyCode;
                                if(n!=46){
                                if ((event.which < 48 || event.which > 57) && event.which!==190)
                                {
                event.preventDefault();
              }
            }
          });
        }
      };
}).directive('decimalPlaces',function(){
  return {
      link:function(scope,ele,attrs){
          ele.bind('keypress',function(e){
              var newVal=$(this).val()+(e.charCode!==0?String.fromCharCode(e.charCode):'');
              if($(this).val().search(/(.*)\.[0-9][0-9]/)===0 && newVal.length>$(this).val().length){
                  e.preventDefault();
              }
          });
      }
  };
}).directive('validOnlynumber', function() {
    return {
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
        if (!ngModelCtrl) {
          return;
        }

        ngModelCtrl.$parsers.push(function(val) {
          if (angular.isUndefined(val)) {
            var val = '';
          }
          var clean = val.replace(/[^0-9]+/g, '');
          if (val !== clean) {
            ngModelCtrl.$setViewValue(clean);
            ngModelCtrl.$render();
          }
          return clean;
        });

        element.bind('keypress', function(event) {
				if (event.keyCode === 32 ) {
            event.preventDefault();
          }
        });
        element.bind('keypress', function(event) {
				if (event.which < 48 || event.which > 57)
                                {
            event.preventDefault();
          }
        });
      }
    };
}).directive('validDecimalPercentage',
function() {
      return {
            require : '?ngModel',
            link : function(scope, element, attrs, ngModelCtrl) {
          if (!ngModelCtrl) {
            return;
          }
          ngModelCtrl.$parsers.push(function(val) {
            if (angular.isUndefined(val) || val == null) {
              var val = '';
            }
            console.log("val: " + val);
                        var clean = val.replace(/[^0-9\.]/g, '');
            var decimalCheck = clean.split('.');
            console.log(decimalCheck);
                        if(!angular.isUndefined(decimalCheck[1])) {
              //decimalCheck[1] = decimalCheck[1].slice(0, 2);
              clean = decimalCheck[0] + '.' + decimalCheck[1].slice(0, 2);
            }

            var dec = parseFloat(clean);
                        if(dec > 100) {
              clean = '100.00';
            }

            if (val !== clean) {
              ngModelCtrl.$setViewValue(clean);
              ngModelCtrl.$render();
            }

            return clean;
          });

          element.bind('keypress', function(event) {
            if (event.keyCode === 32) {
              event.preventDefault();
            }
          });

          element.bind('blur', function() {
            clean = ngModelCtrl.$modelValue;
            var decimalCheck = ngModelCtrl.$modelValue.split('.');
                        if(!angular.isUndefined(decimalCheck[1])) {
                            if(decimalCheck[1].length === 1) {
                clean = clean + '0';
                            } else if(decimalCheck[1].length === 0) {
                clean = clean + '00';
              }
            } else {
              clean = clean + '.00';
            }

            var dec = parseFloat(clean);
                        if(dec === 0) {
              clean = '';
            }

            console.log(clean);
            element.val(clean);
          });
        }
      };
}).directive('noSpecialChar', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^a-zA-Z0-9\s]/gi, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive('noSpecialCharSpace', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^a-zA-Z0-9]/gi, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
        element.bind('keypress', function(event) {
          if (event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    }
}).directive('alphaNumericDot', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^a-zA-Z0-9.]/, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive('floatValue', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^0-9\.]/g, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive('onlyAlphabets', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[0-9]/, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive('onlySlashChar', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^a-zA-Z0-9/\s]/gi, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
})
.directive('disallowSpaces', function() {
    return {
      restrict: 'A',

      link: function($scope, $element) {
        $element.bind('input', function() {
          $(this).val($(this).val().replace(/ /g, ''));
        });
      }
    };
}).directive('onlyNumber', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^0-9]/, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
        element.bind('keypress', function(event) {
				if (event.keyCode === 32 ) {
            event.preventDefault();
          }
        });
        element.bind('keypress', function(event) {
				if (event.which < 48 || event.which > 57)
                                {
            event.preventDefault();
          }
			});
      }

    }
}).directive('specialCharUnderscore', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^\w\s]/gi, '');
				if(cleanInputValue.charAt( 0 ) === '_' )
					cleanInputValue = cleanInputValue.slice( 1 ); //avoid input starting with '_'
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive('specialCharUnderscoreNospace', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^\w\s]/gi, '');
				if(cleanInputValue.charAt( 0 ) === '_' )
					cleanInputValue = cleanInputValue.slice( 1 ); //avoid input starting with '_'
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
        element.bind('keypress', function(event) {
          if (event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    }
}).directive('specialCharHyphenDot', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^\w\s.\-]/gi, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive('specialCharHyphen', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/^[\w.\-]+$/, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive('specialCharUnderscoreHyphenDot', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^\w\s.\-\/]/gi, '');
                                if(cleanInputValue.charAt( 0 ) === '_' )
					cleanInputValue = cleanInputValue.slice( 1 ); //avoid input starting with '_'
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });

//                        element.bind('keypress', function(event) {
//                            if (event.keyCode === 32) {
//                                    event.preventDefault();
//                            }
//			});
      }
    }
})  .directive('capitalize', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attrs, modelCtrl) {
      var capitalize = function(inputValue) {
        if (inputValue == undefined) inputValue = '';
        var capitalized = inputValue.toUpperCase();
        if (capitalized !== inputValue) {
          // see where the cursor is before the update so that we can set it back
          var selection = element[0].selectionStart;
          modelCtrl.$setViewValue(capitalized);
          modelCtrl.$render();
          // set back the cursor after rendering
          element[0].selectionStart = selection;
          element[0].selectionEnd = selection;
        }
        return capitalized;
      }
      modelCtrl.$parsers.push(capitalize);
      capitalize(scope[attrs.ngModel]); // capitalize initial value
    }
  };
}).directive('titleCase', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
                                 cleanInputValue = inputValue.split(' ').map(function (word) {
            return word.charAt(0).toUpperCase() + word.slice(1);
          }).join(' ');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive("myValidDate", function () {
    return {
      require: "ngModel",
      restrict: "A", // Attributes only
		    link: function (scope, elem, attr, ctrl) {
        ctrl.$validators.bzValidDate = function(value) {
          if (value === undefined || value === null || value === "") {
            return true;
          }

          return moment(value, ["DD-MMM-YYYY"], true).isValid();
        }
      }
    }
}).directive("limitToMax", function() {
    return {
      link: function(scope, element, attributes) {
        element.on("keydown keyup", function(e) {
          if (Number(element.val()) > Number(attributes.max) &&
            e.keyCode != 46 // delete
            &&
            e.keyCode != 8 // backspace
          ) {
            e.preventDefault();
            element.val(attributes.max);
          }
        });
      }
    };
}).directive('allowNumbersDot', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^0-9.\s]/gi, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
      }
    }
}).directive('excelExport', function () {
    return {
      restrict: 'A',
      scope: {
        fileName: "@",
        data: "&exportData"
      },
      replace: true,
      template: '<i style="cursor: pointer; font-size: 20px;" class="glyphicon glyphicon-download" ng-click="download()"></i>',
        link: function (scope, element) {
        scope.download = function() {
          function datenum(v, date1904) {
            		if(date1904) v+=1462;
            var epoch = Date.parse(v);
            return (epoch - new Date(Date.UTC(1899, 11, 30))) / (24 * 60 * 60 * 1000);
          };

          function getSheet(data, opts) {
            var ws = {};
            		var range = {s: {c:10000000, r:10000000}, e: {c:0, r:0 }};
            		for(var R = 0; R != data.length; ++R) {
            			for(var C = 0; C != data[R].length; ++C) {
            				if(range.s.r > R) range.s.r = R;
            				if(range.s.c > C) range.s.c = C;
            				if(range.e.r < R) range.e.r = R;
            				if(range.e.c < C) range.e.c = C;
            				var cell = {v: data[R][C] };
            				if(cell.v == null) continue;
            				var cell_ref = XLSX.utils.encode_cell({c:C,r:R});
            				if(typeof cell.v === 'number') cell.t = 'n';
            				else if(typeof cell.v === 'boolean') cell.t = 'b';
            				else if(cell.v instanceof Date) {
            					cell.t = 'n'; cell.z = XLSX.SSF._table[14];
            					cell.v = datenum(cell.v);
              }
            				else cell.t = 's';
                ws[cell_ref] = cell;
              }
            }
            		if(range.s.c < 10000000) ws['!ref'] = XLSX.utils.encode_range(range);
            return ws;
          };

          function Workbook() {
            		if(!(this instanceof Workbook)) return new Workbook();
            this.SheetNames = [];
            this.Sheets = {};
          }

            	var wb = new Workbook(), ws = getSheet(scope.data());
          /* add worksheet to workbook */
          wb.SheetNames.push(scope.fileName);
          wb.Sheets[scope.fileName] = ws;
            	var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});

          function s2ab(s) {
            var buf = new ArrayBuffer(s.length);
            var view = new Uint8Array(buf);
            		for (var i=0; i!=s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
            return buf;
            	}
        	saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), scope.fileName+'.xlsx');
        	};
          }
        };
  }).directive('convertToNumber', function() {
    return {
      require: 'ngModel',
      link: function(scope, element, attrs, ngModel) {
        ngModel.$parsers.push(function(val) {
          return parseInt(val, 10);
        });
        ngModel.$formatters.push(function(val) {
          return '' + val;
        });
      }
    };
}).directive('uppercase', function() {
return {
      restrict: "A",
      require: "?ngModel",
      link: function(scope, element, attrs, ngModel) {

        //This part of the code manipulates the model
        ngModel.$parsers.push(function(input) {
          return input ? input.toUpperCase() : "";
        });

        //This part of the code manipulates the viewvalue of the element
        element.css("text-transform","uppercase");
      }
};
}).directive('allCharsExceptSpace', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[/S/s]/gi, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
        element.bind('keypress', function(event) {
          if (event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    }
}).directive('decimalNumber', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[/S/s]/gi, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
        element.bind('keypress', function(event) {
          if (event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    }
}).directive('currencyFormatter', ['$filter', function ($filter) {
    formatter = function (num) {
        if((typeof num!=="number")|| (isNaN(num)) ){
        return num;
        }
        else{
        var orgnlValue = parseFloat(num).toFixed(4);
            var pricisionVal=(orgnlValue-Math.floor(orgnlValue)).toFixed(4);
            var tmp=($filter('number')(Math.floor(orgnlValue)));
            var finalNum=tmp+"."+pricisionVal.toString().substr(2,pricisionVal.toString().length-2);
      }
      return finalNum;
    };

    return {
      restrict: 'A',
      require: 'ngModel',
      link: function (scope, element, attr, ngModel) {
        ngModel.$parsers.push(function (str) {
          return str ? Number(str) : '';
        });
        ngModel.$formatters.push(formatter);

        element.bind('blur', function() {
          element.val(formatter(ngModel.$modelValue))
        });
        element.bind('focus', function () {
          element.val(ngModel.$modelValue);
        });
      }
    };
}]).directive('dateFormatter', ['$filter', function ($filter) {

    dateFormat = function (date) {
          if(( date === undefined)|| (date === null) || (date === "")){
        return date;

          }
          else{
              var formattedDate= $filter('date')(new Date(date), 'dd-MMM-yyyy');
        //var finalNum=tmp+"."+pricisionVal.toString().substr(2,pricisionVal.toString().length-2);

      }
      return formattedDate;
    };

    return {
      restrict: 'A',
      require: 'ngModel',
      link: function (scope, element, attr, ngModel) {
        /*  ngModel.$parsers.push(function (str) {
           return str ? Number(str) : '';
          });*/
        ngModel.$formatters.push(dateFormat);

        element.bind('blur', function() {
          element.val(dateFormat(ngModel.$modelValue))
        });
        element.bind('focus', function () {
          element.val(ngModel.$modelValue);
        });
      }
    };
}]).directive('checkboxUseEnter', function() {
    return {
        require : 'ngModel',
        restrict : 'A',
        link : function(scope, element, attrs, modelCtrl) {
            element.on("keypress",function(event){
                if(event.which === 13){
                    if(element.prop('checked') == true){
                        element.prop('checked',false);
                    }else{
                        element.prop('checked',true);
            }
          }
        });
      }
    };
}).directive('slashHypenWithAlhpa', function() {
    return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attrs, modelCtrl) {
        modelCtrl.$parsers.push(function(inputValue) {
          if (inputValue == null)
            return ''
          cleanInputValue = inputValue.replace(/[^a-zA-Z0-9\/\-\\]/gi, '');
          if (cleanInputValue != inputValue) {
            modelCtrl.$setViewValue(cleanInputValue);
            modelCtrl.$render();
          }
          return cleanInputValue;
        });
        element.bind('keypress', function(event) {
          if (event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    }
}).directive('tabbingInsideModalOnly', function() {
    //http://stackoverflow.com/questions/14572084
    return {
        restrict : 'A',
        link : function(scope, element, attrs) {
            var allListElements = $( 'input, button, select' );
        //var inputs = $(element.closest('form')).find(allListElements);
        var inputs = $(element.closest('.modal')).find(allListElements);
        var firstInput = inputs.first();
        var lastInput = inputs.last();

        /*set focus on first input*/
        firstInput.focus();

        /*redirect last tab to first input*/
            lastInput.on('keydown', function (e) {
          if ((e.which === 9 && !e.shiftKey)) {
            e.preventDefault();
            firstInput.focus();
          }
        });

        /*redirect first shift+tab to last input*/
            firstInput.on('keydown', function (e) {
          if ((e.which === 9 && e.shiftKey)) {
            e.preventDefault();
            lastInput.focus();
          }
        });
      }
    };
}).directive('convertDecimalNumber',function() {
    return {
          require : '?ngModel',
          link : function(scope, element, attrs, ngModelCtrl) {
        	  if(!attrs.convertDecimalNumber){
        		  var num=8;var decPlace=2;
        	  }else{
        		  var params = scope.$eval(attrs.convertDecimalNumber);
            	  var num=params.num;var decPlace=params.decPlace;
        	  }
        if (!ngModelCtrl) {
          return;
        }
        ngModelCtrl.$parsers.push(function(val) {
          if (angular.isUndefined(val) || val == null) {
            var val = '';
          }
          var clean = val.replace(/[^0-9\.]/g, '');
          var decimalCheck = clean.split('.');

          if(angular.isUndefined(decimalCheck[1])) {
        	  decimalCheck[0]=decimalCheck[0].slice(0,num);
              clean = decimalCheck[0];
            }
          if(!angular.isUndefined(decimalCheck[1])) {
            //decimalCheck[1] = decimalCheck[1].slice(0, 2);
            clean = decimalCheck[0].slice(0,num) + '.' + decimalCheck[1].slice(0,decPlace);
          }
          var dec = parseFloat(clean);
          if (val !== clean) {
            ngModelCtrl.$setViewValue(clean);
            ngModelCtrl.$render();
          }

          return clean;
        });

        element.bind('keypress', function(event) {
          if (event.keyCode === 32) {
            event.preventDefault();
          }
        });

        element.bind('blur', function() {
          clean = ngModelCtrl.$modelValue;
          var decimalCheck = ngModelCtrl.$modelValue.split('.');
                      if(!angular.isUndefined(decimalCheck[1])) {
                          if(decimalCheck[1].length === 1) {
              clean = clean + '0';
                          } else if(decimalCheck[1].length === 0) {
              clean = clean + '00';
            }
          } else {
            clean = clean + '.00';
          }

          var dec = parseFloat(clean);
                      if(dec === 0) {
            clean = '';
          }

          element.val(clean);
          ngModelCtrl.$setViewValue(clean);
          ngModelCtrl.$render();
        });
      }
    };
});
/* directive('uploadfile', function () {
    return {
      restrict: 'A',
      link: function(scope, element) {

        element.bind('click', function(e) {
            //alert("clicked me");
          //angular.element(e.target).siblings('.lineAttach').trigger('click');
          angular.element(e.target.previousElementSibling).trigger('click');

        });
      }
    };
});
 */


function config($routeProvider) {
  $routeProvider.when('/login', {
    title: 'Login',
    templateUrl: 'views/login.html',
    controller: 'LoginCtrl',
    controllerAs: 'vm'
  });
  $routeProvider.when('/dashboard', {
    title: 'Dashboard',
    templateUrl: 'views/dashboard.html',
    controller: 'DashboardCtrl',
    controllerAs: 'vm'
  });
  $routeProvider.when('/lookup', {
    title: 'Lookup',
    templateUrl: 'views/lookup.html',
    controller: 'LookupController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/segmenthirarchy', {
    title: 'Segment Hierarchy',
    templateUrl: 'views/segmenthirarcy.html',
    controller: 'SegmenthirarchyController',
    controllerAs: 'vm'
  });
    $routeProvider.when('/osiResponsibilitiesList', {
    title: 'Responsibilities',
    templateUrl: 'views/osiResponsibilitiesList.html',
    controller: 'osiResponsibilitiesController',
    controllerAs: 'vm'
    });
  $routeProvider.when('/users', {
    title: 'OSI-User Management',
    templateUrl: 'views/userList.html',
    controller: 'userListController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/menu', {
    title: 'Menu',
    templateUrl: 'views/menu.html',
    controller: 'MenuController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/operation', {
    title: 'Operation Exclusion',
    templateUrl: 'views/operationExclusion.html',
    controller: 'operationExclusionController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/menuEntries', {
    title: 'Menu Entries',
    templateUrl: 'views/menuEntryList.html',
    controller: 'menuEntriesListController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/categories', {
        title: 'Categories',
        templateUrl: 'views/categoryList.html',
        controller: 'categoryListController',
        controllerAs: 'vm'
	});

  $routeProvider.when('/function', {
    title: 'Function',
    templateUrl: 'views/function.html',
    controller: 'FunctionController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/userProfile', {
    title: 'User Profile',
    templateUrl: 'views/userProfile.html',
    controller: 'userProfileController',
    controllerAs: 'vm'
  });

	$routeProvider.when('/certificationDetails', {
        title: 'Certification Details',
        templateUrl: 'views/empCertificates.html',
        controller: 'userSkillsController',

	});
	$routeProvider.when('/skillDetails', {
        title: 'Skill Details',
        templateUrl: 'views/userSkills.html',
        controller: 'userSkillsController',

	});
	$routeProvider.when('/emergencyContacts', {
        title: 'Emergency contacts',
        templateUrl: 'views/emergencyContacts.html',
        controller: 'userSkillsController',

    });

	$routeProvider.when('/medicalInfo', {
        title: 'Medical Information',
        templateUrl: 'views/medicalInfo.html',
        controller: 'medicalInfoController',
        controllerAs: 'vm'
    });

	$routeProvider.when('/officeInfo-admin', {
        title: 'Office Information',
        templateUrl: 'views/officeInfo.html',
        controller: 'officeInfoController',
        controllerAs: 'vm'
    });

  $routeProvider.when('/reports', {
    title: 'Reports',
    templateUrl: 'views/reports.html',
    controller: 'ReportsController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/reportshistory', {
    title: 'Reports History',
    templateUrl: 'views/reportshistory.html',
    controller: 'ReportsHistoryController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/reportsgroup', {
    title: 'Reports Group',
    templateUrl: 'views/reportsgroup.html',
    controller: 'ReportsGroupController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/employeeInfo', {
    title: 'Profile',
    templateUrl: 'views/employeeBasicInfo.html',
    controller: 'employeeBasicInfoController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/employeeInfos', {
    title: 'Profile',
    templateUrl: 'views/employeeProfile.html',
    controller: 'employeeBasicInfoController',
    controllerAs: 'vm'
  });
	$routeProvider.when('/employees', {
        title: 'Employee List',
        templateUrl: 'views/employeeList.html',
        controller: 'EmployeesController',
        controllerAs: 'vm'
    });
    $routeProvider.when('/employees-resp', {
      title: 'Employee List',
      templateUrl: 'views/employeeList.html',
      controller: 'EmployeesController',
      controllerAs: 'vm'
  });
    $routeProvider.when('/userResponsibility', {
        title: 'Responsibility',
        templateUrl: 'views/UserResponsibility.html',
        controller: 'userResponsibilityController',
        controllerAs: 'vm'
    });

    $routeProvider.when('/functionExclusion', {
        title: 'Function Exclusion',
        templateUrl: 'views/userFunctionExclusion.html',
        controller: 'functionExclusionController',
        controllerAs: 'vm'
    });

    $routeProvider.when('/personalinfo', {
        title: 'Personal Info',
        templateUrl: 'views/personalInformation.html',
        controller: 'PersonalInfoController',
        controllerAs: 'vm'
    });

    $routeProvider.when('/empBankList', {
        title: 'Bank Details',
        templateUrl: 'views/empBankList.html',
        controller: 'empBankDetailsController',
        controllerAs: 'vm'
    });

    $routeProvider.when('/organization', {
        title: 'Organization',
        templateUrl: 'views/orgnization.html',
        controller: 'organizationController',
        controllerAs: 'vm'
    });

    $routeProvider.when('/passportInfo', {
        title: 'Passport Information',
        templateUrl: 'views/passportInfo.html',
        controller: 'passportInfoController',
        controllerAs: 'vm'
    });
    $routeProvider.when('/employeeAssignments', {
        title: 'Work',
        templateUrl: 'views/assignmentsPage.html',
        controller: 'AssignmentsController',
        controllerAs: 'vm'
    });
		$routeProvider.when('/employeesList', {
			title: 'Employees List',
			templateUrl: 'views/employeeList-sf.html',
			controller: 'EmployeesControllerSelf',
			controllerAs: 'vm'
    })
    $routeProvider.when('/reportee-profile', {
	    title: 'Profile',
	    templateUrl: 'views/employeeBasicInfo-self.html',
	    controller: 'employeeBasicInfoController',
	    controllerAs: 'vm'
    });
		$routeProvider.when('/profile', {
	    title: 'Profile',
	    templateUrl: 'views/employeeBasicInfo-self.html',
	    controller: 'employeeBasicInfoController',
	    controllerAs: 'vm'
    });
    $routeProvider.when('/employeeInfo-sf', {
	    title: 'Profile',
	    templateUrl: 'views/employeeBasicInfo-self.html',
	    controller: 'employeeBasicInfoController',
	    controllerAs: 'vm'
	  });
		$routeProvider.when('/personalinfo-sf', {
        title: 'Personal Information',
        templateUrl: 'views/personalInformation-self.html',
        controller: 'PersonalInfoController',
        controllerAs: 'vm'
    });
		$routeProvider.when('/medicalInfo-sf', {
	        title: 'Medical Information',
	        templateUrl: 'views/medicalInfo-self.html',
	        controller: 'medicalInfoController',
	        controllerAs: 'vm'
	    });
		$routeProvider.when('/officeInfo-sf', {
	        title: 'Office Information',
	        templateUrl: 'views/officeInfo-self.html',
	        controller: 'officeInfoController',
	        controllerAs: 'vm'
	    });
			$routeProvider.when('/certificationDetails-sf', {
		        title: 'Certification Details',
		        templateUrl: 'views/empCertificates-self.html',
		        controller: 'userSkillsController'
			});
			$routeProvider.when('/skillDetails-sf', {
		        title: 'Skill Details',
		        templateUrl: 'views/userSkills-self.html',
		        controller: 'userSkillsController'
			});
			$routeProvider.when('/emergencyContacts-sf', {
		        title: 'Emergency contacts',
		        templateUrl: 'views/emergencyContacts-self.html',
		        controller: 'userSkillsController'
		    });
				$routeProvider.when('/passportInfo-sf', {
		        title: 'Passport Information',
		        templateUrl: 'views/passportInfo-self.html',
		        controller: 'passportInfoController',
		        controllerAs: 'vm'
		    });
		    $routeProvider.when('/employeeAssignments-sf', {
		        title: 'Work',
		        templateUrl: 'views/assignmentsPage-self.html',
		        controller: 'AssignmentsController',
		        controllerAs: 'vm'
		    });
				$routeProvider.when('/empBankList-sf', {
		        title: 'Bank Details',
		        templateUrl: 'views/empBankList-self.html',
		        controller: 'empBankDetailsController',
		        controllerAs: 'vm'
		    });
        $routeProvider.when('/jobCodes', {
          title: 'Job Code',
          templateUrl: 'views/jobCodeList.html',
          controller: 'OsiJobCodesController',
          controllerAs: 'vm'
      });
      $routeProvider.when('/grades', {
        title: 'Grade',
        templateUrl: 'views/gradeList.html',
        controller: 'OsiGradesController',
        controllerAs: 'vm'
    });
    $routeProvider.when('/jobTitles', {
      title: 'Title',
      templateUrl: 'views/titleList.html',
      controller: 'OsiTitlesController',
      controllerAs: 'vm'
   });
    $routeProvider.when('/skills', {
      title: 'Skill',
      templateUrl: 'views/skillList.html',
      controller: 'OsiSkilsController',
      controllerAs: 'vm'
  });
  $routeProvider.when('/certifications', {
    title: 'Certification',
    templateUrl: 'views/certificationList.html',
    controller: 'OsiCertificationsController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/costCenters', {
    title: 'Practice',
    templateUrl: 'views/costCenterList.html',
    controller: 'OsiCostCenterController',
    controllerAs: 'vm'
  });
	$routeProvider.when('/employeeChart', {
    title: 'Empoyee Hierarchy Chart',
    templateUrl: 'views/employeeChart.html',
    controller: 'orgHierarchyController',
    controllerAs: 'vm'
  });
	$routeProvider.when('/employeeChartList', {
    title: 'Employee Grid View',
    templateUrl: 'views/empOrgList.html',
    controller: 'employeeOrgListCtrl',
    controllerAs: 'vm'
  });
  $routeProvider.when('/practices', {
    title: 'Department',
    templateUrl: 'views/practiceList.html',
    controller: 'OsiDepratmentController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/businessUnits', {
    title: 'Business Unit',
    templateUrl: 'views/businessUnitList.html',
    controller: 'OsiBusinessUnitsController',
    controllerAs: 'vm'
  });

  $routeProvider.when('/subpractice', {
    title: 'Sub Practice',
    templateUrl: 'views/subpracticelist.html',
    controller: 'OsiSubPracticeController',
    controllerAs: 'vm'
  });

  // Additional Doocuments
  $routeProvider.when('/additionalDocs', {
    title: 'Addtional Documents',
    templateUrl: 'views/empAdditionalDocs.html',
    controller: 'EmpAdditionalDocsController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/additionalDocs-sf', {
    title: 'Addtional Documents',
    templateUrl: 'views/empAdditionalDocs-sf.html',
    controller: 'EmpAdditionalDocsController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/overallSearch', {
    title: 'Search Employee',
    templateUrl: 'views/employeeOverallSearch.html',
    controller: 'EmployeeOverallSearchController',
    controllerAs: 'vm'
  });
  $routeProvider.when('/skillgroups', {
		title: 'Skill Groups',
		templateUrl: 'views/skillgroup.html',
		controller: 'OsiSkillGroupController',
		controllerAs: 'vm'
});
$routeProvider.when('/skilltags', {
	title: 'Skill Tags',
	templateUrl: 'views/skilltags.html',
	controller: 'OsiSkillTagsController',
	controllerAs: 'vm'
});
$routeProvider.when('/certificationgroups', {
	title: 'Certification Groups',
	templateUrl: 'views/certificationgroup.html',
	controller: 'OsiCertificationGroupController',
	controllerAs: 'vm'
});
$routeProvider.when('/certificationtags', {
title: 'Certification Tags',
templateUrl: 'views/certificationtags.html',
controller: 'OsiCertificationTagsController',
controllerAs: 'vm'
});
$routeProvider.when('/employees-office', {
  title: 'Employee List',
  templateUrl: 'views/employeeList.html',
  controller: 'EmployeesController',
  controllerAs: 'vm'
});
$routeProvider.when('/officeInfo-it', {
  title: 'Office Information',
  templateUrl: 'views/officeInfo-IT.html',
  controller: 'officeInfoController',
  controllerAs: 'vm'
});
$routeProvider.when('/officeInfo', {
  title: 'Office Information',
  templateUrl: 'views/officeInfo-IT.html',
  controller: 'officeInfoController',
  controllerAs: 'vm'
});
$routeProvider.when('/pmosearch', {
  title: 'Employee List',
  templateUrl: 'views/employeeList.html',
  controller: 'EmployeesController',
  controllerAs: 'vm'
});
$routeProvider.when('/employees-resp-global', {
  title: 'Employee List',
  templateUrl: 'views/employeeList.html',
  controller: 'EmployeesController',
  controllerAs: 'vm'
});

$routeProvider.when('/error', {
  title: 'UnAuthorized Access',
  templateUrl: 'error/error.html',
  controller: 'LoginCtrl',
  controllerAs: 'vm'
  });
  $routeProvider.otherwise({
    redirectTo: '/login'
  });

}


osi.run(run);
function run($rootScope, $location, $cookieStore, $http, AuthenticationService,
		$window, $route,$cookieStore,$sessionStorage, $localStorage, configData, $timeout) {


    if($cookieStore.get("LOGGEDINBROWSERID")!=undefined){
    if($cookieStore.get("LOGGEDINBROWSERID")!=sessionStorage.TABID)
    {
      $location.path('/login');

    }
  }
    if($cookieStore.get('globals')===undefined || $cookieStore.get('globals').userDTO===undefined){
    $location.path('/login');
  }
      //  alert("logged in browserid"+$cookieStore.get("LOGGEDINBROWSERID"));


  //alert("newtab id"+sessionStorage.TABID)

  $rootScope.$on('$routeChangeSuccess', function() {
    $window.document.title = $route.current.title;
  });
  // keep user logged in after page refresh
  $rootScope.globals = $cookieStore.get('globals') || {};

  $rootScope.$on('$locationChangeStart', function(event, next, current) {

            if($location.path()!='/login'){
             if($rootScope.globals!=null && $rootScope.globals.userDTO!=null)
		$rootScope.userName = $rootScope.globals.userDTO.firstName + ' '+$rootScope.globals.userDTO.lastName;
	var firstName = $rootScope.globals.userDTO.firstName;
  var lastName = $rootScope.globals.userDTO.lastName;
  $rootScope.currentEmployeeId = $localStorage.employeeId;
  $rootScope.loggedInEmployeeId = $rootScope.globals.userDTO.id;
  $rootScope.photo = $rootScope.globals.userDTO.photo;
		$rootScope.hlastName = lastName.substring(0,1);
		$rootScope.hfirstName = firstName.substring(0,1);
      //$rootScope.hostName = $rootScope.globals.userDTO.hostOrIp;
      $rootScope.orgCode = $rootScope.globals.userDTO.orgCode;
      if($rootScope.orgCode===configData.globalOrgCode){
        $rootScope.orgCode = $localStorage.orgCode;
      }
      $rootScope.profileAcess = $sessionStorage.profileAcess;
      $rootScope.profileAcessHR = $sessionStorage.profileAcessHR;
	  $rootScope.jobTitle = $rootScope.globals.userDTO.jobTitle;
        }else{
      $rootScope.userName = '';
      $rootScope.hostName = '';
      $rootScope.orgCode = '';
      $rootScope.employeeId = '';
      $rootScope.isLogin = false;
    }
    // redirect to login page if not logged in and trying to
    // access a restricted page
    if ($rootScope.globals.userDTO) {
      $http.defaults.headers.common['Auth_Token'] = $cookieStore
        .get('globals').userDTO.token;
    }
    // console.log($.inArray($location.path(),
    // ['/error'])+'----'+loggedIn);
		var restrictedPage = $.inArray($location.path(), [ '/login' ]) === -1;
    var loggedIn = $rootScope.globals.userDTO;
    if (restrictedPage && !loggedIn) {
       $rootScope.isLogin = false;
      $sessionStorage.menuTree = "";
      $location.path('/login');

    }
     var urlFound = false;
    console.log($location.path());
    if(($location.path() != '/login') &&($sessionStorage.userFunctions != undefined) && $location.path() != '') {
      for(var func in $sessionStorage.userFunctions) {
        if(($sessionStorage.userFunctions[func].funcValue == $location.path()) && !urlFound) {
          urlFound = true;
        }
      }
    } else{
      urlFound = true;
    }
    console.log(urlFound);
    if(!urlFound) {
   //   $timeout(function(){
        $rootScope.isLogin = false;
        $rootScope.hideFooter = true;
        $sessionStorage.menuTree = "";
        $location.path('/error');
    //  }, 1000);      
    } 
  });

 }
