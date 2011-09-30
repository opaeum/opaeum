package org.opeum.runtime.environment.marshall;

import org.opeum.runtime.domain.IActiveObject;
import org.opeum.runtime.environment.Environment;

public class HelperValue extends Value{
	private static final long serialVersionUID = 9038651829406737134L;
	private Class<?> helperClass;
	public HelperValue(String typeId){
		super(typeId);
	}
	public HelperValue(IActiveObject s){
		super("ABCDEFGHIJKLMNOP");
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