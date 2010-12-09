package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import nl.klasse.octopus.oclengine.IOclContext;

public class NakedOpaqueActionImpl extends NakedCallActionImpl implements INakedOpaqueAction {
	private static final long serialVersionUID = -8741446980774863436L;
	private INakedMessageStructure asClass;
	private IOclContext bodyExpression;

	public IOclContext getBodyExpression() {
		return bodyExpression;
	}

	@Override
	public boolean isSynchronous() {
		return true;
	}

	public void setBodyExpression(IOclContext bodyExpression) {
		this.bodyExpression = bodyExpression;
	}

	public ActionType getActionType() {
		return ActionType.OPAQUE_ACTION;
	}

	@Override
	public INakedValueSpecification getBody() {
		if (getBodyExpression() == null) {
			return null;
		} else {
			return new NakedValueSpecificationImpl(getBodyExpression());
		}
	}

	public List<INakedInputPin> getInputValues() {
		return super.getArguments();
	}

	public void setInputValues(List<INakedInputPin> arguments) {
		super.setArguments(arguments);
	}

	public List<INakedOutputPin> getOutputValues() {
		return super.getResult();
	}

	public void setOutputValues(List<INakedOutputPin> results) {
		super.setResult(results);
	}

	@Override
	public INakedMessageStructure getMessageStructure() {
		if (asClass == null) {
			this.asClass = new OpaqueActionMessageStructureImpl(this);
		}
		return asClass;
	}

	// TODO is OpaqueACtion a callAction or a ActionWIthTarget?
	public IParameterOwner getCalledElement() {
		return null;
	}


	@Override
	public List<INakedPin> getPins() {
		ArrayList<INakedPin> result = new ArrayList<INakedPin>(getInputValues());
		result.addAll(getOutputValues());
		return result;
	}

	@Override
	public boolean isTask() {
		return getBodyExpression() == null;
	}
}
