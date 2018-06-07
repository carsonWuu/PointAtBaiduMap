/*
 * ʹ�û�õ�data��ͼ��
 */
function draw(map,data){
	
	map.clearOverlays();
	var array = new Array();
	
	var max=num<data.length?num:data.length;//�����û�ѡ��500/1000/1500/2000
	
	for(var i=0;i<max;i++){
		var point;
		//console.log(data[i]);
		if(!data[i].n_clng||!data[i].n_clat){
			point = new BMap.Point(data[i].n_lng,data[i].n_lat);
			point.n_lng=data[i].n_lng;
			point.n_lat=data[i].n_lat;
		}
		
		else{
			point = new BMap.Point(data[i].n_clng,data[i].n_clat);
			point.n_clng=data[i].n_clng;
			point.n_clat=data[i].n_clat;
		}
		point.c_model=data[i].c_model;
		array[i]=point;        
		
	} 
	
	 var options = {
            size: BMAP_POINT_SIZE_SMALL,
            shape: BMAP_POINT_SHAPE_WATERDROP,
            color: '#d340c3'
        }
	 var pointCollection=new BMap.PointCollection(array,options);


	 pointCollection.addEventListener('click', function(e) {// ��������¼�
		console.log(e);
		var point1 = new BMap.Point(e.point.lng, e.point.lat);
		var opts = {
			width: 80,     // ��Ϣ���ڿ��
			height: 60,     // ��Ϣ���ڸ߶�
			title: "Info", // ��Ϣ���ڱ���
			enableMessage: false//����������Ϣ�����Ͷ�Ϣ
		};
		var infowindow = new BMap.InfoWindow(e.point.lng + ',' + e.point.lat+' \n '+e.point.c_model, opts);
		map.openInfoWindow(infowindow, point1);
	 }); 
	 
	 
	 
	 
	 map.addOverlay(pointCollection);
	 
}