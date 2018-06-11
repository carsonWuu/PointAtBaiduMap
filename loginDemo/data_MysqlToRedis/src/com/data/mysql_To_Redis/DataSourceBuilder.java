package com.data.mysql_To_Redis;

import javax.sql.DataSource;



/**
 * @author ybbk
 * @Date 2012-3-10 涓嬪崍2:41:26
 */
public class DataSourceBuilder {

	private com.tonetime.commons.database.Configuration configure = null;
	private com.tonetime.commons.database.DataSourceBuilder builder = null;
	
	
	DataSource dataSource = null;
	DataSource slaveSource = null;

	private DataSourceBuilder(){
		
		long start = System.currentTimeMillis(); //程序开始记录时间  
		
		 //System.out.println("Finally do something ");
      
		configure = new com.tonetime.commons.database.Configuration("db.properties");
		builder = com.tonetime.commons.database.DataSourceBuilder.builder(configure);
		dataSource = builder.getDataSource("dbMaster");
		slaveSource = builder.getDataSource("dbSlave");
		long end = System.currentTimeMillis();
	    System.out.println("建立连接用时: " + (end - start) + "ms");
		
	}

	private static class HolderSingletonHolder {
		static DataSourceBuilder instance = new DataSourceBuilder();
	}

	public static DataSourceBuilder getInstance() {
		return HolderSingletonHolder.instance;
	}
	public DataSource getDataSource(){
		return dataSource;
	}
	public DataSource getSlaveSource(){
		return slaveSource;
	}
}
