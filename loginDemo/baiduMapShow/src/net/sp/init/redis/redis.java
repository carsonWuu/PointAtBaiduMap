package net.sp.init.redis;

import java.util.*;

import redis.clients.jedis.Jedis;
import net.sp.programme.data.mapPosition;

public class redis{
	public static int[] level={2000000,1000000,500000,200000,100000,50000,25000,20000,10000,5000,2000,1000,500,200,100,50,20,10,5,2};//比例尺距离(米)
	private static final Double PI = Math.PI;  
	  
    private static final Double PK = 180 / PI;  
    
	public redis(){
		super();
	}
	public static List init_from_Redis(){
		Jedis jedis=new Jedis("localhost",6379);
		jedis.auth("123456");
		jedis.select(0);
		Set keys=jedis.keys("*");
		Iterator it=keys.iterator();
		Map map=new HashMap<String,String>();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		while(it.hasNext()){
			String key=it.next().toString();
			map=jedis.hgetAll(key);
			list.add(map);
		}
		return list; 
	}
	
	public static List data_from_Redis(mapPosition m){
		Jedis jedis=new Jedis("localhost",6379);
		jedis.auth("123456");
		jedis.select(0);
		Set keys=jedis.keys("*");
		Iterator it=keys.iterator();
		Map map=new HashMap<String,String>();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		while(it.hasNext()){
			String key=it.next().toString();
			map=jedis.hgetAll(key);
			Iterator iterator = map.keySet().iterator();
			String lng="",lat="";
	    	while (iterator.hasNext()){
	    	   String k = (String) iterator.next();
	    	   if(k.equals("n_clng")){
	    		   lng= map.get(k).toString();
	    	   }
	    	   if(k.equals("n_clat")){
	    		   lat= map.get(k).toString();
	    	   }
	    	   
	    	   if(lng!=""&&lat!="")break;     
	    	}
			
	    	double lng_a=Double.parseDouble(lng),lat_a=Double.parseDouble(lat);
			if(checkData(m,lat_a,lng_a))list.add(map);
		}
		return list; 
	}
	public static boolean checkData(mapPosition m,double a,double b){
		//center Point
		double lng_a=m.index_x,lat_a=m.index_y;//经纬度
		int zoom=m.zoom;//level
		int num=50;
		double miles=num*level[zoom];
		
		//checked Point
		
        
		if(getDistanceFromTwoPoints(lat_a, lng_a, a, b)<miles)return true;
		return false;
	}
	
	public static double getDistanceFromTwoPoints(double lat_a, double lng_a, double lat_b, double lng_b) {  
        double t1 = Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);  
        double t2 = Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);  
        double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);  
  
        double tt = Math.acos(t1 + t2 + t3);  
  
        System.out.println("两点间的距离：" + 6366000 * tt + " 米");  
        return 6366000 * tt;  
    }
	
	public static void main(String []args){
		for(int i=0;i<level.length;i++){
			System.out.print(level[level.length-1-i]+",");
		}
		System.out.println(redis.init_from_Redis().size());
	}
}