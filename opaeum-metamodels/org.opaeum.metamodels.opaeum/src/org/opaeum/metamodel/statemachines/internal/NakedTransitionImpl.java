package org.opaeum.metamodel.statemachines.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import nl.klasse.octopus.oclengine.IOclContext;

import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedConstraint;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedNameSpace;
import org.eclipse.uml2.uml.INakedRegion;
import org.eclipse.uml2.uml.INakedState;
import org.eclipse.uml2.uml.INakedStateMachine;
import org.eclipse.uml2.uml.INakedTransition;
import org.eclipse.uml2.uml.INakedTrigger;
import org.eclipse.uml2.uml.INakedTypedElement;
import org.eclipse.uml2.uml.INakedValueSpecification;
import org.eclipse.uml2.uml.IRegionOwner;
import org.eclipse.uml2.uml.PreAndPostConstrained;
import org.eclipse.uml2.uml.StateKind;
import org.eclipse.uml2.uml.TransitionKind;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

public class NakedTransitionImpl extends NakedElementImpl implements INakedElement,INakedTransition{
	private static final long serialVersionUID = 133077616488879831L;
	private boolean isElse;
	protected INakedState source;
	protected INakedState target;
	PreAndPostConstrained effect;
	Collection<INakedTrigger> triggers = new HashSet<INakedTrigger>();
	private INakedConstraint guard;
	private INakedTransition redefinedTransition;
	public NakedTransitionImpl(){
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("isElseTransition")){
			isElse = Boolean.TRUE.equals(stereotype.getFirstValueFor("isElseTransition").getValue());
		}
	}
	@Override
	public Collection<INakedElement> removeOwnedElement(INakedElement element,boolean recursively){
		Collection<INakedElement> result = super.removeOwnedElement(element, recursively);
		if(element == effect){
			effect = null;
		}else if(element == guard){
			guard = null;
		}else if(element instanceof INakedTrigger){
			triggers.remove(element);
		}
		return result;
	}
	public INakedState getMainSource(){
		if(getKind().isLocal() || getKind().isInternal()){
			// Do not invoke any exit behavior
			return null;
		}else{
			return getTopMostStateAffected(this.source);
		}
	}
	public INakedRegion getMainRegion(){
		return getMainTarget().getTopMostRegionContaining(getTarget());
	}
	public INakedState getMainTarget(){
		return getTopMostStateAffected(this.target);
	}
	private INakedState getTopMostStateAffected(INakedState s){
		INakedState parent = s;
		IRegionOwner commonParent = this.source.getLeastCommonAncestor(this.target);
		if(!parent.equals(commonParent)){
			// Find the topmost parent of state 's' that is NOT the LCA
			// Could very well be 's' itself
			while(!(commonParent.equals(parent.getContainer().getRegionOwner()))){
				parent = (INakedState) parent.getContainer().getRegionOwner();
			}
		}
		return parent;
	}
	public void setSource(INakedState source){
		if(this.source != null){
			this.source.removeFromOutgoing(this);
		}
		if(source != null){
			source.addToOutgoing(this);
		}
		this.source = source;
	}
	public void setTarget(INakedState target){
		if(this.target != null){
			this.target.removeFromIncoming(this);
		}
		if(target != null){
			target.addToIncoming(this);
		}
		this.target = target;
	}
	public List<INakedTypedElement> getParameters(){
		return NakedTriggerImpl.getParameters(getTriggers());
	}
	public TransitionKind getKind(){
		if(getTarget() == null || getSource().equals(getTarget())){
			return TransitionKind.INTERNAL;
		}else if(getSource().isAncestorOf(getTarget())){
			return TransitionKind.LOCAL;
		}else{
			return TransitionKind.EXTERNAL;
		}
	}
	public INakedState getSource(){
		if(getRedefinedTransition() != null){
			return getRedefinedTransition().getSource();
		}else{
			return this.source;
		}
	}
	public INakedState getTarget(){
		return this.target;
	}
	public INakedRegion getContainer(){
		return (INakedRegion) getNameSpace();
	}
	public INakedStateMachine getStateMachine(){
		INakedNameSpace parent = getNameSpace();
		while(!(parent == null || parent instanceof NakedStateMachineImpl)){
			parent = parent.getNameSpace();
		}
		return (INakedStateMachine) parent;
	}
	@Override
	public String getMetaClass(){
		return "Transition";
	}
	public PreAndPostConstrained getEffect(){
		return this.effect;
	}
	public Collection<INakedTrigger> getTriggers(){
		StateKind sourceKind = getSource().getKind();
		StateKind targetKind = getTarget().getKind();
		if(!(sourceKind.isSimple() || sourceKind.isOrthogonal() || sourceKind.isComposite()) || targetKind.isJoin()){
			return Collections.emptySet();
		}else{
		}
		return this.triggers;
	}
	public INakedValueSpecification getGuard(){
		if(this.guard != null){
			return this.guard.getSpecification();
		}else{
			return null;
		}
	}
	public boolean hasEffect(){
		return getEffect() != null;
	}
	public void setEffect(INakedBehavior effect){
		addOwnedElement(effect);
	}
	public boolean hasGuard(){
		if(getTarget().getKind().isJoin() || getSource().getKind().isFork()){
			return false;
		}else{
			return !(this.getGuard() == null || Boolean.TRUE.equals(this.getGuard().getValue()) || this.getGuard().getValue() instanceof IOclContext
					&& ((IOclContext) this.getGuard().getValue()).getExpressionString().trim().equals("true") || isElse());
		}
	}
	public boolean isElse(){
		return ELSE.equals(this.getGuard().getValue()) || isElse;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		if(element != null){
			super.addOwnedElement(element);
			if(element instanceof INakedBehavior){
				this.effect = (INakedBehavior) element;
			}
			if(element instanceof INakedConstraint){
				this.guard = (INakedConstraint) element;
			}
			if(element instanceof INakedTrigger){
				triggers.add((INakedTrigger) element);
			}
		}
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> results = new HashSet<INakedElement>(super.getOwnedElements());
		return results;
	}
	public INakedClassifier getContext(){
		if(getStateMachine().getContext() == null){
			return getStateMachine();
		}else{
			return getStateMachine().getContext();
		}
	}
	public INakedStateMachine getOwningBehavior(){
		return getStateMachine();
	}
	public INakedConstraint getGuardConstraint(){
		return this.guard;
	}
	public void setGuardConstraint(INakedConstraint guard){
		addOwnedElement(guard);
	}
	@Override
	public INakedElement getEffectiveTarget(){
		return getTarget();
	}
	@Override
	public INakedTransition getRedefinedTransition(){
		return redefinedTransition;
	}
	public void setRedefinedTransition(INakedTransition redefinedTransition){
		this.redefinedTransition = redefinedTransition;
	}
}