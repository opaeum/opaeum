package org.opeum.seam3.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.opeum.runtime.environment.Environment;

public class DatabaseUpdater{
	

	public static void updateDatabase(String url,String password) throws SQLException{
		Configuration config = ManagedHibernateSessionFactoryProvider.buildConfiguration();
		config.getProperties().remove("hibernate.connection.datasource");
		config.getProperties().put("hibernate.connection.url", url);
		config.getProperties().put("hibernate.connection.username", Environment.getInstance().getProperty(Environment.DB_USER));
		config.getProperties().put("hibernate.connection.password", password);
		Connection conn=DriverManager.getConnection(url,Environment.getInstance().getProperty(Environment.DB_USER), password);
		ManagedHibernateSessionFactoryProvider.createSchemas(config, conn);
		new SchemaUpdate(config).execute(false, true);
	}
}
