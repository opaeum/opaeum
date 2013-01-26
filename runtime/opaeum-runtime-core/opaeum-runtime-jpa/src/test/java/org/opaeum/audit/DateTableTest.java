package org.opaeum.audit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.HSQLDialect;
import org.junit.Test;

public class DateTableTest{
	@Test
	public void testIt() throws Exception{
		Class.forName("org.hsqldb.jdbcDriver");
		Dialect d = new HSQLDialect();
		Connection con = DriverManager.getConnection("jdbc:hsqldb:mem:DB");
		con.prepareStatement("CREATE TABLE date_type(" + //
				"id " + d.getTypeName(Types.TIMESTAMP) + " NOT NULL," + //
				"year " + d.getTypeName(Types.INTEGER) + "," + //
				"month " + d.getTypeName(Types.INTEGER) + "," + //
				"week " + d.getTypeName(Types.INTEGER) + "," + //
				"day " + d.getTypeName(Types.INTEGER) + "," + //
				"hour " + d.getTypeName(Types.INTEGER) + "," + //
				"minute " + d.getTypeName(Types.INTEGER) + "," + //
				"second " + d.getTypeName(Types.INTEGER) + "," + //
				"CONSTRAINT date_type_pkey PRIMARY KEY (id ))").execute();
		con.prepareStatement("CREATE TABLE date_holder(" + //
				"id " + d.getTypeName(Types.INTEGER) + " NOT NULL," + //
				"the_date " + d.getTypeName(Types.TIMESTAMP) + "," + //
				"CONSTRAINT date_holder_pkey PRIMARY KEY (id ))").execute();
		PreparedStatement insertSource = con.prepareStatement("insert into date_holder(id,the_date) values(?,?)");
		insertSource.setInt(1, 1);
		Timestamp x = new Timestamp(System.currentTimeMillis());
		insertSource.setTimestamp(2, x);
		insertSource.execute();
		PreparedStatement insertDate = con.prepareStatement("insert into date_type(id,year,month,week,day) values(?,?,?,?,?)");
		insertDate.setTimestamp(1, x);
		Calendar cal = Calendar.getInstance();
		cal.setTime(x);
		insertDate.setInt(2, cal.get(Calendar.YEAR));
		insertDate.setInt(3, cal.get(Calendar.MONTH));
		insertDate.setInt(4, cal.get(Calendar.WEEK_OF_YEAR));
		insertDate.setInt(5, cal.get(Calendar.DAY_OF_MONTH));
		insertDate.execute();
		ResultSet executeQuery = con.prepareStatement("select d.id from date_type d inner join date_holder s on d.id=s.the_date").executeQuery();
		while(executeQuery.next()){
			System.out.println(executeQuery.getTimestamp(1));
		}
	}
}
