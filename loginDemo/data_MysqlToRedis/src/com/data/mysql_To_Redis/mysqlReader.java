package com.data.mysql_To_Redis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			
			long start = System.currentTimeMillis(); //程序开始记录时间
	       
			
			
			
			List<Map<String,Object>> list =null;
			list=(List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getDataSource(), new JdbcCallback() {
				
				@Override
				public Object doInJdbc(Connection arg0) throws SQLException, Exception {
					//按照字段t_data_time查找出最大的
					final String sqlCom="select * ,max(t_data_time)as t_use from iov_track_"+index+" group by c_imei";
					//System.out.println(sqlCom);
					
					return DbHelper.queryForList(arg0, sqlCom);
				}
			});
			// copy to redis
			long end = System.currentTimeMillis();
	        System.out.println(index+"--------:"+(end - start) + "ms");
			//System.out.println(index+":读取完毕！");
//			data_to_redis toRedis=new data_to_redis(index);
//			
//			toRedis.to_Redis(list);
			
			data_to_redis.getInstance().to_Redis(index, list);//将list数据存入redis缓存中
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
		}
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
