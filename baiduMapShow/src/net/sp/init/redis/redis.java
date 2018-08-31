package net.sp.init.redis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import redis.clients.jedis.Jedis;
import net.sp.Position.mapPosition;
import net.sp.GPS.GPSUtil;

/**
 * redis数据查询类
 */
public class redis{
	public Jedis jedis;
	
    private static redis instance = null;
	public redis(){
		
		jedis=new Jedis("localhost",6379);
		jedis.auth("123456");
		jedis.select(0);
		
	}
	
	public static redis getInstance(){
		if(instance == null){
			instance = new redis();
		}
		return instance;
	}
	/**
	 * 初始化查询
	 */
	public  List init_from_Redis(){
		
		Set keys=jedis.keys("*");
		Iterator it=keys.iterator();
		Map map=new HashMap<String,String>();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		int i=0;//ֻͳ��1000��
		while(it.hasNext()){
			if(i==1000)break;
			i++;
			String key=it.next().toString();
			map=jedis.hgetAll(key);
			list.add(map);
		}
		return list; 
	}
	
	/**
	 * 用户操作后查询
	 * @param mapPosition（当前地图信息）
	 * 
	 */
	public  List data_from_Redis(mapPosition m){
		
		Set keys=jedis.keys("*");
		Iterator it=keys.iterator();
		Map map=new HashMap<String,String>();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		int i=0;
		while(it.hasNext()){
			if(i==m.number)break;
			
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
	    	   

	    	}
	    	double lng_a=Double.parseDouble(templng),lat_a=Double.parseDouble(templat);
	    	if(lng==null||lat==null||lng==""||lat==""){//需要进行转换
	    		lng=templng;
	    		lat=templat;
	    		
	    		
	    		lng_a=Double.parseDouble(lng);
	    		lat_a=Double.parseDouble(lat);
	    		
	    		double temp[]=GPSUtil.gps84_To_bd09(lat_a, lng_a);//ת��
	    		lat_a=temp[0];
	    		lng_a=temp[1];
	    	}
			
	    	
			if(checkData(m,lng_a,lat_a)){//该gps在界面内
				i++;
				list.add(map);
			}
		}
		return list; 
	}
	
	/**判断该gps点在可视界面内
	 * 
	 */
	public static boolean checkData(mapPosition m,double a,double b){
		
		double lng_a=m.index_x,lat_a=m.index_y;//��γ��
		
		double ld_lng=m.bounds_leftDown_x,ld_lat=m.bounds_leftDown_y;
		double rt_lng=m.bounds_rightTop_x,rt_lat=m.bounds_rightTop_y;
		
		if((a>ld_lng)&&(a<rt_lng)&&(b>ld_lat)&&(b<rt_lat))return true;

		
		
		
		return false;
	}
	
	
	public int onlineNum_from_Redis(){
		Set keys=jedis.keys("*");
		Iterator it=keys.iterator();
		Map map=new HashMap<String,String>();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		int i=0;//
		Date currentTime = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		while(it.hasNext()){
			
			
			String key=it.next().toString();
			String dataTime=jedis.hgetAll(key).get("t_data_time");
			try {
				Date t = df.parse(dataTime);
				int d= 300*1000;
				if(Math.abs(t.getTime()-currentTime.getTime())<=d) {
					i++;//在线
					System.out.println(t.getTime());
					System.out.println(currentTime.getTime());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			System.out.println(dataTime);
//			list.add(map);
		}
		return i; 
	
	}
	
	public static void main(String []args){
		for(int i =0;i<2;i++){
			redis re= redis.getInstance();
			re.init_from_Redis();
		}
//		System.out.println(redis.init_from_Redis().size());
	}
}