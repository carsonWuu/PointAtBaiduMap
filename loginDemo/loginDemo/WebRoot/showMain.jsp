<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE html>  
<html>
<head>  
<!--meta name="viewport" content="initial-scale=1.0, user-scalable=no" /-->  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>百度地图展示</title>  
<style type="text/css">  
html{height:100%}  
body{width:100%;height:100%;margin:0px auto;padding:0px}  
#container{height:100%}  
</style>  
<script type="text/javascript" src="./js/getData.js"></script>
<script type="text/javascript" src="./js/drawMap.js"></script>
<script type="text/javascript" src="./javascript/jquery-3.2.1.js"></script>
<script type="text/javascript" src="./javascript/baiduAk.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BMgnVpFhGSH7GE8l7qnWhESkeCr12n9v"></script>

</head>  
 
<body>  
<div id="container"></div> 
<div id="log"></div> 
<script type="text/javascript"> 
	var map = new BMap.Map("container");
	// 地图
	
	var point = new BMap.Point(116.404, 39.915);
	// 中心店
	map.centerAndZoom(point, 5);
	// 层级（1-19,,第一层最大）
	
	map.enableScrollWheelZoom(true); 
	//缩放
	
	//map.addControl(new BMap.NavigationControl());
	
	var opts = {type: BMAP_NAVIGATION_CONTROL_SMALL}    
	map.addControl(new BMap.NavigationControl(opts));
	var mapStyle={  style : "mapbox" }  
	map.setMapStyle(mapStyle);
	
	var myStyleJson=[  
	{  
	    "featureType": "road",  
	    "elementType": "geometry.stroke",  
	    "stylers": {  
	        "color": "#ff0000" ,
	        "visibility":"off"
	         
	    }  
	}];
	map.setMapStyle({
		    	   styleJson:[
		    	         
				          {
				                    "featureType": "road",
				                    "elementType": "all",
				                    "stylers": {
				                              "color": "#ffffff",
				                              "visibility": "on"
				                    }
				          }
					]
				}); 
	
	map.addEventListener("zoomend", function(){  //缩放后的级别  
		var bs = map.getBounds();   //获取可视区域
		getData(map,this.getZoom(),map.getCenter(),map.getBounds());//发送请求到服务器，当前地图层级，当前中心点
		
		
	    console.log("地图缩放至：" + this.getZoom() + "级");    
	    var center = map.getCenter();    
	    console.log("缩放后地图中心点变更为：" + center.lng + ", " + center.lat);   
	});
	map.addEventListener("dragend", function(){ //拖拽事件中心点为   
	    var center = map.getCenter();    
	    console.log("地图中心点变更为：" + center.lng + ", " + center.lat);    
	});
	var local = new BMap.LocalSearch(map, {  //*****城市检索    
	    renderOptions:{map: map}      
	});      
	//local.search("天安门");
	
	local = new BMap.LocalSearch(map,   //圆形区域检索
              { renderOptions:{map: map, autoViewport: true}});      
	//local.searchNearby("桂林米粉", "玉林"); 
	
	local = new BMap.LocalSearch(map,   //矩形区域检索
              { renderOptions:{map: map}});      
	//local.searchInBounds("银行", map.getBounds()); 
	
	local = new BMap.LocalSearch("北京市",  //配置搜索：显示8个POI 
            {renderOptions: {map: map,autoViewport: true},pageCapacity: 8});      
	//local.search("天安门");
	
	local = new BMap.LocalSearch(map,   //结果面板
            {renderOptions: {map: map,panel: "results"}});      
	//local.search("中关村");
	
	var options = {  //******数据接口 （*代表检索）   
    onSearchComplete: function(results){      
        if (local.getStatus() == BMAP_STATUS_SUCCESS){      
            // 判断状态是否正确      
            var s = [];      
            for (var i = 0; i < results.getCurrentNumPois(); i ++){      
                s.push(results.getPoi(i).title + ", " + results.getPoi(i).address);      
            }      
            document.getElementById("log").innerHTML = s.join("<br>");      
	        }      
	    }      
	 };      
	local = new BMap.LocalSearch(map, options);      
	//local.search("公园");
	
	
	var bs = map.getBounds();   //获取可视区域
	var bssw = bs.getSouthWest();   //可视区域左下角
	var bsne = bs.getNorthEast();   //可视区域右上角
	console.log("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
	

</script>  
</body>
<script>
$(document).ready(function(){
	$.ajax({
            type: "post",
            url: 'getMap',
            contentType:"application/x-www-form-urlencoded",  //默认的发送的编码格式
            data: '',
            
            //dataType:'application/json',//接收服务器的编码格式
            cache: true,
            async: true,
            success: function(data){
                //alert(data);
                var arraydata = eval(data);  
                console.log(arraydata.length); 
                draw(map,arraydata); 
            },
            error:function(error){
            	console.log(error);
            }
            
	});
})
</script>  
</html>