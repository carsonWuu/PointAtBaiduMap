/*
 * 用户选择的可视点个数。
 */
$('#num1').click(function(){
	num=1000;
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//发送请求到服务器，当前地图层级，当前中心点	
});
$('#num2').click(function(){
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//发送请求到服务器，当前地图层级，当前中心点	
	num=1500;
});
$('#num3').click(function(){
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//发送请求到服务器，当前地图层级，当前中心点	
	num=2000;
});
$('#test').click(function(){
	alert(num);
});