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

<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="./css/bootstrap.min.css">
  
<title>百度地图展示</title>  
<style type="text/css">
*{
	margin:0;
	padding:0;
}  
html{height:100%}  
body{
	width:100%;
	height:100%;
	margin:auto;
	
	position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
}  
#container{
	position: relative;
	width:100%;
	z-index:1;
	height:100%;
	font-family:"微软雅黑";
	
} 
#button{
	
	bottom:0;
	position: fixed;
	z-index:2;
	
} 
#onlineNum{
	bottom:0;
	right:10px;
	position: fixed;
	z-index:2;
}
</style>  
<script type="text/javascript" src="./javascript/jquery-3.2.1.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BMgnVpFhGSH7GE8l7qnWhESkeCr12n9v"></script>
<script type="text/javascript" src="./javascript/baiduAk.js"></script>
</head>  
 
<body>  

<div id="button" class="btn-group">
	<button class="btn btn-default" id='t1'>30s</button> 
	<button class="btn btn-default" id='t2'>60s</button> 
	<button class="btn btn-default" id='t3'>暂停</button>
	
	<button class="btn btn-default" id='num1'>1000</button> 
	<button class="btn btn-default" id='num2'>1500</button> 
	<button class="btn btn-default" id='num3'>2000</button>  
<!-- button id='test'>test</button>   -->
</div>
<div >
	<p id="onlineNum">
		<b class="onlineNum">在线数：</b>
		<b id="olNum">0000</b>
		
		<b class="onlineNum">设备数：</b>
		<b id="Num">0000</b>
	</p>
	


</div>
<div id="container"></div> 
</body>

 


<script src="./javascript/bootstrap.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="./js/mapMain.js"></script>
<script type="text/javascript" src="./js/getData.js"></script>
<script type="text/javascript" src="./js/drawMap.js"></script>
<script type="text/javascript" src="./js/Interval.js"></script>
<script type="text/javascript" src="./js/num.js"></script>
<script type="text/javascript" src="./js/OnlineNum.js"></script>

<script>
function loadJScript() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=BMgnVpFhGSH7GE8l7qnWhESkeCr12n9v&callback=init";
	document.body.appendChild(script);
}
function init(){
	//show 500 Point
	window.num=900;
	var reqData = {
		number : num,	
	}
	$.ajax({//gps数据点
            type: "post",
            url: 'getMap',
            contentType:"application/x-www-form-urlencoded",  //默认的发送的编码格式
            data: {data:JSON.stringify(reqData)},
            
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
	
	getOnlineNum();
};


window.onload = loadJScript;  //异步加载地图
</script>  
</html>