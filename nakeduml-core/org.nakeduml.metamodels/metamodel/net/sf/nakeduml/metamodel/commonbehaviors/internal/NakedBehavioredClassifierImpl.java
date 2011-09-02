package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedClassifierImpl;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;

public class NakedBehavioredClassifierImpl extends NakedClassifierImpl implements INakedBehavioredClassifier{
	protected List<INakedBehavior> ownedBehaviors = new ArrayList<INakedBehavior>();
	private INakedBehavior classifierBehavior;
	protected List<INakedInterfaceRealization> realizations = new ArrayList<INakedInterfaceRealization>();

	public INakedBehavior getClassifierBehavior() {
		if (this.classifierBehavior == null && hasSupertype()) {
			return ((INakedBehavioredClassifier) getSupertype()).getClassifierBehavior();
		} else {
			return this.classifierBehavior;
		}
	}
	public void setClassifierBehavior(INakedBehavior classifierBehavior) {
		this.classifierBehavior = classifierBehavior;
	}
	public List<INakedInterfaceRealization> getInterfaceRealizations(){
		return this.realizations;
	}
	public void addInterfaceRealization(INakedInterfaceRealization ir){
		this.realizations.add(ir);
		ir.getContract().addImplementingClassifier(this);
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
	public Collection<INakedOperation> getEffectiveOperations(){
		Collection<INakedOperation> effectiveOperations = super.getEffectiveOperations();
		for(INakedInterfaceRealization r:this.realizations){
			effectiveOperations.addAll(r.getContract().getEffectiveOperations());
		}
		return effectiveOperations;
	}
	@Override
	public Collection<INakedReception> getEffectiveReceptions(){
		Collection<INakedReception> effectiveReceptions= super.getEffectiveReceptions();
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
	public List getStates() {
		// Fakes region states
		List<INakedState> results = new ArrayList<INakedState>();
		if (this.classifierBehavior instanceof INakedStateMachine) {
			results.addAll(((INakedStateMachine) this.classifierBehavior).getAllStates());
		}
		// TODO implement something similar for activities with resting states
		return results;
	}
	@Override
	public Collection<IClassifier> getClassifiers() {
		Collection<IClassifier> classifiers = super.getClassifiers();
		classifiers.addAll(getOwnedBehaviors());
		return classifiers;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedBehavior){
			this.ownedBehaviors.add((INakedBehavior) element);
		}
	}
	public Collection<INakedBehavior> getOwnedBehaviors(){
		return this.ownedBehaviors;
	}
	public void removeOwnedElement(INakedElement element){
		super.removeOwnedElement(element);
		if(element instanceof INakedBehavior){
			this.ownedBehaviors.remove(element);
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
}
