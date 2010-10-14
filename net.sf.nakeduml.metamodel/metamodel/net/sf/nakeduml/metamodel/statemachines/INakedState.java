package net.sf.nakeduml.metamodel.statemachines;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IState;

public interface INakedState extends IRegionOwner,IState{
	List<INakedTransition> getIncoming();
	List<INakedTransition> getOutgoing();
	List<INakedBehavior> getActions();
	INakedRegion getContainer();
	INakedStateMachine getStateMachine();
	/**
	 * Returns an array of TriggerInState for which this state has one or more transitions
	 */
	StateKind getKind();
	boolean hasEnclosingState();
	INakedState getEnclosingState();
	INakedClassifier getOwner();
	PathName getStatePath();
	void setKind(StateKind kind);
	INakedBehavior getDoActivity();
	void setDoActivity(INakedBehavior doActivity);
	INakedBehavior getEntry();
	void setEntry(INakedBehavior entry);
	INakedBehavior getExit();
	void setExit(INakedBehavior exit);
	List<INakedRegion> getRegions();
	/**
	 * NB! Used from velocity templates
	 * @return
	 */
	Collection<INakedTransition> getCompletionTransitions();
	/**
	 * NB! Used from velocity templates
	 * @return
	 */
	Collection<INakedTransition> getTimeTriggerTransitions();
}