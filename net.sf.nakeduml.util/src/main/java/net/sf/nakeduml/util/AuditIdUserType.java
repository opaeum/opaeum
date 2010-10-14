package net.sf.nakeduml.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


import org.hibernate.HibernateException;
import org.hibernate.type.ImmutableType;


public class AuditIdUserType extends ImmutableType {

	@Override
	public Class getReturnedClass() {
		return AuditId.class;
	}

	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public Object get(ResultSet rs, String name) throws HibernateException, SQLException {
		String string = rs.getString(name);
		if (rs.wasNull()) {
			return null;
		}
		return new AuditId(string);
	}

	@Override
	public void set(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		st.setString(index, value.toString());
	}

	@Override
	public int sqlType() {
		return Types.VARCHAR;
	}

	@Override
	public String toString(Object value) throws HibernateException {
		return value.toString();
	}

	@Override
	public Object fromStringValue(String xml) throws HibernateException {
		return new AuditId(xml);
	}
}
