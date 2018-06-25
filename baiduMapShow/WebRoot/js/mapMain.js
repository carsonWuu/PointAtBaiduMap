/*�ٶȵ�ͼ����ʵ�ּ���ʼ����
 * 
 */

    var map = new BMap.Map("container");
	// ��ͼ
	
	var point = new BMap.Point(116.404, 39.915);
	// ���ĵ�
	map.centerAndZoom(point, 5);
	// �㼶��1-19,,��һ�����
	
	map.enableScrollWheelZoom(true); 
	//����
	
	//map.addControl(new BMap.NavigationControl());
	
	var opts = {type: BMAP_NAVIGATION_CONTROL_SMALL}    
	map.addControl(new BMap.NavigationControl(opts));
	var mapStyle={  style : "mapbox" }  
	map.setMapStyle(mapStyle);
	
	map.setMapStyle({
		    	   styleJson:[
		    	         
				          {
				                    "featureType": "road",
				                    "elementType": "all",
				                    "stylers": {
				                              "color": "#ffffff",
				                              "visibility": "on"
				                    }
				          }
					]
	}); 
	
	var zoomTimer=null;
	var dragendTimer=null;
	
	map.addEventListener("zoomend", function(){  //���ź�ļ���  
		
		if(zoomTimer){
			clearTimeout(zoomTimer);
		}
		zoomTimer=setTimeout(function(){
			getData(map,map.getZoom(),map.getCenter(),map.getBounds());//�������󵽷���������ǰ��ͼ�㼶����ǰ���ĵ�	
			console.log("zoomed:" + map.getZoom() + "");    
			var center = map.getCenter();    
			console.log("center:" + center.lng + ", " + center.lat);  
		},1000);
	});
	
	map.addEventListener("dragend", function(){ //��ק�¼����ĵ�Ϊ 
		
		
		if(dragendTimer){
			clearTimeout(dragendTimer);
		}
		dragendTimer=setTimeout(function(){
			//console.log("#");
			getData(map,map.getZoom(),map.getCenter(),map.getBounds());//�������󵽷���������ǰ��ͼ�㼶����ǰ���ĵ�	
			console.log("dragend:" + map.getZoom() + "");    
			var center = map.getCenter();    
			console.log("center:" + center.lng + ", " + center.lat);  
		},1000);
	});

