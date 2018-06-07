package com.data.mysql_To_Redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

public class data_to_redis {
	private Jedis jedis;
	
	private data_to_redis(){
		
		jedis=new Jedis("localhost",6379);
		jedis.auth("123456");
		jedis.select(0);
		System.out.println("Redis服务器正在运行"+jedis.ping());

	}
	public static data_to_redis getInstance(){
		return new data_to_redis();
	}
	public  void to_Redis(int index,List<Map<String,Object>> list){
		Gson gson = new Gson();
		//gson.toJson(list);
		
		
		Map<String,Object> temp=null;
		String name;
		//System.out.println(list.size());
		String key_1="iov_test_"+index+":";
        for(int i=0;i<list.size();i++){
        	String key=key_1;
        	Map<String,String> map=new HashMap<String,String>();
        	temp=(Map)list.get(i);
        	Iterator iterator = temp.keySet().iterator();
        	while (iterator.hasNext()){
        	   String k = (String) iterator.next();
        	   String v= temp.get(k).toString();
        	   //System.out.println(k+":"+v);
        	   map.put(k, v);
        	        
        	  }
            name=map.get("n_id").toString();
            key+=name;
            //String json=gson.toJson(map);
            
            jedis.hmset(key,map);  
        }  
        
	}
	public static void main(String[] args){
		//data_to_redis test=new data_to_redis(0);
	}
}
