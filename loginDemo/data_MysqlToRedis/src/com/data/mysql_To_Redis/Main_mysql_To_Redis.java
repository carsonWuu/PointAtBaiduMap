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
		System.out.println("��ʼ��ѯ����");
		long start = System.currentTimeMillis(); //����ʼ��¼ʱ��
		
		ExecutorService exec = Executors.newFixedThreadPool(40);
		
		for (int i = 0; i < 100; i++) {
			exec.execute(new mysqlReader(i));
			// new mysqlReader(i).run();
             
        }
			 
		 
        exec.shutdown();
		
         
        while(true){
             if(exec.isTerminated()){
                 //System.out.println("Finally do something ");
                 long end = System.currentTimeMillis();
                 System.out.println("��ʱ: " + (end - start) + "ms");
                 Thread.sleep(5000);
                 break;
             }

         }
       break; 
		}
		
	}
}
