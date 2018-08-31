/*自动刷新时间间隔
 * 默认30s
 * 可选30s/60s
 */

var timer=30000;


var t=setInterval(change,timer);

function change(){
	//change baiduMap
	//method:draw(map,data)
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//获取当前设备GPS位置
	getOnlineNum();//获得当前在线数
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

