package org.eclipse.uml2.uml;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.model.IState;


public interface INakedState extends IRegionOwner,IState,INakedStep{
	List<INakedTransition> getIncoming();
	List<INakedTransition> getOutgoing();
	List<INakedBehavior> getActions();
	INakedRegion getContainer();
	INakedStateMachine getStateMachine();
	StateKind getKind();
	boolean hasEnclosingState();
	INakedState getEnclosingState();
	INakedClassifier getOwner();
	void setKind(StateKind kind);
	INakedBehavior getDoActivity();
	void setDoActivity(INakedBehavior doActivity);
	INakedBehavior getEntry();
	void setEntry(INakedBehavior entry);
	INakedBehavior getExit();
	void setExit(INakedBehavior exit);
	Collection<INakedRegion> getRegions();
	Collection<INakedTransition> getCompletionTransitions();
	void removeFromOutgoing(INakedTransition nakedTransitionImpl);
	void addToOutgoing(INakedTransition nakedTransitionImpl);
	void removeFromIncoming(INakedTransition nakedTransitionImpl);
	void addToIncoming(INakedTransition nakedTransitionImpl);
	INakedCompletionEvent getCompletionEvent();
	INakedState getRedefinedState();
}