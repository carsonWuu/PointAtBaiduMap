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
//            console.log(data);
//            var arraydata = eval(data); 
            var obj=JSON.parse(data);
//            console.log(arraydata.length); 
            OnlineSrc(obj);
        },
        error:function(error){
        	console.log(error);
        }
        
	});
};

function  OnlineSrc(data){
//	data = 2;
	document.getElementById("olNum").innerHTML=data.olNum;
	document.getElementById("Num").innerHTML=data.Num;
	
}