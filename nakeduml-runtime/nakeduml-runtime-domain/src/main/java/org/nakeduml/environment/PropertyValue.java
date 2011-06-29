package org.nakeduml.environment;

import java.beans.PropertyDescriptor;
import java.io.Serializable;

import org.hibernate.Session;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.AbstractSignal;

public class PropertyValue implements Serializable{
	private static final long serialVersionUID = -5852805763067486690L;
	private Integer id;
	private Value value;
	public PropertyValue(){
	}
	public PropertyValue(PropertyDescriptor pd,Value value){
		this.id = pd.getReadMethod().getAnnotation(NumlMetaInfo.class).nakedUmlId();
		this.value = value;
	}
	public void setValue(AbstractSignal signal,Session session){
		if(value != null){
			try{
				PropertyDescriptor pd = Environment.getMetaInfoMap().getProperty(id);
				pd.getWriteMethod().invoke(signal, value.getValue(session));
			}catch(RuntimeException e){
				throw e;
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
}