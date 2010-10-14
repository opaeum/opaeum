package net.sf.nakeduml.metamodel.statemachines.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.NakedModelElementImpl;
import net.sf.nakeduml.metamodel.statemachines.INakedRegion;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.IRegionOwner;
import net.sf.nakeduml.metamodel.statemachines.TransitionKind;

public class NakedTransitionImpl extends NakedModelElementImpl implements INakedElement, INakedTransition {
	private static final long serialVersionUID = 133077616488879831L;
	protected INakedState source;
	protected INakedState target;
	PreAndPostConstrained effect;
	INakedElement trigger;
	private INakedConstraint guard;
	private String[] parameterNames;

	public NakedTransitionImpl() {
	}

	public INakedState getMainSource() {
		if (getKind().isLocal() || getKind().isInternal()) {
			// Do not invoke any exit behavior
			return null;
		} else {
			return getTopMostStateAffected(this.source);
		}
	}

	public INakedRegion getMainRegion() {
		return getMainTarget().getTopMostRegionContaining(getTarget());
	}

	public INakedState getMainTarget() {
		return getTopMostStateAffected(this.target);
	}

	private INakedState getTopMostStateAffected(INakedState s) {
		INakedState parent = s;
		IRegionOwner commonParent = this.source.getLeastCommonAncestor(this.target);
		if (!parent.equals(commonParent)) {
			// Find the topmost parent of state 's' that is NOT the LCA
			// Could very well be 's' itself
			while (!(commonParent.equals(parent.getContainer().getRegionOwner()))) {
				parent = (INakedState) parent.getContainer().getRegionOwner();
			}
		}
		return parent;
	}

	public void setSource(INakedState source) {
		this.source = source;
	}

	public void setTarget(INakedState target) {
		this.target = target;
	}

	public List getParameters() {
		if (getTrigger() instanceof INakedSignal) {
			return ((INakedSignal) getTrigger()).getArgumentParameters();
		} else if (getTrigger() instanceof INakedOperation) {
			return ((INakedOperation) getTrigger()).getArgumentParameters();
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	public String[] getParameterNames() {
		if (this.parameterNames == null) {
			List params = getParameters();
			this.parameterNames = new String[params.size()];
			for (int i = 0; i < params.size(); i++) {
				INakedTypedElement pw = (INakedTypedElement) params.get(i);
				this.parameterNames[i] = pw.getName();
			}
		}
		return this.parameterNames;
	}

	public TransitionKind getKind() {
		if (getTarget() == null || getSource().equals(getTarget())) {
			return TransitionKind.INTERNAL;
		} else if (getSource().isAncestorOf(getTarget())) {
			return TransitionKind.LOCAL;
		} else {
			return TransitionKind.EXTERNAL;
		}
	}

	public INakedState getSource() {
		return this.source;
	}

	public INakedState getTarget() {
		return this.target;
	}

	public INakedRegion getContainer() {
		return (INakedRegion) getNameSpace();
	}

	public INakedStateMachine getStateMachine() {
		INakedNameSpace parent = getNameSpace();
		while (!(parent == null || parent instanceof NakedStateMachineImpl)) {
			parent = parent.getNameSpace();
		}
		return (INakedStateMachine) parent;
	}

	@Override
	public String getMetaClass() {
		return "Transition";
	}

	public PreAndPostConstrained getEffect() {
		return this.effect;
	}

	public INakedElement getTrigger() {
		return this.trigger;
	}

	public INakedValueSpecification getGuard() {
		if (this.guard != null) {
			return this.guard.getSpecification();
		} else {
			return null;
		}
	}

	public boolean hasEffect() {
		return getEffect() != null;
	}

	public void setEffect(INakedBehavior effect) {
		addOwnedElement(effect);
	}

	public boolean hasTrigger() {
		if (!(getSource().getKind().isSimple() || getSource().getKind().isOrthogonal() || getSource().getKind().isComposite())) {
			return false;
		} else if (getTarget().getKind().isJoin()) {
			return false;
		} else {
			return getTrigger() != null;
		}
	}

	public boolean hasGuard() {
		if (getTarget().getKind().isJoin() || getSource().getKind().isFork()) {
			return false;
		} else {
			return this.getGuard() != null;
		}
	}

	public void setTrigger(INakedElement trigger) {
		this.trigger = trigger;
	}

	@Override
	public void addOwnedElement(INakedElement element) {
		if (element != null) {
			super.addOwnedElement(element);
			if (element instanceof INakedBehavior) {
				this.effect = (INakedBehavior) element;
			}
			if (element instanceof INakedConstraint) {
				this.guard = (INakedConstraint) element;
			}
		}
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> results = new HashSet<INakedElement>(super.getOwnedElements());
		if (this.guard != null) {
			results.add(this.guard);
		}
		return results;
	}

	public INakedClassifier getContext() {
		if (getStateMachine().getContext() == null) {
			return getStateMachine();
		} else {
			return getStateMachine().getContext();
		}
	}

	public INakedStateMachine getOwningBehavior() {
		return getStateMachine();
	}

	public INakedConstraint getGuardConstraint() {
		return this.guard;
	}

	public void setGuardConstraint(INakedConstraint guard) {
		addOwnedElement(effect);
	}
}