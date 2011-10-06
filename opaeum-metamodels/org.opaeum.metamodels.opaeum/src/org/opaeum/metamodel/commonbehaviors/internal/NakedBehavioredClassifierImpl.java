package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IState;

import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedReception;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedInterfaceRealization;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedClassifierImpl;
import org.opaeum.metamodel.core.internal.ParameterUtil;
import org.opaeum.metamodel.statemachines.INakedStateMachine;

public class NakedBehavioredClassifierImpl extends NakedClassifierImpl implements INakedBehavioredClassifier{
	private static final long serialVersionUID = -2856991672094313864L;
	protected List<INakedBehavior> ownedBehaviors = new ArrayList<INakedBehavior>();
	private INakedBehavior classifierBehavior;
	protected List<INakedInterfaceRealization> realizations = new ArrayList<INakedInterfaceRealization>();
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
	public List<INakedProperty> getEffectiveAttributes(){
		//NB!!! copied the getEffectiveAttributes from NakedClassifierImpl because the sequence of the attributes is important
		List<INakedProperty> results = new ArrayList<INakedProperty>();
		for(INakedGeneralization s:getNakedGeneralizations()){
			List<? extends INakedProperty> superAttributes = s.getGeneral().getEffectiveAttributes();
			addEffectiveAttributes(results, superAttributes);
		}
		for(INakedInterfaceRealization r:this.realizations){
			List<? extends INakedProperty> interfaceAttributes = r.getContract().getEffectiveAttributes();
			addEffectiveAttributes(results, interfaceAttributes);
		}
		List<INakedProperty> ownAttributes = this.ownedAttributes;
		addEffectiveAttributes(results, ownAttributes);
		return results;
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
	public void removeOwnedElement(INakedElement element,boolean recursively){
		super.removeOwnedElement(element, recursively);
		if(element instanceof INakedBehavior){
			this.ownedBehaviors.remove(element);
		}else if(element instanceof INakedInterfaceRealization){
			INakedInterfaceRealization ir = (INakedInterfaceRealization) element;
			realizations.remove(ir);
			ir.getContract().removeImplementingClassifier(this);
		}
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
	public boolean hasReceptionFor(INakedSignal signal){
		for(INakedReception r:getEffectiveReceptions()){
			if(r.getSignal().equals(signal)){
				return true;
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
}
