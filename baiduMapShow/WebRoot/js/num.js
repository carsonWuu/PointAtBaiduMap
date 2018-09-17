
$('#num1').click(function(){
	num=100;
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//1000
});
$('#num2').click(function(){
	num=1500;
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//1500	
	
});
$('#num3').click(function(){
	num=2000;
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//2000	
	
});
