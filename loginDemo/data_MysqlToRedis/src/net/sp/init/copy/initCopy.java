package net.sp.init.copy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



import com.data.mysql_Init.DataSourceBuilder;
import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;

public class initCopy {
	public static boolean init(final int index){
		List <Map<String,Object>> list = null ;
		try {
			list = (List<Map<String,Object>>) DbHelper.execute(DataSourceBuilder.getInstance().getSlaveSource(),new JdbcCallback() {
				
				@Override
				public Object doInJdbc(Connection arg0) throws SQLException, Exception {
					// TODO Auto-generated method stub
					String sql="select *  from iov_track_"+index;
					final String sqlCom=sql;
					return DbHelper.queryForList(arg0, sqlCom);
				}
			});
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.size());
	
		return true;
	}
	
	public static void start(){
		ExecutorService exec=Executors.newFixedThreadPool(100);
		//while(true){
		for(int i=0;i<100;i++){
			
		}
	}
}
