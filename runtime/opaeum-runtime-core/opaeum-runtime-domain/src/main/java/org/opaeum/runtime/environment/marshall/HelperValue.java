package org.opaeum.runtime.environment.marshall;

import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class HelperValue extends Value{
	private static final long serialVersionUID = 9038651829406737134L;
	private Class<?> helperClass;
	public HelperValue(String typeId){
		super(typeId);
	}
	public HelperValue(IActiveObject s, Environment env){
		super("ABCDEFGHIJKLMNOP");
		helperClass = env.getImplementationClass(s.getClass());
	}
	public Class<?> getValueClass(JavaMetaInfoMap env){
		if(helperClass == null){
			return super.getValueClass(env);
		}else{
			return helperClass;
		}
	}
}