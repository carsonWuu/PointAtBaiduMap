package com.data.mysql_To_Redis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;

public class Main_mysql_To_Redis {
	public static void main(String[] args) throws InterruptedException {
		new mysqlReader(0).run();
		while(true){
			System.out.println("开始查询：！");
			long start = System.currentTimeMillis(); //程序开始记录时间
			
			ExecutorService exec = Executors.newFixedThreadPool(100);
			
			for (int i = 0; i < 100; i++) {
				exec.execute(new mysqlReader(i));
				//new Thread(new mysqlReader(i)).start();
				// new mysqlReader(i).run();
	             
	        }
				 
			long start1 = System.currentTimeMillis(); //程序开始记录时间
			
			//System.out.println(start1-start);
			 
	        exec.shutdown();
			
	         
	        while(true){
	             if(exec.isTerminated()){
	                 //System.out.println("Finally do something ");
	                 long end = System.currentTimeMillis();
	                 System.out.println("用时: " + (end - start) + "ms");
	                 Thread.sleep(30000);
	                 break;
	             }
	
	         }
//       break; 
		}
		
	}
}
