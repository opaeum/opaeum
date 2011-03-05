package net.sf.nakeduml.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import nl.klasse.octopus.oclengine.IOclContext;

public abstract class NakedActionImpl extends NakedExecutableNodeImpl implements INakedAction {
	private static final long serialVersionUID = 2697132216413111920L;
	public static final String META_CLASS = "action";
	private Collection<IOclContext> preConditions = new ArrayList<IOclContext>();
	private Collection<IOclContext> postConditions = new ArrayList<IOclContext>();

	@Override
	public String getMetaClass() {
		return "action";
	}
	public boolean handlesException(){
		for (INakedInputPin pin : getInput()) {
			if(pin.getIncomingExceptionHandler()!=null){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean isImplicitFork() {
		if (super.isImplicitFork()) {
			return true;
		} else {
			int pinOutputCount = 0;
			for (INakedOutputPin outputPin : getOutput()) {
				// exceptions are not implicit forks
				if (!outputPin.isException() && outputPin.getOutgoing().size() > 0) {
					pinOutputCount++;
					if (pinOutputCount > 1) {
						return true;
					}
				}
			}
			return false;
		}
	}

	public boolean hasExceptions() {
		if (super.hasExceptions()) {
			return true;
		}
		Collection<INakedOutputPin> output = getOutput();
		for (INakedOutputPin pin : output) {
			if (pin.isException() && pin.getOutgoing().size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<INakedActivityEdge> getAllEffectiveOutgoing() {
		Set<INakedActivityEdge> result = new HashSet<INakedActivityEdge>(super.getOutgoing());
		Collection<INakedOutputPin> output = getOutput();
		for (INakedOutputPin out : output) {
			result.addAll(out.getAllEffectiveOutgoing());
		}
		return result;
	}

	@Override
	public Set<INakedActivityEdge> getAllEffectiveIncoming() {
		Set<INakedActivityEdge> result = new HashSet<INakedActivityEdge>(super.getIncoming());
		Collection<INakedInputPin> input = getInput();
		for (INakedInputPin in : input) {
			result.addAll(in.getAllEffectiveIncoming());
		}
		return result;
	}

	public Collection<IOclContext> getPostConditions() {
		return this.postConditions;
	}

	public Collection<IOclContext> getPreConditions() {
		return this.preConditions;
	}

	public void addPostCondition(IOclContext postCondition) {
		this.postConditions.add(postCondition);
	}

	public void addPreCondition(IOclContext preCondition) {
		this.preConditions.add(preCondition);
	}

	public INakedClassifier getContext() {
		if (getActivity().getActivityKind().isSimpleSynchronousMethod()) {
			return getActivity().getContext();
		} else {
			return getActivity();
		}
	}

	public void setPostConditions(Collection<IOclContext> postConditions) {
		this.postConditions = postConditions;
	}

	public void setPreConditions(Collection<IOclContext> preConditions) {
		this.preConditions = preConditions;
	}
}
