package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.oclengine.IOclContext;

import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.CodeGenerationStrategy;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.ParameterUtil;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.profiles.INakedProfile;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedTransition;

/**
 * Defines a common superclass for elements that: 1. Can be called as a method/operation/behavior 2. Has pre and post conditions expressed
 * in OCL 3. Could have a body expressed in OCL 4. Has Parameters and exceptions
 * 
 * @author ampie
 * @s
 * 
 */
public abstract class NakedBehaviorImpl extends NakedBehavioredClassifierImpl implements INakedBehavior{
	private static final long serialVersionUID = 2245169607437688948L;
	private Collection<INakedConstraint> preConditions = new HashSet<INakedConstraint>();
	private Collection<INakedConstraint> postConditions = new HashSet<INakedConstraint>();
	private INakedParameter returnParameter;
	private INakedOperation specification;
	private List<INakedParameter> argumentParameters = new ArrayList<INakedParameter>();
	private List<INakedParameter> resultParameters = new ArrayList<INakedParameter>();
	private List<INakedParameter> exceptionParameters = new ArrayList<INakedParameter>();
	public NakedBehaviorImpl(){
		super();
	}
	@Override
	public boolean isLongRunning(){
		return isProcess();
	}
	public List<IParameter> getParameters(){
		return new ArrayList<IParameter>(getArgumentParameters());
	}
	public boolean isPersistent(){
		return isProcess();
	}
	@Override
	public List<INakedProperty> getEffectiveAttributes(){
		List<INakedProperty> result = super.getEffectiveAttributes();
		return result;
	}
	public List<INakedParameter> getOwnedParameters(){
		List<INakedParameter> results = new ArrayList<INakedParameter>(getArgumentParameters());
		for(INakedParameter p:getResultParameters()){
			if(!getArgumentParameters().contains(p)){
				results.add(p);
			}
		}
		return results;
	}
	public final INakedOperation getSpecification(){
		return this.specification;
	}
	public void setSpecification(INakedOperation specification){
		this.specification = specification;
		if(specification != null && !specification.getMethods().contains(this)){
			specification.addMethod(this);
		}
	}
	public boolean hasMultipleConcurrentResults(){
		if(getOwnedAttributes().size() > 0){
			return true;
		}else{
			int i = 0;
			for(INakedParameter p:this.resultParameters){
				if(!p.isException()){
					i++;
				}
			}
			return i > 1;
		}
	}
	public List<INakedParameter> getResultParameters(){
		return this.resultParameters;
	}
	public List<INakedParameter> getArgumentParameters(){
		return this.argumentParameters;
	}
	public List<INakedParameter> getExceptionParameters(){
		return this.exceptionParameters;
	}
	public IClassifier getReturnType(){
		if(getReturnParameter() == null){
			return null;
		}else{
			return getReturnParameter().getType();
		}
	}
	public Collection<INakedConstraint> getPostConditions(){
		return this.postConditions;
	}
	public void setPostConditions(Collection<INakedConstraint> postConditions){
		this.postConditions = postConditions;
	}
	public Collection<INakedConstraint> getPreConditions(){
		return this.preConditions;
	}
	public void setPreConditions(Collection<INakedConstraint> preConditions){
		this.preConditions = preConditions;
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
			}else if(oc.getType().equals(OclUsageType.POST)){
				preConditions.remove(cnstr);
				preConditions.add(cnstr);
			}
		}
	}
	@Override
	public CodeGenerationStrategy getCodeGenerationStrategy(){
		if(super.getCodeGenerationStrategy() == null){
			if(getOwnerElement() instanceof INakedTransition){
				return ((INakedTransition) getOwnerElement()).getStateMachine().getCodeGenerationStrategy();
			}else if(getOwnerElement() instanceof INakedState){
				return ((INakedState) getOwnerElement()).getStateMachine().getCodeGenerationStrategy();
			}else{
				return CodeGenerationStrategy.ALL;
			}
		}
		return super.getCodeGenerationStrategy();
	}
	public INakedParameter getReturnParameter(){
		if(returnParameter == null){
			for(INakedParameter parm:getOwnedParameters()){
				if(!parm.isException() && parm.isResult()){
					return parm;
				}
			}
		}
		return this.returnParameter;
	}
	public INakedBehavioredClassifier getContext(){
		// NB!! Behaviours can be owned by transitions or directly by packages
		INakedElementOwner owner = getOwnerElement();
		INakedBehavioredClassifier result = null;
		if(owner instanceof INakedActivity && ((INakedActivity) owner).getActivityKind() == ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			result = ((INakedActivity) owner).getContext();
		}else if(owner instanceof INakedBehavioredClassifier){
			result = (INakedBehavioredClassifier) owner;
		}else if((owner instanceof INakedTransition)){
			result = ((INakedTransition) owner).getStateMachine().getContext();
		}else if(owner instanceof INakedState){
			result = ((INakedState) owner).getStateMachine().getContext();
		}
		if(result == null){
			while(true){
				if(owner instanceof INakedBehavioredClassifier){
					result = (INakedBehavioredClassifier) owner;
					break;
				}else if(owner instanceof INakedElement){
					owner = ((INakedElement) owner).getOwnerElement();
				}else{
					break;
				}
			}
		}
		return result;
	}
	@Override
	public INakedBehavioredClassifier getOwner(){
		// Deprecated, implemented only for Octopus;
		if(getContext() != null){
			return getContext();
		}else{
			INakedElement element = (INakedElement) getOwnerElement();
			while(!(element instanceof INakedBehavioredClassifier || element instanceof INakedModel || element instanceof INakedProfile)){
				element = (INakedElement) element.getOwnerElement();
			}
			if(element instanceof INakedBehavioredClassifier){
				return (INakedBehavioredClassifier) element;
			}
			return null;
		}
	}
	@Override
	public INakedNameSpace getNameSpace(){
		// NB!! Behaviours can be owned by transitions that are not namespaces
		INakedElementOwner owner = getOwnerElement();
		while(!(owner instanceof INakedNameSpace) && owner instanceof INakedElement){
			owner = ((INakedElement) owner).getOwnerElement();
		}
		return (INakedNameSpace) owner;
	}
	@Override
	protected boolean isNamedMember(INakedElement e){
		return super.isNamedMember(e) || e instanceof INakedMultiplicityElement;
	}
	public boolean isClassifierBehavior(){
		// TODO this assumes that the Behavior is always nested inside the
		// classifier it is behavior for
		if(getSpecification() == null && getContext() instanceof INakedEntity){
			return equals(((INakedEntity) getContext()).getClassifierBehavior());
		}
		return false;
	}
	@Override
	public void recalculateParameterPositions(){
		this.argumentParameters.clear();
		this.exceptionParameters.clear();
		this.resultParameters.clear();
		for(INakedElement e:getOwnedElements()){
			if(e instanceof INakedParameter){
				INakedParameter p = (INakedParameter) e;
				if(p.isReturn()){
					this.returnParameter = p;
				}
				ParameterUtil.addParameterTolist(p, p.getArgumentIndex(), this.argumentParameters);
				ParameterUtil.addParameterTolist(p, p.getExceptionIndex(), this.exceptionParameters);
				ParameterUtil.addParameterTolist(p, p.getResultIndex(), this.resultParameters);
			}
		}
	}
	@Override
	public void reinitialize(){
		
	}
}
