package org.nakeduml.environment.marshall;

import org.nakeduml.environment.Environment;
import org.nakeduml.runtime.domain.IActiveObject;

public class HelperValue extends Value{
	private static final long serialVersionUID = 9038651829406737134L;
	private Class<?> helperClass;
	public HelperValue(Integer typeId){
		super(typeId);
	}
	public HelperValue(IActiveObject s){
		super(Integer.MIN_VALUE);
		helperClass = Environment.getInstance().getImplementationClass(s.getClass());
	}
	public Class<?> getValueClass(){
		if(helperClass == null){
			return super.getValueClass();
		}else{
			return helperClass;
		}
	}
}