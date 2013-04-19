package org.opaeum.metamodel.statemachines.internal;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedCompletionEvent;
import org.eclipse.uml2.uml.INakedState;
import org.opaeum.metamodel.core.internal.emulated.EmulatingElement;

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
