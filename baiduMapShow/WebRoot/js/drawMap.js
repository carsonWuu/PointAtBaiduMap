/**
 * 地图
 */
function draw(map,data){
	
	map.clearOverlays();
	var array = new Array();
	
	var max=num<data.length?num:data.length;//展示个数1000/1500/2000
	
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
	/** 标注点形状和大小
	 * @size  
	 * BMAP_POINT_SIZE_TINY	定义点的尺寸为超小，宽高为2px*2px
	 * BMAP_POINT_SIZE_SMALLER	定义点的尺寸为很小，宽高为4px*4px
	 * BMAP_POINT_SIZE_SMALL	定义点的尺寸为小，宽高为8px*8px
	 * BMAP_POINT_SIZE_NORMAL	定义点的尺寸为正常，宽高为10px*10px，为海量点默认尺寸
	 * BMAP_POINT_SIZE_BIG	定义点的尺寸为大，宽高为16px*16px
	 * BMAP_POINT_SIZE_BIGGER	定义点的尺寸为很大，宽高为20px*20px
	 * BMAP_POINT_SIZE_HUGE	定义点的尺寸为超大，宽高为30px*30px
	 * @shape 
	 * BMAP_POINT_SHAPE_CIRCLE	圆形，为默认形状
	 * BMAP_POINT_SHAPE_STAR	星形
	 * BMAP_POINT_SHAPE_SQUARE	方形
	 * BMAP_POINT_SHAPE_RHOMBUS	菱形
	 * BMAP_POINT_SHAPE_WATERDROP	水滴状，该类型无size和color属性
	 */
	 var options = {
		
            size: BMAP_POINT_SIZE_SMALL,
            shape: 2,// 1 星星；2 桃心；3 圆形；4 正方形；
            color: 'red'
        }
	 var pointCollection=new BMap.PointCollection(array,options);
	 
	
	 pointCollection.addEventListener('click', function(e) {// 
//		console.log(e);
		var poi = new BMap.Point(e.point.lng, e.point.lat);
//		var marker = new BMap.Marker(poi);
		/*var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/fox.gif", new BMap.Size(50,25));

*/		
		var opts = {
			width: 250, // 信息窗口宽度
			height: 70, // 信息窗口高度
			title:"hr", // 信息窗口标题
			enableMessage: false,// 设置允许信息窗发送短息
		}
		var Content = "<div>"//自定义的展示内容
			+ "<div><span style='margin:0 0 5px 0;padding:0.2em 0;color:#000'>"+e.point.lng+"-"+e.point.lat+"</span></div>"
			+ "</div>";
		var infoWindow = new BMap.InfoWindow(Content);  // 创建信息窗口对象 
//		console.log(infoWindow);

		map.openInfoWindow(infoWindow,poi); //开启信息窗口

		
	 }); 
	 map.addOverlay(pointCollection); //添加海量点
	 
 
	 
}
