package net.rs.init.mysql;

import javax.sql.DataSource;

/**
 * @author wcs
 * @Date 2018-06
 */
public class DataSourceBuilder {

	private com.tonetime.commons.database.Configuration configure = null;
	private com.tonetime.commons.database.DataSourceBuilder builder = null;
	
	DataSource dataSource = null;
	DataSource slaveSource = null;

	private DataSourceBuilder(){
		configure = new com.tonetime.commons.database.Configuration("db.properties");
		builder = com.tonetime.commons.database.DataSourceBuilder.builder(configure);
		dataSource = builder.getDataSource("dbMaster");
		slaveSource = builder.getDataSource("dbSlave");
		
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
