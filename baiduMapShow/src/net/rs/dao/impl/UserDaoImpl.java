package net.rs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.rs.bean.Position.mapPosition;
import net.rs.bean.user.User;
import net.rs.init.mysql.DataSourceBuilder;
import net.rs.util.redis.Redis;








import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;


/**
 * 从redis数据库查询数据
 */
public class UserDaoImpl{
	
	/**
	 * 登陆验证
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
	
	/**
	 * 用户刚登陆初始化查询数据
	 */
	public  static List initOutputData(int number) throws Exception{
		long startTime = System.currentTimeMillis(); 
		List list=new Redis().initData(number);
		long endTime   = System.currentTimeMillis(); 
		long TotalTime = endTime - startTime;       
		System.out.println("初始化(ms):"+TotalTime);
		return list;
		
	}
	
	/**
	 * 用户操作后查询数据
	 */
	public  static List outputData(mapPosition map) throws Exception{
		long startTime = System.currentTimeMillis(); 
		//
		
		double lon=map.index_x,lat=map.index_y;//经纬度
		
		List list=new Redis().getData(map);
					
		long endTime   = System.currentTimeMillis(); 
		long TotalTime = endTime - startTime;       
		System.out.println("耗时(ms):"+TotalTime);
		return list;
			
	}
	
}
