function draw(map,data){
	var array = new Array();
	
	for(var i=0;i<data.length;i++){
		
		
		var point = new BMap.Point(data[i].n_clng,data[i].n_clat);
		//var marker = new BMap.Marker(point1);
		//marker.setAnimation(BMAP_ANIMATION_DROP);
		/*
		BMAP_ANIMATION_DROP		×¹Âä¶¯»­
		BMAP_ANIMATION_BOUNCE	Ìø¶¯¶¯»­
		*/
		array[i]=point;        
		//map.addOverlay(marker); 
	} 
	//var marker = new BMap.Marker(point);  
	//console.log('1')
	//map.addOverlay(marker);
	 var options = {
            size: BMAP_POINT_SIZE_SMALL,
            shape: BMAP_POINT_SHAPE_WATERDROP,
            color: '#d340c3'
        }
	map.addOverlay(new BMap.PointCollection(array,options));
}