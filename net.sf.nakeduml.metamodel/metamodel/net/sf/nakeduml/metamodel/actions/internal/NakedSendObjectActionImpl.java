package net.sf.nakeduml.metamodel.actions.internal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedSendObjectAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedElement;
public class NakedSendObjectActionImpl extends NakedInvocationActionImpl implements INakedSendObjectAction {
	private static final long serialVersionUID = 3165514874679324190L;
	INakedInputPin object;
	public INakedInputPin getObject() {
		return this.object;
	}

	public void setRequest(INakedInputPin object) {
		this.object = object;
	}
	public ActionType getActionType() {
		return ActionType.SEND_OBJECT_ACTION;
	}
	public Collection<INakedOutputPin> getOutput() {
		return new HashSet<INakedOutputPin>();
	}
	@Override
	public Set<INakedInputPin> getInput(){
		Set<INakedInputPin>  result = super.getInput();
		if (this.object != null) {
			result.add(this.object);
		}
		return result;
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> result =super.getOwnedElements();
		if (this.object != null) {
			result.add(this.object);
		}
		return result;
	}

}
