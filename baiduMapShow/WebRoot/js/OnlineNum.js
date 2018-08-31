function getOnlineNum(){
	console.log("获取在线人数");
	$.ajax({//gps数据点
        type: "get",
        url: 'getOnlineNum',
        contentType:"application/x-www-form-urlencoded",  //默认的发送的编码格式
        data: '',
        
        //dataType:'application/json',//接收服务器的编码格式
        cache: true,
        async: true,
        success: function(data){
            //alert(data);
            var arraydata = eval(data);  
//            console.log(arraydata.length); 
            OnlineSrc(data);
        },
        error:function(error){
        	console.log(error);
        }
        
	});
};

function  OnlineSrc(data){
//	data = 2;
	document.getElementById("olNum").innerHTML=data;
	
}