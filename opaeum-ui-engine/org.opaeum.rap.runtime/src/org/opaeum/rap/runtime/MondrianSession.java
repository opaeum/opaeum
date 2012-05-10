package org.opaeum.rap.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.olap4j.OlapConnection;
import org.olap4j.OlapWrapper;
import org.opaeum.runtime.environment.Environment;

public class MondrianSession{
	public static final String SESSION_ATTRIBUTE_NAME = "MondrianSession";
	private OlapConnection connection;
	private String fileName;
	public MondrianSession(URL fileName){
		super();
		InputStream openStream;
		try{
			openStream = fileName.openStream();
			StringBuilder sb = new StringBuilder();
			byte b[] = new byte[256];
			int size = 0;
			while((size = openStream.read(b)) > 0){
				sb.append(new String(b).substring(0, size));
			}
			this.fileName = sb.toString();
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public OlapConnection getConnection(){
		if(this.connection == null || true){
			this.connection = getOlapConnection();
		}
		return this.connection;
	}
	public void close(){
		try{
			if(connection != null && !connection.isClosed()){
				connection.close();
				connection = null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	private OlapConnection getOlapConnection(){
		try{
			Environment env = Environment.getInstance();
			ClassLoader cl = env.getMetaInfoMap().getAllClasses().iterator().next().getClassLoader();
			Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
			Class.forName("org.postgresql.Driver");
			Thread.currentThread().setContextClassLoader(cl);
			String connectionString = "jdbc:mondrian:Jdbc=" + env.getProperty(Environment.JDBC_CONNECTION_URL) + ";CatalogContent=" + fileName
					+ ";JdbcUser=" + env.getProperty(Environment.DB_USER) + ";JdbcPassword=" + env.getProperty(Environment.DB_PASSWORD) + ";";
			Connection connection = DriverManager.getConnection(connectionString);
			OlapWrapper wrapper = (OlapWrapper) connection;
			OlapConnection olapConnection = wrapper.unwrap(OlapConnection.class);
			return olapConnection;
		}catch(RuntimeException re){
			throw re;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
