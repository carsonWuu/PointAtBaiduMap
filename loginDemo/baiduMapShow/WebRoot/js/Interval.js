/*周期刷新数据
 * 时间间隔默认：30s
 * 可选：30s/60s
 */

var timer=30000;


var t=setInterval(change,timer);

function change(){
	//change baiduMap
	//method:draw(map,data)
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//发送请求到服务器，当前地图层级，当前中心点	
	
	$('#t1').click(function(){
		timer=30000;
		clearInterval(t);
		t=setInterval(change,timer);
	});
	$('#t2').click(function(){
		timer=60000;
		clearInterval(t);
		t=setInterval(change,timer);
	});
	$('#t3').click(function(){
		timer=30000;
		clearInterval(t);
		//t=setInterval(change,timer);
	});
	
}

