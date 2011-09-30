package org.opeum.metamodel.statemachines.internal;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.internal.emulated.EmulatingElement;
import org.opeum.metamodel.statemachines.INakedCompletionEvent;
import org.opeum.metamodel.statemachines.INakedState;

public class NakedCompletionEventImpl extends EmulatingElement  implements INakedCompletionEvent{
	private static final long serialVersionUID = 8032662834865668246L;
	private INakedState source;
	public NakedCompletionEventImpl(INakedState source){
		super(source);
		this.source=source;
	}
	public INakedState getSource(){
		return source;
	}
	@Override
	public INakedClassifier getContext(){
		return source.getStateMachine();
	}
	@Override
	public String getMetaClass(){
		return "completionEvent";
	}
}
