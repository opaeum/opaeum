package org.nakeduml.environment.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.type.EnumType;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.IEnum;

public abstract class AbstractEnumResolver extends EnumType implements EnumResolver{
	private static final long serialVersionUID = 6888291985327113775L;
	@Override
	public int[] sqlTypes(){
		return new int[]{
			Types.INTEGER
		};
	}
	@Override
	public Object nullSafeGet(ResultSet rs,String[] names,Object owner) throws HibernateException,SQLException{
		int object = rs.getInt(names[0]);
		if(rs.wasNull()){
			return null;
		}else{
			return fromNakedUmlId(object);
		}
	}
	@Override
	public void nullSafeSet(PreparedStatement st,Object value,int index) throws HibernateException,SQLException{
		if(value == null){
			st.setNull(index, Types.INTEGER);
		}else{
			st.setInt(index, toNakedUmlId((IEnum) value));
		}
	}
}
