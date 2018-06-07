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

import org.cc.practice.data.TestThread;
import org.cc.practice.data.mapPosition;

public class UserDaoImpl{
	public static double[] level={5,10,20,50,100, 200,500,1000 ,2000,5000,10000,20000,25000,50000,100000,200000,
		500000,1000000,2000000};//�����߾���(��)

	
		
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
	
	public  static List initOutputData() throws Exception{
		System.out.println("��ʼ����ѯ��");
		long startTime = System.currentTimeMillis(); //����ʼ��¼ʱ��
		List<Map<String,Object>> list = (List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getSlaveSource(), new JdbcCallback() {
			
					@Override
					public Object doInJdbc(Connection arg0) throws SQLException, Exception {
						
						final double lon=110.017656	,lat=40.591033;
						final double distance=1000;
						final String sqlCom="SELECT n_clng,n_clat,c_model,(6371 * acos (cos ( radians("+lon+") )* cos( radians( n_clat ) )* cos( radians( n_clng ) - radians("+lat+") )+ sin ( radians("+lon+") )    * sin( radians( n_clat ) )    )     ) AS distance    FROM iov_track_0  HAVING distance < "+distance+"  ORDER BY distance;";
						System.out.println(sqlCom);
					
						return DbHelper.queryForList(arg0, sqlCom);
					}
				});
		
//		for(int i =0;i<100;i++){
//			new Thread(new TestThread(i)).start();
//		}
		long endTime   = System.currentTimeMillis(); //���������¼ʱ��
		long TotalTime = endTime - startTime;       //������ʱ��
		System.out.println("��ʼ����ѯʱ��(ms):"+TotalTime);
		return list;
		
	}
	
	public  static List outputData(mapPosition map) throws Exception{
		long startTime = System.currentTimeMillis(); //����ʼ��¼ʱ��
		//������ ������
		
		final double lon=map.index_x,lat=map.index_y;
		
		List<Map<String,Object>> list = (List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getSlaveSource(), new JdbcCallback() {
				
						@Override
			public Object doInJdbc(Connection arg0) throws SQLException, Exception {
				final double distance=10000;
				final String sqlCom="SELECT n_clng,n_clat,c_model,(6371 * acos (cos ( radians("+lon+") )* cos( radians( n_clat ) )* cos( radians( n_clng ) - radians("+lat+") )+ sin ( radians("+lon+") )    * sin( radians( n_clat ) )    )     ) AS distance    FROM iov_track_0  HAVING distance < "+distance+"  ORDER BY distance;";
				System.out.println(sqlCom);						
				//final String sqlCom="select * from iov_track_0";
							
				return DbHelper.queryForList(arg0, sqlCom);
			}
		});
			
		long endTime   = System.currentTimeMillis(); //���������¼ʱ��
		long TotalTime = endTime - startTime;       //������ʱ��
		System.out.println("�û���ѯʱ��(ms):"+TotalTime);
		return list;
			
	}
	
}
