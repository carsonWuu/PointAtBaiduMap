<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE html>  
<html>
<head>  
<!--meta name="viewport" content="initial-scale=1.0, user-scalable=no" /-->  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>Hello, World</title>  
<style type="text/css">  
html{height:100%}  
body{width:100%;height:100%;margin:0px auto;padding:0px}  
#container{height:100%}  
</style>  
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BMgnVpFhGSH7GE8l7qnWhESkeCr12n9v"></script>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
</head>  
 
<body>  
<div id="container"></div> 
<script type="text/javascript"> 
	var map = new BMap.Map("container");
	// 地图
	
	var point = new BMap.Point(116.404, 39.915);
	// 中心店
	map.centerAndZoom(point, 13);
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
	        "color": "#ff0000"  
	    }  
	}];
	//map.setMapStyle({styleJson: myStyleJson }); 
	
	for(var i=0;i<10;i++){
		var j=i/1000;
		var indexX=116.404+j;
		var indexY=39.915+j;
		var point1 = new BMap.Point(indexX, indexY);
		var marker = new BMap.Marker(point1);        // åå»ºæ æ³¨ 
		map.addOverlay(marker); 
	} 
	//var marker = new BMap.Marker(point);  
	//console.log('1')
	map.addOverlay(marker);
	
</script>  
</body>
<script>
$(document).ready(function(){
	$.ajax({
            type: "post",
            url: 'getModelData',
            contentType:"application/x-www-form-urlencoded",  //默认的发送的编码格式
            data: {id:'id1'},
            
            //dataType:'application/json',//接收服务器的编码格式
            cache: true,
            async: true,
            success: function(data){
                //alert(data);
                var arraydata = eval(data);  
                console.log(arraydata.length);  
            },
            error:function(error){
            	console.log(error);
            }
            
});
})
</script>  
</html>