package org.opaeum.runtime.remote;

import org.opaeum.runtime.domain.IActiveEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;

public abstract class AbstractAssembler<O extends IPersistentObject,D extends IDataTransferObject, R extends IReference> implements IAssembler<O,D,R>{
	public static <O1 extends IPersistentObject,D1 extends IDataTransferObject, R1 extends IReference> AbstractAssembler<O1,D1, R1> getAssembler(O1 o){
		Class<O1> originalClass = IntrospectionUtil.getOriginalClass(o);
		AbstractAssembler<?,?,?> result = Environment.getInstance().getMetaInfoMap().getSecondaryObject(AbstractAssembler.class, originalClass);
		if(result == null){
			Environment.getInstance().getMetaInfoMap().addSecondaryClass(AbstractAssembler.class, originalClass, "Assembler", true);
			result = Environment.getInstance().getMetaInfoMap().getSecondaryObject(AbstractAssembler.class, originalClass);
		}
		return (AbstractAssembler<O1,D1,R1>) result;
	}
}
