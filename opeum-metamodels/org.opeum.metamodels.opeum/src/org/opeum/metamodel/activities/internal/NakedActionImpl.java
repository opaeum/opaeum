package org.opeum.metamodel.activities.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedActivityEdge;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.activities.INakedPin;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedConstraint;
import org.opeum.metamodel.core.INakedElement;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.oclengine.IOclContext;

public abstract class NakedActionImpl extends NakedExecutableNodeImpl implements INakedAction{
	private static final long serialVersionUID = 2697132216413111920L;
	public static final String META_CLASS = "action";
	private Collection<INakedConstraint> preConditions = new HashSet<INakedConstraint>();
	private Collection<INakedConstraint> postConditions = new HashSet<INakedConstraint>();
	@Override
	public boolean isLongRunning(){
		return false;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedConstraint && ((INakedConstraint) element).getSpecification() != null){
			INakedConstraint cnstr = (INakedConstraint) element;
			IOclContext oc = cnstr.getSpecification().getOclValue();
			if(oc.getType().equals(OclUsageType.PRE)){
				preConditions.remove(cnstr);
				preConditions.add(cnstr);
			}else{
				postConditions.remove(cnstr);
				postConditions.add(cnstr);
			}
		}
	};
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> ownedElements = super.getOwnedElements();
		ownedElements.addAll(getInput());
		ownedElements.addAll(getOutput());
		return ownedElements;
	}
	@Override
	public String getMetaClass(){
		return "action";
	}
	public boolean handlesException(){
		for(INakedInputPin pin:getInput()){
			if(pin.getIncomingExceptionHandler() != null){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean isImplicitFork(){
		if(super.isImplicitFork()){
			return true;
		}else{
			int pinOutputCount = 0;
			for(INakedOutputPin outputPin:getOutput()){
				// exceptions are not implicit forks
				if(!outputPin.isException() && outputPin.getOutgoing().size() > 0){
					pinOutputCount++;
					if(pinOutputCount > 1){
						return true;
					}
				}
			}
			return false;
		}
	}
	public boolean hasExceptions(){
		if(super.hasExceptions()){
			return true;
		}
		Collection<INakedOutputPin> output = getOutput();
		for(INakedOutputPin pin:output){
			if(pin.isException() && pin.getOutgoing().size() > 0){
				return true;
			}
		}
		return false;
	}
	@Override
	public Set<INakedActivityEdge> getAllEffectiveOutgoing(){
		Set<INakedActivityEdge> result = new HashSet<INakedActivityEdge>(super.getOutgoing());
		Collection<INakedOutputPin> output = getOutput();
		for(INakedOutputPin out:output){
			result.addAll(out.getAllEffectiveOutgoing());
		}
		return result;
	}
	@Override
	public Set<INakedActivityEdge> getAllEffectiveIncoming(){
		Set<INakedActivityEdge> result = new HashSet<INakedActivityEdge>(super.getIncoming());
		Collection<INakedInputPin> input = getInput();
		for(INakedInputPin in:input){
			result.addAll(in.getAllEffectiveIncoming());
		}
		return result;
	}
	public Collection<INakedConstraint> getPostConditions(){
		return this.postConditions;
	}
	public Collection<INakedConstraint> getPreConditions(){
		return this.preConditions;
	}
	public INakedClassifier getContext(){
		if(getActivity().getActivityKind().isSimpleSynchronousMethod()){
			return getActivity().getContext();
		}else{
			return getActivity();
		}
	}
	protected void removePins(List<? extends INakedPin> inputValues2){
		if(inputValues2 != null){
			for(INakedPin iNakedInputPin:inputValues2){
				removeOwnedElement(iNakedInputPin, true);
			}
		}
	}
	@Override
	public void removeOwnedElement(INakedElement element,boolean recursively){
		super.removeOwnedElement(element, recursively);
		if(element instanceof INakedConstraint){
			this.postConditions.remove(element);
			this.preConditions.remove(element);
		}
	}
}
