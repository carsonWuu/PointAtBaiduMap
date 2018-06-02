package org.cc.practice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.cc.init.mysql.DataSourceBuilder;
import org.cc.practice.dao.UserDao;
import org.cc.practice.entity.User;


import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;
import org.cc.practice.data.mapPosition;

public class UserDaoImpl{


	
		
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
	

	
	public  static List outputData(mapPosition map) throws Exception{
			System.out.println(map);
			List<Map<String,Object>> list = (List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getSlaveSource(), new JdbcCallback() {
				
						@Override
						public Object doInJdbc(Connection arg0) throws SQLException, Exception {
							
							final String sqlCom="select * from iov_track_0 limit 20,400";
							return DbHelper.queryForList(arg0, sqlCom);
						}
					});
			
			return list;
			
	}
	
	public  static List initOutputData() throws Exception{
		System.out.println("≥ı ºªØ≤È—Ø£°");
		List<Map<String,Object>> list = (List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getSlaveSource(), new JdbcCallback() {
			
					@Override
					public Object doInJdbc(Connection arg0) throws SQLException, Exception {
						
						final String sqlCom="select * from iov_track_0 limit 20";
						return DbHelper.queryForList(arg0, sqlCom);
					}
				});
		
		return list;
		
}
	

}
