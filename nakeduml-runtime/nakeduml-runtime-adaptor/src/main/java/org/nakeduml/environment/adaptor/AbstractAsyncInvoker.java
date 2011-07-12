package org.nakeduml.environment.adaptor;

import org.nakeduml.environment.MethodInvocationHolder;

public abstract class AbstractAsyncInvoker extends AbstractEventMdb<MethodInvocationHolder>{
	protected void deliverMessage(MethodInvocationHolder holder) throws Exception{
		if(holder.targetIsEntity()){
			umtPersistence.setTransactionTimeout(600);
			umtPersistence.beginTransaction();
			holder.prepareForDelivery(umtPersistence);
			holder.invoke();
			umtPersistence.commitTransaction();
		}else{
			umtPersistence.beginTransaction();
			holder.prepareForDelivery(umtPersistence);
			umtPersistence.commitTransaction();
			holder.invoke();
		}
	}
}
