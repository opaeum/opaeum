package org.opaeum.runtime.remote;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;

public abstract class AbstractAssembler<O extends IPersistentObject,D extends IDataTransferObject, R extends IReference> implements IAssembler<O,D,R>{
	public static <O1 extends IPersistentObject,D1 extends IDataTransferObject, R1 extends IReference> AbstractAssembler<O1,D1, R1> getAssembler(Environment env, O1 o){
		Class<? extends O1> originalClass = IntrospectionUtil.getOriginalClass(o);
		AbstractAssembler<?,?,?> result = env.getMetaInfoMap().getSecondaryObject(AbstractAssembler.class, originalClass);
		if(result == null){
			env.getMetaInfoMap().addSecondaryClass(AbstractAssembler.class, originalClass, "Assembler", true);
			result = env.getMetaInfoMap().getSecondaryObject(AbstractAssembler.class, originalClass);
		}
		return (AbstractAssembler<O1,D1,R1>) result;
	}
}
