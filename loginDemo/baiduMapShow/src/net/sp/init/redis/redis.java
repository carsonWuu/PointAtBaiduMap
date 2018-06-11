package net.sp.init.redis;

import java.util.*;

import redis.clients.jedis.Jedis;
import net.sp.programme.data.mapPosition;
import net.sp.GPS.GPSUtil;

public class redis{
	public static int[] level={2000000,1000000,500000,200000,100000,50000,25000,20000,10000,5000,2000,1000,500,200,100,50,20,10,5,2};//比例尺距离(米)
	 
    
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
			String templng="",templat="";
	    	while (iterator.hasNext()){
	    	   String k = (String) iterator.next();
	    	   if(k.equals("n_clng")){
	    		   lng= map.get(k).toString();
	    		  
	    	   }
	    	   else if(k.equals("n_clat")){
	    		   lat= map.get(k).toString();
	    	   }
	    	   else if(k.equals("n_lng")){
	    		   templng= map.get(k).toString();
	    		  
	    	   }
	    	   else if(k.equals("n_lat")){
	    		   templat= map.get(k).toString();
	    	   }
	    	   
//	    	   if(lng==null||lat==null||lng==""||lat==""){//需由WGS84坐标系转换为BD09坐标
//	    		   
//	    		   break;     
//	    	   }
	    	}
	    	double lng_a=Double.parseDouble(templng),lat_a=Double.parseDouble(templat);
	    	if(lng==null||lat==null||lng==""||lat==""){//需由WGS84坐标系转换为BD09坐标
	    		lng=templng;
	    		lat=templat;
	    		
	    		
	    		lng_a=Double.parseDouble(lng);
	    		lat_a=Double.parseDouble(lat);
	    		
	    		double temp[]=GPSUtil.gps84_To_bd09(lat_a, lng_a);
	    		lat_a=temp[0];
	    		lng_a=temp[1];
	    	}
			
	    	
			if(checkData(m,lng_a,lat_a))list.add(map);
		}
		return list; 
	}
	public static boolean checkData(mapPosition m,double a,double b){
		//center Point
		double lng_a=m.index_x,lat_a=m.index_y;//经纬度
		
		double ld_lng=m.bounds_leftDown_x,ld_lat=m.bounds_leftDown_y;
		double rt_lng=m.bounds_rightTop_x,rt_lat=m.bounds_rightTop_y;
		
		if((a>ld_lng)&&(a<rt_lng)&&(b>ld_lat)&&(b<rt_lat))return true;
//		int zoom=m.zoom;//level
//		int num=6;
//		double miles=num*level[zoom-3];
		
		//checked Point
		
        
//		if(getDistanceFromTwoPoints(lat_a, lng_a, a, b)<miles)return true;//距离判断
		
		/*屏幕内对角线经纬度直接进行比较。
		 * 
		 */
		
		
		
		return false;
	}
	
	
	
	
	public static void main(String []args){
		for(int i=0;i<level.length;i++){
			System.out.print(level[level.length-1-i]+",");
		}
		System.out.println(redis.init_from_Redis().size());
	}
}