/*百度地图生成实现及初始化。
 * 
 */

    var map = new BMap.Map("container");
	// 地图
	
	var point = new BMap.Point(116.404, 39.915);
	// 中心点
	map.centerAndZoom(point, 5);
	// 层级（1-19,,第一层最大）
	
	map.enableScrollWheelZoom(true); 
	//缩放
	
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
	
	map.addEventListener("zoomend", function(){  //缩放后的级别  
		getData(map,this.getZoom(),map.getCenter(),map.getBounds());//发送请求到服务器，当前地图层级，当前中心点	
	    console.log("zoomed:" + this.getZoom() + "");    
	    var center = map.getCenter();    
	    console.log("center:" + center.lng + ", " + center.lat);   
	});
	map.addEventListener("dragend", function(){ //拖拽事件中心点为  
		getData(map,this.getZoom(),map.getCenter(),map.getBounds());//发送请求到服务器，当前地图层级，当前中心点	
	    console.log("dragend:" + this.getZoom() + "");    
	    var center = map.getCenter();    
	    console.log("center:" + center.lng + ", " + center.lat);   
	});
