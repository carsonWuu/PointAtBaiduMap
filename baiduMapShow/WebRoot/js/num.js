/*
 * �û�ѡ��Ŀ��ӵ������
 */
$('#num1').click(function(){
	num=1000;
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//�������󵽷���������ǰ��ͼ�㼶����ǰ���ĵ�	
});
$('#num2').click(function(){
	num=1500;
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//�������󵽷���������ǰ��ͼ�㼶����ǰ���ĵ�	
	
});
$('#num3').click(function(){
	num=2000;
	getData(map,map.getZoom(),map.getCenter(),map.getBounds());//�������󵽷���������ǰ��ͼ�㼶����ǰ���ĵ�	
	
});
