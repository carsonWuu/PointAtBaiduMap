/*����ˢ������
 * ʱ����Ĭ�ϣ�30s
 * ��ѡ��30s/60s
 */

var timer=30000;


var t=setInterval(change,timer);

function change(){
	//change baiduMap
	//method:draw(map,data)
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//�������󵽷���������ǰ��ͼ�㼶����ǰ���ĵ�	
	
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

