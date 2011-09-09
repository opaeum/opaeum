package net.sf.nakeduml.metamodel.statemachines;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;

public interface INakedTransition extends INakedElementOwner,GuardedFlow{
	/**
	 * Returns the main source of this transition, i.e. the outermost state that will effectively be exited by this transition. See UML2 spec
	 */
	INakedState getMainSource();
	/**
	 * Returns the main target of this transition, i.e. the outermost state that will effectively be entered by this transition See UML2 spec
	 */
	INakedState getMainTarget();
	/**
	 * Returns the region inside the main target of this transition where the target resides - either directly or indirectly
	 */
	INakedRegion getMainRegion();
	void setSource(INakedState source);
	INakedState getSource();
	INakedState getTarget();
	void setTarget(INakedState target);
	/**
	 * The parameters that are in scope for the behavioral effects of this transition. These parameters can be assumed to be instances of
	 * TypedElement, but in fact they could be either Parameters in the case of an Operation trigger, or Attributes in the case of a Signal
	 * trigger
	 */
	List<? extends INakedTypedElement> getParameters();
	TransitionKind getKind();
	INakedStateMachine getStateMachine();
	PreAndPostConstrained getEffect();
	Collection<INakedTrigger> getTriggers();
	INakedValueSpecification getGuard();
	boolean hasEffect();
	void setEffect(INakedBehavior effect);
	boolean hasGuard();
	void setGuardConstraint(INakedConstraint guard);
	INakedConstraint getGuardConstraint();
	public INakedRegion getContainer();
}