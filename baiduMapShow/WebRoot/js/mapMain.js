/*
 * 
 */

    var map = new BMap.Map("container");
	// 
	
	var point = new BMap.Point(119.404, 39.915);
	//中心点
	map.centerAndZoom(point, 5);
	// 初始层级
	
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认启用
	
	
	
//	map.addControl(new BMap.NavigationControl());
	
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
	//alert('!!')
	
	map.addEventListener("zoomend", function(){  //缩放
		
		if(zoomTimer){
			clearTimeout(zoomTimer);
		}
		zoomTimer=setTimeout(function(){
			getData(map,map.getZoom(),map.getCenter(),map.getBounds());//当前层级数据	
			console.log("zoomed:" + map.getZoom() + "");    
			var center = map.getCenter();    
			console.log("center:" + center.lng + ", " + center.lat);  
		},1000);
	});
	
	map.addEventListener("dragend", function(){ //平移
		
		
		if(dragendTimer){
			clearTimeout(dragendTimer);
		}
		dragendTimer=setTimeout(function(){
			//console.log("#");
			getData(map,map.getZoom(),map.getCenter(),map.getBounds());//当前层级数据	
			console.log("dragend:" + map.getZoom() + "");    
			var center = map.getCenter();    
			console.log("center:" + center.lng + ", " + center.lat);  
		},1000);
	});
	
//	changeMapStyle('midnight')
//	sel.value = 'midnight';

	function changeMapStyle(style){
		map.setMapStyle({style:style});
//		$('#desc').html(mapstyles[style].desc);
	}
