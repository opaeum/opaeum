package org.nakeduml.environment;

import org.hibernate.Session;
import org.nakeduml.runtime.domain.AbstractEnum;

public class EnumValue extends Value{
	Integer enumId;
	public EnumValue(AbstractEnum value){
		this.enumId = value.getNakedUmlId();
	}
	@Override
	public Object getValue(Session session){
		return Environment.getMetaInfoMap().getEnum(enumId);
	}
	@Override
	public Class<?> getValueClass(){
		Enum<?> enum1 = Environment.getMetaInfoMap().getEnum(enumId);
		if(enum1 != null){
			return enum1.getClass();
		}else{
			return null;
		}
	}
}
