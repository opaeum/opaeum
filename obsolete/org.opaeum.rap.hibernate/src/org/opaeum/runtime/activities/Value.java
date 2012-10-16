package org.opaeum.runtime.activities;

import javax.persistence.Embeddable;

import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;

@Embeddable
public class Value{
	private String type;
	private String value;
	public Object getValue(AbstractPersistence persistence){
		if(type.endsWith("Double")){
			return Double.parseDouble(value);
		}else if(type.endsWith("Integer")){
			return Integer.parseInt(value);
		}else if(type.endsWith("Boolean")){
			return value.equalsIgnoreCase("true");
		}else if(type.endsWith("String")){
			return value;
		}else{
			Class<?> clss = Environment.getInstance().getMetaInfoMap().getClass(type);
			return persistence.getReference(clss, new Long(value));
		}
	}
	public void setValue(Object value2){
		if(value2 != null){
			if(value2.getClass().getName().startsWith("java.lang")){
				type = value2.getClass().getName();
			}else{
				type = Environment.getInstance().getMetaInfoMap().getUuidFor(value2.getClass());
			}
		}else{
			type=null;
			value=null;
		}
	}
	public boolean hasValue(){
		return value!=null;
	}
}
