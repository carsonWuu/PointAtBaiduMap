package com.data.Main_mysqlToRedis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.data.from_Mysql.mysqlReader;
import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;

public class Main_mysqlToRedis {
	public static void main(String[] args) throws InterruptedException {
		new mysqlReader(0).run();
		while(true){
			System.out.println("��ʼ��ѯ��");
			long start = System.currentTimeMillis(); //����ʼ��¼ʱ��
			
			ExecutorService exec = Executors.newFixedThreadPool(100);
			
			for (int i = 0; i < 100; i++) {
				exec.execute(new mysqlReader(i));
				//new Thread(new mysqlReader(i)).start();
				// new mysqlReader(i).run();
	             
	        }
				 
			long start1 = System.currentTimeMillis(); //����ʼ��¼ʱ��
			
			//System.out.println(start1-start);
			 
	        exec.shutdown();
			
	         
	        while(true){
	             if(exec.isTerminated()){
	                 //System.out.println("Finally do something ");
	                 long end = System.currentTimeMillis();
	                 System.out.println("��ʱ: " + (end - start) + "ms");
	                 System.out.println("Sleep 5 minutes!");
	                 Thread.sleep(300000);
	                 break;
	             }
	
	         }
//       break; 
		}
		
	}
}
