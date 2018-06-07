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
#container{height:95%}  
</style>  

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BMgnVpFhGSH7GE8l7qnWhESkeCr12n9v"></script>

</head>  
 
<body>  
<div id="container"></div> 
<div id="button">
<button id='t1'>30s</button> 
<button id='t2'>60s</button> 
<button id='t3'>暂停</button>

<button id='num1'>1000</button> 
<button id='num2'>1500</button> 
<button id='num3'>2000</button>  


<button id='test'>test</button>  
</div>
</body>

 

<script type="text/javascript" src="./javascript/jquery-3.2.1.js"></script>
<script type="text/javascript" src="./js/mapMain.js"></script>
<script type="text/javascript" src="./js/getData.js"></script>
<script type="text/javascript" src="./js/drawMap.js"></script>
<script type="text/javascript" src="./js/Interval.js"></script>
<script type="text/javascript" src="./js/num.js"></script>
<script type="text/javascript" src="./javascript/baiduAk.js"></script>
<script>
$(document).ready(function(){
	//show 500 Point
	window.num=500;
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