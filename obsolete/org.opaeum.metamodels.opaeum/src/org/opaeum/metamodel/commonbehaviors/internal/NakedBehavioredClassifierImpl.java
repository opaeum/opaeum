package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IState;

import org.eclipse.uml2.uml.DefaultOpaeumComparator;
import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedBehavioredClassifier;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedEvent;
import org.eclipse.uml2.uml.INakedGeneralization;
import org.eclipse.uml2.uml.INakedInterfaceRealization;
import org.eclipse.uml2.uml.INakedReception;
import org.eclipse.uml2.uml.INakedSignal;
import org.eclipse.uml2.uml.INakedSignalEvent;
import org.eclipse.uml2.uml.INakedStateMachine;
import org.eclipse.uml2.uml.INakedTriggerContainer;
import org.opaeum.metamodel.core.internal.NakedClassifierImpl;
import org.opaeum.metamodel.core.internal.ParameterUtil;

public class NakedBehavioredClassifierImpl extends NakedClassifierImpl implements INakedBehavioredClassifier{
	private static final long serialVersionUID = -2856991672094313864L;
	protected List<INakedBehavior> ownedBehaviors = new ArrayList<INakedBehavior>();
	private INakedBehavior classifierBehavior;
	protected List<INakedInterfaceRealization> realizations = new ArrayList<INakedInterfaceRealization>();
	public void reorderSequences(){
		super.reorderSequences();
		Collections.sort(realizations, new Comparator<INakedInterfaceRealization>(){
			@Override
			public int compare(INakedInterfaceRealization o1,INakedInterfaceRealization o2){
				int i = o1.getIndex() - o2.getIndex();
				return i;
			}
		});
	};
	public INakedBehavior getClassifierBehavior(){
		if(this.classifierBehavior == null && hasSupertype()){
			return ((INakedBehavioredClassifier) getSupertype()).getClassifierBehavior();
		}else{
			return this.classifierBehavior;
		}
	}
	public void setClassifierBehavior(INakedBehavior classifierBehavior){
		this.classifierBehavior = classifierBehavior;
	}
	public List<INakedInterfaceRealization> getInterfaceRealizations(){
		return this.realizations;
	}
	@Override
	public Collection<INakedReception> getEffectiveReceptions(){
		Collection<INakedReception> effectiveReceptions = super.getEffectiveReceptions();
		for(INakedInterfaceRealization r:this.realizations){
			effectiveReceptions.addAll(r.getContract().getEffectiveReceptions());
		}
		return effectiveReceptions;
	}
	public List<IInterface> getInterfaces(){
		List<IInterface> results = new ArrayList<IInterface>();
		for(INakedInterfaceRealization r:this.realizations){
			results.add(r.getContract());
		}
		return results;
	}
	@Override
	public List<IState> getStates(){
		// Fakes region states
		List<IState> results = new ArrayList<IState>();
		if(this.classifierBehavior instanceof INakedStateMachine){
			results.addAll(((INakedStateMachine) this.classifierBehavior).getAllStates());
		}
		// TODO implement something similar for activities with resting states
		return results;
	}
	@Override
	public Collection<IClassifier> getClassifiers(){
		Collection<IClassifier> classifiers = super.getClassifiers();
		classifiers.addAll(getOwnedBehaviors());
		return classifiers;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedBehavior){
			this.ownedBehaviors.add((INakedBehavior) element);
		}else if(element instanceof INakedInterfaceRealization){
			INakedInterfaceRealization ir = (INakedInterfaceRealization) element;
			realizations.add(ir);
		}
	}
	public Collection<INakedBehavior> getOwnedBehaviors(){
		return this.ownedBehaviors;
	}
	public Collection<INakedElement> removeOwnedElement(INakedElement element,boolean recursively){
		Collection<INakedElement> result = super.removeOwnedElement(element, recursively);
		if(element instanceof INakedBehavior){
			this.ownedBehaviors.remove(element);
		}else if(element instanceof INakedInterfaceRealization){
			INakedInterfaceRealization ir = (INakedInterfaceRealization) element;
			realizations.remove(ir);
			ir.getContract().removeImplementingClassifier(this);
		}
		return result;
	}
	public Collection<INakedBehavior> getEffectiveBehaviors(){
		Collection<INakedBehavior> results = new ArrayList<INakedBehavior>(ownedBehaviors);
		for(INakedGeneralization g:getNakedGeneralizations()){
			if(g.getGeneral() instanceof INakedBehavioredClassifier){
				results.addAll(((INakedBehavioredClassifier) g.getGeneral()).getEffectiveBehaviors());
			}
		}
		return results;
	}
	@Override
	public boolean hasReceptionOrTriggerFor(INakedSignal signal){
		for(INakedReception r:getEffectiveReceptions()){
			if(r.getSignal()!=null && r.getSignal().equals(signal)){
				return true;
			}
		}
		for(INakedEvent e:getEventsInScopeForClassAsContext()){
			if(e instanceof INakedSignalEvent){
				INakedSignalEvent se=(INakedSignalEvent) e;
				if(se.getSignal()!=null && se.getSignal().equals(signal)){
					return true;
				}
				
			}
		}
		return false;
	}
	@Override
	public Collection<? extends INakedReception> getDirectlyImplementedReceptions(){
		Set<String> inheritedConcreteOperationNames = new HashSet<String>();
		for(INakedGeneralization g:getNakedGeneralizations()){
			if(g.getGeneral() instanceof INakedBehavioredClassifier){
				for(INakedReception o:((INakedBehavioredClassifier) g.getGeneral()).getDirectlyImplementedReceptions()){
					inheritedConcreteOperationNames.add(ParameterUtil.toIdentifyingString(o));
				}
			}
		}
		Set<INakedReception> results = new HashSet<INakedReception>();
		for(INakedReception o:getEffectiveReceptions()){
			if(!inheritedConcreteOperationNames.contains(ParameterUtil.toIdentifyingString(o))){
				results.add(o);
			}
		}
		return results;
	}
	@Override
	public Collection<INakedEvent> getEventsInScopeForClassAsContext(){
		SortedSet<INakedEvent> result = new TreeSet<INakedEvent>(new DefaultOpaeumComparator());
		for(INakedBehavior b:getOwnedBehaviors()){
			if(b instanceof INakedTriggerContainer){
				result.addAll(((INakedTriggerContainer) b).getEventsInScopeForClassAsBehavior());
			}
		}
		return result;
	}
}
