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
	public static void main(String[] args) {
		System.out.println("开始查询：！");
		long start = System.currentTimeMillis(); //程序开始记录时间
		
		 ExecutorService exec = Executors.newFixedThreadPool(24);
         for (int i = 0; i < 100; i++) {
             exec.execute(new mysqlReader(i));
             
         }
         exec.shutdown();
		
         while(true){
             if(exec.isTerminated()){
                 //System.out.println("Finally do something ");
                 long end = System.currentTimeMillis();
                 System.out.println("用时: " + (end - start) + "ms");
                 break;
             }

         }
		
	}
}
