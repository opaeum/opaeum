package org.opaeum.hibernate.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public abstract class AbstractEnumResolver  implements EnumResolver,UserType{
	@Override
	public int[] sqlTypes(){
		return new int[]{
			Types.BIGINT
		};
	}
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor arg2, Object arg3) throws HibernateException,
			SQLException {
		long object = rs.getLong(names[0]);
		if(rs.wasNull()){
			return null;
		}else{
			return fromOpaeumId(object);
		}
	}
	public void nullSafeSet(PreparedStatement st,Object value,int index, SessionImplementor si) throws HibernateException,SQLException{
		if(value == null){
			st.setNull(index, Types.BIGINT);
		}else{
			st.setLong(index, toOpaeumId((IEnum) value));
		}
	}
	public Object nullSafeGet(ResultSet rs,String[] names,Object owner) throws HibernateException,SQLException{
		long object = rs.getLong(names[0]);
		if(rs.wasNull()){
			return null;
		}else{
			return fromOpaeumId(object);
		}
	}
	public void nullSafeSet(PreparedStatement st,Object value,int index) throws HibernateException,SQLException{
		if(value == null){
			st.setNull(index, Types.BIGINT);
		}else{
			st.setLong(index, toOpaeumId((IEnum) value));
		}
		
	}

	@Override
	public boolean equals(Object x,Object y) throws HibernateException{
		return x==y;
	}
	@Override
	public int hashCode(Object x) throws HibernateException{
		return x.hashCode();
	}
	@Override
	public Object deepCopy(Object value) throws HibernateException{
		return value;
	}
	@Override
	public boolean isMutable(){
		return false;
	}
	@Override
	public Serializable disassemble(Object value) throws HibernateException{
		return (Serializable) value;
	}
	@Override
	public Object assemble(Serializable cached,Object owner) throws HibernateException{
		return cached;
	}
	@Override
	public Object replace(Object original,Object target,Object owner) throws HibernateException{
		return original;
	}
}
