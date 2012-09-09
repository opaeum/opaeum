package org.opaeum.runtime.remote;

import org.opaeum.runtime.domain.IPersistentObject;

public interface IAssembler <O extends IPersistentObject,D extends IDataTransferObject, R extends IReference>{
	public abstract D buildData(O object);
	public abstract void applyData(O object,D data);
	public abstract R getReference(O o);

}
