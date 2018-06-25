package net.sp.programme.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sp.init.mysql.DataSourceBuilder;
import net.sp.init.redis.redis;

import net.sp.programme.entity.User;

import net.sp.Position.mapPosition;



import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;



public class UserDaoImpl{
	
	/*
	 * un为用户名，pw密码，判断用户是否登录
	 */
	public  static List checkLogin(final String un,final String pw) throws Exception{
			
			List<Map<String,Object>> list = (List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getDataSource(), new JdbcCallback() {
				
						@Override
						public Object doInJdbc(Connection arg0) throws SQLException, Exception {
							
							final String sqlCom="select * from user_login where username='"+un+"' and password='"+pw+"' ";
							return DbHelper.queryForList(arg0, sqlCom);
						}
					});
			
			return list;
			
	}
	
	/*
	 * 页面打开时数据的初始展示。
	 */
	public  static List initOutputData() throws Exception{
		System.out.println("初始化查询！");
		long startTime = System.currentTimeMillis(); //程序开始记录时间
		List list=redis.init_from_Redis();
		long endTime   = System.currentTimeMillis(); //程序结束记录时间
		long TotalTime = endTime - startTime;       //总消耗时间
		System.out.println("初始化查询时间(ms):"+TotalTime);
		return list;
		
	}
	
	/*
	 * 取出在用户界面内可以展示的坐标
	 */
	public  static List outputData(mapPosition map) throws Exception{
		long startTime = System.currentTimeMillis(); //程序开始记录时间
		//。。。 。。。
		
		double lon=map.index_x,lat=map.index_y;//经纬度
		int zoom=map.zoom;//level
		List list=redis.data_from_Redis(map);
				
			
//				final String sqlCom="SELECT n_clng,n_clat,c_model,(6371 * acos (cos ( radians("+lon+") )* cos( radians( n_clat ) )* cos( radians( n_clng ) - radians("+lat+") )+ sin ( radians("+lon+") )    * sin( radians( n_clat ) )    )     ) AS distance    FROM iov_track_0  HAVING distance < "+distance+"  ORDER BY distance;";
//				System.out.println(sqlCom);						
				
		
			
		long endTime   = System.currentTimeMillis(); //程序结束记录时间
		long TotalTime = endTime - startTime;       //总消耗时间
		System.out.println("用户查询时间(ms):"+TotalTime);
		return list;
			
	}
	
}
