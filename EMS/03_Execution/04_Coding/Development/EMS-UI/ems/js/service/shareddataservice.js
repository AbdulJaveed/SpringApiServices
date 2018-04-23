/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';
    angular.module('myApp.SharedDataService', []).factory('SharedDataService', SharedDataService);
    
 function SharedDataService(){
     var service = {};
     var sd = this;
     sd.data = {};
     sd.userUpdate=true;
     sd.form=true;
     sd.info = {};
     sd.userinfo = {};
     sd.responsibility = {};
     sd.functionexcl = {};
     sd.availablefunctionids = [];
     sd.excludedfunctions = {};
     sd.availableFuncFlag=false;
     sd.editPR=true;
     sd.categoryApprovals = {
        excludedOptionsPR : []
     };
     
     service.setData=setData;
     service.getData=getData;
     service.setInfo=setInfo;
     service.getInfo=getInfo;
     service.setUserInfo=setUserInfo;
     service.getUserInfo=getUserInfo;
     service.getEditPr=getEditPr;
     service.setEditPr=setEditPr;
     service.setUpdate=setUpdate;
     service.getUpdate=getUpdate;
     
     return service;
     
     function setUpdate(userUpdate)
     {
    	 sd.userUpdate = userUpdate;
     }
     
     
     function getUpdate(userUpdate)
     {
    	 return sd.userUpdate;
     }
     
     function setData(data)
     {
         sd.data =data;
     }
     
     function getData()
     {
         return sd.data;
     }
     
     function setInfo(key,value)
     {
         sd.info[key] = value;
     }
     
     function getInfo(key)
     {
         return sd.info[key];
     }
     
     function setUserInfo(data)
     {
        sd.userinfo = data;
     }
     
     function getUserInfo()
     {
         return sd.userinfo;
     }
     
     function setEditPr(data)
     {
         sd.editPR=data;
     }
     function getEditPr()
     {
         return sd.editPR;
     }
 }

})();