package com.data.from_Mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.data.To_Redis.data_to_redis;
import com.data.mysql_Init.DataSourceBuilder;
import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;

public class mysqlReader implements Runnable{
	private int index;
	public mysqlReader(int index){
		this.index = index;	
	}
	
	@Override
	public void run() {
		try {
			//System.out.println("thread start:"+index);
			long start = System.currentTimeMillis(); //程序开始记录时间
			
			List<Map<String,Object>> list =null;
			list=(List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getDataSource(), new JdbcCallback() {
				
				@Override
				public Object doInJdbc(Connection arg0) throws SQLException, Exception {
					//按照字段t_data_time查找出最大的
					String sql="";
					
					sql="select c_imei  from iov_track_"+index+" group by c_imei";
		
					//System.out.println(sql);
					final String sqlCom=sql;
					//System.out.println(sqlCom);
					
					return DbHelper.queryForList(arg0, sqlCom);
				}
			});
				        
	        for(int i=0;i<list.size();i++){
	        	
	        	Map<String,String> map=new HashMap<String,String>();
	        	map=(Map)list.get(i);
	        	Iterator iterator = map.keySet().iterator();
	        	while (iterator.hasNext()){
	        	   String k = (String) iterator.next();
	        	   String v= map.get(k).toString();
	        	   //System.out.println(k+":"+v);
	        	   map.put(k, v);
	        	        
	        	  }
	            String c_imei=map.get("c_imei").toString();
	            List list1=connect(c_imei);
	            data_to_redis.getInstance().to_Redis(index, list1);//将list数据存入redis缓存中
	        }
	       // System.out.println(list.size());
	        long end = System.currentTimeMillis();
	       // System.out.println(index+"--------:"+(end - start) + "ms");	
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
		}
	}
	
	
	public List connect(final String c_imei) throws Exception{
		List<Map<String,Object>> list =null;
		list=(List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getDataSource(), new JdbcCallback() {
			
			@Override
			public Object doInJdbc(Connection arg0) throws SQLException, Exception {
				//按照字段t_data_time查找出最大的
				String sql="";
				
				
				sql+="select * from iov_track_"+index+" where c_imei  = "+c_imei +" order by n_id desc limit 1";
				//System.out.println(sql);
				final String sqlCom=sql;
				//System.out.println(sqlCom);
				
				return DbHelper.queryForList(arg0, sqlCom);
			}
		});
		return list;
	}
	
	
	
	
	
	
	
	public static void main(String[] args){
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println("开始查询：！");
		long start = System.currentTimeMillis(); //程序开始记录时间
       
		mysqlReader m=new mysqlReader(0);
		m.run();
		
		long end = System.currentTimeMillis();
        System.out.println("用时: " + (end - start) + "ms");
		
	}

}


