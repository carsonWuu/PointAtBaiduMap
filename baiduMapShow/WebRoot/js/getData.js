/**
 * ��ȡ��̨����
 */
function getData(map,zoom,center,bounds){
	var currentIndex={
			zoom:zoom,
			
			index_x:center.lng ,
			index_y:center.lat,
			
			bounds_leftDown_x:bounds.getSouthWest().lng,
			bounds_leftDown_y:bounds.getSouthWest().lat,
			
			bounds_rightTop_x:bounds.getNorthEast().lng,
			bounds_rightTop_y:bounds.getNorthEast().lat,
			number : num,
			
	}
	$.ajax({
        type: "get",
        url: 'getData',
        contentType:"application/x-www-form-urlencoded",  //Ĭ�ϵķ��͵ı����ʽ
        data: {data:JSON.stringify(currentIndex)},
        
        //dataType:'application/json',//���շ������ı����ʽ
        cache: true,
        async: true,
        success: function(data){
            
            var arraydata = eval(data);  
            console.log(arraydata.length);
            draw(map,arraydata);
            
        },
        error:function(error){
        	console.log(error);
        }
        
	});
} 