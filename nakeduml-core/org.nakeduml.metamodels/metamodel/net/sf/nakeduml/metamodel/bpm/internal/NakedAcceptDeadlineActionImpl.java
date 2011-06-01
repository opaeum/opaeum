package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptEventActionImpl;
import net.sf.nakeduml.metamodel.bpm.INakedAcceptDeadlineAction;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;

public class NakedAcceptDeadlineActionImpl extends NakedAcceptEventActionImpl implements INakedAcceptDeadlineAction{
	@Override
	public INakedDeadline getDeadline(){
		return (INakedDeadline) getTrigger().getEvent();
	}
}
