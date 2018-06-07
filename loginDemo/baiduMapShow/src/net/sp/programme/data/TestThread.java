package net.sp.programme.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sp.init.mysql.DataSourceBuilder;

import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;

public class TestThread implements Runnable{
	private int index;
	public TestThread(int index){
		this.index = index;
	}
	
	@Override
	public void run() {
		try {
			List<Map<String,Object>> list = (List<Map<String, Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getSlaveSource(), new JdbcCallback() {
				
				@Override
				public Object doInJdbc(Connection arg0) throws SQLException, Exception {
					
					final double lon=110.017656	,lat=40.591033;
					final double distance=1000;
					final String sqlCom="SELECT n_clng,n_clat,c_model,(6371 * acos (cos ( radians("+lon+") )* cos( radians( n_clat ) )* cos( radians( n_clng ) - radians("+lat+") )+ sin ( radians("+lon+") )    * sin( radians( n_clat ) )    )     ) AS distance    FROM iov_track_"+index+"  HAVING distance < "+distance+"  ORDER BY distance;";
					System.out.println(sqlCom);
				
					return DbHelper.queryForList(arg0, sqlCom);
				}
			});
			// todo
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
